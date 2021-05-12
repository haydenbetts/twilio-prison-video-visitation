package com.google.android.exoplayer2.extractor.wav;

import android.util.Log;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;

final class WavHeaderReader {
    private static final String TAG = "WavHeaderReader";
    private static final int TYPE_FLOAT = 3;
    private static final int TYPE_PCM = 1;
    private static final int TYPE_WAVE_FORMAT_EXTENSIBLE = 65534;

    WavHeaderReader() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x00c6  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00e3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.exoplayer2.extractor.wav.WavHeader peek(com.google.android.exoplayer2.extractor.ExtractorInput r17) throws java.io.IOException, java.lang.InterruptedException {
        /*
            r0 = r17
            com.google.android.exoplayer2.util.Assertions.checkNotNull(r17)
            com.google.android.exoplayer2.util.ParsableByteArray r1 = new com.google.android.exoplayer2.util.ParsableByteArray
            r2 = 16
            r1.<init>((int) r2)
            com.google.android.exoplayer2.extractor.wav.WavHeaderReader$ChunkHeader r3 = com.google.android.exoplayer2.extractor.wav.WavHeaderReader.ChunkHeader.peek(r0, r1)
            int r3 = r3.id
            java.lang.String r4 = "RIFF"
            int r4 = com.google.android.exoplayer2.util.Util.getIntegerCodeForString(r4)
            r5 = 0
            if (r3 == r4) goto L_0x001c
            return r5
        L_0x001c:
            byte[] r3 = r1.data
            r4 = 0
            r6 = 4
            r0.peekFully(r3, r4, r6)
            r1.setPosition(r4)
            int r3 = r1.readInt()
            java.lang.String r7 = "WAVE"
            int r7 = com.google.android.exoplayer2.util.Util.getIntegerCodeForString(r7)
            java.lang.String r8 = "WavHeaderReader"
            if (r3 == r7) goto L_0x0049
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Unsupported RIFF format: "
            r0.append(r1)
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            android.util.Log.e(r8, r0)
            return r5
        L_0x0049:
            com.google.android.exoplayer2.extractor.wav.WavHeaderReader$ChunkHeader r3 = com.google.android.exoplayer2.extractor.wav.WavHeaderReader.ChunkHeader.peek(r0, r1)
        L_0x004d:
            int r7 = r3.id
            java.lang.String r9 = "fmt "
            int r9 = com.google.android.exoplayer2.util.Util.getIntegerCodeForString(r9)
            if (r7 == r9) goto L_0x0062
            long r9 = r3.size
            int r3 = (int) r9
            r0.advancePeekPosition(r3)
            com.google.android.exoplayer2.extractor.wav.WavHeaderReader$ChunkHeader r3 = com.google.android.exoplayer2.extractor.wav.WavHeaderReader.ChunkHeader.peek(r0, r1)
            goto L_0x004d
        L_0x0062:
            long r9 = r3.size
            r11 = 16
            r7 = 1
            int r13 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r13 < 0) goto L_0x006d
            r9 = 1
            goto L_0x006e
        L_0x006d:
            r9 = 0
        L_0x006e:
            com.google.android.exoplayer2.util.Assertions.checkState(r9)
            byte[] r9 = r1.data
            r0.peekFully(r9, r4, r2)
            r1.setPosition(r4)
            int r9 = r1.readLittleEndianUnsignedShort()
            int r11 = r1.readLittleEndianUnsignedShort()
            int r12 = r1.readLittleEndianUnsignedIntToInt()
            int r13 = r1.readLittleEndianUnsignedIntToInt()
            int r14 = r1.readLittleEndianUnsignedShort()
            int r15 = r1.readLittleEndianUnsignedShort()
            int r1 = r11 * r15
            int r1 = r1 / 8
            if (r14 != r1) goto L_0x00f1
            if (r9 == r7) goto L_0x00be
            r1 = 3
            if (r9 == r1) goto L_0x00b6
            r1 = 65534(0xfffe, float:9.1833E-41)
            if (r9 == r1) goto L_0x00be
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Unsupported WAV format type: "
            r0.append(r1)
            r0.append(r9)
            java.lang.String r0 = r0.toString()
            android.util.Log.e(r8, r0)
            return r5
        L_0x00b6:
            r1 = 32
            if (r15 != r1) goto L_0x00bb
            r4 = 4
        L_0x00bb:
            r16 = r4
            goto L_0x00c4
        L_0x00be:
            int r1 = com.google.android.exoplayer2.util.Util.getPcmEncoding(r15)
            r16 = r1
        L_0x00c4:
            if (r16 != 0) goto L_0x00e3
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Unsupported WAV bit depth "
            r0.append(r1)
            r0.append(r15)
            java.lang.String r1 = " for type "
            r0.append(r1)
            r0.append(r9)
            java.lang.String r0 = r0.toString()
            android.util.Log.e(r8, r0)
            return r5
        L_0x00e3:
            long r3 = r3.size
            int r1 = (int) r3
            int r1 = r1 - r2
            r0.advancePeekPosition(r1)
            com.google.android.exoplayer2.extractor.wav.WavHeader r0 = new com.google.android.exoplayer2.extractor.wav.WavHeader
            r10 = r0
            r10.<init>(r11, r12, r13, r14, r15, r16)
            return r0
        L_0x00f1:
            com.google.android.exoplayer2.ParserException r0 = new com.google.android.exoplayer2.ParserException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Expected block alignment: "
            r2.append(r3)
            r2.append(r1)
            java.lang.String r1 = "; got: "
            r2.append(r1)
            r2.append(r14)
            java.lang.String r1 = r2.toString()
            r0.<init>((java.lang.String) r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.wav.WavHeaderReader.peek(com.google.android.exoplayer2.extractor.ExtractorInput):com.google.android.exoplayer2.extractor.wav.WavHeader");
    }

    public static void skipToData(ExtractorInput extractorInput, WavHeader wavHeader) throws IOException, InterruptedException {
        Assertions.checkNotNull(extractorInput);
        Assertions.checkNotNull(wavHeader);
        extractorInput.resetPeekPosition();
        ParsableByteArray parsableByteArray = new ParsableByteArray(8);
        ChunkHeader peek = ChunkHeader.peek(extractorInput, parsableByteArray);
        while (peek.id != Util.getIntegerCodeForString("data")) {
            Log.w(TAG, "Ignoring unknown WAV chunk: " + peek.id);
            long j = peek.size + 8;
            if (peek.id == Util.getIntegerCodeForString("RIFF")) {
                j = 12;
            }
            if (j <= 2147483647L) {
                extractorInput.skipFully((int) j);
                peek = ChunkHeader.peek(extractorInput, parsableByteArray);
            } else {
                throw new ParserException("Chunk is too large (~2GB+) to skip; id: " + peek.id);
            }
        }
        extractorInput.skipFully(8);
        wavHeader.setDataBounds(extractorInput.getPosition(), peek.size);
    }

    private static final class ChunkHeader {
        public static final int SIZE_IN_BYTES = 8;
        public final int id;
        public final long size;

        private ChunkHeader(int i, long j) {
            this.id = i;
            this.size = j;
        }

        public static ChunkHeader peek(ExtractorInput extractorInput, ParsableByteArray parsableByteArray) throws IOException, InterruptedException {
            extractorInput.peekFully(parsableByteArray.data, 0, 8);
            parsableByteArray.setPosition(0);
            return new ChunkHeader(parsableByteArray.readInt(), parsableByteArray.readLittleEndianUnsignedInt());
        }
    }
}
