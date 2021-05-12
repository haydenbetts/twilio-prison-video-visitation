package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;

public class VideoLayout {
    private HashMap<String, LayoutFrame> _bounds;
    private boolean _crop;
    private HashMap<String, LayoutFrame> _frames;
    private int _height;
    private VideoLayoutRegion[] _regions;
    private int _width;

    /* access modifiers changed from: private */
    public static HashMap<String, LayoutFrame> createLayoutFrameDictionary() {
        return new HashMap<>();
    }

    public static VideoLayout fromJson(String str) {
        return (VideoLayout) JsonSerializer.deserializeObject(str, new IFunction0<VideoLayout>() {
            public VideoLayout invoke() {
                return new VideoLayout();
            }
        }, new IAction3<VideoLayout, String, String>() {
            public void invoke(VideoLayout videoLayout, String str, String str2) {
                if (str == null) {
                    return;
                }
                if (Global.equals(str, "w")) {
                    videoLayout.setWidth(JsonSerializer.deserializeInteger(str2).getValue());
                } else if (Global.equals(str, "h")) {
                    videoLayout.setHeight(JsonSerializer.deserializeInteger(str2).getValue());
                } else if (Global.equals(str, "f")) {
                    videoLayout.setFrames(JsonSerializer.deserializeDictionary(str2, new IFunctionDelegate0<HashMap<String, LayoutFrame>>() {
                        public String getId() {
                            return "fm.liveswitch.VideoLayout.createLayoutFrameDictionary";
                        }

                        public HashMap<String, LayoutFrame> invoke() {
                            return VideoLayout.createLayoutFrameDictionary();
                        }
                    }, new IFunctionDelegate1<String, LayoutFrame>() {
                        public String getId() {
                            return "fm.liveswitch.LayoutFrame.fromJson";
                        }

                        public LayoutFrame invoke(String str) {
                            return LayoutFrame.fromJson(str);
                        }
                    }));
                } else if (Global.equals(str, "b")) {
                    videoLayout.setBounds(JsonSerializer.deserializeDictionary(str2, new IFunctionDelegate0<HashMap<String, LayoutFrame>>() {
                        public String getId() {
                            return "fm.liveswitch.VideoLayout.createLayoutFrameDictionary";
                        }

                        public HashMap<String, LayoutFrame> invoke() {
                            return VideoLayout.createLayoutFrameDictionary();
                        }
                    }, new IFunctionDelegate1<String, LayoutFrame>() {
                        public String getId() {
                            return "fm.liveswitch.LayoutFrame.fromJson";
                        }

                        public LayoutFrame invoke(String str) {
                            return LayoutFrame.fromJson(str);
                        }
                    }));
                } else if (Global.equals(str, "c")) {
                    videoLayout.setCrop(JsonSerializer.deserializeBoolean(str2).getValue());
                }
            }
        });
    }

    public HashMap<String, LayoutFrame> getBounds() {
        return this._bounds;
    }

    private String getClientKey(String str, String str2, String str3) {
        return StringExtensions.format("/u/{0}/d/{1}/c/{2}", str, str2, str3);
    }

    public boolean getCrop() {
        return this._crop;
    }

    public HashMap<String, LayoutFrame> getFrames() {
        return this._frames;
    }

    public int getHeight() {
        return this._height;
    }

    public VideoLayoutRegion[] getRegions() {
        return this._regions;
    }

    public int getWidth() {
        return this._width;
    }

    /* access modifiers changed from: package-private */
    public boolean inflate(Channel channel, ClientInfo clientInfo, ConnectionInfo[] connectionInfoArr) {
        String str;
        String[] strArr;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        HashMap hashMap = new HashMap();
        for (ConnectionInfo connectionInfo : connectionInfoArr) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), connectionInfo.getId(), connectionInfo);
        }
        ArrayList arrayList = new ArrayList();
        for (String next : HashMapExtensions.getKeys(getFrames())) {
            LayoutFrame layoutFrame = HashMapExtensions.getItem(getFrames()).get(next);
            LayoutFrame layoutFrame2 = HashMapExtensions.getItem(getBounds()).get(next);
            layoutFrame.getUserId();
            layoutFrame.getDeviceId();
            layoutFrame.getClientId();
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(hashMap, next, holder);
            ConnectionInfo connectionInfo2 = (ConnectionInfo) holder.getValue();
            if (tryGetValue) {
                String userId = connectionInfo2.getUserId();
                String userAlias = connectionInfo2.getUserAlias();
                String deviceId = connectionInfo2.getDeviceId();
                String deviceAlias = connectionInfo2.getDeviceAlias();
                String clientId = connectionInfo2.getClientId();
                String clientTag = connectionInfo2.getClientTag();
                String[] clientRoles = connectionInfo2.getClientRoles();
                str = connectionInfo2.getTag();
                strArr = clientRoles;
                str2 = clientTag;
                str3 = clientId;
                str4 = deviceAlias;
                str5 = deviceId;
                str6 = userAlias;
                str7 = userId;
            } else if (!Global.equals(clientInfo.getUserId(), layoutFrame.getUserId()) || !Global.equals(clientInfo.getDeviceId(), layoutFrame.getDeviceId()) || !Global.equals(clientInfo.getId(), layoutFrame.getClientId())) {
                return false;
            } else {
                String userId2 = clientInfo.getUserId();
                String userAlias2 = clientInfo.getUserAlias();
                String deviceId2 = clientInfo.getDeviceId();
                String deviceAlias2 = clientInfo.getDeviceAlias();
                String id = clientInfo.getId();
                String tag = clientInfo.getTag();
                str = null;
                strArr = clientInfo.getRoles();
                str2 = tag;
                str3 = id;
                str4 = deviceAlias2;
                str5 = deviceId2;
                str6 = userAlias2;
                str7 = userId2;
            }
            layoutFrame2.setUserId(str7);
            layoutFrame2.setDeviceId(str5);
            layoutFrame2.setClientId(str3);
            arrayList.add(new VideoLayoutRegion(layoutFrame, layoutFrame2, str7, str6, str5, str4, str3, str2, strArr, next, str));
        }
        setRegions((VideoLayoutRegion[]) arrayList.toArray(new VideoLayoutRegion[0]));
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00a7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isEquivalent(fm.liveswitch.VideoLayout r7) {
        /*
            r6 = this;
            int r0 = r6.getWidth()
            int r1 = r7.getWidth()
            r2 = 0
            if (r0 != r1) goto L_0x00da
            int r0 = r6.getHeight()
            int r1 = r7.getHeight()
            if (r0 != r1) goto L_0x00da
            boolean r0 = r6.getCrop()
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            boolean r1 = r7.getCrop()
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
            boolean r0 = fm.liveswitch.Global.equals(r0, r1)
            if (r0 == 0) goto L_0x00da
            java.util.HashMap r0 = r6.getFrames()
            int r0 = fm.liveswitch.HashMapExtensions.getCount(r0)
            java.util.HashMap r1 = r7.getFrames()
            int r1 = fm.liveswitch.HashMapExtensions.getCount(r1)
            if (r0 != r1) goto L_0x00da
            java.util.HashMap r0 = r6.getBounds()
            int r0 = fm.liveswitch.HashMapExtensions.getCount(r0)
            java.util.HashMap r1 = r7.getBounds()
            int r1 = fm.liveswitch.HashMapExtensions.getCount(r1)
            if (r0 == r1) goto L_0x0051
            goto L_0x00da
        L_0x0051:
            java.util.HashMap r0 = r6.getFrames()
            java.util.Set r0 = fm.liveswitch.HashMapExtensions.getKeys(r0)
            java.util.Iterator r0 = r0.iterator()
        L_0x005d:
            boolean r1 = r0.hasNext()
            r3 = 0
            if (r1 == 0) goto L_0x0095
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            fm.liveswitch.Holder r4 = new fm.liveswitch.Holder
            r4.<init>(r3)
            java.util.HashMap r3 = r7.getFrames()
            boolean r3 = fm.liveswitch.HashMapExtensions.tryGetValue(r3, r1, r4)
            java.lang.Object r4 = r4.getValue()
            fm.liveswitch.LayoutFrame r4 = (fm.liveswitch.LayoutFrame) r4
            if (r3 != 0) goto L_0x0080
            return r2
        L_0x0080:
            java.util.HashMap r3 = r6.getFrames()
            java.util.HashMap r3 = fm.liveswitch.HashMapExtensions.getItem(r3)
            java.lang.Object r1 = r3.get(r1)
            fm.liveswitch.LayoutFrame r1 = (fm.liveswitch.LayoutFrame) r1
            boolean r1 = r1.isEquivalent(r4)
            if (r1 != 0) goto L_0x005d
            return r2
        L_0x0095:
            java.util.HashMap r0 = r6.getBounds()
            java.util.Set r0 = fm.liveswitch.HashMapExtensions.getKeys(r0)
            java.util.Iterator r0 = r0.iterator()
        L_0x00a1:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x00d8
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            fm.liveswitch.Holder r4 = new fm.liveswitch.Holder
            r4.<init>(r3)
            java.util.HashMap r5 = r7.getBounds()
            boolean r5 = fm.liveswitch.HashMapExtensions.tryGetValue(r5, r1, r4)
            java.lang.Object r4 = r4.getValue()
            fm.liveswitch.LayoutFrame r4 = (fm.liveswitch.LayoutFrame) r4
            if (r5 != 0) goto L_0x00c3
            return r2
        L_0x00c3:
            java.util.HashMap r5 = r6.getBounds()
            java.util.HashMap r5 = fm.liveswitch.HashMapExtensions.getItem(r5)
            java.lang.Object r1 = r5.get(r1)
            fm.liveswitch.LayoutFrame r1 = (fm.liveswitch.LayoutFrame) r1
            boolean r1 = r1.isEquivalent(r4)
            if (r1 != 0) goto L_0x00a1
            return r2
        L_0x00d8:
            r7 = 1
            return r7
        L_0x00da:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.VideoLayout.isEquivalent(fm.liveswitch.VideoLayout):boolean");
    }

    /* access modifiers changed from: private */
    public void setBounds(HashMap<String, LayoutFrame> hashMap) {
        this._bounds = hashMap;
    }

    /* access modifiers changed from: private */
    public void setCrop(boolean z) {
        this._crop = z;
    }

    /* access modifiers changed from: private */
    public void setFrames(HashMap<String, LayoutFrame> hashMap) {
        this._frames = hashMap;
    }

    /* access modifiers changed from: private */
    public void setHeight(int i) {
        this._height = i;
    }

    private void setRegions(VideoLayoutRegion[] videoLayoutRegionArr) {
        this._regions = videoLayoutRegionArr;
    }

    /* access modifiers changed from: private */
    public void setWidth(int i) {
        this._width = i;
    }

    public static String toJson(VideoLayout videoLayout) {
        return JsonSerializer.serializeObject(videoLayout, new IAction2<VideoLayout, HashMap<String, String>>() {
            public void invoke(VideoLayout videoLayout, HashMap<String, String> hashMap) {
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "w", JsonSerializer.serializeInteger(new NullableInteger(videoLayout.getWidth())));
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "h", JsonSerializer.serializeInteger(new NullableInteger(videoLayout.getHeight())));
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "f", JsonSerializer.serializeDictionary(videoLayout.getFrames(), new IFunctionDelegate1<LayoutFrame, String>() {
                    public String getId() {
                        return "fm.liveswitch.LayoutFrame.toJson";
                    }

                    public String invoke(LayoutFrame layoutFrame) {
                        return LayoutFrame.toJson(layoutFrame);
                    }
                }));
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "b", JsonSerializer.serializeDictionary(videoLayout.getBounds(), new IFunctionDelegate1<LayoutFrame, String>() {
                    public String getId() {
                        return "fm.liveswitch.LayoutFrame.toJson";
                    }

                    public String invoke(LayoutFrame layoutFrame) {
                        return LayoutFrame.toJson(layoutFrame);
                    }
                }));
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "c", JsonSerializer.serializeBoolean(new NullableBoolean(videoLayout.getCrop())));
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    private VideoLayout() {
    }

    public VideoLayout(int i, int i2) {
        this(i, i2, (HashMap<String, LayoutFrame>) null, (HashMap<String, LayoutFrame>) null);
    }

    public VideoLayout(int i, int i2, boolean z) {
        this(i, i2, (HashMap<String, LayoutFrame>) null, (HashMap<String, LayoutFrame>) null, z);
    }

    public VideoLayout(int i, int i2, HashMap<String, LayoutFrame> hashMap, HashMap<String, LayoutFrame> hashMap2) {
        this(i, i2, hashMap, hashMap2, false);
    }

    public VideoLayout(int i, int i2, HashMap<String, LayoutFrame> hashMap, HashMap<String, LayoutFrame> hashMap2, boolean z) {
        setWidth(i);
        setHeight(i2);
        setFrames(hashMap == null ? new HashMap<>() : hashMap);
        setBounds(hashMap2 == null ? new HashMap<>() : hashMap2);
        setCrop(z);
    }
}
