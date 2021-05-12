package androidx.renderscript;

import android.os.Build;
import androidx.renderscript.Script;

public class ScriptIntrinsicResize extends ScriptIntrinsic {
    private static final int INTRINSIC_API_LEVEL = 21;
    private Allocation mInput;

    protected ScriptIntrinsicResize(long j, RenderScript renderScript) {
        super(j, renderScript);
    }

    public static ScriptIntrinsicResize create(RenderScript renderScript) {
        boolean z = renderScript.isUseNative() && Build.VERSION.SDK_INT < 21;
        ScriptIntrinsicResize scriptIntrinsicResize = new ScriptIntrinsicResize(renderScript.nScriptIntrinsicCreate(12, 0, z), renderScript);
        scriptIntrinsicResize.setIncSupp(z);
        return scriptIntrinsicResize;
    }

    public void setInput(Allocation allocation) {
        Element element = allocation.getElement();
        if (element.isCompatible(Element.U8(this.mRS)) || element.isCompatible(Element.U8_2(this.mRS)) || element.isCompatible(Element.U8_3(this.mRS)) || element.isCompatible(Element.U8_4(this.mRS)) || element.isCompatible(Element.F32(this.mRS)) || element.isCompatible(Element.F32_2(this.mRS)) || element.isCompatible(Element.F32_3(this.mRS)) || element.isCompatible(Element.F32_4(this.mRS))) {
            this.mInput = allocation;
            setVar(0, (BaseObj) allocation);
            return;
        }
        throw new RSIllegalArgumentException("Unsupported element type.");
    }

    public Script.FieldID getFieldID_Input() {
        return createFieldID(0, (Element) null);
    }

    public void forEach_bicubic(Allocation allocation) {
        if (allocation != this.mInput) {
            forEach_bicubic(allocation, (Script.LaunchOptions) null);
            return;
        }
        throw new RSIllegalArgumentException("Output cannot be same as Input.");
    }

    public void forEach_bicubic(Allocation allocation, Script.LaunchOptions launchOptions) {
        forEach(0, (Allocation) null, allocation, (FieldPacker) null, launchOptions);
    }

    public Script.KernelID getKernelID_bicubic() {
        return createKernelID(0, 2, (Element) null, (Element) null);
    }
}
