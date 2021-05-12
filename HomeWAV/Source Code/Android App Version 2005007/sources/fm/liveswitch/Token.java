package fm.liveswitch;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Token {
    private static ILog __log = Log.getLogger(Token.class);
    private String __headerBase64;
    private String __payloadBase64;
    private String __signatureBase64;
    private String _algorithm;
    private String _applicationId;
    private ChannelClaim[] _channelClaims;
    private String _clientId;
    private String[] _clientRoles;
    private String _connectorId;
    private String _deviceId;
    private NullableDate _expirationTime;
    private Date _issuedAt;
    private String _mediaServerId;
    private String _region;
    private String _type;
    private String _userId;

    public static int getDefaultExpiry() {
        return 120;
    }

    public static String getHmacSha256Algorithm() {
        return "HS256";
    }

    public static String getRsaSha256Algorithm() {
        return "RS256";
    }

    private DataBuffer computeSignature(String str, String str2, String str3) {
        return MacContextBase.compute(MacType.HmacSha256, DataBuffer.wrap(Utf8.encode(str3)), DataBuffer.wrap(Utf8.encode(StringExtensions.format("{0}.{1}", str, str2))));
    }

    public static String generateClientJoinToken(String str, String str2, String str3, String str4, ChannelClaim channelClaim, String str5) {
        return getClientJoinToken(str, str2, str3, str4, channelClaim).sign(str5);
    }

    public static String generateClientJoinToken(String str, String str2, String str3, String str4, ChannelClaim channelClaim, String str5, Date date) {
        Token clientJoinToken = getClientJoinToken(str, str2, str3, str4, channelClaim);
        clientJoinToken.setExpirationTime(new NullableDate(date));
        return clientJoinToken.sign(str5);
    }

    public static String generateClientJoinToken(String str, String str2, String str3, String str4, String str5, String str6) {
        return getClientJoinToken(str, str2, str3, str4, str5).sign(str6);
    }

    public static String generateClientJoinToken(String str, String str2, String str3, String str4, String str5, String str6, Date date) {
        Token clientJoinToken = getClientJoinToken(str, str2, str3, str4, str5);
        clientJoinToken.setExpirationTime(new NullableDate(date));
        return clientJoinToken.sign(str6);
    }

    public static String generateClientJoinToken(Client client, ChannelClaim channelClaim, String str) {
        return generateClientJoinToken(client.getApplicationId(), client.getUserId(), client.getDeviceId(), client.getId(), channelClaim, str);
    }

    public static String generateClientJoinToken(Client client, ChannelClaim channelClaim, String str, Date date) {
        return generateClientJoinToken(client.getApplicationId(), client.getUserId(), client.getDeviceId(), client.getId(), channelClaim, str, date);
    }

    public static String generateClientJoinToken(Client client, String str, String str2) {
        return generateClientJoinToken(client.getApplicationId(), client.getUserId(), client.getDeviceId(), client.getId(), str, str2);
    }

    public static String generateClientJoinToken(Client client, String str, String str2, Date date) {
        return generateClientJoinToken(client.getApplicationId(), client.getUserId(), client.getDeviceId(), client.getId(), str, str2, date);
    }

    public static String generateClientRegisterToken(String str, String str2, String str3, String str4, String[] strArr, ChannelClaim[] channelClaimArr, String str5) {
        return getClientRegisterToken(str, str2, str3, str4, strArr, channelClaimArr).sign(str5);
    }

    public static String generateClientRegisterToken(String str, String str2, String str3, String str4, String[] strArr, ChannelClaim[] channelClaimArr, String str5, Date date) {
        Token clientRegisterToken = getClientRegisterToken(str, str2, str3, str4, strArr, channelClaimArr);
        clientRegisterToken.setExpirationTime(new NullableDate(date));
        return clientRegisterToken.sign(str5);
    }

    public static String generateClientRegisterToken(String str, String str2, String str3, String str4, String[] strArr, ChannelClaim[] channelClaimArr, String str5, Date date, String str6) {
        Token clientRegisterToken = getClientRegisterToken(str, str2, str3, str4, strArr, channelClaimArr);
        clientRegisterToken.setRegion(str6);
        clientRegisterToken.setExpirationTime(new NullableDate(date));
        return clientRegisterToken.sign(str5);
    }

    public static String generateClientRegisterToken(String str, String str2, String str3, String str4, String[] strArr, ChannelClaim[] channelClaimArr, String str5, String str6) {
        Token clientRegisterToken = getClientRegisterToken(str, str2, str3, str4, strArr, channelClaimArr);
        clientRegisterToken.setRegion(str6);
        return clientRegisterToken.sign(str5);
    }

    public static String generateClientRegisterToken(String str, String str2, String str3, String str4, String[] strArr, String str5, String str6) {
        return getClientRegisterToken(str, str2, str3, str4, strArr, str5).sign(str6);
    }

    public static String generateClientRegisterToken(String str, String str2, String str3, String str4, String[] strArr, String str5, String str6, Date date) {
        Token clientRegisterToken = getClientRegisterToken(str, str2, str3, str4, strArr, str5);
        clientRegisterToken.setExpirationTime(new NullableDate(date));
        return clientRegisterToken.sign(str6);
    }

    public static String generateClientRegisterToken(String str, String str2, String str3, String str4, String[] strArr, String str5, String str6, Date date, String str7) {
        Token clientRegisterToken = getClientRegisterToken(str, str2, str3, str4, strArr, str5);
        clientRegisterToken.setRegion(str7);
        clientRegisterToken.setExpirationTime(new NullableDate(date));
        return clientRegisterToken.sign(str6);
    }

    public static String generateClientRegisterToken(String str, String str2, String str3, String str4, String[] strArr, String str5, String str6, String str7) {
        Token clientRegisterToken = getClientRegisterToken(str, str2, str3, str4, strArr, str5);
        clientRegisterToken.setRegion(str7);
        return clientRegisterToken.sign(str6);
    }

    public static String generateClientRegisterToken(String str, String str2, String str3, String str4, String[] strArr, String[] strArr2, String str5) {
        return getClientRegisterToken(str, str2, str3, str4, strArr, strArr2).sign(str5);
    }

    public static String generateClientRegisterToken(String str, String str2, String str3, String str4, String[] strArr, String[] strArr2, String str5, Date date) {
        Token clientRegisterToken = getClientRegisterToken(str, str2, str3, str4, strArr, strArr2);
        clientRegisterToken.setExpirationTime(new NullableDate(date));
        return clientRegisterToken.sign(str5);
    }

    public static String generateClientRegisterToken(String str, String str2, String str3, String str4, String[] strArr, String[] strArr2, String str5, Date date, String str6) {
        Token clientRegisterToken = getClientRegisterToken(str, str2, str3, str4, strArr, strArr2);
        clientRegisterToken.setRegion(str6);
        clientRegisterToken.setExpirationTime(new NullableDate(date));
        return clientRegisterToken.sign(str5);
    }

    public static String generateClientRegisterToken(String str, String str2, String str3, String str4, String[] strArr, String[] strArr2, String str5, String str6) {
        Token clientRegisterToken = getClientRegisterToken(str, str2, str3, str4, strArr, strArr2);
        clientRegisterToken.setRegion(str6);
        return clientRegisterToken.sign(str5);
    }

    public static String generateClientRegisterToken(Client client, ChannelClaim[] channelClaimArr, String str) {
        return generateClientRegisterToken(client.getApplicationId(), client.getUserId(), client.getDeviceId(), client.getId(), client.getRoles(), channelClaimArr, str);
    }

    public static String generateClientRegisterToken(Client client, ChannelClaim[] channelClaimArr, String str, Date date) {
        return generateClientRegisterToken(client.getApplicationId(), client.getUserId(), client.getDeviceId(), client.getId(), client.getRoles(), channelClaimArr, str, date);
    }

    public static String generateClientRegisterToken(Client client, ChannelClaim[] channelClaimArr, String str, Date date, String str2) {
        return generateClientRegisterToken(client.getApplicationId(), client.getUserId(), client.getDeviceId(), client.getId(), client.getRoles(), channelClaimArr, str, date, str2);
    }

    public static String generateClientRegisterToken(Client client, ChannelClaim[] channelClaimArr, String str, String str2) {
        return generateClientRegisterToken(client.getApplicationId(), client.getUserId(), client.getDeviceId(), client.getId(), client.getRoles(), channelClaimArr, str, str2);
    }

    public static String generateClientRegisterToken(Client client, String str, String str2) {
        return generateClientRegisterToken(client.getApplicationId(), client.getUserId(), client.getDeviceId(), client.getId(), client.getRoles(), str, str2);
    }

    public static String generateClientRegisterToken(Client client, String str, String str2, Date date) {
        return generateClientRegisterToken(client.getApplicationId(), client.getUserId(), client.getDeviceId(), client.getId(), client.getRoles(), str, str2, date);
    }

    public static String generateClientRegisterToken(Client client, String str, String str2, Date date, String str3) {
        return generateClientRegisterToken(client.getApplicationId(), client.getUserId(), client.getDeviceId(), client.getId(), client.getRoles(), str, str2, date, str3);
    }

    public static String generateClientRegisterToken(Client client, String str, String str2, String str3) {
        return generateClientRegisterToken(client.getApplicationId(), client.getUserId(), client.getDeviceId(), client.getId(), client.getRoles(), str, str2, str3);
    }

    public static String generateClientRegisterToken(Client client, String[] strArr, String str) {
        return generateClientRegisterToken(client.getApplicationId(), client.getUserId(), client.getDeviceId(), client.getId(), client.getRoles(), strArr, str);
    }

    public static String generateClientRegisterToken(Client client, String[] strArr, String str, Date date) {
        return generateClientRegisterToken(client.getApplicationId(), client.getUserId(), client.getDeviceId(), client.getId(), client.getRoles(), strArr, str, date);
    }

    public static String generateClientRegisterToken(Client client, String[] strArr, String str, Date date, String str2) {
        return generateClientRegisterToken(client.getApplicationId(), client.getUserId(), client.getDeviceId(), client.getId(), client.getRoles(), strArr, str, date, str2);
    }

    public static String generateClientRegisterToken(Client client, String[] strArr, String str, String str2) {
        return generateClientRegisterToken(client.getApplicationId(), client.getUserId(), client.getDeviceId(), client.getId(), client.getRoles(), strArr, str, str2);
    }

    public static String generateEventToken(String str, String str2) {
        return getEventToken(str).sign(str2);
    }

    public static String generateEventToken(String str, String str2, Date date) {
        Token eventToken = getEventToken(str);
        eventToken.setExpirationTime(new NullableDate(date));
        return eventToken.sign(str2);
    }

    public String getAlgorithm() {
        return this._algorithm;
    }

    public String getApplicationId() {
        return this._applicationId;
    }

    public ChannelClaim getChannelClaim() {
        ChannelClaim[] channelClaims = getChannelClaims();
        if (channelClaims == null || ArrayExtensions.getLength((Object[]) channelClaims) == 0) {
            return null;
        }
        return channelClaims[0];
    }

    public ChannelClaim[] getChannelClaims() {
        return this._channelClaims;
    }

    public String getClientId() {
        return this._clientId;
    }

    private static Token getClientJoinToken(String str, String str2, String str3, String str4, ChannelClaim channelClaim) {
        Token token = new Token();
        token.setType(TokenType.getJoin());
        token.setApplicationId(str);
        token.setUserId(str2);
        token.setDeviceId(str3);
        token.setClientId(str4);
        token.setChannelClaims(channelClaim == null ? null : new ChannelClaim[]{channelClaim});
        return token;
    }

    private static Token getClientJoinToken(String str, String str2, String str3, String str4, String str5) {
        return getClientJoinToken(str, str2, str3, str4, str5 == null ? null : new ChannelClaim(str5));
    }

    private static Token getClientRegisterToken(String str, String str2, String str3, String str4, String[] strArr, ChannelClaim[] channelClaimArr) {
        Token token = new Token();
        token.setType(TokenType.getRegister());
        token.setApplicationId(str);
        token.setUserId(str2);
        token.setDeviceId(str3);
        token.setClientId(str4);
        token.setClientRoles(strArr);
        token.setChannelClaims(channelClaimArr);
        return token;
    }

    private static Token getClientRegisterToken(String str, String str2, String str3, String str4, String[] strArr, String str5) {
        ChannelClaim[] channelClaimArr;
        if (str5 == null) {
            channelClaimArr = new ChannelClaim[0];
        } else {
            channelClaimArr = new ChannelClaim[]{new ChannelClaim(str5)};
        }
        return getClientRegisterToken(str, str2, str3, str4, strArr, channelClaimArr);
    }

    private static Token getClientRegisterToken(String str, String str2, String str3, String str4, String[] strArr, String[] strArr2) {
        ArrayList arrayList = new ArrayList();
        for (String channelClaim : strArr2) {
            arrayList.add(new ChannelClaim(channelClaim));
        }
        return getClientRegisterToken(str, str2, str3, str4, strArr, (ChannelClaim[]) arrayList.toArray(new ChannelClaim[0]));
    }

    public String[] getClientRoles() {
        return this._clientRoles;
    }

    public String getConnectorId() {
        return this._connectorId;
    }

    public String getDeviceId() {
        return this._deviceId;
    }

    private static Token getEventToken(String str) {
        Token token = new Token();
        token.setType(TokenType.getEvent());
        token.setApplicationId(str);
        return token;
    }

    public NullableDate getExpirationTime() {
        return this._expirationTime;
    }

    public Date getIssuedAt() {
        return this._issuedAt;
    }

    public String getMediaServerId() {
        return this._mediaServerId;
    }

    public String getRegion() {
        return this._region;
    }

    public String getType() {
        return this._type;
    }

    public String getUserId() {
        return this._userId;
    }

    private static String headerFromBase64(String str) {
        return headerFromJson(Utf8.decode(Base64.decode(str)));
    }

    private static String headerFromJson(String str) {
        HashMap deserializeDictionary = JsonSerializer.deserializeDictionary(str, new IFunction0<HashMap<String, String>>() {
            public HashMap<String, String> invoke() {
                return new HashMap<>();
            }
        }, new IFunction1<String, String>() {
            public String invoke(String str) {
                return JsonSerializer.deserializeString(str);
            }
        });
        if (!deserializeDictionary.containsKey("alg")) {
            throw new RuntimeException(new Exception("Token header is missing 'alg'."));
        } else if (deserializeDictionary.containsKey("typ")) {
            String str2 = (String) HashMapExtensions.getItem(deserializeDictionary).get("alg");
            if (Global.equals(str2, getHmacSha256Algorithm()) || Global.equals(str2, getRsaSha256Algorithm())) {
                String str3 = (String) HashMapExtensions.getItem(deserializeDictionary).get("typ");
                if (Global.equals(str3, "JWT")) {
                    return str2;
                }
                throw new RuntimeException(new Exception(StringExtensions.format("Token header contains unrecognized 'typ' ({0}).", (Object) str3)));
            }
            throw new RuntimeException(new Exception(StringExtensions.format("Token header contains unrecognized 'alg' ({0}).", (Object) str2)));
        } else {
            throw new RuntimeException(new Exception("Token header is missing 'typ'."));
        }
    }

    private static String headerToBase64(String str) {
        return Base64.encode(Utf8.encode(headerToJson(str)));
    }

    private String headerToBase64() {
        return headerToBase64(getAlgorithm());
    }

    private static String headerToJson(String str) {
        HashMap hashMap = new HashMap();
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "alg", str);
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "typ", "JWT");
        return JsonSerializer.serializeDictionary(hashMap, new IFunction1<String, String>() {
            public String invoke(String str) {
                return JsonSerializer.serializeString(str);
            }
        });
    }

    private String headerToJson() {
        return headerToJson(getAlgorithm());
    }

    public static Token parse(String str) {
        String[] split = StringExtensions.split(str, new char[]{'.'});
        if (ArrayExtensions.getLength((Object[]) split) != 3) {
            __log.error("Invalid token.");
            return null;
        }
        String headerFromBase64 = headerFromBase64(split[0]);
        Token payloadFromBase64 = payloadFromBase64(split[1]);
        payloadFromBase64.setAlgorithm(headerFromBase64);
        payloadFromBase64.__headerBase64 = split[0].replace("\r", "").replace("\n", "");
        payloadFromBase64.__payloadBase64 = split[1].replace("\r", "").replace("\n", "");
        payloadFromBase64.__signatureBase64 = split[2];
        return payloadFromBase64;
    }

    private static Token payloadFromBase64(String str) {
        byte[] decode = Base64.decode(str);
        if (decode != null) {
            return payloadFromJson(Utf8.decode(decode));
        }
        throw new RuntimeException(new Exception("Unable to decode base64."));
    }

    private static Token payloadFromJson(String str) {
        return (Token) JsonSerializer.deserializeObject(str, new IFunction0<Token>() {
            public Token invoke() {
                return new Token();
            }
        }, new IAction3<Token, String, String>() {
            public void invoke(Token token, String str, String str2) {
                if (str.equals("iat")) {
                    NullableLong deserializeLong = JsonSerializer.deserializeLong(str2);
                    if (!deserializeLong.getHasValue()) {
                        token.setIssuedAt(DateExtensions.createDate(0));
                    } else {
                        token.setIssuedAt(UnixTimestamp.unixToDateTime(deserializeLong.getValue()));
                    }
                } else if (str.equals("exp")) {
                    NullableLong deserializeLong2 = JsonSerializer.deserializeLong(str2);
                    if (deserializeLong2.getHasValue()) {
                        token.setExpirationTime(new NullableDate(UnixTimestamp.unixToDateTime(deserializeLong2.getValue())));
                    }
                } else if (str.equals("type")) {
                    token.setType(JsonSerializer.deserializeString(str2));
                } else if (str.equals("connectorId")) {
                    token.setConnectorId(JsonSerializer.deserializeString(str2));
                } else if (str.equals("mediaServerId")) {
                    token.setMediaServerId(JsonSerializer.deserializeString(str2));
                } else if (str.equals("applicationId")) {
                    token.setApplicationId(JsonSerializer.deserializeString(str2));
                } else if (str.equals("userId")) {
                    token.setUserId(JsonSerializer.deserializeString(str2));
                } else if (str.equals("deviceId")) {
                    token.setDeviceId(JsonSerializer.deserializeString(str2));
                } else if (str.equals("region")) {
                    token.setRegion(JsonSerializer.deserializeString(str2));
                } else if (str.equals("clientId")) {
                    token.setClientId(JsonSerializer.deserializeString(str2));
                } else if (str.equals("clientRoles")) {
                    token.setClientRoles(JsonSerializer.deserializeStringArray(str2));
                } else if (str.equals("channels")) {
                    token.setChannelClaims(ChannelClaim.fromJsonArray(str2));
                }
            }
        });
    }

    private static String payloadToBase64(Token token) {
        return Base64.encode(Utf8.encode(payloadToJson(token)));
    }

    private String payloadToBase64() {
        return payloadToBase64(this);
    }

    private static String payloadToJson(Token token) {
        return JsonSerializer.serializeObject(token, new IAction2<Token, HashMap<String, String>>(token) {
            final /* synthetic */ Token val$token;

            {
                this.val$token = r1;
            }

            public void invoke(Token token, HashMap<String, String> hashMap) {
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "iat", JsonSerializer.serializeLong(new NullableLong(UnixTimestamp.dateTimeToUnix(this.val$token.getIssuedAt()))));
                if (this.val$token.getExpirationTime().getHasValue()) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "exp", JsonSerializer.serializeLong(new NullableLong(UnixTimestamp.dateTimeToUnix(this.val$token.getExpirationTime().getValue()))));
                }
                if (this.val$token.getType() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "type", JsonSerializer.serializeString(this.val$token.getType()));
                }
                if (this.val$token.getConnectorId() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "connectorId", JsonSerializer.serializeString(this.val$token.getConnectorId()));
                }
                if (this.val$token.getMediaServerId() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "mediaServerId", JsonSerializer.serializeString(this.val$token.getMediaServerId()));
                }
                if (this.val$token.getApplicationId() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "applicationId", JsonSerializer.serializeString(this.val$token.getApplicationId()));
                }
                if (this.val$token.getUserId() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "userId", JsonSerializer.serializeString(this.val$token.getUserId()));
                }
                if (this.val$token.getDeviceId() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "deviceId", JsonSerializer.serializeString(this.val$token.getDeviceId()));
                }
                if (this.val$token.getClientId() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "clientId", JsonSerializer.serializeString(this.val$token.getClientId()));
                }
                if (this.val$token.getRegion() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "region", JsonSerializer.serializeString(this.val$token.getRegion()));
                }
                if (this.val$token.getClientRoles() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "clientRoles", JsonSerializer.serializeStringArray(this.val$token.getClientRoles()));
                }
                if (this.val$token.getChannelClaims() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "channels", ChannelClaim.toJsonArray(this.val$token.getChannelClaims()));
                }
            }
        });
    }

    private String payloadToJson() {
        return payloadToJson(this);
    }

    private void setAlgorithm(String str) {
        this._algorithm = str;
    }

    /* access modifiers changed from: private */
    public void setApplicationId(String str) {
        this._applicationId = str;
    }

    /* access modifiers changed from: private */
    public void setChannelClaims(ChannelClaim[] channelClaimArr) {
        this._channelClaims = channelClaimArr;
    }

    /* access modifiers changed from: private */
    public void setClientId(String str) {
        this._clientId = str;
    }

    /* access modifiers changed from: private */
    public void setClientRoles(String[] strArr) {
        this._clientRoles = strArr;
    }

    /* access modifiers changed from: private */
    public void setConnectorId(String str) {
        this._connectorId = str;
    }

    /* access modifiers changed from: private */
    public void setDeviceId(String str) {
        this._deviceId = str;
    }

    /* access modifiers changed from: private */
    public void setExpirationTime(NullableDate nullableDate) {
        this._expirationTime = nullableDate;
    }

    /* access modifiers changed from: private */
    public void setIssuedAt(Date date) {
        this._issuedAt = date;
    }

    /* access modifiers changed from: private */
    public void setMediaServerId(String str) {
        this._mediaServerId = str;
    }

    /* access modifiers changed from: private */
    public void setRegion(String str) {
        this._region = str;
    }

    /* access modifiers changed from: private */
    public void setType(String str) {
        this._type = str;
    }

    /* access modifiers changed from: private */
    public void setUserId(String str) {
        this._userId = str;
    }

    private String sign(String str) {
        setAlgorithm(getHmacSha256Algorithm());
        String headerToBase64 = headerToBase64();
        String payloadToBase64 = payloadToBase64();
        return StringExtensions.format("{0}.{1}.{2}", headerToBase64, payloadToBase64, signBase64(headerToBase64, payloadToBase64, str));
    }

    private String signBase64(String str, String str2, String str3) {
        return StringExtensions.trimEnd(Base64.encodeBuffer(computeSignature(str, str2, str3)), new char[]{'='});
    }

    private Token() {
        this._expirationTime = new NullableDate();
        this._issuedAt = new Date();
        setIssuedAt(DateExtensions.getUtcNow());
        setExpirationTime(new NullableDate(DateExtensions.addSeconds(getIssuedAt(), (double) getDefaultExpiry())));
    }

    public static boolean verify(String str, String str2) {
        Token parse = parse(str);
        if (parse == null) {
            return false;
        }
        return parse.verify(str2);
    }

    public boolean verify(String str) {
        if (Global.equals(getAlgorithm(), getHmacSha256Algorithm())) {
            return verifyBase64(this.__signatureBase64, this.__headerBase64, this.__payloadBase64, str);
        }
        __log.error("Cannot verify an RS256 token with a shared secret.");
        return false;
    }

    private boolean verifyBase64(String str, String str2, String str3, String str4) {
        byte[] decode = Base64.decode(str);
        DataBuffer computeSignature = computeSignature(str2, str3, str4);
        for (int i = 0; i < ArrayExtensions.getLength(decode); i++) {
            if (decode[i] != computeSignature.read8(i)) {
                return false;
            }
        }
        return true;
    }
}
