package fm.liveswitch;

import com.twilio.video.VideoDimensions;
import kotlin.jvm.internal.ByteCompanionObject;
import okio.Utf8;
import org.bouncycastle.crypto.signers.PSSSigner;

class FecPacketMaskTable {
    private static byte[] _burstyMask10_1;
    private static byte[] _burstyMask10_10;
    private static byte[] _burstyMask10_2;
    private static byte[] _burstyMask10_3;
    private static byte[] _burstyMask10_4;
    private static byte[] _burstyMask10_5;
    private static byte[] _burstyMask10_6;
    private static byte[] _burstyMask10_7;
    private static byte[] _burstyMask10_8;
    private static byte[] _burstyMask10_9;
    private static byte[] _burstyMask11_1;
    private static byte[] _burstyMask11_10;
    private static byte[] _burstyMask11_11;
    private static byte[] _burstyMask11_2;
    private static byte[] _burstyMask11_3;
    private static byte[] _burstyMask11_4;
    private static byte[] _burstyMask11_5;
    private static byte[] _burstyMask11_6;
    private static byte[] _burstyMask11_7;
    private static byte[] _burstyMask11_8;
    private static byte[] _burstyMask11_9;
    private static byte[] _burstyMask12_1;
    private static byte[] _burstyMask12_10;
    private static byte[] _burstyMask12_11;
    private static byte[] _burstyMask12_12;
    private static byte[] _burstyMask12_2;
    private static byte[] _burstyMask12_3;
    private static byte[] _burstyMask12_4;
    private static byte[] _burstyMask12_5;
    private static byte[] _burstyMask12_6;
    private static byte[] _burstyMask12_7;
    private static byte[] _burstyMask12_8;
    private static byte[] _burstyMask12_9;
    private static byte[] _burstyMask1_1;
    private static byte[] _burstyMask2_1;
    private static byte[] _burstyMask2_2;
    private static byte[] _burstyMask3_1;
    private static byte[] _burstyMask3_2;
    private static byte[] _burstyMask3_3;
    private static byte[] _burstyMask4_1;
    private static byte[] _burstyMask4_2;
    private static byte[] _burstyMask4_3;
    private static byte[] _burstyMask4_4;
    private static byte[] _burstyMask5_1;
    private static byte[] _burstyMask5_2;
    private static byte[] _burstyMask5_3;
    private static byte[] _burstyMask5_4;
    private static byte[] _burstyMask5_5;
    private static byte[] _burstyMask6_1;
    private static byte[] _burstyMask6_2;
    private static byte[] _burstyMask6_3;
    private static byte[] _burstyMask6_4;
    private static byte[] _burstyMask6_5;
    private static byte[] _burstyMask6_6;
    private static byte[] _burstyMask7_1;
    private static byte[] _burstyMask7_2;
    private static byte[] _burstyMask7_3;
    private static byte[] _burstyMask7_4;
    private static byte[] _burstyMask7_5;
    private static byte[] _burstyMask7_6;
    private static byte[] _burstyMask7_7;
    private static byte[] _burstyMask8_1;
    private static byte[] _burstyMask8_2;
    private static byte[] _burstyMask8_3;
    private static byte[] _burstyMask8_4;
    private static byte[] _burstyMask8_5;
    private static byte[] _burstyMask8_6;
    private static byte[] _burstyMask8_7;
    private static byte[] _burstyMask8_8;
    private static byte[] _burstyMask9_1;
    private static byte[] _burstyMask9_2;
    private static byte[] _burstyMask9_3;
    private static byte[] _burstyMask9_4;
    private static byte[] _burstyMask9_5;
    private static byte[] _burstyMask9_6;
    private static byte[] _burstyMask9_7;
    private static byte[] _burstyMask9_8;
    private static byte[] _burstyMask9_9;
    private static byte[][] _burstyPacketMask1;
    private static byte[][] _burstyPacketMask10;
    private static byte[][] _burstyPacketMask11;
    private static byte[][] _burstyPacketMask12;
    private static byte[][] _burstyPacketMask2;
    private static byte[][] _burstyPacketMask3;
    private static byte[][] _burstyPacketMask4;
    private static byte[][] _burstyPacketMask5;
    private static byte[][] _burstyPacketMask6;
    private static byte[][] _burstyPacketMask7;
    private static byte[][] _burstyPacketMask8;
    private static byte[][] _burstyPacketMask9;
    private static byte[][][] _burstyPacketMaskTable;
    private static byte[] _randomMask10_1;
    private static byte[] _randomMask10_10;
    private static byte[] _randomMask10_2;
    private static byte[] _randomMask10_3;
    private static byte[] _randomMask10_4;
    private static byte[] _randomMask10_5;
    private static byte[] _randomMask10_6;
    private static byte[] _randomMask10_7;
    private static byte[] _randomMask10_8;
    private static byte[] _randomMask10_9;
    private static byte[] _randomMask11_1;
    private static byte[] _randomMask11_10;
    private static byte[] _randomMask11_11;
    private static byte[] _randomMask11_2;
    private static byte[] _randomMask11_3;
    private static byte[] _randomMask11_4;
    private static byte[] _randomMask11_5;
    private static byte[] _randomMask11_6;
    private static byte[] _randomMask11_7;
    private static byte[] _randomMask11_8;
    private static byte[] _randomMask11_9;
    private static byte[] _randomMask12_1;
    private static byte[] _randomMask12_10;
    private static byte[] _randomMask12_11;
    private static byte[] _randomMask12_12;
    private static byte[] _randomMask12_2;
    private static byte[] _randomMask12_3;
    private static byte[] _randomMask12_4;
    private static byte[] _randomMask12_5;
    private static byte[] _randomMask12_6;
    private static byte[] _randomMask12_7;
    private static byte[] _randomMask12_8;
    private static byte[] _randomMask12_9;
    private static byte[] _randomMask13_1;
    private static byte[] _randomMask13_10;
    private static byte[] _randomMask13_11;
    private static byte[] _randomMask13_12;
    private static byte[] _randomMask13_13;
    private static byte[] _randomMask13_2;
    private static byte[] _randomMask13_3;
    private static byte[] _randomMask13_4;
    private static byte[] _randomMask13_5;
    private static byte[] _randomMask13_6;
    private static byte[] _randomMask13_7;
    private static byte[] _randomMask13_8;
    private static byte[] _randomMask13_9;
    private static byte[] _randomMask14_1;
    private static byte[] _randomMask14_10;
    private static byte[] _randomMask14_11;
    private static byte[] _randomMask14_12;
    private static byte[] _randomMask14_13;
    private static byte[] _randomMask14_14;
    private static byte[] _randomMask14_2;
    private static byte[] _randomMask14_3;
    private static byte[] _randomMask14_4;
    private static byte[] _randomMask14_5;
    private static byte[] _randomMask14_6;
    private static byte[] _randomMask14_7;
    private static byte[] _randomMask14_8;
    private static byte[] _randomMask14_9;
    private static byte[] _randomMask15_1;
    private static byte[] _randomMask15_10;
    private static byte[] _randomMask15_11;
    private static byte[] _randomMask15_12;
    private static byte[] _randomMask15_13;
    private static byte[] _randomMask15_14;
    private static byte[] _randomMask15_15;
    private static byte[] _randomMask15_2;
    private static byte[] _randomMask15_3;
    private static byte[] _randomMask15_4;
    private static byte[] _randomMask15_5;
    private static byte[] _randomMask15_6;
    private static byte[] _randomMask15_7;
    private static byte[] _randomMask15_8;
    private static byte[] _randomMask15_9;
    private static byte[] _randomMask16_1;
    private static byte[] _randomMask16_10;
    private static byte[] _randomMask16_11;
    private static byte[] _randomMask16_12;
    private static byte[] _randomMask16_13;
    private static byte[] _randomMask16_14;
    private static byte[] _randomMask16_15;
    private static byte[] _randomMask16_16;
    private static byte[] _randomMask16_2;
    private static byte[] _randomMask16_3;
    private static byte[] _randomMask16_4;
    private static byte[] _randomMask16_5;
    private static byte[] _randomMask16_6;
    private static byte[] _randomMask16_7;
    private static byte[] _randomMask16_8;
    private static byte[] _randomMask16_9;
    private static byte[] _randomMask17_1;
    private static byte[] _randomMask17_10;
    private static byte[] _randomMask17_11;
    private static byte[] _randomMask17_12;
    private static byte[] _randomMask17_13;
    private static byte[] _randomMask17_14;
    private static byte[] _randomMask17_15;
    private static byte[] _randomMask17_16;
    private static byte[] _randomMask17_17;
    private static byte[] _randomMask17_2;
    private static byte[] _randomMask17_3;
    private static byte[] _randomMask17_4;
    private static byte[] _randomMask17_5;
    private static byte[] _randomMask17_6;
    private static byte[] _randomMask17_7;
    private static byte[] _randomMask17_8;
    private static byte[] _randomMask17_9;
    private static byte[] _randomMask18_1;
    private static byte[] _randomMask18_10;
    private static byte[] _randomMask18_11;
    private static byte[] _randomMask18_12;
    private static byte[] _randomMask18_13;
    private static byte[] _randomMask18_14;
    private static byte[] _randomMask18_15;
    private static byte[] _randomMask18_16;
    private static byte[] _randomMask18_17;
    private static byte[] _randomMask18_18;
    private static byte[] _randomMask18_2;
    private static byte[] _randomMask18_3;
    private static byte[] _randomMask18_4;
    private static byte[] _randomMask18_5;
    private static byte[] _randomMask18_6;
    private static byte[] _randomMask18_7;
    private static byte[] _randomMask18_8;
    private static byte[] _randomMask18_9;
    private static byte[] _randomMask19_1;
    private static byte[] _randomMask19_10;
    private static byte[] _randomMask19_11;
    private static byte[] _randomMask19_12;
    private static byte[] _randomMask19_13;
    private static byte[] _randomMask19_14;
    private static byte[] _randomMask19_15;
    private static byte[] _randomMask19_16;
    private static byte[] _randomMask19_17;
    private static byte[] _randomMask19_18;
    private static byte[] _randomMask19_19;
    private static byte[] _randomMask19_2;
    private static byte[] _randomMask19_3;
    private static byte[] _randomMask19_4;
    private static byte[] _randomMask19_5;
    private static byte[] _randomMask19_6;
    private static byte[] _randomMask19_7;
    private static byte[] _randomMask19_8;
    private static byte[] _randomMask19_9;
    private static byte[] _randomMask1_1;
    private static byte[] _randomMask20_1;
    private static byte[] _randomMask20_10;
    private static byte[] _randomMask20_11;
    private static byte[] _randomMask20_12;
    private static byte[] _randomMask20_13;
    private static byte[] _randomMask20_14;
    private static byte[] _randomMask20_15;
    private static byte[] _randomMask20_16;
    private static byte[] _randomMask20_17;
    private static byte[] _randomMask20_18;
    private static byte[] _randomMask20_19;
    private static byte[] _randomMask20_2;
    private static byte[] _randomMask20_20;
    private static byte[] _randomMask20_3;
    private static byte[] _randomMask20_4;
    private static byte[] _randomMask20_5;
    private static byte[] _randomMask20_6;
    private static byte[] _randomMask20_7;
    private static byte[] _randomMask20_8;
    private static byte[] _randomMask20_9;
    private static byte[] _randomMask21_1;
    private static byte[] _randomMask21_10;
    private static byte[] _randomMask21_11;
    private static byte[] _randomMask21_12;
    private static byte[] _randomMask21_13;
    private static byte[] _randomMask21_14;
    private static byte[] _randomMask21_15;
    private static byte[] _randomMask21_16;
    private static byte[] _randomMask21_17;
    private static byte[] _randomMask21_18;
    private static byte[] _randomMask21_19;
    private static byte[] _randomMask21_2;
    private static byte[] _randomMask21_20;
    private static byte[] _randomMask21_21;
    private static byte[] _randomMask21_3;
    private static byte[] _randomMask21_4;
    private static byte[] _randomMask21_5;
    private static byte[] _randomMask21_6;
    private static byte[] _randomMask21_7;
    private static byte[] _randomMask21_8;
    private static byte[] _randomMask21_9;
    private static byte[] _randomMask22_1;
    private static byte[] _randomMask22_10;
    private static byte[] _randomMask22_11;
    private static byte[] _randomMask22_12;
    private static byte[] _randomMask22_13;
    private static byte[] _randomMask22_14;
    private static byte[] _randomMask22_15;
    private static byte[] _randomMask22_16;
    private static byte[] _randomMask22_17;
    private static byte[] _randomMask22_18;
    private static byte[] _randomMask22_19;
    private static byte[] _randomMask22_2;
    private static byte[] _randomMask22_20;
    private static byte[] _randomMask22_21;
    private static byte[] _randomMask22_22;
    private static byte[] _randomMask22_3;
    private static byte[] _randomMask22_4;
    private static byte[] _randomMask22_5;
    private static byte[] _randomMask22_6;
    private static byte[] _randomMask22_7;
    private static byte[] _randomMask22_8;
    private static byte[] _randomMask22_9;
    private static byte[] _randomMask23_1;
    private static byte[] _randomMask23_10;
    private static byte[] _randomMask23_11;
    private static byte[] _randomMask23_12;
    private static byte[] _randomMask23_13;
    private static byte[] _randomMask23_14;
    private static byte[] _randomMask23_15;
    private static byte[] _randomMask23_16;
    private static byte[] _randomMask23_17;
    private static byte[] _randomMask23_18;
    private static byte[] _randomMask23_19;
    private static byte[] _randomMask23_2;
    private static byte[] _randomMask23_20;
    private static byte[] _randomMask23_21;
    private static byte[] _randomMask23_22;
    private static byte[] _randomMask23_23;
    private static byte[] _randomMask23_3;
    private static byte[] _randomMask23_4;
    private static byte[] _randomMask23_5;
    private static byte[] _randomMask23_6;
    private static byte[] _randomMask23_7;
    private static byte[] _randomMask23_8;
    private static byte[] _randomMask23_9;
    private static byte[] _randomMask24_1;
    private static byte[] _randomMask24_10;
    private static byte[] _randomMask24_11;
    private static byte[] _randomMask24_12;
    private static byte[] _randomMask24_13;
    private static byte[] _randomMask24_14;
    private static byte[] _randomMask24_15;
    private static byte[] _randomMask24_16;
    private static byte[] _randomMask24_17;
    private static byte[] _randomMask24_18;
    private static byte[] _randomMask24_19;
    private static byte[] _randomMask24_2;
    private static byte[] _randomMask24_20;
    private static byte[] _randomMask24_21;
    private static byte[] _randomMask24_22;
    private static byte[] _randomMask24_23;
    private static byte[] _randomMask24_24;
    private static byte[] _randomMask24_3;
    private static byte[] _randomMask24_4;
    private static byte[] _randomMask24_5;
    private static byte[] _randomMask24_6;
    private static byte[] _randomMask24_7;
    private static byte[] _randomMask24_8;
    private static byte[] _randomMask24_9;
    private static byte[] _randomMask25_1;
    private static byte[] _randomMask25_10;
    private static byte[] _randomMask25_11;
    private static byte[] _randomMask25_12;
    private static byte[] _randomMask25_13;
    private static byte[] _randomMask25_14;
    private static byte[] _randomMask25_15;
    private static byte[] _randomMask25_16;
    private static byte[] _randomMask25_17;
    private static byte[] _randomMask25_18;
    private static byte[] _randomMask25_19;
    private static byte[] _randomMask25_2;
    private static byte[] _randomMask25_20;
    private static byte[] _randomMask25_21;
    private static byte[] _randomMask25_22;
    private static byte[] _randomMask25_23;
    private static byte[] _randomMask25_24;
    private static byte[] _randomMask25_25;
    private static byte[] _randomMask25_3;
    private static byte[] _randomMask25_4;
    private static byte[] _randomMask25_5;
    private static byte[] _randomMask25_6;
    private static byte[] _randomMask25_7;
    private static byte[] _randomMask25_8;
    private static byte[] _randomMask25_9;
    private static byte[] _randomMask26_1;
    private static byte[] _randomMask26_10;
    private static byte[] _randomMask26_11;
    private static byte[] _randomMask26_12;
    private static byte[] _randomMask26_13;
    private static byte[] _randomMask26_14;
    private static byte[] _randomMask26_15;
    private static byte[] _randomMask26_16;
    private static byte[] _randomMask26_17;
    private static byte[] _randomMask26_18;
    private static byte[] _randomMask26_19;
    private static byte[] _randomMask26_2;
    private static byte[] _randomMask26_20;
    private static byte[] _randomMask26_21;
    private static byte[] _randomMask26_22;
    private static byte[] _randomMask26_23;
    private static byte[] _randomMask26_24;
    private static byte[] _randomMask26_25;
    private static byte[] _randomMask26_26;
    private static byte[] _randomMask26_3;
    private static byte[] _randomMask26_4;
    private static byte[] _randomMask26_5;
    private static byte[] _randomMask26_6;
    private static byte[] _randomMask26_7;
    private static byte[] _randomMask26_8;
    private static byte[] _randomMask26_9;
    private static byte[] _randomMask27_1;
    private static byte[] _randomMask27_10;
    private static byte[] _randomMask27_11;
    private static byte[] _randomMask27_12;
    private static byte[] _randomMask27_13;
    private static byte[] _randomMask27_14;
    private static byte[] _randomMask27_15;
    private static byte[] _randomMask27_16;
    private static byte[] _randomMask27_17;
    private static byte[] _randomMask27_18;
    private static byte[] _randomMask27_19;
    private static byte[] _randomMask27_2;
    private static byte[] _randomMask27_20;
    private static byte[] _randomMask27_21;
    private static byte[] _randomMask27_22;
    private static byte[] _randomMask27_23;
    private static byte[] _randomMask27_24;
    private static byte[] _randomMask27_25;
    private static byte[] _randomMask27_26;
    private static byte[] _randomMask27_27;
    private static byte[] _randomMask27_3;
    private static byte[] _randomMask27_4;
    private static byte[] _randomMask27_5;
    private static byte[] _randomMask27_6;
    private static byte[] _randomMask27_7;
    private static byte[] _randomMask27_8;
    private static byte[] _randomMask27_9;
    private static byte[] _randomMask28_1;
    private static byte[] _randomMask28_10;
    private static byte[] _randomMask28_11;
    private static byte[] _randomMask28_12;
    private static byte[] _randomMask28_13;
    private static byte[] _randomMask28_14;
    private static byte[] _randomMask28_15;
    private static byte[] _randomMask28_16;
    private static byte[] _randomMask28_17;
    private static byte[] _randomMask28_18;
    private static byte[] _randomMask28_19;
    private static byte[] _randomMask28_2;
    private static byte[] _randomMask28_20;
    private static byte[] _randomMask28_21;
    private static byte[] _randomMask28_22;
    private static byte[] _randomMask28_23;
    private static byte[] _randomMask28_24;
    private static byte[] _randomMask28_25;
    private static byte[] _randomMask28_26;
    private static byte[] _randomMask28_27;
    private static byte[] _randomMask28_28;
    private static byte[] _randomMask28_3;
    private static byte[] _randomMask28_4;
    private static byte[] _randomMask28_5;
    private static byte[] _randomMask28_6;
    private static byte[] _randomMask28_7;
    private static byte[] _randomMask28_8;
    private static byte[] _randomMask28_9;
    private static byte[] _randomMask29_1;
    private static byte[] _randomMask29_10;
    private static byte[] _randomMask29_11;
    private static byte[] _randomMask29_12;
    private static byte[] _randomMask29_13;
    private static byte[] _randomMask29_14;
    private static byte[] _randomMask29_15;
    private static byte[] _randomMask29_16;
    private static byte[] _randomMask29_17;
    private static byte[] _randomMask29_18;
    private static byte[] _randomMask29_19;
    private static byte[] _randomMask29_2;
    private static byte[] _randomMask29_20;
    private static byte[] _randomMask29_21;
    private static byte[] _randomMask29_22;
    private static byte[] _randomMask29_23;
    private static byte[] _randomMask29_24;
    private static byte[] _randomMask29_25;
    private static byte[] _randomMask29_26;
    private static byte[] _randomMask29_27;
    private static byte[] _randomMask29_28;
    private static byte[] _randomMask29_29;
    private static byte[] _randomMask29_3;
    private static byte[] _randomMask29_4;
    private static byte[] _randomMask29_5;
    private static byte[] _randomMask29_6;
    private static byte[] _randomMask29_7;
    private static byte[] _randomMask29_8;
    private static byte[] _randomMask29_9;
    private static byte[] _randomMask2_1;
    private static byte[] _randomMask2_2;
    private static byte[] _randomMask30_1;
    private static byte[] _randomMask30_10;
    private static byte[] _randomMask30_11;
    private static byte[] _randomMask30_12;
    private static byte[] _randomMask30_13;
    private static byte[] _randomMask30_14;
    private static byte[] _randomMask30_15;
    private static byte[] _randomMask30_16;
    private static byte[] _randomMask30_17;
    private static byte[] _randomMask30_18;
    private static byte[] _randomMask30_19;
    private static byte[] _randomMask30_2;
    private static byte[] _randomMask30_20;
    private static byte[] _randomMask30_21;
    private static byte[] _randomMask30_22;
    private static byte[] _randomMask30_23;
    private static byte[] _randomMask30_24;
    private static byte[] _randomMask30_25;
    private static byte[] _randomMask30_26;
    private static byte[] _randomMask30_27;
    private static byte[] _randomMask30_28;
    private static byte[] _randomMask30_29;
    private static byte[] _randomMask30_3;
    private static byte[] _randomMask30_30;
    private static byte[] _randomMask30_4;
    private static byte[] _randomMask30_5;
    private static byte[] _randomMask30_6;
    private static byte[] _randomMask30_7;
    private static byte[] _randomMask30_8;
    private static byte[] _randomMask30_9;
    private static byte[] _randomMask31_1;
    private static byte[] _randomMask31_10;
    private static byte[] _randomMask31_11;
    private static byte[] _randomMask31_12;
    private static byte[] _randomMask31_13;
    private static byte[] _randomMask31_14;
    private static byte[] _randomMask31_15;
    private static byte[] _randomMask31_16;
    private static byte[] _randomMask31_17;
    private static byte[] _randomMask31_18;
    private static byte[] _randomMask31_19;
    private static byte[] _randomMask31_2;
    private static byte[] _randomMask31_20;
    private static byte[] _randomMask31_21;
    private static byte[] _randomMask31_22;
    private static byte[] _randomMask31_23;
    private static byte[] _randomMask31_24;
    private static byte[] _randomMask31_25;
    private static byte[] _randomMask31_26;
    private static byte[] _randomMask31_27;
    private static byte[] _randomMask31_28;
    private static byte[] _randomMask31_29;
    private static byte[] _randomMask31_3;
    private static byte[] _randomMask31_30;
    private static byte[] _randomMask31_31;
    private static byte[] _randomMask31_4;
    private static byte[] _randomMask31_5;
    private static byte[] _randomMask31_6;
    private static byte[] _randomMask31_7;
    private static byte[] _randomMask31_8;
    private static byte[] _randomMask31_9;
    private static byte[] _randomMask32_1;
    private static byte[] _randomMask32_10;
    private static byte[] _randomMask32_11;
    private static byte[] _randomMask32_12;
    private static byte[] _randomMask32_13;
    private static byte[] _randomMask32_14;
    private static byte[] _randomMask32_15;
    private static byte[] _randomMask32_16;
    private static byte[] _randomMask32_17;
    private static byte[] _randomMask32_18;
    private static byte[] _randomMask32_19;
    private static byte[] _randomMask32_2;
    private static byte[] _randomMask32_20;
    private static byte[] _randomMask32_21;
    private static byte[] _randomMask32_22;
    private static byte[] _randomMask32_23;
    private static byte[] _randomMask32_24;
    private static byte[] _randomMask32_25;
    private static byte[] _randomMask32_26;
    private static byte[] _randomMask32_27;
    private static byte[] _randomMask32_28;
    private static byte[] _randomMask32_29;
    private static byte[] _randomMask32_3;
    private static byte[] _randomMask32_30;
    private static byte[] _randomMask32_31;
    private static byte[] _randomMask32_32;
    private static byte[] _randomMask32_4;
    private static byte[] _randomMask32_5;
    private static byte[] _randomMask32_6;
    private static byte[] _randomMask32_7;
    private static byte[] _randomMask32_8;
    private static byte[] _randomMask32_9;
    private static byte[] _randomMask33_1;
    private static byte[] _randomMask33_10;
    private static byte[] _randomMask33_11;
    private static byte[] _randomMask33_12;
    private static byte[] _randomMask33_13;
    private static byte[] _randomMask33_14;
    private static byte[] _randomMask33_15;
    private static byte[] _randomMask33_16;
    private static byte[] _randomMask33_17;
    private static byte[] _randomMask33_18;
    private static byte[] _randomMask33_19;
    private static byte[] _randomMask33_2;
    private static byte[] _randomMask33_20;
    private static byte[] _randomMask33_21;
    private static byte[] _randomMask33_22;
    private static byte[] _randomMask33_23;
    private static byte[] _randomMask33_24;
    private static byte[] _randomMask33_25;
    private static byte[] _randomMask33_26;
    private static byte[] _randomMask33_27;
    private static byte[] _randomMask33_28;
    private static byte[] _randomMask33_29;
    private static byte[] _randomMask33_3;
    private static byte[] _randomMask33_30;
    private static byte[] _randomMask33_31;
    private static byte[] _randomMask33_32;
    private static byte[] _randomMask33_33;
    private static byte[] _randomMask33_4;
    private static byte[] _randomMask33_5;
    private static byte[] _randomMask33_6;
    private static byte[] _randomMask33_7;
    private static byte[] _randomMask33_8;
    private static byte[] _randomMask33_9;
    private static byte[] _randomMask34_1;
    private static byte[] _randomMask34_10;
    private static byte[] _randomMask34_11;
    private static byte[] _randomMask34_12;
    private static byte[] _randomMask34_13;
    private static byte[] _randomMask34_14;
    private static byte[] _randomMask34_15;
    private static byte[] _randomMask34_16;
    private static byte[] _randomMask34_17;
    private static byte[] _randomMask34_18;
    private static byte[] _randomMask34_19;
    private static byte[] _randomMask34_2;
    private static byte[] _randomMask34_20;
    private static byte[] _randomMask34_21;
    private static byte[] _randomMask34_22;
    private static byte[] _randomMask34_23;
    private static byte[] _randomMask34_24;
    private static byte[] _randomMask34_25;
    private static byte[] _randomMask34_26;
    private static byte[] _randomMask34_27;
    private static byte[] _randomMask34_28;
    private static byte[] _randomMask34_29;
    private static byte[] _randomMask34_3;
    private static byte[] _randomMask34_30;
    private static byte[] _randomMask34_31;
    private static byte[] _randomMask34_32;
    private static byte[] _randomMask34_33;
    private static byte[] _randomMask34_34;
    private static byte[] _randomMask34_4;
    private static byte[] _randomMask34_5;
    private static byte[] _randomMask34_6;
    private static byte[] _randomMask34_7;
    private static byte[] _randomMask34_8;
    private static byte[] _randomMask34_9;
    private static byte[] _randomMask35_1;
    private static byte[] _randomMask35_10;
    private static byte[] _randomMask35_11;
    private static byte[] _randomMask35_12;
    private static byte[] _randomMask35_13;
    private static byte[] _randomMask35_14;
    private static byte[] _randomMask35_15;
    private static byte[] _randomMask35_16;
    private static byte[] _randomMask35_17;
    private static byte[] _randomMask35_18;
    private static byte[] _randomMask35_19;
    private static byte[] _randomMask35_2;
    private static byte[] _randomMask35_20;
    private static byte[] _randomMask35_21;
    private static byte[] _randomMask35_22;
    private static byte[] _randomMask35_23;
    private static byte[] _randomMask35_24;
    private static byte[] _randomMask35_25;
    private static byte[] _randomMask35_26;
    private static byte[] _randomMask35_27;
    private static byte[] _randomMask35_28;
    private static byte[] _randomMask35_29;
    private static byte[] _randomMask35_3;
    private static byte[] _randomMask35_30;
    private static byte[] _randomMask35_31;
    private static byte[] _randomMask35_32;
    private static byte[] _randomMask35_33;
    private static byte[] _randomMask35_34;
    private static byte[] _randomMask35_35;
    private static byte[] _randomMask35_4;
    private static byte[] _randomMask35_5;
    private static byte[] _randomMask35_6;
    private static byte[] _randomMask35_7;
    private static byte[] _randomMask35_8;
    private static byte[] _randomMask35_9;
    private static byte[] _randomMask36_1;
    private static byte[] _randomMask36_10;
    private static byte[] _randomMask36_11;
    private static byte[] _randomMask36_12;
    private static byte[] _randomMask36_13;
    private static byte[] _randomMask36_14;
    private static byte[] _randomMask36_15;
    private static byte[] _randomMask36_16;
    private static byte[] _randomMask36_17;
    private static byte[] _randomMask36_18;
    private static byte[] _randomMask36_19;
    private static byte[] _randomMask36_2;
    private static byte[] _randomMask36_20;
    private static byte[] _randomMask36_21;
    private static byte[] _randomMask36_22;
    private static byte[] _randomMask36_23;
    private static byte[] _randomMask36_24;
    private static byte[] _randomMask36_25;
    private static byte[] _randomMask36_26;
    private static byte[] _randomMask36_27;
    private static byte[] _randomMask36_28;
    private static byte[] _randomMask36_29;
    private static byte[] _randomMask36_3;
    private static byte[] _randomMask36_30;
    private static byte[] _randomMask36_31;
    private static byte[] _randomMask36_32;
    private static byte[] _randomMask36_33;
    private static byte[] _randomMask36_34;
    private static byte[] _randomMask36_35;
    private static byte[] _randomMask36_36;
    private static byte[] _randomMask36_4;
    private static byte[] _randomMask36_5;
    private static byte[] _randomMask36_6;
    private static byte[] _randomMask36_7;
    private static byte[] _randomMask36_8;
    private static byte[] _randomMask36_9;
    private static byte[] _randomMask37_1;
    private static byte[] _randomMask37_10;
    private static byte[] _randomMask37_11;
    private static byte[] _randomMask37_12;
    private static byte[] _randomMask37_13;
    private static byte[] _randomMask37_14;
    private static byte[] _randomMask37_15;
    private static byte[] _randomMask37_16;
    private static byte[] _randomMask37_17;
    private static byte[] _randomMask37_18;
    private static byte[] _randomMask37_19;
    private static byte[] _randomMask37_2;
    private static byte[] _randomMask37_20;
    private static byte[] _randomMask37_21;
    private static byte[] _randomMask37_22;
    private static byte[] _randomMask37_23;
    private static byte[] _randomMask37_24;
    private static byte[] _randomMask37_25;
    private static byte[] _randomMask37_26;
    private static byte[] _randomMask37_27;
    private static byte[] _randomMask37_28;
    private static byte[] _randomMask37_29;
    private static byte[] _randomMask37_3;
    private static byte[] _randomMask37_30;
    private static byte[] _randomMask37_31;
    private static byte[] _randomMask37_32;
    private static byte[] _randomMask37_33;
    private static byte[] _randomMask37_34;
    private static byte[] _randomMask37_35;
    private static byte[] _randomMask37_36;
    private static byte[] _randomMask37_37;
    private static byte[] _randomMask37_4;
    private static byte[] _randomMask37_5;
    private static byte[] _randomMask37_6;
    private static byte[] _randomMask37_7;
    private static byte[] _randomMask37_8;
    private static byte[] _randomMask37_9;
    private static byte[] _randomMask38_1;
    private static byte[] _randomMask38_10;
    private static byte[] _randomMask38_11;
    private static byte[] _randomMask38_12;
    private static byte[] _randomMask38_13;
    private static byte[] _randomMask38_14;
    private static byte[] _randomMask38_15;
    private static byte[] _randomMask38_16;
    private static byte[] _randomMask38_17;
    private static byte[] _randomMask38_18;
    private static byte[] _randomMask38_19;
    private static byte[] _randomMask38_2;
    private static byte[] _randomMask38_20;
    private static byte[] _randomMask38_21;
    private static byte[] _randomMask38_22;
    private static byte[] _randomMask38_23;
    private static byte[] _randomMask38_24;
    private static byte[] _randomMask38_25;
    private static byte[] _randomMask38_26;
    private static byte[] _randomMask38_27;
    private static byte[] _randomMask38_28;
    private static byte[] _randomMask38_29;
    private static byte[] _randomMask38_3;
    private static byte[] _randomMask38_30;
    private static byte[] _randomMask38_31;
    private static byte[] _randomMask38_32;
    private static byte[] _randomMask38_33;
    private static byte[] _randomMask38_34;
    private static byte[] _randomMask38_35;
    private static byte[] _randomMask38_36;
    private static byte[] _randomMask38_37;
    private static byte[] _randomMask38_38;
    private static byte[] _randomMask38_4;
    private static byte[] _randomMask38_5;
    private static byte[] _randomMask38_6;
    private static byte[] _randomMask38_7;
    private static byte[] _randomMask38_8;
    private static byte[] _randomMask38_9;
    private static byte[] _randomMask39_1;
    private static byte[] _randomMask39_10;
    private static byte[] _randomMask39_11;
    private static byte[] _randomMask39_12;
    private static byte[] _randomMask39_13;
    private static byte[] _randomMask39_14;
    private static byte[] _randomMask39_15;
    private static byte[] _randomMask39_16;
    private static byte[] _randomMask39_17;
    private static byte[] _randomMask39_18;
    private static byte[] _randomMask39_19;
    private static byte[] _randomMask39_2;
    private static byte[] _randomMask39_20;
    private static byte[] _randomMask39_21;
    private static byte[] _randomMask39_22;
    private static byte[] _randomMask39_23;
    private static byte[] _randomMask39_24;
    private static byte[] _randomMask39_25;
    private static byte[] _randomMask39_26;
    private static byte[] _randomMask39_27;
    private static byte[] _randomMask39_28;
    private static byte[] _randomMask39_29;
    private static byte[] _randomMask39_3;
    private static byte[] _randomMask39_30;
    private static byte[] _randomMask39_31;
    private static byte[] _randomMask39_32;
    private static byte[] _randomMask39_33;
    private static byte[] _randomMask39_34;
    private static byte[] _randomMask39_35;
    private static byte[] _randomMask39_36;
    private static byte[] _randomMask39_37;
    private static byte[] _randomMask39_38;
    private static byte[] _randomMask39_39;
    private static byte[] _randomMask39_4;
    private static byte[] _randomMask39_5;
    private static byte[] _randomMask39_6;
    private static byte[] _randomMask39_7;
    private static byte[] _randomMask39_8;
    private static byte[] _randomMask39_9;
    private static byte[] _randomMask3_1;
    private static byte[] _randomMask3_2;
    private static byte[] _randomMask3_3;
    private static byte[] _randomMask40_1;
    private static byte[] _randomMask40_10;
    private static byte[] _randomMask40_11;
    private static byte[] _randomMask40_12;
    private static byte[] _randomMask40_13;
    private static byte[] _randomMask40_14;
    private static byte[] _randomMask40_15;
    private static byte[] _randomMask40_16;
    private static byte[] _randomMask40_17;
    private static byte[] _randomMask40_18;
    private static byte[] _randomMask40_19;
    private static byte[] _randomMask40_2;
    private static byte[] _randomMask40_20;
    private static byte[] _randomMask40_21;
    private static byte[] _randomMask40_22;
    private static byte[] _randomMask40_23;
    private static byte[] _randomMask40_24;
    private static byte[] _randomMask40_25;
    private static byte[] _randomMask40_26;
    private static byte[] _randomMask40_27;
    private static byte[] _randomMask40_28;
    private static byte[] _randomMask40_29;
    private static byte[] _randomMask40_3;
    private static byte[] _randomMask40_30;
    private static byte[] _randomMask40_31;
    private static byte[] _randomMask40_32;
    private static byte[] _randomMask40_33;
    private static byte[] _randomMask40_34;
    private static byte[] _randomMask40_35;
    private static byte[] _randomMask40_36;
    private static byte[] _randomMask40_37;
    private static byte[] _randomMask40_38;
    private static byte[] _randomMask40_39;
    private static byte[] _randomMask40_4;
    private static byte[] _randomMask40_40;
    private static byte[] _randomMask40_5;
    private static byte[] _randomMask40_6;
    private static byte[] _randomMask40_7;
    private static byte[] _randomMask40_8;
    private static byte[] _randomMask40_9;
    private static byte[] _randomMask41_1;
    private static byte[] _randomMask41_10;
    private static byte[] _randomMask41_11;
    private static byte[] _randomMask41_12;
    private static byte[] _randomMask41_13;
    private static byte[] _randomMask41_14;
    private static byte[] _randomMask41_15;
    private static byte[] _randomMask41_16;
    private static byte[] _randomMask41_17;
    private static byte[] _randomMask41_18;
    private static byte[] _randomMask41_19;
    private static byte[] _randomMask41_2;
    private static byte[] _randomMask41_20;
    private static byte[] _randomMask41_21;
    private static byte[] _randomMask41_22;
    private static byte[] _randomMask41_23;
    private static byte[] _randomMask41_24;
    private static byte[] _randomMask41_25;
    private static byte[] _randomMask41_26;
    private static byte[] _randomMask41_27;
    private static byte[] _randomMask41_28;
    private static byte[] _randomMask41_29;
    private static byte[] _randomMask41_3;
    private static byte[] _randomMask41_30;
    private static byte[] _randomMask41_31;
    private static byte[] _randomMask41_32;
    private static byte[] _randomMask41_33;
    private static byte[] _randomMask41_34;
    private static byte[] _randomMask41_35;
    private static byte[] _randomMask41_36;
    private static byte[] _randomMask41_37;
    private static byte[] _randomMask41_38;
    private static byte[] _randomMask41_39;
    private static byte[] _randomMask41_4;
    private static byte[] _randomMask41_40;
    private static byte[] _randomMask41_41;
    private static byte[] _randomMask41_5;
    private static byte[] _randomMask41_6;
    private static byte[] _randomMask41_7;
    private static byte[] _randomMask41_8;
    private static byte[] _randomMask41_9;
    private static byte[] _randomMask42_1;
    private static byte[] _randomMask42_10;
    private static byte[] _randomMask42_11;
    private static byte[] _randomMask42_12;
    private static byte[] _randomMask42_13;
    private static byte[] _randomMask42_14;
    private static byte[] _randomMask42_15;
    private static byte[] _randomMask42_16;
    private static byte[] _randomMask42_17;
    private static byte[] _randomMask42_18;
    private static byte[] _randomMask42_19;
    private static byte[] _randomMask42_2;
    private static byte[] _randomMask42_20;
    private static byte[] _randomMask42_21;
    private static byte[] _randomMask42_22;
    private static byte[] _randomMask42_23;
    private static byte[] _randomMask42_24;
    private static byte[] _randomMask42_25;
    private static byte[] _randomMask42_26;
    private static byte[] _randomMask42_27;
    private static byte[] _randomMask42_28;
    private static byte[] _randomMask42_29;
    private static byte[] _randomMask42_3;
    private static byte[] _randomMask42_30;
    private static byte[] _randomMask42_31;
    private static byte[] _randomMask42_32;
    private static byte[] _randomMask42_33;
    private static byte[] _randomMask42_34;
    private static byte[] _randomMask42_35;
    private static byte[] _randomMask42_36;
    private static byte[] _randomMask42_37;
    private static byte[] _randomMask42_38;
    private static byte[] _randomMask42_39;
    private static byte[] _randomMask42_4;
    private static byte[] _randomMask42_40;
    private static byte[] _randomMask42_41;
    private static byte[] _randomMask42_42;
    private static byte[] _randomMask42_5;
    private static byte[] _randomMask42_6;
    private static byte[] _randomMask42_7;
    private static byte[] _randomMask42_8;
    private static byte[] _randomMask42_9;
    private static byte[] _randomMask43_1;
    private static byte[] _randomMask43_10;
    private static byte[] _randomMask43_11;
    private static byte[] _randomMask43_12;
    private static byte[] _randomMask43_13;
    private static byte[] _randomMask43_14;
    private static byte[] _randomMask43_15;
    private static byte[] _randomMask43_16;
    private static byte[] _randomMask43_17;
    private static byte[] _randomMask43_18;
    private static byte[] _randomMask43_19;
    private static byte[] _randomMask43_2;
    private static byte[] _randomMask43_20;
    private static byte[] _randomMask43_21;
    private static byte[] _randomMask43_22;
    private static byte[] _randomMask43_23;
    private static byte[] _randomMask43_24;
    private static byte[] _randomMask43_25;
    private static byte[] _randomMask43_26;
    private static byte[] _randomMask43_27;
    private static byte[] _randomMask43_28;
    private static byte[] _randomMask43_29;
    private static byte[] _randomMask43_3;
    private static byte[] _randomMask43_30;
    private static byte[] _randomMask43_31;
    private static byte[] _randomMask43_32;
    private static byte[] _randomMask43_33;
    private static byte[] _randomMask43_34;
    private static byte[] _randomMask43_35;
    private static byte[] _randomMask43_36;
    private static byte[] _randomMask43_37;
    private static byte[] _randomMask43_38;
    private static byte[] _randomMask43_39;
    private static byte[] _randomMask43_4;
    private static byte[] _randomMask43_40;
    private static byte[] _randomMask43_41;
    private static byte[] _randomMask43_42;
    private static byte[] _randomMask43_43;
    private static byte[] _randomMask43_5;
    private static byte[] _randomMask43_6;
    private static byte[] _randomMask43_7;
    private static byte[] _randomMask43_8;
    private static byte[] _randomMask43_9;
    private static byte[] _randomMask44_1;
    private static byte[] _randomMask44_10;
    private static byte[] _randomMask44_11;
    private static byte[] _randomMask44_12;
    private static byte[] _randomMask44_13;
    private static byte[] _randomMask44_14;
    private static byte[] _randomMask44_15;
    private static byte[] _randomMask44_16;
    private static byte[] _randomMask44_17;
    private static byte[] _randomMask44_18;
    private static byte[] _randomMask44_19;
    private static byte[] _randomMask44_2;
    private static byte[] _randomMask44_20;
    private static byte[] _randomMask44_21;
    private static byte[] _randomMask44_22;
    private static byte[] _randomMask44_23;
    private static byte[] _randomMask44_24;
    private static byte[] _randomMask44_25;
    private static byte[] _randomMask44_26;
    private static byte[] _randomMask44_27;
    private static byte[] _randomMask44_28;
    private static byte[] _randomMask44_29;
    private static byte[] _randomMask44_3;
    private static byte[] _randomMask44_30;
    private static byte[] _randomMask44_31;
    private static byte[] _randomMask44_32;
    private static byte[] _randomMask44_33;
    private static byte[] _randomMask44_34;
    private static byte[] _randomMask44_35;
    private static byte[] _randomMask44_36;
    private static byte[] _randomMask44_37;
    private static byte[] _randomMask44_38;
    private static byte[] _randomMask44_39;
    private static byte[] _randomMask44_4;
    private static byte[] _randomMask44_40;
    private static byte[] _randomMask44_41;
    private static byte[] _randomMask44_42;
    private static byte[] _randomMask44_43;
    private static byte[] _randomMask44_44;
    private static byte[] _randomMask44_5;
    private static byte[] _randomMask44_6;
    private static byte[] _randomMask44_7;
    private static byte[] _randomMask44_8;
    private static byte[] _randomMask44_9;
    private static byte[] _randomMask45_1;
    private static byte[] _randomMask45_10;
    private static byte[] _randomMask45_11;
    private static byte[] _randomMask45_12;
    private static byte[] _randomMask45_13;
    private static byte[] _randomMask45_14;
    private static byte[] _randomMask45_15;
    private static byte[] _randomMask45_16;
    private static byte[] _randomMask45_17;
    private static byte[] _randomMask45_18;
    private static byte[] _randomMask45_19;
    private static byte[] _randomMask45_2;
    private static byte[] _randomMask45_20;
    private static byte[] _randomMask45_21;
    private static byte[] _randomMask45_22;
    private static byte[] _randomMask45_23;
    private static byte[] _randomMask45_24;
    private static byte[] _randomMask45_25;
    private static byte[] _randomMask45_26;
    private static byte[] _randomMask45_27;
    private static byte[] _randomMask45_28;
    private static byte[] _randomMask45_29;
    private static byte[] _randomMask45_3;
    private static byte[] _randomMask45_30;
    private static byte[] _randomMask45_31;
    private static byte[] _randomMask45_32;
    private static byte[] _randomMask45_33;
    private static byte[] _randomMask45_34;
    private static byte[] _randomMask45_35;
    private static byte[] _randomMask45_36;
    private static byte[] _randomMask45_37;
    private static byte[] _randomMask45_38;
    private static byte[] _randomMask45_39;
    private static byte[] _randomMask45_4;
    private static byte[] _randomMask45_40;
    private static byte[] _randomMask45_41;
    private static byte[] _randomMask45_42;
    private static byte[] _randomMask45_43;
    private static byte[] _randomMask45_44;
    private static byte[] _randomMask45_45;
    private static byte[] _randomMask45_5;
    private static byte[] _randomMask45_6;
    private static byte[] _randomMask45_7;
    private static byte[] _randomMask45_8;
    private static byte[] _randomMask45_9;
    private static byte[] _randomMask46_1;
    private static byte[] _randomMask46_10;
    private static byte[] _randomMask46_11;
    private static byte[] _randomMask46_12;
    private static byte[] _randomMask46_13;
    private static byte[] _randomMask46_14;
    private static byte[] _randomMask46_15;
    private static byte[] _randomMask46_16;
    private static byte[] _randomMask46_17;
    private static byte[] _randomMask46_18;
    private static byte[] _randomMask46_19;
    private static byte[] _randomMask46_2;
    private static byte[] _randomMask46_20;
    private static byte[] _randomMask46_21;
    private static byte[] _randomMask46_22;
    private static byte[] _randomMask46_23;
    private static byte[] _randomMask46_24;
    private static byte[] _randomMask46_25;
    private static byte[] _randomMask46_26;
    private static byte[] _randomMask46_27;
    private static byte[] _randomMask46_28;
    private static byte[] _randomMask46_29;
    private static byte[] _randomMask46_3;
    private static byte[] _randomMask46_30;
    private static byte[] _randomMask46_31;
    private static byte[] _randomMask46_32;
    private static byte[] _randomMask46_33;
    private static byte[] _randomMask46_34;
    private static byte[] _randomMask46_35;
    private static byte[] _randomMask46_36;
    private static byte[] _randomMask46_37;
    private static byte[] _randomMask46_38;
    private static byte[] _randomMask46_39;
    private static byte[] _randomMask46_4;
    private static byte[] _randomMask46_40;
    private static byte[] _randomMask46_41;
    private static byte[] _randomMask46_42;
    private static byte[] _randomMask46_43;
    private static byte[] _randomMask46_44;
    private static byte[] _randomMask46_45;
    private static byte[] _randomMask46_46;
    private static byte[] _randomMask46_5;
    private static byte[] _randomMask46_6;
    private static byte[] _randomMask46_7;
    private static byte[] _randomMask46_8;
    private static byte[] _randomMask46_9;
    private static byte[] _randomMask47_1;
    private static byte[] _randomMask47_10;
    private static byte[] _randomMask47_11;
    private static byte[] _randomMask47_12;
    private static byte[] _randomMask47_13;
    private static byte[] _randomMask47_14;
    private static byte[] _randomMask47_15;
    private static byte[] _randomMask47_16;
    private static byte[] _randomMask47_17;
    private static byte[] _randomMask47_18;
    private static byte[] _randomMask47_19;
    private static byte[] _randomMask47_2;
    private static byte[] _randomMask47_20;
    private static byte[] _randomMask47_21;
    private static byte[] _randomMask47_22;
    private static byte[] _randomMask47_23;
    private static byte[] _randomMask47_24;
    private static byte[] _randomMask47_25;
    private static byte[] _randomMask47_26;
    private static byte[] _randomMask47_27;
    private static byte[] _randomMask47_28;
    private static byte[] _randomMask47_29;
    private static byte[] _randomMask47_3;
    private static byte[] _randomMask47_30;
    private static byte[] _randomMask47_31;
    private static byte[] _randomMask47_32;
    private static byte[] _randomMask47_33;
    private static byte[] _randomMask47_34;
    private static byte[] _randomMask47_35;
    private static byte[] _randomMask47_36;
    private static byte[] _randomMask47_37;
    private static byte[] _randomMask47_38;
    private static byte[] _randomMask47_39;
    private static byte[] _randomMask47_4;
    private static byte[] _randomMask47_40;
    private static byte[] _randomMask47_41;
    private static byte[] _randomMask47_42;
    private static byte[] _randomMask47_43;
    private static byte[] _randomMask47_44;
    private static byte[] _randomMask47_45;
    private static byte[] _randomMask47_46;
    private static byte[] _randomMask47_47;
    private static byte[] _randomMask47_5;
    private static byte[] _randomMask47_6;
    private static byte[] _randomMask47_7;
    private static byte[] _randomMask47_8;
    private static byte[] _randomMask47_9;
    private static byte[] _randomMask48_1;
    private static byte[] _randomMask48_10;
    private static byte[] _randomMask48_11;
    private static byte[] _randomMask48_12;
    private static byte[] _randomMask48_13;
    private static byte[] _randomMask48_14;
    private static byte[] _randomMask48_15;
    private static byte[] _randomMask48_16;
    private static byte[] _randomMask48_17;
    private static byte[] _randomMask48_18;
    private static byte[] _randomMask48_19;
    private static byte[] _randomMask48_2;
    private static byte[] _randomMask48_20;
    private static byte[] _randomMask48_21;
    private static byte[] _randomMask48_22;
    private static byte[] _randomMask48_23;
    private static byte[] _randomMask48_24;
    private static byte[] _randomMask48_25;
    private static byte[] _randomMask48_26;
    private static byte[] _randomMask48_27;
    private static byte[] _randomMask48_28;
    private static byte[] _randomMask48_29;
    private static byte[] _randomMask48_3;
    private static byte[] _randomMask48_30;
    private static byte[] _randomMask48_31;
    private static byte[] _randomMask48_32;
    private static byte[] _randomMask48_33;
    private static byte[] _randomMask48_34;
    private static byte[] _randomMask48_35;
    private static byte[] _randomMask48_36;
    private static byte[] _randomMask48_37;
    private static byte[] _randomMask48_38;
    private static byte[] _randomMask48_39;
    private static byte[] _randomMask48_4;
    private static byte[] _randomMask48_40;
    private static byte[] _randomMask48_41;
    private static byte[] _randomMask48_42;
    private static byte[] _randomMask48_43;
    private static byte[] _randomMask48_44;
    private static byte[] _randomMask48_45;
    private static byte[] _randomMask48_46;
    private static byte[] _randomMask48_47;
    private static byte[] _randomMask48_48;
    private static byte[] _randomMask48_5;
    private static byte[] _randomMask48_6;
    private static byte[] _randomMask48_7;
    private static byte[] _randomMask48_8;
    private static byte[] _randomMask48_9;
    private static byte[] _randomMask4_1;
    private static byte[] _randomMask4_2;
    private static byte[] _randomMask4_3;
    private static byte[] _randomMask4_4;
    private static byte[] _randomMask5_1;
    private static byte[] _randomMask5_2;
    private static byte[] _randomMask5_3;
    private static byte[] _randomMask5_4;
    private static byte[] _randomMask5_5;
    private static byte[] _randomMask6_1;
    private static byte[] _randomMask6_2;
    private static byte[] _randomMask6_3;
    private static byte[] _randomMask6_4;
    private static byte[] _randomMask6_5;
    private static byte[] _randomMask6_6;
    private static byte[] _randomMask7_1;
    private static byte[] _randomMask7_2;
    private static byte[] _randomMask7_3;
    private static byte[] _randomMask7_4;
    private static byte[] _randomMask7_5;
    private static byte[] _randomMask7_6;
    private static byte[] _randomMask7_7;
    private static byte[] _randomMask8_1;
    private static byte[] _randomMask8_2;
    private static byte[] _randomMask8_3;
    private static byte[] _randomMask8_4;
    private static byte[] _randomMask8_5;
    private static byte[] _randomMask8_6;
    private static byte[] _randomMask8_7;
    private static byte[] _randomMask8_8;
    private static byte[] _randomMask9_1;
    private static byte[] _randomMask9_2;
    private static byte[] _randomMask9_3;
    private static byte[] _randomMask9_4;
    private static byte[] _randomMask9_5;
    private static byte[] _randomMask9_6;
    private static byte[] _randomMask9_7;
    private static byte[] _randomMask9_8;
    private static byte[] _randomMask9_9;
    private static byte[][] _randomPacketMask1;
    private static byte[][] _randomPacketMask10;
    private static byte[][] _randomPacketMask11;
    private static byte[][] _randomPacketMask12;
    private static byte[][] _randomPacketMask13;
    private static byte[][] _randomPacketMask14;
    private static byte[][] _randomPacketMask15;
    private static byte[][] _randomPacketMask16;
    private static byte[][] _randomPacketMask17;
    private static byte[][] _randomPacketMask18;
    private static byte[][] _randomPacketMask19;
    private static byte[][] _randomPacketMask2;
    private static byte[][] _randomPacketMask20;
    private static byte[][] _randomPacketMask21;
    private static byte[][] _randomPacketMask22;
    private static byte[][] _randomPacketMask23;
    private static byte[][] _randomPacketMask24;
    private static byte[][] _randomPacketMask25;
    private static byte[][] _randomPacketMask26;
    private static byte[][] _randomPacketMask27;
    private static byte[][] _randomPacketMask28;
    private static byte[][] _randomPacketMask29;
    private static byte[][] _randomPacketMask3;
    private static byte[][] _randomPacketMask30;
    private static byte[][] _randomPacketMask31;
    private static byte[][] _randomPacketMask32;
    private static byte[][] _randomPacketMask33;
    private static byte[][] _randomPacketMask34;
    private static byte[][] _randomPacketMask35;
    private static byte[][] _randomPacketMask36;
    private static byte[][] _randomPacketMask37;
    private static byte[][] _randomPacketMask38;
    private static byte[][] _randomPacketMask39;
    private static byte[][] _randomPacketMask4;
    private static byte[][] _randomPacketMask40;
    private static byte[][] _randomPacketMask41;
    private static byte[][] _randomPacketMask42;
    private static byte[][] _randomPacketMask43;
    private static byte[][] _randomPacketMask44;
    private static byte[][] _randomPacketMask45;
    private static byte[][] _randomPacketMask46;
    private static byte[][] _randomPacketMask47;
    private static byte[][] _randomPacketMask48;
    private static byte[][] _randomPacketMask5;
    private static byte[][] _randomPacketMask6;
    private static byte[][] _randomPacketMask7;
    private static byte[][] _randomPacketMask8;
    private static byte[][] _randomPacketMask9;
    private static byte[][][] _randomPacketMaskTable;
    private FecMaskType _maskType;
    private byte[][][] _table;

    private static void createBurstyMask1_1() {
        byte[] bArr = new byte[2];
        bArr[0] = ByteCompanionObject.MIN_VALUE;
        _burstyMask1_1 = bArr;
    }

    private static void createBurstyMask10_1() {
        _burstyMask10_1 = new byte[]{-1, -64};
    }

    private static void createBurstyMask10_10() {
        _burstyMask10_10 = new byte[]{ByteCompanionObject.MIN_VALUE, 0, -64, 0, 96, 0, 48, 0, 24, 0, 12, 0, 6, 0, 3, 0, 1, ByteCompanionObject.MIN_VALUE, 0, -64};
    }

    private static void createBurstyMask10_2() {
        _burstyMask10_2 = new byte[]{-86, ByteCompanionObject.MIN_VALUE, -43, 64};
    }

    private static void createBurstyMask10_3() {
        _burstyMask10_3 = new byte[]{-55, 0, 116, ByteCompanionObject.MIN_VALUE, -110, 64};
    }

    private static void createBurstyMask10_4() {
        _burstyMask10_4 = new byte[]{98, 0, 57, 0, -118, ByteCompanionObject.MIN_VALUE, -59, 64};
    }

    private static void createBurstyMask10_5() {
        _burstyMask10_5 = new byte[]{-123, 0, -62, ByteCompanionObject.MIN_VALUE, 97, 64, 48, ByteCompanionObject.MIN_VALUE, 24, 64};
    }

    private static void createBurstyMask10_6() {
        _burstyMask10_6 = new byte[]{24, 0, 14, 0, -126, ByteCompanionObject.MIN_VALUE, -63, 64, 96, ByteCompanionObject.MIN_VALUE, 48, 64};
    }

    private static void createBurstyMask10_7() {
        _burstyMask10_7 = new byte[]{48, 0, 24, 0, 12, 0, 7, 0, -127, 64, -64, ByteCompanionObject.MIN_VALUE, 96, 64};
    }

    private static void createBurstyMask10_8() {
        _burstyMask10_8 = new byte[]{96, 0, 48, 0, 24, 0, 12, 0, 6, 0, 3, 0, ByteCompanionObject.MIN_VALUE, ByteCompanionObject.MIN_VALUE, -64, 64};
    }

    private static void createBurstyMask10_9() {
        _burstyMask10_9 = new byte[]{-64, 0, 96, 0, 48, 0, 24, 0, 12, 0, 6, 0, 3, 0, 1, ByteCompanionObject.MIN_VALUE, ByteCompanionObject.MIN_VALUE, 64};
    }

    private static void createBurstyMask11_1() {
        _burstyMask11_1 = new byte[]{-1, -32};
    }

    private static void createBurstyMask11_10() {
        _burstyMask11_10 = new byte[]{-64, 0, 96, 0, 48, 0, 24, 0, 12, 0, 6, 0, 3, 0, 1, ByteCompanionObject.MIN_VALUE, 0, -64, ByteCompanionObject.MIN_VALUE, 32};
    }

    private static void createBurstyMask11_11() {
        _burstyMask11_11 = new byte[]{ByteCompanionObject.MIN_VALUE, 0, -64, 0, 96, 0, 48, 0, 24, 0, 12, 0, 6, 0, 3, 0, 1, ByteCompanionObject.MIN_VALUE, 0, -64, 0, 96};
    }

    private static void createBurstyMask11_2() {
        _burstyMask11_2 = new byte[]{-43, 64, -86, -96};
    }

    private static void createBurstyMask11_3() {
        _burstyMask11_3 = new byte[]{116, ByteCompanionObject.MIN_VALUE, -110, 64, -55, 32};
    }

    private static void createBurstyMask11_4() {
        _burstyMask11_4 = new byte[]{57, 0, -118, ByteCompanionObject.MIN_VALUE, -59, 64, 98, 32};
    }

    private static void createBurstyMask11_5() {
        _burstyMask11_5 = new byte[]{-62, -64, 97, 0, 48, -96, 28, 64, -123, 32};
    }

    private static void createBurstyMask11_6() {
        _burstyMask11_6 = new byte[]{14, 0, -126, ByteCompanionObject.MIN_VALUE, -63, 64, 96, -96, 48, 64, 24, 32};
    }

    private static void createBurstyMask11_7() {
        _burstyMask11_7 = new byte[]{24, 0, 12, 0, 7, 0, -127, 64, -64, -96, 96, 64, 48, 32};
    }

    private static void createBurstyMask11_8() {
        _burstyMask11_8 = new byte[]{48, 0, 24, 0, 12, 0, 6, 0, 3, 64, ByteCompanionObject.MIN_VALUE, -96, -64, 64, 96, 32};
    }

    private static void createBurstyMask11_9() {
        _burstyMask11_9 = new byte[]{96, 0, 48, 0, 24, 0, 12, 0, 6, 0, 3, 0, 1, ByteCompanionObject.MIN_VALUE, ByteCompanionObject.MIN_VALUE, 64, -64, 32};
    }

    private static void createBurstyMask12_1() {
        _burstyMask12_1 = new byte[]{-1, -16};
    }

    private static void createBurstyMask12_10() {
        _burstyMask12_10 = new byte[]{96, 0, 48, 0, 24, 0, 12, 0, 6, 0, 3, 0, 1, ByteCompanionObject.MIN_VALUE, 0, -64, ByteCompanionObject.MIN_VALUE, 32, -64, Tnaf.POW_2_WIDTH};
    }

    private static void createBurstyMask12_11() {
        _burstyMask12_11 = new byte[]{-64, 0, 96, 0, 48, 0, 24, 0, 12, 0, 6, 0, 3, 0, 1, ByteCompanionObject.MIN_VALUE, 0, -64, 0, 96, ByteCompanionObject.MIN_VALUE, Tnaf.POW_2_WIDTH};
    }

    private static void createBurstyMask12_12() {
        _burstyMask12_12 = new byte[]{ByteCompanionObject.MIN_VALUE, 0, -64, 0, 96, 0, 48, 0, 24, 0, 12, 0, 6, 0, 3, 0, 1, ByteCompanionObject.MIN_VALUE, 0, -64, 0, 96, 0, 48};
    }

    private static void createBurstyMask12_2() {
        _burstyMask12_2 = new byte[]{-86, -96, -43, 80};
    }

    private static void createBurstyMask12_3() {
        _burstyMask12_3 = new byte[]{-110, 64, -55, 32, 116, -112};
    }

    private static void createBurstyMask12_4() {
        _burstyMask12_4 = new byte[]{-118, ByteCompanionObject.MIN_VALUE, -59, 64, 98, 32, 57, Tnaf.POW_2_WIDTH};
    }

    private static void createBurstyMask12_5() {
        _burstyMask12_5 = new byte[]{97, 0, 48, -96, 28, 80, -123, 32, -62, -112};
    }

    private static void createBurstyMask12_6() {
        _burstyMask12_6 = new byte[]{-126, -112, -63, 64, 96, -96, 48, 80, 24, 32, 12, Tnaf.POW_2_WIDTH};
    }

    private static void createBurstyMask12_7() {
        _burstyMask12_7 = new byte[]{12, 0, 7, 0, -127, 64, -64, -96, 96, 80, 48, 32, 24, Tnaf.POW_2_WIDTH};
    }

    private static void createBurstyMask12_8() {
        _burstyMask12_8 = new byte[]{24, 0, 12, 0, 6, 0, 3, 0, ByteCompanionObject.MIN_VALUE, -96, -64, 80, 96, 32, 48, Tnaf.POW_2_WIDTH};
    }

    private static void createBurstyMask12_9() {
        _burstyMask12_9 = new byte[]{48, 0, 24, 0, 12, 0, 6, 0, 3, 0, 1, ByteCompanionObject.MIN_VALUE, ByteCompanionObject.MIN_VALUE, 80, -64, 32, 96, Tnaf.POW_2_WIDTH};
    }

    private static void createBurstyMask2_1() {
        byte[] bArr = new byte[2];
        bArr[0] = -64;
        _burstyMask2_1 = bArr;
    }

    private static void createBurstyMask2_2() {
        byte[] bArr = new byte[4];
        bArr[0] = ByteCompanionObject.MIN_VALUE;
        bArr[2] = -64;
        _burstyMask2_2 = bArr;
    }

    private static void createBurstyMask3_1() {
        byte[] bArr = new byte[2];
        bArr[0] = -32;
        _burstyMask3_1 = bArr;
    }

    private static void createBurstyMask3_2() {
        byte[] bArr = new byte[4];
        bArr[0] = -64;
        bArr[2] = -96;
        _burstyMask3_2 = bArr;
    }

    private static void createBurstyMask3_3() {
        _burstyMask3_3 = new byte[]{ByteCompanionObject.MIN_VALUE, 0, -64, 0, 96, 0};
    }

    private static void createBurstyMask4_1() {
        byte[] bArr = new byte[2];
        bArr[0] = -16;
        _burstyMask4_1 = bArr;
    }

    private static void createBurstyMask4_2() {
        byte[] bArr = new byte[4];
        bArr[0] = -96;
        bArr[2] = -48;
        _burstyMask4_2 = bArr;
    }

    private static void createBurstyMask4_3() {
        _burstyMask4_3 = new byte[]{-64, 0, 96, 0, -112, 0};
    }

    private static void createBurstyMask4_4() {
        _burstyMask4_4 = new byte[]{ByteCompanionObject.MIN_VALUE, 0, -64, 0, 96, 0, 48, 0};
    }

    private static void createBurstyMask5_1() {
        byte[] bArr = new byte[2];
        bArr[0] = -8;
        _burstyMask5_1 = bArr;
    }

    private static void createBurstyMask5_2() {
        byte[] bArr = new byte[4];
        bArr[0] = -48;
        bArr[2] = -88;
        _burstyMask5_2 = bArr;
    }

    private static void createBurstyMask5_3() {
        _burstyMask5_3 = new byte[]{112, 0, -112, 0, -56, 0};
    }

    private static void createBurstyMask5_4() {
        _burstyMask5_4 = new byte[]{-64, 0, 96, 0, 48, 0, -120, 0};
    }

    private static void createBurstyMask5_5() {
        _burstyMask5_5 = new byte[]{ByteCompanionObject.MIN_VALUE, 0, -64, 0, 96, 0, 48, 0, 24, 0};
    }

    private static void createBurstyMask6_1() {
        byte[] bArr = new byte[2];
        bArr[0] = -4;
        _burstyMask6_1 = bArr;
    }

    private static void createBurstyMask6_2() {
        byte[] bArr = new byte[4];
        bArr[0] = -88;
        bArr[2] = -44;
        _burstyMask6_2 = bArr;
    }

    private static void createBurstyMask6_3() {
        _burstyMask6_3 = new byte[]{-108, 0, -56, 0, 100, 0};
    }

    private static void createBurstyMask6_4() {
        _burstyMask6_4 = new byte[]{96, 0, 56, 0, -120, 0, -60, 0};
    }

    private static void createBurstyMask6_5() {
        _burstyMask6_5 = new byte[]{-64, 0, 96, 0, 48, 0, 24, 0, -124, 0};
    }

    private static void createBurstyMask6_6() {
        _burstyMask6_6 = new byte[]{ByteCompanionObject.MIN_VALUE, 0, -64, 0, 96, 0, 48, 0, 24, 0, 12, 0};
    }

    private static void createBurstyMask7_1() {
        byte[] bArr = new byte[2];
        bArr[0] = -2;
        _burstyMask7_1 = bArr;
    }

    private static void createBurstyMask7_2() {
        byte[] bArr = new byte[4];
        bArr[0] = -44;
        bArr[2] = -86;
        _burstyMask7_2 = bArr;
    }

    private static void createBurstyMask7_3() {
        _burstyMask7_3 = new byte[]{-56, 0, 116, 0, -110, 0};
    }

    private static void createBurstyMask7_4() {
        _burstyMask7_4 = new byte[]{56, 0, -118, 0, -60, 0, 98, 0};
    }

    private static void createBurstyMask7_5() {
        _burstyMask7_5 = new byte[]{96, 0, 48, 0, 28, 0, -124, 0, -62, 0};
    }

    private static void createBurstyMask7_6() {
        _burstyMask7_6 = new byte[]{-64, 0, 96, 0, 48, 0, 24, 0, 12, 0, -126, 0};
    }

    private static void createBurstyMask7_7() {
        _burstyMask7_7 = new byte[]{ByteCompanionObject.MIN_VALUE, 0, -64, 0, 96, 0, 48, 0, 24, 0, 12, 0, 6, 0};
    }

    private static void createBurstyMask8_1() {
        byte[] bArr = new byte[2];
        bArr[0] = -1;
        _burstyMask8_1 = bArr;
    }

    private static void createBurstyMask8_2() {
        byte[] bArr = new byte[4];
        bArr[0] = -86;
        bArr[2] = -43;
        _burstyMask8_2 = bArr;
    }

    private static void createBurstyMask8_3() {
        _burstyMask8_3 = new byte[]{116, 0, -110, 0, -55, 0};
    }

    private static void createBurstyMask8_4() {
        _burstyMask8_4 = new byte[]{-118, 0, -59, 0, 98, 0, 49, 0};
    }

    private static void createBurstyMask8_5() {
        _burstyMask8_5 = new byte[]{48, 0, 28, 0, -123, 0, -62, 0, 97, 0};
    }

    private static void createBurstyMask8_6() {
        _burstyMask8_6 = new byte[]{96, 0, 48, 0, 24, 0, 14, 0, -126, 0, -63, 0};
    }

    private static void createBurstyMask8_7() {
        _burstyMask8_7 = new byte[]{-64, 0, 96, 0, 48, 0, 24, 0, 12, 0, 6, 0, -127, 0};
    }

    private static void createBurstyMask8_8() {
        _burstyMask8_8 = new byte[]{ByteCompanionObject.MIN_VALUE, 0, -64, 0, 96, 0, 48, 0, 24, 0, 12, 0, 6, 0, 3, 0};
    }

    private static void createBurstyMask9_1() {
        _burstyMask9_1 = new byte[]{-1, ByteCompanionObject.MIN_VALUE};
    }

    private static void createBurstyMask9_2() {
        _burstyMask9_2 = new byte[]{-43, 0, -86, ByteCompanionObject.MIN_VALUE};
    }

    private static void createBurstyMask9_3() {
        _burstyMask9_3 = new byte[]{-110, 0, -55, 0, 116, ByteCompanionObject.MIN_VALUE};
    }

    private static void createBurstyMask9_4() {
        _burstyMask9_4 = new byte[]{-59, 0, 98, 0, 57, 0, -118, ByteCompanionObject.MIN_VALUE};
    }

    private static void createBurstyMask9_5() {
        _burstyMask9_5 = new byte[]{28, 0, -123, 0, -62, ByteCompanionObject.MIN_VALUE, 97, 0, 48, ByteCompanionObject.MIN_VALUE};
    }

    private static void createBurstyMask9_6() {
        _burstyMask9_6 = new byte[]{48, 0, 24, 0, 14, 0, -126, ByteCompanionObject.MIN_VALUE, -63, 0, 96, ByteCompanionObject.MIN_VALUE};
    }

    private static void createBurstyMask9_7() {
        _burstyMask9_7 = new byte[]{96, 0, 48, 0, 24, 0, 12, 0, 7, 0, -127, 0, -64, ByteCompanionObject.MIN_VALUE};
    }

    private static void createBurstyMask9_8() {
        _burstyMask9_8 = new byte[]{-64, 0, 96, 0, 48, 0, 24, 0, 12, 0, 6, 0, 3, 0, ByteCompanionObject.MIN_VALUE, ByteCompanionObject.MIN_VALUE};
    }

    private static void createBurstyMask9_9() {
        _burstyMask9_9 = new byte[]{ByteCompanionObject.MIN_VALUE, 0, -64, 0, 96, 0, 48, 0, 24, 0, 12, 0, 6, 0, 3, 0, 1, ByteCompanionObject.MIN_VALUE};
    }

    private static void createBurstyPacketMask1() {
        _burstyPacketMask1 = new byte[][]{_burstyMask1_1};
    }

    private static void createBurstyPacketMask10() {
        _burstyPacketMask10 = new byte[][]{_burstyMask10_1, _burstyMask10_2, _burstyMask10_3, _burstyMask10_4, _burstyMask10_5, _burstyMask10_6, _burstyMask10_7, _burstyMask10_8, _burstyMask10_9, _burstyMask10_10};
    }

    private static void createBurstyPacketMask11() {
        _burstyPacketMask11 = new byte[][]{_burstyMask11_1, _burstyMask11_2, _burstyMask11_3, _burstyMask11_4, _burstyMask11_5, _burstyMask11_6, _burstyMask11_7, _burstyMask11_8, _burstyMask11_9, _burstyMask11_10, _burstyMask11_11};
    }

    private static void createBurstyPacketMask12() {
        _burstyPacketMask12 = new byte[][]{_burstyMask12_1, _burstyMask12_2, _burstyMask12_3, _burstyMask12_4, _burstyMask12_5, _burstyMask12_6, _burstyMask12_7, _burstyMask12_8, _burstyMask12_9, _burstyMask12_10, _burstyMask12_11, _burstyMask12_12};
    }

    private static void createBurstyPacketMask2() {
        _burstyPacketMask2 = new byte[][]{_burstyMask2_1, _burstyMask2_2};
    }

    private static void createBurstyPacketMask3() {
        _burstyPacketMask3 = new byte[][]{_burstyMask3_1, _burstyMask3_2, _burstyMask3_3};
    }

    private static void createBurstyPacketMask4() {
        _burstyPacketMask4 = new byte[][]{_burstyMask4_1, _burstyMask4_2, _burstyMask4_3, _burstyMask4_4};
    }

    private static void createBurstyPacketMask5() {
        _burstyPacketMask5 = new byte[][]{_burstyMask5_1, _burstyMask5_2, _burstyMask5_3, _burstyMask5_4, _burstyMask5_5};
    }

    private static void createBurstyPacketMask6() {
        _burstyPacketMask6 = new byte[][]{_burstyMask6_1, _burstyMask6_2, _burstyMask6_3, _burstyMask6_4, _burstyMask6_5, _burstyMask6_6};
    }

    private static void createBurstyPacketMask7() {
        _burstyPacketMask7 = new byte[][]{_burstyMask7_1, _burstyMask7_2, _burstyMask7_3, _burstyMask7_4, _burstyMask7_5, _burstyMask7_6, _burstyMask7_7};
    }

    private static void createBurstyPacketMask8() {
        _burstyPacketMask8 = new byte[][]{_burstyMask8_1, _burstyMask8_2, _burstyMask8_3, _burstyMask8_4, _burstyMask8_5, _burstyMask8_6, _burstyMask8_7, _burstyMask8_8};
    }

    private static void createBurstyPacketMask9() {
        _burstyPacketMask9 = new byte[][]{_burstyMask9_1, _burstyMask9_2, _burstyMask9_3, _burstyMask9_4, _burstyMask9_5, _burstyMask9_6, _burstyMask9_7, _burstyMask9_8, _burstyMask9_9};
    }

    private static void createBurstyPacketMaskTable() {
        _burstyPacketMaskTable = new byte[][][]{_burstyPacketMask1, _burstyPacketMask2, _burstyPacketMask3, _burstyPacketMask4, _burstyPacketMask5, _burstyPacketMask6, _burstyPacketMask7, _burstyPacketMask8, _burstyPacketMask9, _burstyPacketMask10, _burstyPacketMask11, _burstyPacketMask12};
    }

    private static void createRandomMask1_1() {
        byte[] bArr = new byte[2];
        bArr[0] = ByteCompanionObject.MIN_VALUE;
        _randomMask1_1 = bArr;
    }

    private static void createRandomMask10_1() {
        _randomMask10_1 = new byte[]{-1, -64};
    }

    private static void createRandomMask10_10() {
        _randomMask10_10 = new byte[]{76, 0, 81, 0, -96, 64, 4, -64, 3, ByteCompanionObject.MIN_VALUE, -122, 0, 41, 0, 66, 64, -104, 0, 48, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask10_2() {
        _randomMask10_2 = new byte[]{-86, ByteCompanionObject.MIN_VALUE, -43, 64};
    }

    private static void createRandomMask10_3() {
        _randomMask10_3 = new byte[]{-92, 64, -55, 0, 82, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask10_4() {
        _randomMask10_4 = new byte[]{-54, 0, 50, ByteCompanionObject.MIN_VALUE, -95, 64, 85, 0};
    }

    private static void createRandomMask10_5() {
        _randomMask10_5 = new byte[]{-54, 0, 50, ByteCompanionObject.MIN_VALUE, -95, 64, 85, 0, 8, -64};
    }

    private static void createRandomMask10_6() {
        _randomMask10_6 = new byte[]{14, 0, 51, 0, Tnaf.POW_2_WIDTH, -64, 69, 64, -120, ByteCompanionObject.MIN_VALUE, -32, 0};
    }

    private static void createRandomMask10_7() {
        _randomMask10_7 = new byte[]{70, 0, 51, 0, ByteCompanionObject.MIN_VALUE, -64, 12, 64, 40, ByteCompanionObject.MIN_VALUE, -108, 0, -63, 0};
    }

    private static void createRandomMask10_8() {
        _randomMask10_8 = new byte[]{44, 0, -127, ByteCompanionObject.MIN_VALUE, -96, 64, 5, 64, 24, ByteCompanionObject.MIN_VALUE, -62, 0, 34, ByteCompanionObject.MIN_VALUE, 80, 64};
    }

    private static void createRandomMask10_9() {
        _randomMask10_9 = new byte[]{76, 0, 35, 0, -120, -64, 33, 64, 82, ByteCompanionObject.MIN_VALUE, -108, 0, 38, 0, 72, 64, -111, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask11_1() {
        _randomMask11_1 = new byte[]{-1, -32};
    }

    private static void createRandomMask11_10() {
        _randomMask11_10 = new byte[]{100, 64, 81, 64, -87, 0, 4, -64, -48, 0, -126, 64, 33, 32, 12, 32, 74, 0, 18, -96};
    }

    private static void createRandomMask11_11() {
        _randomMask11_11 = new byte[]{70, 64, 51, 32, -103, 0, 5, ByteCompanionObject.MIN_VALUE, ByteCompanionObject.MIN_VALUE, -96, -124, 64, 64, 96, 10, ByteCompanionObject.MIN_VALUE, 104, 0, Tnaf.POW_2_WIDTH, 32, 48, 64};
    }

    private static void createRandomMask11_2() {
        _randomMask11_2 = new byte[]{-20, -64, -101, -96};
    }

    private static void createRandomMask11_3() {
        _randomMask11_3 = new byte[]{-54, -64, -15, 64, -74, 32};
    }

    private static void createRandomMask11_4() {
        _randomMask11_4 = new byte[]{-60, -64, 49, 96, 75, 32, 44, -96};
    }

    private static void createRandomMask11_5() {
        _randomMask11_5 = new byte[]{-122, ByteCompanionObject.MIN_VALUE, 35, 32, 22, 32, 76, 32, 65, -64};
    }

    private static void createRandomMask11_6() {
        _randomMask11_6 = new byte[]{100, 64, 81, 64, 12, -96, -95, 32, 18, -96, -118, 64};
    }

    private static void createRandomMask11_7() {
        _randomMask11_7 = new byte[]{70, 64, 51, 32, -111, ByteCompanionObject.MIN_VALUE, -92, 32, 80, -96, -124, -64, 9, 96};
    }

    private static void createRandomMask11_8() {
        _randomMask11_8 = new byte[]{12, ByteCompanionObject.MIN_VALUE, ByteCompanionObject.MIN_VALUE, 96, -96, ByteCompanionObject.MIN_VALUE, 5, 64, 67, 0, 26, 0, 96, 32, 20, 32};
    }

    private static void createRandomMask11_9() {
        _randomMask11_9 = new byte[]{70, 64, 98, 96, -116, 0, 1, 96, 7, ByteCompanionObject.MIN_VALUE, -96, ByteCompanionObject.MIN_VALUE, 24, -96, -111, 0, 120, 0};
    }

    private static void createRandomMask12_1() {
        _randomMask12_1 = new byte[]{-1, -16};
    }

    private static void createRandomMask12_10() {
        _randomMask12_10 = new byte[]{81, 64, 69, Tnaf.POW_2_WIDTH, ByteCompanionObject.MIN_VALUE, -48, 36, 32, 10, 32, 0, -32, -72, 0, 9, Tnaf.POW_2_WIDTH, 86, 0, -94, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask12_11() {
        _randomMask12_11 = new byte[]{83, 96, 33, 48, Tnaf.POW_2_WIDTH, -112, 0, 112, 12, Tnaf.POW_2_WIDTH, 64, -64, 106, 0, -122, 0, 36, ByteCompanionObject.MIN_VALUE, -119, 0, -64, 32};
    }

    private static void createRandomMask12_12() {
        _randomMask12_12 = new byte[]{Tnaf.POW_2_WIDTH, 96, 2, 48, 64, 80, 33, ByteCompanionObject.MIN_VALUE, -127, Tnaf.POW_2_WIDTH, 20, ByteCompanionObject.MIN_VALUE, -104, 0, 8, -112, 98, 0, 36, 32, -118, 0, -124, 64};
    }

    private static void createRandomMask12_2() {
        _randomMask12_2 = new byte[]{-20, -64, -109, -80};
    }

    private static void createRandomMask12_3() {
        _randomMask12_3 = new byte[]{-101, ByteCompanionObject.MIN_VALUE, 79, Tnaf.POW_2_WIDTH, 60, 96};
    }

    private static void createRandomMask12_4() {
        _randomMask12_4 = new byte[]{-117, 32, 20, -80, 34, -48, 69, 80};
    }

    private static void createRandomMask12_5() {
        _randomMask12_5 = new byte[]{83, 96, 100, 32, 12, -64, -126, -96, 9, 48};
    }

    private static void createRandomMask12_6() {
        _randomMask12_6 = new byte[]{81, 64, -59, Tnaf.POW_2_WIDTH, 33, ByteCompanionObject.MIN_VALUE, 18, 48, 8, -32, 46, 0};
    }

    private static void createRandomMask12_7() {
        _randomMask12_7 = new byte[]{83, 96, 33, 48, -112, -112, 2, 80, 6, -96, 44, 0, -120, 96};
    }

    private static void createRandomMask12_8() {
        _randomMask12_8 = new byte[]{32, 96, ByteCompanionObject.MIN_VALUE, 48, 66, 64, 1, -112, 20, Tnaf.POW_2_WIDTH, 10, ByteCompanionObject.MIN_VALUE, 56, 0, -59, 0};
    }

    private static void createRandomMask12_9() {
        _randomMask12_9 = new byte[]{83, 96, -28, 32, 36, 64, -95, Tnaf.POW_2_WIDTH, 24, 48, 3, -112, -118, Tnaf.POW_2_WIDTH, 4, -112, 0, -32};
    }

    private static void createRandomMask13_1() {
        _randomMask13_1 = new byte[]{-1, -8};
    }

    private static void createRandomMask13_10() {
        _randomMask13_10 = new byte[]{-47, 0, 68, 80, Tnaf.POW_2_WIDTH, -104, -96, 80, 74, 8, 64, 48, ByteCompanionObject.MIN_VALUE, 40, 12, -112, 5, -120, 98, 32};
    }

    private static void createRandomMask13_11() {
        _randomMask13_11 = new byte[]{81, 32, 34, Tnaf.POW_2_WIDTH, 19, 64, 37, 0, 24, 24, 10, 32, -120, -120, 6, ByteCompanionObject.MIN_VALUE, -32, 32, -124, 64, 68, 24};
    }

    private static void createRandomMask13_12() {
        _randomMask13_12 = new byte[]{40, 40, -124, 80, 96, 64, 5, 72, 2, -104, 1, 48, 72, Tnaf.POW_2_WIDTH, 36, ByteCompanionObject.MIN_VALUE, -108, 0, -118, 0, 17, ByteCompanionObject.MIN_VALUE, 82, 32};
    }

    private static void createRandomMask13_13() {
        _randomMask13_13 = new byte[]{81, 32, 102, 64, 5, 72, -127, 32, -108, 0, 48, ByteCompanionObject.MIN_VALUE, 33, Tnaf.POW_2_WIDTH, 3, -64, -24, 0, 10, Tnaf.POW_2_WIDTH, ByteCompanionObject.MIN_VALUE, 24, 4, -112, 8, -88};
    }

    private static void createRandomMask13_2() {
        _randomMask13_2 = new byte[]{-20, -64, 27, 56};
    }

    private static void createRandomMask13_3() {
        _randomMask13_3 = new byte[]{-103, -80, 70, -40, 55, 40};
    }

    private static void createRandomMask13_4() {
        _randomMask13_4 = new byte[]{73, -80, 38, -48, -123, 104, 82, 88};
    }

    private static void createRandomMask13_5() {
        _randomMask13_5 = new byte[]{81, 48, 102, 64, 12, 104, -95, -64, 34, -104};
    }

    private static void createRandomMask13_6() {
        _randomMask13_6 = new byte[]{-47, 32, 70, -48, 21, 72, 33, 112, 40, -56, -86, 32};
    }

    private static void createRandomMask13_7() {
        _randomMask13_7 = new byte[]{89, 32, 38, 80, -79, 64, 43, 8, 20, -56, -56, -120, -124, -80};
    }

    private static void createRandomMask13_8() {
        _randomMask13_8 = new byte[]{ByteCompanionObject.MIN_VALUE, -88, 48, -112, 22, 8, 3, 48, 68, 96, 8, 24, -40, 0, -95, 64};
    }

    private static void createRandomMask13_9() {
        _randomMask13_9 = new byte[]{89, 32, 102, 64, 20, 64, 33, 72, 2, -56, -108, Tnaf.POW_2_WIDTH, ByteCompanionObject.MIN_VALUE, -88, 10, -112, 64, 24};
    }

    private static void createRandomMask14_1() {
        _randomMask14_1 = new byte[]{-1, -4};
    }

    private static void createRandomMask14_10() {
        _randomMask14_10 = new byte[]{-64, -44, 29, 64, -44, 8, 2, 96, 4, 40, 32, -104, 64, 68, 8, -124, 104, 0, 35, Tnaf.POW_2_WIDTH};
    }

    private static void createRandomMask14_11() {
        _randomMask14_11 = new byte[]{98, -48, 53, 32, 20, 20, -59, 8, 34, 12, -120, -72, 66, 84, 40, -92, -108, 32, 27, 4, 34, -64};
    }

    private static void createRandomMask14_12() {
        _randomMask14_12 = new byte[]{-127, 4, 64, 104, -112, 36, 40, 40, 82, Tnaf.POW_2_WIDTH, 65, -120, 9, 48, 72, 68, 4, 68, 14, ByteCompanionObject.MIN_VALUE, -91, -112, 18, 12};
    }

    private static void createRandomMask14_13() {
        _randomMask14_13 = new byte[]{98, 84, 52, 96, 72, 4, 0, -84, 40, 8, -127, 8, 35, 4, 6, ByteCompanionObject.MIN_VALUE, ByteCompanionObject.MIN_VALUE, 20, 48, Tnaf.POW_2_WIDTH, -116, 32, 84, 0, ByteCompanionObject.MIN_VALUE, -64};
    }

    private static void createRandomMask14_14() {
        _randomMask14_14 = new byte[]{64, 84, 21, 64, -64, 4, 40, Tnaf.POW_2_WIDTH, 5, 12, 100, ByteCompanionObject.MIN_VALUE, -127, ByteCompanionObject.MIN_VALUE, Tnaf.POW_2_WIDTH, -104, -124, 32, 18, 48, 98, 0, 40, 96, 14, 8, Tnaf.POW_2_WIDTH, -124};
    }

    private static void createRandomMask14_2() {
        _randomMask14_2 = new byte[]{-20, -24, 59, -100};
    }

    private static void createRandomMask14_3() {
        _randomMask14_3 = new byte[]{-84, -40, 85, 108, 39, -76};
    }

    private static void createRandomMask14_4() {
        _randomMask14_4 = new byte[]{44, -40, -109, 104, 26, -76, 71, 44};
    }

    private static void createRandomMask14_5() {
        _randomMask14_5 = new byte[]{100, -40, -91, 104, 82, -76, 29, -88, -100, 84};
    }

    private static void createRandomMask14_6() {
        _randomMask14_6 = new byte[]{74, 84, -107, 72, 20, -76, 81, -88, 34, 108, -120, -116};
    }

    private static void createRandomMask14_7() {
        _randomMask14_7 = new byte[]{98, 84, -71, 32, 24, -76, 84, -104, 6, 108, -123, 84, -86, -120};
    }

    private static void createRandomMask14_8() {
        _randomMask14_8 = new byte[]{-64, 20, 65, 96, -120, 48, 32, -92, 10, 72, 4, -104, -108, 64, 114, 0};
    }

    private static void createRandomMask14_9() {
        _randomMask14_9 = new byte[]{-94, 84, 52, 96, 74, 36, 32, -88, 17, -124, 73, 8, -122, 12, 32, -44, -120, 72};
    }

    private static void createRandomMask15_1() {
        _randomMask15_1 = new byte[]{-1, -2};
    }

    private static void createRandomMask15_10() {
        _randomMask15_10 = new byte[]{-64, -96, 21, 86, 116, 64, 0, -100, 1, 44, 68, -110, -120, 80, 32, -92, -86, 4, 2, 98};
    }

    private static void createRandomMask15_11() {
        _randomMask15_11 = new byte[]{98, 34, -15, Tnaf.POW_2_WIDTH, Tnaf.POW_2_WIDTH, 14, Tnaf.POW_2_WIDTH, -80, 36, 36, 1, 18, 0, -60, 4, -94, 2, 88, 43, 0, -104, 64};
    }

    private static void createRandomMask15_12() {
        _randomMask15_12 = new byte[]{-120, -112, 64, 84, -126, 98, 33, -92, Tnaf.POW_2_WIDTH, 100, 68, 10, Tnaf.POW_2_WIDTH, -56, 77, 42, 56, 2, 23, 72, -112, -124, 114, 20};
    }

    private static void createRandomMask15_13() {
        _randomMask15_13 = new byte[]{98, -94, 52, 68, 64, 74, -60, 4, 8, 96, -108, 18, -120, -64, 33, 50, -63, 64, Tnaf.POW_2_WIDTH, 104, 6, -112, 89, 0, 10, 12};
    }

    private static void createRandomMask15_14() {
        _randomMask15_14 = new byte[]{64, -126, 21, 84, -120, 18, -64, Tnaf.POW_2_WIDTH, ByteCompanionObject.MIN_VALUE, -96, 1, 34, 64, 44, 34, 2, -112, 4, 18, 64, 93, 0, 32, 84, -122, 8, 40, -120};
    }

    private static void createRandomMask15_15() {
        _randomMask15_15 = new byte[]{98, 34, 49, Tnaf.POW_2_WIDTH, 88, 0, 1, 18, -120, 32, 68, 2, 41, 4, -126, -96, 10, 26, 17, -32, -124, 4, -122, 64, 0, -122, 68, 72, Tnaf.POW_2_WIDTH, -104};
    }

    private static void createRandomMask15_2() {
        _randomMask15_2 = new byte[]{-20, -22, -69, -100};
    }

    private static void createRandomMask15_3() {
        _randomMask15_3 = new byte[]{-84, -110, 85, 74, 67, 54};
    }

    private static void createRandomMask15_4() {
        _randomMask15_4 = new byte[]{37, -86, -107, 84, 26, 106, 67, -44};
    }

    private static void createRandomMask15_5() {
        _randomMask15_5 = new byte[]{100, -94, 37, 84, 73, 104, 83, -112, -114, 48};
    }

    private static void createRandomMask15_6() {
        _randomMask15_6 = new byte[]{98, -118, 21, 84, 76, 70, 82, -108, 35, 100, -118, 88};
    }

    private static void createRandomMask15_7() {
        _randomMask15_7 = new byte[]{98, -94, -79, 20, 24, 106, 68, -44, 19, 100, 73, 26, -122, -116};
    }

    private static void createRandomMask15_8() {
        _randomMask15_8 = new byte[]{-112, 34, 9, 80, 0, 106, 32, 52, 20, 68, -62, Tnaf.POW_2_WIDTH, 0, -58, 101, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask15_9() {
        _randomMask15_9 = new byte[]{98, 34, 36, 68, -64, 80, 3, 12, 22, 40, -119, 0, -126, -112, 8, -92, -112, 72};
    }

    private static void createRandomMask16_1() {
        _randomMask16_1 = new byte[]{-1, -1};
    }

    private static void createRandomMask16_10() {
        _randomMask16_10 = new byte[]{69, 81, Tnaf.POW_2_WIDTH, -94, 1, 37, 11, 66, -40, 32, -126, -116, 36, 74, 56, 24, 42, 37, -124, -110};
    }

    private static void createRandomMask16_11() {
        _randomMask16_11 = new byte[]{85, 85, 42, 34, 49, 17, -125, 66, 6, -104, 64, -31, 44, 68, -40, 40, -110, -127, -124, 50, 104, 12};
    }

    private static void createRandomMask16_12() {
        _randomMask16_12 = new byte[]{-124, 49, 24, -94, 78, 1, 68, -56, 14, -112, 32, -52, -109, 64, 45, Tnaf.POW_2_WIDTH, 49, 68, -64, 35, 17, 37, -24, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask16_13() {
        _randomMask16_13 = new byte[]{69, 21, 34, 34, -106, 12, 12, 80, 98, 4, 73, 6, 17, -126, 18, 56, 64, 113, -88, -118, 8, -95, -96, -64, -59, Tnaf.POW_2_WIDTH};
    }

    private static void createRandomMask16_14() {
        _randomMask16_14 = new byte[]{69, 81, 34, 10, -124, -48, 12, -118, 24, 6, 48, 3, 97, 8, 64, 17, Tnaf.POW_2_WIDTH, 44, 9, 96, 0, -108, 82, 64, -92, 36, -126, -120};
    }

    private static void createRandomMask16_15() {
        _randomMask16_15 = new byte[]{85, 17, 34, 34, 17, 17, ByteCompanionObject.MIN_VALUE, 69, 32, 26, 8, 104, 34, -124, 72, 9, 7, 1, -108, 32, -126, 6, 96, 72, -119, ByteCompanionObject.MIN_VALUE, 0, -114, 24, 34};
    }

    private static void createRandomMask16_16() {
        _randomMask16_16 = new byte[]{-92, Tnaf.POW_2_WIDTH, 1, 42, 6, 66, 8, 104, -127, -112, 0, -16, 80, 5, 32, 81, 67, 8, 104, ByteCompanionObject.MIN_VALUE, ByteCompanionObject.MIN_VALUE, 11, Tnaf.POW_2_WIDTH, 76, 18, 48, 64, -123, 14, 4, 24, 18};
    }

    private static void createRandomMask16_2() {
        _randomMask16_2 = new byte[]{-82, -82, 121, 121};
    }

    private static void createRandomMask16_3() {
        _randomMask16_3 = new byte[]{-83, 45, 118, 54, 38, -37};
    }

    private static void createRandomMask16_4() {
        _randomMask16_4 = new byte[]{85, 85, -86, -86, 53, 53, -54, -54};
    }

    private static void createRandomMask16_5() {
        _randomMask16_5 = new byte[]{85, 85, 42, 42, 36, 37, -124, -56, Tnaf.POW_2_WIDTH, -74};
    }

    private static void createRandomMask16_6() {
        _randomMask16_6 = new byte[]{81, 81, 10, 42, -94, 21, -124, 74, 48, -110, 4, -84};
    }

    private static void createRandomMask16_7() {
        _randomMask16_7 = new byte[]{69, 81, 34, 42, -111, 17, 46, 8, 72, 52, -112, 41, 9, -122};
    }

    private static void createRandomMask16_8() {
        _randomMask16_8 = new byte[]{32, 84, 24, -120, -124, 7, 96, 72, 18, -126, -127, 65, 64, 98, 22, 48};
    }

    private static void createRandomMask16_9() {
        _randomMask16_9 = new byte[]{85, 81, 34, 42, 5, -123, 9, 74, -124, 50, -64, 13, 32, -90, 26, 9, 68, 100};
    }

    private static void createRandomMask17_1() {
        _randomMask17_1 = new byte[]{-1, -1, ByteCompanionObject.MIN_VALUE, 0, 0, 0};
    }

    private static void createRandomMask17_10() {
        _randomMask17_10 = new byte[]{85, -116, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -86, 39, 0, 0, 0, 0, -91, 50, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 98, 97, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 60, 92, 0, 0, 0, 0, -114, -52, 0, 0, 0, 0, 106, 43, 0, 0, 0, 0, 54, 50, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -47, 37, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -56, 2, ByteCompanionObject.MIN_VALUE, 0, 0, 0};
    }

    private static void createRandomMask17_11() {
        _randomMask17_11 = new byte[]{85, -116, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -86, 39, 0, 0, 0, 0, -91, 50, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 98, 97, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 60, 92, 0, 0, 0, 0, 81, -124, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -94, 39, 0, 0, 0, 0, -107, 81, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 74, 26, 0, 0, 0, 0, 48, 104, 0, 0, 0, 0, 44, -119, 0, 0, 0, 0};
    }

    private static void createRandomMask17_12() {
        _randomMask17_12 = new byte[]{81, -124, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -94, 39, 0, 0, 0, 0, -107, 81, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 74, 26, 0, 0, 0, 0, 48, 104, 0, 0, 0, 0, 44, -119, 0, 0, 0, 0, 85, -116, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -86, 39, 0, 0, 0, 0, -91, 50, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 98, 97, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 60, 92, 0, 0, 0, 0, 81, 53, 0, 0, 0, 0};
    }

    private static void createRandomMask17_13() {
        _randomMask17_13 = new byte[]{81, -124, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -94, 39, 0, 0, 0, 0, -107, 81, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 74, 26, 0, 0, 0, 0, 48, 104, 0, 0, 0, 0, 44, -119, 0, 0, 0, 0, 21, -116, 0, 0, 0, 0, -118, 71, 0, 0, 0, 0, 37, -127, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 98, 18, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 88, 88, 0, 0, 0, 0, 14, 40, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -125, 52, 0, 0, 0, 0};
    }

    private static void createRandomMask17_14() {
        _randomMask17_14 = new byte[]{21, -116, 0, 0, 0, 0, -118, 71, 0, 0, 0, 0, 37, -127, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 98, 18, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 88, 88, 0, 0, 0, 0, 14, 40, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -125, 52, 0, 0, 0, 0, 81, -124, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -94, 39, 0, 0, 0, 0, -107, 81, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 74, 26, 0, 0, 0, 0, 48, 104, 0, 0, 0, 0, 44, -119, 0, 0, 0, 0, -80, -34, ByteCompanionObject.MIN_VALUE, 0, 0, 0};
    }

    private static void createRandomMask17_15() {
        _randomMask17_15 = new byte[]{21, -116, 0, 0, 0, 0, -118, 71, 0, 0, 0, 0, 37, -127, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 98, 18, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 88, 88, 0, 0, 0, 0, 14, 40, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -125, 52, 0, 0, 0, 0, 37, 44, 0, 0, 0, 0, -118, -111, 0, 0, 0, 0, -111, -64, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 104, 6, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 50, -56, 0, 0, 0, 0, 67, 69, 0, 0, 0, 0, -60, 48, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 28, -94, 0, 0, 0, 0};
    }

    private static void createRandomMask17_16() {
        _randomMask17_16 = new byte[]{37, 44, 0, 0, 0, 0, -118, -111, 0, 0, 0, 0, -111, -64, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 104, 6, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 50, -56, 0, 0, 0, 0, 67, 69, 0, 0, 0, 0, -60, 48, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 28, -94, 0, 0, 0, 0, 21, -116, 0, 0, 0, 0, -118, 71, 0, 0, 0, 0, 37, -127, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 98, 18, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 88, 88, 0, 0, 0, 0, 14, 40, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -125, 52, 0, 0, 0, 0, 10, 28, 0, 0, 0, 0};
    }

    private static void createRandomMask17_17() {
        _randomMask17_17 = new byte[]{37, 44, 0, 0, 0, 0, -118, -111, 0, 0, 0, 0, -111, -64, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 104, 6, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 50, -56, 0, 0, 0, 0, 67, 69, 0, 0, 0, 0, -60, 48, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 28, -94, 0, 0, 0, 0, 37, 76, 0, 0, 0, 0, -118, 102, 0, 0, 0, 0, -111, -111, 0, 0, 0, 0, 104, 66, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 50, -92, 0, 0, 0, 0, 67, 19, 0, 0, 0, 0, -60, 48, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 28, -120, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 60, 9, 0, 0, 0, 0};
    }

    private static void createRandomMask17_2() {
        _randomMask17_2 = new byte[]{-50, -50, 0, 0, 0, 0, -71, 57, ByteCompanionObject.MIN_VALUE, 0, 0, 0};
    }

    private static void createRandomMask17_3() {
        _randomMask17_3 = new byte[]{-51, -52, 0, 0, 0, 0, -105, 39, 0, 0, 0, 0, -72, -47, ByteCompanionObject.MIN_VALUE, 0, 0, 0};
    }

    private static void createRandomMask17_4() {
        _randomMask17_4 = new byte[]{-54, -20, 0, 0, 0, 0, -87, 103, 0, 0, 0, 0, 58, -79, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 85, 90, ByteCompanionObject.MIN_VALUE, 0, 0, 0};
    }

    private static void createRandomMask17_5() {
        _randomMask17_5 = new byte[]{85, 68, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 42, 102, 0, 0, 0, 0, 37, -95, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -30, 18, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -103, -104, 0, 0, 0, 0};
    }

    private static void createRandomMask17_6() {
        _randomMask17_6 = new byte[]{-47, 76, 0, 0, 0, 0, -94, -59, 0, 0, 0, 0, -107, 48, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -54, 10, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -92, -86, 0, 0, 0, 0, 120, 21, 0, 0, 0, 0};
    }

    private static void createRandomMask17_7() {
        _randomMask17_7 = new byte[]{21, 68, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -118, 35, 0, 0, 0, 0, -123, -111, 0, 0, 0, 0, 50, 10, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 88, 52, 0, 0, 0, 0, 44, 13, 0, 0, 0, 0, 67, -56, 0, 0, 0, 0};
    }

    private static void createRandomMask17_8() {
        _randomMask17_8 = new byte[]{100, 22, 0, 0, 0, 0, -94, -62, 0, 0, 0, 0, 81, 96, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 74, -123, 0, 0, 0, 0, 56, 76, 0, 0, 0, 0, -119, 41, 0, 0, 0, 0, 7, 17, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -108, -80, 0, 0, 0, 0};
    }

    private static void createRandomMask17_9() {
        _randomMask17_9 = new byte[]{-114, -52, 0, 0, 0, 0, 106, 43, 0, 0, 0, 0, 54, 50, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -47, 37, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 85, -116, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -86, 39, 0, 0, 0, 0, -91, 50, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 98, 97, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 60, 92, 0, 0, 0, 0};
    }

    private static void createRandomMask18_1() {
        _randomMask18_1 = new byte[]{-1, -1, -64, 0, 0, 0};
    }

    private static void createRandomMask18_10() {
        _randomMask18_10 = new byte[]{-116, -58, 64, 0, 0, 0, 39, 19, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 50, -103, 64, 0, 0, 0, 97, -80, -64, 0, 0, 0, 92, 46, 0, 0, 0, 0, -52, 102, 0, 0, 0, 0, 43, 21, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 50, -103, 64, 0, 0, 0, 37, -110, -64, 0, 0, 0, -3, -99, -64, 0, 0, 0};
    }

    private static void createRandomMask18_11() {
        _randomMask18_11 = new byte[]{-116, -58, 64, 0, 0, 0, 39, 19, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 50, -103, 64, 0, 0, 0, 97, -80, -64, 0, 0, 0, 92, 46, 0, 0, 0, 0, -124, -62, 64, 0, 0, 0, 39, 19, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 81, -88, -64, 0, 0, 0, 26, 13, 0, 0, 0, 0, 104, 52, 0, 0, 0, 0, -119, 68, ByteCompanionObject.MIN_VALUE, 0, 0, 0};
    }

    private static void createRandomMask18_12() {
        _randomMask18_12 = new byte[]{-124, -62, 64, 0, 0, 0, 39, 19, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 81, -88, -64, 0, 0, 0, 26, 13, 0, 0, 0, 0, 104, 52, 0, 0, 0, 0, -119, 68, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -116, -58, 64, 0, 0, 0, 39, 19, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 50, -103, 64, 0, 0, 0, 97, -80, -64, 0, 0, 0, 92, 46, 0, 0, 0, 0, 91, 12, 64, 0, 0, 0};
    }

    private static void createRandomMask18_13() {
        _randomMask18_13 = new byte[]{-124, -62, 64, 0, 0, 0, 39, 19, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 81, -88, -64, 0, 0, 0, 26, 13, 0, 0, 0, 0, 104, 52, 0, 0, 0, 0, -119, 68, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -116, 70, 0, 0, 0, 0, 71, 35, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -127, -64, -64, 0, 0, 0, 18, -119, 64, 0, 0, 0, 88, 44, 0, 0, 0, 0, 40, -108, 64, 0, 0, 0, 52, 26, 0, 0, 0, 0};
    }

    private static void createRandomMask18_14() {
        _randomMask18_14 = new byte[]{-116, 70, 0, 0, 0, 0, 71, 35, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -127, -64, -64, 0, 0, 0, 18, -119, 64, 0, 0, 0, 88, 44, 0, 0, 0, 0, 40, -108, 64, 0, 0, 0, 52, 26, 0, 0, 0, 0, -124, -62, 64, 0, 0, 0, 39, 19, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 81, -88, -64, 0, 0, 0, 26, 13, 0, 0, 0, 0, 104, 52, 0, 0, 0, 0, -119, 68, ByteCompanionObject.MIN_VALUE, 0, 0, 0, ByteCompanionObject.MAX_VALUE, 79, -64, 0, 0, 0};
    }

    private static void createRandomMask18_15() {
        _randomMask18_15 = new byte[]{-116, 70, 0, 0, 0, 0, 71, 35, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -127, -64, -64, 0, 0, 0, 18, -119, 64, 0, 0, 0, 88, 44, 0, 0, 0, 0, 40, -108, 64, 0, 0, 0, 52, 26, 0, 0, 0, 0, 44, 22, 0, 0, 0, 0, -111, 72, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -64, -32, 64, 0, 0, 0, 6, -125, 64, 0, 0, 0, -56, 100, 0, 0, 0, 0, 69, 34, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 48, -104, 64, 0, 0, 0, -94, 81, 0, 0, 0, 0};
    }

    private static void createRandomMask18_16() {
        _randomMask18_16 = new byte[]{44, 22, 0, 0, 0, 0, -111, 72, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -64, -32, 64, 0, 0, 0, 6, -125, 64, 0, 0, 0, -56, 100, 0, 0, 0, 0, 69, 34, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 48, -104, 64, 0, 0, 0, -94, 81, 0, 0, 0, 0, -116, 70, 0, 0, 0, 0, 71, 35, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -127, -64, -64, 0, 0, 0, 18, -119, 64, 0, 0, 0, 88, 44, 0, 0, 0, 0, 40, -108, 64, 0, 0, 0, 52, 26, 0, 0, 0, 0, -17, -14, 0, 0, 0, 0};
    }

    private static void createRandomMask18_17() {
        _randomMask18_17 = new byte[]{44, 22, 0, 0, 0, 0, -111, 72, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -64, -32, 64, 0, 0, 0, 6, -125, 64, 0, 0, 0, -56, 100, 0, 0, 0, 0, 69, 34, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 48, -104, 64, 0, 0, 0, -94, 81, 0, 0, 0, 0, 76, 38, 0, 0, 0, 0, 102, 51, 0, 0, 0, 0, -111, 72, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 66, -95, 64, 0, 0, 0, -92, 82, 0, 0, 0, 0, 19, 9, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 48, -104, 64, 0, 0, 0, -120, -60, 64, 0, 0, 0, 9, 4, ByteCompanionObject.MIN_VALUE, 0, 0, 0};
    }

    private static void createRandomMask18_18() {
        _randomMask18_18 = new byte[]{76, 38, 0, 0, 0, 0, 102, 51, 0, 0, 0, 0, -111, 72, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 66, -95, 64, 0, 0, 0, -92, 82, 0, 0, 0, 0, 19, 9, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 48, -104, 64, 0, 0, 0, -120, -60, 64, 0, 0, 0, 9, 4, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 44, 22, 0, 0, 0, 0, -111, 72, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -64, -32, 64, 0, 0, 0, 6, -125, 64, 0, 0, 0, -56, 100, 0, 0, 0, 0, 69, 34, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 48, -104, 64, 0, 0, 0, -94, 81, 0, 0, 0, 0, -48, 3, 64, 0, 0, 0};
    }

    private static void createRandomMask18_2() {
        _randomMask18_2 = new byte[]{-50, 103, 0, 0, 0, 0, 57, -100, -64, 0, 0, 0};
    }

    private static void createRandomMask18_3() {
        _randomMask18_3 = new byte[]{-52, 102, 0, 0, 0, 0, 39, 21, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -110, -55, 64, 0, 0, 0};
    }

    private static void createRandomMask18_4() {
        _randomMask18_4 = new byte[]{-20, 118, 0, 0, 0, 0, 103, 51, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -79, -40, -64, 0, 0, 0, 90, -83, 64, 0, 0, 0};
    }

    private static void createRandomMask18_5() {
        _randomMask18_5 = new byte[]{76, -90, 64, 0, 0, 0, 102, 51, 0, 0, 0, 0, 25, -48, -64, 0, 0, 0, -100, -119, 64, 0, 0, 0, -29, 76, 0, 0, 0, 0};
    }

    private static void createRandomMask18_6() {
        _randomMask18_6 = new byte[]{-52, 38, 0, 0, 0, 0, 69, 98, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -80, -104, 64, 0, 0, 0, -118, -123, 64, 0, 0, 0, 41, 83, 0, 0, 0, 0, -90, 10, ByteCompanionObject.MIN_VALUE, 0, 0, 0};
    }

    private static void createRandomMask18_7() {
        _randomMask18_7 = new byte[]{68, -94, 64, 0, 0, 0, 35, 17, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -111, 72, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 10, -123, 64, 0, 0, 0, 52, 26, 0, 0, 0, 0, 11, 6, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -32, 100, 0, 0, 0, 0};
    }

    private static void createRandomMask18_8() {
        _randomMask18_8 = new byte[]{22, 11, 0, 0, 0, 0, -62, 97, 0, 0, 0, 0, 96, -80, 64, 0, 0, 0, -123, 66, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 76, 38, 0, 0, 0, 0, 41, 20, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 17, -120, -64, 0, 0, 0, -80, 88, 0, 0, 0, 0};
    }

    private static void createRandomMask18_9() {
        _randomMask18_9 = new byte[]{68, -94, 64, 0, 0, 0, 102, 38, 0, 0, 0, 0, -112, 73, 64, 0, 0, 0, 1, -91, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 14, 18, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 19, 11, 0, 0, 0, 0, 32, -48, 64, 0, 0, 0, -62, 81, 0, 0, 0, 0, 41, 12, ByteCompanionObject.MIN_VALUE, 0, 0, 0};
    }

    private static void createRandomMask19_1() {
        _randomMask19_1 = new byte[]{-1, -1, -32, 0, 0, 0};
    }

    private static void createRandomMask19_10() {
        _randomMask19_10 = new byte[]{-116, -29, 0, 0, 0, 0, 39, 17, -64, 0, 0, 0, 50, -115, 32, 0, 0, 0, 97, -110, 96, 0, 0, 0, 92, 56, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -52, 117, 0, 0, 0, 0, 43, 25, -64, 0, 0, 0, 50, -46, 96, 0, 0, 0, 37, -114, -96, 0, 0, 0, 80, -120, -64, 0, 0, 0};
    }

    private static void createRandomMask19_11() {
        _randomMask19_11 = new byte[]{-116, -29, 0, 0, 0, 0, 39, 17, -64, 0, 0, 0, 50, -115, 32, 0, 0, 0, 97, -110, 96, 0, 0, 0, 92, 56, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -124, -121, 0, 0, 0, 0, 39, 25, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 81, -120, 96, 0, 0, 0, 26, 34, -96, 0, 0, 0, 104, 68, 64, 0, 0, 0, -119, 112, 0, 0, 0, 0};
    }

    private static void createRandomMask19_12() {
        _randomMask19_12 = new byte[]{-124, -121, 0, 0, 0, 0, 39, 25, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 81, -120, 96, 0, 0, 0, 26, 34, -96, 0, 0, 0, 104, 68, 64, 0, 0, 0, -119, 112, 0, 0, 0, 0, -116, -29, 0, 0, 0, 0, 39, 17, -64, 0, 0, 0, 50, -115, 32, 0, 0, 0, 97, -110, 96, 0, 0, 0, 92, 56, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -112, -56, ByteCompanionObject.MIN_VALUE, 0, 0, 0};
    }

    private static void createRandomMask19_13() {
        _randomMask19_13 = new byte[]{-124, -121, 0, 0, 0, 0, 39, 25, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 81, -120, 96, 0, 0, 0, 26, 34, -96, 0, 0, 0, 104, 68, 64, 0, 0, 0, -119, 112, 0, 0, 0, 0, -116, 35, 0, 0, 0, 0, 71, 25, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -127, -120, 96, 0, 0, 0, 18, -122, 32, 0, 0, 0, 88, 20, 64, 0, 0, 0, 40, -54, 0, 0, 0, 0, 52, 96, ByteCompanionObject.MIN_VALUE, 0, 0, 0};
    }

    private static void createRandomMask19_14() {
        _randomMask19_14 = new byte[]{-116, 35, 0, 0, 0, 0, 71, 25, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -127, -120, 96, 0, 0, 0, 18, -122, 32, 0, 0, 0, 88, 20, 64, 0, 0, 0, 40, -54, 0, 0, 0, 0, 52, 96, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -124, -121, 0, 0, 0, 0, 39, 25, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 81, -120, 96, 0, 0, 0, 26, 34, -96, 0, 0, 0, 104, 68, 64, 0, 0, 0, -119, 112, 0, 0, 0, 0, 110, 39, 96, 0, 0, 0};
    }

    private static void createRandomMask19_15() {
        _randomMask19_15 = new byte[]{-116, 35, 0, 0, 0, 0, 71, 25, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -127, -120, 96, 0, 0, 0, 18, -122, 32, 0, 0, 0, 88, 20, 64, 0, 0, 0, 40, -54, 0, 0, 0, 0, 52, 96, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 44, 22, 0, 0, 0, 0, -111, 64, -64, 0, 0, 0, -64, -48, 32, 0, 0, 0, 6, -126, -96, 0, 0, 0, -56, 12, 64, 0, 0, 0, 69, 97, 0, 0, 0, 0, 48, -111, 64, 0, 0, 0, -94, 40, 32, 0, 0, 0};
    }

    private static void createRandomMask19_16() {
        _randomMask19_16 = new byte[]{44, 22, 0, 0, 0, 0, -111, 64, -64, 0, 0, 0, -64, -48, 32, 0, 0, 0, 6, -126, -96, 0, 0, 0, -56, 12, 64, 0, 0, 0, 69, 97, 0, 0, 0, 0, 48, -111, 64, 0, 0, 0, -94, 40, 32, 0, 0, 0, -116, 35, 0, 0, 0, 0, 71, 25, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -127, -120, 96, 0, 0, 0, 18, -122, 32, 0, 0, 0, 88, 20, 64, 0, 0, 0, 40, -54, 0, 0, 0, 0, 52, 96, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 126, 117, -32, 0, 0, 0};
    }

    private static void createRandomMask19_17() {
        _randomMask19_17 = new byte[]{44, 22, 0, 0, 0, 0, -111, 64, -64, 0, 0, 0, -64, -48, 32, 0, 0, 0, 6, -126, -96, 0, 0, 0, -56, 12, 64, 0, 0, 0, 69, 97, 0, 0, 0, 0, 48, -111, 64, 0, 0, 0, -94, 40, 32, 0, 0, 0, 76, 39, 0, 0, 0, 0, 102, 113, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -111, 64, -32, 0, 0, 0, 66, -112, -96, 0, 0, 0, -92, 41, 64, 0, 0, 0, 19, 90, 0, 0, 0, 0, 48, -109, 64, 0, 0, 0, -120, -84, 32, 0, 0, 0, 9, 12, -64, 0, 0, 0};
    }

    private static void createRandomMask19_18() {
        _randomMask19_18 = new byte[]{76, 39, 0, 0, 0, 0, 102, 113, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -111, 64, -32, 0, 0, 0, 66, -112, -96, 0, 0, 0, -92, 41, 64, 0, 0, 0, 19, 90, 0, 0, 0, 0, 48, -109, 64, 0, 0, 0, -120, -84, 32, 0, 0, 0, 9, 12, -64, 0, 0, 0, 44, 22, 0, 0, 0, 0, -111, 64, -64, 0, 0, 0, -64, -48, 32, 0, 0, 0, 6, -126, -96, 0, 0, 0, -56, 12, 64, 0, 0, 0, 69, 97, 0, 0, 0, 0, 48, -111, 64, 0, 0, 0, -94, 40, 32, 0, 0, 0, 81, -105, 32, 0, 0, 0};
    }

    private static void createRandomMask19_19() {
        _randomMask19_19 = new byte[]{76, 39, 0, 0, 0, 0, 102, 113, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -111, 64, -32, 0, 0, 0, 66, -112, -96, 0, 0, 0, -92, 41, 64, 0, 0, 0, 19, 90, 0, 0, 0, 0, 48, -109, 64, 0, 0, 0, -120, -84, 32, 0, 0, 0, 9, 12, -64, 0, 0, 0, 76, 38, 0, 0, 0, 0, 102, 40, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -111, 80, 32, 0, 0, 0, 66, -126, 96, 0, 0, 0, -92, 1, -64, 0, 0, 0, 19, 67, 0, 0, 0, 0, 48, -108, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -120, -95, 32, 0, 0, 0, 9, 76, 0, 0, 0, 0, -51, -104, 64, 0, 0, 0};
    }

    private static void createRandomMask19_2() {
        _randomMask19_2 = new byte[]{-50, 119, 0, 0, 0, 0, 57, -52, -32, 0, 0, 0};
    }

    private static void createRandomMask19_3() {
        _randomMask19_3 = new byte[]{-52, 103, 0, 0, 0, 0, 39, 44, -64, 0, 0, 0, -110, -46, 96, 0, 0, 0};
    }

    private static void createRandomMask19_4() {
        _randomMask19_4 = new byte[]{-20, 115, 0, 0, 0, 0, 103, 25, -64, 0, 0, 0, -79, -52, 96, 0, 0, 0, 90, -106, -96, 0, 0, 0};
    }

    private static void createRandomMask19_5() {
        _randomMask19_5 = new byte[]{76, -25, 0, 0, 0, 0, 102, 49, -64, 0, 0, 0, -95, -52, 96, 0, 0, 0, -110, -90, -96, 0, 0, 0, -72, -103, ByteCompanionObject.MIN_VALUE, 0, 0, 0};
    }

    private static void createRandomMask19_6() {
        _randomMask19_6 = new byte[]{76, 54, 0, 0, 0, 0, 69, 104, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 48, -48, 96, 0, 0, 0, -118, -126, -96, 0, 0, 0, 38, 11, 64, 0, 0, 0, -107, 69, 0, 0, 0, 0};
    }

    private static void createRandomMask19_7() {
        _randomMask19_7 = new byte[]{-60, -93, 0, 0, 0, 0, 35, 25, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -111, 28, 32, 0, 0, 0, 74, -126, -96, 0, 0, 0, 52, 73, 64, 0, 0, 0, -117, 74, 0, 0, 0, 0, -56, 36, -64, 0, 0, 0};
    }

    private static void createRandomMask19_8() {
        _randomMask19_8 = new byte[]{22, 19, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -62, 68, -64, 0, 0, 0, 96, -24, 32, 0, 0, 0, -123, 18, 96, 0, 0, 0, -52, 33, 64, 0, 0, 0, 41, 99, 0, 0, 0, 0, 17, -104, -64, 0, 0, 0, -80, 12, 96, 0, 0, 0};
    }

    private static void createRandomMask19_9() {
        _randomMask19_9 = new byte[]{68, -89, 0, 0, 0, 0, 102, 112, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 18, -64, -32, 0, 0, 0, -61, Tnaf.POW_2_WIDTH, -96, 0, 0, 0, -116, 41, 64, 0, 0, 0, 17, 91, 0, 0, 0, 0, 33, -109, 64, 0, 0, 0, -94, 44, 0, 0, 0, 0, 24, 12, -32, 0, 0, 0};
    }

    private static void createRandomMask2_1() {
        byte[] bArr = new byte[2];
        bArr[0] = -64;
        _randomMask2_1 = bArr;
    }

    private static void createRandomMask2_2() {
        byte[] bArr = new byte[4];
        bArr[0] = -64;
        bArr[2] = ByteCompanionObject.MIN_VALUE;
        _randomMask2_2 = bArr;
    }

    private static void createRandomMask20_1() {
        _randomMask20_1 = new byte[]{-1, -1, -16, 0, 0, 0};
    }

    private static void createRandomMask20_10() {
        _randomMask20_10 = new byte[]{76, 19, 0, 0, 0, 0, 81, 20, 64, 0, 0, 0, -96, 104, Tnaf.POW_2_WIDTH, 0, 0, 0, 4, -63, 48, 0, 0, 0, 3, ByteCompanionObject.MIN_VALUE, -32, 0, 0, 0, -122, 33, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 41, 10, 64, 0, 0, 0, 66, 80, -112, 0, 0, 0, -104, 38, 0, 0, 0, 0, 48, -116, 32, 0, 0, 0};
    }

    private static void createRandomMask20_11() {
        _randomMask20_11 = new byte[]{-58, 49, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 35, -120, -32, 0, 0, 0, 26, 70, -112, 0, 0, 0, 36, -55, 48, 0, 0, 0, 113, 28, 64, 0, 0, 0, 14, 3, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 51, 12, -64, 0, 0, 0, Tnaf.POW_2_WIDTH, -60, 48, 0, 0, 0, 69, 81, 80, 0, 0, 0, -120, -94, 32, 0, 0, 0, -32, 56, 0, 0, 0, 0};
    }

    private static void createRandomMask20_12() {
        _randomMask20_12 = new byte[]{14, 3, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 51, 12, -64, 0, 0, 0, Tnaf.POW_2_WIDTH, -60, 48, 0, 0, 0, 69, 81, 80, 0, 0, 0, -120, -94, 32, 0, 0, 0, -32, 56, 0, 0, 0, 0, -58, 49, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 35, -120, -32, 0, 0, 0, 26, 70, -112, 0, 0, 0, 36, -55, 48, 0, 0, 0, 113, 28, 64, 0, 0, 0, -11, -36, 64, 0, 0, 0};
    }

    private static void createRandomMask20_13() {
        _randomMask20_13 = new byte[]{14, 3, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 51, 12, -64, 0, 0, 0, Tnaf.POW_2_WIDTH, -60, 48, 0, 0, 0, 69, 81, 80, 0, 0, 0, -120, -94, 32, 0, 0, 0, -32, 56, 0, 0, 0, 0, 70, 17, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 51, 12, -64, 0, 0, 0, Tnaf.POW_2_WIDTH, -60, 48, 0, 0, 0, 12, 67, Tnaf.POW_2_WIDTH, 0, 0, 0, 40, -118, 32, 0, 0, 0, -108, 37, 0, 0, 0, 0, -63, 48, 64, 0, 0, 0};
    }

    private static void createRandomMask20_14() {
        _randomMask20_14 = new byte[]{70, 17, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 51, 12, -64, 0, 0, 0, Tnaf.POW_2_WIDTH, -60, 48, 0, 0, 0, 12, 67, Tnaf.POW_2_WIDTH, 0, 0, 0, 40, -118, 32, 0, 0, 0, -108, 37, 0, 0, 0, 0, -63, 48, 64, 0, 0, 0, 14, 3, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 51, 12, -64, 0, 0, 0, Tnaf.POW_2_WIDTH, -60, 48, 0, 0, 0, 69, 81, 80, 0, 0, 0, -120, -94, 32, 0, 0, 0, -32, 56, 0, 0, 0, 0, 86, 62, 32, 0, 0, 0};
    }

    private static void createRandomMask20_15() {
        _randomMask20_15 = new byte[]{70, 17, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 51, 12, -64, 0, 0, 0, Tnaf.POW_2_WIDTH, -60, 48, 0, 0, 0, 12, 67, Tnaf.POW_2_WIDTH, 0, 0, 0, 40, -118, 32, 0, 0, 0, -108, 37, 0, 0, 0, 0, -63, 48, 64, 0, 0, 0, 44, 11, 0, 0, 0, 0, -127, -96, 96, 0, 0, 0, -96, 104, Tnaf.POW_2_WIDTH, 0, 0, 0, 5, 65, 80, 0, 0, 0, 24, -122, 32, 0, 0, 0, -62, 48, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 34, -120, -96, 0, 0, 0, 80, 84, Tnaf.POW_2_WIDTH, 0, 0, 0};
    }

    private static void createRandomMask20_16() {
        _randomMask20_16 = new byte[]{44, 11, 0, 0, 0, 0, -127, -96, 96, 0, 0, 0, -96, 104, Tnaf.POW_2_WIDTH, 0, 0, 0, 5, 65, 80, 0, 0, 0, 24, -122, 32, 0, 0, 0, -62, 48, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 34, -120, -96, 0, 0, 0, 80, 84, Tnaf.POW_2_WIDTH, 0, 0, 0, 70, 17, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 51, 12, -64, 0, 0, 0, Tnaf.POW_2_WIDTH, -60, 48, 0, 0, 0, 12, 67, Tnaf.POW_2_WIDTH, 0, 0, 0, 40, -118, 32, 0, 0, 0, -108, 37, 0, 0, 0, 0, -63, 48, 64, 0, 0, 0, 40, 28, 96, 0, 0, 0};
    }

    private static void createRandomMask20_17() {
        _randomMask20_17 = new byte[]{44, 11, 0, 0, 0, 0, -127, -96, 96, 0, 0, 0, -96, 104, Tnaf.POW_2_WIDTH, 0, 0, 0, 5, 65, 80, 0, 0, 0, 24, -122, 32, 0, 0, 0, -62, 48, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 34, -120, -96, 0, 0, 0, 80, 84, Tnaf.POW_2_WIDTH, 0, 0, 0, 78, 19, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -29, 56, -64, 0, 0, 0, -127, -32, 112, 0, 0, 0, 33, 72, 80, 0, 0, 0, 82, -108, -96, 0, 0, 0, -76, 45, 0, 0, 0, 0, 38, -119, -96, 0, 0, 0, 88, 86, Tnaf.POW_2_WIDTH, 0, 0, 0, 25, -122, 96, 0, 0, 0};
    }

    private static void createRandomMask20_18() {
        _randomMask20_18 = new byte[]{78, 19, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -29, 56, -64, 0, 0, 0, -127, -32, 112, 0, 0, 0, 33, 72, 80, 0, 0, 0, 82, -108, -96, 0, 0, 0, -76, 45, 0, 0, 0, 0, 38, -119, -96, 0, 0, 0, 88, 86, Tnaf.POW_2_WIDTH, 0, 0, 0, 25, -122, 96, 0, 0, 0, 44, 11, 0, 0, 0, 0, -127, -96, 96, 0, 0, 0, -96, 104, Tnaf.POW_2_WIDTH, 0, 0, 0, 5, 65, 80, 0, 0, 0, 24, -122, 32, 0, 0, 0, -62, 48, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 34, -120, -96, 0, 0, 0, 80, 84, Tnaf.POW_2_WIDTH, 0, 0, 0, 33, 123, -16, 0, 0, 0};
    }

    private static void createRandomMask20_19() {
        _randomMask20_19 = new byte[]{78, 19, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -29, 56, -64, 0, 0, 0, -127, -32, 112, 0, 0, 0, 33, 72, 80, 0, 0, 0, 82, -108, -96, 0, 0, 0, -76, 45, 0, 0, 0, 0, 38, -119, -96, 0, 0, 0, 88, 86, Tnaf.POW_2_WIDTH, 0, 0, 0, 25, -122, 96, 0, 0, 0, 76, 19, 0, 0, 0, 0, 81, 20, 64, 0, 0, 0, -96, 104, Tnaf.POW_2_WIDTH, 0, 0, 0, 4, -63, 48, 0, 0, 0, 3, ByteCompanionObject.MIN_VALUE, -32, 0, 0, 0, -122, 33, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 41, 10, 64, 0, 0, 0, 66, 80, -112, 0, 0, 0, -104, 38, 0, 0, 0, 0, 48, -116, 32, 0, 0, 0};
    }

    private static void createRandomMask20_2() {
        _randomMask20_2 = new byte[]{-18, 59, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -103, -26, 112, 0, 0, 0};
    }

    private static void createRandomMask20_20() {
        _randomMask20_20 = new byte[]{76, 19, 0, 0, 0, 0, 81, 20, 64, 0, 0, 0, -96, 104, Tnaf.POW_2_WIDTH, 0, 0, 0, 4, -63, 48, 0, 0, 0, 3, ByteCompanionObject.MIN_VALUE, -32, 0, 0, 0, -122, 33, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 41, 10, 64, 0, 0, 0, 66, 80, -112, 0, 0, 0, -104, 38, 0, 0, 0, 0, 48, -116, 32, 0, 0, 0, 78, 19, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -29, 56, -64, 0, 0, 0, -127, -32, 112, 0, 0, 0, 33, 72, 80, 0, 0, 0, 82, -108, -96, 0, 0, 0, -76, 45, 0, 0, 0, 0, 38, -119, -96, 0, 0, 0, 88, 86, Tnaf.POW_2_WIDTH, 0, 0, 0, 25, -122, 96, 0, 0, 0, -9, -115, -96, 0, 0, 0};
    }

    private static void createRandomMask20_3() {
        _randomMask20_3 = new byte[]{-50, 51, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 85, -107, 96, 0, 0, 0, -79, 106, 48, 0, 0, 0};
    }

    private static void createRandomMask20_4() {
        _randomMask20_4 = new byte[]{-26, 57, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 51, -116, -32, 0, 0, 0, -104, -26, 48, 0, 0, 0, 45, 75, 80, 0, 0, 0};
    }

    private static void createRandomMask20_5() {
        _randomMask20_5 = new byte[]{-50, 51, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 99, -104, -32, 0, 0, 0, -104, -27, 48, 0, 0, 0, 43, 83, 80, 0, 0, 0, -76, 92, -96, 0, 0, 0};
    }

    private static void createRandomMask20_6() {
        _randomMask20_6 = new byte[]{76, 27, 0, 0, 0, 0, 81, 52, 64, 0, 0, 0, 32, -24, 48, 0, 0, 0, -123, 65, 80, 0, 0, 0, 6, -122, -96, 0, 0, 0, -102, 33, ByteCompanionObject.MIN_VALUE, 0, 0, 0};
    }

    private static void createRandomMask20_7() {
        _randomMask20_7 = new byte[]{78, 17, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 51, 44, 0, 0, 0, 0, Tnaf.POW_2_WIDTH, 14, -80, 0, 0, 0, -127, 81, 80, 0, 0, 0, 36, -60, -96, 0, 0, 0, -44, 35, 0, 0, 0, 0, 12, -94, 96, 0, 0, 0};
    }

    private static void createRandomMask20_8() {
        _randomMask20_8 = new byte[]{39, 9, -64, 0, 0, 0, -119, -94, 96, 0, 0, 0, -48, 116, Tnaf.POW_2_WIDTH, 0, 0, 0, 36, -55, 48, 0, 0, 0, -30, -112, -96, 0, 0, 0, -58, 49, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 49, -116, 96, 0, 0, 0, 24, -58, 48, 0, 0, 0};
    }

    private static void createRandomMask20_9() {
        _randomMask20_9 = new byte[]{78, 19, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 98, 56, -64, 0, 0, 0, -127, -32, 112, 0, 0, 0, -31, 72, 80, 0, 0, 0, 19, -108, -96, 0, 0, 0, -76, 45, 0, 0, 0, 0, 38, -119, -96, 0, 0, 0, 88, 86, Tnaf.POW_2_WIDTH, 0, 0, 0, 73, -122, 80, 0, 0, 0};
    }

    private static void createRandomMask21_1() {
        _randomMask21_1 = new byte[]{-1, -1, -8, 0, 0, 0};
    }

    private static void createRandomMask21_10() {
        _randomMask21_10 = new byte[]{76, 25, Tnaf.POW_2_WIDTH, 0, 0, 0, 81, 20, 80, 0, 0, 0, -96, 106, 64, 0, 0, 0, 4, -63, 48, 0, 0, 0, 3, -76, 0, 0, 0, 0, -122, 32, -112, 0, 0, 0, 41, 8, 72, 0, 0, 0, 66, 67, 8, 0, 0, 0, -104, 18, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 48, -124, -88, 0, 0, 0};
    }

    private static void createRandomMask21_11() {
        _randomMask21_11 = new byte[]{-58, 33, -96, 0, 0, 0, 35, -120, -56, 0, 0, 0, 26, 69, -120, 0, 0, 0, 36, -45, 8, 0, 0, 0, 113, Tnaf.POW_2_WIDTH, 112, 0, 0, 0, 14, 25, Tnaf.POW_2_WIDTH, 0, 0, 0, 51, 20, 80, 0, 0, 0, Tnaf.POW_2_WIDTH, -61, 40, 0, 0, 0, 69, 104, 72, 0, 0, 0, -120, -124, -88, 0, 0, 0, -32, 34, -112, 0, 0, 0};
    }

    private static void createRandomMask21_12() {
        _randomMask21_12 = new byte[]{14, 25, Tnaf.POW_2_WIDTH, 0, 0, 0, 51, 20, 80, 0, 0, 0, Tnaf.POW_2_WIDTH, -61, 40, 0, 0, 0, 69, 104, 72, 0, 0, 0, -120, -124, -88, 0, 0, 0, -32, 34, -112, 0, 0, 0, -58, 33, -96, 0, 0, 0, 35, -120, -56, 0, 0, 0, 26, 69, -120, 0, 0, 0, 36, -45, 8, 0, 0, 0, 113, Tnaf.POW_2_WIDTH, 112, 0, 0, 0, -96, 101, 24, 0, 0, 0};
    }

    private static void createRandomMask21_13() {
        _randomMask21_13 = new byte[]{14, 25, Tnaf.POW_2_WIDTH, 0, 0, 0, 51, 20, 80, 0, 0, 0, Tnaf.POW_2_WIDTH, -61, 40, 0, 0, 0, 69, 104, 72, 0, 0, 0, -120, -124, -88, 0, 0, 0, -32, 34, -112, 0, 0, 0, 70, 17, -112, 0, 0, 0, 51, 12, -56, 0, 0, 0, Tnaf.POW_2_WIDTH, -28, 96, 0, 0, 0, 12, 105, 8, 0, 0, 0, 40, -108, 40, 0, 0, 0, -108, 33, 48, 0, 0, 0, -63, 2, 88, 0, 0, 0};
    }

    private static void createRandomMask21_14() {
        _randomMask21_14 = new byte[]{70, 17, -112, 0, 0, 0, 51, 12, -56, 0, 0, 0, Tnaf.POW_2_WIDTH, -28, 96, 0, 0, 0, 12, 105, 8, 0, 0, 0, 40, -108, 40, 0, 0, 0, -108, 33, 48, 0, 0, 0, -63, 2, 88, 0, 0, 0, 14, 25, Tnaf.POW_2_WIDTH, 0, 0, 0, 51, 20, 80, 0, 0, 0, Tnaf.POW_2_WIDTH, -61, 40, 0, 0, 0, 69, 104, 72, 0, 0, 0, -120, -124, -88, 0, 0, 0, -32, 34, -112, 0, 0, 0, 77, -48, -64, 0, 0, 0};
    }

    private static void createRandomMask21_15() {
        _randomMask21_15 = new byte[]{70, 17, -112, 0, 0, 0, 51, 12, -56, 0, 0, 0, Tnaf.POW_2_WIDTH, -28, 96, 0, 0, 0, 12, 105, 8, 0, 0, 0, 40, -108, 40, 0, 0, 0, -108, 33, 48, 0, 0, 0, -63, 2, 88, 0, 0, 0, 44, 3, 32, 0, 0, 0, -127, -96, 24, 0, 0, 0, -96, 104, 32, 0, 0, 0, 5, 65, 80, 0, 0, 0, 24, -112, -64, 0, 0, 0, -62, 6, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 34, -104, 8, 0, 0, 0, 80, 69, 8, 0, 0, 0};
    }

    private static void createRandomMask21_16() {
        _randomMask21_16 = new byte[]{44, 3, 32, 0, 0, 0, -127, -96, 24, 0, 0, 0, -96, 104, 32, 0, 0, 0, 5, 65, 80, 0, 0, 0, 24, -112, -64, 0, 0, 0, -62, 6, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 34, -104, 8, 0, 0, 0, 80, 69, 8, 0, 0, 0, 70, 17, -112, 0, 0, 0, 51, 12, -56, 0, 0, 0, Tnaf.POW_2_WIDTH, -28, 96, 0, 0, 0, 12, 105, 8, 0, 0, 0, 40, -108, 40, 0, 0, 0, -108, 33, 48, 0, 0, 0, -63, 2, 88, 0, 0, 0, 59, -11, 56, 0, 0, 0};
    }

    private static void createRandomMask21_17() {
        _randomMask21_17 = new byte[]{44, 3, 32, 0, 0, 0, -127, -96, 24, 0, 0, 0, -96, 104, 32, 0, 0, 0, 5, 65, 80, 0, 0, 0, 24, -112, -64, 0, 0, 0, -62, 6, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 34, -104, 8, 0, 0, 0, 80, 69, 8, 0, 0, 0, 78, 17, -112, 0, 0, 0, -29, 24, -104, 0, 0, 0, -127, -29, 0, 0, 0, 0, 33, 64, 88, 0, 0, 0, 82, -127, -32, 0, 0, 0, -76, 40, 32, 0, 0, 0, 38, -122, 40, 0, 0, 0, 88, 100, 64, 0, 0, 0, 25, -98, 0, 0, 0, 0};
    }

    private static void createRandomMask21_18() {
        _randomMask21_18 = new byte[]{78, 17, -112, 0, 0, 0, -29, 24, -104, 0, 0, 0, -127, -29, 0, 0, 0, 0, 33, 64, 88, 0, 0, 0, 82, -127, -32, 0, 0, 0, -76, 40, 32, 0, 0, 0, 38, -122, 40, 0, 0, 0, 88, 100, 64, 0, 0, 0, 25, -98, 0, 0, 0, 0, 44, 3, 32, 0, 0, 0, -127, -96, 24, 0, 0, 0, -96, 104, 32, 0, 0, 0, 5, 65, 80, 0, 0, 0, 24, -112, -64, 0, 0, 0, -62, 6, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 34, -104, 8, 0, 0, 0, 80, 69, 8, 0, 0, 0, 90, 86, 88, 0, 0, 0};
    }

    private static void createRandomMask21_19() {
        _randomMask21_19 = new byte[]{78, 17, -112, 0, 0, 0, -29, 24, -104, 0, 0, 0, -127, -29, 0, 0, 0, 0, 33, 64, 88, 0, 0, 0, 82, -127, -32, 0, 0, 0, -76, 40, 32, 0, 0, 0, 38, -122, 40, 0, 0, 0, 88, 100, 64, 0, 0, 0, 25, -98, 0, 0, 0, 0, 76, 25, Tnaf.POW_2_WIDTH, 0, 0, 0, 81, 20, 80, 0, 0, 0, -96, 106, 64, 0, 0, 0, 4, -63, 48, 0, 0, 0, 3, -76, 0, 0, 0, 0, -122, 32, -112, 0, 0, 0, 41, 8, 72, 0, 0, 0, 66, 67, 8, 0, 0, 0, -104, 18, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 48, -124, -88, 0, 0, 0};
    }

    private static void createRandomMask21_2() {
        _randomMask21_2 = new byte[]{-18, 59, 48, 0, 0, 0, -103, -26, -24, 0, 0, 0};
    }

    private static void createRandomMask21_20() {
        _randomMask21_20 = new byte[]{76, 25, Tnaf.POW_2_WIDTH, 0, 0, 0, 81, 20, 80, 0, 0, 0, -96, 106, 64, 0, 0, 0, 4, -63, 48, 0, 0, 0, 3, -76, 0, 0, 0, 0, -122, 32, -112, 0, 0, 0, 41, 8, 72, 0, 0, 0, 66, 67, 8, 0, 0, 0, -104, 18, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 48, -124, -88, 0, 0, 0, 78, 17, -112, 0, 0, 0, -29, 24, -104, 0, 0, 0, -127, -29, 0, 0, 0, 0, 33, 64, 88, 0, 0, 0, 82, -127, -32, 0, 0, 0, -76, 40, 32, 0, 0, 0, 38, -122, 40, 0, 0, 0, 88, 100, 64, 0, 0, 0, 25, -98, 0, 0, 0, 0, 42, 3, 48, 0, 0, 0};
    }

    private static void createRandomMask21_21() {
        _randomMask21_21 = new byte[]{76, 25, Tnaf.POW_2_WIDTH, 0, 0, 0, 81, 20, 80, 0, 0, 0, -96, 106, 64, 0, 0, 0, 4, -63, 48, 0, 0, 0, 3, -76, 0, 0, 0, 0, -122, 32, -112, 0, 0, 0, 41, 8, 72, 0, 0, 0, 66, 67, 8, 0, 0, 0, -104, 18, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 48, -124, -88, 0, 0, 0, 76, 17, -112, 0, 0, 0, 81, 12, -56, 0, 0, 0, -96, 102, 64, 0, 0, 0, 4, -63, 96, 0, 0, 0, 3, -96, 40, 0, 0, 0, -122, 33, Tnaf.POW_2_WIDTH, 0, 0, 0, 41, Tnaf.POW_2_WIDTH, 24, 0, 0, 0, 66, 66, -96, 0, 0, 0, -104, 26, 0, 0, 0, 0, 48, -124, 8, 0, 0, 0, -33, 76, Tnaf.POW_2_WIDTH, 0, 0, 0};
    }

    private static void createRandomMask21_3() {
        _randomMask21_3 = new byte[]{-50, 50, -80, 0, 0, 0, 85, -36, 80, 0, 0, 0, -88, -19, -120, 0, 0, 0};
    }

    private static void createRandomMask21_4() {
        _randomMask21_4 = new byte[]{-26, 49, 48, 0, 0, 0, 51, -116, 88, 0, 0, 0, -104, -46, -56, 0, 0, 0, 45, 75, 40, 0, 0, 0};
    }

    private static void createRandomMask21_5() {
        _randomMask21_5 = new byte[]{-50, 49, -80, 0, 0, 0, 99, -104, -40, 0, 0, 0, -104, -57, 104, 0, 0, 0, 77, 107, 80, 0, 0, 0, -78, 108, -88, 0, 0, 0};
    }

    private static void createRandomMask21_6() {
        _randomMask21_6 = new byte[]{76, 25, Tnaf.POW_2_WIDTH, 0, 0, 0, 81, 20, 80, 0, 0, 0, 32, -22, 8, 0, 0, 0, -123, 65, 40, 0, 0, 0, 6, ByteCompanionObject.MIN_VALUE, -40, 0, 0, 0, -118, 36, 48, 0, 0, 0};
    }

    private static void createRandomMask21_7() {
        _randomMask21_7 = new byte[]{-58, 17, -112, 0, 0, 0, 51, 4, -56, 0, 0, 0, 24, 103, 64, 0, 0, 0, 69, 66, -48, 0, 0, 0, 18, -44, 40, 0, 0, 0, -76, 40, 48, 0, 0, 0, 41, -110, 24, 0, 0, 0};
    }

    private static void createRandomMask21_8() {
        _randomMask21_8 = new byte[]{7, 10, 112, 0, 0, 0, 73, -88, 40, 0, 0, 0, -80, 122, 0, 0, 0, 0, 36, -59, -64, 0, 0, 0, 82, ByteCompanionObject.MIN_VALUE, -24, 0, 0, 0, -58, 49, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 49, -108, 24, 0, 0, 0, 24, -57, 8, 0, 0, 0};
    }

    private static void createRandomMask21_9() {
        _randomMask21_9 = new byte[]{78, 17, Tnaf.POW_2_WIDTH, 0, 0, 0, 98, 26, 8, 0, 0, 0, ByteCompanionObject.MIN_VALUE, -23, 64, 0, 0, 0, -95, 80, 80, 0, 0, 0, 83, 0, 104, 0, 0, 0, -92, 36, 48, 0, 0, 0, 22, -96, -120, 0, 0, 0, 88, 69, 32, 0, 0, 0, 41, -122, ByteCompanionObject.MIN_VALUE, 0, 0, 0};
    }

    private static void createRandomMask22_1() {
        _randomMask22_1 = new byte[]{-1, -1, -4, 0, 0, 0};
    }

    private static void createRandomMask22_10() {
        _randomMask22_10 = new byte[]{-64, 56, -120, 0, 0, 0, 48, 14, 40, 0, 0, 0, -24, 7, 0, 0, 0, 0, -123, 8, -88, 0, 0, 0, -48, -110, Tnaf.POW_2_WIDTH, 0, 0, 0, -122, 80, 72, 0, 0, 0, 74, 104, 12, 0, 0, 0, 1, -96, 116, 0, 0, 0, 76, -127, -112, 0, 0, 0, 98, 36, 4, 0, 0, 0};
    }

    private static void createRandomMask22_11() {
        _randomMask22_11 = new byte[]{70, 72, -56, 0, 0, 0, 51, 38, 100, 0, 0, 0, -103, 19, 32, 0, 0, 0, 5, ByteCompanionObject.MIN_VALUE, -80, 0, 0, 0, ByteCompanionObject.MIN_VALUE, -80, 20, 0, 0, 0, -124, 80, -120, 0, 0, 0, 64, 104, 12, 0, 0, 0, 10, -127, 80, 0, 0, 0, 104, 13, 0, 0, 0, 0, Tnaf.POW_2_WIDTH, 34, 4, 0, 0, 0, 48, 70, 8, 0, 0, 0};
    }

    private static void createRandomMask22_12() {
        _randomMask22_12 = new byte[]{100, 76, -120, 0, 0, 0, 81, 74, 40, 0, 0, 0, 12, -95, -108, 0, 0, 0, -95, 52, 36, 0, 0, 0, 18, -94, 84, 0, 0, 0, -118, 81, 72, 0, 0, 0, -122, -112, -48, 0, 0, 0, 35, 36, 100, 0, 0, 0, 22, 34, -60, 0, 0, 0, 76, 41, -124, 0, 0, 0, 65, -56, 56, 0, 0, 0, -12, 24, -100, 0, 0, 0};
    }

    private static void createRandomMask22_13() {
        _randomMask22_13 = new byte[]{100, 76, -120, 0, 0, 0, 81, 74, 40, 0, 0, 0, 12, -95, -108, 0, 0, 0, -95, 52, 36, 0, 0, 0, 18, -94, 84, 0, 0, 0, -118, 81, 72, 0, 0, 0, 70, 72, -56, 0, 0, 0, 51, 38, 100, 0, 0, 0, -111, -110, 48, 0, 0, 0, -92, 52, -124, 0, 0, 0, 80, -86, 20, 0, 0, 0, -124, -48, -104, 0, 0, 0, 9, 97, 44, 0, 0, 0};
    }

    private static void createRandomMask22_14() {
        _randomMask22_14 = new byte[]{70, 72, -56, 0, 0, 0, 51, 38, 100, 0, 0, 0, -111, -110, 48, 0, 0, 0, -92, 52, -124, 0, 0, 0, 80, -86, 20, 0, 0, 0, -124, -48, -104, 0, 0, 0, 9, 97, 44, 0, 0, 0, 100, 76, -120, 0, 0, 0, 81, 74, 40, 0, 0, 0, 12, -95, -108, 0, 0, 0, -95, 52, 36, 0, 0, 0, 18, -94, 84, 0, 0, 0, -118, 81, 72, 0, 0, 0, -58, -54, -24, 0, 0, 0};
    }

    private static void createRandomMask22_15() {
        _randomMask22_15 = new byte[]{70, 72, -56, 0, 0, 0, 51, 38, 100, 0, 0, 0, -111, -110, 48, 0, 0, 0, -92, 52, -124, 0, 0, 0, 80, -86, 20, 0, 0, 0, -124, -48, -104, 0, 0, 0, 9, 97, 44, 0, 0, 0, 12, -127, -112, 0, 0, 0, ByteCompanionObject.MIN_VALUE, 112, 12, 0, 0, 0, -96, -108, Tnaf.POW_2_WIDTH, 0, 0, 0, 5, 64, -88, 0, 0, 0, 67, 8, 96, 0, 0, 0, 26, 3, 64, 0, 0, 0, 96, 44, 4, 0, 0, 0, 20, 34, -124, 0, 0, 0};
    }

    private static void createRandomMask22_16() {
        _randomMask22_16 = new byte[]{12, -127, -112, 0, 0, 0, ByteCompanionObject.MIN_VALUE, 112, 12, 0, 0, 0, -96, -108, Tnaf.POW_2_WIDTH, 0, 0, 0, 5, 64, -88, 0, 0, 0, 67, 8, 96, 0, 0, 0, 26, 3, 64, 0, 0, 0, 96, 44, 4, 0, 0, 0, 20, 34, -124, 0, 0, 0, 70, 72, -56, 0, 0, 0, 51, 38, 100, 0, 0, 0, -111, -110, 48, 0, 0, 0, -92, 52, -124, 0, 0, 0, 80, -86, 20, 0, 0, 0, -124, -48, -104, 0, 0, 0, 9, 97, 44, 0, 0, 0, -122, -63, 68, 0, 0, 0};
    }

    private static void createRandomMask22_17() {
        _randomMask22_17 = new byte[]{12, -127, -112, 0, 0, 0, ByteCompanionObject.MIN_VALUE, 112, 12, 0, 0, 0, -96, -108, Tnaf.POW_2_WIDTH, 0, 0, 0, 5, 64, -88, 0, 0, 0, 67, 8, 96, 0, 0, 0, 26, 3, 64, 0, 0, 0, 96, 44, 4, 0, 0, 0, 20, 34, -124, 0, 0, 0, 70, 72, -56, 0, 0, 0, 98, 108, 76, 0, 0, 0, -116, 17, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 1, 96, 44, 0, 0, 0, 7, ByteCompanionObject.MIN_VALUE, -16, 0, 0, 0, -96, -108, Tnaf.POW_2_WIDTH, 0, 0, 0, 24, -93, 20, 0, 0, 0, -111, 18, 32, 0, 0, 0, 120, 15, 0, 0, 0, 0};
    }

    private static void createRandomMask22_18() {
        _randomMask22_18 = new byte[]{70, 72, -56, 0, 0, 0, 98, 108, 76, 0, 0, 0, -116, 17, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 1, 96, 44, 0, 0, 0, 7, ByteCompanionObject.MIN_VALUE, -16, 0, 0, 0, -96, -108, Tnaf.POW_2_WIDTH, 0, 0, 0, 24, -93, 20, 0, 0, 0, -111, 18, 32, 0, 0, 0, 120, 15, 0, 0, 0, 0, 12, -127, -112, 0, 0, 0, ByteCompanionObject.MIN_VALUE, 112, 12, 0, 0, 0, -96, -108, Tnaf.POW_2_WIDTH, 0, 0, 0, 5, 64, -88, 0, 0, 0, 67, 8, 96, 0, 0, 0, 26, 3, 64, 0, 0, 0, 96, 44, 4, 0, 0, 0, 20, 34, -124, 0, 0, 0, -28, -44, 108, 0, 0, 0};
    }

    private static void createRandomMask22_19() {
        _randomMask22_19 = new byte[]{70, 72, -56, 0, 0, 0, 98, 108, 76, 0, 0, 0, -116, 17, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 1, 96, 44, 0, 0, 0, 7, ByteCompanionObject.MIN_VALUE, -16, 0, 0, 0, -96, -108, Tnaf.POW_2_WIDTH, 0, 0, 0, 24, -93, 20, 0, 0, 0, -111, 18, 32, 0, 0, 0, 120, 15, 0, 0, 0, 0, 100, 76, -120, 0, 0, 0, 81, 74, 40, 0, 0, 0, -87, 21, 32, 0, 0, 0, 4, -64, -104, 0, 0, 0, -48, 26, 0, 0, 0, 0, -126, 80, 72, 0, 0, 0, 33, 36, 36, 0, 0, 0, 12, 33, -124, 0, 0, 0, 74, 9, 64, 0, 0, 0, 18, -94, 84, 0, 0, 0};
    }

    private static void createRandomMask22_2() {
        _randomMask22_2 = new byte[]{-20, -35, -104, 0, 0, 0, -101, -77, 116, 0, 0, 0};
    }

    private static void createRandomMask22_20() {
        _randomMask22_20 = new byte[]{100, 76, -120, 0, 0, 0, 81, 74, 40, 0, 0, 0, -87, 21, 32, 0, 0, 0, 4, -64, -104, 0, 0, 0, -48, 26, 0, 0, 0, 0, -126, 80, 72, 0, 0, 0, 33, 36, 36, 0, 0, 0, 12, 33, -124, 0, 0, 0, 74, 9, 64, 0, 0, 0, 18, -94, 84, 0, 0, 0, 70, 72, -56, 0, 0, 0, 98, 108, 76, 0, 0, 0, -116, 17, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 1, 96, 44, 0, 0, 0, 7, ByteCompanionObject.MIN_VALUE, -16, 0, 0, 0, -96, -108, Tnaf.POW_2_WIDTH, 0, 0, 0, 24, -93, 20, 0, 0, 0, -111, 18, 32, 0, 0, 0, 120, 15, 0, 0, 0, 0, 59, 72, -60, 0, 0, 0};
    }

    private static void createRandomMask22_21() {
        _randomMask22_21 = new byte[]{100, 76, -120, 0, 0, 0, 81, 74, 40, 0, 0, 0, -87, 21, 32, 0, 0, 0, 4, -64, -104, 0, 0, 0, -48, 26, 0, 0, 0, 0, -126, 80, 72, 0, 0, 0, 33, 36, 36, 0, 0, 0, 12, 33, -124, 0, 0, 0, 74, 9, 64, 0, 0, 0, 18, -94, 84, 0, 0, 0, 70, 72, -56, 0, 0, 0, 51, 38, 100, 0, 0, 0, -103, 19, 32, 0, 0, 0, 5, ByteCompanionObject.MIN_VALUE, -80, 0, 0, 0, ByteCompanionObject.MIN_VALUE, -80, 20, 0, 0, 0, -124, 80, -120, 0, 0, 0, 64, 104, 12, 0, 0, 0, 10, -127, 80, 0, 0, 0, 104, 13, 0, 0, 0, 0, Tnaf.POW_2_WIDTH, 34, 4, 0, 0, 0, 48, 70, 8, 0, 0, 0};
    }

    private static void createRandomMask22_22() {
        _randomMask22_22 = new byte[]{70, 72, -56, 0, 0, 0, 51, 38, 100, 0, 0, 0, -103, 19, 32, 0, 0, 0, 5, ByteCompanionObject.MIN_VALUE, -80, 0, 0, 0, ByteCompanionObject.MIN_VALUE, -80, 20, 0, 0, 0, -124, 80, -120, 0, 0, 0, 64, 104, 12, 0, 0, 0, 10, -127, 80, 0, 0, 0, 104, 13, 0, 0, 0, 0, Tnaf.POW_2_WIDTH, 34, 4, 0, 0, 0, 48, 70, 8, 0, 0, 0, 100, 76, -120, 0, 0, 0, 81, 74, 40, 0, 0, 0, -87, 21, 32, 0, 0, 0, 4, -64, -104, 0, 0, 0, -48, 26, 0, 0, 0, 0, -126, 80, 72, 0, 0, 0, 33, 36, 36, 0, 0, 0, 12, 33, -124, 0, 0, 0, 74, 9, 64, 0, 0, 0, 18, -94, 84, 0, 0, 0, -98, -50, -120, 0, 0, 0};
    }

    private static void createRandomMask22_3() {
        _randomMask22_3 = new byte[]{-54, -39, 88, 0, 0, 0, -15, 94, 40, 0, 0, 0, -74, 53, -60, 0, 0, 0};
    }

    private static void createRandomMask22_4() {
        _randomMask22_4 = new byte[]{-60, -40, -104, 0, 0, 0, 49, 102, 44, 0, 0, 0, 75, 41, 100, 0, 0, 0, 44, -91, -108, 0, 0, 0};
    }

    private static void createRandomMask22_5() {
        _randomMask22_5 = new byte[]{-58, -40, -40, 0, 0, 0, 99, 108, 108, 0, 0, 0, 29, -93, -76, 0, 0, 0, -83, 85, -88, 0, 0, 0, -78, -74, 84, 0, 0, 0};
    }

    private static void createRandomMask22_6() {
        _randomMask22_6 = new byte[]{100, 76, -120, 0, 0, 0, 81, 74, 40, 0, 0, 0, -88, 53, 4, 0, 0, 0, -60, -96, -108, 0, 0, 0, 3, 96, 108, 0, 0, 0, -112, -46, 24, 0, 0, 0};
    }

    private static void createRandomMask22_7() {
        _randomMask22_7 = new byte[]{-58, 72, -56, 0, 0, 0, 19, 38, 100, 0, 0, 0, -115, 19, -96, 0, 0, 0, -117, 65, 104, 0, 0, 0, 82, -86, 20, 0, 0, 0, -94, -44, 24, 0, 0, 0, 97, -88, 44, 0, 0, 0};
    }

    private static void createRandomMask22_8() {
        _randomMask22_8 = new byte[]{40, -123, 56, 0, 0, 0, 33, -12, 4, 0, 0, 0, -23, 29, 0, 0, 0, 0, 23, 2, -32, 0, 0, 0, -125, -96, 84, 0, 0, 0, 70, 24, -24, 0, 0, 0, 80, 106, 12, 0, 0, 0, 28, 35, -124, 0, 0, 0};
    }

    private static void createRandomMask22_9() {
        _randomMask22_9 = new byte[]{68, 72, -56, 0, 0, 0, 40, 45, 12, 0, 0, 0, 37, 20, -96, 0, 0, 0, 89, 10, 32, 0, 0, 0, 3, -96, 52, 0, 0, 0, -64, -48, 24, 0, 0, 0, -94, 48, 68, 0, 0, 0, 20, -126, -48, 0, 0, 0, -102, 3, ByteCompanionObject.MIN_VALUE, 0, 0, 0};
    }

    private static void createRandomMask23_1() {
        _randomMask23_1 = new byte[]{-1, -1, -2, 0, 0, 0};
    }

    private static void createRandomMask23_10() {
        _randomMask23_10 = new byte[]{100, 74, 40, 0, 0, 0, 81, 72, -94, 0, 0, 0, -87, Tnaf.POW_2_WIDTH, 26, 0, 0, 0, 4, -60, -124, 0, 0, 0, -48, 1, 68, 0, 0, 0, -126, 64, 28, 0, 0, 0, 33, 55, 0, 0, 0, 0, 12, 33, 34, 0, 0, 0, 74, 10, -64, 0, 0, 0, 18, -76, 80, 0, 0, 0};
    }

    private static void createRandomMask23_11() {
        _randomMask23_11 = new byte[]{70, 74, 108, 0, 0, 0, 51, 36, 38, 0, 0, 0, -103, 2, 18, 0, 0, 0, 5, ByteCompanionObject.MIN_VALUE, 14, 0, 0, 0, ByteCompanionObject.MIN_VALUE, -95, -126, 0, 0, 0, -124, 72, 24, 0, 0, 0, 64, 109, 64, 0, 0, 0, 10, -112, -64, 0, 0, 0, 104, 4, -112, 0, 0, 0, Tnaf.POW_2_WIDTH, 49, 32, 0, 0, 0, 48, 88, 4, 0, 0, 0};
    }

    private static void createRandomMask23_12() {
        _randomMask23_12 = new byte[]{100, 74, 40, 0, 0, 0, 81, 88, -94, 0, 0, 0, 12, -92, 48, 0, 0, 0, -95, 34, 70, 0, 0, 0, 18, -95, 28, 0, 0, 0, -118, 69, -64, 0, 0, 0, -122, -118, 108, 0, 0, 0, 35, 44, -124, 0, 0, 0, 22, 33, -104, 0, 0, 0, 76, 48, 84, 0, 0, 0, 65, -63, 38, 0, 0, 0, 25, 86, -28, 0, 0, 0};
    }

    private static void createRandomMask23_13() {
        _randomMask23_13 = new byte[]{100, 74, 40, 0, 0, 0, 81, 88, -94, 0, 0, 0, 12, -92, 48, 0, 0, 0, -95, 34, 70, 0, 0, 0, 18, -95, 28, 0, 0, 0, -118, 69, -64, 0, 0, 0, 70, 74, 108, 0, 0, 0, 51, 36, 38, 0, 0, 0, -111, -110, 18, 0, 0, 0, -92, 32, 74, 0, 0, 0, 80, -96, -44, 0, 0, 0, -124, -59, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 9, 113, 12, 0, 0, 0};
    }

    private static void createRandomMask23_14() {
        _randomMask23_14 = new byte[]{70, 74, 108, 0, 0, 0, 51, 36, 38, 0, 0, 0, -111, -110, 18, 0, 0, 0, -92, 32, 74, 0, 0, 0, 80, -96, -44, 0, 0, 0, -124, -59, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 9, 113, 12, 0, 0, 0, 100, 74, 40, 0, 0, 0, 81, 88, -94, 0, 0, 0, 12, -92, 48, 0, 0, 0, -95, 34, 70, 0, 0, 0, 18, -95, 28, 0, 0, 0, -118, 69, -64, 0, 0, 0, -100, Utf8.REPLACEMENT_BYTE, -78, 0, 0, 0};
    }

    private static void createRandomMask23_15() {
        _randomMask23_15 = new byte[]{70, 74, 108, 0, 0, 0, 51, 36, 38, 0, 0, 0, -111, -110, 18, 0, 0, 0, -92, 32, 74, 0, 0, 0, 80, -96, -44, 0, 0, 0, -124, -59, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 9, 113, 12, 0, 0, 0, 12, -124, 12, 0, 0, 0, ByteCompanionObject.MIN_VALUE, 112, 6, 0, 0, 0, -96, -120, 72, 0, 0, 0, 5, 64, 50, 0, 0, 0, 67, 2, -126, 0, 0, 0, 26, 1, 80, 0, 0, 0, 96, 39, 0, 0, 0, 0, 20, 56, -96, 0, 0, 0};
    }

    private static void createRandomMask23_16() {
        _randomMask23_16 = new byte[]{12, -124, 12, 0, 0, 0, ByteCompanionObject.MIN_VALUE, 112, 6, 0, 0, 0, -96, -120, 72, 0, 0, 0, 5, 64, 50, 0, 0, 0, 67, 2, -126, 0, 0, 0, 26, 1, 80, 0, 0, 0, 96, 39, 0, 0, 0, 0, 20, 56, -96, 0, 0, 0, 70, 74, 108, 0, 0, 0, 51, 36, 38, 0, 0, 0, -111, -110, 18, 0, 0, 0, -92, 32, 74, 0, 0, 0, 80, -96, -44, 0, 0, 0, -124, -59, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 9, 113, 12, 0, 0, 0, -6, -39, -12, 0, 0, 0};
    }

    private static void createRandomMask23_17() {
        _randomMask23_17 = new byte[]{12, -124, 12, 0, 0, 0, ByteCompanionObject.MIN_VALUE, 112, 6, 0, 0, 0, -96, -120, 72, 0, 0, 0, 5, 64, 50, 0, 0, 0, 67, 2, -126, 0, 0, 0, 26, 1, 80, 0, 0, 0, 96, 39, 0, 0, 0, 0, 20, 56, -96, 0, 0, 0, 70, 74, 108, 0, 0, 0, 98, 124, -124, 0, 0, 0, -116, 4, -120, 0, 0, 0, 1, 116, 34, 0, 0, 0, 7, -125, 6, 0, 0, 0, -96, ByteCompanionObject.MIN_VALUE, 114, 0, 0, 0, 24, -79, 66, 0, 0, 0, -111, 0, -110, 0, 0, 0, 120, 0, 28, 0, 0, 0};
    }

    private static void createRandomMask23_18() {
        _randomMask23_18 = new byte[]{70, 74, 108, 0, 0, 0, 98, 124, -124, 0, 0, 0, -116, 4, -120, 0, 0, 0, 1, 116, 34, 0, 0, 0, 7, -125, 6, 0, 0, 0, -96, ByteCompanionObject.MIN_VALUE, 114, 0, 0, 0, 24, -79, 66, 0, 0, 0, -111, 0, -110, 0, 0, 0, 120, 0, 28, 0, 0, 0, 12, -124, 12, 0, 0, 0, ByteCompanionObject.MIN_VALUE, 112, 6, 0, 0, 0, -96, -120, 72, 0, 0, 0, 5, 64, 50, 0, 0, 0, 67, 2, -126, 0, 0, 0, 26, 1, 80, 0, 0, 0, 96, 39, 0, 0, 0, 0, 20, 56, -96, 0, 0, 0, -126, 50, 86, 0, 0, 0};
    }

    private static void createRandomMask23_19() {
        _randomMask23_19 = new byte[]{70, 74, 108, 0, 0, 0, 98, 124, -124, 0, 0, 0, -116, 4, -120, 0, 0, 0, 1, 116, 34, 0, 0, 0, 7, -125, 6, 0, 0, 0, -96, ByteCompanionObject.MIN_VALUE, 114, 0, 0, 0, 24, -79, 66, 0, 0, 0, -111, 0, -110, 0, 0, 0, 120, 0, 28, 0, 0, 0, 100, 74, 40, 0, 0, 0, 81, 72, -94, 0, 0, 0, -87, Tnaf.POW_2_WIDTH, 26, 0, 0, 0, 4, -60, -124, 0, 0, 0, -48, 1, 68, 0, 0, 0, -126, 64, 28, 0, 0, 0, 33, 55, 0, 0, 0, 0, 12, 33, 34, 0, 0, 0, 74, 10, -64, 0, 0, 0, 18, -76, 80, 0, 0, 0};
    }

    private static void createRandomMask23_2() {
        _randomMask23_2 = new byte[]{-20, -35, -104, 0, 0, 0, -101, -78, 118, 0, 0, 0};
    }

    private static void createRandomMask23_20() {
        _randomMask23_20 = new byte[]{100, 74, 40, 0, 0, 0, 81, 72, -94, 0, 0, 0, -87, Tnaf.POW_2_WIDTH, 26, 0, 0, 0, 4, -60, -124, 0, 0, 0, -48, 1, 68, 0, 0, 0, -126, 64, 28, 0, 0, 0, 33, 55, 0, 0, 0, 0, 12, 33, 34, 0, 0, 0, 74, 10, -64, 0, 0, 0, 18, -76, 80, 0, 0, 0, 70, 74, 108, 0, 0, 0, 98, 124, -124, 0, 0, 0, -116, 4, -120, 0, 0, 0, 1, 116, 34, 0, 0, 0, 7, -125, 6, 0, 0, 0, -96, ByteCompanionObject.MIN_VALUE, 114, 0, 0, 0, 24, -79, 66, 0, 0, 0, -111, 0, -110, 0, 0, 0, 120, 0, 28, 0, 0, 0, -37, 74, 122, 0, 0, 0};
    }

    private static void createRandomMask23_21() {
        _randomMask23_21 = new byte[]{100, 74, 40, 0, 0, 0, 81, 72, -94, 0, 0, 0, -87, Tnaf.POW_2_WIDTH, 26, 0, 0, 0, 4, -60, -124, 0, 0, 0, -48, 1, 68, 0, 0, 0, -126, 64, 28, 0, 0, 0, 33, 55, 0, 0, 0, 0, 12, 33, 34, 0, 0, 0, 74, 10, -64, 0, 0, 0, 18, -76, 80, 0, 0, 0, 70, 74, 108, 0, 0, 0, 51, 36, 38, 0, 0, 0, -103, 2, 18, 0, 0, 0, 5, ByteCompanionObject.MIN_VALUE, 14, 0, 0, 0, ByteCompanionObject.MIN_VALUE, -95, -126, 0, 0, 0, -124, 72, 24, 0, 0, 0, 64, 109, 64, 0, 0, 0, 10, -112, -64, 0, 0, 0, 104, 4, -112, 0, 0, 0, Tnaf.POW_2_WIDTH, 49, 32, 0, 0, 0, 48, 88, 4, 0, 0, 0};
    }

    private static void createRandomMask23_22() {
        _randomMask23_22 = new byte[]{70, 74, 108, 0, 0, 0, 51, 36, 38, 0, 0, 0, -103, 2, 18, 0, 0, 0, 5, ByteCompanionObject.MIN_VALUE, 14, 0, 0, 0, ByteCompanionObject.MIN_VALUE, -95, -126, 0, 0, 0, -124, 72, 24, 0, 0, 0, 64, 109, 64, 0, 0, 0, 10, -112, -64, 0, 0, 0, 104, 4, -112, 0, 0, 0, Tnaf.POW_2_WIDTH, 49, 32, 0, 0, 0, 48, 88, 4, 0, 0, 0, 100, 74, 40, 0, 0, 0, 81, 72, -94, 0, 0, 0, -87, Tnaf.POW_2_WIDTH, 26, 0, 0, 0, 4, -60, -124, 0, 0, 0, -48, 1, 68, 0, 0, 0, -126, 64, 28, 0, 0, 0, 33, 55, 0, 0, 0, 0, 12, 33, 34, 0, 0, 0, 74, 10, -64, 0, 0, 0, 18, -76, 80, 0, 0, 0, -22, -115, 26, 0, 0, 0};
    }

    private static void createRandomMask23_23() {
        _randomMask23_23 = new byte[]{70, 74, 108, 0, 0, 0, 51, 36, 38, 0, 0, 0, -103, 2, 18, 0, 0, 0, 5, ByteCompanionObject.MIN_VALUE, 14, 0, 0, 0, ByteCompanionObject.MIN_VALUE, -95, -126, 0, 0, 0, -124, 72, 24, 0, 0, 0, 64, 109, 64, 0, 0, 0, 10, -112, -64, 0, 0, 0, 104, 4, -112, 0, 0, 0, Tnaf.POW_2_WIDTH, 49, 32, 0, 0, 0, 48, 88, 4, 0, 0, 0, 70, 66, 12, 0, 0, 0, 51, 32, 70, 0, 0, 0, -103, 8, 10, 0, 0, 0, 5, -124, 48, 0, 0, 0, ByteCompanionObject.MIN_VALUE, -80, 34, 0, 0, 0, -124, 66, -112, 0, 0, 0, 64, 115, 0, 0, 0, 0, 10, -127, 18, 0, 0, 0, 104, 12, 64, 0, 0, 0, Tnaf.POW_2_WIDTH, 36, -124, 0, 0, 0, 48, 81, 64, 0, 0, 0, 95, 80, -120, 0, 0, 0};
    }

    private static void createRandomMask23_3() {
        _randomMask23_3 = new byte[]{-54, -45, 100, 0, 0, 0, -15, 73, 58, 0, 0, 0, 118, 39, -48, 0, 0, 0};
    }

    private static void createRandomMask23_4() {
        _randomMask23_4 = new byte[]{-60, -47, 100, 0, 0, 0, 49, 98, -106, 0, 0, 0, 75, 36, 90, 0, 0, 0, 44, -88, -86, 0, 0, 0};
    }

    private static void createRandomMask23_5() {
        _randomMask23_5 = new byte[]{-58, -54, 108, 0, 0, 0, 99, 108, -106, 0, 0, 0, 29, -95, -36, 0, 0, 0, -83, 85, 56, 0, 0, 0, -78, -73, 6, 0, 0, 0};
    }

    private static void createRandomMask23_6() {
        _randomMask23_6 = new byte[]{100, 74, 40, 0, 0, 0, 81, 88, -94, 0, 0, 0, 12, -92, 48, 0, 0, 0, -95, 34, 70, 0, 0, 0, 18, -95, 28, 0, 0, 0, -118, 69, -64, 0, 0, 0};
    }

    private static void createRandomMask23_7() {
        _randomMask23_7 = new byte[]{70, 74, 108, 0, 0, 0, 51, 36, 38, 0, 0, 0, -111, -110, 18, 0, 0, 0, -92, 32, 74, 0, 0, 0, 80, -96, -44, 0, 0, 0, -124, -59, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 9, 113, 12, 0, 0, 0};
    }

    private static void createRandomMask23_8() {
        _randomMask23_8 = new byte[]{12, -124, 12, 0, 0, 0, ByteCompanionObject.MIN_VALUE, 112, 6, 0, 0, 0, -96, -120, 72, 0, 0, 0, 5, 64, 50, 0, 0, 0, 67, 2, -126, 0, 0, 0, 26, 1, 80, 0, 0, 0, 96, 39, 0, 0, 0, 0, 20, 56, -96, 0, 0, 0};
    }

    private static void createRandomMask23_9() {
        _randomMask23_9 = new byte[]{70, 74, 108, 0, 0, 0, 98, 124, -124, 0, 0, 0, -116, 4, -120, 0, 0, 0, 1, 116, 34, 0, 0, 0, 7, -125, 6, 0, 0, 0, -96, ByteCompanionObject.MIN_VALUE, 114, 0, 0, 0, 24, -79, 66, 0, 0, 0, -111, 0, -110, 0, 0, 0, 120, 0, 28, 0, 0, 0};
    }

    private static void createRandomMask24_1() {
        _randomMask24_1 = new byte[]{-1, -1, -1, 0, 0, 0};
    }

    private static void createRandomMask24_10() {
        _randomMask24_10 = new byte[]{17, 69, 20, 0, 0, 0, 69, 52, 83, 0, 0, 0, 0, 72, 5, 0, 0, 0, Tnaf.POW_2_WIDTH, -125, 9, 0, 0, 0, 74, 20, -95, 0, 0, 0, 64, -92, 10, 0, 0, 0, -96, 106, 2, 0, 0, 0, -120, ByteCompanionObject.MIN_VALUE, -116, 0, 0, 0, -122, 8, 96, 0, 0, 0, 84, 13, 64, 0, 0, 0};
    }

    private static void createRandomMask24_11() {
        _randomMask24_11 = new byte[]{83, 101, 52, 0, 0, 0, -96, 50, 17, 0, 0, 0, 21, 17, 65, 0, 0, 0, 3, 80, 21, 0, 0, 0, -116, -120, -56, 0, 0, 0, 40, -126, -120, 0, 0, 0, 8, 72, -124, 0, 0, 0, -103, 1, -112, 0, 0, 0, 34, -110, 41, 0, 0, 0, 70, 4, 96, 0, 0, 0, -116, 44, 2, 0, 0, 0};
    }

    private static void createRandomMask24_12() {
        _randomMask24_12 = new byte[]{Tnaf.POW_2_WIDTH, 97, 6, 0, 0, 0, 2, 48, 35, 0, 0, 0, 64, 84, 5, 0, 0, 0, 33, -126, 24, 0, 0, 0, -127, 24, 17, 0, 0, 0, 20, -127, 72, 0, 0, 0, -104, 9, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 8, -112, -119, 0, 0, 0, 98, 6, 32, 0, 0, 0, 36, 34, 66, 0, 0, 0, -118, 8, -96, 0, 0, 0, -124, 72, 68, 0, 0, 0};
    }

    private static void createRandomMask24_13() {
        _randomMask24_13 = new byte[]{81, 69, 20, 0, 0, 0, -59, 28, 81, 0, 0, 0, 33, -126, 24, 0, 0, 0, 18, 49, 35, 0, 0, 0, 8, -32, -114, 0, 0, 0, 46, 2, -32, 0, 0, 0, 83, 101, 54, 0, 0, 0, 33, 50, 19, 0, 0, 0, -112, -103, 9, 0, 0, 0, 2, 80, 37, 0, 0, 0, 6, -96, 106, 0, 0, 0, 44, 2, -64, 0, 0, 0, -120, 104, -122, 0, 0, 0};
    }

    private static void createRandomMask24_14() {
        _randomMask24_14 = new byte[]{83, 101, 54, 0, 0, 0, 33, 50, 19, 0, 0, 0, -112, -103, 9, 0, 0, 0, 2, 80, 37, 0, 0, 0, 6, -96, 106, 0, 0, 0, 44, 2, -64, 0, 0, 0, -120, 104, -122, 0, 0, 0, 81, 69, 20, 0, 0, 0, -59, 28, 81, 0, 0, 0, 33, -126, 24, 0, 0, 0, 18, 49, 35, 0, 0, 0, 8, -32, -114, 0, 0, 0, 46, 2, -32, 0, 0, 0, -14, -42, -114, 0, 0, 0};
    }

    private static void createRandomMask24_15() {
        _randomMask24_15 = new byte[]{83, 101, 54, 0, 0, 0, 33, 50, 19, 0, 0, 0, -112, -103, 9, 0, 0, 0, 2, 80, 37, 0, 0, 0, 6, -96, 106, 0, 0, 0, 44, 2, -64, 0, 0, 0, -120, 104, -122, 0, 0, 0, 32, 98, 6, 0, 0, 0, ByteCompanionObject.MIN_VALUE, 56, 3, 0, 0, 0, 66, 68, 36, 0, 0, 0, 1, -112, 25, 0, 0, 0, 20, 17, 65, 0, 0, 0, 10, ByteCompanionObject.MIN_VALUE, -88, 0, 0, 0, 56, 3, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -59, 12, 80, 0, 0, 0};
    }

    private static void createRandomMask24_16() {
        _randomMask24_16 = new byte[]{32, 98, 6, 0, 0, 0, ByteCompanionObject.MIN_VALUE, 56, 3, 0, 0, 0, 66, 68, 36, 0, 0, 0, 1, -112, 25, 0, 0, 0, 20, 17, 65, 0, 0, 0, 10, ByteCompanionObject.MIN_VALUE, -88, 0, 0, 0, 56, 3, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -59, 12, 80, 0, 0, 0, 83, 101, 54, 0, 0, 0, 33, 50, 19, 0, 0, 0, -112, -103, 9, 0, 0, 0, 2, 80, 37, 0, 0, 0, 6, -96, 106, 0, 0, 0, 44, 2, -64, 0, 0, 0, -120, 104, -122, 0, 0, 0, -1, 110, 10, 0, 0, 0};
    }

    private static void createRandomMask24_17() {
        _randomMask24_17 = new byte[]{32, 98, 6, 0, 0, 0, ByteCompanionObject.MIN_VALUE, 56, 3, 0, 0, 0, 66, 68, 36, 0, 0, 0, 1, -112, 25, 0, 0, 0, 20, 17, 65, 0, 0, 0, 10, ByteCompanionObject.MIN_VALUE, -88, 0, 0, 0, 56, 3, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -59, 12, 80, 0, 0, 0, 83, 101, 54, 0, 0, 0, -28, 46, 66, 0, 0, 0, 36, 66, 68, 0, 0, 0, -95, 26, 17, 0, 0, 0, 24, 49, -125, 0, 0, 0, 3, -112, 57, 0, 0, 0, -118, 24, -95, 0, 0, 0, 4, -112, 73, 0, 0, 0, 0, -32, 14, 0, 0, 0};
    }

    private static void createRandomMask24_18() {
        _randomMask24_18 = new byte[]{83, 101, 54, 0, 0, 0, -28, 46, 66, 0, 0, 0, 36, 66, 68, 0, 0, 0, -95, 26, 17, 0, 0, 0, 24, 49, -125, 0, 0, 0, 3, -112, 57, 0, 0, 0, -118, 24, -95, 0, 0, 0, 4, -112, 73, 0, 0, 0, 0, -32, 14, 0, 0, 0, 32, 98, 6, 0, 0, 0, ByteCompanionObject.MIN_VALUE, 56, 3, 0, 0, 0, 66, 68, 36, 0, 0, 0, 1, -112, 25, 0, 0, 0, 20, 17, 65, 0, 0, 0, 10, ByteCompanionObject.MIN_VALUE, -88, 0, 0, 0, 56, 3, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -59, 12, 80, 0, 0, 0, 52, 80, -82, 0, 0, 0};
    }

    private static void createRandomMask24_19() {
        _randomMask24_19 = new byte[]{83, 101, 54, 0, 0, 0, -28, 46, 66, 0, 0, 0, 36, 66, 68, 0, 0, 0, -95, 26, 17, 0, 0, 0, 24, 49, -125, 0, 0, 0, 3, -112, 57, 0, 0, 0, -118, 24, -95, 0, 0, 0, 4, -112, 73, 0, 0, 0, 0, -32, 14, 0, 0, 0, 81, 69, 20, 0, 0, 0, 69, 20, 81, 0, 0, 0, ByteCompanionObject.MIN_VALUE, -40, 13, 0, 0, 0, 36, 34, 66, 0, 0, 0, 10, 32, -94, 0, 0, 0, 0, -32, 14, 0, 0, 0, -72, 11, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 9, Tnaf.POW_2_WIDTH, -111, 0, 0, 0, 86, 5, 96, 0, 0, 0, -94, -118, 40, 0, 0, 0};
    }

    private static void createRandomMask24_2() {
        _randomMask24_2 = new byte[]{-20, -50, -52, 0, 0, 0, -109, -71, 59, 0, 0, 0};
    }

    private static void createRandomMask24_20() {
        _randomMask24_20 = new byte[]{81, 69, 20, 0, 0, 0, 69, 20, 81, 0, 0, 0, ByteCompanionObject.MIN_VALUE, -40, 13, 0, 0, 0, 36, 34, 66, 0, 0, 0, 10, 32, -94, 0, 0, 0, 0, -32, 14, 0, 0, 0, -72, 11, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 9, Tnaf.POW_2_WIDTH, -111, 0, 0, 0, 86, 5, 96, 0, 0, 0, -94, -118, 40, 0, 0, 0, 83, 101, 54, 0, 0, 0, -28, 46, 66, 0, 0, 0, 36, 66, 68, 0, 0, 0, -95, 26, 17, 0, 0, 0, 24, 49, -125, 0, 0, 0, 3, -112, 57, 0, 0, 0, -118, 24, -95, 0, 0, 0, 4, -112, 73, 0, 0, 0, 0, -32, 14, 0, 0, 0, -104, -94, -107, 0, 0, 0};
    }

    private static void createRandomMask24_21() {
        _randomMask24_21 = new byte[]{81, 69, 20, 0, 0, 0, 69, 20, 81, 0, 0, 0, ByteCompanionObject.MIN_VALUE, -40, 13, 0, 0, 0, 36, 34, 66, 0, 0, 0, 10, 32, -94, 0, 0, 0, 0, -32, 14, 0, 0, 0, -72, 11, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 9, Tnaf.POW_2_WIDTH, -111, 0, 0, 0, 86, 5, 96, 0, 0, 0, -94, -118, 40, 0, 0, 0, 83, 101, 54, 0, 0, 0, 33, 50, 19, 0, 0, 0, Tnaf.POW_2_WIDTH, -111, 9, 0, 0, 0, 0, 112, 7, 0, 0, 0, 12, Tnaf.POW_2_WIDTH, -63, 0, 0, 0, 64, -60, 12, 0, 0, 0, 106, 6, -96, 0, 0, 0, -122, 8, 96, 0, 0, 0, 36, -126, 72, 0, 0, 0, -119, 8, -112, 0, 0, 0, -64, 44, 2, 0, 0, 0};
    }

    private static void createRandomMask24_22() {
        _randomMask24_22 = new byte[]{83, 101, 54, 0, 0, 0, 33, 50, 19, 0, 0, 0, Tnaf.POW_2_WIDTH, -111, 9, 0, 0, 0, 0, 112, 7, 0, 0, 0, 12, Tnaf.POW_2_WIDTH, -63, 0, 0, 0, 64, -60, 12, 0, 0, 0, 106, 6, -96, 0, 0, 0, -122, 8, 96, 0, 0, 0, 36, -126, 72, 0, 0, 0, -119, 8, -112, 0, 0, 0, -64, 44, 2, 0, 0, 0, 81, 69, 20, 0, 0, 0, 69, 20, 81, 0, 0, 0, ByteCompanionObject.MIN_VALUE, -40, 13, 0, 0, 0, 36, 34, 66, 0, 0, 0, 10, 32, -94, 0, 0, 0, 0, -32, 14, 0, 0, 0, -72, 11, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 9, Tnaf.POW_2_WIDTH, -111, 0, 0, 0, 86, 5, 96, 0, 0, 0, -94, -118, 40, 0, 0, 0, 26, -86, -18, 0, 0, 0};
    }

    private static void createRandomMask24_23() {
        _randomMask24_23 = new byte[]{83, 101, 54, 0, 0, 0, 33, 50, 19, 0, 0, 0, Tnaf.POW_2_WIDTH, -111, 9, 0, 0, 0, 0, 112, 7, 0, 0, 0, 12, Tnaf.POW_2_WIDTH, -63, 0, 0, 0, 64, -60, 12, 0, 0, 0, 106, 6, -96, 0, 0, 0, -122, 8, 96, 0, 0, 0, 36, -126, 72, 0, 0, 0, -119, 8, -112, 0, 0, 0, -64, 44, 2, 0, 0, 0, Tnaf.POW_2_WIDTH, 97, 6, 0, 0, 0, 2, 48, 35, 0, 0, 0, 64, 84, 5, 0, 0, 0, 33, -126, 24, 0, 0, 0, -127, 24, 17, 0, 0, 0, 20, -127, 72, 0, 0, 0, -104, 9, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 8, -112, -119, 0, 0, 0, 98, 6, 32, 0, 0, 0, 36, 34, 66, 0, 0, 0, -118, 8, -96, 0, 0, 0, -124, 72, 68, 0, 0, 0};
    }

    private static void createRandomMask24_24() {
        _randomMask24_24 = new byte[]{Tnaf.POW_2_WIDTH, 97, 6, 0, 0, 0, 2, 48, 35, 0, 0, 0, 64, 84, 5, 0, 0, 0, 33, -126, 24, 0, 0, 0, -127, 24, 17, 0, 0, 0, 20, -127, 72, 0, 0, 0, -104, 9, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 8, -112, -119, 0, 0, 0, 98, 6, 32, 0, 0, 0, 36, 34, 66, 0, 0, 0, -118, 8, -96, 0, 0, 0, -124, 72, 68, 0, 0, 0, 83, 101, 54, 0, 0, 0, 33, 50, 19, 0, 0, 0, Tnaf.POW_2_WIDTH, -111, 9, 0, 0, 0, 0, 112, 7, 0, 0, 0, 12, Tnaf.POW_2_WIDTH, -63, 0, 0, 0, 64, -60, 12, 0, 0, 0, 106, 6, -96, 0, 0, 0, -122, 8, 96, 0, 0, 0, 36, -126, 72, 0, 0, 0, -119, 8, -112, 0, 0, 0, -64, 44, 2, 0, 0, 0, -120, 50, 89, 0, 0, 0};
    }

    private static void createRandomMask24_3() {
        _randomMask24_3 = new byte[]{-101, 41, -78, 0, 0, 0, 73, -44, -99, 0, 0, 0, 62, -125, -24, 0, 0, 0};
    }

    private static void createRandomMask24_4() {
        _randomMask24_4 = new byte[]{-117, 40, -78, 0, 0, 0, 20, -79, 75, 0, 0, 0, 34, -46, 45, 0, 0, 0, 69, 84, 85, 0, 0, 0};
    }

    private static void createRandomMask24_5() {
        _randomMask24_5 = new byte[]{83, 101, 54, 0, 0, 0, 100, -74, 75, 0, 0, 0, 14, -32, -18, 0, 0, 0, -87, -54, -100, 0, 0, 0, -72, 59, -125, 0, 0, 0};
    }

    private static void createRandomMask24_6() {
        _randomMask24_6 = new byte[]{-47, 77, 20, 0, 0, 0, 69, 52, 83, 0, 0, 0, 34, -46, 45, 0, 0, 0, 22, -63, 108, 0, 0, 0, 11, -96, -70, 0, 0, 0, -24, -114, -120, 0, 0, 0};
    }

    private static void createRandomMask24_7() {
        _randomMask24_7 = new byte[]{-45, 101, 54, 0, 0, 0, 37, 50, 83, 0, 0, 0, 48, -45, 5, 0, 0, 0, 6, 72, 108, 0, 0, 0, -64, -72, 27, 0, 0, 0, 42, -94, -86, 0, 0, 0, -88, 78, -124, 0, 0, 0};
    }

    private static void createRandomMask24_8() {
        _randomMask24_8 = new byte[]{-127, 96, 22, 0, 0, 0, 64, 60, 3, 0, 0, 0, Tnaf.POW_2_WIDTH, -111, 9, 0, 0, 0, 6, 80, 101, 0, 0, 0, 32, 74, -124, 0, 0, 0, -118, -96, -86, 0, 0, 0, 51, 3, 48, 0, 0, 0, 76, -124, -56, 0, 0, 0};
    }

    private static void createRandomMask24_9() {
        _randomMask24_9 = new byte[]{-45, 101, 54, 0, 0, 0, 100, 38, 66, 0, 0, 0, 24, 65, -60, 0, 0, 0, -96, 74, 4, 0, 0, 0, -127, 56, 19, 0, 0, 0, 34, -94, 42, 0, 0, 0, 8, 112, -121, 0, 0, 0, 4, -112, 73, 0, 0, 0, 1, -64, 28, 0, 0, 0};
    }

    private static void createRandomMask25_1() {
        _randomMask25_1 = new byte[]{-1, -1, -1, ByteCompanionObject.MIN_VALUE, 0, 0};
    }

    private static void createRandomMask25_10() {
        _randomMask25_10 = new byte[]{81, 77, Tnaf.POW_2_WIDTH, 0, 0, 0, 69, 20, 69, 0, 0, 0, ByteCompanionObject.MIN_VALUE, -47, 9, ByteCompanionObject.MIN_VALUE, 0, 0, 36, 42, 5, 0, 0, 0, 10, 36, -96, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -28, 3, 0, 0, 0, -72, 8, 2, ByteCompanionObject.MIN_VALUE, 0, 0, 9, Tnaf.POW_2_WIDTH, -55, 0, 0, 0, 86, 0, 88, ByteCompanionObject.MIN_VALUE, 0, 0, -94, -122, 34, 0, 0, 0};
    }

    private static void createRandomMask25_11() {
        _randomMask25_11 = new byte[]{83, 101, 18, 0, 0, 0, 33, 50, 33, 0, 0, 0, Tnaf.POW_2_WIDTH, -111, 52, 0, 0, 0, 0, 114, 80, 0, 0, 0, 12, 17, -127, ByteCompanionObject.MIN_VALUE, 0, 0, 64, -64, -94, 0, 0, 0, 106, 8, -120, ByteCompanionObject.MIN_VALUE, 0, 0, -122, 0, 104, 0, 0, 0, 36, -114, 2, 0, 0, 0, -119, 8, 68, 0, 0, 0, -64, 36, 65, ByteCompanionObject.MIN_VALUE, 0, 0};
    }

    private static void createRandomMask25_12() {
        _randomMask25_12 = new byte[]{Tnaf.POW_2_WIDTH, 98, -126, ByteCompanionObject.MIN_VALUE, 0, 0, 2, 56, 69, 0, 0, 0, 64, 86, 4, 0, 0, 0, 33, ByteCompanionObject.MIN_VALUE, 84, ByteCompanionObject.MIN_VALUE, 0, 0, -127, Tnaf.POW_2_WIDTH, 41, ByteCompanionObject.MIN_VALUE, 0, 0, 20, ByteCompanionObject.MIN_VALUE, 19, 0, 0, 0, -104, 4, -127, 0, 0, 0, 8, -110, 72, 0, 0, 0, 98, 9, 64, 0, 0, 0, 36, 40, -96, 0, 0, 0, -118, 1, 24, 0, 0, 0, -124, 69, 34, 0, 0, 0};
    }

    private static void createRandomMask25_13() {
        _randomMask25_13 = new byte[]{81, 77, 18, 0, 0, 0, -59, 20, 109, 0, 0, 0, 33, -127, 84, ByteCompanionObject.MIN_VALUE, 0, 0, 18, 50, 23, 0, 0, 0, 8, -30, -116, ByteCompanionObject.MIN_VALUE, 0, 0, 46, 10, -94, 0, 0, 0, 83, 101, -110, 0, 0, 0, 33, 50, 101, 0, 0, 0, -112, -101, 20, 0, 0, 0, 2, 82, -80, ByteCompanionObject.MIN_VALUE, 0, 0, 6, -95, 76, ByteCompanionObject.MIN_VALUE, 0, 0, 44, 12, -120, ByteCompanionObject.MIN_VALUE, 0, 0, -120, 104, 75, 0, 0, 0};
    }

    private static void createRandomMask25_14() {
        _randomMask25_14 = new byte[]{83, 101, -110, 0, 0, 0, 33, 50, 101, 0, 0, 0, -112, -101, 20, 0, 0, 0, 2, 82, -80, ByteCompanionObject.MIN_VALUE, 0, 0, 6, -95, 76, ByteCompanionObject.MIN_VALUE, 0, 0, 44, 12, -120, ByteCompanionObject.MIN_VALUE, 0, 0, -120, 104, 75, 0, 0, 0, 81, 77, 18, 0, 0, 0, -59, 20, 109, 0, 0, 0, 33, -127, 84, ByteCompanionObject.MIN_VALUE, 0, 0, 18, 50, 23, 0, 0, 0, 8, -30, -116, ByteCompanionObject.MIN_VALUE, 0, 0, 46, 10, -94, 0, 0, 0, 115, 118, 97, ByteCompanionObject.MIN_VALUE, 0, 0};
    }

    private static void createRandomMask25_15() {
        _randomMask25_15 = new byte[]{83, 101, -110, 0, 0, 0, 33, 50, 101, 0, 0, 0, -112, -101, 20, 0, 0, 0, 2, 82, -80, ByteCompanionObject.MIN_VALUE, 0, 0, 6, -95, 76, ByteCompanionObject.MIN_VALUE, 0, 0, 44, 12, -120, ByteCompanionObject.MIN_VALUE, 0, 0, -120, 104, 75, 0, 0, 0, 32, 104, 10, ByteCompanionObject.MIN_VALUE, 0, 0, ByteCompanionObject.MIN_VALUE, 51, 9, 0, 0, 0, 66, 65, 96, ByteCompanionObject.MIN_VALUE, 0, 0, 1, -112, 51, 0, 0, 0, 20, 20, 70, 0, 0, 0, 10, ByteCompanionObject.MIN_VALUE, -127, ByteCompanionObject.MIN_VALUE, 0, 0, 56, 13, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -59, 10, 20, 0, 0, 0};
    }

    private static void createRandomMask25_16() {
        _randomMask25_16 = new byte[]{32, 104, 10, ByteCompanionObject.MIN_VALUE, 0, 0, ByteCompanionObject.MIN_VALUE, 51, 9, 0, 0, 0, 66, 65, 96, ByteCompanionObject.MIN_VALUE, 0, 0, 1, -112, 51, 0, 0, 0, 20, 20, 70, 0, 0, 0, 10, ByteCompanionObject.MIN_VALUE, -127, ByteCompanionObject.MIN_VALUE, 0, 0, 56, 13, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -59, 10, 20, 0, 0, 0, 83, 101, -110, 0, 0, 0, 33, 50, 101, 0, 0, 0, -112, -101, 20, 0, 0, 0, 2, 82, -80, ByteCompanionObject.MIN_VALUE, 0, 0, 6, -95, 76, ByteCompanionObject.MIN_VALUE, 0, 0, 44, 12, -120, ByteCompanionObject.MIN_VALUE, 0, 0, -120, 104, 75, 0, 0, 0, 22, -24, -36, ByteCompanionObject.MIN_VALUE, 0, 0};
    }

    private static void createRandomMask25_17() {
        _randomMask25_17 = new byte[]{32, 104, 10, ByteCompanionObject.MIN_VALUE, 0, 0, ByteCompanionObject.MIN_VALUE, 51, 9, 0, 0, 0, 66, 65, 96, ByteCompanionObject.MIN_VALUE, 0, 0, 1, -112, 51, 0, 0, 0, 20, 20, 70, 0, 0, 0, 10, ByteCompanionObject.MIN_VALUE, -127, ByteCompanionObject.MIN_VALUE, 0, 0, 56, 13, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -59, 10, 20, 0, 0, 0, 83, 101, -110, 0, 0, 0, -28, 38, 100, 0, 0, 0, 36, 65, 68, 0, 0, 0, -95, 18, 20, ByteCompanionObject.MIN_VALUE, 0, 0, 24, 48, 44, ByteCompanionObject.MIN_VALUE, 0, 0, 3, -103, 65, 0, 0, 0, -118, 24, 10, ByteCompanionObject.MIN_VALUE, 0, 0, 4, -112, -87, 0, 0, 0, 0, -28, 1, ByteCompanionObject.MIN_VALUE, 0, 0};
    }

    private static void createRandomMask25_18() {
        _randomMask25_18 = new byte[]{83, 101, -110, 0, 0, 0, -28, 38, 100, 0, 0, 0, 36, 65, 68, 0, 0, 0, -95, 18, 20, ByteCompanionObject.MIN_VALUE, 0, 0, 24, 48, 44, ByteCompanionObject.MIN_VALUE, 0, 0, 3, -103, 65, 0, 0, 0, -118, 24, 10, ByteCompanionObject.MIN_VALUE, 0, 0, 4, -112, -87, 0, 0, 0, 0, -28, 1, ByteCompanionObject.MIN_VALUE, 0, 0, 32, 104, 10, ByteCompanionObject.MIN_VALUE, 0, 0, ByteCompanionObject.MIN_VALUE, 51, 9, 0, 0, 0, 66, 65, 96, ByteCompanionObject.MIN_VALUE, 0, 0, 1, -112, 51, 0, 0, 0, 20, 20, 70, 0, 0, 0, 10, ByteCompanionObject.MIN_VALUE, -127, ByteCompanionObject.MIN_VALUE, 0, 0, 56, 13, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -59, 10, 20, 0, 0, 0, -50, -101, -31, 0, 0, 0};
    }

    private static void createRandomMask25_19() {
        _randomMask25_19 = new byte[]{83, 101, -110, 0, 0, 0, -28, 38, 100, 0, 0, 0, 36, 65, 68, 0, 0, 0, -95, 18, 20, ByteCompanionObject.MIN_VALUE, 0, 0, 24, 48, 44, ByteCompanionObject.MIN_VALUE, 0, 0, 3, -103, 65, 0, 0, 0, -118, 24, 10, ByteCompanionObject.MIN_VALUE, 0, 0, 4, -112, -87, 0, 0, 0, 0, -28, 1, ByteCompanionObject.MIN_VALUE, 0, 0, 81, 77, Tnaf.POW_2_WIDTH, 0, 0, 0, 69, 20, 69, 0, 0, 0, ByteCompanionObject.MIN_VALUE, -47, 9, ByteCompanionObject.MIN_VALUE, 0, 0, 36, 42, 5, 0, 0, 0, 10, 36, -96, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -28, 3, 0, 0, 0, -72, 8, 2, ByteCompanionObject.MIN_VALUE, 0, 0, 9, Tnaf.POW_2_WIDTH, -55, 0, 0, 0, 86, 0, 88, ByteCompanionObject.MIN_VALUE, 0, 0, -94, -122, 34, 0, 0, 0};
    }

    private static void createRandomMask25_2() {
        _randomMask25_2 = new byte[]{-20, -50, -52, 0, 0, 0, -109, -79, -77, ByteCompanionObject.MIN_VALUE, 0, 0};
    }

    private static void createRandomMask25_20() {
        _randomMask25_20 = new byte[]{81, 77, Tnaf.POW_2_WIDTH, 0, 0, 0, 69, 20, 69, 0, 0, 0, ByteCompanionObject.MIN_VALUE, -47, 9, ByteCompanionObject.MIN_VALUE, 0, 0, 36, 42, 5, 0, 0, 0, 10, 36, -96, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -28, 3, 0, 0, 0, -72, 8, 2, ByteCompanionObject.MIN_VALUE, 0, 0, 9, Tnaf.POW_2_WIDTH, -55, 0, 0, 0, 86, 0, 88, ByteCompanionObject.MIN_VALUE, 0, 0, -94, -122, 34, 0, 0, 0, 83, 101, -110, 0, 0, 0, -28, 38, 100, 0, 0, 0, 36, 65, 68, 0, 0, 0, -95, 18, 20, ByteCompanionObject.MIN_VALUE, 0, 0, 24, 48, 44, ByteCompanionObject.MIN_VALUE, 0, 0, 3, -103, 65, 0, 0, 0, -118, 24, 10, ByteCompanionObject.MIN_VALUE, 0, 0, 4, -112, -87, 0, 0, 0, 0, -28, 1, ByteCompanionObject.MIN_VALUE, 0, 0, 27, -118, -96, 0, 0, 0};
    }

    private static void createRandomMask25_21() {
        _randomMask25_21 = new byte[]{81, 77, Tnaf.POW_2_WIDTH, 0, 0, 0, 69, 20, 69, 0, 0, 0, ByteCompanionObject.MIN_VALUE, -47, 9, ByteCompanionObject.MIN_VALUE, 0, 0, 36, 42, 5, 0, 0, 0, 10, 36, -96, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -28, 3, 0, 0, 0, -72, 8, 2, ByteCompanionObject.MIN_VALUE, 0, 0, 9, Tnaf.POW_2_WIDTH, -55, 0, 0, 0, 86, 0, 88, ByteCompanionObject.MIN_VALUE, 0, 0, -94, -122, 34, 0, 0, 0, 83, 101, 18, 0, 0, 0, 33, 50, 33, 0, 0, 0, Tnaf.POW_2_WIDTH, -111, 52, 0, 0, 0, 0, 114, 80, 0, 0, 0, 12, 17, -127, ByteCompanionObject.MIN_VALUE, 0, 0, 64, -64, -94, 0, 0, 0, 106, 8, -120, ByteCompanionObject.MIN_VALUE, 0, 0, -122, 0, 104, 0, 0, 0, 36, -114, 2, 0, 0, 0, -119, 8, 68, 0, 0, 0, -64, 36, 65, ByteCompanionObject.MIN_VALUE, 0, 0};
    }

    private static void createRandomMask25_22() {
        _randomMask25_22 = new byte[]{83, 101, 18, 0, 0, 0, 33, 50, 33, 0, 0, 0, Tnaf.POW_2_WIDTH, -111, 52, 0, 0, 0, 0, 114, 80, 0, 0, 0, 12, 17, -127, ByteCompanionObject.MIN_VALUE, 0, 0, 64, -64, -94, 0, 0, 0, 106, 8, -120, ByteCompanionObject.MIN_VALUE, 0, 0, -122, 0, 104, 0, 0, 0, 36, -114, 2, 0, 0, 0, -119, 8, 68, 0, 0, 0, -64, 36, 65, ByteCompanionObject.MIN_VALUE, 0, 0, 81, 77, Tnaf.POW_2_WIDTH, 0, 0, 0, 69, 20, 69, 0, 0, 0, ByteCompanionObject.MIN_VALUE, -47, 9, ByteCompanionObject.MIN_VALUE, 0, 0, 36, 42, 5, 0, 0, 0, 10, 36, -96, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -28, 3, 0, 0, 0, -72, 8, 2, ByteCompanionObject.MIN_VALUE, 0, 0, 9, Tnaf.POW_2_WIDTH, -55, 0, 0, 0, 86, 0, 88, ByteCompanionObject.MIN_VALUE, 0, 0, -94, -122, 34, 0, 0, 0, 21, -94, -103, 0, 0, 0};
    }

    private static void createRandomMask25_23() {
        _randomMask25_23 = new byte[]{83, 101, 18, 0, 0, 0, 33, 50, 33, 0, 0, 0, Tnaf.POW_2_WIDTH, -111, 52, 0, 0, 0, 0, 114, 80, 0, 0, 0, 12, 17, -127, ByteCompanionObject.MIN_VALUE, 0, 0, 64, -64, -94, 0, 0, 0, 106, 8, -120, ByteCompanionObject.MIN_VALUE, 0, 0, -122, 0, 104, 0, 0, 0, 36, -114, 2, 0, 0, 0, -119, 8, 68, 0, 0, 0, -64, 36, 65, ByteCompanionObject.MIN_VALUE, 0, 0, Tnaf.POW_2_WIDTH, 98, -126, ByteCompanionObject.MIN_VALUE, 0, 0, 2, 56, 69, 0, 0, 0, 64, 86, 4, 0, 0, 0, 33, ByteCompanionObject.MIN_VALUE, 84, ByteCompanionObject.MIN_VALUE, 0, 0, -127, Tnaf.POW_2_WIDTH, 41, ByteCompanionObject.MIN_VALUE, 0, 0, 20, ByteCompanionObject.MIN_VALUE, 19, 0, 0, 0, -104, 4, -127, 0, 0, 0, 8, -110, 72, 0, 0, 0, 98, 9, 64, 0, 0, 0, 36, 40, -96, 0, 0, 0, -118, 1, 24, 0, 0, 0, -124, 69, 34, 0, 0, 0};
    }

    private static void createRandomMask25_24() {
        _randomMask25_24 = new byte[]{Tnaf.POW_2_WIDTH, 98, -126, ByteCompanionObject.MIN_VALUE, 0, 0, 2, 56, 69, 0, 0, 0, 64, 86, 4, 0, 0, 0, 33, ByteCompanionObject.MIN_VALUE, 84, ByteCompanionObject.MIN_VALUE, 0, 0, -127, Tnaf.POW_2_WIDTH, 41, ByteCompanionObject.MIN_VALUE, 0, 0, 20, ByteCompanionObject.MIN_VALUE, 19, 0, 0, 0, -104, 4, -127, 0, 0, 0, 8, -110, 72, 0, 0, 0, 98, 9, 64, 0, 0, 0, 36, 40, -96, 0, 0, 0, -118, 1, 24, 0, 0, 0, -124, 69, 34, 0, 0, 0, 83, 101, 18, 0, 0, 0, 33, 50, 33, 0, 0, 0, Tnaf.POW_2_WIDTH, -111, 52, 0, 0, 0, 0, 114, 80, 0, 0, 0, 12, 17, -127, ByteCompanionObject.MIN_VALUE, 0, 0, 64, -64, -94, 0, 0, 0, 106, 8, -120, ByteCompanionObject.MIN_VALUE, 0, 0, -122, 0, 104, 0, 0, 0, 36, -114, 2, 0, 0, 0, -119, 8, 68, 0, 0, 0, -64, 36, 65, ByteCompanionObject.MIN_VALUE, 0, 0, -7, 12, 20, ByteCompanionObject.MIN_VALUE, 0, 0};
    }

    private static void createRandomMask25_25() {
        _randomMask25_25 = new byte[]{Tnaf.POW_2_WIDTH, 98, -126, ByteCompanionObject.MIN_VALUE, 0, 0, 2, 56, 69, 0, 0, 0, 64, 86, 4, 0, 0, 0, 33, ByteCompanionObject.MIN_VALUE, 84, ByteCompanionObject.MIN_VALUE, 0, 0, -127, Tnaf.POW_2_WIDTH, 41, ByteCompanionObject.MIN_VALUE, 0, 0, 20, ByteCompanionObject.MIN_VALUE, 19, 0, 0, 0, -104, 4, -127, 0, 0, 0, 8, -110, 72, 0, 0, 0, 98, 9, 64, 0, 0, 0, 36, 40, -96, 0, 0, 0, -118, 1, 24, 0, 0, 0, -124, 69, 34, 0, 0, 0, Tnaf.POW_2_WIDTH, 101, 18, 0, 0, 0, 2, 54, 100, 0, 0, 0, 64, 80, 84, ByteCompanionObject.MIN_VALUE, 0, 0, 33, -120, 18, 0, 0, 0, -127, 25, 64, 0, 0, 0, 20, -125, 8, 0, 0, 0, -104, 2, 17, 0, 0, 0, 8, -112, 60, 0, 0, 0, 98, 14, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 36, 32, -95, 0, 0, 0, -118, 8, 1, ByteCompanionObject.MIN_VALUE, 0, 0, -124, 64, 73, 0, 0, 0, 28, 32, -118, ByteCompanionObject.MIN_VALUE, 0, 0};
    }

    private static void createRandomMask25_3() {
        _randomMask25_3 = new byte[]{-101, -119, -101, 0, 0, 0, 79, 20, 109, ByteCompanionObject.MIN_VALUE, 0, 0, 60, 99, 114, ByteCompanionObject.MIN_VALUE, 0, 0};
    }

    private static void createRandomMask25_4() {
        _randomMask25_4 = new byte[]{-117, 36, -101, 0, 0, 0, 20, -78, 109, 0, 0, 0, 34, -40, 86, ByteCompanionObject.MIN_VALUE, 0, 0, 69, 85, 37, ByteCompanionObject.MIN_VALUE, 0, 0};
    }

    private static void createRandomMask25_5() {
        _randomMask25_5 = new byte[]{83, 101, 19, 0, 0, 0, 100, 38, 100, 0, 0, 0, 12, -64, -58, ByteCompanionObject.MIN_VALUE, 0, 0, -126, -86, 28, 0, 0, 0, 9, 50, 41, ByteCompanionObject.MIN_VALUE, 0, 0};
    }

    private static void createRandomMask25_6() {
        _randomMask25_6 = new byte[]{81, 77, 18, 0, 0, 0, -59, 20, 109, 0, 0, 0, 33, -127, 84, ByteCompanionObject.MIN_VALUE, 0, 0, 18, 50, 23, 0, 0, 0, 8, -30, -116, ByteCompanionObject.MIN_VALUE, 0, 0, 46, 10, -94, 0, 0, 0};
    }

    private static void createRandomMask25_7() {
        _randomMask25_7 = new byte[]{83, 101, -110, 0, 0, 0, 33, 50, 101, 0, 0, 0, -112, -101, 20, 0, 0, 0, 2, 82, -80, ByteCompanionObject.MIN_VALUE, 0, 0, 6, -95, 76, ByteCompanionObject.MIN_VALUE, 0, 0, 44, 12, -120, ByteCompanionObject.MIN_VALUE, 0, 0, -120, 104, 75, 0, 0, 0};
    }

    private static void createRandomMask25_8() {
        _randomMask25_8 = new byte[]{32, 104, 10, ByteCompanionObject.MIN_VALUE, 0, 0, ByteCompanionObject.MIN_VALUE, 51, 9, 0, 0, 0, 66, 65, 96, ByteCompanionObject.MIN_VALUE, 0, 0, 1, -112, 51, 0, 0, 0, 20, 20, 70, 0, 0, 0, 10, ByteCompanionObject.MIN_VALUE, -127, ByteCompanionObject.MIN_VALUE, 0, 0, 56, 13, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -59, 10, 20, 0, 0, 0};
    }

    private static void createRandomMask25_9() {
        _randomMask25_9 = new byte[]{83, 101, -110, 0, 0, 0, -28, 38, 100, 0, 0, 0, 36, 65, 68, 0, 0, 0, -95, 18, 20, ByteCompanionObject.MIN_VALUE, 0, 0, 24, 48, 44, ByteCompanionObject.MIN_VALUE, 0, 0, 3, -103, 65, 0, 0, 0, -118, 24, 10, ByteCompanionObject.MIN_VALUE, 0, 0, 4, -112, -87, 0, 0, 0, 0, -28, 1, ByteCompanionObject.MIN_VALUE, 0, 0};
    }

    private static void createRandomMask26_1() {
        _randomMask26_1 = new byte[]{-1, -1, -1, -64, 0, 0};
    }

    private static void createRandomMask26_10() {
        _randomMask26_10 = new byte[]{-47, 6, -120, 0, 0, 0, 68, 82, 34, ByteCompanionObject.MIN_VALUE, 0, 0, Tnaf.POW_2_WIDTH, -104, -124, -64, 0, 0, -96, 85, 2, ByteCompanionObject.MIN_VALUE, 0, 0, 74, 10, 80, 64, 0, 0, 64, 50, 1, ByteCompanionObject.MIN_VALUE, 0, 0, ByteCompanionObject.MIN_VALUE, 44, 1, 64, 0, 0, 12, -112, 100, ByteCompanionObject.MIN_VALUE, 0, 0, 5, -120, 44, 64, 0, 0, 98, 35, 17, 0, 0, 0};
    }

    private static void createRandomMask26_11() {
        _randomMask26_11 = new byte[]{81, 34, -119, 0, 0, 0, 34, 17, Tnaf.POW_2_WIDTH, ByteCompanionObject.MIN_VALUE, 0, 0, 19, 64, -102, 0, 0, 0, 37, 1, 40, 0, 0, 0, 24, 24, -64, -64, 0, 0, 10, 32, 81, 0, 0, 0, -120, -116, 68, 64, 0, 0, 6, ByteCompanionObject.MIN_VALUE, 52, 0, 0, 0, -32, 39, 1, 0, 0, 0, -124, 68, 34, 0, 0, 0, 68, 26, 32, -64, 0, 0};
    }

    private static void createRandomMask26_12() {
        _randomMask26_12 = new byte[]{40, 41, 65, 64, 0, 0, -124, 84, 34, ByteCompanionObject.MIN_VALUE, 0, 0, 96, 67, 2, 0, 0, 0, 5, 72, 42, 64, 0, 0, 2, -104, 20, -64, 0, 0, 1, 48, 9, ByteCompanionObject.MIN_VALUE, 0, 0, 72, 18, 64, ByteCompanionObject.MIN_VALUE, 0, 0, 36, -127, 36, 0, 0, 0, -108, 4, -96, 0, 0, 0, -118, 4, 80, 0, 0, 0, 17, ByteCompanionObject.MIN_VALUE, -116, 0, 0, 0, 82, 34, -111, 0, 0, 0};
    }

    private static void createRandomMask26_13() {
        _randomMask26_13 = new byte[]{81, 34, -119, 0, 0, 0, 102, 67, 50, 0, 0, 0, 5, 72, 42, 64, 0, 0, -127, 36, 9, 0, 0, 0, -108, 4, -96, 0, 0, 0, 48, -127, -124, 0, 0, 0, 33, 17, 8, ByteCompanionObject.MIN_VALUE, 0, 0, 3, -64, 30, 0, 0, 0, -24, 7, 64, 0, 0, 0, 10, Tnaf.POW_2_WIDTH, 80, ByteCompanionObject.MIN_VALUE, 0, 0, ByteCompanionObject.MIN_VALUE, 28, 0, -64, 0, 0, 4, -112, 36, ByteCompanionObject.MIN_VALUE, 0, 0, 8, -88, 69, 64, 0, 0};
    }

    private static void createRandomMask26_14() {
        _randomMask26_14 = new byte[]{89, 34, -55, 0, 0, 0, 38, 81, 50, ByteCompanionObject.MIN_VALUE, 0, 0, -79, 69, -118, 0, 0, 0, 43, 9, 88, 64, 0, 0, 20, -56, -90, 64, 0, 0, -56, -114, 68, 64, 0, 0, -124, -76, 37, ByteCompanionObject.MIN_VALUE, 0, 0, -47, 38, -119, 0, 0, 0, 70, -46, 54, ByteCompanionObject.MIN_VALUE, 0, 0, 21, 72, -86, 64, 0, 0, 33, 113, 11, ByteCompanionObject.MIN_VALUE, 0, 0, 40, -55, 70, 64, 0, 0, -86, 37, 81, 0, 0, 0, 93, -89, 120, 64, 0, 0};
    }

    private static void createRandomMask26_15() {
        _randomMask26_15 = new byte[]{89, 34, -55, 0, 0, 0, 38, 81, 50, ByteCompanionObject.MIN_VALUE, 0, 0, -79, 69, -118, 0, 0, 0, 43, 9, 88, 64, 0, 0, 20, -56, -90, 64, 0, 0, -56, -114, 68, 64, 0, 0, -124, -76, 37, ByteCompanionObject.MIN_VALUE, 0, 0, ByteCompanionObject.MIN_VALUE, -84, 5, 64, 0, 0, 48, -111, -124, ByteCompanionObject.MIN_VALUE, 0, 0, 22, 8, -80, 64, 0, 0, 3, 48, 25, ByteCompanionObject.MIN_VALUE, 0, 0, 68, 98, 35, 0, 0, 0, 8, 24, 64, -64, 0, 0, -40, 6, -64, 0, 0, 0, -95, 69, 10, 0, 0, 0};
    }

    private static void createRandomMask26_16() {
        _randomMask26_16 = new byte[]{ByteCompanionObject.MIN_VALUE, -84, 5, 64, 0, 0, 48, -111, -124, ByteCompanionObject.MIN_VALUE, 0, 0, 22, 8, -80, 64, 0, 0, 3, 48, 25, ByteCompanionObject.MIN_VALUE, 0, 0, 68, 98, 35, 0, 0, 0, 8, 24, 64, -64, 0, 0, -40, 6, -64, 0, 0, 0, -95, 69, 10, 0, 0, 0, 89, 34, -55, 0, 0, 0, 38, 81, 50, ByteCompanionObject.MIN_VALUE, 0, 0, -79, 69, -118, 0, 0, 0, 43, 9, 88, 64, 0, 0, 20, -56, -90, 64, 0, 0, -56, -114, 68, 64, 0, 0, -124, -76, 37, ByteCompanionObject.MIN_VALUE, 0, 0, 60, -81, -120, ByteCompanionObject.MIN_VALUE, 0, 0};
    }

    private static void createRandomMask26_17() {
        _randomMask26_17 = new byte[]{ByteCompanionObject.MIN_VALUE, -84, 5, 64, 0, 0, 48, -111, -124, ByteCompanionObject.MIN_VALUE, 0, 0, 22, 8, -80, 64, 0, 0, 3, 48, 25, ByteCompanionObject.MIN_VALUE, 0, 0, 68, 98, 35, 0, 0, 0, 8, 24, 64, -64, 0, 0, -40, 6, -64, 0, 0, 0, -95, 69, 10, 0, 0, 0, 89, 34, -55, 0, 0, 0, 102, 67, 50, 0, 0, 0, 20, 64, -94, 0, 0, 0, 33, 73, 10, 64, 0, 0, 2, -56, 22, 64, 0, 0, -108, 20, -96, ByteCompanionObject.MIN_VALUE, 0, 0, ByteCompanionObject.MIN_VALUE, -84, 5, 64, 0, 0, 10, -112, 84, ByteCompanionObject.MIN_VALUE, 0, 0, 64, 26, 0, -64, 0, 0};
    }

    private static void createRandomMask26_18() {
        _randomMask26_18 = new byte[]{89, 34, -55, 0, 0, 0, 102, 67, 50, 0, 0, 0, 20, 64, -94, 0, 0, 0, 33, 73, 10, 64, 0, 0, 2, -56, 22, 64, 0, 0, -108, 20, -96, ByteCompanionObject.MIN_VALUE, 0, 0, ByteCompanionObject.MIN_VALUE, -84, 5, 64, 0, 0, 10, -112, 84, ByteCompanionObject.MIN_VALUE, 0, 0, 64, 26, 0, -64, 0, 0, ByteCompanionObject.MIN_VALUE, -84, 5, 64, 0, 0, 48, -111, -124, ByteCompanionObject.MIN_VALUE, 0, 0, 22, 8, -80, 64, 0, 0, 3, 48, 25, ByteCompanionObject.MIN_VALUE, 0, 0, 68, 98, 35, 0, 0, 0, 8, 24, 64, -64, 0, 0, -40, 6, -64, 0, 0, 0, -95, 69, 10, 0, 0, 0, -86, 12, -125, ByteCompanionObject.MIN_VALUE, 0, 0};
    }

    private static void createRandomMask26_19() {
        _randomMask26_19 = new byte[]{89, 34, -55, 0, 0, 0, 102, 67, 50, 0, 0, 0, 20, 64, -94, 0, 0, 0, 33, 73, 10, 64, 0, 0, 2, -56, 22, 64, 0, 0, -108, 20, -96, ByteCompanionObject.MIN_VALUE, 0, 0, ByteCompanionObject.MIN_VALUE, -84, 5, 64, 0, 0, 10, -112, 84, ByteCompanionObject.MIN_VALUE, 0, 0, 64, 26, 0, -64, 0, 0, -47, 6, -120, 0, 0, 0, 68, 82, 34, ByteCompanionObject.MIN_VALUE, 0, 0, Tnaf.POW_2_WIDTH, -104, -124, -64, 0, 0, -96, 85, 2, ByteCompanionObject.MIN_VALUE, 0, 0, 74, 10, 80, 64, 0, 0, 64, 50, 1, ByteCompanionObject.MIN_VALUE, 0, 0, ByteCompanionObject.MIN_VALUE, 44, 1, 64, 0, 0, 12, -112, 100, ByteCompanionObject.MIN_VALUE, 0, 0, 5, -120, 44, 64, 0, 0, 98, 35, 17, 0, 0, 0};
    }

    private static void createRandomMask26_2() {
        _randomMask26_2 = new byte[]{-20, -57, 102, 0, 0, 0, 27, 56, -39, -64, 0, 0};
    }

    private static void createRandomMask26_20() {
        _randomMask26_20 = new byte[]{-47, 6, -120, 0, 0, 0, 68, 82, 34, ByteCompanionObject.MIN_VALUE, 0, 0, Tnaf.POW_2_WIDTH, -104, -124, -64, 0, 0, -96, 85, 2, ByteCompanionObject.MIN_VALUE, 0, 0, 74, 10, 80, 64, 0, 0, 64, 50, 1, ByteCompanionObject.MIN_VALUE, 0, 0, ByteCompanionObject.MIN_VALUE, 44, 1, 64, 0, 0, 12, -112, 100, ByteCompanionObject.MIN_VALUE, 0, 0, 5, -120, 44, 64, 0, 0, 98, 35, 17, 0, 0, 0, 89, 34, -55, 0, 0, 0, 102, 67, 50, 0, 0, 0, 20, 64, -94, 0, 0, 0, 33, 73, 10, 64, 0, 0, 2, -56, 22, 64, 0, 0, -108, 20, -96, ByteCompanionObject.MIN_VALUE, 0, 0, ByteCompanionObject.MIN_VALUE, -84, 5, 64, 0, 0, 10, -112, 84, ByteCompanionObject.MIN_VALUE, 0, 0, 64, 26, 0, -64, 0, 0, -12, 8, -20, 0, 0, 0};
    }

    private static void createRandomMask26_21() {
        _randomMask26_21 = new byte[]{-47, 6, -120, 0, 0, 0, 68, 82, 34, ByteCompanionObject.MIN_VALUE, 0, 0, Tnaf.POW_2_WIDTH, -104, -124, -64, 0, 0, -96, 85, 2, ByteCompanionObject.MIN_VALUE, 0, 0, 74, 10, 80, 64, 0, 0, 64, 50, 1, ByteCompanionObject.MIN_VALUE, 0, 0, ByteCompanionObject.MIN_VALUE, 44, 1, 64, 0, 0, 12, -112, 100, ByteCompanionObject.MIN_VALUE, 0, 0, 5, -120, 44, 64, 0, 0, 98, 35, 17, 0, 0, 0, 81, 34, -119, 0, 0, 0, 34, 17, Tnaf.POW_2_WIDTH, ByteCompanionObject.MIN_VALUE, 0, 0, 19, 64, -102, 0, 0, 0, 37, 1, 40, 0, 0, 0, 24, 24, -64, -64, 0, 0, 10, 32, 81, 0, 0, 0, -120, -116, 68, 64, 0, 0, 6, ByteCompanionObject.MIN_VALUE, 52, 0, 0, 0, -32, 39, 1, 0, 0, 0, -124, 68, 34, 0, 0, 0, 68, 26, 32, -64, 0, 0};
    }

    private static void createRandomMask26_22() {
        _randomMask26_22 = new byte[]{81, 34, -119, 0, 0, 0, 34, 17, Tnaf.POW_2_WIDTH, ByteCompanionObject.MIN_VALUE, 0, 0, 19, 64, -102, 0, 0, 0, 37, 1, 40, 0, 0, 0, 24, 24, -64, -64, 0, 0, 10, 32, 81, 0, 0, 0, -120, -116, 68, 64, 0, 0, 6, ByteCompanionObject.MIN_VALUE, 52, 0, 0, 0, -32, 39, 1, 0, 0, 0, -124, 68, 34, 0, 0, 0, 68, 26, 32, -64, 0, 0, -47, 6, -120, 0, 0, 0, 68, 82, 34, ByteCompanionObject.MIN_VALUE, 0, 0, Tnaf.POW_2_WIDTH, -104, -124, -64, 0, 0, -96, 85, 2, ByteCompanionObject.MIN_VALUE, 0, 0, 74, 10, 80, 64, 0, 0, 64, 50, 1, ByteCompanionObject.MIN_VALUE, 0, 0, ByteCompanionObject.MIN_VALUE, 44, 1, 64, 0, 0, 12, -112, 100, ByteCompanionObject.MIN_VALUE, 0, 0, 5, -120, 44, 64, 0, 0, 98, 35, 17, 0, 0, 0, 19, -58, 107, 64, 0, 0};
    }

    private static void createRandomMask26_23() {
        _randomMask26_23 = new byte[]{81, 34, -119, 0, 0, 0, 34, 17, Tnaf.POW_2_WIDTH, ByteCompanionObject.MIN_VALUE, 0, 0, 19, 64, -102, 0, 0, 0, 37, 1, 40, 0, 0, 0, 24, 24, -64, -64, 0, 0, 10, 32, 81, 0, 0, 0, -120, -116, 68, 64, 0, 0, 6, ByteCompanionObject.MIN_VALUE, 52, 0, 0, 0, -32, 39, 1, 0, 0, 0, -124, 68, 34, 0, 0, 0, 68, 26, 32, -64, 0, 0, 40, 41, 65, 64, 0, 0, -124, 84, 34, ByteCompanionObject.MIN_VALUE, 0, 0, 96, 67, 2, 0, 0, 0, 5, 72, 42, 64, 0, 0, 2, -104, 20, -64, 0, 0, 1, 48, 9, ByteCompanionObject.MIN_VALUE, 0, 0, 72, 18, 64, ByteCompanionObject.MIN_VALUE, 0, 0, 36, -127, 36, 0, 0, 0, -108, 4, -96, 0, 0, 0, -118, 4, 80, 0, 0, 0, 17, ByteCompanionObject.MIN_VALUE, -116, 0, 0, 0, 82, 34, -111, 0, 0, 0};
    }

    private static void createRandomMask26_24() {
        _randomMask26_24 = new byte[]{40, 41, 65, 64, 0, 0, -124, 84, 34, ByteCompanionObject.MIN_VALUE, 0, 0, 96, 67, 2, 0, 0, 0, 5, 72, 42, 64, 0, 0, 2, -104, 20, -64, 0, 0, 1, 48, 9, ByteCompanionObject.MIN_VALUE, 0, 0, 72, 18, 64, ByteCompanionObject.MIN_VALUE, 0, 0, 36, -127, 36, 0, 0, 0, -108, 4, -96, 0, 0, 0, -118, 4, 80, 0, 0, 0, 17, ByteCompanionObject.MIN_VALUE, -116, 0, 0, 0, 82, 34, -111, 0, 0, 0, 81, 34, -119, 0, 0, 0, 34, 17, Tnaf.POW_2_WIDTH, ByteCompanionObject.MIN_VALUE, 0, 0, 19, 64, -102, 0, 0, 0, 37, 1, 40, 0, 0, 0, 24, 24, -64, -64, 0, 0, 10, 32, 81, 0, 0, 0, -120, -116, 68, 64, 0, 0, 6, ByteCompanionObject.MIN_VALUE, 52, 0, 0, 0, -32, 39, 1, 0, 0, 0, -124, 68, 34, 0, 0, 0, 68, 26, 32, -64, 0, 0, -37, 77, -40, 64, 0, 0};
    }

    private static void createRandomMask26_25() {
        _randomMask26_25 = new byte[]{40, 41, 65, 64, 0, 0, -124, 84, 34, ByteCompanionObject.MIN_VALUE, 0, 0, 96, 67, 2, 0, 0, 0, 5, 72, 42, 64, 0, 0, 2, -104, 20, -64, 0, 0, 1, 48, 9, ByteCompanionObject.MIN_VALUE, 0, 0, 72, 18, 64, ByteCompanionObject.MIN_VALUE, 0, 0, 36, -127, 36, 0, 0, 0, -108, 4, -96, 0, 0, 0, -118, 4, 80, 0, 0, 0, 17, ByteCompanionObject.MIN_VALUE, -116, 0, 0, 0, 82, 34, -111, 0, 0, 0, 81, 34, -119, 0, 0, 0, 102, 67, 50, 0, 0, 0, 5, 72, 42, 64, 0, 0, -127, 36, 9, 0, 0, 0, -108, 4, -96, 0, 0, 0, 48, -127, -124, 0, 0, 0, 33, 17, 8, ByteCompanionObject.MIN_VALUE, 0, 0, 3, -64, 30, 0, 0, 0, -24, 7, 64, 0, 0, 0, 10, Tnaf.POW_2_WIDTH, 80, ByteCompanionObject.MIN_VALUE, 0, 0, ByteCompanionObject.MIN_VALUE, 28, 0, -64, 0, 0, 4, -112, 36, ByteCompanionObject.MIN_VALUE, 0, 0, 8, -88, 69, 64, 0, 0};
    }

    private static void createRandomMask26_26() {
        _randomMask26_26 = new byte[]{81, 34, -119, 0, 0, 0, 102, 67, 50, 0, 0, 0, 5, 72, 42, 64, 0, 0, -127, 36, 9, 0, 0, 0, -108, 4, -96, 0, 0, 0, 48, -127, -124, 0, 0, 0, 33, 17, 8, ByteCompanionObject.MIN_VALUE, 0, 0, 3, -64, 30, 0, 0, 0, -24, 7, 64, 0, 0, 0, 10, Tnaf.POW_2_WIDTH, 80, ByteCompanionObject.MIN_VALUE, 0, 0, ByteCompanionObject.MIN_VALUE, 28, 0, -64, 0, 0, 4, -112, 36, ByteCompanionObject.MIN_VALUE, 0, 0, 8, -88, 69, 64, 0, 0, 40, 41, 65, 64, 0, 0, -124, 84, 34, ByteCompanionObject.MIN_VALUE, 0, 0, 96, 67, 2, 0, 0, 0, 5, 72, 42, 64, 0, 0, 2, -104, 20, -64, 0, 0, 1, 48, 9, ByteCompanionObject.MIN_VALUE, 0, 0, 72, 18, 64, ByteCompanionObject.MIN_VALUE, 0, 0, 36, -127, 36, 0, 0, 0, -108, 4, -96, 0, 0, 0, -118, 4, 80, 0, 0, 0, 17, ByteCompanionObject.MIN_VALUE, -116, 0, 0, 0, 82, 34, -111, 0, 0, 0, -7, 19, 81, ByteCompanionObject.MIN_VALUE, 0, 0};
    }

    private static void createRandomMask26_3() {
        _randomMask26_3 = new byte[]{-103, -76, -51, ByteCompanionObject.MIN_VALUE, 0, 0, 70, -38, 54, -64, 0, 0, 55, 41, -71, 64, 0, 0};
    }

    private static void createRandomMask26_4() {
        _randomMask26_4 = new byte[]{73, -78, 77, ByteCompanionObject.MIN_VALUE, 0, 0, 38, -47, 54, ByteCompanionObject.MIN_VALUE, 0, 0, -123, 108, 43, 64, 0, 0, 82, 90, -110, -64, 0, 0};
    }

    private static void createRandomMask26_5() {
        _randomMask26_5 = new byte[]{81, 50, -119, ByteCompanionObject.MIN_VALUE, 0, 0, 102, 67, 50, 0, 0, 0, 12, 104, 99, 64, 0, 0, -95, -59, 14, 0, 0, 0, 34, -103, 20, -64, 0, 0};
    }

    private static void createRandomMask26_6() {
        _randomMask26_6 = new byte[]{-47, 38, -119, 0, 0, 0, 70, -46, 54, ByteCompanionObject.MIN_VALUE, 0, 0, 21, 72, -86, 64, 0, 0, 33, 113, 11, ByteCompanionObject.MIN_VALUE, 0, 0, 40, -55, 70, 64, 0, 0, -86, 37, 81, 0, 0, 0};
    }

    private static void createRandomMask26_7() {
        _randomMask26_7 = new byte[]{89, 34, -55, 0, 0, 0, 38, 81, 50, ByteCompanionObject.MIN_VALUE, 0, 0, -79, 69, -118, 0, 0, 0, 43, 9, 88, 64, 0, 0, 20, -56, -90, 64, 0, 0, -56, -114, 68, 64, 0, 0, -124, -76, 37, ByteCompanionObject.MIN_VALUE, 0, 0};
    }

    private static void createRandomMask26_8() {
        _randomMask26_8 = new byte[]{ByteCompanionObject.MIN_VALUE, -84, 5, 64, 0, 0, 48, -111, -124, ByteCompanionObject.MIN_VALUE, 0, 0, 22, 8, -80, 64, 0, 0, 3, 48, 25, ByteCompanionObject.MIN_VALUE, 0, 0, 68, 98, 35, 0, 0, 0, 8, 24, 64, -64, 0, 0, -40, 6, -64, 0, 0, 0, -95, 69, 10, 0, 0, 0};
    }

    private static void createRandomMask26_9() {
        _randomMask26_9 = new byte[]{89, 34, -55, 0, 0, 0, 102, 67, 50, 0, 0, 0, 20, 64, -94, 0, 0, 0, 33, 73, 10, 64, 0, 0, 2, -56, 22, 64, 0, 0, -108, 20, -96, ByteCompanionObject.MIN_VALUE, 0, 0, ByteCompanionObject.MIN_VALUE, -84, 5, 64, 0, 0, 10, -112, 84, ByteCompanionObject.MIN_VALUE, 0, 0, 64, 26, 0, -64, 0, 0};
    }

    private static void createRandomMask27_1() {
        _randomMask27_1 = new byte[]{-1, -1, -1, -32, 0, 0};
    }

    private static void createRandomMask27_10() {
        _randomMask27_10 = new byte[]{-47, 6, 6, -96, 0, 0, 68, 80, -22, 0, 0, 0, Tnaf.POW_2_WIDTH, -98, -96, 64, 0, 0, -96, 80, 19, 0, 0, 0, 74, 8, 33, 64, 0, 0, 64, 49, 4, -64, 0, 0, ByteCompanionObject.MIN_VALUE, 42, 2, 32, 0, 0, 12, -112, 68, 32, 0, 0, 5, -117, 64, 0, 0, 0, 98, 33, 24, ByteCompanionObject.MIN_VALUE, 0, 0};
    }

    private static void createRandomMask27_11() {
        _randomMask27_11 = new byte[]{81, 35, 22, ByteCompanionObject.MIN_VALUE, 0, 0, 34, 17, -87, 0, 0, 0, 19, 64, -96, -96, 0, 0, 37, 6, 40, 64, 0, 0, 24, 25, Tnaf.POW_2_WIDTH, 96, 0, 0, 10, 36, 69, -64, 0, 0, -120, -118, 18, -96, 0, 0, 6, -127, 69, 32, 0, 0, -32, 36, -95, 0, 0, 0, -124, 64, -40, 32, 0, 0, 68, 25, 22, 0, 0, 0};
    }

    private static void createRandomMask27_12() {
        _randomMask27_12 = new byte[]{40, 44, 8, 32, 0, 0, -124, 82, 3, 64, 0, 0, 96, 68, -127, 32, 0, 0, 5, 73, 65, 64, 0, 0, 2, -102, -112, ByteCompanionObject.MIN_VALUE, 0, 0, 1, 50, 12, 64, 0, 0, 72, Tnaf.POW_2_WIDTH, 73, ByteCompanionObject.MIN_VALUE, 0, 0, 36, -126, 66, 32, 0, 0, -108, 0, 34, 32, 0, 0, -118, 0, 116, 0, 0, 0, 17, -123, 44, ByteCompanionObject.MIN_VALUE, 0, 0, 82, 32, -112, 96, 0, 0};
    }

    private static void createRandomMask27_13() {
        _randomMask27_13 = new byte[]{81, 35, 18, -96, 0, 0, 102, 65, -93, 0, 0, 0, 5, 74, 64, 32, 0, 0, -127, 32, 5, 96, 0, 0, -108, 1, 64, 64, 0, 0, 48, -124, 8, 64, 0, 0, 33, 17, 24, 32, 0, 0, 3, -64, 52, 0, 0, 0, -24, 4, 0, -96, 0, 0, 10, 17, ByteCompanionObject.MIN_VALUE, ByteCompanionObject.MIN_VALUE, 0, 0, ByteCompanionObject.MIN_VALUE, 28, 97, 0, 0, 0, 4, -110, -96, 0, 0, 0, 8, -84, 6, 0, 0, 0};
    }

    private static void createRandomMask27_14() {
        _randomMask27_14 = new byte[]{89, 35, 18, -96, 0, 0, 38, 85, -55, 0, 0, 0, -79, 64, -59, -96, 0, 0, 43, 10, -92, -64, 0, 0, 20, -56, 51, 96, 0, 0, -56, -116, 42, -96, 0, 0, -124, -75, 84, 64, 0, 0, -47, 34, 82, -96, 0, 0, 70, -44, -86, 64, 0, 0, 21, 72, -91, -96, 0, 0, 33, 114, -115, 64, 0, 0, 40, -55, 19, 96, 0, 0, -86, 36, 68, 96, 0, 0, 10, -25, 59, 32, 0, 0};
    }

    private static void createRandomMask27_15() {
        _randomMask27_15 = new byte[]{89, 35, 18, -96, 0, 0, 38, 85, -55, 0, 0, 0, -79, 64, -59, -96, 0, 0, 43, 10, -92, -64, 0, 0, 20, -56, 51, 96, 0, 0, -56, -116, 42, -96, 0, 0, -124, -75, 84, 64, 0, 0, ByteCompanionObject.MIN_VALUE, -82, 0, -96, 0, 0, 48, -110, 11, 0, 0, 0, 22, 12, 65, ByteCompanionObject.MIN_VALUE, 0, 0, 3, 49, 5, 32, 0, 0, 68, 96, 82, 64, 0, 0, 8, 24, 36, -64, 0, 0, -40, 4, -94, 0, 0, 0, -95, 67, -112, 0, 0, 0};
    }

    private static void createRandomMask27_16() {
        _randomMask27_16 = new byte[]{ByteCompanionObject.MIN_VALUE, -82, 0, -96, 0, 0, 48, -110, 11, 0, 0, 0, 22, 12, 65, ByteCompanionObject.MIN_VALUE, 0, 0, 3, 49, 5, 32, 0, 0, 68, 96, 82, 64, 0, 0, 8, 24, 36, -64, 0, 0, -40, 4, -94, 0, 0, 0, -95, 67, -112, 0, 0, 0, 89, 35, 18, -96, 0, 0, 38, 85, -55, 0, 0, 0, -79, 64, -59, -96, 0, 0, 43, 10, -92, -64, 0, 0, 20, -56, 51, 96, 0, 0, -56, -116, 42, -96, 0, 0, -124, -75, 84, 64, 0, 0, 1, 80, -5, -32, 0, 0};
    }

    private static void createRandomMask27_17() {
        _randomMask27_17 = new byte[]{ByteCompanionObject.MIN_VALUE, -82, 0, -96, 0, 0, 48, -110, 11, 0, 0, 0, 22, 12, 65, ByteCompanionObject.MIN_VALUE, 0, 0, 3, 49, 5, 32, 0, 0, 68, 96, 82, 64, 0, 0, 8, 24, 36, -64, 0, 0, -40, 4, -94, 0, 0, 0, -95, 67, -112, 0, 0, 0, 89, 37, 18, -96, 0, 0, 102, 65, -93, 0, 0, 0, 20, 66, 81, 32, 0, 0, 33, 73, 5, 64, 0, 0, 2, -56, -116, 32, 0, 0, -108, 18, 72, 64, 0, 0, ByteCompanionObject.MIN_VALUE, -84, 48, 96, 0, 0, 10, -111, 6, -96, 0, 0, 64, 28, 66, 64, 0, 0};
    }

    private static void createRandomMask27_18() {
        _randomMask27_18 = new byte[]{89, 37, 18, -96, 0, 0, 102, 65, -93, 0, 0, 0, 20, 66, 81, 32, 0, 0, 33, 73, 5, 64, 0, 0, 2, -56, -116, 32, 0, 0, -108, 18, 72, 64, 0, 0, ByteCompanionObject.MIN_VALUE, -84, 48, 96, 0, 0, 10, -111, 6, -96, 0, 0, 64, 28, 66, 64, 0, 0, ByteCompanionObject.MIN_VALUE, -82, 0, -96, 0, 0, 48, -110, 11, 0, 0, 0, 22, 12, 65, ByteCompanionObject.MIN_VALUE, 0, 0, 3, 49, 5, 32, 0, 0, 68, 96, 82, 64, 0, 0, 8, 24, 36, -64, 0, 0, -40, 4, -94, 0, 0, 0, -95, 67, -112, 0, 0, 0, 83, -61, 51, ByteCompanionObject.MIN_VALUE, 0, 0};
    }

    private static void createRandomMask27_19() {
        _randomMask27_19 = new byte[]{89, 37, 18, -96, 0, 0, 102, 65, -93, 0, 0, 0, 20, 66, 81, 32, 0, 0, 33, 73, 5, 64, 0, 0, 2, -56, -116, 32, 0, 0, -108, 18, 72, 64, 0, 0, ByteCompanionObject.MIN_VALUE, -84, 48, 96, 0, 0, 10, -111, 6, -96, 0, 0, 64, 28, 66, 64, 0, 0, -47, 6, 6, -96, 0, 0, 68, 80, -22, 0, 0, 0, Tnaf.POW_2_WIDTH, -98, -96, 64, 0, 0, -96, 80, 19, 0, 0, 0, 74, 8, 33, 64, 0, 0, 64, 49, 4, -64, 0, 0, ByteCompanionObject.MIN_VALUE, 42, 2, 32, 0, 0, 12, -112, 68, 32, 0, 0, 5, -117, 64, 0, 0, 0, 98, 33, 24, ByteCompanionObject.MIN_VALUE, 0, 0};
    }

    private static void createRandomMask27_2() {
        _randomMask27_2 = new byte[]{-20, -57, 103, 64, 0, 0, 27, 57, -36, -32, 0, 0};
    }

    private static void createRandomMask27_20() {
        _randomMask27_20 = new byte[]{-47, 6, 6, -96, 0, 0, 68, 80, -22, 0, 0, 0, Tnaf.POW_2_WIDTH, -98, -96, 64, 0, 0, -96, 80, 19, 0, 0, 0, 74, 8, 33, 64, 0, 0, 64, 49, 4, -64, 0, 0, ByteCompanionObject.MIN_VALUE, 42, 2, 32, 0, 0, 12, -112, 68, 32, 0, 0, 5, -117, 64, 0, 0, 0, 98, 33, 24, ByteCompanionObject.MIN_VALUE, 0, 0, 89, 37, 18, -96, 0, 0, 102, 65, -93, 0, 0, 0, 20, 66, 81, 32, 0, 0, 33, 73, 5, 64, 0, 0, 2, -56, -116, 32, 0, 0, -108, 18, 72, 64, 0, 0, ByteCompanionObject.MIN_VALUE, -84, 48, 96, 0, 0, 10, -111, 6, -96, 0, 0, 64, 28, 66, 64, 0, 0, -53, -1, 111, -64, 0, 0};
    }

    private static void createRandomMask27_21() {
        _randomMask27_21 = new byte[]{-47, 6, 6, -96, 0, 0, 68, 80, -22, 0, 0, 0, Tnaf.POW_2_WIDTH, -98, -96, 64, 0, 0, -96, 80, 19, 0, 0, 0, 74, 8, 33, 64, 0, 0, 64, 49, 4, -64, 0, 0, ByteCompanionObject.MIN_VALUE, 42, 2, 32, 0, 0, 12, -112, 68, 32, 0, 0, 5, -117, 64, 0, 0, 0, 98, 33, 24, ByteCompanionObject.MIN_VALUE, 0, 0, 81, 35, 22, ByteCompanionObject.MIN_VALUE, 0, 0, 34, 17, -87, 0, 0, 0, 19, 64, -96, -96, 0, 0, 37, 6, 40, 64, 0, 0, 24, 25, Tnaf.POW_2_WIDTH, 96, 0, 0, 10, 36, 69, -64, 0, 0, -120, -118, 18, -96, 0, 0, 6, -127, 69, 32, 0, 0, -32, 36, -95, 0, 0, 0, -124, 64, -40, 32, 0, 0, 68, 25, 22, 0, 0, 0};
    }

    private static void createRandomMask27_22() {
        _randomMask27_22 = new byte[]{81, 35, 22, ByteCompanionObject.MIN_VALUE, 0, 0, 34, 17, -87, 0, 0, 0, 19, 64, -96, -96, 0, 0, 37, 6, 40, 64, 0, 0, 24, 25, Tnaf.POW_2_WIDTH, 96, 0, 0, 10, 36, 69, -64, 0, 0, -120, -118, 18, -96, 0, 0, 6, -127, 69, 32, 0, 0, -32, 36, -95, 0, 0, 0, -124, 64, -40, 32, 0, 0, 68, 25, 22, 0, 0, 0, -47, 6, 6, -96, 0, 0, 68, 80, -22, 0, 0, 0, Tnaf.POW_2_WIDTH, -98, -96, 64, 0, 0, -96, 80, 19, 0, 0, 0, 74, 8, 33, 64, 0, 0, 64, 49, 4, -64, 0, 0, ByteCompanionObject.MIN_VALUE, 42, 2, 32, 0, 0, 12, -112, 68, 32, 0, 0, 5, -117, 64, 0, 0, 0, 98, 33, 24, ByteCompanionObject.MIN_VALUE, 0, 0, -11, 45, 82, 64, 0, 0};
    }

    private static void createRandomMask27_23() {
        _randomMask27_23 = new byte[]{81, 35, 22, ByteCompanionObject.MIN_VALUE, 0, 0, 34, 17, -87, 0, 0, 0, 19, 64, -96, -96, 0, 0, 37, 6, 40, 64, 0, 0, 24, 25, Tnaf.POW_2_WIDTH, 96, 0, 0, 10, 36, 69, -64, 0, 0, -120, -118, 18, -96, 0, 0, 6, -127, 69, 32, 0, 0, -32, 36, -95, 0, 0, 0, -124, 64, -40, 32, 0, 0, 68, 25, 22, 0, 0, 0, 40, 44, 8, 32, 0, 0, -124, 82, 3, 64, 0, 0, 96, 68, -127, 32, 0, 0, 5, 73, 65, 64, 0, 0, 2, -102, -112, ByteCompanionObject.MIN_VALUE, 0, 0, 1, 50, 12, 64, 0, 0, 72, Tnaf.POW_2_WIDTH, 73, ByteCompanionObject.MIN_VALUE, 0, 0, 36, -126, 66, 32, 0, 0, -108, 0, 34, 32, 0, 0, -118, 0, 116, 0, 0, 0, 17, -123, 44, ByteCompanionObject.MIN_VALUE, 0, 0, 82, 32, -112, 96, 0, 0};
    }

    private static void createRandomMask27_24() {
        _randomMask27_24 = new byte[]{40, 44, 8, 32, 0, 0, -124, 82, 3, 64, 0, 0, 96, 68, -127, 32, 0, 0, 5, 73, 65, 64, 0, 0, 2, -102, -112, ByteCompanionObject.MIN_VALUE, 0, 0, 1, 50, 12, 64, 0, 0, 72, Tnaf.POW_2_WIDTH, 73, ByteCompanionObject.MIN_VALUE, 0, 0, 36, -126, 66, 32, 0, 0, -108, 0, 34, 32, 0, 0, -118, 0, 116, 0, 0, 0, 17, -123, 44, ByteCompanionObject.MIN_VALUE, 0, 0, 82, 32, -112, 96, 0, 0, 81, 35, 22, ByteCompanionObject.MIN_VALUE, 0, 0, 34, 17, -87, 0, 0, 0, 19, 64, -96, -96, 0, 0, 37, 6, 40, 64, 0, 0, 24, 25, Tnaf.POW_2_WIDTH, 96, 0, 0, 10, 36, 69, -64, 0, 0, -120, -118, 18, -96, 0, 0, 6, -127, 69, 32, 0, 0, -32, 36, -95, 0, 0, 0, -124, 64, -40, 32, 0, 0, 68, 25, 22, 0, 0, 0, -94, -123, -37, -96, 0, 0};
    }

    private static void createRandomMask27_25() {
        _randomMask27_25 = new byte[]{40, 44, 8, 32, 0, 0, -124, 82, 3, 64, 0, 0, 96, 68, -127, 32, 0, 0, 5, 73, 65, 64, 0, 0, 2, -102, -112, ByteCompanionObject.MIN_VALUE, 0, 0, 1, 50, 12, 64, 0, 0, 72, Tnaf.POW_2_WIDTH, 73, ByteCompanionObject.MIN_VALUE, 0, 0, 36, -126, 66, 32, 0, 0, -108, 0, 34, 32, 0, 0, -118, 0, 116, 0, 0, 0, 17, -123, 44, ByteCompanionObject.MIN_VALUE, 0, 0, 82, 32, -112, 96, 0, 0, 81, 35, 18, -96, 0, 0, 102, 65, -93, 0, 0, 0, 5, 74, 64, 32, 0, 0, -127, 32, 5, 96, 0, 0, -108, 1, 64, 64, 0, 0, 48, -124, 8, 64, 0, 0, 33, 17, 24, 32, 0, 0, 3, -64, 52, 0, 0, 0, -24, 4, 0, -96, 0, 0, 10, 17, ByteCompanionObject.MIN_VALUE, ByteCompanionObject.MIN_VALUE, 0, 0, ByteCompanionObject.MIN_VALUE, 28, 97, 0, 0, 0, 4, -110, -96, 0, 0, 0, 8, -84, 6, 0, 0, 0};
    }

    private static void createRandomMask27_26() {
        _randomMask27_26 = new byte[]{81, 35, 18, -96, 0, 0, 102, 65, -93, 0, 0, 0, 5, 74, 64, 32, 0, 0, -127, 32, 5, 96, 0, 0, -108, 1, 64, 64, 0, 0, 48, -124, 8, 64, 0, 0, 33, 17, 24, 32, 0, 0, 3, -64, 52, 0, 0, 0, -24, 4, 0, -96, 0, 0, 10, 17, ByteCompanionObject.MIN_VALUE, ByteCompanionObject.MIN_VALUE, 0, 0, ByteCompanionObject.MIN_VALUE, 28, 97, 0, 0, 0, 4, -110, -96, 0, 0, 0, 8, -84, 6, 0, 0, 0, 40, 44, 8, 32, 0, 0, -124, 82, 3, 64, 0, 0, 96, 68, -127, 32, 0, 0, 5, 73, 65, 64, 0, 0, 2, -102, -112, ByteCompanionObject.MIN_VALUE, 0, 0, 1, 50, 12, 64, 0, 0, 72, Tnaf.POW_2_WIDTH, 73, ByteCompanionObject.MIN_VALUE, 0, 0, 36, -126, 66, 32, 0, 0, -108, 0, 34, 32, 0, 0, -118, 0, 116, 0, 0, 0, 17, -123, 44, ByteCompanionObject.MIN_VALUE, 0, 0, 82, 32, -112, 96, 0, 0, -51, 65, -94, 64, 0, 0};
    }

    private static void createRandomMask27_27() {
        _randomMask27_27 = new byte[]{81, 35, 18, -96, 0, 0, 102, 65, -93, 0, 0, 0, 5, 74, 64, 32, 0, 0, -127, 32, 5, 96, 0, 0, -108, 1, 64, 64, 0, 0, 48, -124, 8, 64, 0, 0, 33, 17, 24, 32, 0, 0, 3, -64, 52, 0, 0, 0, -24, 4, 0, -96, 0, 0, 10, 17, ByteCompanionObject.MIN_VALUE, ByteCompanionObject.MIN_VALUE, 0, 0, ByteCompanionObject.MIN_VALUE, 28, 97, 0, 0, 0, 4, -110, -96, 0, 0, 0, 8, -84, 6, 0, 0, 0, 81, 34, 2, -96, 0, 0, 102, 64, -86, 0, 0, 0, 5, 78, 0, 32, 0, 0, -127, 33, 64, ByteCompanionObject.MIN_VALUE, 0, 0, -108, 0, 40, 96, 0, 0, 48, -125, 36, 0, 0, 0, 33, 20, 12, 0, 0, 0, 3, -64, -124, -64, 0, 0, -24, 4, 33, 0, 0, 0, 10, Tnaf.POW_2_WIDTH, -111, ByteCompanionObject.MIN_VALUE, 0, 0, ByteCompanionObject.MIN_VALUE, 27, Tnaf.POW_2_WIDTH, 0, 0, 0, 4, -111, 67, 0, 0, 0, 8, -88, 112, 64, 0, 0, -100, -64, -124, 32, 0, 0};
    }

    private static void createRandomMask27_3() {
        _randomMask27_3 = new byte[]{-103, -75, 102, -64, 0, 0, 70, -38, -85, 96, 0, 0, 55, 41, 61, -96, 0, 0};
    }

    private static void createRandomMask27_4() {
        _randomMask27_4 = new byte[]{73, -79, 102, -64, 0, 0, 38, -44, -101, 64, 0, 0, -123, 104, -43, -96, 0, 0, 82, 90, 57, 96, 0, 0};
    }

    private static void createRandomMask27_5() {
        _randomMask27_5 = new byte[]{81, 51, 38, -64, 0, 0, 102, 69, 43, 64, 0, 0, 12, 106, -107, -96, 0, 0, -95, -64, -19, 64, 0, 0, 34, -100, -30, -96, 0, 0};
    }

    private static void createRandomMask27_6() {
        _randomMask27_6 = new byte[]{-47, 34, 82, -96, 0, 0, 70, -44, -86, 64, 0, 0, 21, 72, -91, -96, 0, 0, 33, 114, -115, 64, 0, 0, 40, -55, 19, 96, 0, 0, -86, 36, 68, 96, 0, 0};
    }

    private static void createRandomMask27_7() {
        _randomMask27_7 = new byte[]{89, 35, 18, -96, 0, 0, 38, 85, -55, 0, 0, 0, -79, 64, -59, -96, 0, 0, 43, 10, -92, -64, 0, 0, 20, -56, 51, 96, 0, 0, -56, -116, 42, -96, 0, 0, -124, -75, 84, 64, 0, 0};
    }

    private static void createRandomMask27_8() {
        _randomMask27_8 = new byte[]{ByteCompanionObject.MIN_VALUE, -82, 0, -96, 0, 0, 48, -110, 11, 0, 0, 0, 22, 12, 65, ByteCompanionObject.MIN_VALUE, 0, 0, 3, 49, 5, 32, 0, 0, 68, 96, 82, 64, 0, 0, 8, 24, 36, -64, 0, 0, -40, 4, -94, 0, 0, 0, -95, 67, -112, 0, 0, 0};
    }

    private static void createRandomMask27_9() {
        _randomMask27_9 = new byte[]{89, 37, 18, -96, 0, 0, 102, 65, -93, 0, 0, 0, 20, 66, 81, 32, 0, 0, 33, 73, 5, 64, 0, 0, 2, -56, -116, 32, 0, 0, -108, 18, 72, 64, 0, 0, ByteCompanionObject.MIN_VALUE, -84, 48, 96, 0, 0, 10, -111, 6, -96, 0, 0, 64, 28, 66, 64, 0, 0};
    }

    private static void createRandomMask28_1() {
        _randomMask28_1 = new byte[]{-1, -1, -1, -16, 0, 0};
    }

    private static void createRandomMask28_10() {
        _randomMask28_10 = new byte[]{-64, -41, 3, 80, 0, 0, 29, 64, 117, 0, 0, 0, -44, 11, 80, 32, 0, 0, 2, 96, 9, ByteCompanionObject.MIN_VALUE, 0, 0, 4, 40, Tnaf.POW_2_WIDTH, -96, 0, 0, 32, -104, -126, 96, 0, 0, 64, 69, 1, Tnaf.POW_2_WIDTH, 0, 0, 8, -124, 34, Tnaf.POW_2_WIDTH, 0, 0, 104, 1, -96, 0, 0, 0, 35, Tnaf.POW_2_WIDTH, -116, 64, 0, 0};
    }

    private static void createRandomMask28_11() {
        _randomMask28_11 = new byte[]{98, -47, -117, 64, 0, 0, 53, 32, -44, ByteCompanionObject.MIN_VALUE, 0, 0, 20, 20, 80, 80, 0, 0, -59, 11, 20, 32, 0, 0, 34, 12, -120, 48, 0, 0, -120, -70, 34, -32, 0, 0, 66, 85, 9, 80, 0, 0, 40, -92, -94, -112, 0, 0, -108, 34, 80, ByteCompanionObject.MIN_VALUE, 0, 0, 27, 4, 108, Tnaf.POW_2_WIDTH, 0, 0, 34, -64, -117, 0, 0, 0};
    }

    private static void createRandomMask28_12() {
        _randomMask28_12 = new byte[]{-127, 6, 4, Tnaf.POW_2_WIDTH, 0, 0, 64, 105, 1, -96, 0, 0, -112, 38, 64, -112, 0, 0, 40, 40, -96, -96, 0, 0, 82, 17, 72, 64, 0, 0, 65, -119, 6, 32, 0, 0, 9, 48, 36, -64, 0, 0, 72, 69, 33, Tnaf.POW_2_WIDTH, 0, 0, 4, 68, 17, Tnaf.POW_2_WIDTH, 0, 0, 14, ByteCompanionObject.MIN_VALUE, 58, 0, 0, 0, -91, -110, -106, 64, 0, 0, 18, 12, 72, 48, 0, 0};
    }

    private static void createRandomMask28_13() {
        _randomMask28_13 = new byte[]{98, 85, -119, 80, 0, 0, 52, 96, -47, ByteCompanionObject.MIN_VALUE, 0, 0, 72, 5, 32, Tnaf.POW_2_WIDTH, 0, 0, 0, -84, 2, -80, 0, 0, 40, 8, -96, 32, 0, 0, -127, 10, 4, 32, 0, 0, 35, 4, -116, Tnaf.POW_2_WIDTH, 0, 0, 6, ByteCompanionObject.MIN_VALUE, 26, 0, 0, 0, ByteCompanionObject.MIN_VALUE, 22, 0, 80, 0, 0, 48, Tnaf.POW_2_WIDTH, -64, 64, 0, 0, -116, 34, 48, ByteCompanionObject.MIN_VALUE, 0, 0, 84, 1, 80, 0, 0, 0, ByteCompanionObject.MIN_VALUE, -62, 3, 0, 0, 0};
    }

    private static void createRandomMask28_14() {
        _randomMask28_14 = new byte[]{64, 85, 1, 80, 0, 0, 21, 64, 85, 0, 0, 0, -64, 7, 0, Tnaf.POW_2_WIDTH, 0, 0, 40, Tnaf.POW_2_WIDTH, -96, 64, 0, 0, 5, 12, 20, 48, 0, 0, 100, -127, -110, 0, 0, 0, -127, -126, 6, 0, 0, 0, Tnaf.POW_2_WIDTH, -104, 66, 96, 0, 0, -124, 34, Tnaf.POW_2_WIDTH, ByteCompanionObject.MIN_VALUE, 0, 0, 18, 48, 72, -64, 0, 0, 98, 1, -120, 0, 0, 0, 40, 96, -95, ByteCompanionObject.MIN_VALUE, 0, 0, 14, 8, 56, 32, 0, 0, Tnaf.POW_2_WIDTH, -124, 66, Tnaf.POW_2_WIDTH, 0, 0};
    }

    private static void createRandomMask28_15() {
        _randomMask28_15 = new byte[]{98, 85, -119, 80, 0, 0, -71, 34, -28, ByteCompanionObject.MIN_VALUE, 0, 0, 24, -76, 98, -48, 0, 0, 84, -103, 82, 96, 0, 0, 6, 108, 25, -80, 0, 0, -123, 86, 21, 80, 0, 0, -86, -118, -86, 32, 0, 0, -64, 23, 0, 80, 0, 0, 65, 97, 5, ByteCompanionObject.MIN_VALUE, 0, 0, -120, 50, 32, -64, 0, 0, 32, -92, -126, -112, 0, 0, 10, 72, 41, 32, 0, 0, 4, -104, 18, 96, 0, 0, -108, 66, 81, 0, 0, 0, 114, 1, -56, 0, 0, 0};
    }

    private static void createRandomMask28_16() {
        _randomMask28_16 = new byte[]{-64, 23, 0, 80, 0, 0, 65, 97, 5, ByteCompanionObject.MIN_VALUE, 0, 0, -120, 50, 32, -64, 0, 0, 32, -92, -126, -112, 0, 0, 10, 72, 41, 32, 0, 0, 4, -104, 18, 96, 0, 0, -108, 66, 81, 0, 0, 0, 114, 1, -56, 0, 0, 0, 98, 85, -119, 80, 0, 0, -71, 34, -28, ByteCompanionObject.MIN_VALUE, 0, 0, 24, -76, 98, -48, 0, 0, 84, -103, 82, 96, 0, 0, 6, 108, 25, -80, 0, 0, -123, 86, 21, 80, 0, 0, -86, -118, -86, 32, 0, 0, -19, 118, 54, 80, 0, 0};
    }

    private static void createRandomMask28_17() {
        _randomMask28_17 = new byte[]{-64, 23, 0, 80, 0, 0, 65, 97, 5, ByteCompanionObject.MIN_VALUE, 0, 0, -120, 50, 32, -64, 0, 0, 32, -92, -126, -112, 0, 0, 10, 72, 41, 32, 0, 0, 4, -104, 18, 96, 0, 0, -108, 66, 81, 0, 0, 0, 114, 1, -56, 0, 0, 0, -94, 86, -119, 80, 0, 0, 52, 96, -47, ByteCompanionObject.MIN_VALUE, 0, 0, 74, 37, 40, -112, 0, 0, 32, -88, -126, -96, 0, 0, 17, -124, 70, Tnaf.POW_2_WIDTH, 0, 0, 73, 9, 36, 32, 0, 0, -122, 14, 24, 48, 0, 0, 32, -44, -125, 80, 0, 0, -120, 74, 33, 32, 0, 0};
    }

    private static void createRandomMask28_18() {
        _randomMask28_18 = new byte[]{-94, 86, -119, 80, 0, 0, 52, 96, -47, ByteCompanionObject.MIN_VALUE, 0, 0, 74, 37, 40, -112, 0, 0, 32, -88, -126, -96, 0, 0, 17, -124, 70, Tnaf.POW_2_WIDTH, 0, 0, 73, 9, 36, 32, 0, 0, -122, 14, 24, 48, 0, 0, 32, -44, -125, 80, 0, 0, -120, 74, 33, 32, 0, 0, -64, 23, 0, 80, 0, 0, 65, 97, 5, ByteCompanionObject.MIN_VALUE, 0, 0, -120, 50, 32, -64, 0, 0, 32, -92, -126, -112, 0, 0, 10, 72, 41, 32, 0, 0, 4, -104, 18, 96, 0, 0, -108, 66, 81, 0, 0, 0, 114, 1, -56, 0, 0, 0, 110, -97, -104, Tnaf.POW_2_WIDTH, 0, 0};
    }

    private static void createRandomMask28_19() {
        _randomMask28_19 = new byte[]{-94, 86, -119, 80, 0, 0, 52, 96, -47, ByteCompanionObject.MIN_VALUE, 0, 0, 74, 37, 40, -112, 0, 0, 32, -88, -126, -96, 0, 0, 17, -124, 70, Tnaf.POW_2_WIDTH, 0, 0, 73, 9, 36, 32, 0, 0, -122, 14, 24, 48, 0, 0, 32, -44, -125, 80, 0, 0, -120, 74, 33, 32, 0, 0, -64, -41, 3, 80, 0, 0, 29, 64, 117, 0, 0, 0, -44, 11, 80, 32, 0, 0, 2, 96, 9, ByteCompanionObject.MIN_VALUE, 0, 0, 4, 40, Tnaf.POW_2_WIDTH, -96, 0, 0, 32, -104, -126, 96, 0, 0, 64, 69, 1, Tnaf.POW_2_WIDTH, 0, 0, 8, -124, 34, Tnaf.POW_2_WIDTH, 0, 0, 104, 1, -96, 0, 0, 0, 35, Tnaf.POW_2_WIDTH, -116, 64, 0, 0};
    }

    private static void createRandomMask28_2() {
        _randomMask28_2 = new byte[]{-20, -21, -77, -96, 0, 0, 59, -100, -18, 112, 0, 0};
    }

    private static void createRandomMask28_20() {
        _randomMask28_20 = new byte[]{-64, -41, 3, 80, 0, 0, 29, 64, 117, 0, 0, 0, -44, 11, 80, 32, 0, 0, 2, 96, 9, ByteCompanionObject.MIN_VALUE, 0, 0, 4, 40, Tnaf.POW_2_WIDTH, -96, 0, 0, 32, -104, -126, 96, 0, 0, 64, 69, 1, Tnaf.POW_2_WIDTH, 0, 0, 8, -124, 34, Tnaf.POW_2_WIDTH, 0, 0, 104, 1, -96, 0, 0, 0, 35, Tnaf.POW_2_WIDTH, -116, 64, 0, 0, -94, 86, -119, 80, 0, 0, 52, 96, -47, ByteCompanionObject.MIN_VALUE, 0, 0, 74, 37, 40, -112, 0, 0, 32, -88, -126, -96, 0, 0, 17, -124, 70, Tnaf.POW_2_WIDTH, 0, 0, 73, 9, 36, 32, 0, 0, -122, 14, 24, 48, 0, 0, 32, -44, -125, 80, 0, 0, -120, 74, 33, 32, 0, 0, -22, 27, 58, Tnaf.POW_2_WIDTH, 0, 0};
    }

    private static void createRandomMask28_21() {
        _randomMask28_21 = new byte[]{-64, -41, 3, 80, 0, 0, 29, 64, 117, 0, 0, 0, -44, 11, 80, 32, 0, 0, 2, 96, 9, ByteCompanionObject.MIN_VALUE, 0, 0, 4, 40, Tnaf.POW_2_WIDTH, -96, 0, 0, 32, -104, -126, 96, 0, 0, 64, 69, 1, Tnaf.POW_2_WIDTH, 0, 0, 8, -124, 34, Tnaf.POW_2_WIDTH, 0, 0, 104, 1, -96, 0, 0, 0, 35, Tnaf.POW_2_WIDTH, -116, 64, 0, 0, 98, -47, -117, 64, 0, 0, 53, 32, -44, ByteCompanionObject.MIN_VALUE, 0, 0, 20, 20, 80, 80, 0, 0, -59, 11, 20, 32, 0, 0, 34, 12, -120, 48, 0, 0, -120, -70, 34, -32, 0, 0, 66, 85, 9, 80, 0, 0, 40, -92, -94, -112, 0, 0, -108, 34, 80, ByteCompanionObject.MIN_VALUE, 0, 0, 27, 4, 108, Tnaf.POW_2_WIDTH, 0, 0, 34, -64, -117, 0, 0, 0};
    }

    private static void createRandomMask28_22() {
        _randomMask28_22 = new byte[]{98, -47, -117, 64, 0, 0, 53, 32, -44, ByteCompanionObject.MIN_VALUE, 0, 0, 20, 20, 80, 80, 0, 0, -59, 11, 20, 32, 0, 0, 34, 12, -120, 48, 0, 0, -120, -70, 34, -32, 0, 0, 66, 85, 9, 80, 0, 0, 40, -92, -94, -112, 0, 0, -108, 34, 80, ByteCompanionObject.MIN_VALUE, 0, 0, 27, 4, 108, Tnaf.POW_2_WIDTH, 0, 0, 34, -64, -117, 0, 0, 0, -64, -41, 3, 80, 0, 0, 29, 64, 117, 0, 0, 0, -44, 11, 80, 32, 0, 0, 2, 96, 9, ByteCompanionObject.MIN_VALUE, 0, 0, 4, 40, Tnaf.POW_2_WIDTH, -96, 0, 0, 32, -104, -126, 96, 0, 0, 64, 69, 1, Tnaf.POW_2_WIDTH, 0, 0, 8, -124, 34, Tnaf.POW_2_WIDTH, 0, 0, 104, 1, -96, 0, 0, 0, 35, Tnaf.POW_2_WIDTH, -116, 64, 0, 0, 69, 5, Tnaf.POW_2_WIDTH, 0, 0, 0};
    }

    private static void createRandomMask28_23() {
        _randomMask28_23 = new byte[]{98, -47, -117, 64, 0, 0, 53, 32, -44, ByteCompanionObject.MIN_VALUE, 0, 0, 20, 20, 80, 80, 0, 0, -59, 11, 20, 32, 0, 0, 34, 12, -120, 48, 0, 0, -120, -70, 34, -32, 0, 0, 66, 85, 9, 80, 0, 0, 40, -92, -94, -112, 0, 0, -108, 34, 80, ByteCompanionObject.MIN_VALUE, 0, 0, 27, 4, 108, Tnaf.POW_2_WIDTH, 0, 0, 34, -64, -117, 0, 0, 0, -127, 6, 4, Tnaf.POW_2_WIDTH, 0, 0, 64, 105, 1, -96, 0, 0, -112, 38, 64, -112, 0, 0, 40, 40, -96, -96, 0, 0, 82, 17, 72, 64, 0, 0, 65, -119, 6, 32, 0, 0, 9, 48, 36, -64, 0, 0, 72, 69, 33, Tnaf.POW_2_WIDTH, 0, 0, 4, 68, 17, Tnaf.POW_2_WIDTH, 0, 0, 14, ByteCompanionObject.MIN_VALUE, 58, 0, 0, 0, -91, -110, -106, 64, 0, 0, 18, 12, 72, 48, 0, 0};
    }

    private static void createRandomMask28_24() {
        _randomMask28_24 = new byte[]{-127, 6, 4, Tnaf.POW_2_WIDTH, 0, 0, 64, 105, 1, -96, 0, 0, -112, 38, 64, -112, 0, 0, 40, 40, -96, -96, 0, 0, 82, 17, 72, 64, 0, 0, 65, -119, 6, 32, 0, 0, 9, 48, 36, -64, 0, 0, 72, 69, 33, Tnaf.POW_2_WIDTH, 0, 0, 4, 68, 17, Tnaf.POW_2_WIDTH, 0, 0, 14, ByteCompanionObject.MIN_VALUE, 58, 0, 0, 0, -91, -110, -106, 64, 0, 0, 18, 12, 72, 48, 0, 0, 98, -47, -117, 64, 0, 0, 53, 32, -44, ByteCompanionObject.MIN_VALUE, 0, 0, 20, 20, 80, 80, 0, 0, -59, 11, 20, 32, 0, 0, 34, 12, -120, 48, 0, 0, -120, -70, 34, -32, 0, 0, 66, 85, 9, 80, 0, 0, 40, -92, -94, -112, 0, 0, -108, 34, 80, ByteCompanionObject.MIN_VALUE, 0, 0, 27, 4, 108, Tnaf.POW_2_WIDTH, 0, 0, 34, -64, -117, 0, 0, 0, 111, -40, -18, -96, 0, 0};
    }

    private static void createRandomMask28_25() {
        _randomMask28_25 = new byte[]{-127, 6, 4, Tnaf.POW_2_WIDTH, 0, 0, 64, 105, 1, -96, 0, 0, -112, 38, 64, -112, 0, 0, 40, 40, -96, -96, 0, 0, 82, 17, 72, 64, 0, 0, 65, -119, 6, 32, 0, 0, 9, 48, 36, -64, 0, 0, 72, 69, 33, Tnaf.POW_2_WIDTH, 0, 0, 4, 68, 17, Tnaf.POW_2_WIDTH, 0, 0, 14, ByteCompanionObject.MIN_VALUE, 58, 0, 0, 0, -91, -110, -106, 64, 0, 0, 18, 12, 72, 48, 0, 0, 98, 85, -119, 80, 0, 0, 52, 96, -47, ByteCompanionObject.MIN_VALUE, 0, 0, 72, 5, 32, Tnaf.POW_2_WIDTH, 0, 0, 0, -84, 2, -80, 0, 0, 40, 8, -96, 32, 0, 0, -127, 10, 4, 32, 0, 0, 35, 4, -116, Tnaf.POW_2_WIDTH, 0, 0, 6, ByteCompanionObject.MIN_VALUE, 26, 0, 0, 0, ByteCompanionObject.MIN_VALUE, 22, 0, 80, 0, 0, 48, Tnaf.POW_2_WIDTH, -64, 64, 0, 0, -116, 34, 48, ByteCompanionObject.MIN_VALUE, 0, 0, 84, 1, 80, 0, 0, 0, ByteCompanionObject.MIN_VALUE, -62, 3, 0, 0, 0};
    }

    private static void createRandomMask28_26() {
        _randomMask28_26 = new byte[]{98, 85, -119, 80, 0, 0, 52, 96, -47, ByteCompanionObject.MIN_VALUE, 0, 0, 72, 5, 32, Tnaf.POW_2_WIDTH, 0, 0, 0, -84, 2, -80, 0, 0, 40, 8, -96, 32, 0, 0, -127, 10, 4, 32, 0, 0, 35, 4, -116, Tnaf.POW_2_WIDTH, 0, 0, 6, ByteCompanionObject.MIN_VALUE, 26, 0, 0, 0, ByteCompanionObject.MIN_VALUE, 22, 0, 80, 0, 0, 48, Tnaf.POW_2_WIDTH, -64, 64, 0, 0, -116, 34, 48, ByteCompanionObject.MIN_VALUE, 0, 0, 84, 1, 80, 0, 0, 0, ByteCompanionObject.MIN_VALUE, -62, 3, 0, 0, 0, -127, 6, 4, Tnaf.POW_2_WIDTH, 0, 0, 64, 105, 1, -96, 0, 0, -112, 38, 64, -112, 0, 0, 40, 40, -96, -96, 0, 0, 82, 17, 72, 64, 0, 0, 65, -119, 6, 32, 0, 0, 9, 48, 36, -64, 0, 0, 72, 69, 33, Tnaf.POW_2_WIDTH, 0, 0, 4, 68, 17, Tnaf.POW_2_WIDTH, 0, 0, 14, ByteCompanionObject.MIN_VALUE, 58, 0, 0, 0, -91, -110, -106, 64, 0, 0, 18, 12, 72, 48, 0, 0, -15, 100, -66, 64, 0, 0};
    }

    private static void createRandomMask28_27() {
        _randomMask28_27 = new byte[]{98, 85, -119, 80, 0, 0, 52, 96, -47, ByteCompanionObject.MIN_VALUE, 0, 0, 72, 5, 32, Tnaf.POW_2_WIDTH, 0, 0, 0, -84, 2, -80, 0, 0, 40, 8, -96, 32, 0, 0, -127, 10, 4, 32, 0, 0, 35, 4, -116, Tnaf.POW_2_WIDTH, 0, 0, 6, ByteCompanionObject.MIN_VALUE, 26, 0, 0, 0, ByteCompanionObject.MIN_VALUE, 22, 0, 80, 0, 0, 48, Tnaf.POW_2_WIDTH, -64, 64, 0, 0, -116, 34, 48, ByteCompanionObject.MIN_VALUE, 0, 0, 84, 1, 80, 0, 0, 0, ByteCompanionObject.MIN_VALUE, -62, 3, 0, 0, 0, 64, 85, 1, 80, 0, 0, 21, 64, 85, 0, 0, 0, -64, 7, 0, Tnaf.POW_2_WIDTH, 0, 0, 40, Tnaf.POW_2_WIDTH, -96, 64, 0, 0, 5, 12, 20, 48, 0, 0, 100, -127, -110, 0, 0, 0, -127, -126, 6, 0, 0, 0, Tnaf.POW_2_WIDTH, -104, 66, 96, 0, 0, -124, 34, Tnaf.POW_2_WIDTH, ByteCompanionObject.MIN_VALUE, 0, 0, 18, 48, 72, -64, 0, 0, 98, 1, -120, 0, 0, 0, 40, 96, -95, ByteCompanionObject.MIN_VALUE, 0, 0, 14, 8, 56, 32, 0, 0, Tnaf.POW_2_WIDTH, -124, 66, Tnaf.POW_2_WIDTH, 0, 0};
    }

    private static void createRandomMask28_28() {
        _randomMask28_28 = new byte[]{64, 85, 1, 80, 0, 0, 21, 64, 85, 0, 0, 0, -64, 7, 0, Tnaf.POW_2_WIDTH, 0, 0, 40, Tnaf.POW_2_WIDTH, -96, 64, 0, 0, 5, 12, 20, 48, 0, 0, 100, -127, -110, 0, 0, 0, -127, -126, 6, 0, 0, 0, Tnaf.POW_2_WIDTH, -104, 66, 96, 0, 0, -124, 34, Tnaf.POW_2_WIDTH, ByteCompanionObject.MIN_VALUE, 0, 0, 18, 48, 72, -64, 0, 0, 98, 1, -120, 0, 0, 0, 40, 96, -95, ByteCompanionObject.MIN_VALUE, 0, 0, 14, 8, 56, 32, 0, 0, Tnaf.POW_2_WIDTH, -124, 66, Tnaf.POW_2_WIDTH, 0, 0, 98, 85, -119, 80, 0, 0, 52, 96, -47, ByteCompanionObject.MIN_VALUE, 0, 0, 72, 5, 32, Tnaf.POW_2_WIDTH, 0, 0, 0, -84, 2, -80, 0, 0, 40, 8, -96, 32, 0, 0, -127, 10, 4, 32, 0, 0, 35, 4, -116, Tnaf.POW_2_WIDTH, 0, 0, 6, ByteCompanionObject.MIN_VALUE, 26, 0, 0, 0, ByteCompanionObject.MIN_VALUE, 22, 0, 80, 0, 0, 48, Tnaf.POW_2_WIDTH, -64, 64, 0, 0, -116, 34, 48, ByteCompanionObject.MIN_VALUE, 0, 0, 84, 1, 80, 0, 0, 0, ByteCompanionObject.MIN_VALUE, -62, 3, 0, 0, 0, 54, 79, 31, -80, 0, 0};
    }

    private static void createRandomMask28_3() {
        _randomMask28_3 = new byte[]{-84, -38, -77, 96, 0, 0, 85, 109, 85, -80, 0, 0, 39, -76, -98, -48, 0, 0};
    }

    private static void createRandomMask28_4() {
        _randomMask28_4 = new byte[]{44, -40, -77, 96, 0, 0, -109, 106, 77, -96, 0, 0, 26, -76, 106, -48, 0, 0, 71, 45, 28, -80, 0, 0};
    }

    private static void createRandomMask28_5() {
        _randomMask28_5 = new byte[]{100, -39, -109, 96, 0, 0, -91, 106, -107, -96, 0, 0, 82, -75, 74, -48, 0, 0, 29, -88, 118, -96, 0, 0, -100, 86, 113, 80, 0, 0};
    }

    private static void createRandomMask28_6() {
        _randomMask28_6 = new byte[]{74, 85, 41, 80, 0, 0, -107, 74, 85, 32, 0, 0, 20, -76, 82, -48, 0, 0, 81, -87, 70, -96, 0, 0, 34, 108, -119, -80, 0, 0, -120, -114, 34, 48, 0, 0};
    }

    private static void createRandomMask28_7() {
        _randomMask28_7 = new byte[]{98, 85, -119, 80, 0, 0, -71, 34, -28, ByteCompanionObject.MIN_VALUE, 0, 0, 24, -76, 98, -48, 0, 0, 84, -103, 82, 96, 0, 0, 6, 108, 25, -80, 0, 0, -123, 86, 21, 80, 0, 0, -86, -118, -86, 32, 0, 0};
    }

    private static void createRandomMask28_8() {
        _randomMask28_8 = new byte[]{-64, 23, 0, 80, 0, 0, 65, 97, 5, ByteCompanionObject.MIN_VALUE, 0, 0, -120, 50, 32, -64, 0, 0, 32, -92, -126, -112, 0, 0, 10, 72, 41, 32, 0, 0, 4, -104, 18, 96, 0, 0, -108, 66, 81, 0, 0, 0, 114, 1, -56, 0, 0, 0};
    }

    private static void createRandomMask28_9() {
        _randomMask28_9 = new byte[]{-94, 86, -119, 80, 0, 0, 52, 96, -47, ByteCompanionObject.MIN_VALUE, 0, 0, 74, 37, 40, -112, 0, 0, 32, -88, -126, -96, 0, 0, 17, -124, 70, Tnaf.POW_2_WIDTH, 0, 0, 73, 9, 36, 32, 0, 0, -122, 14, 24, 48, 0, 0, 32, -44, -125, 80, 0, 0, -120, 74, 33, 32, 0, 0};
    }

    private static void createRandomMask29_1() {
        _randomMask29_1 = new byte[]{-1, -1, -1, -8, 0, 0};
    }

    private static void createRandomMask29_10() {
        _randomMask29_10 = new byte[]{-64, -41, 2, ByteCompanionObject.MIN_VALUE, 0, 0, 29, 64, 85, 88, 0, 0, -44, 9, -47, 0, 0, 0, 2, 96, 2, 112, 0, 0, 4, 40, 4, -80, 0, 0, 32, -103, 18, 72, 0, 0, 64, 70, 33, 64, 0, 0, 8, -124, -126, -112, 0, 0, 104, 2, -88, Tnaf.POW_2_WIDTH, 0, 0, 35, Tnaf.POW_2_WIDTH, 9, -120, 0, 0};
    }

    private static void createRandomMask29_11() {
        _randomMask29_11 = new byte[]{98, -47, -120, -120, 0, 0, 53, 35, -60, 64, 0, 0, 20, 20, 64, 56, 0, 0, -59, 8, 66, -64, 0, 0, 34, 12, -112, -112, 0, 0, -120, -72, 4, 72, 0, 0, 66, 84, 3, Tnaf.POW_2_WIDTH, 0, 0, 40, -92, 18, -120, 0, 0, -108, 32, 9, 96, 0, 0, 27, 4, -84, 0, 0, 0, 34, -62, 97, 0, 0, 0};
    }

    private static void createRandomMask29_12() {
        _randomMask29_12 = new byte[]{-127, 6, 34, 64, 0, 0, 64, 105, 1, 80, 0, 0, -112, 38, 9, -120, 0, 0, 40, 40, -122, -112, 0, 0, 82, Tnaf.POW_2_WIDTH, 65, -112, 0, 0, 65, -119, Tnaf.POW_2_WIDTH, 40, 0, 0, 9, 48, 67, 32, 0, 0, 72, 69, 52, -88, 0, 0, 4, 68, -32, 8, 0, 0, 14, ByteCompanionObject.MIN_VALUE, 93, 32, 0, 0, -91, -110, 66, Tnaf.POW_2_WIDTH, 0, 0, 18, 13, -56, 80, 0, 0};
    }

    private static void createRandomMask29_13() {
        _randomMask29_13 = new byte[]{98, 85, -118, -120, 0, 0, 52, 96, -47, Tnaf.POW_2_WIDTH, 0, 0, 72, 5, 1, 40, 0, 0, 0, -81, Tnaf.POW_2_WIDTH, Tnaf.POW_2_WIDTH, 0, 0, 40, 8, 33, ByteCompanionObject.MIN_VALUE, 0, 0, -127, 10, 80, 72, 0, 0, 35, 6, 35, 0, 0, 0, 6, ByteCompanionObject.MIN_VALUE, -124, -56, 0, 0, ByteCompanionObject.MIN_VALUE, 23, 5, 0, 0, 0, 48, Tnaf.POW_2_WIDTH, 65, -96, 0, 0, -116, 32, 26, 64, 0, 0, 84, 1, 100, 0, 0, 0, ByteCompanionObject.MIN_VALUE, -64, 40, 48, 0, 0};
    }

    private static void createRandomMask29_14() {
        _randomMask29_14 = new byte[]{64, 85, 2, 8, 0, 0, 21, 64, 85, 80, 0, 0, -64, 6, 32, 72, 0, 0, 40, 19, 0, 64, 0, 0, 5, 14, 2, ByteCompanionObject.MIN_VALUE, 0, 0, 100, ByteCompanionObject.MIN_VALUE, 4, -120, 0, 0, -127, -127, 0, -80, 0, 0, Tnaf.POW_2_WIDTH, -104, -120, 8, 0, 0, -124, 34, 64, Tnaf.POW_2_WIDTH, 0, 0, 18, 48, 73, 0, 0, 0, 98, 1, 116, 0, 0, 0, 40, 96, -127, 80, 0, 0, 14, 10, 24, 32, 0, 0, Tnaf.POW_2_WIDTH, -124, -94, 32, 0, 0};
    }

    private static void createRandomMask29_15() {
        _randomMask29_15 = new byte[]{98, 85, -118, -120, 0, 0, -71, 34, -60, 80, 0, 0, 24, -76, 97, -88, 0, 0, 84, -103, 19, 80, 0, 0, 6, 108, 77, -112, 0, 0, -123, 85, 36, 104, 0, 0, -86, -118, 26, 48, 0, 0, -64, 22, 64, -120, 0, 0, 65, 96, 37, 64, 0, 0, -120, 48, 1, -88, 0, 0, 32, -92, ByteCompanionObject.MIN_VALUE, -48, 0, 0, 10, 72, 81, Tnaf.POW_2_WIDTH, 0, 0, 4, -101, 8, 64, 0, 0, -108, 64, 3, 24, 0, 0, 114, 1, -106, 0, 0, 0};
    }

    private static void createRandomMask29_16() {
        _randomMask29_16 = new byte[]{-64, 22, 64, -120, 0, 0, 65, 96, 37, 64, 0, 0, -120, 48, 1, -88, 0, 0, 32, -92, ByteCompanionObject.MIN_VALUE, -48, 0, 0, 10, 72, 81, Tnaf.POW_2_WIDTH, 0, 0, 4, -101, 8, 64, 0, 0, -108, 64, 3, 24, 0, 0, 114, 1, -106, 0, 0, 0, 98, 85, -118, -120, 0, 0, -71, 34, -60, 80, 0, 0, 24, -76, 97, -88, 0, 0, 84, -103, 19, 80, 0, 0, 6, 108, 77, -112, 0, 0, -123, 85, 36, 104, 0, 0, -86, -118, 26, 48, 0, 0, 13, 44, -14, 32, 0, 0};
    }

    private static void createRandomMask29_17() {
        _randomMask29_17 = new byte[]{-64, 22, 64, -120, 0, 0, 65, 96, 37, 64, 0, 0, -120, 48, 1, -88, 0, 0, 32, -92, ByteCompanionObject.MIN_VALUE, -48, 0, 0, 10, 72, 81, Tnaf.POW_2_WIDTH, 0, 0, 4, -101, 8, 64, 0, 0, -108, 64, 3, 24, 0, 0, 114, 1, -106, 0, 0, 0, -94, 85, -120, -120, 0, 0, 52, 96, -111, Tnaf.POW_2_WIDTH, 0, 0, 74, 39, 1, 64, 0, 0, 32, -88, 12, 48, 0, 0, 17, -124, 88, -96, 0, 0, 73, 10, 36, 0, 0, 0, -122, 14, 10, 64, 0, 0, 32, -44, 34, -112, 0, 0, -120, 74, 65, 32, 0, 0};
    }

    private static void createRandomMask29_18() {
        _randomMask29_18 = new byte[]{-94, 85, -120, -120, 0, 0, 52, 96, -111, Tnaf.POW_2_WIDTH, 0, 0, 74, 39, 1, 64, 0, 0, 32, -88, 12, 48, 0, 0, 17, -124, 88, -96, 0, 0, 73, 10, 36, 0, 0, 0, -122, 14, 10, 64, 0, 0, 32, -44, 34, -112, 0, 0, -120, 74, 65, 32, 0, 0, -64, 22, 64, -120, 0, 0, 65, 96, 37, 64, 0, 0, -120, 48, 1, -88, 0, 0, 32, -92, ByteCompanionObject.MIN_VALUE, -48, 0, 0, 10, 72, 81, Tnaf.POW_2_WIDTH, 0, 0, 4, -101, 8, 64, 0, 0, -108, 64, 3, 24, 0, 0, 114, 1, -106, 0, 0, 0, 113, 54, -14, -80, 0, 0};
    }

    private static void createRandomMask29_19() {
        _randomMask29_19 = new byte[]{-94, 85, -120, -120, 0, 0, 52, 96, -111, Tnaf.POW_2_WIDTH, 0, 0, 74, 39, 1, 64, 0, 0, 32, -88, 12, 48, 0, 0, 17, -124, 88, -96, 0, 0, 73, 10, 36, 0, 0, 0, -122, 14, 10, 64, 0, 0, 32, -44, 34, -112, 0, 0, -120, 74, 65, 32, 0, 0, -64, -41, 2, ByteCompanionObject.MIN_VALUE, 0, 0, 29, 64, 85, 88, 0, 0, -44, 9, -47, 0, 0, 0, 2, 96, 2, 112, 0, 0, 4, 40, 4, -80, 0, 0, 32, -103, 18, 72, 0, 0, 64, 70, 33, 64, 0, 0, 8, -124, -126, -112, 0, 0, 104, 2, -88, Tnaf.POW_2_WIDTH, 0, 0, 35, Tnaf.POW_2_WIDTH, 9, -120, 0, 0};
    }

    private static void createRandomMask29_2() {
        _randomMask29_2 = new byte[]{-20, -21, -77, -88, 0, 0, 59, -98, -18, 112, 0, 0};
    }

    private static void createRandomMask29_20() {
        _randomMask29_20 = new byte[]{-64, -41, 2, ByteCompanionObject.MIN_VALUE, 0, 0, 29, 64, 85, 88, 0, 0, -44, 9, -47, 0, 0, 0, 2, 96, 2, 112, 0, 0, 4, 40, 4, -80, 0, 0, 32, -103, 18, 72, 0, 0, 64, 70, 33, 64, 0, 0, 8, -124, -126, -112, 0, 0, 104, 2, -88, Tnaf.POW_2_WIDTH, 0, 0, 35, Tnaf.POW_2_WIDTH, 9, -120, 0, 0, -94, 85, -120, -120, 0, 0, 52, 96, -111, Tnaf.POW_2_WIDTH, 0, 0, 74, 39, 1, 64, 0, 0, 32, -88, 12, 48, 0, 0, 17, -124, 88, -96, 0, 0, 73, 10, 36, 0, 0, 0, -122, 14, 10, 64, 0, 0, 32, -44, 34, -112, 0, 0, -120, 74, 65, 32, 0, 0, -25, -20, -36, -80, 0, 0};
    }

    private static void createRandomMask29_21() {
        _randomMask29_21 = new byte[]{-64, -41, 2, ByteCompanionObject.MIN_VALUE, 0, 0, 29, 64, 85, 88, 0, 0, -44, 9, -47, 0, 0, 0, 2, 96, 2, 112, 0, 0, 4, 40, 4, -80, 0, 0, 32, -103, 18, 72, 0, 0, 64, 70, 33, 64, 0, 0, 8, -124, -126, -112, 0, 0, 104, 2, -88, Tnaf.POW_2_WIDTH, 0, 0, 35, Tnaf.POW_2_WIDTH, 9, -120, 0, 0, 98, -47, -120, -120, 0, 0, 53, 35, -60, 64, 0, 0, 20, 20, 64, 56, 0, 0, -59, 8, 66, -64, 0, 0, 34, 12, -112, -112, 0, 0, -120, -72, 4, 72, 0, 0, 66, 84, 3, Tnaf.POW_2_WIDTH, 0, 0, 40, -92, 18, -120, 0, 0, -108, 32, 9, 96, 0, 0, 27, 4, -84, 0, 0, 0, 34, -62, 97, 0, 0, 0};
    }

    private static void createRandomMask29_22() {
        _randomMask29_22 = new byte[]{98, -47, -120, -120, 0, 0, 53, 35, -60, 64, 0, 0, 20, 20, 64, 56, 0, 0, -59, 8, 66, -64, 0, 0, 34, 12, -112, -112, 0, 0, -120, -72, 4, 72, 0, 0, 66, 84, 3, Tnaf.POW_2_WIDTH, 0, 0, 40, -92, 18, -120, 0, 0, -108, 32, 9, 96, 0, 0, 27, 4, -84, 0, 0, 0, 34, -62, 97, 0, 0, 0, -64, -41, 2, ByteCompanionObject.MIN_VALUE, 0, 0, 29, 64, 85, 88, 0, 0, -44, 9, -47, 0, 0, 0, 2, 96, 2, 112, 0, 0, 4, 40, 4, -80, 0, 0, 32, -103, 18, 72, 0, 0, 64, 70, 33, 64, 0, 0, 8, -124, -126, -112, 0, 0, 104, 2, -88, Tnaf.POW_2_WIDTH, 0, 0, 35, Tnaf.POW_2_WIDTH, 9, -120, 0, 0, 28, -112, -87, -96, 0, 0};
    }

    private static void createRandomMask29_23() {
        _randomMask29_23 = new byte[]{98, -47, -120, -120, 0, 0, 53, 35, -60, 64, 0, 0, 20, 20, 64, 56, 0, 0, -59, 8, 66, -64, 0, 0, 34, 12, -112, -112, 0, 0, -120, -72, 4, 72, 0, 0, 66, 84, 3, Tnaf.POW_2_WIDTH, 0, 0, 40, -92, 18, -120, 0, 0, -108, 32, 9, 96, 0, 0, 27, 4, -84, 0, 0, 0, 34, -62, 97, 0, 0, 0, -127, 6, 34, 64, 0, 0, 64, 105, 1, 80, 0, 0, -112, 38, 9, -120, 0, 0, 40, 40, -122, -112, 0, 0, 82, Tnaf.POW_2_WIDTH, 65, -112, 0, 0, 65, -119, Tnaf.POW_2_WIDTH, 40, 0, 0, 9, 48, 67, 32, 0, 0, 72, 69, 52, -88, 0, 0, 4, 68, -32, 8, 0, 0, 14, ByteCompanionObject.MIN_VALUE, 93, 32, 0, 0, -91, -110, 66, Tnaf.POW_2_WIDTH, 0, 0, 18, 13, -56, 80, 0, 0};
    }

    private static void createRandomMask29_24() {
        _randomMask29_24 = new byte[]{-127, 6, 34, 64, 0, 0, 64, 105, 1, 80, 0, 0, -112, 38, 9, -120, 0, 0, 40, 40, -122, -112, 0, 0, 82, Tnaf.POW_2_WIDTH, 65, -112, 0, 0, 65, -119, Tnaf.POW_2_WIDTH, 40, 0, 0, 9, 48, 67, 32, 0, 0, 72, 69, 52, -88, 0, 0, 4, 68, -32, 8, 0, 0, 14, ByteCompanionObject.MIN_VALUE, 93, 32, 0, 0, -91, -110, 66, Tnaf.POW_2_WIDTH, 0, 0, 18, 13, -56, 80, 0, 0, 98, -47, -120, -120, 0, 0, 53, 35, -60, 64, 0, 0, 20, 20, 64, 56, 0, 0, -59, 8, 66, -64, 0, 0, 34, 12, -112, -112, 0, 0, -120, -72, 4, 72, 0, 0, 66, 84, 3, Tnaf.POW_2_WIDTH, 0, 0, 40, -92, 18, -120, 0, 0, -108, 32, 9, 96, 0, 0, 27, 4, -84, 0, 0, 0, 34, -62, 97, 0, 0, 0, -67, -122, -105, -64, 0, 0};
    }

    private static void createRandomMask29_25() {
        _randomMask29_25 = new byte[]{-127, 6, 34, 64, 0, 0, 64, 105, 1, 80, 0, 0, -112, 38, 9, -120, 0, 0, 40, 40, -122, -112, 0, 0, 82, Tnaf.POW_2_WIDTH, 65, -112, 0, 0, 65, -119, Tnaf.POW_2_WIDTH, 40, 0, 0, 9, 48, 67, 32, 0, 0, 72, 69, 52, -88, 0, 0, 4, 68, -32, 8, 0, 0, 14, ByteCompanionObject.MIN_VALUE, 93, 32, 0, 0, -91, -110, 66, Tnaf.POW_2_WIDTH, 0, 0, 18, 13, -56, 80, 0, 0, 98, 85, -118, -120, 0, 0, 52, 96, -47, Tnaf.POW_2_WIDTH, 0, 0, 72, 5, 1, 40, 0, 0, 0, -81, Tnaf.POW_2_WIDTH, Tnaf.POW_2_WIDTH, 0, 0, 40, 8, 33, ByteCompanionObject.MIN_VALUE, 0, 0, -127, 10, 80, 72, 0, 0, 35, 6, 35, 0, 0, 0, 6, ByteCompanionObject.MIN_VALUE, -124, -56, 0, 0, ByteCompanionObject.MIN_VALUE, 23, 5, 0, 0, 0, 48, Tnaf.POW_2_WIDTH, 65, -96, 0, 0, -116, 32, 26, 64, 0, 0, 84, 1, 100, 0, 0, 0, ByteCompanionObject.MIN_VALUE, -64, 40, 48, 0, 0};
    }

    private static void createRandomMask29_26() {
        _randomMask29_26 = new byte[]{98, 85, -118, -120, 0, 0, 52, 96, -47, Tnaf.POW_2_WIDTH, 0, 0, 72, 5, 1, 40, 0, 0, 0, -81, Tnaf.POW_2_WIDTH, Tnaf.POW_2_WIDTH, 0, 0, 40, 8, 33, ByteCompanionObject.MIN_VALUE, 0, 0, -127, 10, 80, 72, 0, 0, 35, 6, 35, 0, 0, 0, 6, ByteCompanionObject.MIN_VALUE, -124, -56, 0, 0, ByteCompanionObject.MIN_VALUE, 23, 5, 0, 0, 0, 48, Tnaf.POW_2_WIDTH, 65, -96, 0, 0, -116, 32, 26, 64, 0, 0, 84, 1, 100, 0, 0, 0, ByteCompanionObject.MIN_VALUE, -64, 40, 48, 0, 0, -127, 6, 34, 64, 0, 0, 64, 105, 1, 80, 0, 0, -112, 38, 9, -120, 0, 0, 40, 40, -122, -112, 0, 0, 82, Tnaf.POW_2_WIDTH, 65, -112, 0, 0, 65, -119, Tnaf.POW_2_WIDTH, 40, 0, 0, 9, 48, 67, 32, 0, 0, 72, 69, 52, -88, 0, 0, 4, 68, -32, 8, 0, 0, 14, ByteCompanionObject.MIN_VALUE, 93, 32, 0, 0, -91, -110, 66, Tnaf.POW_2_WIDTH, 0, 0, 18, 13, -56, 80, 0, 0, -75, 76, -87, 112, 0, 0};
    }

    private static void createRandomMask29_27() {
        _randomMask29_27 = new byte[]{98, 85, -118, -120, 0, 0, 52, 96, -47, Tnaf.POW_2_WIDTH, 0, 0, 72, 5, 1, 40, 0, 0, 0, -81, Tnaf.POW_2_WIDTH, Tnaf.POW_2_WIDTH, 0, 0, 40, 8, 33, ByteCompanionObject.MIN_VALUE, 0, 0, -127, 10, 80, 72, 0, 0, 35, 6, 35, 0, 0, 0, 6, ByteCompanionObject.MIN_VALUE, -124, -56, 0, 0, ByteCompanionObject.MIN_VALUE, 23, 5, 0, 0, 0, 48, Tnaf.POW_2_WIDTH, 65, -96, 0, 0, -116, 32, 26, 64, 0, 0, 84, 1, 100, 0, 0, 0, ByteCompanionObject.MIN_VALUE, -64, 40, 48, 0, 0, 64, 85, 2, 8, 0, 0, 21, 64, 85, 80, 0, 0, -64, 6, 32, 72, 0, 0, 40, 19, 0, 64, 0, 0, 5, 14, 2, ByteCompanionObject.MIN_VALUE, 0, 0, 100, ByteCompanionObject.MIN_VALUE, 4, -120, 0, 0, -127, -127, 0, -80, 0, 0, Tnaf.POW_2_WIDTH, -104, -120, 8, 0, 0, -124, 34, 64, Tnaf.POW_2_WIDTH, 0, 0, 18, 48, 73, 0, 0, 0, 98, 1, 116, 0, 0, 0, 40, 96, -127, 80, 0, 0, 14, 10, 24, 32, 0, 0, Tnaf.POW_2_WIDTH, -124, -94, 32, 0, 0};
    }

    private static void createRandomMask29_28() {
        _randomMask29_28 = new byte[]{64, 85, 2, 8, 0, 0, 21, 64, 85, 80, 0, 0, -64, 6, 32, 72, 0, 0, 40, 19, 0, 64, 0, 0, 5, 14, 2, ByteCompanionObject.MIN_VALUE, 0, 0, 100, ByteCompanionObject.MIN_VALUE, 4, -120, 0, 0, -127, -127, 0, -80, 0, 0, Tnaf.POW_2_WIDTH, -104, -120, 8, 0, 0, -124, 34, 64, Tnaf.POW_2_WIDTH, 0, 0, 18, 48, 73, 0, 0, 0, 98, 1, 116, 0, 0, 0, 40, 96, -127, 80, 0, 0, 14, 10, 24, 32, 0, 0, Tnaf.POW_2_WIDTH, -124, -94, 32, 0, 0, 98, 85, -118, -120, 0, 0, 52, 96, -47, Tnaf.POW_2_WIDTH, 0, 0, 72, 5, 1, 40, 0, 0, 0, -81, Tnaf.POW_2_WIDTH, Tnaf.POW_2_WIDTH, 0, 0, 40, 8, 33, ByteCompanionObject.MIN_VALUE, 0, 0, -127, 10, 80, 72, 0, 0, 35, 6, 35, 0, 0, 0, 6, ByteCompanionObject.MIN_VALUE, -124, -56, 0, 0, ByteCompanionObject.MIN_VALUE, 23, 5, 0, 0, 0, 48, Tnaf.POW_2_WIDTH, 65, -96, 0, 0, -116, 32, 26, 64, 0, 0, 84, 1, 100, 0, 0, 0, ByteCompanionObject.MIN_VALUE, -64, 40, 48, 0, 0, -66, 31, -103, -80, 0, 0};
    }

    private static void createRandomMask29_29() {
        _randomMask29_29 = new byte[]{64, 85, 2, 8, 0, 0, 21, 64, 85, 80, 0, 0, -64, 6, 32, 72, 0, 0, 40, 19, 0, 64, 0, 0, 5, 14, 2, ByteCompanionObject.MIN_VALUE, 0, 0, 100, ByteCompanionObject.MIN_VALUE, 4, -120, 0, 0, -127, -127, 0, -80, 0, 0, Tnaf.POW_2_WIDTH, -104, -120, 8, 0, 0, -124, 34, 64, Tnaf.POW_2_WIDTH, 0, 0, 18, 48, 73, 0, 0, 0, 98, 1, 116, 0, 0, 0, 40, 96, -127, 80, 0, 0, 14, 10, 24, 32, 0, 0, Tnaf.POW_2_WIDTH, -124, -94, 32, 0, 0, 64, 85, -120, -120, 0, 0, 21, 64, -60, 64, 0, 0, -64, 5, 96, 0, 0, 0, 40, Tnaf.POW_2_WIDTH, 4, 72, 0, 0, 5, 14, 32, ByteCompanionObject.MIN_VALUE, 0, 0, 100, -127, Tnaf.POW_2_WIDTH, 8, 0, 0, -127, ByteCompanionObject.MIN_VALUE, -92, Tnaf.POW_2_WIDTH, 0, 0, Tnaf.POW_2_WIDTH, -102, 10, ByteCompanionObject.MIN_VALUE, 0, 0, -124, 32, 40, 104, 0, 0, 18, 48, 71, ByteCompanionObject.MIN_VALUE, 0, 0, 98, 2, Tnaf.POW_2_WIDTH, Tnaf.POW_2_WIDTH, 0, 0, 40, 98, 25, 0, 0, 0, 14, 8, 2, 24, 0, 0, Tnaf.POW_2_WIDTH, -123, 17, 32, 0, 0, 41, 80, 66, 96, 0, 0};
    }

    private static void createRandomMask29_3() {
        _randomMask29_3 = new byte[]{-84, -38, -78, 72, 0, 0, 85, 109, 85, 40, 0, 0, 39, -75, 12, -40, 0, 0};
    }

    private static void createRandomMask29_4() {
        _randomMask29_4 = new byte[]{44, -40, -106, -88, 0, 0, -109, 106, 85, 80, 0, 0, 26, -76, 105, -88, 0, 0, 71, 45, 15, 80, 0, 0};
    }

    private static void createRandomMask29_5() {
        _randomMask29_5 = new byte[]{100, -39, -110, -120, 0, 0, -91, 104, -107, 80, 0, 0, 82, -75, 37, -96, 0, 0, 29, -87, 78, 64, 0, 0, -100, 86, 56, -64, 0, 0};
    }

    private static void createRandomMask29_6() {
        _randomMask29_6 = new byte[]{74, 85, -118, 40, 0, 0, -107, 72, 85, 80, 0, 0, 20, -75, 49, 24, 0, 0, 81, -87, 74, 80, 0, 0, 34, 108, -115, -112, 0, 0, -120, -114, 41, 96, 0, 0};
    }

    private static void createRandomMask29_7() {
        _randomMask29_7 = new byte[]{98, 85, -118, -120, 0, 0, -71, 34, -60, 80, 0, 0, 24, -76, 97, -88, 0, 0, 84, -103, 19, 80, 0, 0, 6, 108, 77, -112, 0, 0, -123, 85, 36, 104, 0, 0, -86, -118, 26, 48, 0, 0};
    }

    private static void createRandomMask29_8() {
        _randomMask29_8 = new byte[]{-64, 22, 64, -120, 0, 0, 65, 96, 37, 64, 0, 0, -120, 48, 1, -88, 0, 0, 32, -92, ByteCompanionObject.MIN_VALUE, -48, 0, 0, 10, 72, 81, Tnaf.POW_2_WIDTH, 0, 0, 4, -101, 8, 64, 0, 0, -108, 64, 3, 24, 0, 0, 114, 1, -106, 0, 0, 0};
    }

    private static void createRandomMask29_9() {
        _randomMask29_9 = new byte[]{-94, 85, -120, -120, 0, 0, 52, 96, -111, Tnaf.POW_2_WIDTH, 0, 0, 74, 39, 1, 64, 0, 0, 32, -88, 12, 48, 0, 0, 17, -124, 88, -96, 0, 0, 73, 10, 36, 0, 0, 0, -122, 14, 10, 64, 0, 0, 32, -44, 34, -112, 0, 0, -120, 74, 65, 32, 0, 0};
    }

    private static void createRandomMask3_1() {
        byte[] bArr = new byte[2];
        bArr[0] = -32;
        _randomMask3_1 = bArr;
    }

    private static void createRandomMask3_2() {
        byte[] bArr = new byte[4];
        bArr[0] = -64;
        bArr[2] = -96;
        _randomMask3_2 = bArr;
    }

    private static void createRandomMask3_3() {
        _randomMask3_3 = new byte[]{-64, 0, -96, 0, 96, 0};
    }

    private static void createRandomMask30_1() {
        _randomMask30_1 = new byte[]{-1, -1, -1, -4, 0, 0};
    }

    private static void createRandomMask30_10() {
        _randomMask30_10 = new byte[]{-64, -95, -127, 64, 0, 0, 21, 86, 42, -84, 0, 0, 116, 64, -24, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -100, 1, 56, 0, 0, 1, 44, 2, 88, 0, 0, 68, -110, -119, 36, 0, 0, -120, 81, Tnaf.POW_2_WIDTH, -96, 0, 0, 32, -92, 65, 72, 0, 0, -86, 5, 84, 8, 0, 0, 2, 98, 4, -60, 0, 0};
    }

    private static void createRandomMask30_11() {
        _randomMask30_11 = new byte[]{98, 34, -60, 68, 0, 0, -15, 17, -30, 32, 0, 0, Tnaf.POW_2_WIDTH, 14, 32, 28, 0, 0, Tnaf.POW_2_WIDTH, -80, 33, 96, 0, 0, 36, 36, 72, 72, 0, 0, 1, 18, 2, 36, 0, 0, 0, -60, 1, -120, 0, 0, 4, -94, 9, 68, 0, 0, 2, 88, 4, -80, 0, 0, 43, 0, 86, 0, 0, 0, -104, 65, 48, ByteCompanionObject.MIN_VALUE, 0, 0};
    }

    private static void createRandomMask30_12() {
        _randomMask30_12 = new byte[]{-120, -111, 17, 32, 0, 0, 64, 84, ByteCompanionObject.MIN_VALUE, -88, 0, 0, -126, 99, 4, -60, 0, 0, 33, -92, 67, 72, 0, 0, Tnaf.POW_2_WIDTH, 100, 32, -56, 0, 0, 68, 10, -120, 20, 0, 0, Tnaf.POW_2_WIDTH, -56, 33, -112, 0, 0, 77, 42, -102, 84, 0, 0, 56, 2, 112, 4, 0, 0, 23, 72, 46, -112, 0, 0, -112, -123, 33, 8, 0, 0, 114, 20, -28, 40, 0, 0};
    }

    private static void createRandomMask30_13() {
        _randomMask30_13 = new byte[]{98, -94, -59, 68, 0, 0, 52, 68, 104, -120, 0, 0, 64, 74, ByteCompanionObject.MIN_VALUE, -108, 0, 0, -60, 5, -120, 8, 0, 0, 8, 96, Tnaf.POW_2_WIDTH, -64, 0, 0, -108, 19, 40, 36, 0, 0, -120, -63, 17, ByteCompanionObject.MIN_VALUE, 0, 0, 33, 50, 66, 100, 0, 0, -63, 65, -126, ByteCompanionObject.MIN_VALUE, 0, 0, Tnaf.POW_2_WIDTH, 104, 32, -48, 0, 0, 6, -112, 13, 32, 0, 0, 89, 0, -78, 0, 0, 0, 10, 12, 20, 24, 0, 0};
    }

    private static void createRandomMask30_14() {
        _randomMask30_14 = new byte[]{64, -126, -127, 4, 0, 0, 21, 84, 42, -88, 0, 0, -120, 19, Tnaf.POW_2_WIDTH, 36, 0, 0, -64, 17, ByteCompanionObject.MIN_VALUE, 32, 0, 0, ByteCompanionObject.MIN_VALUE, -95, 1, 64, 0, 0, 1, 34, 2, 68, 0, 0, 64, 44, ByteCompanionObject.MIN_VALUE, 88, 0, 0, 34, 2, 68, 4, 0, 0, -112, 5, 32, 8, 0, 0, 18, 64, 36, ByteCompanionObject.MIN_VALUE, 0, 0, 93, 0, -70, 0, 0, 0, 32, 84, 64, -88, 0, 0, -122, 9, 12, Tnaf.POW_2_WIDTH, 0, 0, 40, -120, 81, Tnaf.POW_2_WIDTH, 0, 0};
    }

    private static void createRandomMask30_15() {
        _randomMask30_15 = new byte[]{98, 34, -60, 68, 0, 0, 49, Tnaf.POW_2_WIDTH, 98, 32, 0, 0, 88, 0, -80, 0, 0, 0, 1, 18, 2, 36, 0, 0, -120, 33, Tnaf.POW_2_WIDTH, 64, 0, 0, 68, 2, -120, 4, 0, 0, 41, 4, 82, 8, 0, 0, -126, -95, 5, 64, 0, 0, 10, 26, 20, 52, 0, 0, 17, -32, 35, -64, 0, 0, -124, 5, 8, 8, 0, 0, -122, 65, 12, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -122, 1, 12, 0, 0, 68, 72, -120, -112, 0, 0, Tnaf.POW_2_WIDTH, -104, 33, 48, 0, 0};
    }

    private static void createRandomMask30_16() {
        _randomMask30_16 = new byte[]{-112, 35, 32, 68, 0, 0, 9, 80, 18, -96, 0, 0, 0, 106, 0, -44, 0, 0, 32, 52, 64, 104, 0, 0, 20, 68, 40, -120, 0, 0, -62, 17, -124, 32, 0, 0, 0, -58, 1, -116, 0, 0, 101, ByteCompanionObject.MIN_VALUE, -53, 0, 0, 0, 98, -94, -59, 68, 0, 0, -79, 21, 98, 40, 0, 0, 24, 106, 48, -44, 0, 0, 68, -44, -119, -88, 0, 0, 19, 100, 38, -56, 0, 0, 73, 26, -110, 52, 0, 0, -122, -115, 13, 24, 0, 0, -50, 88, -96, 20, 0, 0};
    }

    private static void createRandomMask30_17() {
        _randomMask30_17 = new byte[]{-112, 35, 32, 68, 0, 0, 9, 80, 18, -96, 0, 0, 0, 106, 0, -44, 0, 0, 32, 52, 64, 104, 0, 0, 20, 68, 40, -120, 0, 0, -62, 17, -124, 32, 0, 0, 0, -58, 1, -116, 0, 0, 101, ByteCompanionObject.MIN_VALUE, -53, 0, 0, 0, 98, 34, -60, 68, 0, 0, 36, 68, 72, -120, 0, 0, -64, 81, ByteCompanionObject.MIN_VALUE, -96, 0, 0, 3, 12, 6, 24, 0, 0, 22, 40, 44, 80, 0, 0, -119, 1, 18, 0, 0, 0, -126, -111, 5, 32, 0, 0, 8, -92, 17, 72, 0, 0, -112, 73, 32, -112, 0, 0};
    }

    private static void createRandomMask30_18() {
        _randomMask30_18 = new byte[]{98, 34, -60, 68, 0, 0, 36, 68, 72, -120, 0, 0, -64, 81, ByteCompanionObject.MIN_VALUE, -96, 0, 0, 3, 12, 6, 24, 0, 0, 22, 40, 44, 80, 0, 0, -119, 1, 18, 0, 0, 0, -126, -111, 5, 32, 0, 0, 8, -92, 17, 72, 0, 0, -112, 73, 32, -112, 0, 0, -112, 35, 32, 68, 0, 0, 9, 80, 18, -96, 0, 0, 0, 106, 0, -44, 0, 0, 32, 52, 64, 104, 0, 0, 20, 68, 40, -120, 0, 0, -62, 17, -124, 32, 0, 0, 0, -58, 1, -116, 0, 0, 101, ByteCompanionObject.MIN_VALUE, -53, 0, 0, 0, 0, -78, 71, ByteCompanionObject.MIN_VALUE, 0, 0};
    }

    private static void createRandomMask30_19() {
        _randomMask30_19 = new byte[]{98, 34, -60, 68, 0, 0, 36, 68, 72, -120, 0, 0, -64, 81, ByteCompanionObject.MIN_VALUE, -96, 0, 0, 3, 12, 6, 24, 0, 0, 22, 40, 44, 80, 0, 0, -119, 1, 18, 0, 0, 0, -126, -111, 5, 32, 0, 0, 8, -92, 17, 72, 0, 0, -112, 73, 32, -112, 0, 0, -64, -95, -127, 64, 0, 0, 21, 86, 42, -84, 0, 0, 116, 64, -24, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -100, 1, 56, 0, 0, 1, 44, 2, 88, 0, 0, 68, -110, -119, 36, 0, 0, -120, 81, Tnaf.POW_2_WIDTH, -96, 0, 0, 32, -92, 65, 72, 0, 0, -86, 5, 84, 8, 0, 0, 2, 98, 4, -60, 0, 0};
    }

    private static void createRandomMask30_2() {
        _randomMask30_2 = new byte[]{-20, -21, -39, -44, 0, 0, -69, -99, 119, 56, 0, 0};
    }

    private static void createRandomMask30_20() {
        _randomMask30_20 = new byte[]{-64, -95, -127, 64, 0, 0, 21, 86, 42, -84, 0, 0, 116, 64, -24, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -100, 1, 56, 0, 0, 1, 44, 2, 88, 0, 0, 68, -110, -119, 36, 0, 0, -120, 81, Tnaf.POW_2_WIDTH, -96, 0, 0, 32, -92, 65, 72, 0, 0, -86, 5, 84, 8, 0, 0, 2, 98, 4, -60, 0, 0, 98, 34, -60, 68, 0, 0, 36, 68, 72, -120, 0, 0, -64, 81, ByteCompanionObject.MIN_VALUE, -96, 0, 0, 3, 12, 6, 24, 0, 0, 22, 40, 44, 80, 0, 0, -119, 1, 18, 0, 0, 0, -126, -111, 5, 32, 0, 0, 8, -92, 17, 72, 0, 0, -112, 73, 32, -112, 0, 0, 81, -120, -47, 120, 0, 0};
    }

    private static void createRandomMask30_21() {
        _randomMask30_21 = new byte[]{-64, -95, -127, 64, 0, 0, 21, 86, 42, -84, 0, 0, 116, 64, -24, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -100, 1, 56, 0, 0, 1, 44, 2, 88, 0, 0, 68, -110, -119, 36, 0, 0, -120, 81, Tnaf.POW_2_WIDTH, -96, 0, 0, 32, -92, 65, 72, 0, 0, -86, 5, 84, 8, 0, 0, 2, 98, 4, -60, 0, 0, 98, 34, -60, 68, 0, 0, -15, 17, -30, 32, 0, 0, Tnaf.POW_2_WIDTH, 14, 32, 28, 0, 0, Tnaf.POW_2_WIDTH, -80, 33, 96, 0, 0, 36, 36, 72, 72, 0, 0, 1, 18, 2, 36, 0, 0, 0, -60, 1, -120, 0, 0, 4, -94, 9, 68, 0, 0, 2, 88, 4, -80, 0, 0, 43, 0, 86, 0, 0, 0, -104, 65, 48, ByteCompanionObject.MIN_VALUE, 0, 0};
    }

    private static void createRandomMask30_22() {
        _randomMask30_22 = new byte[]{98, 34, -60, 68, 0, 0, -15, 17, -30, 32, 0, 0, Tnaf.POW_2_WIDTH, 14, 32, 28, 0, 0, Tnaf.POW_2_WIDTH, -80, 33, 96, 0, 0, 36, 36, 72, 72, 0, 0, 1, 18, 2, 36, 0, 0, 0, -60, 1, -120, 0, 0, 4, -94, 9, 68, 0, 0, 2, 88, 4, -80, 0, 0, 43, 0, 86, 0, 0, 0, -104, 65, 48, ByteCompanionObject.MIN_VALUE, 0, 0, -64, -95, -127, 64, 0, 0, 21, 86, 42, -84, 0, 0, 116, 64, -24, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -100, 1, 56, 0, 0, 1, 44, 2, 88, 0, 0, 68, -110, -119, 36, 0, 0, -120, 81, Tnaf.POW_2_WIDTH, -96, 0, 0, 32, -92, 65, 72, 0, 0, -86, 5, 84, 8, 0, 0, 2, 98, 4, -60, 0, 0, 3, Tnaf.POW_2_WIDTH, 24, 116, 0, 0};
    }

    private static void createRandomMask30_23() {
        _randomMask30_23 = new byte[]{98, 34, -60, 68, 0, 0, -15, 17, -30, 32, 0, 0, Tnaf.POW_2_WIDTH, 14, 32, 28, 0, 0, Tnaf.POW_2_WIDTH, -80, 33, 96, 0, 0, 36, 36, 72, 72, 0, 0, 1, 18, 2, 36, 0, 0, 0, -60, 1, -120, 0, 0, 4, -94, 9, 68, 0, 0, 2, 88, 4, -80, 0, 0, 43, 0, 86, 0, 0, 0, -104, 65, 48, ByteCompanionObject.MIN_VALUE, 0, 0, -120, -111, 17, 32, 0, 0, 64, 84, ByteCompanionObject.MIN_VALUE, -88, 0, 0, -126, 99, 4, -60, 0, 0, 33, -92, 67, 72, 0, 0, Tnaf.POW_2_WIDTH, 100, 32, -56, 0, 0, 68, 10, -120, 20, 0, 0, Tnaf.POW_2_WIDTH, -56, 33, -112, 0, 0, 77, 42, -102, 84, 0, 0, 56, 2, 112, 4, 0, 0, 23, 72, 46, -112, 0, 0, -112, -123, 33, 8, 0, 0, 114, 20, -28, 40, 0, 0};
    }

    private static void createRandomMask30_24() {
        _randomMask30_24 = new byte[]{-120, -111, 17, 32, 0, 0, 64, 84, ByteCompanionObject.MIN_VALUE, -88, 0, 0, -126, 99, 4, -60, 0, 0, 33, -92, 67, 72, 0, 0, Tnaf.POW_2_WIDTH, 100, 32, -56, 0, 0, 68, 10, -120, 20, 0, 0, Tnaf.POW_2_WIDTH, -56, 33, -112, 0, 0, 77, 42, -102, 84, 0, 0, 56, 2, 112, 4, 0, 0, 23, 72, 46, -112, 0, 0, -112, -123, 33, 8, 0, 0, 114, 20, -28, 40, 0, 0, 98, 34, -60, 68, 0, 0, -15, 17, -30, 32, 0, 0, Tnaf.POW_2_WIDTH, 14, 32, 28, 0, 0, Tnaf.POW_2_WIDTH, -80, 33, 96, 0, 0, 36, 36, 72, 72, 0, 0, 1, 18, 2, 36, 0, 0, 0, -60, 1, -120, 0, 0, 4, -94, 9, 68, 0, 0, 2, 88, 4, -80, 0, 0, 43, 0, 86, 0, 0, 0, -104, 65, 48, ByteCompanionObject.MIN_VALUE, 0, 0, -13, 77, 28, 112, 0, 0};
    }

    private static void createRandomMask30_25() {
        _randomMask30_25 = new byte[]{-120, -111, 17, 32, 0, 0, 64, 84, ByteCompanionObject.MIN_VALUE, -88, 0, 0, -126, 99, 4, -60, 0, 0, 33, -92, 67, 72, 0, 0, Tnaf.POW_2_WIDTH, 100, 32, -56, 0, 0, 68, 10, -120, 20, 0, 0, Tnaf.POW_2_WIDTH, -56, 33, -112, 0, 0, 77, 42, -102, 84, 0, 0, 56, 2, 112, 4, 0, 0, 23, 72, 46, -112, 0, 0, -112, -123, 33, 8, 0, 0, 114, 20, -28, 40, 0, 0, 98, -94, -59, 68, 0, 0, 52, 68, 104, -120, 0, 0, 64, 74, ByteCompanionObject.MIN_VALUE, -108, 0, 0, -60, 5, -120, 8, 0, 0, 8, 96, Tnaf.POW_2_WIDTH, -64, 0, 0, -108, 19, 40, 36, 0, 0, -120, -63, 17, ByteCompanionObject.MIN_VALUE, 0, 0, 33, 50, 66, 100, 0, 0, -63, 65, -126, ByteCompanionObject.MIN_VALUE, 0, 0, Tnaf.POW_2_WIDTH, 104, 32, -48, 0, 0, 6, -112, 13, 32, 0, 0, 89, 0, -78, 0, 0, 0, 10, 12, 20, 24, 0, 0};
    }

    private static void createRandomMask30_26() {
        _randomMask30_26 = new byte[]{98, -94, -59, 68, 0, 0, 52, 68, 104, -120, 0, 0, 64, 74, ByteCompanionObject.MIN_VALUE, -108, 0, 0, -60, 5, -120, 8, 0, 0, 8, 96, Tnaf.POW_2_WIDTH, -64, 0, 0, -108, 19, 40, 36, 0, 0, -120, -63, 17, ByteCompanionObject.MIN_VALUE, 0, 0, 33, 50, 66, 100, 0, 0, -63, 65, -126, ByteCompanionObject.MIN_VALUE, 0, 0, Tnaf.POW_2_WIDTH, 104, 32, -48, 0, 0, 6, -112, 13, 32, 0, 0, 89, 0, -78, 0, 0, 0, 10, 12, 20, 24, 0, 0, -120, -111, 17, 32, 0, 0, 64, 84, ByteCompanionObject.MIN_VALUE, -88, 0, 0, -126, 99, 4, -60, 0, 0, 33, -92, 67, 72, 0, 0, Tnaf.POW_2_WIDTH, 100, 32, -56, 0, 0, 68, 10, -120, 20, 0, 0, Tnaf.POW_2_WIDTH, -56, 33, -112, 0, 0, 77, 42, -102, 84, 0, 0, 56, 2, 112, 4, 0, 0, 23, 72, 46, -112, 0, 0, -112, -123, 33, 8, 0, 0, 114, 20, -28, 40, 0, 0, -125, 17, -83, -24, 0, 0};
    }

    private static void createRandomMask30_27() {
        _randomMask30_27 = new byte[]{98, -94, -59, 68, 0, 0, 52, 68, 104, -120, 0, 0, 64, 74, ByteCompanionObject.MIN_VALUE, -108, 0, 0, -60, 5, -120, 8, 0, 0, 8, 96, Tnaf.POW_2_WIDTH, -64, 0, 0, -108, 19, 40, 36, 0, 0, -120, -63, 17, ByteCompanionObject.MIN_VALUE, 0, 0, 33, 50, 66, 100, 0, 0, -63, 65, -126, ByteCompanionObject.MIN_VALUE, 0, 0, Tnaf.POW_2_WIDTH, 104, 32, -48, 0, 0, 6, -112, 13, 32, 0, 0, 89, 0, -78, 0, 0, 0, 10, 12, 20, 24, 0, 0, 64, -126, -127, 4, 0, 0, 21, 84, 42, -88, 0, 0, -120, 19, Tnaf.POW_2_WIDTH, 36, 0, 0, -64, 17, ByteCompanionObject.MIN_VALUE, 32, 0, 0, ByteCompanionObject.MIN_VALUE, -95, 1, 64, 0, 0, 1, 34, 2, 68, 0, 0, 64, 44, ByteCompanionObject.MIN_VALUE, 88, 0, 0, 34, 2, 68, 4, 0, 0, -112, 5, 32, 8, 0, 0, 18, 64, 36, ByteCompanionObject.MIN_VALUE, 0, 0, 93, 0, -70, 0, 0, 0, 32, 84, 64, -88, 0, 0, -122, 9, 12, Tnaf.POW_2_WIDTH, 0, 0, 40, -120, 81, Tnaf.POW_2_WIDTH, 0, 0};
    }

    private static void createRandomMask30_28() {
        _randomMask30_28 = new byte[]{64, -126, -127, 4, 0, 0, 21, 84, 42, -88, 0, 0, -120, 19, Tnaf.POW_2_WIDTH, 36, 0, 0, -64, 17, ByteCompanionObject.MIN_VALUE, 32, 0, 0, ByteCompanionObject.MIN_VALUE, -95, 1, 64, 0, 0, 1, 34, 2, 68, 0, 0, 64, 44, ByteCompanionObject.MIN_VALUE, 88, 0, 0, 34, 2, 68, 4, 0, 0, -112, 5, 32, 8, 0, 0, 18, 64, 36, ByteCompanionObject.MIN_VALUE, 0, 0, 93, 0, -70, 0, 0, 0, 32, 84, 64, -88, 0, 0, -122, 9, 12, Tnaf.POW_2_WIDTH, 0, 0, 40, -120, 81, Tnaf.POW_2_WIDTH, 0, 0, 98, -94, -59, 68, 0, 0, 52, 68, 104, -120, 0, 0, 64, 74, ByteCompanionObject.MIN_VALUE, -108, 0, 0, -60, 5, -120, 8, 0, 0, 8, 96, Tnaf.POW_2_WIDTH, -64, 0, 0, -108, 19, 40, 36, 0, 0, -120, -63, 17, ByteCompanionObject.MIN_VALUE, 0, 0, 33, 50, 66, 100, 0, 0, -63, 65, -126, ByteCompanionObject.MIN_VALUE, 0, 0, Tnaf.POW_2_WIDTH, 104, 32, -48, 0, 0, 6, -112, 13, 32, 0, 0, 89, 0, -78, 0, 0, 0, 10, 12, 20, 24, 0, 0, -108, 89, 3, 24, 0, 0};
    }

    private static void createRandomMask30_29() {
        _randomMask30_29 = new byte[]{64, -126, -127, 4, 0, 0, 21, 84, 42, -88, 0, 0, -120, 19, Tnaf.POW_2_WIDTH, 36, 0, 0, -64, 17, ByteCompanionObject.MIN_VALUE, 32, 0, 0, ByteCompanionObject.MIN_VALUE, -95, 1, 64, 0, 0, 1, 34, 2, 68, 0, 0, 64, 44, ByteCompanionObject.MIN_VALUE, 88, 0, 0, 34, 2, 68, 4, 0, 0, -112, 5, 32, 8, 0, 0, 18, 64, 36, ByteCompanionObject.MIN_VALUE, 0, 0, 93, 0, -70, 0, 0, 0, 32, 84, 64, -88, 0, 0, -122, 9, 12, Tnaf.POW_2_WIDTH, 0, 0, 40, -120, 81, Tnaf.POW_2_WIDTH, 0, 0, 98, 34, -60, 68, 0, 0, 49, Tnaf.POW_2_WIDTH, 98, 32, 0, 0, 88, 0, -80, 0, 0, 0, 1, 18, 2, 36, 0, 0, -120, 33, Tnaf.POW_2_WIDTH, 64, 0, 0, 68, 2, -120, 4, 0, 0, 41, 4, 82, 8, 0, 0, -126, -95, 5, 64, 0, 0, 10, 26, 20, 52, 0, 0, 17, -32, 35, -64, 0, 0, -124, 5, 8, 8, 0, 0, -122, 65, 12, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -122, 1, 12, 0, 0, 68, 72, -120, -112, 0, 0, Tnaf.POW_2_WIDTH, -104, 33, 48, 0, 0};
    }

    private static void createRandomMask30_3() {
        _randomMask30_3 = new byte[]{-84, -109, 89, 36, 0, 0, 85, 74, -86, -108, 0, 0, 67, 54, -122, 108, 0, 0};
    }

    private static void createRandomMask30_30() {
        _randomMask30_30 = new byte[]{98, 34, -60, 68, 0, 0, 49, Tnaf.POW_2_WIDTH, 98, 32, 0, 0, 88, 0, -80, 0, 0, 0, 1, 18, 2, 36, 0, 0, -120, 33, Tnaf.POW_2_WIDTH, 64, 0, 0, 68, 2, -120, 4, 0, 0, 41, 4, 82, 8, 0, 0, -126, -95, 5, 64, 0, 0, 10, 26, 20, 52, 0, 0, 17, -32, 35, -64, 0, 0, -124, 5, 8, 8, 0, 0, -122, 65, 12, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -122, 1, 12, 0, 0, 68, 72, -120, -112, 0, 0, Tnaf.POW_2_WIDTH, -104, 33, 48, 0, 0, 64, -126, -127, 4, 0, 0, 21, 84, 42, -88, 0, 0, -120, 19, Tnaf.POW_2_WIDTH, 36, 0, 0, -64, 17, ByteCompanionObject.MIN_VALUE, 32, 0, 0, ByteCompanionObject.MIN_VALUE, -95, 1, 64, 0, 0, 1, 34, 2, 68, 0, 0, 64, 44, ByteCompanionObject.MIN_VALUE, 88, 0, 0, 34, 2, 68, 4, 0, 0, -112, 5, 32, 8, 0, 0, 18, 64, 36, ByteCompanionObject.MIN_VALUE, 0, 0, 93, 0, -70, 0, 0, 0, 32, 84, 64, -88, 0, 0, -122, 9, 12, Tnaf.POW_2_WIDTH, 0, 0, 40, -120, 81, Tnaf.POW_2_WIDTH, 0, 0, 70, -15, -17, -20, 0, 0};
    }

    private static void createRandomMask30_4() {
        _randomMask30_4 = new byte[]{37, -86, 75, 84, 0, 0, -107, 85, 42, -88, 0, 0, 26, 106, 52, -44, 0, 0, 67, -44, -121, -88, 0, 0};
    }

    private static void createRandomMask30_5() {
        _randomMask30_5 = new byte[]{100, -94, -55, 68, 0, 0, 37, 84, 74, -88, 0, 0, 73, 104, -110, -48, 0, 0, 83, -112, -89, 32, 0, 0, -114, 49, 28, 96, 0, 0};
    }

    private static void createRandomMask30_6() {
        _randomMask30_6 = new byte[]{98, -118, -59, 20, 0, 0, 21, 84, 42, -88, 0, 0, 76, 70, -104, -116, 0, 0, 82, -108, -91, 40, 0, 0, 35, 100, 70, -56, 0, 0, -118, 89, 20, -80, 0, 0};
    }

    private static void createRandomMask30_7() {
        _randomMask30_7 = new byte[]{98, -94, -59, 68, 0, 0, -79, 21, 98, 40, 0, 0, 24, 106, 48, -44, 0, 0, 68, -44, -119, -88, 0, 0, 19, 100, 38, -56, 0, 0, 73, 26, -110, 52, 0, 0, -122, -115, 13, 24, 0, 0};
    }

    private static void createRandomMask30_8() {
        _randomMask30_8 = new byte[]{-112, 35, 32, 68, 0, 0, 9, 80, 18, -96, 0, 0, 0, 106, 0, -44, 0, 0, 32, 52, 64, 104, 0, 0, 20, 68, 40, -120, 0, 0, -62, 17, -124, 32, 0, 0, 0, -58, 1, -116, 0, 0, 101, ByteCompanionObject.MIN_VALUE, -53, 0, 0, 0};
    }

    private static void createRandomMask30_9() {
        _randomMask30_9 = new byte[]{98, 34, -60, 68, 0, 0, 36, 68, 72, -120, 0, 0, -64, 81, ByteCompanionObject.MIN_VALUE, -96, 0, 0, 3, 12, 6, 24, 0, 0, 22, 40, 44, 80, 0, 0, -119, 1, 18, 0, 0, 0, -126, -111, 5, 32, 0, 0, 8, -92, 17, 72, 0, 0, -112, 73, 32, -112, 0, 0};
    }

    private static void createRandomMask31_1() {
        _randomMask31_1 = new byte[]{-1, -1, -1, -2, 0, 0};
    }

    private static void createRandomMask31_10() {
        _randomMask31_10 = new byte[]{-64, -96, -118, -94, 0, 0, 21, 86, 33, 68, 0, 0, 116, 64, 2, 74, 0, 0, 0, -100, 22, -124, 0, 0, 1, 45, -80, 64, 0, 0, 68, -109, 5, 24, 0, 0, -120, 80, 72, -108, 0, 0, 32, -92, 112, 48, 0, 0, -86, 4, 84, 74, 0, 0, 2, 99, 9, 36, 0, 0};
    }

    private static void createRandomMask31_11() {
        _randomMask31_11 = new byte[]{98, 34, -86, -86, 0, 0, -15, Tnaf.POW_2_WIDTH, 84, 68, 0, 0, Tnaf.POW_2_WIDTH, 14, 98, 34, 0, 0, Tnaf.POW_2_WIDTH, -79, 6, -124, 0, 0, 36, 36, 13, 48, 0, 0, 1, 18, -127, -62, 0, 0, 0, -60, 88, -120, 0, 0, 4, -93, -80, 80, 0, 0, 2, 89, 37, 2, 0, 0, 43, 1, 8, 100, 0, 0, -104, 64, -48, 24, 0, 0};
    }

    private static void createRandomMask31_12() {
        _randomMask31_12 = new byte[]{-120, -111, 8, 98, 0, 0, 64, 84, 49, 68, 0, 0, -126, 98, -100, 2, 0, 0, 33, -92, -119, -112, 0, 0, Tnaf.POW_2_WIDTH, 100, 29, 32, 0, 0, 68, 10, 65, -104, 0, 0, Tnaf.POW_2_WIDTH, -55, 38, ByteCompanionObject.MIN_VALUE, 0, 0, 77, 42, 90, 32, 0, 0, 56, 2, 98, -120, 0, 0, 23, 73, ByteCompanionObject.MIN_VALUE, 70, 0, 0, -112, -124, 34, 74, 0, 0, 114, 21, -47, 0, 0, 0};
    }

    private static void createRandomMask31_13() {
        _randomMask31_13 = new byte[]{98, -94, -118, 42, 0, 0, 52, 68, 68, 68, 0, 0, 64, 75, 44, 24, 0, 0, -60, 4, 24, -96, 0, 0, 8, 96, -60, 8, 0, 0, -108, 18, -110, 12, 0, 0, -120, -64, 35, 4, 0, 0, 33, 50, 36, 112, 0, 0, -63, 64, ByteCompanionObject.MIN_VALUE, -30, 0, 0, Tnaf.POW_2_WIDTH, 105, 81, 20, 0, 0, 6, -112, 17, 66, 0, 0, 89, 1, 65, ByteCompanionObject.MIN_VALUE, 0, 0, 10, 13, -118, 32, 0, 0};
    }

    private static void createRandomMask31_14() {
        _randomMask31_14 = new byte[]{64, -126, -118, -94, 0, 0, 21, 84, 68, 20, 0, 0, -120, 19, 9, -96, 0, 0, -64, Tnaf.POW_2_WIDTH, 25, 20, 0, 0, ByteCompanionObject.MIN_VALUE, -96, 48, 12, 0, 0, 1, 34, 96, 6, 0, 0, 64, 44, -62, Tnaf.POW_2_WIDTH, 0, 0, 34, 2, ByteCompanionObject.MIN_VALUE, 34, 0, 0, -112, 4, 32, 88, 0, 0, 18, 64, 18, -64, 0, 0, 93, 0, 1, 40, 0, 0, 32, 84, -92, ByteCompanionObject.MIN_VALUE, 0, 0, -122, 9, 72, 72, 0, 0, 40, -119, 5, Tnaf.POW_2_WIDTH, 0, 0};
    }

    private static void createRandomMask31_15() {
        _randomMask31_15 = new byte[]{98, 34, -86, 34, 0, 0, 49, Tnaf.POW_2_WIDTH, 68, 68, 0, 0, 88, 0, 34, 34, 0, 0, 1, 19, 0, -118, 0, 0, -120, 32, 64, 52, 0, 0, 68, 2, Tnaf.POW_2_WIDTH, -48, 0, 0, 41, 4, 69, 8, 0, 0, -126, -96, -112, 18, 0, 0, 10, 26, 14, 2, 0, 0, 17, -31, 40, 64, 0, 0, -124, 5, 4, 12, 0, 0, -122, 64, -64, -112, 0, 0, 0, -121, 19, 0, 0, 0, 68, 72, 1, 28, 0, 0, Tnaf.POW_2_WIDTH, -104, 48, 68, 0, 0};
    }

    private static void createRandomMask31_16() {
        _randomMask31_16 = new byte[]{-112, 34, 64, -88, 0, 0, 9, 80, 49, Tnaf.POW_2_WIDTH, 0, 0, 0, 107, 8, 14, 0, 0, 32, 52, -64, -112, 0, 0, 20, 68, 37, 4, 0, 0, -62, 17, 2, -126, 0, 0, 0, -58, ByteCompanionObject.MIN_VALUE, -60, 0, 0, 101, ByteCompanionObject.MIN_VALUE, 44, 96, 0, 0, 98, -94, -118, -94, 0, 0, -79, 20, 68, 84, 0, 0, 24, 107, 34, 34, 0, 0, 68, -44, 92, Tnaf.POW_2_WIDTH, 0, 0, 19, 100, -112, 104, 0, 0, 73, 27, 32, 82, 0, 0, -122, -116, 19, 12, 0, 0, -115, -108, -87, -32, 0, 0};
    }

    private static void createRandomMask31_17() {
        _randomMask31_17 = new byte[]{-112, 34, 64, -88, 0, 0, 9, 80, 49, Tnaf.POW_2_WIDTH, 0, 0, 0, 107, 8, 14, 0, 0, 32, 52, -64, -112, 0, 0, 20, 68, 37, 4, 0, 0, -62, 17, 2, -126, 0, 0, 0, -58, ByteCompanionObject.MIN_VALUE, -60, 0, 0, 101, ByteCompanionObject.MIN_VALUE, 44, 96, 0, 0, 98, 34, -86, -94, 0, 0, 36, 68, 68, 84, 0, 0, -64, 80, 11, 10, 0, 0, 3, 12, 18, -108, 0, 0, 22, 41, 8, 100, 0, 0, -119, 1, ByteCompanionObject.MIN_VALUE, 26, 0, 0, -126, -112, 65, 76, 0, 0, 8, -92, 52, 18, 0, 0, -112, 72, -120, -56, 0, 0};
    }

    private static void createRandomMask31_18() {
        _randomMask31_18 = new byte[]{98, 34, -86, -94, 0, 0, 36, 68, 68, 84, 0, 0, -64, 80, 11, 10, 0, 0, 3, 12, 18, -108, 0, 0, 22, 41, 8, 100, 0, 0, -119, 1, ByteCompanionObject.MIN_VALUE, 26, 0, 0, -126, -112, 65, 76, 0, 0, 8, -92, 52, 18, 0, 0, -112, 72, -120, -56, 0, 0, -112, 34, 64, -88, 0, 0, 9, 80, 49, Tnaf.POW_2_WIDTH, 0, 0, 0, 107, 8, 14, 0, 0, 32, 52, -64, -112, 0, 0, 20, 68, 37, 4, 0, 0, -62, 17, 2, -126, 0, 0, 0, -58, ByteCompanionObject.MIN_VALUE, -60, 0, 0, 101, ByteCompanionObject.MIN_VALUE, 44, 96, 0, 0, -29, -47, 46, 0, 0, 0};
    }

    private static void createRandomMask31_19() {
        _randomMask31_19 = new byte[]{98, 34, -86, -94, 0, 0, 36, 68, 68, 84, 0, 0, -64, 80, 11, 10, 0, 0, 3, 12, 18, -108, 0, 0, 22, 41, 8, 100, 0, 0, -119, 1, ByteCompanionObject.MIN_VALUE, 26, 0, 0, -126, -112, 65, 76, 0, 0, 8, -92, 52, 18, 0, 0, -112, 72, -120, -56, 0, 0, -64, -96, -118, -94, 0, 0, 21, 86, 33, 68, 0, 0, 116, 64, 2, 74, 0, 0, 0, -100, 22, -124, 0, 0, 1, 45, -80, 64, 0, 0, 68, -109, 5, 24, 0, 0, -120, 80, 72, -108, 0, 0, 32, -92, 112, 48, 0, 0, -86, 4, 84, 74, 0, 0, 2, 99, 9, 36, 0, 0};
    }

    private static void createRandomMask31_2() {
        _randomMask31_2 = new byte[]{-20, -21, 93, 92, 0, 0, -69, -100, -14, -14, 0, 0};
    }

    private static void createRandomMask31_20() {
        _randomMask31_20 = new byte[]{-64, -96, -118, -94, 0, 0, 21, 86, 33, 68, 0, 0, 116, 64, 2, 74, 0, 0, 0, -100, 22, -124, 0, 0, 1, 45, -80, 64, 0, 0, 68, -109, 5, 24, 0, 0, -120, 80, 72, -108, 0, 0, 32, -92, 112, 48, 0, 0, -86, 4, 84, 74, 0, 0, 2, 99, 9, 36, 0, 0, 98, 34, -86, -94, 0, 0, 36, 68, 68, 84, 0, 0, -64, 80, 11, 10, 0, 0, 3, 12, 18, -108, 0, 0, 22, 41, 8, 100, 0, 0, -119, 1, ByteCompanionObject.MIN_VALUE, 26, 0, 0, -126, -112, 65, 76, 0, 0, 8, -92, 52, 18, 0, 0, -112, 72, -120, -56, 0, 0, -102, -44, 106, 54, 0, 0};
    }

    private static void createRandomMask31_21() {
        _randomMask31_21 = new byte[]{-64, -96, -118, -94, 0, 0, 21, 86, 33, 68, 0, 0, 116, 64, 2, 74, 0, 0, 0, -100, 22, -124, 0, 0, 1, 45, -80, 64, 0, 0, 68, -109, 5, 24, 0, 0, -120, 80, 72, -108, 0, 0, 32, -92, 112, 48, 0, 0, -86, 4, 84, 74, 0, 0, 2, 99, 9, 36, 0, 0, 98, 34, -86, -86, 0, 0, -15, Tnaf.POW_2_WIDTH, 84, 68, 0, 0, Tnaf.POW_2_WIDTH, 14, 98, 34, 0, 0, Tnaf.POW_2_WIDTH, -79, 6, -124, 0, 0, 36, 36, 13, 48, 0, 0, 1, 18, -127, -62, 0, 0, 0, -60, 88, -120, 0, 0, 4, -93, -80, 80, 0, 0, 2, 89, 37, 2, 0, 0, 43, 1, 8, 100, 0, 0, -104, 64, -48, 24, 0, 0};
    }

    private static void createRandomMask31_22() {
        _randomMask31_22 = new byte[]{98, 34, -86, -86, 0, 0, -15, Tnaf.POW_2_WIDTH, 84, 68, 0, 0, Tnaf.POW_2_WIDTH, 14, 98, 34, 0, 0, Tnaf.POW_2_WIDTH, -79, 6, -124, 0, 0, 36, 36, 13, 48, 0, 0, 1, 18, -127, -62, 0, 0, 0, -60, 88, -120, 0, 0, 4, -93, -80, 80, 0, 0, 2, 89, 37, 2, 0, 0, 43, 1, 8, 100, 0, 0, -104, 64, -48, 24, 0, 0, -64, -96, -118, -94, 0, 0, 21, 86, 33, 68, 0, 0, 116, 64, 2, 74, 0, 0, 0, -100, 22, -124, 0, 0, 1, 45, -80, 64, 0, 0, 68, -109, 5, 24, 0, 0, -120, 80, 72, -108, 0, 0, 32, -92, 112, 48, 0, 0, -86, 4, 84, 74, 0, 0, 2, 99, 9, 36, 0, 0, 50, 35, 115, -114, 0, 0};
    }

    private static void createRandomMask31_23() {
        _randomMask31_23 = new byte[]{98, 34, -86, -86, 0, 0, -15, Tnaf.POW_2_WIDTH, 84, 68, 0, 0, Tnaf.POW_2_WIDTH, 14, 98, 34, 0, 0, Tnaf.POW_2_WIDTH, -79, 6, -124, 0, 0, 36, 36, 13, 48, 0, 0, 1, 18, -127, -62, 0, 0, 0, -60, 88, -120, 0, 0, 4, -93, -80, 80, 0, 0, 2, 89, 37, 2, 0, 0, 43, 1, 8, 100, 0, 0, -104, 64, -48, 24, 0, 0, -120, -111, 8, 98, 0, 0, 64, 84, 49, 68, 0, 0, -126, 98, -100, 2, 0, 0, 33, -92, -119, -112, 0, 0, Tnaf.POW_2_WIDTH, 100, 29, 32, 0, 0, 68, 10, 65, -104, 0, 0, Tnaf.POW_2_WIDTH, -55, 38, ByteCompanionObject.MIN_VALUE, 0, 0, 77, 42, 90, 32, 0, 0, 56, 2, 98, -120, 0, 0, 23, 73, ByteCompanionObject.MIN_VALUE, 70, 0, 0, -112, -124, 34, 74, 0, 0, 114, 21, -47, 0, 0, 0};
    }

    private static void createRandomMask31_24() {
        _randomMask31_24 = new byte[]{-120, -111, 8, 98, 0, 0, 64, 84, 49, 68, 0, 0, -126, 98, -100, 2, 0, 0, 33, -92, -119, -112, 0, 0, Tnaf.POW_2_WIDTH, 100, 29, 32, 0, 0, 68, 10, 65, -104, 0, 0, Tnaf.POW_2_WIDTH, -55, 38, ByteCompanionObject.MIN_VALUE, 0, 0, 77, 42, 90, 32, 0, 0, 56, 2, 98, -120, 0, 0, 23, 73, ByteCompanionObject.MIN_VALUE, 70, 0, 0, -112, -124, 34, 74, 0, 0, 114, 21, -47, 0, 0, 0, 98, 34, -86, -86, 0, 0, -15, Tnaf.POW_2_WIDTH, 84, 68, 0, 0, Tnaf.POW_2_WIDTH, 14, 98, 34, 0, 0, Tnaf.POW_2_WIDTH, -79, 6, -124, 0, 0, 36, 36, 13, 48, 0, 0, 1, 18, -127, -62, 0, 0, 0, -60, 88, -120, 0, 0, 4, -93, -80, 80, 0, 0, 2, 89, 37, 2, 0, 0, 43, 1, 8, 100, 0, 0, -104, 64, -48, 24, 0, 0, -16, -33, -111, -74, 0, 0};
    }

    private static void createRandomMask31_25() {
        _randomMask31_25 = new byte[]{-120, -111, 8, 98, 0, 0, 64, 84, 49, 68, 0, 0, -126, 98, -100, 2, 0, 0, 33, -92, -119, -112, 0, 0, Tnaf.POW_2_WIDTH, 100, 29, 32, 0, 0, 68, 10, 65, -104, 0, 0, Tnaf.POW_2_WIDTH, -55, 38, ByteCompanionObject.MIN_VALUE, 0, 0, 77, 42, 90, 32, 0, 0, 56, 2, 98, -120, 0, 0, 23, 73, ByteCompanionObject.MIN_VALUE, 70, 0, 0, -112, -124, 34, 74, 0, 0, 114, 21, -47, 0, 0, 0, 98, -94, -118, 42, 0, 0, 52, 68, 68, 68, 0, 0, 64, 75, 44, 24, 0, 0, -60, 4, 24, -96, 0, 0, 8, 96, -60, 8, 0, 0, -108, 18, -110, 12, 0, 0, -120, -64, 35, 4, 0, 0, 33, 50, 36, 112, 0, 0, -63, 64, ByteCompanionObject.MIN_VALUE, -30, 0, 0, Tnaf.POW_2_WIDTH, 105, 81, 20, 0, 0, 6, -112, 17, 66, 0, 0, 89, 1, 65, ByteCompanionObject.MIN_VALUE, 0, 0, 10, 13, -118, 32, 0, 0};
    }

    private static void createRandomMask31_26() {
        _randomMask31_26 = new byte[]{98, -94, -118, 42, 0, 0, 52, 68, 68, 68, 0, 0, 64, 75, 44, 24, 0, 0, -60, 4, 24, -96, 0, 0, 8, 96, -60, 8, 0, 0, -108, 18, -110, 12, 0, 0, -120, -64, 35, 4, 0, 0, 33, 50, 36, 112, 0, 0, -63, 64, ByteCompanionObject.MIN_VALUE, -30, 0, 0, Tnaf.POW_2_WIDTH, 105, 81, 20, 0, 0, 6, -112, 17, 66, 0, 0, 89, 1, 65, ByteCompanionObject.MIN_VALUE, 0, 0, 10, 13, -118, 32, 0, 0, -120, -111, 8, 98, 0, 0, 64, 84, 49, 68, 0, 0, -126, 98, -100, 2, 0, 0, 33, -92, -119, -112, 0, 0, Tnaf.POW_2_WIDTH, 100, 29, 32, 0, 0, 68, 10, 65, -104, 0, 0, Tnaf.POW_2_WIDTH, -55, 38, ByteCompanionObject.MIN_VALUE, 0, 0, 77, 42, 90, 32, 0, 0, 56, 2, 98, -120, 0, 0, 23, 73, ByteCompanionObject.MIN_VALUE, 70, 0, 0, -112, -124, 34, 74, 0, 0, 114, 21, -47, 0, 0, 0, -59, 117, 72, -70, 0, 0};
    }

    private static void createRandomMask31_27() {
        _randomMask31_27 = new byte[]{98, -94, -118, 42, 0, 0, 52, 68, 68, 68, 0, 0, 64, 75, 44, 24, 0, 0, -60, 4, 24, -96, 0, 0, 8, 96, -60, 8, 0, 0, -108, 18, -110, 12, 0, 0, -120, -64, 35, 4, 0, 0, 33, 50, 36, 112, 0, 0, -63, 64, ByteCompanionObject.MIN_VALUE, -30, 0, 0, Tnaf.POW_2_WIDTH, 105, 81, 20, 0, 0, 6, -112, 17, 66, 0, 0, 89, 1, 65, ByteCompanionObject.MIN_VALUE, 0, 0, 10, 13, -118, 32, 0, 0, 64, -126, -118, -94, 0, 0, 21, 84, 68, 20, 0, 0, -120, 19, 9, -96, 0, 0, -64, Tnaf.POW_2_WIDTH, 25, 20, 0, 0, ByteCompanionObject.MIN_VALUE, -96, 48, 12, 0, 0, 1, 34, 96, 6, 0, 0, 64, 44, -62, Tnaf.POW_2_WIDTH, 0, 0, 34, 2, ByteCompanionObject.MIN_VALUE, 34, 0, 0, -112, 4, 32, 88, 0, 0, 18, 64, 18, -64, 0, 0, 93, 0, 1, 40, 0, 0, 32, 84, -92, ByteCompanionObject.MIN_VALUE, 0, 0, -122, 9, 72, 72, 0, 0, 40, -119, 5, Tnaf.POW_2_WIDTH, 0, 0};
    }

    private static void createRandomMask31_28() {
        _randomMask31_28 = new byte[]{64, -126, -118, -94, 0, 0, 21, 84, 68, 20, 0, 0, -120, 19, 9, -96, 0, 0, -64, Tnaf.POW_2_WIDTH, 25, 20, 0, 0, ByteCompanionObject.MIN_VALUE, -96, 48, 12, 0, 0, 1, 34, 96, 6, 0, 0, 64, 44, -62, Tnaf.POW_2_WIDTH, 0, 0, 34, 2, ByteCompanionObject.MIN_VALUE, 34, 0, 0, -112, 4, 32, 88, 0, 0, 18, 64, 18, -64, 0, 0, 93, 0, 1, 40, 0, 0, 32, 84, -92, ByteCompanionObject.MIN_VALUE, 0, 0, -122, 9, 72, 72, 0, 0, 40, -119, 5, Tnaf.POW_2_WIDTH, 0, 0, 98, -94, -118, 42, 0, 0, 52, 68, 68, 68, 0, 0, 64, 75, 44, 24, 0, 0, -60, 4, 24, -96, 0, 0, 8, 96, -60, 8, 0, 0, -108, 18, -110, 12, 0, 0, -120, -64, 35, 4, 0, 0, 33, 50, 36, 112, 0, 0, -63, 64, ByteCompanionObject.MIN_VALUE, -30, 0, 0, Tnaf.POW_2_WIDTH, 105, 81, 20, 0, 0, 6, -112, 17, 66, 0, 0, 89, 1, 65, ByteCompanionObject.MIN_VALUE, 0, 0, 10, 13, -118, 32, 0, 0, PSSSigner.TRAILER_IMPLICIT, 13, -54, 40, 0, 0};
    }

    private static void createRandomMask31_29() {
        _randomMask31_29 = new byte[]{64, -126, -118, -94, 0, 0, 21, 84, 68, 20, 0, 0, -120, 19, 9, -96, 0, 0, -64, Tnaf.POW_2_WIDTH, 25, 20, 0, 0, ByteCompanionObject.MIN_VALUE, -96, 48, 12, 0, 0, 1, 34, 96, 6, 0, 0, 64, 44, -62, Tnaf.POW_2_WIDTH, 0, 0, 34, 2, ByteCompanionObject.MIN_VALUE, 34, 0, 0, -112, 4, 32, 88, 0, 0, 18, 64, 18, -64, 0, 0, 93, 0, 1, 40, 0, 0, 32, 84, -92, ByteCompanionObject.MIN_VALUE, 0, 0, -122, 9, 72, 72, 0, 0, 40, -119, 5, Tnaf.POW_2_WIDTH, 0, 0, 98, 34, -86, 34, 0, 0, 49, Tnaf.POW_2_WIDTH, 68, 68, 0, 0, 88, 0, 34, 34, 0, 0, 1, 19, 0, -118, 0, 0, -120, 32, 64, 52, 0, 0, 68, 2, Tnaf.POW_2_WIDTH, -48, 0, 0, 41, 4, 69, 8, 0, 0, -126, -96, -112, 18, 0, 0, 10, 26, 14, 2, 0, 0, 17, -31, 40, 64, 0, 0, -124, 5, 4, 12, 0, 0, -122, 64, -64, -112, 0, 0, 0, -121, 19, 0, 0, 0, 68, 72, 1, 28, 0, 0, Tnaf.POW_2_WIDTH, -104, 48, 68, 0, 0};
    }

    private static void createRandomMask31_3() {
        _randomMask31_3 = new byte[]{-84, -109, 90, 90, 0, 0, 85, 74, -20, 108, 0, 0, 67, 54, 77, -74, 0, 0};
    }

    private static void createRandomMask31_30() {
        _randomMask31_30 = new byte[]{98, 34, -86, 34, 0, 0, 49, Tnaf.POW_2_WIDTH, 68, 68, 0, 0, 88, 0, 34, 34, 0, 0, 1, 19, 0, -118, 0, 0, -120, 32, 64, 52, 0, 0, 68, 2, Tnaf.POW_2_WIDTH, -48, 0, 0, 41, 4, 69, 8, 0, 0, -126, -96, -112, 18, 0, 0, 10, 26, 14, 2, 0, 0, 17, -31, 40, 64, 0, 0, -124, 5, 4, 12, 0, 0, -122, 64, -64, -112, 0, 0, 0, -121, 19, 0, 0, 0, 68, 72, 1, 28, 0, 0, Tnaf.POW_2_WIDTH, -104, 48, 68, 0, 0, 64, -126, -118, -94, 0, 0, 21, 84, 68, 20, 0, 0, -120, 19, 9, -96, 0, 0, -64, Tnaf.POW_2_WIDTH, 25, 20, 0, 0, ByteCompanionObject.MIN_VALUE, -96, 48, 12, 0, 0, 1, 34, 96, 6, 0, 0, 64, 44, -62, Tnaf.POW_2_WIDTH, 0, 0, 34, 2, ByteCompanionObject.MIN_VALUE, 34, 0, 0, -112, 4, 32, 88, 0, 0, 18, 64, 18, -64, 0, 0, 93, 0, 1, 40, 0, 0, 32, 84, -92, ByteCompanionObject.MIN_VALUE, 0, 0, -122, 9, 72, 72, 0, 0, 40, -119, 5, Tnaf.POW_2_WIDTH, 0, 0, -31, 79, -32, ByteCompanionObject.MIN_VALUE, 0, 0};
    }

    private static void createRandomMask31_31() {
        _randomMask31_31 = new byte[]{98, 34, -86, 34, 0, 0, 49, Tnaf.POW_2_WIDTH, 68, 68, 0, 0, 88, 0, 34, 34, 0, 0, 1, 19, 0, -118, 0, 0, -120, 32, 64, 52, 0, 0, 68, 2, Tnaf.POW_2_WIDTH, -48, 0, 0, 41, 4, 69, 8, 0, 0, -126, -96, -112, 18, 0, 0, 10, 26, 14, 2, 0, 0, 17, -31, 40, 64, 0, 0, -124, 5, 4, 12, 0, 0, -122, 64, -64, -112, 0, 0, 0, -121, 19, 0, 0, 0, 68, 72, 1, 28, 0, 0, Tnaf.POW_2_WIDTH, -104, 48, 68, 0, 0, 98, 35, 72, 32, 0, 0, 49, Tnaf.POW_2_WIDTH, 2, 84, 0, 0, 88, 0, 12, -124, 0, 0, 1, 18, Tnaf.POW_2_WIDTH, -48, 0, 0, -120, 33, 3, 32, 0, 0, 68, 2, 1, -32, 0, 0, 41, 4, -96, 10, 0, 0, -126, -96, 64, -94, 0, 0, 10, 26, -122, Tnaf.POW_2_WIDTH, 0, 0, 17, -32, -47, 0, 0, 0, -124, 5, 0, 22, 0, 0, -122, 64, 32, -104, 0, 0, 0, -122, 36, 96, 0, 0, 68, 72, -127, 10, 0, 0, Tnaf.POW_2_WIDTH, -104, 28, 8, 0, 0, -121, 116, 48, 36, 0, 0};
    }

    private static void createRandomMask31_4() {
        _randomMask31_4 = new byte[]{37, -86, -86, -86, 0, 0, -107, 85, 85, 84, 0, 0, 26, 106, 106, 106, 0, 0, 67, -43, -107, -108, 0, 0};
    }

    private static void createRandomMask31_5() {
        _randomMask31_5 = new byte[]{100, -94, -86, -86, 0, 0, 37, 84, 84, 84, 0, 0, 73, 104, 72, 74, 0, 0, 83, -111, 9, -112, 0, 0, -114, 48, 33, 108, 0, 0};
    }

    private static void createRandomMask31_6() {
        _randomMask31_6 = new byte[]{98, -118, -94, -94, 0, 0, 21, 84, 20, 84, 0, 0, 76, 71, 68, 42, 0, 0, 82, -107, 8, -108, 0, 0, 35, 100, 97, 36, 0, 0, -118, 88, 9, 88, 0, 0};
    }

    private static void createRandomMask31_7() {
        _randomMask31_7 = new byte[]{98, -94, -118, -94, 0, 0, -79, 20, 68, 84, 0, 0, 24, 107, 34, 34, 0, 0, 68, -44, 92, Tnaf.POW_2_WIDTH, 0, 0, 19, 100, -112, 104, 0, 0, 73, 27, 32, 82, 0, 0, -122, -116, 19, 12, 0, 0};
    }

    private static void createRandomMask31_8() {
        _randomMask31_8 = new byte[]{-112, 34, 64, -88, 0, 0, 9, 80, 49, Tnaf.POW_2_WIDTH, 0, 0, 0, 107, 8, 14, 0, 0, 32, 52, -64, -112, 0, 0, 20, 68, 37, 4, 0, 0, -62, 17, 2, -126, 0, 0, 0, -58, ByteCompanionObject.MIN_VALUE, -60, 0, 0, 101, ByteCompanionObject.MIN_VALUE, 44, 96, 0, 0};
    }

    private static void createRandomMask31_9() {
        _randomMask31_9 = new byte[]{98, 34, -86, -94, 0, 0, 36, 68, 68, 84, 0, 0, -64, 80, 11, 10, 0, 0, 3, 12, 18, -108, 0, 0, 22, 41, 8, 100, 0, 0, -119, 1, ByteCompanionObject.MIN_VALUE, 26, 0, 0, -126, -112, 65, 76, 0, 0, 8, -92, 52, 18, 0, 0, -112, 72, -120, -56, 0, 0};
    }

    private static void createRandomMask32_1() {
        _randomMask32_1 = new byte[]{-1, -1, -1, -1, 0, 0};
    }

    private static void createRandomMask32_10() {
        _randomMask32_10 = new byte[]{69, 81, 69, 81, 0, 0, Tnaf.POW_2_WIDTH, -94, Tnaf.POW_2_WIDTH, -94, 0, 0, 1, 37, 1, 37, 0, 0, 11, 66, 11, 66, 0, 0, -40, 32, -40, 32, 0, 0, -126, -116, -126, -116, 0, 0, 36, 74, 36, 74, 0, 0, 56, 24, 56, 24, 0, 0, 42, 37, 42, 37, 0, 0, -124, -110, -124, -110, 0, 0};
    }

    private static void createRandomMask32_11() {
        _randomMask32_11 = new byte[]{85, 85, 85, 85, 0, 0, 42, 34, 42, 34, 0, 0, 49, 17, 49, 17, 0, 0, -125, 66, -125, 66, 0, 0, 6, -104, 6, -104, 0, 0, 64, -31, 64, -31, 0, 0, 44, 68, 44, 68, 0, 0, -40, 40, -40, 40, 0, 0, -110, -127, -110, -127, 0, 0, -124, 50, -124, 50, 0, 0, 104, 12, 104, 12, 0, 0};
    }

    private static void createRandomMask32_12() {
        _randomMask32_12 = new byte[]{-124, 49, -124, 49, 0, 0, 24, -94, 24, -94, 0, 0, 78, 1, 78, 1, 0, 0, 68, -56, 68, -56, 0, 0, 14, -112, 14, -112, 0, 0, 32, -52, 32, -52, 0, 0, -109, 64, -109, 64, 0, 0, 45, Tnaf.POW_2_WIDTH, 45, Tnaf.POW_2_WIDTH, 0, 0, 49, 68, 49, 68, 0, 0, -64, 35, -64, 35, 0, 0, 17, 37, 17, 37, 0, 0, -24, ByteCompanionObject.MIN_VALUE, -24, ByteCompanionObject.MIN_VALUE, 0, 0};
    }

    private static void createRandomMask32_13() {
        _randomMask32_13 = new byte[]{69, 21, 69, 21, 0, 0, 34, 34, 34, 34, 0, 0, -106, 12, -106, 12, 0, 0, 12, 80, 12, 80, 0, 0, 98, 4, 98, 4, 0, 0, 73, 6, 73, 6, 0, 0, 17, -126, 17, -126, 0, 0, 18, 56, 18, 56, 0, 0, 64, 113, 64, 113, 0, 0, -88, -118, -88, -118, 0, 0, 8, -95, 8, -95, 0, 0, -96, -64, -96, -64, 0, 0, -59, Tnaf.POW_2_WIDTH, -59, Tnaf.POW_2_WIDTH, 0, 0};
    }

    private static void createRandomMask32_14() {
        _randomMask32_14 = new byte[]{69, 81, 69, 81, 0, 0, 34, 10, 34, 10, 0, 0, -124, -48, -124, -48, 0, 0, 12, -118, 12, -118, 0, 0, 24, 6, 24, 6, 0, 0, 48, 3, 48, 3, 0, 0, 97, 8, 97, 8, 0, 0, 64, 17, 64, 17, 0, 0, Tnaf.POW_2_WIDTH, 44, Tnaf.POW_2_WIDTH, 44, 0, 0, 9, 96, 9, 96, 0, 0, 0, -108, 0, -108, 0, 0, 82, 64, 82, 64, 0, 0, -92, 36, -92, 36, 0, 0, -126, -120, -126, -120, 0, 0};
    }

    private static void createRandomMask32_15() {
        _randomMask32_15 = new byte[]{85, 17, 85, 17, 0, 0, 34, 34, 34, 34, 0, 0, 17, 17, 17, 17, 0, 0, ByteCompanionObject.MIN_VALUE, 69, ByteCompanionObject.MIN_VALUE, 69, 0, 0, 32, 26, 32, 26, 0, 0, 8, 104, 8, 104, 0, 0, 34, -124, 34, -124, 0, 0, 72, 9, 72, 9, 0, 0, 7, 1, 7, 1, 0, 0, -108, 32, -108, 32, 0, 0, -126, 6, -126, 6, 0, 0, 96, 72, 96, 72, 0, 0, -119, ByteCompanionObject.MIN_VALUE, -119, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -114, 0, -114, 0, 0, 24, 34, 24, 34, 0, 0};
    }

    private static void createRandomMask32_16() {
        _randomMask32_16 = new byte[]{-92, Tnaf.POW_2_WIDTH, -92, Tnaf.POW_2_WIDTH, 0, 0, 1, 42, 1, 42, 0, 0, 6, 66, 6, 66, 0, 0, 8, 104, 8, 104, 0, 0, -127, -112, -127, -112, 0, 0, 0, -16, 0, -16, 0, 0, 80, 5, 80, 5, 0, 0, 32, 81, 32, 81, 0, 0, 67, 8, 67, 8, 0, 0, 104, ByteCompanionObject.MIN_VALUE, 104, ByteCompanionObject.MIN_VALUE, 0, 0, ByteCompanionObject.MIN_VALUE, 11, ByteCompanionObject.MIN_VALUE, 11, 0, 0, Tnaf.POW_2_WIDTH, 76, Tnaf.POW_2_WIDTH, 76, 0, 0, 18, 48, 18, 48, 0, 0, 64, -123, 64, -123, 0, 0, 14, 4, 14, 4, 0, 0, 24, 18, 24, 18, 0, 0};
    }

    private static void createRandomMask32_17() {
        _randomMask32_17 = new byte[]{32, 84, 32, 84, 0, 0, 24, -120, 24, -120, 0, 0, -124, 7, -124, 7, 0, 0, 96, 72, 96, 72, 0, 0, 18, -126, 18, -126, 0, 0, -127, 65, -127, 65, 0, 0, 64, 98, 64, 98, 0, 0, 22, 48, 22, 48, 0, 0, 85, 81, 85, 81, 0, 0, 34, 42, 34, 42, 0, 0, 5, -123, 5, -123, 0, 0, 9, 74, 9, 74, 0, 0, -124, 50, -124, 50, 0, 0, -64, 13, -64, 13, 0, 0, 32, -90, 32, -90, 0, 0, 26, 9, 26, 9, 0, 0, 68, 100, 68, 100, 0, 0};
    }

    private static void createRandomMask32_18() {
        _randomMask32_18 = new byte[]{85, 81, 85, 81, 0, 0, 34, 42, 34, 42, 0, 0, 5, -123, 5, -123, 0, 0, 9, 74, 9, 74, 0, 0, -124, 50, -124, 50, 0, 0, -64, 13, -64, 13, 0, 0, 32, -90, 32, -90, 0, 0, 26, 9, 26, 9, 0, 0, 68, 100, 68, 100, 0, 0, 32, 84, 32, 84, 0, 0, 24, -120, 24, -120, 0, 0, -124, 7, -124, 7, 0, 0, 96, 72, 96, 72, 0, 0, 18, -126, 18, -126, 0, 0, -127, 65, -127, 65, 0, 0, 64, 98, 64, 98, 0, 0, 22, 48, 22, 48, 0, 0, 30, -78, -40, 83, 0, 0};
    }

    private static void createRandomMask32_19() {
        _randomMask32_19 = new byte[]{85, 81, 85, 81, 0, 0, 34, 42, 34, 42, 0, 0, 5, -123, 5, -123, 0, 0, 9, 74, 9, 74, 0, 0, -124, 50, -124, 50, 0, 0, -64, 13, -64, 13, 0, 0, 32, -90, 32, -90, 0, 0, 26, 9, 26, 9, 0, 0, 68, 100, 68, 100, 0, 0, 69, 81, 69, 81, 0, 0, Tnaf.POW_2_WIDTH, -94, Tnaf.POW_2_WIDTH, -94, 0, 0, 1, 37, 1, 37, 0, 0, 11, 66, 11, 66, 0, 0, -40, 32, -40, 32, 0, 0, -126, -116, -126, -116, 0, 0, 36, 74, 36, 74, 0, 0, 56, 24, 56, 24, 0, 0, 42, 37, 42, 37, 0, 0, -124, -110, -124, -110, 0, 0};
    }

    private static void createRandomMask32_2() {
        _randomMask32_2 = new byte[]{-82, -82, -82, -82, 0, 0, 121, 121, 121, 121, 0, 0};
    }

    private static void createRandomMask32_20() {
        _randomMask32_20 = new byte[]{69, 81, 69, 81, 0, 0, Tnaf.POW_2_WIDTH, -94, Tnaf.POW_2_WIDTH, -94, 0, 0, 1, 37, 1, 37, 0, 0, 11, 66, 11, 66, 0, 0, -40, 32, -40, 32, 0, 0, -126, -116, -126, -116, 0, 0, 36, 74, 36, 74, 0, 0, 56, 24, 56, 24, 0, 0, 42, 37, 42, 37, 0, 0, -124, -110, -124, -110, 0, 0, 85, 81, 85, 81, 0, 0, 34, 42, 34, 42, 0, 0, 5, -123, 5, -123, 0, 0, 9, 74, 9, 74, 0, 0, -124, 50, -124, 50, 0, 0, -64, 13, -64, 13, 0, 0, 32, -90, 32, -90, 0, 0, 26, 9, 26, 9, 0, 0, 68, 100, 68, 100, 0, 0, -106, -45, -10, -84, 0, 0};
    }

    private static void createRandomMask32_21() {
        _randomMask32_21 = new byte[]{69, 81, 69, 81, 0, 0, Tnaf.POW_2_WIDTH, -94, Tnaf.POW_2_WIDTH, -94, 0, 0, 1, 37, 1, 37, 0, 0, 11, 66, 11, 66, 0, 0, -40, 32, -40, 32, 0, 0, -126, -116, -126, -116, 0, 0, 36, 74, 36, 74, 0, 0, 56, 24, 56, 24, 0, 0, 42, 37, 42, 37, 0, 0, -124, -110, -124, -110, 0, 0, 85, 85, 85, 85, 0, 0, 42, 34, 42, 34, 0, 0, 49, 17, 49, 17, 0, 0, -125, 66, -125, 66, 0, 0, 6, -104, 6, -104, 0, 0, 64, -31, 64, -31, 0, 0, 44, 68, 44, 68, 0, 0, -40, 40, -40, 40, 0, 0, -110, -127, -110, -127, 0, 0, -124, 50, -124, 50, 0, 0, 104, 12, 104, 12, 0, 0};
    }

    private static void createRandomMask32_22() {
        _randomMask32_22 = new byte[]{85, 85, 85, 85, 0, 0, 42, 34, 42, 34, 0, 0, 49, 17, 49, 17, 0, 0, -125, 66, -125, 66, 0, 0, 6, -104, 6, -104, 0, 0, 64, -31, 64, -31, 0, 0, 44, 68, 44, 68, 0, 0, -40, 40, -40, 40, 0, 0, -110, -127, -110, -127, 0, 0, -124, 50, -124, 50, 0, 0, 104, 12, 104, 12, 0, 0, 69, 81, 69, 81, 0, 0, Tnaf.POW_2_WIDTH, -94, Tnaf.POW_2_WIDTH, -94, 0, 0, 1, 37, 1, 37, 0, 0, 11, 66, 11, 66, 0, 0, -40, 32, -40, 32, 0, 0, -126, -116, -126, -116, 0, 0, 36, 74, 36, 74, 0, 0, 56, 24, 56, 24, 0, 0, 42, 37, 42, 37, 0, 0, -124, -110, -124, -110, 0, 0, -21, -78, 34, -119, 0, 0};
    }

    private static void createRandomMask32_23() {
        _randomMask32_23 = new byte[]{85, 85, 85, 85, 0, 0, 42, 34, 42, 34, 0, 0, 49, 17, 49, 17, 0, 0, -125, 66, -125, 66, 0, 0, 6, -104, 6, -104, 0, 0, 64, -31, 64, -31, 0, 0, 44, 68, 44, 68, 0, 0, -40, 40, -40, 40, 0, 0, -110, -127, -110, -127, 0, 0, -124, 50, -124, 50, 0, 0, 104, 12, 104, 12, 0, 0, -124, 49, -124, 49, 0, 0, 24, -94, 24, -94, 0, 0, 78, 1, 78, 1, 0, 0, 68, -56, 68, -56, 0, 0, 14, -112, 14, -112, 0, 0, 32, -52, 32, -52, 0, 0, -109, 64, -109, 64, 0, 0, 45, Tnaf.POW_2_WIDTH, 45, Tnaf.POW_2_WIDTH, 0, 0, 49, 68, 49, 68, 0, 0, -64, 35, -64, 35, 0, 0, 17, 37, 17, 37, 0, 0, -24, ByteCompanionObject.MIN_VALUE, -24, ByteCompanionObject.MIN_VALUE, 0, 0};
    }

    private static void createRandomMask32_24() {
        _randomMask32_24 = new byte[]{-124, 49, -124, 49, 0, 0, 24, -94, 24, -94, 0, 0, 78, 1, 78, 1, 0, 0, 68, -56, 68, -56, 0, 0, 14, -112, 14, -112, 0, 0, 32, -52, 32, -52, 0, 0, -109, 64, -109, 64, 0, 0, 45, Tnaf.POW_2_WIDTH, 45, Tnaf.POW_2_WIDTH, 0, 0, 49, 68, 49, 68, 0, 0, -64, 35, -64, 35, 0, 0, 17, 37, 17, 37, 0, 0, -24, ByteCompanionObject.MIN_VALUE, -24, ByteCompanionObject.MIN_VALUE, 0, 0, 85, 85, 85, 85, 0, 0, 42, 34, 42, 34, 0, 0, 49, 17, 49, 17, 0, 0, -125, 66, -125, 66, 0, 0, 6, -104, 6, -104, 0, 0, 64, -31, 64, -31, 0, 0, 44, 68, 44, 68, 0, 0, -40, 40, -40, 40, 0, 0, -110, -127, -110, -127, 0, 0, -124, 50, -124, 50, 0, 0, 104, 12, 104, 12, 0, 0, -13, 90, 47, 93, 0, 0};
    }

    private static void createRandomMask32_25() {
        _randomMask32_25 = new byte[]{-124, 49, -124, 49, 0, 0, 24, -94, 24, -94, 0, 0, 78, 1, 78, 1, 0, 0, 68, -56, 68, -56, 0, 0, 14, -112, 14, -112, 0, 0, 32, -52, 32, -52, 0, 0, -109, 64, -109, 64, 0, 0, 45, Tnaf.POW_2_WIDTH, 45, Tnaf.POW_2_WIDTH, 0, 0, 49, 68, 49, 68, 0, 0, -64, 35, -64, 35, 0, 0, 17, 37, 17, 37, 0, 0, -24, ByteCompanionObject.MIN_VALUE, -24, ByteCompanionObject.MIN_VALUE, 0, 0, 69, 21, 69, 21, 0, 0, 34, 34, 34, 34, 0, 0, -106, 12, -106, 12, 0, 0, 12, 80, 12, 80, 0, 0, 98, 4, 98, 4, 0, 0, 73, 6, 73, 6, 0, 0, 17, -126, 17, -126, 0, 0, 18, 56, 18, 56, 0, 0, 64, 113, 64, 113, 0, 0, -88, -118, -88, -118, 0, 0, 8, -95, 8, -95, 0, 0, -96, -64, -96, -64, 0, 0, -59, Tnaf.POW_2_WIDTH, -59, Tnaf.POW_2_WIDTH, 0, 0};
    }

    private static void createRandomMask32_26() {
        _randomMask32_26 = new byte[]{69, 21, 69, 21, 0, 0, 34, 34, 34, 34, 0, 0, -106, 12, -106, 12, 0, 0, 12, 80, 12, 80, 0, 0, 98, 4, 98, 4, 0, 0, 73, 6, 73, 6, 0, 0, 17, -126, 17, -126, 0, 0, 18, 56, 18, 56, 0, 0, 64, 113, 64, 113, 0, 0, -88, -118, -88, -118, 0, 0, 8, -95, 8, -95, 0, 0, -96, -64, -96, -64, 0, 0, -59, Tnaf.POW_2_WIDTH, -59, Tnaf.POW_2_WIDTH, 0, 0, -124, 49, -124, 49, 0, 0, 24, -94, 24, -94, 0, 0, 78, 1, 78, 1, 0, 0, 68, -56, 68, -56, 0, 0, 14, -112, 14, -112, 0, 0, 32, -52, 32, -52, 0, 0, -109, 64, -109, 64, 0, 0, 45, Tnaf.POW_2_WIDTH, 45, Tnaf.POW_2_WIDTH, 0, 0, 49, 68, 49, 68, 0, 0, -64, 35, -64, 35, 0, 0, 17, 37, 17, 37, 0, 0, -24, ByteCompanionObject.MIN_VALUE, -24, ByteCompanionObject.MIN_VALUE, 0, 0, 82, 21, 98, 10, 0, 0};
    }

    private static void createRandomMask32_27() {
        _randomMask32_27 = new byte[]{69, 21, 69, 21, 0, 0, 34, 34, 34, 34, 0, 0, -106, 12, -106, 12, 0, 0, 12, 80, 12, 80, 0, 0, 98, 4, 98, 4, 0, 0, 73, 6, 73, 6, 0, 0, 17, -126, 17, -126, 0, 0, 18, 56, 18, 56, 0, 0, 64, 113, 64, 113, 0, 0, -88, -118, -88, -118, 0, 0, 8, -95, 8, -95, 0, 0, -96, -64, -96, -64, 0, 0, -59, Tnaf.POW_2_WIDTH, -59, Tnaf.POW_2_WIDTH, 0, 0, 69, 81, 69, 81, 0, 0, 34, 10, 34, 10, 0, 0, -124, -48, -124, -48, 0, 0, 12, -118, 12, -118, 0, 0, 24, 6, 24, 6, 0, 0, 48, 3, 48, 3, 0, 0, 97, 8, 97, 8, 0, 0, 64, 17, 64, 17, 0, 0, Tnaf.POW_2_WIDTH, 44, Tnaf.POW_2_WIDTH, 44, 0, 0, 9, 96, 9, 96, 0, 0, 0, -108, 0, -108, 0, 0, 82, 64, 82, 64, 0, 0, -92, 36, -92, 36, 0, 0, -126, -120, -126, -120, 0, 0};
    }

    private static void createRandomMask32_28() {
        _randomMask32_28 = new byte[]{69, 81, 69, 81, 0, 0, 34, 10, 34, 10, 0, 0, -124, -48, -124, -48, 0, 0, 12, -118, 12, -118, 0, 0, 24, 6, 24, 6, 0, 0, 48, 3, 48, 3, 0, 0, 97, 8, 97, 8, 0, 0, 64, 17, 64, 17, 0, 0, Tnaf.POW_2_WIDTH, 44, Tnaf.POW_2_WIDTH, 44, 0, 0, 9, 96, 9, 96, 0, 0, 0, -108, 0, -108, 0, 0, 82, 64, 82, 64, 0, 0, -92, 36, -92, 36, 0, 0, -126, -120, -126, -120, 0, 0, 69, 21, 69, 21, 0, 0, 34, 34, 34, 34, 0, 0, -106, 12, -106, 12, 0, 0, 12, 80, 12, 80, 0, 0, 98, 4, 98, 4, 0, 0, 73, 6, 73, 6, 0, 0, 17, -126, 17, -126, 0, 0, 18, 56, 18, 56, 0, 0, 64, 113, 64, 113, 0, 0, -88, -118, -88, -118, 0, 0, 8, -95, 8, -95, 0, 0, -96, -64, -96, -64, 0, 0, -59, Tnaf.POW_2_WIDTH, -59, Tnaf.POW_2_WIDTH, 0, 0, ByteCompanionObject.MAX_VALUE, -30, PSSSigner.TRAILER_IMPLICIT, 1, 0, 0};
    }

    private static void createRandomMask32_29() {
        _randomMask32_29 = new byte[]{69, 81, 69, 81, 0, 0, 34, 10, 34, 10, 0, 0, -124, -48, -124, -48, 0, 0, 12, -118, 12, -118, 0, 0, 24, 6, 24, 6, 0, 0, 48, 3, 48, 3, 0, 0, 97, 8, 97, 8, 0, 0, 64, 17, 64, 17, 0, 0, Tnaf.POW_2_WIDTH, 44, Tnaf.POW_2_WIDTH, 44, 0, 0, 9, 96, 9, 96, 0, 0, 0, -108, 0, -108, 0, 0, 82, 64, 82, 64, 0, 0, -92, 36, -92, 36, 0, 0, -126, -120, -126, -120, 0, 0, 85, 17, 85, 17, 0, 0, 34, 34, 34, 34, 0, 0, 17, 17, 17, 17, 0, 0, ByteCompanionObject.MIN_VALUE, 69, ByteCompanionObject.MIN_VALUE, 69, 0, 0, 32, 26, 32, 26, 0, 0, 8, 104, 8, 104, 0, 0, 34, -124, 34, -124, 0, 0, 72, 9, 72, 9, 0, 0, 7, 1, 7, 1, 0, 0, -108, 32, -108, 32, 0, 0, -126, 6, -126, 6, 0, 0, 96, 72, 96, 72, 0, 0, -119, ByteCompanionObject.MIN_VALUE, -119, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -114, 0, -114, 0, 0, 24, 34, 24, 34, 0, 0};
    }

    private static void createRandomMask32_3() {
        _randomMask32_3 = new byte[]{-83, 45, -83, 45, 0, 0, 118, 54, 118, 54, 0, 0, 38, -37, 38, -37, 0, 0};
    }

    private static void createRandomMask32_30() {
        _randomMask32_30 = new byte[]{85, 17, 85, 17, 0, 0, 34, 34, 34, 34, 0, 0, 17, 17, 17, 17, 0, 0, ByteCompanionObject.MIN_VALUE, 69, ByteCompanionObject.MIN_VALUE, 69, 0, 0, 32, 26, 32, 26, 0, 0, 8, 104, 8, 104, 0, 0, 34, -124, 34, -124, 0, 0, 72, 9, 72, 9, 0, 0, 7, 1, 7, 1, 0, 0, -108, 32, -108, 32, 0, 0, -126, 6, -126, 6, 0, 0, 96, 72, 96, 72, 0, 0, -119, ByteCompanionObject.MIN_VALUE, -119, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -114, 0, -114, 0, 0, 24, 34, 24, 34, 0, 0, 69, 81, 69, 81, 0, 0, 34, 10, 34, 10, 0, 0, -124, -48, -124, -48, 0, 0, 12, -118, 12, -118, 0, 0, 24, 6, 24, 6, 0, 0, 48, 3, 48, 3, 0, 0, 97, 8, 97, 8, 0, 0, 64, 17, 64, 17, 0, 0, Tnaf.POW_2_WIDTH, 44, Tnaf.POW_2_WIDTH, 44, 0, 0, 9, 96, 9, 96, 0, 0, 0, -108, 0, -108, 0, 0, 82, 64, 82, 64, 0, 0, -92, 36, -92, 36, 0, 0, -126, -120, -126, -120, 0, 0, 30, 39, -30, -40, 0, 0};
    }

    private static void createRandomMask32_31() {
        _randomMask32_31 = new byte[]{85, 17, 85, 17, 0, 0, 34, 34, 34, 34, 0, 0, 17, 17, 17, 17, 0, 0, ByteCompanionObject.MIN_VALUE, 69, ByteCompanionObject.MIN_VALUE, 69, 0, 0, 32, 26, 32, 26, 0, 0, 8, 104, 8, 104, 0, 0, 34, -124, 34, -124, 0, 0, 72, 9, 72, 9, 0, 0, 7, 1, 7, 1, 0, 0, -108, 32, -108, 32, 0, 0, -126, 6, -126, 6, 0, 0, 96, 72, 96, 72, 0, 0, -119, ByteCompanionObject.MIN_VALUE, -119, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -114, 0, -114, 0, 0, 24, 34, 24, 34, 0, 0, -92, Tnaf.POW_2_WIDTH, -92, Tnaf.POW_2_WIDTH, 0, 0, 1, 42, 1, 42, 0, 0, 6, 66, 6, 66, 0, 0, 8, 104, 8, 104, 0, 0, -127, -112, -127, -112, 0, 0, 0, -16, 0, -16, 0, 0, 80, 5, 80, 5, 0, 0, 32, 81, 32, 81, 0, 0, 67, 8, 67, 8, 0, 0, 104, ByteCompanionObject.MIN_VALUE, 104, ByteCompanionObject.MIN_VALUE, 0, 0, ByteCompanionObject.MIN_VALUE, 11, ByteCompanionObject.MIN_VALUE, 11, 0, 0, Tnaf.POW_2_WIDTH, 76, Tnaf.POW_2_WIDTH, 76, 0, 0, 18, 48, 18, 48, 0, 0, 64, -123, 64, -123, 0, 0, 14, 4, 14, 4, 0, 0, 24, 18, 24, 18, 0, 0};
    }

    private static void createRandomMask32_32() {
        _randomMask32_32 = new byte[]{-92, Tnaf.POW_2_WIDTH, -92, Tnaf.POW_2_WIDTH, 0, 0, 1, 42, 1, 42, 0, 0, 6, 66, 6, 66, 0, 0, 8, 104, 8, 104, 0, 0, -127, -112, -127, -112, 0, 0, 0, -16, 0, -16, 0, 0, 80, 5, 80, 5, 0, 0, 32, 81, 32, 81, 0, 0, 67, 8, 67, 8, 0, 0, 104, ByteCompanionObject.MIN_VALUE, 104, ByteCompanionObject.MIN_VALUE, 0, 0, ByteCompanionObject.MIN_VALUE, 11, ByteCompanionObject.MIN_VALUE, 11, 0, 0, Tnaf.POW_2_WIDTH, 76, Tnaf.POW_2_WIDTH, 76, 0, 0, 18, 48, 18, 48, 0, 0, 64, -123, 64, -123, 0, 0, 14, 4, 14, 4, 0, 0, 24, 18, 24, 18, 0, 0, 85, 17, 85, 17, 0, 0, 34, 34, 34, 34, 0, 0, 17, 17, 17, 17, 0, 0, ByteCompanionObject.MIN_VALUE, 69, ByteCompanionObject.MIN_VALUE, 69, 0, 0, 32, 26, 32, 26, 0, 0, 8, 104, 8, 104, 0, 0, 34, -124, 34, -124, 0, 0, 72, 9, 72, 9, 0, 0, 7, 1, 7, 1, 0, 0, -108, 32, -108, 32, 0, 0, -126, 6, -126, 6, 0, 0, 96, 72, 96, 72, 0, 0, -119, ByteCompanionObject.MIN_VALUE, -119, ByteCompanionObject.MIN_VALUE, 0, 0, 0, -114, 0, -114, 0, 0, 24, 34, 24, 34, 0, 0, 96, -60, 2, 2, 0, 0};
    }

    private static void createRandomMask32_4() {
        _randomMask32_4 = new byte[]{85, 85, 85, 85, 0, 0, -86, -86, -86, -86, 0, 0, 53, 53, 53, 53, 0, 0, -54, -54, -54, -54, 0, 0};
    }

    private static void createRandomMask32_5() {
        _randomMask32_5 = new byte[]{85, 85, 85, 85, 0, 0, 42, 42, 42, 42, 0, 0, 36, 37, 36, 37, 0, 0, -124, -56, -124, -56, 0, 0, Tnaf.POW_2_WIDTH, -74, Tnaf.POW_2_WIDTH, -74, 0, 0};
    }

    private static void createRandomMask32_6() {
        _randomMask32_6 = new byte[]{81, 81, 81, 81, 0, 0, 10, 42, 10, 42, 0, 0, -94, 21, -94, 21, 0, 0, -124, 74, -124, 74, 0, 0, 48, -110, 48, -110, 0, 0, 4, -84, 4, -84, 0, 0};
    }

    private static void createRandomMask32_7() {
        _randomMask32_7 = new byte[]{69, 81, 69, 81, 0, 0, 34, 42, 34, 42, 0, 0, -111, 17, -111, 17, 0, 0, 46, 8, 46, 8, 0, 0, 72, 52, 72, 52, 0, 0, -112, 41, -112, 41, 0, 0, 9, -122, 9, -122, 0, 0};
    }

    private static void createRandomMask32_8() {
        _randomMask32_8 = new byte[]{32, 84, 32, 84, 0, 0, 24, -120, 24, -120, 0, 0, -124, 7, -124, 7, 0, 0, 96, 72, 96, 72, 0, 0, 18, -126, 18, -126, 0, 0, -127, 65, -127, 65, 0, 0, 64, 98, 64, 98, 0, 0, 22, 48, 22, 48, 0, 0};
    }

    private static void createRandomMask32_9() {
        _randomMask32_9 = new byte[]{85, 81, 85, 81, 0, 0, 34, 42, 34, 42, 0, 0, 5, -123, 5, -123, 0, 0, 9, 74, 9, 74, 0, 0, -124, 50, -124, 50, 0, 0, -64, 13, -64, 13, 0, 0, 32, -90, 32, -90, 0, 0, 26, 9, 26, 9, 0, 0, 68, 100, 68, 100, 0, 0};
    }

    private static void createRandomMask33_1() {
        _randomMask33_1 = new byte[]{-1, -1, -1, -1, ByteCompanionObject.MIN_VALUE, 0};
    }

    private static void createRandomMask33_10() {
        _randomMask33_10 = new byte[]{69, 81, 85, -116, ByteCompanionObject.MIN_VALUE, 0, Tnaf.POW_2_WIDTH, -94, -86, 39, 0, 0, 1, 37, -91, 50, ByteCompanionObject.MIN_VALUE, 0, 11, 66, 98, 97, ByteCompanionObject.MIN_VALUE, 0, -40, 32, 60, 92, 0, 0, -126, -116, -114, -52, 0, 0, 36, 74, 106, 43, 0, 0, 56, 24, 54, 50, ByteCompanionObject.MIN_VALUE, 0, 42, 37, -47, 37, ByteCompanionObject.MIN_VALUE, 0, -124, -110, -56, 2, ByteCompanionObject.MIN_VALUE, 0};
    }

    private static void createRandomMask33_11() {
        _randomMask33_11 = new byte[]{85, 85, 85, -116, ByteCompanionObject.MIN_VALUE, 0, 42, 34, -86, 39, 0, 0, 49, 17, -91, 50, ByteCompanionObject.MIN_VALUE, 0, -125, 66, 98, 97, ByteCompanionObject.MIN_VALUE, 0, 6, -104, 60, 92, 0, 0, 64, -31, 81, -124, ByteCompanionObject.MIN_VALUE, 0, 44, 68, -94, 39, 0, 0, -40, 40, -107, 81, ByteCompanionObject.MIN_VALUE, 0, -110, -127, 74, 26, 0, 0, -124, 50, 48, 104, 0, 0, 104, 12, 44, -119, 0, 0};
    }

    private static void createRandomMask33_12() {
        _randomMask33_12 = new byte[]{-124, 49, 81, -124, ByteCompanionObject.MIN_VALUE, 0, 24, -94, -94, 39, 0, 0, 78, 1, -107, 81, ByteCompanionObject.MIN_VALUE, 0, 68, -56, 74, 26, 0, 0, 14, -112, 48, 104, 0, 0, 32, -52, 44, -119, 0, 0, -109, 64, 85, -116, ByteCompanionObject.MIN_VALUE, 0, 45, Tnaf.POW_2_WIDTH, -86, 39, 0, 0, 49, 68, -91, 50, ByteCompanionObject.MIN_VALUE, 0, -64, 35, 98, 97, ByteCompanionObject.MIN_VALUE, 0, 17, 37, 60, 92, 0, 0, -24, ByteCompanionObject.MIN_VALUE, 81, 53, 0, 0};
    }

    private static void createRandomMask33_13() {
        _randomMask33_13 = new byte[]{69, 21, 81, -124, ByteCompanionObject.MIN_VALUE, 0, 34, 34, -94, 39, 0, 0, -106, 12, -107, 81, ByteCompanionObject.MIN_VALUE, 0, 12, 80, 74, 26, 0, 0, 98, 4, 48, 104, 0, 0, 73, 6, 44, -119, 0, 0, 17, -126, 21, -116, 0, 0, 18, 56, -118, 71, 0, 0, 64, 113, 37, -127, ByteCompanionObject.MIN_VALUE, 0, -88, -118, 98, 18, ByteCompanionObject.MIN_VALUE, 0, 8, -95, 88, 88, 0, 0, -96, -64, 14, 40, ByteCompanionObject.MIN_VALUE, 0, -59, Tnaf.POW_2_WIDTH, -125, 52, 0, 0};
    }

    private static void createRandomMask33_14() {
        _randomMask33_14 = new byte[]{69, 81, 21, -116, 0, 0, 34, 10, -118, 71, 0, 0, -124, -48, 37, -127, ByteCompanionObject.MIN_VALUE, 0, 12, -118, 98, 18, ByteCompanionObject.MIN_VALUE, 0, 24, 6, 88, 88, 0, 0, 48, 3, 14, 40, ByteCompanionObject.MIN_VALUE, 0, 97, 8, -125, 52, 0, 0, 64, 17, 81, -124, ByteCompanionObject.MIN_VALUE, 0, Tnaf.POW_2_WIDTH, 44, -94, 39, 0, 0, 9, 96, -107, 81, ByteCompanionObject.MIN_VALUE, 0, 0, -108, 74, 26, 0, 0, 82, 64, 48, 104, 0, 0, -92, 36, 44, -119, 0, 0, -126, -120, -80, -34, ByteCompanionObject.MIN_VALUE, 0};
    }

    private static void createRandomMask33_15() {
        _randomMask33_15 = new byte[]{85, 17, 21, -116, 0, 0, 34, 34, -118, 71, 0, 0, 17, 17, 37, -127, ByteCompanionObject.MIN_VALUE, 0, ByteCompanionObject.MIN_VALUE, 69, 98, 18, ByteCompanionObject.MIN_VALUE, 0, 32, 26, 88, 88, 0, 0, 8, 104, 14, 40, ByteCompanionObject.MIN_VALUE, 0, 34, -124, -125, 52, 0, 0, 72, 9, 37, 44, 0, 0, 7, 1, -118, -111, 0, 0, -108, 32, -111, -64, ByteCompanionObject.MIN_VALUE, 0, -126, 6, 104, 6, ByteCompanionObject.MIN_VALUE, 0, 96, 72, 50, -56, 0, 0, -119, ByteCompanionObject.MIN_VALUE, 67, 69, 0, 0, 0, -114, -60, 48, ByteCompanionObject.MIN_VALUE, 0, 24, 34, 28, -94, 0, 0};
    }

    private static void createRandomMask33_16() {
        _randomMask33_16 = new byte[]{-92, Tnaf.POW_2_WIDTH, 37, 44, 0, 0, 1, 42, -118, -111, 0, 0, 6, 66, -111, -64, ByteCompanionObject.MIN_VALUE, 0, 8, 104, 104, 6, ByteCompanionObject.MIN_VALUE, 0, -127, -112, 50, -56, 0, 0, 0, -16, 67, 69, 0, 0, 80, 5, -60, 48, ByteCompanionObject.MIN_VALUE, 0, 32, 81, 28, -94, 0, 0, 67, 8, 21, -116, 0, 0, 104, ByteCompanionObject.MIN_VALUE, -118, 71, 0, 0, ByteCompanionObject.MIN_VALUE, 11, 37, -127, ByteCompanionObject.MIN_VALUE, 0, Tnaf.POW_2_WIDTH, 76, 98, 18, ByteCompanionObject.MIN_VALUE, 0, 18, 48, 88, 88, 0, 0, 64, -123, 14, 40, ByteCompanionObject.MIN_VALUE, 0, 14, 4, -125, 52, 0, 0, 24, 18, 10, 28, 0, 0};
    }

    private static void createRandomMask33_17() {
        _randomMask33_17 = new byte[]{32, 84, 100, 22, 0, 0, 24, -120, -94, -62, 0, 0, -124, 7, 81, 96, ByteCompanionObject.MIN_VALUE, 0, 96, 72, 74, -123, 0, 0, 18, -126, 56, 76, 0, 0, -127, 65, -119, 41, 0, 0, 64, 98, 7, 17, ByteCompanionObject.MIN_VALUE, 0, 22, 48, -108, -80, 0, 0, 85, 81, -114, -52, 0, 0, 34, 42, 106, 43, 0, 0, 5, -123, 54, 50, ByteCompanionObject.MIN_VALUE, 0, 9, 74, -47, 37, ByteCompanionObject.MIN_VALUE, 0, -124, 50, 85, -116, ByteCompanionObject.MIN_VALUE, 0, -64, 13, -86, 39, 0, 0, 32, -90, -91, 50, ByteCompanionObject.MIN_VALUE, 0, 26, 9, 98, 97, ByteCompanionObject.MIN_VALUE, 0, 68, 100, 60, 92, 0, 0};
    }

    private static void createRandomMask33_18() {
        _randomMask33_18 = new byte[]{85, 81, -114, -52, 0, 0, 34, 42, 106, 43, 0, 0, 5, -123, 54, 50, ByteCompanionObject.MIN_VALUE, 0, 9, 74, -47, 37, ByteCompanionObject.MIN_VALUE, 0, -124, 50, 85, -116, ByteCompanionObject.MIN_VALUE, 0, -64, 13, -86, 39, 0, 0, 32, -90, -91, 50, ByteCompanionObject.MIN_VALUE, 0, 26, 9, 98, 97, ByteCompanionObject.MIN_VALUE, 0, 68, 100, 60, 92, 0, 0, 32, 84, 100, 22, 0, 0, 24, -120, -94, -62, 0, 0, -124, 7, 81, 96, ByteCompanionObject.MIN_VALUE, 0, 96, 72, 74, -123, 0, 0, 18, -126, 56, 76, 0, 0, -127, 65, -119, 41, 0, 0, 64, 98, 7, 17, ByteCompanionObject.MIN_VALUE, 0, 22, 48, -108, -80, 0, 0, -119, 83, 3, -83, 0, 0};
    }

    private static void createRandomMask33_19() {
        _randomMask33_19 = new byte[]{85, 81, -114, -52, 0, 0, 34, 42, 106, 43, 0, 0, 5, -123, 54, 50, ByteCompanionObject.MIN_VALUE, 0, 9, 74, -47, 37, ByteCompanionObject.MIN_VALUE, 0, -124, 50, 85, -116, ByteCompanionObject.MIN_VALUE, 0, -64, 13, -86, 39, 0, 0, 32, -90, -91, 50, ByteCompanionObject.MIN_VALUE, 0, 26, 9, 98, 97, ByteCompanionObject.MIN_VALUE, 0, 68, 100, 60, 92, 0, 0, 69, 81, 85, -116, ByteCompanionObject.MIN_VALUE, 0, Tnaf.POW_2_WIDTH, -94, -86, 39, 0, 0, 1, 37, -91, 50, ByteCompanionObject.MIN_VALUE, 0, 11, 66, 98, 97, ByteCompanionObject.MIN_VALUE, 0, -40, 32, 60, 92, 0, 0, -126, -116, -114, -52, 0, 0, 36, 74, 106, 43, 0, 0, 56, 24, 54, 50, ByteCompanionObject.MIN_VALUE, 0, 42, 37, -47, 37, ByteCompanionObject.MIN_VALUE, 0, -124, -110, -56, 2, ByteCompanionObject.MIN_VALUE, 0};
    }

    private static void createRandomMask33_2() {
        _randomMask33_2 = new byte[]{-82, -82, -50, -50, 0, 0, 121, 121, -71, 57, ByteCompanionObject.MIN_VALUE, 0};
    }

    private static void createRandomMask33_20() {
        _randomMask33_20 = new byte[]{69, 81, 85, -116, ByteCompanionObject.MIN_VALUE, 0, Tnaf.POW_2_WIDTH, -94, -86, 39, 0, 0, 1, 37, -91, 50, ByteCompanionObject.MIN_VALUE, 0, 11, 66, 98, 97, ByteCompanionObject.MIN_VALUE, 0, -40, 32, 60, 92, 0, 0, -126, -116, -114, -52, 0, 0, 36, 74, 106, 43, 0, 0, 56, 24, 54, 50, ByteCompanionObject.MIN_VALUE, 0, 42, 37, -47, 37, ByteCompanionObject.MIN_VALUE, 0, -124, -110, -56, 2, ByteCompanionObject.MIN_VALUE, 0, 85, 81, -114, -52, 0, 0, 34, 42, 106, 43, 0, 0, 5, -123, 54, 50, ByteCompanionObject.MIN_VALUE, 0, 9, 74, -47, 37, ByteCompanionObject.MIN_VALUE, 0, -124, 50, 85, -116, ByteCompanionObject.MIN_VALUE, 0, -64, 13, -86, 39, 0, 0, 32, -90, -91, 50, ByteCompanionObject.MIN_VALUE, 0, 26, 9, 98, 97, ByteCompanionObject.MIN_VALUE, 0, 68, 100, 60, 92, 0, 0, 115, 95, 91, 14, ByteCompanionObject.MIN_VALUE, 0};
    }

    private static void createRandomMask33_21() {
        _randomMask33_21 = new byte[]{69, 81, 85, -116, ByteCompanionObject.MIN_VALUE, 0, Tnaf.POW_2_WIDTH, -94, -86, 39, 0, 0, 1, 37, -91, 50, ByteCompanionObject.MIN_VALUE, 0, 11, 66, 98, 97, ByteCompanionObject.MIN_VALUE, 0, -40, 32, 60, 92, 0, 0, -126, -116, -114, -52, 0, 0, 36, 74, 106, 43, 0, 0, 56, 24, 54, 50, ByteCompanionObject.MIN_VALUE, 0, 42, 37, -47, 37, ByteCompanionObject.MIN_VALUE, 0, -124, -110, -56, 2, ByteCompanionObject.MIN_VALUE, 0, 85, 85, 85, -116, ByteCompanionObject.MIN_VALUE, 0, 42, 34, -86, 39, 0, 0, 49, 17, -91, 50, ByteCompanionObject.MIN_VALUE, 0, -125, 66, 98, 97, ByteCompanionObject.MIN_VALUE, 0, 6, -104, 60, 92, 0, 0, 64, -31, 81, -124, ByteCompanionObject.MIN_VALUE, 0, 44, 68, -94, 39, 0, 0, -40, 40, -107, 81, ByteCompanionObject.MIN_VALUE, 0, -110, -127, 74, 26, 0, 0, -124, 50, 48, 104, 0, 0, 104, 12, 44, -119, 0, 0};
    }

    private static void createRandomMask33_22() {
        _randomMask33_22 = new byte[]{85, 85, 85, -116, ByteCompanionObject.MIN_VALUE, 0, 42, 34, -86, 39, 0, 0, 49, 17, -91, 50, ByteCompanionObject.MIN_VALUE, 0, -125, 66, 98, 97, ByteCompanionObject.MIN_VALUE, 0, 6, -104, 60, 92, 0, 0, 64, -31, 81, -124, ByteCompanionObject.MIN_VALUE, 0, 44, 68, -94, 39, 0, 0, -40, 40, -107, 81, ByteCompanionObject.MIN_VALUE, 0, -110, -127, 74, 26, 0, 0, -124, 50, 48, 104, 0, 0, 104, 12, 44, -119, 0, 0, 69, 81, 85, -116, ByteCompanionObject.MIN_VALUE, 0, Tnaf.POW_2_WIDTH, -94, -86, 39, 0, 0, 1, 37, -91, 50, ByteCompanionObject.MIN_VALUE, 0, 11, 66, 98, 97, ByteCompanionObject.MIN_VALUE, 0, -40, 32, 60, 92, 0, 0, -126, -116, -114, -52, 0, 0, 36, 74, 106, 43, 0, 0, 56, 24, 54, 50, ByteCompanionObject.MIN_VALUE, 0, 42, 37, -47, 37, ByteCompanionObject.MIN_VALUE, 0, -124, -110, -56, 2, ByteCompanionObject.MIN_VALUE, 0, -52, -29, 66, 107, ByteCompanionObject.MIN_VALUE, 0};
    }

    private static void createRandomMask33_23() {
        _randomMask33_23 = new byte[]{85, 85, 85, -116, ByteCompanionObject.MIN_VALUE, 0, 42, 34, -86, 39, 0, 0, 49, 17, -91, 50, ByteCompanionObject.MIN_VALUE, 0, -125, 66, 98, 97, ByteCompanionObject.MIN_VALUE, 0, 6, -104, 60, 92, 0, 0, 64, -31, 81, -124, ByteCompanionObject.MIN_VALUE, 0, 44, 68, -94, 39, 0, 0, -40, 40, -107, 81, ByteCompanionObject.MIN_VALUE, 0, -110, -127, 74, 26, 0, 0, -124, 50, 48, 104, 0, 0, 104, 12, 44, -119, 0, 0, -124, 49, 81, -124, ByteCompanionObject.MIN_VALUE, 0, 24, -94, -94, 39, 0, 0, 78, 1, -107, 81, ByteCompanionObject.MIN_VALUE, 0, 68, -56, 74, 26, 0, 0, 14, -112, 48, 104, 0, 0, 32, -52, 44, -119, 0, 0, -109, 64, 85, -116, ByteCompanionObject.MIN_VALUE, 0, 45, Tnaf.POW_2_WIDTH, -86, 39, 0, 0, 49, 68, -91, 50, ByteCompanionObject.MIN_VALUE, 0, -64, 35, 98, 97, ByteCompanionObject.MIN_VALUE, 0, 17, 37, 60, 92, 0, 0, -24, ByteCompanionObject.MIN_VALUE, 81, 53, 0, 0};
    }

    private static void createRandomMask33_24() {
        _randomMask33_24 = new byte[]{-124, 49, 81, -124, ByteCompanionObject.MIN_VALUE, 0, 24, -94, -94, 39, 0, 0, 78, 1, -107, 81, ByteCompanionObject.MIN_VALUE, 0, 68, -56, 74, 26, 0, 0, 14, -112, 48, 104, 0, 0, 32, -52, 44, -119, 0, 0, -109, 64, 85, -116, ByteCompanionObject.MIN_VALUE, 0, 45, Tnaf.POW_2_WIDTH, -86, 39, 0, 0, 49, 68, -91, 50, ByteCompanionObject.MIN_VALUE, 0, -64, 35, 98, 97, ByteCompanionObject.MIN_VALUE, 0, 17, 37, 60, 92, 0, 0, -24, ByteCompanionObject.MIN_VALUE, 81, 53, 0, 0, 85, 85, 85, -116, ByteCompanionObject.MIN_VALUE, 0, 42, 34, -86, 39, 0, 0, 49, 17, -91, 50, ByteCompanionObject.MIN_VALUE, 0, -125, 66, 98, 97, ByteCompanionObject.MIN_VALUE, 0, 6, -104, 60, 92, 0, 0, 64, -31, 81, -124, ByteCompanionObject.MIN_VALUE, 0, 44, 68, -94, 39, 0, 0, -40, 40, -107, 81, ByteCompanionObject.MIN_VALUE, 0, -110, -127, 74, 26, 0, 0, -124, 50, 48, 104, 0, 0, 104, 12, 44, -119, 0, 0, -36, 78, -4, 112, 0, 0};
    }

    private static void createRandomMask33_25() {
        _randomMask33_25 = new byte[]{-124, 49, 81, -124, ByteCompanionObject.MIN_VALUE, 0, 24, -94, -94, 39, 0, 0, 78, 1, -107, 81, ByteCompanionObject.MIN_VALUE, 0, 68, -56, 74, 26, 0, 0, 14, -112, 48, 104, 0, 0, 32, -52, 44, -119, 0, 0, -109, 64, 85, -116, ByteCompanionObject.MIN_VALUE, 0, 45, Tnaf.POW_2_WIDTH, -86, 39, 0, 0, 49, 68, -91, 50, ByteCompanionObject.MIN_VALUE, 0, -64, 35, 98, 97, ByteCompanionObject.MIN_VALUE, 0, 17, 37, 60, 92, 0, 0, -24, ByteCompanionObject.MIN_VALUE, 81, 53, 0, 0, 69, 21, 81, -124, ByteCompanionObject.MIN_VALUE, 0, 34, 34, -94, 39, 0, 0, -106, 12, -107, 81, ByteCompanionObject.MIN_VALUE, 0, 12, 80, 74, 26, 0, 0, 98, 4, 48, 104, 0, 0, 73, 6, 44, -119, 0, 0, 17, -126, 21, -116, 0, 0, 18, 56, -118, 71, 0, 0, 64, 113, 37, -127, ByteCompanionObject.MIN_VALUE, 0, -88, -118, 98, 18, ByteCompanionObject.MIN_VALUE, 0, 8, -95, 88, 88, 0, 0, -96, -64, 14, 40, ByteCompanionObject.MIN_VALUE, 0, -59, Tnaf.POW_2_WIDTH, -125, 52, 0, 0};
    }

    private static void createRandomMask33_26() {
        _randomMask33_26 = new byte[]{69, 21, 81, -124, ByteCompanionObject.MIN_VALUE, 0, 34, 34, -94, 39, 0, 0, -106, 12, -107, 81, ByteCompanionObject.MIN_VALUE, 0, 12, 80, 74, 26, 0, 0, 98, 4, 48, 104, 0, 0, 73, 6, 44, -119, 0, 0, 17, -126, 21, -116, 0, 0, 18, 56, -118, 71, 0, 0, 64, 113, 37, -127, ByteCompanionObject.MIN_VALUE, 0, -88, -118, 98, 18, ByteCompanionObject.MIN_VALUE, 0, 8, -95, 88, 88, 0, 0, -96, -64, 14, 40, ByteCompanionObject.MIN_VALUE, 0, -59, Tnaf.POW_2_WIDTH, -125, 52, 0, 0, -124, 49, 81, -124, ByteCompanionObject.MIN_VALUE, 0, 24, -94, -94, 39, 0, 0, 78, 1, -107, 81, ByteCompanionObject.MIN_VALUE, 0, 68, -56, 74, 26, 0, 0, 14, -112, 48, 104, 0, 0, 32, -52, 44, -119, 0, 0, -109, 64, 85, -116, ByteCompanionObject.MIN_VALUE, 0, 45, Tnaf.POW_2_WIDTH, -86, 39, 0, 0, 49, 68, -91, 50, ByteCompanionObject.MIN_VALUE, 0, -64, 35, 98, 97, ByteCompanionObject.MIN_VALUE, 0, 17, 37, 60, 92, 0, 0, -24, ByteCompanionObject.MIN_VALUE, 81, 53, 0, 0, -92, -92, -4, -111, 0, 0};
    }

    private static void createRandomMask33_27() {
        _randomMask33_27 = new byte[]{69, 21, 81, -124, ByteCompanionObject.MIN_VALUE, 0, 34, 34, -94, 39, 0, 0, -106, 12, -107, 81, ByteCompanionObject.MIN_VALUE, 0, 12, 80, 74, 26, 0, 0, 98, 4, 48, 104, 0, 0, 73, 6, 44, -119, 0, 0, 17, -126, 21, -116, 0, 0, 18, 56, -118, 71, 0, 0, 64, 113, 37, -127, ByteCompanionObject.MIN_VALUE, 0, -88, -118, 98, 18, ByteCompanionObject.MIN_VALUE, 0, 8, -95, 88, 88, 0, 0, -96, -64, 14, 40, ByteCompanionObject.MIN_VALUE, 0, -59, Tnaf.POW_2_WIDTH, -125, 52, 0, 0, 69, 81, 21, -116, 0, 0, 34, 10, -118, 71, 0, 0, -124, -48, 37, -127, ByteCompanionObject.MIN_VALUE, 0, 12, -118, 98, 18, ByteCompanionObject.MIN_VALUE, 0, 24, 6, 88, 88, 0, 0, 48, 3, 14, 40, ByteCompanionObject.MIN_VALUE, 0, 97, 8, -125, 52, 0, 0, 64, 17, 81, -124, ByteCompanionObject.MIN_VALUE, 0, Tnaf.POW_2_WIDTH, 44, -94, 39, 0, 0, 9, 96, -107, 81, ByteCompanionObject.MIN_VALUE, 0, 0, -108, 74, 26, 0, 0, 82, 64, 48, 104, 0, 0, -92, 36, 44, -119, 0, 0, -126, -120, -80, -34, ByteCompanionObject.MIN_VALUE, 0};
    }

    private static void createRandomMask33_28() {
        _randomMask33_28 = new byte[]{69, 81, 21, -116, 0, 0, 34, 10, -118, 71, 0, 0, -124, -48, 37, -127, ByteCompanionObject.MIN_VALUE, 0, 12, -118, 98, 18, ByteCompanionObject.MIN_VALUE, 0, 24, 6, 88, 88, 0, 0, 48, 3, 14, 40, ByteCompanionObject.MIN_VALUE, 0, 97, 8, -125, 52, 0, 0, 64, 17, 81, -124, ByteCompanionObject.MIN_VALUE, 0, Tnaf.POW_2_WIDTH, 44, -94, 39, 0, 0, 9, 96, -107, 81, ByteCompanionObject.MIN_VALUE, 0, 0, -108, 74, 26, 0, 0, 82, 64, 48, 104, 0, 0, -92, 36, 44, -119, 0, 0, -126, -120, -80, -34, ByteCompanionObject.MIN_VALUE, 0, 69, 21, 81, -124, ByteCompanionObject.MIN_VALUE, 0, 34, 34, -94, 39, 0, 0, -106, 12, -107, 81, ByteCompanionObject.MIN_VALUE, 0, 12, 80, 74, 26, 0, 0, 98, 4, 48, 104, 0, 0, 73, 6, 44, -119, 0, 0, 17, -126, 21, -116, 0, 0, 18, 56, -118, 71, 0, 0, 64, 113, 37, -127, ByteCompanionObject.MIN_VALUE, 0, -88, -118, 98, 18, ByteCompanionObject.MIN_VALUE, 0, 8, -95, 88, 88, 0, 0, -96, -64, 14, 40, ByteCompanionObject.MIN_VALUE, 0, -59, Tnaf.POW_2_WIDTH, -125, 52, 0, 0, 27, -12, -86, -20, ByteCompanionObject.MIN_VALUE, 0};
    }

    private static void createRandomMask33_29() {
        _randomMask33_29 = new byte[]{69, 81, 21, -116, 0, 0, 34, 10, -118, 71, 0, 0, -124, -48, 37, -127, ByteCompanionObject.MIN_VALUE, 0, 12, -118, 98, 18, ByteCompanionObject.MIN_VALUE, 0, 24, 6, 88, 88, 0, 0, 48, 3, 14, 40, ByteCompanionObject.MIN_VALUE, 0, 97, 8, -125, 52, 0, 0, 64, 17, 81, -124, ByteCompanionObject.MIN_VALUE, 0, Tnaf.POW_2_WIDTH, 44, -94, 39, 0, 0, 9, 96, -107, 81, ByteCompanionObject.MIN_VALUE, 0, 0, -108, 74, 26, 0, 0, 82, 64, 48, 104, 0, 0, -92, 36, 44, -119, 0, 0, -126, -120, -80, -34, ByteCompanionObject.MIN_VALUE, 0, 85, 17, 21, -116, 0, 0, 34, 34, -118, 71, 0, 0, 17, 17, 37, -127, ByteCompanionObject.MIN_VALUE, 0, ByteCompanionObject.MIN_VALUE, 69, 98, 18, ByteCompanionObject.MIN_VALUE, 0, 32, 26, 88, 88, 0, 0, 8, 104, 14, 40, ByteCompanionObject.MIN_VALUE, 0, 34, -124, -125, 52, 0, 0, 72, 9, 37, 44, 0, 0, 7, 1, -118, -111, 0, 0, -108, 32, -111, -64, ByteCompanionObject.MIN_VALUE, 0, -126, 6, 104, 6, ByteCompanionObject.MIN_VALUE, 0, 96, 72, 50, -56, 0, 0, -119, ByteCompanionObject.MIN_VALUE, 67, 69, 0, 0, 0, -114, -60, 48, ByteCompanionObject.MIN_VALUE, 0, 24, 34, 28, -94, 0, 0};
    }

    private static void createRandomMask33_3() {
        _randomMask33_3 = new byte[]{-83, 45, -51, -52, 0, 0, 118, 54, -105, 39, 0, 0, 38, -37, -72, -47, ByteCompanionObject.MIN_VALUE, 0};
    }

    private static void createRandomMask33_30() {
        _randomMask33_30 = new byte[]{85, 17, 21, -116, 0, 0, 34, 34, -118, 71, 0, 0, 17, 17, 37, -127, ByteCompanionObject.MIN_VALUE, 0, ByteCompanionObject.MIN_VALUE, 69, 98, 18, ByteCompanionObject.MIN_VALUE, 0, 32, 26, 88, 88, 0, 0, 8, 104, 14, 40, ByteCompanionObject.MIN_VALUE, 0, 34, -124, -125, 52, 0, 0, 72, 9, 37, 44, 0, 0, 7, 1, -118, -111, 0, 0, -108, 32, -111, -64, ByteCompanionObject.MIN_VALUE, 0, -126, 6, 104, 6, ByteCompanionObject.MIN_VALUE, 0, 96, 72, 50, -56, 0, 0, -119, ByteCompanionObject.MIN_VALUE, 67, 69, 0, 0, 0, -114, -60, 48, ByteCompanionObject.MIN_VALUE, 0, 24, 34, 28, -94, 0, 0, 69, 81, 21, -116, 0, 0, 34, 10, -118, 71, 0, 0, -124, -48, 37, -127, ByteCompanionObject.MIN_VALUE, 0, 12, -118, 98, 18, ByteCompanionObject.MIN_VALUE, 0, 24, 6, 88, 88, 0, 0, 48, 3, 14, 40, ByteCompanionObject.MIN_VALUE, 0, 97, 8, -125, 52, 0, 0, 64, 17, 81, -124, ByteCompanionObject.MIN_VALUE, 0, Tnaf.POW_2_WIDTH, 44, -94, 39, 0, 0, 9, 96, -107, 81, ByteCompanionObject.MIN_VALUE, 0, 0, -108, 74, 26, 0, 0, 82, 64, 48, 104, 0, 0, -92, 36, 44, -119, 0, 0, -126, -120, -80, -34, ByteCompanionObject.MIN_VALUE, 0, 109, -46, -116, 0, 0, 0};
    }

    private static void createRandomMask33_31() {
        _randomMask33_31 = new byte[]{85, 17, 21, -116, 0, 0, 34, 34, -118, 71, 0, 0, 17, 17, 37, -127, ByteCompanionObject.MIN_VALUE, 0, ByteCompanionObject.MIN_VALUE, 69, 98, 18, ByteCompanionObject.MIN_VALUE, 0, 32, 26, 88, 88, 0, 0, 8, 104, 14, 40, ByteCompanionObject.MIN_VALUE, 0, 34, -124, -125, 52, 0, 0, 72, 9, 37, 44, 0, 0, 7, 1, -118, -111, 0, 0, -108, 32, -111, -64, ByteCompanionObject.MIN_VALUE, 0, -126, 6, 104, 6, ByteCompanionObject.MIN_VALUE, 0, 96, 72, 50, -56, 0, 0, -119, ByteCompanionObject.MIN_VALUE, 67, 69, 0, 0, 0, -114, -60, 48, ByteCompanionObject.MIN_VALUE, 0, 24, 34, 28, -94, 0, 0, -92, Tnaf.POW_2_WIDTH, 37, 44, 0, 0, 1, 42, -118, -111, 0, 0, 6, 66, -111, -64, ByteCompanionObject.MIN_VALUE, 0, 8, 104, 104, 6, ByteCompanionObject.MIN_VALUE, 0, -127, -112, 50, -56, 0, 0, 0, -16, 67, 69, 0, 0, 80, 5, -60, 48, ByteCompanionObject.MIN_VALUE, 0, 32, 81, 28, -94, 0, 0, 67, 8, 21, -116, 0, 0, 104, ByteCompanionObject.MIN_VALUE, -118, 71, 0, 0, ByteCompanionObject.MIN_VALUE, 11, 37, -127, ByteCompanionObject.MIN_VALUE, 0, Tnaf.POW_2_WIDTH, 76, 98, 18, ByteCompanionObject.MIN_VALUE, 0, 18, 48, 88, 88, 0, 0, 64, -123, 14, 40, ByteCompanionObject.MIN_VALUE, 0, 14, 4, -125, 52, 0, 0, 24, 18, 10, 28, 0, 0};
    }

    private static void createRandomMask33_32() {
        _randomMask33_32 = new byte[]{-92, Tnaf.POW_2_WIDTH, 37, 44, 0, 0, 1, 42, -118, -111, 0, 0, 6, 66, -111, -64, ByteCompanionObject.MIN_VALUE, 0, 8, 104, 104, 6, ByteCompanionObject.MIN_VALUE, 0, -127, -112, 50, -56, 0, 0, 0, -16, 67, 69, 0, 0, 80, 5, -60, 48, ByteCompanionObject.MIN_VALUE, 0, 32, 81, 28, -94, 0, 0, 67, 8, 21, -116, 0, 0, 104, ByteCompanionObject.MIN_VALUE, -118, 71, 0, 0, ByteCompanionObject.MIN_VALUE, 11, 37, -127, ByteCompanionObject.MIN_VALUE, 0, Tnaf.POW_2_WIDTH, 76, 98, 18, ByteCompanionObject.MIN_VALUE, 0, 18, 48, 88, 88, 0, 0, 64, -123, 14, 40, ByteCompanionObject.MIN_VALUE, 0, 14, 4, -125, 52, 0, 0, 24, 18, 10, 28, 0, 0, 85, 17, 21, -116, 0, 0, 34, 34, -118, 71, 0, 0, 17, 17, 37, -127, ByteCompanionObject.MIN_VALUE, 0, ByteCompanionObject.MIN_VALUE, 69, 98, 18, ByteCompanionObject.MIN_VALUE, 0, 32, 26, 88, 88, 0, 0, 8, 104, 14, 40, ByteCompanionObject.MIN_VALUE, 0, 34, -124, -125, 52, 0, 0, 72, 9, 37, 44, 0, 0, 7, 1, -118, -111, 0, 0, -108, 32, -111, -64, ByteCompanionObject.MIN_VALUE, 0, -126, 6, 104, 6, ByteCompanionObject.MIN_VALUE, 0, 96, 72, 50, -56, 0, 0, -119, ByteCompanionObject.MIN_VALUE, 67, 69, 0, 0, 0, -114, -60, 48, ByteCompanionObject.MIN_VALUE, 0, 24, 34, 28, -94, 0, 0, 115, -114, 18, -54, ByteCompanionObject.MIN_VALUE, 0};
    }

    private static void createRandomMask33_33() {
        _randomMask33_33 = new byte[]{-92, Tnaf.POW_2_WIDTH, 37, 44, 0, 0, 1, 42, -118, -111, 0, 0, 6, 66, -111, -64, ByteCompanionObject.MIN_VALUE, 0, 8, 104, 104, 6, ByteCompanionObject.MIN_VALUE, 0, -127, -112, 50, -56, 0, 0, 0, -16, 67, 69, 0, 0, 80, 5, -60, 48, ByteCompanionObject.MIN_VALUE, 0, 32, 81, 28, -94, 0, 0, 67, 8, 21, -116, 0, 0, 104, ByteCompanionObject.MIN_VALUE, -118, 71, 0, 0, ByteCompanionObject.MIN_VALUE, 11, 37, -127, ByteCompanionObject.MIN_VALUE, 0, Tnaf.POW_2_WIDTH, 76, 98, 18, ByteCompanionObject.MIN_VALUE, 0, 18, 48, 88, 88, 0, 0, 64, -123, 14, 40, ByteCompanionObject.MIN_VALUE, 0, 14, 4, -125, 52, 0, 0, 24, 18, 10, 28, 0, 0, -92, Tnaf.POW_2_WIDTH, 37, 44, 0, 0, 1, 42, -118, -111, 0, 0, 6, 66, -111, -64, ByteCompanionObject.MIN_VALUE, 0, 8, 104, 104, 6, ByteCompanionObject.MIN_VALUE, 0, -127, -112, 50, -56, 0, 0, 0, -16, 67, 69, 0, 0, 80, 5, -60, 48, ByteCompanionObject.MIN_VALUE, 0, 32, 81, 28, -94, 0, 0, 67, 8, 37, 76, 0, 0, 104, ByteCompanionObject.MIN_VALUE, -118, 102, 0, 0, ByteCompanionObject.MIN_VALUE, 11, -111, -111, 0, 0, Tnaf.POW_2_WIDTH, 76, 104, 66, ByteCompanionObject.MIN_VALUE, 0, 18, 48, 50, -92, 0, 0, 64, -123, 67, 19, 0, 0, 14, 4, -60, 48, ByteCompanionObject.MIN_VALUE, 0, 24, 18, 28, -120, ByteCompanionObject.MIN_VALUE, 0, -37, Tnaf.POW_2_WIDTH, 60, 9, 0, 0};
    }

    private static void createRandomMask33_4() {
        _randomMask33_4 = new byte[]{85, 85, -54, -20, 0, 0, -86, -86, -87, 103, 0, 0, 53, 53, 58, -79, ByteCompanionObject.MIN_VALUE, 0, -54, -54, 85, 90, ByteCompanionObject.MIN_VALUE, 0};
    }

    private static void createRandomMask33_5() {
        _randomMask33_5 = new byte[]{85, 85, 85, 68, ByteCompanionObject.MIN_VALUE, 0, 42, 42, 42, 102, 0, 0, 36, 37, 37, -95, ByteCompanionObject.MIN_VALUE, 0, -124, -56, -30, 18, ByteCompanionObject.MIN_VALUE, 0, Tnaf.POW_2_WIDTH, -74, -103, -104, 0, 0};
    }

    private static void createRandomMask33_6() {
        _randomMask33_6 = new byte[]{81, 81, -47, 76, 0, 0, 10, 42, -94, -59, 0, 0, -94, 21, -107, 48, ByteCompanionObject.MIN_VALUE, 0, -124, 74, -54, 10, ByteCompanionObject.MIN_VALUE, 0, 48, -110, -92, -86, 0, 0, 4, -84, 120, 21, 0, 0};
    }

    private static void createRandomMask33_7() {
        _randomMask33_7 = new byte[]{69, 81, 21, 68, ByteCompanionObject.MIN_VALUE, 0, 34, 42, -118, 35, 0, 0, -111, 17, -123, -111, 0, 0, 46, 8, 50, 10, ByteCompanionObject.MIN_VALUE, 0, 72, 52, 88, 52, 0, 0, -112, 41, 44, 13, 0, 0, 9, -122, 67, -56, 0, 0};
    }

    private static void createRandomMask33_8() {
        _randomMask33_8 = new byte[]{32, 84, 100, 22, 0, 0, 24, -120, -94, -62, 0, 0, -124, 7, 81, 96, ByteCompanionObject.MIN_VALUE, 0, 96, 72, 74, -123, 0, 0, 18, -126, 56, 76, 0, 0, -127, 65, -119, 41, 0, 0, 64, 98, 7, 17, ByteCompanionObject.MIN_VALUE, 0, 22, 48, -108, -80, 0, 0};
    }

    private static void createRandomMask33_9() {
        _randomMask33_9 = new byte[]{85, 81, -114, -52, 0, 0, 34, 42, 106, 43, 0, 0, 5, -123, 54, 50, ByteCompanionObject.MIN_VALUE, 0, 9, 74, -47, 37, ByteCompanionObject.MIN_VALUE, 0, -124, 50, 85, -116, ByteCompanionObject.MIN_VALUE, 0, -64, 13, -86, 39, 0, 0, 32, -90, -91, 50, ByteCompanionObject.MIN_VALUE, 0, 26, 9, 98, 97, ByteCompanionObject.MIN_VALUE, 0, 68, 100, 60, 92, 0, 0};
    }

    private static void createRandomMask34_1() {
        _randomMask34_1 = new byte[]{-1, -1, -1, -1, -64, 0};
    }

    private static void createRandomMask34_10() {
        _randomMask34_10 = new byte[]{85, -116, -86, -58, 64, 0, -86, 39, 85, 19, ByteCompanionObject.MIN_VALUE, 0, -91, 50, -46, -103, 64, 0, 98, 97, -79, 48, -64, 0, 60, 92, 30, 46, 0, 0, -114, -52, 71, 102, 0, 0, 106, 43, 53, 21, ByteCompanionObject.MIN_VALUE, 0, 54, 50, -101, 25, 64, 0, -47, 37, -24, -110, -64, 0, -56, 2, -28, 1, 64, 0};
    }

    private static void createRandomMask34_11() {
        _randomMask34_11 = new byte[]{85, -116, -86, -58, 64, 0, -86, 39, 85, 19, ByteCompanionObject.MIN_VALUE, 0, -91, 50, -46, -103, 64, 0, 98, 97, -79, 48, -64, 0, 60, 92, 30, 46, 0, 0, 81, -124, -88, -62, 64, 0, -94, 39, 81, 19, ByteCompanionObject.MIN_VALUE, 0, -107, 81, -54, -88, -64, 0, 74, 26, 37, 13, 0, 0, 48, 104, 24, 52, 0, 0, 44, -119, 22, 68, ByteCompanionObject.MIN_VALUE, 0};
    }

    private static void createRandomMask34_12() {
        _randomMask34_12 = new byte[]{81, -124, -88, -62, 64, 0, -94, 39, 81, 19, ByteCompanionObject.MIN_VALUE, 0, -107, 81, -54, -88, -64, 0, 74, 26, 37, 13, 0, 0, 48, 104, 24, 52, 0, 0, 44, -119, 22, 68, ByteCompanionObject.MIN_VALUE, 0, 85, -116, -86, -58, 64, 0, -86, 39, 85, 19, ByteCompanionObject.MIN_VALUE, 0, -91, 50, -46, -103, 64, 0, 98, 97, -79, 48, -64, 0, 60, 92, 30, 46, 0, 0, 81, 53, 40, -102, ByteCompanionObject.MIN_VALUE, 0};
    }

    private static void createRandomMask34_13() {
        _randomMask34_13 = new byte[]{81, -124, -88, -62, 64, 0, -94, 39, 81, 19, ByteCompanionObject.MIN_VALUE, 0, -107, 81, -54, -88, -64, 0, 74, 26, 37, 13, 0, 0, 48, 104, 24, 52, 0, 0, 44, -119, 22, 68, ByteCompanionObject.MIN_VALUE, 0, 21, -116, 10, -58, 0, 0, -118, 71, 69, 35, ByteCompanionObject.MIN_VALUE, 0, 37, -127, -110, -64, -64, 0, 98, 18, -79, 9, 64, 0, 88, 88, 44, 44, 0, 0, 14, 40, -121, 20, 64, 0, -125, 52, 65, -102, 0, 0};
    }

    private static void createRandomMask34_14() {
        _randomMask34_14 = new byte[]{21, -116, 10, -58, 0, 0, -118, 71, 69, 35, ByteCompanionObject.MIN_VALUE, 0, 37, -127, -110, -64, -64, 0, 98, 18, -79, 9, 64, 0, 88, 88, 44, 44, 0, 0, 14, 40, -121, 20, 64, 0, -125, 52, 65, -102, 0, 0, 81, -124, -88, -62, 64, 0, -94, 39, 81, 19, ByteCompanionObject.MIN_VALUE, 0, -107, 81, -54, -88, -64, 0, 74, 26, 37, 13, 0, 0, 48, 104, 24, 52, 0, 0, 44, -119, 22, 68, ByteCompanionObject.MIN_VALUE, 0, -80, -34, -40, 111, 64, 0};
    }

    private static void createRandomMask34_15() {
        _randomMask34_15 = new byte[]{21, -116, 10, -58, 0, 0, -118, 71, 69, 35, ByteCompanionObject.MIN_VALUE, 0, 37, -127, -110, -64, -64, 0, 98, 18, -79, 9, 64, 0, 88, 88, 44, 44, 0, 0, 14, 40, -121, 20, 64, 0, -125, 52, 65, -102, 0, 0, 37, 44, 18, -106, 0, 0, -118, -111, 69, 72, ByteCompanionObject.MIN_VALUE, 0, -111, -64, -56, -32, 64, 0, 104, 6, -76, 3, 64, 0, 50, -56, 25, 100, 0, 0, 67, 69, 33, -94, ByteCompanionObject.MIN_VALUE, 0, -60, 48, -30, 24, 64, 0, 28, -94, 14, 81, 0, 0};
    }

    private static void createRandomMask34_16() {
        _randomMask34_16 = new byte[]{37, 44, 18, -106, 0, 0, -118, -111, 69, 72, ByteCompanionObject.MIN_VALUE, 0, -111, -64, -56, -32, 64, 0, 104, 6, -76, 3, 64, 0, 50, -56, 25, 100, 0, 0, 67, 69, 33, -94, ByteCompanionObject.MIN_VALUE, 0, -60, 48, -30, 24, 64, 0, 28, -94, 14, 81, 0, 0, 21, -116, 10, -58, 0, 0, -118, 71, 69, 35, ByteCompanionObject.MIN_VALUE, 0, 37, -127, -110, -64, -64, 0, 98, 18, -79, 9, 64, 0, 88, 88, 44, 44, 0, 0, 14, 40, -121, 20, 64, 0, -125, 52, 65, -102, 0, 0, 10, 28, 5, 14, 0, 0};
    }

    private static void createRandomMask34_17() {
        _randomMask34_17 = new byte[]{37, 44, 18, -106, 0, 0, -118, -111, 69, 72, ByteCompanionObject.MIN_VALUE, 0, -111, -64, -56, -32, 64, 0, 104, 6, -76, 3, 64, 0, 50, -56, 25, 100, 0, 0, 67, 69, 33, -94, ByteCompanionObject.MIN_VALUE, 0, -60, 48, -30, 24, 64, 0, 28, -94, 14, 81, 0, 0, 37, 76, 18, -90, 0, 0, -118, 102, 69, 51, 0, 0, -111, -111, 72, -56, ByteCompanionObject.MIN_VALUE, 0, 104, 66, -76, 33, 64, 0, 50, -92, 25, 82, 0, 0, 67, 19, 33, -119, ByteCompanionObject.MIN_VALUE, 0, -60, 48, -30, 24, 64, 0, 28, -120, -114, 68, 64, 0, 60, 9, 30, 4, ByteCompanionObject.MIN_VALUE, 0};
    }

    private static void createRandomMask34_18() {
        _randomMask34_18 = new byte[]{-114, -52, 71, 102, 0, 0, 106, 43, 53, 21, ByteCompanionObject.MIN_VALUE, 0, 54, 50, -101, 25, 64, 0, -47, 37, -24, -110, -64, 0, 85, -116, -86, -58, 64, 0, -86, 39, 85, 19, ByteCompanionObject.MIN_VALUE, 0, -91, 50, -46, -103, 64, 0, 98, 97, -79, 48, -64, 0, 60, 92, 30, 46, 0, 0, 100, 22, 50, 11, 0, 0, -94, -62, 81, 97, 0, 0, 81, 96, -88, -80, 64, 0, 74, -123, 37, 66, ByteCompanionObject.MIN_VALUE, 0, 56, 76, 28, 38, 0, 0, -119, 41, 68, -108, ByteCompanionObject.MIN_VALUE, 0, 7, 17, -125, -120, -64, 0, -108, -80, 74, 88, 0, 0, -119, 112, -13, -9, 64, 0};
    }

    private static void createRandomMask34_19() {
        _randomMask34_19 = new byte[]{-114, -52, 71, 102, 0, 0, 106, 43, 53, 21, ByteCompanionObject.MIN_VALUE, 0, 54, 50, -101, 25, 64, 0, -47, 37, -24, -110, -64, 0, 85, -116, -86, -58, 64, 0, -86, 39, 85, 19, ByteCompanionObject.MIN_VALUE, 0, -91, 50, -46, -103, 64, 0, 98, 97, -79, 48, -64, 0, 60, 92, 30, 46, 0, 0, 85, -116, -86, -58, 64, 0, -86, 39, 85, 19, ByteCompanionObject.MIN_VALUE, 0, -91, 50, -46, -103, 64, 0, 98, 97, -79, 48, -64, 0, 60, 92, 30, 46, 0, 0, -114, -52, 71, 102, 0, 0, 106, 43, 53, 21, ByteCompanionObject.MIN_VALUE, 0, 54, 50, -101, 25, 64, 0, -47, 37, -24, -110, -64, 0, -56, 2, -28, 1, 64, 0};
    }

    private static void createRandomMask34_2() {
        _randomMask34_2 = new byte[]{-50, -50, 103, 103, 0, 0, -71, 57, -36, -100, -64, 0};
    }

    private static void createRandomMask34_20() {
        _randomMask34_20 = new byte[]{85, -116, -86, -58, 64, 0, -86, 39, 85, 19, ByteCompanionObject.MIN_VALUE, 0, -91, 50, -46, -103, 64, 0, 98, 97, -79, 48, -64, 0, 60, 92, 30, 46, 0, 0, -114, -52, 71, 102, 0, 0, 106, 43, 53, 21, ByteCompanionObject.MIN_VALUE, 0, 54, 50, -101, 25, 64, 0, -47, 37, -24, -110, -64, 0, -56, 2, -28, 1, 64, 0, -114, -52, 71, 102, 0, 0, 106, 43, 53, 21, ByteCompanionObject.MIN_VALUE, 0, 54, 50, -101, 25, 64, 0, -47, 37, -24, -110, -64, 0, 85, -116, -86, -58, 64, 0, -86, 39, 85, 19, ByteCompanionObject.MIN_VALUE, 0, -91, 50, -46, -103, 64, 0, 98, 97, -79, 48, -64, 0, 60, 92, 30, 46, 0, 0, 93, -59, -2, -40, 64, 0};
    }

    private static void createRandomMask34_21() {
        _randomMask34_21 = new byte[]{85, -116, -86, -58, 64, 0, -86, 39, 85, 19, ByteCompanionObject.MIN_VALUE, 0, -91, 50, -46, -103, 64, 0, 98, 97, -79, 48, -64, 0, 60, 92, 30, 46, 0, 0, -114, -52, 71, 102, 0, 0, 106, 43, 53, 21, ByteCompanionObject.MIN_VALUE, 0, 54, 50, -101, 25, 64, 0, -47, 37, -24, -110, -64, 0, -56, 2, -28, 1, 64, 0, 85, -116, -86, -58, 64, 0, -86, 39, 85, 19, ByteCompanionObject.MIN_VALUE, 0, -91, 50, -46, -103, 64, 0, 98, 97, -79, 48, -64, 0, 60, 92, 30, 46, 0, 0, 81, -124, -88, -62, 64, 0, -94, 39, 81, 19, ByteCompanionObject.MIN_VALUE, 0, -107, 81, -54, -88, -64, 0, 74, 26, 37, 13, 0, 0, 48, 104, 24, 52, 0, 0, 44, -119, 22, 68, ByteCompanionObject.MIN_VALUE, 0};
    }

    private static void createRandomMask34_22() {
        _randomMask34_22 = new byte[]{85, -116, -86, -58, 64, 0, -86, 39, 85, 19, ByteCompanionObject.MIN_VALUE, 0, -91, 50, -46, -103, 64, 0, 98, 97, -79, 48, -64, 0, 60, 92, 30, 46, 0, 0, 81, -124, -88, -62, 64, 0, -94, 39, 81, 19, ByteCompanionObject.MIN_VALUE, 0, -107, 81, -54, -88, -64, 0, 74, 26, 37, 13, 0, 0, 48, 104, 24, 52, 0, 0, 44, -119, 22, 68, ByteCompanionObject.MIN_VALUE, 0, 85, -116, -86, -58, 64, 0, -86, 39, 85, 19, ByteCompanionObject.MIN_VALUE, 0, -91, 50, -46, -103, 64, 0, 98, 97, -79, 48, -64, 0, 60, 92, 30, 46, 0, 0, -114, -52, 71, 102, 0, 0, 106, 43, 53, 21, ByteCompanionObject.MIN_VALUE, 0, 54, 50, -101, 25, 64, 0, -47, 37, -24, -110, -64, 0, -56, 2, -28, 1, 64, 0, 42, -9, 79, -11, ByteCompanionObject.MIN_VALUE, 0};
    }

    private static void createRandomMask34_23() {
        _randomMask34_23 = new byte[]{85, -116, -86, -58, 64, 0, -86, 39, 85, 19, ByteCompanionObject.MIN_VALUE, 0, -91, 50, -46, -103, 64, 0, 98, 97, -79, 48, -64, 0, 60, 92, 30, 46, 0, 0, 81, -124, -88, -62, 64, 0, -94, 39, 81, 19, ByteCompanionObject.MIN_VALUE, 0, -107, 81, -54, -88, -64, 0, 74, 26, 37, 13, 0, 0, 48, 104, 24, 52, 0, 0, 44, -119, 22, 68, ByteCompanionObject.MIN_VALUE, 0, 81, -124, -88, -62, 64, 0, -94, 39, 81, 19, ByteCompanionObject.MIN_VALUE, 0, -107, 81, -54, -88, -64, 0, 74, 26, 37, 13, 0, 0, 48, 104, 24, 52, 0, 0, 44, -119, 22, 68, ByteCompanionObject.MIN_VALUE, 0, 85, -116, -86, -58, 64, 0, -86, 39, 85, 19, ByteCompanionObject.MIN_VALUE, 0, -91, 50, -46, -103, 64, 0, 98, 97, -79, 48, -64, 0, 60, 92, 30, 46, 0, 0, 81, 53, 40, -102, ByteCompanionObject.MIN_VALUE, 0};
    }

    private static void createRandomMask34_24() {
        _randomMask34_24 = new byte[]{81, -124, -88, -62, 64, 0, -94, 39, 81, 19, ByteCompanionObject.MIN_VALUE, 0, -107, 81, -54, -88, -64, 0, 74, 26, 37, 13, 0, 0, 48, 104, 24, 52, 0, 0, 44, -119, 22, 68, ByteCompanionObject.MIN_VALUE, 0, 85, -116, -86, -58, 64, 0, -86, 39, 85, 19, ByteCompanionObject.MIN_VALUE, 0, -91, 50, -46, -103, 64, 0, 98, 97, -79, 48, -64, 0, 60, 92, 30, 46, 0, 0, 81, 53, 40, -102, ByteCompanionObject.MIN_VALUE, 0, 85, -116, -86, -58, 64, 0, -86, 39, 85, 19, ByteCompanionObject.MIN_VALUE, 0, -91, 50, -46, -103, 64, 0, 98, 97, -79, 48, -64, 0, 60, 92, 30, 46, 0, 0, 81, -124, -88, -62, 64, 0, -94, 39, 81, 19, ByteCompanionObject.MIN_VALUE, 0, -107, 81, -54, -88, -64, 0, 74, 26, 37, 13, 0, 0, 48, 104, 24, 52, 0, 0, 44, -119, 22, 68, ByteCompanionObject.MIN_VALUE, 0, 76, -72, 4, 116, -64, 0};
    }

    private static void createRandomMask34_25() {
        _randomMask34_25 = new byte[]{81, -124, -88, -62, 64, 0, -94, 39, 81, 19, ByteCompanionObject.MIN_VALUE, 0, -107, 81, -54, -88, -64, 0, 74, 26, 37, 13, 0, 0, 48, 104, 24, 52, 0, 0, 44, -119, 22, 68, ByteCompanionObject.MIN_VALUE, 0, 85, -116, -86, -58, 64, 0, -86, 39, 85, 19, ByteCompanionObject.MIN_VALUE, 0, -91, 50, -46, -103, 64, 0, 98, 97, -79, 48, -64, 0, 60, 92, 30, 46, 0, 0, 81, 53, 40, -102, ByteCompanionObject.MIN_VALUE, 0, 81, -124, -88, -62, 64, 0, -94, 39, 81, 19, ByteCompanionObject.MIN_VALUE, 0, -107, 81, -54, -88, -64, 0, 74, 26, 37, 13, 0, 0, 48, 104, 24, 52, 0, 0, 44, -119, 22, 68, ByteCompanionObject.MIN_VALUE, 0, 21, -116, 10, -58, 0, 0, -118, 71, 69, 35, ByteCompanionObject.MIN_VALUE, 0, 37, -127, -110, -64, -64, 0, 98, 18, -79, 9, 64, 0, 88, 88, 44, 44, 0, 0, 14, 40, -121, 20, 64, 0, -125, 52, 65, -102, 0, 0};
    }

    private static void createRandomMask34_26() {
        _randomMask34_26 = new byte[]{81, -124, -88, -62, 64, 0, -94, 39, 81, 19, ByteCompanionObject.MIN_VALUE, 0, -107, 81, -54, -88, -64, 0, 74, 26, 37, 13, 0, 0, 48, 104, 24, 52, 0, 0, 44, -119, 22, 68, ByteCompanionObject.MIN_VALUE, 0, 21, -116, 10, -58, 0, 0, -118, 71, 69, 35, ByteCompanionObject.MIN_VALUE, 0, 37, -127, -110, -64, -64, 0, 98, 18, -79, 9, 64, 0, 88, 88, 44, 44, 0, 0, 14, 40, -121, 20, 64, 0, -125, 52, 65, -102, 0, 0, 81, -124, -88, -62, 64, 0, -94, 39, 81, 19, ByteCompanionObject.MIN_VALUE, 0, -107, 81, -54, -88, -64, 0, 74, 26, 37, 13, 0, 0, 48, 104, 24, 52, 0, 0, 44, -119, 22, 68, ByteCompanionObject.MIN_VALUE, 0, 85, -116, -86, -58, 64, 0, -86, 39, 85, 19, ByteCompanionObject.MIN_VALUE, 0, -91, 50, -46, -103, 64, 0, 98, 97, -79, 48, -64, 0, 60, 92, 30, 46, 0, 0, 81, 53, 40, -102, ByteCompanionObject.MIN_VALUE, 0, -107, 32, -23, -17, -64, 0};
    }

    private static void createRandomMask34_27() {
        _randomMask34_27 = new byte[]{81, -124, -88, -62, 64, 0, -94, 39, 81, 19, ByteCompanionObject.MIN_VALUE, 0, -107, 81, -54, -88, -64, 0, 74, 26, 37, 13, 0, 0, 48, 104, 24, 52, 0, 0, 44, -119, 22, 68, ByteCompanionObject.MIN_VALUE, 0, 21, -116, 10, -58, 0, 0, -118, 71, 69, 35, ByteCompanionObject.MIN_VALUE, 0, 37, -127, -110, -64, -64, 0, 98, 18, -79, 9, 64, 0, 88, 88, 44, 44, 0, 0, 14, 40, -121, 20, 64, 0, -125, 52, 65, -102, 0, 0, 21, -116, 10, -58, 0, 0, -118, 71, 69, 35, ByteCompanionObject.MIN_VALUE, 0, 37, -127, -110, -64, -64, 0, 98, 18, -79, 9, 64, 0, 88, 88, 44, 44, 0, 0, 14, 40, -121, 20, 64, 0, -125, 52, 65, -102, 0, 0, 81, -124, -88, -62, 64, 0, -94, 39, 81, 19, ByteCompanionObject.MIN_VALUE, 0, -107, 81, -54, -88, -64, 0, 74, 26, 37, 13, 0, 0, 48, 104, 24, 52, 0, 0, 44, -119, 22, 68, ByteCompanionObject.MIN_VALUE, 0, -80, -34, -40, 111, 64, 0};
    }

    private static void createRandomMask34_28() {
        _randomMask34_28 = new byte[]{21, -116, 10, -58, 0, 0, -118, 71, 69, 35, ByteCompanionObject.MIN_VALUE, 0, 37, -127, -110, -64, -64, 0, 98, 18, -79, 9, 64, 0, 88, 88, 44, 44, 0, 0, 14, 40, -121, 20, 64, 0, -125, 52, 65, -102, 0, 0, 81, -124, -88, -62, 64, 0, -94, 39, 81, 19, ByteCompanionObject.MIN_VALUE, 0, -107, 81, -54, -88, -64, 0, 74, 26, 37, 13, 0, 0, 48, 104, 24, 52, 0, 0, 44, -119, 22, 68, ByteCompanionObject.MIN_VALUE, 0, -80, -34, -40, 111, 64, 0, 81, -124, -88, -62, 64, 0, -94, 39, 81, 19, ByteCompanionObject.MIN_VALUE, 0, -107, 81, -54, -88, -64, 0, 74, 26, 37, 13, 0, 0, 48, 104, 24, 52, 0, 0, 44, -119, 22, 68, ByteCompanionObject.MIN_VALUE, 0, 21, -116, 10, -58, 0, 0, -118, 71, 69, 35, ByteCompanionObject.MIN_VALUE, 0, 37, -127, -110, -64, -64, 0, 98, 18, -79, 9, 64, 0, 88, 88, 44, 44, 0, 0, 14, 40, -121, 20, 64, 0, -125, 52, 65, -102, 0, 0, Tnaf.POW_2_WIDTH, 108, -1, 96, ByteCompanionObject.MIN_VALUE, 0};
    }

    private static void createRandomMask34_29() {
        _randomMask34_29 = new byte[]{21, -116, 10, -58, 0, 0, -118, 71, 69, 35, ByteCompanionObject.MIN_VALUE, 0, 37, -127, -110, -64, -64, 0, 98, 18, -79, 9, 64, 0, 88, 88, 44, 44, 0, 0, 14, 40, -121, 20, 64, 0, -125, 52, 65, -102, 0, 0, 81, -124, -88, -62, 64, 0, -94, 39, 81, 19, ByteCompanionObject.MIN_VALUE, 0, -107, 81, -54, -88, -64, 0, 74, 26, 37, 13, 0, 0, 48, 104, 24, 52, 0, 0, 44, -119, 22, 68, ByteCompanionObject.MIN_VALUE, 0, -80, -34, -40, 111, 64, 0, 21, -116, 10, -58, 0, 0, -118, 71, 69, 35, ByteCompanionObject.MIN_VALUE, 0, 37, -127, -110, -64, -64, 0, 98, 18, -79, 9, 64, 0, 88, 88, 44, 44, 0, 0, 14, 40, -121, 20, 64, 0, -125, 52, 65, -102, 0, 0, 37, 44, 18, -106, 0, 0, -118, -111, 69, 72, ByteCompanionObject.MIN_VALUE, 0, -111, -64, -56, -32, 64, 0, 104, 6, -76, 3, 64, 0, 50, -56, 25, 100, 0, 0, 67, 69, 33, -94, ByteCompanionObject.MIN_VALUE, 0, -60, 48, -30, 24, 64, 0, 28, -94, 14, 81, 0, 0};
    }

    private static void createRandomMask34_3() {
        _randomMask34_3 = new byte[]{-51, -52, 102, -26, 0, 0, -105, 39, 75, -109, ByteCompanionObject.MIN_VALUE, 0, -72, -47, -36, 104, -64, 0};
    }

    private static void createRandomMask34_30() {
        _randomMask34_30 = new byte[]{21, -116, 10, -58, 0, 0, -118, 71, 69, 35, ByteCompanionObject.MIN_VALUE, 0, 37, -127, -110, -64, -64, 0, 98, 18, -79, 9, 64, 0, 88, 88, 44, 44, 0, 0, 14, 40, -121, 20, 64, 0, -125, 52, 65, -102, 0, 0, 37, 44, 18, -106, 0, 0, -118, -111, 69, 72, ByteCompanionObject.MIN_VALUE, 0, -111, -64, -56, -32, 64, 0, 104, 6, -76, 3, 64, 0, 50, -56, 25, 100, 0, 0, 67, 69, 33, -94, ByteCompanionObject.MIN_VALUE, 0, -60, 48, -30, 24, 64, 0, 28, -94, 14, 81, 0, 0, 21, -116, 10, -58, 0, 0, -118, 71, 69, 35, ByteCompanionObject.MIN_VALUE, 0, 37, -127, -110, -64, -64, 0, 98, 18, -79, 9, 64, 0, 88, 88, 44, 44, 0, 0, 14, 40, -121, 20, 64, 0, -125, 52, 65, -102, 0, 0, 81, -124, -88, -62, 64, 0, -94, 39, 81, 19, ByteCompanionObject.MIN_VALUE, 0, -107, 81, -54, -88, -64, 0, 74, 26, 37, 13, 0, 0, 48, 104, 24, 52, 0, 0, 44, -119, 22, 68, ByteCompanionObject.MIN_VALUE, 0, -80, -34, -40, 111, 64, 0, -121, -109, -106, -57, ByteCompanionObject.MIN_VALUE, 0};
    }

    private static void createRandomMask34_31() {
        _randomMask34_31 = new byte[]{21, -116, 10, -58, 0, 0, -118, 71, 69, 35, ByteCompanionObject.MIN_VALUE, 0, 37, -127, -110, -64, -64, 0, 98, 18, -79, 9, 64, 0, 88, 88, 44, 44, 0, 0, 14, 40, -121, 20, 64, 0, -125, 52, 65, -102, 0, 0, 37, 44, 18, -106, 0, 0, -118, -111, 69, 72, ByteCompanionObject.MIN_VALUE, 0, -111, -64, -56, -32, 64, 0, 104, 6, -76, 3, 64, 0, 50, -56, 25, 100, 0, 0, 67, 69, 33, -94, ByteCompanionObject.MIN_VALUE, 0, -60, 48, -30, 24, 64, 0, 28, -94, 14, 81, 0, 0, 37, 44, 18, -106, 0, 0, -118, -111, 69, 72, ByteCompanionObject.MIN_VALUE, 0, -111, -64, -56, -32, 64, 0, 104, 6, -76, 3, 64, 0, 50, -56, 25, 100, 0, 0, 67, 69, 33, -94, ByteCompanionObject.MIN_VALUE, 0, -60, 48, -30, 24, 64, 0, 28, -94, 14, 81, 0, 0, 21, -116, 10, -58, 0, 0, -118, 71, 69, 35, ByteCompanionObject.MIN_VALUE, 0, 37, -127, -110, -64, -64, 0, 98, 18, -79, 9, 64, 0, 88, 88, 44, 44, 0, 0, 14, 40, -121, 20, 64, 0, -125, 52, 65, -102, 0, 0, 10, 28, 5, 14, 0, 0};
    }

    private static void createRandomMask34_32() {
        _randomMask34_32 = new byte[]{37, 44, 18, -106, 0, 0, -118, -111, 69, 72, ByteCompanionObject.MIN_VALUE, 0, -111, -64, -56, -32, 64, 0, 104, 6, -76, 3, 64, 0, 50, -56, 25, 100, 0, 0, 67, 69, 33, -94, ByteCompanionObject.MIN_VALUE, 0, -60, 48, -30, 24, 64, 0, 28, -94, 14, 81, 0, 0, 21, -116, 10, -58, 0, 0, -118, 71, 69, 35, ByteCompanionObject.MIN_VALUE, 0, 37, -127, -110, -64, -64, 0, 98, 18, -79, 9, 64, 0, 88, 88, 44, 44, 0, 0, 14, 40, -121, 20, 64, 0, -125, 52, 65, -102, 0, 0, 10, 28, 5, 14, 0, 0, 21, -116, 10, -58, 0, 0, -118, 71, 69, 35, ByteCompanionObject.MIN_VALUE, 0, 37, -127, -110, -64, -64, 0, 98, 18, -79, 9, 64, 0, 88, 88, 44, 44, 0, 0, 14, 40, -121, 20, 64, 0, -125, 52, 65, -102, 0, 0, 37, 44, 18, -106, 0, 0, -118, -111, 69, 72, ByteCompanionObject.MIN_VALUE, 0, -111, -64, -56, -32, 64, 0, 104, 6, -76, 3, 64, 0, 50, -56, 25, 100, 0, 0, 67, 69, 33, -94, ByteCompanionObject.MIN_VALUE, 0, -60, 48, -30, 24, 64, 0, 28, -94, 14, 81, 0, 0, -90, 39, -87, 74, 64, 0};
    }

    private static void createRandomMask34_33() {
        _randomMask34_33 = new byte[]{37, 44, 18, -106, 0, 0, -118, -111, 69, 72, ByteCompanionObject.MIN_VALUE, 0, -111, -64, -56, -32, 64, 0, 104, 6, -76, 3, 64, 0, 50, -56, 25, 100, 0, 0, 67, 69, 33, -94, ByteCompanionObject.MIN_VALUE, 0, -60, 48, -30, 24, 64, 0, 28, -94, 14, 81, 0, 0, 21, -116, 10, -58, 0, 0, -118, 71, 69, 35, ByteCompanionObject.MIN_VALUE, 0, 37, -127, -110, -64, -64, 0, 98, 18, -79, 9, 64, 0, 88, 88, 44, 44, 0, 0, 14, 40, -121, 20, 64, 0, -125, 52, 65, -102, 0, 0, 10, 28, 5, 14, 0, 0, 37, 44, 18, -106, 0, 0, -118, -111, 69, 72, ByteCompanionObject.MIN_VALUE, 0, -111, -64, -56, -32, 64, 0, 104, 6, -76, 3, 64, 0, 50, -56, 25, 100, 0, 0, 67, 69, 33, -94, ByteCompanionObject.MIN_VALUE, 0, -60, 48, -30, 24, 64, 0, 28, -94, 14, 81, 0, 0, 37, 76, 18, -90, 0, 0, -118, 102, 69, 51, 0, 0, -111, -111, 72, -56, ByteCompanionObject.MIN_VALUE, 0, 104, 66, -76, 33, 64, 0, 50, -92, 25, 82, 0, 0, 67, 19, 33, -119, ByteCompanionObject.MIN_VALUE, 0, -60, 48, -30, 24, 64, 0, 28, -120, -114, 68, 64, 0, 60, 9, 30, 4, ByteCompanionObject.MIN_VALUE, 0};
    }

    private static void createRandomMask34_34() {
        _randomMask34_34 = new byte[]{37, 44, 18, -106, 0, 0, -118, -111, 69, 72, ByteCompanionObject.MIN_VALUE, 0, -111, -64, -56, -32, 64, 0, 104, 6, -76, 3, 64, 0, 50, -56, 25, 100, 0, 0, 67, 69, 33, -94, ByteCompanionObject.MIN_VALUE, 0, -60, 48, -30, 24, 64, 0, 28, -94, 14, 81, 0, 0, 37, 76, 18, -90, 0, 0, -118, 102, 69, 51, 0, 0, -111, -111, 72, -56, ByteCompanionObject.MIN_VALUE, 0, 104, 66, -76, 33, 64, 0, 50, -92, 25, 82, 0, 0, 67, 19, 33, -119, ByteCompanionObject.MIN_VALUE, 0, -60, 48, -30, 24, 64, 0, 28, -120, -114, 68, 64, 0, 60, 9, 30, 4, ByteCompanionObject.MIN_VALUE, 0, 37, 44, 18, -106, 0, 0, -118, -111, 69, 72, ByteCompanionObject.MIN_VALUE, 0, -111, -64, -56, -32, 64, 0, 104, 6, -76, 3, 64, 0, 50, -56, 25, 100, 0, 0, 67, 69, 33, -94, ByteCompanionObject.MIN_VALUE, 0, -60, 48, -30, 24, 64, 0, 28, -94, 14, 81, 0, 0, 21, -116, 10, -58, 0, 0, -118, 71, 69, 35, ByteCompanionObject.MIN_VALUE, 0, 37, -127, -110, -64, -64, 0, 98, 18, -79, 9, 64, 0, 88, 88, 44, 44, 0, 0, 14, 40, -121, 20, 64, 0, -125, 52, 65, -102, 0, 0, 10, 28, 5, 14, 0, 0, 48, 60, -77, -26, ByteCompanionObject.MIN_VALUE, 0};
    }

    private static void createRandomMask34_4() {
        _randomMask34_4 = new byte[]{-54, -20, 101, 118, 0, 0, -87, 103, 84, -77, ByteCompanionObject.MIN_VALUE, 0, 58, -79, -99, 88, -64, 0, 85, 90, -86, -83, 64, 0};
    }

    private static void createRandomMask34_5() {
        _randomMask34_5 = new byte[]{85, 68, -86, -94, 64, 0, 42, 102, 21, 51, 0, 0, 37, -95, -110, -48, -64, 0, -30, 18, -15, 9, 64, 0, -103, -104, 76, -52, 0, 0};
    }

    private static void createRandomMask34_6() {
        _randomMask34_6 = new byte[]{-47, 76, 104, -90, 0, 0, -94, -59, 81, 98, ByteCompanionObject.MIN_VALUE, 0, -107, 48, -54, -104, 64, 0, -54, 10, -27, 5, 64, 0, -92, -86, 82, 85, 0, 0, 120, 21, 60, 10, ByteCompanionObject.MIN_VALUE, 0};
    }

    private static void createRandomMask34_7() {
        _randomMask34_7 = new byte[]{21, 68, -118, -94, 64, 0, -118, 35, 69, 17, ByteCompanionObject.MIN_VALUE, 0, -123, -111, 66, -56, ByteCompanionObject.MIN_VALUE, 0, 50, 10, -103, 5, 64, 0, 88, 52, 44, 26, 0, 0, 44, 13, 22, 6, ByteCompanionObject.MIN_VALUE, 0, 67, -56, 33, -28, 0, 0};
    }

    private static void createRandomMask34_8() {
        _randomMask34_8 = new byte[]{100, 22, 50, 11, 0, 0, -94, -62, 81, 97, 0, 0, 81, 96, -88, -80, 64, 0, 74, -123, 37, 66, ByteCompanionObject.MIN_VALUE, 0, 56, 76, 28, 38, 0, 0, -119, 41, 68, -108, ByteCompanionObject.MIN_VALUE, 0, 7, 17, -125, -120, -64, 0, -108, -80, 74, 88, 0, 0};
    }

    private static void createRandomMask34_9() {
        _randomMask34_9 = new byte[]{-114, -52, 71, 102, 0, 0, 106, 43, 53, 21, ByteCompanionObject.MIN_VALUE, 0, 54, 50, -101, 25, 64, 0, -47, 37, -24, -110, -64, 0, 85, -116, -86, -58, 64, 0, -86, 39, 85, 19, ByteCompanionObject.MIN_VALUE, 0, -91, 50, -46, -103, 64, 0, 98, 97, -79, 48, -64, 0, 60, 92, 30, 46, 0, 0};
    }

    private static void createRandomMask35_1() {
        _randomMask35_1 = new byte[]{-1, -1, -1, -1, -32, 0};
    }

    private static void createRandomMask35_10() {
        _randomMask35_10 = new byte[]{85, -116, -58, 99, 32, 0, -86, 39, 19, -119, -64, 0, -91, 50, -103, 76, -96, 0, 98, 97, -80, -40, 96, 0, 60, 92, 46, 23, 0, 0, -114, -52, 102, 51, 0, 0, 106, 43, 21, -118, -64, 0, 54, 50, -103, 76, -96, 0, -47, 37, -110, -55, 96, 0, -56, 2, -2, -50, -32, 0};
    }

    private static void createRandomMask35_11() {
        _randomMask35_11 = new byte[]{85, -116, -58, 99, 32, 0, -86, 39, 19, -119, -64, 0, -91, 50, -103, 76, -96, 0, 98, 97, -80, -40, 96, 0, 60, 92, 46, 23, 0, 0, 81, -124, -62, 97, 32, 0, -94, 39, 19, -119, -64, 0, -107, 81, -88, -44, 96, 0, 74, 26, 13, 6, ByteCompanionObject.MIN_VALUE, 0, 48, 104, 52, 26, 0, 0, 44, -119, 68, -94, 64, 0};
    }

    private static void createRandomMask35_12() {
        _randomMask35_12 = new byte[]{81, -124, -62, 97, 32, 0, -94, 39, 19, -119, -64, 0, -107, 81, -88, -44, 96, 0, 74, 26, 13, 6, ByteCompanionObject.MIN_VALUE, 0, 48, 104, 52, 26, 0, 0, 44, -119, 68, -94, 64, 0, 85, -116, -58, 99, 32, 0, -86, 39, 19, -119, -64, 0, -91, 50, -103, 76, -96, 0, 98, 97, -80, -40, 96, 0, 60, 92, 46, 23, 0, 0, 81, 53, 45, -122, 32, 0};
    }

    private static void createRandomMask35_13() {
        _randomMask35_13 = new byte[]{81, -124, -62, 97, 32, 0, -94, 39, 19, -119, -64, 0, -107, 81, -88, -44, 96, 0, 74, 26, 13, 6, ByteCompanionObject.MIN_VALUE, 0, 48, 104, 52, 26, 0, 0, 44, -119, 68, -94, 64, 0, 21, -116, 70, 35, 0, 0, -118, 71, 35, -111, -64, 0, 37, -127, -64, -32, 96, 0, 98, 18, -119, 68, -96, 0, 88, 88, 44, 22, 0, 0, 14, 40, -108, 74, 32, 0, -125, 52, 26, 13, 0, 0};
    }

    private static void createRandomMask35_14() {
        _randomMask35_14 = new byte[]{21, -116, 70, 35, 0, 0, -118, 71, 35, -111, -64, 0, 37, -127, -64, -32, 96, 0, 98, 18, -119, 68, -96, 0, 88, 88, 44, 22, 0, 0, 14, 40, -108, 74, 32, 0, -125, 52, 26, 13, 0, 0, 81, -124, -62, 97, 32, 0, -94, 39, 19, -119, -64, 0, -107, 81, -88, -44, 96, 0, 74, 26, 13, 6, ByteCompanionObject.MIN_VALUE, 0, 48, 104, 52, 26, 0, 0, 44, -119, 68, -94, 64, 0, -80, -34, -65, -89, -32, 0};
    }

    private static void createRandomMask35_15() {
        _randomMask35_15 = new byte[]{21, -116, 70, 35, 0, 0, -118, 71, 35, -111, -64, 0, 37, -127, -64, -32, 96, 0, 98, 18, -119, 68, -96, 0, 88, 88, 44, 22, 0, 0, 14, 40, -108, 74, 32, 0, -125, 52, 26, 13, 0, 0, 37, 44, 22, 11, 0, 0, -118, -111, 72, -92, 64, 0, -111, -64, -32, 112, 32, 0, 104, 6, -125, 65, -96, 0, 50, -56, 100, 50, 0, 0, 67, 69, 34, -111, 64, 0, -60, 48, -104, 76, 32, 0, 28, -94, 81, 40, ByteCompanionObject.MIN_VALUE, 0};
    }

    private static void createRandomMask35_16() {
        _randomMask35_16 = new byte[]{37, 44, 22, 11, 0, 0, -118, -111, 72, -92, 64, 0, -111, -64, -32, 112, 32, 0, 104, 6, -125, 65, -96, 0, 50, -56, 100, 50, 0, 0, 67, 69, 34, -111, 64, 0, -60, 48, -104, 76, 32, 0, 28, -94, 81, 40, ByteCompanionObject.MIN_VALUE, 0, 21, -116, 70, 35, 0, 0, -118, 71, 35, -111, -64, 0, 37, -127, -64, -32, 96, 0, 98, 18, -119, 68, -96, 0, 88, 88, 44, 22, 0, 0, 14, 40, -108, 74, 32, 0, -125, 52, 26, 13, 0, 0, 10, 28, 119, -7, 0, 0};
    }

    private static void createRandomMask35_17() {
        _randomMask35_17 = new byte[]{37, 44, 22, 11, 0, 0, -118, -111, 72, -92, 64, 0, -111, -64, -32, 112, 32, 0, 104, 6, -125, 65, -96, 0, 50, -56, 100, 50, 0, 0, 67, 69, 34, -111, 64, 0, -60, 48, -104, 76, 32, 0, 28, -94, 81, 40, ByteCompanionObject.MIN_VALUE, 0, 37, 76, 38, 19, 0, 0, -118, 102, 51, 25, ByteCompanionObject.MIN_VALUE, 0, -111, -111, 72, -92, 64, 0, 104, 66, -95, 80, -96, 0, 50, -92, 82, 41, 0, 0, 67, 19, 9, -124, -64, 0, -60, 48, -104, 76, 32, 0, 28, -120, -60, 98, 32, 0, 60, 9, 4, -126, 64, 0};
    }

    private static void createRandomMask35_18() {
        _randomMask35_18 = new byte[]{-114, -52, 34, 81, 32, 0, 106, 43, 51, 19, 0, 0, 54, 50, -56, 36, -96, 0, -47, 37, ByteCompanionObject.MIN_VALUE, -46, -64, 0, 85, -116, -121, 9, 64, 0, -86, 39, 9, -123, ByteCompanionObject.MIN_VALUE, 0, -91, 50, -112, 104, 32, 0, 98, 97, -31, 40, ByteCompanionObject.MIN_VALUE, 0, 60, 92, 20, -122, 64, 0, 100, 22, 11, 5, ByteCompanionObject.MIN_VALUE, 0, -94, -62, 97, 48, ByteCompanionObject.MIN_VALUE, 0, 81, 96, -80, 88, 32, 0, 74, -123, 66, -95, 64, 0, 56, 76, 38, 19, 0, 0, -119, 41, 20, -118, 64, 0, 7, 17, -120, -60, 96, 0, -108, -80, 88, 44, 0, 0, 64, -55, 101, -66, -64, 0};
    }

    private static void createRandomMask35_19() {
        _randomMask35_19 = new byte[]{-114, -52, 34, 81, 32, 0, 106, 43, 51, 19, 0, 0, 54, 50, -56, 36, -96, 0, -47, 37, ByteCompanionObject.MIN_VALUE, -46, -64, 0, 85, -116, -121, 9, 64, 0, -86, 39, 9, -123, ByteCompanionObject.MIN_VALUE, 0, -91, 50, -112, 104, 32, 0, 98, 97, -31, 40, ByteCompanionObject.MIN_VALUE, 0, 60, 92, 20, -122, 64, 0, 85, -116, -58, 99, 32, 0, -86, 39, 19, -119, -64, 0, -91, 50, -103, 76, -96, 0, 98, 97, -80, -40, 96, 0, 60, 92, 46, 23, 0, 0, -114, -52, 102, 51, 0, 0, 106, 43, 21, -118, -64, 0, 54, 50, -103, 76, -96, 0, -47, 37, -110, -55, 96, 0, -56, 2, -2, -50, -32, 0};
    }

    private static void createRandomMask35_2() {
        _randomMask35_2 = new byte[]{-50, -50, 103, 51, ByteCompanionObject.MIN_VALUE, 0, -71, 57, -100, -50, 96, 0};
    }

    private static void createRandomMask35_20() {
        _randomMask35_20 = new byte[]{85, -116, -58, 99, 32, 0, -86, 39, 19, -119, -64, 0, -91, 50, -103, 76, -96, 0, 98, 97, -80, -40, 96, 0, 60, 92, 46, 23, 0, 0, -114, -52, 102, 51, 0, 0, 106, 43, 21, -118, -64, 0, 54, 50, -103, 76, -96, 0, -47, 37, -110, -55, 96, 0, -56, 2, -2, -50, -32, 0, -114, -52, 34, 81, 32, 0, 106, 43, 51, 19, 0, 0, 54, 50, -56, 36, -96, 0, -47, 37, ByteCompanionObject.MIN_VALUE, -46, -64, 0, 85, -116, -121, 9, 64, 0, -86, 39, 9, -123, ByteCompanionObject.MIN_VALUE, 0, -91, 50, -112, 104, 32, 0, 98, 97, -31, 40, ByteCompanionObject.MIN_VALUE, 0, 60, 92, 20, -122, 64, 0, 99, 54, 92, -45, ByteCompanionObject.MIN_VALUE, 0};
    }

    private static void createRandomMask35_21() {
        _randomMask35_21 = new byte[]{85, -116, -58, 99, 32, 0, -86, 39, 19, -119, -64, 0, -91, 50, -103, 76, -96, 0, 98, 97, -80, -40, 96, 0, 60, 92, 46, 23, 0, 0, -114, -52, 102, 51, 0, 0, 106, 43, 21, -118, -64, 0, 54, 50, -103, 76, -96, 0, -47, 37, -110, -55, 96, 0, -56, 2, -2, -50, -32, 0, 85, -116, -58, 99, 32, 0, -86, 39, 19, -119, -64, 0, -91, 50, -103, 76, -96, 0, 98, 97, -80, -40, 96, 0, 60, 92, 46, 23, 0, 0, 81, -124, -62, 97, 32, 0, -94, 39, 19, -119, -64, 0, -107, 81, -88, -44, 96, 0, 74, 26, 13, 6, ByteCompanionObject.MIN_VALUE, 0, 48, 104, 52, 26, 0, 0, 44, -119, 68, -94, 64, 0};
    }

    private static void createRandomMask35_22() {
        _randomMask35_22 = new byte[]{85, -116, -58, 99, 32, 0, -86, 39, 19, -119, -64, 0, -91, 50, -103, 76, -96, 0, 98, 97, -80, -40, 96, 0, 60, 92, 46, 23, 0, 0, 81, -124, -62, 97, 32, 0, -94, 39, 19, -119, -64, 0, -107, 81, -88, -44, 96, 0, 74, 26, 13, 6, ByteCompanionObject.MIN_VALUE, 0, 48, 104, 52, 26, 0, 0, 44, -119, 68, -94, 64, 0, 85, -116, -58, 99, 32, 0, -86, 39, 19, -119, -64, 0, -91, 50, -103, 76, -96, 0, 98, 97, -80, -40, 96, 0, 60, 92, 46, 23, 0, 0, -114, -52, 102, 51, 0, 0, 106, 43, 21, -118, -64, 0, 54, 50, -103, 76, -96, 0, -47, 37, -110, -55, 96, 0, -56, 2, -2, -50, -32, 0, -124, -57, PSSSigner.TRAILER_IMPLICIT, -52, 0, 0};
    }

    private static void createRandomMask35_23() {
        _randomMask35_23 = new byte[]{85, -116, -58, 99, 32, 0, -86, 39, 19, -119, -64, 0, -91, 50, -103, 76, -96, 0, 98, 97, -80, -40, 96, 0, 60, 92, 46, 23, 0, 0, 81, -124, -62, 97, 32, 0, -94, 39, 19, -119, -64, 0, -107, 81, -88, -44, 96, 0, 74, 26, 13, 6, ByteCompanionObject.MIN_VALUE, 0, 48, 104, 52, 26, 0, 0, 44, -119, 68, -94, 64, 0, 81, -124, -62, 97, 32, 0, -94, 39, 19, -119, -64, 0, -107, 81, -88, -44, 96, 0, 74, 26, 13, 6, ByteCompanionObject.MIN_VALUE, 0, 48, 104, 52, 26, 0, 0, 44, -119, 68, -94, 64, 0, 85, -116, -58, 99, 32, 0, -86, 39, 19, -119, -64, 0, -91, 50, -103, 76, -96, 0, 98, 97, -80, -40, 96, 0, 60, 92, 46, 23, 0, 0, 81, 53, 45, -122, 32, 0};
    }

    private static void createRandomMask35_24() {
        _randomMask35_24 = new byte[]{81, -124, -62, 97, 32, 0, -94, 39, 19, -119, -64, 0, -107, 81, -88, -44, 96, 0, 74, 26, 13, 6, ByteCompanionObject.MIN_VALUE, 0, 48, 104, 52, 26, 0, 0, 44, -119, 68, -94, 64, 0, 85, -116, -58, 99, 32, 0, -86, 39, 19, -119, -64, 0, -91, 50, -103, 76, -96, 0, 98, 97, -80, -40, 96, 0, 60, 92, 46, 23, 0, 0, 81, 53, 45, -122, 32, 0, 85, -116, -58, 99, 32, 0, -86, 39, 19, -119, -64, 0, -91, 50, -103, 76, -96, 0, 98, 97, -80, -40, 96, 0, 60, 92, 46, 23, 0, 0, 81, -124, -62, 97, 32, 0, -94, 39, 19, -119, -64, 0, -107, 81, -88, -44, 96, 0, 74, 26, 13, 6, ByteCompanionObject.MIN_VALUE, 0, 48, 104, 52, 26, 0, 0, 44, -119, 68, -94, 64, 0, 13, -5, 6, -119, 0, 0};
    }

    private static void createRandomMask35_25() {
        _randomMask35_25 = new byte[]{81, -124, -62, 97, 32, 0, -94, 39, 19, -119, -64, 0, -107, 81, -88, -44, 96, 0, 74, 26, 13, 6, ByteCompanionObject.MIN_VALUE, 0, 48, 104, 52, 26, 0, 0, 44, -119, 68, -94, 64, 0, 85, -116, -58, 99, 32, 0, -86, 39, 19, -119, -64, 0, -91, 50, -103, 76, -96, 0, 98, 97, -80, -40, 96, 0, 60, 92, 46, 23, 0, 0, 81, 53, 45, -122, 32, 0, 81, -124, -62, 97, 32, 0, -94, 39, 19, -119, -64, 0, -107, 81, -88, -44, 96, 0, 74, 26, 13, 6, ByteCompanionObject.MIN_VALUE, 0, 48, 104, 52, 26, 0, 0, 44, -119, 68, -94, 64, 0, 21, -116, 70, 35, 0, 0, -118, 71, 35, -111, -64, 0, 37, -127, -64, -32, 96, 0, 98, 18, -119, 68, -96, 0, 88, 88, 44, 22, 0, 0, 14, 40, -108, 74, 32, 0, -125, 52, 26, 13, 0, 0};
    }

    private static void createRandomMask35_26() {
        _randomMask35_26 = new byte[]{81, -124, -62, 97, 32, 0, -94, 39, 19, -119, -64, 0, -107, 81, -88, -44, 96, 0, 74, 26, 13, 6, ByteCompanionObject.MIN_VALUE, 0, 48, 104, 52, 26, 0, 0, 44, -119, 68, -94, 64, 0, 21, -116, 70, 35, 0, 0, -118, 71, 35, -111, -64, 0, 37, -127, -64, -32, 96, 0, 98, 18, -119, 68, -96, 0, 88, 88, 44, 22, 0, 0, 14, 40, -108, 74, 32, 0, -125, 52, 26, 13, 0, 0, 81, -124, -62, 97, 32, 0, -94, 39, 19, -119, -64, 0, -107, 81, -88, -44, 96, 0, 74, 26, 13, 6, ByteCompanionObject.MIN_VALUE, 0, 48, 104, 52, 26, 0, 0, 44, -119, 68, -94, 64, 0, 85, -116, -58, 99, 32, 0, -86, 39, 19, -119, -64, 0, -91, 50, -103, 76, -96, 0, 98, 97, -80, -40, 96, 0, 60, 92, 46, 23, 0, 0, 81, 53, 45, -122, 32, 0, -60, 87, 112, 71, 64, 0};
    }

    private static void createRandomMask35_27() {
        _randomMask35_27 = new byte[]{81, -124, -62, 97, 32, 0, -94, 39, 19, -119, -64, 0, -107, 81, -88, -44, 96, 0, 74, 26, 13, 6, ByteCompanionObject.MIN_VALUE, 0, 48, 104, 52, 26, 0, 0, 44, -119, 68, -94, 64, 0, 21, -116, 70, 35, 0, 0, -118, 71, 35, -111, -64, 0, 37, -127, -64, -32, 96, 0, 98, 18, -119, 68, -96, 0, 88, 88, 44, 22, 0, 0, 14, 40, -108, 74, 32, 0, -125, 52, 26, 13, 0, 0, 21, -116, 70, 35, 0, 0, -118, 71, 35, -111, -64, 0, 37, -127, -64, -32, 96, 0, 98, 18, -119, 68, -96, 0, 88, 88, 44, 22, 0, 0, 14, 40, -108, 74, 32, 0, -125, 52, 26, 13, 0, 0, 81, -124, -62, 97, 32, 0, -94, 39, 19, -119, -64, 0, -107, 81, -88, -44, 96, 0, 74, 26, 13, 6, ByteCompanionObject.MIN_VALUE, 0, 48, 104, 52, 26, 0, 0, 44, -119, 68, -94, 64, 0, -80, -34, -65, -89, -32, 0};
    }

    private static void createRandomMask35_28() {
        _randomMask35_28 = new byte[]{21, -116, 70, 35, 0, 0, -118, 71, 35, -111, -64, 0, 37, -127, -64, -32, 96, 0, 98, 18, -119, 68, -96, 0, 88, 88, 44, 22, 0, 0, 14, 40, -108, 74, 32, 0, -125, 52, 26, 13, 0, 0, 81, -124, -62, 97, 32, 0, -94, 39, 19, -119, -64, 0, -107, 81, -88, -44, 96, 0, 74, 26, 13, 6, ByteCompanionObject.MIN_VALUE, 0, 48, 104, 52, 26, 0, 0, 44, -119, 68, -94, 64, 0, -80, -34, -65, -89, -32, 0, 81, -124, -62, 97, 32, 0, -94, 39, 19, -119, -64, 0, -107, 81, -88, -44, 96, 0, 74, 26, 13, 6, ByteCompanionObject.MIN_VALUE, 0, 48, 104, 52, 26, 0, 0, 44, -119, 68, -94, 64, 0, 21, -116, 70, 35, 0, 0, -118, 71, 35, -111, -64, 0, 37, -127, -64, -32, 96, 0, 98, 18, -119, 68, -96, 0, 88, 88, 44, 22, 0, 0, 14, 40, -108, 74, 32, 0, -125, 52, 26, 13, 0, 0, 52, 74, ByteCompanionObject.MIN_VALUE, -108, 64, 0};
    }

    private static void createRandomMask35_29() {
        _randomMask35_29 = new byte[]{21, -116, 70, 35, 0, 0, -118, 71, 35, -111, -64, 0, 37, -127, -64, -32, 96, 0, 98, 18, -119, 68, -96, 0, 88, 88, 44, 22, 0, 0, 14, 40, -108, 74, 32, 0, -125, 52, 26, 13, 0, 0, 81, -124, -62, 97, 32, 0, -94, 39, 19, -119, -64, 0, -107, 81, -88, -44, 96, 0, 74, 26, 13, 6, ByteCompanionObject.MIN_VALUE, 0, 48, 104, 52, 26, 0, 0, 44, -119, 68, -94, 64, 0, -80, -34, -65, -89, -32, 0, 21, -116, 70, 35, 0, 0, -118, 71, 35, -111, -64, 0, 37, -127, -64, -32, 96, 0, 98, 18, -119, 68, -96, 0, 88, 88, 44, 22, 0, 0, 14, 40, -108, 74, 32, 0, -125, 52, 26, 13, 0, 0, 37, 44, 22, 11, 0, 0, -118, -111, 72, -92, 64, 0, -111, -64, -32, 112, 32, 0, 104, 6, -125, 65, -96, 0, 50, -56, 100, 50, 0, 0, 67, 69, 34, -111, 64, 0, -60, 48, -104, 76, 32, 0, 28, -94, 81, 40, ByteCompanionObject.MIN_VALUE, 0};
    }

    private static void createRandomMask35_3() {
        _randomMask35_3 = new byte[]{-51, -52, 102, 51, 0, 0, -105, 39, 19, -118, -64, 0, -72, -47, -55, 100, -96, 0};
    }

    private static void createRandomMask35_30() {
        _randomMask35_30 = new byte[]{21, -116, 70, 35, 0, 0, -118, 71, 35, -111, -64, 0, 37, -127, -64, -32, 96, 0, 98, 18, -119, 68, -96, 0, 88, 88, 44, 22, 0, 0, 14, 40, -108, 74, 32, 0, -125, 52, 26, 13, 0, 0, 37, 44, 22, 11, 0, 0, -118, -111, 72, -92, 64, 0, -111, -64, -32, 112, 32, 0, 104, 6, -125, 65, -96, 0, 50, -56, 100, 50, 0, 0, 67, 69, 34, -111, 64, 0, -60, 48, -104, 76, 32, 0, 28, -94, 81, 40, ByteCompanionObject.MIN_VALUE, 0, 21, -116, 70, 35, 0, 0, -118, 71, 35, -111, -64, 0, 37, -127, -64, -32, 96, 0, 98, 18, -119, 68, -96, 0, 88, 88, 44, 22, 0, 0, 14, 40, -108, 74, 32, 0, -125, 52, 26, 13, 0, 0, 81, -124, -62, 97, 32, 0, -94, 39, 19, -119, -64, 0, -107, 81, -88, -44, 96, 0, 74, 26, 13, 6, ByteCompanionObject.MIN_VALUE, 0, 48, 104, 52, 26, 0, 0, 44, -119, 68, -94, 64, 0, -80, -34, -65, -89, -32, 0, 50, 27, -97, 9, 32, 0};
    }

    private static void createRandomMask35_31() {
        _randomMask35_31 = new byte[]{21, -116, 70, 35, 0, 0, -118, 71, 35, -111, -64, 0, 37, -127, -64, -32, 96, 0, 98, 18, -119, 68, -96, 0, 88, 88, 44, 22, 0, 0, 14, 40, -108, 74, 32, 0, -125, 52, 26, 13, 0, 0, 37, 44, 22, 11, 0, 0, -118, -111, 72, -92, 64, 0, -111, -64, -32, 112, 32, 0, 104, 6, -125, 65, -96, 0, 50, -56, 100, 50, 0, 0, 67, 69, 34, -111, 64, 0, -60, 48, -104, 76, 32, 0, 28, -94, 81, 40, ByteCompanionObject.MIN_VALUE, 0, 37, 44, 22, 11, 0, 0, -118, -111, 72, -92, 64, 0, -111, -64, -32, 112, 32, 0, 104, 6, -125, 65, -96, 0, 50, -56, 100, 50, 0, 0, 67, 69, 34, -111, 64, 0, -60, 48, -104, 76, 32, 0, 28, -94, 81, 40, ByteCompanionObject.MIN_VALUE, 0, 21, -116, 70, 35, 0, 0, -118, 71, 35, -111, -64, 0, 37, -127, -64, -32, 96, 0, 98, 18, -119, 68, -96, 0, 88, 88, 44, 22, 0, 0, 14, 40, -108, 74, 32, 0, -125, 52, 26, 13, 0, 0, 10, 28, 119, -7, 0, 0};
    }

    private static void createRandomMask35_32() {
        _randomMask35_32 = new byte[]{37, 44, 22, 11, 0, 0, -118, -111, 72, -92, 64, 0, -111, -64, -32, 112, 32, 0, 104, 6, -125, 65, -96, 0, 50, -56, 100, 50, 0, 0, 67, 69, 34, -111, 64, 0, -60, 48, -104, 76, 32, 0, 28, -94, 81, 40, ByteCompanionObject.MIN_VALUE, 0, 21, -116, 70, 35, 0, 0, -118, 71, 35, -111, -64, 0, 37, -127, -64, -32, 96, 0, 98, 18, -119, 68, -96, 0, 88, 88, 44, 22, 0, 0, 14, 40, -108, 74, 32, 0, -125, 52, 26, 13, 0, 0, 10, 28, 119, -7, 0, 0, 21, -116, 70, 35, 0, 0, -118, 71, 35, -111, -64, 0, 37, -127, -64, -32, 96, 0, 98, 18, -119, 68, -96, 0, 88, 88, 44, 22, 0, 0, 14, 40, -108, 74, 32, 0, -125, 52, 26, 13, 0, 0, 37, 44, 22, 11, 0, 0, -118, -111, 72, -92, 64, 0, -111, -64, -32, 112, 32, 0, 104, 6, -125, 65, -96, 0, 50, -56, 100, 50, 0, 0, 67, 69, 34, -111, 64, 0, -60, 48, -104, 76, 32, 0, 28, -94, 81, 40, ByteCompanionObject.MIN_VALUE, 0, -21, 49, 123, ByteCompanionObject.MIN_VALUE, 0, 0};
    }

    private static void createRandomMask35_33() {
        _randomMask35_33 = new byte[]{37, 44, 22, 11, 0, 0, -118, -111, 72, -92, 64, 0, -111, -64, -32, 112, 32, 0, 104, 6, -125, 65, -96, 0, 50, -56, 100, 50, 0, 0, 67, 69, 34, -111, 64, 0, -60, 48, -104, 76, 32, 0, 28, -94, 81, 40, ByteCompanionObject.MIN_VALUE, 0, 21, -116, 70, 35, 0, 0, -118, 71, 35, -111, -64, 0, 37, -127, -64, -32, 96, 0, 98, 18, -119, 68, -96, 0, 88, 88, 44, 22, 0, 0, 14, 40, -108, 74, 32, 0, -125, 52, 26, 13, 0, 0, 10, 28, 119, -7, 0, 0, 37, 44, 22, 11, 0, 0, -118, -111, 72, -92, 64, 0, -111, -64, -32, 112, 32, 0, 104, 6, -125, 65, -96, 0, 50, -56, 100, 50, 0, 0, 67, 69, 34, -111, 64, 0, -60, 48, -104, 76, 32, 0, 28, -94, 81, 40, ByteCompanionObject.MIN_VALUE, 0, 37, 76, 38, 19, 0, 0, -118, 102, 51, 25, ByteCompanionObject.MIN_VALUE, 0, -111, -111, 72, -92, 64, 0, 104, 66, -95, 80, -96, 0, 50, -92, 82, 41, 0, 0, 67, 19, 9, -124, -64, 0, -60, 48, -104, 76, 32, 0, 28, -120, -60, 98, 32, 0, 60, 9, 4, -126, 64, 0};
    }

    private static void createRandomMask35_34() {
        _randomMask35_34 = new byte[]{37, 44, 22, 11, 0, 0, -118, -111, 72, -92, 64, 0, -111, -64, -32, 112, 32, 0, 104, 6, -125, 65, -96, 0, 50, -56, 100, 50, 0, 0, 67, 69, 34, -111, 64, 0, -60, 48, -104, 76, 32, 0, 28, -94, 81, 40, ByteCompanionObject.MIN_VALUE, 0, 37, 76, 38, 19, 0, 0, -118, 102, 51, 25, ByteCompanionObject.MIN_VALUE, 0, -111, -111, 72, -92, 64, 0, 104, 66, -95, 80, -96, 0, 50, -92, 82, 41, 0, 0, 67, 19, 9, -124, -64, 0, -60, 48, -104, 76, 32, 0, 28, -120, -60, 98, 32, 0, 60, 9, 4, -126, 64, 0, 37, 44, 22, 11, 0, 0, -118, -111, 72, -92, 64, 0, -111, -64, -32, 112, 32, 0, 104, 6, -125, 65, -96, 0, 50, -56, 100, 50, 0, 0, 67, 69, 34, -111, 64, 0, -60, 48, -104, 76, 32, 0, 28, -94, 81, 40, ByteCompanionObject.MIN_VALUE, 0, 21, -116, 70, 35, 0, 0, -118, 71, 35, -111, -64, 0, 37, -127, -64, -32, 96, 0, 98, 18, -119, 68, -96, 0, 88, 88, 44, 22, 0, 0, 14, 40, -108, 74, 32, 0, -125, 52, 26, 13, 0, 0, 10, 28, 119, -7, 0, 0, 112, 7, -51, -116, -64, 0};
    }

    private static void createRandomMask35_35() {
        _randomMask35_35 = new byte[]{37, 44, 22, 11, 0, 0, -118, -111, 72, -92, 64, 0, -111, -64, -32, 112, 32, 0, 104, 6, -125, 65, -96, 0, 50, -56, 100, 50, 0, 0, 67, 69, 34, -111, 64, 0, -60, 48, -104, 76, 32, 0, 28, -94, 81, 40, ByteCompanionObject.MIN_VALUE, 0, 37, 76, 38, 19, 0, 0, -118, 102, 51, 25, ByteCompanionObject.MIN_VALUE, 0, -111, -111, 72, -92, 64, 0, 104, 66, -95, 80, -96, 0, 50, -92, 82, 41, 0, 0, 67, 19, 9, -124, -64, 0, -60, 48, -104, 76, 32, 0, 28, -120, -60, 98, 32, 0, 60, 9, 4, -126, 64, 0, 37, 44, 38, 19, 0, 0, -118, -111, 51, 25, ByteCompanionObject.MIN_VALUE, 0, -111, -64, -56, -92, 64, 0, 104, 6, -95, 80, -96, 0, 50, -56, 82, 41, 0, 0, 67, 69, 9, -124, -64, 0, -60, 48, -104, 76, 32, 0, 28, -94, 68, 98, 32, 0, 37, 76, 4, -126, 64, 0, -118, 102, 22, 11, 0, 0, -111, -111, 72, -92, 64, 0, 104, 66, -32, 112, 32, 0, 50, -92, 3, 65, -96, 0, 67, 19, 100, 50, 0, 0, -60, 48, -94, -111, 64, 0, 28, -120, -104, 76, 32, 0, 60, 9, 81, 40, ByteCompanionObject.MIN_VALUE, 0, -62, 28, 104, 1, -96, 0};
    }

    private static void createRandomMask35_4() {
        _randomMask35_4 = new byte[]{-54, -20, 118, 59, 0, 0, -87, 103, 51, -103, -64, 0, 58, -79, -40, -20, 96, 0, 85, 90, -83, 86, -96, 0};
    }

    private static void createRandomMask35_5() {
        _randomMask35_5 = new byte[]{85, 68, -90, 83, 32, 0, 42, 102, 51, 25, ByteCompanionObject.MIN_VALUE, 0, 37, -95, -116, -24, 96, 0, -30, 18, -50, 68, -96, 0, -103, -104, 113, -90, 0, 0};
    }

    private static void createRandomMask35_6() {
        _randomMask35_6 = new byte[]{-47, 76, 102, 19, 0, 0, -94, -59, 34, -79, 64, 0, -107, 48, -40, 76, 32, 0, -54, 10, -59, 66, -96, 0, -92, -86, 20, -87, ByteCompanionObject.MIN_VALUE, 0, 120, 21, 83, 5, 64, 0};
    }

    private static void createRandomMask35_7() {
        _randomMask35_7 = new byte[]{21, 68, -94, 81, 32, 0, -118, 35, 17, -120, -64, 0, -123, -111, 72, -92, 64, 0, 50, 10, -123, 66, -96, 0, 88, 52, 26, 13, 0, 0, 44, 13, 5, -125, 64, 0, 67, -56, 112, 50, 0, 0};
    }

    private static void createRandomMask35_8() {
        _randomMask35_8 = new byte[]{100, 22, 11, 5, ByteCompanionObject.MIN_VALUE, 0, -94, -62, 97, 48, ByteCompanionObject.MIN_VALUE, 0, 81, 96, -80, 88, 32, 0, 74, -123, 66, -95, 64, 0, 56, 76, 38, 19, 0, 0, -119, 41, 20, -118, 64, 0, 7, 17, -120, -60, 96, 0, -108, -80, 88, 44, 0, 0};
    }

    private static void createRandomMask35_9() {
        _randomMask35_9 = new byte[]{-114, -52, 34, 81, 32, 0, 106, 43, 51, 19, 0, 0, 54, 50, -56, 36, -96, 0, -47, 37, ByteCompanionObject.MIN_VALUE, -46, -64, 0, 85, -116, -121, 9, 64, 0, -86, 39, 9, -123, ByteCompanionObject.MIN_VALUE, 0, -91, 50, -112, 104, 32, 0, 98, 97, -31, 40, ByteCompanionObject.MIN_VALUE, 0, 60, 92, 20, -122, 64, 0};
    }

    private static void createRandomMask36_1() {
        _randomMask36_1 = new byte[]{-1, -1, -1, -1, -16, 0};
    }

    private static void createRandomMask36_10() {
        _randomMask36_10 = new byte[]{-116, -58, 99, 49, -112, 0, 39, 19, -119, -60, -32, 0, 50, -103, 76, -90, 80, 0, 97, -80, -40, 108, 48, 0, 92, 46, 23, 11, ByteCompanionObject.MIN_VALUE, 0, -52, 102, 51, 25, ByteCompanionObject.MIN_VALUE, 0, 43, 21, -118, -59, 96, 0, 50, -103, 76, -90, 80, 0, 37, -110, -55, 100, -80, 0, -3, -99, -1, 103, 112, 0};
    }

    private static void createRandomMask36_11() {
        _randomMask36_11 = new byte[]{-116, -58, 99, 49, -112, 0, 39, 19, -119, -60, -32, 0, 50, -103, 76, -90, 80, 0, 97, -80, -40, 108, 48, 0, 92, 46, 23, 11, ByteCompanionObject.MIN_VALUE, 0, -124, -62, 97, 48, -112, 0, 39, 19, -119, -60, -32, 0, 81, -88, -44, 106, 48, 0, 26, 13, 6, -125, 64, 0, 104, 52, 26, 13, 0, 0, -119, 68, -94, 81, 32, 0};
    }

    private static void createRandomMask36_12() {
        _randomMask36_12 = new byte[]{-124, -62, 97, 48, -112, 0, 39, 19, -119, -60, -32, 0, 81, -88, -44, 106, 48, 0, 26, 13, 6, -125, 64, 0, 104, 52, 26, 13, 0, 0, -119, 68, -94, 81, 32, 0, -116, -58, 99, 49, -112, 0, 39, 19, -119, -60, -32, 0, 50, -103, 76, -90, 80, 0, 97, -80, -40, 108, 48, 0, 92, 46, 23, 11, ByteCompanionObject.MIN_VALUE, 0, 91, 12, 86, -61, Tnaf.POW_2_WIDTH, 0};
    }

    private static void createRandomMask36_13() {
        _randomMask36_13 = new byte[]{-124, -62, 97, 48, -112, 0, 39, 19, -119, -60, -32, 0, 81, -88, -44, 106, 48, 0, 26, 13, 6, -125, 64, 0, 104, 52, 26, 13, 0, 0, -119, 68, -94, 81, 32, 0, -116, 70, 35, 17, ByteCompanionObject.MIN_VALUE, 0, 71, 35, -111, -56, -32, 0, -127, -64, -32, 112, 48, 0, 18, -119, 68, -94, 80, 0, 88, 44, 22, 11, 0, 0, 40, -108, 74, 37, Tnaf.POW_2_WIDTH, 0, 52, 26, 13, 6, ByteCompanionObject.MIN_VALUE, 0};
    }

    private static void createRandomMask36_14() {
        _randomMask36_14 = new byte[]{-116, 70, 35, 17, ByteCompanionObject.MIN_VALUE, 0, 71, 35, -111, -56, -32, 0, -127, -64, -32, 112, 48, 0, 18, -119, 68, -94, 80, 0, 88, 44, 22, 11, 0, 0, 40, -108, 74, 37, Tnaf.POW_2_WIDTH, 0, 52, 26, 13, 6, ByteCompanionObject.MIN_VALUE, 0, -124, -62, 97, 48, -112, 0, 39, 19, -119, -60, -32, 0, 81, -88, -44, 106, 48, 0, 26, 13, 6, -125, 64, 0, 104, 52, 26, 13, 0, 0, -119, 68, -94, 81, 32, 0, ByteCompanionObject.MAX_VALUE, 79, -33, -45, -16, 0};
    }

    private static void createRandomMask36_15() {
        _randomMask36_15 = new byte[]{-116, 70, 35, 17, ByteCompanionObject.MIN_VALUE, 0, 71, 35, -111, -56, -32, 0, -127, -64, -32, 112, 48, 0, 18, -119, 68, -94, 80, 0, 88, 44, 22, 11, 0, 0, 40, -108, 74, 37, Tnaf.POW_2_WIDTH, 0, 52, 26, 13, 6, ByteCompanionObject.MIN_VALUE, 0, 44, 22, 11, 5, ByteCompanionObject.MIN_VALUE, 0, -111, 72, -92, 82, 32, 0, -64, -32, 112, 56, Tnaf.POW_2_WIDTH, 0, 6, -125, 65, -96, -48, 0, -56, 100, 50, 25, 0, 0, 69, 34, -111, 72, -96, 0, 48, -104, 76, 38, Tnaf.POW_2_WIDTH, 0, -94, 81, 40, -108, 64, 0};
    }

    private static void createRandomMask36_16() {
        _randomMask36_16 = new byte[]{44, 22, 11, 5, ByteCompanionObject.MIN_VALUE, 0, -111, 72, -92, 82, 32, 0, -64, -32, 112, 56, Tnaf.POW_2_WIDTH, 0, 6, -125, 65, -96, -48, 0, -56, 100, 50, 25, 0, 0, 69, 34, -111, 72, -96, 0, 48, -104, 76, 38, Tnaf.POW_2_WIDTH, 0, -94, 81, 40, -108, 64, 0, -116, 70, 35, 17, ByteCompanionObject.MIN_VALUE, 0, 71, 35, -111, -56, -32, 0, -127, -64, -32, 112, 48, 0, 18, -119, 68, -94, 80, 0, 88, 44, 22, 11, 0, 0, 40, -108, 74, 37, Tnaf.POW_2_WIDTH, 0, 52, 26, 13, 6, ByteCompanionObject.MIN_VALUE, 0, -17, -14, 59, -4, ByteCompanionObject.MIN_VALUE, 0};
    }

    private static void createRandomMask36_17() {
        _randomMask36_17 = new byte[]{44, 22, 11, 5, ByteCompanionObject.MIN_VALUE, 0, -111, 72, -92, 82, 32, 0, -64, -32, 112, 56, Tnaf.POW_2_WIDTH, 0, 6, -125, 65, -96, -48, 0, -56, 100, 50, 25, 0, 0, 69, 34, -111, 72, -96, 0, 48, -104, 76, 38, Tnaf.POW_2_WIDTH, 0, -94, 81, 40, -108, 64, 0, 76, 38, 19, 9, ByteCompanionObject.MIN_VALUE, 0, 102, 51, 25, -116, -64, 0, -111, 72, -92, 82, 32, 0, 66, -95, 80, -88, 80, 0, -92, 82, 41, 20, ByteCompanionObject.MIN_VALUE, 0, 19, 9, -124, -62, 96, 0, 48, -104, 76, 38, Tnaf.POW_2_WIDTH, 0, -120, -60, 98, 49, Tnaf.POW_2_WIDTH, 0, 9, 4, -126, 65, 32, 0};
    }

    private static void createRandomMask36_18() {
        _randomMask36_18 = new byte[]{76, 38, 19, 9, ByteCompanionObject.MIN_VALUE, 0, 102, 51, 25, -116, -64, 0, -111, 72, -92, 82, 32, 0, 66, -95, 80, -88, 80, 0, -92, 82, 41, 20, ByteCompanionObject.MIN_VALUE, 0, 19, 9, -124, -62, 96, 0, 48, -104, 76, 38, Tnaf.POW_2_WIDTH, 0, -120, -60, 98, 49, Tnaf.POW_2_WIDTH, 0, 9, 4, -126, 65, 32, 0, 44, 22, 11, 5, ByteCompanionObject.MIN_VALUE, 0, -111, 72, -92, 82, 32, 0, -64, -32, 112, 56, Tnaf.POW_2_WIDTH, 0, 6, -125, 65, -96, -48, 0, -56, 100, 50, 25, 0, 0, 69, 34, -111, 72, -96, 0, 48, -104, 76, 38, Tnaf.POW_2_WIDTH, 0, -94, 81, 40, -108, 64, 0, -48, 3, 116, 0, -48, 0};
    }

    private static void createRandomMask36_19() {
        _randomMask36_19 = new byte[]{68, -94, 81, 40, -112, 0, 102, 38, 25, -119, ByteCompanionObject.MIN_VALUE, 0, -112, 73, 100, 18, 80, 0, 1, -91, ByteCompanionObject.MIN_VALUE, 105, 96, 0, 14, 18, -125, -124, -96, 0, 19, 11, 4, -62, -64, 0, 32, -48, 72, 52, Tnaf.POW_2_WIDTH, 0, -62, 81, 48, -108, 64, 0, 41, 12, -118, 67, 32, 0, -116, -58, 99, 49, -112, 0, 39, 19, -119, -60, -32, 0, 50, -103, 76, -90, 80, 0, 97, -80, -40, 108, 48, 0, 92, 46, 23, 11, ByteCompanionObject.MIN_VALUE, 0, -52, 102, 51, 25, ByteCompanionObject.MIN_VALUE, 0, 43, 21, -118, -59, 96, 0, 50, -103, 76, -90, 80, 0, 37, -110, -55, 100, -80, 0, -3, -99, -1, 103, 112, 0};
    }

    private static void createRandomMask36_2() {
        _randomMask36_2 = new byte[]{-50, 103, 51, -103, -64, 0, 57, -100, -50, 103, 48, 0};
    }

    private static void createRandomMask36_20() {
        _randomMask36_20 = new byte[]{-116, -58, 99, 49, -112, 0, 39, 19, -119, -60, -32, 0, 50, -103, 76, -90, 80, 0, 97, -80, -40, 108, 48, 0, 92, 46, 23, 11, ByteCompanionObject.MIN_VALUE, 0, -52, 102, 51, 25, ByteCompanionObject.MIN_VALUE, 0, 43, 21, -118, -59, 96, 0, 50, -103, 76, -90, 80, 0, 37, -110, -55, 100, -80, 0, -3, -99, -1, 103, 112, 0, 68, -94, 81, 40, -112, 0, 102, 38, 25, -119, ByteCompanionObject.MIN_VALUE, 0, -112, 73, 100, 18, 80, 0, 1, -91, ByteCompanionObject.MIN_VALUE, 105, 96, 0, 14, 18, -125, -124, -96, 0, 19, 11, 4, -62, -64, 0, 32, -48, 72, 52, Tnaf.POW_2_WIDTH, 0, -62, 81, 48, -108, 64, 0, 41, 12, -118, 67, 32, 0, 69, -71, 8, 22, 48, 0};
    }

    private static void createRandomMask36_21() {
        _randomMask36_21 = new byte[]{-116, -58, 99, 49, -112, 0, 39, 19, -119, -60, -32, 0, 50, -103, 76, -90, 80, 0, 97, -80, -40, 108, 48, 0, 92, 46, 23, 11, ByteCompanionObject.MIN_VALUE, 0, -52, 102, 51, 25, ByteCompanionObject.MIN_VALUE, 0, 43, 21, -118, -59, 96, 0, 50, -103, 76, -90, 80, 0, 37, -110, -55, 100, -80, 0, -3, -99, -1, 103, 112, 0, -116, -58, 99, 49, -112, 0, 39, 19, -119, -60, -32, 0, 50, -103, 76, -90, 80, 0, 97, -80, -40, 108, 48, 0, 92, 46, 23, 11, ByteCompanionObject.MIN_VALUE, 0, -124, -62, 97, 48, -112, 0, 39, 19, -119, -60, -32, 0, 81, -88, -44, 106, 48, 0, 26, 13, 6, -125, 64, 0, 104, 52, 26, 13, 0, 0, -119, 68, -94, 81, 32, 0};
    }

    private static void createRandomMask36_22() {
        _randomMask36_22 = new byte[]{-116, -58, 99, 49, -112, 0, 39, 19, -119, -60, -32, 0, 50, -103, 76, -90, 80, 0, 97, -80, -40, 108, 48, 0, 92, 46, 23, 11, ByteCompanionObject.MIN_VALUE, 0, -124, -62, 97, 48, -112, 0, 39, 19, -119, -60, -32, 0, 81, -88, -44, 106, 48, 0, 26, 13, 6, -125, 64, 0, 104, 52, 26, 13, 0, 0, -119, 68, -94, 81, 32, 0, -116, -58, 99, 49, -112, 0, 39, 19, -119, -60, -32, 0, 50, -103, 76, -90, 80, 0, 97, -80, -40, 108, 48, 0, 92, 46, 23, 11, ByteCompanionObject.MIN_VALUE, 0, -52, 102, 51, 25, ByteCompanionObject.MIN_VALUE, 0, 43, 21, -118, -59, 96, 0, 50, -103, 76, -90, 80, 0, 37, -110, -55, 100, -80, 0, -3, -99, -1, 103, 112, 0, 113, 4, -70, 123, -32, 0};
    }

    private static void createRandomMask36_23() {
        _randomMask36_23 = new byte[]{-116, -58, 99, 49, -112, 0, 39, 19, -119, -60, -32, 0, 50, -103, 76, -90, 80, 0, 97, -80, -40, 108, 48, 0, 92, 46, 23, 11, ByteCompanionObject.MIN_VALUE, 0, -124, -62, 97, 48, -112, 0, 39, 19, -119, -60, -32, 0, 81, -88, -44, 106, 48, 0, 26, 13, 6, -125, 64, 0, 104, 52, 26, 13, 0, 0, -119, 68, -94, 81, 32, 0, -124, -62, 97, 48, -112, 0, 39, 19, -119, -60, -32, 0, 81, -88, -44, 106, 48, 0, 26, 13, 6, -125, 64, 0, 104, 52, 26, 13, 0, 0, -119, 68, -94, 81, 32, 0, -116, -58, 99, 49, -112, 0, 39, 19, -119, -60, -32, 0, 50, -103, 76, -90, 80, 0, 97, -80, -40, 108, 48, 0, 92, 46, 23, 11, ByteCompanionObject.MIN_VALUE, 0, 91, 12, 86, -61, Tnaf.POW_2_WIDTH, 0};
    }

    private static void createRandomMask36_24() {
        _randomMask36_24 = new byte[]{-124, -62, 97, 48, -112, 0, 39, 19, -119, -60, -32, 0, 81, -88, -44, 106, 48, 0, 26, 13, 6, -125, 64, 0, 104, 52, 26, 13, 0, 0, -119, 68, -94, 81, 32, 0, -116, -58, 99, 49, -112, 0, 39, 19, -119, -60, -32, 0, 50, -103, 76, -90, 80, 0, 97, -80, -40, 108, 48, 0, 92, 46, 23, 11, ByteCompanionObject.MIN_VALUE, 0, 91, 12, 86, -61, Tnaf.POW_2_WIDTH, 0, -116, -58, 99, 49, -112, 0, 39, 19, -119, -60, -32, 0, 50, -103, 76, -90, 80, 0, 97, -80, -40, 108, 48, 0, 92, 46, 23, 11, ByteCompanionObject.MIN_VALUE, 0, -124, -62, 97, 48, -112, 0, 39, 19, -119, -60, -32, 0, 81, -88, -44, 106, 48, 0, 26, 13, 6, -125, 64, 0, 104, 52, 26, 13, 0, 0, -119, 68, -94, 81, 32, 0, 118, 58, -21, 23, -64, 0};
    }

    private static void createRandomMask36_25() {
        _randomMask36_25 = new byte[]{-124, -62, 97, 48, -112, 0, 39, 19, -119, -60, -32, 0, 81, -88, -44, 106, 48, 0, 26, 13, 6, -125, 64, 0, 104, 52, 26, 13, 0, 0, -119, 68, -94, 81, 32, 0, -116, -58, 99, 49, -112, 0, 39, 19, -119, -60, -32, 0, 50, -103, 76, -90, 80, 0, 97, -80, -40, 108, 48, 0, 92, 46, 23, 11, ByteCompanionObject.MIN_VALUE, 0, 91, 12, 86, -61, Tnaf.POW_2_WIDTH, 0, -124, -62, 97, 48, -112, 0, 39, 19, -119, -60, -32, 0, 81, -88, -44, 106, 48, 0, 26, 13, 6, -125, 64, 0, 104, 52, 26, 13, 0, 0, -119, 68, -94, 81, 32, 0, -116, 70, 35, 17, ByteCompanionObject.MIN_VALUE, 0, 71, 35, -111, -56, -32, 0, -127, -64, -32, 112, 48, 0, 18, -119, 68, -94, 80, 0, 88, 44, 22, 11, 0, 0, 40, -108, 74, 37, Tnaf.POW_2_WIDTH, 0, 52, 26, 13, 6, ByteCompanionObject.MIN_VALUE, 0};
    }

    private static void createRandomMask36_26() {
        _randomMask36_26 = new byte[]{-124, -62, 97, 48, -112, 0, 39, 19, -119, -60, -32, 0, 81, -88, -44, 106, 48, 0, 26, 13, 6, -125, 64, 0, 104, 52, 26, 13, 0, 0, -119, 68, -94, 81, 32, 0, -116, 70, 35, 17, ByteCompanionObject.MIN_VALUE, 0, 71, 35, -111, -56, -32, 0, -127, -64, -32, 112, 48, 0, 18, -119, 68, -94, 80, 0, 88, 44, 22, 11, 0, 0, 40, -108, 74, 37, Tnaf.POW_2_WIDTH, 0, 52, 26, 13, 6, ByteCompanionObject.MIN_VALUE, 0, -124, -62, 97, 48, -112, 0, 39, 19, -119, -60, -32, 0, 81, -88, -44, 106, 48, 0, 26, 13, 6, -125, 64, 0, 104, 52, 26, 13, 0, 0, -119, 68, -94, 81, 32, 0, -116, -58, 99, 49, -112, 0, 39, 19, -119, -60, -32, 0, 50, -103, 76, -90, 80, 0, 97, -80, -40, 108, 48, 0, 92, 46, 23, 11, ByteCompanionObject.MIN_VALUE, 0, 91, 12, 86, -61, Tnaf.POW_2_WIDTH, 0, -20, 88, 14, 108, -32, 0};
    }

    private static void createRandomMask36_27() {
        _randomMask36_27 = new byte[]{-124, -62, 97, 48, -112, 0, 39, 19, -119, -60, -32, 0, 81, -88, -44, 106, 48, 0, 26, 13, 6, -125, 64, 0, 104, 52, 26, 13, 0, 0, -119, 68, -94, 81, 32, 0, -116, 70, 35, 17, ByteCompanionObject.MIN_VALUE, 0, 71, 35, -111, -56, -32, 0, -127, -64, -32, 112, 48, 0, 18, -119, 68, -94, 80, 0, 88, 44, 22, 11, 0, 0, 40, -108, 74, 37, Tnaf.POW_2_WIDTH, 0, 52, 26, 13, 6, ByteCompanionObject.MIN_VALUE, 0, -116, 70, 35, 17, ByteCompanionObject.MIN_VALUE, 0, 71, 35, -111, -56, -32, 0, -127, -64, -32, 112, 48, 0, 18, -119, 68, -94, 80, 0, 88, 44, 22, 11, 0, 0, 40, -108, 74, 37, Tnaf.POW_2_WIDTH, 0, 52, 26, 13, 6, ByteCompanionObject.MIN_VALUE, 0, -124, -62, 97, 48, -112, 0, 39, 19, -119, -60, -32, 0, 81, -88, -44, 106, 48, 0, 26, 13, 6, -125, 64, 0, 104, 52, 26, 13, 0, 0, -119, 68, -94, 81, 32, 0, ByteCompanionObject.MAX_VALUE, 79, -33, -45, -16, 0};
    }

    private static void createRandomMask36_28() {
        _randomMask36_28 = new byte[]{-116, 70, 35, 17, ByteCompanionObject.MIN_VALUE, 0, 71, 35, -111, -56, -32, 0, -127, -64, -32, 112, 48, 0, 18, -119, 68, -94, 80, 0, 88, 44, 22, 11, 0, 0, 40, -108, 74, 37, Tnaf.POW_2_WIDTH, 0, 52, 26, 13, 6, ByteCompanionObject.MIN_VALUE, 0, -124, -62, 97, 48, -112, 0, 39, 19, -119, -60, -32, 0, 81, -88, -44, 106, 48, 0, 26, 13, 6, -125, 64, 0, 104, 52, 26, 13, 0, 0, -119, 68, -94, 81, 32, 0, ByteCompanionObject.MAX_VALUE, 79, -33, -45, -16, 0, -124, -62, 97, 48, -112, 0, 39, 19, -119, -60, -32, 0, 81, -88, -44, 106, 48, 0, 26, 13, 6, -125, 64, 0, 104, 52, 26, 13, 0, 0, -119, 68, -94, 81, 32, 0, -116, 70, 35, 17, ByteCompanionObject.MIN_VALUE, 0, 71, 35, -111, -56, -32, 0, -127, -64, -32, 112, 48, 0, 18, -119, 68, -94, 80, 0, 88, 44, 22, 11, 0, 0, 40, -108, 74, 37, Tnaf.POW_2_WIDTH, 0, 52, 26, 13, 6, ByteCompanionObject.MIN_VALUE, 0, 41, -3, -111, 111, -48, 0};
    }

    private static void createRandomMask36_29() {
        _randomMask36_29 = new byte[]{-116, 70, 35, 17, ByteCompanionObject.MIN_VALUE, 0, 71, 35, -111, -56, -32, 0, -127, -64, -32, 112, 48, 0, 18, -119, 68, -94, 80, 0, 88, 44, 22, 11, 0, 0, 40, -108, 74, 37, Tnaf.POW_2_WIDTH, 0, 52, 26, 13, 6, ByteCompanionObject.MIN_VALUE, 0, -124, -62, 97, 48, -112, 0, 39, 19, -119, -60, -32, 0, 81, -88, -44, 106, 48, 0, 26, 13, 6, -125, 64, 0, 104, 52, 26, 13, 0, 0, -119, 68, -94, 81, 32, 0, ByteCompanionObject.MAX_VALUE, 79, -33, -45, -16, 0, -116, 70, 35, 17, ByteCompanionObject.MIN_VALUE, 0, 71, 35, -111, -56, -32, 0, -127, -64, -32, 112, 48, 0, 18, -119, 68, -94, 80, 0, 88, 44, 22, 11, 0, 0, 40, -108, 74, 37, Tnaf.POW_2_WIDTH, 0, 52, 26, 13, 6, ByteCompanionObject.MIN_VALUE, 0, 44, 22, 11, 5, ByteCompanionObject.MIN_VALUE, 0, -111, 72, -92, 82, 32, 0, -64, -32, 112, 56, Tnaf.POW_2_WIDTH, 0, 6, -125, 65, -96, -48, 0, -56, 100, 50, 25, 0, 0, 69, 34, -111, 72, -96, 0, 48, -104, 76, 38, Tnaf.POW_2_WIDTH, 0, -94, 81, 40, -108, 64, 0};
    }

    private static void createRandomMask36_3() {
        _randomMask36_3 = new byte[]{-52, 102, 51, 25, ByteCompanionObject.MIN_VALUE, 0, 39, 21, -119, -59, 96, 0, -110, -55, 100, -78, 80, 0};
    }

    private static void createRandomMask36_30() {
        _randomMask36_30 = new byte[]{-116, 70, 35, 17, ByteCompanionObject.MIN_VALUE, 0, 71, 35, -111, -56, -32, 0, -127, -64, -32, 112, 48, 0, 18, -119, 68, -94, 80, 0, 88, 44, 22, 11, 0, 0, 40, -108, 74, 37, Tnaf.POW_2_WIDTH, 0, 52, 26, 13, 6, ByteCompanionObject.MIN_VALUE, 0, 44, 22, 11, 5, ByteCompanionObject.MIN_VALUE, 0, -111, 72, -92, 82, 32, 0, -64, -32, 112, 56, Tnaf.POW_2_WIDTH, 0, 6, -125, 65, -96, -48, 0, -56, 100, 50, 25, 0, 0, 69, 34, -111, 72, -96, 0, 48, -104, 76, 38, Tnaf.POW_2_WIDTH, 0, -94, 81, 40, -108, 64, 0, -116, 70, 35, 17, ByteCompanionObject.MIN_VALUE, 0, 71, 35, -111, -56, -32, 0, -127, -64, -32, 112, 48, 0, 18, -119, 68, -94, 80, 0, 88, 44, 22, 11, 0, 0, 40, -108, 74, 37, Tnaf.POW_2_WIDTH, 0, 52, 26, 13, 6, ByteCompanionObject.MIN_VALUE, 0, -124, -62, 97, 48, -112, 0, 39, 19, -119, -60, -32, 0, 81, -88, -44, 106, 48, 0, 26, 13, 6, -125, 64, 0, 104, 52, 26, 13, 0, 0, -119, 68, -94, 81, 32, 0, ByteCompanionObject.MAX_VALUE, 79, -33, -45, -16, 0, -59, 56, -69, -104, ByteCompanionObject.MIN_VALUE, 0};
    }

    private static void createRandomMask36_31() {
        _randomMask36_31 = new byte[]{-116, 70, 35, 17, ByteCompanionObject.MIN_VALUE, 0, 71, 35, -111, -56, -32, 0, -127, -64, -32, 112, 48, 0, 18, -119, 68, -94, 80, 0, 88, 44, 22, 11, 0, 0, 40, -108, 74, 37, Tnaf.POW_2_WIDTH, 0, 52, 26, 13, 6, ByteCompanionObject.MIN_VALUE, 0, 44, 22, 11, 5, ByteCompanionObject.MIN_VALUE, 0, -111, 72, -92, 82, 32, 0, -64, -32, 112, 56, Tnaf.POW_2_WIDTH, 0, 6, -125, 65, -96, -48, 0, -56, 100, 50, 25, 0, 0, 69, 34, -111, 72, -96, 0, 48, -104, 76, 38, Tnaf.POW_2_WIDTH, 0, -94, 81, 40, -108, 64, 0, 44, 22, 11, 5, ByteCompanionObject.MIN_VALUE, 0, -111, 72, -92, 82, 32, 0, -64, -32, 112, 56, Tnaf.POW_2_WIDTH, 0, 6, -125, 65, -96, -48, 0, -56, 100, 50, 25, 0, 0, 69, 34, -111, 72, -96, 0, 48, -104, 76, 38, Tnaf.POW_2_WIDTH, 0, -94, 81, 40, -108, 64, 0, -116, 70, 35, 17, ByteCompanionObject.MIN_VALUE, 0, 71, 35, -111, -56, -32, 0, -127, -64, -32, 112, 48, 0, 18, -119, 68, -94, 80, 0, 88, 44, 22, 11, 0, 0, 40, -108, 74, 37, Tnaf.POW_2_WIDTH, 0, 52, 26, 13, 6, ByteCompanionObject.MIN_VALUE, 0, -17, -14, 59, -4, ByteCompanionObject.MIN_VALUE, 0};
    }

    private static void createRandomMask36_32() {
        _randomMask36_32 = new byte[]{44, 22, 11, 5, ByteCompanionObject.MIN_VALUE, 0, -111, 72, -92, 82, 32, 0, -64, -32, 112, 56, Tnaf.POW_2_WIDTH, 0, 6, -125, 65, -96, -48, 0, -56, 100, 50, 25, 0, 0, 69, 34, -111, 72, -96, 0, 48, -104, 76, 38, Tnaf.POW_2_WIDTH, 0, -94, 81, 40, -108, 64, 0, -116, 70, 35, 17, ByteCompanionObject.MIN_VALUE, 0, 71, 35, -111, -56, -32, 0, -127, -64, -32, 112, 48, 0, 18, -119, 68, -94, 80, 0, 88, 44, 22, 11, 0, 0, 40, -108, 74, 37, Tnaf.POW_2_WIDTH, 0, 52, 26, 13, 6, ByteCompanionObject.MIN_VALUE, 0, -17, -14, 59, -4, ByteCompanionObject.MIN_VALUE, 0, -116, 70, 35, 17, ByteCompanionObject.MIN_VALUE, 0, 71, 35, -111, -56, -32, 0, -127, -64, -32, 112, 48, 0, 18, -119, 68, -94, 80, 0, 88, 44, 22, 11, 0, 0, 40, -108, 74, 37, Tnaf.POW_2_WIDTH, 0, 52, 26, 13, 6, ByteCompanionObject.MIN_VALUE, 0, 44, 22, 11, 5, ByteCompanionObject.MIN_VALUE, 0, -111, 72, -92, 82, 32, 0, -64, -32, 112, 56, Tnaf.POW_2_WIDTH, 0, 6, -125, 65, -96, -48, 0, -56, 100, 50, 25, 0, 0, 69, 34, -111, 72, -96, 0, 48, -104, 76, 38, Tnaf.POW_2_WIDTH, 0, -94, 81, 40, -108, 64, 0, 58, 40, -100, 47, -64, 0};
    }

    private static void createRandomMask36_33() {
        _randomMask36_33 = new byte[]{44, 22, 11, 5, ByteCompanionObject.MIN_VALUE, 0, -111, 72, -92, 82, 32, 0, -64, -32, 112, 56, Tnaf.POW_2_WIDTH, 0, 6, -125, 65, -96, -48, 0, -56, 100, 50, 25, 0, 0, 69, 34, -111, 72, -96, 0, 48, -104, 76, 38, Tnaf.POW_2_WIDTH, 0, -94, 81, 40, -108, 64, 0, -116, 70, 35, 17, ByteCompanionObject.MIN_VALUE, 0, 71, 35, -111, -56, -32, 0, -127, -64, -32, 112, 48, 0, 18, -119, 68, -94, 80, 0, 88, 44, 22, 11, 0, 0, 40, -108, 74, 37, Tnaf.POW_2_WIDTH, 0, 52, 26, 13, 6, ByteCompanionObject.MIN_VALUE, 0, -17, -14, 59, -4, ByteCompanionObject.MIN_VALUE, 0, 44, 22, 11, 5, ByteCompanionObject.MIN_VALUE, 0, -111, 72, -92, 82, 32, 0, -64, -32, 112, 56, Tnaf.POW_2_WIDTH, 0, 6, -125, 65, -96, -48, 0, -56, 100, 50, 25, 0, 0, 69, 34, -111, 72, -96, 0, 48, -104, 76, 38, Tnaf.POW_2_WIDTH, 0, -94, 81, 40, -108, 64, 0, 76, 38, 19, 9, ByteCompanionObject.MIN_VALUE, 0, 102, 51, 25, -116, -64, 0, -111, 72, -92, 82, 32, 0, 66, -95, 80, -88, 80, 0, -92, 82, 41, 20, ByteCompanionObject.MIN_VALUE, 0, 19, 9, -124, -62, 96, 0, 48, -104, 76, 38, Tnaf.POW_2_WIDTH, 0, -120, -60, 98, 49, Tnaf.POW_2_WIDTH, 0, 9, 4, -126, 65, 32, 0};
    }

    private static void createRandomMask36_34() {
        _randomMask36_34 = new byte[]{44, 22, 11, 5, ByteCompanionObject.MIN_VALUE, 0, -111, 72, -92, 82, 32, 0, -64, -32, 112, 56, Tnaf.POW_2_WIDTH, 0, 6, -125, 65, -96, -48, 0, -56, 100, 50, 25, 0, 0, 69, 34, -111, 72, -96, 0, 48, -104, 76, 38, Tnaf.POW_2_WIDTH, 0, -94, 81, 40, -108, 64, 0, 76, 38, 19, 9, ByteCompanionObject.MIN_VALUE, 0, 102, 51, 25, -116, -64, 0, -111, 72, -92, 82, 32, 0, 66, -95, 80, -88, 80, 0, -92, 82, 41, 20, ByteCompanionObject.MIN_VALUE, 0, 19, 9, -124, -62, 96, 0, 48, -104, 76, 38, Tnaf.POW_2_WIDTH, 0, -120, -60, 98, 49, Tnaf.POW_2_WIDTH, 0, 9, 4, -126, 65, 32, 0, 44, 22, 11, 5, ByteCompanionObject.MIN_VALUE, 0, -111, 72, -92, 82, 32, 0, -64, -32, 112, 56, Tnaf.POW_2_WIDTH, 0, 6, -125, 65, -96, -48, 0, -56, 100, 50, 25, 0, 0, 69, 34, -111, 72, -96, 0, 48, -104, 76, 38, Tnaf.POW_2_WIDTH, 0, -94, 81, 40, -108, 64, 0, -116, 70, 35, 17, ByteCompanionObject.MIN_VALUE, 0, 71, 35, -111, -56, -32, 0, -127, -64, -32, 112, 48, 0, 18, -119, 68, -94, 80, 0, 88, 44, 22, 11, 0, 0, 40, -108, 74, 37, Tnaf.POW_2_WIDTH, 0, 52, 26, 13, 6, ByteCompanionObject.MIN_VALUE, 0, -17, -14, 59, -4, ByteCompanionObject.MIN_VALUE, 0, -9, 94, 102, 91, 96, 0};
    }

    private static void createRandomMask36_35() {
        _randomMask36_35 = new byte[]{44, 22, 11, 5, ByteCompanionObject.MIN_VALUE, 0, -111, 72, -92, 82, 32, 0, -64, -32, 112, 56, Tnaf.POW_2_WIDTH, 0, 6, -125, 65, -96, -48, 0, -56, 100, 50, 25, 0, 0, 69, 34, -111, 72, -96, 0, 48, -104, 76, 38, Tnaf.POW_2_WIDTH, 0, -94, 81, 40, -108, 64, 0, 76, 38, 19, 9, ByteCompanionObject.MIN_VALUE, 0, 102, 51, 25, -116, -64, 0, -111, 72, -92, 82, 32, 0, 66, -95, 80, -88, 80, 0, -92, 82, 41, 20, ByteCompanionObject.MIN_VALUE, 0, 19, 9, -124, -62, 96, 0, 48, -104, 76, 38, Tnaf.POW_2_WIDTH, 0, -120, -60, 98, 49, Tnaf.POW_2_WIDTH, 0, 9, 4, -126, 65, 32, 0, 76, 38, 19, 9, ByteCompanionObject.MIN_VALUE, 0, 102, 51, 25, -116, -64, 0, -111, 72, -92, 82, 32, 0, 66, -95, 80, -88, 80, 0, -92, 82, 41, 20, ByteCompanionObject.MIN_VALUE, 0, 19, 9, -124, -62, 96, 0, 48, -104, 76, 38, Tnaf.POW_2_WIDTH, 0, -120, -60, 98, 49, Tnaf.POW_2_WIDTH, 0, 9, 4, -126, 65, 32, 0, 44, 22, 11, 5, ByteCompanionObject.MIN_VALUE, 0, -111, 72, -92, 82, 32, 0, -64, -32, 112, 56, Tnaf.POW_2_WIDTH, 0, 6, -125, 65, -96, -48, 0, -56, 100, 50, 25, 0, 0, 69, 34, -111, 72, -96, 0, 48, -104, 76, 38, Tnaf.POW_2_WIDTH, 0, -94, 81, 40, -108, 64, 0, -48, 3, 116, 0, -48, 0};
    }

    private static void createRandomMask36_36() {
        _randomMask36_36 = new byte[]{76, 38, 19, 9, ByteCompanionObject.MIN_VALUE, 0, 102, 51, 25, -116, -64, 0, -111, 72, -92, 82, 32, 0, 66, -95, 80, -88, 80, 0, -92, 82, 41, 20, ByteCompanionObject.MIN_VALUE, 0, 19, 9, -124, -62, 96, 0, 48, -104, 76, 38, Tnaf.POW_2_WIDTH, 0, -120, -60, 98, 49, Tnaf.POW_2_WIDTH, 0, 9, 4, -126, 65, 32, 0, 44, 22, 11, 5, ByteCompanionObject.MIN_VALUE, 0, -111, 72, -92, 82, 32, 0, -64, -32, 112, 56, Tnaf.POW_2_WIDTH, 0, 6, -125, 65, -96, -48, 0, -56, 100, 50, 25, 0, 0, 69, 34, -111, 72, -96, 0, 48, -104, 76, 38, Tnaf.POW_2_WIDTH, 0, -94, 81, 40, -108, 64, 0, -48, 3, 116, 0, -48, 0, 44, 22, 11, 5, ByteCompanionObject.MIN_VALUE, 0, -111, 72, -92, 82, 32, 0, -64, -32, 112, 56, Tnaf.POW_2_WIDTH, 0, 6, -125, 65, -96, -48, 0, -56, 100, 50, 25, 0, 0, 69, 34, -111, 72, -96, 0, 48, -104, 76, 38, Tnaf.POW_2_WIDTH, 0, -94, 81, 40, -108, 64, 0, 76, 38, 19, 9, ByteCompanionObject.MIN_VALUE, 0, 102, 51, 25, -116, -64, 0, -111, 72, -92, 82, 32, 0, 66, -95, 80, -88, 80, 0, -92, 82, 41, 20, ByteCompanionObject.MIN_VALUE, 0, 19, 9, -124, -62, 96, 0, 48, -104, 76, 38, Tnaf.POW_2_WIDTH, 0, -120, -60, 98, 49, Tnaf.POW_2_WIDTH, 0, 9, 4, -126, 65, 32, 0, -92, -100, 49, 19, ByteCompanionObject.MIN_VALUE, 0};
    }

    private static void createRandomMask36_4() {
        _randomMask36_4 = new byte[]{-20, 118, 59, 29, ByteCompanionObject.MIN_VALUE, 0, 103, 51, -103, -52, -32, 0, -79, -40, -20, 118, 48, 0, 90, -83, 86, -85, 80, 0};
    }

    private static void createRandomMask36_5() {
        _randomMask36_5 = new byte[]{76, -90, 83, 41, -112, 0, 102, 51, 25, -116, -64, 0, 25, -48, -58, 116, 48, 0, -100, -119, 103, 34, 80, 0, -29, 76, 56, -45, 0, 0};
    }

    private static void createRandomMask36_6() {
        _randomMask36_6 = new byte[]{-52, 38, 51, 9, ByteCompanionObject.MIN_VALUE, 0, 69, 98, -111, 88, -96, 0, -80, -104, 108, 38, Tnaf.POW_2_WIDTH, 0, -118, -123, 98, -95, 80, 0, 41, 83, 10, 84, -64, 0, -90, 10, -87, -126, -96, 0};
    }

    private static void createRandomMask36_7() {
        _randomMask36_7 = new byte[]{68, -94, 81, 40, -112, 0, 35, 17, -120, -60, 96, 0, -111, 72, -92, 82, 32, 0, 10, -123, 66, -95, 80, 0, 52, 26, 13, 6, ByteCompanionObject.MIN_VALUE, 0, 11, 6, -126, -63, -96, 0, -32, 100, 56, 25, 0, 0};
    }

    private static void createRandomMask36_8() {
        _randomMask36_8 = new byte[]{22, 11, 5, -126, -64, 0, -62, 97, 48, -104, 64, 0, 96, -80, 88, 44, Tnaf.POW_2_WIDTH, 0, -123, 66, -95, 80, -96, 0, 76, 38, 19, 9, ByteCompanionObject.MIN_VALUE, 0, 41, 20, -118, 69, 32, 0, 17, -120, -60, 98, 48, 0, -80, 88, 44, 22, 0, 0};
    }

    private static void createRandomMask36_9() {
        _randomMask36_9 = new byte[]{68, -94, 81, 40, -112, 0, 102, 38, 25, -119, ByteCompanionObject.MIN_VALUE, 0, -112, 73, 100, 18, 80, 0, 1, -91, ByteCompanionObject.MIN_VALUE, 105, 96, 0, 14, 18, -125, -124, -96, 0, 19, 11, 4, -62, -64, 0, 32, -48, 72, 52, Tnaf.POW_2_WIDTH, 0, -62, 81, 48, -108, 64, 0, 41, 12, -118, 67, 32, 0};
    }

    private static void createRandomMask37_1() {
        _randomMask37_1 = new byte[]{-1, -1, -1, -1, -8, 0};
    }

    private static void createRandomMask37_10() {
        _randomMask37_10 = new byte[]{-116, -58, 99, 56, -64, 0, 39, 19, -119, -60, 112, 0, 50, -103, 76, -93, 72, 0, 97, -80, -40, 100, -104, 0, 92, 46, 23, 14, 32, 0, -52, 102, 51, 29, 64, 0, 43, 21, -118, -58, 112, 0, 50, -103, 76, -76, -104, 0, 37, -110, -55, 99, -88, 0, -3, -99, -44, 34, 48, 0};
    }

    private static void createRandomMask37_11() {
        _randomMask37_11 = new byte[]{-116, -58, 99, 56, -64, 0, 39, 19, -119, -60, 112, 0, 50, -103, 76, -93, 72, 0, 97, -80, -40, 100, -104, 0, 92, 46, 23, 14, 32, 0, -124, -62, 97, 33, -64, 0, 39, 19, -119, -58, 96, 0, 81, -88, -44, 98, 24, 0, 26, 13, 6, -120, -88, 0, 104, 52, 26, 17, Tnaf.POW_2_WIDTH, 0, -119, 68, -94, 92, 0, 0};
    }

    private static void createRandomMask37_12() {
        _randomMask37_12 = new byte[]{-124, -62, 97, 33, -64, 0, 39, 19, -119, -58, 96, 0, 81, -88, -44, 98, 24, 0, 26, 13, 6, -120, -88, 0, 104, 52, 26, 17, Tnaf.POW_2_WIDTH, 0, -119, 68, -94, 92, 0, 0, -116, -58, 99, 56, -64, 0, 39, 19, -119, -60, 112, 0, 50, -103, 76, -93, 72, 0, 97, -80, -40, 100, -104, 0, 92, 46, 23, 14, 32, 0, 91, 12, 100, 50, 32, 0};
    }

    private static void createRandomMask37_13() {
        _randomMask37_13 = new byte[]{-124, -62, 97, 33, -64, 0, 39, 19, -119, -58, 96, 0, 81, -88, -44, 98, 24, 0, 26, 13, 6, -120, -88, 0, 104, 52, 26, 17, Tnaf.POW_2_WIDTH, 0, -119, 68, -94, 92, 0, 0, -116, 70, 35, 8, -64, 0, 71, 35, -111, -58, 96, 0, -127, -64, -32, 98, 24, 0, 18, -119, 68, -95, -120, 0, 88, 44, 22, 5, Tnaf.POW_2_WIDTH, 0, 40, -108, 74, 50, ByteCompanionObject.MIN_VALUE, 0, 52, 26, 13, 24, 32, 0};
    }

    private static void createRandomMask37_14() {
        _randomMask37_14 = new byte[]{-116, 70, 35, 8, -64, 0, 71, 35, -111, -58, 96, 0, -127, -64, -32, 98, 24, 0, 18, -119, 68, -95, -120, 0, 88, 44, 22, 5, Tnaf.POW_2_WIDTH, 0, 40, -108, 74, 50, ByteCompanionObject.MIN_VALUE, 0, 52, 26, 13, 24, 32, 0, -124, -62, 97, 33, -64, 0, 39, 19, -119, -58, 96, 0, 81, -88, -44, 98, 24, 0, 26, 13, 6, -120, -88, 0, 104, 52, 26, 17, Tnaf.POW_2_WIDTH, 0, -119, 68, -94, 92, 0, 0, ByteCompanionObject.MAX_VALUE, 79, -37, -119, -40, 0};
    }

    private static void createRandomMask37_15() {
        _randomMask37_15 = new byte[]{-116, 70, 35, 8, -64, 0, 71, 35, -111, -58, 96, 0, -127, -64, -32, 98, 24, 0, 18, -119, 68, -95, -120, 0, 88, 44, 22, 5, Tnaf.POW_2_WIDTH, 0, 40, -108, 74, 50, ByteCompanionObject.MIN_VALUE, 0, 52, 26, 13, 24, 32, 0, 44, 22, 11, 5, ByteCompanionObject.MIN_VALUE, 0, -111, 72, -92, 80, 48, 0, -64, -32, 112, 52, 8, 0, 6, -125, 65, -96, -88, 0, -56, 100, 50, 3, Tnaf.POW_2_WIDTH, 0, 69, 34, -111, 88, 64, 0, 48, -104, 76, 36, 80, 0, -94, 81, 40, -118, 8, 0};
    }

    private static void createRandomMask37_16() {
        _randomMask37_16 = new byte[]{44, 22, 11, 5, ByteCompanionObject.MIN_VALUE, 0, -111, 72, -92, 80, 48, 0, -64, -32, 112, 52, 8, 0, 6, -125, 65, -96, -88, 0, -56, 100, 50, 3, Tnaf.POW_2_WIDTH, 0, 69, 34, -111, 88, 64, 0, 48, -104, 76, 36, 80, 0, -94, 81, 40, -118, 8, 0, -116, 70, 35, 8, -64, 0, 71, 35, -111, -58, 96, 0, -127, -64, -32, 98, 24, 0, 18, -119, 68, -95, -120, 0, 88, 44, 22, 5, Tnaf.POW_2_WIDTH, 0, 40, -108, 74, 50, ByteCompanionObject.MIN_VALUE, 0, 52, 26, 13, 24, 32, 0, -17, -14, 31, -99, 120, 0};
    }

    private static void createRandomMask37_17() {
        _randomMask37_17 = new byte[]{44, 22, 11, 5, ByteCompanionObject.MIN_VALUE, 0, -111, 72, -92, 80, 48, 0, -64, -32, 112, 52, 8, 0, 6, -125, 65, -96, -88, 0, -56, 100, 50, 3, Tnaf.POW_2_WIDTH, 0, 69, 34, -111, 88, 64, 0, 48, -104, 76, 36, 80, 0, -94, 81, 40, -118, 8, 0, 76, 38, 19, 9, -64, 0, 102, 51, 25, -100, 96, 0, -111, 72, -92, 80, 56, 0, 66, -95, 80, -92, 40, 0, -92, 82, 41, 10, 80, 0, 19, 9, -124, -42, ByteCompanionObject.MIN_VALUE, 0, 48, -104, 76, 36, -48, 0, -120, -60, 98, 43, 8, 0, 9, 4, -126, 67, 48, 0};
    }

    private static void createRandomMask37_18() {
        _randomMask37_18 = new byte[]{76, 38, 19, 9, -64, 0, 102, 51, 25, -100, 96, 0, -111, 72, -92, 80, 56, 0, 66, -95, 80, -92, 40, 0, -92, 82, 41, 10, 80, 0, 19, 9, -124, -42, ByteCompanionObject.MIN_VALUE, 0, 48, -104, 76, 36, -48, 0, -120, -60, 98, 43, 8, 0, 9, 4, -126, 67, 48, 0, 44, 22, 11, 5, ByteCompanionObject.MIN_VALUE, 0, -111, 72, -92, 80, 48, 0, -64, -32, 112, 52, 8, 0, 6, -125, 65, -96, -88, 0, -56, 100, 50, 3, Tnaf.POW_2_WIDTH, 0, 69, 34, -111, 88, 64, 0, 48, -104, 76, 36, 80, 0, -94, 81, 40, -118, 8, 0, -48, 3, 84, 101, -56, 0};
    }

    private static void createRandomMask37_19() {
        _randomMask37_19 = new byte[]{68, -94, 81, 41, -64, 0, 102, 38, 25, -100, 32, 0, -112, 73, 68, -80, 56, 0, 1, -91, -80, -60, 40, 0, 14, 18, -93, 10, 80, 0, 19, 11, 4, 86, -64, 0, 32, -48, 72, 100, -48, 0, -62, 81, 40, -117, 0, 0, 41, 12, -122, 3, 56, 0, -116, -58, 99, 56, -64, 0, 39, 19, -119, -60, 112, 0, 50, -103, 76, -93, 72, 0, 97, -80, -40, 100, -104, 0, 92, 46, 23, 14, 32, 0, -52, 102, 51, 29, 64, 0, 43, 21, -118, -58, 112, 0, 50, -103, 76, -76, -104, 0, 37, -110, -55, 99, -88, 0, -3, -99, -44, 34, 48, 0};
    }

    private static void createRandomMask37_2() {
        _randomMask37_2 = new byte[]{-50, 103, 51, -99, -64, 0, 57, -100, -50, 115, 56, 0};
    }

    private static void createRandomMask37_20() {
        _randomMask37_20 = new byte[]{-116, -58, 99, 56, -64, 0, 39, 19, -119, -60, 112, 0, 50, -103, 76, -93, 72, 0, 97, -80, -40, 100, -104, 0, 92, 46, 23, 14, 32, 0, -52, 102, 51, 29, 64, 0, 43, 21, -118, -58, 112, 0, 50, -103, 76, -76, -104, 0, 37, -110, -55, 99, -88, 0, -3, -99, -44, 34, 48, 0, 68, -94, 81, 41, -64, 0, 102, 38, 25, -100, 32, 0, -112, 73, 68, -80, 56, 0, 1, -91, -80, -60, 40, 0, 14, 18, -93, 10, 80, 0, 19, 11, 4, 86, -64, 0, 32, -48, 72, 100, -48, 0, -62, 81, 40, -117, 0, 0, 41, 12, -122, 3, 56, 0, -27, 68, -38, 58, -56, 0};
    }

    private static void createRandomMask37_21() {
        _randomMask37_21 = new byte[]{-116, -58, 99, 56, -64, 0, 39, 19, -119, -60, 112, 0, 50, -103, 76, -93, 72, 0, 97, -80, -40, 100, -104, 0, 92, 46, 23, 14, 32, 0, -52, 102, 51, 29, 64, 0, 43, 21, -118, -58, 112, 0, 50, -103, 76, -76, -104, 0, 37, -110, -55, 99, -88, 0, -3, -99, -44, 34, 48, 0, -116, -58, 99, 56, -64, 0, 39, 19, -119, -60, 112, 0, 50, -103, 76, -93, 72, 0, 97, -80, -40, 100, -104, 0, 92, 46, 23, 14, 32, 0, -124, -62, 97, 33, -64, 0, 39, 19, -119, -58, 96, 0, 81, -88, -44, 98, 24, 0, 26, 13, 6, -120, -88, 0, 104, 52, 26, 17, Tnaf.POW_2_WIDTH, 0, -119, 68, -94, 92, 0, 0};
    }

    private static void createRandomMask37_22() {
        _randomMask37_22 = new byte[]{-116, -58, 99, 56, -64, 0, 39, 19, -119, -60, 112, 0, 50, -103, 76, -93, 72, 0, 97, -80, -40, 100, -104, 0, 92, 46, 23, 14, 32, 0, -124, -62, 97, 33, -64, 0, 39, 19, -119, -58, 96, 0, 81, -88, -44, 98, 24, 0, 26, 13, 6, -120, -88, 0, 104, 52, 26, 17, Tnaf.POW_2_WIDTH, 0, -119, 68, -94, 92, 0, 0, -116, -58, 99, 56, -64, 0, 39, 19, -119, -60, 112, 0, 50, -103, 76, -93, 72, 0, 97, -80, -40, 100, -104, 0, 92, 46, 23, 14, 32, 0, -52, 102, 51, 29, 64, 0, 43, 21, -118, -58, 112, 0, 50, -103, 76, -76, -104, 0, 37, -110, -55, 99, -88, 0, -3, -99, -44, 34, 48, 0, -28, -45, -1, 90, 40, 0};
    }

    private static void createRandomMask37_23() {
        _randomMask37_23 = new byte[]{-116, -58, 99, 56, -64, 0, 39, 19, -119, -60, 112, 0, 50, -103, 76, -93, 72, 0, 97, -80, -40, 100, -104, 0, 92, 46, 23, 14, 32, 0, -124, -62, 97, 33, -64, 0, 39, 19, -119, -58, 96, 0, 81, -88, -44, 98, 24, 0, 26, 13, 6, -120, -88, 0, 104, 52, 26, 17, Tnaf.POW_2_WIDTH, 0, -119, 68, -94, 92, 0, 0, -124, -62, 97, 33, -64, 0, 39, 19, -119, -58, 96, 0, 81, -88, -44, 98, 24, 0, 26, 13, 6, -120, -88, 0, 104, 52, 26, 17, Tnaf.POW_2_WIDTH, 0, -119, 68, -94, 92, 0, 0, -116, -58, 99, 56, -64, 0, 39, 19, -119, -60, 112, 0, 50, -103, 76, -93, 72, 0, 97, -80, -40, 100, -104, 0, 92, 46, 23, 14, 32, 0, 91, 12, 100, 50, 32, 0};
    }

    private static void createRandomMask37_24() {
        _randomMask37_24 = new byte[]{-124, -62, 97, 33, -64, 0, 39, 19, -119, -58, 96, 0, 81, -88, -44, 98, 24, 0, 26, 13, 6, -120, -88, 0, 104, 52, 26, 17, Tnaf.POW_2_WIDTH, 0, -119, 68, -94, 92, 0, 0, -116, -58, 99, 56, -64, 0, 39, 19, -119, -60, 112, 0, 50, -103, 76, -93, 72, 0, 97, -80, -40, 100, -104, 0, 92, 46, 23, 14, 32, 0, 91, 12, 100, 50, 32, 0, -116, -58, 99, 56, -64, 0, 39, 19, -119, -60, 112, 0, 50, -103, 76, -93, 72, 0, 97, -80, -40, 100, -104, 0, 92, 46, 23, 14, 32, 0, -124, -62, 97, 33, -64, 0, 39, 19, -119, -58, 96, 0, 81, -88, -44, 98, 24, 0, 26, 13, 6, -120, -88, 0, 104, 52, 26, 17, Tnaf.POW_2_WIDTH, 0, -119, 68, -94, 92, 0, 0, -83, 88, -78, 54, 104, 0};
    }

    private static void createRandomMask37_25() {
        _randomMask37_25 = new byte[]{-124, -62, 97, 33, -64, 0, 39, 19, -119, -58, 96, 0, 81, -88, -44, 98, 24, 0, 26, 13, 6, -120, -88, 0, 104, 52, 26, 17, Tnaf.POW_2_WIDTH, 0, -119, 68, -94, 92, 0, 0, -116, -58, 99, 56, -64, 0, 39, 19, -119, -60, 112, 0, 50, -103, 76, -93, 72, 0, 97, -80, -40, 100, -104, 0, 92, 46, 23, 14, 32, 0, 91, 12, 100, 50, 32, 0, -124, -62, 97, 33, -64, 0, 39, 19, -119, -58, 96, 0, 81, -88, -44, 98, 24, 0, 26, 13, 6, -120, -88, 0, 104, 52, 26, 17, Tnaf.POW_2_WIDTH, 0, -119, 68, -94, 92, 0, 0, -116, 70, 35, 8, -64, 0, 71, 35, -111, -58, 96, 0, -127, -64, -32, 98, 24, 0, 18, -119, 68, -95, -120, 0, 88, 44, 22, 5, Tnaf.POW_2_WIDTH, 0, 40, -108, 74, 50, ByteCompanionObject.MIN_VALUE, 0, 52, 26, 13, 24, 32, 0};
    }

    private static void createRandomMask37_26() {
        _randomMask37_26 = new byte[]{-124, -62, 97, 33, -64, 0, 39, 19, -119, -58, 96, 0, 81, -88, -44, 98, 24, 0, 26, 13, 6, -120, -88, 0, 104, 52, 26, 17, Tnaf.POW_2_WIDTH, 0, -119, 68, -94, 92, 0, 0, -116, 70, 35, 8, -64, 0, 71, 35, -111, -58, 96, 0, -127, -64, -32, 98, 24, 0, 18, -119, 68, -95, -120, 0, 88, 44, 22, 5, Tnaf.POW_2_WIDTH, 0, 40, -108, 74, 50, ByteCompanionObject.MIN_VALUE, 0, 52, 26, 13, 24, 32, 0, -124, -62, 97, 33, -64, 0, 39, 19, -119, -58, 96, 0, 81, -88, -44, 98, 24, 0, 26, 13, 6, -120, -88, 0, 104, 52, 26, 17, Tnaf.POW_2_WIDTH, 0, -119, 68, -94, 92, 0, 0, -116, -58, 99, 56, -64, 0, 39, 19, -119, -60, 112, 0, 50, -103, 76, -93, 72, 0, 97, -80, -40, 100, -104, 0, 92, 46, 23, 14, 32, 0, 91, 12, 100, 50, 32, 0, ByteCompanionObject.MAX_VALUE, -78, 90, -86, 32, 0};
    }

    private static void createRandomMask37_27() {
        _randomMask37_27 = new byte[]{-124, -62, 97, 33, -64, 0, 39, 19, -119, -58, 96, 0, 81, -88, -44, 98, 24, 0, 26, 13, 6, -120, -88, 0, 104, 52, 26, 17, Tnaf.POW_2_WIDTH, 0, -119, 68, -94, 92, 0, 0, -116, 70, 35, 8, -64, 0, 71, 35, -111, -58, 96, 0, -127, -64, -32, 98, 24, 0, 18, -119, 68, -95, -120, 0, 88, 44, 22, 5, Tnaf.POW_2_WIDTH, 0, 40, -108, 74, 50, ByteCompanionObject.MIN_VALUE, 0, 52, 26, 13, 24, 32, 0, -116, 70, 35, 8, -64, 0, 71, 35, -111, -58, 96, 0, -127, -64, -32, 98, 24, 0, 18, -119, 68, -95, -120, 0, 88, 44, 22, 5, Tnaf.POW_2_WIDTH, 0, 40, -108, 74, 50, ByteCompanionObject.MIN_VALUE, 0, 52, 26, 13, 24, 32, 0, -124, -62, 97, 33, -64, 0, 39, 19, -119, -58, 96, 0, 81, -88, -44, 98, 24, 0, 26, 13, 6, -120, -88, 0, 104, 52, 26, 17, Tnaf.POW_2_WIDTH, 0, -119, 68, -94, 92, 0, 0, ByteCompanionObject.MAX_VALUE, 79, -37, -119, -40, 0};
    }

    private static void createRandomMask37_28() {
        _randomMask37_28 = new byte[]{-116, 70, 35, 8, -64, 0, 71, 35, -111, -58, 96, 0, -127, -64, -32, 98, 24, 0, 18, -119, 68, -95, -120, 0, 88, 44, 22, 5, Tnaf.POW_2_WIDTH, 0, 40, -108, 74, 50, ByteCompanionObject.MIN_VALUE, 0, 52, 26, 13, 24, 32, 0, -124, -62, 97, 33, -64, 0, 39, 19, -119, -58, 96, 0, 81, -88, -44, 98, 24, 0, 26, 13, 6, -120, -88, 0, 104, 52, 26, 17, Tnaf.POW_2_WIDTH, 0, -119, 68, -94, 92, 0, 0, ByteCompanionObject.MAX_VALUE, 79, -37, -119, -40, 0, -124, -62, 97, 33, -64, 0, 39, 19, -119, -58, 96, 0, 81, -88, -44, 98, 24, 0, 26, 13, 6, -120, -88, 0, 104, 52, 26, 17, Tnaf.POW_2_WIDTH, 0, -119, 68, -94, 92, 0, 0, -116, 70, 35, 8, -64, 0, 71, 35, -111, -58, 96, 0, -127, -64, -32, 98, 24, 0, 18, -119, 68, -95, -120, 0, 88, 44, 22, 5, Tnaf.POW_2_WIDTH, 0, 40, -108, 74, 50, ByteCompanionObject.MIN_VALUE, 0, 52, 26, 13, 24, 32, 0, 123, -60, 36, -65, Tnaf.POW_2_WIDTH, 0};
    }

    private static void createRandomMask37_29() {
        _randomMask37_29 = new byte[]{-116, 70, 35, 8, -64, 0, 71, 35, -111, -58, 96, 0, -127, -64, -32, 98, 24, 0, 18, -119, 68, -95, -120, 0, 88, 44, 22, 5, Tnaf.POW_2_WIDTH, 0, 40, -108, 74, 50, ByteCompanionObject.MIN_VALUE, 0, 52, 26, 13, 24, 32, 0, -124, -62, 97, 33, -64, 0, 39, 19, -119, -58, 96, 0, 81, -88, -44, 98, 24, 0, 26, 13, 6, -120, -88, 0, 104, 52, 26, 17, Tnaf.POW_2_WIDTH, 0, -119, 68, -94, 92, 0, 0, ByteCompanionObject.MAX_VALUE, 79, -37, -119, -40, 0, -116, 70, 35, 8, -64, 0, 71, 35, -111, -58, 96, 0, -127, -64, -32, 98, 24, 0, 18, -119, 68, -95, -120, 0, 88, 44, 22, 5, Tnaf.POW_2_WIDTH, 0, 40, -108, 74, 50, ByteCompanionObject.MIN_VALUE, 0, 52, 26, 13, 24, 32, 0, 44, 22, 11, 5, ByteCompanionObject.MIN_VALUE, 0, -111, 72, -92, 80, 48, 0, -64, -32, 112, 52, 8, 0, 6, -125, 65, -96, -88, 0, -56, 100, 50, 3, Tnaf.POW_2_WIDTH, 0, 69, 34, -111, 88, 64, 0, 48, -104, 76, 36, 80, 0, -94, 81, 40, -118, 8, 0};
    }

    private static void createRandomMask37_3() {
        _randomMask37_3 = new byte[]{-52, 102, 51, 25, -64, 0, 39, 21, -119, -53, 48, 0, -110, -55, 100, -76, -104, 0};
    }

    private static void createRandomMask37_30() {
        _randomMask37_30 = new byte[]{-116, 70, 35, 8, -64, 0, 71, 35, -111, -58, 96, 0, -127, -64, -32, 98, 24, 0, 18, -119, 68, -95, -120, 0, 88, 44, 22, 5, Tnaf.POW_2_WIDTH, 0, 40, -108, 74, 50, ByteCompanionObject.MIN_VALUE, 0, 52, 26, 13, 24, 32, 0, 44, 22, 11, 5, ByteCompanionObject.MIN_VALUE, 0, -111, 72, -92, 80, 48, 0, -64, -32, 112, 52, 8, 0, 6, -125, 65, -96, -88, 0, -56, 100, 50, 3, Tnaf.POW_2_WIDTH, 0, 69, 34, -111, 88, 64, 0, 48, -104, 76, 36, 80, 0, -94, 81, 40, -118, 8, 0, -116, 70, 35, 8, -64, 0, 71, 35, -111, -58, 96, 0, -127, -64, -32, 98, 24, 0, 18, -119, 68, -95, -120, 0, 88, 44, 22, 5, Tnaf.POW_2_WIDTH, 0, 40, -108, 74, 50, ByteCompanionObject.MIN_VALUE, 0, 52, 26, 13, 24, 32, 0, -124, -62, 97, 33, -64, 0, 39, 19, -119, -58, 96, 0, 81, -88, -44, 98, 24, 0, 26, 13, 6, -120, -88, 0, 104, 52, 26, 17, Tnaf.POW_2_WIDTH, 0, -119, 68, -94, 92, 0, 0, ByteCompanionObject.MAX_VALUE, 79, -37, -119, -40, 0, 29, -114, 17, -80, -24, 0};
    }

    private static void createRandomMask37_31() {
        _randomMask37_31 = new byte[]{-116, 70, 35, 8, -64, 0, 71, 35, -111, -58, 96, 0, -127, -64, -32, 98, 24, 0, 18, -119, 68, -95, -120, 0, 88, 44, 22, 5, Tnaf.POW_2_WIDTH, 0, 40, -108, 74, 50, ByteCompanionObject.MIN_VALUE, 0, 52, 26, 13, 24, 32, 0, 44, 22, 11, 5, ByteCompanionObject.MIN_VALUE, 0, -111, 72, -92, 80, 48, 0, -64, -32, 112, 52, 8, 0, 6, -125, 65, -96, -88, 0, -56, 100, 50, 3, Tnaf.POW_2_WIDTH, 0, 69, 34, -111, 88, 64, 0, 48, -104, 76, 36, 80, 0, -94, 81, 40, -118, 8, 0, 44, 22, 11, 5, ByteCompanionObject.MIN_VALUE, 0, -111, 72, -92, 80, 48, 0, -64, -32, 112, 52, 8, 0, 6, -125, 65, -96, -88, 0, -56, 100, 50, 3, Tnaf.POW_2_WIDTH, 0, 69, 34, -111, 88, 64, 0, 48, -104, 76, 36, 80, 0, -94, 81, 40, -118, 8, 0, -116, 70, 35, 8, -64, 0, 71, 35, -111, -58, 96, 0, -127, -64, -32, 98, 24, 0, 18, -119, 68, -95, -120, 0, 88, 44, 22, 5, Tnaf.POW_2_WIDTH, 0, 40, -108, 74, 50, ByteCompanionObject.MIN_VALUE, 0, 52, 26, 13, 24, 32, 0, -17, -14, 31, -99, 120, 0};
    }

    private static void createRandomMask37_32() {
        _randomMask37_32 = new byte[]{44, 22, 11, 5, ByteCompanionObject.MIN_VALUE, 0, -111, 72, -92, 80, 48, 0, -64, -32, 112, 52, 8, 0, 6, -125, 65, -96, -88, 0, -56, 100, 50, 3, Tnaf.POW_2_WIDTH, 0, 69, 34, -111, 88, 64, 0, 48, -104, 76, 36, 80, 0, -94, 81, 40, -118, 8, 0, -116, 70, 35, 8, -64, 0, 71, 35, -111, -58, 96, 0, -127, -64, -32, 98, 24, 0, 18, -119, 68, -95, -120, 0, 88, 44, 22, 5, Tnaf.POW_2_WIDTH, 0, 40, -108, 74, 50, ByteCompanionObject.MIN_VALUE, 0, 52, 26, 13, 24, 32, 0, -17, -14, 31, -99, 120, 0, -116, 70, 35, 8, -64, 0, 71, 35, -111, -58, 96, 0, -127, -64, -32, 98, 24, 0, 18, -119, 68, -95, -120, 0, 88, 44, 22, 5, Tnaf.POW_2_WIDTH, 0, 40, -108, 74, 50, ByteCompanionObject.MIN_VALUE, 0, 52, 26, 13, 24, 32, 0, 44, 22, 11, 5, ByteCompanionObject.MIN_VALUE, 0, -111, 72, -92, 80, 48, 0, -64, -32, 112, 52, 8, 0, 6, -125, 65, -96, -88, 0, -56, 100, 50, 3, Tnaf.POW_2_WIDTH, 0, 69, 34, -111, 88, 64, 0, 48, -104, 76, 36, 80, 0, -94, 81, 40, -118, 8, 0, -9, -107, 87, -116, 64, 0};
    }

    private static void createRandomMask37_33() {
        _randomMask37_33 = new byte[]{44, 22, 11, 5, ByteCompanionObject.MIN_VALUE, 0, -111, 72, -92, 80, 48, 0, -64, -32, 112, 52, 8, 0, 6, -125, 65, -96, -88, 0, -56, 100, 50, 3, Tnaf.POW_2_WIDTH, 0, 69, 34, -111, 88, 64, 0, 48, -104, 76, 36, 80, 0, -94, 81, 40, -118, 8, 0, -116, 70, 35, 8, -64, 0, 71, 35, -111, -58, 96, 0, -127, -64, -32, 98, 24, 0, 18, -119, 68, -95, -120, 0, 88, 44, 22, 5, Tnaf.POW_2_WIDTH, 0, 40, -108, 74, 50, ByteCompanionObject.MIN_VALUE, 0, 52, 26, 13, 24, 32, 0, -17, -14, 31, -99, 120, 0, 44, 22, 11, 5, ByteCompanionObject.MIN_VALUE, 0, -111, 72, -92, 80, 48, 0, -64, -32, 112, 52, 8, 0, 6, -125, 65, -96, -88, 0, -56, 100, 50, 3, Tnaf.POW_2_WIDTH, 0, 69, 34, -111, 88, 64, 0, 48, -104, 76, 36, 80, 0, -94, 81, 40, -118, 8, 0, 76, 38, 19, 9, -64, 0, 102, 51, 25, -100, 96, 0, -111, 72, -92, 80, 56, 0, 66, -95, 80, -92, 40, 0, -92, 82, 41, 10, 80, 0, 19, 9, -124, -42, ByteCompanionObject.MIN_VALUE, 0, 48, -104, 76, 36, -48, 0, -120, -60, 98, 43, 8, 0, 9, 4, -126, 67, 48, 0};
    }

    private static void createRandomMask37_34() {
        _randomMask37_34 = new byte[]{44, 22, 11, 5, ByteCompanionObject.MIN_VALUE, 0, -111, 72, -92, 80, 48, 0, -64, -32, 112, 52, 8, 0, 6, -125, 65, -96, -88, 0, -56, 100, 50, 3, Tnaf.POW_2_WIDTH, 0, 69, 34, -111, 88, 64, 0, 48, -104, 76, 36, 80, 0, -94, 81, 40, -118, 8, 0, 76, 38, 19, 9, -64, 0, 102, 51, 25, -100, 96, 0, -111, 72, -92, 80, 56, 0, 66, -95, 80, -92, 40, 0, -92, 82, 41, 10, 80, 0, 19, 9, -124, -42, ByteCompanionObject.MIN_VALUE, 0, 48, -104, 76, 36, -48, 0, -120, -60, 98, 43, 8, 0, 9, 4, -126, 67, 48, 0, 44, 22, 11, 5, ByteCompanionObject.MIN_VALUE, 0, -111, 72, -92, 80, 48, 0, -64, -32, 112, 52, 8, 0, 6, -125, 65, -96, -88, 0, -56, 100, 50, 3, Tnaf.POW_2_WIDTH, 0, 69, 34, -111, 88, 64, 0, 48, -104, 76, 36, 80, 0, -94, 81, 40, -118, 8, 0, -116, 70, 35, 8, -64, 0, 71, 35, -111, -58, 96, 0, -127, -64, -32, 98, 24, 0, 18, -119, 68, -95, -120, 0, 88, 44, 22, 5, Tnaf.POW_2_WIDTH, 0, 40, -108, 74, 50, ByteCompanionObject.MIN_VALUE, 0, 52, 26, 13, 24, 32, 0, -17, -14, 31, -99, 120, 0, 49, -100, -5, 55, -64, 0};
    }

    private static void createRandomMask37_35() {
        _randomMask37_35 = new byte[]{44, 22, 11, 5, ByteCompanionObject.MIN_VALUE, 0, -111, 72, -92, 80, 48, 0, -64, -32, 112, 52, 8, 0, 6, -125, 65, -96, -88, 0, -56, 100, 50, 3, Tnaf.POW_2_WIDTH, 0, 69, 34, -111, 88, 64, 0, 48, -104, 76, 36, 80, 0, -94, 81, 40, -118, 8, 0, 76, 38, 19, 9, -64, 0, 102, 51, 25, -100, 96, 0, -111, 72, -92, 80, 56, 0, 66, -95, 80, -92, 40, 0, -92, 82, 41, 10, 80, 0, 19, 9, -124, -42, ByteCompanionObject.MIN_VALUE, 0, 48, -104, 76, 36, -48, 0, -120, -60, 98, 43, 8, 0, 9, 4, -126, 67, 48, 0, 76, 38, 19, 9, -64, 0, 102, 51, 25, -100, 96, 0, -111, 72, -92, 80, 56, 0, 66, -95, 80, -92, 40, 0, -92, 82, 41, 10, 80, 0, 19, 9, -124, -42, ByteCompanionObject.MIN_VALUE, 0, 48, -104, 76, 36, -48, 0, -120, -60, 98, 43, 8, 0, 9, 4, -126, 67, 48, 0, 44, 22, 11, 5, ByteCompanionObject.MIN_VALUE, 0, -111, 72, -92, 80, 48, 0, -64, -32, 112, 52, 8, 0, 6, -125, 65, -96, -88, 0, -56, 100, 50, 3, Tnaf.POW_2_WIDTH, 0, 69, 34, -111, 88, 64, 0, 48, -104, 76, 36, 80, 0, -94, 81, 40, -118, 8, 0, -48, 3, 84, 101, -56, 0};
    }

    private static void createRandomMask37_36() {
        _randomMask37_36 = new byte[]{76, 38, 19, 9, -64, 0, 102, 51, 25, -100, 96, 0, -111, 72, -92, 80, 56, 0, 66, -95, 80, -92, 40, 0, -92, 82, 41, 10, 80, 0, 19, 9, -124, -42, ByteCompanionObject.MIN_VALUE, 0, 48, -104, 76, 36, -48, 0, -120, -60, 98, 43, 8, 0, 9, 4, -126, 67, 48, 0, 44, 22, 11, 5, ByteCompanionObject.MIN_VALUE, 0, -111, 72, -92, 80, 48, 0, -64, -32, 112, 52, 8, 0, 6, -125, 65, -96, -88, 0, -56, 100, 50, 3, Tnaf.POW_2_WIDTH, 0, 69, 34, -111, 88, 64, 0, 48, -104, 76, 36, 80, 0, -94, 81, 40, -118, 8, 0, -48, 3, 84, 101, -56, 0, 44, 22, 11, 5, ByteCompanionObject.MIN_VALUE, 0, -111, 72, -92, 80, 48, 0, -64, -32, 112, 52, 8, 0, 6, -125, 65, -96, -88, 0, -56, 100, 50, 3, Tnaf.POW_2_WIDTH, 0, 69, 34, -111, 88, 64, 0, 48, -104, 76, 36, 80, 0, -94, 81, 40, -118, 8, 0, 76, 38, 19, 9, -64, 0, 102, 51, 25, -100, 96, 0, -111, 72, -92, 80, 56, 0, 66, -95, 80, -92, 40, 0, -92, 82, 41, 10, 80, 0, 19, 9, -124, -42, ByteCompanionObject.MIN_VALUE, 0, 48, -104, 76, 36, -48, 0, -120, -60, 98, 43, 8, 0, 9, 4, -126, 67, 48, 0, -61, -57, -50, -40, 80, 0};
    }

    private static void createRandomMask37_37() {
        _randomMask37_37 = new byte[]{76, 38, 19, 9, -64, 0, 102, 51, 25, -100, 96, 0, -111, 72, -92, 80, 56, 0, 66, -95, 80, -92, 40, 0, -92, 82, 41, 10, 80, 0, 19, 9, -124, -42, ByteCompanionObject.MIN_VALUE, 0, 48, -104, 76, 36, -48, 0, -120, -60, 98, 43, 8, 0, 9, 4, -126, 67, 48, 0, 44, 22, 11, 5, ByteCompanionObject.MIN_VALUE, 0, -111, 72, -92, 80, 48, 0, -64, -32, 112, 52, 8, 0, 6, -125, 65, -96, -88, 0, -56, 100, 50, 3, Tnaf.POW_2_WIDTH, 0, 69, 34, -111, 88, 64, 0, 48, -104, 76, 36, 80, 0, -94, 81, 40, -118, 8, 0, -48, 3, 84, 101, -56, 0, 76, 38, 19, 9, -64, 0, 102, 51, 25, -100, 96, 0, -111, 72, -92, 80, 56, 0, 66, -95, 80, -92, 40, 0, -92, 82, 41, 10, 80, 0, 19, 9, -124, -42, ByteCompanionObject.MIN_VALUE, 0, 48, -104, 76, 36, -48, 0, -120, -60, 98, 43, 8, 0, 9, 4, -126, 67, 48, 0, 44, 22, 19, 9, ByteCompanionObject.MIN_VALUE, 0, -111, 72, -103, -118, 32, 0, -64, -32, 100, 84, 8, 0, 6, -125, 80, -96, -104, 0, -56, 100, 41, 0, 112, 0, 69, 34, -124, -48, -64, 0, 48, -104, 76, 37, 32, 0, -94, 81, 34, 40, 72, 0, -48, 3, 66, 83, 0, 0, -18, -11, -77, 102, Tnaf.POW_2_WIDTH, 0};
    }

    private static void createRandomMask37_4() {
        _randomMask37_4 = new byte[]{-20, 118, 59, 28, -64, 0, 103, 51, -103, -58, 112, 0, -79, -40, -20, 115, 24, 0, 90, -83, 86, -91, -88, 0};
    }

    private static void createRandomMask37_5() {
        _randomMask37_5 = new byte[]{76, -90, 83, 57, -64, 0, 102, 51, 25, -116, 112, 0, 25, -48, -24, 115, 24, 0, -100, -119, 100, -87, -88, 0, -29, 76, 46, 38, 96, 0};
    }

    private static void createRandomMask37_6() {
        _randomMask37_6 = new byte[]{-52, 38, 19, 13, ByteCompanionObject.MIN_VALUE, 0, 69, 98, -111, 90, 32, 0, -80, -104, 76, 52, 24, 0, -118, -123, 98, -96, -88, 0, 41, 83, 9, -126, -48, 0, -90, 10, -91, 81, 64, 0};
    }

    private static void createRandomMask37_7() {
        _randomMask37_7 = new byte[]{68, -94, 113, 40, -64, 0, 35, 17, -120, -58, 96, 0, -111, 72, -92, 71, 8, 0, 10, -123, 82, -96, -88, 0, 52, 26, 13, 18, 80, 0, 11, 6, -94, -46, ByteCompanionObject.MIN_VALUE, 0, -32, 100, 50, 9, 48, 0};
    }

    private static void createRandomMask37_8() {
        _randomMask37_8 = new byte[]{22, 11, 5, -124, -32, 0, -62, 97, 48, -111, 48, 0, 96, -80, 88, 58, 8, 0, -123, 66, -95, 68, -104, 0, 76, 38, 51, 8, 80, 0, 41, 20, -118, 88, -64, 0, 17, -120, -60, 102, 48, 0, -80, 88, 44, 3, 24, 0};
    }

    private static void createRandomMask37_9() {
        _randomMask37_9 = new byte[]{68, -94, 81, 41, -64, 0, 102, 38, 25, -100, 32, 0, -112, 73, 68, -80, 56, 0, 1, -91, -80, -60, 40, 0, 14, 18, -93, 10, 80, 0, 19, 11, 4, 86, -64, 0, 32, -48, 72, 100, -48, 0, -62, 81, 40, -117, 0, 0, 41, 12, -122, 3, 56, 0};
    }

    private static void createRandomMask38_1() {
        _randomMask38_1 = new byte[]{-1, -1, -1, -1, -4, 0};
    }

    private static void createRandomMask38_10() {
        _randomMask38_10 = new byte[]{-116, -29, 17, -100, 96, 0, 39, 17, -60, -30, 56, 0, 50, -115, 38, 81, -92, 0, 97, -110, 108, 50, 76, 0, 92, 56, -117, -121, Tnaf.POW_2_WIDTH, 0, -52, 117, 25, -114, -96, 0, 43, 25, -59, 99, 56, 0, 50, -46, 102, 90, 76, 0, 37, -114, -92, -79, -44, 0, 80, -120, -54, 17, 24, 0};
    }

    private static void createRandomMask38_11() {
        _randomMask38_11 = new byte[]{-116, -29, 17, -100, 96, 0, 39, 17, -60, -30, 56, 0, 50, -115, 38, 81, -92, 0, 97, -110, 108, 50, 76, 0, 92, 56, -117, -121, Tnaf.POW_2_WIDTH, 0, -124, -121, Tnaf.POW_2_WIDTH, -112, -32, 0, 39, 25, -124, -29, 48, 0, 81, -120, 106, 49, 12, 0, 26, 34, -93, 68, 84, 0, 104, 68, 77, 8, -120, 0, -119, 112, 17, 46, 0, 0};
    }

    private static void createRandomMask38_12() {
        _randomMask38_12 = new byte[]{-124, -121, Tnaf.POW_2_WIDTH, -112, -32, 0, 39, 25, -124, -29, 48, 0, 81, -120, 106, 49, 12, 0, 26, 34, -93, 68, 84, 0, 104, 68, 77, 8, -120, 0, -119, 112, 17, 46, 0, 0, -116, -29, 17, -100, 96, 0, 39, 17, -60, -30, 56, 0, 50, -115, 38, 81, -92, 0, 97, -110, 108, 50, 76, 0, 92, 56, -117, -121, Tnaf.POW_2_WIDTH, 0, -112, -56, -110, 25, Tnaf.POW_2_WIDTH, 0};
    }

    private static void createRandomMask38_13() {
        _randomMask38_13 = new byte[]{-124, -121, Tnaf.POW_2_WIDTH, -112, -32, 0, 39, 25, -124, -29, 48, 0, 81, -120, 106, 49, 12, 0, 26, 34, -93, 68, 84, 0, 104, 68, 77, 8, -120, 0, -119, 112, 17, 46, 0, 0, -116, 35, 17, -124, 96, 0, 71, 25, -120, -29, 48, 0, -127, -120, 112, 49, 12, 0, 18, -122, 34, 80, -60, 0, 88, 20, 75, 2, -120, 0, 40, -54, 5, 25, 64, 0, 52, 96, -122, -116, Tnaf.POW_2_WIDTH, 0};
    }

    private static void createRandomMask38_14() {
        _randomMask38_14 = new byte[]{-116, 35, 17, -124, 96, 0, 71, 25, -120, -29, 48, 0, -127, -120, 112, 49, 12, 0, 18, -122, 34, 80, -60, 0, 88, 20, 75, 2, -120, 0, 40, -54, 5, 25, 64, 0, 52, 96, -122, -116, Tnaf.POW_2_WIDTH, 0, -124, -121, Tnaf.POW_2_WIDTH, -112, -32, 0, 39, 25, -124, -29, 48, 0, 81, -120, 106, 49, 12, 0, 26, 34, -93, 68, 84, 0, 104, 68, 77, 8, -120, 0, -119, 112, 17, 46, 0, 0, 110, 39, 109, -60, -20, 0};
    }

    private static void createRandomMask38_15() {
        _randomMask38_15 = new byte[]{-116, 35, 17, -124, 96, 0, 71, 25, -120, -29, 48, 0, -127, -120, 112, 49, 12, 0, 18, -122, 34, 80, -60, 0, 88, 20, 75, 2, -120, 0, 40, -54, 5, 25, 64, 0, 52, 96, -122, -116, Tnaf.POW_2_WIDTH, 0, 44, 22, 5, -126, -64, 0, -111, 64, -46, 40, 24, 0, -64, -48, 56, 26, 4, 0, 6, -126, -96, -48, 84, 0, -56, 12, 89, 1, -120, 0, 69, 97, 8, -84, 32, 0, 48, -111, 70, 18, 40, 0, -94, 40, 52, 69, 4, 0};
    }

    private static void createRandomMask38_16() {
        _randomMask38_16 = new byte[]{44, 22, 5, -126, -64, 0, -111, 64, -46, 40, 24, 0, -64, -48, 56, 26, 4, 0, 6, -126, -96, -48, 84, 0, -56, 12, 89, 1, -120, 0, 69, 97, 8, -84, 32, 0, 48, -111, 70, 18, 40, 0, -94, 40, 52, 69, 4, 0, -116, 35, 17, -124, 96, 0, 71, 25, -120, -29, 48, 0, -127, -120, 112, 49, 12, 0, 18, -122, 34, 80, -60, 0, 88, 20, 75, 2, -120, 0, 40, -54, 5, 25, 64, 0, 52, 96, -122, -116, Tnaf.POW_2_WIDTH, 0, 126, 117, -17, -50, PSSSigner.TRAILER_IMPLICIT, 0};
    }

    private static void createRandomMask38_17() {
        _randomMask38_17 = new byte[]{44, 22, 5, -126, -64, 0, -111, 64, -46, 40, 24, 0, -64, -48, 56, 26, 4, 0, 6, -126, -96, -48, 84, 0, -56, 12, 89, 1, -120, 0, 69, 97, 8, -84, 32, 0, 48, -111, 70, 18, 40, 0, -94, 40, 52, 69, 4, 0, 76, 39, 9, -124, -32, 0, 102, 113, -116, -50, 48, 0, -111, 64, -14, 40, 28, 0, 66, -112, -88, 82, 20, 0, -92, 41, 84, -123, 40, 0, 19, 90, 2, 107, 64, 0, 48, -109, 70, 18, 104, 0, -120, -84, 49, 21, -124, 0, 9, 12, -63, 33, -104, 0};
    }

    private static void createRandomMask38_18() {
        _randomMask38_18 = new byte[]{76, 39, 9, -124, -32, 0, 102, 113, -116, -50, 48, 0, -111, 64, -14, 40, 28, 0, 66, -112, -88, 82, 20, 0, -92, 41, 84, -123, 40, 0, 19, 90, 2, 107, 64, 0, 48, -109, 70, 18, 104, 0, -120, -84, 49, 21, -124, 0, 9, 12, -63, 33, -104, 0, 44, 22, 5, -126, -64, 0, -111, 64, -46, 40, 24, 0, -64, -48, 56, 26, 4, 0, 6, -126, -96, -48, 84, 0, -56, 12, 89, 1, -120, 0, 69, 97, 8, -84, 32, 0, 48, -111, 70, 18, 40, 0, -94, 40, 52, 69, 4, 0, 81, -105, 42, 50, -28, 0};
    }

    private static void createRandomMask38_19() {
        _randomMask38_19 = new byte[]{76, 39, 9, -124, -32, 0, 102, 113, -116, -50, 48, 0, -111, 64, -14, 40, 28, 0, 66, -112, -88, 82, 20, 0, -92, 41, 84, -123, 40, 0, 19, 90, 2, 107, 64, 0, 48, -109, 70, 18, 104, 0, -120, -84, 49, 21, -124, 0, 9, 12, -63, 33, -104, 0, 76, 38, 9, -124, -64, 0, 102, 40, -116, -59, Tnaf.POW_2_WIDTH, 0, -111, 80, 50, 42, 4, 0, 66, -126, 104, 80, 76, 0, -92, 1, -44, ByteCompanionObject.MIN_VALUE, 56, 0, 19, 67, 2, 104, 96, 0, 48, -108, -122, 18, -112, 0, -120, -95, 49, 20, 36, 0, 9, 76, 1, 41, ByteCompanionObject.MIN_VALUE, 0, -51, -104, 89, -77, 8, 0};
    }

    private static void createRandomMask38_2() {
        _randomMask38_2 = new byte[]{-50, 119, 25, -50, -32, 0, 57, -52, -25, 57, -100, 0};
    }

    private static void createRandomMask38_20() {
        _randomMask38_20 = new byte[]{-116, -29, 17, -100, 96, 0, 39, 17, -60, -30, 56, 0, 50, -115, 38, 81, -92, 0, 97, -110, 108, 50, 76, 0, 92, 56, -117, -121, Tnaf.POW_2_WIDTH, 0, -52, 117, 25, -114, -96, 0, 43, 25, -59, 99, 56, 0, 50, -46, 102, 90, 76, 0, 37, -114, -92, -79, -44, 0, 80, -120, -54, 17, 24, 0, 68, -89, 8, -108, -32, 0, 102, 112, -116, -50, Tnaf.POW_2_WIDTH, 0, 18, -64, -30, 88, 28, 0, -61, Tnaf.POW_2_WIDTH, -72, 98, 20, 0, -116, 41, 81, -123, 40, 0, 17, 91, 2, 43, 96, 0, 33, -109, 68, 50, 104, 0, -94, 44, 20, 69, ByteCompanionObject.MIN_VALUE, 0, 24, 12, -29, 1, -100, 0, -26, PSSSigner.TRAILER_IMPLICIT, -120, -29, 120, 0};
    }

    private static void createRandomMask38_21() {
        _randomMask38_21 = new byte[]{-116, -29, 17, -100, 96, 0, 39, 17, -60, -30, 56, 0, 50, -115, 38, 81, -92, 0, 97, -110, 108, 50, 76, 0, 92, 56, -117, -121, Tnaf.POW_2_WIDTH, 0, -52, 117, 25, -114, -96, 0, 43, 25, -59, 99, 56, 0, 50, -46, 102, 90, 76, 0, 37, -114, -92, -79, -44, 0, 80, -120, -54, 17, 24, 0, -116, -29, 17, -100, 96, 0, 39, 17, -60, -30, 56, 0, 50, -115, 38, 81, -92, 0, 97, -110, 108, 50, 76, 0, 92, 56, -117, -121, Tnaf.POW_2_WIDTH, 0, -124, -121, Tnaf.POW_2_WIDTH, -112, -32, 0, 39, 25, -124, -29, 48, 0, 81, -120, 106, 49, 12, 0, 26, 34, -93, 68, 84, 0, 104, 68, 77, 8, -120, 0, -119, 112, 17, 46, 0, 0};
    }

    private static void createRandomMask38_22() {
        _randomMask38_22 = new byte[]{-116, -29, 17, -100, 96, 0, 39, 17, -60, -30, 56, 0, 50, -115, 38, 81, -92, 0, 97, -110, 108, 50, 76, 0, 92, 56, -117, -121, Tnaf.POW_2_WIDTH, 0, -124, -121, Tnaf.POW_2_WIDTH, -112, -32, 0, 39, 25, -124, -29, 48, 0, 81, -120, 106, 49, 12, 0, 26, 34, -93, 68, 84, 0, 104, 68, 77, 8, -120, 0, -119, 112, 17, 46, 0, 0, -116, -29, 17, -100, 96, 0, 39, 17, -60, -30, 56, 0, 50, -115, 38, 81, -92, 0, 97, -110, 108, 50, 76, 0, 92, 56, -117, -121, Tnaf.POW_2_WIDTH, 0, -52, 117, 25, -114, -96, 0, 43, 25, -59, 99, 56, 0, 50, -46, 102, 90, 76, 0, 37, -114, -92, -79, -44, 0, 80, -120, -54, 17, 24, 0, 12, 60, 72, 61, 88, 0};
    }

    private static void createRandomMask38_23() {
        _randomMask38_23 = new byte[]{-116, -29, 17, -100, 96, 0, 39, 17, -60, -30, 56, 0, 50, -115, 38, 81, -92, 0, 97, -110, 108, 50, 76, 0, 92, 56, -117, -121, Tnaf.POW_2_WIDTH, 0, -124, -121, Tnaf.POW_2_WIDTH, -112, -32, 0, 39, 25, -124, -29, 48, 0, 81, -120, 106, 49, 12, 0, 26, 34, -93, 68, 84, 0, 104, 68, 77, 8, -120, 0, -119, 112, 17, 46, 0, 0, -124, -121, Tnaf.POW_2_WIDTH, -112, -32, 0, 39, 25, -124, -29, 48, 0, 81, -120, 106, 49, 12, 0, 26, 34, -93, 68, 84, 0, 104, 68, 77, 8, -120, 0, -119, 112, 17, 46, 0, 0, -116, -29, 17, -100, 96, 0, 39, 17, -60, -30, 56, 0, 50, -115, 38, 81, -92, 0, 97, -110, 108, 50, 76, 0, 92, 56, -117, -121, Tnaf.POW_2_WIDTH, 0, -112, -56, -110, 25, Tnaf.POW_2_WIDTH, 0};
    }

    private static void createRandomMask38_24() {
        _randomMask38_24 = new byte[]{-124, -121, Tnaf.POW_2_WIDTH, -112, -32, 0, 39, 25, -124, -29, 48, 0, 81, -120, 106, 49, 12, 0, 26, 34, -93, 68, 84, 0, 104, 68, 77, 8, -120, 0, -119, 112, 17, 46, 0, 0, -116, -29, 17, -100, 96, 0, 39, 17, -60, -30, 56, 0, 50, -115, 38, 81, -92, 0, 97, -110, 108, 50, 76, 0, 92, 56, -117, -121, Tnaf.POW_2_WIDTH, 0, -112, -56, -110, 25, Tnaf.POW_2_WIDTH, 0, -116, -29, 17, -100, 96, 0, 39, 17, -60, -30, 56, 0, 50, -115, 38, 81, -92, 0, 97, -110, 108, 50, 76, 0, 92, 56, -117, -121, Tnaf.POW_2_WIDTH, 0, -124, -121, Tnaf.POW_2_WIDTH, -112, -32, 0, 39, 25, -124, -29, 48, 0, 81, -120, 106, 49, 12, 0, 26, 34, -93, 68, 84, 0, 104, 68, 77, 8, -120, 0, -119, 112, 17, 46, 0, 0, -109, -56, -77, -66, 92, 0};
    }

    private static void createRandomMask38_25() {
        _randomMask38_25 = new byte[]{-124, -121, Tnaf.POW_2_WIDTH, -112, -32, 0, 39, 25, -124, -29, 48, 0, 81, -120, 106, 49, 12, 0, 26, 34, -93, 68, 84, 0, 104, 68, 77, 8, -120, 0, -119, 112, 17, 46, 0, 0, -116, -29, 17, -100, 96, 0, 39, 17, -60, -30, 56, 0, 50, -115, 38, 81, -92, 0, 97, -110, 108, 50, 76, 0, 92, 56, -117, -121, Tnaf.POW_2_WIDTH, 0, -112, -56, -110, 25, Tnaf.POW_2_WIDTH, 0, -124, -121, Tnaf.POW_2_WIDTH, -112, -32, 0, 39, 25, -124, -29, 48, 0, 81, -120, 106, 49, 12, 0, 26, 34, -93, 68, 84, 0, 104, 68, 77, 8, -120, 0, -119, 112, 17, 46, 0, 0, -116, 35, 17, -124, 96, 0, 71, 25, -120, -29, 48, 0, -127, -120, 112, 49, 12, 0, 18, -122, 34, 80, -60, 0, 88, 20, 75, 2, -120, 0, 40, -54, 5, 25, 64, 0, 52, 96, -122, -116, Tnaf.POW_2_WIDTH, 0};
    }

    private static void createRandomMask38_26() {
        _randomMask38_26 = new byte[]{-124, -121, Tnaf.POW_2_WIDTH, -112, -32, 0, 39, 25, -124, -29, 48, 0, 81, -120, 106, 49, 12, 0, 26, 34, -93, 68, 84, 0, 104, 68, 77, 8, -120, 0, -119, 112, 17, 46, 0, 0, -116, 35, 17, -124, 96, 0, 71, 25, -120, -29, 48, 0, -127, -120, 112, 49, 12, 0, 18, -122, 34, 80, -60, 0, 88, 20, 75, 2, -120, 0, 40, -54, 5, 25, 64, 0, 52, 96, -122, -116, Tnaf.POW_2_WIDTH, 0, -124, -121, Tnaf.POW_2_WIDTH, -112, -32, 0, 39, 25, -124, -29, 48, 0, 81, -120, 106, 49, 12, 0, 26, 34, -93, 68, 84, 0, 104, 68, 77, 8, -120, 0, -119, 112, 17, 46, 0, 0, -116, -29, 17, -100, 96, 0, 39, 17, -60, -30, 56, 0, 50, -115, 38, 81, -92, 0, 97, -110, 108, 50, 76, 0, 92, 56, -117, -121, Tnaf.POW_2_WIDTH, 0, -112, -56, -110, 25, Tnaf.POW_2_WIDTH, 0, 75, -85, -4, -26, -24, 0};
    }

    private static void createRandomMask38_27() {
        _randomMask38_27 = new byte[]{-124, -121, Tnaf.POW_2_WIDTH, -112, -32, 0, 39, 25, -124, -29, 48, 0, 81, -120, 106, 49, 12, 0, 26, 34, -93, 68, 84, 0, 104, 68, 77, 8, -120, 0, -119, 112, 17, 46, 0, 0, -116, 35, 17, -124, 96, 0, 71, 25, -120, -29, 48, 0, -127, -120, 112, 49, 12, 0, 18, -122, 34, 80, -60, 0, 88, 20, 75, 2, -120, 0, 40, -54, 5, 25, 64, 0, 52, 96, -122, -116, Tnaf.POW_2_WIDTH, 0, -116, 35, 17, -124, 96, 0, 71, 25, -120, -29, 48, 0, -127, -120, 112, 49, 12, 0, 18, -122, 34, 80, -60, 0, 88, 20, 75, 2, -120, 0, 40, -54, 5, 25, 64, 0, 52, 96, -122, -116, Tnaf.POW_2_WIDTH, 0, -124, -121, Tnaf.POW_2_WIDTH, -112, -32, 0, 39, 25, -124, -29, 48, 0, 81, -120, 106, 49, 12, 0, 26, 34, -93, 68, 84, 0, 104, 68, 77, 8, -120, 0, -119, 112, 17, 46, 0, 0, 110, 39, 109, -60, -20, 0};
    }

    private static void createRandomMask38_28() {
        _randomMask38_28 = new byte[]{-116, 35, 17, -124, 96, 0, 71, 25, -120, -29, 48, 0, -127, -120, 112, 49, 12, 0, 18, -122, 34, 80, -60, 0, 88, 20, 75, 2, -120, 0, 40, -54, 5, 25, 64, 0, 52, 96, -122, -116, Tnaf.POW_2_WIDTH, 0, -124, -121, Tnaf.POW_2_WIDTH, -112, -32, 0, 39, 25, -124, -29, 48, 0, 81, -120, 106, 49, 12, 0, 26, 34, -93, 68, 84, 0, 104, 68, 77, 8, -120, 0, -119, 112, 17, 46, 0, 0, 110, 39, 109, -60, -20, 0, -124, -121, Tnaf.POW_2_WIDTH, -112, -32, 0, 39, 25, -124, -29, 48, 0, 81, -120, 106, 49, 12, 0, 26, 34, -93, 68, 84, 0, 104, 68, 77, 8, -120, 0, -119, 112, 17, 46, 0, 0, -116, 35, 17, -124, 96, 0, 71, 25, -120, -29, 48, 0, -127, -120, 112, 49, 12, 0, 18, -122, 34, 80, -60, 0, 88, 20, 75, 2, -120, 0, 40, -54, 5, 25, 64, 0, 52, 96, -122, -116, Tnaf.POW_2_WIDTH, 0, 112, 27, 91, 44, 12, 0};
    }

    private static void createRandomMask38_29() {
        _randomMask38_29 = new byte[]{-116, 35, 17, -124, 96, 0, 71, 25, -120, -29, 48, 0, -127, -120, 112, 49, 12, 0, 18, -122, 34, 80, -60, 0, 88, 20, 75, 2, -120, 0, 40, -54, 5, 25, 64, 0, 52, 96, -122, -116, Tnaf.POW_2_WIDTH, 0, -124, -121, Tnaf.POW_2_WIDTH, -112, -32, 0, 39, 25, -124, -29, 48, 0, 81, -120, 106, 49, 12, 0, 26, 34, -93, 68, 84, 0, 104, 68, 77, 8, -120, 0, -119, 112, 17, 46, 0, 0, 110, 39, 109, -60, -20, 0, -116, 35, 17, -124, 96, 0, 71, 25, -120, -29, 48, 0, -127, -120, 112, 49, 12, 0, 18, -122, 34, 80, -60, 0, 88, 20, 75, 2, -120, 0, 40, -54, 5, 25, 64, 0, 52, 96, -122, -116, Tnaf.POW_2_WIDTH, 0, 44, 22, 5, -126, -64, 0, -111, 64, -46, 40, 24, 0, -64, -48, 56, 26, 4, 0, 6, -126, -96, -48, 84, 0, -56, 12, 89, 1, -120, 0, 69, 97, 8, -84, 32, 0, 48, -111, 70, 18, 40, 0, -94, 40, 52, 69, 4, 0};
    }

    private static void createRandomMask38_3() {
        _randomMask38_3 = new byte[]{-52, 103, 25, -116, -32, 0, 39, 44, -60, -27, -104, 0, -110, -46, 114, 90, 76, 0};
    }

    private static void createRandomMask38_30() {
        _randomMask38_30 = new byte[]{-116, 35, 17, -124, 96, 0, 71, 25, -120, -29, 48, 0, -127, -120, 112, 49, 12, 0, 18, -122, 34, 80, -60, 0, 88, 20, 75, 2, -120, 0, 40, -54, 5, 25, 64, 0, 52, 96, -122, -116, Tnaf.POW_2_WIDTH, 0, 44, 22, 5, -126, -64, 0, -111, 64, -46, 40, 24, 0, -64, -48, 56, 26, 4, 0, 6, -126, -96, -48, 84, 0, -56, 12, 89, 1, -120, 0, 69, 97, 8, -84, 32, 0, 48, -111, 70, 18, 40, 0, -94, 40, 52, 69, 4, 0, -116, 35, 17, -124, 96, 0, 71, 25, -120, -29, 48, 0, -127, -120, 112, 49, 12, 0, 18, -122, 34, 80, -60, 0, 88, 20, 75, 2, -120, 0, 40, -54, 5, 25, 64, 0, 52, 96, -122, -116, Tnaf.POW_2_WIDTH, 0, -124, -121, Tnaf.POW_2_WIDTH, -112, -32, 0, 39, 25, -124, -29, 48, 0, 81, -120, 106, 49, 12, 0, 26, 34, -93, 68, 84, 0, 104, 68, 77, 8, -120, 0, -119, 112, 17, 46, 0, 0, 110, 39, 109, -60, -20, 0, 91, 22, -33, -72, -48, 0};
    }

    private static void createRandomMask38_31() {
        _randomMask38_31 = new byte[]{-116, 35, 17, -124, 96, 0, 71, 25, -120, -29, 48, 0, -127, -120, 112, 49, 12, 0, 18, -122, 34, 80, -60, 0, 88, 20, 75, 2, -120, 0, 40, -54, 5, 25, 64, 0, 52, 96, -122, -116, Tnaf.POW_2_WIDTH, 0, 44, 22, 5, -126, -64, 0, -111, 64, -46, 40, 24, 0, -64, -48, 56, 26, 4, 0, 6, -126, -96, -48, 84, 0, -56, 12, 89, 1, -120, 0, 69, 97, 8, -84, 32, 0, 48, -111, 70, 18, 40, 0, -94, 40, 52, 69, 4, 0, 44, 22, 5, -126, -64, 0, -111, 64, -46, 40, 24, 0, -64, -48, 56, 26, 4, 0, 6, -126, -96, -48, 84, 0, -56, 12, 89, 1, -120, 0, 69, 97, 8, -84, 32, 0, 48, -111, 70, 18, 40, 0, -94, 40, 52, 69, 4, 0, -116, 35, 17, -124, 96, 0, 71, 25, -120, -29, 48, 0, -127, -120, 112, 49, 12, 0, 18, -122, 34, 80, -60, 0, 88, 20, 75, 2, -120, 0, 40, -54, 5, 25, 64, 0, 52, 96, -122, -116, Tnaf.POW_2_WIDTH, 0, 126, 117, -17, -50, PSSSigner.TRAILER_IMPLICIT, 0};
    }

    private static void createRandomMask38_32() {
        _randomMask38_32 = new byte[]{44, 22, 5, -126, -64, 0, -111, 64, -46, 40, 24, 0, -64, -48, 56, 26, 4, 0, 6, -126, -96, -48, 84, 0, -56, 12, 89, 1, -120, 0, 69, 97, 8, -84, 32, 0, 48, -111, 70, 18, 40, 0, -94, 40, 52, 69, 4, 0, -116, 35, 17, -124, 96, 0, 71, 25, -120, -29, 48, 0, -127, -120, 112, 49, 12, 0, 18, -122, 34, 80, -60, 0, 88, 20, 75, 2, -120, 0, 40, -54, 5, 25, 64, 0, 52, 96, -122, -116, Tnaf.POW_2_WIDTH, 0, 126, 117, -17, -50, PSSSigner.TRAILER_IMPLICIT, 0, -116, 35, 17, -124, 96, 0, 71, 25, -120, -29, 48, 0, -127, -120, 112, 49, 12, 0, 18, -122, 34, 80, -60, 0, 88, 20, 75, 2, -120, 0, 40, -54, 5, 25, 64, 0, 52, 96, -122, -116, Tnaf.POW_2_WIDTH, 0, 44, 22, 5, -126, -64, 0, -111, 64, -46, 40, 24, 0, -64, -48, 56, 26, 4, 0, 6, -126, -96, -48, 84, 0, -56, 12, 89, 1, -120, 0, 69, 97, 8, -84, 32, 0, 48, -111, 70, 18, 40, 0, -94, 40, 52, 69, 4, 0, 51, Tnaf.POW_2_WIDTH, 2, 78, 84, 0};
    }

    private static void createRandomMask38_33() {
        _randomMask38_33 = new byte[]{44, 22, 5, -126, -64, 0, -111, 64, -46, 40, 24, 0, -64, -48, 56, 26, 4, 0, 6, -126, -96, -48, 84, 0, -56, 12, 89, 1, -120, 0, 69, 97, 8, -84, 32, 0, 48, -111, 70, 18, 40, 0, -94, 40, 52, 69, 4, 0, -116, 35, 17, -124, 96, 0, 71, 25, -120, -29, 48, 0, -127, -120, 112, 49, 12, 0, 18, -122, 34, 80, -60, 0, 88, 20, 75, 2, -120, 0, 40, -54, 5, 25, 64, 0, 52, 96, -122, -116, Tnaf.POW_2_WIDTH, 0, 126, 117, -17, -50, PSSSigner.TRAILER_IMPLICIT, 0, 44, 22, 5, -126, -64, 0, -111, 64, -46, 40, 24, 0, -64, -48, 56, 26, 4, 0, 6, -126, -96, -48, 84, 0, -56, 12, 89, 1, -120, 0, 69, 97, 8, -84, 32, 0, 48, -111, 70, 18, 40, 0, -94, 40, 52, 69, 4, 0, 76, 39, 9, -124, -32, 0, 102, 113, -116, -50, 48, 0, -111, 64, -14, 40, 28, 0, 66, -112, -88, 82, 20, 0, -92, 41, 84, -123, 40, 0, 19, 90, 2, 107, 64, 0, 48, -109, 70, 18, 104, 0, -120, -84, 49, 21, -124, 0, 9, 12, -63, 33, -104, 0};
    }

    private static void createRandomMask38_34() {
        _randomMask38_34 = new byte[]{44, 22, 5, -126, -64, 0, -111, 64, -46, 40, 24, 0, -64, -48, 56, 26, 4, 0, 6, -126, -96, -48, 84, 0, -56, 12, 89, 1, -120, 0, 69, 97, 8, -84, 32, 0, 48, -111, 70, 18, 40, 0, -94, 40, 52, 69, 4, 0, 76, 39, 9, -124, -32, 0, 102, 113, -116, -50, 48, 0, -111, 64, -14, 40, 28, 0, 66, -112, -88, 82, 20, 0, -92, 41, 84, -123, 40, 0, 19, 90, 2, 107, 64, 0, 48, -109, 70, 18, 104, 0, -120, -84, 49, 21, -124, 0, 9, 12, -63, 33, -104, 0, 44, 22, 5, -126, -64, 0, -111, 64, -46, 40, 24, 0, -64, -48, 56, 26, 4, 0, 6, -126, -96, -48, 84, 0, -56, 12, 89, 1, -120, 0, 69, 97, 8, -84, 32, 0, 48, -111, 70, 18, 40, 0, -94, 40, 52, 69, 4, 0, -116, 35, 17, -124, 96, 0, 71, 25, -120, -29, 48, 0, -127, -120, 112, 49, 12, 0, 18, -122, 34, 80, -60, 0, 88, 20, 75, 2, -120, 0, 40, -54, 5, 25, 64, 0, 52, 96, -122, -116, Tnaf.POW_2_WIDTH, 0, 126, 117, -17, -50, PSSSigner.TRAILER_IMPLICIT, 0, -111, 72, -6, -16, -40, 0};
    }

    private static void createRandomMask38_35() {
        _randomMask38_35 = new byte[]{44, 22, 5, -126, -64, 0, -111, 64, -46, 40, 24, 0, -64, -48, 56, 26, 4, 0, 6, -126, -96, -48, 84, 0, -56, 12, 89, 1, -120, 0, 69, 97, 8, -84, 32, 0, 48, -111, 70, 18, 40, 0, -94, 40, 52, 69, 4, 0, 76, 39, 9, -124, -32, 0, 102, 113, -116, -50, 48, 0, -111, 64, -14, 40, 28, 0, 66, -112, -88, 82, 20, 0, -92, 41, 84, -123, 40, 0, 19, 90, 2, 107, 64, 0, 48, -109, 70, 18, 104, 0, -120, -84, 49, 21, -124, 0, 9, 12, -63, 33, -104, 0, 76, 39, 9, -124, -32, 0, 102, 113, -116, -50, 48, 0, -111, 64, -14, 40, 28, 0, 66, -112, -88, 82, 20, 0, -92, 41, 84, -123, 40, 0, 19, 90, 2, 107, 64, 0, 48, -109, 70, 18, 104, 0, -120, -84, 49, 21, -124, 0, 9, 12, -63, 33, -104, 0, 44, 22, 5, -126, -64, 0, -111, 64, -46, 40, 24, 0, -64, -48, 56, 26, 4, 0, 6, -126, -96, -48, 84, 0, -56, 12, 89, 1, -120, 0, 69, 97, 8, -84, 32, 0, 48, -111, 70, 18, 40, 0, -94, 40, 52, 69, 4, 0, 81, -105, 42, 50, -28, 0};
    }

    private static void createRandomMask38_36() {
        _randomMask38_36 = new byte[]{76, 39, 9, -124, -32, 0, 102, 113, -116, -50, 48, 0, -111, 64, -14, 40, 28, 0, 66, -112, -88, 82, 20, 0, -92, 41, 84, -123, 40, 0, 19, 90, 2, 107, 64, 0, 48, -109, 70, 18, 104, 0, -120, -84, 49, 21, -124, 0, 9, 12, -63, 33, -104, 0, 44, 22, 5, -126, -64, 0, -111, 64, -46, 40, 24, 0, -64, -48, 56, 26, 4, 0, 6, -126, -96, -48, 84, 0, -56, 12, 89, 1, -120, 0, 69, 97, 8, -84, 32, 0, 48, -111, 70, 18, 40, 0, -94, 40, 52, 69, 4, 0, 81, -105, 42, 50, -28, 0, 44, 22, 5, -126, -64, 0, -111, 64, -46, 40, 24, 0, -64, -48, 56, 26, 4, 0, 6, -126, -96, -48, 84, 0, -56, 12, 89, 1, -120, 0, 69, 97, 8, -84, 32, 0, 48, -111, 70, 18, 40, 0, -94, 40, 52, 69, 4, 0, 76, 39, 9, -124, -32, 0, 102, 113, -116, -50, 48, 0, -111, 64, -14, 40, 28, 0, 66, -112, -88, 82, 20, 0, -92, 41, 84, -123, 40, 0, 19, 90, 2, 107, 64, 0, 48, -109, 70, 18, 104, 0, -120, -84, 49, 21, -124, 0, 9, 12, -63, 33, -104, 0, ByteCompanionObject.MIN_VALUE, -107, -62, 104, 40, 0};
    }

    private static void createRandomMask38_37() {
        _randomMask38_37 = new byte[]{76, 39, 9, -124, -32, 0, 102, 113, -116, -50, 48, 0, -111, 64, -14, 40, 28, 0, 66, -112, -88, 82, 20, 0, -92, 41, 84, -123, 40, 0, 19, 90, 2, 107, 64, 0, 48, -109, 70, 18, 104, 0, -120, -84, 49, 21, -124, 0, 9, 12, -63, 33, -104, 0, 44, 22, 5, -126, -64, 0, -111, 64, -46, 40, 24, 0, -64, -48, 56, 26, 4, 0, 6, -126, -96, -48, 84, 0, -56, 12, 89, 1, -120, 0, 69, 97, 8, -84, 32, 0, 48, -111, 70, 18, 40, 0, -94, 40, 52, 69, 4, 0, 81, -105, 42, 50, -28, 0, 76, 39, 9, -124, -32, 0, 102, 113, -116, -50, 48, 0, -111, 64, -14, 40, 28, 0, 66, -112, -88, 82, 20, 0, -92, 41, 84, -123, 40, 0, 19, 90, 2, 107, 64, 0, 48, -109, 70, 18, 104, 0, -120, -84, 49, 21, -124, 0, 9, 12, -63, 33, -104, 0, 76, 38, 9, -124, -64, 0, 102, 40, -116, -59, Tnaf.POW_2_WIDTH, 0, -111, 80, 50, 42, 4, 0, 66, -126, 104, 80, 76, 0, -92, 1, -44, ByteCompanionObject.MIN_VALUE, 56, 0, 19, 67, 2, 104, 96, 0, 48, -108, -122, 18, -112, 0, -120, -95, 49, 20, 36, 0, 9, 76, 1, 41, ByteCompanionObject.MIN_VALUE, 0, -51, -104, 89, -77, 8, 0};
    }

    private static void createRandomMask38_38() {
        _randomMask38_38 = new byte[]{76, 39, 9, -124, -32, 0, 102, 113, -116, -50, 48, 0, -111, 64, -14, 40, 28, 0, 66, -112, -88, 82, 20, 0, -92, 41, 84, -123, 40, 0, 19, 90, 2, 107, 64, 0, 48, -109, 70, 18, 104, 0, -120, -84, 49, 21, -124, 0, 9, 12, -63, 33, -104, 0, 76, 38, 9, -124, -64, 0, 102, 40, -116, -59, Tnaf.POW_2_WIDTH, 0, -111, 80, 50, 42, 4, 0, 66, -126, 104, 80, 76, 0, -92, 1, -44, ByteCompanionObject.MIN_VALUE, 56, 0, 19, 67, 2, 104, 96, 0, 48, -108, -122, 18, -112, 0, -120, -95, 49, 20, 36, 0, 9, 76, 1, 41, ByteCompanionObject.MIN_VALUE, 0, -51, -104, 89, -77, 8, 0, 76, 39, 9, -124, -32, 0, 102, 113, -116, -50, 48, 0, -111, 64, -14, 40, 28, 0, 66, -112, -88, 82, 20, 0, -92, 41, 84, -123, 40, 0, 19, 90, 2, 107, 64, 0, 48, -109, 70, 18, 104, 0, -120, -84, 49, 21, -124, 0, 9, 12, -63, 33, -104, 0, 44, 22, 5, -126, -64, 0, -111, 64, -46, 40, 24, 0, -64, -48, 56, 26, 4, 0, 6, -126, -96, -48, 84, 0, -56, 12, 89, 1, -120, 0, 69, 97, 8, -84, 32, 0, 48, -111, 70, 18, 40, 0, -94, 40, 52, 69, 4, 0, 81, -105, 42, 50, -28, 0, -116, -19, 17, 95, 36, 0};
    }

    private static void createRandomMask38_4() {
        _randomMask38_4 = new byte[]{-20, 115, 29, -114, 96, 0, 103, 25, -52, -29, 56, 0, -79, -52, 118, 57, -116, 0, 90, -106, -85, 82, -44, 0};
    }

    private static void createRandomMask38_5() {
        _randomMask38_5 = new byte[]{76, -25, 9, -100, -32, 0, 102, 49, -52, -58, 56, 0, -95, -52, 116, 57, -116, 0, -110, -90, -78, 84, -44, 0, -72, -103, -105, 19, 48, 0};
    }

    private static void createRandomMask38_6() {
        _randomMask38_6 = new byte[]{76, 54, 9, -122, -64, 0, 69, 104, -120, -83, Tnaf.POW_2_WIDTH, 0, 48, -48, 102, 26, 12, 0, -118, -126, -79, 80, 84, 0, 38, 11, 68, -63, 104, 0, -107, 69, 18, -88, -96, 0};
    }

    private static void createRandomMask38_7() {
        _randomMask38_7 = new byte[]{-60, -93, 24, -108, 96, 0, 35, 25, -124, 99, 48, 0, -111, 28, 50, 35, -124, 0, 74, -126, -87, 80, 84, 0, 52, 73, 70, -119, 40, 0, -117, 74, 17, 105, 64, 0, -56, 36, -39, 4, -104, 0};
    }

    private static void createRandomMask38_8() {
        _randomMask38_8 = new byte[]{22, 19, -126, -62, 112, 0, -62, 68, -40, 72, -104, 0, 96, -24, 44, 29, 4, 0, -123, 18, 112, -94, 76, 0, -52, 33, 89, -124, 40, 0, 41, 99, 5, 44, 96, 0, 17, -104, -62, 51, 24, 0, -80, 12, 118, 1, -116, 0};
    }

    private static void createRandomMask38_9() {
        _randomMask38_9 = new byte[]{68, -89, 8, -108, -32, 0, 102, 112, -116, -50, Tnaf.POW_2_WIDTH, 0, 18, -64, -30, 88, 28, 0, -61, Tnaf.POW_2_WIDTH, -72, 98, 20, 0, -116, 41, 81, -123, 40, 0, 17, 91, 2, 43, 96, 0, 33, -109, 68, 50, 104, 0, -94, 44, 20, 69, ByteCompanionObject.MIN_VALUE, 0, 24, 12, -29, 1, -100, 0};
    }

    private static void createRandomMask39_1() {
        _randomMask39_1 = new byte[]{-1, -1, -1, -1, -2, 0};
    }

    private static void createRandomMask39_10() {
        _randomMask39_10 = new byte[]{-116, -29, 9, -126, 96, 0, 39, 17, -54, 34, -120, 0, 50, -115, 52, 13, 2, 0, 97, -110, 96, -104, 38, 0, 92, 56, ByteCompanionObject.MIN_VALUE, 112, 28, 0, -52, 117, Tnaf.POW_2_WIDTH, -60, 48, 0, 43, 25, -59, 33, 72, 0, 50, -46, 104, 74, 18, 0, 37, -114, -77, 4, -64, 0, 80, -120, -58, 17, -124, 0};
    }

    private static void createRandomMask39_11() {
        _randomMask39_11 = new byte[]{-116, -29, 24, -58, 48, 0, 39, 17, -60, 113, 28, 0, 50, -115, 35, 72, -46, 0, 97, -110, 100, -103, 38, 0, 92, 56, -114, 35, -120, 0, -124, -121, 1, -64, 112, 0, 39, 25, -122, 97, -104, 0, 81, -120, 98, 24, -122, 0, 26, 34, -88, -86, 42, 0, 104, 68, 81, 20, 68, 0, -119, 112, 28, 7, 0, 0};
    }

    private static void createRandomMask39_12() {
        _randomMask39_12 = new byte[]{-124, -121, 1, -64, 112, 0, 39, 25, -122, 97, -104, 0, 81, -120, 98, 24, -122, 0, 26, 34, -88, -86, 42, 0, 104, 68, 81, 20, 68, 0, -119, 112, 28, 7, 0, 0, -116, -29, 24, -58, 48, 0, 39, 17, -60, 113, 28, 0, 50, -115, 35, 72, -46, 0, 97, -110, 100, -103, 38, 0, 92, 56, -114, 35, -120, 0, -112, -56, -98, -69, -120, 0};
    }

    private static void createRandomMask39_13() {
        _randomMask39_13 = new byte[]{-124, -121, 1, -64, 112, 0, 39, 25, -122, 97, -104, 0, 81, -120, 98, 24, -122, 0, 26, 34, -88, -86, 42, 0, 104, 68, 81, 20, 68, 0, -119, 112, 28, 7, 0, 0, -116, 35, 8, -62, 48, 0, 71, 25, -122, 97, -104, 0, -127, -120, 98, 24, -122, 0, 18, -122, 33, -120, 98, 0, 88, 20, 69, 17, 68, 0, 40, -54, 18, -124, -96, 0, 52, 96, -104, 38, 8, 0};
    }

    private static void createRandomMask39_14() {
        _randomMask39_14 = new byte[]{-116, 35, 8, -62, 48, 0, 71, 25, -122, 97, -104, 0, -127, -120, 98, 24, -122, 0, 18, -122, 33, -120, 98, 0, 88, 20, 69, 17, 68, 0, 40, -54, 18, -124, -96, 0, 52, 96, -104, 38, 8, 0, -124, -121, 1, -64, 112, 0, 39, 25, -122, 97, -104, 0, 81, -120, 98, 24, -122, 0, 26, 34, -88, -86, 42, 0, 104, 68, 81, 20, 68, 0, -119, 112, 28, 7, 0, 0, 110, 39, 106, -57, -60, 0};
    }

    private static void createRandomMask39_15() {
        _randomMask39_15 = new byte[]{-116, 35, 8, -62, 48, 0, 71, 25, -122, 97, -104, 0, -127, -120, 98, 24, -122, 0, 18, -122, 33, -120, 98, 0, 88, 20, 69, 17, 68, 0, 40, -54, 18, -124, -96, 0, 52, 96, -104, 38, 8, 0, 44, 22, 5, -127, 96, 0, -111, 64, -48, 52, 12, 0, -64, -48, 52, 13, 2, 0, 6, -126, -96, -88, 42, 0, -56, 12, 67, Tnaf.POW_2_WIDTH, -60, 0, 69, 97, 24, 70, Tnaf.POW_2_WIDTH, 0, 48, -111, 68, 81, 20, 0, -94, 40, 42, 10, -126, 0};
    }

    private static void createRandomMask39_16() {
        _randomMask39_16 = new byte[]{44, 22, 5, -127, 96, 0, -111, 64, -48, 52, 12, 0, -64, -48, 52, 13, 2, 0, 6, -126, -96, -88, 42, 0, -56, 12, 67, Tnaf.POW_2_WIDTH, -60, 0, 69, 97, 24, 70, Tnaf.POW_2_WIDTH, 0, 48, -111, 68, 81, 20, 0, -94, 40, 42, 10, -126, 0, -116, 35, 8, -62, 48, 0, 71, 25, -122, 97, -104, 0, -127, -120, 98, 24, -122, 0, 18, -122, 33, -120, 98, 0, 88, 20, 69, 17, 68, 0, 40, -54, 18, -124, -96, 0, 52, 96, -104, 38, 8, 0, 126, 117, -27, 3, -116, 0};
    }

    private static void createRandomMask39_17() {
        _randomMask39_17 = new byte[]{44, 22, 5, -127, 96, 0, -111, 64, -48, 52, 12, 0, -64, -48, 52, 13, 2, 0, 6, -126, -96, -88, 42, 0, -56, 12, 67, Tnaf.POW_2_WIDTH, -60, 0, 69, 97, 24, 70, Tnaf.POW_2_WIDTH, 0, 48, -111, 68, 81, 20, 0, -94, 40, 42, 10, -126, 0, 76, 39, 9, -62, 112, 0, 102, 113, -100, 103, 24, 0, -111, 64, -16, 60, 14, 0, 66, -112, -92, 41, 10, 0, -92, 41, 74, 82, -108, 0, 19, 90, 22, -123, -96, 0, 48, -109, 68, -47, 52, 0, -120, -84, 43, 10, -62, 0, 9, 12, -61, 48, -52, 0};
    }

    private static void createRandomMask39_18() {
        _randomMask39_18 = new byte[]{76, 39, 9, -62, 112, 0, 102, 113, -100, 103, 24, 0, -111, 64, -16, 60, 14, 0, 66, -112, -92, 41, 10, 0, -92, 41, 74, 82, -108, 0, 19, 90, 22, -123, -96, 0, 48, -109, 68, -47, 52, 0, -120, -84, 43, 10, -62, 0, 9, 12, -61, 48, -52, 0, 44, 22, 5, -127, 96, 0, -111, 64, -48, 52, 12, 0, -64, -48, 52, 13, 2, 0, 6, -126, -96, -88, 42, 0, -56, 12, 67, Tnaf.POW_2_WIDTH, -60, 0, 69, 97, 24, 70, Tnaf.POW_2_WIDTH, 0, 48, -111, 68, 81, 20, 0, -94, 40, 42, 10, -126, 0, 81, -105, 36, 47, 126, 0};
    }

    private static void createRandomMask39_19() {
        _randomMask39_19 = new byte[]{76, 39, 9, -62, 112, 0, 102, 113, -100, 103, 24, 0, -111, 64, -16, 60, 14, 0, 66, -112, -92, 41, 10, 0, -92, 41, 74, 82, -108, 0, 19, 90, 22, -123, -96, 0, 48, -109, 68, -47, 52, 0, -120, -84, 43, 10, -62, 0, 9, 12, -61, 48, -52, 0, 76, 38, 9, -126, 96, 0, 102, 40, -118, 34, -120, 0, -111, 80, 52, 13, 2, 0, 66, -126, 96, -104, 38, 0, -92, 1, -64, 112, 28, 0, 19, 67, Tnaf.POW_2_WIDTH, -60, 48, 0, 48, -108, -123, 33, 72, 0, -120, -95, 40, 74, 18, 0, 9, 76, 19, 4, -64, 0, -51, -104, 70, 17, -124, 0};
    }

    private static void createRandomMask39_2() {
        _randomMask39_2 = new byte[]{-50, 119, 29, -57, 112, 0, 57, -52, -13, 60, -50, 0};
    }

    private static void createRandomMask39_20() {
        _randomMask39_20 = new byte[]{-116, -29, 9, -126, 96, 0, 39, 17, -54, 34, -120, 0, 50, -115, 52, 13, 2, 0, 97, -110, 96, -104, 38, 0, 92, 56, ByteCompanionObject.MIN_VALUE, 112, 28, 0, -52, 117, Tnaf.POW_2_WIDTH, -60, 48, 0, 43, 25, -59, 33, 72, 0, 50, -46, 104, 74, 18, 0, 37, -114, -77, 4, -64, 0, 80, -120, -58, 17, -124, 0, 68, -89, 9, -62, 112, 0, 102, 112, -116, 71, 24, 0, 18, -64, -16, 60, 14, 0, -61, Tnaf.POW_2_WIDTH, PSSSigner.TRAILER_IMPLICIT, 41, 10, 0, -116, 41, 66, 114, -108, 0, 17, 91, 22, -123, -96, 0, 33, -109, 68, -47, 52, 0, -94, 44, 11, 10, -62, 0, 24, 12, -23, 48, -54, 0, 13, -70, 82, 56, PSSSigner.TRAILER_IMPLICIT, 0};
    }

    private static void createRandomMask39_21() {
        _randomMask39_21 = new byte[]{-116, -29, 9, -126, 96, 0, 39, 17, -54, 34, -120, 0, 50, -115, 52, 13, 2, 0, 97, -110, 96, -104, 38, 0, 92, 56, ByteCompanionObject.MIN_VALUE, 112, 28, 0, -52, 117, Tnaf.POW_2_WIDTH, -60, 48, 0, 43, 25, -59, 33, 72, 0, 50, -46, 104, 74, 18, 0, 37, -114, -77, 4, -64, 0, 80, -120, -58, 17, -124, 0, -116, -29, 24, -58, 48, 0, 39, 17, -60, 113, 28, 0, 50, -115, 35, 72, -46, 0, 97, -110, 100, -103, 38, 0, 92, 56, -114, 35, -120, 0, -124, -121, 1, -64, 112, 0, 39, 25, -122, 97, -104, 0, 81, -120, 98, 24, -122, 0, 26, 34, -88, -86, 42, 0, 104, 68, 81, 20, 68, 0, -119, 112, 28, 7, 0, 0};
    }

    private static void createRandomMask39_22() {
        _randomMask39_22 = new byte[]{-116, -29, 24, -58, 48, 0, 39, 17, -60, 113, 28, 0, 50, -115, 35, 72, -46, 0, 97, -110, 100, -103, 38, 0, 92, 56, -114, 35, -120, 0, -124, -121, 1, -64, 112, 0, 39, 25, -122, 97, -104, 0, 81, -120, 98, 24, -122, 0, 26, 34, -88, -86, 42, 0, 104, 68, 81, 20, 68, 0, -119, 112, 28, 7, 0, 0, -116, -29, 9, -126, 96, 0, 39, 17, -54, 34, -120, 0, 50, -115, 52, 13, 2, 0, 97, -110, 96, -104, 38, 0, 92, 56, ByteCompanionObject.MIN_VALUE, 112, 28, 0, -52, 117, Tnaf.POW_2_WIDTH, -60, 48, 0, 43, 25, -59, 33, 72, 0, 50, -46, 104, 74, 18, 0, 37, -114, -77, 4, -64, 0, 80, -120, -58, 17, -124, 0, -4, 90, -78, 19, 18, 0};
    }

    private static void createRandomMask39_23() {
        _randomMask39_23 = new byte[]{-116, -29, 24, -58, 48, 0, 39, 17, -60, 113, 28, 0, 50, -115, 35, 72, -46, 0, 97, -110, 100, -103, 38, 0, 92, 56, -114, 35, -120, 0, -124, -121, 1, -64, 112, 0, 39, 25, -122, 97, -104, 0, 81, -120, 98, 24, -122, 0, 26, 34, -88, -86, 42, 0, 104, 68, 81, 20, 68, 0, -119, 112, 28, 7, 0, 0, -124, -121, 1, -64, 112, 0, 39, 25, -122, 97, -104, 0, 81, -120, 98, 24, -122, 0, 26, 34, -88, -86, 42, 0, 104, 68, 81, 20, 68, 0, -119, 112, 28, 7, 0, 0, -116, -29, 24, -58, 48, 0, 39, 17, -60, 113, 28, 0, 50, -115, 35, 72, -46, 0, 97, -110, 100, -103, 38, 0, 92, 56, -114, 35, -120, 0, -112, -56, -98, -69, -120, 0};
    }

    private static void createRandomMask39_24() {
        _randomMask39_24 = new byte[]{-124, -121, 1, -64, 112, 0, 39, 25, -122, 97, -104, 0, 81, -120, 98, 24, -122, 0, 26, 34, -88, -86, 42, 0, 104, 68, 81, 20, 68, 0, -119, 112, 28, 7, 0, 0, -116, -29, 24, -58, 48, 0, 39, 17, -60, 113, 28, 0, 50, -115, 35, 72, -46, 0, 97, -110, 100, -103, 38, 0, 92, 56, -114, 35, -120, 0, -112, -56, -98, -69, -120, 0, -116, -29, 24, -58, 48, 0, 39, 17, -60, 113, 28, 0, 50, -115, 35, 72, -46, 0, 97, -110, 100, -103, 38, 0, 92, 56, -114, 35, -120, 0, -124, -121, 1, -64, 112, 0, 39, 25, -122, 97, -104, 0, 81, -120, 98, 24, -122, 0, 26, 34, -88, -86, 42, 0, 104, 68, 81, 20, 68, 0, -119, 112, 28, 7, 0, 0, -84, PSSSigner.TRAILER_IMPLICIT, -16, -1, 98, 0};
    }

    private static void createRandomMask39_25() {
        _randomMask39_25 = new byte[]{-124, -121, 1, -64, 112, 0, 39, 25, -122, 97, -104, 0, 81, -120, 98, 24, -122, 0, 26, 34, -88, -86, 42, 0, 104, 68, 81, 20, 68, 0, -119, 112, 28, 7, 0, 0, -116, -29, 24, -58, 48, 0, 39, 17, -60, 113, 28, 0, 50, -115, 35, 72, -46, 0, 97, -110, 100, -103, 38, 0, 92, 56, -114, 35, -120, 0, -112, -56, -98, -69, -120, 0, -124, -121, 1, -64, 112, 0, 39, 25, -122, 97, -104, 0, 81, -120, 98, 24, -122, 0, 26, 34, -88, -86, 42, 0, 104, 68, 81, 20, 68, 0, -119, 112, 28, 7, 0, 0, -116, 35, 8, -62, 48, 0, 71, 25, -122, 97, -104, 0, -127, -120, 98, 24, -122, 0, 18, -122, 33, -120, 98, 0, 88, 20, 69, 17, 68, 0, 40, -54, 18, -124, -96, 0, 52, 96, -104, 38, 8, 0};
    }

    private static void createRandomMask39_26() {
        _randomMask39_26 = new byte[]{-124, -121, 1, -64, 112, 0, 39, 25, -122, 97, -104, 0, 81, -120, 98, 24, -122, 0, 26, 34, -88, -86, 42, 0, 104, 68, 81, 20, 68, 0, -119, 112, 28, 7, 0, 0, -116, 35, 8, -62, 48, 0, 71, 25, -122, 97, -104, 0, -127, -120, 98, 24, -122, 0, 18, -122, 33, -120, 98, 0, 88, 20, 69, 17, 68, 0, 40, -54, 18, -124, -96, 0, 52, 96, -104, 38, 8, 0, -124, -121, 1, -64, 112, 0, 39, 25, -122, 97, -104, 0, 81, -120, 98, 24, -122, 0, 26, 34, -88, -86, 42, 0, 104, 68, 81, 20, 68, 0, -119, 112, 28, 7, 0, 0, -116, -29, 24, -58, 48, 0, 39, 17, -60, 113, 28, 0, 50, -115, 35, 72, -46, 0, 97, -110, 100, -103, 38, 0, 92, 56, -114, 35, -120, 0, -112, -56, -98, -69, -120, 0, Tnaf.POW_2_WIDTH, 23, 68, 114, -20, 0};
    }

    private static void createRandomMask39_27() {
        _randomMask39_27 = new byte[]{-124, -121, 1, -64, 112, 0, 39, 25, -122, 97, -104, 0, 81, -120, 98, 24, -122, 0, 26, 34, -88, -86, 42, 0, 104, 68, 81, 20, 68, 0, -119, 112, 28, 7, 0, 0, -116, 35, 8, -62, 48, 0, 71, 25, -122, 97, -104, 0, -127, -120, 98, 24, -122, 0, 18, -122, 33, -120, 98, 0, 88, 20, 69, 17, 68, 0, 40, -54, 18, -124, -96, 0, 52, 96, -104, 38, 8, 0, -116, 35, 8, -62, 48, 0, 71, 25, -122, 97, -104, 0, -127, -120, 98, 24, -122, 0, 18, -122, 33, -120, 98, 0, 88, 20, 69, 17, 68, 0, 40, -54, 18, -124, -96, 0, 52, 96, -104, 38, 8, 0, -124, -121, 1, -64, 112, 0, 39, 25, -122, 97, -104, 0, 81, -120, 98, 24, -122, 0, 26, 34, -88, -86, 42, 0, 104, 68, 81, 20, 68, 0, -119, 112, 28, 7, 0, 0, 110, 39, 106, -57, -60, 0};
    }

    private static void createRandomMask39_28() {
        _randomMask39_28 = new byte[]{-116, 35, 8, -62, 48, 0, 71, 25, -122, 97, -104, 0, -127, -120, 98, 24, -122, 0, 18, -122, 33, -120, 98, 0, 88, 20, 69, 17, 68, 0, 40, -54, 18, -124, -96, 0, 52, 96, -104, 38, 8, 0, -124, -121, 1, -64, 112, 0, 39, 25, -122, 97, -104, 0, 81, -120, 98, 24, -122, 0, 26, 34, -88, -86, 42, 0, 104, 68, 81, 20, 68, 0, -119, 112, 28, 7, 0, 0, 110, 39, 106, -57, -60, 0, -124, -121, 1, -64, 112, 0, 39, 25, -122, 97, -104, 0, 81, -120, 98, 24, -122, 0, 26, 34, -88, -86, 42, 0, 104, 68, 81, 20, 68, 0, -119, 112, 28, 7, 0, 0, -116, 35, 8, -62, 48, 0, 71, 25, -122, 97, -104, 0, -127, -120, 98, 24, -122, 0, 18, -122, 33, -120, 98, 0, 88, 20, 69, 17, 68, 0, 40, -54, 18, -124, -96, 0, 52, 96, -104, 38, 8, 0, -122, -74, 4, PSSSigner.TRAILER_IMPLICIT, 30, 0};
    }

    private static void createRandomMask39_29() {
        _randomMask39_29 = new byte[]{-116, 35, 8, -62, 48, 0, 71, 25, -122, 97, -104, 0, -127, -120, 98, 24, -122, 0, 18, -122, 33, -120, 98, 0, 88, 20, 69, 17, 68, 0, 40, -54, 18, -124, -96, 0, 52, 96, -104, 38, 8, 0, -124, -121, 1, -64, 112, 0, 39, 25, -122, 97, -104, 0, 81, -120, 98, 24, -122, 0, 26, 34, -88, -86, 42, 0, 104, 68, 81, 20, 68, 0, -119, 112, 28, 7, 0, 0, 110, 39, 106, -57, -60, 0, -116, 35, 8, -62, 48, 0, 71, 25, -122, 97, -104, 0, -127, -120, 98, 24, -122, 0, 18, -122, 33, -120, 98, 0, 88, 20, 69, 17, 68, 0, 40, -54, 18, -124, -96, 0, 52, 96, -104, 38, 8, 0, 44, 22, 5, -127, 96, 0, -111, 64, -48, 52, 12, 0, -64, -48, 52, 13, 2, 0, 6, -126, -96, -88, 42, 0, -56, 12, 67, Tnaf.POW_2_WIDTH, -60, 0, 69, 97, 24, 70, Tnaf.POW_2_WIDTH, 0, 48, -111, 68, 81, 20, 0, -94, 40, 42, 10, -126, 0};
    }

    private static void createRandomMask39_3() {
        _randomMask39_3 = new byte[]{-52, 103, 25, -58, 112, 0, 39, 44, -54, -78, -84, 0, -110, -46, 118, 45, 70, 0};
    }

    private static void createRandomMask39_30() {
        _randomMask39_30 = new byte[]{-116, 35, 8, -62, 48, 0, 71, 25, -122, 97, -104, 0, -127, -120, 98, 24, -122, 0, 18, -122, 33, -120, 98, 0, 88, 20, 69, 17, 68, 0, 40, -54, 18, -124, -96, 0, 52, 96, -104, 38, 8, 0, 44, 22, 5, -127, 96, 0, -111, 64, -48, 52, 12, 0, -64, -48, 52, 13, 2, 0, 6, -126, -96, -88, 42, 0, -56, 12, 67, Tnaf.POW_2_WIDTH, -60, 0, 69, 97, 24, 70, Tnaf.POW_2_WIDTH, 0, 48, -111, 68, 81, 20, 0, -94, 40, 42, 10, -126, 0, -116, 35, 8, -62, 48, 0, 71, 25, -122, 97, -104, 0, -127, -120, 98, 24, -122, 0, 18, -122, 33, -120, 98, 0, 88, 20, 69, 17, 68, 0, 40, -54, 18, -124, -96, 0, 52, 96, -104, 38, 8, 0, -124, -121, 1, -64, 112, 0, 39, 25, -122, 97, -104, 0, 81, -120, 98, 24, -122, 0, 26, 34, -88, -86, 42, 0, 104, 68, 81, 20, 68, 0, -119, 112, 28, 7, 0, 0, 110, 39, 106, -57, -60, 0, -77, 29, 19, 3, 90, 0};
    }

    private static void createRandomMask39_31() {
        _randomMask39_31 = new byte[]{-116, 35, 8, -62, 48, 0, 71, 25, -122, 97, -104, 0, -127, -120, 98, 24, -122, 0, 18, -122, 33, -120, 98, 0, 88, 20, 69, 17, 68, 0, 40, -54, 18, -124, -96, 0, 52, 96, -104, 38, 8, 0, 44, 22, 5, -127, 96, 0, -111, 64, -48, 52, 12, 0, -64, -48, 52, 13, 2, 0, 6, -126, -96, -88, 42, 0, -56, 12, 67, Tnaf.POW_2_WIDTH, -60, 0, 69, 97, 24, 70, Tnaf.POW_2_WIDTH, 0, 48, -111, 68, 81, 20, 0, -94, 40, 42, 10, -126, 0, 44, 22, 5, -127, 96, 0, -111, 64, -48, 52, 12, 0, -64, -48, 52, 13, 2, 0, 6, -126, -96, -88, 42, 0, -56, 12, 67, Tnaf.POW_2_WIDTH, -60, 0, 69, 97, 24, 70, Tnaf.POW_2_WIDTH, 0, 48, -111, 68, 81, 20, 0, -94, 40, 42, 10, -126, 0, -116, 35, 8, -62, 48, 0, 71, 25, -122, 97, -104, 0, -127, -120, 98, 24, -122, 0, 18, -122, 33, -120, 98, 0, 88, 20, 69, 17, 68, 0, 40, -54, 18, -124, -96, 0, 52, 96, -104, 38, 8, 0, 126, 117, -27, 3, -116, 0};
    }

    private static void createRandomMask39_32() {
        _randomMask39_32 = new byte[]{44, 22, 5, -127, 96, 0, -111, 64, -48, 52, 12, 0, -64, -48, 52, 13, 2, 0, 6, -126, -96, -88, 42, 0, -56, 12, 67, Tnaf.POW_2_WIDTH, -60, 0, 69, 97, 24, 70, Tnaf.POW_2_WIDTH, 0, 48, -111, 68, 81, 20, 0, -94, 40, 42, 10, -126, 0, -116, 35, 8, -62, 48, 0, 71, 25, -122, 97, -104, 0, -127, -120, 98, 24, -122, 0, 18, -122, 33, -120, 98, 0, 88, 20, 69, 17, 68, 0, 40, -54, 18, -124, -96, 0, 52, 96, -104, 38, 8, 0, 126, 117, -27, 3, -116, 0, -116, 35, 8, -62, 48, 0, 71, 25, -122, 97, -104, 0, -127, -120, 98, 24, -122, 0, 18, -122, 33, -120, 98, 0, 88, 20, 69, 17, 68, 0, 40, -54, 18, -124, -96, 0, 52, 96, -104, 38, 8, 0, 44, 22, 5, -127, 96, 0, -111, 64, -48, 52, 12, 0, -64, -48, 52, 13, 2, 0, 6, -126, -96, -88, 42, 0, -56, 12, 67, Tnaf.POW_2_WIDTH, -60, 0, 69, 97, 24, 70, Tnaf.POW_2_WIDTH, 0, 48, -111, 68, 81, 20, 0, -94, 40, 42, 10, -126, 0, -125, 26, 60, 42, 122, 0};
    }

    private static void createRandomMask39_33() {
        _randomMask39_33 = new byte[]{44, 22, 5, -127, 96, 0, -111, 64, -48, 52, 12, 0, -64, -48, 52, 13, 2, 0, 6, -126, -96, -88, 42, 0, -56, 12, 67, Tnaf.POW_2_WIDTH, -60, 0, 69, 97, 24, 70, Tnaf.POW_2_WIDTH, 0, 48, -111, 68, 81, 20, 0, -94, 40, 42, 10, -126, 0, -116, 35, 8, -62, 48, 0, 71, 25, -122, 97, -104, 0, -127, -120, 98, 24, -122, 0, 18, -122, 33, -120, 98, 0, 88, 20, 69, 17, 68, 0, 40, -54, 18, -124, -96, 0, 52, 96, -104, 38, 8, 0, 126, 117, -27, 3, -116, 0, 44, 22, 5, -127, 96, 0, -111, 64, -48, 52, 12, 0, -64, -48, 52, 13, 2, 0, 6, -126, -96, -88, 42, 0, -56, 12, 67, Tnaf.POW_2_WIDTH, -60, 0, 69, 97, 24, 70, Tnaf.POW_2_WIDTH, 0, 48, -111, 68, 81, 20, 0, -94, 40, 42, 10, -126, 0, 76, 39, 9, -62, 112, 0, 102, 113, -100, 103, 24, 0, -111, 64, -16, 60, 14, 0, 66, -112, -92, 41, 10, 0, -92, 41, 74, 82, -108, 0, 19, 90, 22, -123, -96, 0, 48, -109, 68, -47, 52, 0, -120, -84, 43, 10, -62, 0, 9, 12, -61, 48, -52, 0};
    }

    private static void createRandomMask39_34() {
        _randomMask39_34 = new byte[]{44, 22, 5, -127, 96, 0, -111, 64, -48, 52, 12, 0, -64, -48, 52, 13, 2, 0, 6, -126, -96, -88, 42, 0, -56, 12, 67, Tnaf.POW_2_WIDTH, -60, 0, 69, 97, 24, 70, Tnaf.POW_2_WIDTH, 0, 48, -111, 68, 81, 20, 0, -94, 40, 42, 10, -126, 0, 76, 39, 9, -62, 112, 0, 102, 113, -100, 103, 24, 0, -111, 64, -16, 60, 14, 0, 66, -112, -92, 41, 10, 0, -92, 41, 74, 82, -108, 0, 19, 90, 22, -123, -96, 0, 48, -109, 68, -47, 52, 0, -120, -84, 43, 10, -62, 0, 9, 12, -61, 48, -52, 0, 44, 22, 5, -127, 96, 0, -111, 64, -48, 52, 12, 0, -64, -48, 52, 13, 2, 0, 6, -126, -96, -88, 42, 0, -56, 12, 67, Tnaf.POW_2_WIDTH, -60, 0, 69, 97, 24, 70, Tnaf.POW_2_WIDTH, 0, 48, -111, 68, 81, 20, 0, -94, 40, 42, 10, -126, 0, -116, 35, 8, -62, 48, 0, 71, 25, -122, 97, -104, 0, -127, -120, 98, 24, -122, 0, 18, -122, 33, -120, 98, 0, 88, 20, 69, 17, 68, 0, 40, -54, 18, -124, -96, 0, 52, 96, -104, 38, 8, 0, 126, 117, -27, 3, -116, 0, -58, -69, 126, -39, ByteCompanionObject.MIN_VALUE, 0};
    }

    private static void createRandomMask39_35() {
        _randomMask39_35 = new byte[]{44, 22, 5, -127, 96, 0, -111, 64, -48, 52, 12, 0, -64, -48, 52, 13, 2, 0, 6, -126, -96, -88, 42, 0, -56, 12, 67, Tnaf.POW_2_WIDTH, -60, 0, 69, 97, 24, 70, Tnaf.POW_2_WIDTH, 0, 48, -111, 68, 81, 20, 0, -94, 40, 42, 10, -126, 0, 76, 39, 9, -62, 112, 0, 102, 113, -100, 103, 24, 0, -111, 64, -16, 60, 14, 0, 66, -112, -92, 41, 10, 0, -92, 41, 74, 82, -108, 0, 19, 90, 22, -123, -96, 0, 48, -109, 68, -47, 52, 0, -120, -84, 43, 10, -62, 0, 9, 12, -61, 48, -52, 0, 76, 39, 9, -62, 112, 0, 102, 113, -100, 103, 24, 0, -111, 64, -16, 60, 14, 0, 66, -112, -92, 41, 10, 0, -92, 41, 74, 82, -108, 0, 19, 90, 22, -123, -96, 0, 48, -109, 68, -47, 52, 0, -120, -84, 43, 10, -62, 0, 9, 12, -61, 48, -52, 0, 44, 22, 5, -127, 96, 0, -111, 64, -48, 52, 12, 0, -64, -48, 52, 13, 2, 0, 6, -126, -96, -88, 42, 0, -56, 12, 67, Tnaf.POW_2_WIDTH, -60, 0, 69, 97, 24, 70, Tnaf.POW_2_WIDTH, 0, 48, -111, 68, 81, 20, 0, -94, 40, 42, 10, -126, 0, 81, -105, 36, 47, 126, 0};
    }

    private static void createRandomMask39_36() {
        _randomMask39_36 = new byte[]{76, 39, 9, -62, 112, 0, 102, 113, -100, 103, 24, 0, -111, 64, -16, 60, 14, 0, 66, -112, -92, 41, 10, 0, -92, 41, 74, 82, -108, 0, 19, 90, 22, -123, -96, 0, 48, -109, 68, -47, 52, 0, -120, -84, 43, 10, -62, 0, 9, 12, -61, 48, -52, 0, 44, 22, 5, -127, 96, 0, -111, 64, -48, 52, 12, 0, -64, -48, 52, 13, 2, 0, 6, -126, -96, -88, 42, 0, -56, 12, 67, Tnaf.POW_2_WIDTH, -60, 0, 69, 97, 24, 70, Tnaf.POW_2_WIDTH, 0, 48, -111, 68, 81, 20, 0, -94, 40, 42, 10, -126, 0, 81, -105, 36, 47, 126, 0, 44, 22, 5, -127, 96, 0, -111, 64, -48, 52, 12, 0, -64, -48, 52, 13, 2, 0, 6, -126, -96, -88, 42, 0, -56, 12, 67, Tnaf.POW_2_WIDTH, -60, 0, 69, 97, 24, 70, Tnaf.POW_2_WIDTH, 0, 48, -111, 68, 81, 20, 0, -94, 40, 42, 10, -126, 0, 76, 39, 9, -62, 112, 0, 102, 113, -100, 103, 24, 0, -111, 64, -16, 60, 14, 0, 66, -112, -92, 41, 10, 0, -92, 41, 74, 82, -108, 0, 19, 90, 22, -123, -96, 0, 48, -109, 68, -47, 52, 0, -120, -84, 43, 10, -62, 0, 9, 12, -61, 48, -52, 0, 17, 120, -2, 67, -42, 0};
    }

    private static void createRandomMask39_37() {
        _randomMask39_37 = new byte[]{76, 39, 9, -62, 112, 0, 102, 113, -100, 103, 24, 0, -111, 64, -16, 60, 14, 0, 66, -112, -92, 41, 10, 0, -92, 41, 74, 82, -108, 0, 19, 90, 22, -123, -96, 0, 48, -109, 68, -47, 52, 0, -120, -84, 43, 10, -62, 0, 9, 12, -61, 48, -52, 0, 44, 22, 5, -127, 96, 0, -111, 64, -48, 52, 12, 0, -64, -48, 52, 13, 2, 0, 6, -126, -96, -88, 42, 0, -56, 12, 67, Tnaf.POW_2_WIDTH, -60, 0, 69, 97, 24, 70, Tnaf.POW_2_WIDTH, 0, 48, -111, 68, 81, 20, 0, -94, 40, 42, 10, -126, 0, 81, -105, 36, 47, 126, 0, 76, 39, 9, -62, 112, 0, 102, 113, -100, 103, 24, 0, -111, 64, -16, 60, 14, 0, 66, -112, -92, 41, 10, 0, -92, 41, 74, 82, -108, 0, 19, 90, 22, -123, -96, 0, 48, -109, 68, -47, 52, 0, -120, -84, 43, 10, -62, 0, 9, 12, -61, 48, -52, 0, 76, 38, 9, -126, 96, 0, 102, 40, -118, 34, -120, 0, -111, 80, 52, 13, 2, 0, 66, -126, 96, -104, 38, 0, -92, 1, -64, 112, 28, 0, 19, 67, Tnaf.POW_2_WIDTH, -60, 48, 0, 48, -108, -123, 33, 72, 0, -120, -95, 40, 74, 18, 0, 9, 76, 19, 4, -64, 0, -51, -104, 70, 17, -124, 0};
    }

    private static void createRandomMask39_38() {
        _randomMask39_38 = new byte[]{76, 39, 9, -62, 112, 0, 102, 113, -100, 103, 24, 0, -111, 64, -16, 60, 14, 0, 66, -112, -92, 41, 10, 0, -92, 41, 74, 82, -108, 0, 19, 90, 22, -123, -96, 0, 48, -109, 68, -47, 52, 0, -120, -84, 43, 10, -62, 0, 9, 12, -61, 48, -52, 0, 76, 38, 9, -126, 96, 0, 102, 40, -118, 34, -120, 0, -111, 80, 52, 13, 2, 0, 66, -126, 96, -104, 38, 0, -92, 1, -64, 112, 28, 0, 19, 67, Tnaf.POW_2_WIDTH, -60, 48, 0, 48, -108, -123, 33, 72, 0, -120, -95, 40, 74, 18, 0, 9, 76, 19, 4, -64, 0, -51, -104, 70, 17, -124, 0, 76, 39, 9, -62, 112, 0, 102, 113, -100, 103, 24, 0, -111, 64, -16, 60, 14, 0, 66, -112, -92, 41, 10, 0, -92, 41, 74, 82, -108, 0, 19, 90, 22, -123, -96, 0, 48, -109, 68, -47, 52, 0, -120, -84, 43, 10, -62, 0, 9, 12, -61, 48, -52, 0, 44, 22, 5, -127, 96, 0, -111, 64, -48, 52, 12, 0, -64, -48, 52, 13, 2, 0, 6, -126, -96, -88, 42, 0, -56, 12, 67, Tnaf.POW_2_WIDTH, -60, 0, 69, 97, 24, 70, Tnaf.POW_2_WIDTH, 0, 48, -111, 68, 81, 20, 0, -94, 40, 42, 10, -126, 0, 81, -105, 36, 47, 126, 0, -98, -40, 60, 126, 46, 0};
    }

    private static void createRandomMask39_39() {
        _randomMask39_39 = new byte[]{76, 39, 9, -62, 112, 0, 102, 113, -100, 103, 24, 0, -111, 64, -16, 60, 14, 0, 66, -112, -92, 41, 10, 0, -92, 41, 74, 82, -108, 0, 19, 90, 22, -123, -96, 0, 48, -109, 68, -47, 52, 0, -120, -84, 43, 10, -62, 0, 9, 12, -61, 48, -52, 0, 76, 38, 9, -126, 96, 0, 102, 40, -118, 34, -120, 0, -111, 80, 52, 13, 2, 0, 66, -126, 96, -104, 38, 0, -92, 1, -64, 112, 28, 0, 19, 67, Tnaf.POW_2_WIDTH, -60, 48, 0, 48, -108, -123, 33, 72, 0, -120, -95, 40, 74, 18, 0, 9, 76, 19, 4, -64, 0, -51, -104, 70, 17, -124, 0, 76, 39, 9, -126, 96, 0, 102, 113, -118, 34, -120, 0, -111, 64, -12, 13, 2, 0, 66, -112, -96, -104, 38, 0, -92, 41, 64, 112, 28, 0, 19, 90, Tnaf.POW_2_WIDTH, -60, 48, 0, 48, -109, 69, 33, 72, 0, -120, -84, 40, 74, 18, 0, 9, 12, -45, 4, -64, 0, 76, 38, 6, 17, -124, 0, 102, 40, -119, -62, 112, 0, -111, 80, 60, 103, 24, 0, 66, -126, 112, 60, 14, 0, -92, 1, -60, 41, 10, 0, 19, 67, 10, 82, -108, 0, 48, -108, -106, -123, -96, 0, -120, -95, 36, -47, 52, 0, 9, 76, 11, 10, -62, 0, -51, -104, 67, 48, -52, 0, 29, 4, 62, -15, -76, 0};
    }

    private static void createRandomMask39_4() {
        _randomMask39_4 = new byte[]{-20, 115, 28, -57, 48, 0, 103, 25, -58, 113, -100, 0, -79, -52, 115, 28, -58, 0, 90, -106, -91, -87, 106, 0};
    }

    private static void createRandomMask39_5() {
        _randomMask39_5 = new byte[]{76, -25, 25, -58, 112, 0, 102, 49, -52, 115, 28, 0, -95, -52, 115, 28, -90, 0, -110, -90, -91, 106, 106, 0, -72, -103, -106, -117, -108, 0};
    }

    private static void createRandomMask39_6() {
        _randomMask39_6 = new byte[]{76, 54, 9, -125, 96, 0, 69, 104, -118, 38, -120, 0, 48, -48, 100, 29, 6, 0, -118, -126, -80, -88, 42, 0, 38, 11, 64, -48, -44, 0, -107, 69, 19, 68, 48, 0};
    }

    private static void createRandomMask39_7() {
        _randomMask39_7 = new byte[]{-60, -93, 9, -62, 48, 0, 35, 25, -122, 101, ByteCompanionObject.MIN_VALUE, 0, -111, 28, 34, 1, -42, 0, 74, -126, -80, 42, 42, 0, 52, 73, 68, -104, -108, 0, -117, 74, 26, -124, 96, 0, -56, 36, -63, -108, 76, 0};
    }

    private static void createRandomMask39_8() {
        _randomMask39_8 = new byte[]{22, 19, -124, -31, 56, 0, -62, 68, -47, 52, 76, 0, 96, -24, 58, 14, -126, 0, -123, 18, 100, -103, 38, 0, -52, 33, 92, 82, 20, 0, 41, 99, 24, -58, 48, 0, 17, -104, -58, 49, -116, 0, -80, 12, 99, 24, -58, 0};
    }

    private static void createRandomMask39_9() {
        _randomMask39_9 = new byte[]{68, -89, 9, -62, 112, 0, 102, 112, -116, 71, 24, 0, 18, -64, -16, 60, 14, 0, -61, Tnaf.POW_2_WIDTH, PSSSigner.TRAILER_IMPLICIT, 41, 10, 0, -116, 41, 66, 114, -108, 0, 17, 91, 22, -123, -96, 0, 33, -109, 68, -47, 52, 0, -94, 44, 11, 10, -62, 0, 24, 12, -23, 48, -54, 0};
    }

    private static void createRandomMask4_1() {
        byte[] bArr = new byte[2];
        bArr[0] = -16;
        _randomMask4_1 = bArr;
    }

    private static void createRandomMask4_2() {
        byte[] bArr = new byte[4];
        bArr[0] = -64;
        bArr[2] = -80;
        _randomMask4_2 = bArr;
    }

    private static void createRandomMask4_3() {
        _randomMask4_3 = new byte[]{-64, 0, -80, 0, 96, 0};
    }

    private static void createRandomMask4_4() {
        _randomMask4_4 = new byte[]{-64, 0, -96, 0, 48, 0, 80, 0};
    }

    private static void createRandomMask40_1() {
        _randomMask40_1 = new byte[]{-1, -1, -1, -1, -1, 0};
    }

    private static void createRandomMask40_10() {
        _randomMask40_10 = new byte[]{76, 19, 4, -63, 48, 0, 81, 20, 69, 17, 68, 0, -96, 104, 26, 6, -127, 0, 4, -63, 48, 76, 19, 0, 3, ByteCompanionObject.MIN_VALUE, -32, 56, 14, 0, -122, 33, -120, 98, 24, 0, 41, 10, 66, -112, -92, 0, 66, 80, -108, 37, 9, 0, -104, 38, 9, -126, 96, 0, 48, -116, 35, 8, -62, 0};
    }

    private static void createRandomMask40_11() {
        _randomMask40_11 = new byte[]{-58, 49, -116, 99, 24, 0, 35, -120, -30, 56, -114, 0, 26, 70, -111, -92, 105, 0, 36, -55, 50, 76, -109, 0, 113, 28, 71, 17, -60, 0, 14, 3, ByteCompanionObject.MIN_VALUE, -32, 56, 0, 51, 12, -61, 48, -52, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 67, 0, 69, 81, 84, 85, 21, 0, -120, -94, 40, -118, 34, 0, -32, 56, 14, 3, ByteCompanionObject.MIN_VALUE, 0};
    }

    private static void createRandomMask40_12() {
        _randomMask40_12 = new byte[]{14, 3, ByteCompanionObject.MIN_VALUE, -32, 56, 0, 51, 12, -61, 48, -52, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 67, 0, 69, 81, 84, 85, 21, 0, -120, -94, 40, -118, 34, 0, -32, 56, 14, 3, ByteCompanionObject.MIN_VALUE, 0, -58, 49, -116, 99, 24, 0, 35, -120, -30, 56, -114, 0, 26, 70, -111, -92, 105, 0, 36, -55, 50, 76, -109, 0, 113, 28, 71, 17, -60, 0, -11, -36, 79, 93, -60, 0};
    }

    private static void createRandomMask40_13() {
        _randomMask40_13 = new byte[]{14, 3, ByteCompanionObject.MIN_VALUE, -32, 56, 0, 51, 12, -61, 48, -52, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 67, 0, 69, 81, 84, 85, 21, 0, -120, -94, 40, -118, 34, 0, -32, 56, 14, 3, ByteCompanionObject.MIN_VALUE, 0, 70, 17, -124, 97, 24, 0, 51, 12, -61, 48, -52, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 67, 0, 12, 67, Tnaf.POW_2_WIDTH, -60, 49, 0, 40, -118, 34, -120, -94, 0, -108, 37, 9, 66, 80, 0, -63, 48, 76, 19, 4, 0};
    }

    private static void createRandomMask40_14() {
        _randomMask40_14 = new byte[]{70, 17, -124, 97, 24, 0, 51, 12, -61, 48, -52, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 67, 0, 12, 67, Tnaf.POW_2_WIDTH, -60, 49, 0, 40, -118, 34, -120, -94, 0, -108, 37, 9, 66, 80, 0, -63, 48, 76, 19, 4, 0, 14, 3, ByteCompanionObject.MIN_VALUE, -32, 56, 0, 51, 12, -61, 48, -52, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 67, 0, 69, 81, 84, 85, 21, 0, -120, -94, 40, -118, 34, 0, -32, 56, 14, 3, ByteCompanionObject.MIN_VALUE, 0, 86, 62, 37, 99, -30, 0};
    }

    private static void createRandomMask40_15() {
        _randomMask40_15 = new byte[]{70, 17, -124, 97, 24, 0, 51, 12, -61, 48, -52, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 67, 0, 12, 67, Tnaf.POW_2_WIDTH, -60, 49, 0, 40, -118, 34, -120, -94, 0, -108, 37, 9, 66, 80, 0, -63, 48, 76, 19, 4, 0, 44, 11, 2, -64, -80, 0, -127, -96, 104, 26, 6, 0, -96, 104, 26, 6, -127, 0, 5, 65, 80, 84, 21, 0, 24, -122, 33, -120, 98, 0, -62, 48, -116, 35, 8, 0, 34, -120, -94, 40, -118, 0, 80, 84, 21, 5, 65, 0};
    }

    private static void createRandomMask40_16() {
        _randomMask40_16 = new byte[]{44, 11, 2, -64, -80, 0, -127, -96, 104, 26, 6, 0, -96, 104, 26, 6, -127, 0, 5, 65, 80, 84, 21, 0, 24, -122, 33, -120, 98, 0, -62, 48, -116, 35, 8, 0, 34, -120, -94, 40, -118, 0, 80, 84, 21, 5, 65, 0, 70, 17, -124, 97, 24, 0, 51, 12, -61, 48, -52, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 67, 0, 12, 67, Tnaf.POW_2_WIDTH, -60, 49, 0, 40, -118, 34, -120, -94, 0, -108, 37, 9, 66, 80, 0, -63, 48, 76, 19, 4, 0, 40, 28, 98, -127, -58, 0};
    }

    private static void createRandomMask40_17() {
        _randomMask40_17 = new byte[]{44, 11, 2, -64, -80, 0, -127, -96, 104, 26, 6, 0, -96, 104, 26, 6, -127, 0, 5, 65, 80, 84, 21, 0, 24, -122, 33, -120, 98, 0, -62, 48, -116, 35, 8, 0, 34, -120, -94, 40, -118, 0, 80, 84, 21, 5, 65, 0, 78, 19, -124, -31, 56, 0, -29, 56, -50, 51, -116, 0, -127, -32, 120, 30, 7, 0, 33, 72, 82, 20, -123, 0, 82, -108, -91, 41, 74, 0, -76, 45, 11, 66, -48, 0, 38, -119, -94, 104, -102, 0, 88, 86, 21, -123, 97, 0, 25, -122, 97, -104, 102, 0};
    }

    private static void createRandomMask40_18() {
        _randomMask40_18 = new byte[]{78, 19, -124, -31, 56, 0, -29, 56, -50, 51, -116, 0, -127, -32, 120, 30, 7, 0, 33, 72, 82, 20, -123, 0, 82, -108, -91, 41, 74, 0, -76, 45, 11, 66, -48, 0, 38, -119, -94, 104, -102, 0, 88, 86, 21, -123, 97, 0, 25, -122, 97, -104, 102, 0, 44, 11, 2, -64, -80, 0, -127, -96, 104, 26, 6, 0, -96, 104, 26, 6, -127, 0, 5, 65, 80, 84, 21, 0, 24, -122, 33, -120, 98, 0, -62, 48, -116, 35, 8, 0, 34, -120, -94, 40, -118, 0, 80, 84, 21, 5, 65, 0, 33, 123, -14, 23, -65, 0};
    }

    private static void createRandomMask40_19() {
        _randomMask40_19 = new byte[]{78, 19, -124, -31, 56, 0, -29, 56, -50, 51, -116, 0, -127, -32, 120, 30, 7, 0, 33, 72, 82, 20, -123, 0, 82, -108, -91, 41, 74, 0, -76, 45, 11, 66, -48, 0, 38, -119, -94, 104, -102, 0, 88, 86, 21, -123, 97, 0, 25, -122, 97, -104, 102, 0, 76, 19, 4, -63, 48, 0, 81, 20, 69, 17, 68, 0, -96, 104, 26, 6, -127, 0, 4, -63, 48, 76, 19, 0, 3, ByteCompanionObject.MIN_VALUE, -32, 56, 14, 0, -122, 33, -120, 98, 24, 0, 41, 10, 66, -112, -92, 0, 66, 80, -108, 37, 9, 0, -104, 38, 9, -126, 96, 0, 48, -116, 35, 8, -62, 0};
    }

    private static void createRandomMask40_2() {
        _randomMask40_2 = new byte[]{-18, 59, -114, -29, -72, 0, -103, -26, 121, -98, 103, 0};
    }

    private static void createRandomMask40_20() {
        _randomMask40_20 = new byte[]{76, 19, 4, -63, 48, 0, 81, 20, 69, 17, 68, 0, -96, 104, 26, 6, -127, 0, 4, -63, 48, 76, 19, 0, 3, ByteCompanionObject.MIN_VALUE, -32, 56, 14, 0, -122, 33, -120, 98, 24, 0, 41, 10, 66, -112, -92, 0, 66, 80, -108, 37, 9, 0, -104, 38, 9, -126, 96, 0, 48, -116, 35, 8, -62, 0, 78, 19, -124, -31, 56, 0, -29, 56, -50, 51, -116, 0, -127, -32, 120, 30, 7, 0, 33, 72, 82, 20, -123, 0, 82, -108, -91, 41, 74, 0, -76, 45, 11, 66, -48, 0, 38, -119, -94, 104, -102, 0, 88, 86, 21, -123, 97, 0, 25, -122, 97, -104, 102, 0, -9, -115, -81, 120, -38, 0};
    }

    private static void createRandomMask40_21() {
        _randomMask40_21 = new byte[]{76, 19, 4, -63, 48, 0, 81, 20, 69, 17, 68, 0, -96, 104, 26, 6, -127, 0, 4, -63, 48, 76, 19, 0, 3, ByteCompanionObject.MIN_VALUE, -32, 56, 14, 0, -122, 33, -120, 98, 24, 0, 41, 10, 66, -112, -92, 0, 66, 80, -108, 37, 9, 0, -104, 38, 9, -126, 96, 0, 48, -116, 35, 8, -62, 0, -58, 49, -116, 99, 24, 0, 35, -120, -30, 56, -114, 0, 26, 70, -111, -92, 105, 0, 36, -55, 50, 76, -109, 0, 113, 28, 71, 17, -60, 0, 14, 3, ByteCompanionObject.MIN_VALUE, -32, 56, 0, 51, 12, -61, 48, -52, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 67, 0, 69, 81, 84, 85, 21, 0, -120, -94, 40, -118, 34, 0, -32, 56, 14, 3, ByteCompanionObject.MIN_VALUE, 0};
    }

    private static void createRandomMask40_22() {
        _randomMask40_22 = new byte[]{-58, 49, -116, 99, 24, 0, 35, -120, -30, 56, -114, 0, 26, 70, -111, -92, 105, 0, 36, -55, 50, 76, -109, 0, 113, 28, 71, 17, -60, 0, 14, 3, ByteCompanionObject.MIN_VALUE, -32, 56, 0, 51, 12, -61, 48, -52, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 67, 0, 69, 81, 84, 85, 21, 0, -120, -94, 40, -118, 34, 0, -32, 56, 14, 3, ByteCompanionObject.MIN_VALUE, 0, 76, 19, 4, -63, 48, 0, 81, 20, 69, 17, 68, 0, -96, 104, 26, 6, -127, 0, 4, -63, 48, 76, 19, 0, 3, ByteCompanionObject.MIN_VALUE, -32, 56, 14, 0, -122, 33, -120, 98, 24, 0, 41, 10, 66, -112, -92, 0, 66, 80, -108, 37, 9, 0, -104, 38, 9, -126, 96, 0, 48, -116, 35, 8, -62, 0, -119, -18, 31, 56, -54, 0};
    }

    private static void createRandomMask40_23() {
        _randomMask40_23 = new byte[]{-58, 49, -116, 99, 24, 0, 35, -120, -30, 56, -114, 0, 26, 70, -111, -92, 105, 0, 36, -55, 50, 76, -109, 0, 113, 28, 71, 17, -60, 0, 14, 3, ByteCompanionObject.MIN_VALUE, -32, 56, 0, 51, 12, -61, 48, -52, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 67, 0, 69, 81, 84, 85, 21, 0, -120, -94, 40, -118, 34, 0, -32, 56, 14, 3, ByteCompanionObject.MIN_VALUE, 0, 14, 3, ByteCompanionObject.MIN_VALUE, -32, 56, 0, 51, 12, -61, 48, -52, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 67, 0, 69, 81, 84, 85, 21, 0, -120, -94, 40, -118, 34, 0, -32, 56, 14, 3, ByteCompanionObject.MIN_VALUE, 0, -58, 49, -116, 99, 24, 0, 35, -120, -30, 56, -114, 0, 26, 70, -111, -92, 105, 0, 36, -55, 50, 76, -109, 0, 113, 28, 71, 17, -60, 0, -11, -36, 79, 93, -60, 0};
    }

    private static void createRandomMask40_24() {
        _randomMask40_24 = new byte[]{14, 3, ByteCompanionObject.MIN_VALUE, -32, 56, 0, 51, 12, -61, 48, -52, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 67, 0, 69, 81, 84, 85, 21, 0, -120, -94, 40, -118, 34, 0, -32, 56, 14, 3, ByteCompanionObject.MIN_VALUE, 0, -58, 49, -116, 99, 24, 0, 35, -120, -30, 56, -114, 0, 26, 70, -111, -92, 105, 0, 36, -55, 50, 76, -109, 0, 113, 28, 71, 17, -60, 0, -11, -36, 79, 93, -60, 0, -58, 49, -116, 99, 24, 0, 35, -120, -30, 56, -114, 0, 26, 70, -111, -92, 105, 0, 36, -55, 50, 76, -109, 0, 113, 28, 71, 17, -60, 0, 14, 3, ByteCompanionObject.MIN_VALUE, -32, 56, 0, 51, 12, -61, 48, -52, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 67, 0, 69, 81, 84, 85, 21, 0, -120, -94, 40, -118, 34, 0, -32, 56, 14, 3, ByteCompanionObject.MIN_VALUE, 0, 104, -34, -125, -87, -49, 0};
    }

    private static void createRandomMask40_25() {
        _randomMask40_25 = new byte[]{14, 3, ByteCompanionObject.MIN_VALUE, -32, 56, 0, 51, 12, -61, 48, -52, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 67, 0, 69, 81, 84, 85, 21, 0, -120, -94, 40, -118, 34, 0, -32, 56, 14, 3, ByteCompanionObject.MIN_VALUE, 0, -58, 49, -116, 99, 24, 0, 35, -120, -30, 56, -114, 0, 26, 70, -111, -92, 105, 0, 36, -55, 50, 76, -109, 0, 113, 28, 71, 17, -60, 0, -11, -36, 79, 93, -60, 0, 14, 3, ByteCompanionObject.MIN_VALUE, -32, 56, 0, 51, 12, -61, 48, -52, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 67, 0, 69, 81, 84, 85, 21, 0, -120, -94, 40, -118, 34, 0, -32, 56, 14, 3, ByteCompanionObject.MIN_VALUE, 0, 70, 17, -124, 97, 24, 0, 51, 12, -61, 48, -52, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 67, 0, 12, 67, Tnaf.POW_2_WIDTH, -60, 49, 0, 40, -118, 34, -120, -94, 0, -108, 37, 9, 66, 80, 0, -63, 48, 76, 19, 4, 0};
    }

    private static void createRandomMask40_26() {
        _randomMask40_26 = new byte[]{14, 3, ByteCompanionObject.MIN_VALUE, -32, 56, 0, 51, 12, -61, 48, -52, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 67, 0, 69, 81, 84, 85, 21, 0, -120, -94, 40, -118, 34, 0, -32, 56, 14, 3, ByteCompanionObject.MIN_VALUE, 0, 70, 17, -124, 97, 24, 0, 51, 12, -61, 48, -52, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 67, 0, 12, 67, Tnaf.POW_2_WIDTH, -60, 49, 0, 40, -118, 34, -120, -94, 0, -108, 37, 9, 66, 80, 0, -63, 48, 76, 19, 4, 0, 14, 3, ByteCompanionObject.MIN_VALUE, -32, 56, 0, 51, 12, -61, 48, -52, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 67, 0, 69, 81, 84, 85, 21, 0, -120, -94, 40, -118, 34, 0, -32, 56, 14, 3, ByteCompanionObject.MIN_VALUE, 0, -58, 49, -116, 99, 24, 0, 35, -120, -30, 56, -114, 0, 26, 70, -111, -92, 105, 0, 36, -55, 50, 76, -109, 0, 113, 28, 71, 17, -60, 0, -11, -36, 79, 93, -60, 0, 6, -114, -116, 26, -46, 0};
    }

    private static void createRandomMask40_27() {
        _randomMask40_27 = new byte[]{14, 3, ByteCompanionObject.MIN_VALUE, -32, 56, 0, 51, 12, -61, 48, -52, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 67, 0, 69, 81, 84, 85, 21, 0, -120, -94, 40, -118, 34, 0, -32, 56, 14, 3, ByteCompanionObject.MIN_VALUE, 0, 70, 17, -124, 97, 24, 0, 51, 12, -61, 48, -52, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 67, 0, 12, 67, Tnaf.POW_2_WIDTH, -60, 49, 0, 40, -118, 34, -120, -94, 0, -108, 37, 9, 66, 80, 0, -63, 48, 76, 19, 4, 0, 70, 17, -124, 97, 24, 0, 51, 12, -61, 48, -52, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 67, 0, 12, 67, Tnaf.POW_2_WIDTH, -60, 49, 0, 40, -118, 34, -120, -94, 0, -108, 37, 9, 66, 80, 0, -63, 48, 76, 19, 4, 0, 14, 3, ByteCompanionObject.MIN_VALUE, -32, 56, 0, 51, 12, -61, 48, -52, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 67, 0, 69, 81, 84, 85, 21, 0, -120, -94, 40, -118, 34, 0, -32, 56, 14, 3, ByteCompanionObject.MIN_VALUE, 0, 86, 62, 37, 99, -30, 0};
    }

    private static void createRandomMask40_28() {
        _randomMask40_28 = new byte[]{70, 17, -124, 97, 24, 0, 51, 12, -61, 48, -52, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 67, 0, 12, 67, Tnaf.POW_2_WIDTH, -60, 49, 0, 40, -118, 34, -120, -94, 0, -108, 37, 9, 66, 80, 0, -63, 48, 76, 19, 4, 0, 14, 3, ByteCompanionObject.MIN_VALUE, -32, 56, 0, 51, 12, -61, 48, -52, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 67, 0, 69, 81, 84, 85, 21, 0, -120, -94, 40, -118, 34, 0, -32, 56, 14, 3, ByteCompanionObject.MIN_VALUE, 0, 86, 62, 37, 99, -30, 0, 14, 3, ByteCompanionObject.MIN_VALUE, -32, 56, 0, 51, 12, -61, 48, -52, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 67, 0, 69, 81, 84, 85, 21, 0, -120, -94, 40, -118, 34, 0, -32, 56, 14, 3, ByteCompanionObject.MIN_VALUE, 0, 70, 17, -124, 97, 24, 0, 51, 12, -61, 48, -52, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 67, 0, 12, 67, Tnaf.POW_2_WIDTH, -60, 49, 0, 40, -118, 34, -120, -94, 0, -108, 37, 9, 66, 80, 0, -63, 48, 76, 19, 4, 0, 104, 14, -101, 82, -74, 0};
    }

    private static void createRandomMask40_29() {
        _randomMask40_29 = new byte[]{70, 17, -124, 97, 24, 0, 51, 12, -61, 48, -52, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 67, 0, 12, 67, Tnaf.POW_2_WIDTH, -60, 49, 0, 40, -118, 34, -120, -94, 0, -108, 37, 9, 66, 80, 0, -63, 48, 76, 19, 4, 0, 14, 3, ByteCompanionObject.MIN_VALUE, -32, 56, 0, 51, 12, -61, 48, -52, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 67, 0, 69, 81, 84, 85, 21, 0, -120, -94, 40, -118, 34, 0, -32, 56, 14, 3, ByteCompanionObject.MIN_VALUE, 0, 86, 62, 37, 99, -30, 0, 70, 17, -124, 97, 24, 0, 51, 12, -61, 48, -52, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 67, 0, 12, 67, Tnaf.POW_2_WIDTH, -60, 49, 0, 40, -118, 34, -120, -94, 0, -108, 37, 9, 66, 80, 0, -63, 48, 76, 19, 4, 0, 44, 11, 2, -64, -80, 0, -127, -96, 104, 26, 6, 0, -96, 104, 26, 6, -127, 0, 5, 65, 80, 84, 21, 0, 24, -122, 33, -120, 98, 0, -62, 48, -116, 35, 8, 0, 34, -120, -94, 40, -118, 0, 80, 84, 21, 5, 65, 0};
    }

    private static void createRandomMask40_3() {
        _randomMask40_3 = new byte[]{-50, 51, -116, -29, 56, 0, 85, -107, 101, 89, 86, 0, -79, 106, 59, 22, -93, 0};
    }

    private static void createRandomMask40_30() {
        _randomMask40_30 = new byte[]{70, 17, -124, 97, 24, 0, 51, 12, -61, 48, -52, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 67, 0, 12, 67, Tnaf.POW_2_WIDTH, -60, 49, 0, 40, -118, 34, -120, -94, 0, -108, 37, 9, 66, 80, 0, -63, 48, 76, 19, 4, 0, 44, 11, 2, -64, -80, 0, -127, -96, 104, 26, 6, 0, -96, 104, 26, 6, -127, 0, 5, 65, 80, 84, 21, 0, 24, -122, 33, -120, 98, 0, -62, 48, -116, 35, 8, 0, 34, -120, -94, 40, -118, 0, 80, 84, 21, 5, 65, 0, 70, 17, -124, 97, 24, 0, 51, 12, -61, 48, -52, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 67, 0, 12, 67, Tnaf.POW_2_WIDTH, -60, 49, 0, 40, -118, 34, -120, -94, 0, -108, 37, 9, 66, 80, 0, -63, 48, 76, 19, 4, 0, 14, 3, ByteCompanionObject.MIN_VALUE, -32, 56, 0, 51, 12, -61, 48, -52, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 67, 0, 69, 81, 84, 85, 21, 0, -120, -94, 40, -118, 34, 0, -32, 56, 14, 3, ByteCompanionObject.MIN_VALUE, 0, 86, 62, 37, 99, -30, 0, -31, 71, 4, 5, 71, 0};
    }

    private static void createRandomMask40_31() {
        _randomMask40_31 = new byte[]{70, 17, -124, 97, 24, 0, 51, 12, -61, 48, -52, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 67, 0, 12, 67, Tnaf.POW_2_WIDTH, -60, 49, 0, 40, -118, 34, -120, -94, 0, -108, 37, 9, 66, 80, 0, -63, 48, 76, 19, 4, 0, 44, 11, 2, -64, -80, 0, -127, -96, 104, 26, 6, 0, -96, 104, 26, 6, -127, 0, 5, 65, 80, 84, 21, 0, 24, -122, 33, -120, 98, 0, -62, 48, -116, 35, 8, 0, 34, -120, -94, 40, -118, 0, 80, 84, 21, 5, 65, 0, 44, 11, 2, -64, -80, 0, -127, -96, 104, 26, 6, 0, -96, 104, 26, 6, -127, 0, 5, 65, 80, 84, 21, 0, 24, -122, 33, -120, 98, 0, -62, 48, -116, 35, 8, 0, 34, -120, -94, 40, -118, 0, 80, 84, 21, 5, 65, 0, 70, 17, -124, 97, 24, 0, 51, 12, -61, 48, -52, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 67, 0, 12, 67, Tnaf.POW_2_WIDTH, -60, 49, 0, 40, -118, 34, -120, -94, 0, -108, 37, 9, 66, 80, 0, -63, 48, 76, 19, 4, 0, 40, 28, 98, -127, -58, 0};
    }

    private static void createRandomMask40_32() {
        _randomMask40_32 = new byte[]{44, 11, 2, -64, -80, 0, -127, -96, 104, 26, 6, 0, -96, 104, 26, 6, -127, 0, 5, 65, 80, 84, 21, 0, 24, -122, 33, -120, 98, 0, -62, 48, -116, 35, 8, 0, 34, -120, -94, 40, -118, 0, 80, 84, 21, 5, 65, 0, 70, 17, -124, 97, 24, 0, 51, 12, -61, 48, -52, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 67, 0, 12, 67, Tnaf.POW_2_WIDTH, -60, 49, 0, 40, -118, 34, -120, -94, 0, -108, 37, 9, 66, 80, 0, -63, 48, 76, 19, 4, 0, 40, 28, 98, -127, -58, 0, 70, 17, -124, 97, 24, 0, 51, 12, -61, 48, -52, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 67, 0, 12, 67, Tnaf.POW_2_WIDTH, -60, 49, 0, 40, -118, 34, -120, -94, 0, -108, 37, 9, 66, 80, 0, -63, 48, 76, 19, 4, 0, 44, 11, 2, -64, -80, 0, -127, -96, 104, 26, 6, 0, -96, 104, 26, 6, -127, 0, 5, 65, 80, 84, 21, 0, 24, -122, 33, -120, 98, 0, -62, 48, -116, 35, 8, 0, 34, -120, -94, 40, -118, 0, 80, 84, 21, 5, 65, 0, 3, 12, 70, Tnaf.POW_2_WIDTH, -59, 0};
    }

    private static void createRandomMask40_33() {
        _randomMask40_33 = new byte[]{44, 11, 2, -64, -80, 0, -127, -96, 104, 26, 6, 0, -96, 104, 26, 6, -127, 0, 5, 65, 80, 84, 21, 0, 24, -122, 33, -120, 98, 0, -62, 48, -116, 35, 8, 0, 34, -120, -94, 40, -118, 0, 80, 84, 21, 5, 65, 0, 70, 17, -124, 97, 24, 0, 51, 12, -61, 48, -52, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 67, 0, 12, 67, Tnaf.POW_2_WIDTH, -60, 49, 0, 40, -118, 34, -120, -94, 0, -108, 37, 9, 66, 80, 0, -63, 48, 76, 19, 4, 0, 40, 28, 98, -127, -58, 0, 44, 11, 2, -64, -80, 0, -127, -96, 104, 26, 6, 0, -96, 104, 26, 6, -127, 0, 5, 65, 80, 84, 21, 0, 24, -122, 33, -120, 98, 0, -62, 48, -116, 35, 8, 0, 34, -120, -94, 40, -118, 0, 80, 84, 21, 5, 65, 0, 78, 19, -124, -31, 56, 0, -29, 56, -50, 51, -116, 0, -127, -32, 120, 30, 7, 0, 33, 72, 82, 20, -123, 0, 82, -108, -91, 41, 74, 0, -76, 45, 11, 66, -48, 0, 38, -119, -94, 104, -102, 0, 88, 86, 21, -123, 97, 0, 25, -122, 97, -104, 102, 0};
    }

    private static void createRandomMask40_34() {
        _randomMask40_34 = new byte[]{44, 11, 2, -64, -80, 0, -127, -96, 104, 26, 6, 0, -96, 104, 26, 6, -127, 0, 5, 65, 80, 84, 21, 0, 24, -122, 33, -120, 98, 0, -62, 48, -116, 35, 8, 0, 34, -120, -94, 40, -118, 0, 80, 84, 21, 5, 65, 0, 78, 19, -124, -31, 56, 0, -29, 56, -50, 51, -116, 0, -127, -32, 120, 30, 7, 0, 33, 72, 82, 20, -123, 0, 82, -108, -91, 41, 74, 0, -76, 45, 11, 66, -48, 0, 38, -119, -94, 104, -102, 0, 88, 86, 21, -123, 97, 0, 25, -122, 97, -104, 102, 0, 44, 11, 2, -64, -80, 0, -127, -96, 104, 26, 6, 0, -96, 104, 26, 6, -127, 0, 5, 65, 80, 84, 21, 0, 24, -122, 33, -120, 98, 0, -62, 48, -116, 35, 8, 0, 34, -120, -94, 40, -118, 0, 80, 84, 21, 5, 65, 0, 70, 17, -124, 97, 24, 0, 51, 12, -61, 48, -52, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 67, 0, 12, 67, Tnaf.POW_2_WIDTH, -60, 49, 0, 40, -118, 34, -120, -94, 0, -108, 37, 9, 66, 80, 0, -63, 48, 76, 19, 4, 0, 40, 28, 98, -127, -58, 0, -121, 60, 8, 25, 49, 0};
    }

    private static void createRandomMask40_35() {
        _randomMask40_35 = new byte[]{44, 11, 2, -64, -80, 0, -127, -96, 104, 26, 6, 0, -96, 104, 26, 6, -127, 0, 5, 65, 80, 84, 21, 0, 24, -122, 33, -120, 98, 0, -62, 48, -116, 35, 8, 0, 34, -120, -94, 40, -118, 0, 80, 84, 21, 5, 65, 0, 78, 19, -124, -31, 56, 0, -29, 56, -50, 51, -116, 0, -127, -32, 120, 30, 7, 0, 33, 72, 82, 20, -123, 0, 82, -108, -91, 41, 74, 0, -76, 45, 11, 66, -48, 0, 38, -119, -94, 104, -102, 0, 88, 86, 21, -123, 97, 0, 25, -122, 97, -104, 102, 0, 78, 19, -124, -31, 56, 0, -29, 56, -50, 51, -116, 0, -127, -32, 120, 30, 7, 0, 33, 72, 82, 20, -123, 0, 82, -108, -91, 41, 74, 0, -76, 45, 11, 66, -48, 0, 38, -119, -94, 104, -102, 0, 88, 86, 21, -123, 97, 0, 25, -122, 97, -104, 102, 0, 44, 11, 2, -64, -80, 0, -127, -96, 104, 26, 6, 0, -96, 104, 26, 6, -127, 0, 5, 65, 80, 84, 21, 0, 24, -122, 33, -120, 98, 0, -62, 48, -116, 35, 8, 0, 34, -120, -94, 40, -118, 0, 80, 84, 21, 5, 65, 0, 33, 123, -14, 23, -65, 0};
    }

    private static void createRandomMask40_36() {
        _randomMask40_36 = new byte[]{78, 19, -124, -31, 56, 0, -29, 56, -50, 51, -116, 0, -127, -32, 120, 30, 7, 0, 33, 72, 82, 20, -123, 0, 82, -108, -91, 41, 74, 0, -76, 45, 11, 66, -48, 0, 38, -119, -94, 104, -102, 0, 88, 86, 21, -123, 97, 0, 25, -122, 97, -104, 102, 0, 44, 11, 2, -64, -80, 0, -127, -96, 104, 26, 6, 0, -96, 104, 26, 6, -127, 0, 5, 65, 80, 84, 21, 0, 24, -122, 33, -120, 98, 0, -62, 48, -116, 35, 8, 0, 34, -120, -94, 40, -118, 0, 80, 84, 21, 5, 65, 0, 33, 123, -14, 23, -65, 0, 44, 11, 2, -64, -80, 0, -127, -96, 104, 26, 6, 0, -96, 104, 26, 6, -127, 0, 5, 65, 80, 84, 21, 0, 24, -122, 33, -120, 98, 0, -62, 48, -116, 35, 8, 0, 34, -120, -94, 40, -118, 0, 80, 84, 21, 5, 65, 0, 78, 19, -124, -31, 56, 0, -29, 56, -50, 51, -116, 0, -127, -32, 120, 30, 7, 0, 33, 72, 82, 20, -123, 0, 82, -108, -91, 41, 74, 0, -76, 45, 11, 66, -48, 0, 38, -119, -94, 104, -102, 0, 88, 86, 21, -123, 97, 0, 25, -122, 97, -104, 102, 0, 30, -71, 61, 37, -52, 0};
    }

    private static void createRandomMask40_37() {
        _randomMask40_37 = new byte[]{78, 19, -124, -31, 56, 0, -29, 56, -50, 51, -116, 0, -127, -32, 120, 30, 7, 0, 33, 72, 82, 20, -123, 0, 82, -108, -91, 41, 74, 0, -76, 45, 11, 66, -48, 0, 38, -119, -94, 104, -102, 0, 88, 86, 21, -123, 97, 0, 25, -122, 97, -104, 102, 0, 44, 11, 2, -64, -80, 0, -127, -96, 104, 26, 6, 0, -96, 104, 26, 6, -127, 0, 5, 65, 80, 84, 21, 0, 24, -122, 33, -120, 98, 0, -62, 48, -116, 35, 8, 0, 34, -120, -94, 40, -118, 0, 80, 84, 21, 5, 65, 0, 33, 123, -14, 23, -65, 0, 78, 19, -124, -31, 56, 0, -29, 56, -50, 51, -116, 0, -127, -32, 120, 30, 7, 0, 33, 72, 82, 20, -123, 0, 82, -108, -91, 41, 74, 0, -76, 45, 11, 66, -48, 0, 38, -119, -94, 104, -102, 0, 88, 86, 21, -123, 97, 0, 25, -122, 97, -104, 102, 0, 76, 19, 4, -63, 48, 0, 81, 20, 69, 17, 68, 0, -96, 104, 26, 6, -127, 0, 4, -63, 48, 76, 19, 0, 3, ByteCompanionObject.MIN_VALUE, -32, 56, 14, 0, -122, 33, -120, 98, 24, 0, 41, 10, 66, -112, -92, 0, 66, 80, -108, 37, 9, 0, -104, 38, 9, -126, 96, 0, 48, -116, 35, 8, -62, 0};
    }

    private static void createRandomMask40_38() {
        _randomMask40_38 = new byte[]{78, 19, -124, -31, 56, 0, -29, 56, -50, 51, -116, 0, -127, -32, 120, 30, 7, 0, 33, 72, 82, 20, -123, 0, 82, -108, -91, 41, 74, 0, -76, 45, 11, 66, -48, 0, 38, -119, -94, 104, -102, 0, 88, 86, 21, -123, 97, 0, 25, -122, 97, -104, 102, 0, 76, 19, 4, -63, 48, 0, 81, 20, 69, 17, 68, 0, -96, 104, 26, 6, -127, 0, 4, -63, 48, 76, 19, 0, 3, ByteCompanionObject.MIN_VALUE, -32, 56, 14, 0, -122, 33, -120, 98, 24, 0, 41, 10, 66, -112, -92, 0, 66, 80, -108, 37, 9, 0, -104, 38, 9, -126, 96, 0, 48, -116, 35, 8, -62, 0, 78, 19, -124, -31, 56, 0, -29, 56, -50, 51, -116, 0, -127, -32, 120, 30, 7, 0, 33, 72, 82, 20, -123, 0, 82, -108, -91, 41, 74, 0, -76, 45, 11, 66, -48, 0, 38, -119, -94, 104, -102, 0, 88, 86, 21, -123, 97, 0, 25, -122, 97, -104, 102, 0, 44, 11, 2, -64, -80, 0, -127, -96, 104, 26, 6, 0, -96, 104, 26, 6, -127, 0, 5, 65, 80, 84, 21, 0, 24, -122, 33, -120, 98, 0, -62, 48, -116, 35, 8, 0, 34, -120, -94, 40, -118, 0, 80, 84, 21, 5, 65, 0, 33, 123, -14, 23, -65, 0, -22, -86, 32, -94, 27, 0};
    }

    private static void createRandomMask40_39() {
        _randomMask40_39 = new byte[]{78, 19, -124, -31, 56, 0, -29, 56, -50, 51, -116, 0, -127, -32, 120, 30, 7, 0, 33, 72, 82, 20, -123, 0, 82, -108, -91, 41, 74, 0, -76, 45, 11, 66, -48, 0, 38, -119, -94, 104, -102, 0, 88, 86, 21, -123, 97, 0, 25, -122, 97, -104, 102, 0, 76, 19, 4, -63, 48, 0, 81, 20, 69, 17, 68, 0, -96, 104, 26, 6, -127, 0, 4, -63, 48, 76, 19, 0, 3, ByteCompanionObject.MIN_VALUE, -32, 56, 14, 0, -122, 33, -120, 98, 24, 0, 41, 10, 66, -112, -92, 0, 66, 80, -108, 37, 9, 0, -104, 38, 9, -126, 96, 0, 48, -116, 35, 8, -62, 0, 76, 19, 4, -63, 48, 0, 81, 20, 69, 17, 68, 0, -96, 104, 26, 6, -127, 0, 4, -63, 48, 76, 19, 0, 3, ByteCompanionObject.MIN_VALUE, -32, 56, 14, 0, -122, 33, -120, 98, 24, 0, 41, 10, 66, -112, -92, 0, 66, 80, -108, 37, 9, 0, -104, 38, 9, -126, 96, 0, 48, -116, 35, 8, -62, 0, 78, 19, -124, -31, 56, 0, -29, 56, -50, 51, -116, 0, -127, -32, 120, 30, 7, 0, 33, 72, 82, 20, -123, 0, 82, -108, -91, 41, 74, 0, -76, 45, 11, 66, -48, 0, 38, -119, -94, 104, -102, 0, 88, 86, 21, -123, 97, 0, 25, -122, 97, -104, 102, 0, -9, -115, -81, 120, -38, 0};
    }

    private static void createRandomMask40_4() {
        _randomMask40_4 = new byte[]{-26, 57, -114, 99, -104, 0, 51, -116, -29, 56, -50, 0, -104, -26, 57, -114, 99, 0, 45, 75, 82, -44, -75, 0};
    }

    private static void createRandomMask40_40() {
        _randomMask40_40 = new byte[]{76, 19, 4, -63, 48, 0, 81, 20, 69, 17, 68, 0, -96, 104, 26, 6, -127, 0, 4, -63, 48, 76, 19, 0, 3, ByteCompanionObject.MIN_VALUE, -32, 56, 14, 0, -122, 33, -120, 98, 24, 0, 41, 10, 66, -112, -92, 0, 66, 80, -108, 37, 9, 0, -104, 38, 9, -126, 96, 0, 48, -116, 35, 8, -62, 0, 78, 19, -124, -31, 56, 0, -29, 56, -50, 51, -116, 0, -127, -32, 120, 30, 7, 0, 33, 72, 82, 20, -123, 0, 82, -108, -91, 41, 74, 0, -76, 45, 11, 66, -48, 0, 38, -119, -94, 104, -102, 0, 88, 86, 21, -123, 97, 0, 25, -122, 97, -104, 102, 0, -9, -115, -81, 120, -38, 0, 78, 19, -124, -31, 56, 0, -29, 56, -50, 51, -116, 0, -127, -32, 120, 30, 7, 0, 33, 72, 82, 20, -123, 0, 82, -108, -91, 41, 74, 0, -76, 45, 11, 66, -48, 0, 38, -119, -94, 104, -102, 0, 88, 86, 21, -123, 97, 0, 25, -122, 97, -104, 102, 0, 76, 19, 4, -63, 48, 0, 81, 20, 69, 17, 68, 0, -96, 104, 26, 6, -127, 0, 4, -63, 48, 76, 19, 0, 3, ByteCompanionObject.MIN_VALUE, -32, 56, 14, 0, -122, 33, -120, 98, 24, 0, 41, 10, 66, -112, -92, 0, 66, 80, -108, 37, 9, 0, -104, 38, 9, -126, 96, 0, 48, -116, 35, 8, -62, 0, -90, -13, -85, 27, -121, 0};
    }

    private static void createRandomMask40_5() {
        _randomMask40_5 = new byte[]{-50, 51, -116, -29, 56, 0, 99, -104, -26, 57, -114, 0, -104, -27, 57, -114, 83, 0, 43, 83, 82, -75, 53, 0, -76, 92, -85, 69, -54, 0};
    }

    private static void createRandomMask40_6() {
        _randomMask40_6 = new byte[]{76, 27, 4, -63, -80, 0, 81, 52, 69, 19, 68, 0, 32, -24, 50, 14, -125, 0, -123, 65, 88, 84, 21, 0, 6, -122, -96, 104, 106, 0, -102, 33, -119, -94, 24, 0};
    }

    private static void createRandomMask40_7() {
        _randomMask40_7 = new byte[]{78, 17, -124, -31, 24, 0, 51, 44, 3, 50, -64, 0, Tnaf.POW_2_WIDTH, 14, -79, 0, -21, 0, -127, 81, 88, 21, 21, 0, 36, -60, -94, 76, 74, 0, -44, 35, 13, 66, 48, 0, 12, -94, 96, -54, 38, 0};
    }

    private static void createRandomMask40_8() {
        _randomMask40_8 = new byte[]{39, 9, -62, 112, -100, 0, -119, -94, 104, -102, 38, 0, -48, 116, 29, 7, 65, 0, 36, -55, 50, 76, -109, 0, -30, -112, -82, 41, 10, 0, -58, 49, -116, 99, 24, 0, 49, -116, 99, 24, -58, 0, 24, -58, 49, -116, 99, 0};
    }

    private static void createRandomMask40_9() {
        _randomMask40_9 = new byte[]{78, 19, -124, -31, 56, 0, 98, 56, -58, 35, -116, 0, -127, -32, 120, 30, 7, 0, -31, 72, 94, 20, -123, 0, 19, -108, -95, 57, 74, 0, -76, 45, 11, 66, -48, 0, 38, -119, -94, 104, -102, 0, 88, 86, 21, -123, 97, 0, 73, -122, 84, -104, 101, 0};
    }

    private static void createRandomMask41_1() {
        _randomMask41_1 = new byte[]{-1, -1, -1, -1, -1, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask41_10() {
        _randomMask41_10 = new byte[]{76, 19, 4, -63, -111, 0, 81, 20, 69, 17, 69, 0, -96, 104, 26, 6, -92, 0, 4, -63, 48, 76, 19, 0, 3, ByteCompanionObject.MIN_VALUE, -32, 59, 64, 0, -122, 33, -120, 98, 9, 0, 41, 10, 66, -112, -124, ByteCompanionObject.MIN_VALUE, 66, 80, -108, 36, 48, ByteCompanionObject.MIN_VALUE, -104, 38, 9, -127, 40, 0, 48, -116, 35, 8, 74, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask41_11() {
        _randomMask41_11 = new byte[]{-58, 49, -116, 98, 26, 0, 35, -120, -30, 56, -116, ByteCompanionObject.MIN_VALUE, 26, 70, -111, -92, 88, ByteCompanionObject.MIN_VALUE, 36, -55, 50, 77, 48, ByteCompanionObject.MIN_VALUE, 113, 28, 71, 17, 7, 0, 14, 3, ByteCompanionObject.MIN_VALUE, -31, -111, 0, 51, 12, -61, 49, 69, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 50, ByteCompanionObject.MIN_VALUE, 69, 81, 84, 86, -124, ByteCompanionObject.MIN_VALUE, -120, -94, 40, -120, 74, ByteCompanionObject.MIN_VALUE, -32, 56, 14, 2, 41, 0};
    }

    private static void createRandomMask41_12() {
        _randomMask41_12 = new byte[]{14, 3, ByteCompanionObject.MIN_VALUE, -31, -111, 0, 51, 12, -61, 49, 69, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 50, ByteCompanionObject.MIN_VALUE, 69, 81, 84, 86, -124, ByteCompanionObject.MIN_VALUE, -120, -94, 40, -120, 74, ByteCompanionObject.MIN_VALUE, -32, 56, 14, 2, 41, 0, -58, 49, -116, 98, 26, 0, 35, -120, -30, 56, -116, ByteCompanionObject.MIN_VALUE, 26, 70, -111, -92, 88, ByteCompanionObject.MIN_VALUE, 36, -55, 50, 77, 48, ByteCompanionObject.MIN_VALUE, 113, 28, 71, 17, 7, 0, -11, -36, 74, 6, 81, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask41_13() {
        _randomMask41_13 = new byte[]{14, 3, ByteCompanionObject.MIN_VALUE, -31, -111, 0, 51, 12, -61, 49, 69, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 50, ByteCompanionObject.MIN_VALUE, 69, 81, 84, 86, -124, ByteCompanionObject.MIN_VALUE, -120, -94, 40, -120, 74, ByteCompanionObject.MIN_VALUE, -32, 56, 14, 2, 41, 0, 70, 17, -124, 97, 25, 0, 51, 12, -61, 48, -52, ByteCompanionObject.MIN_VALUE, Tnaf.POW_2_WIDTH, -60, 49, 14, 70, 0, 12, 67, Tnaf.POW_2_WIDTH, -58, -112, ByteCompanionObject.MIN_VALUE, 40, -118, 34, -119, 66, ByteCompanionObject.MIN_VALUE, -108, 37, 9, 66, 19, 0, -63, 48, 76, Tnaf.POW_2_WIDTH, 37, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask41_14() {
        _randomMask41_14 = new byte[]{70, 17, -124, 97, 25, 0, 51, 12, -61, 48, -52, ByteCompanionObject.MIN_VALUE, Tnaf.POW_2_WIDTH, -60, 49, 14, 70, 0, 12, 67, Tnaf.POW_2_WIDTH, -58, -112, ByteCompanionObject.MIN_VALUE, 40, -118, 34, -119, 66, ByteCompanionObject.MIN_VALUE, -108, 37, 9, 66, 19, 0, -63, 48, 76, Tnaf.POW_2_WIDTH, 37, ByteCompanionObject.MIN_VALUE, 14, 3, ByteCompanionObject.MIN_VALUE, -31, -111, 0, 51, 12, -61, 49, 69, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 50, ByteCompanionObject.MIN_VALUE, 69, 81, 84, 86, -124, ByteCompanionObject.MIN_VALUE, -120, -94, 40, -120, 74, ByteCompanionObject.MIN_VALUE, -32, 56, 14, 2, 41, 0, 86, 62, 36, -35, 12, 0};
    }

    private static void createRandomMask41_15() {
        _randomMask41_15 = new byte[]{70, 17, -124, 97, 25, 0, 51, 12, -61, 48, -52, ByteCompanionObject.MIN_VALUE, Tnaf.POW_2_WIDTH, -60, 49, 14, 70, 0, 12, 67, Tnaf.POW_2_WIDTH, -58, -112, ByteCompanionObject.MIN_VALUE, 40, -118, 34, -119, 66, ByteCompanionObject.MIN_VALUE, -108, 37, 9, 66, 19, 0, -63, 48, 76, Tnaf.POW_2_WIDTH, 37, ByteCompanionObject.MIN_VALUE, 44, 11, 2, -64, 50, 0, -127, -96, 104, 26, 1, ByteCompanionObject.MIN_VALUE, -96, 104, 26, 6, -126, 0, 5, 65, 80, 84, 21, 0, 24, -122, 33, -119, 12, 0, -62, 48, -116, 32, 104, 0, 34, -120, -94, 41, ByteCompanionObject.MIN_VALUE, ByteCompanionObject.MIN_VALUE, 80, 84, 21, 4, 80, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask41_16() {
        _randomMask41_16 = new byte[]{44, 11, 2, -64, 50, 0, -127, -96, 104, 26, 1, ByteCompanionObject.MIN_VALUE, -96, 104, 26, 6, -126, 0, 5, 65, 80, 84, 21, 0, 24, -122, 33, -119, 12, 0, -62, 48, -116, 32, 104, 0, 34, -120, -94, 41, ByteCompanionObject.MIN_VALUE, ByteCompanionObject.MIN_VALUE, 80, 84, 21, 4, 80, ByteCompanionObject.MIN_VALUE, 70, 17, -124, 97, 25, 0, 51, 12, -61, 48, -52, ByteCompanionObject.MIN_VALUE, Tnaf.POW_2_WIDTH, -60, 49, 14, 70, 0, 12, 67, Tnaf.POW_2_WIDTH, -58, -112, ByteCompanionObject.MIN_VALUE, 40, -118, 34, -119, 66, ByteCompanionObject.MIN_VALUE, -108, 37, 9, 66, 19, 0, -63, 48, 76, Tnaf.POW_2_WIDTH, 37, ByteCompanionObject.MIN_VALUE, 40, 28, 99, -65, 83, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask41_17() {
        _randomMask41_17 = new byte[]{44, 11, 2, -64, 50, 0, -127, -96, 104, 26, 1, ByteCompanionObject.MIN_VALUE, -96, 104, 26, 6, -126, 0, 5, 65, 80, 84, 21, 0, 24, -122, 33, -119, 12, 0, -62, 48, -116, 32, 104, 0, 34, -120, -94, 41, ByteCompanionObject.MIN_VALUE, ByteCompanionObject.MIN_VALUE, 80, 84, 21, 4, 80, ByteCompanionObject.MIN_VALUE, 78, 19, -124, -31, 25, 0, -29, 56, -50, 49, -119, ByteCompanionObject.MIN_VALUE, -127, -32, 120, 30, 48, 0, 33, 72, 82, 20, 5, ByteCompanionObject.MIN_VALUE, 82, -108, -91, 40, 30, 0, -76, 45, 11, 66, -126, 0, 38, -119, -94, 104, 98, ByteCompanionObject.MIN_VALUE, 88, 86, 21, -122, 68, 0, 25, -122, 97, -103, -32, 0};
    }

    private static void createRandomMask41_18() {
        _randomMask41_18 = new byte[]{78, 19, -124, -31, 25, 0, -29, 56, -50, 49, -119, ByteCompanionObject.MIN_VALUE, -127, -32, 120, 30, 48, 0, 33, 72, 82, 20, 5, ByteCompanionObject.MIN_VALUE, 82, -108, -91, 40, 30, 0, -76, 45, 11, 66, -126, 0, 38, -119, -94, 104, 98, ByteCompanionObject.MIN_VALUE, 88, 86, 21, -122, 68, 0, 25, -122, 97, -103, -32, 0, 44, 11, 2, -64, 50, 0, -127, -96, 104, 26, 1, ByteCompanionObject.MIN_VALUE, -96, 104, 26, 6, -126, 0, 5, 65, 80, 84, 21, 0, 24, -122, 33, -119, 12, 0, -62, 48, -116, 32, 104, 0, 34, -120, -94, 41, ByteCompanionObject.MIN_VALUE, ByteCompanionObject.MIN_VALUE, 80, 84, 21, 4, 80, ByteCompanionObject.MIN_VALUE, 33, 123, -11, -91, 101, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask41_19() {
        _randomMask41_19 = new byte[]{78, 19, -124, -31, 25, 0, -29, 56, -50, 49, -119, ByteCompanionObject.MIN_VALUE, -127, -32, 120, 30, 48, 0, 33, 72, 82, 20, 5, ByteCompanionObject.MIN_VALUE, 82, -108, -91, 40, 30, 0, -76, 45, 11, 66, -126, 0, 38, -119, -94, 104, 98, ByteCompanionObject.MIN_VALUE, 88, 86, 21, -122, 68, 0, 25, -122, 97, -103, -32, 0, 76, 19, 4, -63, -111, 0, 81, 20, 69, 17, 69, 0, -96, 104, 26, 6, -92, 0, 4, -63, 48, 76, 19, 0, 3, ByteCompanionObject.MIN_VALUE, -32, 59, 64, 0, -122, 33, -120, 98, 9, 0, 41, 10, 66, -112, -124, ByteCompanionObject.MIN_VALUE, 66, 80, -108, 36, 48, ByteCompanionObject.MIN_VALUE, -104, 38, 9, -127, 40, 0, 48, -116, 35, 8, 74, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask41_2() {
        _randomMask41_2 = new byte[]{-18, 59, -114, -29, -77, 0, -103, -26, 121, -98, 110, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask41_20() {
        _randomMask41_20 = new byte[]{76, 19, 4, -63, -111, 0, 81, 20, 69, 17, 69, 0, -96, 104, 26, 6, -92, 0, 4, -63, 48, 76, 19, 0, 3, ByteCompanionObject.MIN_VALUE, -32, 59, 64, 0, -122, 33, -120, 98, 9, 0, 41, 10, 66, -112, -124, ByteCompanionObject.MIN_VALUE, 66, 80, -108, 36, 48, ByteCompanionObject.MIN_VALUE, -104, 38, 9, -127, 40, 0, 48, -116, 35, 8, 74, ByteCompanionObject.MIN_VALUE, 78, 19, -124, -31, 25, 0, -29, 56, -50, 49, -119, ByteCompanionObject.MIN_VALUE, -127, -32, 120, 30, 48, 0, 33, 72, 82, 20, 5, ByteCompanionObject.MIN_VALUE, 82, -108, -91, 40, 30, 0, -76, 45, 11, 66, -126, 0, 38, -119, -94, 104, 98, ByteCompanionObject.MIN_VALUE, 88, 86, 21, -122, 68, 0, 25, -122, 97, -103, -32, 0, -9, -115, -94, -96, 51, 0};
    }

    private static void createRandomMask41_21() {
        _randomMask41_21 = new byte[]{76, 19, 4, -63, -111, 0, 81, 20, 69, 17, 69, 0, -96, 104, 26, 6, -92, 0, 4, -63, 48, 76, 19, 0, 3, ByteCompanionObject.MIN_VALUE, -32, 59, 64, 0, -122, 33, -120, 98, 9, 0, 41, 10, 66, -112, -124, ByteCompanionObject.MIN_VALUE, 66, 80, -108, 36, 48, ByteCompanionObject.MIN_VALUE, -104, 38, 9, -127, 40, 0, 48, -116, 35, 8, 74, ByteCompanionObject.MIN_VALUE, -58, 49, -116, 98, 26, 0, 35, -120, -30, 56, -116, ByteCompanionObject.MIN_VALUE, 26, 70, -111, -92, 88, ByteCompanionObject.MIN_VALUE, 36, -55, 50, 77, 48, ByteCompanionObject.MIN_VALUE, 113, 28, 71, 17, 7, 0, 14, 3, ByteCompanionObject.MIN_VALUE, -31, -111, 0, 51, 12, -61, 49, 69, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 50, ByteCompanionObject.MIN_VALUE, 69, 81, 84, 86, -124, ByteCompanionObject.MIN_VALUE, -120, -94, 40, -120, 74, ByteCompanionObject.MIN_VALUE, -32, 56, 14, 2, 41, 0};
    }

    private static void createRandomMask41_22() {
        _randomMask41_22 = new byte[]{-58, 49, -116, 98, 26, 0, 35, -120, -30, 56, -116, ByteCompanionObject.MIN_VALUE, 26, 70, -111, -92, 88, ByteCompanionObject.MIN_VALUE, 36, -55, 50, 77, 48, ByteCompanionObject.MIN_VALUE, 113, 28, 71, 17, 7, 0, 14, 3, ByteCompanionObject.MIN_VALUE, -31, -111, 0, 51, 12, -61, 49, 69, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 50, ByteCompanionObject.MIN_VALUE, 69, 81, 84, 86, -124, ByteCompanionObject.MIN_VALUE, -120, -94, 40, -120, 74, ByteCompanionObject.MIN_VALUE, -32, 56, 14, 2, 41, 0, 76, 19, 4, -63, -111, 0, 81, 20, 69, 17, 69, 0, -96, 104, 26, 6, -92, 0, 4, -63, 48, 76, 19, 0, 3, ByteCompanionObject.MIN_VALUE, -32, 59, 64, 0, -122, 33, -120, 98, 9, 0, 41, 10, 66, -112, -124, ByteCompanionObject.MIN_VALUE, 66, 80, -108, 36, 48, ByteCompanionObject.MIN_VALUE, -104, 38, 9, -127, 40, 0, 48, -116, 35, 8, 74, ByteCompanionObject.MIN_VALUE, 51, 9, 110, 73, 107, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask41_23() {
        _randomMask41_23 = new byte[]{-58, 49, -116, 98, 26, 0, 35, -120, -30, 56, -116, ByteCompanionObject.MIN_VALUE, 26, 70, -111, -92, 88, ByteCompanionObject.MIN_VALUE, 36, -55, 50, 77, 48, ByteCompanionObject.MIN_VALUE, 113, 28, 71, 17, 7, 0, 14, 3, ByteCompanionObject.MIN_VALUE, -31, -111, 0, 51, 12, -61, 49, 69, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 50, ByteCompanionObject.MIN_VALUE, 69, 81, 84, 86, -124, ByteCompanionObject.MIN_VALUE, -120, -94, 40, -120, 74, ByteCompanionObject.MIN_VALUE, -32, 56, 14, 2, 41, 0, 14, 3, ByteCompanionObject.MIN_VALUE, -31, -111, 0, 51, 12, -61, 49, 69, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 50, ByteCompanionObject.MIN_VALUE, 69, 81, 84, 86, -124, ByteCompanionObject.MIN_VALUE, -120, -94, 40, -120, 74, ByteCompanionObject.MIN_VALUE, -32, 56, 14, 2, 41, 0, -58, 49, -116, 98, 26, 0, 35, -120, -30, 56, -116, ByteCompanionObject.MIN_VALUE, 26, 70, -111, -92, 88, ByteCompanionObject.MIN_VALUE, 36, -55, 50, 77, 48, ByteCompanionObject.MIN_VALUE, 113, 28, 71, 17, 7, 0, -11, -36, 74, 6, 81, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask41_24() {
        _randomMask41_24 = new byte[]{14, 3, ByteCompanionObject.MIN_VALUE, -31, -111, 0, 51, 12, -61, 49, 69, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 50, ByteCompanionObject.MIN_VALUE, 69, 81, 84, 86, -124, ByteCompanionObject.MIN_VALUE, -120, -94, 40, -120, 74, ByteCompanionObject.MIN_VALUE, -32, 56, 14, 2, 41, 0, -58, 49, -116, 98, 26, 0, 35, -120, -30, 56, -116, ByteCompanionObject.MIN_VALUE, 26, 70, -111, -92, 88, ByteCompanionObject.MIN_VALUE, 36, -55, 50, 77, 48, ByteCompanionObject.MIN_VALUE, 113, 28, 71, 17, 7, 0, -11, -36, 74, 6, 81, ByteCompanionObject.MIN_VALUE, -58, 49, -116, 98, 26, 0, 35, -120, -30, 56, -116, ByteCompanionObject.MIN_VALUE, 26, 70, -111, -92, 88, ByteCompanionObject.MIN_VALUE, 36, -55, 50, 77, 48, ByteCompanionObject.MIN_VALUE, 113, 28, 71, 17, 7, 0, 14, 3, ByteCompanionObject.MIN_VALUE, -31, -111, 0, 51, 12, -61, 49, 69, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 50, ByteCompanionObject.MIN_VALUE, 69, 81, 84, 86, -124, ByteCompanionObject.MIN_VALUE, -120, -94, 40, -120, 74, ByteCompanionObject.MIN_VALUE, -32, 56, 14, 2, 41, 0, 69, -90, -17, -55, -61, 0};
    }

    private static void createRandomMask41_25() {
        _randomMask41_25 = new byte[]{14, 3, ByteCompanionObject.MIN_VALUE, -31, -111, 0, 51, 12, -61, 49, 69, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 50, ByteCompanionObject.MIN_VALUE, 69, 81, 84, 86, -124, ByteCompanionObject.MIN_VALUE, -120, -94, 40, -120, 74, ByteCompanionObject.MIN_VALUE, -32, 56, 14, 2, 41, 0, -58, 49, -116, 98, 26, 0, 35, -120, -30, 56, -116, ByteCompanionObject.MIN_VALUE, 26, 70, -111, -92, 88, ByteCompanionObject.MIN_VALUE, 36, -55, 50, 77, 48, ByteCompanionObject.MIN_VALUE, 113, 28, 71, 17, 7, 0, -11, -36, 74, 6, 81, ByteCompanionObject.MIN_VALUE, 14, 3, ByteCompanionObject.MIN_VALUE, -31, -111, 0, 51, 12, -61, 49, 69, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 50, ByteCompanionObject.MIN_VALUE, 69, 81, 84, 86, -124, ByteCompanionObject.MIN_VALUE, -120, -94, 40, -120, 74, ByteCompanionObject.MIN_VALUE, -32, 56, 14, 2, 41, 0, 70, 17, -124, 97, 25, 0, 51, 12, -61, 48, -52, ByteCompanionObject.MIN_VALUE, Tnaf.POW_2_WIDTH, -60, 49, 14, 70, 0, 12, 67, Tnaf.POW_2_WIDTH, -58, -112, ByteCompanionObject.MIN_VALUE, 40, -118, 34, -119, 66, ByteCompanionObject.MIN_VALUE, -108, 37, 9, 66, 19, 0, -63, 48, 76, Tnaf.POW_2_WIDTH, 37, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask41_26() {
        _randomMask41_26 = new byte[]{14, 3, ByteCompanionObject.MIN_VALUE, -31, -111, 0, 51, 12, -61, 49, 69, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 50, ByteCompanionObject.MIN_VALUE, 69, 81, 84, 86, -124, ByteCompanionObject.MIN_VALUE, -120, -94, 40, -120, 74, ByteCompanionObject.MIN_VALUE, -32, 56, 14, 2, 41, 0, 70, 17, -124, 97, 25, 0, 51, 12, -61, 48, -52, ByteCompanionObject.MIN_VALUE, Tnaf.POW_2_WIDTH, -60, 49, 14, 70, 0, 12, 67, Tnaf.POW_2_WIDTH, -58, -112, ByteCompanionObject.MIN_VALUE, 40, -118, 34, -119, 66, ByteCompanionObject.MIN_VALUE, -108, 37, 9, 66, 19, 0, -63, 48, 76, Tnaf.POW_2_WIDTH, 37, ByteCompanionObject.MIN_VALUE, 14, 3, ByteCompanionObject.MIN_VALUE, -31, -111, 0, 51, 12, -61, 49, 69, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 50, ByteCompanionObject.MIN_VALUE, 69, 81, 84, 86, -124, ByteCompanionObject.MIN_VALUE, -120, -94, 40, -120, 74, ByteCompanionObject.MIN_VALUE, -32, 56, 14, 2, 41, 0, -58, 49, -116, 98, 26, 0, 35, -120, -30, 56, -116, ByteCompanionObject.MIN_VALUE, 26, 70, -111, -92, 88, ByteCompanionObject.MIN_VALUE, 36, -55, 50, 77, 48, ByteCompanionObject.MIN_VALUE, 113, 28, 71, 17, 7, 0, -11, -36, 74, 6, 81, ByteCompanionObject.MIN_VALUE, 111, 114, -15, -25, 26, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask41_27() {
        _randomMask41_27 = new byte[]{14, 3, ByteCompanionObject.MIN_VALUE, -31, -111, 0, 51, 12, -61, 49, 69, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 50, ByteCompanionObject.MIN_VALUE, 69, 81, 84, 86, -124, ByteCompanionObject.MIN_VALUE, -120, -94, 40, -120, 74, ByteCompanionObject.MIN_VALUE, -32, 56, 14, 2, 41, 0, 70, 17, -124, 97, 25, 0, 51, 12, -61, 48, -52, ByteCompanionObject.MIN_VALUE, Tnaf.POW_2_WIDTH, -60, 49, 14, 70, 0, 12, 67, Tnaf.POW_2_WIDTH, -58, -112, ByteCompanionObject.MIN_VALUE, 40, -118, 34, -119, 66, ByteCompanionObject.MIN_VALUE, -108, 37, 9, 66, 19, 0, -63, 48, 76, Tnaf.POW_2_WIDTH, 37, ByteCompanionObject.MIN_VALUE, 70, 17, -124, 97, 25, 0, 51, 12, -61, 48, -52, ByteCompanionObject.MIN_VALUE, Tnaf.POW_2_WIDTH, -60, 49, 14, 70, 0, 12, 67, Tnaf.POW_2_WIDTH, -58, -112, ByteCompanionObject.MIN_VALUE, 40, -118, 34, -119, 66, ByteCompanionObject.MIN_VALUE, -108, 37, 9, 66, 19, 0, -63, 48, 76, Tnaf.POW_2_WIDTH, 37, ByteCompanionObject.MIN_VALUE, 14, 3, ByteCompanionObject.MIN_VALUE, -31, -111, 0, 51, 12, -61, 49, 69, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 50, ByteCompanionObject.MIN_VALUE, 69, 81, 84, 86, -124, ByteCompanionObject.MIN_VALUE, -120, -94, 40, -120, 74, ByteCompanionObject.MIN_VALUE, -32, 56, 14, 2, 41, 0, 86, 62, 36, -35, 12, 0};
    }

    private static void createRandomMask41_28() {
        _randomMask41_28 = new byte[]{70, 17, -124, 97, 25, 0, 51, 12, -61, 48, -52, ByteCompanionObject.MIN_VALUE, Tnaf.POW_2_WIDTH, -60, 49, 14, 70, 0, 12, 67, Tnaf.POW_2_WIDTH, -58, -112, ByteCompanionObject.MIN_VALUE, 40, -118, 34, -119, 66, ByteCompanionObject.MIN_VALUE, -108, 37, 9, 66, 19, 0, -63, 48, 76, Tnaf.POW_2_WIDTH, 37, ByteCompanionObject.MIN_VALUE, 14, 3, ByteCompanionObject.MIN_VALUE, -31, -111, 0, 51, 12, -61, 49, 69, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 50, ByteCompanionObject.MIN_VALUE, 69, 81, 84, 86, -124, ByteCompanionObject.MIN_VALUE, -120, -94, 40, -120, 74, ByteCompanionObject.MIN_VALUE, -32, 56, 14, 2, 41, 0, 86, 62, 36, -35, 12, 0, 14, 3, ByteCompanionObject.MIN_VALUE, -31, -111, 0, 51, 12, -61, 49, 69, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 50, ByteCompanionObject.MIN_VALUE, 69, 81, 84, 86, -124, ByteCompanionObject.MIN_VALUE, -120, -94, 40, -120, 74, ByteCompanionObject.MIN_VALUE, -32, 56, 14, 2, 41, 0, 70, 17, -124, 97, 25, 0, 51, 12, -61, 48, -52, ByteCompanionObject.MIN_VALUE, Tnaf.POW_2_WIDTH, -60, 49, 14, 70, 0, 12, 67, Tnaf.POW_2_WIDTH, -58, -112, ByteCompanionObject.MIN_VALUE, 40, -118, 34, -119, 66, ByteCompanionObject.MIN_VALUE, -108, 37, 9, 66, 19, 0, -63, 48, 76, Tnaf.POW_2_WIDTH, 37, ByteCompanionObject.MIN_VALUE, 97, 44, -6, 37, 56, 0};
    }

    private static void createRandomMask41_29() {
        _randomMask41_29 = new byte[]{70, 17, -124, 97, 25, 0, 51, 12, -61, 48, -52, ByteCompanionObject.MIN_VALUE, Tnaf.POW_2_WIDTH, -60, 49, 14, 70, 0, 12, 67, Tnaf.POW_2_WIDTH, -58, -112, ByteCompanionObject.MIN_VALUE, 40, -118, 34, -119, 66, ByteCompanionObject.MIN_VALUE, -108, 37, 9, 66, 19, 0, -63, 48, 76, Tnaf.POW_2_WIDTH, 37, ByteCompanionObject.MIN_VALUE, 14, 3, ByteCompanionObject.MIN_VALUE, -31, -111, 0, 51, 12, -61, 49, 69, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 50, ByteCompanionObject.MIN_VALUE, 69, 81, 84, 86, -124, ByteCompanionObject.MIN_VALUE, -120, -94, 40, -120, 74, ByteCompanionObject.MIN_VALUE, -32, 56, 14, 2, 41, 0, 86, 62, 36, -35, 12, 0, 70, 17, -124, 97, 25, 0, 51, 12, -61, 48, -52, ByteCompanionObject.MIN_VALUE, Tnaf.POW_2_WIDTH, -60, 49, 14, 70, 0, 12, 67, Tnaf.POW_2_WIDTH, -58, -112, ByteCompanionObject.MIN_VALUE, 40, -118, 34, -119, 66, ByteCompanionObject.MIN_VALUE, -108, 37, 9, 66, 19, 0, -63, 48, 76, Tnaf.POW_2_WIDTH, 37, ByteCompanionObject.MIN_VALUE, 44, 11, 2, -64, 50, 0, -127, -96, 104, 26, 1, ByteCompanionObject.MIN_VALUE, -96, 104, 26, 6, -126, 0, 5, 65, 80, 84, 21, 0, 24, -122, 33, -119, 12, 0, -62, 48, -116, 32, 104, 0, 34, -120, -94, 41, ByteCompanionObject.MIN_VALUE, ByteCompanionObject.MIN_VALUE, 80, 84, 21, 4, 80, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask41_3() {
        _randomMask41_3 = new byte[]{-50, 51, -116, -29, 43, 0, 85, -107, 101, 93, -59, 0, -79, 106, 58, -114, -40, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask41_30() {
        _randomMask41_30 = new byte[]{70, 17, -124, 97, 25, 0, 51, 12, -61, 48, -52, ByteCompanionObject.MIN_VALUE, Tnaf.POW_2_WIDTH, -60, 49, 14, 70, 0, 12, 67, Tnaf.POW_2_WIDTH, -58, -112, ByteCompanionObject.MIN_VALUE, 40, -118, 34, -119, 66, ByteCompanionObject.MIN_VALUE, -108, 37, 9, 66, 19, 0, -63, 48, 76, Tnaf.POW_2_WIDTH, 37, ByteCompanionObject.MIN_VALUE, 44, 11, 2, -64, 50, 0, -127, -96, 104, 26, 1, ByteCompanionObject.MIN_VALUE, -96, 104, 26, 6, -126, 0, 5, 65, 80, 84, 21, 0, 24, -122, 33, -119, 12, 0, -62, 48, -116, 32, 104, 0, 34, -120, -94, 41, ByteCompanionObject.MIN_VALUE, ByteCompanionObject.MIN_VALUE, 80, 84, 21, 4, 80, ByteCompanionObject.MIN_VALUE, 70, 17, -124, 97, 25, 0, 51, 12, -61, 48, -52, ByteCompanionObject.MIN_VALUE, Tnaf.POW_2_WIDTH, -60, 49, 14, 70, 0, 12, 67, Tnaf.POW_2_WIDTH, -58, -112, ByteCompanionObject.MIN_VALUE, 40, -118, 34, -119, 66, ByteCompanionObject.MIN_VALUE, -108, 37, 9, 66, 19, 0, -63, 48, 76, Tnaf.POW_2_WIDTH, 37, ByteCompanionObject.MIN_VALUE, 14, 3, ByteCompanionObject.MIN_VALUE, -31, -111, 0, 51, 12, -61, 49, 69, 0, Tnaf.POW_2_WIDTH, -60, 49, 12, 50, ByteCompanionObject.MIN_VALUE, 69, 81, 84, 86, -124, ByteCompanionObject.MIN_VALUE, -120, -94, 40, -120, 74, ByteCompanionObject.MIN_VALUE, -32, 56, 14, 2, 41, 0, 86, 62, 36, -35, 12, 0, 89, 83, 49, 98, 21, 0};
    }

    private static void createRandomMask41_31() {
        _randomMask41_31 = new byte[]{70, 17, -124, 97, 25, 0, 51, 12, -61, 48, -52, ByteCompanionObject.MIN_VALUE, Tnaf.POW_2_WIDTH, -60, 49, 14, 70, 0, 12, 67, Tnaf.POW_2_WIDTH, -58, -112, ByteCompanionObject.MIN_VALUE, 40, -118, 34, -119, 66, ByteCompanionObject.MIN_VALUE, -108, 37, 9, 66, 19, 0, -63, 48, 76, Tnaf.POW_2_WIDTH, 37, ByteCompanionObject.MIN_VALUE, 44, 11, 2, -64, 50, 0, -127, -96, 104, 26, 1, ByteCompanionObject.MIN_VALUE, -96, 104, 26, 6, -126, 0, 5, 65, 80, 84, 21, 0, 24, -122, 33, -119, 12, 0, -62, 48, -116, 32, 104, 0, 34, -120, -94, 41, ByteCompanionObject.MIN_VALUE, ByteCompanionObject.MIN_VALUE, 80, 84, 21, 4, 80, ByteCompanionObject.MIN_VALUE, 44, 11, 2, -64, 50, 0, -127, -96, 104, 26, 1, ByteCompanionObject.MIN_VALUE, -96, 104, 26, 6, -126, 0, 5, 65, 80, 84, 21, 0, 24, -122, 33, -119, 12, 0, -62, 48, -116, 32, 104, 0, 34, -120, -94, 41, ByteCompanionObject.MIN_VALUE, ByteCompanionObject.MIN_VALUE, 80, 84, 21, 4, 80, ByteCompanionObject.MIN_VALUE, 70, 17, -124, 97, 25, 0, 51, 12, -61, 48, -52, ByteCompanionObject.MIN_VALUE, Tnaf.POW_2_WIDTH, -60, 49, 14, 70, 0, 12, 67, Tnaf.POW_2_WIDTH, -58, -112, ByteCompanionObject.MIN_VALUE, 40, -118, 34, -119, 66, ByteCompanionObject.MIN_VALUE, -108, 37, 9, 66, 19, 0, -63, 48, 76, Tnaf.POW_2_WIDTH, 37, ByteCompanionObject.MIN_VALUE, 40, 28, 99, -65, 83, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask41_32() {
        _randomMask41_32 = new byte[]{44, 11, 2, -64, 50, 0, -127, -96, 104, 26, 1, ByteCompanionObject.MIN_VALUE, -96, 104, 26, 6, -126, 0, 5, 65, 80, 84, 21, 0, 24, -122, 33, -119, 12, 0, -62, 48, -116, 32, 104, 0, 34, -120, -94, 41, ByteCompanionObject.MIN_VALUE, ByteCompanionObject.MIN_VALUE, 80, 84, 21, 4, 80, ByteCompanionObject.MIN_VALUE, 70, 17, -124, 97, 25, 0, 51, 12, -61, 48, -52, ByteCompanionObject.MIN_VALUE, Tnaf.POW_2_WIDTH, -60, 49, 14, 70, 0, 12, 67, Tnaf.POW_2_WIDTH, -58, -112, ByteCompanionObject.MIN_VALUE, 40, -118, 34, -119, 66, ByteCompanionObject.MIN_VALUE, -108, 37, 9, 66, 19, 0, -63, 48, 76, Tnaf.POW_2_WIDTH, 37, ByteCompanionObject.MIN_VALUE, 40, 28, 99, -65, 83, ByteCompanionObject.MIN_VALUE, 70, 17, -124, 97, 25, 0, 51, 12, -61, 48, -52, ByteCompanionObject.MIN_VALUE, Tnaf.POW_2_WIDTH, -60, 49, 14, 70, 0, 12, 67, Tnaf.POW_2_WIDTH, -58, -112, ByteCompanionObject.MIN_VALUE, 40, -118, 34, -119, 66, ByteCompanionObject.MIN_VALUE, -108, 37, 9, 66, 19, 0, -63, 48, 76, Tnaf.POW_2_WIDTH, 37, ByteCompanionObject.MIN_VALUE, 44, 11, 2, -64, 50, 0, -127, -96, 104, 26, 1, ByteCompanionObject.MIN_VALUE, -96, 104, 26, 6, -126, 0, 5, 65, 80, 84, 21, 0, 24, -122, 33, -119, 12, 0, -62, 48, -116, 32, 104, 0, 34, -120, -94, 41, ByteCompanionObject.MIN_VALUE, ByteCompanionObject.MIN_VALUE, 80, 84, 21, 4, 80, ByteCompanionObject.MIN_VALUE, -54, -69, -53, 109, -86, 0};
    }

    private static void createRandomMask41_33() {
        _randomMask41_33 = new byte[]{44, 11, 2, -64, 50, 0, -127, -96, 104, 26, 1, ByteCompanionObject.MIN_VALUE, -96, 104, 26, 6, -126, 0, 5, 65, 80, 84, 21, 0, 24, -122, 33, -119, 12, 0, -62, 48, -116, 32, 104, 0, 34, -120, -94, 41, ByteCompanionObject.MIN_VALUE, ByteCompanionObject.MIN_VALUE, 80, 84, 21, 4, 80, ByteCompanionObject.MIN_VALUE, 70, 17, -124, 97, 25, 0, 51, 12, -61, 48, -52, ByteCompanionObject.MIN_VALUE, Tnaf.POW_2_WIDTH, -60, 49, 14, 70, 0, 12, 67, Tnaf.POW_2_WIDTH, -58, -112, ByteCompanionObject.MIN_VALUE, 40, -118, 34, -119, 66, ByteCompanionObject.MIN_VALUE, -108, 37, 9, 66, 19, 0, -63, 48, 76, Tnaf.POW_2_WIDTH, 37, ByteCompanionObject.MIN_VALUE, 40, 28, 99, -65, 83, ByteCompanionObject.MIN_VALUE, 44, 11, 2, -64, 50, 0, -127, -96, 104, 26, 1, ByteCompanionObject.MIN_VALUE, -96, 104, 26, 6, -126, 0, 5, 65, 80, 84, 21, 0, 24, -122, 33, -119, 12, 0, -62, 48, -116, 32, 104, 0, 34, -120, -94, 41, ByteCompanionObject.MIN_VALUE, ByteCompanionObject.MIN_VALUE, 80, 84, 21, 4, 80, ByteCompanionObject.MIN_VALUE, 78, 19, -124, -31, 25, 0, -29, 56, -50, 49, -119, ByteCompanionObject.MIN_VALUE, -127, -32, 120, 30, 48, 0, 33, 72, 82, 20, 5, ByteCompanionObject.MIN_VALUE, 82, -108, -91, 40, 30, 0, -76, 45, 11, 66, -126, 0, 38, -119, -94, 104, 98, ByteCompanionObject.MIN_VALUE, 88, 86, 21, -122, 68, 0, 25, -122, 97, -103, -32, 0};
    }

    private static void createRandomMask41_34() {
        _randomMask41_34 = new byte[]{44, 11, 2, -64, 50, 0, -127, -96, 104, 26, 1, ByteCompanionObject.MIN_VALUE, -96, 104, 26, 6, -126, 0, 5, 65, 80, 84, 21, 0, 24, -122, 33, -119, 12, 0, -62, 48, -116, 32, 104, 0, 34, -120, -94, 41, ByteCompanionObject.MIN_VALUE, ByteCompanionObject.MIN_VALUE, 80, 84, 21, 4, 80, ByteCompanionObject.MIN_VALUE, 78, 19, -124, -31, 25, 0, -29, 56, -50, 49, -119, ByteCompanionObject.MIN_VALUE, -127, -32, 120, 30, 48, 0, 33, 72, 82, 20, 5, ByteCompanionObject.MIN_VALUE, 82, -108, -91, 40, 30, 0, -76, 45, 11, 66, -126, 0, 38, -119, -94, 104, 98, ByteCompanionObject.MIN_VALUE, 88, 86, 21, -122, 68, 0, 25, -122, 97, -103, -32, 0, 44, 11, 2, -64, 50, 0, -127, -96, 104, 26, 1, ByteCompanionObject.MIN_VALUE, -96, 104, 26, 6, -126, 0, 5, 65, 80, 84, 21, 0, 24, -122, 33, -119, 12, 0, -62, 48, -116, 32, 104, 0, 34, -120, -94, 41, ByteCompanionObject.MIN_VALUE, ByteCompanionObject.MIN_VALUE, 80, 84, 21, 4, 80, ByteCompanionObject.MIN_VALUE, 70, 17, -124, 97, 25, 0, 51, 12, -61, 48, -52, ByteCompanionObject.MIN_VALUE, Tnaf.POW_2_WIDTH, -60, 49, 14, 70, 0, 12, 67, Tnaf.POW_2_WIDTH, -58, -112, ByteCompanionObject.MIN_VALUE, 40, -118, 34, -119, 66, ByteCompanionObject.MIN_VALUE, -108, 37, 9, 66, 19, 0, -63, 48, 76, Tnaf.POW_2_WIDTH, 37, ByteCompanionObject.MIN_VALUE, 40, 28, 99, -65, 83, ByteCompanionObject.MIN_VALUE, -67, 55, Utf8.REPLACEMENT_BYTE, 117, 54, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask41_35() {
        _randomMask41_35 = new byte[]{44, 11, 2, -64, 50, 0, -127, -96, 104, 26, 1, ByteCompanionObject.MIN_VALUE, -96, 104, 26, 6, -126, 0, 5, 65, 80, 84, 21, 0, 24, -122, 33, -119, 12, 0, -62, 48, -116, 32, 104, 0, 34, -120, -94, 41, ByteCompanionObject.MIN_VALUE, ByteCompanionObject.MIN_VALUE, 80, 84, 21, 4, 80, ByteCompanionObject.MIN_VALUE, 78, 19, -124, -31, 25, 0, -29, 56, -50, 49, -119, ByteCompanionObject.MIN_VALUE, -127, -32, 120, 30, 48, 0, 33, 72, 82, 20, 5, ByteCompanionObject.MIN_VALUE, 82, -108, -91, 40, 30, 0, -76, 45, 11, 66, -126, 0, 38, -119, -94, 104, 98, ByteCompanionObject.MIN_VALUE, 88, 86, 21, -122, 68, 0, 25, -122, 97, -103, -32, 0, 78, 19, -124, -31, 25, 0, -29, 56, -50, 49, -119, ByteCompanionObject.MIN_VALUE, -127, -32, 120, 30, 48, 0, 33, 72, 82, 20, 5, ByteCompanionObject.MIN_VALUE, 82, -108, -91, 40, 30, 0, -76, 45, 11, 66, -126, 0, 38, -119, -94, 104, 98, ByteCompanionObject.MIN_VALUE, 88, 86, 21, -122, 68, 0, 25, -122, 97, -103, -32, 0, 44, 11, 2, -64, 50, 0, -127, -96, 104, 26, 1, ByteCompanionObject.MIN_VALUE, -96, 104, 26, 6, -126, 0, 5, 65, 80, 84, 21, 0, 24, -122, 33, -119, 12, 0, -62, 48, -116, 32, 104, 0, 34, -120, -94, 41, ByteCompanionObject.MIN_VALUE, ByteCompanionObject.MIN_VALUE, 80, 84, 21, 4, 80, ByteCompanionObject.MIN_VALUE, 33, 123, -11, -91, 101, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask41_36() {
        _randomMask41_36 = new byte[]{78, 19, -124, -31, 25, 0, -29, 56, -50, 49, -119, ByteCompanionObject.MIN_VALUE, -127, -32, 120, 30, 48, 0, 33, 72, 82, 20, 5, ByteCompanionObject.MIN_VALUE, 82, -108, -91, 40, 30, 0, -76, 45, 11, 66, -126, 0, 38, -119, -94, 104, 98, ByteCompanionObject.MIN_VALUE, 88, 86, 21, -122, 68, 0, 25, -122, 97, -103, -32, 0, 44, 11, 2, -64, 50, 0, -127, -96, 104, 26, 1, ByteCompanionObject.MIN_VALUE, -96, 104, 26, 6, -126, 0, 5, 65, 80, 84, 21, 0, 24, -122, 33, -119, 12, 0, -62, 48, -116, 32, 104, 0, 34, -120, -94, 41, ByteCompanionObject.MIN_VALUE, ByteCompanionObject.MIN_VALUE, 80, 84, 21, 4, 80, ByteCompanionObject.MIN_VALUE, 33, 123, -11, -91, 101, ByteCompanionObject.MIN_VALUE, 44, 11, 2, -64, 50, 0, -127, -96, 104, 26, 1, ByteCompanionObject.MIN_VALUE, -96, 104, 26, 6, -126, 0, 5, 65, 80, 84, 21, 0, 24, -122, 33, -119, 12, 0, -62, 48, -116, 32, 104, 0, 34, -120, -94, 41, ByteCompanionObject.MIN_VALUE, ByteCompanionObject.MIN_VALUE, 80, 84, 21, 4, 80, ByteCompanionObject.MIN_VALUE, 78, 19, -124, -31, 25, 0, -29, 56, -50, 49, -119, ByteCompanionObject.MIN_VALUE, -127, -32, 120, 30, 48, 0, 33, 72, 82, 20, 5, ByteCompanionObject.MIN_VALUE, 82, -108, -91, 40, 30, 0, -76, 45, 11, 66, -126, 0, 38, -119, -94, 104, 98, ByteCompanionObject.MIN_VALUE, 88, 86, 21, -122, 68, 0, 25, -122, 97, -103, -32, 0, -63, -79, ByteCompanionObject.MIN_VALUE, -66, 62, 0};
    }

    private static void createRandomMask41_37() {
        _randomMask41_37 = new byte[]{78, 19, -124, -31, 25, 0, -29, 56, -50, 49, -119, ByteCompanionObject.MIN_VALUE, -127, -32, 120, 30, 48, 0, 33, 72, 82, 20, 5, ByteCompanionObject.MIN_VALUE, 82, -108, -91, 40, 30, 0, -76, 45, 11, 66, -126, 0, 38, -119, -94, 104, 98, ByteCompanionObject.MIN_VALUE, 88, 86, 21, -122, 68, 0, 25, -122, 97, -103, -32, 0, 44, 11, 2, -64, 50, 0, -127, -96, 104, 26, 1, ByteCompanionObject.MIN_VALUE, -96, 104, 26, 6, -126, 0, 5, 65, 80, 84, 21, 0, 24, -122, 33, -119, 12, 0, -62, 48, -116, 32, 104, 0, 34, -120, -94, 41, ByteCompanionObject.MIN_VALUE, ByteCompanionObject.MIN_VALUE, 80, 84, 21, 4, 80, ByteCompanionObject.MIN_VALUE, 33, 123, -11, -91, 101, ByteCompanionObject.MIN_VALUE, 78, 19, -124, -31, 25, 0, -29, 56, -50, 49, -119, ByteCompanionObject.MIN_VALUE, -127, -32, 120, 30, 48, 0, 33, 72, 82, 20, 5, ByteCompanionObject.MIN_VALUE, 82, -108, -91, 40, 30, 0, -76, 45, 11, 66, -126, 0, 38, -119, -94, 104, 98, ByteCompanionObject.MIN_VALUE, 88, 86, 21, -122, 68, 0, 25, -122, 97, -103, -32, 0, 76, 19, 4, -63, -111, 0, 81, 20, 69, 17, 69, 0, -96, 104, 26, 6, -92, 0, 4, -63, 48, 76, 19, 0, 3, ByteCompanionObject.MIN_VALUE, -32, 59, 64, 0, -122, 33, -120, 98, 9, 0, 41, 10, 66, -112, -124, ByteCompanionObject.MIN_VALUE, 66, 80, -108, 36, 48, ByteCompanionObject.MIN_VALUE, -104, 38, 9, -127, 40, 0, 48, -116, 35, 8, 74, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask41_38() {
        _randomMask41_38 = new byte[]{78, 19, -124, -31, 25, 0, -29, 56, -50, 49, -119, ByteCompanionObject.MIN_VALUE, -127, -32, 120, 30, 48, 0, 33, 72, 82, 20, 5, ByteCompanionObject.MIN_VALUE, 82, -108, -91, 40, 30, 0, -76, 45, 11, 66, -126, 0, 38, -119, -94, 104, 98, ByteCompanionObject.MIN_VALUE, 88, 86, 21, -122, 68, 0, 25, -122, 97, -103, -32, 0, 76, 19, 4, -63, -111, 0, 81, 20, 69, 17, 69, 0, -96, 104, 26, 6, -92, 0, 4, -63, 48, 76, 19, 0, 3, ByteCompanionObject.MIN_VALUE, -32, 59, 64, 0, -122, 33, -120, 98, 9, 0, 41, 10, 66, -112, -124, ByteCompanionObject.MIN_VALUE, 66, 80, -108, 36, 48, ByteCompanionObject.MIN_VALUE, -104, 38, 9, -127, 40, 0, 48, -116, 35, 8, 74, ByteCompanionObject.MIN_VALUE, 78, 19, -124, -31, 25, 0, -29, 56, -50, 49, -119, ByteCompanionObject.MIN_VALUE, -127, -32, 120, 30, 48, 0, 33, 72, 82, 20, 5, ByteCompanionObject.MIN_VALUE, 82, -108, -91, 40, 30, 0, -76, 45, 11, 66, -126, 0, 38, -119, -94, 104, 98, ByteCompanionObject.MIN_VALUE, 88, 86, 21, -122, 68, 0, 25, -122, 97, -103, -32, 0, 44, 11, 2, -64, 50, 0, -127, -96, 104, 26, 1, ByteCompanionObject.MIN_VALUE, -96, 104, 26, 6, -126, 0, 5, 65, 80, 84, 21, 0, 24, -122, 33, -119, 12, 0, -62, 48, -116, 32, 104, 0, 34, -120, -94, 41, ByteCompanionObject.MIN_VALUE, ByteCompanionObject.MIN_VALUE, 80, 84, 21, 4, 80, ByteCompanionObject.MIN_VALUE, 33, 123, -11, -91, 101, ByteCompanionObject.MIN_VALUE, -22, -56, -69, -44, 93, 0};
    }

    private static void createRandomMask41_39() {
        _randomMask41_39 = new byte[]{78, 19, -124, -31, 25, 0, -29, 56, -50, 49, -119, ByteCompanionObject.MIN_VALUE, -127, -32, 120, 30, 48, 0, 33, 72, 82, 20, 5, ByteCompanionObject.MIN_VALUE, 82, -108, -91, 40, 30, 0, -76, 45, 11, 66, -126, 0, 38, -119, -94, 104, 98, ByteCompanionObject.MIN_VALUE, 88, 86, 21, -122, 68, 0, 25, -122, 97, -103, -32, 0, 76, 19, 4, -63, -111, 0, 81, 20, 69, 17, 69, 0, -96, 104, 26, 6, -92, 0, 4, -63, 48, 76, 19, 0, 3, ByteCompanionObject.MIN_VALUE, -32, 59, 64, 0, -122, 33, -120, 98, 9, 0, 41, 10, 66, -112, -124, ByteCompanionObject.MIN_VALUE, 66, 80, -108, 36, 48, ByteCompanionObject.MIN_VALUE, -104, 38, 9, -127, 40, 0, 48, -116, 35, 8, 74, ByteCompanionObject.MIN_VALUE, 76, 19, 4, -63, -111, 0, 81, 20, 69, 17, 69, 0, -96, 104, 26, 6, -92, 0, 4, -63, 48, 76, 19, 0, 3, ByteCompanionObject.MIN_VALUE, -32, 59, 64, 0, -122, 33, -120, 98, 9, 0, 41, 10, 66, -112, -124, ByteCompanionObject.MIN_VALUE, 66, 80, -108, 36, 48, ByteCompanionObject.MIN_VALUE, -104, 38, 9, -127, 40, 0, 48, -116, 35, 8, 74, ByteCompanionObject.MIN_VALUE, 78, 19, -124, -31, 25, 0, -29, 56, -50, 49, -119, ByteCompanionObject.MIN_VALUE, -127, -32, 120, 30, 48, 0, 33, 72, 82, 20, 5, ByteCompanionObject.MIN_VALUE, 82, -108, -91, 40, 30, 0, -76, 45, 11, 66, -126, 0, 38, -119, -94, 104, 98, ByteCompanionObject.MIN_VALUE, 88, 86, 21, -122, 68, 0, 25, -122, 97, -103, -32, 0, -9, -115, -94, -96, 51, 0};
    }

    private static void createRandomMask41_4() {
        _randomMask41_4 = new byte[]{-26, 57, -114, 99, 19, 0, 51, -116, -29, 56, -59, ByteCompanionObject.MIN_VALUE, -104, -26, 57, -115, 44, ByteCompanionObject.MIN_VALUE, 45, 75, 82, -44, -78, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask41_40() {
        _randomMask41_40 = new byte[]{76, 19, 4, -63, -111, 0, 81, 20, 69, 17, 69, 0, -96, 104, 26, 6, -92, 0, 4, -63, 48, 76, 19, 0, 3, ByteCompanionObject.MIN_VALUE, -32, 59, 64, 0, -122, 33, -120, 98, 9, 0, 41, 10, 66, -112, -124, ByteCompanionObject.MIN_VALUE, 66, 80, -108, 36, 48, ByteCompanionObject.MIN_VALUE, -104, 38, 9, -127, 40, 0, 48, -116, 35, 8, 74, ByteCompanionObject.MIN_VALUE, 78, 19, -124, -31, 25, 0, -29, 56, -50, 49, -119, ByteCompanionObject.MIN_VALUE, -127, -32, 120, 30, 48, 0, 33, 72, 82, 20, 5, ByteCompanionObject.MIN_VALUE, 82, -108, -91, 40, 30, 0, -76, 45, 11, 66, -126, 0, 38, -119, -94, 104, 98, ByteCompanionObject.MIN_VALUE, 88, 86, 21, -122, 68, 0, 25, -122, 97, -103, -32, 0, -9, -115, -94, -96, 51, 0, 78, 19, -124, -31, 25, 0, -29, 56, -50, 49, -119, ByteCompanionObject.MIN_VALUE, -127, -32, 120, 30, 48, 0, 33, 72, 82, 20, 5, ByteCompanionObject.MIN_VALUE, 82, -108, -91, 40, 30, 0, -76, 45, 11, 66, -126, 0, 38, -119, -94, 104, 98, ByteCompanionObject.MIN_VALUE, 88, 86, 21, -122, 68, 0, 25, -122, 97, -103, -32, 0, 76, 19, 4, -63, -111, 0, 81, 20, 69, 17, 69, 0, -96, 104, 26, 6, -92, 0, 4, -63, 48, 76, 19, 0, 3, ByteCompanionObject.MIN_VALUE, -32, 59, 64, 0, -122, 33, -120, 98, 9, 0, 41, 10, 66, -112, -124, ByteCompanionObject.MIN_VALUE, 66, 80, -108, 36, 48, ByteCompanionObject.MIN_VALUE, -104, 38, 9, -127, 40, 0, 48, -116, 35, 8, 74, ByteCompanionObject.MIN_VALUE, -24, 7, 24, -102, 2, 0};
    }

    private static void createRandomMask41_41() {
        _randomMask41_41 = new byte[]{76, 19, 4, -63, -111, 0, 81, 20, 69, 17, 69, 0, -96, 104, 26, 6, -92, 0, 4, -63, 48, 76, 19, 0, 3, ByteCompanionObject.MIN_VALUE, -32, 59, 64, 0, -122, 33, -120, 98, 9, 0, 41, 10, 66, -112, -124, ByteCompanionObject.MIN_VALUE, 66, 80, -108, 36, 48, ByteCompanionObject.MIN_VALUE, -104, 38, 9, -127, 40, 0, 48, -116, 35, 8, 74, ByteCompanionObject.MIN_VALUE, 78, 19, -124, -31, 25, 0, -29, 56, -50, 49, -119, ByteCompanionObject.MIN_VALUE, -127, -32, 120, 30, 48, 0, 33, 72, 82, 20, 5, ByteCompanionObject.MIN_VALUE, 82, -108, -91, 40, 30, 0, -76, 45, 11, 66, -126, 0, 38, -119, -94, 104, 98, ByteCompanionObject.MIN_VALUE, 88, 86, 21, -122, 68, 0, 25, -122, 97, -103, -32, 0, -9, -115, -94, -96, 51, 0, 76, 19, 4, -63, -111, 0, 81, 20, 69, 17, 69, 0, -96, 104, 26, 6, -92, 0, 4, -63, 48, 76, 19, 0, 3, ByteCompanionObject.MIN_VALUE, -32, 59, 64, 0, -122, 33, -120, 98, 9, 0, 41, 10, 66, -112, -124, ByteCompanionObject.MIN_VALUE, 66, 80, -108, 36, 48, ByteCompanionObject.MIN_VALUE, -104, 38, 9, -127, 40, 0, 48, -116, 35, 8, 74, ByteCompanionObject.MIN_VALUE, 78, 19, -124, -63, 25, 0, -29, 56, -59, Tnaf.POW_2_WIDTH, -52, ByteCompanionObject.MIN_VALUE, -127, -32, 122, 6, 100, 0, 33, 72, 80, 76, 22, 0, 82, -108, -96, 58, 2, ByteCompanionObject.MIN_VALUE, -76, 45, 8, 98, 17, 0, 38, -119, -94, -111, 1, ByteCompanionObject.MIN_VALUE, 88, 86, 20, 36, 42, 0, 25, -122, 105, -127, -96, 0, -9, -115, -93, 8, 64, ByteCompanionObject.MIN_VALUE, 43, -22, 77, -12, -63, 0};
    }

    private static void createRandomMask41_5() {
        _randomMask41_5 = new byte[]{-50, 51, -116, -29, 27, 0, 99, -104, -26, 57, -115, ByteCompanionObject.MIN_VALUE, -104, -27, 57, -116, 118, ByteCompanionObject.MIN_VALUE, 43, 83, 84, -42, -75, 0, -76, 92, -85, 38, -54, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask41_6() {
        _randomMask41_6 = new byte[]{76, 27, 4, -63, -111, 0, 81, 52, 69, 17, 69, 0, 32, -24, 50, 14, -96, ByteCompanionObject.MIN_VALUE, -123, 65, 88, 84, 18, ByteCompanionObject.MIN_VALUE, 6, -122, -96, 104, 13, ByteCompanionObject.MIN_VALUE, -102, 33, -120, -94, 67, 0};
    }

    private static void createRandomMask41_7() {
        _randomMask41_7 = new byte[]{78, 17, -116, 97, 25, 0, 51, 44, 3, 48, 76, ByteCompanionObject.MIN_VALUE, Tnaf.POW_2_WIDTH, 14, -79, -122, 116, 0, -127, 81, 84, 84, 45, 0, 36, -60, -95, 45, 66, ByteCompanionObject.MIN_VALUE, -44, 35, 11, 66, -125, 0, 12, -94, 98, -103, 33, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask41_8() {
        _randomMask41_8 = new byte[]{39, 9, -64, 112, -89, 0, -119, -94, 100, -102, -126, ByteCompanionObject.MIN_VALUE, -48, 116, 27, 7, -96, 0, 36, -55, 50, 76, 92, 0, -30, -112, -91, 40, 14, ByteCompanionObject.MIN_VALUE, -58, 49, -116, 99, 24, 0, 49, -116, 99, 25, 65, ByteCompanionObject.MIN_VALUE, 24, -58, 49, -116, 112, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask41_9() {
        _randomMask41_9 = new byte[]{78, 19, -124, -31, 17, 0, 98, 56, -58, 33, -96, ByteCompanionObject.MIN_VALUE, -127, -32, 120, 14, -108, 0, -31, 72, 90, 21, 5, 0, 19, -108, -91, 48, 6, ByteCompanionObject.MIN_VALUE, -76, 45, 10, 66, 67, 0, 38, -119, -95, 106, 8, ByteCompanionObject.MIN_VALUE, 88, 86, 21, -124, 82, 0, 73, -122, 82, -104, 104, 0};
    }

    private static void createRandomMask42_1() {
        _randomMask42_1 = new byte[]{-1, -1, -1, -1, -1, -64};
    }

    private static void createRandomMask42_10() {
        _randomMask42_10 = new byte[]{76, 25, 18, 96, -56, ByteCompanionObject.MIN_VALUE, 81, 20, 82, -120, -94, ByteCompanionObject.MIN_VALUE, -96, 106, 69, 3, 82, 0, 4, -63, 48, 38, 9, ByteCompanionObject.MIN_VALUE, 3, -76, 0, 29, -96, 0, -122, 32, -108, 49, 4, ByteCompanionObject.MIN_VALUE, 41, 8, 73, 72, 66, 64, 66, 67, 10, 18, 24, 64, -104, 18, -124, -64, -108, 0, 48, -124, -87, -124, 37, 64};
    }

    private static void createRandomMask42_11() {
        _randomMask42_11 = new byte[]{-58, 33, -90, 49, 13, 0, 35, -120, -55, 28, 70, 64, 26, 69, -120, -46, 44, 64, 36, -45, 9, 38, -104, 64, 113, Tnaf.POW_2_WIDTH, 115, -120, -125, ByteCompanionObject.MIN_VALUE, 14, 25, Tnaf.POW_2_WIDTH, 112, -56, ByteCompanionObject.MIN_VALUE, 51, 20, 81, -104, -94, ByteCompanionObject.MIN_VALUE, Tnaf.POW_2_WIDTH, -61, 40, -122, 25, 64, 69, 104, 74, 43, 66, 64, -120, -124, -84, 68, 37, 64, -32, 34, -105, 1, 20, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask42_12() {
        _randomMask42_12 = new byte[]{14, 25, Tnaf.POW_2_WIDTH, 112, -56, ByteCompanionObject.MIN_VALUE, 51, 20, 81, -104, -94, ByteCompanionObject.MIN_VALUE, Tnaf.POW_2_WIDTH, -61, 40, -122, 25, 64, 69, 104, 74, 43, 66, 64, -120, -124, -84, 68, 37, 64, -32, 34, -105, 1, 20, ByteCompanionObject.MIN_VALUE, -58, 33, -90, 49, 13, 0, 35, -120, -55, 28, 70, 64, 26, 69, -120, -46, 44, 64, 36, -45, 9, 38, -104, 64, 113, Tnaf.POW_2_WIDTH, 115, -120, -125, ByteCompanionObject.MIN_VALUE, -96, 101, 29, 3, 40, -64};
    }

    private static void createRandomMask42_13() {
        _randomMask42_13 = new byte[]{14, 25, Tnaf.POW_2_WIDTH, 112, -56, ByteCompanionObject.MIN_VALUE, 51, 20, 81, -104, -94, ByteCompanionObject.MIN_VALUE, Tnaf.POW_2_WIDTH, -61, 40, -122, 25, 64, 69, 104, 74, 43, 66, 64, -120, -124, -84, 68, 37, 64, -32, 34, -105, 1, 20, ByteCompanionObject.MIN_VALUE, 70, 17, -110, 48, -116, ByteCompanionObject.MIN_VALUE, 51, 12, -55, -104, 102, 64, Tnaf.POW_2_WIDTH, -28, 96, -121, 35, 0, 12, 105, 8, 99, 72, 64, 40, -108, 41, 68, -95, 64, -108, 33, 52, -95, 9, ByteCompanionObject.MIN_VALUE, -63, 2, 94, 8, 18, -64};
    }

    private static void createRandomMask42_14() {
        _randomMask42_14 = new byte[]{70, 17, -110, 48, -116, ByteCompanionObject.MIN_VALUE, 51, 12, -55, -104, 102, 64, Tnaf.POW_2_WIDTH, -28, 96, -121, 35, 0, 12, 105, 8, 99, 72, 64, 40, -108, 41, 68, -95, 64, -108, 33, 52, -95, 9, ByteCompanionObject.MIN_VALUE, -63, 2, 94, 8, 18, -64, 14, 25, Tnaf.POW_2_WIDTH, 112, -56, ByteCompanionObject.MIN_VALUE, 51, 20, 81, -104, -94, ByteCompanionObject.MIN_VALUE, Tnaf.POW_2_WIDTH, -61, 40, -122, 25, 64, 69, 104, 74, 43, 66, 64, -120, -124, -84, 68, 37, 64, -32, 34, -105, 1, 20, ByteCompanionObject.MIN_VALUE, 77, -48, -62, 110, -122, 0};
    }

    private static void createRandomMask42_15() {
        _randomMask42_15 = new byte[]{70, 17, -110, 48, -116, ByteCompanionObject.MIN_VALUE, 51, 12, -55, -104, 102, 64, Tnaf.POW_2_WIDTH, -28, 96, -121, 35, 0, 12, 105, 8, 99, 72, 64, 40, -108, 41, 68, -95, 64, -108, 33, 52, -95, 9, ByteCompanionObject.MIN_VALUE, -63, 2, 94, 8, 18, -64, 44, 3, 33, 96, 25, 0, -127, -96, 28, 13, 0, -64, -96, 104, 37, 3, 65, 0, 5, 65, 80, 42, 10, ByteCompanionObject.MIN_VALUE, 24, -112, -64, -60, -122, 0, -62, 6, -122, Tnaf.POW_2_WIDTH, 52, 0, 34, -104, 9, 20, -64, 64, 80, 69, 10, -126, 40, 64};
    }

    private static void createRandomMask42_16() {
        _randomMask42_16 = new byte[]{44, 3, 33, 96, 25, 0, -127, -96, 28, 13, 0, -64, -96, 104, 37, 3, 65, 0, 5, 65, 80, 42, 10, ByteCompanionObject.MIN_VALUE, 24, -112, -64, -60, -122, 0, -62, 6, -122, Tnaf.POW_2_WIDTH, 52, 0, 34, -104, 9, 20, -64, 64, 80, 69, 10, -126, 40, 64, 70, 17, -110, 48, -116, ByteCompanionObject.MIN_VALUE, 51, 12, -55, -104, 102, 64, Tnaf.POW_2_WIDTH, -28, 96, -121, 35, 0, 12, 105, 8, 99, 72, 64, 40, -108, 41, 68, -95, 64, -108, 33, 52, -95, 9, ByteCompanionObject.MIN_VALUE, -63, 2, 94, 8, 18, -64, 59, -11, 57, -33, -87, -64};
    }

    private static void createRandomMask42_17() {
        _randomMask42_17 = new byte[]{44, 3, 33, 96, 25, 0, -127, -96, 28, 13, 0, -64, -96, 104, 37, 3, 65, 0, 5, 65, 80, 42, 10, ByteCompanionObject.MIN_VALUE, 24, -112, -64, -60, -122, 0, -62, 6, -122, Tnaf.POW_2_WIDTH, 52, 0, 34, -104, 9, 20, -64, 64, 80, 69, 10, -126, 40, 64, 78, 17, -110, 112, -116, ByteCompanionObject.MIN_VALUE, -29, 24, -97, 24, -60, -64, -127, -29, 4, 15, 24, 0, 33, 64, 89, 10, 2, -64, 82, -127, -30, -108, 15, 0, -76, 40, 37, -95, 65, 0, 38, -122, 41, 52, 49, 64, 88, 100, 66, -61, 34, 0, 25, -98, 0, -52, -16, 0};
    }

    private static void createRandomMask42_18() {
        _randomMask42_18 = new byte[]{78, 17, -110, 112, -116, ByteCompanionObject.MIN_VALUE, -29, 24, -97, 24, -60, -64, -127, -29, 4, 15, 24, 0, 33, 64, 89, 10, 2, -64, 82, -127, -30, -108, 15, 0, -76, 40, 37, -95, 65, 0, 38, -122, 41, 52, 49, 64, 88, 100, 66, -61, 34, 0, 25, -98, 0, -52, -16, 0, 44, 3, 33, 96, 25, 0, -127, -96, 28, 13, 0, -64, -96, 104, 37, 3, 65, 0, 5, 65, 80, 42, 10, ByteCompanionObject.MIN_VALUE, 24, -112, -64, -60, -122, 0, -62, 6, -122, Tnaf.POW_2_WIDTH, 52, 0, 34, -104, 9, 20, -64, 64, 80, 69, 10, -126, 40, 64, 90, 86, 90, -46, -78, -64};
    }

    private static void createRandomMask42_19() {
        _randomMask42_19 = new byte[]{78, 17, -110, 112, -116, ByteCompanionObject.MIN_VALUE, -29, 24, -97, 24, -60, -64, -127, -29, 4, 15, 24, 0, 33, 64, 89, 10, 2, -64, 82, -127, -30, -108, 15, 0, -76, 40, 37, -95, 65, 0, 38, -122, 41, 52, 49, 64, 88, 100, 66, -61, 34, 0, 25, -98, 0, -52, -16, 0, 76, 25, 18, 96, -56, ByteCompanionObject.MIN_VALUE, 81, 20, 82, -120, -94, ByteCompanionObject.MIN_VALUE, -96, 106, 69, 3, 82, 0, 4, -63, 48, 38, 9, ByteCompanionObject.MIN_VALUE, 3, -76, 0, 29, -96, 0, -122, 32, -108, 49, 4, ByteCompanionObject.MIN_VALUE, 41, 8, 73, 72, 66, 64, 66, 67, 10, 18, 24, 64, -104, 18, -124, -64, -108, 0, 48, -124, -87, -124, 37, 64};
    }

    private static void createRandomMask42_2() {
        _randomMask42_2 = new byte[]{-18, 59, 55, 113, -39, ByteCompanionObject.MIN_VALUE, -103, -26, -20, -49, 55, 64};
    }

    private static void createRandomMask42_20() {
        _randomMask42_20 = new byte[]{76, 25, 18, 96, -56, ByteCompanionObject.MIN_VALUE, 81, 20, 82, -120, -94, ByteCompanionObject.MIN_VALUE, -96, 106, 69, 3, 82, 0, 4, -63, 48, 38, 9, ByteCompanionObject.MIN_VALUE, 3, -76, 0, 29, -96, 0, -122, 32, -108, 49, 4, ByteCompanionObject.MIN_VALUE, 41, 8, 73, 72, 66, 64, 66, 67, 10, 18, 24, 64, -104, 18, -124, -64, -108, 0, 48, -124, -87, -124, 37, 64, 78, 17, -110, 112, -116, ByteCompanionObject.MIN_VALUE, -29, 24, -97, 24, -60, -64, -127, -29, 4, 15, 24, 0, 33, 64, 89, 10, 2, -64, 82, -127, -30, -108, 15, 0, -76, 40, 37, -95, 65, 0, 38, -122, 41, 52, 49, 64, 88, 100, 66, -61, 34, 0, 25, -98, 0, -52, -16, 0, 42, 3, 49, 80, 25, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask42_21() {
        _randomMask42_21 = new byte[]{76, 25, 18, 96, -56, ByteCompanionObject.MIN_VALUE, 81, 20, 82, -120, -94, ByteCompanionObject.MIN_VALUE, -96, 106, 69, 3, 82, 0, 4, -63, 48, 38, 9, ByteCompanionObject.MIN_VALUE, 3, -76, 0, 29, -96, 0, -122, 32, -108, 49, 4, ByteCompanionObject.MIN_VALUE, 41, 8, 73, 72, 66, 64, 66, 67, 10, 18, 24, 64, -104, 18, -124, -64, -108, 0, 48, -124, -87, -124, 37, 64, 76, 17, -110, 96, -116, ByteCompanionObject.MIN_VALUE, 81, 12, -54, -120, 102, 64, -96, 102, 69, 3, 50, 0, 4, -63, 96, 38, 11, 0, 3, -96, 40, 29, 1, 64, -122, 33, 20, 49, 8, ByteCompanionObject.MIN_VALUE, 41, Tnaf.POW_2_WIDTH, 25, 72, ByteCompanionObject.MIN_VALUE, -64, 66, 66, -94, 18, 21, 0, -104, 26, 4, -64, -48, 0, 48, -124, 9, -124, 32, 64, -33, 76, 22, -6, 96, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask42_22() {
        _randomMask42_22 = new byte[]{-58, 33, -90, 49, 13, 0, 35, -120, -55, 28, 70, 64, 26, 69, -120, -46, 44, 64, 36, -45, 9, 38, -104, 64, 113, Tnaf.POW_2_WIDTH, 115, -120, -125, ByteCompanionObject.MIN_VALUE, 14, 25, Tnaf.POW_2_WIDTH, 112, -56, ByteCompanionObject.MIN_VALUE, 51, 20, 81, -104, -94, ByteCompanionObject.MIN_VALUE, Tnaf.POW_2_WIDTH, -61, 40, -122, 25, 64, 69, 104, 74, 43, 66, 64, -120, -124, -84, 68, 37, 64, -32, 34, -105, 1, 20, ByteCompanionObject.MIN_VALUE, 76, 25, 18, 96, -56, ByteCompanionObject.MIN_VALUE, 81, 20, 82, -120, -94, ByteCompanionObject.MIN_VALUE, -96, 106, 69, 3, 82, 0, 4, -63, 48, 38, 9, ByteCompanionObject.MIN_VALUE, 3, -76, 0, 29, -96, 0, -122, 32, -108, 49, 4, ByteCompanionObject.MIN_VALUE, 41, 8, 73, 72, 66, 64, 66, 67, 10, 18, 24, 64, -104, 18, -124, -64, -108, 0, 48, -124, -87, -124, 37, 64, -37, 54, -80, 51, 20, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask42_23() {
        _randomMask42_23 = new byte[]{-58, 33, -90, 49, 13, 0, 35, -120, -55, 28, 70, 64, 26, 69, -120, -46, 44, 64, 36, -45, 9, 38, -104, 64, 113, Tnaf.POW_2_WIDTH, 115, -120, -125, ByteCompanionObject.MIN_VALUE, 14, 25, Tnaf.POW_2_WIDTH, 112, -56, ByteCompanionObject.MIN_VALUE, 51, 20, 81, -104, -94, ByteCompanionObject.MIN_VALUE, Tnaf.POW_2_WIDTH, -61, 40, -122, 25, 64, 69, 104, 74, 43, 66, 64, -120, -124, -84, 68, 37, 64, -32, 34, -105, 1, 20, ByteCompanionObject.MIN_VALUE, 14, 25, Tnaf.POW_2_WIDTH, 112, -56, ByteCompanionObject.MIN_VALUE, 51, 20, 81, -104, -94, ByteCompanionObject.MIN_VALUE, Tnaf.POW_2_WIDTH, -61, 40, -122, 25, 64, 69, 104, 74, 43, 66, 64, -120, -124, -84, 68, 37, 64, -32, 34, -105, 1, 20, ByteCompanionObject.MIN_VALUE, -58, 33, -90, 49, 13, 0, 35, -120, -55, 28, 70, 64, 26, 69, -120, -46, 44, 64, 36, -45, 9, 38, -104, 64, 113, Tnaf.POW_2_WIDTH, 115, -120, -125, ByteCompanionObject.MIN_VALUE, -96, 101, 29, 3, 40, -64};
    }

    private static void createRandomMask42_24() {
        _randomMask42_24 = new byte[]{14, 25, Tnaf.POW_2_WIDTH, 112, -56, ByteCompanionObject.MIN_VALUE, 51, 20, 81, -104, -94, ByteCompanionObject.MIN_VALUE, Tnaf.POW_2_WIDTH, -61, 40, -122, 25, 64, 69, 104, 74, 43, 66, 64, -120, -124, -84, 68, 37, 64, -32, 34, -105, 1, 20, ByteCompanionObject.MIN_VALUE, -58, 33, -90, 49, 13, 0, 35, -120, -55, 28, 70, 64, 26, 69, -120, -46, 44, 64, 36, -45, 9, 38, -104, 64, 113, Tnaf.POW_2_WIDTH, 115, -120, -125, ByteCompanionObject.MIN_VALUE, -96, 101, 29, 3, 40, -64, -58, 33, -90, 49, 13, 0, 35, -120, -55, 28, 70, 64, 26, 69, -120, -46, 44, 64, 36, -45, 9, 38, -104, 64, 113, Tnaf.POW_2_WIDTH, 115, -120, -125, ByteCompanionObject.MIN_VALUE, 14, 25, Tnaf.POW_2_WIDTH, 112, -56, ByteCompanionObject.MIN_VALUE, 51, 20, 81, -104, -94, ByteCompanionObject.MIN_VALUE, Tnaf.POW_2_WIDTH, -61, 40, -122, 25, 64, 69, 104, 74, 43, 66, 64, -120, -124, -84, 68, 37, 64, -32, 34, -105, 1, 20, ByteCompanionObject.MIN_VALUE, 46, 28, -110, -69, 7, -64};
    }

    private static void createRandomMask42_25() {
        _randomMask42_25 = new byte[]{14, 25, Tnaf.POW_2_WIDTH, 112, -56, ByteCompanionObject.MIN_VALUE, 51, 20, 81, -104, -94, ByteCompanionObject.MIN_VALUE, Tnaf.POW_2_WIDTH, -61, 40, -122, 25, 64, 69, 104, 74, 43, 66, 64, -120, -124, -84, 68, 37, 64, -32, 34, -105, 1, 20, ByteCompanionObject.MIN_VALUE, -58, 33, -90, 49, 13, 0, 35, -120, -55, 28, 70, 64, 26, 69, -120, -46, 44, 64, 36, -45, 9, 38, -104, 64, 113, Tnaf.POW_2_WIDTH, 115, -120, -125, ByteCompanionObject.MIN_VALUE, -96, 101, 29, 3, 40, -64, 14, 25, Tnaf.POW_2_WIDTH, 112, -56, ByteCompanionObject.MIN_VALUE, 51, 20, 81, -104, -94, ByteCompanionObject.MIN_VALUE, Tnaf.POW_2_WIDTH, -61, 40, -122, 25, 64, 69, 104, 74, 43, 66, 64, -120, -124, -84, 68, 37, 64, -32, 34, -105, 1, 20, ByteCompanionObject.MIN_VALUE, 70, 17, -110, 48, -116, ByteCompanionObject.MIN_VALUE, 51, 12, -55, -104, 102, 64, Tnaf.POW_2_WIDTH, -28, 96, -121, 35, 0, 12, 105, 8, 99, 72, 64, 40, -108, 41, 68, -95, 64, -108, 33, 52, -95, 9, ByteCompanionObject.MIN_VALUE, -63, 2, 94, 8, 18, -64};
    }

    private static void createRandomMask42_26() {
        _randomMask42_26 = new byte[]{14, 25, Tnaf.POW_2_WIDTH, 112, -56, ByteCompanionObject.MIN_VALUE, 51, 20, 81, -104, -94, ByteCompanionObject.MIN_VALUE, Tnaf.POW_2_WIDTH, -61, 40, -122, 25, 64, 69, 104, 74, 43, 66, 64, -120, -124, -84, 68, 37, 64, -32, 34, -105, 1, 20, ByteCompanionObject.MIN_VALUE, 70, 17, -110, 48, -116, ByteCompanionObject.MIN_VALUE, 51, 12, -55, -104, 102, 64, Tnaf.POW_2_WIDTH, -28, 96, -121, 35, 0, 12, 105, 8, 99, 72, 64, 40, -108, 41, 68, -95, 64, -108, 33, 52, -95, 9, ByteCompanionObject.MIN_VALUE, -63, 2, 94, 8, 18, -64, 14, 25, Tnaf.POW_2_WIDTH, 112, -56, ByteCompanionObject.MIN_VALUE, 51, 20, 81, -104, -94, ByteCompanionObject.MIN_VALUE, Tnaf.POW_2_WIDTH, -61, 40, -122, 25, 64, 69, 104, 74, 43, 66, 64, -120, -124, -84, 68, 37, 64, -32, 34, -105, 1, 20, ByteCompanionObject.MIN_VALUE, -58, 33, -90, 49, 13, 0, 35, -120, -55, 28, 70, 64, 26, 69, -120, -46, 44, 64, 36, -45, 9, 38, -104, 64, 113, Tnaf.POW_2_WIDTH, 115, -120, -125, ByteCompanionObject.MIN_VALUE, -96, 101, 29, 3, 40, -64, -72, 65, -19, -93, 119, -64};
    }

    private static void createRandomMask42_27() {
        _randomMask42_27 = new byte[]{14, 25, Tnaf.POW_2_WIDTH, 112, -56, ByteCompanionObject.MIN_VALUE, 51, 20, 81, -104, -94, ByteCompanionObject.MIN_VALUE, Tnaf.POW_2_WIDTH, -61, 40, -122, 25, 64, 69, 104, 74, 43, 66, 64, -120, -124, -84, 68, 37, 64, -32, 34, -105, 1, 20, ByteCompanionObject.MIN_VALUE, 70, 17, -110, 48, -116, ByteCompanionObject.MIN_VALUE, 51, 12, -55, -104, 102, 64, Tnaf.POW_2_WIDTH, -28, 96, -121, 35, 0, 12, 105, 8, 99, 72, 64, 40, -108, 41, 68, -95, 64, -108, 33, 52, -95, 9, ByteCompanionObject.MIN_VALUE, -63, 2, 94, 8, 18, -64, 70, 17, -110, 48, -116, ByteCompanionObject.MIN_VALUE, 51, 12, -55, -104, 102, 64, Tnaf.POW_2_WIDTH, -28, 96, -121, 35, 0, 12, 105, 8, 99, 72, 64, 40, -108, 41, 68, -95, 64, -108, 33, 52, -95, 9, ByteCompanionObject.MIN_VALUE, -63, 2, 94, 8, 18, -64, 14, 25, Tnaf.POW_2_WIDTH, 112, -56, ByteCompanionObject.MIN_VALUE, 51, 20, 81, -104, -94, ByteCompanionObject.MIN_VALUE, Tnaf.POW_2_WIDTH, -61, 40, -122, 25, 64, 69, 104, 74, 43, 66, 64, -120, -124, -84, 68, 37, 64, -32, 34, -105, 1, 20, ByteCompanionObject.MIN_VALUE, 77, -48, -62, 110, -122, 0};
    }

    private static void createRandomMask42_28() {
        _randomMask42_28 = new byte[]{70, 17, -110, 48, -116, ByteCompanionObject.MIN_VALUE, 51, 12, -55, -104, 102, 64, Tnaf.POW_2_WIDTH, -28, 96, -121, 35, 0, 12, 105, 8, 99, 72, 64, 40, -108, 41, 68, -95, 64, -108, 33, 52, -95, 9, ByteCompanionObject.MIN_VALUE, -63, 2, 94, 8, 18, -64, 14, 25, Tnaf.POW_2_WIDTH, 112, -56, ByteCompanionObject.MIN_VALUE, 51, 20, 81, -104, -94, ByteCompanionObject.MIN_VALUE, Tnaf.POW_2_WIDTH, -61, 40, -122, 25, 64, 69, 104, 74, 43, 66, 64, -120, -124, -84, 68, 37, 64, -32, 34, -105, 1, 20, ByteCompanionObject.MIN_VALUE, 77, -48, -62, 110, -122, 0, 14, 25, Tnaf.POW_2_WIDTH, 112, -56, ByteCompanionObject.MIN_VALUE, 51, 20, 81, -104, -94, ByteCompanionObject.MIN_VALUE, Tnaf.POW_2_WIDTH, -61, 40, -122, 25, 64, 69, 104, 74, 43, 66, 64, -120, -124, -84, 68, 37, 64, -32, 34, -105, 1, 20, ByteCompanionObject.MIN_VALUE, 70, 17, -110, 48, -116, ByteCompanionObject.MIN_VALUE, 51, 12, -55, -104, 102, 64, Tnaf.POW_2_WIDTH, -28, 96, -121, 35, 0, 12, 105, 8, 99, 72, 64, 40, -108, 41, 68, -95, 64, -108, 33, 52, -95, 9, ByteCompanionObject.MIN_VALUE, -63, 2, 94, 8, 18, -64, -61, 60, 86, -62, 48, 64};
    }

    private static void createRandomMask42_29() {
        _randomMask42_29 = new byte[]{70, 17, -110, 48, -116, ByteCompanionObject.MIN_VALUE, 51, 12, -55, -104, 102, 64, Tnaf.POW_2_WIDTH, -28, 96, -121, 35, 0, 12, 105, 8, 99, 72, 64, 40, -108, 41, 68, -95, 64, -108, 33, 52, -95, 9, ByteCompanionObject.MIN_VALUE, -63, 2, 94, 8, 18, -64, 14, 25, Tnaf.POW_2_WIDTH, 112, -56, ByteCompanionObject.MIN_VALUE, 51, 20, 81, -104, -94, ByteCompanionObject.MIN_VALUE, Tnaf.POW_2_WIDTH, -61, 40, -122, 25, 64, 69, 104, 74, 43, 66, 64, -120, -124, -84, 68, 37, 64, -32, 34, -105, 1, 20, ByteCompanionObject.MIN_VALUE, 77, -48, -62, 110, -122, 0, 70, 17, -110, 48, -116, ByteCompanionObject.MIN_VALUE, 51, 12, -55, -104, 102, 64, Tnaf.POW_2_WIDTH, -28, 96, -121, 35, 0, 12, 105, 8, 99, 72, 64, 40, -108, 41, 68, -95, 64, -108, 33, 52, -95, 9, ByteCompanionObject.MIN_VALUE, -63, 2, 94, 8, 18, -64, 44, 3, 33, 96, 25, 0, -127, -96, 28, 13, 0, -64, -96, 104, 37, 3, 65, 0, 5, 65, 80, 42, 10, ByteCompanionObject.MIN_VALUE, 24, -112, -64, -60, -122, 0, -62, 6, -122, Tnaf.POW_2_WIDTH, 52, 0, 34, -104, 9, 20, -64, 64, 80, 69, 10, -126, 40, 64};
    }

    private static void createRandomMask42_3() {
        _randomMask42_3 = new byte[]{-50, 50, -74, 113, -107, ByteCompanionObject.MIN_VALUE, 85, -36, 82, -82, -30, ByteCompanionObject.MIN_VALUE, -88, -19, -115, 71, 108, 64};
    }

    private static void createRandomMask42_30() {
        _randomMask42_30 = new byte[]{70, 17, -110, 48, -116, ByteCompanionObject.MIN_VALUE, 51, 12, -55, -104, 102, 64, Tnaf.POW_2_WIDTH, -28, 96, -121, 35, 0, 12, 105, 8, 99, 72, 64, 40, -108, 41, 68, -95, 64, -108, 33, 52, -95, 9, ByteCompanionObject.MIN_VALUE, -63, 2, 94, 8, 18, -64, 44, 3, 33, 96, 25, 0, -127, -96, 28, 13, 0, -64, -96, 104, 37, 3, 65, 0, 5, 65, 80, 42, 10, ByteCompanionObject.MIN_VALUE, 24, -112, -64, -60, -122, 0, -62, 6, -122, Tnaf.POW_2_WIDTH, 52, 0, 34, -104, 9, 20, -64, 64, 80, 69, 10, -126, 40, 64, 70, 17, -110, 48, -116, ByteCompanionObject.MIN_VALUE, 51, 12, -55, -104, 102, 64, Tnaf.POW_2_WIDTH, -28, 96, -121, 35, 0, 12, 105, 8, 99, 72, 64, 40, -108, 41, 68, -95, 64, -108, 33, 52, -95, 9, ByteCompanionObject.MIN_VALUE, -63, 2, 94, 8, 18, -64, 14, 25, Tnaf.POW_2_WIDTH, 112, -56, ByteCompanionObject.MIN_VALUE, 51, 20, 81, -104, -94, ByteCompanionObject.MIN_VALUE, Tnaf.POW_2_WIDTH, -61, 40, -122, 25, 64, 69, 104, 74, 43, 66, 64, -120, -124, -84, 68, 37, 64, -32, 34, -105, 1, 20, ByteCompanionObject.MIN_VALUE, 77, -48, -62, 110, -122, 0, -11, -35, 13, 88, -21, 0};
    }

    private static void createRandomMask42_31() {
        _randomMask42_31 = new byte[]{70, 17, -110, 48, -116, ByteCompanionObject.MIN_VALUE, 51, 12, -55, -104, 102, 64, Tnaf.POW_2_WIDTH, -28, 96, -121, 35, 0, 12, 105, 8, 99, 72, 64, 40, -108, 41, 68, -95, 64, -108, 33, 52, -95, 9, ByteCompanionObject.MIN_VALUE, -63, 2, 94, 8, 18, -64, 44, 3, 33, 96, 25, 0, -127, -96, 28, 13, 0, -64, -96, 104, 37, 3, 65, 0, 5, 65, 80, 42, 10, ByteCompanionObject.MIN_VALUE, 24, -112, -64, -60, -122, 0, -62, 6, -122, Tnaf.POW_2_WIDTH, 52, 0, 34, -104, 9, 20, -64, 64, 80, 69, 10, -126, 40, 64, 44, 3, 33, 96, 25, 0, -127, -96, 28, 13, 0, -64, -96, 104, 37, 3, 65, 0, 5, 65, 80, 42, 10, ByteCompanionObject.MIN_VALUE, 24, -112, -64, -60, -122, 0, -62, 6, -122, Tnaf.POW_2_WIDTH, 52, 0, 34, -104, 9, 20, -64, 64, 80, 69, 10, -126, 40, 64, 70, 17, -110, 48, -116, ByteCompanionObject.MIN_VALUE, 51, 12, -55, -104, 102, 64, Tnaf.POW_2_WIDTH, -28, 96, -121, 35, 0, 12, 105, 8, 99, 72, 64, 40, -108, 41, 68, -95, 64, -108, 33, 52, -95, 9, ByteCompanionObject.MIN_VALUE, -63, 2, 94, 8, 18, -64, 59, -11, 57, -33, -87, -64};
    }

    private static void createRandomMask42_32() {
        _randomMask42_32 = new byte[]{44, 3, 33, 96, 25, 0, -127, -96, 28, 13, 0, -64, -96, 104, 37, 3, 65, 0, 5, 65, 80, 42, 10, ByteCompanionObject.MIN_VALUE, 24, -112, -64, -60, -122, 0, -62, 6, -122, Tnaf.POW_2_WIDTH, 52, 0, 34, -104, 9, 20, -64, 64, 80, 69, 10, -126, 40, 64, 70, 17, -110, 48, -116, ByteCompanionObject.MIN_VALUE, 51, 12, -55, -104, 102, 64, Tnaf.POW_2_WIDTH, -28, 96, -121, 35, 0, 12, 105, 8, 99, 72, 64, 40, -108, 41, 68, -95, 64, -108, 33, 52, -95, 9, ByteCompanionObject.MIN_VALUE, -63, 2, 94, 8, 18, -64, 59, -11, 57, -33, -87, -64, 70, 17, -110, 48, -116, ByteCompanionObject.MIN_VALUE, 51, 12, -55, -104, 102, 64, Tnaf.POW_2_WIDTH, -28, 96, -121, 35, 0, 12, 105, 8, 99, 72, 64, 40, -108, 41, 68, -95, 64, -108, 33, 52, -95, 9, ByteCompanionObject.MIN_VALUE, -63, 2, 94, 8, 18, -64, 44, 3, 33, 96, 25, 0, -127, -96, 28, 13, 0, -64, -96, 104, 37, 3, 65, 0, 5, 65, 80, 42, 10, ByteCompanionObject.MIN_VALUE, 24, -112, -64, -60, -122, 0, -62, 6, -122, Tnaf.POW_2_WIDTH, 52, 0, 34, -104, 9, 20, -64, 64, 80, 69, 10, -126, 40, 64, -7, 31, -74, -31, 9, -64};
    }

    private static void createRandomMask42_33() {
        _randomMask42_33 = new byte[]{44, 3, 33, 96, 25, 0, -127, -96, 28, 13, 0, -64, -96, 104, 37, 3, 65, 0, 5, 65, 80, 42, 10, ByteCompanionObject.MIN_VALUE, 24, -112, -64, -60, -122, 0, -62, 6, -122, Tnaf.POW_2_WIDTH, 52, 0, 34, -104, 9, 20, -64, 64, 80, 69, 10, -126, 40, 64, 70, 17, -110, 48, -116, ByteCompanionObject.MIN_VALUE, 51, 12, -55, -104, 102, 64, Tnaf.POW_2_WIDTH, -28, 96, -121, 35, 0, 12, 105, 8, 99, 72, 64, 40, -108, 41, 68, -95, 64, -108, 33, 52, -95, 9, ByteCompanionObject.MIN_VALUE, -63, 2, 94, 8, 18, -64, 59, -11, 57, -33, -87, -64, 44, 3, 33, 96, 25, 0, -127, -96, 28, 13, 0, -64, -96, 104, 37, 3, 65, 0, 5, 65, 80, 42, 10, ByteCompanionObject.MIN_VALUE, 24, -112, -64, -60, -122, 0, -62, 6, -122, Tnaf.POW_2_WIDTH, 52, 0, 34, -104, 9, 20, -64, 64, 80, 69, 10, -126, 40, 64, 78, 17, -110, 112, -116, ByteCompanionObject.MIN_VALUE, -29, 24, -97, 24, -60, -64, -127, -29, 4, 15, 24, 0, 33, 64, 89, 10, 2, -64, 82, -127, -30, -108, 15, 0, -76, 40, 37, -95, 65, 0, 38, -122, 41, 52, 49, 64, 88, 100, 66, -61, 34, 0, 25, -98, 0, -52, -16, 0};
    }

    private static void createRandomMask42_34() {
        _randomMask42_34 = new byte[]{44, 3, 33, 96, 25, 0, -127, -96, 28, 13, 0, -64, -96, 104, 37, 3, 65, 0, 5, 65, 80, 42, 10, ByteCompanionObject.MIN_VALUE, 24, -112, -64, -60, -122, 0, -62, 6, -122, Tnaf.POW_2_WIDTH, 52, 0, 34, -104, 9, 20, -64, 64, 80, 69, 10, -126, 40, 64, 78, 17, -110, 112, -116, ByteCompanionObject.MIN_VALUE, -29, 24, -97, 24, -60, -64, -127, -29, 4, 15, 24, 0, 33, 64, 89, 10, 2, -64, 82, -127, -30, -108, 15, 0, -76, 40, 37, -95, 65, 0, 38, -122, 41, 52, 49, 64, 88, 100, 66, -61, 34, 0, 25, -98, 0, -52, -16, 0, 44, 3, 33, 96, 25, 0, -127, -96, 28, 13, 0, -64, -96, 104, 37, 3, 65, 0, 5, 65, 80, 42, 10, ByteCompanionObject.MIN_VALUE, 24, -112, -64, -60, -122, 0, -62, 6, -122, Tnaf.POW_2_WIDTH, 52, 0, 34, -104, 9, 20, -64, 64, 80, 69, 10, -126, 40, 64, 70, 17, -110, 48, -116, ByteCompanionObject.MIN_VALUE, 51, 12, -55, -104, 102, 64, Tnaf.POW_2_WIDTH, -28, 96, -121, 35, 0, 12, 105, 8, 99, 72, 64, 40, -108, 41, 68, -95, 64, -108, 33, 52, -95, 9, ByteCompanionObject.MIN_VALUE, -63, 2, 94, 8, 18, -64, 59, -11, 57, -33, -87, -64, -8, -65, -10, 118, 27, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask42_35() {
        _randomMask42_35 = new byte[]{44, 3, 33, 96, 25, 0, -127, -96, 28, 13, 0, -64, -96, 104, 37, 3, 65, 0, 5, 65, 80, 42, 10, ByteCompanionObject.MIN_VALUE, 24, -112, -64, -60, -122, 0, -62, 6, -122, Tnaf.POW_2_WIDTH, 52, 0, 34, -104, 9, 20, -64, 64, 80, 69, 10, -126, 40, 64, 78, 17, -110, 112, -116, ByteCompanionObject.MIN_VALUE, -29, 24, -97, 24, -60, -64, -127, -29, 4, 15, 24, 0, 33, 64, 89, 10, 2, -64, 82, -127, -30, -108, 15, 0, -76, 40, 37, -95, 65, 0, 38, -122, 41, 52, 49, 64, 88, 100, 66, -61, 34, 0, 25, -98, 0, -52, -16, 0, 78, 17, -110, 112, -116, ByteCompanionObject.MIN_VALUE, -29, 24, -97, 24, -60, -64, -127, -29, 4, 15, 24, 0, 33, 64, 89, 10, 2, -64, 82, -127, -30, -108, 15, 0, -76, 40, 37, -95, 65, 0, 38, -122, 41, 52, 49, 64, 88, 100, 66, -61, 34, 0, 25, -98, 0, -52, -16, 0, 44, 3, 33, 96, 25, 0, -127, -96, 28, 13, 0, -64, -96, 104, 37, 3, 65, 0, 5, 65, 80, 42, 10, ByteCompanionObject.MIN_VALUE, 24, -112, -64, -60, -122, 0, -62, 6, -122, Tnaf.POW_2_WIDTH, 52, 0, 34, -104, 9, 20, -64, 64, 80, 69, 10, -126, 40, 64, 90, 86, 90, -46, -78, -64};
    }

    private static void createRandomMask42_36() {
        _randomMask42_36 = new byte[]{78, 17, -110, 112, -116, ByteCompanionObject.MIN_VALUE, -29, 24, -97, 24, -60, -64, -127, -29, 4, 15, 24, 0, 33, 64, 89, 10, 2, -64, 82, -127, -30, -108, 15, 0, -76, 40, 37, -95, 65, 0, 38, -122, 41, 52, 49, 64, 88, 100, 66, -61, 34, 0, 25, -98, 0, -52, -16, 0, 44, 3, 33, 96, 25, 0, -127, -96, 28, 13, 0, -64, -96, 104, 37, 3, 65, 0, 5, 65, 80, 42, 10, ByteCompanionObject.MIN_VALUE, 24, -112, -64, -60, -122, 0, -62, 6, -122, Tnaf.POW_2_WIDTH, 52, 0, 34, -104, 9, 20, -64, 64, 80, 69, 10, -126, 40, 64, 90, 86, 90, -46, -78, -64, 44, 3, 33, 96, 25, 0, -127, -96, 28, 13, 0, -64, -96, 104, 37, 3, 65, 0, 5, 65, 80, 42, 10, ByteCompanionObject.MIN_VALUE, 24, -112, -64, -60, -122, 0, -62, 6, -122, Tnaf.POW_2_WIDTH, 52, 0, 34, -104, 9, 20, -64, 64, 80, 69, 10, -126, 40, 64, 78, 17, -110, 112, -116, ByteCompanionObject.MIN_VALUE, -29, 24, -97, 24, -60, -64, -127, -29, 4, 15, 24, 0, 33, 64, 89, 10, 2, -64, 82, -127, -30, -108, 15, 0, -76, 40, 37, -95, 65, 0, 38, -122, 41, 52, 49, 64, 88, 100, 66, -61, 34, 0, 25, -98, 0, -52, -16, 0, 87, -57, 3, -7, -58, 0};
    }

    private static void createRandomMask42_37() {
        _randomMask42_37 = new byte[]{78, 17, -110, 112, -116, ByteCompanionObject.MIN_VALUE, -29, 24, -97, 24, -60, -64, -127, -29, 4, 15, 24, 0, 33, 64, 89, 10, 2, -64, 82, -127, -30, -108, 15, 0, -76, 40, 37, -95, 65, 0, 38, -122, 41, 52, 49, 64, 88, 100, 66, -61, 34, 0, 25, -98, 0, -52, -16, 0, 44, 3, 33, 96, 25, 0, -127, -96, 28, 13, 0, -64, -96, 104, 37, 3, 65, 0, 5, 65, 80, 42, 10, ByteCompanionObject.MIN_VALUE, 24, -112, -64, -60, -122, 0, -62, 6, -122, Tnaf.POW_2_WIDTH, 52, 0, 34, -104, 9, 20, -64, 64, 80, 69, 10, -126, 40, 64, 90, 86, 90, -46, -78, -64, 78, 17, -110, 112, -116, ByteCompanionObject.MIN_VALUE, -29, 24, -97, 24, -60, -64, -127, -29, 4, 15, 24, 0, 33, 64, 89, 10, 2, -64, 82, -127, -30, -108, 15, 0, -76, 40, 37, -95, 65, 0, 38, -122, 41, 52, 49, 64, 88, 100, 66, -61, 34, 0, 25, -98, 0, -52, -16, 0, 76, 25, 18, 96, -56, ByteCompanionObject.MIN_VALUE, 81, 20, 82, -120, -94, ByteCompanionObject.MIN_VALUE, -96, 106, 69, 3, 82, 0, 4, -63, 48, 38, 9, ByteCompanionObject.MIN_VALUE, 3, -76, 0, 29, -96, 0, -122, 32, -108, 49, 4, ByteCompanionObject.MIN_VALUE, 41, 8, 73, 72, 66, 64, 66, 67, 10, 18, 24, 64, -104, 18, -124, -64, -108, 0, 48, -124, -87, -124, 37, 64};
    }

    private static void createRandomMask42_38() {
        _randomMask42_38 = new byte[]{78, 17, -110, 112, -116, ByteCompanionObject.MIN_VALUE, -29, 24, -97, 24, -60, -64, -127, -29, 4, 15, 24, 0, 33, 64, 89, 10, 2, -64, 82, -127, -30, -108, 15, 0, -76, 40, 37, -95, 65, 0, 38, -122, 41, 52, 49, 64, 88, 100, 66, -61, 34, 0, 25, -98, 0, -52, -16, 0, 76, 25, 18, 96, -56, ByteCompanionObject.MIN_VALUE, 81, 20, 82, -120, -94, ByteCompanionObject.MIN_VALUE, -96, 106, 69, 3, 82, 0, 4, -63, 48, 38, 9, ByteCompanionObject.MIN_VALUE, 3, -76, 0, 29, -96, 0, -122, 32, -108, 49, 4, ByteCompanionObject.MIN_VALUE, 41, 8, 73, 72, 66, 64, 66, 67, 10, 18, 24, 64, -104, 18, -124, -64, -108, 0, 48, -124, -87, -124, 37, 64, 78, 17, -110, 112, -116, ByteCompanionObject.MIN_VALUE, -29, 24, -97, 24, -60, -64, -127, -29, 4, 15, 24, 0, 33, 64, 89, 10, 2, -64, 82, -127, -30, -108, 15, 0, -76, 40, 37, -95, 65, 0, 38, -122, 41, 52, 49, 64, 88, 100, 66, -61, 34, 0, 25, -98, 0, -52, -16, 0, 44, 3, 33, 96, 25, 0, -127, -96, 28, 13, 0, -64, -96, 104, 37, 3, 65, 0, 5, 65, 80, 42, 10, ByteCompanionObject.MIN_VALUE, 24, -112, -64, -60, -122, 0, -62, 6, -122, Tnaf.POW_2_WIDTH, 52, 0, 34, -104, 9, 20, -64, 64, 80, 69, 10, -126, 40, 64, 90, 86, 90, -46, -78, -64, 5, 25, 85, -18, -30, -64};
    }

    private static void createRandomMask42_39() {
        _randomMask42_39 = new byte[]{78, 17, -110, 112, -116, ByteCompanionObject.MIN_VALUE, -29, 24, -97, 24, -60, -64, -127, -29, 4, 15, 24, 0, 33, 64, 89, 10, 2, -64, 82, -127, -30, -108, 15, 0, -76, 40, 37, -95, 65, 0, 38, -122, 41, 52, 49, 64, 88, 100, 66, -61, 34, 0, 25, -98, 0, -52, -16, 0, 76, 25, 18, 96, -56, ByteCompanionObject.MIN_VALUE, 81, 20, 82, -120, -94, ByteCompanionObject.MIN_VALUE, -96, 106, 69, 3, 82, 0, 4, -63, 48, 38, 9, ByteCompanionObject.MIN_VALUE, 3, -76, 0, 29, -96, 0, -122, 32, -108, 49, 4, ByteCompanionObject.MIN_VALUE, 41, 8, 73, 72, 66, 64, 66, 67, 10, 18, 24, 64, -104, 18, -124, -64, -108, 0, 48, -124, -87, -124, 37, 64, 76, 25, 18, 96, -56, ByteCompanionObject.MIN_VALUE, 81, 20, 82, -120, -94, ByteCompanionObject.MIN_VALUE, -96, 106, 69, 3, 82, 0, 4, -63, 48, 38, 9, ByteCompanionObject.MIN_VALUE, 3, -76, 0, 29, -96, 0, -122, 32, -108, 49, 4, ByteCompanionObject.MIN_VALUE, 41, 8, 73, 72, 66, 64, 66, 67, 10, 18, 24, 64, -104, 18, -124, -64, -108, 0, 48, -124, -87, -124, 37, 64, 78, 17, -110, 112, -116, ByteCompanionObject.MIN_VALUE, -29, 24, -97, 24, -60, -64, -127, -29, 4, 15, 24, 0, 33, 64, 89, 10, 2, -64, 82, -127, -30, -108, 15, 0, -76, 40, 37, -95, 65, 0, 38, -122, 41, 52, 49, 64, 88, 100, 66, -61, 34, 0, 25, -98, 0, -52, -16, 0, 42, 3, 49, 80, 25, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask42_4() {
        _randomMask42_4 = new byte[]{-26, 49, 55, 49, -119, ByteCompanionObject.MIN_VALUE, 51, -116, 89, -100, 98, -64, -104, -46, -52, -58, -106, 64, 45, 75, 41, 106, 89, 64};
    }

    private static void createRandomMask42_40() {
        _randomMask42_40 = new byte[]{76, 25, 18, 96, -56, ByteCompanionObject.MIN_VALUE, 81, 20, 82, -120, -94, ByteCompanionObject.MIN_VALUE, -96, 106, 69, 3, 82, 0, 4, -63, 48, 38, 9, ByteCompanionObject.MIN_VALUE, 3, -76, 0, 29, -96, 0, -122, 32, -108, 49, 4, ByteCompanionObject.MIN_VALUE, 41, 8, 73, 72, 66, 64, 66, 67, 10, 18, 24, 64, -104, 18, -124, -64, -108, 0, 48, -124, -87, -124, 37, 64, 78, 17, -110, 112, -116, ByteCompanionObject.MIN_VALUE, -29, 24, -97, 24, -60, -64, -127, -29, 4, 15, 24, 0, 33, 64, 89, 10, 2, -64, 82, -127, -30, -108, 15, 0, -76, 40, 37, -95, 65, 0, 38, -122, 41, 52, 49, 64, 88, 100, 66, -61, 34, 0, 25, -98, 0, -52, -16, 0, 42, 3, 49, 80, 25, ByteCompanionObject.MIN_VALUE, 78, 17, -110, 112, -116, ByteCompanionObject.MIN_VALUE, -29, 24, -97, 24, -60, -64, -127, -29, 4, 15, 24, 0, 33, 64, 89, 10, 2, -64, 82, -127, -30, -108, 15, 0, -76, 40, 37, -95, 65, 0, 38, -122, 41, 52, 49, 64, 88, 100, 66, -61, 34, 0, 25, -98, 0, -52, -16, 0, 76, 25, 18, 96, -56, ByteCompanionObject.MIN_VALUE, 81, 20, 82, -120, -94, ByteCompanionObject.MIN_VALUE, -96, 106, 69, 3, 82, 0, 4, -63, 48, 38, 9, ByteCompanionObject.MIN_VALUE, 3, -76, 0, 29, -96, 0, -122, 32, -108, 49, 4, ByteCompanionObject.MIN_VALUE, 41, 8, 73, 72, 66, 64, 66, 67, 10, 18, 24, 64, -104, 18, -124, -64, -108, 0, 48, -124, -87, -124, 37, 64, -7, -37, 93, 122, -44, 64};
    }

    private static void createRandomMask42_41() {
        _randomMask42_41 = new byte[]{76, 25, 18, 96, -56, ByteCompanionObject.MIN_VALUE, 81, 20, 82, -120, -94, ByteCompanionObject.MIN_VALUE, -96, 106, 69, 3, 82, 0, 4, -63, 48, 38, 9, ByteCompanionObject.MIN_VALUE, 3, -76, 0, 29, -96, 0, -122, 32, -108, 49, 4, ByteCompanionObject.MIN_VALUE, 41, 8, 73, 72, 66, 64, 66, 67, 10, 18, 24, 64, -104, 18, -124, -64, -108, 0, 48, -124, -87, -124, 37, 64, 78, 17, -110, 112, -116, ByteCompanionObject.MIN_VALUE, -29, 24, -97, 24, -60, -64, -127, -29, 4, 15, 24, 0, 33, 64, 89, 10, 2, -64, 82, -127, -30, -108, 15, 0, -76, 40, 37, -95, 65, 0, 38, -122, 41, 52, 49, 64, 88, 100, 66, -61, 34, 0, 25, -98, 0, -52, -16, 0, 42, 3, 49, 80, 25, ByteCompanionObject.MIN_VALUE, 76, 25, 18, 96, -56, ByteCompanionObject.MIN_VALUE, 81, 20, 82, -120, -94, ByteCompanionObject.MIN_VALUE, -96, 106, 69, 3, 82, 0, 4, -63, 48, 38, 9, ByteCompanionObject.MIN_VALUE, 3, -76, 0, 29, -96, 0, -122, 32, -108, 49, 4, ByteCompanionObject.MIN_VALUE, 41, 8, 73, 72, 66, 64, 66, 67, 10, 18, 24, 64, -104, 18, -124, -64, -108, 0, 48, -124, -87, -124, 37, 64, 76, 17, -110, 96, -116, ByteCompanionObject.MIN_VALUE, 81, 12, -54, -120, 102, 64, -96, 102, 69, 3, 50, 0, 4, -63, 96, 38, 11, 0, 3, -96, 40, 29, 1, 64, -122, 33, 20, 49, 8, ByteCompanionObject.MIN_VALUE, 41, Tnaf.POW_2_WIDTH, 25, 72, ByteCompanionObject.MIN_VALUE, -64, 66, 66, -94, 18, 21, 0, -104, 26, 4, -64, -48, 0, 48, -124, 9, -124, 32, 64, -33, 76, 22, -6, 96, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask42_42() {
        _randomMask42_42 = new byte[]{76, 25, 18, 96, -56, ByteCompanionObject.MIN_VALUE, 81, 20, 82, -120, -94, ByteCompanionObject.MIN_VALUE, -96, 106, 69, 3, 82, 0, 4, -63, 48, 38, 9, ByteCompanionObject.MIN_VALUE, 3, -76, 0, 29, -96, 0, -122, 32, -108, 49, 4, ByteCompanionObject.MIN_VALUE, 41, 8, 73, 72, 66, 64, 66, 67, 10, 18, 24, 64, -104, 18, -124, -64, -108, 0, 48, -124, -87, -124, 37, 64, 76, 17, -110, 96, -116, ByteCompanionObject.MIN_VALUE, 81, 12, -54, -120, 102, 64, -96, 102, 69, 3, 50, 0, 4, -63, 96, 38, 11, 0, 3, -96, 40, 29, 1, 64, -122, 33, 20, 49, 8, ByteCompanionObject.MIN_VALUE, 41, Tnaf.POW_2_WIDTH, 25, 72, ByteCompanionObject.MIN_VALUE, -64, 66, 66, -94, 18, 21, 0, -104, 26, 4, -64, -48, 0, 48, -124, 9, -124, 32, 64, -33, 76, 22, -6, 96, ByteCompanionObject.MIN_VALUE, 76, 25, 18, 96, -56, ByteCompanionObject.MIN_VALUE, 81, 20, 82, -120, -94, ByteCompanionObject.MIN_VALUE, -96, 106, 69, 3, 82, 0, 4, -63, 48, 38, 9, ByteCompanionObject.MIN_VALUE, 3, -76, 0, 29, -96, 0, -122, 32, -108, 49, 4, ByteCompanionObject.MIN_VALUE, 41, 8, 73, 72, 66, 64, 66, 67, 10, 18, 24, 64, -104, 18, -124, -64, -108, 0, 48, -124, -87, -124, 37, 64, 78, 17, -110, 112, -116, ByteCompanionObject.MIN_VALUE, -29, 24, -97, 24, -60, -64, -127, -29, 4, 15, 24, 0, 33, 64, 89, 10, 2, -64, 82, -127, -30, -108, 15, 0, -76, 40, 37, -95, 65, 0, 38, -122, 41, 52, 49, 64, 88, 100, 66, -61, 34, 0, 25, -98, 0, -52, -16, 0, 42, 3, 49, 80, 25, ByteCompanionObject.MIN_VALUE, -22, -98, 35, -77, 101, 0};
    }

    private static void createRandomMask42_5() {
        _randomMask42_5 = new byte[]{-50, 49, -74, 113, -115, ByteCompanionObject.MIN_VALUE, 99, -104, -37, 28, -58, -64, -104, -57, 108, -58, 59, 64, 77, 107, 82, 107, 90, ByteCompanionObject.MIN_VALUE, -78, 108, -83, -109, 101, 64};
    }

    private static void createRandomMask42_6() {
        _randomMask42_6 = new byte[]{76, 25, 18, 96, -56, ByteCompanionObject.MIN_VALUE, 81, 20, 82, -120, -94, ByteCompanionObject.MIN_VALUE, 32, -22, 9, 7, 80, 64, -123, 65, 44, 42, 9, 64, 6, ByteCompanionObject.MIN_VALUE, -40, 52, 6, -64, -118, 36, 52, 81, 33, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask42_7() {
        _randomMask42_7 = new byte[]{-58, 17, -106, 48, -116, ByteCompanionObject.MIN_VALUE, 51, 4, -55, -104, 38, 64, 24, 103, 64, -61, 58, 0, 69, 66, -46, 42, 22, ByteCompanionObject.MIN_VALUE, 18, -44, 40, -106, -95, 64, -76, 40, 53, -95, 65, ByteCompanionObject.MIN_VALUE, 41, -110, 25, 76, -112, -64};
    }

    private static void createRandomMask42_8() {
        _randomMask42_8 = new byte[]{7, 10, 112, 56, 83, ByteCompanionObject.MIN_VALUE, 73, -88, 42, 77, 65, 64, -80, 122, 5, -125, -48, 0, 36, -59, -63, 38, 46, 0, 82, ByteCompanionObject.MIN_VALUE, -22, -108, 7, 64, -58, 49, -122, 49, -116, 0, 49, -108, 25, -116, -96, -64, 24, -57, 8, -58, 56, 64};
    }

    private static void createRandomMask42_9() {
        _randomMask42_9 = new byte[]{78, 17, 18, 112, -120, ByteCompanionObject.MIN_VALUE, 98, 26, 11, Tnaf.POW_2_WIDTH, -48, 64, ByteCompanionObject.MIN_VALUE, -23, 68, 7, 74, 0, -95, 80, 85, 10, -126, ByteCompanionObject.MIN_VALUE, 83, 0, 106, -104, 3, 64, -92, 36, 53, 33, 33, ByteCompanionObject.MIN_VALUE, 22, -96, -120, -75, 4, 64, 88, 69, 34, -62, 41, 0, 41, -122, -127, 76, 52, 0};
    }

    private static void createRandomMask43_1() {
        _randomMask43_1 = new byte[]{-1, -1, -1, -1, -1, -32};
    }

    private static void createRandomMask43_10() {
        _randomMask43_10 = new byte[]{76, 25, 22, 1, -60, 64, 81, 20, 81, ByteCompanionObject.MIN_VALUE, 113, 64, -96, 106, 71, 64, 56, 0, 4, -63, 52, 40, 69, 64, 3, -76, 6, -124, -112, ByteCompanionObject.MIN_VALUE, -122, 32, -108, 50, -126, 64, 41, 8, 74, 83, 64, 96, 66, 67, 8, 13, 3, -96, -104, 18, -126, 100, 12, ByteCompanionObject.MIN_VALUE, 48, -124, -85, 17, 32, 32};
    }

    private static void createRandomMask43_11() {
        _randomMask43_11 = new byte[]{-58, 33, -94, 50, 70, 64, 35, -120, -55, -103, 51, 32, 26, 69, -116, -56, -103, 0, 36, -45, 8, 44, 5, ByteCompanionObject.MIN_VALUE, 113, Tnaf.POW_2_WIDTH, 116, 5, ByteCompanionObject.MIN_VALUE, -96, 14, 25, 20, 34, -124, 64, 51, 20, 82, 3, 64, 96, Tnaf.POW_2_WIDTH, -61, 40, 84, 10, ByteCompanionObject.MIN_VALUE, 69, 104, 75, 64, 104, 0, -120, -124, -88, -127, Tnaf.POW_2_WIDTH, 32, -32, 34, -111, -126, 48, 64};
    }

    private static void createRandomMask43_12() {
        _randomMask43_12 = new byte[]{14, 25, 19, 34, 100, 64, 51, 20, 82, -118, 81, 64, Tnaf.POW_2_WIDTH, -61, 40, 101, 12, -96, 69, 104, 77, 9, -95, 32, -120, -124, -88, -107, 18, -96, -32, 34, -108, 82, -118, 64, -58, 33, -92, 52, -122, ByteCompanionObject.MIN_VALUE, 35, -120, -55, 25, 35, 32, 26, 69, -120, -79, 22, 32, 36, -45, 10, 97, 76, 32, 113, Tnaf.POW_2_WIDTH, 114, 14, 65, -64, -96, 101, 31, -96, -60, -32};
    }

    private static void createRandomMask43_13() {
        _randomMask43_13 = new byte[]{14, 25, 19, 34, 100, 64, 51, 20, 82, -118, 81, 64, Tnaf.POW_2_WIDTH, -61, 40, 101, 12, -96, 69, 104, 77, 9, -95, 32, -120, -124, -88, -107, 18, -96, -32, 34, -108, 82, -118, 64, 70, 17, -110, 50, 70, 64, 51, 12, -55, -103, 51, 32, Tnaf.POW_2_WIDTH, -28, 100, -116, -111, ByteCompanionObject.MIN_VALUE, 12, 105, 13, 33, -92, 32, 40, -108, 42, -123, 80, -96, -108, 33, 52, 38, -124, -64, -63, 2, 88, 75, 9, 96};
    }

    private static void createRandomMask43_14() {
        _randomMask43_14 = new byte[]{70, 17, -110, 50, 70, 64, 51, 12, -55, -103, 51, 32, Tnaf.POW_2_WIDTH, -28, 100, -116, -111, ByteCompanionObject.MIN_VALUE, 12, 105, 13, 33, -92, 32, 40, -108, 42, -123, 80, -96, -108, 33, 52, 38, -124, -64, -63, 2, 88, 75, 9, 96, 14, 25, 19, 34, 100, 64, 51, 20, 82, -118, 81, 64, Tnaf.POW_2_WIDTH, -61, 40, 101, 12, -96, 69, 104, 77, 9, -95, 32, -120, -124, -88, -107, 18, -96, -32, 34, -108, 82, -118, 64, 77, -48, -58, 54, 87, 64};
    }

    private static void createRandomMask43_15() {
        _randomMask43_15 = new byte[]{70, 17, -110, 50, 70, 64, 51, 12, -55, -103, 51, 32, Tnaf.POW_2_WIDTH, -28, 100, -116, -111, ByteCompanionObject.MIN_VALUE, 12, 105, 13, 33, -92, 32, 40, -108, 42, -123, 80, -96, -108, 33, 52, 38, -124, -64, -63, 2, 88, 75, 9, 96, 44, 3, 32, 100, 12, ByteCompanionObject.MIN_VALUE, -127, -96, 28, 3, ByteCompanionObject.MIN_VALUE, 96, -96, 104, 37, 4, -96, ByteCompanionObject.MIN_VALUE, 5, 65, 80, 42, 5, 64, 24, -112, -62, 24, 67, 0, -62, 6, ByteCompanionObject.MIN_VALUE, -48, 26, 0, 34, -104, 11, 1, 96, 32, 80, 69, 8, -95, 20, 32};
    }

    private static void createRandomMask43_16() {
        _randomMask43_16 = new byte[]{44, 3, 32, 100, 12, ByteCompanionObject.MIN_VALUE, -127, -96, 28, 3, ByteCompanionObject.MIN_VALUE, 96, -96, 104, 37, 4, -96, ByteCompanionObject.MIN_VALUE, 5, 65, 80, 42, 5, 64, 24, -112, -62, 24, 67, 0, -62, 6, ByteCompanionObject.MIN_VALUE, -48, 26, 0, 34, -104, 11, 1, 96, 32, 80, 69, 8, -95, 20, 32, 70, 17, -110, 50, 70, 64, 51, 12, -55, -103, 51, 32, Tnaf.POW_2_WIDTH, -28, 100, -116, -111, ByteCompanionObject.MIN_VALUE, 12, 105, 13, 33, -92, 32, 40, -108, 42, -123, 80, -96, -108, 33, 52, 38, -124, -64, -63, 2, 88, 75, 9, 96, 59, -11, 60, 54, 10, 32};
    }

    private static void createRandomMask43_17() {
        _randomMask43_17 = new byte[]{44, 3, 32, 100, 12, ByteCompanionObject.MIN_VALUE, -127, -96, 28, 3, ByteCompanionObject.MIN_VALUE, 96, -96, 104, 37, 4, -96, ByteCompanionObject.MIN_VALUE, 5, 65, 80, 42, 5, 64, 24, -112, -62, 24, 67, 0, -62, 6, ByteCompanionObject.MIN_VALUE, -48, 26, 0, 34, -104, 11, 1, 96, 32, 80, 69, 8, -95, 20, 32, 78, 17, -110, 50, 70, 64, -29, 24, -101, 19, 98, 96, -127, -29, 4, 96, -116, 0, 33, 64, 88, 11, 1, 96, 82, -127, -32, 60, 7, ByteCompanionObject.MIN_VALUE, -76, 40, 37, 4, -96, ByteCompanionObject.MIN_VALUE, 38, -122, 40, -59, 24, -96, 88, 100, 68, -120, -111, 0, 25, -98, 3, -64, 120, 0};
    }

    private static void createRandomMask43_18() {
        _randomMask43_18 = new byte[]{78, 17, -110, 50, 70, 64, -29, 24, -101, 19, 98, 96, -127, -29, 4, 96, -116, 0, 33, 64, 88, 11, 1, 96, 82, -127, -32, 60, 7, ByteCompanionObject.MIN_VALUE, -76, 40, 37, 4, -96, ByteCompanionObject.MIN_VALUE, 38, -122, 40, -59, 24, -96, 88, 100, 68, -120, -111, 0, 25, -98, 3, -64, 120, 0, 44, 3, 32, 100, 12, ByteCompanionObject.MIN_VALUE, -127, -96, 28, 3, ByteCompanionObject.MIN_VALUE, 96, -96, 104, 37, 4, -96, ByteCompanionObject.MIN_VALUE, 5, 65, 80, 42, 5, 64, 24, -112, -62, 24, 67, 0, -62, 6, ByteCompanionObject.MIN_VALUE, -48, 26, 0, 34, -104, 11, 1, 96, 32, 80, 69, 8, -95, 20, 32, 90, 86, 95, 38, -93, 96};
    }

    private static void createRandomMask43_19() {
        _randomMask43_19 = new byte[]{78, 17, -110, 50, 70, 64, -29, 24, -101, 19, 98, 96, -127, -29, 4, 96, -116, 0, 33, 64, 88, 11, 1, 96, 82, -127, -32, 60, 7, ByteCompanionObject.MIN_VALUE, -76, 40, 37, 4, -96, ByteCompanionObject.MIN_VALUE, 38, -122, 40, -59, 24, -96, 88, 100, 68, -120, -111, 0, 25, -98, 3, -64, 120, 0, 76, 25, 19, 34, 100, 64, 81, 20, 82, -118, 81, 64, -96, 106, 69, 72, -87, 0, 4, -63, 48, 38, 4, -64, 3, -76, 6, ByteCompanionObject.MIN_VALUE, -48, 0, -122, 32, -108, 18, -126, 64, 41, 8, 73, 9, 33, 32, 66, 67, 8, 97, 12, 32, -104, 18, -126, 80, 74, 0, 48, -124, -88, -107, 18, -96};
    }

    private static void createRandomMask43_2() {
        _randomMask43_2 = new byte[]{-18, 59, 55, 102, -20, -64, -103, -26, -20, -35, -101, -96};
    }

    private static void createRandomMask43_20() {
        _randomMask43_20 = new byte[]{76, 25, 19, 34, 100, 64, 81, 20, 82, -118, 81, 64, -96, 106, 69, 72, -87, 0, 4, -63, 48, 38, 4, -64, 3, -76, 6, ByteCompanionObject.MIN_VALUE, -48, 0, -122, 32, -108, 18, -126, 64, 41, 8, 73, 9, 33, 32, 66, 67, 8, 97, 12, 32, -104, 18, -126, 80, 74, 0, 48, -124, -88, -107, 18, -96, 78, 17, -110, 50, 70, 64, -29, 24, -101, 19, 98, 96, -127, -29, 4, 96, -116, 0, 33, 64, 88, 11, 1, 96, 82, -127, -32, 60, 7, ByteCompanionObject.MIN_VALUE, -76, 40, 37, 4, -96, ByteCompanionObject.MIN_VALUE, 38, -122, 40, -59, 24, -96, 88, 100, 68, -120, -111, 0, 25, -98, 3, -64, 120, 0, 42, 3, 49, -38, 70, 32};
    }

    private static void createRandomMask43_21() {
        _randomMask43_21 = new byte[]{76, 25, 19, 34, 100, 64, 81, 20, 82, -118, 81, 64, -96, 106, 69, 72, -87, 0, 4, -63, 48, 38, 4, -64, 3, -76, 6, ByteCompanionObject.MIN_VALUE, -48, 0, -122, 32, -108, 18, -126, 64, 41, 8, 73, 9, 33, 32, 66, 67, 8, 97, 12, 32, -104, 18, -126, 80, 74, 0, 48, -124, -88, -107, 18, -96, 76, 17, -110, 50, 70, 64, 81, 12, -55, -103, 51, 32, -96, 102, 68, -56, -103, 0, 4, -63, 96, 44, 5, ByteCompanionObject.MIN_VALUE, 3, -96, 44, 5, ByteCompanionObject.MIN_VALUE, -96, -122, 33, 20, 34, -124, 64, 41, Tnaf.POW_2_WIDTH, 26, 3, 64, 96, 66, 66, -96, 84, 10, ByteCompanionObject.MIN_VALUE, -104, 26, 3, 64, 104, 0, 48, -124, 8, -127, Tnaf.POW_2_WIDTH, 32, -33, 76, 17, -126, 48, 64};
    }

    private static void createRandomMask43_22() {
        _randomMask43_22 = new byte[]{-58, 33, -94, 50, 70, 64, 35, -120, -55, -103, 51, 32, 26, 69, -116, -56, -103, 0, 36, -45, 8, 44, 5, ByteCompanionObject.MIN_VALUE, 113, Tnaf.POW_2_WIDTH, 116, 5, ByteCompanionObject.MIN_VALUE, -96, 14, 25, 20, 34, -124, 64, 51, 20, 82, 3, 64, 96, Tnaf.POW_2_WIDTH, -61, 40, 84, 10, ByteCompanionObject.MIN_VALUE, 69, 104, 75, 64, 104, 0, -120, -124, -88, -127, Tnaf.POW_2_WIDTH, 32, -32, 34, -111, -126, 48, 64, 76, 25, 22, 1, -60, 64, 81, 20, 81, ByteCompanionObject.MIN_VALUE, 113, 64, -96, 106, 71, 64, 56, 0, 4, -63, 52, 40, 69, 64, 3, -76, 6, -124, -112, ByteCompanionObject.MIN_VALUE, -122, 32, -108, 50, -126, 64, 41, 8, 74, 83, 64, 96, 66, 67, 8, 13, 3, -96, -104, 18, -126, 100, 12, ByteCompanionObject.MIN_VALUE, 48, -124, -85, 17, 32, 32, -2, 44, -123, -52, 36, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask43_23() {
        _randomMask43_23 = new byte[]{-58, 33, -94, 50, 70, 64, 35, -120, -55, -103, 51, 32, 26, 69, -116, -56, -103, 0, 36, -45, 8, 44, 5, ByteCompanionObject.MIN_VALUE, 113, Tnaf.POW_2_WIDTH, 116, 5, ByteCompanionObject.MIN_VALUE, -96, 14, 25, 20, 34, -124, 64, 51, 20, 82, 3, 64, 96, Tnaf.POW_2_WIDTH, -61, 40, 84, 10, ByteCompanionObject.MIN_VALUE, 69, 104, 75, 64, 104, 0, -120, -124, -88, -127, Tnaf.POW_2_WIDTH, 32, -32, 34, -111, -126, 48, 64, 14, 25, 19, 34, 100, 64, 51, 20, 82, -118, 81, 64, Tnaf.POW_2_WIDTH, -61, 40, 101, 12, -96, 69, 104, 77, 9, -95, 32, -120, -124, -88, -107, 18, -96, -32, 34, -108, 82, -118, 64, -58, 33, -92, 52, -122, ByteCompanionObject.MIN_VALUE, 35, -120, -55, 25, 35, 32, 26, 69, -120, -79, 22, 32, 36, -45, 10, 97, 76, 32, 113, Tnaf.POW_2_WIDTH, 114, 14, 65, -64, -96, 101, 31, -96, -60, -32};
    }

    private static void createRandomMask43_24() {
        _randomMask43_24 = new byte[]{14, 25, 19, 34, 100, 64, 51, 20, 82, -118, 81, 64, Tnaf.POW_2_WIDTH, -61, 40, 101, 12, -96, 69, 104, 77, 9, -95, 32, -120, -124, -88, -107, 18, -96, -32, 34, -108, 82, -118, 64, -58, 33, -92, 52, -122, ByteCompanionObject.MIN_VALUE, 35, -120, -55, 25, 35, 32, 26, 69, -120, -79, 22, 32, 36, -45, 10, 97, 76, 32, 113, Tnaf.POW_2_WIDTH, 114, 14, 65, -64, -96, 101, 31, -96, -60, -32, -58, 33, -94, 50, 70, 64, 35, -120, -55, -103, 51, 32, 26, 69, -116, -56, -103, 0, 36, -45, 8, 44, 5, ByteCompanionObject.MIN_VALUE, 113, Tnaf.POW_2_WIDTH, 116, 5, ByteCompanionObject.MIN_VALUE, -96, 14, 25, 20, 34, -124, 64, 51, 20, 82, 3, 64, 96, Tnaf.POW_2_WIDTH, -61, 40, 84, 10, ByteCompanionObject.MIN_VALUE, 69, 104, 75, 64, 104, 0, -120, -124, -88, -127, Tnaf.POW_2_WIDTH, 32, -32, 34, -111, -126, 48, 64, -7, -79, 38, 108, 81, -32};
    }

    private static void createRandomMask43_25() {
        _randomMask43_25 = new byte[]{14, 25, 19, 34, 100, 64, 51, 20, 82, -118, 81, 64, Tnaf.POW_2_WIDTH, -61, 40, 101, 12, -96, 69, 104, 77, 9, -95, 32, -120, -124, -88, -107, 18, -96, -32, 34, -108, 82, -118, 64, -58, 33, -92, 52, -122, ByteCompanionObject.MIN_VALUE, 35, -120, -55, 25, 35, 32, 26, 69, -120, -79, 22, 32, 36, -45, 10, 97, 76, 32, 113, Tnaf.POW_2_WIDTH, 114, 14, 65, -64, -96, 101, 31, -96, -60, -32, 14, 25, 19, 34, 100, 64, 51, 20, 82, -118, 81, 64, Tnaf.POW_2_WIDTH, -61, 40, 101, 12, -96, 69, 104, 77, 9, -95, 32, -120, -124, -88, -107, 18, -96, -32, 34, -108, 82, -118, 64, 70, 17, -110, 50, 70, 64, 51, 12, -55, -103, 51, 32, Tnaf.POW_2_WIDTH, -28, 100, -116, -111, ByteCompanionObject.MIN_VALUE, 12, 105, 13, 33, -92, 32, 40, -108, 42, -123, 80, -96, -108, 33, 52, 38, -124, -64, -63, 2, 88, 75, 9, 96};
    }

    private static void createRandomMask43_26() {
        _randomMask43_26 = new byte[]{14, 25, 19, 34, 100, 64, 51, 20, 82, -118, 81, 64, Tnaf.POW_2_WIDTH, -61, 40, 101, 12, -96, 69, 104, 77, 9, -95, 32, -120, -124, -88, -107, 18, -96, -32, 34, -108, 82, -118, 64, 70, 17, -110, 50, 70, 64, 51, 12, -55, -103, 51, 32, Tnaf.POW_2_WIDTH, -28, 100, -116, -111, ByteCompanionObject.MIN_VALUE, 12, 105, 13, 33, -92, 32, 40, -108, 42, -123, 80, -96, -108, 33, 52, 38, -124, -64, -63, 2, 88, 75, 9, 96, 14, 25, 19, 34, 100, 64, 51, 20, 82, -118, 81, 64, Tnaf.POW_2_WIDTH, -61, 40, 101, 12, -96, 69, 104, 77, 9, -95, 32, -120, -124, -88, -107, 18, -96, -32, 34, -108, 82, -118, 64, -58, 33, -92, 52, -122, ByteCompanionObject.MIN_VALUE, 35, -120, -55, 25, 35, 32, 26, 69, -120, -79, 22, 32, 36, -45, 10, 97, 76, 32, 113, Tnaf.POW_2_WIDTH, 114, 14, 65, -64, -96, 101, 31, -96, -60, -32, -17, -124, 119, -54, 13, 64};
    }

    private static void createRandomMask43_27() {
        _randomMask43_27 = new byte[]{14, 25, 19, 34, 100, 64, 51, 20, 82, -118, 81, 64, Tnaf.POW_2_WIDTH, -61, 40, 101, 12, -96, 69, 104, 77, 9, -95, 32, -120, -124, -88, -107, 18, -96, -32, 34, -108, 82, -118, 64, 70, 17, -110, 50, 70, 64, 51, 12, -55, -103, 51, 32, Tnaf.POW_2_WIDTH, -28, 100, -116, -111, ByteCompanionObject.MIN_VALUE, 12, 105, 13, 33, -92, 32, 40, -108, 42, -123, 80, -96, -108, 33, 52, 38, -124, -64, -63, 2, 88, 75, 9, 96, 70, 17, -110, 50, 70, 64, 51, 12, -55, -103, 51, 32, Tnaf.POW_2_WIDTH, -28, 100, -116, -111, ByteCompanionObject.MIN_VALUE, 12, 105, 13, 33, -92, 32, 40, -108, 42, -123, 80, -96, -108, 33, 52, 38, -124, -64, -63, 2, 88, 75, 9, 96, 14, 25, 19, 34, 100, 64, 51, 20, 82, -118, 81, 64, Tnaf.POW_2_WIDTH, -61, 40, 101, 12, -96, 69, 104, 77, 9, -95, 32, -120, -124, -88, -107, 18, -96, -32, 34, -108, 82, -118, 64, 77, -48, -58, 54, 87, 64};
    }

    private static void createRandomMask43_28() {
        _randomMask43_28 = new byte[]{70, 17, -110, 50, 70, 64, 51, 12, -55, -103, 51, 32, Tnaf.POW_2_WIDTH, -28, 100, -116, -111, ByteCompanionObject.MIN_VALUE, 12, 105, 13, 33, -92, 32, 40, -108, 42, -123, 80, -96, -108, 33, 52, 38, -124, -64, -63, 2, 88, 75, 9, 96, 14, 25, 19, 34, 100, 64, 51, 20, 82, -118, 81, 64, Tnaf.POW_2_WIDTH, -61, 40, 101, 12, -96, 69, 104, 77, 9, -95, 32, -120, -124, -88, -107, 18, -96, -32, 34, -108, 82, -118, 64, 77, -48, -58, 54, 87, 64, 14, 25, 19, 34, 100, 64, 51, 20, 82, -118, 81, 64, Tnaf.POW_2_WIDTH, -61, 40, 101, 12, -96, 69, 104, 77, 9, -95, 32, -120, -124, -88, -107, 18, -96, -32, 34, -108, 82, -118, 64, 70, 17, -110, 50, 70, 64, 51, 12, -55, -103, 51, 32, Tnaf.POW_2_WIDTH, -28, 100, -116, -111, ByteCompanionObject.MIN_VALUE, 12, 105, 13, 33, -92, 32, 40, -108, 42, -123, 80, -96, -108, 33, 52, 38, -124, -64, -63, 2, 88, 75, 9, 96, 22, -55, 83, 30, -60, 0};
    }

    private static void createRandomMask43_29() {
        _randomMask43_29 = new byte[]{70, 17, -110, 50, 70, 64, 51, 12, -55, -103, 51, 32, Tnaf.POW_2_WIDTH, -28, 100, -116, -111, ByteCompanionObject.MIN_VALUE, 12, 105, 13, 33, -92, 32, 40, -108, 42, -123, 80, -96, -108, 33, 52, 38, -124, -64, -63, 2, 88, 75, 9, 96, 14, 25, 19, 34, 100, 64, 51, 20, 82, -118, 81, 64, Tnaf.POW_2_WIDTH, -61, 40, 101, 12, -96, 69, 104, 77, 9, -95, 32, -120, -124, -88, -107, 18, -96, -32, 34, -108, 82, -118, 64, 77, -48, -58, 54, 87, 64, 70, 17, -110, 50, 70, 64, 51, 12, -55, -103, 51, 32, Tnaf.POW_2_WIDTH, -28, 100, -116, -111, ByteCompanionObject.MIN_VALUE, 12, 105, 13, 33, -92, 32, 40, -108, 42, -123, 80, -96, -108, 33, 52, 38, -124, -64, -63, 2, 88, 75, 9, 96, 44, 3, 32, 100, 12, ByteCompanionObject.MIN_VALUE, -127, -96, 28, 3, ByteCompanionObject.MIN_VALUE, 96, -96, 104, 37, 4, -96, ByteCompanionObject.MIN_VALUE, 5, 65, 80, 42, 5, 64, 24, -112, -62, 24, 67, 0, -62, 6, ByteCompanionObject.MIN_VALUE, -48, 26, 0, 34, -104, 11, 1, 96, 32, 80, 69, 8, -95, 20, 32};
    }

    private static void createRandomMask43_3() {
        _randomMask43_3 = new byte[]{-50, 50, -74, 86, -54, -64, 85, -36, 87, -118, -15, 64, -88, -19, -115, -79, -82, 32};
    }

    private static void createRandomMask43_30() {
        _randomMask43_30 = new byte[]{70, 17, -110, 50, 70, 64, 51, 12, -55, -103, 51, 32, Tnaf.POW_2_WIDTH, -28, 100, -116, -111, ByteCompanionObject.MIN_VALUE, 12, 105, 13, 33, -92, 32, 40, -108, 42, -123, 80, -96, -108, 33, 52, 38, -124, -64, -63, 2, 88, 75, 9, 96, 44, 3, 32, 100, 12, ByteCompanionObject.MIN_VALUE, -127, -96, 28, 3, ByteCompanionObject.MIN_VALUE, 96, -96, 104, 37, 4, -96, ByteCompanionObject.MIN_VALUE, 5, 65, 80, 42, 5, 64, 24, -112, -62, 24, 67, 0, -62, 6, ByteCompanionObject.MIN_VALUE, -48, 26, 0, 34, -104, 11, 1, 96, 32, 80, 69, 8, -95, 20, 32, 70, 17, -110, 50, 70, 64, 51, 12, -55, -103, 51, 32, Tnaf.POW_2_WIDTH, -28, 100, -116, -111, ByteCompanionObject.MIN_VALUE, 12, 105, 13, 33, -92, 32, 40, -108, 42, -123, 80, -96, -108, 33, 52, 38, -124, -64, -63, 2, 88, 75, 9, 96, 14, 25, 19, 34, 100, 64, 51, 20, 82, -118, 81, 64, Tnaf.POW_2_WIDTH, -61, 40, 101, 12, -96, 69, 104, 77, 9, -95, 32, -120, -124, -88, -107, 18, -96, -32, 34, -108, 82, -118, 64, 77, -48, -58, 54, 87, 64, 121, 74, -113, 66, 121, 64};
    }

    private static void createRandomMask43_31() {
        _randomMask43_31 = new byte[]{70, 17, -110, 50, 70, 64, 51, 12, -55, -103, 51, 32, Tnaf.POW_2_WIDTH, -28, 100, -116, -111, ByteCompanionObject.MIN_VALUE, 12, 105, 13, 33, -92, 32, 40, -108, 42, -123, 80, -96, -108, 33, 52, 38, -124, -64, -63, 2, 88, 75, 9, 96, 44, 3, 32, 100, 12, ByteCompanionObject.MIN_VALUE, -127, -96, 28, 3, ByteCompanionObject.MIN_VALUE, 96, -96, 104, 37, 4, -96, ByteCompanionObject.MIN_VALUE, 5, 65, 80, 42, 5, 64, 24, -112, -62, 24, 67, 0, -62, 6, ByteCompanionObject.MIN_VALUE, -48, 26, 0, 34, -104, 11, 1, 96, 32, 80, 69, 8, -95, 20, 32, 44, 3, 32, 100, 12, ByteCompanionObject.MIN_VALUE, -127, -96, 28, 3, ByteCompanionObject.MIN_VALUE, 96, -96, 104, 37, 4, -96, ByteCompanionObject.MIN_VALUE, 5, 65, 80, 42, 5, 64, 24, -112, -62, 24, 67, 0, -62, 6, ByteCompanionObject.MIN_VALUE, -48, 26, 0, 34, -104, 11, 1, 96, 32, 80, 69, 8, -95, 20, 32, 70, 17, -110, 50, 70, 64, 51, 12, -55, -103, 51, 32, Tnaf.POW_2_WIDTH, -28, 100, -116, -111, ByteCompanionObject.MIN_VALUE, 12, 105, 13, 33, -92, 32, 40, -108, 42, -123, 80, -96, -108, 33, 52, 38, -124, -64, -63, 2, 88, 75, 9, 96, 59, -11, 60, 54, 10, 32};
    }

    private static void createRandomMask43_32() {
        _randomMask43_32 = new byte[]{44, 3, 32, 100, 12, ByteCompanionObject.MIN_VALUE, -127, -96, 28, 3, ByteCompanionObject.MIN_VALUE, 96, -96, 104, 37, 4, -96, ByteCompanionObject.MIN_VALUE, 5, 65, 80, 42, 5, 64, 24, -112, -62, 24, 67, 0, -62, 6, ByteCompanionObject.MIN_VALUE, -48, 26, 0, 34, -104, 11, 1, 96, 32, 80, 69, 8, -95, 20, 32, 70, 17, -110, 50, 70, 64, 51, 12, -55, -103, 51, 32, Tnaf.POW_2_WIDTH, -28, 100, -116, -111, ByteCompanionObject.MIN_VALUE, 12, 105, 13, 33, -92, 32, 40, -108, 42, -123, 80, -96, -108, 33, 52, 38, -124, -64, -63, 2, 88, 75, 9, 96, 59, -11, 60, 54, 10, 32, 70, 17, -110, 50, 70, 64, 51, 12, -55, -103, 51, 32, Tnaf.POW_2_WIDTH, -28, 100, -116, -111, ByteCompanionObject.MIN_VALUE, 12, 105, 13, 33, -92, 32, 40, -108, 42, -123, 80, -96, -108, 33, 52, 38, -124, -64, -63, 2, 88, 75, 9, 96, 44, 3, 32, 100, 12, ByteCompanionObject.MIN_VALUE, -127, -96, 28, 3, ByteCompanionObject.MIN_VALUE, 96, -96, 104, 37, 4, -96, ByteCompanionObject.MIN_VALUE, 5, 65, 80, 42, 5, 64, 24, -112, -62, 24, 67, 0, -62, 6, ByteCompanionObject.MIN_VALUE, -48, 26, 0, 34, -104, 11, 1, 96, 32, 80, 69, 8, -95, 20, 32, -47, -47, 17, -92, -19, -64};
    }

    private static void createRandomMask43_33() {
        _randomMask43_33 = new byte[]{44, 3, 32, 100, 12, ByteCompanionObject.MIN_VALUE, -127, -96, 28, 3, ByteCompanionObject.MIN_VALUE, 96, -96, 104, 37, 4, -96, ByteCompanionObject.MIN_VALUE, 5, 65, 80, 42, 5, 64, 24, -112, -62, 24, 67, 0, -62, 6, ByteCompanionObject.MIN_VALUE, -48, 26, 0, 34, -104, 11, 1, 96, 32, 80, 69, 8, -95, 20, 32, 70, 17, -110, 50, 70, 64, 51, 12, -55, -103, 51, 32, Tnaf.POW_2_WIDTH, -28, 100, -116, -111, ByteCompanionObject.MIN_VALUE, 12, 105, 13, 33, -92, 32, 40, -108, 42, -123, 80, -96, -108, 33, 52, 38, -124, -64, -63, 2, 88, 75, 9, 96, 59, -11, 60, 54, 10, 32, 44, 3, 32, 100, 12, ByteCompanionObject.MIN_VALUE, -127, -96, 28, 3, ByteCompanionObject.MIN_VALUE, 96, -96, 104, 37, 4, -96, ByteCompanionObject.MIN_VALUE, 5, 65, 80, 42, 5, 64, 24, -112, -62, 24, 67, 0, -62, 6, ByteCompanionObject.MIN_VALUE, -48, 26, 0, 34, -104, 11, 1, 96, 32, 80, 69, 8, -95, 20, 32, 78, 17, -110, 50, 70, 64, -29, 24, -101, 19, 98, 96, -127, -29, 4, 96, -116, 0, 33, 64, 88, 11, 1, 96, 82, -127, -32, 60, 7, ByteCompanionObject.MIN_VALUE, -76, 40, 37, 4, -96, ByteCompanionObject.MIN_VALUE, 38, -122, 40, -59, 24, -96, 88, 100, 68, -120, -111, 0, 25, -98, 3, -64, 120, 0};
    }

    private static void createRandomMask43_34() {
        _randomMask43_34 = new byte[]{44, 3, 32, 100, 12, ByteCompanionObject.MIN_VALUE, -127, -96, 28, 3, ByteCompanionObject.MIN_VALUE, 96, -96, 104, 37, 4, -96, ByteCompanionObject.MIN_VALUE, 5, 65, 80, 42, 5, 64, 24, -112, -62, 24, 67, 0, -62, 6, ByteCompanionObject.MIN_VALUE, -48, 26, 0, 34, -104, 11, 1, 96, 32, 80, 69, 8, -95, 20, 32, 78, 17, -110, 50, 70, 64, -29, 24, -101, 19, 98, 96, -127, -29, 4, 96, -116, 0, 33, 64, 88, 11, 1, 96, 82, -127, -32, 60, 7, ByteCompanionObject.MIN_VALUE, -76, 40, 37, 4, -96, ByteCompanionObject.MIN_VALUE, 38, -122, 40, -59, 24, -96, 88, 100, 68, -120, -111, 0, 25, -98, 3, -64, 120, 0, 44, 3, 32, 100, 12, ByteCompanionObject.MIN_VALUE, -127, -96, 28, 3, ByteCompanionObject.MIN_VALUE, 96, -96, 104, 37, 4, -96, ByteCompanionObject.MIN_VALUE, 5, 65, 80, 42, 5, 64, 24, -112, -62, 24, 67, 0, -62, 6, ByteCompanionObject.MIN_VALUE, -48, 26, 0, 34, -104, 11, 1, 96, 32, 80, 69, 8, -95, 20, 32, 70, 17, -110, 50, 70, 64, 51, 12, -55, -103, 51, 32, Tnaf.POW_2_WIDTH, -28, 100, -116, -111, ByteCompanionObject.MIN_VALUE, 12, 105, 13, 33, -92, 32, 40, -108, 42, -123, 80, -96, -108, 33, 52, 38, -124, -64, -63, 2, 88, 75, 9, 96, 59, -11, 60, 54, 10, 32, 118, -127, 77, 51, 102, 0};
    }

    private static void createRandomMask43_35() {
        _randomMask43_35 = new byte[]{44, 3, 32, 100, 12, ByteCompanionObject.MIN_VALUE, -127, -96, 28, 3, ByteCompanionObject.MIN_VALUE, 96, -96, 104, 37, 4, -96, ByteCompanionObject.MIN_VALUE, 5, 65, 80, 42, 5, 64, 24, -112, -62, 24, 67, 0, -62, 6, ByteCompanionObject.MIN_VALUE, -48, 26, 0, 34, -104, 11, 1, 96, 32, 80, 69, 8, -95, 20, 32, 78, 17, -110, 50, 70, 64, -29, 24, -101, 19, 98, 96, -127, -29, 4, 96, -116, 0, 33, 64, 88, 11, 1, 96, 82, -127, -32, 60, 7, ByteCompanionObject.MIN_VALUE, -76, 40, 37, 4, -96, ByteCompanionObject.MIN_VALUE, 38, -122, 40, -59, 24, -96, 88, 100, 68, -120, -111, 0, 25, -98, 3, -64, 120, 0, 78, 17, -110, 50, 70, 64, -29, 24, -101, 19, 98, 96, -127, -29, 4, 96, -116, 0, 33, 64, 88, 11, 1, 96, 82, -127, -32, 60, 7, ByteCompanionObject.MIN_VALUE, -76, 40, 37, 4, -96, ByteCompanionObject.MIN_VALUE, 38, -122, 40, -59, 24, -96, 88, 100, 68, -120, -111, 0, 25, -98, 3, -64, 120, 0, 44, 3, 32, 100, 12, ByteCompanionObject.MIN_VALUE, -127, -96, 28, 3, ByteCompanionObject.MIN_VALUE, 96, -96, 104, 37, 4, -96, ByteCompanionObject.MIN_VALUE, 5, 65, 80, 42, 5, 64, 24, -112, -62, 24, 67, 0, -62, 6, ByteCompanionObject.MIN_VALUE, -48, 26, 0, 34, -104, 11, 1, 96, 32, 80, 69, 8, -95, 20, 32, 90, 86, 95, 38, -93, 96};
    }

    private static void createRandomMask43_36() {
        _randomMask43_36 = new byte[]{78, 17, -110, 50, 70, 64, -29, 24, -101, 19, 98, 96, -127, -29, 4, 96, -116, 0, 33, 64, 88, 11, 1, 96, 82, -127, -32, 60, 7, ByteCompanionObject.MIN_VALUE, -76, 40, 37, 4, -96, ByteCompanionObject.MIN_VALUE, 38, -122, 40, -59, 24, -96, 88, 100, 68, -120, -111, 0, 25, -98, 3, -64, 120, 0, 44, 3, 32, 100, 12, ByteCompanionObject.MIN_VALUE, -127, -96, 28, 3, ByteCompanionObject.MIN_VALUE, 96, -96, 104, 37, 4, -96, ByteCompanionObject.MIN_VALUE, 5, 65, 80, 42, 5, 64, 24, -112, -62, 24, 67, 0, -62, 6, ByteCompanionObject.MIN_VALUE, -48, 26, 0, 34, -104, 11, 1, 96, 32, 80, 69, 8, -95, 20, 32, 90, 86, 95, 38, -93, 96, 44, 3, 32, 100, 12, ByteCompanionObject.MIN_VALUE, -127, -96, 28, 3, ByteCompanionObject.MIN_VALUE, 96, -96, 104, 37, 4, -96, ByteCompanionObject.MIN_VALUE, 5, 65, 80, 42, 5, 64, 24, -112, -62, 24, 67, 0, -62, 6, ByteCompanionObject.MIN_VALUE, -48, 26, 0, 34, -104, 11, 1, 96, 32, 80, 69, 8, -95, 20, 32, 78, 17, -110, 50, 70, 64, -29, 24, -101, 19, 98, 96, -127, -29, 4, 96, -116, 0, 33, 64, 88, 11, 1, 96, 82, -127, -32, 60, 7, ByteCompanionObject.MIN_VALUE, -76, 40, 37, 4, -96, ByteCompanionObject.MIN_VALUE, 38, -122, 40, -59, 24, -96, 88, 100, 68, -120, -111, 0, 25, -98, 3, -64, 120, 0, -93, -123, 10, -75, 17, 96};
    }

    private static void createRandomMask43_37() {
        _randomMask43_37 = new byte[]{78, 17, -110, 50, 70, 64, -29, 24, -101, 19, 98, 96, -127, -29, 4, 96, -116, 0, 33, 64, 88, 11, 1, 96, 82, -127, -32, 60, 7, ByteCompanionObject.MIN_VALUE, -76, 40, 37, 4, -96, ByteCompanionObject.MIN_VALUE, 38, -122, 40, -59, 24, -96, 88, 100, 68, -120, -111, 0, 25, -98, 3, -64, 120, 0, 44, 3, 32, 100, 12, ByteCompanionObject.MIN_VALUE, -127, -96, 28, 3, ByteCompanionObject.MIN_VALUE, 96, -96, 104, 37, 4, -96, ByteCompanionObject.MIN_VALUE, 5, 65, 80, 42, 5, 64, 24, -112, -62, 24, 67, 0, -62, 6, ByteCompanionObject.MIN_VALUE, -48, 26, 0, 34, -104, 11, 1, 96, 32, 80, 69, 8, -95, 20, 32, 90, 86, 95, 38, -93, 96, 78, 17, -110, 50, 70, 64, -29, 24, -101, 19, 98, 96, -127, -29, 4, 96, -116, 0, 33, 64, 88, 11, 1, 96, 82, -127, -32, 60, 7, ByteCompanionObject.MIN_VALUE, -76, 40, 37, 4, -96, ByteCompanionObject.MIN_VALUE, 38, -122, 40, -59, 24, -96, 88, 100, 68, -120, -111, 0, 25, -98, 3, -64, 120, 0, 76, 25, 19, 34, 100, 64, 81, 20, 82, -118, 81, 64, -96, 106, 69, 72, -87, 0, 4, -63, 48, 38, 4, -64, 3, -76, 6, ByteCompanionObject.MIN_VALUE, -48, 0, -122, 32, -108, 18, -126, 64, 41, 8, 73, 9, 33, 32, 66, 67, 8, 97, 12, 32, -104, 18, -126, 80, 74, 0, 48, -124, -88, -107, 18, -96};
    }

    private static void createRandomMask43_38() {
        _randomMask43_38 = new byte[]{78, 17, -110, 50, 70, 64, -29, 24, -101, 19, 98, 96, -127, -29, 4, 96, -116, 0, 33, 64, 88, 11, 1, 96, 82, -127, -32, 60, 7, ByteCompanionObject.MIN_VALUE, -76, 40, 37, 4, -96, ByteCompanionObject.MIN_VALUE, 38, -122, 40, -59, 24, -96, 88, 100, 68, -120, -111, 0, 25, -98, 3, -64, 120, 0, 76, 25, 19, 34, 100, 64, 81, 20, 82, -118, 81, 64, -96, 106, 69, 72, -87, 0, 4, -63, 48, 38, 4, -64, 3, -76, 6, ByteCompanionObject.MIN_VALUE, -48, 0, -122, 32, -108, 18, -126, 64, 41, 8, 73, 9, 33, 32, 66, 67, 8, 97, 12, 32, -104, 18, -126, 80, 74, 0, 48, -124, -88, -107, 18, -96, 78, 17, -110, 50, 70, 64, -29, 24, -101, 19, 98, 96, -127, -29, 4, 96, -116, 0, 33, 64, 88, 11, 1, 96, 82, -127, -32, 60, 7, ByteCompanionObject.MIN_VALUE, -76, 40, 37, 4, -96, ByteCompanionObject.MIN_VALUE, 38, -122, 40, -59, 24, -96, 88, 100, 68, -120, -111, 0, 25, -98, 3, -64, 120, 0, 44, 3, 32, 100, 12, ByteCompanionObject.MIN_VALUE, -127, -96, 28, 3, ByteCompanionObject.MIN_VALUE, 96, -96, 104, 37, 4, -96, ByteCompanionObject.MIN_VALUE, 5, 65, 80, 42, 5, 64, 24, -112, -62, 24, 67, 0, -62, 6, ByteCompanionObject.MIN_VALUE, -48, 26, 0, 34, -104, 11, 1, 96, 32, 80, 69, 8, -95, 20, 32, 90, 86, 95, 38, -93, 96, -102, 22, -105, 33, -71, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask43_39() {
        _randomMask43_39 = new byte[]{78, 17, -110, 50, 70, 64, -29, 24, -101, 19, 98, 96, -127, -29, 4, 96, -116, 0, 33, 64, 88, 11, 1, 96, 82, -127, -32, 60, 7, ByteCompanionObject.MIN_VALUE, -76, 40, 37, 4, -96, ByteCompanionObject.MIN_VALUE, 38, -122, 40, -59, 24, -96, 88, 100, 68, -120, -111, 0, 25, -98, 3, -64, 120, 0, 76, 25, 19, 34, 100, 64, 81, 20, 82, -118, 81, 64, -96, 106, 69, 72, -87, 0, 4, -63, 48, 38, 4, -64, 3, -76, 6, ByteCompanionObject.MIN_VALUE, -48, 0, -122, 32, -108, 18, -126, 64, 41, 8, 73, 9, 33, 32, 66, 67, 8, 97, 12, 32, -104, 18, -126, 80, 74, 0, 48, -124, -88, -107, 18, -96, 76, 25, 19, 34, 100, 64, 81, 20, 82, -118, 81, 64, -96, 106, 69, 72, -87, 0, 4, -63, 48, 38, 4, -64, 3, -76, 6, ByteCompanionObject.MIN_VALUE, -48, 0, -122, 32, -108, 18, -126, 64, 41, 8, 73, 9, 33, 32, 66, 67, 8, 97, 12, 32, -104, 18, -126, 80, 74, 0, 48, -124, -88, -107, 18, -96, 78, 17, -110, 50, 70, 64, -29, 24, -101, 19, 98, 96, -127, -29, 4, 96, -116, 0, 33, 64, 88, 11, 1, 96, 82, -127, -32, 60, 7, ByteCompanionObject.MIN_VALUE, -76, 40, 37, 4, -96, ByteCompanionObject.MIN_VALUE, 38, -122, 40, -59, 24, -96, 88, 100, 68, -120, -111, 0, 25, -98, 3, -64, 120, 0, 42, 3, 49, -38, 70, 32};
    }

    private static void createRandomMask43_4() {
        _randomMask43_4 = new byte[]{-26, 49, 54, 38, -60, -64, 51, -116, 89, -117, 49, 96, -104, -46, -54, 89, 75, 32, 45, 75, 41, 101, 44, -96};
    }

    private static void createRandomMask43_40() {
        _randomMask43_40 = new byte[]{76, 25, 19, 34, 100, 64, 81, 20, 82, -118, 81, 64, -96, 106, 69, 72, -87, 0, 4, -63, 48, 38, 4, -64, 3, -76, 6, ByteCompanionObject.MIN_VALUE, -48, 0, -122, 32, -108, 18, -126, 64, 41, 8, 73, 9, 33, 32, 66, 67, 8, 97, 12, 32, -104, 18, -126, 80, 74, 0, 48, -124, -88, -107, 18, -96, 78, 17, -110, 50, 70, 64, -29, 24, -101, 19, 98, 96, -127, -29, 4, 96, -116, 0, 33, 64, 88, 11, 1, 96, 82, -127, -32, 60, 7, ByteCompanionObject.MIN_VALUE, -76, 40, 37, 4, -96, ByteCompanionObject.MIN_VALUE, 38, -122, 40, -59, 24, -96, 88, 100, 68, -120, -111, 0, 25, -98, 3, -64, 120, 0, 42, 3, 49, -38, 70, 32, 78, 17, -110, 50, 70, 64, -29, 24, -101, 19, 98, 96, -127, -29, 4, 96, -116, 0, 33, 64, 88, 11, 1, 96, 82, -127, -32, 60, 7, ByteCompanionObject.MIN_VALUE, -76, 40, 37, 4, -96, ByteCompanionObject.MIN_VALUE, 38, -122, 40, -59, 24, -96, 88, 100, 68, -120, -111, 0, 25, -98, 3, -64, 120, 0, 76, 25, 19, 34, 100, 64, 81, 20, 82, -118, 81, 64, -96, 106, 69, 72, -87, 0, 4, -63, 48, 38, 4, -64, 3, -76, 6, ByteCompanionObject.MIN_VALUE, -48, 0, -122, 32, -108, 18, -126, 64, 41, 8, 73, 9, 33, 32, 66, 67, 8, 97, 12, 32, -104, 18, -126, 80, 74, 0, 48, -124, -88, -107, 18, -96, 58, -85, 119, 99, -17, 96};
    }

    private static void createRandomMask43_41() {
        _randomMask43_41 = new byte[]{76, 25, 19, 34, 100, 64, 81, 20, 82, -118, 81, 64, -96, 106, 69, 72, -87, 0, 4, -63, 48, 38, 4, -64, 3, -76, 6, ByteCompanionObject.MIN_VALUE, -48, 0, -122, 32, -108, 18, -126, 64, 41, 8, 73, 9, 33, 32, 66, 67, 8, 97, 12, 32, -104, 18, -126, 80, 74, 0, 48, -124, -88, -107, 18, -96, 78, 17, -110, 50, 70, 64, -29, 24, -101, 19, 98, 96, -127, -29, 4, 96, -116, 0, 33, 64, 88, 11, 1, 96, 82, -127, -32, 60, 7, ByteCompanionObject.MIN_VALUE, -76, 40, 37, 4, -96, ByteCompanionObject.MIN_VALUE, 38, -122, 40, -59, 24, -96, 88, 100, 68, -120, -111, 0, 25, -98, 3, -64, 120, 0, 42, 3, 49, -38, 70, 32, 76, 25, 19, 34, 100, 64, 81, 20, 82, -118, 81, 64, -96, 106, 69, 72, -87, 0, 4, -63, 48, 38, 4, -64, 3, -76, 6, ByteCompanionObject.MIN_VALUE, -48, 0, -122, 32, -108, 18, -126, 64, 41, 8, 73, 9, 33, 32, 66, 67, 8, 97, 12, 32, -104, 18, -126, 80, 74, 0, 48, -124, -88, -107, 18, -96, 76, 17, -110, 50, 70, 64, 81, 12, -55, -103, 51, 32, -96, 102, 68, -56, -103, 0, 4, -63, 96, 44, 5, ByteCompanionObject.MIN_VALUE, 3, -96, 44, 5, ByteCompanionObject.MIN_VALUE, -96, -122, 33, 20, 34, -124, 64, 41, Tnaf.POW_2_WIDTH, 26, 3, 64, 96, 66, 66, -96, 84, 10, ByteCompanionObject.MIN_VALUE, -104, 26, 3, 64, 104, 0, 48, -124, 8, -127, Tnaf.POW_2_WIDTH, 32, -33, 76, 17, -126, 48, 64};
    }

    private static void createRandomMask43_42() {
        _randomMask43_42 = new byte[]{76, 25, 19, 34, 100, 64, 81, 20, 82, -118, 81, 64, -96, 106, 69, 72, -87, 0, 4, -63, 48, 38, 4, -64, 3, -76, 6, ByteCompanionObject.MIN_VALUE, -48, 0, -122, 32, -108, 18, -126, 64, 41, 8, 73, 9, 33, 32, 66, 67, 8, 97, 12, 32, -104, 18, -126, 80, 74, 0, 48, -124, -88, -107, 18, -96, 76, 17, -110, 50, 70, 64, 81, 12, -55, -103, 51, 32, -96, 102, 68, -56, -103, 0, 4, -63, 96, 44, 5, ByteCompanionObject.MIN_VALUE, 3, -96, 44, 5, ByteCompanionObject.MIN_VALUE, -96, -122, 33, 20, 34, -124, 64, 41, Tnaf.POW_2_WIDTH, 26, 3, 64, 96, 66, 66, -96, 84, 10, ByteCompanionObject.MIN_VALUE, -104, 26, 3, 64, 104, 0, 48, -124, 8, -127, Tnaf.POW_2_WIDTH, 32, -33, 76, 17, -126, 48, 64, 76, 25, 19, 34, 100, 64, 81, 20, 82, -118, 81, 64, -96, 106, 69, 72, -87, 0, 4, -63, 48, 38, 4, -64, 3, -76, 6, ByteCompanionObject.MIN_VALUE, -48, 0, -122, 32, -108, 18, -126, 64, 41, 8, 73, 9, 33, 32, 66, 67, 8, 97, 12, 32, -104, 18, -126, 80, 74, 0, 48, -124, -88, -107, 18, -96, 78, 17, -110, 50, 70, 64, -29, 24, -101, 19, 98, 96, -127, -29, 4, 96, -116, 0, 33, 64, 88, 11, 1, 96, 82, -127, -32, 60, 7, ByteCompanionObject.MIN_VALUE, -76, 40, 37, 4, -96, ByteCompanionObject.MIN_VALUE, 38, -122, 40, -59, 24, -96, 88, 100, 68, -120, -111, 0, 25, -98, 3, -64, 120, 0, 42, 3, 49, -38, 70, 32, 38, -124, Tnaf.POW_2_WIDTH, -51, -9, 96};
    }

    private static void createRandomMask43_43() {
        _randomMask43_43 = new byte[]{76, 25, 19, 34, 100, 64, 81, 20, 82, -118, 81, 64, -96, 106, 69, 72, -87, 0, 4, -63, 48, 38, 4, -64, 3, -76, 6, ByteCompanionObject.MIN_VALUE, -48, 0, -122, 32, -108, 18, -126, 64, 41, 8, 73, 9, 33, 32, 66, 67, 8, 97, 12, 32, -104, 18, -126, 80, 74, 0, 48, -124, -88, -107, 18, -96, 76, 17, -110, 50, 70, 64, 81, 12, -55, -103, 51, 32, -96, 102, 68, -56, -103, 0, 4, -63, 96, 44, 5, ByteCompanionObject.MIN_VALUE, 3, -96, 44, 5, ByteCompanionObject.MIN_VALUE, -96, -122, 33, 20, 34, -124, 64, 41, Tnaf.POW_2_WIDTH, 26, 3, 64, 96, 66, 66, -96, 84, 10, ByteCompanionObject.MIN_VALUE, -104, 26, 3, 64, 104, 0, 48, -124, 8, -127, Tnaf.POW_2_WIDTH, 32, -33, 76, 17, -126, 48, 64, 76, 25, 18, 50, 70, 64, 81, 20, 81, -103, 51, 32, -96, 106, 68, -56, -103, 0, 4, -63, 48, 44, 5, ByteCompanionObject.MIN_VALUE, 3, -76, 4, 5, ByteCompanionObject.MIN_VALUE, -96, -122, 32, -108, 34, -124, 64, 41, 8, 74, 3, 64, 96, 66, 67, 8, 84, 10, ByteCompanionObject.MIN_VALUE, -104, 18, -125, 64, 104, 0, 48, -124, -88, -127, Tnaf.POW_2_WIDTH, 32, 76, 17, -111, -126, 48, 64, 81, 12, -53, 34, 100, 64, -96, 102, 66, -118, 81, 64, 4, -63, 101, 72, -87, 0, 3, -96, 40, 38, 4, -64, -122, 33, 22, ByteCompanionObject.MIN_VALUE, -48, 0, 41, Tnaf.POW_2_WIDTH, 28, 18, -126, 64, 66, 66, -95, 9, 33, 32, -104, 26, 0, 97, 12, 32, 48, -124, 10, 80, 74, 0, -33, 76, Tnaf.POW_2_WIDTH, -107, 18, -96, 114, 6, -108, -10, 116, 64};
    }

    private static void createRandomMask43_5() {
        _randomMask43_5 = new byte[]{-50, 49, -74, 54, -58, -64, 99, -104, -37, 27, 99, 96, -104, -57, 104, -19, 29, -96, 77, 107, 85, 106, -83, 64, -78, 108, -83, -107, -78, -96};
    }

    private static void createRandomMask43_6() {
        _randomMask43_6 = new byte[]{76, 25, 19, 34, 100, 64, 81, 20, 82, -118, 81, 64, 32, -22, 13, 65, -88, 32, -123, 65, 46, 37, 4, -96, 6, ByteCompanionObject.MIN_VALUE, -40, 27, 3, 96, -118, 36, 52, -122, -112, -64};
    }

    private static void createRandomMask43_7() {
        _randomMask43_7 = new byte[]{-58, 17, -106, 50, 70, 64, 51, 4, -56, -103, 51, 32, 24, 103, 68, 104, -99, 0, 69, 66, -44, 90, 11, 64, 18, -44, 42, -107, 80, -96, -76, 40, 53, 22, -96, -64, 41, -110, 27, 13, 65, 96};
    }

    private static void createRandomMask43_8() {
        _randomMask43_8 = new byte[]{7, 10, 113, 68, 41, -64, 73, -88, 41, 15, -96, 32, -80, 122, 7, 72, -24, 0, 36, -59, -64, -72, 23, 0, 82, ByteCompanionObject.MIN_VALUE, -20, 29, 2, -96, -58, 49, -126, 48, -57, 64, 49, -108, 26, -125, 80, 96, 24, -57, 8, -31, 28, 32};
    }

    private static void createRandomMask43_9() {
        _randomMask43_9 = new byte[]{78, 17, 18, 34, 70, 64, 98, 26, 9, 65, 104, 96, ByteCompanionObject.MIN_VALUE, -23, 65, 40, -91, 0, -95, 80, 82, -56, 81, 0, 83, 0, 104, 29, 1, -96, -92, 36, 54, 6, ByteCompanionObject.MIN_VALUE, -64, 22, -96, -115, 17, -126, 32, 88, 69, 32, -92, 22, ByteCompanionObject.MIN_VALUE, 41, -122, -124, -48, 28, 0};
    }

    private static void createRandomMask44_1() {
        _randomMask44_1 = new byte[]{-1, -1, -1, -1, -1, -16};
    }

    private static void createRandomMask44_10() {
        _randomMask44_10 = new byte[]{-64, 56, -117, 0, -30, 32, 48, 14, 40, -64, 56, -96, -24, 7, 3, -96, 28, 0, -123, 8, -86, 20, 34, -96, -48, -110, 19, 66, 72, 64, -122, 80, 74, 25, 65, 32, 74, 104, 13, 41, -96, 48, 1, -96, 116, 6, -127, -48, 76, -127, -111, 50, 6, 64, 98, 36, 5, -120, -112, Tnaf.POW_2_WIDTH};
    }

    private static void createRandomMask44_11() {
        _randomMask44_11 = new byte[]{70, 72, -55, 25, 35, 32, 51, 38, 100, -52, -103, -112, -103, 19, 34, 100, 76, ByteCompanionObject.MIN_VALUE, 5, ByteCompanionObject.MIN_VALUE, -80, 22, 2, -64, ByteCompanionObject.MIN_VALUE, -80, 22, 2, -64, 80, -124, 80, -118, 17, 66, 32, 64, 104, 13, 1, -96, 48, 10, -127, 80, 42, 5, 64, 104, 13, 1, -96, 52, 0, Tnaf.POW_2_WIDTH, 34, 4, 64, -120, Tnaf.POW_2_WIDTH, 48, 70, 8, -63, 24, 32};
    }

    private static void createRandomMask44_12() {
        _randomMask44_12 = new byte[]{100, 76, -119, -111, 50, 32, 81, 74, 41, 69, 40, -96, 12, -95, -108, 50, -122, 80, -95, 52, 38, -124, -48, -112, 18, -94, 84, 74, -119, 80, -118, 81, 74, 41, 69, 32, -122, -112, -46, 26, 67, 64, 35, 36, 100, -116, -111, -112, 22, 34, -60, 88, -117, Tnaf.POW_2_WIDTH, 76, 41, -123, 48, -90, Tnaf.POW_2_WIDTH, 65, -56, 57, 7, 32, -32, -12, 24, -97, -48, 98, 112};
    }

    private static void createRandomMask44_13() {
        _randomMask44_13 = new byte[]{100, 76, -119, -111, 50, 32, 81, 74, 41, 69, 40, -96, 12, -95, -108, 50, -122, 80, -95, 52, 38, -124, -48, -112, 18, -94, 84, 74, -119, 80, -118, 81, 74, 41, 69, 32, 70, 72, -55, 25, 35, 32, 51, 38, 100, -52, -103, -112, -111, -110, 50, 70, 72, -64, -92, 52, -122, -112, -46, Tnaf.POW_2_WIDTH, 80, -86, 21, 66, -88, 80, -124, -48, -102, 19, 66, 96, 9, 97, 44, 37, -124, -80};
    }

    private static void createRandomMask44_14() {
        _randomMask44_14 = new byte[]{70, 72, -55, 25, 35, 32, 51, 38, 100, -52, -103, -112, -111, -110, 50, 70, 72, -64, -92, 52, -122, -112, -46, Tnaf.POW_2_WIDTH, 80, -86, 21, 66, -88, 80, -124, -48, -102, 19, 66, 96, 9, 97, 44, 37, -124, -80, 100, 76, -119, -111, 50, 32, 81, 74, 41, 69, 40, -96, 12, -95, -108, 50, -122, 80, -95, 52, 38, -124, -48, -112, 18, -94, 84, 74, -119, 80, -118, 81, 74, 41, 69, 32, -58, -54, -21, 27, 43, -96};
    }

    private static void createRandomMask44_15() {
        _randomMask44_15 = new byte[]{70, 72, -55, 25, 35, 32, 51, 38, 100, -52, -103, -112, -111, -110, 50, 70, 72, -64, -92, 52, -122, -112, -46, Tnaf.POW_2_WIDTH, 80, -86, 21, 66, -88, 80, -124, -48, -102, 19, 66, 96, 9, 97, 44, 37, -124, -80, 12, -127, -112, 50, 6, 64, ByteCompanionObject.MIN_VALUE, 112, 14, 1, -64, 48, -96, -108, 18, -126, 80, 64, 5, 64, -88, 21, 2, -96, 67, 8, 97, 12, 33, ByteCompanionObject.MIN_VALUE, 26, 3, 64, 104, 13, 0, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -80, Tnaf.POW_2_WIDTH, 20, 34, -124, 80, -118, Tnaf.POW_2_WIDTH};
    }

    private static void createRandomMask44_16() {
        _randomMask44_16 = new byte[]{12, -127, -112, 50, 6, 64, ByteCompanionObject.MIN_VALUE, 112, 14, 1, -64, 48, -96, -108, 18, -126, 80, 64, 5, 64, -88, 21, 2, -96, 67, 8, 97, 12, 33, ByteCompanionObject.MIN_VALUE, 26, 3, 64, 104, 13, 0, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -80, Tnaf.POW_2_WIDTH, 20, 34, -124, 80, -118, Tnaf.POW_2_WIDTH, 70, 72, -55, 25, 35, 32, 51, 38, 100, -52, -103, -112, -111, -110, 50, 70, 72, -64, -92, 52, -122, -112, -46, Tnaf.POW_2_WIDTH, 80, -86, 21, 66, -88, 80, -124, -48, -102, 19, 66, 96, 9, 97, 44, 37, -124, -80, -122, -63, 70, 27, 5, Tnaf.POW_2_WIDTH};
    }

    private static void createRandomMask44_17() {
        _randomMask44_17 = new byte[]{12, -127, -112, 50, 6, 64, ByteCompanionObject.MIN_VALUE, 112, 14, 1, -64, 48, -96, -108, 18, -126, 80, 64, 5, 64, -88, 21, 2, -96, 67, 8, 97, 12, 33, ByteCompanionObject.MIN_VALUE, 26, 3, 64, 104, 13, 0, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -80, Tnaf.POW_2_WIDTH, 20, 34, -124, 80, -118, Tnaf.POW_2_WIDTH, 70, 72, -55, 25, 35, 32, 98, 108, 77, -119, -79, 48, -116, 17, -126, 48, 70, 0, 1, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -80, 7, ByteCompanionObject.MIN_VALUE, -16, 30, 3, -64, -96, -108, 18, -126, 80, 64, 24, -93, 20, 98, -116, 80, -111, 18, 34, 68, 72, ByteCompanionObject.MIN_VALUE, 120, 15, 1, -32, 60, 0};
    }

    private static void createRandomMask44_18() {
        _randomMask44_18 = new byte[]{70, 72, -55, 25, 35, 32, 98, 108, 77, -119, -79, 48, -116, 17, -126, 48, 70, 0, 1, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -80, 7, ByteCompanionObject.MIN_VALUE, -16, 30, 3, -64, -96, -108, 18, -126, 80, 64, 24, -93, 20, 98, -116, 80, -111, 18, 34, 68, 72, ByteCompanionObject.MIN_VALUE, 120, 15, 1, -32, 60, 0, 12, -127, -112, 50, 6, 64, ByteCompanionObject.MIN_VALUE, 112, 14, 1, -64, 48, -96, -108, 18, -126, 80, 64, 5, 64, -88, 21, 2, -96, 67, 8, 97, 12, 33, ByteCompanionObject.MIN_VALUE, 26, 3, 64, 104, 13, 0, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -80, Tnaf.POW_2_WIDTH, 20, 34, -124, 80, -118, Tnaf.POW_2_WIDTH, -28, -44, 111, -109, 81, -80};
    }

    private static void createRandomMask44_19() {
        _randomMask44_19 = new byte[]{70, 72, -55, 25, 35, 32, 98, 108, 77, -119, -79, 48, -116, 17, -126, 48, 70, 0, 1, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -80, 7, ByteCompanionObject.MIN_VALUE, -16, 30, 3, -64, -96, -108, 18, -126, 80, 64, 24, -93, 20, 98, -116, 80, -111, 18, 34, 68, 72, ByteCompanionObject.MIN_VALUE, 120, 15, 1, -32, 60, 0, 100, 76, -119, -111, 50, 32, 81, 74, 41, 69, 40, -96, -87, 21, 34, -92, 84, ByteCompanionObject.MIN_VALUE, 4, -64, -104, 19, 2, 96, -48, 26, 3, 64, 104, 0, -126, 80, 74, 9, 65, 32, 33, 36, 36, -124, -112, -112, 12, 33, -124, 48, -122, Tnaf.POW_2_WIDTH, 74, 9, 65, 40, 37, 0, 18, -94, 84, 74, -119, 80};
    }

    private static void createRandomMask44_2() {
        _randomMask44_2 = new byte[]{-20, -35, -101, -77, 118, 96, -101, -77, 118, 110, -51, -48};
    }

    private static void createRandomMask44_20() {
        _randomMask44_20 = new byte[]{100, 76, -119, -111, 50, 32, 81, 74, 41, 69, 40, -96, -87, 21, 34, -92, 84, ByteCompanionObject.MIN_VALUE, 4, -64, -104, 19, 2, 96, -48, 26, 3, 64, 104, 0, -126, 80, 74, 9, 65, 32, 33, 36, 36, -124, -112, -112, 12, 33, -124, 48, -122, Tnaf.POW_2_WIDTH, 74, 9, 65, 40, 37, 0, 18, -94, 84, 74, -119, 80, 70, 72, -55, 25, 35, 32, 98, 108, 77, -119, -79, 48, -116, 17, -126, 48, 70, 0, 1, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -80, 7, ByteCompanionObject.MIN_VALUE, -16, 30, 3, -64, -96, -108, 18, -126, 80, 64, 24, -93, 20, 98, -116, 80, -111, 18, 34, 68, 72, ByteCompanionObject.MIN_VALUE, 120, 15, 1, -32, 60, 0, 59, 72, -60, -19, 35, Tnaf.POW_2_WIDTH};
    }

    private static void createRandomMask44_21() {
        _randomMask44_21 = new byte[]{100, 76, -119, -111, 50, 32, 81, 74, 41, 69, 40, -96, -87, 21, 34, -92, 84, ByteCompanionObject.MIN_VALUE, 4, -64, -104, 19, 2, 96, -48, 26, 3, 64, 104, 0, -126, 80, 74, 9, 65, 32, 33, 36, 36, -124, -112, -112, 12, 33, -124, 48, -122, Tnaf.POW_2_WIDTH, 74, 9, 65, 40, 37, 0, 18, -94, 84, 74, -119, 80, 70, 72, -55, 25, 35, 32, 51, 38, 100, -52, -103, -112, -103, 19, 34, 100, 76, ByteCompanionObject.MIN_VALUE, 5, ByteCompanionObject.MIN_VALUE, -80, 22, 2, -64, ByteCompanionObject.MIN_VALUE, -80, 22, 2, -64, 80, -124, 80, -118, 17, 66, 32, 64, 104, 13, 1, -96, 48, 10, -127, 80, 42, 5, 64, 104, 13, 1, -96, 52, 0, Tnaf.POW_2_WIDTH, 34, 4, 64, -120, Tnaf.POW_2_WIDTH, 48, 70, 8, -63, 24, 32};
    }

    private static void createRandomMask44_22() {
        _randomMask44_22 = new byte[]{70, 72, -55, 25, 35, 32, 51, 38, 100, -52, -103, -112, -103, 19, 34, 100, 76, ByteCompanionObject.MIN_VALUE, 5, ByteCompanionObject.MIN_VALUE, -80, 22, 2, -64, ByteCompanionObject.MIN_VALUE, -80, 22, 2, -64, 80, -124, 80, -118, 17, 66, 32, 64, 104, 13, 1, -96, 48, 10, -127, 80, 42, 5, 64, 104, 13, 1, -96, 52, 0, Tnaf.POW_2_WIDTH, 34, 4, 64, -120, Tnaf.POW_2_WIDTH, 48, 70, 8, -63, 24, 32, 100, 76, -119, -111, 50, 32, 81, 74, 41, 69, 40, -96, -87, 21, 34, -92, 84, ByteCompanionObject.MIN_VALUE, 4, -64, -104, 19, 2, 96, -48, 26, 3, 64, 104, 0, -126, 80, 74, 9, 65, 32, 33, 36, 36, -124, -112, -112, 12, 33, -124, 48, -122, Tnaf.POW_2_WIDTH, 74, 9, 65, 40, 37, 0, 18, -94, 84, 74, -119, 80, -98, -50, -118, 123, 58, 32};
    }

    private static void createRandomMask44_23() {
        _randomMask44_23 = new byte[]{70, 72, -55, 25, 35, 32, 51, 38, 100, -52, -103, -112, -103, 19, 34, 100, 76, ByteCompanionObject.MIN_VALUE, 5, ByteCompanionObject.MIN_VALUE, -80, 22, 2, -64, ByteCompanionObject.MIN_VALUE, -80, 22, 2, -64, 80, -124, 80, -118, 17, 66, 32, 64, 104, 13, 1, -96, 48, 10, -127, 80, 42, 5, 64, 104, 13, 1, -96, 52, 0, Tnaf.POW_2_WIDTH, 34, 4, 64, -120, Tnaf.POW_2_WIDTH, 48, 70, 8, -63, 24, 32, 100, 76, -119, -111, 50, 32, 81, 74, 41, 69, 40, -96, 12, -95, -108, 50, -122, 80, -95, 52, 38, -124, -48, -112, 18, -94, 84, 74, -119, 80, -118, 81, 74, 41, 69, 32, -122, -112, -46, 26, 67, 64, 35, 36, 100, -116, -111, -112, 22, 34, -60, 88, -117, Tnaf.POW_2_WIDTH, 76, 41, -123, 48, -90, Tnaf.POW_2_WIDTH, 65, -56, 57, 7, 32, -32, -12, 24, -97, -48, 98, 112};
    }

    private static void createRandomMask44_24() {
        _randomMask44_24 = new byte[]{100, 76, -119, -111, 50, 32, 81, 74, 41, 69, 40, -96, 12, -95, -108, 50, -122, 80, -95, 52, 38, -124, -48, -112, 18, -94, 84, 74, -119, 80, -118, 81, 74, 41, 69, 32, -122, -112, -46, 26, 67, 64, 35, 36, 100, -116, -111, -112, 22, 34, -60, 88, -117, Tnaf.POW_2_WIDTH, 76, 41, -123, 48, -90, Tnaf.POW_2_WIDTH, 65, -56, 57, 7, 32, -32, -12, 24, -97, -48, 98, 112, 70, 72, -55, 25, 35, 32, 51, 38, 100, -52, -103, -112, -103, 19, 34, 100, 76, ByteCompanionObject.MIN_VALUE, 5, ByteCompanionObject.MIN_VALUE, -80, 22, 2, -64, ByteCompanionObject.MIN_VALUE, -80, 22, 2, -64, 80, -124, 80, -118, 17, 66, 32, 64, 104, 13, 1, -96, 48, 10, -127, 80, 42, 5, 64, 104, 13, 1, -96, 52, 0, Tnaf.POW_2_WIDTH, 34, 4, 64, -120, Tnaf.POW_2_WIDTH, 48, 70, 8, -63, 24, 32, 21, 15, 68, 109, -99, -96};
    }

    private static void createRandomMask44_25() {
        _randomMask44_25 = new byte[]{100, 76, -119, -111, 50, 32, 81, 74, 41, 69, 40, -96, 12, -95, -108, 50, -122, 80, -95, 52, 38, -124, -48, -112, 18, -94, 84, 74, -119, 80, -118, 81, 74, 41, 69, 32, -122, -112, -46, 26, 67, 64, 35, 36, 100, -116, -111, -112, 22, 34, -60, 88, -117, Tnaf.POW_2_WIDTH, 76, 41, -123, 48, -90, Tnaf.POW_2_WIDTH, 65, -56, 57, 7, 32, -32, -12, 24, -97, -48, 98, 112, 100, 76, -119, -111, 50, 32, 81, 74, 41, 69, 40, -96, 12, -95, -108, 50, -122, 80, -95, 52, 38, -124, -48, -112, 18, -94, 84, 74, -119, 80, -118, 81, 74, 41, 69, 32, 70, 72, -55, 25, 35, 32, 51, 38, 100, -52, -103, -112, -111, -110, 50, 70, 72, -64, -92, 52, -122, -112, -46, Tnaf.POW_2_WIDTH, 80, -86, 21, 66, -88, 80, -124, -48, -102, 19, 66, 96, 9, 97, 44, 37, -124, -80};
    }

    private static void createRandomMask44_26() {
        _randomMask44_26 = new byte[]{100, 76, -119, -111, 50, 32, 81, 74, 41, 69, 40, -96, 12, -95, -108, 50, -122, 80, -95, 52, 38, -124, -48, -112, 18, -94, 84, 74, -119, 80, -118, 81, 74, 41, 69, 32, 70, 72, -55, 25, 35, 32, 51, 38, 100, -52, -103, -112, -111, -110, 50, 70, 72, -64, -92, 52, -122, -112, -46, Tnaf.POW_2_WIDTH, 80, -86, 21, 66, -88, 80, -124, -48, -102, 19, 66, 96, 9, 97, 44, 37, -124, -80, 100, 76, -119, -111, 50, 32, 81, 74, 41, 69, 40, -96, 12, -95, -108, 50, -122, 80, -95, 52, 38, -124, -48, -112, 18, -94, 84, 74, -119, 80, -118, 81, 74, 41, 69, 32, -122, -112, -46, 26, 67, 64, 35, 36, 100, -116, -111, -112, 22, 34, -60, 88, -117, Tnaf.POW_2_WIDTH, 76, 41, -123, 48, -90, Tnaf.POW_2_WIDTH, 65, -56, 57, 7, 32, -32, -12, 24, -97, -48, 98, 112, 2, -53, 100, -72, 85, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask44_27() {
        _randomMask44_27 = new byte[]{100, 76, -119, -111, 50, 32, 81, 74, 41, 69, 40, -96, 12, -95, -108, 50, -122, 80, -95, 52, 38, -124, -48, -112, 18, -94, 84, 74, -119, 80, -118, 81, 74, 41, 69, 32, 70, 72, -55, 25, 35, 32, 51, 38, 100, -52, -103, -112, -111, -110, 50, 70, 72, -64, -92, 52, -122, -112, -46, Tnaf.POW_2_WIDTH, 80, -86, 21, 66, -88, 80, -124, -48, -102, 19, 66, 96, 9, 97, 44, 37, -124, -80, 70, 72, -55, 25, 35, 32, 51, 38, 100, -52, -103, -112, -111, -110, 50, 70, 72, -64, -92, 52, -122, -112, -46, Tnaf.POW_2_WIDTH, 80, -86, 21, 66, -88, 80, -124, -48, -102, 19, 66, 96, 9, 97, 44, 37, -124, -80, 100, 76, -119, -111, 50, 32, 81, 74, 41, 69, 40, -96, 12, -95, -108, 50, -122, 80, -95, 52, 38, -124, -48, -112, 18, -94, 84, 74, -119, 80, -118, 81, 74, 41, 69, 32, -58, -54, -21, 27, 43, -96};
    }

    private static void createRandomMask44_28() {
        _randomMask44_28 = new byte[]{70, 72, -55, 25, 35, 32, 51, 38, 100, -52, -103, -112, -111, -110, 50, 70, 72, -64, -92, 52, -122, -112, -46, Tnaf.POW_2_WIDTH, 80, -86, 21, 66, -88, 80, -124, -48, -102, 19, 66, 96, 9, 97, 44, 37, -124, -80, 100, 76, -119, -111, 50, 32, 81, 74, 41, 69, 40, -96, 12, -95, -108, 50, -122, 80, -95, 52, 38, -124, -48, -112, 18, -94, 84, 74, -119, 80, -118, 81, 74, 41, 69, 32, -58, -54, -21, 27, 43, -96, 100, 76, -119, -111, 50, 32, 81, 74, 41, 69, 40, -96, 12, -95, -108, 50, -122, 80, -95, 52, 38, -124, -48, -112, 18, -94, 84, 74, -119, 80, -118, 81, 74, 41, 69, 32, 70, 72, -55, 25, 35, 32, 51, 38, 100, -52, -103, -112, -111, -110, 50, 70, 72, -64, -92, 52, -122, -112, -46, Tnaf.POW_2_WIDTH, 80, -86, 21, 66, -88, 80, -124, -48, -102, 19, 66, 96, 9, 97, 44, 37, -124, -80, 102, 38, 108, -111, -57, 32};
    }

    private static void createRandomMask44_29() {
        _randomMask44_29 = new byte[]{70, 72, -55, 25, 35, 32, 51, 38, 100, -52, -103, -112, -111, -110, 50, 70, 72, -64, -92, 52, -122, -112, -46, Tnaf.POW_2_WIDTH, 80, -86, 21, 66, -88, 80, -124, -48, -102, 19, 66, 96, 9, 97, 44, 37, -124, -80, 100, 76, -119, -111, 50, 32, 81, 74, 41, 69, 40, -96, 12, -95, -108, 50, -122, 80, -95, 52, 38, -124, -48, -112, 18, -94, 84, 74, -119, 80, -118, 81, 74, 41, 69, 32, -58, -54, -21, 27, 43, -96, 70, 72, -55, 25, 35, 32, 51, 38, 100, -52, -103, -112, -111, -110, 50, 70, 72, -64, -92, 52, -122, -112, -46, Tnaf.POW_2_WIDTH, 80, -86, 21, 66, -88, 80, -124, -48, -102, 19, 66, 96, 9, 97, 44, 37, -124, -80, 12, -127, -112, 50, 6, 64, ByteCompanionObject.MIN_VALUE, 112, 14, 1, -64, 48, -96, -108, 18, -126, 80, 64, 5, 64, -88, 21, 2, -96, 67, 8, 97, 12, 33, ByteCompanionObject.MIN_VALUE, 26, 3, 64, 104, 13, 0, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -80, Tnaf.POW_2_WIDTH, 20, 34, -124, 80, -118, Tnaf.POW_2_WIDTH};
    }

    private static void createRandomMask44_3() {
        _randomMask44_3 = new byte[]{-54, -39, 91, 43, 101, 96, -15, 94, 43, -59, 120, -96, -74, 53, -58, -40, -41, Tnaf.POW_2_WIDTH};
    }

    private static void createRandomMask44_30() {
        _randomMask44_30 = new byte[]{70, 72, -55, 25, 35, 32, 51, 38, 100, -52, -103, -112, -111, -110, 50, 70, 72, -64, -92, 52, -122, -112, -46, Tnaf.POW_2_WIDTH, 80, -86, 21, 66, -88, 80, -124, -48, -102, 19, 66, 96, 9, 97, 44, 37, -124, -80, 12, -127, -112, 50, 6, 64, ByteCompanionObject.MIN_VALUE, 112, 14, 1, -64, 48, -96, -108, 18, -126, 80, 64, 5, 64, -88, 21, 2, -96, 67, 8, 97, 12, 33, ByteCompanionObject.MIN_VALUE, 26, 3, 64, 104, 13, 0, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -80, Tnaf.POW_2_WIDTH, 20, 34, -124, 80, -118, Tnaf.POW_2_WIDTH, 70, 72, -55, 25, 35, 32, 51, 38, 100, -52, -103, -112, -111, -110, 50, 70, 72, -64, -92, 52, -122, -112, -46, Tnaf.POW_2_WIDTH, 80, -86, 21, 66, -88, 80, -124, -48, -102, 19, 66, 96, 9, 97, 44, 37, -124, -80, 100, 76, -119, -111, 50, 32, 81, 74, 41, 69, 40, -96, 12, -95, -108, 50, -122, 80, -95, 52, 38, -124, -48, -112, 18, -94, 84, 74, -119, 80, -118, 81, 74, 41, 69, 32, -58, -54, -21, 27, 43, -96, 96, -12, 117, -124, -112, -64};
    }

    private static void createRandomMask44_31() {
        _randomMask44_31 = new byte[]{70, 72, -55, 25, 35, 32, 51, 38, 100, -52, -103, -112, -111, -110, 50, 70, 72, -64, -92, 52, -122, -112, -46, Tnaf.POW_2_WIDTH, 80, -86, 21, 66, -88, 80, -124, -48, -102, 19, 66, 96, 9, 97, 44, 37, -124, -80, 12, -127, -112, 50, 6, 64, ByteCompanionObject.MIN_VALUE, 112, 14, 1, -64, 48, -96, -108, 18, -126, 80, 64, 5, 64, -88, 21, 2, -96, 67, 8, 97, 12, 33, ByteCompanionObject.MIN_VALUE, 26, 3, 64, 104, 13, 0, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -80, Tnaf.POW_2_WIDTH, 20, 34, -124, 80, -118, Tnaf.POW_2_WIDTH, 12, -127, -112, 50, 6, 64, ByteCompanionObject.MIN_VALUE, 112, 14, 1, -64, 48, -96, -108, 18, -126, 80, 64, 5, 64, -88, 21, 2, -96, 67, 8, 97, 12, 33, ByteCompanionObject.MIN_VALUE, 26, 3, 64, 104, 13, 0, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -80, Tnaf.POW_2_WIDTH, 20, 34, -124, 80, -118, Tnaf.POW_2_WIDTH, 70, 72, -55, 25, 35, 32, 51, 38, 100, -52, -103, -112, -111, -110, 50, 70, 72, -64, -92, 52, -122, -112, -46, Tnaf.POW_2_WIDTH, 80, -86, 21, 66, -88, 80, -124, -48, -102, 19, 66, 96, 9, 97, 44, 37, -124, -80, -122, -63, 70, 27, 5, Tnaf.POW_2_WIDTH};
    }

    private static void createRandomMask44_32() {
        _randomMask44_32 = new byte[]{12, -127, -112, 50, 6, 64, ByteCompanionObject.MIN_VALUE, 112, 14, 1, -64, 48, -96, -108, 18, -126, 80, 64, 5, 64, -88, 21, 2, -96, 67, 8, 97, 12, 33, ByteCompanionObject.MIN_VALUE, 26, 3, 64, 104, 13, 0, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -80, Tnaf.POW_2_WIDTH, 20, 34, -124, 80, -118, Tnaf.POW_2_WIDTH, 70, 72, -55, 25, 35, 32, 51, 38, 100, -52, -103, -112, -111, -110, 50, 70, 72, -64, -92, 52, -122, -112, -46, Tnaf.POW_2_WIDTH, 80, -86, 21, 66, -88, 80, -124, -48, -102, 19, 66, 96, 9, 97, 44, 37, -124, -80, -122, -63, 70, 27, 5, Tnaf.POW_2_WIDTH, 70, 72, -55, 25, 35, 32, 51, 38, 100, -52, -103, -112, -111, -110, 50, 70, 72, -64, -92, 52, -122, -112, -46, Tnaf.POW_2_WIDTH, 80, -86, 21, 66, -88, 80, -124, -48, -102, 19, 66, 96, 9, 97, 44, 37, -124, -80, 12, -127, -112, 50, 6, 64, ByteCompanionObject.MIN_VALUE, 112, 14, 1, -64, 48, -96, -108, 18, -126, 80, 64, 5, 64, -88, 21, 2, -96, 67, 8, 97, 12, 33, ByteCompanionObject.MIN_VALUE, 26, 3, 64, 104, 13, 0, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -80, Tnaf.POW_2_WIDTH, 20, 34, -124, 80, -118, Tnaf.POW_2_WIDTH, 62, 57, -122, 92, -39, -48};
    }

    private static void createRandomMask44_33() {
        _randomMask44_33 = new byte[]{12, -127, -112, 50, 6, 64, ByteCompanionObject.MIN_VALUE, 112, 14, 1, -64, 48, -96, -108, 18, -126, 80, 64, 5, 64, -88, 21, 2, -96, 67, 8, 97, 12, 33, ByteCompanionObject.MIN_VALUE, 26, 3, 64, 104, 13, 0, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -80, Tnaf.POW_2_WIDTH, 20, 34, -124, 80, -118, Tnaf.POW_2_WIDTH, 70, 72, -55, 25, 35, 32, 51, 38, 100, -52, -103, -112, -111, -110, 50, 70, 72, -64, -92, 52, -122, -112, -46, Tnaf.POW_2_WIDTH, 80, -86, 21, 66, -88, 80, -124, -48, -102, 19, 66, 96, 9, 97, 44, 37, -124, -80, -122, -63, 70, 27, 5, Tnaf.POW_2_WIDTH, 12, -127, -112, 50, 6, 64, ByteCompanionObject.MIN_VALUE, 112, 14, 1, -64, 48, -96, -108, 18, -126, 80, 64, 5, 64, -88, 21, 2, -96, 67, 8, 97, 12, 33, ByteCompanionObject.MIN_VALUE, 26, 3, 64, 104, 13, 0, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -80, Tnaf.POW_2_WIDTH, 20, 34, -124, 80, -118, Tnaf.POW_2_WIDTH, 70, 72, -55, 25, 35, 32, 98, 108, 77, -119, -79, 48, -116, 17, -126, 48, 70, 0, 1, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -80, 7, ByteCompanionObject.MIN_VALUE, -16, 30, 3, -64, -96, -108, 18, -126, 80, 64, 24, -93, 20, 98, -116, 80, -111, 18, 34, 68, 72, ByteCompanionObject.MIN_VALUE, 120, 15, 1, -32, 60, 0};
    }

    private static void createRandomMask44_34() {
        _randomMask44_34 = new byte[]{12, -127, -112, 50, 6, 64, ByteCompanionObject.MIN_VALUE, 112, 14, 1, -64, 48, -96, -108, 18, -126, 80, 64, 5, 64, -88, 21, 2, -96, 67, 8, 97, 12, 33, ByteCompanionObject.MIN_VALUE, 26, 3, 64, 104, 13, 0, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -80, Tnaf.POW_2_WIDTH, 20, 34, -124, 80, -118, Tnaf.POW_2_WIDTH, 70, 72, -55, 25, 35, 32, 98, 108, 77, -119, -79, 48, -116, 17, -126, 48, 70, 0, 1, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -80, 7, ByteCompanionObject.MIN_VALUE, -16, 30, 3, -64, -96, -108, 18, -126, 80, 64, 24, -93, 20, 98, -116, 80, -111, 18, 34, 68, 72, ByteCompanionObject.MIN_VALUE, 120, 15, 1, -32, 60, 0, 12, -127, -112, 50, 6, 64, ByteCompanionObject.MIN_VALUE, 112, 14, 1, -64, 48, -96, -108, 18, -126, 80, 64, 5, 64, -88, 21, 2, -96, 67, 8, 97, 12, 33, ByteCompanionObject.MIN_VALUE, 26, 3, 64, 104, 13, 0, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -80, Tnaf.POW_2_WIDTH, 20, 34, -124, 80, -118, Tnaf.POW_2_WIDTH, 70, 72, -55, 25, 35, 32, 51, 38, 100, -52, -103, -112, -111, -110, 50, 70, 72, -64, -92, 52, -122, -112, -46, Tnaf.POW_2_WIDTH, 80, -86, 21, 66, -88, 80, -124, -48, -102, 19, 66, 96, 9, 97, 44, 37, -124, -80, -122, -63, 70, 27, 5, Tnaf.POW_2_WIDTH, -75, -57, -24, 12, -71, -112};
    }

    private static void createRandomMask44_35() {
        _randomMask44_35 = new byte[]{12, -127, -112, 50, 6, 64, ByteCompanionObject.MIN_VALUE, 112, 14, 1, -64, 48, -96, -108, 18, -126, 80, 64, 5, 64, -88, 21, 2, -96, 67, 8, 97, 12, 33, ByteCompanionObject.MIN_VALUE, 26, 3, 64, 104, 13, 0, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -80, Tnaf.POW_2_WIDTH, 20, 34, -124, 80, -118, Tnaf.POW_2_WIDTH, 70, 72, -55, 25, 35, 32, 98, 108, 77, -119, -79, 48, -116, 17, -126, 48, 70, 0, 1, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -80, 7, ByteCompanionObject.MIN_VALUE, -16, 30, 3, -64, -96, -108, 18, -126, 80, 64, 24, -93, 20, 98, -116, 80, -111, 18, 34, 68, 72, ByteCompanionObject.MIN_VALUE, 120, 15, 1, -32, 60, 0, 70, 72, -55, 25, 35, 32, 98, 108, 77, -119, -79, 48, -116, 17, -126, 48, 70, 0, 1, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -80, 7, ByteCompanionObject.MIN_VALUE, -16, 30, 3, -64, -96, -108, 18, -126, 80, 64, 24, -93, 20, 98, -116, 80, -111, 18, 34, 68, 72, ByteCompanionObject.MIN_VALUE, 120, 15, 1, -32, 60, 0, 12, -127, -112, 50, 6, 64, ByteCompanionObject.MIN_VALUE, 112, 14, 1, -64, 48, -96, -108, 18, -126, 80, 64, 5, 64, -88, 21, 2, -96, 67, 8, 97, 12, 33, ByteCompanionObject.MIN_VALUE, 26, 3, 64, 104, 13, 0, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -80, Tnaf.POW_2_WIDTH, 20, 34, -124, 80, -118, Tnaf.POW_2_WIDTH, -28, -44, 111, -109, 81, -80};
    }

    private static void createRandomMask44_36() {
        _randomMask44_36 = new byte[]{70, 72, -55, 25, 35, 32, 98, 108, 77, -119, -79, 48, -116, 17, -126, 48, 70, 0, 1, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -80, 7, ByteCompanionObject.MIN_VALUE, -16, 30, 3, -64, -96, -108, 18, -126, 80, 64, 24, -93, 20, 98, -116, 80, -111, 18, 34, 68, 72, ByteCompanionObject.MIN_VALUE, 120, 15, 1, -32, 60, 0, 12, -127, -112, 50, 6, 64, ByteCompanionObject.MIN_VALUE, 112, 14, 1, -64, 48, -96, -108, 18, -126, 80, 64, 5, 64, -88, 21, 2, -96, 67, 8, 97, 12, 33, ByteCompanionObject.MIN_VALUE, 26, 3, 64, 104, 13, 0, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -80, Tnaf.POW_2_WIDTH, 20, 34, -124, 80, -118, Tnaf.POW_2_WIDTH, -28, -44, 111, -109, 81, -80, 12, -127, -112, 50, 6, 64, ByteCompanionObject.MIN_VALUE, 112, 14, 1, -64, 48, -96, -108, 18, -126, 80, 64, 5, 64, -88, 21, 2, -96, 67, 8, 97, 12, 33, ByteCompanionObject.MIN_VALUE, 26, 3, 64, 104, 13, 0, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -80, Tnaf.POW_2_WIDTH, 20, 34, -124, 80, -118, Tnaf.POW_2_WIDTH, 70, 72, -55, 25, 35, 32, 98, 108, 77, -119, -79, 48, -116, 17, -126, 48, 70, 0, 1, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -80, 7, ByteCompanionObject.MIN_VALUE, -16, 30, 3, -64, -96, -108, 18, -126, 80, 64, 24, -93, 20, 98, -116, 80, -111, 18, 34, 68, 72, ByteCompanionObject.MIN_VALUE, 120, 15, 1, -32, 60, 0, -90, -110, 1, 101, -111, 32};
    }

    private static void createRandomMask44_37() {
        _randomMask44_37 = new byte[]{70, 72, -55, 25, 35, 32, 98, 108, 77, -119, -79, 48, -116, 17, -126, 48, 70, 0, 1, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -80, 7, ByteCompanionObject.MIN_VALUE, -16, 30, 3, -64, -96, -108, 18, -126, 80, 64, 24, -93, 20, 98, -116, 80, -111, 18, 34, 68, 72, ByteCompanionObject.MIN_VALUE, 120, 15, 1, -32, 60, 0, 12, -127, -112, 50, 6, 64, ByteCompanionObject.MIN_VALUE, 112, 14, 1, -64, 48, -96, -108, 18, -126, 80, 64, 5, 64, -88, 21, 2, -96, 67, 8, 97, 12, 33, ByteCompanionObject.MIN_VALUE, 26, 3, 64, 104, 13, 0, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -80, Tnaf.POW_2_WIDTH, 20, 34, -124, 80, -118, Tnaf.POW_2_WIDTH, -28, -44, 111, -109, 81, -80, 70, 72, -55, 25, 35, 32, 98, 108, 77, -119, -79, 48, -116, 17, -126, 48, 70, 0, 1, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -80, 7, ByteCompanionObject.MIN_VALUE, -16, 30, 3, -64, -96, -108, 18, -126, 80, 64, 24, -93, 20, 98, -116, 80, -111, 18, 34, 68, 72, ByteCompanionObject.MIN_VALUE, 120, 15, 1, -32, 60, 0, 100, 76, -119, -111, 50, 32, 81, 74, 41, 69, 40, -96, -87, 21, 34, -92, 84, ByteCompanionObject.MIN_VALUE, 4, -64, -104, 19, 2, 96, -48, 26, 3, 64, 104, 0, -126, 80, 74, 9, 65, 32, 33, 36, 36, -124, -112, -112, 12, 33, -124, 48, -122, Tnaf.POW_2_WIDTH, 74, 9, 65, 40, 37, 0, 18, -94, 84, 74, -119, 80};
    }

    private static void createRandomMask44_38() {
        _randomMask44_38 = new byte[]{70, 72, -55, 25, 35, 32, 98, 108, 77, -119, -79, 48, -116, 17, -126, 48, 70, 0, 1, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -80, 7, ByteCompanionObject.MIN_VALUE, -16, 30, 3, -64, -96, -108, 18, -126, 80, 64, 24, -93, 20, 98, -116, 80, -111, 18, 34, 68, 72, ByteCompanionObject.MIN_VALUE, 120, 15, 1, -32, 60, 0, 100, 76, -119, -111, 50, 32, 81, 74, 41, 69, 40, -96, -87, 21, 34, -92, 84, ByteCompanionObject.MIN_VALUE, 4, -64, -104, 19, 2, 96, -48, 26, 3, 64, 104, 0, -126, 80, 74, 9, 65, 32, 33, 36, 36, -124, -112, -112, 12, 33, -124, 48, -122, Tnaf.POW_2_WIDTH, 74, 9, 65, 40, 37, 0, 18, -94, 84, 74, -119, 80, 70, 72, -55, 25, 35, 32, 98, 108, 77, -119, -79, 48, -116, 17, -126, 48, 70, 0, 1, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -80, 7, ByteCompanionObject.MIN_VALUE, -16, 30, 3, -64, -96, -108, 18, -126, 80, 64, 24, -93, 20, 98, -116, 80, -111, 18, 34, 68, 72, ByteCompanionObject.MIN_VALUE, 120, 15, 1, -32, 60, 0, 12, -127, -112, 50, 6, 64, ByteCompanionObject.MIN_VALUE, 112, 14, 1, -64, 48, -96, -108, 18, -126, 80, 64, 5, 64, -88, 21, 2, -96, 67, 8, 97, 12, 33, ByteCompanionObject.MIN_VALUE, 26, 3, 64, 104, 13, 0, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -80, Tnaf.POW_2_WIDTH, 20, 34, -124, 80, -118, Tnaf.POW_2_WIDTH, -28, -44, 111, -109, 81, -80, 67, 100, -14, -27, 93, Tnaf.POW_2_WIDTH};
    }

    private static void createRandomMask44_39() {
        _randomMask44_39 = new byte[]{70, 72, -55, 25, 35, 32, 98, 108, 77, -119, -79, 48, -116, 17, -126, 48, 70, 0, 1, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -80, 7, ByteCompanionObject.MIN_VALUE, -16, 30, 3, -64, -96, -108, 18, -126, 80, 64, 24, -93, 20, 98, -116, 80, -111, 18, 34, 68, 72, ByteCompanionObject.MIN_VALUE, 120, 15, 1, -32, 60, 0, 100, 76, -119, -111, 50, 32, 81, 74, 41, 69, 40, -96, -87, 21, 34, -92, 84, ByteCompanionObject.MIN_VALUE, 4, -64, -104, 19, 2, 96, -48, 26, 3, 64, 104, 0, -126, 80, 74, 9, 65, 32, 33, 36, 36, -124, -112, -112, 12, 33, -124, 48, -122, Tnaf.POW_2_WIDTH, 74, 9, 65, 40, 37, 0, 18, -94, 84, 74, -119, 80, 100, 76, -119, -111, 50, 32, 81, 74, 41, 69, 40, -96, -87, 21, 34, -92, 84, ByteCompanionObject.MIN_VALUE, 4, -64, -104, 19, 2, 96, -48, 26, 3, 64, 104, 0, -126, 80, 74, 9, 65, 32, 33, 36, 36, -124, -112, -112, 12, 33, -124, 48, -122, Tnaf.POW_2_WIDTH, 74, 9, 65, 40, 37, 0, 18, -94, 84, 74, -119, 80, 70, 72, -55, 25, 35, 32, 98, 108, 77, -119, -79, 48, -116, 17, -126, 48, 70, 0, 1, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -80, 7, ByteCompanionObject.MIN_VALUE, -16, 30, 3, -64, -96, -108, 18, -126, 80, 64, 24, -93, 20, 98, -116, 80, -111, 18, 34, 68, 72, ByteCompanionObject.MIN_VALUE, 120, 15, 1, -32, 60, 0, 59, 72, -60, -19, 35, Tnaf.POW_2_WIDTH};
    }

    private static void createRandomMask44_4() {
        _randomMask44_4 = new byte[]{-60, -40, -101, 19, 98, 96, 49, 102, 44, -59, -104, -80, 75, 41, 101, 44, -91, -112, 44, -91, -108, -78, -106, 80};
    }

    private static void createRandomMask44_40() {
        _randomMask44_40 = new byte[]{100, 76, -119, -111, 50, 32, 81, 74, 41, 69, 40, -96, -87, 21, 34, -92, 84, ByteCompanionObject.MIN_VALUE, 4, -64, -104, 19, 2, 96, -48, 26, 3, 64, 104, 0, -126, 80, 74, 9, 65, 32, 33, 36, 36, -124, -112, -112, 12, 33, -124, 48, -122, Tnaf.POW_2_WIDTH, 74, 9, 65, 40, 37, 0, 18, -94, 84, 74, -119, 80, 70, 72, -55, 25, 35, 32, 98, 108, 77, -119, -79, 48, -116, 17, -126, 48, 70, 0, 1, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -80, 7, ByteCompanionObject.MIN_VALUE, -16, 30, 3, -64, -96, -108, 18, -126, 80, 64, 24, -93, 20, 98, -116, 80, -111, 18, 34, 68, 72, ByteCompanionObject.MIN_VALUE, 120, 15, 1, -32, 60, 0, 59, 72, -60, -19, 35, Tnaf.POW_2_WIDTH, 70, 72, -55, 25, 35, 32, 98, 108, 77, -119, -79, 48, -116, 17, -126, 48, 70, 0, 1, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -80, 7, ByteCompanionObject.MIN_VALUE, -16, 30, 3, -64, -96, -108, 18, -126, 80, 64, 24, -93, 20, 98, -116, 80, -111, 18, 34, 68, 72, ByteCompanionObject.MIN_VALUE, 120, 15, 1, -32, 60, 0, 100, 76, -119, -111, 50, 32, 81, 74, 41, 69, 40, -96, -87, 21, 34, -92, 84, ByteCompanionObject.MIN_VALUE, 4, -64, -104, 19, 2, 96, -48, 26, 3, 64, 104, 0, -126, 80, 74, 9, 65, 32, 33, 36, 36, -124, -112, -112, 12, 33, -124, 48, -122, Tnaf.POW_2_WIDTH, 74, 9, 65, 40, 37, 0, 18, -94, 84, 74, -119, 80, -40, 42, 22, 38, 81, 64};
    }

    private static void createRandomMask44_41() {
        _randomMask44_41 = new byte[]{100, 76, -119, -111, 50, 32, 81, 74, 41, 69, 40, -96, -87, 21, 34, -92, 84, ByteCompanionObject.MIN_VALUE, 4, -64, -104, 19, 2, 96, -48, 26, 3, 64, 104, 0, -126, 80, 74, 9, 65, 32, 33, 36, 36, -124, -112, -112, 12, 33, -124, 48, -122, Tnaf.POW_2_WIDTH, 74, 9, 65, 40, 37, 0, 18, -94, 84, 74, -119, 80, 70, 72, -55, 25, 35, 32, 98, 108, 77, -119, -79, 48, -116, 17, -126, 48, 70, 0, 1, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -80, 7, ByteCompanionObject.MIN_VALUE, -16, 30, 3, -64, -96, -108, 18, -126, 80, 64, 24, -93, 20, 98, -116, 80, -111, 18, 34, 68, 72, ByteCompanionObject.MIN_VALUE, 120, 15, 1, -32, 60, 0, 59, 72, -60, -19, 35, Tnaf.POW_2_WIDTH, 100, 76, -119, -111, 50, 32, 81, 74, 41, 69, 40, -96, -87, 21, 34, -92, 84, ByteCompanionObject.MIN_VALUE, 4, -64, -104, 19, 2, 96, -48, 26, 3, 64, 104, 0, -126, 80, 74, 9, 65, 32, 33, 36, 36, -124, -112, -112, 12, 33, -124, 48, -122, Tnaf.POW_2_WIDTH, 74, 9, 65, 40, 37, 0, 18, -94, 84, 74, -119, 80, 70, 72, -55, 25, 35, 32, 51, 38, 100, -52, -103, -112, -103, 19, 34, 100, 76, ByteCompanionObject.MIN_VALUE, 5, ByteCompanionObject.MIN_VALUE, -80, 22, 2, -64, ByteCompanionObject.MIN_VALUE, -80, 22, 2, -64, 80, -124, 80, -118, 17, 66, 32, 64, 104, 13, 1, -96, 48, 10, -127, 80, 42, 5, 64, 104, 13, 1, -96, 52, 0, Tnaf.POW_2_WIDTH, 34, 4, 64, -120, Tnaf.POW_2_WIDTH, 48, 70, 8, -63, 24, 32};
    }

    private static void createRandomMask44_42() {
        _randomMask44_42 = new byte[]{100, 76, -119, -111, 50, 32, 81, 74, 41, 69, 40, -96, -87, 21, 34, -92, 84, ByteCompanionObject.MIN_VALUE, 4, -64, -104, 19, 2, 96, -48, 26, 3, 64, 104, 0, -126, 80, 74, 9, 65, 32, 33, 36, 36, -124, -112, -112, 12, 33, -124, 48, -122, Tnaf.POW_2_WIDTH, 74, 9, 65, 40, 37, 0, 18, -94, 84, 74, -119, 80, 70, 72, -55, 25, 35, 32, 51, 38, 100, -52, -103, -112, -103, 19, 34, 100, 76, ByteCompanionObject.MIN_VALUE, 5, ByteCompanionObject.MIN_VALUE, -80, 22, 2, -64, ByteCompanionObject.MIN_VALUE, -80, 22, 2, -64, 80, -124, 80, -118, 17, 66, 32, 64, 104, 13, 1, -96, 48, 10, -127, 80, 42, 5, 64, 104, 13, 1, -96, 52, 0, Tnaf.POW_2_WIDTH, 34, 4, 64, -120, Tnaf.POW_2_WIDTH, 48, 70, 8, -63, 24, 32, 100, 76, -119, -111, 50, 32, 81, 74, 41, 69, 40, -96, -87, 21, 34, -92, 84, ByteCompanionObject.MIN_VALUE, 4, -64, -104, 19, 2, 96, -48, 26, 3, 64, 104, 0, -126, 80, 74, 9, 65, 32, 33, 36, 36, -124, -112, -112, 12, 33, -124, 48, -122, Tnaf.POW_2_WIDTH, 74, 9, 65, 40, 37, 0, 18, -94, 84, 74, -119, 80, 70, 72, -55, 25, 35, 32, 98, 108, 77, -119, -79, 48, -116, 17, -126, 48, 70, 0, 1, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -80, 7, ByteCompanionObject.MIN_VALUE, -16, 30, 3, -64, -96, -108, 18, -126, 80, 64, 24, -93, 20, 98, -116, 80, -111, 18, 34, 68, 72, ByteCompanionObject.MIN_VALUE, 120, 15, 1, -32, 60, 0, 59, 72, -60, -19, 35, Tnaf.POW_2_WIDTH, -39, -63, 111, -88, 28, -112};
    }

    private static void createRandomMask44_43() {
        _randomMask44_43 = new byte[]{100, 76, -119, -111, 50, 32, 81, 74, 41, 69, 40, -96, -87, 21, 34, -92, 84, ByteCompanionObject.MIN_VALUE, 4, -64, -104, 19, 2, 96, -48, 26, 3, 64, 104, 0, -126, 80, 74, 9, 65, 32, 33, 36, 36, -124, -112, -112, 12, 33, -124, 48, -122, Tnaf.POW_2_WIDTH, 74, 9, 65, 40, 37, 0, 18, -94, 84, 74, -119, 80, 70, 72, -55, 25, 35, 32, 51, 38, 100, -52, -103, -112, -103, 19, 34, 100, 76, ByteCompanionObject.MIN_VALUE, 5, ByteCompanionObject.MIN_VALUE, -80, 22, 2, -64, ByteCompanionObject.MIN_VALUE, -80, 22, 2, -64, 80, -124, 80, -118, 17, 66, 32, 64, 104, 13, 1, -96, 48, 10, -127, 80, 42, 5, 64, 104, 13, 1, -96, 52, 0, Tnaf.POW_2_WIDTH, 34, 4, 64, -120, Tnaf.POW_2_WIDTH, 48, 70, 8, -63, 24, 32, 70, 72, -55, 25, 35, 32, 51, 38, 100, -52, -103, -112, -103, 19, 34, 100, 76, ByteCompanionObject.MIN_VALUE, 5, ByteCompanionObject.MIN_VALUE, -80, 22, 2, -64, ByteCompanionObject.MIN_VALUE, -80, 22, 2, -64, 80, -124, 80, -118, 17, 66, 32, 64, 104, 13, 1, -96, 48, 10, -127, 80, 42, 5, 64, 104, 13, 1, -96, 52, 0, Tnaf.POW_2_WIDTH, 34, 4, 64, -120, Tnaf.POW_2_WIDTH, 48, 70, 8, -63, 24, 32, 100, 76, -119, -111, 50, 32, 81, 74, 41, 69, 40, -96, -87, 21, 34, -92, 84, ByteCompanionObject.MIN_VALUE, 4, -64, -104, 19, 2, 96, -48, 26, 3, 64, 104, 0, -126, 80, 74, 9, 65, 32, 33, 36, 36, -124, -112, -112, 12, 33, -124, 48, -122, Tnaf.POW_2_WIDTH, 74, 9, 65, 40, 37, 0, 18, -94, 84, 74, -119, 80, -98, -50, -118, 123, 58, 32};
    }

    private static void createRandomMask44_44() {
        _randomMask44_44 = new byte[]{70, 72, -55, 25, 35, 32, 51, 38, 100, -52, -103, -112, -103, 19, 34, 100, 76, ByteCompanionObject.MIN_VALUE, 5, ByteCompanionObject.MIN_VALUE, -80, 22, 2, -64, ByteCompanionObject.MIN_VALUE, -80, 22, 2, -64, 80, -124, 80, -118, 17, 66, 32, 64, 104, 13, 1, -96, 48, 10, -127, 80, 42, 5, 64, 104, 13, 1, -96, 52, 0, Tnaf.POW_2_WIDTH, 34, 4, 64, -120, Tnaf.POW_2_WIDTH, 48, 70, 8, -63, 24, 32, 100, 76, -119, -111, 50, 32, 81, 74, 41, 69, 40, -96, -87, 21, 34, -92, 84, ByteCompanionObject.MIN_VALUE, 4, -64, -104, 19, 2, 96, -48, 26, 3, 64, 104, 0, -126, 80, 74, 9, 65, 32, 33, 36, 36, -124, -112, -112, 12, 33, -124, 48, -122, Tnaf.POW_2_WIDTH, 74, 9, 65, 40, 37, 0, 18, -94, 84, 74, -119, 80, -98, -50, -118, 123, 58, 32, 100, 76, -119, -111, 50, 32, 81, 74, 41, 69, 40, -96, -87, 21, 34, -92, 84, ByteCompanionObject.MIN_VALUE, 4, -64, -104, 19, 2, 96, -48, 26, 3, 64, 104, 0, -126, 80, 74, 9, 65, 32, 33, 36, 36, -124, -112, -112, 12, 33, -124, 48, -122, Tnaf.POW_2_WIDTH, 74, 9, 65, 40, 37, 0, 18, -94, 84, 74, -119, 80, 70, 72, -55, 25, 35, 32, 51, 38, 100, -52, -103, -112, -103, 19, 34, 100, 76, ByteCompanionObject.MIN_VALUE, 5, ByteCompanionObject.MIN_VALUE, -80, 22, 2, -64, ByteCompanionObject.MIN_VALUE, -80, 22, 2, -64, 80, -124, 80, -118, 17, 66, 32, 64, 104, 13, 1, -96, 48, 10, -127, 80, 42, 5, 64, 104, 13, 1, -96, 52, 0, Tnaf.POW_2_WIDTH, 34, 4, 64, -120, Tnaf.POW_2_WIDTH, 48, 70, 8, -63, 24, 32, -75, 28, 28, 33, -84, -96};
    }

    private static void createRandomMask44_5() {
        _randomMask44_5 = new byte[]{-58, -40, -37, 27, 99, 96, 99, 108, 109, -115, -79, -80, 29, -93, -76, 118, -114, -48, -83, 85, -86, -75, 86, -96, -78, -74, 86, -54, -39, 80};
    }

    private static void createRandomMask44_6() {
        _randomMask44_6 = new byte[]{100, 76, -119, -111, 50, 32, 81, 74, 41, 69, 40, -96, -88, 53, 6, -96, -44, Tnaf.POW_2_WIDTH, -60, -96, -105, 18, -126, 80, 3, 96, 108, 13, -127, -80, -112, -46, 26, 67, 72, 96};
    }

    private static void createRandomMask44_7() {
        _randomMask44_7 = new byte[]{-58, 72, -53, 25, 35, 32, 19, 38, 100, 76, -103, -112, -115, 19, -94, 52, 78, ByteCompanionObject.MIN_VALUE, -117, 65, 106, 45, 5, -96, 82, -86, 21, 74, -88, 80, -94, -44, 26, -117, 80, 96, 97, -88, 45, -122, -96, -80};
    }

    private static void createRandomMask44_8() {
        _randomMask44_8 = new byte[]{40, -123, 56, -94, 20, -32, 33, -12, 4, -121, -48, Tnaf.POW_2_WIDTH, -23, 29, 3, -92, 116, 0, 23, 2, -32, 92, 11, ByteCompanionObject.MIN_VALUE, -125, -96, 86, 14, -127, 80, 70, 24, -23, 24, 99, -96, 80, 106, 13, 65, -88, 48, 28, 35, -124, 112, -114, Tnaf.POW_2_WIDTH};
    }

    private static void createRandomMask44_9() {
        _randomMask44_9 = new byte[]{68, 72, -55, 17, 35, 32, 40, 45, 12, -96, -76, 48, 37, 20, -96, -108, 82, ByteCompanionObject.MIN_VALUE, 89, 10, 33, 100, 40, ByteCompanionObject.MIN_VALUE, 3, -96, 52, 14, ByteCompanionObject.MIN_VALUE, -48, -64, -48, 27, 3, 64, 96, -94, 48, 70, -120, -63, Tnaf.POW_2_WIDTH, 20, -126, -48, 82, 11, 64, -102, 3, -126, 104, 14, 0};
    }

    private static void createRandomMask45_1() {
        _randomMask45_1 = new byte[]{-1, -1, -1, -1, -1, -8};
    }

    private static void createRandomMask45_10() {
        _randomMask45_10 = new byte[]{-64, 56, -119, -111, 40, -96, 48, 14, 41, 69, 34, -120, -24, 7, 2, -92, 64, 104, -123, 8, -88, 19, 18, Tnaf.POW_2_WIDTH, -48, -110, 19, 64, 5, Tnaf.POW_2_WIDTH, -122, 80, 74, 9, 0, 112, 74, 104, 12, -124, -36, 0, 1, -96, 116, 48, -124, -120, 76, -127, -111, 40, 43, 0, 98, 36, 4, 74, -47, 64};
    }

    private static void createRandomMask45_11() {
        _randomMask45_11 = new byte[]{70, 72, -55, 25, 41, -80, 51, 38, 100, -52, -112, -104, -103, 19, 34, 100, 8, 72, 5, ByteCompanionObject.MIN_VALUE, -80, 22, 0, 56, ByteCompanionObject.MIN_VALUE, -80, 22, 2, -122, 8, -124, 80, -118, 17, 32, 96, 64, 104, 13, 1, -75, 0, 10, -127, 80, 42, 67, 0, 104, 13, 1, -96, 18, 64, Tnaf.POW_2_WIDTH, 34, 4, 64, -60, ByteCompanionObject.MIN_VALUE, 48, 70, 8, -63, 96, Tnaf.POW_2_WIDTH};
    }

    private static void createRandomMask45_12() {
        _randomMask45_12 = new byte[]{100, 76, -119, -111, 40, -96, 81, 74, 41, 69, 98, -120, 12, -95, -108, 50, -112, -64, -95, 52, 38, -124, -119, 24, 18, -94, 84, 74, -124, 112, -118, 81, 74, 41, 23, 0, -122, -112, -46, 26, 41, -80, 35, 36, 100, -116, -78, Tnaf.POW_2_WIDTH, 22, 34, -60, 88, -122, 96, 76, 41, -123, 48, -63, 80, 65, -56, 57, 7, 4, -104, -12, 24, -100, 101, 91, -112};
    }

    private static void createRandomMask45_13() {
        _randomMask45_13 = new byte[]{100, 76, -119, -111, 40, -96, 81, 74, 41, 69, 98, -120, 12, -95, -108, 50, -112, -64, -95, 52, 38, -124, -119, 24, 18, -94, 84, 74, -124, 112, -118, 81, 74, 41, 23, 0, 70, 72, -55, 25, 41, -80, 51, 38, 100, -52, -112, -104, -111, -110, 50, 70, 72, 72, -92, 52, -122, -112, -127, 40, 80, -86, 21, 66, -125, 80, -124, -48, -102, 19, 22, 0, 9, 97, 44, 37, -60, 48};
    }

    private static void createRandomMask45_14() {
        _randomMask45_14 = new byte[]{70, 72, -55, 25, 41, -80, 51, 38, 100, -52, -112, -104, -111, -110, 50, 70, 72, 72, -92, 52, -122, -112, -127, 40, 80, -86, 21, 66, -125, 80, -124, -48, -102, 19, 22, 0, 9, 97, 44, 37, -60, 48, 100, 76, -119, -111, 40, -96, 81, 74, 41, 69, 98, -120, 12, -95, -108, 50, -112, -64, -95, 52, 38, -124, -119, 24, 18, -94, 84, 74, -124, 112, -118, 81, 74, 41, 23, 0, -58, -54, -22, 112, -2, -56};
    }

    private static void createRandomMask45_15() {
        _randomMask45_15 = new byte[]{70, 72, -55, 25, 41, -80, 51, 38, 100, -52, -112, -104, -111, -110, 50, 70, 72, 72, -92, 52, -122, -112, -127, 40, 80, -86, 21, 66, -125, 80, -124, -48, -102, 19, 22, 0, 9, 97, 44, 37, -60, 48, 12, -127, -112, 50, Tnaf.POW_2_WIDTH, 48, ByteCompanionObject.MIN_VALUE, 112, 14, 1, -64, 24, -96, -108, 18, -126, 33, 32, 5, 64, -88, 21, 0, -56, 67, 8, 97, 12, 10, 8, 26, 3, 64, 104, 5, 64, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -100, 0, 20, 34, -124, 80, -30, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask45_16() {
        _randomMask45_16 = new byte[]{12, -127, -112, 50, Tnaf.POW_2_WIDTH, 48, ByteCompanionObject.MIN_VALUE, 112, 14, 1, -64, 24, -96, -108, 18, -126, 33, 32, 5, 64, -88, 21, 0, -56, 67, 8, 97, 12, 10, 8, 26, 3, 64, 104, 5, 64, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -100, 0, 20, 34, -124, 80, -30, ByteCompanionObject.MIN_VALUE, 70, 72, -55, 25, 41, -80, 51, 38, 100, -52, -112, -104, -111, -110, 50, 70, 72, 72, -92, 52, -122, -112, -127, 40, 80, -86, 21, 66, -125, 80, -124, -48, -102, 19, 22, 0, 9, 97, 44, 37, -60, 48, -122, -63, 71, -21, 103, -48};
    }

    private static void createRandomMask45_17() {
        _randomMask45_17 = new byte[]{12, -127, -112, 50, Tnaf.POW_2_WIDTH, 48, ByteCompanionObject.MIN_VALUE, 112, 14, 1, -64, 24, -96, -108, 18, -126, 33, 32, 5, 64, -88, 21, 0, -56, 67, 8, 97, 12, 10, 8, 26, 3, 64, 104, 5, 64, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -100, 0, 20, 34, -124, 80, -30, ByteCompanionObject.MIN_VALUE, 70, 72, -55, 25, 41, -80, 98, 108, 77, -119, -14, Tnaf.POW_2_WIDTH, -116, 17, -126, 48, 18, 32, 1, 96, 44, 5, -48, -120, 7, ByteCompanionObject.MIN_VALUE, -16, 30, 12, 24, -96, -108, 18, -126, 1, -56, 24, -93, 20, 98, -59, 8, -111, 18, 34, 68, 2, 72, 120, 15, 1, -32, 0, 112};
    }

    private static void createRandomMask45_18() {
        _randomMask45_18 = new byte[]{70, 72, -55, 25, 41, -80, 98, 108, 77, -119, -14, Tnaf.POW_2_WIDTH, -116, 17, -126, 48, 18, 32, 1, 96, 44, 5, -48, -120, 7, ByteCompanionObject.MIN_VALUE, -16, 30, 12, 24, -96, -108, 18, -126, 1, -56, 24, -93, 20, 98, -59, 8, -111, 18, 34, 68, 2, 72, 120, 15, 1, -32, 0, 112, 12, -127, -112, 50, Tnaf.POW_2_WIDTH, 48, ByteCompanionObject.MIN_VALUE, 112, 14, 1, -64, 24, -96, -108, 18, -126, 33, 32, 5, 64, -88, 21, 0, -56, 67, 8, 97, 12, 10, 8, 26, 3, 64, 104, 5, 64, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -100, 0, 20, 34, -124, 80, -30, ByteCompanionObject.MIN_VALUE, -28, -44, 110, 8, -55, 88};
    }

    private static void createRandomMask45_19() {
        _randomMask45_19 = new byte[]{70, 72, -55, 25, 41, -80, 98, 108, 77, -119, -14, Tnaf.POW_2_WIDTH, -116, 17, -126, 48, 18, 32, 1, 96, 44, 5, -48, -120, 7, ByteCompanionObject.MIN_VALUE, -16, 30, 12, 24, -96, -108, 18, -126, 1, -56, 24, -93, 20, 98, -59, 8, -111, 18, 34, 68, 2, 72, 120, 15, 1, -32, 0, 112, 100, 76, -119, -111, 40, -96, 81, 74, 41, 69, 34, -120, -87, 21, 34, -92, 64, 104, 4, -64, -104, 19, 18, Tnaf.POW_2_WIDTH, -48, 26, 3, 64, 5, Tnaf.POW_2_WIDTH, -126, 80, 74, 9, 0, 112, 33, 36, 36, -124, -36, 0, 12, 33, -124, 48, -124, -120, 74, 9, 65, 40, 43, 0, 18, -94, 84, 74, -47, 64};
    }

    private static void createRandomMask45_2() {
        _randomMask45_2 = new byte[]{-20, -35, -101, -77, 118, 96, -101, -77, 118, 110, -55, -40};
    }

    private static void createRandomMask45_20() {
        _randomMask45_20 = new byte[]{100, 76, -119, -111, 40, -96, 81, 74, 41, 69, 34, -120, -87, 21, 34, -92, 64, 104, 4, -64, -104, 19, 18, Tnaf.POW_2_WIDTH, -48, 26, 3, 64, 5, Tnaf.POW_2_WIDTH, -126, 80, 74, 9, 0, 112, 33, 36, 36, -124, -36, 0, 12, 33, -124, 48, -124, -120, 74, 9, 65, 40, 43, 0, 18, -94, 84, 74, -47, 64, 70, 72, -55, 25, 41, -80, 98, 108, 77, -119, -14, Tnaf.POW_2_WIDTH, -116, 17, -126, 48, 18, 32, 1, 96, 44, 5, -48, -120, 7, ByteCompanionObject.MIN_VALUE, -16, 30, 12, 24, -96, -108, 18, -126, 1, -56, 24, -93, 20, 98, -59, 8, -111, 18, 34, 68, 2, 72, 120, 15, 1, -32, 0, 112, 59, 72, -57, 109, 41, -24};
    }

    private static void createRandomMask45_21() {
        _randomMask45_21 = new byte[]{100, 76, -119, -111, 40, -96, 81, 74, 41, 69, 34, -120, -87, 21, 34, -92, 64, 104, 4, -64, -104, 19, 18, Tnaf.POW_2_WIDTH, -48, 26, 3, 64, 5, Tnaf.POW_2_WIDTH, -126, 80, 74, 9, 0, 112, 33, 36, 36, -124, -36, 0, 12, 33, -124, 48, -124, -120, 74, 9, 65, 40, 43, 0, 18, -94, 84, 74, -47, 64, 70, 72, -55, 25, 41, -80, 51, 38, 100, -52, -112, -104, -103, 19, 34, 100, 8, 72, 5, ByteCompanionObject.MIN_VALUE, -80, 22, 0, 56, ByteCompanionObject.MIN_VALUE, -80, 22, 2, -122, 8, -124, 80, -118, 17, 32, 96, 64, 104, 13, 1, -75, 0, 10, -127, 80, 42, 67, 0, 104, 13, 1, -96, 18, 64, Tnaf.POW_2_WIDTH, 34, 4, 64, -60, ByteCompanionObject.MIN_VALUE, 48, 70, 8, -63, 96, Tnaf.POW_2_WIDTH};
    }

    private static void createRandomMask45_22() {
        _randomMask45_22 = new byte[]{70, 72, -55, 25, 41, -80, 51, 38, 100, -52, -112, -104, -103, 19, 34, 100, 8, 72, 5, ByteCompanionObject.MIN_VALUE, -80, 22, 0, 56, ByteCompanionObject.MIN_VALUE, -80, 22, 2, -122, 8, -124, 80, -118, 17, 32, 96, 64, 104, 13, 1, -75, 0, 10, -127, 80, 42, 67, 0, 104, 13, 1, -96, 18, 64, Tnaf.POW_2_WIDTH, 34, 4, 64, -60, ByteCompanionObject.MIN_VALUE, 48, 70, 8, -63, 96, Tnaf.POW_2_WIDTH, 100, 76, -119, -111, 40, -96, 81, 74, 41, 69, 34, -120, -87, 21, 34, -92, 64, 104, 4, -64, -104, 19, 18, Tnaf.POW_2_WIDTH, -48, 26, 3, 64, 5, Tnaf.POW_2_WIDTH, -126, 80, 74, 9, 0, 112, 33, 36, 36, -124, -36, 0, 12, 33, -124, 48, -124, -120, 74, 9, 65, 40, 43, 0, 18, -94, 84, 74, -47, 64, -98, -50, -117, -86, 52, 104};
    }

    private static void createRandomMask45_23() {
        _randomMask45_23 = new byte[]{70, 72, -55, 25, 41, -80, 51, 38, 100, -52, -112, -104, -103, 19, 34, 100, 8, 72, 5, ByteCompanionObject.MIN_VALUE, -80, 22, 0, 56, ByteCompanionObject.MIN_VALUE, -80, 22, 2, -122, 8, -124, 80, -118, 17, 32, 96, 64, 104, 13, 1, -75, 0, 10, -127, 80, 42, 67, 0, 104, 13, 1, -96, 18, 64, Tnaf.POW_2_WIDTH, 34, 4, 64, -60, ByteCompanionObject.MIN_VALUE, 48, 70, 8, -63, 96, Tnaf.POW_2_WIDTH, 100, 76, -119, -111, 40, -96, 81, 74, 41, 69, 98, -120, 12, -95, -108, 50, -112, -64, -95, 52, 38, -124, -119, 24, 18, -94, 84, 74, -124, 112, -118, 81, 74, 41, 23, 0, -122, -112, -46, 26, 41, -80, 35, 36, 100, -116, -78, Tnaf.POW_2_WIDTH, 22, 34, -60, 88, -122, 96, 76, 41, -123, 48, -63, 80, 65, -56, 57, 7, 4, -104, -12, 24, -100, 101, 91, -112};
    }

    private static void createRandomMask45_24() {
        _randomMask45_24 = new byte[]{100, 76, -119, -111, 40, -96, 81, 74, 41, 69, 98, -120, 12, -95, -108, 50, -112, -64, -95, 52, 38, -124, -119, 24, 18, -94, 84, 74, -124, 112, -118, 81, 74, 41, 23, 0, -122, -112, -46, 26, 41, -80, 35, 36, 100, -116, -78, Tnaf.POW_2_WIDTH, 22, 34, -60, 88, -122, 96, 76, 41, -123, 48, -63, 80, 65, -56, 57, 7, 4, -104, -12, 24, -100, 101, 91, -112, 70, 72, -55, 25, 41, -80, 51, 38, 100, -52, -112, -104, -103, 19, 34, 100, 8, 72, 5, ByteCompanionObject.MIN_VALUE, -80, 22, 0, 56, ByteCompanionObject.MIN_VALUE, -80, 22, 2, -122, 8, -124, 80, -118, 17, 32, 96, 64, 104, 13, 1, -75, 0, 10, -127, 80, 42, 67, 0, 104, 13, 1, -96, 18, 64, Tnaf.POW_2_WIDTH, 34, 4, 64, -60, ByteCompanionObject.MIN_VALUE, 48, 70, 8, -63, 96, Tnaf.POW_2_WIDTH, -107, -111, -83, -39, -122, -104};
    }

    private static void createRandomMask45_25() {
        _randomMask45_25 = new byte[]{100, 76, -119, -111, 40, -96, 81, 74, 41, 69, 98, -120, 12, -95, -108, 50, -112, -64, -95, 52, 38, -124, -119, 24, 18, -94, 84, 74, -124, 112, -118, 81, 74, 41, 23, 0, -122, -112, -46, 26, 41, -80, 35, 36, 100, -116, -78, Tnaf.POW_2_WIDTH, 22, 34, -60, 88, -122, 96, 76, 41, -123, 48, -63, 80, 65, -56, 57, 7, 4, -104, -12, 24, -100, 101, 91, -112, 100, 76, -119, -111, 40, -96, 81, 74, 41, 69, 98, -120, 12, -95, -108, 50, -112, -64, -95, 52, 38, -124, -119, 24, 18, -94, 84, 74, -124, 112, -118, 81, 74, 41, 23, 0, 70, 72, -55, 25, 41, -80, 51, 38, 100, -52, -112, -104, -111, -110, 50, 70, 72, 72, -92, 52, -122, -112, -127, 40, 80, -86, 21, 66, -125, 80, -124, -48, -102, 19, 22, 0, 9, 97, 44, 37, -60, 48};
    }

    private static void createRandomMask45_26() {
        _randomMask45_26 = new byte[]{100, 76, -119, -111, 40, -96, 81, 74, 41, 69, 98, -120, 12, -95, -108, 50, -112, -64, -95, 52, 38, -124, -119, 24, 18, -94, 84, 74, -124, 112, -118, 81, 74, 41, 23, 0, 70, 72, -55, 25, 41, -80, 51, 38, 100, -52, -112, -104, -111, -110, 50, 70, 72, 72, -92, 52, -122, -112, -127, 40, 80, -86, 21, 66, -125, 80, -124, -48, -102, 19, 22, 0, 9, 97, 44, 37, -60, 48, 100, 76, -119, -111, 40, -96, 81, 74, 41, 69, 98, -120, 12, -95, -108, 50, -112, -64, -95, 52, 38, -124, -119, 24, 18, -94, 84, 74, -124, 112, -118, 81, 74, 41, 23, 0, -122, -112, -46, 26, 41, -80, 35, 36, 100, -116, -78, Tnaf.POW_2_WIDTH, 22, 34, -60, 88, -122, 96, 76, 41, -123, 48, -63, 80, 65, -56, 57, 7, 4, -104, -12, 24, -100, 101, 91, -112, -80, -3, -78, -13, -118, -64};
    }

    private static void createRandomMask45_27() {
        _randomMask45_27 = new byte[]{100, 76, -119, -111, 40, -96, 81, 74, 41, 69, 98, -120, 12, -95, -108, 50, -112, -64, -95, 52, 38, -124, -119, 24, 18, -94, 84, 74, -124, 112, -118, 81, 74, 41, 23, 0, 70, 72, -55, 25, 41, -80, 51, 38, 100, -52, -112, -104, -111, -110, 50, 70, 72, 72, -92, 52, -122, -112, -127, 40, 80, -86, 21, 66, -125, 80, -124, -48, -102, 19, 22, 0, 9, 97, 44, 37, -60, 48, 70, 72, -55, 25, 41, -80, 51, 38, 100, -52, -112, -104, -111, -110, 50, 70, 72, 72, -92, 52, -122, -112, -127, 40, 80, -86, 21, 66, -125, 80, -124, -48, -102, 19, 22, 0, 9, 97, 44, 37, -60, 48, 100, 76, -119, -111, 40, -96, 81, 74, 41, 69, 98, -120, 12, -95, -108, 50, -112, -64, -95, 52, 38, -124, -119, 24, 18, -94, 84, 74, -124, 112, -118, 81, 74, 41, 23, 0, -58, -54, -22, 112, -2, -56};
    }

    private static void createRandomMask45_28() {
        _randomMask45_28 = new byte[]{70, 72, -55, 25, 41, -80, 51, 38, 100, -52, -112, -104, -111, -110, 50, 70, 72, 72, -92, 52, -122, -112, -127, 40, 80, -86, 21, 66, -125, 80, -124, -48, -102, 19, 22, 0, 9, 97, 44, 37, -60, 48, 100, 76, -119, -111, 40, -96, 81, 74, 41, 69, 98, -120, 12, -95, -108, 50, -112, -64, -95, 52, 38, -124, -119, 24, 18, -94, 84, 74, -124, 112, -118, 81, 74, 41, 23, 0, -58, -54, -22, 112, -2, -56, 100, 76, -119, -111, 40, -96, 81, 74, 41, 69, 98, -120, 12, -95, -108, 50, -112, -64, -95, 52, 38, -124, -119, 24, 18, -94, 84, 74, -124, 112, -118, 81, 74, 41, 23, 0, 70, 72, -55, 25, 41, -80, 51, 38, 100, -52, -112, -104, -111, -110, 50, 70, 72, 72, -92, 52, -122, -112, -127, 40, 80, -86, 21, 66, -125, 80, -124, -48, -102, 19, 22, 0, 9, 97, 44, 37, -60, 48, 68, 70, 40, -5, 102, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask45_29() {
        _randomMask45_29 = new byte[]{70, 72, -55, 25, 41, -80, 51, 38, 100, -52, -112, -104, -111, -110, 50, 70, 72, 72, -92, 52, -122, -112, -127, 40, 80, -86, 21, 66, -125, 80, -124, -48, -102, 19, 22, 0, 9, 97, 44, 37, -60, 48, 100, 76, -119, -111, 40, -96, 81, 74, 41, 69, 98, -120, 12, -95, -108, 50, -112, -64, -95, 52, 38, -124, -119, 24, 18, -94, 84, 74, -124, 112, -118, 81, 74, 41, 23, 0, -58, -54, -22, 112, -2, -56, 70, 72, -55, 25, 41, -80, 51, 38, 100, -52, -112, -104, -111, -110, 50, 70, 72, 72, -92, 52, -122, -112, -127, 40, 80, -86, 21, 66, -125, 80, -124, -48, -102, 19, 22, 0, 9, 97, 44, 37, -60, 48, 12, -127, -112, 50, Tnaf.POW_2_WIDTH, 48, ByteCompanionObject.MIN_VALUE, 112, 14, 1, -64, 24, -96, -108, 18, -126, 33, 32, 5, 64, -88, 21, 0, -56, 67, 8, 97, 12, 10, 8, 26, 3, 64, 104, 5, 64, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -100, 0, 20, 34, -124, 80, -30, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask45_3() {
        _randomMask45_3 = new byte[]{-54, -39, 91, 43, 77, -112, -15, 94, 43, -59, 36, -24, -74, 53, -59, -40, -97, 64};
    }

    private static void createRandomMask45_30() {
        _randomMask45_30 = new byte[]{70, 72, -55, 25, 41, -80, 51, 38, 100, -52, -112, -104, -111, -110, 50, 70, 72, 72, -92, 52, -122, -112, -127, 40, 80, -86, 21, 66, -125, 80, -124, -48, -102, 19, 22, 0, 9, 97, 44, 37, -60, 48, 12, -127, -112, 50, Tnaf.POW_2_WIDTH, 48, ByteCompanionObject.MIN_VALUE, 112, 14, 1, -64, 24, -96, -108, 18, -126, 33, 32, 5, 64, -88, 21, 0, -56, 67, 8, 97, 12, 10, 8, 26, 3, 64, 104, 5, 64, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -100, 0, 20, 34, -124, 80, -30, ByteCompanionObject.MIN_VALUE, 70, 72, -55, 25, 41, -80, 51, 38, 100, -52, -112, -104, -111, -110, 50, 70, 72, 72, -92, 52, -122, -112, -127, 40, 80, -86, 21, 66, -125, 80, -124, -48, -102, 19, 22, 0, 9, 97, 44, 37, -60, 48, 100, 76, -119, -111, 40, -96, 81, 74, 41, 69, 98, -120, 12, -95, -108, 50, -112, -64, -95, 52, 38, -124, -119, 24, 18, -94, 84, 74, -124, 112, -118, 81, 74, 41, 23, 0, -58, -54, -22, 112, -2, -56, 28, -55, 67, 37, -89, 0};
    }

    private static void createRandomMask45_31() {
        _randomMask45_31 = new byte[]{70, 72, -55, 25, 41, -80, 51, 38, 100, -52, -112, -104, -111, -110, 50, 70, 72, 72, -92, 52, -122, -112, -127, 40, 80, -86, 21, 66, -125, 80, -124, -48, -102, 19, 22, 0, 9, 97, 44, 37, -60, 48, 12, -127, -112, 50, Tnaf.POW_2_WIDTH, 48, ByteCompanionObject.MIN_VALUE, 112, 14, 1, -64, 24, -96, -108, 18, -126, 33, 32, 5, 64, -88, 21, 0, -56, 67, 8, 97, 12, 10, 8, 26, 3, 64, 104, 5, 64, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -100, 0, 20, 34, -124, 80, -30, ByteCompanionObject.MIN_VALUE, 12, -127, -112, 50, Tnaf.POW_2_WIDTH, 48, ByteCompanionObject.MIN_VALUE, 112, 14, 1, -64, 24, -96, -108, 18, -126, 33, 32, 5, 64, -88, 21, 0, -56, 67, 8, 97, 12, 10, 8, 26, 3, 64, 104, 5, 64, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -100, 0, 20, 34, -124, 80, -30, ByteCompanionObject.MIN_VALUE, 70, 72, -55, 25, 41, -80, 51, 38, 100, -52, -112, -104, -111, -110, 50, 70, 72, 72, -92, 52, -122, -112, -127, 40, 80, -86, 21, 66, -125, 80, -124, -48, -102, 19, 22, 0, 9, 97, 44, 37, -60, 48, -122, -63, 71, -21, 103, -48};
    }

    private static void createRandomMask45_32() {
        _randomMask45_32 = new byte[]{12, -127, -112, 50, Tnaf.POW_2_WIDTH, 48, ByteCompanionObject.MIN_VALUE, 112, 14, 1, -64, 24, -96, -108, 18, -126, 33, 32, 5, 64, -88, 21, 0, -56, 67, 8, 97, 12, 10, 8, 26, 3, 64, 104, 5, 64, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -100, 0, 20, 34, -124, 80, -30, ByteCompanionObject.MIN_VALUE, 70, 72, -55, 25, 41, -80, 51, 38, 100, -52, -112, -104, -111, -110, 50, 70, 72, 72, -92, 52, -122, -112, -127, 40, 80, -86, 21, 66, -125, 80, -124, -48, -102, 19, 22, 0, 9, 97, 44, 37, -60, 48, -122, -63, 71, -21, 103, -48, 70, 72, -55, 25, 41, -80, 51, 38, 100, -52, -112, -104, -111, -110, 50, 70, 72, 72, -92, 52, -122, -112, -127, 40, 80, -86, 21, 66, -125, 80, -124, -48, -102, 19, 22, 0, 9, 97, 44, 37, -60, 48, 12, -127, -112, 50, Tnaf.POW_2_WIDTH, 48, ByteCompanionObject.MIN_VALUE, 112, 14, 1, -64, 24, -96, -108, 18, -126, 33, 32, 5, 64, -88, 21, 0, -56, 67, 8, 97, 12, 10, 8, 26, 3, 64, 104, 5, 64, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -100, 0, 20, 34, -124, 80, -30, ByteCompanionObject.MIN_VALUE, 64, 126, -63, 48, 41, 80};
    }

    private static void createRandomMask45_33() {
        _randomMask45_33 = new byte[]{12, -127, -112, 50, Tnaf.POW_2_WIDTH, 48, ByteCompanionObject.MIN_VALUE, 112, 14, 1, -64, 24, -96, -108, 18, -126, 33, 32, 5, 64, -88, 21, 0, -56, 67, 8, 97, 12, 10, 8, 26, 3, 64, 104, 5, 64, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -100, 0, 20, 34, -124, 80, -30, ByteCompanionObject.MIN_VALUE, 70, 72, -55, 25, 41, -80, 51, 38, 100, -52, -112, -104, -111, -110, 50, 70, 72, 72, -92, 52, -122, -112, -127, 40, 80, -86, 21, 66, -125, 80, -124, -48, -102, 19, 22, 0, 9, 97, 44, 37, -60, 48, -122, -63, 71, -21, 103, -48, 12, -127, -112, 50, Tnaf.POW_2_WIDTH, 48, ByteCompanionObject.MIN_VALUE, 112, 14, 1, -64, 24, -96, -108, 18, -126, 33, 32, 5, 64, -88, 21, 0, -56, 67, 8, 97, 12, 10, 8, 26, 3, 64, 104, 5, 64, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -100, 0, 20, 34, -124, 80, -30, ByteCompanionObject.MIN_VALUE, 70, 72, -55, 25, 41, -80, 98, 108, 77, -119, -14, Tnaf.POW_2_WIDTH, -116, 17, -126, 48, 18, 32, 1, 96, 44, 5, -48, -120, 7, ByteCompanionObject.MIN_VALUE, -16, 30, 12, 24, -96, -108, 18, -126, 1, -56, 24, -93, 20, 98, -59, 8, -111, 18, 34, 68, 2, 72, 120, 15, 1, -32, 0, 112};
    }

    private static void createRandomMask45_34() {
        _randomMask45_34 = new byte[]{12, -127, -112, 50, Tnaf.POW_2_WIDTH, 48, ByteCompanionObject.MIN_VALUE, 112, 14, 1, -64, 24, -96, -108, 18, -126, 33, 32, 5, 64, -88, 21, 0, -56, 67, 8, 97, 12, 10, 8, 26, 3, 64, 104, 5, 64, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -100, 0, 20, 34, -124, 80, -30, ByteCompanionObject.MIN_VALUE, 70, 72, -55, 25, 41, -80, 98, 108, 77, -119, -14, Tnaf.POW_2_WIDTH, -116, 17, -126, 48, 18, 32, 1, 96, 44, 5, -48, -120, 7, ByteCompanionObject.MIN_VALUE, -16, 30, 12, 24, -96, -108, 18, -126, 1, -56, 24, -93, 20, 98, -59, 8, -111, 18, 34, 68, 2, 72, 120, 15, 1, -32, 0, 112, 12, -127, -112, 50, Tnaf.POW_2_WIDTH, 48, ByteCompanionObject.MIN_VALUE, 112, 14, 1, -64, 24, -96, -108, 18, -126, 33, 32, 5, 64, -88, 21, 0, -56, 67, 8, 97, 12, 10, 8, 26, 3, 64, 104, 5, 64, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -100, 0, 20, 34, -124, 80, -30, ByteCompanionObject.MIN_VALUE, 70, 72, -55, 25, 41, -80, 51, 38, 100, -52, -112, -104, -111, -110, 50, 70, 72, 72, -92, 52, -122, -112, -127, 40, 80, -86, 21, 66, -125, 80, -124, -48, -102, 19, 22, 0, 9, 97, 44, 37, -60, 48, -122, -63, 71, -21, 103, -48, 31, 120, 69, 94, 70, 80};
    }

    private static void createRandomMask45_35() {
        _randomMask45_35 = new byte[]{12, -127, -112, 50, Tnaf.POW_2_WIDTH, 48, ByteCompanionObject.MIN_VALUE, 112, 14, 1, -64, 24, -96, -108, 18, -126, 33, 32, 5, 64, -88, 21, 0, -56, 67, 8, 97, 12, 10, 8, 26, 3, 64, 104, 5, 64, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -100, 0, 20, 34, -124, 80, -30, ByteCompanionObject.MIN_VALUE, 70, 72, -55, 25, 41, -80, 98, 108, 77, -119, -14, Tnaf.POW_2_WIDTH, -116, 17, -126, 48, 18, 32, 1, 96, 44, 5, -48, -120, 7, ByteCompanionObject.MIN_VALUE, -16, 30, 12, 24, -96, -108, 18, -126, 1, -56, 24, -93, 20, 98, -59, 8, -111, 18, 34, 68, 2, 72, 120, 15, 1, -32, 0, 112, 70, 72, -55, 25, 41, -80, 98, 108, 77, -119, -14, Tnaf.POW_2_WIDTH, -116, 17, -126, 48, 18, 32, 1, 96, 44, 5, -48, -120, 7, ByteCompanionObject.MIN_VALUE, -16, 30, 12, 24, -96, -108, 18, -126, 1, -56, 24, -93, 20, 98, -59, 8, -111, 18, 34, 68, 2, 72, 120, 15, 1, -32, 0, 112, 12, -127, -112, 50, Tnaf.POW_2_WIDTH, 48, ByteCompanionObject.MIN_VALUE, 112, 14, 1, -64, 24, -96, -108, 18, -126, 33, 32, 5, 64, -88, 21, 0, -56, 67, 8, 97, 12, 10, 8, 26, 3, 64, 104, 5, 64, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -100, 0, 20, 34, -124, 80, -30, ByteCompanionObject.MIN_VALUE, -28, -44, 110, 8, -55, 88};
    }

    private static void createRandomMask45_36() {
        _randomMask45_36 = new byte[]{70, 72, -55, 25, 41, -80, 98, 108, 77, -119, -14, Tnaf.POW_2_WIDTH, -116, 17, -126, 48, 18, 32, 1, 96, 44, 5, -48, -120, 7, ByteCompanionObject.MIN_VALUE, -16, 30, 12, 24, -96, -108, 18, -126, 1, -56, 24, -93, 20, 98, -59, 8, -111, 18, 34, 68, 2, 72, 120, 15, 1, -32, 0, 112, 12, -127, -112, 50, Tnaf.POW_2_WIDTH, 48, ByteCompanionObject.MIN_VALUE, 112, 14, 1, -64, 24, -96, -108, 18, -126, 33, 32, 5, 64, -88, 21, 0, -56, 67, 8, 97, 12, 10, 8, 26, 3, 64, 104, 5, 64, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -100, 0, 20, 34, -124, 80, -30, ByteCompanionObject.MIN_VALUE, -28, -44, 110, 8, -55, 88, 12, -127, -112, 50, Tnaf.POW_2_WIDTH, 48, ByteCompanionObject.MIN_VALUE, 112, 14, 1, -64, 24, -96, -108, 18, -126, 33, 32, 5, 64, -88, 21, 0, -56, 67, 8, 97, 12, 10, 8, 26, 3, 64, 104, 5, 64, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -100, 0, 20, 34, -124, 80, -30, ByteCompanionObject.MIN_VALUE, 70, 72, -55, 25, 41, -80, 98, 108, 77, -119, -14, Tnaf.POW_2_WIDTH, -116, 17, -126, 48, 18, 32, 1, 96, 44, 5, -48, -120, 7, ByteCompanionObject.MIN_VALUE, -16, 30, 12, 24, -96, -108, 18, -126, 1, -56, 24, -93, 20, 98, -59, 8, -111, 18, 34, 68, 2, 72, 120, 15, 1, -32, 0, 112, -48, 26, -16, 20, -16, -24};
    }

    private static void createRandomMask45_37() {
        _randomMask45_37 = new byte[]{70, 72, -55, 25, 41, -80, 98, 108, 77, -119, -14, Tnaf.POW_2_WIDTH, -116, 17, -126, 48, 18, 32, 1, 96, 44, 5, -48, -120, 7, ByteCompanionObject.MIN_VALUE, -16, 30, 12, 24, -96, -108, 18, -126, 1, -56, 24, -93, 20, 98, -59, 8, -111, 18, 34, 68, 2, 72, 120, 15, 1, -32, 0, 112, 12, -127, -112, 50, Tnaf.POW_2_WIDTH, 48, ByteCompanionObject.MIN_VALUE, 112, 14, 1, -64, 24, -96, -108, 18, -126, 33, 32, 5, 64, -88, 21, 0, -56, 67, 8, 97, 12, 10, 8, 26, 3, 64, 104, 5, 64, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -100, 0, 20, 34, -124, 80, -30, ByteCompanionObject.MIN_VALUE, -28, -44, 110, 8, -55, 88, 70, 72, -55, 25, 41, -80, 98, 108, 77, -119, -14, Tnaf.POW_2_WIDTH, -116, 17, -126, 48, 18, 32, 1, 96, 44, 5, -48, -120, 7, ByteCompanionObject.MIN_VALUE, -16, 30, 12, 24, -96, -108, 18, -126, 1, -56, 24, -93, 20, 98, -59, 8, -111, 18, 34, 68, 2, 72, 120, 15, 1, -32, 0, 112, 100, 76, -119, -111, 40, -96, 81, 74, 41, 69, 34, -120, -87, 21, 34, -92, 64, 104, 4, -64, -104, 19, 18, Tnaf.POW_2_WIDTH, -48, 26, 3, 64, 5, Tnaf.POW_2_WIDTH, -126, 80, 74, 9, 0, 112, 33, 36, 36, -124, -36, 0, 12, 33, -124, 48, -124, -120, 74, 9, 65, 40, 43, 0, 18, -94, 84, 74, -47, 64};
    }

    private static void createRandomMask45_38() {
        _randomMask45_38 = new byte[]{70, 72, -55, 25, 41, -80, 98, 108, 77, -119, -14, Tnaf.POW_2_WIDTH, -116, 17, -126, 48, 18, 32, 1, 96, 44, 5, -48, -120, 7, ByteCompanionObject.MIN_VALUE, -16, 30, 12, 24, -96, -108, 18, -126, 1, -56, 24, -93, 20, 98, -59, 8, -111, 18, 34, 68, 2, 72, 120, 15, 1, -32, 0, 112, 100, 76, -119, -111, 40, -96, 81, 74, 41, 69, 34, -120, -87, 21, 34, -92, 64, 104, 4, -64, -104, 19, 18, Tnaf.POW_2_WIDTH, -48, 26, 3, 64, 5, Tnaf.POW_2_WIDTH, -126, 80, 74, 9, 0, 112, 33, 36, 36, -124, -36, 0, 12, 33, -124, 48, -124, -120, 74, 9, 65, 40, 43, 0, 18, -94, 84, 74, -47, 64, 70, 72, -55, 25, 41, -80, 98, 108, 77, -119, -14, Tnaf.POW_2_WIDTH, -116, 17, -126, 48, 18, 32, 1, 96, 44, 5, -48, -120, 7, ByteCompanionObject.MIN_VALUE, -16, 30, 12, 24, -96, -108, 18, -126, 1, -56, 24, -93, 20, 98, -59, 8, -111, 18, 34, 68, 2, 72, 120, 15, 1, -32, 0, 112, 12, -127, -112, 50, Tnaf.POW_2_WIDTH, 48, ByteCompanionObject.MIN_VALUE, 112, 14, 1, -64, 24, -96, -108, 18, -126, 33, 32, 5, 64, -88, 21, 0, -56, 67, 8, 97, 12, 10, 8, 26, 3, 64, 104, 5, 64, 96, 44, 5, ByteCompanionObject.MIN_VALUE, -100, 0, 20, 34, -124, 80, -30, ByteCompanionObject.MIN_VALUE, -28, -44, 110, 8, -55, 88, 4, 103, 27, -70, 29, -96};
    }

    private static void createRandomMask45_39() {
        _randomMask45_39 = new byte[]{70, 72, -55, 25, 41, -80, 98, 108, 77, -119, -14, Tnaf.POW_2_WIDTH, -116, 17, -126, 48, 18, 32, 1, 96, 44, 5, -48, -120, 7, ByteCompanionObject.MIN_VALUE, -16, 30, 12, 24, -96, -108, 18, -126, 1, -56, 24, -93, 20, 98, -59, 8, -111, 18, 34, 68, 2, 72, 120, 15, 1, -32, 0, 112, 100, 76, -119, -111, 40, -96, 81, 74, 41, 69, 34, -120, -87, 21, 34, -92, 64, 104, 4, -64, -104, 19, 18, Tnaf.POW_2_WIDTH, -48, 26, 3, 64, 5, Tnaf.POW_2_WIDTH, -126, 80, 74, 9, 0, 112, 33, 36, 36, -124, -36, 0, 12, 33, -124, 48, -124, -120, 74, 9, 65, 40, 43, 0, 18, -94, 84, 74, -47, 64, 100, 76, -119, -111, 40, -96, 81, 74, 41, 69, 34, -120, -87, 21, 34, -92, 64, 104, 4, -64, -104, 19, 18, Tnaf.POW_2_WIDTH, -48, 26, 3, 64, 5, Tnaf.POW_2_WIDTH, -126, 80, 74, 9, 0, 112, 33, 36, 36, -124, -36, 0, 12, 33, -124, 48, -124, -120, 74, 9, 65, 40, 43, 0, 18, -94, 84, 74, -47, 64, 70, 72, -55, 25, 41, -80, 98, 108, 77, -119, -14, Tnaf.POW_2_WIDTH, -116, 17, -126, 48, 18, 32, 1, 96, 44, 5, -48, -120, 7, ByteCompanionObject.MIN_VALUE, -16, 30, 12, 24, -96, -108, 18, -126, 1, -56, 24, -93, 20, 98, -59, 8, -111, 18, 34, 68, 2, 72, 120, 15, 1, -32, 0, 112, 59, 72, -57, 109, 41, -24};
    }

    private static void createRandomMask45_4() {
        _randomMask45_4 = new byte[]{-60, -40, -101, 19, 69, -112, 49, 102, 44, -59, -118, 88, 75, 41, 101, 44, -111, 104, 44, -91, -108, -78, -94, -88};
    }

    private static void createRandomMask45_40() {
        _randomMask45_40 = new byte[]{100, 76, -119, -111, 40, -96, 81, 74, 41, 69, 34, -120, -87, 21, 34, -92, 64, 104, 4, -64, -104, 19, 18, Tnaf.POW_2_WIDTH, -48, 26, 3, 64, 5, Tnaf.POW_2_WIDTH, -126, 80, 74, 9, 0, 112, 33, 36, 36, -124, -36, 0, 12, 33, -124, 48, -124, -120, 74, 9, 65, 40, 43, 0, 18, -94, 84, 74, -47, 64, 70, 72, -55, 25, 41, -80, 98, 108, 77, -119, -14, Tnaf.POW_2_WIDTH, -116, 17, -126, 48, 18, 32, 1, 96, 44, 5, -48, -120, 7, ByteCompanionObject.MIN_VALUE, -16, 30, 12, 24, -96, -108, 18, -126, 1, -56, 24, -93, 20, 98, -59, 8, -111, 18, 34, 68, 2, 72, 120, 15, 1, -32, 0, 112, 59, 72, -57, 109, 41, -24, 70, 72, -55, 25, 41, -80, 98, 108, 77, -119, -14, Tnaf.POW_2_WIDTH, -116, 17, -126, 48, 18, 32, 1, 96, 44, 5, -48, -120, 7, ByteCompanionObject.MIN_VALUE, -16, 30, 12, 24, -96, -108, 18, -126, 1, -56, 24, -93, 20, 98, -59, 8, -111, 18, 34, 68, 2, 72, 120, 15, 1, -32, 0, 112, 100, 76, -119, -111, 40, -96, 81, 74, 41, 69, 34, -120, -87, 21, 34, -92, 64, 104, 4, -64, -104, 19, 18, Tnaf.POW_2_WIDTH, -48, 26, 3, 64, 5, Tnaf.POW_2_WIDTH, -126, 80, 74, 9, 0, 112, 33, 36, 36, -124, -36, 0, 12, 33, -124, 48, -124, -120, 74, 9, 65, 40, 43, 0, 18, -94, 84, 74, -47, 64, -39, 64, 70, -26, 79, -40};
    }

    private static void createRandomMask45_41() {
        _randomMask45_41 = new byte[]{100, 76, -119, -111, 40, -96, 81, 74, 41, 69, 34, -120, -87, 21, 34, -92, 64, 104, 4, -64, -104, 19, 18, Tnaf.POW_2_WIDTH, -48, 26, 3, 64, 5, Tnaf.POW_2_WIDTH, -126, 80, 74, 9, 0, 112, 33, 36, 36, -124, -36, 0, 12, 33, -124, 48, -124, -120, 74, 9, 65, 40, 43, 0, 18, -94, 84, 74, -47, 64, 70, 72, -55, 25, 41, -80, 98, 108, 77, -119, -14, Tnaf.POW_2_WIDTH, -116, 17, -126, 48, 18, 32, 1, 96, 44, 5, -48, -120, 7, ByteCompanionObject.MIN_VALUE, -16, 30, 12, 24, -96, -108, 18, -126, 1, -56, 24, -93, 20, 98, -59, 8, -111, 18, 34, 68, 2, 72, 120, 15, 1, -32, 0, 112, 59, 72, -57, 109, 41, -24, 100, 76, -119, -111, 40, -96, 81, 74, 41, 69, 34, -120, -87, 21, 34, -92, 64, 104, 4, -64, -104, 19, 18, Tnaf.POW_2_WIDTH, -48, 26, 3, 64, 5, Tnaf.POW_2_WIDTH, -126, 80, 74, 9, 0, 112, 33, 36, 36, -124, -36, 0, 12, 33, -124, 48, -124, -120, 74, 9, 65, 40, 43, 0, 18, -94, 84, 74, -47, 64, 70, 72, -55, 25, 41, -80, 51, 38, 100, -52, -112, -104, -103, 19, 34, 100, 8, 72, 5, ByteCompanionObject.MIN_VALUE, -80, 22, 0, 56, ByteCompanionObject.MIN_VALUE, -80, 22, 2, -122, 8, -124, 80, -118, 17, 32, 96, 64, 104, 13, 1, -75, 0, 10, -127, 80, 42, 67, 0, 104, 13, 1, -96, 18, 64, Tnaf.POW_2_WIDTH, 34, 4, 64, -60, ByteCompanionObject.MIN_VALUE, 48, 70, 8, -63, 96, Tnaf.POW_2_WIDTH};
    }

    private static void createRandomMask45_42() {
        _randomMask45_42 = new byte[]{100, 76, -119, -111, 40, -96, 81, 74, 41, 69, 34, -120, -87, 21, 34, -92, 64, 104, 4, -64, -104, 19, 18, Tnaf.POW_2_WIDTH, -48, 26, 3, 64, 5, Tnaf.POW_2_WIDTH, -126, 80, 74, 9, 0, 112, 33, 36, 36, -124, -36, 0, 12, 33, -124, 48, -124, -120, 74, 9, 65, 40, 43, 0, 18, -94, 84, 74, -47, 64, 70, 72, -55, 25, 41, -80, 51, 38, 100, -52, -112, -104, -103, 19, 34, 100, 8, 72, 5, ByteCompanionObject.MIN_VALUE, -80, 22, 0, 56, ByteCompanionObject.MIN_VALUE, -80, 22, 2, -122, 8, -124, 80, -118, 17, 32, 96, 64, 104, 13, 1, -75, 0, 10, -127, 80, 42, 67, 0, 104, 13, 1, -96, 18, 64, Tnaf.POW_2_WIDTH, 34, 4, 64, -60, ByteCompanionObject.MIN_VALUE, 48, 70, 8, -63, 96, Tnaf.POW_2_WIDTH, 100, 76, -119, -111, 40, -96, 81, 74, 41, 69, 34, -120, -87, 21, 34, -92, 64, 104, 4, -64, -104, 19, 18, Tnaf.POW_2_WIDTH, -48, 26, 3, 64, 5, Tnaf.POW_2_WIDTH, -126, 80, 74, 9, 0, 112, 33, 36, 36, -124, -36, 0, 12, 33, -124, 48, -124, -120, 74, 9, 65, 40, 43, 0, 18, -94, 84, 74, -47, 64, 70, 72, -55, 25, 41, -80, 98, 108, 77, -119, -14, Tnaf.POW_2_WIDTH, -116, 17, -126, 48, 18, 32, 1, 96, 44, 5, -48, -120, 7, ByteCompanionObject.MIN_VALUE, -16, 30, 12, 24, -96, -108, 18, -126, 1, -56, 24, -93, 20, 98, -59, 8, -111, 18, 34, 68, 2, 72, 120, 15, 1, -32, 0, 112, 59, 72, -57, 109, 41, -24, -84, -52, 4, 65, -105, 48};
    }

    private static void createRandomMask45_43() {
        _randomMask45_43 = new byte[]{100, 76, -119, -111, 40, -96, 81, 74, 41, 69, 34, -120, -87, 21, 34, -92, 64, 104, 4, -64, -104, 19, 18, Tnaf.POW_2_WIDTH, -48, 26, 3, 64, 5, Tnaf.POW_2_WIDTH, -126, 80, 74, 9, 0, 112, 33, 36, 36, -124, -36, 0, 12, 33, -124, 48, -124, -120, 74, 9, 65, 40, 43, 0, 18, -94, 84, 74, -47, 64, 70, 72, -55, 25, 41, -80, 51, 38, 100, -52, -112, -104, -103, 19, 34, 100, 8, 72, 5, ByteCompanionObject.MIN_VALUE, -80, 22, 0, 56, ByteCompanionObject.MIN_VALUE, -80, 22, 2, -122, 8, -124, 80, -118, 17, 32, 96, 64, 104, 13, 1, -75, 0, 10, -127, 80, 42, 67, 0, 104, 13, 1, -96, 18, 64, Tnaf.POW_2_WIDTH, 34, 4, 64, -60, ByteCompanionObject.MIN_VALUE, 48, 70, 8, -63, 96, Tnaf.POW_2_WIDTH, 70, 72, -55, 25, 41, -80, 51, 38, 100, -52, -112, -104, -103, 19, 34, 100, 8, 72, 5, ByteCompanionObject.MIN_VALUE, -80, 22, 0, 56, ByteCompanionObject.MIN_VALUE, -80, 22, 2, -122, 8, -124, 80, -118, 17, 32, 96, 64, 104, 13, 1, -75, 0, 10, -127, 80, 42, 67, 0, 104, 13, 1, -96, 18, 64, Tnaf.POW_2_WIDTH, 34, 4, 64, -60, ByteCompanionObject.MIN_VALUE, 48, 70, 8, -63, 96, Tnaf.POW_2_WIDTH, 100, 76, -119, -111, 40, -96, 81, 74, 41, 69, 34, -120, -87, 21, 34, -92, 64, 104, 4, -64, -104, 19, 18, Tnaf.POW_2_WIDTH, -48, 26, 3, 64, 5, Tnaf.POW_2_WIDTH, -126, 80, 74, 9, 0, 112, 33, 36, 36, -124, -36, 0, 12, 33, -124, 48, -124, -120, 74, 9, 65, 40, 43, 0, 18, -94, 84, 74, -47, 64, -98, -50, -117, -86, 52, 104};
    }

    private static void createRandomMask45_44() {
        _randomMask45_44 = new byte[]{70, 72, -55, 25, 41, -80, 51, 38, 100, -52, -112, -104, -103, 19, 34, 100, 8, 72, 5, ByteCompanionObject.MIN_VALUE, -80, 22, 0, 56, ByteCompanionObject.MIN_VALUE, -80, 22, 2, -122, 8, -124, 80, -118, 17, 32, 96, 64, 104, 13, 1, -75, 0, 10, -127, 80, 42, 67, 0, 104, 13, 1, -96, 18, 64, Tnaf.POW_2_WIDTH, 34, 4, 64, -60, ByteCompanionObject.MIN_VALUE, 48, 70, 8, -63, 96, Tnaf.POW_2_WIDTH, 100, 76, -119, -111, 40, -96, 81, 74, 41, 69, 34, -120, -87, 21, 34, -92, 64, 104, 4, -64, -104, 19, 18, Tnaf.POW_2_WIDTH, -48, 26, 3, 64, 5, Tnaf.POW_2_WIDTH, -126, 80, 74, 9, 0, 112, 33, 36, 36, -124, -36, 0, 12, 33, -124, 48, -124, -120, 74, 9, 65, 40, 43, 0, 18, -94, 84, 74, -47, 64, -98, -50, -117, -86, 52, 104, 100, 76, -119, -111, 40, -96, 81, 74, 41, 69, 34, -120, -87, 21, 34, -92, 64, 104, 4, -64, -104, 19, 18, Tnaf.POW_2_WIDTH, -48, 26, 3, 64, 5, Tnaf.POW_2_WIDTH, -126, 80, 74, 9, 0, 112, 33, 36, 36, -124, -36, 0, 12, 33, -124, 48, -124, -120, 74, 9, 65, 40, 43, 0, 18, -94, 84, 74, -47, 64, 70, 72, -55, 25, 41, -80, 51, 38, 100, -52, -112, -104, -103, 19, 34, 100, 8, 72, 5, ByteCompanionObject.MIN_VALUE, -80, 22, 0, 56, ByteCompanionObject.MIN_VALUE, -80, 22, 2, -122, 8, -124, 80, -118, 17, 32, 96, 64, 104, 13, 1, -75, 0, 10, -127, 80, 42, 67, 0, 104, 13, 1, -96, 18, 64, Tnaf.POW_2_WIDTH, 34, 4, 64, -60, ByteCompanionObject.MIN_VALUE, 48, 70, 8, -63, 96, Tnaf.POW_2_WIDTH, -8, 64, -29, 46, 22, 0};
    }

    private static void createRandomMask45_45() {
        _randomMask45_45 = new byte[]{70, 72, -55, 25, 41, -80, 51, 38, 100, -52, -112, -104, -103, 19, 34, 100, 8, 72, 5, ByteCompanionObject.MIN_VALUE, -80, 22, 0, 56, ByteCompanionObject.MIN_VALUE, -80, 22, 2, -122, 8, -124, 80, -118, 17, 32, 96, 64, 104, 13, 1, -75, 0, 10, -127, 80, 42, 67, 0, 104, 13, 1, -96, 18, 64, Tnaf.POW_2_WIDTH, 34, 4, 64, -60, ByteCompanionObject.MIN_VALUE, 48, 70, 8, -63, 96, Tnaf.POW_2_WIDTH, 100, 76, -119, -111, 40, -96, 81, 74, 41, 69, 34, -120, -87, 21, 34, -92, 64, 104, 4, -64, -104, 19, 18, Tnaf.POW_2_WIDTH, -48, 26, 3, 64, 5, Tnaf.POW_2_WIDTH, -126, 80, 74, 9, 0, 112, 33, 36, 36, -124, -36, 0, 12, 33, -124, 48, -124, -120, 74, 9, 65, 40, 43, 0, 18, -94, 84, 74, -47, 64, -98, -50, -117, -86, 52, 104, 70, 72, -55, 25, 41, -80, 51, 38, 100, -52, -112, -104, -103, 19, 34, 100, 8, 72, 5, ByteCompanionObject.MIN_VALUE, -80, 22, 0, 56, ByteCompanionObject.MIN_VALUE, -80, 22, 2, -122, 8, -124, 80, -118, 17, 32, 96, 64, 104, 13, 1, -75, 0, 10, -127, 80, 42, 67, 0, 104, 13, 1, -96, 18, 64, Tnaf.POW_2_WIDTH, 34, 4, 64, -60, ByteCompanionObject.MIN_VALUE, 48, 70, 8, -63, 96, Tnaf.POW_2_WIDTH, 100, 76, -119, 25, 8, 48, 81, 74, 40, -52, -127, 24, -87, 21, 34, 100, 32, 40, 4, -64, -104, 22, Tnaf.POW_2_WIDTH, -64, -48, 26, 2, 2, -64, -120, -126, 80, 74, 17, 10, 64, 33, 36, 37, 1, -52, 0, 12, 33, -124, 42, 4, 72, 74, 9, 65, -96, 49, 0, 18, -94, 84, 64, -110, Tnaf.POW_2_WIDTH, -98, -50, -120, -63, 69, 0, -5, -105, 93, 125, 66, 32};
    }

    private static void createRandomMask45_5() {
        _randomMask45_5 = new byte[]{-58, -40, -37, 27, 41, -80, 99, 108, 109, -115, -78, 88, 29, -93, -76, 118, -121, 112, -83, 85, -86, -75, 84, -32, -78, -74, 86, -54, -36, 24};
    }

    private static void createRandomMask45_6() {
        _randomMask45_6 = new byte[]{100, 76, -119, -111, 40, -96, 81, 74, 41, 69, 98, -120, -88, 53, 4, 50, -112, -64, -60, -96, -106, -124, -119, 24, 3, 96, 108, 74, -124, 112, -112, -46, 26, 41, 23, 0};
    }

    private static void createRandomMask45_7() {
        _randomMask45_7 = new byte[]{-58, 72, -55, 25, 41, -80, 19, 38, 100, -52, -112, -104, -115, 19, -94, 70, 72, 72, -117, 65, 106, -112, -127, 40, 82, -86, 21, 66, -125, 80, -94, -44, 26, 19, 22, 0, 97, -88, 44, 37, -60, 48};
    }

    private static void createRandomMask45_8() {
        _randomMask45_8 = new byte[]{40, -123, 56, 50, Tnaf.POW_2_WIDTH, 48, 33, -12, 6, 1, -64, 24, -23, 29, 2, -126, 33, 32, 23, 2, -32, 21, 0, -56, -125, -96, 85, 12, 10, 8, 70, 24, -24, 104, 5, 64, 80, 106, 13, ByteCompanionObject.MIN_VALUE, -100, 0, 28, 35, -124, 80, -30, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask45_9() {
        _randomMask45_9 = new byte[]{68, 72, -55, 25, 41, -80, 40, 45, 13, -119, -14, Tnaf.POW_2_WIDTH, 37, 20, -94, 48, 18, 32, 89, 10, 32, 5, -48, -120, 3, -96, 52, 30, 12, 24, -64, -48, 26, -126, 1, -56, -94, 48, 68, 98, -59, 8, 20, -126, -46, 68, 2, 72, -102, 3, -127, -32, 0, 112};
    }

    private static void createRandomMask46_1() {
        _randomMask46_1 = new byte[]{-1, -1, -1, -1, -1, -4};
    }

    private static void createRandomMask46_10() {
        _randomMask46_10 = new byte[]{100, 74, 40, -56, -108, 80, 81, 72, -94, -94, -111, 68, -87, Tnaf.POW_2_WIDTH, 27, 82, 32, 52, 4, -60, -124, 9, -119, 8, -48, 1, 69, -96, 2, -120, -126, 64, 29, 4, ByteCompanionObject.MIN_VALUE, 56, 33, 55, 0, 66, 110, 0, 12, 33, 34, 24, 66, 68, 74, 10, -64, -108, 21, ByteCompanionObject.MIN_VALUE, 18, -76, 80, 37, 104, -96};
    }

    private static void createRandomMask46_11() {
        _randomMask46_11 = new byte[]{70, 74, 108, -116, -108, -40, 51, 36, 38, 102, 72, 76, -103, 2, 19, 50, 4, 36, 5, ByteCompanionObject.MIN_VALUE, 14, 11, 0, 28, ByteCompanionObject.MIN_VALUE, -95, -125, 1, 67, 4, -124, 72, 25, 8, -112, 48, 64, 109, 64, ByteCompanionObject.MIN_VALUE, -38, ByteCompanionObject.MIN_VALUE, 10, -112, -64, 21, 33, ByteCompanionObject.MIN_VALUE, 104, 4, -112, -48, 9, 32, Tnaf.POW_2_WIDTH, 49, 32, 32, 98, 64, 48, 88, 4, 96, -80, 8};
    }

    private static void createRandomMask46_12() {
        _randomMask46_12 = new byte[]{100, 74, 40, -56, -108, 80, 81, 88, -94, -94, -79, 68, 12, -92, 48, 25, 72, 96, -95, 34, 71, 66, 68, -116, 18, -95, 28, 37, 66, 56, -118, 69, -63, 20, -117, ByteCompanionObject.MIN_VALUE, -122, -118, 109, 13, 20, -40, 35, 44, -124, 70, 89, 8, 22, 33, -104, 44, 67, 48, 76, 48, 84, -104, 96, -88, 65, -63, 38, -125, -126, 76, 25, 86, -28, 50, -83, -56};
    }

    private static void createRandomMask46_13() {
        _randomMask46_13 = new byte[]{100, 74, 40, -56, -108, 80, 81, 88, -94, -94, -79, 68, 12, -92, 48, 25, 72, 96, -95, 34, 71, 66, 68, -116, 18, -95, 28, 37, 66, 56, -118, 69, -63, 20, -117, ByteCompanionObject.MIN_VALUE, 70, 74, 108, -116, -108, -40, 51, 36, 38, 102, 72, 76, -111, -110, 19, 35, 36, 36, -92, 32, 75, 72, 64, -108, 80, -96, -44, -95, 65, -88, -124, -59, -127, 9, -117, 0, 9, 113, 12, 18, -30, 24};
    }

    private static void createRandomMask46_14() {
        _randomMask46_14 = new byte[]{70, 74, 108, -116, -108, -40, 51, 36, 38, 102, 72, 76, -111, -110, 19, 35, 36, 36, -92, 32, 75, 72, 64, -108, 80, -96, -44, -95, 65, -88, -124, -59, -127, 9, -117, 0, 9, 113, 12, 18, -30, 24, 100, 74, 40, -56, -108, 80, 81, 88, -94, -94, -79, 68, 12, -92, 48, 25, 72, 96, -95, 34, 71, 66, 68, -116, 18, -95, 28, 37, 66, 56, -118, 69, -63, 20, -117, ByteCompanionObject.MIN_VALUE, -100, Utf8.REPLACEMENT_BYTE, -77, 56, ByteCompanionObject.MAX_VALUE, 100};
    }

    private static void createRandomMask46_15() {
        _randomMask46_15 = new byte[]{70, 74, 108, -116, -108, -40, 51, 36, 38, 102, 72, 76, -111, -110, 19, 35, 36, 36, -92, 32, 75, 72, 64, -108, 80, -96, -44, -95, 65, -88, -124, -59, -127, 9, -117, 0, 9, 113, 12, 18, -30, 24, 12, -124, 12, 25, 8, 24, ByteCompanionObject.MIN_VALUE, 112, 7, 0, -32, 12, -96, -120, 73, 65, Tnaf.POW_2_WIDTH, -112, 5, 64, 50, 10, ByteCompanionObject.MIN_VALUE, 100, 67, 2, -126, -122, 5, 4, 26, 1, 80, 52, 2, -96, 96, 39, 0, -64, 78, 0, 20, 56, -96, 40, 113, 64};
    }

    private static void createRandomMask46_16() {
        _randomMask46_16 = new byte[]{12, -124, 12, 25, 8, 24, ByteCompanionObject.MIN_VALUE, 112, 7, 0, -32, 12, -96, -120, 73, 65, Tnaf.POW_2_WIDTH, -112, 5, 64, 50, 10, ByteCompanionObject.MIN_VALUE, 100, 67, 2, -126, -122, 5, 4, 26, 1, 80, 52, 2, -96, 96, 39, 0, -64, 78, 0, 20, 56, -96, 40, 113, 64, 70, 74, 108, -116, -108, -40, 51, 36, 38, 102, 72, 76, -111, -110, 19, 35, 36, 36, -92, 32, 75, 72, 64, -108, 80, -96, -44, -95, 65, -88, -124, -59, -127, 9, -117, 0, 9, 113, 12, 18, -30, 24, -6, -39, -11, -11, -77, -24};
    }

    private static void createRandomMask46_17() {
        _randomMask46_17 = new byte[]{12, -124, 12, 25, 8, 24, ByteCompanionObject.MIN_VALUE, 112, 7, 0, -32, 12, -96, -120, 73, 65, Tnaf.POW_2_WIDTH, -112, 5, 64, 50, 10, ByteCompanionObject.MIN_VALUE, 100, 67, 2, -126, -122, 5, 4, 26, 1, 80, 52, 2, -96, 96, 39, 0, -64, 78, 0, 20, 56, -96, 40, 113, 64, 70, 74, 108, -116, -108, -40, 98, 124, -124, -60, -7, 8, -116, 4, -119, 24, 9, Tnaf.POW_2_WIDTH, 1, 116, 34, 2, -24, 68, 7, -125, 6, 15, 6, 12, -96, ByteCompanionObject.MIN_VALUE, 115, 65, 0, -28, 24, -79, 66, 49, 98, -124, -111, 0, -109, 34, 1, 36, 120, 0, 28, -16, 0, 56};
    }

    private static void createRandomMask46_18() {
        _randomMask46_18 = new byte[]{70, 74, 108, -116, -108, -40, 98, 124, -124, -60, -7, 8, -116, 4, -119, 24, 9, Tnaf.POW_2_WIDTH, 1, 116, 34, 2, -24, 68, 7, -125, 6, 15, 6, 12, -96, ByteCompanionObject.MIN_VALUE, 115, 65, 0, -28, 24, -79, 66, 49, 98, -124, -111, 0, -109, 34, 1, 36, 120, 0, 28, -16, 0, 56, 12, -124, 12, 25, 8, 24, ByteCompanionObject.MIN_VALUE, 112, 7, 0, -32, 12, -96, -120, 73, 65, Tnaf.POW_2_WIDTH, -112, 5, 64, 50, 10, ByteCompanionObject.MIN_VALUE, 100, 67, 2, -126, -122, 5, 4, 26, 1, 80, 52, 2, -96, 96, 39, 0, -64, 78, 0, 20, 56, -96, 40, 113, 64, -126, 50, 87, 4, 100, -84};
    }

    private static void createRandomMask46_19() {
        _randomMask46_19 = new byte[]{70, 74, 108, -116, -108, -40, 98, 124, -124, -60, -7, 8, -116, 4, -119, 24, 9, Tnaf.POW_2_WIDTH, 1, 116, 34, 2, -24, 68, 7, -125, 6, 15, 6, 12, -96, ByteCompanionObject.MIN_VALUE, 115, 65, 0, -28, 24, -79, 66, 49, 98, -124, -111, 0, -109, 34, 1, 36, 120, 0, 28, -16, 0, 56, 100, 74, 40, -56, -108, 80, 81, 72, -94, -94, -111, 68, -87, Tnaf.POW_2_WIDTH, 27, 82, 32, 52, 4, -60, -124, 9, -119, 8, -48, 1, 69, -96, 2, -120, -126, 64, 29, 4, ByteCompanionObject.MIN_VALUE, 56, 33, 55, 0, 66, 110, 0, 12, 33, 34, 24, 66, 68, 74, 10, -64, -108, 21, ByteCompanionObject.MIN_VALUE, 18, -76, 80, 37, 104, -96};
    }

    private static void createRandomMask46_2() {
        _randomMask46_2 = new byte[]{-20, -35, -103, -39, -69, 48, -101, -78, 119, 55, 100, -20};
    }

    private static void createRandomMask46_20() {
        _randomMask46_20 = new byte[]{100, 74, 40, -56, -108, 80, 81, 72, -94, -94, -111, 68, -87, Tnaf.POW_2_WIDTH, 27, 82, 32, 52, 4, -60, -124, 9, -119, 8, -48, 1, 69, -96, 2, -120, -126, 64, 29, 4, ByteCompanionObject.MIN_VALUE, 56, 33, 55, 0, 66, 110, 0, 12, 33, 34, 24, 66, 68, 74, 10, -64, -108, 21, ByteCompanionObject.MIN_VALUE, 18, -76, 80, 37, 104, -96, 70, 74, 108, -116, -108, -40, 98, 124, -124, -60, -7, 8, -116, 4, -119, 24, 9, Tnaf.POW_2_WIDTH, 1, 116, 34, 2, -24, 68, 7, -125, 6, 15, 6, 12, -96, ByteCompanionObject.MIN_VALUE, 115, 65, 0, -28, 24, -79, 66, 49, 98, -124, -111, 0, -109, 34, 1, 36, 120, 0, 28, -16, 0, 56, -37, 74, 123, -74, -108, -12};
    }

    private static void createRandomMask46_21() {
        _randomMask46_21 = new byte[]{100, 74, 40, -56, -108, 80, 81, 72, -94, -94, -111, 68, -87, Tnaf.POW_2_WIDTH, 27, 82, 32, 52, 4, -60, -124, 9, -119, 8, -48, 1, 69, -96, 2, -120, -126, 64, 29, 4, ByteCompanionObject.MIN_VALUE, 56, 33, 55, 0, 66, 110, 0, 12, 33, 34, 24, 66, 68, 74, 10, -64, -108, 21, ByteCompanionObject.MIN_VALUE, 18, -76, 80, 37, 104, -96, 70, 74, 108, -116, -108, -40, 51, 36, 38, 102, 72, 76, -103, 2, 19, 50, 4, 36, 5, ByteCompanionObject.MIN_VALUE, 14, 11, 0, 28, ByteCompanionObject.MIN_VALUE, -95, -125, 1, 67, 4, -124, 72, 25, 8, -112, 48, 64, 109, 64, ByteCompanionObject.MIN_VALUE, -38, ByteCompanionObject.MIN_VALUE, 10, -112, -64, 21, 33, ByteCompanionObject.MIN_VALUE, 104, 4, -112, -48, 9, 32, Tnaf.POW_2_WIDTH, 49, 32, 32, 98, 64, 48, 88, 4, 96, -80, 8};
    }

    private static void createRandomMask46_22() {
        _randomMask46_22 = new byte[]{70, 74, 108, -116, -108, -40, 51, 36, 38, 102, 72, 76, -103, 2, 19, 50, 4, 36, 5, ByteCompanionObject.MIN_VALUE, 14, 11, 0, 28, ByteCompanionObject.MIN_VALUE, -95, -125, 1, 67, 4, -124, 72, 25, 8, -112, 48, 64, 109, 64, ByteCompanionObject.MIN_VALUE, -38, ByteCompanionObject.MIN_VALUE, 10, -112, -64, 21, 33, ByteCompanionObject.MIN_VALUE, 104, 4, -112, -48, 9, 32, Tnaf.POW_2_WIDTH, 49, 32, 32, 98, 64, 48, 88, 4, 96, -80, 8, 100, 74, 40, -56, -108, 80, 81, 72, -94, -94, -111, 68, -87, Tnaf.POW_2_WIDTH, 27, 82, 32, 52, 4, -60, -124, 9, -119, 8, -48, 1, 69, -96, 2, -120, -126, 64, 29, 4, ByteCompanionObject.MIN_VALUE, 56, 33, 55, 0, 66, 110, 0, 12, 33, 34, 24, 66, 68, 74, 10, -64, -108, 21, ByteCompanionObject.MIN_VALUE, 18, -76, 80, 37, 104, -96, -22, -115, 27, -43, 26, 52};
    }

    private static void createRandomMask46_23() {
        _randomMask46_23 = new byte[]{70, 74, 108, -116, -108, -40, 51, 36, 38, 102, 72, 76, -103, 2, 19, 50, 4, 36, 5, ByteCompanionObject.MIN_VALUE, 14, 11, 0, 28, ByteCompanionObject.MIN_VALUE, -95, -125, 1, 67, 4, -124, 72, 25, 8, -112, 48, 64, 109, 64, ByteCompanionObject.MIN_VALUE, -38, ByteCompanionObject.MIN_VALUE, 10, -112, -64, 21, 33, ByteCompanionObject.MIN_VALUE, 104, 4, -112, -48, 9, 32, Tnaf.POW_2_WIDTH, 49, 32, 32, 98, 64, 48, 88, 4, 96, -80, 8, 70, 66, 12, -116, -124, 24, 51, 32, 70, 102, 64, -116, -103, 8, 11, 50, Tnaf.POW_2_WIDTH, 20, 5, -124, 48, 11, 8, 96, ByteCompanionObject.MIN_VALUE, -80, 35, 1, 96, 68, -124, 66, -111, 8, -123, 32, 64, 115, 0, ByteCompanionObject.MIN_VALUE, -26, 0, 10, -127, 18, 21, 2, 36, 104, 12, 64, -48, 24, ByteCompanionObject.MIN_VALUE, Tnaf.POW_2_WIDTH, 36, -124, 32, 73, 8, 48, 81, 64, 96, -94, ByteCompanionObject.MIN_VALUE, 95, 80, -120, -66, -95, Tnaf.POW_2_WIDTH};
    }

    private static void createRandomMask46_24() {
        _randomMask46_24 = new byte[]{100, 74, 40, -56, -108, 80, 81, 88, -94, -94, -79, 68, 12, -92, 48, 25, 72, 96, -95, 34, 71, 66, 68, -116, 18, -95, 28, 37, 66, 56, -118, 69, -63, 20, -117, ByteCompanionObject.MIN_VALUE, -122, -118, 109, 13, 20, -40, 35, 44, -124, 70, 89, 8, 22, 33, -104, 44, 67, 48, 76, 48, 84, -104, 96, -88, 65, -63, 38, -125, -126, 76, 25, 86, -28, 50, -83, -56, 70, 74, 108, -116, -108, -40, 51, 36, 38, 102, 72, 76, -103, 2, 19, 50, 4, 36, 5, ByteCompanionObject.MIN_VALUE, 14, 11, 0, 28, ByteCompanionObject.MIN_VALUE, -95, -125, 1, 67, 4, -124, 72, 25, 8, -112, 48, 64, 109, 64, ByteCompanionObject.MIN_VALUE, -38, ByteCompanionObject.MIN_VALUE, 10, -112, -64, 21, 33, ByteCompanionObject.MIN_VALUE, 104, 4, -112, -48, 9, 32, Tnaf.POW_2_WIDTH, 49, 32, 32, 98, 64, 48, 88, 4, 96, -80, 8, 24, -117, 3, -76, 59, Tnaf.POW_2_WIDTH};
    }

    private static void createRandomMask46_25() {
        _randomMask46_25 = new byte[]{100, 74, 40, -56, -108, 80, 81, 88, -94, -94, -79, 68, 12, -92, 48, 25, 72, 96, -95, 34, 71, 66, 68, -116, 18, -95, 28, 37, 66, 56, -118, 69, -63, 20, -117, ByteCompanionObject.MIN_VALUE, -122, -118, 109, 13, 20, -40, 35, 44, -124, 70, 89, 8, 22, 33, -104, 44, 67, 48, 76, 48, 84, -104, 96, -88, 65, -63, 38, -125, -126, 76, 25, 86, -28, 50, -83, -56, 100, 74, 40, -56, -108, 80, 81, 88, -94, -94, -79, 68, 12, -92, 48, 25, 72, 96, -95, 34, 71, 66, 68, -116, 18, -95, 28, 37, 66, 56, -118, 69, -63, 20, -117, ByteCompanionObject.MIN_VALUE, 70, 74, 108, -116, -108, -40, 51, 36, 38, 102, 72, 76, -111, -110, 19, 35, 36, 36, -92, 32, 75, 72, 64, -108, 80, -96, -44, -95, 65, -88, -124, -59, -127, 9, -117, 0, 9, 113, 12, 18, -30, 24};
    }

    private static void createRandomMask46_26() {
        _randomMask46_26 = new byte[]{100, 74, 40, -56, -108, 80, 81, 88, -94, -94, -79, 68, 12, -92, 48, 25, 72, 96, -95, 34, 71, 66, 68, -116, 18, -95, 28, 37, 66, 56, -118, 69, -63, 20, -117, ByteCompanionObject.MIN_VALUE, 70, 74, 108, -116, -108, -40, 51, 36, 38, 102, 72, 76, -111, -110, 19, 35, 36, 36, -92, 32, 75, 72, 64, -108, 80, -96, -44, -95, 65, -88, -124, -59, -127, 9, -117, 0, 9, 113, 12, 18, -30, 24, 100, 74, 40, -56, -108, 80, 81, 88, -94, -94, -79, 68, 12, -92, 48, 25, 72, 96, -95, 34, 71, 66, 68, -116, 18, -95, 28, 37, 66, 56, -118, 69, -63, 20, -117, ByteCompanionObject.MIN_VALUE, -122, -118, 109, 13, 20, -40, 35, 44, -124, 70, 89, 8, 22, 33, -104, 44, 67, 48, 76, 48, 84, -104, 96, -88, 65, -63, 38, -125, -126, 76, 25, 86, -28, 50, -83, -56, 45, 109, -46, 87, -42, 44};
    }

    private static void createRandomMask46_27() {
        _randomMask46_27 = new byte[]{100, 74, 40, -56, -108, 80, 81, 88, -94, -94, -79, 68, 12, -92, 48, 25, 72, 96, -95, 34, 71, 66, 68, -116, 18, -95, 28, 37, 66, 56, -118, 69, -63, 20, -117, ByteCompanionObject.MIN_VALUE, 70, 74, 108, -116, -108, -40, 51, 36, 38, 102, 72, 76, -111, -110, 19, 35, 36, 36, -92, 32, 75, 72, 64, -108, 80, -96, -44, -95, 65, -88, -124, -59, -127, 9, -117, 0, 9, 113, 12, 18, -30, 24, 70, 74, 108, -116, -108, -40, 51, 36, 38, 102, 72, 76, -111, -110, 19, 35, 36, 36, -92, 32, 75, 72, 64, -108, 80, -96, -44, -95, 65, -88, -124, -59, -127, 9, -117, 0, 9, 113, 12, 18, -30, 24, 100, 74, 40, -56, -108, 80, 81, 88, -94, -94, -79, 68, 12, -92, 48, 25, 72, 96, -95, 34, 71, 66, 68, -116, 18, -95, 28, 37, 66, 56, -118, 69, -63, 20, -117, ByteCompanionObject.MIN_VALUE, -100, Utf8.REPLACEMENT_BYTE, -77, 56, ByteCompanionObject.MAX_VALUE, 100};
    }

    private static void createRandomMask46_28() {
        _randomMask46_28 = new byte[]{70, 74, 108, -116, -108, -40, 51, 36, 38, 102, 72, 76, -111, -110, 19, 35, 36, 36, -92, 32, 75, 72, 64, -108, 80, -96, -44, -95, 65, -88, -124, -59, -127, 9, -117, 0, 9, 113, 12, 18, -30, 24, 100, 74, 40, -56, -108, 80, 81, 88, -94, -94, -79, 68, 12, -92, 48, 25, 72, 96, -95, 34, 71, 66, 68, -116, 18, -95, 28, 37, 66, 56, -118, 69, -63, 20, -117, ByteCompanionObject.MIN_VALUE, -100, Utf8.REPLACEMENT_BYTE, -77, 56, ByteCompanionObject.MAX_VALUE, 100, 100, 74, 40, -56, -108, 80, 81, 88, -94, -94, -79, 68, 12, -92, 48, 25, 72, 96, -95, 34, 71, 66, 68, -116, 18, -95, 28, 37, 66, 56, -118, 69, -63, 20, -117, ByteCompanionObject.MIN_VALUE, 70, 74, 108, -116, -108, -40, 51, 36, 38, 102, 72, 76, -111, -110, 19, 35, 36, 36, -92, 32, 75, 72, 64, -108, 80, -96, -44, -95, 65, -88, -124, -59, -127, 9, -117, 0, 9, 113, 12, 18, -30, 24, -6, 82, -7, 114, -39, 104};
    }

    private static void createRandomMask46_29() {
        _randomMask46_29 = new byte[]{70, 74, 108, -116, -108, -40, 51, 36, 38, 102, 72, 76, -111, -110, 19, 35, 36, 36, -92, 32, 75, 72, 64, -108, 80, -96, -44, -95, 65, -88, -124, -59, -127, 9, -117, 0, 9, 113, 12, 18, -30, 24, 100, 74, 40, -56, -108, 80, 81, 88, -94, -94, -79, 68, 12, -92, 48, 25, 72, 96, -95, 34, 71, 66, 68, -116, 18, -95, 28, 37, 66, 56, -118, 69, -63, 20, -117, ByteCompanionObject.MIN_VALUE, -100, Utf8.REPLACEMENT_BYTE, -77, 56, ByteCompanionObject.MAX_VALUE, 100, 70, 74, 108, -116, -108, -40, 51, 36, 38, 102, 72, 76, -111, -110, 19, 35, 36, 36, -92, 32, 75, 72, 64, -108, 80, -96, -44, -95, 65, -88, -124, -59, -127, 9, -117, 0, 9, 113, 12, 18, -30, 24, 12, -124, 12, 25, 8, 24, ByteCompanionObject.MIN_VALUE, 112, 7, 0, -32, 12, -96, -120, 73, 65, Tnaf.POW_2_WIDTH, -112, 5, 64, 50, 10, ByteCompanionObject.MIN_VALUE, 100, 67, 2, -126, -122, 5, 4, 26, 1, 80, 52, 2, -96, 96, 39, 0, -64, 78, 0, 20, 56, -96, 40, 113, 64};
    }

    private static void createRandomMask46_3() {
        _randomMask46_3 = new byte[]{-54, -45, 101, -107, -90, -56, -15, 73, 59, -30, -110, 116, 118, 39, -48, -20, 79, -96};
    }

    private static void createRandomMask46_30() {
        _randomMask46_30 = new byte[]{70, 74, 108, -116, -108, -40, 51, 36, 38, 102, 72, 76, -111, -110, 19, 35, 36, 36, -92, 32, 75, 72, 64, -108, 80, -96, -44, -95, 65, -88, -124, -59, -127, 9, -117, 0, 9, 113, 12, 18, -30, 24, 12, -124, 12, 25, 8, 24, ByteCompanionObject.MIN_VALUE, 112, 7, 0, -32, 12, -96, -120, 73, 65, Tnaf.POW_2_WIDTH, -112, 5, 64, 50, 10, ByteCompanionObject.MIN_VALUE, 100, 67, 2, -126, -122, 5, 4, 26, 1, 80, 52, 2, -96, 96, 39, 0, -64, 78, 0, 20, 56, -96, 40, 113, 64, 70, 74, 108, -116, -108, -40, 51, 36, 38, 102, 72, 76, -111, -110, 19, 35, 36, 36, -92, 32, 75, 72, 64, -108, 80, -96, -44, -95, 65, -88, -124, -59, -127, 9, -117, 0, 9, 113, 12, 18, -30, 24, 100, 74, 40, -56, -108, 80, 81, 88, -94, -94, -79, 68, 12, -92, 48, 25, 72, 96, -95, 34, 71, 66, 68, -116, 18, -95, 28, 37, 66, 56, -118, 69, -63, 20, -117, ByteCompanionObject.MIN_VALUE, -100, Utf8.REPLACEMENT_BYTE, -77, 56, ByteCompanionObject.MAX_VALUE, 100, -103, -10, 10, -35, 22, -80};
    }

    private static void createRandomMask46_31() {
        _randomMask46_31 = new byte[]{70, 74, 108, -116, -108, -40, 51, 36, 38, 102, 72, 76, -111, -110, 19, 35, 36, 36, -92, 32, 75, 72, 64, -108, 80, -96, -44, -95, 65, -88, -124, -59, -127, 9, -117, 0, 9, 113, 12, 18, -30, 24, 12, -124, 12, 25, 8, 24, ByteCompanionObject.MIN_VALUE, 112, 7, 0, -32, 12, -96, -120, 73, 65, Tnaf.POW_2_WIDTH, -112, 5, 64, 50, 10, ByteCompanionObject.MIN_VALUE, 100, 67, 2, -126, -122, 5, 4, 26, 1, 80, 52, 2, -96, 96, 39, 0, -64, 78, 0, 20, 56, -96, 40, 113, 64, 12, -124, 12, 25, 8, 24, ByteCompanionObject.MIN_VALUE, 112, 7, 0, -32, 12, -96, -120, 73, 65, Tnaf.POW_2_WIDTH, -112, 5, 64, 50, 10, ByteCompanionObject.MIN_VALUE, 100, 67, 2, -126, -122, 5, 4, 26, 1, 80, 52, 2, -96, 96, 39, 0, -64, 78, 0, 20, 56, -96, 40, 113, 64, 70, 74, 108, -116, -108, -40, 51, 36, 38, 102, 72, 76, -111, -110, 19, 35, 36, 36, -92, 32, 75, 72, 64, -108, 80, -96, -44, -95, 65, -88, -124, -59, -127, 9, -117, 0, 9, 113, 12, 18, -30, 24, -6, -39, -11, -11, -77, -24};
    }

    private static void createRandomMask46_32() {
        _randomMask46_32 = new byte[]{12, -124, 12, 25, 8, 24, ByteCompanionObject.MIN_VALUE, 112, 7, 0, -32, 12, -96, -120, 73, 65, Tnaf.POW_2_WIDTH, -112, 5, 64, 50, 10, ByteCompanionObject.MIN_VALUE, 100, 67, 2, -126, -122, 5, 4, 26, 1, 80, 52, 2, -96, 96, 39, 0, -64, 78, 0, 20, 56, -96, 40, 113, 64, 70, 74, 108, -116, -108, -40, 51, 36, 38, 102, 72, 76, -111, -110, 19, 35, 36, 36, -92, 32, 75, 72, 64, -108, 80, -96, -44, -95, 65, -88, -124, -59, -127, 9, -117, 0, 9, 113, 12, 18, -30, 24, -6, -39, -11, -11, -77, -24, 70, 74, 108, -116, -108, -40, 51, 36, 38, 102, 72, 76, -111, -110, 19, 35, 36, 36, -92, 32, 75, 72, 64, -108, 80, -96, -44, -95, 65, -88, -124, -59, -127, 9, -117, 0, 9, 113, 12, 18, -30, 24, 12, -124, 12, 25, 8, 24, ByteCompanionObject.MIN_VALUE, 112, 7, 0, -32, 12, -96, -120, 73, 65, Tnaf.POW_2_WIDTH, -112, 5, 64, 50, 10, ByteCompanionObject.MIN_VALUE, 100, 67, 2, -126, -122, 5, 4, 26, 1, 80, 52, 2, -96, 96, 39, 0, -64, 78, 0, 20, 56, -96, 40, 113, 64, 105, -51, -21, 81, -55, -88};
    }

    private static void createRandomMask46_33() {
        _randomMask46_33 = new byte[]{12, -124, 12, 25, 8, 24, ByteCompanionObject.MIN_VALUE, 112, 7, 0, -32, 12, -96, -120, 73, 65, Tnaf.POW_2_WIDTH, -112, 5, 64, 50, 10, ByteCompanionObject.MIN_VALUE, 100, 67, 2, -126, -122, 5, 4, 26, 1, 80, 52, 2, -96, 96, 39, 0, -64, 78, 0, 20, 56, -96, 40, 113, 64, 70, 74, 108, -116, -108, -40, 51, 36, 38, 102, 72, 76, -111, -110, 19, 35, 36, 36, -92, 32, 75, 72, 64, -108, 80, -96, -44, -95, 65, -88, -124, -59, -127, 9, -117, 0, 9, 113, 12, 18, -30, 24, -6, -39, -11, -11, -77, -24, 12, -124, 12, 25, 8, 24, ByteCompanionObject.MIN_VALUE, 112, 7, 0, -32, 12, -96, -120, 73, 65, Tnaf.POW_2_WIDTH, -112, 5, 64, 50, 10, ByteCompanionObject.MIN_VALUE, 100, 67, 2, -126, -122, 5, 4, 26, 1, 80, 52, 2, -96, 96, 39, 0, -64, 78, 0, 20, 56, -96, 40, 113, 64, 70, 74, 108, -116, -108, -40, 98, 124, -124, -60, -7, 8, -116, 4, -119, 24, 9, Tnaf.POW_2_WIDTH, 1, 116, 34, 2, -24, 68, 7, -125, 6, 15, 6, 12, -96, ByteCompanionObject.MIN_VALUE, 115, 65, 0, -28, 24, -79, 66, 49, 98, -124, -111, 0, -109, 34, 1, 36, 120, 0, 28, -16, 0, 56};
    }

    private static void createRandomMask46_34() {
        _randomMask46_34 = new byte[]{12, -124, 12, 25, 8, 24, ByteCompanionObject.MIN_VALUE, 112, 7, 0, -32, 12, -96, -120, 73, 65, Tnaf.POW_2_WIDTH, -112, 5, 64, 50, 10, ByteCompanionObject.MIN_VALUE, 100, 67, 2, -126, -122, 5, 4, 26, 1, 80, 52, 2, -96, 96, 39, 0, -64, 78, 0, 20, 56, -96, 40, 113, 64, 70, 74, 108, -116, -108, -40, 98, 124, -124, -60, -7, 8, -116, 4, -119, 24, 9, Tnaf.POW_2_WIDTH, 1, 116, 34, 2, -24, 68, 7, -125, 6, 15, 6, 12, -96, ByteCompanionObject.MIN_VALUE, 115, 65, 0, -28, 24, -79, 66, 49, 98, -124, -111, 0, -109, 34, 1, 36, 120, 0, 28, -16, 0, 56, 12, -124, 12, 25, 8, 24, ByteCompanionObject.MIN_VALUE, 112, 7, 0, -32, 12, -96, -120, 73, 65, Tnaf.POW_2_WIDTH, -112, 5, 64, 50, 10, ByteCompanionObject.MIN_VALUE, 100, 67, 2, -126, -122, 5, 4, 26, 1, 80, 52, 2, -96, 96, 39, 0, -64, 78, 0, 20, 56, -96, 40, 113, 64, 70, 74, 108, -116, -108, -40, 51, 36, 38, 102, 72, 76, -111, -110, 19, 35, 36, 36, -92, 32, 75, 72, 64, -108, 80, -96, -44, -95, 65, -88, -124, -59, -127, 9, -117, 0, 9, 113, 12, 18, -30, 24, -6, -39, -11, -11, -77, -24, 96, -16, 19, -16, 77, -32};
    }

    private static void createRandomMask46_35() {
        _randomMask46_35 = new byte[]{12, -124, 12, 25, 8, 24, ByteCompanionObject.MIN_VALUE, 112, 7, 0, -32, 12, -96, -120, 73, 65, Tnaf.POW_2_WIDTH, -112, 5, 64, 50, 10, ByteCompanionObject.MIN_VALUE, 100, 67, 2, -126, -122, 5, 4, 26, 1, 80, 52, 2, -96, 96, 39, 0, -64, 78, 0, 20, 56, -96, 40, 113, 64, 70, 74, 108, -116, -108, -40, 98, 124, -124, -60, -7, 8, -116, 4, -119, 24, 9, Tnaf.POW_2_WIDTH, 1, 116, 34, 2, -24, 68, 7, -125, 6, 15, 6, 12, -96, ByteCompanionObject.MIN_VALUE, 115, 65, 0, -28, 24, -79, 66, 49, 98, -124, -111, 0, -109, 34, 1, 36, 120, 0, 28, -16, 0, 56, 70, 74, 108, -116, -108, -40, 98, 124, -124, -60, -7, 8, -116, 4, -119, 24, 9, Tnaf.POW_2_WIDTH, 1, 116, 34, 2, -24, 68, 7, -125, 6, 15, 6, 12, -96, ByteCompanionObject.MIN_VALUE, 115, 65, 0, -28, 24, -79, 66, 49, 98, -124, -111, 0, -109, 34, 1, 36, 120, 0, 28, -16, 0, 56, 12, -124, 12, 25, 8, 24, ByteCompanionObject.MIN_VALUE, 112, 7, 0, -32, 12, -96, -120, 73, 65, Tnaf.POW_2_WIDTH, -112, 5, 64, 50, 10, ByteCompanionObject.MIN_VALUE, 100, 67, 2, -126, -122, 5, 4, 26, 1, 80, 52, 2, -96, 96, 39, 0, -64, 78, 0, 20, 56, -96, 40, 113, 64, -126, 50, 87, 4, 100, -84};
    }

    private static void createRandomMask46_36() {
        _randomMask46_36 = new byte[]{70, 74, 108, -116, -108, -40, 98, 124, -124, -60, -7, 8, -116, 4, -119, 24, 9, Tnaf.POW_2_WIDTH, 1, 116, 34, 2, -24, 68, 7, -125, 6, 15, 6, 12, -96, ByteCompanionObject.MIN_VALUE, 115, 65, 0, -28, 24, -79, 66, 49, 98, -124, -111, 0, -109, 34, 1, 36, 120, 0, 28, -16, 0, 56, 12, -124, 12, 25, 8, 24, ByteCompanionObject.MIN_VALUE, 112, 7, 0, -32, 12, -96, -120, 73, 65, Tnaf.POW_2_WIDTH, -112, 5, 64, 50, 10, ByteCompanionObject.MIN_VALUE, 100, 67, 2, -126, -122, 5, 4, 26, 1, 80, 52, 2, -96, 96, 39, 0, -64, 78, 0, 20, 56, -96, 40, 113, 64, -126, 50, 87, 4, 100, -84, 12, -124, 12, 25, 8, 24, ByteCompanionObject.MIN_VALUE, 112, 7, 0, -32, 12, -96, -120, 73, 65, Tnaf.POW_2_WIDTH, -112, 5, 64, 50, 10, ByteCompanionObject.MIN_VALUE, 100, 67, 2, -126, -122, 5, 4, 26, 1, 80, 52, 2, -96, 96, 39, 0, -64, 78, 0, 20, 56, -96, 40, 113, 64, 70, 74, 108, -116, -108, -40, 98, 124, -124, -60, -7, 8, -116, 4, -119, 24, 9, Tnaf.POW_2_WIDTH, 1, 116, 34, 2, -24, 68, 7, -125, 6, 15, 6, 12, -96, ByteCompanionObject.MIN_VALUE, 115, 65, 0, -28, 24, -79, 66, 49, 98, -124, -111, 0, -109, 34, 1, 36, 120, 0, 28, -16, 0, 56, 108, 58, 69, 112, -41, 0};
    }

    private static void createRandomMask46_37() {
        _randomMask46_37 = new byte[]{70, 74, 108, -116, -108, -40, 98, 124, -124, -60, -7, 8, -116, 4, -119, 24, 9, Tnaf.POW_2_WIDTH, 1, 116, 34, 2, -24, 68, 7, -125, 6, 15, 6, 12, -96, ByteCompanionObject.MIN_VALUE, 115, 65, 0, -28, 24, -79, 66, 49, 98, -124, -111, 0, -109, 34, 1, 36, 120, 0, 28, -16, 0, 56, 12, -124, 12, 25, 8, 24, ByteCompanionObject.MIN_VALUE, 112, 7, 0, -32, 12, -96, -120, 73, 65, Tnaf.POW_2_WIDTH, -112, 5, 64, 50, 10, ByteCompanionObject.MIN_VALUE, 100, 67, 2, -126, -122, 5, 4, 26, 1, 80, 52, 2, -96, 96, 39, 0, -64, 78, 0, 20, 56, -96, 40, 113, 64, -126, 50, 87, 4, 100, -84, 70, 74, 108, -116, -108, -40, 98, 124, -124, -60, -7, 8, -116, 4, -119, 24, 9, Tnaf.POW_2_WIDTH, 1, 116, 34, 2, -24, 68, 7, -125, 6, 15, 6, 12, -96, ByteCompanionObject.MIN_VALUE, 115, 65, 0, -28, 24, -79, 66, 49, 98, -124, -111, 0, -109, 34, 1, 36, 120, 0, 28, -16, 0, 56, 100, 74, 40, -56, -108, 80, 81, 72, -94, -94, -111, 68, -87, Tnaf.POW_2_WIDTH, 27, 82, 32, 52, 4, -60, -124, 9, -119, 8, -48, 1, 69, -96, 2, -120, -126, 64, 29, 4, ByteCompanionObject.MIN_VALUE, 56, 33, 55, 0, 66, 110, 0, 12, 33, 34, 24, 66, 68, 74, 10, -64, -108, 21, ByteCompanionObject.MIN_VALUE, 18, -76, 80, 37, 104, -96};
    }

    private static void createRandomMask46_38() {
        _randomMask46_38 = new byte[]{70, 74, 108, -116, -108, -40, 98, 124, -124, -60, -7, 8, -116, 4, -119, 24, 9, Tnaf.POW_2_WIDTH, 1, 116, 34, 2, -24, 68, 7, -125, 6, 15, 6, 12, -96, ByteCompanionObject.MIN_VALUE, 115, 65, 0, -28, 24, -79, 66, 49, 98, -124, -111, 0, -109, 34, 1, 36, 120, 0, 28, -16, 0, 56, 100, 74, 40, -56, -108, 80, 81, 72, -94, -94, -111, 68, -87, Tnaf.POW_2_WIDTH, 27, 82, 32, 52, 4, -60, -124, 9, -119, 8, -48, 1, 69, -96, 2, -120, -126, 64, 29, 4, ByteCompanionObject.MIN_VALUE, 56, 33, 55, 0, 66, 110, 0, 12, 33, 34, 24, 66, 68, 74, 10, -64, -108, 21, ByteCompanionObject.MIN_VALUE, 18, -76, 80, 37, 104, -96, 70, 74, 108, -116, -108, -40, 98, 124, -124, -60, -7, 8, -116, 4, -119, 24, 9, Tnaf.POW_2_WIDTH, 1, 116, 34, 2, -24, 68, 7, -125, 6, 15, 6, 12, -96, ByteCompanionObject.MIN_VALUE, 115, 65, 0, -28, 24, -79, 66, 49, 98, -124, -111, 0, -109, 34, 1, 36, 120, 0, 28, -16, 0, 56, 12, -124, 12, 25, 8, 24, ByteCompanionObject.MIN_VALUE, 112, 7, 0, -32, 12, -96, -120, 73, 65, Tnaf.POW_2_WIDTH, -112, 5, 64, 50, 10, ByteCompanionObject.MIN_VALUE, 100, 67, 2, -126, -122, 5, 4, 26, 1, 80, 52, 2, -96, 96, 39, 0, -64, 78, 0, 20, 56, -96, 40, 113, 64, -126, 50, 87, 4, 100, -84, 114, 43, -91, -44, -71, 48};
    }

    private static void createRandomMask46_39() {
        _randomMask46_39 = new byte[]{70, 74, 108, -116, -108, -40, 98, 124, -124, -60, -7, 8, -116, 4, -119, 24, 9, Tnaf.POW_2_WIDTH, 1, 116, 34, 2, -24, 68, 7, -125, 6, 15, 6, 12, -96, ByteCompanionObject.MIN_VALUE, 115, 65, 0, -28, 24, -79, 66, 49, 98, -124, -111, 0, -109, 34, 1, 36, 120, 0, 28, -16, 0, 56, 100, 74, 40, -56, -108, 80, 81, 72, -94, -94, -111, 68, -87, Tnaf.POW_2_WIDTH, 27, 82, 32, 52, 4, -60, -124, 9, -119, 8, -48, 1, 69, -96, 2, -120, -126, 64, 29, 4, ByteCompanionObject.MIN_VALUE, 56, 33, 55, 0, 66, 110, 0, 12, 33, 34, 24, 66, 68, 74, 10, -64, -108, 21, ByteCompanionObject.MIN_VALUE, 18, -76, 80, 37, 104, -96, 100, 74, 40, -56, -108, 80, 81, 72, -94, -94, -111, 68, -87, Tnaf.POW_2_WIDTH, 27, 82, 32, 52, 4, -60, -124, 9, -119, 8, -48, 1, 69, -96, 2, -120, -126, 64, 29, 4, ByteCompanionObject.MIN_VALUE, 56, 33, 55, 0, 66, 110, 0, 12, 33, 34, 24, 66, 68, 74, 10, -64, -108, 21, ByteCompanionObject.MIN_VALUE, 18, -76, 80, 37, 104, -96, 70, 74, 108, -116, -108, -40, 98, 124, -124, -60, -7, 8, -116, 4, -119, 24, 9, Tnaf.POW_2_WIDTH, 1, 116, 34, 2, -24, 68, 7, -125, 6, 15, 6, 12, -96, ByteCompanionObject.MIN_VALUE, 115, 65, 0, -28, 24, -79, 66, 49, 98, -124, -111, 0, -109, 34, 1, 36, 120, 0, 28, -16, 0, 56, -37, 74, 123, -74, -108, -12};
    }

    private static void createRandomMask46_4() {
        _randomMask46_4 = new byte[]{-60, -47, 101, -119, -94, -56, 49, 98, -106, 98, -59, 44, 75, 36, 90, -106, 72, -76, 44, -88, -86, 89, 81, 84};
    }

    private static void createRandomMask46_40() {
        _randomMask46_40 = new byte[]{100, 74, 40, -56, -108, 80, 81, 72, -94, -94, -111, 68, -87, Tnaf.POW_2_WIDTH, 27, 82, 32, 52, 4, -60, -124, 9, -119, 8, -48, 1, 69, -96, 2, -120, -126, 64, 29, 4, ByteCompanionObject.MIN_VALUE, 56, 33, 55, 0, 66, 110, 0, 12, 33, 34, 24, 66, 68, 74, 10, -64, -108, 21, ByteCompanionObject.MIN_VALUE, 18, -76, 80, 37, 104, -96, 70, 74, 108, -116, -108, -40, 98, 124, -124, -60, -7, 8, -116, 4, -119, 24, 9, Tnaf.POW_2_WIDTH, 1, 116, 34, 2, -24, 68, 7, -125, 6, 15, 6, 12, -96, ByteCompanionObject.MIN_VALUE, 115, 65, 0, -28, 24, -79, 66, 49, 98, -124, -111, 0, -109, 34, 1, 36, 120, 0, 28, -16, 0, 56, -37, 74, 123, -74, -108, -12, 70, 74, 108, -116, -108, -40, 98, 124, -124, -60, -7, 8, -116, 4, -119, 24, 9, Tnaf.POW_2_WIDTH, 1, 116, 34, 2, -24, 68, 7, -125, 6, 15, 6, 12, -96, ByteCompanionObject.MIN_VALUE, 115, 65, 0, -28, 24, -79, 66, 49, 98, -124, -111, 0, -109, 34, 1, 36, 120, 0, 28, -16, 0, 56, 100, 74, 40, -56, -108, 80, 81, 72, -94, -94, -111, 68, -87, Tnaf.POW_2_WIDTH, 27, 82, 32, 52, 4, -60, -124, 9, -119, 8, -48, 1, 69, -96, 2, -120, -126, 64, 29, 4, ByteCompanionObject.MIN_VALUE, 56, 33, 55, 0, 66, 110, 0, 12, 33, 34, 24, 66, 68, 74, 10, -64, -108, 21, ByteCompanionObject.MIN_VALUE, 18, -76, 80, 37, 104, -96, 124, -56, -109, 99, 60, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask46_41() {
        _randomMask46_41 = new byte[]{100, 74, 40, -56, -108, 80, 81, 72, -94, -94, -111, 68, -87, Tnaf.POW_2_WIDTH, 27, 82, 32, 52, 4, -60, -124, 9, -119, 8, -48, 1, 69, -96, 2, -120, -126, 64, 29, 4, ByteCompanionObject.MIN_VALUE, 56, 33, 55, 0, 66, 110, 0, 12, 33, 34, 24, 66, 68, 74, 10, -64, -108, 21, ByteCompanionObject.MIN_VALUE, 18, -76, 80, 37, 104, -96, 70, 74, 108, -116, -108, -40, 98, 124, -124, -60, -7, 8, -116, 4, -119, 24, 9, Tnaf.POW_2_WIDTH, 1, 116, 34, 2, -24, 68, 7, -125, 6, 15, 6, 12, -96, ByteCompanionObject.MIN_VALUE, 115, 65, 0, -28, 24, -79, 66, 49, 98, -124, -111, 0, -109, 34, 1, 36, 120, 0, 28, -16, 0, 56, -37, 74, 123, -74, -108, -12, 100, 74, 40, -56, -108, 80, 81, 72, -94, -94, -111, 68, -87, Tnaf.POW_2_WIDTH, 27, 82, 32, 52, 4, -60, -124, 9, -119, 8, -48, 1, 69, -96, 2, -120, -126, 64, 29, 4, ByteCompanionObject.MIN_VALUE, 56, 33, 55, 0, 66, 110, 0, 12, 33, 34, 24, 66, 68, 74, 10, -64, -108, 21, ByteCompanionObject.MIN_VALUE, 18, -76, 80, 37, 104, -96, 70, 74, 108, -116, -108, -40, 51, 36, 38, 102, 72, 76, -103, 2, 19, 50, 4, 36, 5, ByteCompanionObject.MIN_VALUE, 14, 11, 0, 28, ByteCompanionObject.MIN_VALUE, -95, -125, 1, 67, 4, -124, 72, 25, 8, -112, 48, 64, 109, 64, ByteCompanionObject.MIN_VALUE, -38, ByteCompanionObject.MIN_VALUE, 10, -112, -64, 21, 33, ByteCompanionObject.MIN_VALUE, 104, 4, -112, -48, 9, 32, Tnaf.POW_2_WIDTH, 49, 32, 32, 98, 64, 48, 88, 4, 96, -80, 8};
    }

    private static void createRandomMask46_42() {
        _randomMask46_42 = new byte[]{100, 74, 40, -56, -108, 80, 81, 72, -94, -94, -111, 68, -87, Tnaf.POW_2_WIDTH, 27, 82, 32, 52, 4, -60, -124, 9, -119, 8, -48, 1, 69, -96, 2, -120, -126, 64, 29, 4, ByteCompanionObject.MIN_VALUE, 56, 33, 55, 0, 66, 110, 0, 12, 33, 34, 24, 66, 68, 74, 10, -64, -108, 21, ByteCompanionObject.MIN_VALUE, 18, -76, 80, 37, 104, -96, 70, 74, 108, -116, -108, -40, 51, 36, 38, 102, 72, 76, -103, 2, 19, 50, 4, 36, 5, ByteCompanionObject.MIN_VALUE, 14, 11, 0, 28, ByteCompanionObject.MIN_VALUE, -95, -125, 1, 67, 4, -124, 72, 25, 8, -112, 48, 64, 109, 64, ByteCompanionObject.MIN_VALUE, -38, ByteCompanionObject.MIN_VALUE, 10, -112, -64, 21, 33, ByteCompanionObject.MIN_VALUE, 104, 4, -112, -48, 9, 32, Tnaf.POW_2_WIDTH, 49, 32, 32, 98, 64, 48, 88, 4, 96, -80, 8, 100, 74, 40, -56, -108, 80, 81, 72, -94, -94, -111, 68, -87, Tnaf.POW_2_WIDTH, 27, 82, 32, 52, 4, -60, -124, 9, -119, 8, -48, 1, 69, -96, 2, -120, -126, 64, 29, 4, ByteCompanionObject.MIN_VALUE, 56, 33, 55, 0, 66, 110, 0, 12, 33, 34, 24, 66, 68, 74, 10, -64, -108, 21, ByteCompanionObject.MIN_VALUE, 18, -76, 80, 37, 104, -96, 70, 74, 108, -116, -108, -40, 98, 124, -124, -60, -7, 8, -116, 4, -119, 24, 9, Tnaf.POW_2_WIDTH, 1, 116, 34, 2, -24, 68, 7, -125, 6, 15, 6, 12, -96, ByteCompanionObject.MIN_VALUE, 115, 65, 0, -28, 24, -79, 66, 49, 98, -124, -111, 0, -109, 34, 1, 36, 120, 0, 28, -16, 0, 56, -37, 74, 123, -74, -108, -12, -4, 110, -119, 84, 79, 0};
    }

    private static void createRandomMask46_43() {
        _randomMask46_43 = new byte[]{100, 74, 40, -56, -108, 80, 81, 72, -94, -94, -111, 68, -87, Tnaf.POW_2_WIDTH, 27, 82, 32, 52, 4, -60, -124, 9, -119, 8, -48, 1, 69, -96, 2, -120, -126, 64, 29, 4, ByteCompanionObject.MIN_VALUE, 56, 33, 55, 0, 66, 110, 0, 12, 33, 34, 24, 66, 68, 74, 10, -64, -108, 21, ByteCompanionObject.MIN_VALUE, 18, -76, 80, 37, 104, -96, 70, 74, 108, -116, -108, -40, 51, 36, 38, 102, 72, 76, -103, 2, 19, 50, 4, 36, 5, ByteCompanionObject.MIN_VALUE, 14, 11, 0, 28, ByteCompanionObject.MIN_VALUE, -95, -125, 1, 67, 4, -124, 72, 25, 8, -112, 48, 64, 109, 64, ByteCompanionObject.MIN_VALUE, -38, ByteCompanionObject.MIN_VALUE, 10, -112, -64, 21, 33, ByteCompanionObject.MIN_VALUE, 104, 4, -112, -48, 9, 32, Tnaf.POW_2_WIDTH, 49, 32, 32, 98, 64, 48, 88, 4, 96, -80, 8, 70, 74, 108, -116, -108, -40, 51, 36, 38, 102, 72, 76, -103, 2, 19, 50, 4, 36, 5, ByteCompanionObject.MIN_VALUE, 14, 11, 0, 28, ByteCompanionObject.MIN_VALUE, -95, -125, 1, 67, 4, -124, 72, 25, 8, -112, 48, 64, 109, 64, ByteCompanionObject.MIN_VALUE, -38, ByteCompanionObject.MIN_VALUE, 10, -112, -64, 21, 33, ByteCompanionObject.MIN_VALUE, 104, 4, -112, -48, 9, 32, Tnaf.POW_2_WIDTH, 49, 32, 32, 98, 64, 48, 88, 4, 96, -80, 8, 100, 74, 40, -56, -108, 80, 81, 72, -94, -94, -111, 68, -87, Tnaf.POW_2_WIDTH, 27, 82, 32, 52, 4, -60, -124, 9, -119, 8, -48, 1, 69, -96, 2, -120, -126, 64, 29, 4, ByteCompanionObject.MIN_VALUE, 56, 33, 55, 0, 66, 110, 0, 12, 33, 34, 24, 66, 68, 74, 10, -64, -108, 21, ByteCompanionObject.MIN_VALUE, 18, -76, 80, 37, 104, -96, -22, -115, 27, -43, 26, 52};
    }

    private static void createRandomMask46_44() {
        _randomMask46_44 = new byte[]{70, 74, 108, -116, -108, -40, 51, 36, 38, 102, 72, 76, -103, 2, 19, 50, 4, 36, 5, ByteCompanionObject.MIN_VALUE, 14, 11, 0, 28, ByteCompanionObject.MIN_VALUE, -95, -125, 1, 67, 4, -124, 72, 25, 8, -112, 48, 64, 109, 64, ByteCompanionObject.MIN_VALUE, -38, ByteCompanionObject.MIN_VALUE, 10, -112, -64, 21, 33, ByteCompanionObject.MIN_VALUE, 104, 4, -112, -48, 9, 32, Tnaf.POW_2_WIDTH, 49, 32, 32, 98, 64, 48, 88, 4, 96, -80, 8, 100, 74, 40, -56, -108, 80, 81, 72, -94, -94, -111, 68, -87, Tnaf.POW_2_WIDTH, 27, 82, 32, 52, 4, -60, -124, 9, -119, 8, -48, 1, 69, -96, 2, -120, -126, 64, 29, 4, ByteCompanionObject.MIN_VALUE, 56, 33, 55, 0, 66, 110, 0, 12, 33, 34, 24, 66, 68, 74, 10, -64, -108, 21, ByteCompanionObject.MIN_VALUE, 18, -76, 80, 37, 104, -96, -22, -115, 27, -43, 26, 52, 100, 74, 40, -56, -108, 80, 81, 72, -94, -94, -111, 68, -87, Tnaf.POW_2_WIDTH, 27, 82, 32, 52, 4, -60, -124, 9, -119, 8, -48, 1, 69, -96, 2, -120, -126, 64, 29, 4, ByteCompanionObject.MIN_VALUE, 56, 33, 55, 0, 66, 110, 0, 12, 33, 34, 24, 66, 68, 74, 10, -64, -108, 21, ByteCompanionObject.MIN_VALUE, 18, -76, 80, 37, 104, -96, 70, 74, 108, -116, -108, -40, 51, 36, 38, 102, 72, 76, -103, 2, 19, 50, 4, 36, 5, ByteCompanionObject.MIN_VALUE, 14, 11, 0, 28, ByteCompanionObject.MIN_VALUE, -95, -125, 1, 67, 4, -124, 72, 25, 8, -112, 48, 64, 109, 64, ByteCompanionObject.MIN_VALUE, -38, ByteCompanionObject.MIN_VALUE, 10, -112, -64, 21, 33, ByteCompanionObject.MIN_VALUE, 104, 4, -112, -48, 9, 32, Tnaf.POW_2_WIDTH, 49, 32, 32, 98, 64, 48, 88, 4, 96, -80, 8, 26, -118, 0, 28, -119, 84};
    }

    private static void createRandomMask46_45() {
        _randomMask46_45 = new byte[]{70, 74, 108, -116, -108, -40, 51, 36, 38, 102, 72, 76, -103, 2, 19, 50, 4, 36, 5, ByteCompanionObject.MIN_VALUE, 14, 11, 0, 28, ByteCompanionObject.MIN_VALUE, -95, -125, 1, 67, 4, -124, 72, 25, 8, -112, 48, 64, 109, 64, ByteCompanionObject.MIN_VALUE, -38, ByteCompanionObject.MIN_VALUE, 10, -112, -64, 21, 33, ByteCompanionObject.MIN_VALUE, 104, 4, -112, -48, 9, 32, Tnaf.POW_2_WIDTH, 49, 32, 32, 98, 64, 48, 88, 4, 96, -80, 8, 100, 74, 40, -56, -108, 80, 81, 72, -94, -94, -111, 68, -87, Tnaf.POW_2_WIDTH, 27, 82, 32, 52, 4, -60, -124, 9, -119, 8, -48, 1, 69, -96, 2, -120, -126, 64, 29, 4, ByteCompanionObject.MIN_VALUE, 56, 33, 55, 0, 66, 110, 0, 12, 33, 34, 24, 66, 68, 74, 10, -64, -108, 21, ByteCompanionObject.MIN_VALUE, 18, -76, 80, 37, 104, -96, -22, -115, 27, -43, 26, 52, 70, 74, 108, -116, -108, -40, 51, 36, 38, 102, 72, 76, -103, 2, 19, 50, 4, 36, 5, ByteCompanionObject.MIN_VALUE, 14, 11, 0, 28, ByteCompanionObject.MIN_VALUE, -95, -125, 1, 67, 4, -124, 72, 25, 8, -112, 48, 64, 109, 64, ByteCompanionObject.MIN_VALUE, -38, ByteCompanionObject.MIN_VALUE, 10, -112, -64, 21, 33, ByteCompanionObject.MIN_VALUE, 104, 4, -112, -48, 9, 32, Tnaf.POW_2_WIDTH, 49, 32, 32, 98, 64, 48, 88, 4, 96, -80, 8, 70, 66, 12, -116, -124, 24, 51, 32, 70, 102, 64, -116, -103, 8, 11, 50, Tnaf.POW_2_WIDTH, 20, 5, -124, 48, 11, 8, 96, ByteCompanionObject.MIN_VALUE, -80, 35, 1, 96, 68, -124, 66, -111, 8, -123, 32, 64, 115, 0, ByteCompanionObject.MIN_VALUE, -26, 0, 10, -127, 18, 21, 2, 36, 104, 12, 64, -48, 24, ByteCompanionObject.MIN_VALUE, Tnaf.POW_2_WIDTH, 36, -124, 32, 73, 8, 48, 81, 64, 96, -94, ByteCompanionObject.MIN_VALUE, 95, 80, -120, -66, -95, Tnaf.POW_2_WIDTH};
    }

    private static void createRandomMask46_46() {
        _randomMask46_46 = new byte[]{70, 74, 108, -116, -108, -40, 51, 36, 38, 102, 72, 76, -103, 2, 19, 50, 4, 36, 5, ByteCompanionObject.MIN_VALUE, 14, 11, 0, 28, ByteCompanionObject.MIN_VALUE, -95, -125, 1, 67, 4, -124, 72, 25, 8, -112, 48, 64, 109, 64, ByteCompanionObject.MIN_VALUE, -38, ByteCompanionObject.MIN_VALUE, 10, -112, -64, 21, 33, ByteCompanionObject.MIN_VALUE, 104, 4, -112, -48, 9, 32, Tnaf.POW_2_WIDTH, 49, 32, 32, 98, 64, 48, 88, 4, 96, -80, 8, 70, 66, 12, -116, -124, 24, 51, 32, 70, 102, 64, -116, -103, 8, 11, 50, Tnaf.POW_2_WIDTH, 20, 5, -124, 48, 11, 8, 96, ByteCompanionObject.MIN_VALUE, -80, 35, 1, 96, 68, -124, 66, -111, 8, -123, 32, 64, 115, 0, ByteCompanionObject.MIN_VALUE, -26, 0, 10, -127, 18, 21, 2, 36, 104, 12, 64, -48, 24, ByteCompanionObject.MIN_VALUE, Tnaf.POW_2_WIDTH, 36, -124, 32, 73, 8, 48, 81, 64, 96, -94, ByteCompanionObject.MIN_VALUE, 95, 80, -120, -66, -95, Tnaf.POW_2_WIDTH, 70, 74, 108, -116, -108, -40, 51, 36, 38, 102, 72, 76, -103, 2, 19, 50, 4, 36, 5, ByteCompanionObject.MIN_VALUE, 14, 11, 0, 28, ByteCompanionObject.MIN_VALUE, -95, -125, 1, 67, 4, -124, 72, 25, 8, -112, 48, 64, 109, 64, ByteCompanionObject.MIN_VALUE, -38, ByteCompanionObject.MIN_VALUE, 10, -112, -64, 21, 33, ByteCompanionObject.MIN_VALUE, 104, 4, -112, -48, 9, 32, Tnaf.POW_2_WIDTH, 49, 32, 32, 98, 64, 48, 88, 4, 96, -80, 8, 100, 74, 40, -56, -108, 80, 81, 72, -94, -94, -111, 68, -87, Tnaf.POW_2_WIDTH, 27, 82, 32, 52, 4, -60, -124, 9, -119, 8, -48, 1, 69, -96, 2, -120, -126, 64, 29, 4, ByteCompanionObject.MIN_VALUE, 56, 33, 55, 0, 66, 110, 0, 12, 33, 34, 24, 66, 68, 74, 10, -64, -108, 21, ByteCompanionObject.MIN_VALUE, 18, -76, 80, 37, 104, -96, -22, -115, 27, -43, 26, 52, -43, -33, 89, -71, -70, Tnaf.POW_2_WIDTH};
    }

    private static void createRandomMask46_5() {
        _randomMask46_5 = new byte[]{-58, -54, 109, -115, -108, -40, 99, 108, -106, -58, -39, 44, 29, -95, -36, 59, 67, -72, -83, 85, 57, 90, -86, 112, -78, -73, 7, 101, 110, 12};
    }

    private static void createRandomMask46_6() {
        _randomMask46_6 = new byte[]{100, 74, 40, -56, -108, 80, 81, 88, -94, -94, -79, 68, 12, -92, 48, 25, 72, 96, -95, 34, 71, 66, 68, -116, 18, -95, 28, 37, 66, 56, -118, 69, -63, 20, -117, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask46_7() {
        _randomMask46_7 = new byte[]{70, 74, 108, -116, -108, -40, 51, 36, 38, 102, 72, 76, -111, -110, 19, 35, 36, 36, -92, 32, 75, 72, 64, -108, 80, -96, -44, -95, 65, -88, -124, -59, -127, 9, -117, 0, 9, 113, 12, 18, -30, 24};
    }

    private static void createRandomMask46_8() {
        _randomMask46_8 = new byte[]{12, -124, 12, 25, 8, 24, ByteCompanionObject.MIN_VALUE, 112, 7, 0, -32, 12, -96, -120, 73, 65, Tnaf.POW_2_WIDTH, -112, 5, 64, 50, 10, ByteCompanionObject.MIN_VALUE, 100, 67, 2, -126, -122, 5, 4, 26, 1, 80, 52, 2, -96, 96, 39, 0, -64, 78, 0, 20, 56, -96, 40, 113, 64};
    }

    private static void createRandomMask46_9() {
        _randomMask46_9 = new byte[]{70, 74, 108, -116, -108, -40, 98, 124, -124, -60, -7, 8, -116, 4, -119, 24, 9, Tnaf.POW_2_WIDTH, 1, 116, 34, 2, -24, 68, 7, -125, 6, 15, 6, 12, -96, ByteCompanionObject.MIN_VALUE, 115, 65, 0, -28, 24, -79, 66, 49, 98, -124, -111, 0, -109, 34, 1, 36, 120, 0, 28, -16, 0, 56};
    }

    private static void createRandomMask47_1() {
        _randomMask47_1 = new byte[]{-1, -1, -1, -1, -1, -2};
    }

    private static void createRandomMask47_10() {
        _randomMask47_10 = new byte[]{100, 74, 40, 34, -118, 40, 81, 72, -94, -118, 104, -90, -87, Tnaf.POW_2_WIDTH, 26, 0, -112, 10, 4, -60, -124, 33, 6, 18, -48, 1, 68, -108, 41, 66, -126, 64, 28, -127, 72, 20, 33, 55, 1, 64, -44, 4, 12, 33, 35, 17, 1, 24, 74, 10, -63, 12, Tnaf.POW_2_WIDTH, -64, 18, -76, 80, -88, 26, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask47_11() {
        _randomMask47_11 = new byte[]{70, 74, 108, -90, -54, 104, 51, 36, 39, 64, 100, 34, -103, 2, 18, 42, 34, -126, 5, ByteCompanionObject.MIN_VALUE, 14, 6, -96, 42, ByteCompanionObject.MIN_VALUE, -95, -125, 25, 17, -112, -124, 72, 24, 81, 5, Tnaf.POW_2_WIDTH, 64, 109, 64, Tnaf.POW_2_WIDTH, -111, 8, 10, -112, -63, 50, 3, 32, 104, 4, -112, 69, 36, 82, Tnaf.POW_2_WIDTH, 49, 32, -116, 8, -64, 48, 88, 5, 24, 88, 4};
    }

    private static void createRandomMask47_12() {
        _randomMask47_12 = new byte[]{100, 74, 40, 32, -62, 12, 81, 88, -94, 4, 96, 70, 12, -92, 48, ByteCompanionObject.MIN_VALUE, -88, 10, -95, 34, 70, 67, 4, 48, 18, -95, 29, 2, 48, 34, -118, 69, -64, 41, 2, -112, -122, -118, 109, 48, 19, 0, 35, 44, -124, 17, 33, 18, 22, 33, -104, -60, 12, 64, 76, 48, 84, 72, 68, -124, 65, -63, 39, 20, 17, 64, 25, 86, -27, 8, -112, -120};
    }

    private static void createRandomMask47_13() {
        _randomMask47_13 = new byte[]{100, 74, 40, -94, -118, 40, 81, 88, -93, -118, 56, -94, 12, -92, 48, 67, 4, 48, -95, 34, 70, 36, 98, 70, 18, -95, 28, 17, -63, 28, -118, 69, -64, 92, 5, -64, 70, 74, 108, -90, -54, 108, 51, 36, 38, 66, 100, 38, -111, -110, 19, 33, 50, 18, -92, 32, 74, 4, -96, 74, 80, -96, -44, 13, 64, -44, -124, -59, ByteCompanionObject.MIN_VALUE, 88, 5, ByteCompanionObject.MIN_VALUE, 9, 113, 13, Tnaf.POW_2_WIDTH, -47, 12};
    }

    private static void createRandomMask47_14() {
        _randomMask47_14 = new byte[]{70, 74, 108, -90, -54, 108, 51, 36, 38, 66, 100, 38, -111, -110, 19, 33, 50, 18, -92, 32, 74, 4, -96, 74, 80, -96, -44, 13, 64, -44, -124, -59, ByteCompanionObject.MIN_VALUE, 88, 5, ByteCompanionObject.MIN_VALUE, 9, 113, 13, Tnaf.POW_2_WIDTH, -47, 12, 100, 74, 40, -94, -118, 40, 81, 88, -93, -118, 56, -94, 12, -92, 48, 67, 4, 48, -95, 34, 70, 36, 98, 70, 18, -95, 28, 17, -63, 28, -118, 69, -64, 92, 5, -64, -100, Utf8.REPLACEMENT_BYTE, -77, -27, -83, 28};
    }

    private static void createRandomMask47_15() {
        _randomMask47_15 = new byte[]{70, 74, 108, -90, -54, 108, 51, 36, 38, 66, 100, 38, -111, -110, 19, 33, 50, 18, -92, 32, 74, 4, -96, 74, 80, -96, -44, 13, 64, -44, -124, -59, ByteCompanionObject.MIN_VALUE, 88, 5, ByteCompanionObject.MIN_VALUE, 9, 113, 13, Tnaf.POW_2_WIDTH, -47, 12, 12, -124, 12, 64, -60, 12, ByteCompanionObject.MIN_VALUE, 112, 7, 0, 112, 6, -96, -120, 72, -124, -120, 72, 5, 64, 50, 3, 32, 50, 67, 2, -126, 40, 34, -126, 26, 1, 80, 21, 1, 80, 96, 39, 0, 112, 7, 0, 20, 56, -95, -118, 24, -96};
    }

    private static void createRandomMask47_16() {
        _randomMask47_16 = new byte[]{12, -124, 12, 64, -60, 12, ByteCompanionObject.MIN_VALUE, 112, 7, 0, 112, 6, -96, -120, 72, -124, -120, 72, 5, 64, 50, 3, 32, 50, 67, 2, -126, 40, 34, -126, 26, 1, 80, 21, 1, 80, 96, 39, 0, 112, 7, 0, 20, 56, -95, -118, 24, -96, 70, 74, 108, -90, -54, 108, 51, 36, 38, 66, 100, 38, -111, -110, 19, 33, 50, 18, -92, 32, 74, 4, -96, 74, 80, -96, -44, 13, 64, -44, -124, -59, ByteCompanionObject.MIN_VALUE, 88, 5, ByteCompanionObject.MIN_VALUE, 9, 113, 13, Tnaf.POW_2_WIDTH, -47, 12, -6, -39, -11, -2, -36, 20};
    }

    private static void createRandomMask47_17() {
        _randomMask47_17 = new byte[]{12, -124, 12, 64, -60, 12, ByteCompanionObject.MIN_VALUE, 112, 7, 0, 112, 6, -96, -120, 72, -124, -120, 72, 5, 64, 50, 3, 32, 50, 67, 2, -126, 40, 34, -126, 26, 1, 80, 21, 1, 80, 96, 39, 0, 112, 7, 0, 20, 56, -95, -118, 24, -96, 70, 74, 108, -90, -54, 108, 98, 124, -123, -56, 92, -124, -116, 4, -120, 72, -124, -120, 1, 116, 35, 66, 52, 34, 7, -125, 6, 48, 99, 6, -96, ByteCompanionObject.MIN_VALUE, 114, 7, 32, 114, 24, -79, 67, 20, 49, 66, -111, 0, -110, 9, 32, -110, 120, 0, 28, 1, -64, 28};
    }

    private static void createRandomMask47_18() {
        _randomMask47_18 = new byte[]{70, 74, 108, -90, -54, 108, 98, 124, -123, -56, 92, -124, -116, 4, -120, 72, -124, -120, 1, 116, 35, 66, 52, 34, 7, -125, 6, 48, 99, 6, -96, ByteCompanionObject.MIN_VALUE, 114, 7, 32, 114, 24, -79, 67, 20, 49, 66, -111, 0, -110, 9, 32, -110, 120, 0, 28, 1, -64, 28, 12, -124, 12, 64, -60, 12, ByteCompanionObject.MIN_VALUE, 112, 7, 0, 112, 6, -96, -120, 72, -124, -120, 72, 5, 64, 50, 3, 32, 50, 67, 2, -126, 40, 34, -126, 26, 1, 80, 21, 1, 80, 96, 39, 0, 112, 7, 0, 20, 56, -95, -118, 24, -96, -126, 50, 86, 104, -95, 92};
    }

    private static void createRandomMask47_19() {
        _randomMask47_19 = new byte[]{70, 74, 108, -90, -54, 108, 98, 124, -123, -56, 92, -124, -116, 4, -120, 72, -124, -120, 1, 116, 35, 66, 52, 34, 7, -125, 6, 48, 99, 6, -96, ByteCompanionObject.MIN_VALUE, 114, 7, 32, 114, 24, -79, 67, 20, 49, 66, -111, 0, -110, 9, 32, -110, 120, 0, 28, 1, -64, 28, 100, 74, 40, -94, -118, 40, 81, 72, -94, -118, 40, -94, -87, Tnaf.POW_2_WIDTH, 27, 1, -80, 26, 4, -60, -124, 72, 68, -124, -48, 1, 68, 20, 65, 68, -126, 64, 28, 1, -64, 28, 33, 55, 1, 112, 23, 0, 12, 33, 34, 18, 33, 34, 74, 10, -64, -84, 10, -64, 18, -76, 81, 69, 20, 80};
    }

    private static void createRandomMask47_2() {
        _randomMask47_2 = new byte[]{-20, -35, -103, -39, -99, -104, -101, -78, 119, 39, 114, 118};
    }

    private static void createRandomMask47_20() {
        _randomMask47_20 = new byte[]{100, 74, 40, -94, -118, 40, 81, 72, -94, -118, 40, -94, -87, Tnaf.POW_2_WIDTH, 27, 1, -80, 26, 4, -60, -124, 72, 68, -124, -48, 1, 68, 20, 65, 68, -126, 64, 28, 1, -64, 28, 33, 55, 1, 112, 23, 0, 12, 33, 34, 18, 33, 34, 74, 10, -64, -84, 10, -64, 18, -76, 81, 69, 20, 80, 70, 74, 108, -90, -54, 108, 98, 124, -123, -56, 92, -124, -116, 4, -120, 72, -124, -120, 1, 116, 35, 66, 52, 34, 7, -125, 6, 48, 99, 6, -96, ByteCompanionObject.MIN_VALUE, 114, 7, 32, 114, 24, -79, 67, 20, 49, 66, -111, 0, -110, 9, 32, -110, 120, 0, 28, 1, -64, 28, -37, 74, 123, 49, 69, 42};
    }

    private static void createRandomMask47_21() {
        _randomMask47_21 = new byte[]{100, 74, 40, -94, -118, 40, 81, 72, -94, -118, 40, -94, -87, Tnaf.POW_2_WIDTH, 27, 1, -80, 26, 4, -60, -124, 72, 68, -124, -48, 1, 68, 20, 65, 68, -126, 64, 28, 1, -64, 28, 33, 55, 1, 112, 23, 0, 12, 33, 34, 18, 33, 34, 74, 10, -64, -84, 10, -64, 18, -76, 81, 69, 20, 80, 70, 74, 108, -90, -54, 108, 51, 36, 38, 66, 100, 38, -103, 2, 18, 33, 34, 18, 5, ByteCompanionObject.MIN_VALUE, 14, 0, -32, 14, ByteCompanionObject.MIN_VALUE, -95, -126, 24, 33, -126, -124, 72, 24, -127, -120, 24, 64, 109, 64, -44, 13, 64, 10, -112, -63, 12, Tnaf.POW_2_WIDTH, -64, 104, 4, -112, 73, 4, -112, Tnaf.POW_2_WIDTH, 49, 33, 18, 17, 32, 48, 88, 5, ByteCompanionObject.MIN_VALUE, 88, 4};
    }

    private static void createRandomMask47_22() {
        _randomMask47_22 = new byte[]{70, 74, 108, -90, -54, 108, 51, 36, 38, 66, 100, 38, -103, 2, 18, 33, 34, 18, 5, ByteCompanionObject.MIN_VALUE, 14, 0, -32, 14, ByteCompanionObject.MIN_VALUE, -95, -126, 24, 33, -126, -124, 72, 24, -127, -120, 24, 64, 109, 64, -44, 13, 64, 10, -112, -63, 12, Tnaf.POW_2_WIDTH, -64, 104, 4, -112, 73, 4, -112, Tnaf.POW_2_WIDTH, 49, 33, 18, 17, 32, 48, 88, 5, ByteCompanionObject.MIN_VALUE, 88, 4, 100, 74, 40, -94, -118, 40, 81, 72, -94, -118, 40, -94, -87, Tnaf.POW_2_WIDTH, 27, 1, -80, 26, 4, -60, -124, 72, 68, -124, -48, 1, 68, 20, 65, 68, -126, 64, 28, 1, -64, 28, 33, 55, 1, 112, 23, 0, 12, 33, 34, 18, 33, 34, 74, 10, -64, -84, 10, -64, 18, -76, 81, 69, 20, 80, -22, -115, 26, 53, 85, -36};
    }

    private static void createRandomMask47_23() {
        _randomMask47_23 = new byte[]{70, 74, 108, -90, -54, 108, 51, 36, 38, 66, 100, 38, -103, 2, 18, 33, 34, 18, 5, ByteCompanionObject.MIN_VALUE, 14, 0, -32, 14, ByteCompanionObject.MIN_VALUE, -95, -126, 24, 33, -126, -124, 72, 24, -127, -120, 24, 64, 109, 64, -44, 13, 64, 10, -112, -63, 12, Tnaf.POW_2_WIDTH, -64, 104, 4, -112, 73, 4, -112, Tnaf.POW_2_WIDTH, 49, 33, 18, 17, 32, 48, 88, 5, ByteCompanionObject.MIN_VALUE, 88, 4, 70, 66, 12, 32, -62, 12, 51, 32, 70, 4, 96, 70, -103, 8, 10, ByteCompanionObject.MIN_VALUE, -88, 10, 5, -124, 48, 67, 4, 48, ByteCompanionObject.MIN_VALUE, -80, 35, 2, 48, 34, -124, 66, -112, 41, 2, -112, 64, 115, 1, 48, 19, 0, 10, -127, 18, 17, 33, 18, 104, 12, 64, -60, 12, 64, Tnaf.POW_2_WIDTH, 36, -124, 72, 68, -124, 48, 81, 65, 20, 17, 64, 95, 80, -119, 8, -112, -120};
    }

    private static void createRandomMask47_24() {
        _randomMask47_24 = new byte[]{100, 74, 40, 32, -62, 12, 81, 88, -94, 4, 96, 70, 12, -92, 48, ByteCompanionObject.MIN_VALUE, -88, 10, -95, 34, 70, 67, 4, 48, 18, -95, 29, 2, 48, 34, -118, 69, -64, 41, 2, -112, -122, -118, 109, 48, 19, 0, 35, 44, -124, 17, 33, 18, 22, 33, -104, -60, 12, 64, 76, 48, 84, 72, 68, -124, 65, -63, 39, 20, 17, 64, 25, 86, -27, 8, -112, -120, 70, 74, 108, -90, -54, 104, 51, 36, 39, 64, 100, 34, -103, 2, 18, 42, 34, -126, 5, ByteCompanionObject.MIN_VALUE, 14, 6, -96, 42, ByteCompanionObject.MIN_VALUE, -95, -125, 25, 17, -112, -124, 72, 24, 81, 5, Tnaf.POW_2_WIDTH, 64, 109, 64, Tnaf.POW_2_WIDTH, -111, 8, 10, -112, -63, 50, 3, 32, 104, 4, -112, 69, 36, 82, Tnaf.POW_2_WIDTH, 49, 32, -116, 8, -64, 48, 88, 5, 24, 88, 4, 39, 65, 53, 87, 126, 106};
    }

    private static void createRandomMask47_25() {
        _randomMask47_25 = new byte[]{100, 74, 40, 32, -62, 12, 81, 88, -94, 4, 96, 70, 12, -92, 48, ByteCompanionObject.MIN_VALUE, -88, 10, -95, 34, 70, 67, 4, 48, 18, -95, 29, 2, 48, 34, -118, 69, -64, 41, 2, -112, -122, -118, 109, 48, 19, 0, 35, 44, -124, 17, 33, 18, 22, 33, -104, -60, 12, 64, 76, 48, 84, 72, 68, -124, 65, -63, 39, 20, 17, 64, 25, 86, -27, 8, -112, -120, 100, 74, 40, -94, -118, 40, 81, 88, -93, -118, 56, -94, 12, -92, 48, 67, 4, 48, -95, 34, 70, 36, 98, 70, 18, -95, 28, 17, -63, 28, -118, 69, -64, 92, 5, -64, 70, 74, 108, -90, -54, 108, 51, 36, 38, 66, 100, 38, -111, -110, 19, 33, 50, 18, -92, 32, 74, 4, -96, 74, 80, -96, -44, 13, 64, -44, -124, -59, ByteCompanionObject.MIN_VALUE, 88, 5, ByteCompanionObject.MIN_VALUE, 9, 113, 13, Tnaf.POW_2_WIDTH, -47, 12};
    }

    private static void createRandomMask47_26() {
        _randomMask47_26 = new byte[]{100, 74, 40, -94, -118, 40, 81, 88, -93, -118, 56, -94, 12, -92, 48, 67, 4, 48, -95, 34, 70, 36, 98, 70, 18, -95, 28, 17, -63, 28, -118, 69, -64, 92, 5, -64, 70, 74, 108, -90, -54, 108, 51, 36, 38, 66, 100, 38, -111, -110, 19, 33, 50, 18, -92, 32, 74, 4, -96, 74, 80, -96, -44, 13, 64, -44, -124, -59, ByteCompanionObject.MIN_VALUE, 88, 5, ByteCompanionObject.MIN_VALUE, 9, 113, 13, Tnaf.POW_2_WIDTH, -47, 12, 100, 74, 40, 32, -62, 12, 81, 88, -94, 4, 96, 70, 12, -92, 48, ByteCompanionObject.MIN_VALUE, -88, 10, -95, 34, 70, 67, 4, 48, 18, -95, 29, 2, 48, 34, -118, 69, -64, 41, 2, -112, -122, -118, 109, 48, 19, 0, 35, 44, -124, 17, 33, 18, 22, 33, -104, -60, 12, 64, 76, 48, 84, 72, 68, -124, 65, -63, 39, 20, 17, 64, 25, 86, -27, 8, -112, -120, 108, -22, -60, 66, 32, -98};
    }

    private static void createRandomMask47_27() {
        _randomMask47_27 = new byte[]{100, 74, 40, -94, -118, 40, 81, 88, -93, -118, 56, -94, 12, -92, 48, 67, 4, 48, -95, 34, 70, 36, 98, 70, 18, -95, 28, 17, -63, 28, -118, 69, -64, 92, 5, -64, 70, 74, 108, -90, -54, 108, 51, 36, 38, 66, 100, 38, -111, -110, 19, 33, 50, 18, -92, 32, 74, 4, -96, 74, 80, -96, -44, 13, 64, -44, -124, -59, ByteCompanionObject.MIN_VALUE, 88, 5, ByteCompanionObject.MIN_VALUE, 9, 113, 13, Tnaf.POW_2_WIDTH, -47, 12, 70, 74, 108, -90, -54, 108, 51, 36, 38, 66, 100, 38, -111, -110, 19, 33, 50, 18, -92, 32, 74, 4, -96, 74, 80, -96, -44, 13, 64, -44, -124, -59, ByteCompanionObject.MIN_VALUE, 88, 5, ByteCompanionObject.MIN_VALUE, 9, 113, 13, Tnaf.POW_2_WIDTH, -47, 12, 100, 74, 40, -94, -118, 40, 81, 88, -93, -118, 56, -94, 12, -92, 48, 67, 4, 48, -95, 34, 70, 36, 98, 70, 18, -95, 28, 17, -63, 28, -118, 69, -64, 92, 5, -64, -100, Utf8.REPLACEMENT_BYTE, -77, -27, -83, 28};
    }

    private static void createRandomMask47_28() {
        _randomMask47_28 = new byte[]{70, 74, 108, -90, -54, 108, 51, 36, 38, 66, 100, 38, -111, -110, 19, 33, 50, 18, -92, 32, 74, 4, -96, 74, 80, -96, -44, 13, 64, -44, -124, -59, ByteCompanionObject.MIN_VALUE, 88, 5, ByteCompanionObject.MIN_VALUE, 9, 113, 13, Tnaf.POW_2_WIDTH, -47, 12, 100, 74, 40, -94, -118, 40, 81, 88, -93, -118, 56, -94, 12, -92, 48, 67, 4, 48, -95, 34, 70, 36, 98, 70, 18, -95, 28, 17, -63, 28, -118, 69, -64, 92, 5, -64, -100, Utf8.REPLACEMENT_BYTE, -77, -27, -83, 28, 100, 74, 40, -94, -118, 40, 81, 88, -93, -118, 56, -94, 12, -92, 48, 67, 4, 48, -95, 34, 70, 36, 98, 70, 18, -95, 28, 17, -63, 28, -118, 69, -64, 92, 5, -64, 70, 74, 108, -90, -54, 108, 51, 36, 38, 66, 100, 38, -111, -110, 19, 33, 50, 18, -92, 32, 74, 4, -96, 74, 80, -96, -44, 13, 64, -44, -124, -59, ByteCompanionObject.MIN_VALUE, 88, 5, ByteCompanionObject.MIN_VALUE, 9, 113, 13, Tnaf.POW_2_WIDTH, -47, 12, -122, 30, -90, -81, 61, 4};
    }

    private static void createRandomMask47_29() {
        _randomMask47_29 = new byte[]{70, 74, 108, -90, -54, 108, 51, 36, 38, 66, 100, 38, -111, -110, 19, 33, 50, 18, -92, 32, 74, 4, -96, 74, 80, -96, -44, 13, 64, -44, -124, -59, ByteCompanionObject.MIN_VALUE, 88, 5, ByteCompanionObject.MIN_VALUE, 9, 113, 13, Tnaf.POW_2_WIDTH, -47, 12, 100, 74, 40, -94, -118, 40, 81, 88, -93, -118, 56, -94, 12, -92, 48, 67, 4, 48, -95, 34, 70, 36, 98, 70, 18, -95, 28, 17, -63, 28, -118, 69, -64, 92, 5, -64, -100, Utf8.REPLACEMENT_BYTE, -77, -27, -83, 28, 70, 74, 108, -90, -54, 108, 51, 36, 38, 66, 100, 38, -111, -110, 19, 33, 50, 18, -92, 32, 74, 4, -96, 74, 80, -96, -44, 13, 64, -44, -124, -59, ByteCompanionObject.MIN_VALUE, 88, 5, ByteCompanionObject.MIN_VALUE, 9, 113, 13, Tnaf.POW_2_WIDTH, -47, 12, 12, -124, 12, 64, -60, 12, ByteCompanionObject.MIN_VALUE, 112, 7, 0, 112, 6, -96, -120, 72, -124, -120, 72, 5, 64, 50, 3, 32, 50, 67, 2, -126, 40, 34, -126, 26, 1, 80, 21, 1, 80, 96, 39, 0, 112, 7, 0, 20, 56, -95, -118, 24, -96};
    }

    private static void createRandomMask47_3() {
        _randomMask47_3 = new byte[]{-54, -45, 101, 54, 83, 100, -15, 73, 58, -109, -87, 58, 118, 39, -48, 125, 7, -48};
    }

    private static void createRandomMask47_30() {
        _randomMask47_30 = new byte[]{70, 74, 108, -90, -54, 108, 51, 36, 38, 66, 100, 38, -111, -110, 19, 33, 50, 18, -92, 32, 74, 4, -96, 74, 80, -96, -44, 13, 64, -44, -124, -59, ByteCompanionObject.MIN_VALUE, 88, 5, ByteCompanionObject.MIN_VALUE, 9, 113, 13, Tnaf.POW_2_WIDTH, -47, 12, 12, -124, 12, 64, -60, 12, ByteCompanionObject.MIN_VALUE, 112, 7, 0, 112, 6, -96, -120, 72, -124, -120, 72, 5, 64, 50, 3, 32, 50, 67, 2, -126, 40, 34, -126, 26, 1, 80, 21, 1, 80, 96, 39, 0, 112, 7, 0, 20, 56, -95, -118, 24, -96, 70, 74, 108, -90, -54, 108, 51, 36, 38, 66, 100, 38, -111, -110, 19, 33, 50, 18, -92, 32, 74, 4, -96, 74, 80, -96, -44, 13, 64, -44, -124, -59, ByteCompanionObject.MIN_VALUE, 88, 5, ByteCompanionObject.MIN_VALUE, 9, 113, 13, Tnaf.POW_2_WIDTH, -47, 12, 100, 74, 40, -94, -118, 40, 81, 88, -93, -118, 56, -94, 12, -92, 48, 67, 4, 48, -95, 34, 70, 36, 98, 70, 18, -95, 28, 17, -63, 28, -118, 69, -64, 92, 5, -64, -100, Utf8.REPLACEMENT_BYTE, -77, -27, -83, 28, -105, 67, 99, -58, 9, -100};
    }

    private static void createRandomMask47_31() {
        _randomMask47_31 = new byte[]{70, 74, 108, -90, -54, 108, 51, 36, 38, 66, 100, 38, -111, -110, 19, 33, 50, 18, -92, 32, 74, 4, -96, 74, 80, -96, -44, 13, 64, -44, -124, -59, ByteCompanionObject.MIN_VALUE, 88, 5, ByteCompanionObject.MIN_VALUE, 9, 113, 13, Tnaf.POW_2_WIDTH, -47, 12, 12, -124, 12, 64, -60, 12, ByteCompanionObject.MIN_VALUE, 112, 7, 0, 112, 6, -96, -120, 72, -124, -120, 72, 5, 64, 50, 3, 32, 50, 67, 2, -126, 40, 34, -126, 26, 1, 80, 21, 1, 80, 96, 39, 0, 112, 7, 0, 20, 56, -95, -118, 24, -96, 12, -124, 12, 64, -60, 12, ByteCompanionObject.MIN_VALUE, 112, 7, 0, 112, 6, -96, -120, 72, -124, -120, 72, 5, 64, 50, 3, 32, 50, 67, 2, -126, 40, 34, -126, 26, 1, 80, 21, 1, 80, 96, 39, 0, 112, 7, 0, 20, 56, -95, -118, 24, -96, 70, 74, 108, -90, -54, 108, 51, 36, 38, 66, 100, 38, -111, -110, 19, 33, 50, 18, -92, 32, 74, 4, -96, 74, 80, -96, -44, 13, 64, -44, -124, -59, ByteCompanionObject.MIN_VALUE, 88, 5, ByteCompanionObject.MIN_VALUE, 9, 113, 13, Tnaf.POW_2_WIDTH, -47, 12, -6, -39, -11, -2, -36, 20};
    }

    private static void createRandomMask47_32() {
        _randomMask47_32 = new byte[]{12, -124, 12, 64, -60, 12, ByteCompanionObject.MIN_VALUE, 112, 7, 0, 112, 6, -96, -120, 72, -124, -120, 72, 5, 64, 50, 3, 32, 50, 67, 2, -126, 40, 34, -126, 26, 1, 80, 21, 1, 80, 96, 39, 0, 112, 7, 0, 20, 56, -95, -118, 24, -96, 70, 74, 108, -90, -54, 108, 51, 36, 38, 66, 100, 38, -111, -110, 19, 33, 50, 18, -92, 32, 74, 4, -96, 74, 80, -96, -44, 13, 64, -44, -124, -59, ByteCompanionObject.MIN_VALUE, 88, 5, ByteCompanionObject.MIN_VALUE, 9, 113, 13, Tnaf.POW_2_WIDTH, -47, 12, -6, -39, -11, -2, -36, 20, 70, 74, 108, -90, -54, 108, 51, 36, 38, 66, 100, 38, -111, -110, 19, 33, 50, 18, -92, 32, 74, 4, -96, 74, 80, -96, -44, 13, 64, -44, -124, -59, ByteCompanionObject.MIN_VALUE, 88, 5, ByteCompanionObject.MIN_VALUE, 9, 113, 13, Tnaf.POW_2_WIDTH, -47, 12, 12, -124, 12, 64, -60, 12, ByteCompanionObject.MIN_VALUE, 112, 7, 0, 112, 6, -96, -120, 72, -124, -120, 72, 5, 64, 50, 3, 32, 50, 67, 2, -126, 40, 34, -126, 26, 1, 80, 21, 1, 80, 96, 39, 0, 112, 7, 0, 20, 56, -95, -118, 24, -96, -27, 80, 69, 99, -62, -12};
    }

    private static void createRandomMask47_33() {
        _randomMask47_33 = new byte[]{12, -124, 12, 64, -60, 12, ByteCompanionObject.MIN_VALUE, 112, 7, 0, 112, 6, -96, -120, 72, -124, -120, 72, 5, 64, 50, 3, 32, 50, 67, 2, -126, 40, 34, -126, 26, 1, 80, 21, 1, 80, 96, 39, 0, 112, 7, 0, 20, 56, -95, -118, 24, -96, 70, 74, 108, -90, -54, 108, 51, 36, 38, 66, 100, 38, -111, -110, 19, 33, 50, 18, -92, 32, 74, 4, -96, 74, 80, -96, -44, 13, 64, -44, -124, -59, ByteCompanionObject.MIN_VALUE, 88, 5, ByteCompanionObject.MIN_VALUE, 9, 113, 13, Tnaf.POW_2_WIDTH, -47, 12, -6, -39, -11, -2, -36, 20, 12, -124, 12, 64, -60, 12, ByteCompanionObject.MIN_VALUE, 112, 7, 0, 112, 6, -96, -120, 72, -124, -120, 72, 5, 64, 50, 3, 32, 50, 67, 2, -126, 40, 34, -126, 26, 1, 80, 21, 1, 80, 96, 39, 0, 112, 7, 0, 20, 56, -95, -118, 24, -96, 70, 74, 108, -90, -54, 108, 98, 124, -123, -56, 92, -124, -116, 4, -120, 72, -124, -120, 1, 116, 35, 66, 52, 34, 7, -125, 6, 48, 99, 6, -96, ByteCompanionObject.MIN_VALUE, 114, 7, 32, 114, 24, -79, 67, 20, 49, 66, -111, 0, -110, 9, 32, -110, 120, 0, 28, 1, -64, 28};
    }

    private static void createRandomMask47_34() {
        _randomMask47_34 = new byte[]{12, -124, 12, 64, -60, 12, ByteCompanionObject.MIN_VALUE, 112, 7, 0, 112, 6, -96, -120, 72, -124, -120, 72, 5, 64, 50, 3, 32, 50, 67, 2, -126, 40, 34, -126, 26, 1, 80, 21, 1, 80, 96, 39, 0, 112, 7, 0, 20, 56, -95, -118, 24, -96, 70, 74, 108, -90, -54, 108, 98, 124, -123, -56, 92, -124, -116, 4, -120, 72, -124, -120, 1, 116, 35, 66, 52, 34, 7, -125, 6, 48, 99, 6, -96, ByteCompanionObject.MIN_VALUE, 114, 7, 32, 114, 24, -79, 67, 20, 49, 66, -111, 0, -110, 9, 32, -110, 120, 0, 28, 1, -64, 28, 12, -124, 12, 64, -60, 12, ByteCompanionObject.MIN_VALUE, 112, 7, 0, 112, 6, -96, -120, 72, -124, -120, 72, 5, 64, 50, 3, 32, 50, 67, 2, -126, 40, 34, -126, 26, 1, 80, 21, 1, 80, 96, 39, 0, 112, 7, 0, 20, 56, -95, -118, 24, -96, 70, 74, 108, -90, -54, 108, 51, 36, 38, 66, 100, 38, -111, -110, 19, 33, 50, 18, -92, 32, 74, 4, -96, 74, 80, -96, -44, 13, 64, -44, -124, -59, ByteCompanionObject.MIN_VALUE, 88, 5, ByteCompanionObject.MIN_VALUE, 9, 113, 13, Tnaf.POW_2_WIDTH, -47, 12, -6, -39, -11, -2, -36, 20, -17, -69, -90, 35, 92, -66};
    }

    private static void createRandomMask47_35() {
        _randomMask47_35 = new byte[]{12, -124, 12, 64, -60, 12, ByteCompanionObject.MIN_VALUE, 112, 7, 0, 112, 6, -96, -120, 72, -124, -120, 72, 5, 64, 50, 3, 32, 50, 67, 2, -126, 40, 34, -126, 26, 1, 80, 21, 1, 80, 96, 39, 0, 112, 7, 0, 20, 56, -95, -118, 24, -96, 70, 74, 108, -90, -54, 108, 98, 124, -123, -56, 92, -124, -116, 4, -120, 72, -124, -120, 1, 116, 35, 66, 52, 34, 7, -125, 6, 48, 99, 6, -96, ByteCompanionObject.MIN_VALUE, 114, 7, 32, 114, 24, -79, 67, 20, 49, 66, -111, 0, -110, 9, 32, -110, 120, 0, 28, 1, -64, 28, 70, 74, 108, -90, -54, 108, 98, 124, -123, -56, 92, -124, -116, 4, -120, 72, -124, -120, 1, 116, 35, 66, 52, 34, 7, -125, 6, 48, 99, 6, -96, ByteCompanionObject.MIN_VALUE, 114, 7, 32, 114, 24, -79, 67, 20, 49, 66, -111, 0, -110, 9, 32, -110, 120, 0, 28, 1, -64, 28, 12, -124, 12, 64, -60, 12, ByteCompanionObject.MIN_VALUE, 112, 7, 0, 112, 6, -96, -120, 72, -124, -120, 72, 5, 64, 50, 3, 32, 50, 67, 2, -126, 40, 34, -126, 26, 1, 80, 21, 1, 80, 96, 39, 0, 112, 7, 0, 20, 56, -95, -118, 24, -96, -126, 50, 86, 104, -95, 92};
    }

    private static void createRandomMask47_36() {
        _randomMask47_36 = new byte[]{70, 74, 108, -90, -54, 108, 98, 124, -123, -56, 92, -124, -116, 4, -120, 72, -124, -120, 1, 116, 35, 66, 52, 34, 7, -125, 6, 48, 99, 6, -96, ByteCompanionObject.MIN_VALUE, 114, 7, 32, 114, 24, -79, 67, 20, 49, 66, -111, 0, -110, 9, 32, -110, 120, 0, 28, 1, -64, 28, 12, -124, 12, 64, -60, 12, ByteCompanionObject.MIN_VALUE, 112, 7, 0, 112, 6, -96, -120, 72, -124, -120, 72, 5, 64, 50, 3, 32, 50, 67, 2, -126, 40, 34, -126, 26, 1, 80, 21, 1, 80, 96, 39, 0, 112, 7, 0, 20, 56, -95, -118, 24, -96, -126, 50, 86, 104, -95, 92, 12, -124, 12, 64, -60, 12, ByteCompanionObject.MIN_VALUE, 112, 7, 0, 112, 6, -96, -120, 72, -124, -120, 72, 5, 64, 50, 3, 32, 50, 67, 2, -126, 40, 34, -126, 26, 1, 80, 21, 1, 80, 96, 39, 0, 112, 7, 0, 20, 56, -95, -118, 24, -96, 70, 74, 108, -90, -54, 108, 98, 124, -123, -56, 92, -124, -116, 4, -120, 72, -124, -120, 1, 116, 35, 66, 52, 34, 7, -125, 6, 48, 99, 6, -96, ByteCompanionObject.MIN_VALUE, 114, 7, 32, 114, 24, -79, 67, 20, 49, 66, -111, 0, -110, 9, 32, -110, 120, 0, 28, 1, -64, 28, 14, -41, 56, 32, -121, 102};
    }

    private static void createRandomMask47_37() {
        _randomMask47_37 = new byte[]{70, 74, 108, -90, -54, 108, 98, 124, -123, -56, 92, -124, -116, 4, -120, 72, -124, -120, 1, 116, 35, 66, 52, 34, 7, -125, 6, 48, 99, 6, -96, ByteCompanionObject.MIN_VALUE, 114, 7, 32, 114, 24, -79, 67, 20, 49, 66, -111, 0, -110, 9, 32, -110, 120, 0, 28, 1, -64, 28, 12, -124, 12, 64, -60, 12, ByteCompanionObject.MIN_VALUE, 112, 7, 0, 112, 6, -96, -120, 72, -124, -120, 72, 5, 64, 50, 3, 32, 50, 67, 2, -126, 40, 34, -126, 26, 1, 80, 21, 1, 80, 96, 39, 0, 112, 7, 0, 20, 56, -95, -118, 24, -96, -126, 50, 86, 104, -95, 92, 70, 74, 108, -90, -54, 108, 98, 124, -123, -56, 92, -124, -116, 4, -120, 72, -124, -120, 1, 116, 35, 66, 52, 34, 7, -125, 6, 48, 99, 6, -96, ByteCompanionObject.MIN_VALUE, 114, 7, 32, 114, 24, -79, 67, 20, 49, 66, -111, 0, -110, 9, 32, -110, 120, 0, 28, 1, -64, 28, 100, 74, 40, -94, -118, 40, 81, 72, -94, -118, 40, -94, -87, Tnaf.POW_2_WIDTH, 27, 1, -80, 26, 4, -60, -124, 72, 68, -124, -48, 1, 68, 20, 65, 68, -126, 64, 28, 1, -64, 28, 33, 55, 1, 112, 23, 0, 12, 33, 34, 18, 33, 34, 74, 10, -64, -84, 10, -64, 18, -76, 81, 69, 20, 80};
    }

    private static void createRandomMask47_38() {
        _randomMask47_38 = new byte[]{70, 74, 108, -90, -54, 108, 98, 124, -123, -56, 92, -124, -116, 4, -120, 72, -124, -120, 1, 116, 35, 66, 52, 34, 7, -125, 6, 48, 99, 6, -96, ByteCompanionObject.MIN_VALUE, 114, 7, 32, 114, 24, -79, 67, 20, 49, 66, -111, 0, -110, 9, 32, -110, 120, 0, 28, 1, -64, 28, 100, 74, 40, -94, -118, 40, 81, 72, -94, -118, 40, -94, -87, Tnaf.POW_2_WIDTH, 27, 1, -80, 26, 4, -60, -124, 72, 68, -124, -48, 1, 68, 20, 65, 68, -126, 64, 28, 1, -64, 28, 33, 55, 1, 112, 23, 0, 12, 33, 34, 18, 33, 34, 74, 10, -64, -84, 10, -64, 18, -76, 81, 69, 20, 80, 70, 74, 108, -90, -54, 108, 98, 124, -123, -56, 92, -124, -116, 4, -120, 72, -124, -120, 1, 116, 35, 66, 52, 34, 7, -125, 6, 48, 99, 6, -96, ByteCompanionObject.MIN_VALUE, 114, 7, 32, 114, 24, -79, 67, 20, 49, 66, -111, 0, -110, 9, 32, -110, 120, 0, 28, 1, -64, 28, 12, -124, 12, 64, -60, 12, ByteCompanionObject.MIN_VALUE, 112, 7, 0, 112, 6, -96, -120, 72, -124, -120, 72, 5, 64, 50, 3, 32, 50, 67, 2, -126, 40, 34, -126, 26, 1, 80, 21, 1, 80, 96, 39, 0, 112, 7, 0, 20, 56, -95, -118, 24, -96, -126, 50, 86, 104, -95, 92, 123, 71, -91, -34, -102, -44};
    }

    private static void createRandomMask47_39() {
        _randomMask47_39 = new byte[]{70, 74, 108, -90, -54, 108, 98, 124, -123, -56, 92, -124, -116, 4, -120, 72, -124, -120, 1, 116, 35, 66, 52, 34, 7, -125, 6, 48, 99, 6, -96, ByteCompanionObject.MIN_VALUE, 114, 7, 32, 114, 24, -79, 67, 20, 49, 66, -111, 0, -110, 9, 32, -110, 120, 0, 28, 1, -64, 28, 100, 74, 40, -94, -118, 40, 81, 72, -94, -118, 40, -94, -87, Tnaf.POW_2_WIDTH, 27, 1, -80, 26, 4, -60, -124, 72, 68, -124, -48, 1, 68, 20, 65, 68, -126, 64, 28, 1, -64, 28, 33, 55, 1, 112, 23, 0, 12, 33, 34, 18, 33, 34, 74, 10, -64, -84, 10, -64, 18, -76, 81, 69, 20, 80, 100, 74, 40, -94, -118, 40, 81, 72, -94, -118, 40, -94, -87, Tnaf.POW_2_WIDTH, 27, 1, -80, 26, 4, -60, -124, 72, 68, -124, -48, 1, 68, 20, 65, 68, -126, 64, 28, 1, -64, 28, 33, 55, 1, 112, 23, 0, 12, 33, 34, 18, 33, 34, 74, 10, -64, -84, 10, -64, 18, -76, 81, 69, 20, 80, 70, 74, 108, -90, -54, 108, 98, 124, -123, -56, 92, -124, -116, 4, -120, 72, -124, -120, 1, 116, 35, 66, 52, 34, 7, -125, 6, 48, 99, 6, -96, ByteCompanionObject.MIN_VALUE, 114, 7, 32, 114, 24, -79, 67, 20, 49, 66, -111, 0, -110, 9, 32, -110, 120, 0, 28, 1, -64, 28, -37, 74, 123, 49, 69, 42};
    }

    private static void createRandomMask47_4() {
        _randomMask47_4 = new byte[]{-60, -47, 101, 22, 81, 100, 49, 98, -106, 41, 98, -106, 75, 36, 90, 69, -92, 90, 44, -88, -86, -118, -88, -86};
    }

    private static void createRandomMask47_40() {
        _randomMask47_40 = new byte[]{100, 74, 40, -94, -118, 40, 81, 72, -94, -118, 40, -94, -87, Tnaf.POW_2_WIDTH, 27, 1, -80, 26, 4, -60, -124, 72, 68, -124, -48, 1, 68, 20, 65, 68, -126, 64, 28, 1, -64, 28, 33, 55, 1, 112, 23, 0, 12, 33, 34, 18, 33, 34, 74, 10, -64, -84, 10, -64, 18, -76, 81, 69, 20, 80, 70, 74, 108, -90, -54, 108, 98, 124, -123, -56, 92, -124, -116, 4, -120, 72, -124, -120, 1, 116, 35, 66, 52, 34, 7, -125, 6, 48, 99, 6, -96, ByteCompanionObject.MIN_VALUE, 114, 7, 32, 114, 24, -79, 67, 20, 49, 66, -111, 0, -110, 9, 32, -110, 120, 0, 28, 1, -64, 28, -37, 74, 123, 49, 69, 42, 70, 74, 108, -90, -54, 108, 98, 124, -123, -56, 92, -124, -116, 4, -120, 72, -124, -120, 1, 116, 35, 66, 52, 34, 7, -125, 6, 48, 99, 6, -96, ByteCompanionObject.MIN_VALUE, 114, 7, 32, 114, 24, -79, 67, 20, 49, 66, -111, 0, -110, 9, 32, -110, 120, 0, 28, 1, -64, 28, 100, 74, 40, -94, -118, 40, 81, 72, -94, -118, 40, -94, -87, Tnaf.POW_2_WIDTH, 27, 1, -80, 26, 4, -60, -124, 72, 68, -124, -48, 1, 68, 20, 65, 68, -126, 64, 28, 1, -64, 28, 33, 55, 1, 112, 23, 0, 12, 33, 34, 18, 33, 34, 74, 10, -64, -84, 10, -64, 18, -76, 81, 69, 20, 80, -60, -82, 94, 51, -11, 26};
    }

    private static void createRandomMask47_41() {
        _randomMask47_41 = new byte[]{100, 74, 40, -94, -118, 40, 81, 72, -94, -118, 40, -94, -87, Tnaf.POW_2_WIDTH, 27, 1, -80, 26, 4, -60, -124, 72, 68, -124, -48, 1, 68, 20, 65, 68, -126, 64, 28, 1, -64, 28, 33, 55, 1, 112, 23, 0, 12, 33, 34, 18, 33, 34, 74, 10, -64, -84, 10, -64, 18, -76, 81, 69, 20, 80, 70, 74, 108, -90, -54, 108, 98, 124, -123, -56, 92, -124, -116, 4, -120, 72, -124, -120, 1, 116, 35, 66, 52, 34, 7, -125, 6, 48, 99, 6, -96, ByteCompanionObject.MIN_VALUE, 114, 7, 32, 114, 24, -79, 67, 20, 49, 66, -111, 0, -110, 9, 32, -110, 120, 0, 28, 1, -64, 28, -37, 74, 123, 49, 69, 42, 100, 74, 40, -94, -118, 40, 81, 72, -94, -118, 40, -94, -87, Tnaf.POW_2_WIDTH, 27, 1, -80, 26, 4, -60, -124, 72, 68, -124, -48, 1, 68, 20, 65, 68, -126, 64, 28, 1, -64, 28, 33, 55, 1, 112, 23, 0, 12, 33, 34, 18, 33, 34, 74, 10, -64, -84, 10, -64, 18, -76, 81, 69, 20, 80, 70, 74, 108, -90, -54, 108, 51, 36, 38, 66, 100, 38, -103, 2, 18, 33, 34, 18, 5, ByteCompanionObject.MIN_VALUE, 14, 0, -32, 14, ByteCompanionObject.MIN_VALUE, -95, -126, 24, 33, -126, -124, 72, 24, -127, -120, 24, 64, 109, 64, -44, 13, 64, 10, -112, -63, 12, Tnaf.POW_2_WIDTH, -64, 104, 4, -112, 73, 4, -112, Tnaf.POW_2_WIDTH, 49, 33, 18, 17, 32, 48, 88, 5, ByteCompanionObject.MIN_VALUE, 88, 4};
    }

    private static void createRandomMask47_42() {
        _randomMask47_42 = new byte[]{100, 74, 40, -94, -118, 40, 81, 72, -94, -118, 40, -94, -87, Tnaf.POW_2_WIDTH, 27, 1, -80, 26, 4, -60, -124, 72, 68, -124, -48, 1, 68, 20, 65, 68, -126, 64, 28, 1, -64, 28, 33, 55, 1, 112, 23, 0, 12, 33, 34, 18, 33, 34, 74, 10, -64, -84, 10, -64, 18, -76, 81, 69, 20, 80, 70, 74, 108, -90, -54, 108, 51, 36, 38, 66, 100, 38, -103, 2, 18, 33, 34, 18, 5, ByteCompanionObject.MIN_VALUE, 14, 0, -32, 14, ByteCompanionObject.MIN_VALUE, -95, -126, 24, 33, -126, -124, 72, 24, -127, -120, 24, 64, 109, 64, -44, 13, 64, 10, -112, -63, 12, Tnaf.POW_2_WIDTH, -64, 104, 4, -112, 73, 4, -112, Tnaf.POW_2_WIDTH, 49, 33, 18, 17, 32, 48, 88, 5, ByteCompanionObject.MIN_VALUE, 88, 4, 100, 74, 40, -94, -118, 40, 81, 72, -94, -118, 40, -94, -87, Tnaf.POW_2_WIDTH, 27, 1, -80, 26, 4, -60, -124, 72, 68, -124, -48, 1, 68, 20, 65, 68, -126, 64, 28, 1, -64, 28, 33, 55, 1, 112, 23, 0, 12, 33, 34, 18, 33, 34, 74, 10, -64, -84, 10, -64, 18, -76, 81, 69, 20, 80, 70, 74, 108, -90, -54, 108, 98, 124, -123, -56, 92, -124, -116, 4, -120, 72, -124, -120, 1, 116, 35, 66, 52, 34, 7, -125, 6, 48, 99, 6, -96, ByteCompanionObject.MIN_VALUE, 114, 7, 32, 114, 24, -79, 67, 20, 49, 66, -111, 0, -110, 9, 32, -110, 120, 0, 28, 1, -64, 28, -37, 74, 123, 49, 69, 42, 60, -80, 54, 59, 20, -94};
    }

    private static void createRandomMask47_43() {
        _randomMask47_43 = new byte[]{100, 74, 40, -94, -118, 40, 81, 72, -94, -118, 40, -94, -87, Tnaf.POW_2_WIDTH, 27, 1, -80, 26, 4, -60, -124, 72, 68, -124, -48, 1, 68, 20, 65, 68, -126, 64, 28, 1, -64, 28, 33, 55, 1, 112, 23, 0, 12, 33, 34, 18, 33, 34, 74, 10, -64, -84, 10, -64, 18, -76, 81, 69, 20, 80, 70, 74, 108, -90, -54, 108, 51, 36, 38, 66, 100, 38, -103, 2, 18, 33, 34, 18, 5, ByteCompanionObject.MIN_VALUE, 14, 0, -32, 14, ByteCompanionObject.MIN_VALUE, -95, -126, 24, 33, -126, -124, 72, 24, -127, -120, 24, 64, 109, 64, -44, 13, 64, 10, -112, -63, 12, Tnaf.POW_2_WIDTH, -64, 104, 4, -112, 73, 4, -112, Tnaf.POW_2_WIDTH, 49, 33, 18, 17, 32, 48, 88, 5, ByteCompanionObject.MIN_VALUE, 88, 4, 70, 74, 108, -90, -54, 108, 51, 36, 38, 66, 100, 38, -103, 2, 18, 33, 34, 18, 5, ByteCompanionObject.MIN_VALUE, 14, 0, -32, 14, ByteCompanionObject.MIN_VALUE, -95, -126, 24, 33, -126, -124, 72, 24, -127, -120, 24, 64, 109, 64, -44, 13, 64, 10, -112, -63, 12, Tnaf.POW_2_WIDTH, -64, 104, 4, -112, 73, 4, -112, Tnaf.POW_2_WIDTH, 49, 33, 18, 17, 32, 48, 88, 5, ByteCompanionObject.MIN_VALUE, 88, 4, 100, 74, 40, -94, -118, 40, 81, 72, -94, -118, 40, -94, -87, Tnaf.POW_2_WIDTH, 27, 1, -80, 26, 4, -60, -124, 72, 68, -124, -48, 1, 68, 20, 65, 68, -126, 64, 28, 1, -64, 28, 33, 55, 1, 112, 23, 0, 12, 33, 34, 18, 33, 34, 74, 10, -64, -84, 10, -64, 18, -76, 81, 69, 20, 80, -22, -115, 26, 53, 85, -36};
    }

    private static void createRandomMask47_44() {
        _randomMask47_44 = new byte[]{70, 74, 108, -90, -54, 108, 51, 36, 38, 66, 100, 38, -103, 2, 18, 33, 34, 18, 5, ByteCompanionObject.MIN_VALUE, 14, 0, -32, 14, ByteCompanionObject.MIN_VALUE, -95, -126, 24, 33, -126, -124, 72, 24, -127, -120, 24, 64, 109, 64, -44, 13, 64, 10, -112, -63, 12, Tnaf.POW_2_WIDTH, -64, 104, 4, -112, 73, 4, -112, Tnaf.POW_2_WIDTH, 49, 33, 18, 17, 32, 48, 88, 5, ByteCompanionObject.MIN_VALUE, 88, 4, 100, 74, 40, -94, -118, 40, 81, 72, -94, -118, 40, -94, -87, Tnaf.POW_2_WIDTH, 27, 1, -80, 26, 4, -60, -124, 72, 68, -124, -48, 1, 68, 20, 65, 68, -126, 64, 28, 1, -64, 28, 33, 55, 1, 112, 23, 0, 12, 33, 34, 18, 33, 34, 74, 10, -64, -84, 10, -64, 18, -76, 81, 69, 20, 80, -22, -115, 26, 53, 85, -36, 100, 74, 40, -94, -118, 40, 81, 72, -94, -118, 40, -94, -87, Tnaf.POW_2_WIDTH, 27, 1, -80, 26, 4, -60, -124, 72, 68, -124, -48, 1, 68, 20, 65, 68, -126, 64, 28, 1, -64, 28, 33, 55, 1, 112, 23, 0, 12, 33, 34, 18, 33, 34, 74, 10, -64, -84, 10, -64, 18, -76, 81, 69, 20, 80, 70, 74, 108, -90, -54, 108, 51, 36, 38, 66, 100, 38, -103, 2, 18, 33, 34, 18, 5, ByteCompanionObject.MIN_VALUE, 14, 0, -32, 14, ByteCompanionObject.MIN_VALUE, -95, -126, 24, 33, -126, -124, 72, 24, -127, -120, 24, 64, 109, 64, -44, 13, 64, 10, -112, -63, 12, Tnaf.POW_2_WIDTH, -64, 104, 4, -112, 73, 4, -112, Tnaf.POW_2_WIDTH, 49, 33, 18, 17, 32, 48, 88, 5, ByteCompanionObject.MIN_VALUE, 88, 4, -44, -118, -44, -45, Utf8.REPLACEMENT_BYTE, -26};
    }

    private static void createRandomMask47_45() {
        _randomMask47_45 = new byte[]{70, 74, 108, -90, -54, 108, 51, 36, 38, 66, 100, 38, -103, 2, 18, 33, 34, 18, 5, ByteCompanionObject.MIN_VALUE, 14, 0, -32, 14, ByteCompanionObject.MIN_VALUE, -95, -126, 24, 33, -126, -124, 72, 24, -127, -120, 24, 64, 109, 64, -44, 13, 64, 10, -112, -63, 12, Tnaf.POW_2_WIDTH, -64, 104, 4, -112, 73, 4, -112, Tnaf.POW_2_WIDTH, 49, 33, 18, 17, 32, 48, 88, 5, ByteCompanionObject.MIN_VALUE, 88, 4, 100, 74, 40, -94, -118, 40, 81, 72, -94, -118, 40, -94, -87, Tnaf.POW_2_WIDTH, 27, 1, -80, 26, 4, -60, -124, 72, 68, -124, -48, 1, 68, 20, 65, 68, -126, 64, 28, 1, -64, 28, 33, 55, 1, 112, 23, 0, 12, 33, 34, 18, 33, 34, 74, 10, -64, -84, 10, -64, 18, -76, 81, 69, 20, 80, -22, -115, 26, 53, 85, -36, 70, 74, 108, -90, -54, 108, 51, 36, 38, 66, 100, 38, -103, 2, 18, 33, 34, 18, 5, ByteCompanionObject.MIN_VALUE, 14, 0, -32, 14, ByteCompanionObject.MIN_VALUE, -95, -126, 24, 33, -126, -124, 72, 24, -127, -120, 24, 64, 109, 64, -44, 13, 64, 10, -112, -63, 12, Tnaf.POW_2_WIDTH, -64, 104, 4, -112, 73, 4, -112, Tnaf.POW_2_WIDTH, 49, 33, 18, 17, 32, 48, 88, 5, ByteCompanionObject.MIN_VALUE, 88, 4, 70, 66, 12, 32, -62, 12, 51, 32, 70, 4, 96, 70, -103, 8, 10, ByteCompanionObject.MIN_VALUE, -88, 10, 5, -124, 48, 67, 4, 48, ByteCompanionObject.MIN_VALUE, -80, 35, 2, 48, 34, -124, 66, -112, 41, 2, -112, 64, 115, 1, 48, 19, 0, 10, -127, 18, 17, 33, 18, 104, 12, 64, -60, 12, 64, Tnaf.POW_2_WIDTH, 36, -124, 72, 68, -124, 48, 81, 65, 20, 17, 64, 95, 80, -119, 8, -112, -120};
    }

    private static void createRandomMask47_46() {
        _randomMask47_46 = new byte[]{70, 74, 108, -90, -54, 108, 51, 36, 38, 66, 100, 38, -103, 2, 18, 33, 34, 18, 5, ByteCompanionObject.MIN_VALUE, 14, 0, -32, 14, ByteCompanionObject.MIN_VALUE, -95, -126, 24, 33, -126, -124, 72, 24, -127, -120, 24, 64, 109, 64, -44, 13, 64, 10, -112, -63, 12, Tnaf.POW_2_WIDTH, -64, 104, 4, -112, 73, 4, -112, Tnaf.POW_2_WIDTH, 49, 33, 18, 17, 32, 48, 88, 5, ByteCompanionObject.MIN_VALUE, 88, 4, 70, 66, 12, 32, -62, 12, 51, 32, 70, 4, 96, 70, -103, 8, 10, ByteCompanionObject.MIN_VALUE, -88, 10, 5, -124, 48, 67, 4, 48, ByteCompanionObject.MIN_VALUE, -80, 35, 2, 48, 34, -124, 66, -112, 41, 2, -112, 64, 115, 1, 48, 19, 0, 10, -127, 18, 17, 33, 18, 104, 12, 64, -60, 12, 64, Tnaf.POW_2_WIDTH, 36, -124, 72, 68, -124, 48, 81, 65, 20, 17, 64, 95, 80, -119, 8, -112, -120, 70, 74, 108, -90, -54, 108, 51, 36, 38, 66, 100, 38, -103, 2, 18, 33, 34, 18, 5, ByteCompanionObject.MIN_VALUE, 14, 0, -32, 14, ByteCompanionObject.MIN_VALUE, -95, -126, 24, 33, -126, -124, 72, 24, -127, -120, 24, 64, 109, 64, -44, 13, 64, 10, -112, -63, 12, Tnaf.POW_2_WIDTH, -64, 104, 4, -112, 73, 4, -112, Tnaf.POW_2_WIDTH, 49, 33, 18, 17, 32, 48, 88, 5, ByteCompanionObject.MIN_VALUE, 88, 4, 100, 74, 40, -94, -118, 40, 81, 72, -94, -118, 40, -94, -87, Tnaf.POW_2_WIDTH, 27, 1, -80, 26, 4, -60, -124, 72, 68, -124, -48, 1, 68, 20, 65, 68, -126, 64, 28, 1, -64, 28, 33, 55, 1, 112, 23, 0, 12, 33, 34, 18, 33, 34, 74, 10, -64, -84, 10, -64, 18, -76, 81, 69, 20, 80, -22, -115, 26, 53, 85, -36, 55, -99, -49, -32, -28, 32};
    }

    private static void createRandomMask47_47() {
        _randomMask47_47 = new byte[]{70, 74, 108, -90, -54, 108, 51, 36, 38, 66, 100, 38, -103, 2, 18, 33, 34, 18, 5, ByteCompanionObject.MIN_VALUE, 14, 0, -32, 14, ByteCompanionObject.MIN_VALUE, -95, -126, 24, 33, -126, -124, 72, 24, -127, -120, 24, 64, 109, 64, -44, 13, 64, 10, -112, -63, 12, Tnaf.POW_2_WIDTH, -64, 104, 4, -112, 73, 4, -112, Tnaf.POW_2_WIDTH, 49, 33, 18, 17, 32, 48, 88, 5, ByteCompanionObject.MIN_VALUE, 88, 4, 70, 66, 12, 32, -62, 12, 51, 32, 70, 4, 96, 70, -103, 8, 10, ByteCompanionObject.MIN_VALUE, -88, 10, 5, -124, 48, 67, 4, 48, ByteCompanionObject.MIN_VALUE, -80, 35, 2, 48, 34, -124, 66, -112, 41, 2, -112, 64, 115, 1, 48, 19, 0, 10, -127, 18, 17, 33, 18, 104, 12, 64, -60, 12, 64, Tnaf.POW_2_WIDTH, 36, -124, 72, 68, -124, 48, 81, 65, 20, 17, 64, 95, 80, -119, 8, -112, -120, 70, 74, 108, 32, -62, 12, 51, 36, 38, 4, 96, 70, -103, 2, 18, ByteCompanionObject.MIN_VALUE, -88, 10, 5, ByteCompanionObject.MIN_VALUE, 14, 67, 4, 48, ByteCompanionObject.MIN_VALUE, -95, -125, 2, 48, 34, -124, 72, 24, 41, 2, -112, 64, 109, 65, 48, 19, 0, 10, -112, -64, 17, 33, 18, 104, 4, -112, -60, 12, 64, Tnaf.POW_2_WIDTH, 49, 32, 72, 68, -124, 48, 88, 5, 20, 17, 64, 70, 66, 13, 8, -112, -120, 51, 32, 70, -90, -54, 108, -103, 8, 10, 66, 100, 38, 5, -124, 48, 33, 34, 18, ByteCompanionObject.MIN_VALUE, -80, 34, 0, -32, 14, -124, 66, -112, 24, 33, -126, 64, 115, 0, -127, -120, 24, 10, -127, 18, -44, 13, 64, 104, 12, 65, 12, Tnaf.POW_2_WIDTH, -64, Tnaf.POW_2_WIDTH, 36, -124, 73, 4, -112, 48, 81, 65, 18, 17, 32, 95, 80, -119, ByteCompanionObject.MIN_VALUE, 88, 4, 31, 47, 99, Tnaf.POW_2_WIDTH, 100, -78};
    }

    private static void createRandomMask47_5() {
        _randomMask47_5 = new byte[]{-58, -54, 108, -90, -54, 108, 99, 108, -106, -55, 108, -106, 29, -95, -36, 29, -63, -36, -83, 85, 57, 83, -107, 56, -78, -73, 7, 112, 119, 6};
    }

    private static void createRandomMask47_6() {
        _randomMask47_6 = new byte[]{100, 74, 41, -94, -102, 40, 81, 88, -94, -118, 104, -90, 12, -92, 48, 69, -92, 90, -95, 34, 70, 45, -126, -40, 18, -95, 28, 23, 65, 116, -118, 69, -63, -47, 29, Tnaf.POW_2_WIDTH};
    }

    private static void createRandomMask47_7() {
        _randomMask47_7 = new byte[]{70, 74, 109, -90, -54, 108, 51, 36, 38, 74, 100, -90, -111, -110, 18, 97, -90, 10, -92, 32, 74, 12, -112, -40, 80, -96, -43, -127, 112, 54, -124, -59, ByteCompanionObject.MIN_VALUE, 85, 69, 84, 9, 113, 13, 80, -99, 8};
    }

    private static void createRandomMask47_8() {
        _randomMask47_8 = new byte[]{12, -124, 13, 2, -64, 44, ByteCompanionObject.MIN_VALUE, 112, 6, ByteCompanionObject.MIN_VALUE, 120, 6, -96, -120, 72, 33, 34, 18, 5, 64, 50, 12, -96, -54, 67, 2, -126, 64, -107, 8, 26, 1, 81, 21, 65, 84, 96, 39, 0, 102, 6, 96, 20, 56, -96, -103, 9, -112};
    }

    private static void createRandomMask47_9() {
        _randomMask47_9 = new byte[]{70, 74, 109, -90, -54, 108, 98, 124, -124, -56, 76, -124, -116, 4, -120, 48, -125, -120, 1, 116, 35, 64, -108, 8, 7, -125, 7, 2, 112, 38, -96, ByteCompanionObject.MIN_VALUE, 114, 69, 68, 84, 24, -79, 66, Tnaf.POW_2_WIDTH, -31, 14, -111, 0, -110, 9, 32, -110, 120, 0, 28, 3, ByteCompanionObject.MIN_VALUE, 56};
    }

    private static void createRandomMask48_1() {
        _randomMask48_1 = new byte[]{-1, -1, -1, -1, -1, -1};
    }

    private static void createRandomMask48_10() {
        _randomMask48_10 = new byte[]{17, 69, 20, 17, 69, 20, 69, 52, 83, 69, 52, 83, 0, 72, 5, 0, 72, 5, Tnaf.POW_2_WIDTH, -125, 9, Tnaf.POW_2_WIDTH, -125, 9, 74, 20, -95, 74, 20, -95, 64, -92, 10, 64, -92, 10, -96, 106, 2, -96, 106, 2, -120, ByteCompanionObject.MIN_VALUE, -116, -120, ByteCompanionObject.MIN_VALUE, -116, -122, 8, 96, -122, 8, 96, 84, 13, 64, 84, 13, 64};
    }

    private static void createRandomMask48_11() {
        _randomMask48_11 = new byte[]{83, 101, 52, 83, 101, 52, -96, 50, 17, -96, 50, 17, 21, 17, 65, 21, 17, 65, 3, 80, 21, 3, 80, 21, -116, -120, -56, -116, -120, -56, 40, -126, -120, 40, -126, -120, 8, 72, -124, 8, 72, -124, -103, 1, -112, -103, 1, -112, 34, -110, 41, 34, -110, 41, 70, 4, 96, 70, 4, 96, -116, 44, 2, -116, 44, 2};
    }

    private static void createRandomMask48_12() {
        _randomMask48_12 = new byte[]{Tnaf.POW_2_WIDTH, 97, 6, Tnaf.POW_2_WIDTH, 97, 6, 2, 48, 35, 2, 48, 35, 64, 84, 5, 64, 84, 5, 33, -126, 24, 33, -126, 24, -127, 24, 17, -127, 24, 17, 20, -127, 72, 20, -127, 72, -104, 9, ByteCompanionObject.MIN_VALUE, -104, 9, ByteCompanionObject.MIN_VALUE, 8, -112, -119, 8, -112, -119, 98, 6, 32, 98, 6, 32, 36, 34, 66, 36, 34, 66, -118, 8, -96, -118, 8, -96, -124, 72, 68, -124, 72, 68};
    }

    private static void createRandomMask48_13() {
        _randomMask48_13 = new byte[]{81, 69, 20, 81, 69, 20, -59, 28, 81, -59, 28, 81, 33, -126, 24, 33, -126, 24, 18, 49, 35, 18, 49, 35, 8, -32, -114, 8, -32, -114, 46, 2, -32, 46, 2, -32, 83, 101, 54, 83, 101, 54, 33, 50, 19, 33, 50, 19, -112, -103, 9, -112, -103, 9, 2, 80, 37, 2, 80, 37, 6, -96, 106, 6, -96, 106, 44, 2, -64, 44, 2, -64, -120, 104, -122, -120, 104, -122};
    }

    private static void createRandomMask48_14() {
        _randomMask48_14 = new byte[]{83, 101, 54, 83, 101, 54, 33, 50, 19, 33, 50, 19, -112, -103, 9, -112, -103, 9, 2, 80, 37, 2, 80, 37, 6, -96, 106, 6, -96, 106, 44, 2, -64, 44, 2, -64, -120, 104, -122, -120, 104, -122, 81, 69, 20, 81, 69, 20, -59, 28, 81, -59, 28, 81, 33, -126, 24, 33, -126, 24, 18, 49, 35, 18, 49, 35, 8, -32, -114, 8, -32, -114, 46, 2, -32, 46, 2, -32, -14, -42, -114, -14, -42, -114};
    }

    private static void createRandomMask48_15() {
        _randomMask48_15 = new byte[]{83, 101, 54, 83, 101, 54, 33, 50, 19, 33, 50, 19, -112, -103, 9, -112, -103, 9, 2, 80, 37, 2, 80, 37, 6, -96, 106, 6, -96, 106, 44, 2, -64, 44, 2, -64, -120, 104, -122, -120, 104, -122, 32, 98, 6, 32, 98, 6, ByteCompanionObject.MIN_VALUE, 56, 3, ByteCompanionObject.MIN_VALUE, 56, 3, 66, 68, 36, 66, 68, 36, 1, -112, 25, 1, -112, 25, 20, 17, 65, 20, 17, 65, 10, ByteCompanionObject.MIN_VALUE, -88, 10, ByteCompanionObject.MIN_VALUE, -88, 56, 3, ByteCompanionObject.MIN_VALUE, 56, 3, ByteCompanionObject.MIN_VALUE, -59, 12, 80, -59, 12, 80};
    }

    private static void createRandomMask48_16() {
        _randomMask48_16 = new byte[]{32, 98, 6, 32, 98, 6, ByteCompanionObject.MIN_VALUE, 56, 3, ByteCompanionObject.MIN_VALUE, 56, 3, 66, 68, 36, 66, 68, 36, 1, -112, 25, 1, -112, 25, 20, 17, 65, 20, 17, 65, 10, ByteCompanionObject.MIN_VALUE, -88, 10, ByteCompanionObject.MIN_VALUE, -88, 56, 3, ByteCompanionObject.MIN_VALUE, 56, 3, ByteCompanionObject.MIN_VALUE, -59, 12, 80, -59, 12, 80, 83, 101, 54, 83, 101, 54, 33, 50, 19, 33, 50, 19, -112, -103, 9, -112, -103, 9, 2, 80, 37, 2, 80, 37, 6, -96, 106, 6, -96, 106, 44, 2, -64, 44, 2, -64, -120, 104, -122, -120, 104, -122, -1, 110, 10, -1, 110, 10};
    }

    private static void createRandomMask48_17() {
        _randomMask48_17 = new byte[]{32, 98, 6, 32, 98, 6, ByteCompanionObject.MIN_VALUE, 56, 3, ByteCompanionObject.MIN_VALUE, 56, 3, 66, 68, 36, 66, 68, 36, 1, -112, 25, 1, -112, 25, 20, 17, 65, 20, 17, 65, 10, ByteCompanionObject.MIN_VALUE, -88, 10, ByteCompanionObject.MIN_VALUE, -88, 56, 3, ByteCompanionObject.MIN_VALUE, 56, 3, ByteCompanionObject.MIN_VALUE, -59, 12, 80, -59, 12, 80, 83, 101, 54, 83, 101, 54, -28, 46, 66, -28, 46, 66, 36, 66, 68, 36, 66, 68, -95, 26, 17, -95, 26, 17, 24, 49, -125, 24, 49, -125, 3, -112, 57, 3, -112, 57, -118, 24, -95, -118, 24, -95, 4, -112, 73, 4, -112, 73, 0, -32, 14, 0, -32, 14};
    }

    private static void createRandomMask48_18() {
        _randomMask48_18 = new byte[]{83, 101, 54, 83, 101, 54, -28, 46, 66, -28, 46, 66, 36, 66, 68, 36, 66, 68, -95, 26, 17, -95, 26, 17, 24, 49, -125, 24, 49, -125, 3, -112, 57, 3, -112, 57, -118, 24, -95, -118, 24, -95, 4, -112, 73, 4, -112, 73, 0, -32, 14, 0, -32, 14, 32, 98, 6, 32, 98, 6, ByteCompanionObject.MIN_VALUE, 56, 3, ByteCompanionObject.MIN_VALUE, 56, 3, 66, 68, 36, 66, 68, 36, 1, -112, 25, 1, -112, 25, 20, 17, 65, 20, 17, 65, 10, ByteCompanionObject.MIN_VALUE, -88, 10, ByteCompanionObject.MIN_VALUE, -88, 56, 3, ByteCompanionObject.MIN_VALUE, 56, 3, ByteCompanionObject.MIN_VALUE, -59, 12, 80, -59, 12, 80, 52, 80, -82, 52, 80, -82};
    }

    private static void createRandomMask48_19() {
        _randomMask48_19 = new byte[]{83, 101, 54, 83, 101, 54, -28, 46, 66, -28, 46, 66, 36, 66, 68, 36, 66, 68, -95, 26, 17, -95, 26, 17, 24, 49, -125, 24, 49, -125, 3, -112, 57, 3, -112, 57, -118, 24, -95, -118, 24, -95, 4, -112, 73, 4, -112, 73, 0, -32, 14, 0, -32, 14, 81, 69, 20, 81, 69, 20, 69, 20, 81, 69, 20, 81, ByteCompanionObject.MIN_VALUE, -40, 13, ByteCompanionObject.MIN_VALUE, -40, 13, 36, 34, 66, 36, 34, 66, 10, 32, -94, 10, 32, -94, 0, -32, 14, 0, -32, 14, -72, 11, ByteCompanionObject.MIN_VALUE, -72, 11, ByteCompanionObject.MIN_VALUE, 9, Tnaf.POW_2_WIDTH, -111, 9, Tnaf.POW_2_WIDTH, -111, 86, 5, 96, 86, 5, 96, -94, -118, 40, -94, -118, 40};
    }

    private static void createRandomMask48_2() {
        _randomMask48_2 = new byte[]{-20, -50, -52, -20, -50, -52, -109, -71, 59, -109, -71, 59};
    }

    private static void createRandomMask48_20() {
        _randomMask48_20 = new byte[]{81, 69, 20, 81, 69, 20, 69, 20, 81, 69, 20, 81, ByteCompanionObject.MIN_VALUE, -40, 13, ByteCompanionObject.MIN_VALUE, -40, 13, 36, 34, 66, 36, 34, 66, 10, 32, -94, 10, 32, -94, 0, -32, 14, 0, -32, 14, -72, 11, ByteCompanionObject.MIN_VALUE, -72, 11, ByteCompanionObject.MIN_VALUE, 9, Tnaf.POW_2_WIDTH, -111, 9, Tnaf.POW_2_WIDTH, -111, 86, 5, 96, 86, 5, 96, -94, -118, 40, -94, -118, 40, 83, 101, 54, 83, 101, 54, -28, 46, 66, -28, 46, 66, 36, 66, 68, 36, 66, 68, -95, 26, 17, -95, 26, 17, 24, 49, -125, 24, 49, -125, 3, -112, 57, 3, -112, 57, -118, 24, -95, -118, 24, -95, 4, -112, 73, 4, -112, 73, 0, -32, 14, 0, -32, 14, -104, -94, -107, -104, -94, -107};
    }

    private static void createRandomMask48_21() {
        _randomMask48_21 = new byte[]{81, 69, 20, 81, 69, 20, 69, 20, 81, 69, 20, 81, ByteCompanionObject.MIN_VALUE, -40, 13, ByteCompanionObject.MIN_VALUE, -40, 13, 36, 34, 66, 36, 34, 66, 10, 32, -94, 10, 32, -94, 0, -32, 14, 0, -32, 14, -72, 11, ByteCompanionObject.MIN_VALUE, -72, 11, ByteCompanionObject.MIN_VALUE, 9, Tnaf.POW_2_WIDTH, -111, 9, Tnaf.POW_2_WIDTH, -111, 86, 5, 96, 86, 5, 96, -94, -118, 40, -94, -118, 40, 83, 101, 54, 83, 101, 54, 33, 50, 19, 33, 50, 19, Tnaf.POW_2_WIDTH, -111, 9, Tnaf.POW_2_WIDTH, -111, 9, 0, 112, 7, 0, 112, 7, 12, Tnaf.POW_2_WIDTH, -63, 12, Tnaf.POW_2_WIDTH, -63, 64, -60, 12, 64, -60, 12, 106, 6, -96, 106, 6, -96, -122, 8, 96, -122, 8, 96, 36, -126, 72, 36, -126, 72, -119, 8, -112, -119, 8, -112, -64, 44, 2, -64, 44, 2};
    }

    private static void createRandomMask48_22() {
        _randomMask48_22 = new byte[]{83, 101, 54, 83, 101, 54, 33, 50, 19, 33, 50, 19, Tnaf.POW_2_WIDTH, -111, 9, Tnaf.POW_2_WIDTH, -111, 9, 0, 112, 7, 0, 112, 7, 12, Tnaf.POW_2_WIDTH, -63, 12, Tnaf.POW_2_WIDTH, -63, 64, -60, 12, 64, -60, 12, 106, 6, -96, 106, 6, -96, -122, 8, 96, -122, 8, 96, 36, -126, 72, 36, -126, 72, -119, 8, -112, -119, 8, -112, -64, 44, 2, -64, 44, 2, 81, 69, 20, 81, 69, 20, 69, 20, 81, 69, 20, 81, ByteCompanionObject.MIN_VALUE, -40, 13, ByteCompanionObject.MIN_VALUE, -40, 13, 36, 34, 66, 36, 34, 66, 10, 32, -94, 10, 32, -94, 0, -32, 14, 0, -32, 14, -72, 11, ByteCompanionObject.MIN_VALUE, -72, 11, ByteCompanionObject.MIN_VALUE, 9, Tnaf.POW_2_WIDTH, -111, 9, Tnaf.POW_2_WIDTH, -111, 86, 5, 96, 86, 5, 96, -94, -118, 40, -94, -118, 40, 26, -86, -18, 26, -86, -18};
    }

    private static void createRandomMask48_23() {
        _randomMask48_23 = new byte[]{83, 101, 54, 83, 101, 54, 33, 50, 19, 33, 50, 19, Tnaf.POW_2_WIDTH, -111, 9, Tnaf.POW_2_WIDTH, -111, 9, 0, 112, 7, 0, 112, 7, 12, Tnaf.POW_2_WIDTH, -63, 12, Tnaf.POW_2_WIDTH, -63, 64, -60, 12, 64, -60, 12, 106, 6, -96, 106, 6, -96, -122, 8, 96, -122, 8, 96, 36, -126, 72, 36, -126, 72, -119, 8, -112, -119, 8, -112, -64, 44, 2, -64, 44, 2, Tnaf.POW_2_WIDTH, 97, 6, Tnaf.POW_2_WIDTH, 97, 6, 2, 48, 35, 2, 48, 35, 64, 84, 5, 64, 84, 5, 33, -126, 24, 33, -126, 24, -127, 24, 17, -127, 24, 17, 20, -127, 72, 20, -127, 72, -104, 9, ByteCompanionObject.MIN_VALUE, -104, 9, ByteCompanionObject.MIN_VALUE, 8, -112, -119, 8, -112, -119, 98, 6, 32, 98, 6, 32, 36, 34, 66, 36, 34, 66, -118, 8, -96, -118, 8, -96, -124, 72, 68, -124, 72, 68};
    }

    private static void createRandomMask48_24() {
        _randomMask48_24 = new byte[]{Tnaf.POW_2_WIDTH, 97, 6, Tnaf.POW_2_WIDTH, 97, 6, 2, 48, 35, 2, 48, 35, 64, 84, 5, 64, 84, 5, 33, -126, 24, 33, -126, 24, -127, 24, 17, -127, 24, 17, 20, -127, 72, 20, -127, 72, -104, 9, ByteCompanionObject.MIN_VALUE, -104, 9, ByteCompanionObject.MIN_VALUE, 8, -112, -119, 8, -112, -119, 98, 6, 32, 98, 6, 32, 36, 34, 66, 36, 34, 66, -118, 8, -96, -118, 8, -96, -124, 72, 68, -124, 72, 68, 83, 101, 54, 83, 101, 54, 33, 50, 19, 33, 50, 19, Tnaf.POW_2_WIDTH, -111, 9, Tnaf.POW_2_WIDTH, -111, 9, 0, 112, 7, 0, 112, 7, 12, Tnaf.POW_2_WIDTH, -63, 12, Tnaf.POW_2_WIDTH, -63, 64, -60, 12, 64, -60, 12, 106, 6, -96, 106, 6, -96, -122, 8, 96, -122, 8, 96, 36, -126, 72, 36, -126, 72, -119, 8, -112, -119, 8, -112, -64, 44, 2, -64, 44, 2, -120, 50, 89, -120, 50, 89};
    }

    private static void createRandomMask48_25() {
        _randomMask48_25 = new byte[]{Tnaf.POW_2_WIDTH, 97, 6, Tnaf.POW_2_WIDTH, 97, 6, 2, 48, 35, 2, 48, 35, 64, 84, 5, 64, 84, 5, 33, -126, 24, 33, -126, 24, -127, 24, 17, -127, 24, 17, 20, -127, 72, 20, -127, 72, -104, 9, ByteCompanionObject.MIN_VALUE, -104, 9, ByteCompanionObject.MIN_VALUE, 8, -112, -119, 8, -112, -119, 98, 6, 32, 98, 6, 32, 36, 34, 66, 36, 34, 66, -118, 8, -96, -118, 8, -96, -124, 72, 68, -124, 72, 68, 81, 69, 20, 81, 69, 20, -59, 28, 81, -59, 28, 81, 33, -126, 24, 33, -126, 24, 18, 49, 35, 18, 49, 35, 8, -32, -114, 8, -32, -114, 46, 2, -32, 46, 2, -32, 83, 101, 54, 83, 101, 54, 33, 50, 19, 33, 50, 19, -112, -103, 9, -112, -103, 9, 2, 80, 37, 2, 80, 37, 6, -96, 106, 6, -96, 106, 44, 2, -64, 44, 2, -64, -120, 104, -122, -120, 104, -122};
    }

    private static void createRandomMask48_26() {
        _randomMask48_26 = new byte[]{81, 69, 20, 81, 69, 20, -59, 28, 81, -59, 28, 81, 33, -126, 24, 33, -126, 24, 18, 49, 35, 18, 49, 35, 8, -32, -114, 8, -32, -114, 46, 2, -32, 46, 2, -32, 83, 101, 54, 83, 101, 54, 33, 50, 19, 33, 50, 19, -112, -103, 9, -112, -103, 9, 2, 80, 37, 2, 80, 37, 6, -96, 106, 6, -96, 106, 44, 2, -64, 44, 2, -64, -120, 104, -122, -120, 104, -122, Tnaf.POW_2_WIDTH, 97, 6, Tnaf.POW_2_WIDTH, 97, 6, 2, 48, 35, 2, 48, 35, 64, 84, 5, 64, 84, 5, 33, -126, 24, 33, -126, 24, -127, 24, 17, -127, 24, 17, 20, -127, 72, 20, -127, 72, -104, 9, ByteCompanionObject.MIN_VALUE, -104, 9, ByteCompanionObject.MIN_VALUE, 8, -112, -119, 8, -112, -119, 98, 6, 32, 98, 6, 32, 36, 34, 66, 36, 34, 66, -118, 8, -96, -118, 8, -96, -124, 72, 68, -124, 72, 68, 62, 32, 121, -27, 85, 112};
    }

    private static void createRandomMask48_27() {
        _randomMask48_27 = new byte[]{81, 69, 20, 81, 69, 20, -59, 28, 81, -59, 28, 81, 33, -126, 24, 33, -126, 24, 18, 49, 35, 18, 49, 35, 8, -32, -114, 8, -32, -114, 46, 2, -32, 46, 2, -32, 83, 101, 54, 83, 101, 54, 33, 50, 19, 33, 50, 19, -112, -103, 9, -112, -103, 9, 2, 80, 37, 2, 80, 37, 6, -96, 106, 6, -96, 106, 44, 2, -64, 44, 2, -64, -120, 104, -122, -120, 104, -122, 83, 101, 54, 83, 101, 54, 33, 50, 19, 33, 50, 19, -112, -103, 9, -112, -103, 9, 2, 80, 37, 2, 80, 37, 6, -96, 106, 6, -96, 106, 44, 2, -64, 44, 2, -64, -120, 104, -122, -120, 104, -122, 81, 69, 20, 81, 69, 20, -59, 28, 81, -59, 28, 81, 33, -126, 24, 33, -126, 24, 18, 49, 35, 18, 49, 35, 8, -32, -114, 8, -32, -114, 46, 2, -32, 46, 2, -32, -14, -42, -114, -14, -42, -114};
    }

    private static void createRandomMask48_28() {
        _randomMask48_28 = new byte[]{83, 101, 54, 83, 101, 54, 33, 50, 19, 33, 50, 19, -112, -103, 9, -112, -103, 9, 2, 80, 37, 2, 80, 37, 6, -96, 106, 6, -96, 106, 44, 2, -64, 44, 2, -64, -120, 104, -122, -120, 104, -122, 81, 69, 20, 81, 69, 20, -59, 28, 81, -59, 28, 81, 33, -126, 24, 33, -126, 24, 18, 49, 35, 18, 49, 35, 8, -32, -114, 8, -32, -114, 46, 2, -32, 46, 2, -32, -14, -42, -114, -14, -42, -114, 81, 69, 20, 81, 69, 20, -59, 28, 81, -59, 28, 81, 33, -126, 24, 33, -126, 24, 18, 49, 35, 18, 49, 35, 8, -32, -114, 8, -32, -114, 46, 2, -32, 46, 2, -32, 83, 101, 54, 83, 101, 54, 33, 50, 19, 33, 50, 19, -112, -103, 9, -112, -103, 9, 2, 80, 37, 2, 80, 37, 6, -96, 106, 6, -96, 106, 44, 2, -64, 44, 2, -64, -120, 104, -122, -120, 104, -122, 50, -29, -64, 74, -14, 42};
    }

    private static void createRandomMask48_29() {
        _randomMask48_29 = new byte[]{83, 101, 54, 83, 101, 54, 33, 50, 19, 33, 50, 19, -112, -103, 9, -112, -103, 9, 2, 80, 37, 2, 80, 37, 6, -96, 106, 6, -96, 106, 44, 2, -64, 44, 2, -64, -120, 104, -122, -120, 104, -122, 81, 69, 20, 81, 69, 20, -59, 28, 81, -59, 28, 81, 33, -126, 24, 33, -126, 24, 18, 49, 35, 18, 49, 35, 8, -32, -114, 8, -32, -114, 46, 2, -32, 46, 2, -32, -14, -42, -114, -14, -42, -114, 83, 101, 54, 83, 101, 54, 33, 50, 19, 33, 50, 19, -112, -103, 9, -112, -103, 9, 2, 80, 37, 2, 80, 37, 6, -96, 106, 6, -96, 106, 44, 2, -64, 44, 2, -64, -120, 104, -122, -120, 104, -122, 32, 98, 6, 32, 98, 6, ByteCompanionObject.MIN_VALUE, 56, 3, ByteCompanionObject.MIN_VALUE, 56, 3, 66, 68, 36, 66, 68, 36, 1, -112, 25, 1, -112, 25, 20, 17, 65, 20, 17, 65, 10, ByteCompanionObject.MIN_VALUE, -88, 10, ByteCompanionObject.MIN_VALUE, -88, 56, 3, ByteCompanionObject.MIN_VALUE, 56, 3, ByteCompanionObject.MIN_VALUE, -59, 12, 80, -59, 12, 80};
    }

    private static void createRandomMask48_3() {
        _randomMask48_3 = new byte[]{-101, 41, -78, -101, 41, -78, 73, -44, -99, 73, -44, -99, 62, -125, -24, 62, -125, -24};
    }

    private static void createRandomMask48_30() {
        _randomMask48_30 = new byte[]{83, 101, 54, 83, 101, 54, 33, 50, 19, 33, 50, 19, -112, -103, 9, -112, -103, 9, 2, 80, 37, 2, 80, 37, 6, -96, 106, 6, -96, 106, 44, 2, -64, 44, 2, -64, -120, 104, -122, -120, 104, -122, 32, 98, 6, 32, 98, 6, ByteCompanionObject.MIN_VALUE, 56, 3, ByteCompanionObject.MIN_VALUE, 56, 3, 66, 68, 36, 66, 68, 36, 1, -112, 25, 1, -112, 25, 20, 17, 65, 20, 17, 65, 10, ByteCompanionObject.MIN_VALUE, -88, 10, ByteCompanionObject.MIN_VALUE, -88, 56, 3, ByteCompanionObject.MIN_VALUE, 56, 3, ByteCompanionObject.MIN_VALUE, -59, 12, 80, -59, 12, 80, 83, 101, 54, 83, 101, 54, 33, 50, 19, 33, 50, 19, -112, -103, 9, -112, -103, 9, 2, 80, 37, 2, 80, 37, 6, -96, 106, 6, -96, 106, 44, 2, -64, 44, 2, -64, -120, 104, -122, -120, 104, -122, 81, 69, 20, 81, 69, 20, -59, 28, 81, -59, 28, 81, 33, -126, 24, 33, -126, 24, 18, 49, 35, 18, 49, 35, 8, -32, -114, 8, -32, -114, 46, 2, -32, 46, 2, -32, -14, -42, -114, -14, -42, -114, 102, -13, -102, -35, 104, -109};
    }

    private static void createRandomMask48_31() {
        _randomMask48_31 = new byte[]{83, 101, 54, 83, 101, 54, 33, 50, 19, 33, 50, 19, -112, -103, 9, -112, -103, 9, 2, 80, 37, 2, 80, 37, 6, -96, 106, 6, -96, 106, 44, 2, -64, 44, 2, -64, -120, 104, -122, -120, 104, -122, 32, 98, 6, 32, 98, 6, ByteCompanionObject.MIN_VALUE, 56, 3, ByteCompanionObject.MIN_VALUE, 56, 3, 66, 68, 36, 66, 68, 36, 1, -112, 25, 1, -112, 25, 20, 17, 65, 20, 17, 65, 10, ByteCompanionObject.MIN_VALUE, -88, 10, ByteCompanionObject.MIN_VALUE, -88, 56, 3, ByteCompanionObject.MIN_VALUE, 56, 3, ByteCompanionObject.MIN_VALUE, -59, 12, 80, -59, 12, 80, 32, 98, 6, 32, 98, 6, ByteCompanionObject.MIN_VALUE, 56, 3, ByteCompanionObject.MIN_VALUE, 56, 3, 66, 68, 36, 66, 68, 36, 1, -112, 25, 1, -112, 25, 20, 17, 65, 20, 17, 65, 10, ByteCompanionObject.MIN_VALUE, -88, 10, ByteCompanionObject.MIN_VALUE, -88, 56, 3, ByteCompanionObject.MIN_VALUE, 56, 3, ByteCompanionObject.MIN_VALUE, -59, 12, 80, -59, 12, 80, 83, 101, 54, 83, 101, 54, 33, 50, 19, 33, 50, 19, -112, -103, 9, -112, -103, 9, 2, 80, 37, 2, 80, 37, 6, -96, 106, 6, -96, 106, 44, 2, -64, 44, 2, -64, -120, 104, -122, -120, 104, -122, -1, 110, 10, -1, 110, 10};
    }

    private static void createRandomMask48_32() {
        _randomMask48_32 = new byte[]{32, 98, 6, 32, 98, 6, ByteCompanionObject.MIN_VALUE, 56, 3, ByteCompanionObject.MIN_VALUE, 56, 3, 66, 68, 36, 66, 68, 36, 1, -112, 25, 1, -112, 25, 20, 17, 65, 20, 17, 65, 10, ByteCompanionObject.MIN_VALUE, -88, 10, ByteCompanionObject.MIN_VALUE, -88, 56, 3, ByteCompanionObject.MIN_VALUE, 56, 3, ByteCompanionObject.MIN_VALUE, -59, 12, 80, -59, 12, 80, 83, 101, 54, 83, 101, 54, 33, 50, 19, 33, 50, 19, -112, -103, 9, -112, -103, 9, 2, 80, 37, 2, 80, 37, 6, -96, 106, 6, -96, 106, 44, 2, -64, 44, 2, -64, -120, 104, -122, -120, 104, -122, -1, 110, 10, -1, 110, 10, 83, 101, 54, 83, 101, 54, 33, 50, 19, 33, 50, 19, -112, -103, 9, -112, -103, 9, 2, 80, 37, 2, 80, 37, 6, -96, 106, 6, -96, 106, 44, 2, -64, 44, 2, -64, -120, 104, -122, -120, 104, -122, 32, 98, 6, 32, 98, 6, ByteCompanionObject.MIN_VALUE, 56, 3, ByteCompanionObject.MIN_VALUE, 56, 3, 66, 68, 36, 66, 68, 36, 1, -112, 25, 1, -112, 25, 20, 17, 65, 20, 17, 65, 10, ByteCompanionObject.MIN_VALUE, -88, 10, ByteCompanionObject.MIN_VALUE, -88, 56, 3, ByteCompanionObject.MIN_VALUE, 56, 3, ByteCompanionObject.MIN_VALUE, -59, 12, 80, -59, 12, 80, -43, 74, 79, 72, -75, 49};
    }

    private static void createRandomMask48_33() {
        _randomMask48_33 = new byte[]{32, 98, 6, 32, 98, 6, ByteCompanionObject.MIN_VALUE, 56, 3, ByteCompanionObject.MIN_VALUE, 56, 3, 66, 68, 36, 66, 68, 36, 1, -112, 25, 1, -112, 25, 20, 17, 65, 20, 17, 65, 10, ByteCompanionObject.MIN_VALUE, -88, 10, ByteCompanionObject.MIN_VALUE, -88, 56, 3, ByteCompanionObject.MIN_VALUE, 56, 3, ByteCompanionObject.MIN_VALUE, -59, 12, 80, -59, 12, 80, 83, 101, 54, 83, 101, 54, 33, 50, 19, 33, 50, 19, -112, -103, 9, -112, -103, 9, 2, 80, 37, 2, 80, 37, 6, -96, 106, 6, -96, 106, 44, 2, -64, 44, 2, -64, -120, 104, -122, -120, 104, -122, -1, 110, 10, -1, 110, 10, 32, 98, 6, 32, 98, 6, ByteCompanionObject.MIN_VALUE, 56, 3, ByteCompanionObject.MIN_VALUE, 56, 3, 66, 68, 36, 66, 68, 36, 1, -112, 25, 1, -112, 25, 20, 17, 65, 20, 17, 65, 10, ByteCompanionObject.MIN_VALUE, -88, 10, ByteCompanionObject.MIN_VALUE, -88, 56, 3, ByteCompanionObject.MIN_VALUE, 56, 3, ByteCompanionObject.MIN_VALUE, -59, 12, 80, -59, 12, 80, 83, 101, 54, 83, 101, 54, -28, 46, 66, -28, 46, 66, 36, 66, 68, 36, 66, 68, -95, 26, 17, -95, 26, 17, 24, 49, -125, 24, 49, -125, 3, -112, 57, 3, -112, 57, -118, 24, -95, -118, 24, -95, 4, -112, 73, 4, -112, 73, 0, -32, 14, 0, -32, 14};
    }

    private static void createRandomMask48_34() {
        _randomMask48_34 = new byte[]{32, 98, 6, 32, 98, 6, ByteCompanionObject.MIN_VALUE, 56, 3, ByteCompanionObject.MIN_VALUE, 56, 3, 66, 68, 36, 66, 68, 36, 1, -112, 25, 1, -112, 25, 20, 17, 65, 20, 17, 65, 10, ByteCompanionObject.MIN_VALUE, -88, 10, ByteCompanionObject.MIN_VALUE, -88, 56, 3, ByteCompanionObject.MIN_VALUE, 56, 3, ByteCompanionObject.MIN_VALUE, -59, 12, 80, -59, 12, 80, 83, 101, 54, 83, 101, 54, -28, 46, 66, -28, 46, 66, 36, 66, 68, 36, 66, 68, -95, 26, 17, -95, 26, 17, 24, 49, -125, 24, 49, -125, 3, -112, 57, 3, -112, 57, -118, 24, -95, -118, 24, -95, 4, -112, 73, 4, -112, 73, 0, -32, 14, 0, -32, 14, 32, 98, 6, 32, 98, 6, ByteCompanionObject.MIN_VALUE, 56, 3, ByteCompanionObject.MIN_VALUE, 56, 3, 66, 68, 36, 66, 68, 36, 1, -112, 25, 1, -112, 25, 20, 17, 65, 20, 17, 65, 10, ByteCompanionObject.MIN_VALUE, -88, 10, ByteCompanionObject.MIN_VALUE, -88, 56, 3, ByteCompanionObject.MIN_VALUE, 56, 3, ByteCompanionObject.MIN_VALUE, -59, 12, 80, -59, 12, 80, 83, 101, 54, 83, 101, 54, 33, 50, 19, 33, 50, 19, -112, -103, 9, -112, -103, 9, 2, 80, 37, 2, 80, 37, 6, -96, 106, 6, -96, 106, 44, 2, -64, 44, 2, -64, -120, 104, -122, -120, 104, -122, -1, 110, 10, -1, 110, 10, 64, 114, 76, -24, -14, 66};
    }

    private static void createRandomMask48_35() {
        _randomMask48_35 = new byte[]{32, 98, 6, 32, 98, 6, ByteCompanionObject.MIN_VALUE, 56, 3, ByteCompanionObject.MIN_VALUE, 56, 3, 66, 68, 36, 66, 68, 36, 1, -112, 25, 1, -112, 25, 20, 17, 65, 20, 17, 65, 10, ByteCompanionObject.MIN_VALUE, -88, 10, ByteCompanionObject.MIN_VALUE, -88, 56, 3, ByteCompanionObject.MIN_VALUE, 56, 3, ByteCompanionObject.MIN_VALUE, -59, 12, 80, -59, 12, 80, 83, 101, 54, 83, 101, 54, -28, 46, 66, -28, 46, 66, 36, 66, 68, 36, 66, 68, -95, 26, 17, -95, 26, 17, 24, 49, -125, 24, 49, -125, 3, -112, 57, 3, -112, 57, -118, 24, -95, -118, 24, -95, 4, -112, 73, 4, -112, 73, 0, -32, 14, 0, -32, 14, 83, 101, 54, 83, 101, 54, -28, 46, 66, -28, 46, 66, 36, 66, 68, 36, 66, 68, -95, 26, 17, -95, 26, 17, 24, 49, -125, 24, 49, -125, 3, -112, 57, 3, -112, 57, -118, 24, -95, -118, 24, -95, 4, -112, 73, 4, -112, 73, 0, -32, 14, 0, -32, 14, 32, 98, 6, 32, 98, 6, ByteCompanionObject.MIN_VALUE, 56, 3, ByteCompanionObject.MIN_VALUE, 56, 3, 66, 68, 36, 66, 68, 36, 1, -112, 25, 1, -112, 25, 20, 17, 65, 20, 17, 65, 10, ByteCompanionObject.MIN_VALUE, -88, 10, ByteCompanionObject.MIN_VALUE, -88, 56, 3, ByteCompanionObject.MIN_VALUE, 56, 3, ByteCompanionObject.MIN_VALUE, -59, 12, 80, -59, 12, 80, 52, 80, -82, 52, 80, -82};
    }

    private static void createRandomMask48_36() {
        _randomMask48_36 = new byte[]{83, 101, 54, 83, 101, 54, -28, 46, 66, -28, 46, 66, 36, 66, 68, 36, 66, 68, -95, 26, 17, -95, 26, 17, 24, 49, -125, 24, 49, -125, 3, -112, 57, 3, -112, 57, -118, 24, -95, -118, 24, -95, 4, -112, 73, 4, -112, 73, 0, -32, 14, 0, -32, 14, 32, 98, 6, 32, 98, 6, ByteCompanionObject.MIN_VALUE, 56, 3, ByteCompanionObject.MIN_VALUE, 56, 3, 66, 68, 36, 66, 68, 36, 1, -112, 25, 1, -112, 25, 20, 17, 65, 20, 17, 65, 10, ByteCompanionObject.MIN_VALUE, -88, 10, ByteCompanionObject.MIN_VALUE, -88, 56, 3, ByteCompanionObject.MIN_VALUE, 56, 3, ByteCompanionObject.MIN_VALUE, -59, 12, 80, -59, 12, 80, 52, 80, -82, 52, 80, -82, 32, 98, 6, 32, 98, 6, ByteCompanionObject.MIN_VALUE, 56, 3, ByteCompanionObject.MIN_VALUE, 56, 3, 66, 68, 36, 66, 68, 36, 1, -112, 25, 1, -112, 25, 20, 17, 65, 20, 17, 65, 10, ByteCompanionObject.MIN_VALUE, -88, 10, ByteCompanionObject.MIN_VALUE, -88, 56, 3, ByteCompanionObject.MIN_VALUE, 56, 3, ByteCompanionObject.MIN_VALUE, -59, 12, 80, -59, 12, 80, 83, 101, 54, 83, 101, 54, -28, 46, 66, -28, 46, 66, 36, 66, 68, 36, 66, 68, -95, 26, 17, -95, 26, 17, 24, 49, -125, 24, 49, -125, 3, -112, 57, 3, -112, 57, -118, 24, -95, -118, 24, -95, 4, -112, 73, 4, -112, 73, 0, -32, 14, 0, -32, 14, 113, -70, -117, -13, -6, -99};
    }

    private static void createRandomMask48_37() {
        _randomMask48_37 = new byte[]{83, 101, 54, 83, 101, 54, -28, 46, 66, -28, 46, 66, 36, 66, 68, 36, 66, 68, -95, 26, 17, -95, 26, 17, 24, 49, -125, 24, 49, -125, 3, -112, 57, 3, -112, 57, -118, 24, -95, -118, 24, -95, 4, -112, 73, 4, -112, 73, 0, -32, 14, 0, -32, 14, 32, 98, 6, 32, 98, 6, ByteCompanionObject.MIN_VALUE, 56, 3, ByteCompanionObject.MIN_VALUE, 56, 3, 66, 68, 36, 66, 68, 36, 1, -112, 25, 1, -112, 25, 20, 17, 65, 20, 17, 65, 10, ByteCompanionObject.MIN_VALUE, -88, 10, ByteCompanionObject.MIN_VALUE, -88, 56, 3, ByteCompanionObject.MIN_VALUE, 56, 3, ByteCompanionObject.MIN_VALUE, -59, 12, 80, -59, 12, 80, 52, 80, -82, 52, 80, -82, 83, 101, 54, 83, 101, 54, -28, 46, 66, -28, 46, 66, 36, 66, 68, 36, 66, 68, -95, 26, 17, -95, 26, 17, 24, 49, -125, 24, 49, -125, 3, -112, 57, 3, -112, 57, -118, 24, -95, -118, 24, -95, 4, -112, 73, 4, -112, 73, 0, -32, 14, 0, -32, 14, 81, 69, 20, 81, 69, 20, 69, 20, 81, 69, 20, 81, ByteCompanionObject.MIN_VALUE, -40, 13, ByteCompanionObject.MIN_VALUE, -40, 13, 36, 34, 66, 36, 34, 66, 10, 32, -94, 10, 32, -94, 0, -32, 14, 0, -32, 14, -72, 11, ByteCompanionObject.MIN_VALUE, -72, 11, ByteCompanionObject.MIN_VALUE, 9, Tnaf.POW_2_WIDTH, -111, 9, Tnaf.POW_2_WIDTH, -111, 86, 5, 96, 86, 5, 96, -94, -118, 40, -94, -118, 40};
    }

    private static void createRandomMask48_38() {
        _randomMask48_38 = new byte[]{83, 101, 54, 83, 101, 54, -28, 46, 66, -28, 46, 66, 36, 66, 68, 36, 66, 68, -95, 26, 17, -95, 26, 17, 24, 49, -125, 24, 49, -125, 3, -112, 57, 3, -112, 57, -118, 24, -95, -118, 24, -95, 4, -112, 73, 4, -112, 73, 0, -32, 14, 0, -32, 14, 81, 69, 20, 81, 69, 20, 69, 20, 81, 69, 20, 81, ByteCompanionObject.MIN_VALUE, -40, 13, ByteCompanionObject.MIN_VALUE, -40, 13, 36, 34, 66, 36, 34, 66, 10, 32, -94, 10, 32, -94, 0, -32, 14, 0, -32, 14, -72, 11, ByteCompanionObject.MIN_VALUE, -72, 11, ByteCompanionObject.MIN_VALUE, 9, Tnaf.POW_2_WIDTH, -111, 9, Tnaf.POW_2_WIDTH, -111, 86, 5, 96, 86, 5, 96, -94, -118, 40, -94, -118, 40, 83, 101, 54, 83, 101, 54, -28, 46, 66, -28, 46, 66, 36, 66, 68, 36, 66, 68, -95, 26, 17, -95, 26, 17, 24, 49, -125, 24, 49, -125, 3, -112, 57, 3, -112, 57, -118, 24, -95, -118, 24, -95, 4, -112, 73, 4, -112, 73, 0, -32, 14, 0, -32, 14, 32, 98, 6, 32, 98, 6, ByteCompanionObject.MIN_VALUE, 56, 3, ByteCompanionObject.MIN_VALUE, 56, 3, 66, 68, 36, 66, 68, 36, 1, -112, 25, 1, -112, 25, 20, 17, 65, 20, 17, 65, 10, ByteCompanionObject.MIN_VALUE, -88, 10, ByteCompanionObject.MIN_VALUE, -88, 56, 3, ByteCompanionObject.MIN_VALUE, 56, 3, ByteCompanionObject.MIN_VALUE, -59, 12, 80, -59, 12, 80, 52, 80, -82, 52, 80, -82, 42, 122, -10, -116, -34, 81};
    }

    private static void createRandomMask48_39() {
        _randomMask48_39 = new byte[]{83, 101, 54, 83, 101, 54, -28, 46, 66, -28, 46, 66, 36, 66, 68, 36, 66, 68, -95, 26, 17, -95, 26, 17, 24, 49, -125, 24, 49, -125, 3, -112, 57, 3, -112, 57, -118, 24, -95, -118, 24, -95, 4, -112, 73, 4, -112, 73, 0, -32, 14, 0, -32, 14, 81, 69, 20, 81, 69, 20, 69, 20, 81, 69, 20, 81, ByteCompanionObject.MIN_VALUE, -40, 13, ByteCompanionObject.MIN_VALUE, -40, 13, 36, 34, 66, 36, 34, 66, 10, 32, -94, 10, 32, -94, 0, -32, 14, 0, -32, 14, -72, 11, ByteCompanionObject.MIN_VALUE, -72, 11, ByteCompanionObject.MIN_VALUE, 9, Tnaf.POW_2_WIDTH, -111, 9, Tnaf.POW_2_WIDTH, -111, 86, 5, 96, 86, 5, 96, -94, -118, 40, -94, -118, 40, 81, 69, 20, 81, 69, 20, 69, 20, 81, 69, 20, 81, ByteCompanionObject.MIN_VALUE, -40, 13, ByteCompanionObject.MIN_VALUE, -40, 13, 36, 34, 66, 36, 34, 66, 10, 32, -94, 10, 32, -94, 0, -32, 14, 0, -32, 14, -72, 11, ByteCompanionObject.MIN_VALUE, -72, 11, ByteCompanionObject.MIN_VALUE, 9, Tnaf.POW_2_WIDTH, -111, 9, Tnaf.POW_2_WIDTH, -111, 86, 5, 96, 86, 5, 96, -94, -118, 40, -94, -118, 40, 83, 101, 54, 83, 101, 54, -28, 46, 66, -28, 46, 66, 36, 66, 68, 36, 66, 68, -95, 26, 17, -95, 26, 17, 24, 49, -125, 24, 49, -125, 3, -112, 57, 3, -112, 57, -118, 24, -95, -118, 24, -95, 4, -112, 73, 4, -112, 73, 0, -32, 14, 0, -32, 14, -104, -94, -107, -104, -94, -107};
    }

    private static void createRandomMask48_4() {
        _randomMask48_4 = new byte[]{-117, 40, -78, -117, 40, -78, 20, -79, 75, 20, -79, 75, 34, -46, 45, 34, -46, 45, 69, 84, 85, 69, 84, 85};
    }

    private static void createRandomMask48_40() {
        _randomMask48_40 = new byte[]{81, 69, 20, 81, 69, 20, 69, 20, 81, 69, 20, 81, ByteCompanionObject.MIN_VALUE, -40, 13, ByteCompanionObject.MIN_VALUE, -40, 13, 36, 34, 66, 36, 34, 66, 10, 32, -94, 10, 32, -94, 0, -32, 14, 0, -32, 14, -72, 11, ByteCompanionObject.MIN_VALUE, -72, 11, ByteCompanionObject.MIN_VALUE, 9, Tnaf.POW_2_WIDTH, -111, 9, Tnaf.POW_2_WIDTH, -111, 86, 5, 96, 86, 5, 96, -94, -118, 40, -94, -118, 40, 83, 101, 54, 83, 101, 54, -28, 46, 66, -28, 46, 66, 36, 66, 68, 36, 66, 68, -95, 26, 17, -95, 26, 17, 24, 49, -125, 24, 49, -125, 3, -112, 57, 3, -112, 57, -118, 24, -95, -118, 24, -95, 4, -112, 73, 4, -112, 73, 0, -32, 14, 0, -32, 14, -104, -94, -107, -104, -94, -107, 83, 101, 54, 83, 101, 54, -28, 46, 66, -28, 46, 66, 36, 66, 68, 36, 66, 68, -95, 26, 17, -95, 26, 17, 24, 49, -125, 24, 49, -125, 3, -112, 57, 3, -112, 57, -118, 24, -95, -118, 24, -95, 4, -112, 73, 4, -112, 73, 0, -32, 14, 0, -32, 14, 81, 69, 20, 81, 69, 20, 69, 20, 81, 69, 20, 81, ByteCompanionObject.MIN_VALUE, -40, 13, ByteCompanionObject.MIN_VALUE, -40, 13, 36, 34, 66, 36, 34, 66, 10, 32, -94, 10, 32, -94, 0, -32, 14, 0, -32, 14, -72, 11, ByteCompanionObject.MIN_VALUE, -72, 11, ByteCompanionObject.MIN_VALUE, 9, Tnaf.POW_2_WIDTH, -111, 9, Tnaf.POW_2_WIDTH, -111, 86, 5, 96, 86, 5, 96, -94, -118, 40, -94, -118, 40, 32, 95, 104, -43, -94, 27};
    }

    private static void createRandomMask48_41() {
        _randomMask48_41 = new byte[]{81, 69, 20, 81, 69, 20, 69, 20, 81, 69, 20, 81, ByteCompanionObject.MIN_VALUE, -40, 13, ByteCompanionObject.MIN_VALUE, -40, 13, 36, 34, 66, 36, 34, 66, 10, 32, -94, 10, 32, -94, 0, -32, 14, 0, -32, 14, -72, 11, ByteCompanionObject.MIN_VALUE, -72, 11, ByteCompanionObject.MIN_VALUE, 9, Tnaf.POW_2_WIDTH, -111, 9, Tnaf.POW_2_WIDTH, -111, 86, 5, 96, 86, 5, 96, -94, -118, 40, -94, -118, 40, 83, 101, 54, 83, 101, 54, -28, 46, 66, -28, 46, 66, 36, 66, 68, 36, 66, 68, -95, 26, 17, -95, 26, 17, 24, 49, -125, 24, 49, -125, 3, -112, 57, 3, -112, 57, -118, 24, -95, -118, 24, -95, 4, -112, 73, 4, -112, 73, 0, -32, 14, 0, -32, 14, -104, -94, -107, -104, -94, -107, 81, 69, 20, 81, 69, 20, 69, 20, 81, 69, 20, 81, ByteCompanionObject.MIN_VALUE, -40, 13, ByteCompanionObject.MIN_VALUE, -40, 13, 36, 34, 66, 36, 34, 66, 10, 32, -94, 10, 32, -94, 0, -32, 14, 0, -32, 14, -72, 11, ByteCompanionObject.MIN_VALUE, -72, 11, ByteCompanionObject.MIN_VALUE, 9, Tnaf.POW_2_WIDTH, -111, 9, Tnaf.POW_2_WIDTH, -111, 86, 5, 96, 86, 5, 96, -94, -118, 40, -94, -118, 40, 83, 101, 54, 83, 101, 54, 33, 50, 19, 33, 50, 19, Tnaf.POW_2_WIDTH, -111, 9, Tnaf.POW_2_WIDTH, -111, 9, 0, 112, 7, 0, 112, 7, 12, Tnaf.POW_2_WIDTH, -63, 12, Tnaf.POW_2_WIDTH, -63, 64, -60, 12, 64, -60, 12, 106, 6, -96, 106, 6, -96, -122, 8, 96, -122, 8, 96, 36, -126, 72, 36, -126, 72, -119, 8, -112, -119, 8, -112, -64, 44, 2, -64, 44, 2};
    }

    private static void createRandomMask48_42() {
        _randomMask48_42 = new byte[]{81, 69, 20, 81, 69, 20, 69, 20, 81, 69, 20, 81, ByteCompanionObject.MIN_VALUE, -40, 13, ByteCompanionObject.MIN_VALUE, -40, 13, 36, 34, 66, 36, 34, 66, 10, 32, -94, 10, 32, -94, 0, -32, 14, 0, -32, 14, -72, 11, ByteCompanionObject.MIN_VALUE, -72, 11, ByteCompanionObject.MIN_VALUE, 9, Tnaf.POW_2_WIDTH, -111, 9, Tnaf.POW_2_WIDTH, -111, 86, 5, 96, 86, 5, 96, -94, -118, 40, -94, -118, 40, 83, 101, 54, 83, 101, 54, 33, 50, 19, 33, 50, 19, Tnaf.POW_2_WIDTH, -111, 9, Tnaf.POW_2_WIDTH, -111, 9, 0, 112, 7, 0, 112, 7, 12, Tnaf.POW_2_WIDTH, -63, 12, Tnaf.POW_2_WIDTH, -63, 64, -60, 12, 64, -60, 12, 106, 6, -96, 106, 6, -96, -122, 8, 96, -122, 8, 96, 36, -126, 72, 36, -126, 72, -119, 8, -112, -119, 8, -112, -64, 44, 2, -64, 44, 2, 81, 69, 20, 81, 69, 20, 69, 20, 81, 69, 20, 81, ByteCompanionObject.MIN_VALUE, -40, 13, ByteCompanionObject.MIN_VALUE, -40, 13, 36, 34, 66, 36, 34, 66, 10, 32, -94, 10, 32, -94, 0, -32, 14, 0, -32, 14, -72, 11, ByteCompanionObject.MIN_VALUE, -72, 11, ByteCompanionObject.MIN_VALUE, 9, Tnaf.POW_2_WIDTH, -111, 9, Tnaf.POW_2_WIDTH, -111, 86, 5, 96, 86, 5, 96, -94, -118, 40, -94, -118, 40, 83, 101, 54, 83, 101, 54, -28, 46, 66, -28, 46, 66, 36, 66, 68, 36, 66, 68, -95, 26, 17, -95, 26, 17, 24, 49, -125, 24, 49, -125, 3, -112, 57, 3, -112, 57, -118, 24, -95, -118, 24, -95, 4, -112, 73, 4, -112, 73, 0, -32, 14, 0, -32, 14, -104, -94, -107, -104, -94, -107, 102, -49, -93, 71, 105, 0};
    }

    private static void createRandomMask48_43() {
        _randomMask48_43 = new byte[]{81, 69, 20, 81, 69, 20, 69, 20, 81, 69, 20, 81, ByteCompanionObject.MIN_VALUE, -40, 13, ByteCompanionObject.MIN_VALUE, -40, 13, 36, 34, 66, 36, 34, 66, 10, 32, -94, 10, 32, -94, 0, -32, 14, 0, -32, 14, -72, 11, ByteCompanionObject.MIN_VALUE, -72, 11, ByteCompanionObject.MIN_VALUE, 9, Tnaf.POW_2_WIDTH, -111, 9, Tnaf.POW_2_WIDTH, -111, 86, 5, 96, 86, 5, 96, -94, -118, 40, -94, -118, 40, 83, 101, 54, 83, 101, 54, 33, 50, 19, 33, 50, 19, Tnaf.POW_2_WIDTH, -111, 9, Tnaf.POW_2_WIDTH, -111, 9, 0, 112, 7, 0, 112, 7, 12, Tnaf.POW_2_WIDTH, -63, 12, Tnaf.POW_2_WIDTH, -63, 64, -60, 12, 64, -60, 12, 106, 6, -96, 106, 6, -96, -122, 8, 96, -122, 8, 96, 36, -126, 72, 36, -126, 72, -119, 8, -112, -119, 8, -112, -64, 44, 2, -64, 44, 2, 83, 101, 54, 83, 101, 54, 33, 50, 19, 33, 50, 19, Tnaf.POW_2_WIDTH, -111, 9, Tnaf.POW_2_WIDTH, -111, 9, 0, 112, 7, 0, 112, 7, 12, Tnaf.POW_2_WIDTH, -63, 12, Tnaf.POW_2_WIDTH, -63, 64, -60, 12, 64, -60, 12, 106, 6, -96, 106, 6, -96, -122, 8, 96, -122, 8, 96, 36, -126, 72, 36, -126, 72, -119, 8, -112, -119, 8, -112, -64, 44, 2, -64, 44, 2, 81, 69, 20, 81, 69, 20, 69, 20, 81, 69, 20, 81, ByteCompanionObject.MIN_VALUE, -40, 13, ByteCompanionObject.MIN_VALUE, -40, 13, 36, 34, 66, 36, 34, 66, 10, 32, -94, 10, 32, -94, 0, -32, 14, 0, -32, 14, -72, 11, ByteCompanionObject.MIN_VALUE, -72, 11, ByteCompanionObject.MIN_VALUE, 9, Tnaf.POW_2_WIDTH, -111, 9, Tnaf.POW_2_WIDTH, -111, 86, 5, 96, 86, 5, 96, -94, -118, 40, -94, -118, 40, 26, -86, -18, 26, -86, -18};
    }

    private static void createRandomMask48_44() {
        _randomMask48_44 = new byte[]{83, 101, 54, 83, 101, 54, 33, 50, 19, 33, 50, 19, Tnaf.POW_2_WIDTH, -111, 9, Tnaf.POW_2_WIDTH, -111, 9, 0, 112, 7, 0, 112, 7, 12, Tnaf.POW_2_WIDTH, -63, 12, Tnaf.POW_2_WIDTH, -63, 64, -60, 12, 64, -60, 12, 106, 6, -96, 106, 6, -96, -122, 8, 96, -122, 8, 96, 36, -126, 72, 36, -126, 72, -119, 8, -112, -119, 8, -112, -64, 44, 2, -64, 44, 2, 81, 69, 20, 81, 69, 20, 69, 20, 81, 69, 20, 81, ByteCompanionObject.MIN_VALUE, -40, 13, ByteCompanionObject.MIN_VALUE, -40, 13, 36, 34, 66, 36, 34, 66, 10, 32, -94, 10, 32, -94, 0, -32, 14, 0, -32, 14, -72, 11, ByteCompanionObject.MIN_VALUE, -72, 11, ByteCompanionObject.MIN_VALUE, 9, Tnaf.POW_2_WIDTH, -111, 9, Tnaf.POW_2_WIDTH, -111, 86, 5, 96, 86, 5, 96, -94, -118, 40, -94, -118, 40, 26, -86, -18, 26, -86, -18, 81, 69, 20, 81, 69, 20, 69, 20, 81, 69, 20, 81, ByteCompanionObject.MIN_VALUE, -40, 13, ByteCompanionObject.MIN_VALUE, -40, 13, 36, 34, 66, 36, 34, 66, 10, 32, -94, 10, 32, -94, 0, -32, 14, 0, -32, 14, -72, 11, ByteCompanionObject.MIN_VALUE, -72, 11, ByteCompanionObject.MIN_VALUE, 9, Tnaf.POW_2_WIDTH, -111, 9, Tnaf.POW_2_WIDTH, -111, 86, 5, 96, 86, 5, 96, -94, -118, 40, -94, -118, 40, 83, 101, 54, 83, 101, 54, 33, 50, 19, 33, 50, 19, Tnaf.POW_2_WIDTH, -111, 9, Tnaf.POW_2_WIDTH, -111, 9, 0, 112, 7, 0, 112, 7, 12, Tnaf.POW_2_WIDTH, -63, 12, Tnaf.POW_2_WIDTH, -63, 64, -60, 12, 64, -60, 12, 106, 6, -96, 106, 6, -96, -122, 8, 96, -122, 8, 96, 36, -126, 72, 36, -126, 72, -119, 8, -112, -119, 8, -112, -64, 44, 2, -64, 44, 2, -58, 64, 31, 87, -58, -26};
    }

    private static void createRandomMask48_45() {
        _randomMask48_45 = new byte[]{83, 101, 54, 83, 101, 54, 33, 50, 19, 33, 50, 19, Tnaf.POW_2_WIDTH, -111, 9, Tnaf.POW_2_WIDTH, -111, 9, 0, 112, 7, 0, 112, 7, 12, Tnaf.POW_2_WIDTH, -63, 12, Tnaf.POW_2_WIDTH, -63, 64, -60, 12, 64, -60, 12, 106, 6, -96, 106, 6, -96, -122, 8, 96, -122, 8, 96, 36, -126, 72, 36, -126, 72, -119, 8, -112, -119, 8, -112, -64, 44, 2, -64, 44, 2, 81, 69, 20, 81, 69, 20, 69, 20, 81, 69, 20, 81, ByteCompanionObject.MIN_VALUE, -40, 13, ByteCompanionObject.MIN_VALUE, -40, 13, 36, 34, 66, 36, 34, 66, 10, 32, -94, 10, 32, -94, 0, -32, 14, 0, -32, 14, -72, 11, ByteCompanionObject.MIN_VALUE, -72, 11, ByteCompanionObject.MIN_VALUE, 9, Tnaf.POW_2_WIDTH, -111, 9, Tnaf.POW_2_WIDTH, -111, 86, 5, 96, 86, 5, 96, -94, -118, 40, -94, -118, 40, 26, -86, -18, 26, -86, -18, 83, 101, 54, 83, 101, 54, 33, 50, 19, 33, 50, 19, Tnaf.POW_2_WIDTH, -111, 9, Tnaf.POW_2_WIDTH, -111, 9, 0, 112, 7, 0, 112, 7, 12, Tnaf.POW_2_WIDTH, -63, 12, Tnaf.POW_2_WIDTH, -63, 64, -60, 12, 64, -60, 12, 106, 6, -96, 106, 6, -96, -122, 8, 96, -122, 8, 96, 36, -126, 72, 36, -126, 72, -119, 8, -112, -119, 8, -112, -64, 44, 2, -64, 44, 2, Tnaf.POW_2_WIDTH, 97, 6, Tnaf.POW_2_WIDTH, 97, 6, 2, 48, 35, 2, 48, 35, 64, 84, 5, 64, 84, 5, 33, -126, 24, 33, -126, 24, -127, 24, 17, -127, 24, 17, 20, -127, 72, 20, -127, 72, -104, 9, ByteCompanionObject.MIN_VALUE, -104, 9, ByteCompanionObject.MIN_VALUE, 8, -112, -119, 8, -112, -119, 98, 6, 32, 98, 6, 32, 36, 34, 66, 36, 34, 66, -118, 8, -96, -118, 8, -96, -124, 72, 68, -124, 72, 68};
    }

    private static void createRandomMask48_46() {
        _randomMask48_46 = new byte[]{83, 101, 54, 83, 101, 54, 33, 50, 19, 33, 50, 19, Tnaf.POW_2_WIDTH, -111, 9, Tnaf.POW_2_WIDTH, -111, 9, 0, 112, 7, 0, 112, 7, 12, Tnaf.POW_2_WIDTH, -63, 12, Tnaf.POW_2_WIDTH, -63, 64, -60, 12, 64, -60, 12, 106, 6, -96, 106, 6, -96, -122, 8, 96, -122, 8, 96, 36, -126, 72, 36, -126, 72, -119, 8, -112, -119, 8, -112, -64, 44, 2, -64, 44, 2, Tnaf.POW_2_WIDTH, 97, 6, Tnaf.POW_2_WIDTH, 97, 6, 2, 48, 35, 2, 48, 35, 64, 84, 5, 64, 84, 5, 33, -126, 24, 33, -126, 24, -127, 24, 17, -127, 24, 17, 20, -127, 72, 20, -127, 72, -104, 9, ByteCompanionObject.MIN_VALUE, -104, 9, ByteCompanionObject.MIN_VALUE, 8, -112, -119, 8, -112, -119, 98, 6, 32, 98, 6, 32, 36, 34, 66, 36, 34, 66, -118, 8, -96, -118, 8, -96, -124, 72, 68, -124, 72, 68, 83, 101, 54, 83, 101, 54, 33, 50, 19, 33, 50, 19, Tnaf.POW_2_WIDTH, -111, 9, Tnaf.POW_2_WIDTH, -111, 9, 0, 112, 7, 0, 112, 7, 12, Tnaf.POW_2_WIDTH, -63, 12, Tnaf.POW_2_WIDTH, -63, 64, -60, 12, 64, -60, 12, 106, 6, -96, 106, 6, -96, -122, 8, 96, -122, 8, 96, 36, -126, 72, 36, -126, 72, -119, 8, -112, -119, 8, -112, -64, 44, 2, -64, 44, 2, 81, 69, 20, 81, 69, 20, 69, 20, 81, 69, 20, 81, ByteCompanionObject.MIN_VALUE, -40, 13, ByteCompanionObject.MIN_VALUE, -40, 13, 36, 34, 66, 36, 34, 66, 10, 32, -94, 10, 32, -94, 0, -32, 14, 0, -32, 14, -72, 11, ByteCompanionObject.MIN_VALUE, -72, 11, ByteCompanionObject.MIN_VALUE, 9, Tnaf.POW_2_WIDTH, -111, 9, Tnaf.POW_2_WIDTH, -111, 86, 5, 96, 86, 5, 96, -94, -118, 40, -94, -118, 40, 26, -86, -18, 26, -86, -18, Tnaf.POW_2_WIDTH, -7, -85, 18, 20, -17};
    }

    private static void createRandomMask48_47() {
        _randomMask48_47 = new byte[]{83, 101, 54, 83, 101, 54, 33, 50, 19, 33, 50, 19, Tnaf.POW_2_WIDTH, -111, 9, Tnaf.POW_2_WIDTH, -111, 9, 0, 112, 7, 0, 112, 7, 12, Tnaf.POW_2_WIDTH, -63, 12, Tnaf.POW_2_WIDTH, -63, 64, -60, 12, 64, -60, 12, 106, 6, -96, 106, 6, -96, -122, 8, 96, -122, 8, 96, 36, -126, 72, 36, -126, 72, -119, 8, -112, -119, 8, -112, -64, 44, 2, -64, 44, 2, Tnaf.POW_2_WIDTH, 97, 6, Tnaf.POW_2_WIDTH, 97, 6, 2, 48, 35, 2, 48, 35, 64, 84, 5, 64, 84, 5, 33, -126, 24, 33, -126, 24, -127, 24, 17, -127, 24, 17, 20, -127, 72, 20, -127, 72, -104, 9, ByteCompanionObject.MIN_VALUE, -104, 9, ByteCompanionObject.MIN_VALUE, 8, -112, -119, 8, -112, -119, 98, 6, 32, 98, 6, 32, 36, 34, 66, 36, 34, 66, -118, 8, -96, -118, 8, -96, -124, 72, 68, -124, 72, 68, Tnaf.POW_2_WIDTH, 97, 6, Tnaf.POW_2_WIDTH, 97, 6, 2, 48, 35, 2, 48, 35, 64, 84, 5, 64, 84, 5, 33, -126, 24, 33, -126, 24, -127, 24, 17, -127, 24, 17, 20, -127, 72, 20, -127, 72, -104, 9, ByteCompanionObject.MIN_VALUE, -104, 9, ByteCompanionObject.MIN_VALUE, 8, -112, -119, 8, -112, -119, 98, 6, 32, 98, 6, 32, 36, 34, 66, 36, 34, 66, -118, 8, -96, -118, 8, -96, -124, 72, 68, -124, 72, 68, 83, 101, 54, 83, 101, 54, 33, 50, 19, 33, 50, 19, Tnaf.POW_2_WIDTH, -111, 9, Tnaf.POW_2_WIDTH, -111, 9, 0, 112, 7, 0, 112, 7, 12, Tnaf.POW_2_WIDTH, -63, 12, Tnaf.POW_2_WIDTH, -63, 64, -60, 12, 64, -60, 12, 106, 6, -96, 106, 6, -96, -122, 8, 96, -122, 8, 96, 36, -126, 72, 36, -126, 72, -119, 8, -112, -119, 8, -112, -64, 44, 2, -64, 44, 2, -120, 50, 89, -120, 50, 89};
    }

    private static void createRandomMask48_48() {
        byte[] bArr = new byte[VideoDimensions.CIF_VIDEO_HEIGHT];
        // fill-array-data instruction
        bArr[0] = 16;
        bArr[1] = 97;
        bArr[2] = 6;
        bArr[3] = 16;
        bArr[4] = 97;
        bArr[5] = 6;
        bArr[6] = 2;
        bArr[7] = 48;
        bArr[8] = 35;
        bArr[9] = 2;
        bArr[10] = 48;
        bArr[11] = 35;
        bArr[12] = 64;
        bArr[13] = 84;
        bArr[14] = 5;
        bArr[15] = 64;
        bArr[16] = 84;
        bArr[17] = 5;
        bArr[18] = 33;
        bArr[19] = -126;
        bArr[20] = 24;
        bArr[21] = 33;
        bArr[22] = -126;
        bArr[23] = 24;
        bArr[24] = -127;
        bArr[25] = 24;
        bArr[26] = 17;
        bArr[27] = -127;
        bArr[28] = 24;
        bArr[29] = 17;
        bArr[30] = 20;
        bArr[31] = -127;
        bArr[32] = 72;
        bArr[33] = 20;
        bArr[34] = -127;
        bArr[35] = 72;
        bArr[36] = -104;
        bArr[37] = 9;
        bArr[38] = -128;
        bArr[39] = -104;
        bArr[40] = 9;
        bArr[41] = -128;
        bArr[42] = 8;
        bArr[43] = -112;
        bArr[44] = -119;
        bArr[45] = 8;
        bArr[46] = -112;
        bArr[47] = -119;
        bArr[48] = 98;
        bArr[49] = 6;
        bArr[50] = 32;
        bArr[51] = 98;
        bArr[52] = 6;
        bArr[53] = 32;
        bArr[54] = 36;
        bArr[55] = 34;
        bArr[56] = 66;
        bArr[57] = 36;
        bArr[58] = 34;
        bArr[59] = 66;
        bArr[60] = -118;
        bArr[61] = 8;
        bArr[62] = -96;
        bArr[63] = -118;
        bArr[64] = 8;
        bArr[65] = -96;
        bArr[66] = -124;
        bArr[67] = 72;
        bArr[68] = 68;
        bArr[69] = -124;
        bArr[70] = 72;
        bArr[71] = 68;
        bArr[72] = 83;
        bArr[73] = 101;
        bArr[74] = 54;
        bArr[75] = 83;
        bArr[76] = 101;
        bArr[77] = 54;
        bArr[78] = 33;
        bArr[79] = 50;
        bArr[80] = 19;
        bArr[81] = 33;
        bArr[82] = 50;
        bArr[83] = 19;
        bArr[84] = 16;
        bArr[85] = -111;
        bArr[86] = 9;
        bArr[87] = 16;
        bArr[88] = -111;
        bArr[89] = 9;
        bArr[90] = 0;
        bArr[91] = 112;
        bArr[92] = 7;
        bArr[93] = 0;
        bArr[94] = 112;
        bArr[95] = 7;
        bArr[96] = 12;
        bArr[97] = 16;
        bArr[98] = -63;
        bArr[99] = 12;
        bArr[100] = 16;
        bArr[101] = -63;
        bArr[102] = 64;
        bArr[103] = -60;
        bArr[104] = 12;
        bArr[105] = 64;
        bArr[106] = -60;
        bArr[107] = 12;
        bArr[108] = 106;
        bArr[109] = 6;
        bArr[110] = -96;
        bArr[111] = 106;
        bArr[112] = 6;
        bArr[113] = -96;
        bArr[114] = -122;
        bArr[115] = 8;
        bArr[116] = 96;
        bArr[117] = -122;
        bArr[118] = 8;
        bArr[119] = 96;
        bArr[120] = 36;
        bArr[121] = -126;
        bArr[122] = 72;
        bArr[123] = 36;
        bArr[124] = -126;
        bArr[125] = 72;
        bArr[126] = -119;
        bArr[127] = 8;
        bArr[128] = -112;
        bArr[129] = -119;
        bArr[130] = 8;
        bArr[131] = -112;
        bArr[132] = -64;
        bArr[133] = 44;
        bArr[134] = 2;
        bArr[135] = -64;
        bArr[136] = 44;
        bArr[137] = 2;
        bArr[138] = -120;
        bArr[139] = 50;
        bArr[140] = 89;
        bArr[141] = -120;
        bArr[142] = 50;
        bArr[143] = 89;
        bArr[144] = 83;
        bArr[145] = 101;
        bArr[146] = 54;
        bArr[147] = 83;
        bArr[148] = 101;
        bArr[149] = 54;
        bArr[150] = 33;
        bArr[151] = 50;
        bArr[152] = 19;
        bArr[153] = 33;
        bArr[154] = 50;
        bArr[155] = 19;
        bArr[156] = 16;
        bArr[157] = -111;
        bArr[158] = 9;
        bArr[159] = 16;
        bArr[160] = -111;
        bArr[161] = 9;
        bArr[162] = 0;
        bArr[163] = 112;
        bArr[164] = 7;
        bArr[165] = 0;
        bArr[166] = 112;
        bArr[167] = 7;
        bArr[168] = 12;
        bArr[169] = 16;
        bArr[170] = -63;
        bArr[171] = 12;
        bArr[172] = 16;
        bArr[173] = -63;
        bArr[174] = 64;
        bArr[175] = -60;
        bArr[176] = 12;
        bArr[177] = 64;
        bArr[178] = -60;
        bArr[179] = 12;
        bArr[180] = 106;
        bArr[181] = 6;
        bArr[182] = -96;
        bArr[183] = 106;
        bArr[184] = 6;
        bArr[185] = -96;
        bArr[186] = -122;
        bArr[187] = 8;
        bArr[188] = 96;
        bArr[189] = -122;
        bArr[190] = 8;
        bArr[191] = 96;
        bArr[192] = 36;
        bArr[193] = -126;
        bArr[194] = 72;
        bArr[195] = 36;
        bArr[196] = -126;
        bArr[197] = 72;
        bArr[198] = -119;
        bArr[199] = 8;
        bArr[200] = -112;
        bArr[201] = -119;
        bArr[202] = 8;
        bArr[203] = -112;
        bArr[204] = -64;
        bArr[205] = 44;
        bArr[206] = 2;
        bArr[207] = -64;
        bArr[208] = 44;
        bArr[209] = 2;
        bArr[210] = 16;
        bArr[211] = 97;
        bArr[212] = 6;
        bArr[213] = 16;
        bArr[214] = 97;
        bArr[215] = 6;
        bArr[216] = 2;
        bArr[217] = 48;
        bArr[218] = 35;
        bArr[219] = 2;
        bArr[220] = 48;
        bArr[221] = 35;
        bArr[222] = 64;
        bArr[223] = 84;
        bArr[224] = 5;
        bArr[225] = 64;
        bArr[226] = 84;
        bArr[227] = 5;
        bArr[228] = 33;
        bArr[229] = -126;
        bArr[230] = 24;
        bArr[231] = 33;
        bArr[232] = -126;
        bArr[233] = 24;
        bArr[234] = -127;
        bArr[235] = 24;
        bArr[236] = 17;
        bArr[237] = -127;
        bArr[238] = 24;
        bArr[239] = 17;
        bArr[240] = 20;
        bArr[241] = -127;
        bArr[242] = 72;
        bArr[243] = 20;
        bArr[244] = -127;
        bArr[245] = 72;
        bArr[246] = -104;
        bArr[247] = 9;
        bArr[248] = -128;
        bArr[249] = -104;
        bArr[250] = 9;
        bArr[251] = -128;
        bArr[252] = 8;
        bArr[253] = -112;
        bArr[254] = -119;
        bArr[255] = 8;
        bArr[256] = -112;
        bArr[257] = -119;
        bArr[258] = 98;
        bArr[259] = 6;
        bArr[260] = 32;
        bArr[261] = 98;
        bArr[262] = 6;
        bArr[263] = 32;
        bArr[264] = 36;
        bArr[265] = 34;
        bArr[266] = 66;
        bArr[267] = 36;
        bArr[268] = 34;
        bArr[269] = 66;
        bArr[270] = -118;
        bArr[271] = 8;
        bArr[272] = -96;
        bArr[273] = -118;
        bArr[274] = 8;
        bArr[275] = -96;
        bArr[276] = -124;
        bArr[277] = 72;
        bArr[278] = 68;
        bArr[279] = -124;
        bArr[280] = 72;
        bArr[281] = 68;
        bArr[282] = -1;
        bArr[283] = -101;
        bArr[284] = -33;
        bArr[285] = -20;
        bArr[286] = -82;
        bArr[287] = 14;
        _randomMask48_48 = bArr;
    }

    private static void createRandomMask48_5() {
        _randomMask48_5 = new byte[]{83, 101, 54, 83, 101, 54, 100, -74, 75, 100, -74, 75, 14, -32, -18, 14, -32, -18, -87, -54, -100, -87, -54, -100, -72, 59, -125, -72, 59, -125};
    }

    private static void createRandomMask48_6() {
        _randomMask48_6 = new byte[]{-47, 77, 20, -47, 77, 20, 69, 52, 83, 69, 52, 83, 34, -46, 45, 34, -46, 45, 22, -63, 108, 22, -63, 108, 11, -96, -70, 11, -96, -70, -24, -114, -120, -24, -114, -120};
    }

    private static void createRandomMask48_7() {
        _randomMask48_7 = new byte[]{-45, 101, 54, -45, 101, 54, 37, 50, 83, 37, 50, 83, 48, -45, 5, 48, -45, 5, 6, 72, 108, 6, 72, 108, -64, -72, 27, -64, -72, 27, 42, -94, -86, 42, -94, -86, -88, 78, -124, -88, 78, -124};
    }

    private static void createRandomMask48_8() {
        _randomMask48_8 = new byte[]{-127, 96, 22, -127, 96, 22, 64, 60, 3, 64, 60, 3, Tnaf.POW_2_WIDTH, -111, 9, Tnaf.POW_2_WIDTH, -111, 9, 6, 80, 101, 6, 80, 101, 32, 74, -124, 32, 74, -124, -118, -96, -86, -118, -96, -86, 51, 3, 48, 51, 3, 48, 76, -124, -56, 76, -124, -56};
    }

    private static void createRandomMask48_9() {
        _randomMask48_9 = new byte[]{-45, 101, 54, -45, 101, 54, 100, 38, 66, 100, 38, 66, 24, 65, -60, 24, 65, -60, -96, 74, 4, -96, 74, 4, -127, 56, 19, -127, 56, 19, 34, -94, 42, 34, -94, 42, 8, 112, -121, 8, 112, -121, 4, -112, 73, 4, -112, 73, 1, -64, 28, 1, -64, 28};
    }

    private static void createRandomMask5_1() {
        byte[] bArr = new byte[2];
        bArr[0] = -8;
        _randomMask5_1 = bArr;
    }

    private static void createRandomMask5_2() {
        byte[] bArr = new byte[4];
        bArr[0] = -88;
        bArr[2] = -48;
        _randomMask5_2 = bArr;
    }

    private static void createRandomMask5_3() {
        _randomMask5_3 = new byte[]{-80, 0, -56, 0, 80, 0};
    }

    private static void createRandomMask5_4() {
        _randomMask5_4 = new byte[]{-56, 0, -80, 0, 80, 0, 40, 0};
    }

    private static void createRandomMask5_5() {
        _randomMask5_5 = new byte[]{-64, 0, 48, 0, 24, 0, -96, 0, 72, 0};
    }

    private static void createRandomMask6_1() {
        byte[] bArr = new byte[2];
        bArr[0] = -4;
        _randomMask6_1 = bArr;
    }

    private static void createRandomMask6_2() {
        byte[] bArr = new byte[4];
        bArr[0] = -88;
        bArr[2] = -44;
        _randomMask6_2 = bArr;
    }

    private static void createRandomMask6_3() {
        _randomMask6_3 = new byte[]{-48, 0, 104, 0, -92, 0};
    }

    private static void createRandomMask6_4() {
        _randomMask6_4 = new byte[]{-88, 0, 88, 0, 100, 0, -108, 0};
    }

    private static void createRandomMask6_5() {
        _randomMask6_5 = new byte[]{-88, 0, -124, 0, 100, 0, -112, 0, 88, 0};
    }

    private static void createRandomMask6_6() {
        _randomMask6_6 = new byte[]{-104, 0, 100, 0, 80, 0, 20, 0, -88, 0, -32, 0};
    }

    private static void createRandomMask7_1() {
        byte[] bArr = new byte[2];
        bArr[0] = -2;
        _randomMask7_1 = bArr;
    }

    private static void createRandomMask7_2() {
        byte[] bArr = new byte[4];
        bArr[0] = -44;
        bArr[2] = -86;
        _randomMask7_2 = bArr;
    }

    private static void createRandomMask7_3() {
        _randomMask7_3 = new byte[]{-48, 0, -86, 0, 100, 0};
    }

    private static void createRandomMask7_4() {
        _randomMask7_4 = new byte[]{-48, 0, -86, 0, 100, 0, 28, 0};
    }

    private static void createRandomMask7_5() {
        _randomMask7_5 = new byte[]{12, 0, -80, 0, 26, 0, -60, 0, 98, 0};
    }

    private static void createRandomMask7_6() {
        _randomMask7_6 = new byte[]{-116, 0, 74, 0, 100, 0, -48, 0, -96, 0, 50, 0};
    }

    private static void createRandomMask7_7() {
        _randomMask7_7 = new byte[]{74, 0, -108, 0, 26, 0, -60, 0, 40, 0, -62, 0, 52, 0};
    }

    private static void createRandomMask8_1() {
        byte[] bArr = new byte[2];
        bArr[0] = -1;
        _randomMask8_1 = bArr;
    }

    private static void createRandomMask8_2() {
        byte[] bArr = new byte[4];
        bArr[0] = -86;
        bArr[2] = -43;
        _randomMask8_2 = bArr;
    }

    private static void createRandomMask8_3() {
        _randomMask8_3 = new byte[]{-59, 0, -110, 0, 106, 0};
    }

    private static void createRandomMask8_4() {
        _randomMask8_4 = new byte[]{69, 0, -76, 0, 106, 0, -119, 0};
    }

    private static void createRandomMask8_5() {
        _randomMask8_5 = new byte[]{-116, 0, -110, 0, 43, 0, 81, 0, 100, 0};
    }

    private static void createRandomMask8_6() {
        _randomMask8_6 = new byte[]{-95, 0, 82, 0, -111, 0, 42, 0, -60, 0, 76, 0};
    }

    private static void createRandomMask8_7() {
        _randomMask8_7 = new byte[]{21, 0, -62, 0, 37, 0, 98, 0, 88, 0, -116, 0, -93, 0};
    }

    private static void createRandomMask8_8() {
        _randomMask8_8 = new byte[]{37, 0, -118, 0, -111, 0, 104, 0, 50, 0, 67, 0, -60, 0, 28, 0};
    }

    private static void createRandomMask9_1() {
        _randomMask9_1 = new byte[]{-1, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask9_2() {
        _randomMask9_2 = new byte[]{-86, ByteCompanionObject.MIN_VALUE, -43, 0};
    }

    private static void createRandomMask9_3() {
        _randomMask9_3 = new byte[]{-91, 0, -56, 0, 82, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask9_4() {
        _randomMask9_4 = new byte[]{-94, 0, -55, 0, 82, ByteCompanionObject.MIN_VALUE, 36, ByteCompanionObject.MIN_VALUE};
    }

    private static void createRandomMask9_5() {
        _randomMask9_5 = new byte[]{-116, 0, 37, 0, -110, ByteCompanionObject.MIN_VALUE, 65, ByteCompanionObject.MIN_VALUE, 88, 0};
    }

    private static void createRandomMask9_6() {
        _randomMask9_6 = new byte[]{-124, ByteCompanionObject.MIN_VALUE, 39, 0, 81, ByteCompanionObject.MIN_VALUE, 26, 0, 104, 0, -119, 0};
    }

    private static void createRandomMask9_7() {
        _randomMask9_7 = new byte[]{-116, 0, 71, 0, -127, ByteCompanionObject.MIN_VALUE, 18, ByteCompanionObject.MIN_VALUE, 88, 0, 40, ByteCompanionObject.MIN_VALUE, -76, 0};
    }

    private static void createRandomMask9_8() {
        _randomMask9_8 = new byte[]{44, 0, -111, 0, 64, ByteCompanionObject.MIN_VALUE, 6, ByteCompanionObject.MIN_VALUE, -56, 0, 69, 0, 48, ByteCompanionObject.MIN_VALUE, -94, 0};
    }

    private static void createRandomMask9_9() {
        _randomMask9_9 = new byte[]{76, 0, 98, 0, -111, 0, 66, ByteCompanionObject.MIN_VALUE, -92, 0, 19, 0, 48, ByteCompanionObject.MIN_VALUE, -120, ByteCompanionObject.MIN_VALUE, 9, 0};
    }

    private static void createRandomPacketMask1() {
        _randomPacketMask1 = new byte[][]{_randomMask1_1};
    }

    private static void createRandomPacketMask10() {
        _randomPacketMask10 = new byte[][]{_randomMask10_1, _randomMask10_2, _randomMask10_3, _randomMask10_4, _randomMask10_5, _randomMask10_6, _randomMask10_7, _randomMask10_8, _randomMask10_9, _randomMask10_10};
    }

    private static void createRandomPacketMask11() {
        _randomPacketMask11 = new byte[][]{_randomMask11_1, _randomMask11_2, _randomMask11_3, _randomMask11_4, _randomMask11_5, _randomMask11_6, _randomMask11_7, _randomMask11_8, _randomMask11_9, _randomMask11_10, _randomMask11_11};
    }

    private static void createRandomPacketMask12() {
        _randomPacketMask12 = new byte[][]{_randomMask12_1, _randomMask12_2, _randomMask12_3, _randomMask12_4, _randomMask12_5, _randomMask12_6, _randomMask12_7, _randomMask12_8, _randomMask12_9, _randomMask12_10, _randomMask12_11, _randomMask12_12};
    }

    private static void createRandomPacketMask13() {
        _randomPacketMask13 = new byte[][]{_randomMask13_1, _randomMask13_2, _randomMask13_3, _randomMask13_4, _randomMask13_5, _randomMask13_6, _randomMask13_7, _randomMask13_8, _randomMask13_9, _randomMask13_10, _randomMask13_11, _randomMask13_12, _randomMask13_13};
    }

    private static void createRandomPacketMask14() {
        _randomPacketMask14 = new byte[][]{_randomMask14_1, _randomMask14_2, _randomMask14_3, _randomMask14_4, _randomMask14_5, _randomMask14_6, _randomMask14_7, _randomMask14_8, _randomMask14_9, _randomMask14_10, _randomMask14_11, _randomMask14_12, _randomMask14_13, _randomMask14_14};
    }

    private static void createRandomPacketMask15() {
        _randomPacketMask15 = new byte[][]{_randomMask15_1, _randomMask15_2, _randomMask15_3, _randomMask15_4, _randomMask15_5, _randomMask15_6, _randomMask15_7, _randomMask15_8, _randomMask15_9, _randomMask15_10, _randomMask15_11, _randomMask15_12, _randomMask15_13, _randomMask15_14, _randomMask15_15};
    }

    private static void createRandomPacketMask16() {
        _randomPacketMask16 = new byte[][]{_randomMask16_1, _randomMask16_2, _randomMask16_3, _randomMask16_4, _randomMask16_5, _randomMask16_6, _randomMask16_7, _randomMask16_8, _randomMask16_9, _randomMask16_10, _randomMask16_11, _randomMask16_12, _randomMask16_13, _randomMask16_14, _randomMask16_15, _randomMask16_16};
    }

    private static void createRandomPacketMask17() {
        _randomPacketMask17 = new byte[][]{_randomMask17_1, _randomMask17_2, _randomMask17_3, _randomMask17_4, _randomMask17_5, _randomMask17_6, _randomMask17_7, _randomMask17_8, _randomMask17_9, _randomMask17_10, _randomMask17_11, _randomMask17_12, _randomMask17_13, _randomMask17_14, _randomMask17_15, _randomMask17_16, _randomMask17_17};
    }

    private static void createRandomPacketMask18() {
        _randomPacketMask18 = new byte[][]{_randomMask18_1, _randomMask18_2, _randomMask18_3, _randomMask18_4, _randomMask18_5, _randomMask18_6, _randomMask18_7, _randomMask18_8, _randomMask18_9, _randomMask18_10, _randomMask18_11, _randomMask18_12, _randomMask18_13, _randomMask18_14, _randomMask18_15, _randomMask18_16, _randomMask18_17, _randomMask18_18};
    }

    private static void createRandomPacketMask19() {
        _randomPacketMask19 = new byte[][]{_randomMask19_1, _randomMask19_2, _randomMask19_3, _randomMask19_4, _randomMask19_5, _randomMask19_6, _randomMask19_7, _randomMask19_8, _randomMask19_9, _randomMask19_10, _randomMask19_11, _randomMask19_12, _randomMask19_13, _randomMask19_14, _randomMask19_15, _randomMask19_16, _randomMask19_17, _randomMask19_18, _randomMask19_19};
    }

    private static void createRandomPacketMask2() {
        _randomPacketMask2 = new byte[][]{_randomMask2_1, _randomMask2_2};
    }

    private static void createRandomPacketMask20() {
        _randomPacketMask20 = new byte[][]{_randomMask20_1, _randomMask20_2, _randomMask20_3, _randomMask20_4, _randomMask20_5, _randomMask20_6, _randomMask20_7, _randomMask20_8, _randomMask20_9, _randomMask20_10, _randomMask20_11, _randomMask20_12, _randomMask20_13, _randomMask20_14, _randomMask20_15, _randomMask20_16, _randomMask20_17, _randomMask20_18, _randomMask20_19, _randomMask20_20};
    }

    private static void createRandomPacketMask21() {
        _randomPacketMask21 = new byte[][]{_randomMask21_1, _randomMask21_2, _randomMask21_3, _randomMask21_4, _randomMask21_5, _randomMask21_6, _randomMask21_7, _randomMask21_8, _randomMask21_9, _randomMask21_10, _randomMask21_11, _randomMask21_12, _randomMask21_13, _randomMask21_14, _randomMask21_15, _randomMask21_16, _randomMask21_17, _randomMask21_18, _randomMask21_19, _randomMask21_20, _randomMask21_21};
    }

    private static void createRandomPacketMask22() {
        _randomPacketMask22 = new byte[][]{_randomMask22_1, _randomMask22_2, _randomMask22_3, _randomMask22_4, _randomMask22_5, _randomMask22_6, _randomMask22_7, _randomMask22_8, _randomMask22_9, _randomMask22_10, _randomMask22_11, _randomMask22_12, _randomMask22_13, _randomMask22_14, _randomMask22_15, _randomMask22_16, _randomMask22_17, _randomMask22_18, _randomMask22_19, _randomMask22_20, _randomMask22_21, _randomMask22_22};
    }

    private static void createRandomPacketMask23() {
        _randomPacketMask23 = new byte[][]{_randomMask23_1, _randomMask23_2, _randomMask23_3, _randomMask23_4, _randomMask23_5, _randomMask23_6, _randomMask23_7, _randomMask23_8, _randomMask23_9, _randomMask23_10, _randomMask23_11, _randomMask23_12, _randomMask23_13, _randomMask23_14, _randomMask23_15, _randomMask23_16, _randomMask23_17, _randomMask23_18, _randomMask23_19, _randomMask23_20, _randomMask23_21, _randomMask23_22, _randomMask23_23};
    }

    private static void createRandomPacketMask24() {
        _randomPacketMask24 = new byte[][]{_randomMask24_1, _randomMask24_2, _randomMask24_3, _randomMask24_4, _randomMask24_5, _randomMask24_6, _randomMask24_7, _randomMask24_8, _randomMask24_9, _randomMask24_10, _randomMask24_11, _randomMask24_12, _randomMask24_13, _randomMask24_14, _randomMask24_15, _randomMask24_16, _randomMask24_17, _randomMask24_18, _randomMask24_19, _randomMask24_20, _randomMask24_21, _randomMask24_22, _randomMask24_23, _randomMask24_24};
    }

    private static void createRandomPacketMask25() {
        _randomPacketMask25 = new byte[][]{_randomMask25_1, _randomMask25_2, _randomMask25_3, _randomMask25_4, _randomMask25_5, _randomMask25_6, _randomMask25_7, _randomMask25_8, _randomMask25_9, _randomMask25_10, _randomMask25_11, _randomMask25_12, _randomMask25_13, _randomMask25_14, _randomMask25_15, _randomMask25_16, _randomMask25_17, _randomMask25_18, _randomMask25_19, _randomMask25_20, _randomMask25_21, _randomMask25_22, _randomMask25_23, _randomMask25_24, _randomMask25_25};
    }

    private static void createRandomPacketMask26() {
        _randomPacketMask26 = new byte[][]{_randomMask26_1, _randomMask26_2, _randomMask26_3, _randomMask26_4, _randomMask26_5, _randomMask26_6, _randomMask26_7, _randomMask26_8, _randomMask26_9, _randomMask26_10, _randomMask26_11, _randomMask26_12, _randomMask26_13, _randomMask26_14, _randomMask26_15, _randomMask26_16, _randomMask26_17, _randomMask26_18, _randomMask26_19, _randomMask26_20, _randomMask26_21, _randomMask26_22, _randomMask26_23, _randomMask26_24, _randomMask26_25, _randomMask26_26};
    }

    private static void createRandomPacketMask27() {
        _randomPacketMask27 = new byte[][]{_randomMask27_1, _randomMask27_2, _randomMask27_3, _randomMask27_4, _randomMask27_5, _randomMask27_6, _randomMask27_7, _randomMask27_8, _randomMask27_9, _randomMask27_10, _randomMask27_11, _randomMask27_12, _randomMask27_13, _randomMask27_14, _randomMask27_15, _randomMask27_16, _randomMask27_17, _randomMask27_18, _randomMask27_19, _randomMask27_20, _randomMask27_21, _randomMask27_22, _randomMask27_23, _randomMask27_24, _randomMask27_25, _randomMask27_26, _randomMask27_27};
    }

    private static void createRandomPacketMask28() {
        _randomPacketMask28 = new byte[][]{_randomMask28_1, _randomMask28_2, _randomMask28_3, _randomMask28_4, _randomMask28_5, _randomMask28_6, _randomMask28_7, _randomMask28_8, _randomMask28_9, _randomMask28_10, _randomMask28_11, _randomMask28_12, _randomMask28_13, _randomMask28_14, _randomMask28_15, _randomMask28_16, _randomMask28_17, _randomMask28_18, _randomMask28_19, _randomMask28_20, _randomMask28_21, _randomMask28_22, _randomMask28_23, _randomMask28_24, _randomMask28_25, _randomMask28_26, _randomMask28_27, _randomMask28_28};
    }

    private static void createRandomPacketMask29() {
        _randomPacketMask29 = new byte[][]{_randomMask29_1, _randomMask29_2, _randomMask29_3, _randomMask29_4, _randomMask29_5, _randomMask29_6, _randomMask29_7, _randomMask29_8, _randomMask29_9, _randomMask29_10, _randomMask29_11, _randomMask29_12, _randomMask29_13, _randomMask29_14, _randomMask29_15, _randomMask29_16, _randomMask29_17, _randomMask29_18, _randomMask29_19, _randomMask29_20, _randomMask29_21, _randomMask29_22, _randomMask29_23, _randomMask29_24, _randomMask29_25, _randomMask29_26, _randomMask29_27, _randomMask29_28, _randomMask29_29};
    }

    private static void createRandomPacketMask3() {
        _randomPacketMask3 = new byte[][]{_randomMask3_1, _randomMask3_2, _randomMask3_3};
    }

    private static void createRandomPacketMask30() {
        _randomPacketMask30 = new byte[][]{_randomMask30_1, _randomMask30_2, _randomMask30_3, _randomMask30_4, _randomMask30_5, _randomMask30_6, _randomMask30_7, _randomMask30_8, _randomMask30_9, _randomMask30_10, _randomMask30_11, _randomMask30_12, _randomMask30_13, _randomMask30_14, _randomMask30_15, _randomMask30_16, _randomMask30_17, _randomMask30_18, _randomMask30_19, _randomMask30_20, _randomMask30_21, _randomMask30_22, _randomMask30_23, _randomMask30_24, _randomMask30_25, _randomMask30_26, _randomMask30_27, _randomMask30_28, _randomMask30_29, _randomMask30_30};
    }

    private static void createRandomPacketMask31() {
        _randomPacketMask31 = new byte[][]{_randomMask31_1, _randomMask31_2, _randomMask31_3, _randomMask31_4, _randomMask31_5, _randomMask31_6, _randomMask31_7, _randomMask31_8, _randomMask31_9, _randomMask31_10, _randomMask31_11, _randomMask31_12, _randomMask31_13, _randomMask31_14, _randomMask31_15, _randomMask31_16, _randomMask31_17, _randomMask31_18, _randomMask31_19, _randomMask31_20, _randomMask31_21, _randomMask31_22, _randomMask31_23, _randomMask31_24, _randomMask31_25, _randomMask31_26, _randomMask31_27, _randomMask31_28, _randomMask31_29, _randomMask31_30, _randomMask31_31};
    }

    private static void createRandomPacketMask32() {
        _randomPacketMask32 = new byte[][]{_randomMask32_1, _randomMask32_2, _randomMask32_3, _randomMask32_4, _randomMask32_5, _randomMask32_6, _randomMask32_7, _randomMask32_8, _randomMask32_9, _randomMask32_10, _randomMask32_11, _randomMask32_12, _randomMask32_13, _randomMask32_14, _randomMask32_15, _randomMask32_16, _randomMask32_17, _randomMask32_18, _randomMask32_19, _randomMask32_20, _randomMask32_21, _randomMask32_22, _randomMask32_23, _randomMask32_24, _randomMask32_25, _randomMask32_26, _randomMask32_27, _randomMask32_28, _randomMask32_29, _randomMask32_30, _randomMask32_31, _randomMask32_32};
    }

    private static void createRandomPacketMask33() {
        _randomPacketMask33 = new byte[][]{_randomMask33_1, _randomMask33_2, _randomMask33_3, _randomMask33_4, _randomMask33_5, _randomMask33_6, _randomMask33_7, _randomMask33_8, _randomMask33_9, _randomMask33_10, _randomMask33_11, _randomMask33_12, _randomMask33_13, _randomMask33_14, _randomMask33_15, _randomMask33_16, _randomMask33_17, _randomMask33_18, _randomMask33_19, _randomMask33_20, _randomMask33_21, _randomMask33_22, _randomMask33_23, _randomMask33_24, _randomMask33_25, _randomMask33_26, _randomMask33_27, _randomMask33_28, _randomMask33_29, _randomMask33_30, _randomMask33_31, _randomMask33_32, _randomMask33_33};
    }

    private static void createRandomPacketMask34() {
        _randomPacketMask34 = new byte[][]{_randomMask34_1, _randomMask34_2, _randomMask34_3, _randomMask34_4, _randomMask34_5, _randomMask34_6, _randomMask34_7, _randomMask34_8, _randomMask34_9, _randomMask34_10, _randomMask34_11, _randomMask34_12, _randomMask34_13, _randomMask34_14, _randomMask34_15, _randomMask34_16, _randomMask34_17, _randomMask34_18, _randomMask34_19, _randomMask34_20, _randomMask34_21, _randomMask34_22, _randomMask34_23, _randomMask34_24, _randomMask34_25, _randomMask34_26, _randomMask34_27, _randomMask34_28, _randomMask34_29, _randomMask34_30, _randomMask34_31, _randomMask34_32, _randomMask34_33, _randomMask34_34};
    }

    private static void createRandomPacketMask35() {
        _randomPacketMask35 = new byte[][]{_randomMask35_1, _randomMask35_2, _randomMask35_3, _randomMask35_4, _randomMask35_5, _randomMask35_6, _randomMask35_7, _randomMask35_8, _randomMask35_9, _randomMask35_10, _randomMask35_11, _randomMask35_12, _randomMask35_13, _randomMask35_14, _randomMask35_15, _randomMask35_16, _randomMask35_17, _randomMask35_18, _randomMask35_19, _randomMask35_20, _randomMask35_21, _randomMask35_22, _randomMask35_23, _randomMask35_24, _randomMask35_25, _randomMask35_26, _randomMask35_27, _randomMask35_28, _randomMask35_29, _randomMask35_30, _randomMask35_31, _randomMask35_32, _randomMask35_33, _randomMask35_34, _randomMask35_35};
    }

    private static void createRandomPacketMask36() {
        _randomPacketMask36 = new byte[][]{_randomMask36_1, _randomMask36_2, _randomMask36_3, _randomMask36_4, _randomMask36_5, _randomMask36_6, _randomMask36_7, _randomMask36_8, _randomMask36_9, _randomMask36_10, _randomMask36_11, _randomMask36_12, _randomMask36_13, _randomMask36_14, _randomMask36_15, _randomMask36_16, _randomMask36_17, _randomMask36_18, _randomMask36_19, _randomMask36_20, _randomMask36_21, _randomMask36_22, _randomMask36_23, _randomMask36_24, _randomMask36_25, _randomMask36_26, _randomMask36_27, _randomMask36_28, _randomMask36_29, _randomMask36_30, _randomMask36_31, _randomMask36_32, _randomMask36_33, _randomMask36_34, _randomMask36_35, _randomMask36_36};
    }

    private static void createRandomPacketMask37() {
        _randomPacketMask37 = new byte[][]{_randomMask37_1, _randomMask37_2, _randomMask37_3, _randomMask37_4, _randomMask37_5, _randomMask37_6, _randomMask37_7, _randomMask37_8, _randomMask37_9, _randomMask37_10, _randomMask37_11, _randomMask37_12, _randomMask37_13, _randomMask37_14, _randomMask37_15, _randomMask37_16, _randomMask37_17, _randomMask37_18, _randomMask37_19, _randomMask37_20, _randomMask37_21, _randomMask37_22, _randomMask37_23, _randomMask37_24, _randomMask37_25, _randomMask37_26, _randomMask37_27, _randomMask37_28, _randomMask37_29, _randomMask37_30, _randomMask37_31, _randomMask37_32, _randomMask37_33, _randomMask37_34, _randomMask37_35, _randomMask37_36, _randomMask37_37};
    }

    private static void createRandomPacketMask38() {
        _randomPacketMask38 = new byte[][]{_randomMask38_1, _randomMask38_2, _randomMask38_3, _randomMask38_4, _randomMask38_5, _randomMask38_6, _randomMask38_7, _randomMask38_8, _randomMask38_9, _randomMask38_10, _randomMask38_11, _randomMask38_12, _randomMask38_13, _randomMask38_14, _randomMask38_15, _randomMask38_16, _randomMask38_17, _randomMask38_18, _randomMask38_19, _randomMask38_20, _randomMask38_21, _randomMask38_22, _randomMask38_23, _randomMask38_24, _randomMask38_25, _randomMask38_26, _randomMask38_27, _randomMask38_28, _randomMask38_29, _randomMask38_30, _randomMask38_31, _randomMask38_32, _randomMask38_33, _randomMask38_34, _randomMask38_35, _randomMask38_36, _randomMask38_37, _randomMask38_38};
    }

    private static void createRandomPacketMask39() {
        _randomPacketMask39 = new byte[][]{_randomMask39_1, _randomMask39_2, _randomMask39_3, _randomMask39_4, _randomMask39_5, _randomMask39_6, _randomMask39_7, _randomMask39_8, _randomMask39_9, _randomMask39_10, _randomMask39_11, _randomMask39_12, _randomMask39_13, _randomMask39_14, _randomMask39_15, _randomMask39_16, _randomMask39_17, _randomMask39_18, _randomMask39_19, _randomMask39_20, _randomMask39_21, _randomMask39_22, _randomMask39_23, _randomMask39_24, _randomMask39_25, _randomMask39_26, _randomMask39_27, _randomMask39_28, _randomMask39_29, _randomMask39_30, _randomMask39_31, _randomMask39_32, _randomMask39_33, _randomMask39_34, _randomMask39_35, _randomMask39_36, _randomMask39_37, _randomMask39_38, _randomMask39_39};
    }

    private static void createRandomPacketMask4() {
        _randomPacketMask4 = new byte[][]{_randomMask4_1, _randomMask4_2, _randomMask4_3, _randomMask4_4};
    }

    private static void createRandomPacketMask40() {
        _randomPacketMask40 = new byte[][]{_randomMask40_1, _randomMask40_2, _randomMask40_3, _randomMask40_4, _randomMask40_5, _randomMask40_6, _randomMask40_7, _randomMask40_8, _randomMask40_9, _randomMask40_10, _randomMask40_11, _randomMask40_12, _randomMask40_13, _randomMask40_14, _randomMask40_15, _randomMask40_16, _randomMask40_17, _randomMask40_18, _randomMask40_19, _randomMask40_20, _randomMask40_21, _randomMask40_22, _randomMask40_23, _randomMask40_24, _randomMask40_25, _randomMask40_26, _randomMask40_27, _randomMask40_28, _randomMask40_29, _randomMask40_30, _randomMask40_31, _randomMask40_32, _randomMask40_33, _randomMask40_34, _randomMask40_35, _randomMask40_36, _randomMask40_37, _randomMask40_38, _randomMask40_39, _randomMask40_40};
    }

    private static void createRandomPacketMask41() {
        _randomPacketMask41 = new byte[][]{_randomMask41_1, _randomMask41_2, _randomMask41_3, _randomMask41_4, _randomMask41_5, _randomMask41_6, _randomMask41_7, _randomMask41_8, _randomMask41_9, _randomMask41_10, _randomMask41_11, _randomMask41_12, _randomMask41_13, _randomMask41_14, _randomMask41_15, _randomMask41_16, _randomMask41_17, _randomMask41_18, _randomMask41_19, _randomMask41_20, _randomMask41_21, _randomMask41_22, _randomMask41_23, _randomMask41_24, _randomMask41_25, _randomMask41_26, _randomMask41_27, _randomMask41_28, _randomMask41_29, _randomMask41_30, _randomMask41_31, _randomMask41_32, _randomMask41_33, _randomMask41_34, _randomMask41_35, _randomMask41_36, _randomMask41_37, _randomMask41_38, _randomMask41_39, _randomMask41_40, _randomMask41_41};
    }

    private static void createRandomPacketMask42() {
        _randomPacketMask42 = new byte[][]{_randomMask42_1, _randomMask42_2, _randomMask42_3, _randomMask42_4, _randomMask42_5, _randomMask42_6, _randomMask42_7, _randomMask42_8, _randomMask42_9, _randomMask42_10, _randomMask42_11, _randomMask42_12, _randomMask42_13, _randomMask42_14, _randomMask42_15, _randomMask42_16, _randomMask42_17, _randomMask42_18, _randomMask42_19, _randomMask42_20, _randomMask42_21, _randomMask42_22, _randomMask42_23, _randomMask42_24, _randomMask42_25, _randomMask42_26, _randomMask42_27, _randomMask42_28, _randomMask42_29, _randomMask42_30, _randomMask42_31, _randomMask42_32, _randomMask42_33, _randomMask42_34, _randomMask42_35, _randomMask42_36, _randomMask42_37, _randomMask42_38, _randomMask42_39, _randomMask42_40, _randomMask42_41, _randomMask42_42};
    }

    private static void createRandomPacketMask43() {
        _randomPacketMask43 = new byte[][]{_randomMask43_1, _randomMask43_2, _randomMask43_3, _randomMask43_4, _randomMask43_5, _randomMask43_6, _randomMask43_7, _randomMask43_8, _randomMask43_9, _randomMask43_10, _randomMask43_11, _randomMask43_12, _randomMask43_13, _randomMask43_14, _randomMask43_15, _randomMask43_16, _randomMask43_17, _randomMask43_18, _randomMask43_19, _randomMask43_20, _randomMask43_21, _randomMask43_22, _randomMask43_23, _randomMask43_24, _randomMask43_25, _randomMask43_26, _randomMask43_27, _randomMask43_28, _randomMask43_29, _randomMask43_30, _randomMask43_31, _randomMask43_32, _randomMask43_33, _randomMask43_34, _randomMask43_35, _randomMask43_36, _randomMask43_37, _randomMask43_38, _randomMask43_39, _randomMask43_40, _randomMask43_41, _randomMask43_42, _randomMask43_43};
    }

    private static void createRandomPacketMask44() {
        _randomPacketMask44 = new byte[][]{_randomMask44_1, _randomMask44_2, _randomMask44_3, _randomMask44_4, _randomMask44_5, _randomMask44_6, _randomMask44_7, _randomMask44_8, _randomMask44_9, _randomMask44_10, _randomMask44_11, _randomMask44_12, _randomMask44_13, _randomMask44_14, _randomMask44_15, _randomMask44_16, _randomMask44_17, _randomMask44_18, _randomMask44_19, _randomMask44_20, _randomMask44_21, _randomMask44_22, _randomMask44_23, _randomMask44_24, _randomMask44_25, _randomMask44_26, _randomMask44_27, _randomMask44_28, _randomMask44_29, _randomMask44_30, _randomMask44_31, _randomMask44_32, _randomMask44_33, _randomMask44_34, _randomMask44_35, _randomMask44_36, _randomMask44_37, _randomMask44_38, _randomMask44_39, _randomMask44_40, _randomMask44_41, _randomMask44_42, _randomMask44_43, _randomMask44_44};
    }

    private static void createRandomPacketMask45() {
        _randomPacketMask45 = new byte[][]{_randomMask45_1, _randomMask45_2, _randomMask45_3, _randomMask45_4, _randomMask45_5, _randomMask45_6, _randomMask45_7, _randomMask45_8, _randomMask45_9, _randomMask45_10, _randomMask45_11, _randomMask45_12, _randomMask45_13, _randomMask45_14, _randomMask45_15, _randomMask45_16, _randomMask45_17, _randomMask45_18, _randomMask45_19, _randomMask45_20, _randomMask45_21, _randomMask45_22, _randomMask45_23, _randomMask45_24, _randomMask45_25, _randomMask45_26, _randomMask45_27, _randomMask45_28, _randomMask45_29, _randomMask45_30, _randomMask45_31, _randomMask45_32, _randomMask45_33, _randomMask45_34, _randomMask45_35, _randomMask45_36, _randomMask45_37, _randomMask45_38, _randomMask45_39, _randomMask45_40, _randomMask45_41, _randomMask45_42, _randomMask45_43, _randomMask45_44, _randomMask45_45};
    }

    private static void createRandomPacketMask46() {
        _randomPacketMask46 = new byte[][]{_randomMask46_1, _randomMask46_2, _randomMask46_3, _randomMask46_4, _randomMask46_5, _randomMask46_6, _randomMask46_7, _randomMask46_8, _randomMask46_9, _randomMask46_10, _randomMask46_11, _randomMask46_12, _randomMask46_13, _randomMask46_14, _randomMask46_15, _randomMask46_16, _randomMask46_17, _randomMask46_18, _randomMask46_19, _randomMask46_20, _randomMask46_21, _randomMask46_22, _randomMask46_23, _randomMask46_24, _randomMask46_25, _randomMask46_26, _randomMask46_27, _randomMask46_28, _randomMask46_29, _randomMask46_30, _randomMask46_31, _randomMask46_32, _randomMask46_33, _randomMask46_34, _randomMask46_35, _randomMask46_36, _randomMask46_37, _randomMask46_38, _randomMask46_39, _randomMask46_40, _randomMask46_41, _randomMask46_42, _randomMask46_43, _randomMask46_44, _randomMask46_45, _randomMask46_46};
    }

    private static void createRandomPacketMask47() {
        _randomPacketMask47 = new byte[][]{_randomMask47_1, _randomMask47_2, _randomMask47_3, _randomMask47_4, _randomMask47_5, _randomMask47_6, _randomMask47_7, _randomMask47_8, _randomMask47_9, _randomMask47_10, _randomMask47_11, _randomMask47_12, _randomMask47_13, _randomMask47_14, _randomMask47_15, _randomMask47_16, _randomMask47_17, _randomMask47_18, _randomMask47_19, _randomMask47_20, _randomMask47_21, _randomMask47_22, _randomMask47_23, _randomMask47_24, _randomMask47_25, _randomMask47_26, _randomMask47_27, _randomMask47_28, _randomMask47_29, _randomMask47_30, _randomMask47_31, _randomMask47_32, _randomMask47_33, _randomMask47_34, _randomMask47_35, _randomMask47_36, _randomMask47_37, _randomMask47_38, _randomMask47_39, _randomMask47_40, _randomMask47_41, _randomMask47_42, _randomMask47_43, _randomMask47_44, _randomMask47_45, _randomMask47_46, _randomMask47_47};
    }

    private static void createRandomPacketMask48() {
        _randomPacketMask48 = new byte[][]{_randomMask48_1, _randomMask48_2, _randomMask48_3, _randomMask48_4, _randomMask48_5, _randomMask48_6, _randomMask48_7, _randomMask48_8, _randomMask48_9, _randomMask48_10, _randomMask48_11, _randomMask48_12, _randomMask48_13, _randomMask48_14, _randomMask48_15, _randomMask48_16, _randomMask48_17, _randomMask48_18, _randomMask48_19, _randomMask48_20, _randomMask48_21, _randomMask48_22, _randomMask48_23, _randomMask48_24, _randomMask48_25, _randomMask48_26, _randomMask48_27, _randomMask48_28, _randomMask48_29, _randomMask48_30, _randomMask48_31, _randomMask48_32, _randomMask48_33, _randomMask48_34, _randomMask48_35, _randomMask48_36, _randomMask48_37, _randomMask48_38, _randomMask48_39, _randomMask48_40, _randomMask48_41, _randomMask48_42, _randomMask48_43, _randomMask48_44, _randomMask48_45, _randomMask48_46, _randomMask48_47, _randomMask48_48};
    }

    private static void createRandomPacketMask5() {
        _randomPacketMask5 = new byte[][]{_randomMask5_1, _randomMask5_2, _randomMask5_3, _randomMask5_4, _randomMask5_5};
    }

    private static void createRandomPacketMask6() {
        _randomPacketMask6 = new byte[][]{_randomMask6_1, _randomMask6_2, _randomMask6_3, _randomMask6_4, _randomMask6_5, _randomMask6_6};
    }

    private static void createRandomPacketMask7() {
        _randomPacketMask7 = new byte[][]{_randomMask7_1, _randomMask7_2, _randomMask7_3, _randomMask7_4, _randomMask7_5, _randomMask7_6, _randomMask7_7};
    }

    private static void createRandomPacketMask8() {
        _randomPacketMask8 = new byte[][]{_randomMask8_1, _randomMask8_2, _randomMask8_3, _randomMask8_4, _randomMask8_5, _randomMask8_6, _randomMask8_7, _randomMask8_8};
    }

    private static void createRandomPacketMask9() {
        _randomPacketMask9 = new byte[][]{_randomMask9_1, _randomMask9_2, _randomMask9_3, _randomMask9_4, _randomMask9_5, _randomMask9_6, _randomMask9_7, _randomMask9_8, _randomMask9_9};
    }

    private static void createRandomPacketMaskTable() {
        _randomPacketMaskTable = new byte[][][]{_randomPacketMask1, _randomPacketMask2, _randomPacketMask3, _randomPacketMask4, _randomPacketMask5, _randomPacketMask6, _randomPacketMask7, _randomPacketMask8, _randomPacketMask9, _randomPacketMask10, _randomPacketMask11, _randomPacketMask12, _randomPacketMask13, _randomPacketMask14, _randomPacketMask15, _randomPacketMask16, _randomPacketMask17, _randomPacketMask18, _randomPacketMask19, _randomPacketMask20, _randomPacketMask21, _randomPacketMask22, _randomPacketMask23, _randomPacketMask24, _randomPacketMask25, _randomPacketMask26, _randomPacketMask27, _randomPacketMask28, _randomPacketMask29, _randomPacketMask30, _randomPacketMask31, _randomPacketMask32, _randomPacketMask33, _randomPacketMask34, _randomPacketMask35, _randomPacketMask36, _randomPacketMask37, _randomPacketMask38, _randomPacketMask39, _randomPacketMask40, _randomPacketMask41, _randomPacketMask42, _randomPacketMask43, _randomPacketMask44, _randomPacketMask45, _randomPacketMask46, _randomPacketMask47, _randomPacketMask48};
    }

    static {
        createRandomMask10_1();
        createRandomMask10_10();
        createRandomMask10_2();
        createRandomMask10_3();
        createRandomMask10_4();
        createRandomMask10_5();
        createRandomMask10_6();
        createRandomMask10_7();
        createRandomMask10_8();
        createRandomMask10_9();
        createRandomMask11_1();
        createRandomMask11_10();
        createRandomMask11_11();
        createRandomMask11_2();
        createRandomMask11_3();
        createRandomMask11_4();
        createRandomMask11_5();
        createRandomMask11_6();
        createRandomMask11_7();
        createRandomMask11_8();
        createRandomMask11_9();
        createRandomMask12_1();
        createRandomMask12_10();
        createRandomMask12_11();
        createRandomMask12_12();
        createRandomMask12_2();
        createRandomMask12_3();
        createRandomMask12_4();
        createRandomMask12_5();
        createRandomMask12_6();
        createRandomMask12_7();
        createRandomMask12_8();
        createRandomMask12_9();
        createRandomMask13_1();
        createRandomMask13_10();
        createRandomMask13_11();
        createRandomMask13_12();
        createRandomMask13_13();
        createRandomMask13_2();
        createRandomMask13_3();
        createRandomMask13_4();
        createRandomMask13_5();
        createRandomMask13_6();
        createRandomMask13_7();
        createRandomMask13_8();
        createRandomMask13_9();
        createRandomMask14_1();
        createRandomMask14_10();
        createRandomMask14_11();
        createRandomMask14_12();
        createRandomMask14_13();
        createRandomMask14_14();
        createRandomMask14_2();
        createRandomMask14_3();
        createRandomMask14_4();
        createRandomMask14_5();
        createRandomMask14_6();
        createRandomMask14_7();
        createRandomMask14_8();
        createRandomMask14_9();
        createRandomMask15_1();
        createRandomMask15_10();
        createRandomMask15_11();
        createRandomMask15_12();
        createRandomMask15_13();
        createRandomMask15_14();
        createRandomMask15_15();
        createRandomMask15_2();
        createRandomMask15_3();
        createRandomMask15_4();
        createRandomMask15_5();
        createRandomMask15_6();
        createRandomMask15_7();
        createRandomMask15_8();
        createRandomMask15_9();
        createRandomMask16_1();
        createRandomMask16_10();
        createRandomMask16_11();
        createRandomMask16_12();
        createRandomMask16_13();
        createRandomMask16_14();
        createRandomMask16_15();
        createRandomMask16_16();
        createRandomMask16_2();
        createRandomMask16_3();
        createRandomMask16_4();
        createRandomMask16_5();
        createRandomMask16_6();
        createRandomMask16_7();
        createRandomMask16_8();
        createRandomMask16_9();
        createRandomMask17_1();
        createRandomMask17_10();
        createRandomMask17_11();
        createRandomMask17_12();
        createRandomMask17_13();
        createRandomMask17_14();
        createRandomMask17_15();
        createRandomMask17_16();
        createRandomMask17_17();
        createRandomMask17_2();
        createRandomMask17_3();
        createRandomMask17_4();
        createRandomMask17_5();
        createRandomMask17_6();
        createRandomMask17_7();
        createRandomMask17_8();
        createRandomMask17_9();
        createRandomMask18_1();
        createRandomMask18_10();
        createRandomMask18_11();
        createRandomMask18_12();
        createRandomMask18_13();
        createRandomMask18_14();
        createRandomMask18_15();
        createRandomMask18_16();
        createRandomMask18_17();
        createRandomMask18_18();
        createRandomMask18_2();
        createRandomMask18_3();
        createRandomMask18_4();
        createRandomMask18_5();
        createRandomMask18_6();
        createRandomMask18_7();
        createRandomMask18_8();
        createRandomMask18_9();
        createRandomMask19_1();
        createRandomMask19_10();
        createRandomMask19_11();
        createRandomMask19_12();
        createRandomMask19_13();
        createRandomMask19_14();
        createRandomMask19_15();
        createRandomMask19_16();
        createRandomMask19_17();
        createRandomMask19_18();
        createRandomMask19_19();
        createRandomMask19_2();
        createRandomMask19_3();
        createRandomMask19_4();
        createRandomMask19_5();
        createRandomMask19_6();
        createRandomMask19_7();
        createRandomMask19_8();
        createRandomMask19_9();
        createRandomMask1_1();
        createRandomMask20_1();
        createRandomMask20_10();
        createRandomMask20_11();
        createRandomMask20_12();
        createRandomMask20_13();
        createRandomMask20_14();
        createRandomMask20_15();
        createRandomMask20_16();
        createRandomMask20_17();
        createRandomMask20_18();
        createRandomMask20_19();
        createRandomMask20_2();
        createRandomMask20_20();
        createRandomMask20_3();
        createRandomMask20_4();
        createRandomMask20_5();
        createRandomMask20_6();
        createRandomMask20_7();
        createRandomMask20_8();
        createRandomMask20_9();
        createRandomMask21_1();
        createRandomMask21_10();
        createRandomMask21_11();
        createRandomMask21_12();
        createRandomMask21_13();
        createRandomMask21_14();
        createRandomMask21_15();
        createRandomMask21_16();
        createRandomMask21_17();
        createRandomMask21_18();
        createRandomMask21_19();
        createRandomMask21_2();
        createRandomMask21_20();
        createRandomMask21_21();
        createRandomMask21_3();
        createRandomMask21_4();
        createRandomMask21_5();
        createRandomMask21_6();
        createRandomMask21_7();
        createRandomMask21_8();
        createRandomMask21_9();
        createRandomMask22_1();
        createRandomMask22_10();
        createRandomMask22_11();
        createRandomMask22_12();
        createRandomMask22_13();
        createRandomMask22_14();
        createRandomMask22_15();
        createRandomMask22_16();
        createRandomMask22_17();
        createRandomMask22_18();
        createRandomMask22_19();
        createRandomMask22_2();
        createRandomMask22_20();
        createRandomMask22_21();
        createRandomMask22_22();
        createRandomMask22_3();
        createRandomMask22_4();
        createRandomMask22_5();
        createRandomMask22_6();
        createRandomMask22_7();
        createRandomMask22_8();
        createRandomMask22_9();
        createRandomMask23_1();
        createRandomMask23_10();
        createRandomMask23_11();
        createRandomMask23_12();
        createRandomMask23_13();
        createRandomMask23_14();
        createRandomMask23_15();
        createRandomMask23_16();
        createRandomMask23_17();
        createRandomMask23_18();
        createRandomMask23_19();
        createRandomMask23_2();
        createRandomMask23_20();
        createRandomMask23_21();
        createRandomMask23_22();
        createRandomMask23_23();
        createRandomMask23_3();
        createRandomMask23_4();
        createRandomMask23_5();
        createRandomMask23_6();
        createRandomMask23_7();
        createRandomMask23_8();
        createRandomMask23_9();
        createRandomMask24_1();
        createRandomMask24_10();
        createRandomMask24_11();
        createRandomMask24_12();
        createRandomMask24_13();
        createRandomMask24_14();
        createRandomMask24_15();
        createRandomMask24_16();
        createRandomMask24_17();
        createRandomMask24_18();
        createRandomMask24_19();
        createRandomMask24_2();
        createRandomMask24_20();
        createRandomMask24_21();
        createRandomMask24_22();
        createRandomMask24_23();
        createRandomMask24_24();
        createRandomMask24_3();
        createRandomMask24_4();
        createRandomMask24_5();
        createRandomMask24_6();
        createRandomMask24_7();
        createRandomMask24_8();
        createRandomMask24_9();
        createRandomMask25_1();
        createRandomMask25_10();
        createRandomMask25_11();
        createRandomMask25_12();
        createRandomMask25_13();
        createRandomMask25_14();
        createRandomMask25_15();
        createRandomMask25_16();
        createRandomMask25_17();
        createRandomMask25_18();
        createRandomMask25_19();
        createRandomMask25_2();
        createRandomMask25_20();
        createRandomMask25_21();
        createRandomMask25_22();
        createRandomMask25_23();
        createRandomMask25_24();
        createRandomMask25_25();
        createRandomMask25_3();
        createRandomMask25_4();
        createRandomMask25_5();
        createRandomMask25_6();
        createRandomMask25_7();
        createRandomMask25_8();
        createRandomMask25_9();
        createRandomMask26_1();
        createRandomMask26_10();
        createRandomMask26_11();
        createRandomMask26_12();
        createRandomMask26_13();
        createRandomMask26_14();
        createRandomMask26_15();
        createRandomMask26_16();
        createRandomMask26_17();
        createRandomMask26_18();
        createRandomMask26_19();
        createRandomMask26_2();
        createRandomMask26_20();
        createRandomMask26_21();
        createRandomMask26_22();
        createRandomMask26_23();
        createRandomMask26_24();
        createRandomMask26_25();
        createRandomMask26_26();
        createRandomMask26_3();
        createRandomMask26_4();
        createRandomMask26_5();
        createRandomMask26_6();
        createRandomMask26_7();
        createRandomMask26_8();
        createRandomMask26_9();
        createRandomMask27_1();
        createRandomMask27_10();
        createRandomMask27_11();
        createRandomMask27_12();
        createRandomMask27_13();
        createRandomMask27_14();
        createRandomMask27_15();
        createRandomMask27_16();
        createRandomMask27_17();
        createRandomMask27_18();
        createRandomMask27_19();
        createRandomMask27_2();
        createRandomMask27_20();
        createRandomMask27_21();
        createRandomMask27_22();
        createRandomMask27_23();
        createRandomMask27_24();
        createRandomMask27_25();
        createRandomMask27_26();
        createRandomMask27_27();
        createRandomMask27_3();
        createRandomMask27_4();
        createRandomMask27_5();
        createRandomMask27_6();
        createRandomMask27_7();
        createRandomMask27_8();
        createRandomMask27_9();
        createRandomMask28_1();
        createRandomMask28_10();
        createRandomMask28_11();
        createRandomMask28_12();
        createRandomMask28_13();
        createRandomMask28_14();
        createRandomMask28_15();
        createRandomMask28_16();
        createRandomMask28_17();
        createRandomMask28_18();
        createRandomMask28_19();
        createRandomMask28_2();
        createRandomMask28_20();
        createRandomMask28_21();
        createRandomMask28_22();
        createRandomMask28_23();
        createRandomMask28_24();
        createRandomMask28_25();
        createRandomMask28_26();
        createRandomMask28_27();
        createRandomMask28_28();
        createRandomMask28_3();
        createRandomMask28_4();
        createRandomMask28_5();
        createRandomMask28_6();
        createRandomMask28_7();
        createRandomMask28_8();
        createRandomMask28_9();
        createRandomMask29_1();
        createRandomMask29_10();
        createRandomMask29_11();
        createRandomMask29_12();
        createRandomMask29_13();
        createRandomMask29_14();
        createRandomMask29_15();
        createRandomMask29_16();
        createRandomMask29_17();
        createRandomMask29_18();
        createRandomMask29_19();
        createRandomMask29_2();
        createRandomMask29_20();
        createRandomMask29_21();
        createRandomMask29_22();
        createRandomMask29_23();
        createRandomMask29_24();
        createRandomMask29_25();
        createRandomMask29_26();
        createRandomMask29_27();
        createRandomMask29_28();
        createRandomMask29_29();
        createRandomMask29_3();
        createRandomMask29_4();
        createRandomMask29_5();
        createRandomMask29_6();
        createRandomMask29_7();
        createRandomMask29_8();
        createRandomMask29_9();
        createRandomMask2_1();
        createRandomMask2_2();
        createRandomMask30_1();
        createRandomMask30_10();
        createRandomMask30_11();
        createRandomMask30_12();
        createRandomMask30_13();
        createRandomMask30_14();
        createRandomMask30_15();
        createRandomMask30_16();
        createRandomMask30_17();
        createRandomMask30_18();
        createRandomMask30_19();
        createRandomMask30_2();
        createRandomMask30_20();
        createRandomMask30_21();
        createRandomMask30_22();
        createRandomMask30_23();
        createRandomMask30_24();
        createRandomMask30_25();
        createRandomMask30_26();
        createRandomMask30_27();
        createRandomMask30_28();
        createRandomMask30_29();
        createRandomMask30_3();
        createRandomMask30_30();
        createRandomMask30_4();
        createRandomMask30_5();
        createRandomMask30_6();
        createRandomMask30_7();
        createRandomMask30_8();
        createRandomMask30_9();
        createRandomMask31_1();
        createRandomMask31_10();
        createRandomMask31_11();
        createRandomMask31_12();
        createRandomMask31_13();
        createRandomMask31_14();
        createRandomMask31_15();
        createRandomMask31_16();
        createRandomMask31_17();
        createRandomMask31_18();
        createRandomMask31_19();
        createRandomMask31_2();
        createRandomMask31_20();
        createRandomMask31_21();
        createRandomMask31_22();
        createRandomMask31_23();
        createRandomMask31_24();
        createRandomMask31_25();
        createRandomMask31_26();
        createRandomMask31_27();
        createRandomMask31_28();
        createRandomMask31_29();
        createRandomMask31_3();
        createRandomMask31_30();
        createRandomMask31_31();
        createRandomMask31_4();
        createRandomMask31_5();
        createRandomMask31_6();
        createRandomMask31_7();
        createRandomMask31_8();
        createRandomMask31_9();
        createRandomMask32_1();
        createRandomMask32_10();
        createRandomMask32_11();
        createRandomMask32_12();
        createRandomMask32_13();
        createRandomMask32_14();
        createRandomMask32_15();
        createRandomMask32_16();
        createRandomMask32_17();
        createRandomMask32_18();
        createRandomMask32_19();
        createRandomMask32_2();
        createRandomMask32_20();
        createRandomMask32_21();
        createRandomMask32_22();
        createRandomMask32_23();
        createRandomMask32_24();
        createRandomMask32_25();
        createRandomMask32_26();
        createRandomMask32_27();
        createRandomMask32_28();
        createRandomMask32_29();
        createRandomMask32_3();
        createRandomMask32_30();
        createRandomMask32_31();
        createRandomMask32_32();
        createRandomMask32_4();
        createRandomMask32_5();
        createRandomMask32_6();
        createRandomMask32_7();
        createRandomMask32_8();
        createRandomMask32_9();
        createRandomMask33_1();
        createRandomMask33_10();
        createRandomMask33_11();
        createRandomMask33_12();
        createRandomMask33_13();
        createRandomMask33_14();
        createRandomMask33_15();
        createRandomMask33_16();
        createRandomMask33_17();
        createRandomMask33_18();
        createRandomMask33_19();
        createRandomMask33_2();
        createRandomMask33_20();
        createRandomMask33_21();
        createRandomMask33_22();
        createRandomMask33_23();
        createRandomMask33_24();
        createRandomMask33_25();
        createRandomMask33_26();
        createRandomMask33_27();
        createRandomMask33_28();
        createRandomMask33_29();
        createRandomMask33_3();
        createRandomMask33_30();
        createRandomMask33_31();
        createRandomMask33_32();
        createRandomMask33_33();
        createRandomMask33_4();
        createRandomMask33_5();
        createRandomMask33_6();
        createRandomMask33_7();
        createRandomMask33_8();
        createRandomMask33_9();
        createRandomMask34_1();
        createRandomMask34_10();
        createRandomMask34_11();
        createRandomMask34_12();
        createRandomMask34_13();
        createRandomMask34_14();
        createRandomMask34_15();
        createRandomMask34_16();
        createRandomMask34_17();
        createRandomMask34_18();
        createRandomMask34_19();
        createRandomMask34_2();
        createRandomMask34_20();
        createRandomMask34_21();
        createRandomMask34_22();
        createRandomMask34_23();
        createRandomMask34_24();
        createRandomMask34_25();
        createRandomMask34_26();
        createRandomMask34_27();
        createRandomMask34_28();
        createRandomMask34_29();
        createRandomMask34_3();
        createRandomMask34_30();
        createRandomMask34_31();
        createRandomMask34_32();
        createRandomMask34_33();
        createRandomMask34_34();
        createRandomMask34_4();
        createRandomMask34_5();
        createRandomMask34_6();
        createRandomMask34_7();
        createRandomMask34_8();
        createRandomMask34_9();
        createRandomMask35_1();
        createRandomMask35_10();
        createRandomMask35_11();
        createRandomMask35_12();
        createRandomMask35_13();
        createRandomMask35_14();
        createRandomMask35_15();
        createRandomMask35_16();
        createRandomMask35_17();
        createRandomMask35_18();
        createRandomMask35_19();
        createRandomMask35_2();
        createRandomMask35_20();
        createRandomMask35_21();
        createRandomMask35_22();
        createRandomMask35_23();
        createRandomMask35_24();
        createRandomMask35_25();
        createRandomMask35_26();
        createRandomMask35_27();
        createRandomMask35_28();
        createRandomMask35_29();
        createRandomMask35_3();
        createRandomMask35_30();
        createRandomMask35_31();
        createRandomMask35_32();
        createRandomMask35_33();
        createRandomMask35_34();
        createRandomMask35_35();
        createRandomMask35_4();
        createRandomMask35_5();
        createRandomMask35_6();
        createRandomMask35_7();
        createRandomMask35_8();
        createRandomMask35_9();
        createRandomMask36_1();
        createRandomMask36_10();
        createRandomMask36_11();
        createRandomMask36_12();
        createRandomMask36_13();
        createRandomMask36_14();
        createRandomMask36_15();
        createRandomMask36_16();
        createRandomMask36_17();
        createRandomMask36_18();
        createRandomMask36_19();
        createRandomMask36_2();
        createRandomMask36_20();
        createRandomMask36_21();
        createRandomMask36_22();
        createRandomMask36_23();
        createRandomMask36_24();
        createRandomMask36_25();
        createRandomMask36_26();
        createRandomMask36_27();
        createRandomMask36_28();
        createRandomMask36_29();
        createRandomMask36_3();
        createRandomMask36_30();
        createRandomMask36_31();
        createRandomMask36_32();
        createRandomMask36_33();
        createRandomMask36_34();
        createRandomMask36_35();
        createRandomMask36_36();
        createRandomMask36_4();
        createRandomMask36_5();
        createRandomMask36_6();
        createRandomMask36_7();
        createRandomMask36_8();
        createRandomMask36_9();
        createRandomMask37_1();
        createRandomMask37_10();
        createRandomMask37_11();
        createRandomMask37_12();
        createRandomMask37_13();
        createRandomMask37_14();
        createRandomMask37_15();
        createRandomMask37_16();
        createRandomMask37_17();
        createRandomMask37_18();
        createRandomMask37_19();
        createRandomMask37_2();
        createRandomMask37_20();
        createRandomMask37_21();
        createRandomMask37_22();
        createRandomMask37_23();
        createRandomMask37_24();
        createRandomMask37_25();
        createRandomMask37_26();
        createRandomMask37_27();
        createRandomMask37_28();
        createRandomMask37_29();
        createRandomMask37_3();
        createRandomMask37_30();
        createRandomMask37_31();
        createRandomMask37_32();
        createRandomMask37_33();
        createRandomMask37_34();
        createRandomMask37_35();
        createRandomMask37_36();
        createRandomMask37_37();
        createRandomMask37_4();
        createRandomMask37_5();
        createRandomMask37_6();
        createRandomMask37_7();
        createRandomMask37_8();
        createRandomMask37_9();
        createRandomMask38_1();
        createRandomMask38_10();
        createRandomMask38_11();
        createRandomMask38_12();
        createRandomMask38_13();
        createRandomMask38_14();
        createRandomMask38_15();
        createRandomMask38_16();
        createRandomMask38_17();
        createRandomMask38_18();
        createRandomMask38_19();
        createRandomMask38_2();
        createRandomMask38_20();
        createRandomMask38_21();
        createRandomMask38_22();
        createRandomMask38_23();
        createRandomMask38_24();
        createRandomMask38_25();
        createRandomMask38_26();
        createRandomMask38_27();
        createRandomMask38_28();
        createRandomMask38_29();
        createRandomMask38_3();
        createRandomMask38_30();
        createRandomMask38_31();
        createRandomMask38_32();
        createRandomMask38_33();
        createRandomMask38_34();
        createRandomMask38_35();
        createRandomMask38_36();
        createRandomMask38_37();
        createRandomMask38_38();
        createRandomMask38_4();
        createRandomMask38_5();
        createRandomMask38_6();
        createRandomMask38_7();
        createRandomMask38_8();
        createRandomMask38_9();
        createRandomMask39_1();
        createRandomMask39_10();
        createRandomMask39_11();
        createRandomMask39_12();
        createRandomMask39_13();
        createRandomMask39_14();
        createRandomMask39_15();
        createRandomMask39_16();
        createRandomMask39_17();
        createRandomMask39_18();
        createRandomMask39_19();
        createRandomMask39_2();
        createRandomMask39_20();
        createRandomMask39_21();
        createRandomMask39_22();
        createRandomMask39_23();
        createRandomMask39_24();
        createRandomMask39_25();
        createRandomMask39_26();
        createRandomMask39_27();
        createRandomMask39_28();
        createRandomMask39_29();
        createRandomMask39_3();
        createRandomMask39_30();
        createRandomMask39_31();
        createRandomMask39_32();
        createRandomMask39_33();
        createRandomMask39_34();
        createRandomMask39_35();
        createRandomMask39_36();
        createRandomMask39_37();
        createRandomMask39_38();
        createRandomMask39_39();
        createRandomMask39_4();
        createRandomMask39_5();
        createRandomMask39_6();
        createRandomMask39_7();
        createRandomMask39_8();
        createRandomMask39_9();
        createRandomMask3_1();
        createRandomMask3_2();
        createRandomMask3_3();
        createRandomMask40_1();
        createRandomMask40_10();
        createRandomMask40_11();
        createRandomMask40_12();
        createRandomMask40_13();
        createRandomMask40_14();
        createRandomMask40_15();
        createRandomMask40_16();
        createRandomMask40_17();
        createRandomMask40_18();
        createRandomMask40_19();
        createRandomMask40_2();
        createRandomMask40_20();
        createRandomMask40_21();
        createRandomMask40_22();
        createRandomMask40_23();
        createRandomMask40_24();
        createRandomMask40_25();
        createRandomMask40_26();
        createRandomMask40_27();
        createRandomMask40_28();
        createRandomMask40_29();
        createRandomMask40_3();
        createRandomMask40_30();
        createRandomMask40_31();
        createRandomMask40_32();
        createRandomMask40_33();
        createRandomMask40_34();
        createRandomMask40_35();
        createRandomMask40_36();
        createRandomMask40_37();
        createRandomMask40_38();
        createRandomMask40_39();
        createRandomMask40_4();
        createRandomMask40_40();
        createRandomMask40_5();
        createRandomMask40_6();
        createRandomMask40_7();
        createRandomMask40_8();
        createRandomMask40_9();
        createRandomMask41_1();
        createRandomMask41_10();
        createRandomMask41_11();
        createRandomMask41_12();
        createRandomMask41_13();
        createRandomMask41_14();
        createRandomMask41_15();
        createRandomMask41_16();
        createRandomMask41_17();
        createRandomMask41_18();
        createRandomMask41_19();
        createRandomMask41_2();
        createRandomMask41_20();
        createRandomMask41_21();
        createRandomMask41_22();
        createRandomMask41_23();
        createRandomMask41_24();
        createRandomMask41_25();
        createRandomMask41_26();
        createRandomMask41_27();
        createRandomMask41_28();
        createRandomMask41_29();
        createRandomMask41_3();
        createRandomMask41_30();
        createRandomMask41_31();
        createRandomMask41_32();
        createRandomMask41_33();
        createRandomMask41_34();
        createRandomMask41_35();
        createRandomMask41_36();
        createRandomMask41_37();
        createRandomMask41_38();
        createRandomMask41_39();
        createRandomMask41_4();
        createRandomMask41_40();
        createRandomMask41_41();
        createRandomMask41_5();
        createRandomMask41_6();
        createRandomMask41_7();
        createRandomMask41_8();
        createRandomMask41_9();
        createRandomMask42_1();
        createRandomMask42_10();
        createRandomMask42_11();
        createRandomMask42_12();
        createRandomMask42_13();
        createRandomMask42_14();
        createRandomMask42_15();
        createRandomMask42_16();
        createRandomMask42_17();
        createRandomMask42_18();
        createRandomMask42_19();
        createRandomMask42_2();
        createRandomMask42_20();
        createRandomMask42_21();
        createRandomMask42_22();
        createRandomMask42_23();
        createRandomMask42_24();
        createRandomMask42_25();
        createRandomMask42_26();
        createRandomMask42_27();
        createRandomMask42_28();
        createRandomMask42_29();
        createRandomMask42_3();
        createRandomMask42_30();
        createRandomMask42_31();
        createRandomMask42_32();
        createRandomMask42_33();
        createRandomMask42_34();
        createRandomMask42_35();
        createRandomMask42_36();
        createRandomMask42_37();
        createRandomMask42_38();
        createRandomMask42_39();
        createRandomMask42_4();
        createRandomMask42_40();
        createRandomMask42_41();
        createRandomMask42_42();
        createRandomMask42_5();
        createRandomMask42_6();
        createRandomMask42_7();
        createRandomMask42_8();
        createRandomMask42_9();
        createRandomMask43_1();
        createRandomMask43_10();
        createRandomMask43_11();
        createRandomMask43_12();
        createRandomMask43_13();
        createRandomMask43_14();
        createRandomMask43_15();
        createRandomMask43_16();
        createRandomMask43_17();
        createRandomMask43_18();
        createRandomMask43_19();
        createRandomMask43_2();
        createRandomMask43_20();
        createRandomMask43_21();
        createRandomMask43_22();
        createRandomMask43_23();
        createRandomMask43_24();
        createRandomMask43_25();
        createRandomMask43_26();
        createRandomMask43_27();
        createRandomMask43_28();
        createRandomMask43_29();
        createRandomMask43_3();
        createRandomMask43_30();
        createRandomMask43_31();
        createRandomMask43_32();
        createRandomMask43_33();
        createRandomMask43_34();
        createRandomMask43_35();
        createRandomMask43_36();
        createRandomMask43_37();
        createRandomMask43_38();
        createRandomMask43_39();
        createRandomMask43_4();
        createRandomMask43_40();
        createRandomMask43_41();
        createRandomMask43_42();
        createRandomMask43_43();
        createRandomMask43_5();
        createRandomMask43_6();
        createRandomMask43_7();
        createRandomMask43_8();
        createRandomMask43_9();
        createRandomMask44_1();
        createRandomMask44_10();
        createRandomMask44_11();
        createRandomMask44_12();
        createRandomMask44_13();
        createRandomMask44_14();
        createRandomMask44_15();
        createRandomMask44_16();
        createRandomMask44_17();
        createRandomMask44_18();
        createRandomMask44_19();
        createRandomMask44_2();
        createRandomMask44_20();
        createRandomMask44_21();
        createRandomMask44_22();
        createRandomMask44_23();
        createRandomMask44_24();
        createRandomMask44_25();
        createRandomMask44_26();
        createRandomMask44_27();
        createRandomMask44_28();
        createRandomMask44_29();
        createRandomMask44_3();
        createRandomMask44_30();
        createRandomMask44_31();
        createRandomMask44_32();
        createRandomMask44_33();
        createRandomMask44_34();
        createRandomMask44_35();
        createRandomMask44_36();
        createRandomMask44_37();
        createRandomMask44_38();
        createRandomMask44_39();
        createRandomMask44_4();
        createRandomMask44_40();
        createRandomMask44_41();
        createRandomMask44_42();
        createRandomMask44_43();
        createRandomMask44_44();
        createRandomMask44_5();
        createRandomMask44_6();
        createRandomMask44_7();
        createRandomMask44_8();
        createRandomMask44_9();
        createRandomMask45_1();
        createRandomMask45_10();
        createRandomMask45_11();
        createRandomMask45_12();
        createRandomMask45_13();
        createRandomMask45_14();
        createRandomMask45_15();
        createRandomMask45_16();
        createRandomMask45_17();
        createRandomMask45_18();
        createRandomMask45_19();
        createRandomMask45_2();
        createRandomMask45_20();
        createRandomMask45_21();
        createRandomMask45_22();
        createRandomMask45_23();
        createRandomMask45_24();
        createRandomMask45_25();
        createRandomMask45_26();
        createRandomMask45_27();
        createRandomMask45_28();
        createRandomMask45_29();
        createRandomMask45_3();
        createRandomMask45_30();
        createRandomMask45_31();
        createRandomMask45_32();
        createRandomMask45_33();
        createRandomMask45_34();
        createRandomMask45_35();
        createRandomMask45_36();
        createRandomMask45_37();
        createRandomMask45_38();
        createRandomMask45_39();
        createRandomMask45_4();
        createRandomMask45_40();
        createRandomMask45_41();
        createRandomMask45_42();
        createRandomMask45_43();
        createRandomMask45_44();
        createRandomMask45_45();
        createRandomMask45_5();
        createRandomMask45_6();
        createRandomMask45_7();
        createRandomMask45_8();
        createRandomMask45_9();
        createRandomMask46_1();
        createRandomMask46_10();
        createRandomMask46_11();
        createRandomMask46_12();
        createRandomMask46_13();
        createRandomMask46_14();
        createRandomMask46_15();
        createRandomMask46_16();
        createRandomMask46_17();
        createRandomMask46_18();
        createRandomMask46_19();
        createRandomMask46_2();
        createRandomMask46_20();
        createRandomMask46_21();
        createRandomMask46_22();
        createRandomMask46_23();
        createRandomMask46_24();
        createRandomMask46_25();
        createRandomMask46_26();
        createRandomMask46_27();
        createRandomMask46_28();
        createRandomMask46_29();
        createRandomMask46_3();
        createRandomMask46_30();
        createRandomMask46_31();
        createRandomMask46_32();
        createRandomMask46_33();
        createRandomMask46_34();
        createRandomMask46_35();
        createRandomMask46_36();
        createRandomMask46_37();
        createRandomMask46_38();
        createRandomMask46_39();
        createRandomMask46_4();
        createRandomMask46_40();
        createRandomMask46_41();
        createRandomMask46_42();
        createRandomMask46_43();
        createRandomMask46_44();
        createRandomMask46_45();
        createRandomMask46_46();
        createRandomMask46_5();
        createRandomMask46_6();
        createRandomMask46_7();
        createRandomMask46_8();
        createRandomMask46_9();
        createRandomMask47_1();
        createRandomMask47_10();
        createRandomMask47_11();
        createRandomMask47_12();
        createRandomMask47_13();
        createRandomMask47_14();
        createRandomMask47_15();
        createRandomMask47_16();
        createRandomMask47_17();
        createRandomMask47_18();
        createRandomMask47_19();
        createRandomMask47_2();
        createRandomMask47_20();
        createRandomMask47_21();
        createRandomMask47_22();
        createRandomMask47_23();
        createRandomMask47_24();
        createRandomMask47_25();
        createRandomMask47_26();
        createRandomMask47_27();
        createRandomMask47_28();
        createRandomMask47_29();
        createRandomMask47_3();
        createRandomMask47_30();
        createRandomMask47_31();
        createRandomMask47_32();
        createRandomMask47_33();
        createRandomMask47_34();
        createRandomMask47_35();
        createRandomMask47_36();
        createRandomMask47_37();
        createRandomMask47_38();
        createRandomMask47_39();
        createRandomMask47_4();
        createRandomMask47_40();
        createRandomMask47_41();
        createRandomMask47_42();
        createRandomMask47_43();
        createRandomMask47_44();
        createRandomMask47_45();
        createRandomMask47_46();
        createRandomMask47_47();
        createRandomMask47_5();
        createRandomMask47_6();
        createRandomMask47_7();
        createRandomMask47_8();
        createRandomMask47_9();
        createRandomMask48_1();
        createRandomMask48_10();
        createRandomMask48_11();
        createRandomMask48_12();
        createRandomMask48_13();
        createRandomMask48_14();
        createRandomMask48_15();
        createRandomMask48_16();
        createRandomMask48_17();
        createRandomMask48_18();
        createRandomMask48_19();
        createRandomMask48_2();
        createRandomMask48_20();
        createRandomMask48_21();
        createRandomMask48_22();
        createRandomMask48_23();
        createRandomMask48_24();
        createRandomMask48_25();
        createRandomMask48_26();
        createRandomMask48_27();
        createRandomMask48_28();
        createRandomMask48_29();
        createRandomMask48_3();
        createRandomMask48_30();
        createRandomMask48_31();
        createRandomMask48_32();
        createRandomMask48_33();
        createRandomMask48_34();
        createRandomMask48_35();
        createRandomMask48_36();
        createRandomMask48_37();
        createRandomMask48_38();
        createRandomMask48_39();
        createRandomMask48_4();
        createRandomMask48_40();
        createRandomMask48_41();
        createRandomMask48_42();
        createRandomMask48_43();
        createRandomMask48_44();
        createRandomMask48_45();
        createRandomMask48_46();
        createRandomMask48_47();
        createRandomMask48_48();
        createRandomMask48_5();
        createRandomMask48_6();
        createRandomMask48_7();
        createRandomMask48_8();
        createRandomMask48_9();
        createRandomMask4_1();
        createRandomMask4_2();
        createRandomMask4_3();
        createRandomMask4_4();
        createRandomMask5_1();
        createRandomMask5_2();
        createRandomMask5_3();
        createRandomMask5_4();
        createRandomMask5_5();
        createRandomMask6_1();
        createRandomMask6_2();
        createRandomMask6_3();
        createRandomMask6_4();
        createRandomMask6_5();
        createRandomMask6_6();
        createRandomMask7_1();
        createRandomMask7_2();
        createRandomMask7_3();
        createRandomMask7_4();
        createRandomMask7_5();
        createRandomMask7_6();
        createRandomMask7_7();
        createRandomMask8_1();
        createRandomMask8_2();
        createRandomMask8_3();
        createRandomMask8_4();
        createRandomMask8_5();
        createRandomMask8_6();
        createRandomMask8_7();
        createRandomMask8_8();
        createRandomMask9_1();
        createRandomMask9_2();
        createRandomMask9_3();
        createRandomMask9_4();
        createRandomMask9_5();
        createRandomMask9_6();
        createRandomMask9_7();
        createRandomMask9_8();
        createRandomMask9_9();
        createRandomPacketMask1();
        createRandomPacketMask2();
        createRandomPacketMask3();
        createRandomPacketMask4();
        createRandomPacketMask5();
        createRandomPacketMask6();
        createRandomPacketMask7();
        createRandomPacketMask8();
        createRandomPacketMask9();
        createRandomPacketMask10();
        createRandomPacketMask11();
        createRandomPacketMask12();
        createRandomPacketMask13();
        createRandomPacketMask14();
        createRandomPacketMask15();
        createRandomPacketMask16();
        createRandomPacketMask17();
        createRandomPacketMask18();
        createRandomPacketMask19();
        createRandomPacketMask20();
        createRandomPacketMask21();
        createRandomPacketMask22();
        createRandomPacketMask23();
        createRandomPacketMask24();
        createRandomPacketMask25();
        createRandomPacketMask26();
        createRandomPacketMask27();
        createRandomPacketMask28();
        createRandomPacketMask29();
        createRandomPacketMask30();
        createRandomPacketMask31();
        createRandomPacketMask32();
        createRandomPacketMask33();
        createRandomPacketMask34();
        createRandomPacketMask35();
        createRandomPacketMask36();
        createRandomPacketMask37();
        createRandomPacketMask38();
        createRandomPacketMask39();
        createRandomPacketMask40();
        createRandomPacketMask41();
        createRandomPacketMask42();
        createRandomPacketMask43();
        createRandomPacketMask44();
        createRandomPacketMask45();
        createRandomPacketMask46();
        createRandomPacketMask47();
        createRandomPacketMask48();
        createRandomPacketMaskTable();
        createBurstyMask1_1();
        createBurstyMask2_1();
        createBurstyMask2_2();
        createBurstyMask3_1();
        createBurstyMask3_2();
        createBurstyMask3_3();
        createBurstyMask4_1();
        createBurstyMask4_2();
        createBurstyMask4_3();
        createBurstyMask4_4();
        createBurstyMask5_1();
        createBurstyMask5_2();
        createBurstyMask5_3();
        createBurstyMask5_4();
        createBurstyMask5_5();
        createBurstyMask6_1();
        createBurstyMask6_2();
        createBurstyMask6_3();
        createBurstyMask6_4();
        createBurstyMask6_5();
        createBurstyMask6_6();
        createBurstyMask7_1();
        createBurstyMask7_2();
        createBurstyMask7_3();
        createBurstyMask7_4();
        createBurstyMask7_5();
        createBurstyMask7_6();
        createBurstyMask7_7();
        createBurstyMask8_1();
        createBurstyMask8_2();
        createBurstyMask8_3();
        createBurstyMask8_4();
        createBurstyMask8_5();
        createBurstyMask8_6();
        createBurstyMask8_7();
        createBurstyMask8_8();
        createBurstyMask9_1();
        createBurstyMask9_2();
        createBurstyMask9_3();
        createBurstyMask9_4();
        createBurstyMask9_5();
        createBurstyMask9_6();
        createBurstyMask9_7();
        createBurstyMask9_8();
        createBurstyMask9_9();
        createBurstyMask10_1();
        createBurstyMask10_2();
        createBurstyMask10_3();
        createBurstyMask10_4();
        createBurstyMask10_5();
        createBurstyMask10_6();
        createBurstyMask10_7();
        createBurstyMask10_8();
        createBurstyMask10_9();
        createBurstyMask10_10();
        createBurstyMask11_1();
        createBurstyMask11_2();
        createBurstyMask11_3();
        createBurstyMask11_4();
        createBurstyMask11_5();
        createBurstyMask11_6();
        createBurstyMask11_7();
        createBurstyMask11_8();
        createBurstyMask11_9();
        createBurstyMask11_10();
        createBurstyMask11_11();
        createBurstyMask12_1();
        createBurstyMask12_2();
        createBurstyMask12_3();
        createBurstyMask12_4();
        createBurstyMask12_5();
        createBurstyMask12_6();
        createBurstyMask12_7();
        createBurstyMask12_8();
        createBurstyMask12_9();
        createBurstyMask12_10();
        createBurstyMask12_11();
        createBurstyMask12_12();
        createBurstyPacketMask1();
        createBurstyPacketMask2();
        createBurstyPacketMask3();
        createBurstyPacketMask4();
        createBurstyPacketMask5();
        createBurstyPacketMask6();
        createBurstyPacketMask7();
        createBurstyPacketMask8();
        createBurstyPacketMask9();
        createBurstyPacketMask10();
        createBurstyPacketMask11();
        createBurstyPacketMask12();
        createBurstyPacketMaskTable();
    }

    public FecPacketMaskTable(FecMaskType fecMaskType, int i) {
        setMaskType(selectMaskType(fecMaskType, i));
        setTable(selectMaskTable(getMaskType()));
    }

    public FecMaskType getMaskType() {
        return this._maskType;
    }

    public byte[][][] getTable() {
        return this._table;
    }

    private byte[][][] selectMaskTable(FecMaskType fecMaskType) {
        if (Global.equals(fecMaskType, FecMaskType.Random)) {
            return _randomPacketMaskTable;
        }
        if (Global.equals(fecMaskType, FecMaskType.Bursty)) {
            return _burstyPacketMaskTable;
        }
        throw new RuntimeException(new Exception("Could not select mask table. Invalid mask type."));
    }

    private FecMaskType selectMaskType(FecMaskType fecMaskType, int i) {
        if (i > ArrayExtensions.getLength((Object[]) _randomPacketMaskTable)) {
            throw new RuntimeException(new Exception("The number of media packets cannot exceed the table length."));
        } else if (Global.equals(fecMaskType, FecMaskType.Random)) {
            return FecMaskType.Random;
        } else {
            if (!Global.equals(fecMaskType, FecMaskType.Bursty)) {
                throw new RuntimeException(new Exception("Could not select mask type. Invalid mask type."));
            } else if (i > ArrayExtensions.getLength((Object[]) _burstyPacketMaskTable)) {
                return FecMaskType.Random;
            } else {
                return FecMaskType.Bursty;
            }
        }
    }

    private void setMaskType(FecMaskType fecMaskType) {
        this._maskType = fecMaskType;
    }

    private void setTable(byte[][][] bArr) {
        this._table = bArr;
    }
}
