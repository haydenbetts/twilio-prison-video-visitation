package retrofit2;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import okhttp3.Request;
import okio.Timeout;
import retrofit2.CallAdapter;

final class DefaultCallAdapterFactory extends CallAdapter.Factory {
    @Nullable
    private final Executor callbackExecutor;

    DefaultCallAdapterFactory(@Nullable Executor executor) {
        this.callbackExecutor = executor;
    }

    @Nullable
    public CallAdapter<?, ?> get(Type type, Annotation[] annotationArr, Retrofit retrofit) {
        final Executor executor = null;
        if (getRawType(type) != Call.class) {
            return null;
        }
        if (type instanceof ParameterizedType) {
            final Type parameterUpperBound = Utils.getParameterUpperBound(0, (ParameterizedType) type);
            if (!Utils.isAnnotationPresent(annotationArr, SkipCallbackExecutor.class)) {
                executor = this.callbackExecutor;
            }
            return new CallAdapter<Object, Call<?>>() {
                public Type responseType() {
                    return parameterUpperBound;
                }

                public Call<Object> adapt(Call<Object> call) {
                    Executor executor = executor;
                    return executor == null ? call : new ExecutorCallbackCall(executor, call);
                }
            };
        }
        throw new IllegalArgumentException("Call return type must be parameterized as Call<Foo> or Call<? extends Foo>");
    }

    static final class ExecutorCallbackCall<T> implements Call<T> {
        final Executor callbackExecutor;
        final Call<T> delegate;

        ExecutorCallbackCall(Executor executor, Call<T> call) {
            this.callbackExecutor = executor;
            this.delegate = call;
        }

        public void enqueue(final Callback<T> callback) {
            Objects.requireNonNull(callback, "callback == null");
            this.delegate.enqueue(new Callback<T>() {
                public void onResponse(Call<T> call, Response<T> response) {
                    ExecutorCallbackCall.this.callbackExecutor.execute(
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000b: INVOKE  
                          (wrap: java.util.concurrent.Executor : 0x0002: IGET  (r3v2 java.util.concurrent.Executor) = 
                          (wrap: retrofit2.DefaultCallAdapterFactory$ExecutorCallbackCall : 0x0000: IGET  (r3v1 retrofit2.DefaultCallAdapterFactory$ExecutorCallbackCall) = 
                          (r2v0 'this' retrofit2.DefaultCallAdapterFactory$ExecutorCallbackCall$1 A[THIS])
                         retrofit2.DefaultCallAdapterFactory.ExecutorCallbackCall.1.this$0 retrofit2.DefaultCallAdapterFactory$ExecutorCallbackCall)
                         retrofit2.DefaultCallAdapterFactory.ExecutorCallbackCall.callbackExecutor java.util.concurrent.Executor)
                          (wrap: retrofit2.-$$Lambda$DefaultCallAdapterFactory$ExecutorCallbackCall$1$hVGjmafRi6VitDIrPNdoFizVAdk : 0x0008: CONSTRUCTOR  (r1v0 retrofit2.-$$Lambda$DefaultCallAdapterFactory$ExecutorCallbackCall$1$hVGjmafRi6VitDIrPNdoFizVAdk) = 
                          (r2v0 'this' retrofit2.DefaultCallAdapterFactory$ExecutorCallbackCall$1 A[THIS])
                          (wrap: retrofit2.Callback : 0x0004: IGET  (r0v0 retrofit2.Callback) = 
                          (r2v0 'this' retrofit2.DefaultCallAdapterFactory$ExecutorCallbackCall$1 A[THIS])
                         retrofit2.DefaultCallAdapterFactory.ExecutorCallbackCall.1.val$callback retrofit2.Callback)
                          (r4v0 'response' retrofit2.Response<T>)
                         call: retrofit2.-$$Lambda$DefaultCallAdapterFactory$ExecutorCallbackCall$1$hVGjmafRi6VitDIrPNdoFizVAdk.<init>(retrofit2.DefaultCallAdapterFactory$ExecutorCallbackCall$1, retrofit2.Callback, retrofit2.Response):void type: CONSTRUCTOR)
                         java.util.concurrent.Executor.execute(java.lang.Runnable):void type: INTERFACE in method: retrofit2.DefaultCallAdapterFactory.ExecutorCallbackCall.1.onResponse(retrofit2.Call, retrofit2.Response):void, dex: classes4.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                        	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                        	at java.util.ArrayList.forEach(ArrayList.java:1259)
                        	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                        	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                        	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:483)
                        	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
                        	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                        	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                        	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                        	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:485)
                        	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                        	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:676)
                        	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:607)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                        	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                        	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                        	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                        	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                        	at java.util.ArrayList.forEach(ArrayList.java:1259)
                        	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                        	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                        	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:483)
                        	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
                        	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                        	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                        	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                        	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:485)
                        	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                        	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                        	at jadx.core.codegen.ClassGen.addInnerClass(ClassGen.java:249)
                        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:238)
                        	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                        	at java.util.ArrayList.forEach(ArrayList.java:1259)
                        	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                        	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                        	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:483)
                        	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
                        	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                        	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                        	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                        	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:485)
                        	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                        	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                        	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                        	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                        	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                        	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                        Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0008: CONSTRUCTOR  (r1v0 retrofit2.-$$Lambda$DefaultCallAdapterFactory$ExecutorCallbackCall$1$hVGjmafRi6VitDIrPNdoFizVAdk) = 
                          (r2v0 'this' retrofit2.DefaultCallAdapterFactory$ExecutorCallbackCall$1 A[THIS])
                          (wrap: retrofit2.Callback : 0x0004: IGET  (r0v0 retrofit2.Callback) = 
                          (r2v0 'this' retrofit2.DefaultCallAdapterFactory$ExecutorCallbackCall$1 A[THIS])
                         retrofit2.DefaultCallAdapterFactory.ExecutorCallbackCall.1.val$callback retrofit2.Callback)
                          (r4v0 'response' retrofit2.Response<T>)
                         call: retrofit2.-$$Lambda$DefaultCallAdapterFactory$ExecutorCallbackCall$1$hVGjmafRi6VitDIrPNdoFizVAdk.<init>(retrofit2.DefaultCallAdapterFactory$ExecutorCallbackCall$1, retrofit2.Callback, retrofit2.Response):void type: CONSTRUCTOR in method: retrofit2.DefaultCallAdapterFactory.ExecutorCallbackCall.1.onResponse(retrofit2.Call, retrofit2.Response):void, dex: classes4.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                        	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                        	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                        	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                        	... 76 more
                        Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: retrofit2.-$$Lambda$DefaultCallAdapterFactory$ExecutorCallbackCall$1$hVGjmafRi6VitDIrPNdoFizVAdk, state: NOT_LOADED
                        	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:260)
                        	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:606)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                        	... 82 more
                        */
                    /*
                        this = this;
                        retrofit2.DefaultCallAdapterFactory$ExecutorCallbackCall r3 = retrofit2.DefaultCallAdapterFactory.ExecutorCallbackCall.this
                        java.util.concurrent.Executor r3 = r3.callbackExecutor
                        retrofit2.Callback r0 = r3
                        retrofit2.-$$Lambda$DefaultCallAdapterFactory$ExecutorCallbackCall$1$hVGjmafRi6VitDIrPNdoFizVAdk r1 = new retrofit2.-$$Lambda$DefaultCallAdapterFactory$ExecutorCallbackCall$1$hVGjmafRi6VitDIrPNdoFizVAdk
                        r1.<init>(r2, r0, r4)
                        r3.execute(r1)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: retrofit2.DefaultCallAdapterFactory.ExecutorCallbackCall.AnonymousClass1.onResponse(retrofit2.Call, retrofit2.Response):void");
                }

                public /* synthetic */ void lambda$onResponse$0$DefaultCallAdapterFactory$ExecutorCallbackCall$1(Callback callback, Response response) {
                    if (ExecutorCallbackCall.this.delegate.isCanceled()) {
                        callback.onFailure(ExecutorCallbackCall.this, new IOException("Canceled"));
                    } else {
                        callback.onResponse(ExecutorCallbackCall.this, response);
                    }
                }

                public /* synthetic */ void lambda$onFailure$1$DefaultCallAdapterFactory$ExecutorCallbackCall$1(Callback callback, Throwable th) {
                    callback.onFailure(ExecutorCallbackCall.this, th);
                }

                public void onFailure(Call<T> call, Throwable th) {
                    ExecutorCallbackCall.this.callbackExecutor.execute(
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000b: INVOKE  
                          (wrap: java.util.concurrent.Executor : 0x0002: IGET  (r3v2 java.util.concurrent.Executor) = 
                          (wrap: retrofit2.DefaultCallAdapterFactory$ExecutorCallbackCall : 0x0000: IGET  (r3v1 retrofit2.DefaultCallAdapterFactory$ExecutorCallbackCall) = 
                          (r2v0 'this' retrofit2.DefaultCallAdapterFactory$ExecutorCallbackCall$1 A[THIS])
                         retrofit2.DefaultCallAdapterFactory.ExecutorCallbackCall.1.this$0 retrofit2.DefaultCallAdapterFactory$ExecutorCallbackCall)
                         retrofit2.DefaultCallAdapterFactory.ExecutorCallbackCall.callbackExecutor java.util.concurrent.Executor)
                          (wrap: retrofit2.-$$Lambda$DefaultCallAdapterFactory$ExecutorCallbackCall$1$G9BY9eQQk64nBfFjfIpx-YzJzUo : 0x0008: CONSTRUCTOR  (r1v0 retrofit2.-$$Lambda$DefaultCallAdapterFactory$ExecutorCallbackCall$1$G9BY9eQQk64nBfFjfIpx-YzJzUo) = 
                          (r2v0 'this' retrofit2.DefaultCallAdapterFactory$ExecutorCallbackCall$1 A[THIS])
                          (wrap: retrofit2.Callback : 0x0004: IGET  (r0v0 retrofit2.Callback) = 
                          (r2v0 'this' retrofit2.DefaultCallAdapterFactory$ExecutorCallbackCall$1 A[THIS])
                         retrofit2.DefaultCallAdapterFactory.ExecutorCallbackCall.1.val$callback retrofit2.Callback)
                          (r4v0 'th' java.lang.Throwable)
                         call: retrofit2.-$$Lambda$DefaultCallAdapterFactory$ExecutorCallbackCall$1$G9BY9eQQk64nBfFjfIpx-YzJzUo.<init>(retrofit2.DefaultCallAdapterFactory$ExecutorCallbackCall$1, retrofit2.Callback, java.lang.Throwable):void type: CONSTRUCTOR)
                         java.util.concurrent.Executor.execute(java.lang.Runnable):void type: INTERFACE in method: retrofit2.DefaultCallAdapterFactory.ExecutorCallbackCall.1.onFailure(retrofit2.Call, java.lang.Throwable):void, dex: classes4.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                        	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                        	at java.util.ArrayList.forEach(ArrayList.java:1259)
                        	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                        	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                        	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:483)
                        	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
                        	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                        	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                        	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                        	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:485)
                        	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                        	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:676)
                        	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:607)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                        	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                        	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                        	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                        	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                        	at java.util.ArrayList.forEach(ArrayList.java:1259)
                        	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                        	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                        	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:483)
                        	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
                        	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                        	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                        	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                        	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:485)
                        	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                        	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                        	at jadx.core.codegen.ClassGen.addInnerClass(ClassGen.java:249)
                        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:238)
                        	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                        	at java.util.ArrayList.forEach(ArrayList.java:1259)
                        	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                        	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                        	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:483)
                        	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
                        	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                        	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                        	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                        	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:485)
                        	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                        	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                        	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                        	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                        	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                        	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                        Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0008: CONSTRUCTOR  (r1v0 retrofit2.-$$Lambda$DefaultCallAdapterFactory$ExecutorCallbackCall$1$G9BY9eQQk64nBfFjfIpx-YzJzUo) = 
                          (r2v0 'this' retrofit2.DefaultCallAdapterFactory$ExecutorCallbackCall$1 A[THIS])
                          (wrap: retrofit2.Callback : 0x0004: IGET  (r0v0 retrofit2.Callback) = 
                          (r2v0 'this' retrofit2.DefaultCallAdapterFactory$ExecutorCallbackCall$1 A[THIS])
                         retrofit2.DefaultCallAdapterFactory.ExecutorCallbackCall.1.val$callback retrofit2.Callback)
                          (r4v0 'th' java.lang.Throwable)
                         call: retrofit2.-$$Lambda$DefaultCallAdapterFactory$ExecutorCallbackCall$1$G9BY9eQQk64nBfFjfIpx-YzJzUo.<init>(retrofit2.DefaultCallAdapterFactory$ExecutorCallbackCall$1, retrofit2.Callback, java.lang.Throwable):void type: CONSTRUCTOR in method: retrofit2.DefaultCallAdapterFactory.ExecutorCallbackCall.1.onFailure(retrofit2.Call, java.lang.Throwable):void, dex: classes4.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                        	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                        	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                        	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                        	... 76 more
                        Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: retrofit2.-$$Lambda$DefaultCallAdapterFactory$ExecutorCallbackCall$1$G9BY9eQQk64nBfFjfIpx-YzJzUo, state: NOT_LOADED
                        	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:260)
                        	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:606)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                        	... 82 more
                        */
                    /*
                        this = this;
                        retrofit2.DefaultCallAdapterFactory$ExecutorCallbackCall r3 = retrofit2.DefaultCallAdapterFactory.ExecutorCallbackCall.this
                        java.util.concurrent.Executor r3 = r3.callbackExecutor
                        retrofit2.Callback r0 = r3
                        retrofit2.-$$Lambda$DefaultCallAdapterFactory$ExecutorCallbackCall$1$G9BY9eQQk64nBfFjfIpx-YzJzUo r1 = new retrofit2.-$$Lambda$DefaultCallAdapterFactory$ExecutorCallbackCall$1$G9BY9eQQk64nBfFjfIpx-YzJzUo
                        r1.<init>(r2, r0, r4)
                        r3.execute(r1)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: retrofit2.DefaultCallAdapterFactory.ExecutorCallbackCall.AnonymousClass1.onFailure(retrofit2.Call, java.lang.Throwable):void");
                }
            });
        }

        public boolean isExecuted() {
            return this.delegate.isExecuted();
        }

        public Response<T> execute() throws IOException {
            return this.delegate.execute();
        }

        public void cancel() {
            this.delegate.cancel();
        }

        public boolean isCanceled() {
            return this.delegate.isCanceled();
        }

        public Call<T> clone() {
            return new ExecutorCallbackCall(this.callbackExecutor, this.delegate.clone());
        }

        public Request request() {
            return this.delegate.request();
        }

        public Timeout timeout() {
            return this.delegate.timeout();
        }
    }
}
