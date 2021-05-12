package org.bytedeco.javacpp.tools;

import java.lang.management.BufferPoolMXBean;
import java.lang.management.ManagementFactory;
import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import org.bytedeco.javacpp.Pointer;

public class PointerBufferPoolMXBean implements BufferPoolMXBean {
    private static final String JAVACPP_MXBEAN_NAME = "javacpp";
    private static final Logger LOGGER = Logger.create(PointerBufferPoolMXBean.class);
    private static final ObjectName OBJECT_NAME;

    public String getName() {
        return JAVACPP_MXBEAN_NAME;
    }

    static {
        ObjectName objectName;
        try {
            objectName = new ObjectName("java.nio:type=BufferPool,name=javacpp");
        } catch (MalformedObjectNameException unused) {
            LOGGER.warn("Could not create OBJECT_NAME for javacpp");
            objectName = null;
        }
        OBJECT_NAME = objectName;
    }

    public ObjectName getObjectName() {
        return OBJECT_NAME;
    }

    public long getCount() {
        return Pointer.totalCount();
    }

    public long getTotalCapacity() {
        return Pointer.maxPhysicalBytes();
    }

    public long getMemoryUsed() {
        return Pointer.totalBytes();
    }

    public static void register() {
        ObjectName objectName = OBJECT_NAME;
        if (objectName != null) {
            try {
                ManagementFactory.getPlatformMBeanServer().registerMBean(new PointerBufferPoolMXBean(), objectName);
            } catch (InstanceAlreadyExistsException | MBeanRegistrationException | NotCompliantMBeanException unused) {
                LOGGER.warn("Could not register javacpp BufferPoolMXBean");
            }
        }
    }
}
