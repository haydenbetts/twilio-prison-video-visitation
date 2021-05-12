package org.bouncycastle.crypto.engines;

import androidx.recyclerview.widget.ItemTouchHelper;
import java.lang.reflect.Array;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import okio.Utf8;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.crypto.tls.CipherSuite;
import org.bouncycastle.util.Pack;

public class AESEngine implements BlockCipher {
    private static final int BLOCK_SIZE = 16;
    private static final byte[] S = {99, 124, 119, 123, -14, 107, 111, -59, 48, 1, 103, 43, -2, -41, -85, 118, -54, -126, -55, 125, -6, 89, 71, -16, -83, -44, -94, -81, -100, -92, 114, -64, -73, -3, -109, 38, 54, Utf8.REPLACEMENT_BYTE, -9, -52, 52, -91, -27, -15, 113, -40, 49, 21, 4, -57, 35, -61, 24, -106, 5, -102, 7, 18, ByteCompanionObject.MIN_VALUE, -30, -21, 39, -78, 117, 9, -125, 44, 26, 27, 110, 90, -96, 82, 59, -42, -77, 41, -29, 47, -124, 83, -47, 0, -19, 32, -4, -79, 91, 106, -53, -66, 57, 74, 76, 88, -49, -48, -17, -86, -5, 67, 77, 51, -123, 69, -7, 2, ByteCompanionObject.MAX_VALUE, 80, 60, -97, -88, 81, -93, 64, -113, -110, -99, 56, -11, PSSSigner.TRAILER_IMPLICIT, -74, -38, 33, Tnaf.POW_2_WIDTH, -1, -13, -46, -51, 12, 19, -20, 95, -105, 68, 23, -60, -89, 126, 61, 100, 93, 25, 115, 96, -127, 79, -36, 34, 42, -112, -120, 70, -18, -72, 20, -34, 94, 11, -37, -32, 50, 58, 10, 73, 6, 36, 92, -62, -45, -84, 98, -111, -107, -28, 121, -25, -56, 55, 109, -115, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8, -70, 120, 37, 46, 28, -90, -76, -58, -24, -35, 116, 31, 75, -67, -117, -118, 112, 62, -75, 102, 72, 3, -10, 14, 97, 53, 87, -71, -122, -63, 29, -98, -31, -8, -104, 17, 105, -39, -114, -108, -101, 30, -121, -23, -50, 85, 40, -33, -116, -95, -119, 13, -65, -26, 66, 104, 65, -103, 45, 15, -80, 84, -69, 22};
    private static final byte[] Si = {82, 9, 106, -43, 48, 54, -91, 56, -65, 64, -93, -98, -127, -13, -41, -5, 124, -29, 57, -126, -101, 47, -1, -121, 52, -114, 67, 68, -60, -34, -23, -53, 84, 123, -108, 50, -90, -62, 35, 61, -18, 76, -107, 11, 66, -6, -61, 78, 8, 46, -95, 102, 40, -39, 36, -78, 118, 91, -94, 73, 109, -117, -47, 37, 114, -8, -10, 100, -122, 104, -104, 22, -44, -92, 92, -52, 93, 101, -74, -110, 108, 112, 72, 80, -3, -19, -71, -38, 94, 21, 70, 87, -89, -115, -99, -124, -112, -40, -85, 0, -116, PSSSigner.TRAILER_IMPLICIT, -45, 10, -9, -28, 88, 5, -72, -77, 69, 6, -48, 44, 30, -113, -54, Utf8.REPLACEMENT_BYTE, 15, 2, -63, -81, -67, 3, 1, 19, -118, 107, 58, -111, 17, 65, 79, 103, -36, -22, -105, -14, -49, -50, -16, -76, -26, 115, -106, -84, 116, 34, -25, -83, 53, -123, -30, -7, 55, -24, 28, 117, -33, 110, 71, -15, 26, 113, 29, 41, -59, -119, 111, -73, 98, 14, -86, 24, -66, 27, -4, 86, 62, 75, -58, -46, 121, 32, -102, -37, -64, -2, 120, -51, 90, -12, 31, -35, -88, 51, -120, 7, -57, 49, -79, 18, Tnaf.POW_2_WIDTH, 89, 39, ByteCompanionObject.MIN_VALUE, -20, 95, 96, 81, ByteCompanionObject.MAX_VALUE, -87, 25, -75, 74, 13, 45, -27, 122, -97, -109, -55, -100, -17, -96, -32, 59, 77, -82, 42, -11, -80, -56, -21, -69, 60, -125, 83, -103, 97, 23, 43, 4, 126, -70, 119, -42, 38, -31, 105, 20, 99, 85, 33, 12, 125};
    private static final int[] T0 = {-1520213050, -2072216328, -1720223762, -1921287178, 234025727, -1117033514, -1318096930, 1422247313, 1345335392, 50397442, -1452841010, 2099981142, 436141799, 1658312629, -424957107, -1703512340, 1170918031, -1652391393, 1086966153, -2021818886, 368769775, -346465870, -918075506, 200339707, -324162239, 1742001331, -39673249, -357585083, -1080255453, -140204973, -1770884380, 1539358875, -1028147339, 486407649, -1366060227, 1780885068, 1513502316, 1094664062, 49805301, 1338821763, 1546925160, -190470831, 887481809, 150073849, -1821281822, 1943591083, 1395732834, 1058346282, 201589768, 1388824469, 1696801606, 1589887901, 672667696, -1583966665, 251987210, -1248159185, 151455502, 907153956, -1686077413, 1038279391, 652995533, 1764173646, -843926913, -1619692054, 453576978, -1635548387, 1949051992, 773462580, 756751158, -1301385508, -296068428, -73359269, -162377052, 1295727478, 1641469623, -827083907, 2066295122, 1055122397, 1898917726, -1752923117, -179088474, 1758581177, 0, 753790401, 1612718144, 536673507, -927878791, -312779850, -1100322092, 1187761037, -641810841, 1262041458, -565556588, -733197160, -396863312, 1255133061, 1808847035, 720367557, -441800113, 385612781, -985447546, -682799718, 1429418854, -1803188975, -817543798, 284817897, 100794884, -2122350594, -263171936, 1144798328, -1163944155, -475486133, -212774494, -22830243, -1069531008, -1970303227, -1382903233, -1130521311, 1211644016, 83228145, -541279133, -1044990345, 1977277103, 1663115586, 806359072, 452984805, 250868733, 1842533055, 1288555905, 336333848, 890442534, 804056259, -513843266, -1567123659, -867941240, 957814574, 1472513171, -223893675, -2105639172, 1195195770, -1402706744, -413311558, 723065138, -1787595802, -1604296512, -1736343271, -783331426, 2145180835, 1713513028, 2116692564, -1416589253, -2088204277, -901364084, 703524551, -742868885, 1007948840, 2044649127, -497131844, 487262998, 1994120109, 1004593371, 1446130276, 1312438900, 503974420, -615954030, 168166924, 1814307912, -463709000, 1573044895, 1859376061, -273896381, -1503501628, -1466855111, -1533700815, 937747667, -1954973198, 854058965, 1137232011, 1496790894, -1217565222, -1936880383, 1691735473, -766620004, -525751991, -1267962664, -95005012, 133494003, 636152527, -1352309302, -1904575756, -374428089, 403179536, -709182865, -2005370640, 1864705354, 1915629148, 605822008, -240736681, -944458637, 1371981463, 602466507, 2094914977, -1670089496, 555687742, -582268010, -591544991, -2037675251, -2054518257, -1871679264, 1111375484, -994724495, -1436129588, -666351472, 84083462, 32962295, 302911004, -1553899070, 1597322602, -111716434, -793134743, -1853454825, 1489093017, 656219450, -1180787161, 954327513, 335083755, -1281845205, 856756514, -1150719534, 1893325225, -1987146233, -1483434957, -1231316179, 572399164, -1836611819, 552200649, 1238290055, -11184726, 2015897680, 2061492133, -1886614525, -123625127, -2138470135, 386731290, -624967835, 837215959, -968736124, -1201116976, -1019133566, -1332111063, 1999449434, 286199582, -877612933, -61582168, -692339859, 974525996};
    private static final int[] Tinv0 = {1353184337, 1399144830, -1012656358, -1772214470, -882136261, -247096033, -1420232020, -1828461749, 1442459680, -160598355, -1854485368, 625738485, -52959921, -674551099, -2143013594, -1885117771, 1230680542, 1729870373, -1743852987, -507445667, 41234371, 317738113, -1550367091, -956705941, -413167869, -1784901099, -344298049, -631680363, 763608788, -752782248, 694804553, 1154009486, 1787413109, 2021232372, 1799248025, -579749593, -1236278850, 397248752, 1722556617, -1271214467, 407560035, -2110711067, 1613975959, 1165972322, -529046351, -2068943941, 480281086, -1809118983, 1483229296, 436028815, -2022908268, -1208452270, 601060267, -503166094, 1468997603, 715871590, 120122290, 63092015, -1703164538, -1526188077, -226023376, -1297760477, -1167457534, 1552029421, 723308426, -1833666137, -252573709, -1578997426, -839591323, -708967162, 526529745, -1963022652, -1655493068, -1604979806, 853641733, 1978398372, 971801355, -1427152832, 111112542, 1360031421, -108388034, 1023860118, -1375387939, 1186850381, -1249028975, 90031217, 1876166148, -15380384, 620468249, -1746289194, -868007799, 2006899047, -1119688528, -2004121337, 945494503, -605108103, 1191869601, -384875908, -920746760, 0, -2088337399, 1223502642, -1401941730, 1316117100, -67170563, 1446544655, 517320253, 658058550, 1691946762, 564550760, -783000677, 976107044, -1318647284, 266819475, -761860428, -1634624741, 1338359936, -1574904735, 1766553434, 370807324, 179999714, -450191168, 1138762300, 488053522, 185403662, -1379431438, -1180125651, -928440812, -2061897385, 1275557295, -1143105042, -44007517, -1624899081, -1124765092, -985962940, 880737115, 1982415755, -590994485, 1761406390, 1676797112, -891538985, 277177154, 1076008723, 538035844, 2099530373, -130171950, 288553390, 1839278535, 1261411869, -214912292, -330136051, -790380169, 1813426987, -1715900247, -95906799, 577038663, -997393240, 440397984, -668172970, -275762398, -951170681, -1043253031, -22885748, 906744984, -813566554, 685669029, 646887386, -1530942145, -459458004, 227702864, -1681105046, 1648787028, -1038905866, -390539120, 1593260334, -173030526, -1098883681, 2090061929, -1456614033, -1290656305, 999926984, -1484974064, 1852021992, 2075868123, 158869197, -199730834, 28809964, -1466282109, 1701746150, 2129067946, 147831841, -420997649, -644094022, -835293366, -737566742, -696471511, -1347247055, 824393514, 815048134, -1067015627, 935087732, -1496677636, -1328508704, 366520115, 1251476721, -136647615, 240176511, 804688151, -1915335306, 1303441219, 1414376140, -553347356, -474623586, 461924940, -1205916479, 2136040774, 82468509, 1563790337, 1937016826, 776014843, 1511876531, 1389550482, 861278441, 323475053, -1939744870, 2047648055, -1911228327, -1992551445, -299390514, 902390199, -303751967, 1018251130, 1507840668, 1064563285, 2043548696, -1086863501, -355600557, 1537932639, 342834655, -2032450440, -2114736182, 1053059257, 741614648, 1598071746, 1925389590, 203809468, -1958134744, 1100287487, 1895934009, -558691320, -1662733096, -1866377628, 1636092795, 1890988757, 1952214088, 1113045200};
    private static final int m1 = -2139062144;
    private static final int m2 = 2139062143;
    private static final int m3 = 27;
    private static final int m4 = -1061109568;
    private static final int m5 = 1061109567;
    private static final int[] rcon = {1, 2, 4, 8, 16, 32, 64, 128, 27, 54, 108, 216, 171, 77, 154, 47, 94, 188, 99, 198, 151, 53, 106, 212, 179, 125, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 239, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256, 145};
    private int C0;
    private int C1;
    private int C2;
    private int C3;
    private int ROUNDS;
    private int[][] WorkingKey = null;
    private boolean forEncryption;

    private static int FFmulX(int i) {
        return (((i & m1) >>> 7) * 27) ^ ((m2 & i) << 1);
    }

    private static int FFmulX2(int i) {
        int i2 = i & m4;
        int i3 = i2 ^ (i2 >>> 1);
        return (i3 >>> 5) ^ (((m5 & i) << 2) ^ (i3 >>> 2));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v20, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v22, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v17, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v21, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v25, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v30, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v53, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v33, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v37, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v42, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v10, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v45, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v49, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v18, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v15, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v44, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v18, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v49, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v19, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v23, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v27, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v32, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v7, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v11, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v15, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v20, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v56, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v60, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v64, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v23, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v18, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v61, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v65, resolved type: int} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void decryptBlock(int[][] r17) {
        /*
            r16 = this;
            r0 = r16
            int r1 = r0.C0
            int r2 = r0.ROUNDS
            r3 = r17[r2]
            r4 = 0
            r3 = r3[r4]
            r1 = r1 ^ r3
            int r3 = r0.C1
            r5 = r17[r2]
            r6 = 1
            r5 = r5[r6]
            r3 = r3 ^ r5
            int r5 = r0.C2
            r7 = r17[r2]
            r8 = 2
            r7 = r7[r8]
            r5 = r5 ^ r7
            int r7 = r2 + -1
            int r9 = r0.C3
            r2 = r17[r2]
            r10 = 3
            r2 = r2[r10]
            r2 = r2 ^ r9
        L_0x0026:
            r9 = 16
            r11 = 24
            r12 = 8
            if (r7 <= r6) goto L_0x018c
            int[] r13 = Tinv0
            r14 = r1 & 255(0xff, float:3.57E-43)
            r14 = r13[r14]
            int r15 = r2 >> 8
            r15 = r15 & 255(0xff, float:3.57E-43)
            r15 = r13[r15]
            int r15 = shift(r15, r11)
            r14 = r14 ^ r15
            int r15 = r5 >> 16
            r15 = r15 & 255(0xff, float:3.57E-43)
            r15 = r13[r15]
            int r15 = shift(r15, r9)
            r14 = r14 ^ r15
            int r15 = r3 >> 24
            r15 = r15 & 255(0xff, float:3.57E-43)
            r15 = r13[r15]
            int r15 = shift(r15, r12)
            r14 = r14 ^ r15
            r15 = r17[r7]
            r15 = r15[r4]
            r14 = r14 ^ r15
            r15 = r3 & 255(0xff, float:3.57E-43)
            r15 = r13[r15]
            int r4 = r1 >> 8
            r4 = r4 & 255(0xff, float:3.57E-43)
            r4 = r13[r4]
            int r4 = shift(r4, r11)
            r4 = r4 ^ r15
            int r15 = r2 >> 16
            r15 = r15 & 255(0xff, float:3.57E-43)
            r15 = r13[r15]
            int r15 = shift(r15, r9)
            r4 = r4 ^ r15
            int r15 = r5 >> 24
            r15 = r15 & 255(0xff, float:3.57E-43)
            r15 = r13[r15]
            int r15 = shift(r15, r12)
            r4 = r4 ^ r15
            r15 = r17[r7]
            r15 = r15[r6]
            r4 = r4 ^ r15
            r15 = r5 & 255(0xff, float:3.57E-43)
            r15 = r13[r15]
            int r6 = r3 >> 8
            r6 = r6 & 255(0xff, float:3.57E-43)
            r6 = r13[r6]
            int r6 = shift(r6, r11)
            r6 = r6 ^ r15
            int r15 = r1 >> 16
            r15 = r15 & 255(0xff, float:3.57E-43)
            r15 = r13[r15]
            int r15 = shift(r15, r9)
            r6 = r6 ^ r15
            int r15 = r2 >> 24
            r15 = r15 & 255(0xff, float:3.57E-43)
            r15 = r13[r15]
            int r15 = shift(r15, r12)
            r6 = r6 ^ r15
            r15 = r17[r7]
            r15 = r15[r8]
            r6 = r6 ^ r15
            r2 = r2 & 255(0xff, float:3.57E-43)
            r2 = r13[r2]
            int r5 = r5 >> 8
            r5 = r5 & 255(0xff, float:3.57E-43)
            r5 = r13[r5]
            int r5 = shift(r5, r11)
            r2 = r2 ^ r5
            int r3 = r3 >> 16
            r3 = r3 & 255(0xff, float:3.57E-43)
            r3 = r13[r3]
            int r3 = shift(r3, r9)
            r2 = r2 ^ r3
            int r1 = r1 >> 24
            r1 = r1 & 255(0xff, float:3.57E-43)
            r1 = r13[r1]
            int r1 = shift(r1, r12)
            r1 = r1 ^ r2
            int r2 = r7 + -1
            r3 = r17[r7]
            r3 = r3[r10]
            r1 = r1 ^ r3
            r3 = r14 & 255(0xff, float:3.57E-43)
            r3 = r13[r3]
            int r5 = r1 >> 8
            r5 = r5 & 255(0xff, float:3.57E-43)
            r5 = r13[r5]
            int r5 = shift(r5, r11)
            r3 = r3 ^ r5
            int r5 = r6 >> 16
            r5 = r5 & 255(0xff, float:3.57E-43)
            r5 = r13[r5]
            int r5 = shift(r5, r9)
            r3 = r3 ^ r5
            int r5 = r4 >> 24
            r5 = r5 & 255(0xff, float:3.57E-43)
            r5 = r13[r5]
            int r5 = shift(r5, r12)
            r3 = r3 ^ r5
            r5 = r17[r2]
            r7 = 0
            r5 = r5[r7]
            r3 = r3 ^ r5
            r5 = r4 & 255(0xff, float:3.57E-43)
            r5 = r13[r5]
            int r7 = r14 >> 8
            r7 = r7 & 255(0xff, float:3.57E-43)
            r7 = r13[r7]
            int r7 = shift(r7, r11)
            r5 = r5 ^ r7
            int r7 = r1 >> 16
            r7 = r7 & 255(0xff, float:3.57E-43)
            r7 = r13[r7]
            int r7 = shift(r7, r9)
            r5 = r5 ^ r7
            int r7 = r6 >> 24
            r7 = r7 & 255(0xff, float:3.57E-43)
            r7 = r13[r7]
            int r7 = shift(r7, r12)
            r5 = r5 ^ r7
            r7 = r17[r2]
            r15 = 1
            r7 = r7[r15]
            r5 = r5 ^ r7
            r7 = r6 & 255(0xff, float:3.57E-43)
            r7 = r13[r7]
            int r15 = r4 >> 8
            r15 = r15 & 255(0xff, float:3.57E-43)
            r15 = r13[r15]
            int r15 = shift(r15, r11)
            r7 = r7 ^ r15
            int r15 = r14 >> 16
            r15 = r15 & 255(0xff, float:3.57E-43)
            r15 = r13[r15]
            int r15 = shift(r15, r9)
            r7 = r7 ^ r15
            int r15 = r1 >> 24
            r15 = r15 & 255(0xff, float:3.57E-43)
            r15 = r13[r15]
            int r15 = shift(r15, r12)
            r7 = r7 ^ r15
            r15 = r17[r2]
            r15 = r15[r8]
            r7 = r7 ^ r15
            r1 = r1 & 255(0xff, float:3.57E-43)
            r1 = r13[r1]
            int r6 = r6 >> r12
            r6 = r6 & 255(0xff, float:3.57E-43)
            r6 = r13[r6]
            int r6 = shift(r6, r11)
            r1 = r1 ^ r6
            int r4 = r4 >> r9
            r4 = r4 & 255(0xff, float:3.57E-43)
            r4 = r13[r4]
            int r4 = shift(r4, r9)
            r1 = r1 ^ r4
            int r4 = r14 >> 24
            r4 = r4 & 255(0xff, float:3.57E-43)
            r4 = r13[r4]
            int r4 = shift(r4, r12)
            r1 = r1 ^ r4
            int r4 = r2 + -1
            r2 = r17[r2]
            r2 = r2[r10]
            r2 = r2 ^ r1
            r1 = r3
            r3 = r5
            r5 = r7
            r6 = 1
            r7 = r4
            r4 = 0
            goto L_0x0026
        L_0x018c:
            int[] r4 = Tinv0
            r6 = r1 & 255(0xff, float:3.57E-43)
            r6 = r4[r6]
            int r13 = r2 >> 8
            r13 = r13 & 255(0xff, float:3.57E-43)
            r13 = r4[r13]
            int r13 = shift(r13, r11)
            r6 = r6 ^ r13
            int r13 = r5 >> 16
            r13 = r13 & 255(0xff, float:3.57E-43)
            r13 = r4[r13]
            int r13 = shift(r13, r9)
            r6 = r6 ^ r13
            int r13 = r3 >> 24
            r13 = r13 & 255(0xff, float:3.57E-43)
            r13 = r4[r13]
            int r13 = shift(r13, r12)
            r6 = r6 ^ r13
            r13 = r17[r7]
            r14 = 0
            r13 = r13[r14]
            r6 = r6 ^ r13
            r13 = r3 & 255(0xff, float:3.57E-43)
            r13 = r4[r13]
            int r14 = r1 >> 8
            r14 = r14 & 255(0xff, float:3.57E-43)
            r14 = r4[r14]
            int r14 = shift(r14, r11)
            r13 = r13 ^ r14
            int r14 = r2 >> 16
            r14 = r14 & 255(0xff, float:3.57E-43)
            r14 = r4[r14]
            int r14 = shift(r14, r9)
            r13 = r13 ^ r14
            int r14 = r5 >> 24
            r14 = r14 & 255(0xff, float:3.57E-43)
            r14 = r4[r14]
            int r14 = shift(r14, r12)
            r13 = r13 ^ r14
            r14 = r17[r7]
            r15 = 1
            r14 = r14[r15]
            r13 = r13 ^ r14
            r14 = r5 & 255(0xff, float:3.57E-43)
            r14 = r4[r14]
            int r15 = r3 >> 8
            r15 = r15 & 255(0xff, float:3.57E-43)
            r15 = r4[r15]
            int r15 = shift(r15, r11)
            r14 = r14 ^ r15
            int r15 = r1 >> 16
            r15 = r15 & 255(0xff, float:3.57E-43)
            r15 = r4[r15]
            int r15 = shift(r15, r9)
            r14 = r14 ^ r15
            int r15 = r2 >> 24
            r15 = r15 & 255(0xff, float:3.57E-43)
            r15 = r4[r15]
            int r15 = shift(r15, r12)
            r14 = r14 ^ r15
            r15 = r17[r7]
            r15 = r15[r8]
            r14 = r14 ^ r15
            r2 = r2 & 255(0xff, float:3.57E-43)
            r2 = r4[r2]
            int r5 = r5 >> r12
            r5 = r5 & 255(0xff, float:3.57E-43)
            r5 = r4[r5]
            int r5 = shift(r5, r11)
            r2 = r2 ^ r5
            int r3 = r3 >> r9
            r3 = r3 & 255(0xff, float:3.57E-43)
            r3 = r4[r3]
            int r3 = shift(r3, r9)
            r2 = r2 ^ r3
            int r1 = r1 >> r11
            r1 = r1 & 255(0xff, float:3.57E-43)
            r1 = r4[r1]
            int r1 = shift(r1, r12)
            r1 = r1 ^ r2
            r2 = r17[r7]
            r2 = r2[r10]
            r1 = r1 ^ r2
            byte[] r2 = Si
            r3 = r6 & 255(0xff, float:3.57E-43)
            byte r3 = r2[r3]
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r4 = r1 >> 8
            r4 = r4 & 255(0xff, float:3.57E-43)
            byte r4 = r2[r4]
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r4 = r4 << r12
            r3 = r3 ^ r4
            int r4 = r14 >> 16
            r4 = r4 & 255(0xff, float:3.57E-43)
            byte r4 = r2[r4]
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r4 = r4 << r9
            r3 = r3 ^ r4
            int r4 = r13 >> 24
            r4 = r4 & 255(0xff, float:3.57E-43)
            byte r4 = r2[r4]
            int r4 = r4 << r11
            r3 = r3 ^ r4
            r4 = 0
            r5 = r17[r4]
            r5 = r5[r4]
            r3 = r3 ^ r5
            r0.C0 = r3
            r3 = r13 & 255(0xff, float:3.57E-43)
            byte r3 = r2[r3]
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r4 = r6 >> 8
            r4 = r4 & 255(0xff, float:3.57E-43)
            byte r4 = r2[r4]
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r4 = r4 << r12
            r3 = r3 ^ r4
            int r4 = r1 >> 16
            r4 = r4 & 255(0xff, float:3.57E-43)
            byte r4 = r2[r4]
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r4 = r4 << r9
            r3 = r3 ^ r4
            int r4 = r14 >> 24
            r4 = r4 & 255(0xff, float:3.57E-43)
            byte r4 = r2[r4]
            int r4 = r4 << r11
            r3 = r3 ^ r4
            r4 = 0
            r5 = r17[r4]
            r4 = 1
            r4 = r5[r4]
            r3 = r3 ^ r4
            r0.C1 = r3
            r3 = r14 & 255(0xff, float:3.57E-43)
            byte r3 = r2[r3]
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r4 = r13 >> 8
            r4 = r4 & 255(0xff, float:3.57E-43)
            byte r4 = r2[r4]
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r4 = r4 << r12
            r3 = r3 ^ r4
            int r4 = r6 >> 16
            r4 = r4 & 255(0xff, float:3.57E-43)
            byte r4 = r2[r4]
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r4 = r4 << r9
            r3 = r3 ^ r4
            int r4 = r1 >> 24
            r4 = r4 & 255(0xff, float:3.57E-43)
            byte r4 = r2[r4]
            int r4 = r4 << r11
            r3 = r3 ^ r4
            r4 = 0
            r5 = r17[r4]
            r4 = r5[r8]
            r3 = r3 ^ r4
            r0.C2 = r3
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = r2[r1]
            r1 = r1 & 255(0xff, float:3.57E-43)
            int r3 = r14 >> 8
            r3 = r3 & 255(0xff, float:3.57E-43)
            byte r3 = r2[r3]
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r3 = r3 << r12
            r1 = r1 ^ r3
            int r3 = r13 >> 16
            r3 = r3 & 255(0xff, float:3.57E-43)
            byte r3 = r2[r3]
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r3 = r3 << r9
            r1 = r1 ^ r3
            int r3 = r6 >> 24
            r3 = r3 & 255(0xff, float:3.57E-43)
            byte r2 = r2[r3]
            int r2 = r2 << r11
            r1 = r1 ^ r2
            r2 = 0
            r2 = r17[r2]
            r2 = r2[r10]
            r1 = r1 ^ r2
            r0.C3 = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.engines.AESEngine.decryptBlock(int[][]):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v22, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v22, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v17, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v21, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v25, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v30, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v33, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v37, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v42, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v10, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v45, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v49, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v56, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v20, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v18, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v22, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v41, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v59, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v63, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v67, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v72, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v9, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v13, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v17, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v22, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v56, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v60, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v64, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v27, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v29, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v18, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v22, resolved type: int} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void encryptBlock(int[][] r17) {
        /*
            r16 = this;
            r0 = r16
            int r1 = r0.C0
            r2 = 0
            r3 = r17[r2]
            r3 = r3[r2]
            r1 = r1 ^ r3
            int r3 = r0.C1
            r4 = r17[r2]
            r5 = 1
            r4 = r4[r5]
            r3 = r3 ^ r4
            int r4 = r0.C2
            r6 = r17[r2]
            r7 = 2
            r6 = r6[r7]
            r4 = r4 ^ r6
            int r6 = r0.C3
            r8 = r17[r2]
            r9 = 3
            r8 = r8[r9]
            r6 = r6 ^ r8
            r8 = 1
        L_0x0023:
            int r10 = r0.ROUNDS
            int r10 = r10 - r5
            r11 = 16
            r12 = 24
            r13 = 8
            if (r8 >= r10) goto L_0x018d
            int[] r10 = T0
            r14 = r1 & 255(0xff, float:3.57E-43)
            r14 = r10[r14]
            int r15 = r3 >> 8
            r15 = r15 & 255(0xff, float:3.57E-43)
            r15 = r10[r15]
            int r15 = shift(r15, r12)
            r14 = r14 ^ r15
            int r15 = r4 >> 16
            r15 = r15 & 255(0xff, float:3.57E-43)
            r15 = r10[r15]
            int r15 = shift(r15, r11)
            r14 = r14 ^ r15
            int r15 = r6 >> 24
            r15 = r15 & 255(0xff, float:3.57E-43)
            r15 = r10[r15]
            int r15 = shift(r15, r13)
            r14 = r14 ^ r15
            r15 = r17[r8]
            r15 = r15[r2]
            r14 = r14 ^ r15
            r15 = r3 & 255(0xff, float:3.57E-43)
            r15 = r10[r15]
            int r2 = r4 >> 8
            r2 = r2 & 255(0xff, float:3.57E-43)
            r2 = r10[r2]
            int r2 = shift(r2, r12)
            r2 = r2 ^ r15
            int r15 = r6 >> 16
            r15 = r15 & 255(0xff, float:3.57E-43)
            r15 = r10[r15]
            int r15 = shift(r15, r11)
            r2 = r2 ^ r15
            int r15 = r1 >> 24
            r15 = r15 & 255(0xff, float:3.57E-43)
            r15 = r10[r15]
            int r15 = shift(r15, r13)
            r2 = r2 ^ r15
            r15 = r17[r8]
            r15 = r15[r5]
            r2 = r2 ^ r15
            r15 = r4 & 255(0xff, float:3.57E-43)
            r15 = r10[r15]
            int r5 = r6 >> 8
            r5 = r5 & 255(0xff, float:3.57E-43)
            r5 = r10[r5]
            int r5 = shift(r5, r12)
            r5 = r5 ^ r15
            int r15 = r1 >> 16
            r15 = r15 & 255(0xff, float:3.57E-43)
            r15 = r10[r15]
            int r15 = shift(r15, r11)
            r5 = r5 ^ r15
            int r15 = r3 >> 24
            r15 = r15 & 255(0xff, float:3.57E-43)
            r15 = r10[r15]
            int r15 = shift(r15, r13)
            r5 = r5 ^ r15
            r15 = r17[r8]
            r15 = r15[r7]
            r5 = r5 ^ r15
            r6 = r6 & 255(0xff, float:3.57E-43)
            r6 = r10[r6]
            int r1 = r1 >> 8
            r1 = r1 & 255(0xff, float:3.57E-43)
            r1 = r10[r1]
            int r1 = shift(r1, r12)
            r1 = r1 ^ r6
            int r3 = r3 >> 16
            r3 = r3 & 255(0xff, float:3.57E-43)
            r3 = r10[r3]
            int r3 = shift(r3, r11)
            r1 = r1 ^ r3
            int r3 = r4 >> 24
            r3 = r3 & 255(0xff, float:3.57E-43)
            r3 = r10[r3]
            int r3 = shift(r3, r13)
            r1 = r1 ^ r3
            int r3 = r8 + 1
            r4 = r17[r8]
            r4 = r4[r9]
            r1 = r1 ^ r4
            r4 = r14 & 255(0xff, float:3.57E-43)
            r4 = r10[r4]
            int r6 = r2 >> 8
            r6 = r6 & 255(0xff, float:3.57E-43)
            r6 = r10[r6]
            int r6 = shift(r6, r12)
            r4 = r4 ^ r6
            int r6 = r5 >> 16
            r6 = r6 & 255(0xff, float:3.57E-43)
            r6 = r10[r6]
            int r6 = shift(r6, r11)
            r4 = r4 ^ r6
            int r6 = r1 >> 24
            r6 = r6 & 255(0xff, float:3.57E-43)
            r6 = r10[r6]
            int r6 = shift(r6, r13)
            r4 = r4 ^ r6
            r6 = r17[r3]
            r8 = 0
            r6 = r6[r8]
            r4 = r4 ^ r6
            r6 = r2 & 255(0xff, float:3.57E-43)
            r6 = r10[r6]
            int r8 = r5 >> 8
            r8 = r8 & 255(0xff, float:3.57E-43)
            r8 = r10[r8]
            int r8 = shift(r8, r12)
            r6 = r6 ^ r8
            int r8 = r1 >> 16
            r8 = r8 & 255(0xff, float:3.57E-43)
            r8 = r10[r8]
            int r8 = shift(r8, r11)
            r6 = r6 ^ r8
            int r8 = r14 >> 24
            r8 = r8 & 255(0xff, float:3.57E-43)
            r8 = r10[r8]
            int r8 = shift(r8, r13)
            r6 = r6 ^ r8
            r8 = r17[r3]
            r15 = 1
            r8 = r8[r15]
            r6 = r6 ^ r8
            r8 = r5 & 255(0xff, float:3.57E-43)
            r8 = r10[r8]
            int r15 = r1 >> 8
            r15 = r15 & 255(0xff, float:3.57E-43)
            r15 = r10[r15]
            int r15 = shift(r15, r12)
            r8 = r8 ^ r15
            int r15 = r14 >> 16
            r15 = r15 & 255(0xff, float:3.57E-43)
            r15 = r10[r15]
            int r15 = shift(r15, r11)
            r8 = r8 ^ r15
            int r15 = r2 >> 24
            r15 = r15 & 255(0xff, float:3.57E-43)
            r15 = r10[r15]
            int r15 = shift(r15, r13)
            r8 = r8 ^ r15
            r15 = r17[r3]
            r15 = r15[r7]
            r8 = r8 ^ r15
            r1 = r1 & 255(0xff, float:3.57E-43)
            r1 = r10[r1]
            int r14 = r14 >> r13
            r14 = r14 & 255(0xff, float:3.57E-43)
            r14 = r10[r14]
            int r14 = shift(r14, r12)
            r1 = r1 ^ r14
            int r2 = r2 >> r11
            r2 = r2 & 255(0xff, float:3.57E-43)
            r2 = r10[r2]
            int r2 = shift(r2, r11)
            r1 = r1 ^ r2
            int r2 = r5 >> 24
            r2 = r2 & 255(0xff, float:3.57E-43)
            r2 = r10[r2]
            int r2 = shift(r2, r13)
            r1 = r1 ^ r2
            int r2 = r3 + 1
            r3 = r17[r3]
            r3 = r3[r9]
            r1 = r1 ^ r3
            r3 = r6
            r5 = 1
            r6 = r1
            r1 = r4
            r4 = r8
            r8 = r2
            r2 = 0
            goto L_0x0023
        L_0x018d:
            int[] r2 = T0
            r5 = r1 & 255(0xff, float:3.57E-43)
            r5 = r2[r5]
            int r10 = r3 >> 8
            r10 = r10 & 255(0xff, float:3.57E-43)
            r10 = r2[r10]
            int r10 = shift(r10, r12)
            r5 = r5 ^ r10
            int r10 = r4 >> 16
            r10 = r10 & 255(0xff, float:3.57E-43)
            r10 = r2[r10]
            int r10 = shift(r10, r11)
            r5 = r5 ^ r10
            int r10 = r6 >> 24
            r10 = r10 & 255(0xff, float:3.57E-43)
            r10 = r2[r10]
            int r10 = shift(r10, r13)
            r5 = r5 ^ r10
            r10 = r17[r8]
            r14 = 0
            r10 = r10[r14]
            r5 = r5 ^ r10
            r10 = r3 & 255(0xff, float:3.57E-43)
            r10 = r2[r10]
            int r14 = r4 >> 8
            r14 = r14 & 255(0xff, float:3.57E-43)
            r14 = r2[r14]
            int r14 = shift(r14, r12)
            r10 = r10 ^ r14
            int r14 = r6 >> 16
            r14 = r14 & 255(0xff, float:3.57E-43)
            r14 = r2[r14]
            int r14 = shift(r14, r11)
            r10 = r10 ^ r14
            int r14 = r1 >> 24
            r14 = r14 & 255(0xff, float:3.57E-43)
            r14 = r2[r14]
            int r14 = shift(r14, r13)
            r10 = r10 ^ r14
            r14 = r17[r8]
            r15 = 1
            r14 = r14[r15]
            r10 = r10 ^ r14
            r14 = r4 & 255(0xff, float:3.57E-43)
            r14 = r2[r14]
            int r15 = r6 >> 8
            r15 = r15 & 255(0xff, float:3.57E-43)
            r15 = r2[r15]
            int r15 = shift(r15, r12)
            r14 = r14 ^ r15
            int r15 = r1 >> 16
            r15 = r15 & 255(0xff, float:3.57E-43)
            r15 = r2[r15]
            int r15 = shift(r15, r11)
            r14 = r14 ^ r15
            int r15 = r3 >> 24
            r15 = r15 & 255(0xff, float:3.57E-43)
            r15 = r2[r15]
            int r15 = shift(r15, r13)
            r14 = r14 ^ r15
            r15 = r17[r8]
            r15 = r15[r7]
            r14 = r14 ^ r15
            r6 = r6 & 255(0xff, float:3.57E-43)
            r6 = r2[r6]
            int r1 = r1 >> r13
            r1 = r1 & 255(0xff, float:3.57E-43)
            r1 = r2[r1]
            int r1 = shift(r1, r12)
            r1 = r1 ^ r6
            int r3 = r3 >> r11
            r3 = r3 & 255(0xff, float:3.57E-43)
            r3 = r2[r3]
            int r3 = shift(r3, r11)
            r1 = r1 ^ r3
            int r3 = r4 >> 24
            r3 = r3 & 255(0xff, float:3.57E-43)
            r2 = r2[r3]
            int r2 = shift(r2, r13)
            r1 = r1 ^ r2
            int r2 = r8 + 1
            r3 = r17[r8]
            r3 = r3[r9]
            r1 = r1 ^ r3
            byte[] r3 = S
            r4 = r5 & 255(0xff, float:3.57E-43)
            byte r4 = r3[r4]
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r6 = r10 >> 8
            r6 = r6 & 255(0xff, float:3.57E-43)
            byte r6 = r3[r6]
            r6 = r6 & 255(0xff, float:3.57E-43)
            int r6 = r6 << r13
            r4 = r4 ^ r6
            int r6 = r14 >> 16
            r6 = r6 & 255(0xff, float:3.57E-43)
            byte r6 = r3[r6]
            r6 = r6 & 255(0xff, float:3.57E-43)
            int r6 = r6 << r11
            r4 = r4 ^ r6
            int r6 = r1 >> 24
            r6 = r6 & 255(0xff, float:3.57E-43)
            byte r6 = r3[r6]
            int r6 = r6 << r12
            r4 = r4 ^ r6
            r6 = r17[r2]
            r8 = 0
            r6 = r6[r8]
            r4 = r4 ^ r6
            r0.C0 = r4
            r4 = r10 & 255(0xff, float:3.57E-43)
            byte r4 = r3[r4]
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r6 = r14 >> 8
            r6 = r6 & 255(0xff, float:3.57E-43)
            byte r6 = r3[r6]
            r6 = r6 & 255(0xff, float:3.57E-43)
            int r6 = r6 << r13
            r4 = r4 ^ r6
            int r6 = r1 >> 16
            r6 = r6 & 255(0xff, float:3.57E-43)
            byte r6 = r3[r6]
            r6 = r6 & 255(0xff, float:3.57E-43)
            int r6 = r6 << r11
            r4 = r4 ^ r6
            int r6 = r5 >> 24
            r6 = r6 & 255(0xff, float:3.57E-43)
            byte r6 = r3[r6]
            int r6 = r6 << r12
            r4 = r4 ^ r6
            r6 = r17[r2]
            r8 = 1
            r6 = r6[r8]
            r4 = r4 ^ r6
            r0.C1 = r4
            r4 = r14 & 255(0xff, float:3.57E-43)
            byte r4 = r3[r4]
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r6 = r1 >> 8
            r6 = r6 & 255(0xff, float:3.57E-43)
            byte r6 = r3[r6]
            r6 = r6 & 255(0xff, float:3.57E-43)
            int r6 = r6 << r13
            r4 = r4 ^ r6
            int r6 = r5 >> 16
            r6 = r6 & 255(0xff, float:3.57E-43)
            byte r6 = r3[r6]
            r6 = r6 & 255(0xff, float:3.57E-43)
            int r6 = r6 << r11
            r4 = r4 ^ r6
            int r6 = r10 >> 24
            r6 = r6 & 255(0xff, float:3.57E-43)
            byte r6 = r3[r6]
            int r6 = r6 << r12
            r4 = r4 ^ r6
            r6 = r17[r2]
            r6 = r6[r7]
            r4 = r4 ^ r6
            r0.C2 = r4
            r1 = r1 & 255(0xff, float:3.57E-43)
            byte r1 = r3[r1]
            r1 = r1 & 255(0xff, float:3.57E-43)
            int r4 = r5 >> 8
            r4 = r4 & 255(0xff, float:3.57E-43)
            byte r4 = r3[r4]
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r4 = r4 << r13
            r1 = r1 ^ r4
            int r4 = r10 >> 16
            r4 = r4 & 255(0xff, float:3.57E-43)
            byte r4 = r3[r4]
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r4 = r4 << r11
            r1 = r1 ^ r4
            int r4 = r14 >> 24
            r4 = r4 & 255(0xff, float:3.57E-43)
            byte r3 = r3[r4]
            int r3 = r3 << r12
            r1 = r1 ^ r3
            r2 = r17[r2]
            r2 = r2[r9]
            r1 = r1 ^ r2
            r0.C3 = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.engines.AESEngine.encryptBlock(int[][]):void");
    }

    private int[][] generateWorkingKey(byte[] bArr, boolean z) {
        byte[] bArr2 = bArr;
        int length = bArr2.length;
        if (length < 16 || length > 32 || (length & 7) != 0) {
            throw new IllegalArgumentException("Key length not 128/192/256 bits.");
        }
        int i = length >>> 2;
        int i2 = i + 6;
        this.ROUNDS = i2;
        int[] iArr = new int[2];
        iArr[1] = 4;
        iArr[0] = i2 + 1;
        int[][] iArr2 = (int[][]) Array.newInstance(int.class, iArr);
        if (i == 4) {
            int littleEndianToInt = Pack.littleEndianToInt(bArr2, 0);
            iArr2[0][0] = littleEndianToInt;
            int littleEndianToInt2 = Pack.littleEndianToInt(bArr2, 4);
            iArr2[0][1] = littleEndianToInt2;
            int littleEndianToInt3 = Pack.littleEndianToInt(bArr2, 8);
            iArr2[0][2] = littleEndianToInt3;
            int littleEndianToInt4 = Pack.littleEndianToInt(bArr2, 12);
            iArr2[0][3] = littleEndianToInt4;
            for (int i3 = 1; i3 <= 10; i3++) {
                littleEndianToInt ^= subWord(shift(littleEndianToInt4, 8)) ^ rcon[i3 - 1];
                iArr2[i3][0] = littleEndianToInt;
                littleEndianToInt2 ^= littleEndianToInt;
                iArr2[i3][1] = littleEndianToInt2;
                littleEndianToInt3 ^= littleEndianToInt2;
                iArr2[i3][2] = littleEndianToInt3;
                littleEndianToInt4 ^= littleEndianToInt3;
                iArr2[i3][3] = littleEndianToInt4;
            }
        } else if (i == 6) {
            int littleEndianToInt5 = Pack.littleEndianToInt(bArr2, 0);
            iArr2[0][0] = littleEndianToInt5;
            int littleEndianToInt6 = Pack.littleEndianToInt(bArr2, 4);
            iArr2[0][1] = littleEndianToInt6;
            int littleEndianToInt7 = Pack.littleEndianToInt(bArr2, 8);
            iArr2[0][2] = littleEndianToInt7;
            int littleEndianToInt8 = Pack.littleEndianToInt(bArr2, 12);
            iArr2[0][3] = littleEndianToInt8;
            int littleEndianToInt9 = Pack.littleEndianToInt(bArr2, 16);
            iArr2[1][0] = littleEndianToInt9;
            int littleEndianToInt10 = Pack.littleEndianToInt(bArr2, 20);
            iArr2[1][1] = littleEndianToInt10;
            int subWord = littleEndianToInt5 ^ (subWord(shift(littleEndianToInt10, 8)) ^ 1);
            iArr2[1][2] = subWord;
            int i4 = littleEndianToInt6 ^ subWord;
            iArr2[1][3] = i4;
            int i5 = littleEndianToInt7 ^ i4;
            iArr2[2][0] = i5;
            int i6 = littleEndianToInt8 ^ i5;
            iArr2[2][1] = i6;
            int i7 = littleEndianToInt9 ^ i6;
            iArr2[2][2] = i7;
            int i8 = littleEndianToInt10 ^ i7;
            iArr2[2][3] = i8;
            int i9 = 2;
            for (int i10 = 3; i10 < 12; i10 += 3) {
                int subWord2 = subWord(shift(i8, 8)) ^ i9;
                int i11 = i9 << 1;
                int i12 = subWord ^ subWord2;
                iArr2[i10][0] = i12;
                int i13 = i4 ^ i12;
                iArr2[i10][1] = i13;
                int i14 = i5 ^ i13;
                iArr2[i10][2] = i14;
                int i15 = i6 ^ i14;
                iArr2[i10][3] = i15;
                int i16 = i7 ^ i15;
                int i17 = i10 + 1;
                iArr2[i17][0] = i16;
                int i18 = i8 ^ i16;
                iArr2[i17][1] = i18;
                int subWord3 = subWord(shift(i18, 8)) ^ i11;
                i9 = i11 << 1;
                subWord = i12 ^ subWord3;
                iArr2[i17][2] = subWord;
                i4 = i13 ^ subWord;
                iArr2[i17][3] = i4;
                i5 = i14 ^ i4;
                int i19 = i10 + 2;
                iArr2[i19][0] = i5;
                i6 = i15 ^ i5;
                iArr2[i19][1] = i6;
                i7 = i16 ^ i6;
                iArr2[i19][2] = i7;
                i8 = i18 ^ i7;
                iArr2[i19][3] = i8;
            }
            int subWord4 = (subWord(shift(i8, 8)) ^ i9) ^ subWord;
            iArr2[12][0] = subWord4;
            int i20 = subWord4 ^ i4;
            iArr2[12][1] = i20;
            int i21 = i20 ^ i5;
            iArr2[12][2] = i21;
            iArr2[12][3] = i21 ^ i6;
        } else if (i == 8) {
            int littleEndianToInt11 = Pack.littleEndianToInt(bArr2, 0);
            iArr2[0][0] = littleEndianToInt11;
            int littleEndianToInt12 = Pack.littleEndianToInt(bArr2, 4);
            iArr2[0][1] = littleEndianToInt12;
            int littleEndianToInt13 = Pack.littleEndianToInt(bArr2, 8);
            iArr2[0][2] = littleEndianToInt13;
            int littleEndianToInt14 = Pack.littleEndianToInt(bArr2, 12);
            iArr2[0][3] = littleEndianToInt14;
            int littleEndianToInt15 = Pack.littleEndianToInt(bArr2, 16);
            iArr2[1][0] = littleEndianToInt15;
            int littleEndianToInt16 = Pack.littleEndianToInt(bArr2, 20);
            iArr2[1][1] = littleEndianToInt16;
            int littleEndianToInt17 = Pack.littleEndianToInt(bArr2, 24);
            iArr2[1][2] = littleEndianToInt17;
            int littleEndianToInt18 = Pack.littleEndianToInt(bArr2, 28);
            iArr2[1][3] = littleEndianToInt18;
            int i22 = 1;
            for (int i23 = 2; i23 < 14; i23 += 2) {
                int subWord5 = subWord(shift(littleEndianToInt18, 8)) ^ i22;
                i22 <<= 1;
                littleEndianToInt11 ^= subWord5;
                iArr2[i23][0] = littleEndianToInt11;
                littleEndianToInt12 ^= littleEndianToInt11;
                iArr2[i23][1] = littleEndianToInt12;
                littleEndianToInt13 ^= littleEndianToInt12;
                iArr2[i23][2] = littleEndianToInt13;
                littleEndianToInt14 ^= littleEndianToInt13;
                iArr2[i23][3] = littleEndianToInt14;
                littleEndianToInt15 ^= subWord(littleEndianToInt14);
                int i24 = i23 + 1;
                iArr2[i24][0] = littleEndianToInt15;
                littleEndianToInt16 ^= littleEndianToInt15;
                iArr2[i24][1] = littleEndianToInt16;
                littleEndianToInt17 ^= littleEndianToInt16;
                iArr2[i24][2] = littleEndianToInt17;
                littleEndianToInt18 ^= littleEndianToInt17;
                iArr2[i24][3] = littleEndianToInt18;
            }
            int subWord6 = (subWord(shift(littleEndianToInt18, 8)) ^ i22) ^ littleEndianToInt11;
            iArr2[14][0] = subWord6;
            int i25 = subWord6 ^ littleEndianToInt12;
            iArr2[14][1] = i25;
            int i26 = i25 ^ littleEndianToInt13;
            iArr2[14][2] = i26;
            iArr2[14][3] = i26 ^ littleEndianToInt14;
        } else {
            throw new IllegalStateException("Should never get here");
        }
        if (!z) {
            for (int i27 = 1; i27 < this.ROUNDS; i27++) {
                for (int i28 = 0; i28 < 4; i28++) {
                    iArr2[i27][i28] = inv_mcol(iArr2[i27][i28]);
                }
            }
        }
        return iArr2;
    }

    private static int inv_mcol(int i) {
        int shift = shift(i, 8) ^ i;
        int FFmulX = i ^ FFmulX(shift);
        int FFmulX2 = shift ^ FFmulX2(FFmulX);
        return FFmulX ^ (FFmulX2 ^ shift(FFmulX2, 16));
    }

    private void packBlock(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = this.C0;
        bArr[i] = (byte) i3;
        int i4 = i2 + 1;
        bArr[i2] = (byte) (i3 >> 8);
        int i5 = i4 + 1;
        bArr[i4] = (byte) (i3 >> 16);
        int i6 = i5 + 1;
        bArr[i5] = (byte) (i3 >> 24);
        int i7 = i6 + 1;
        int i8 = this.C1;
        bArr[i6] = (byte) i8;
        int i9 = i7 + 1;
        bArr[i7] = (byte) (i8 >> 8);
        int i10 = i9 + 1;
        bArr[i9] = (byte) (i8 >> 16);
        int i11 = i10 + 1;
        bArr[i10] = (byte) (i8 >> 24);
        int i12 = i11 + 1;
        int i13 = this.C2;
        bArr[i11] = (byte) i13;
        int i14 = i12 + 1;
        bArr[i12] = (byte) (i13 >> 8);
        int i15 = i14 + 1;
        bArr[i14] = (byte) (i13 >> 16);
        int i16 = i15 + 1;
        bArr[i15] = (byte) (i13 >> 24);
        int i17 = i16 + 1;
        int i18 = this.C3;
        bArr[i16] = (byte) i18;
        int i19 = i17 + 1;
        bArr[i17] = (byte) (i18 >> 8);
        bArr[i19] = (byte) (i18 >> 16);
        bArr[i19 + 1] = (byte) (i18 >> 24);
    }

    private static int shift(int i, int i2) {
        return (i << (-i2)) | (i >>> i2);
    }

    private static int subWord(int i) {
        byte[] bArr = S;
        return (bArr[(i >> 24) & 255] << 24) | (bArr[i & 255] & UByte.MAX_VALUE) | ((bArr[(i >> 8) & 255] & UByte.MAX_VALUE) << 8) | ((bArr[(i >> 16) & 255] & UByte.MAX_VALUE) << Tnaf.POW_2_WIDTH);
    }

    private void unpackBlock(byte[] bArr, int i) {
        int i2 = i + 1;
        byte b = bArr[i] & UByte.MAX_VALUE;
        this.C0 = b;
        int i3 = i2 + 1;
        byte b2 = b | ((bArr[i2] & UByte.MAX_VALUE) << 8);
        this.C0 = b2;
        int i4 = i3 + 1;
        byte b3 = b2 | ((bArr[i3] & UByte.MAX_VALUE) << Tnaf.POW_2_WIDTH);
        this.C0 = b3;
        int i5 = i4 + 1;
        this.C0 = b3 | (bArr[i4] << 24);
        int i6 = i5 + 1;
        byte b4 = bArr[i5] & UByte.MAX_VALUE;
        this.C1 = b4;
        int i7 = i6 + 1;
        byte b5 = ((bArr[i6] & UByte.MAX_VALUE) << 8) | b4;
        this.C1 = b5;
        int i8 = i7 + 1;
        byte b6 = b5 | ((bArr[i7] & UByte.MAX_VALUE) << Tnaf.POW_2_WIDTH);
        this.C1 = b6;
        int i9 = i8 + 1;
        this.C1 = b6 | (bArr[i8] << 24);
        int i10 = i9 + 1;
        byte b7 = bArr[i9] & UByte.MAX_VALUE;
        this.C2 = b7;
        int i11 = i10 + 1;
        byte b8 = ((bArr[i10] & UByte.MAX_VALUE) << 8) | b7;
        this.C2 = b8;
        int i12 = i11 + 1;
        byte b9 = b8 | ((bArr[i11] & UByte.MAX_VALUE) << Tnaf.POW_2_WIDTH);
        this.C2 = b9;
        int i13 = i12 + 1;
        this.C2 = b9 | (bArr[i12] << 24);
        int i14 = i13 + 1;
        byte b10 = bArr[i13] & UByte.MAX_VALUE;
        this.C3 = b10;
        int i15 = i14 + 1;
        byte b11 = ((bArr[i14] & UByte.MAX_VALUE) << 8) | b10;
        this.C3 = b11;
        byte b12 = b11 | ((bArr[i15] & UByte.MAX_VALUE) << Tnaf.POW_2_WIDTH);
        this.C3 = b12;
        this.C3 = (bArr[i15 + 1] << 24) | b12;
    }

    public String getAlgorithmName() {
        return "AES";
    }

    public int getBlockSize() {
        return 16;
    }

    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.WorkingKey = generateWorkingKey(((KeyParameter) cipherParameters).getKey(), z);
            this.forEncryption = z;
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to AES init - " + cipherParameters.getClass().getName());
    }

    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.WorkingKey == null) {
            throw new IllegalStateException("AES engine not initialised");
        } else if (i + 16 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (i2 + 16 <= bArr2.length) {
            boolean z = this.forEncryption;
            unpackBlock(bArr, i);
            int[][] iArr = this.WorkingKey;
            if (z) {
                encryptBlock(iArr);
            } else {
                decryptBlock(iArr);
            }
            packBlock(bArr2, i2);
            return 16;
        } else {
            throw new OutputLengthException("output buffer too short");
        }
    }

    public void reset() {
    }
}
