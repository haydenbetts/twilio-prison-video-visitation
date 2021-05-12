package androidx.renderscript;

import android.renderscript.Script;
import android.util.SparseArray;
import java.io.UnsupportedEncodingException;

public class Script extends BaseObj {
    private final SparseArray<FieldID> mFIDs = new SparseArray<>();
    private final SparseArray<InvokeID> mIIDs = new SparseArray<>();
    private final SparseArray<KernelID> mKIDs = new SparseArray<>();
    private boolean mUseIncSupp = false;

    /* access modifiers changed from: protected */
    public void setIncSupp(boolean z) {
        this.mUseIncSupp = z;
    }

    /* access modifiers changed from: protected */
    public boolean isIncSupp() {
        return this.mUseIncSupp;
    }

    /* access modifiers changed from: package-private */
    public long getDummyAlloc(Allocation allocation) {
        if (allocation == null) {
            return 0;
        }
        Type type = allocation.getType();
        long nIncAllocationCreateTyped = this.mRS.nIncAllocationCreateTyped(allocation.getID(this.mRS), type.getDummyType(this.mRS, type.getElement().getDummyElement(this.mRS)), type.getX() * type.getElement().getBytesSize());
        allocation.setIncAllocID(nIncAllocationCreateTyped);
        return nIncAllocationCreateTyped;
    }

    public static final class KernelID extends BaseObj {
        Script.KernelID mN;
        Script mScript;
        int mSig;
        int mSlot;

        KernelID(long j, RenderScript renderScript, Script script, int i, int i2) {
            super(j, renderScript);
            this.mScript = script;
            this.mSlot = i;
            this.mSig = i2;
        }
    }

    /* access modifiers changed from: protected */
    public KernelID createKernelID(int i, int i2, Element element, Element element2) {
        KernelID kernelID = this.mKIDs.get(i);
        if (kernelID != null) {
            return kernelID;
        }
        long nScriptKernelIDCreate = this.mRS.nScriptKernelIDCreate(getID(this.mRS), i, i2, this.mUseIncSupp);
        if (nScriptKernelIDCreate != 0) {
            KernelID kernelID2 = new KernelID(nScriptKernelIDCreate, this.mRS, this, i, i2);
            this.mKIDs.put(i, kernelID2);
            return kernelID2;
        }
        throw new RSDriverException("Failed to create KernelID");
    }

    public static final class InvokeID extends BaseObj {
        Script mScript;
        int mSlot;

        InvokeID(long j, RenderScript renderScript, Script script, int i) {
            super(j, renderScript);
            this.mScript = script;
            this.mSlot = i;
        }
    }

    /* access modifiers changed from: protected */
    public InvokeID createInvokeID(int i) {
        InvokeID invokeID = this.mIIDs.get(i);
        if (invokeID != null) {
            return invokeID;
        }
        long nScriptInvokeIDCreate = this.mRS.nScriptInvokeIDCreate(getID(this.mRS), i);
        if (nScriptInvokeIDCreate != 0) {
            InvokeID invokeID2 = new InvokeID(nScriptInvokeIDCreate, this.mRS, this, i);
            this.mIIDs.put(i, invokeID2);
            return invokeID2;
        }
        throw new RSDriverException("Failed to create KernelID");
    }

    public static final class FieldID extends BaseObj {
        Script.FieldID mN;
        Script mScript;
        int mSlot;

        FieldID(long j, RenderScript renderScript, Script script, int i) {
            super(j, renderScript);
            this.mScript = script;
            this.mSlot = i;
        }
    }

    /* access modifiers changed from: protected */
    public FieldID createFieldID(int i, Element element) {
        FieldID fieldID = this.mFIDs.get(i);
        if (fieldID != null) {
            return fieldID;
        }
        long nScriptFieldIDCreate = this.mRS.nScriptFieldIDCreate(getID(this.mRS), i, this.mUseIncSupp);
        if (nScriptFieldIDCreate != 0) {
            FieldID fieldID2 = new FieldID(nScriptFieldIDCreate, this.mRS, this, i);
            this.mFIDs.put(i, fieldID2);
            return fieldID2;
        }
        throw new RSDriverException("Failed to create FieldID");
    }

    /* access modifiers changed from: protected */
    public void invoke(int i) {
        this.mRS.nScriptInvoke(getID(this.mRS), i, this.mUseIncSupp);
    }

    /* access modifiers changed from: protected */
    public void invoke(int i, FieldPacker fieldPacker) {
        if (fieldPacker != null) {
            this.mRS.nScriptInvokeV(getID(this.mRS), i, fieldPacker.getData(), this.mUseIncSupp);
            return;
        }
        this.mRS.nScriptInvoke(getID(this.mRS), i, this.mUseIncSupp);
    }

    public void bindAllocation(Allocation allocation, int i) {
        Allocation allocation2 = allocation;
        this.mRS.validate();
        if (allocation2 != null) {
            this.mRS.nScriptBindAllocation(getID(this.mRS), allocation2.getID(this.mRS), i, this.mUseIncSupp);
            return;
        }
        this.mRS.nScriptBindAllocation(getID(this.mRS), 0, i, this.mUseIncSupp);
    }

    public void setTimeZone(String str) {
        this.mRS.validate();
        try {
            this.mRS.nScriptSetTimeZone(getID(this.mRS), str.getBytes("UTF-8"), this.mUseIncSupp);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: protected */
    public void forEach(int i, Allocation allocation, Allocation allocation2, FieldPacker fieldPacker) {
        Allocation allocation3 = allocation;
        Allocation allocation4 = allocation2;
        if (allocation3 == null && allocation4 == null) {
            throw new RSIllegalArgumentException("At least one of ain or aout is required to be non-null.");
        }
        long j = 0;
        long id = allocation3 != null ? allocation3.getID(this.mRS) : 0;
        if (allocation4 != null) {
            j = allocation4.getID(this.mRS);
        }
        long j2 = j;
        byte[] bArr = null;
        if (fieldPacker != null) {
            bArr = fieldPacker.getData();
        }
        byte[] bArr2 = bArr;
        if (this.mUseIncSupp) {
            this.mRS.nScriptForEach(getID(this.mRS), i, getDummyAlloc(allocation3), getDummyAlloc(allocation4), bArr2, this.mUseIncSupp);
        } else {
            this.mRS.nScriptForEach(getID(this.mRS), i, id, j2, bArr2, this.mUseIncSupp);
        }
    }

    /* access modifiers changed from: protected */
    public void forEach(int i, Allocation allocation, Allocation allocation2, FieldPacker fieldPacker, LaunchOptions launchOptions) {
        Allocation allocation3 = allocation;
        Allocation allocation4 = allocation2;
        if (allocation3 == null && allocation4 == null) {
            throw new RSIllegalArgumentException("At least one of ain or aout is required to be non-null.");
        } else if (launchOptions == null) {
            forEach(i, allocation, allocation2, fieldPacker);
        } else {
            long j = 0;
            long id = allocation3 != null ? allocation3.getID(this.mRS) : 0;
            if (allocation4 != null) {
                j = allocation4.getID(this.mRS);
            }
            long j2 = j;
            byte[] bArr = null;
            if (fieldPacker != null) {
                bArr = fieldPacker.getData();
            }
            byte[] bArr2 = bArr;
            if (this.mUseIncSupp) {
                this.mRS.nScriptForEachClipped(getID(this.mRS), i, getDummyAlloc(allocation3), getDummyAlloc(allocation4), bArr2, launchOptions.xstart, launchOptions.xend, launchOptions.ystart, launchOptions.yend, launchOptions.zstart, launchOptions.zend, this.mUseIncSupp);
                return;
            }
            this.mRS.nScriptForEachClipped(getID(this.mRS), i, id, j2, bArr2, launchOptions.xstart, launchOptions.xend, launchOptions.ystart, launchOptions.yend, launchOptions.zstart, launchOptions.zend, this.mUseIncSupp);
        }
    }

    Script(long j, RenderScript renderScript) {
        super(j, renderScript);
    }

    /* access modifiers changed from: protected */
    public void forEach(int i, Allocation[] allocationArr, Allocation allocation, FieldPacker fieldPacker) {
        forEach(i, allocationArr, allocation, fieldPacker, (LaunchOptions) null);
    }

    /* access modifiers changed from: protected */
    public void forEach(int i, Allocation[] allocationArr, Allocation allocation, FieldPacker fieldPacker, LaunchOptions launchOptions) {
        long[] jArr;
        Allocation[] allocationArr2 = allocationArr;
        Allocation allocation2 = allocation;
        this.mRS.validate();
        if (allocationArr2 != null) {
            for (Allocation validateObject : allocationArr2) {
                this.mRS.validateObject(validateObject);
            }
        }
        this.mRS.validateObject(allocation2);
        if (allocationArr2 == null && allocation2 == null) {
            throw new RSIllegalArgumentException("At least one of ain or aout is required to be non-null.");
        }
        int[] iArr = null;
        if (allocationArr2 != null) {
            long[] jArr2 = new long[allocationArr2.length];
            for (int i2 = 0; i2 < allocationArr2.length; i2++) {
                jArr2[i2] = allocationArr2[i2].getID(this.mRS);
            }
            jArr = jArr2;
        } else {
            jArr = null;
        }
        long id = allocation2 != null ? allocation2.getID(this.mRS) : 0;
        byte[] data = fieldPacker != null ? fieldPacker.getData() : null;
        if (launchOptions != null) {
            iArr = new int[]{launchOptions.xstart, launchOptions.xend, launchOptions.ystart, launchOptions.yend, launchOptions.zstart, launchOptions.zend};
        }
        this.mRS.nScriptForEach(getID(this.mRS), i, jArr, id, data, iArr);
    }

    /* access modifiers changed from: protected */
    public void reduce(int i, Allocation[] allocationArr, Allocation allocation, LaunchOptions launchOptions) {
        this.mRS.validate();
        if (allocationArr == null || allocationArr.length < 1) {
            throw new RSIllegalArgumentException("At least one input is required.");
        } else if (allocation != null) {
            for (Allocation validateObject : allocationArr) {
                this.mRS.validateObject(validateObject);
            }
            long[] jArr = new long[allocationArr.length];
            for (int i2 = 0; i2 < allocationArr.length; i2++) {
                jArr[i2] = allocationArr[i2].getID(this.mRS);
            }
            long id = allocation.getID(this.mRS);
            int[] iArr = null;
            if (launchOptions != null) {
                iArr = new int[]{launchOptions.xstart, launchOptions.xend, launchOptions.ystart, launchOptions.yend, launchOptions.zstart, launchOptions.zend};
            }
            this.mRS.nScriptReduce(getID(this.mRS), i, jArr, id, iArr);
        } else {
            throw new RSIllegalArgumentException("aout is required to be non-null.");
        }
    }

    public void setVar(int i, float f) {
        this.mRS.nScriptSetVarF(getID(this.mRS), i, f, this.mUseIncSupp);
    }

    public void setVar(int i, double d) {
        this.mRS.nScriptSetVarD(getID(this.mRS), i, d, this.mUseIncSupp);
    }

    public void setVar(int i, int i2) {
        this.mRS.nScriptSetVarI(getID(this.mRS), i, i2, this.mUseIncSupp);
    }

    public void setVar(int i, long j) {
        this.mRS.nScriptSetVarJ(getID(this.mRS), i, j, this.mUseIncSupp);
    }

    public void setVar(int i, boolean z) {
        this.mRS.nScriptSetVarI(getID(this.mRS), i, z ? 1 : 0, this.mUseIncSupp);
    }

    public void setVar(int i, BaseObj baseObj) {
        BaseObj baseObj2 = baseObj;
        long j = 0;
        if (this.mUseIncSupp) {
            this.mRS.nScriptSetVarObj(getID(this.mRS), i, baseObj2 == null ? 0 : getDummyAlloc((Allocation) baseObj2), this.mUseIncSupp);
            return;
        }
        RenderScript renderScript = this.mRS;
        long id = getID(this.mRS);
        if (baseObj2 != null) {
            j = baseObj2.getID(this.mRS);
        }
        renderScript.nScriptSetVarObj(id, i, j, this.mUseIncSupp);
    }

    public void setVar(int i, FieldPacker fieldPacker) {
        this.mRS.nScriptSetVarV(getID(this.mRS), i, fieldPacker.getData(), this.mUseIncSupp);
    }

    public void setVar(int i, FieldPacker fieldPacker, Element element, int[] iArr) {
        Element element2 = element;
        if (this.mUseIncSupp) {
            int i2 = i;
            this.mRS.nScriptSetVarVE(getID(this.mRS), i2, fieldPacker.getData(), element2.getDummyElement(this.mRS), iArr, this.mUseIncSupp);
            return;
        }
        this.mRS.nScriptSetVarVE(getID(this.mRS), i, fieldPacker.getData(), element2.getID(this.mRS), iArr, this.mUseIncSupp);
    }

    public static class Builder {
        RenderScript mRS;

        Builder(RenderScript renderScript) {
            this.mRS = renderScript;
        }
    }

    public static class FieldBase {
        protected Allocation mAllocation;
        protected Element mElement;

        public void updateAllocation() {
        }

        /* access modifiers changed from: protected */
        public void init(RenderScript renderScript, int i) {
            this.mAllocation = Allocation.createSized(renderScript, this.mElement, i, 1);
        }

        /* access modifiers changed from: protected */
        public void init(RenderScript renderScript, int i, int i2) {
            this.mAllocation = Allocation.createSized(renderScript, this.mElement, i, i2 | 1);
        }

        protected FieldBase() {
        }

        public Element getElement() {
            return this.mElement;
        }

        public Type getType() {
            return this.mAllocation.getType();
        }

        public Allocation getAllocation() {
            return this.mAllocation;
        }
    }

    public static final class LaunchOptions {
        private int strategy;
        /* access modifiers changed from: private */
        public int xend = 0;
        /* access modifiers changed from: private */
        public int xstart = 0;
        /* access modifiers changed from: private */
        public int yend = 0;
        /* access modifiers changed from: private */
        public int ystart = 0;
        /* access modifiers changed from: private */
        public int zend = 0;
        /* access modifiers changed from: private */
        public int zstart = 0;

        public LaunchOptions setX(int i, int i2) {
            if (i < 0 || i2 <= i) {
                throw new RSIllegalArgumentException("Invalid dimensions");
            }
            this.xstart = i;
            this.xend = i2;
            return this;
        }

        public LaunchOptions setY(int i, int i2) {
            if (i < 0 || i2 <= i) {
                throw new RSIllegalArgumentException("Invalid dimensions");
            }
            this.ystart = i;
            this.yend = i2;
            return this;
        }

        public LaunchOptions setZ(int i, int i2) {
            if (i < 0 || i2 <= i) {
                throw new RSIllegalArgumentException("Invalid dimensions");
            }
            this.zstart = i;
            this.zend = i2;
            return this;
        }

        public int getXStart() {
            return this.xstart;
        }

        public int getXEnd() {
            return this.xend;
        }

        public int getYStart() {
            return this.ystart;
        }

        public int getYEnd() {
            return this.yend;
        }

        public int getZStart() {
            return this.zstart;
        }

        public int getZEnd() {
            return this.zend;
        }
    }
}
