package com.pingfangx.algorithm.encoding.base64;

import java.util.Arrays;

/**
 * 官方的实现简化版
 * {@link java.util.Base64}
 *
 * @author pingfangx
 * @date 2018/9/17
 */
class Base64Util {

    public static class Encoder {
        private static final char[] toBase64 = {
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'
        };

        public String encode(String src) {
            return new String(encode(src.getBytes()));
        }

        public byte[] encode(byte[] src) {
            //计算输出长度
            int len = outLength(src.length);
            byte[] dst = new byte[len];
            int ret = encode0(src, 0, src.length, dst);
            if (ret != dst.length) {
                //返回的长度不足，copy
                return Arrays.copyOf(dst, ret);
            }
            return dst;
        }

        private int outLength(int srcLen) {
            //直接取 4/3
            return 4 * ((srcLen + 2) / 3);
        }

        /**
         * 分为两部分，第一部分，将 3 个 8 字节的字符生成 4 个 6 字节表示的 index，index 取出对应字符
         * <p>
         * & 0xff 取为 8 位
         * << 16 || << 8 ||  取 3 个字节
         * (>>> 18) & 0x3f
         * (>>> 12) & 0x3f
         * (>>> 6) & 0x3f
         * & 0x3f
         * 分别取每个 6 位
         * <p>
         * 不足 3 个字节的
         * >> 2 取前 6 位
         * >> 4 取前 4 位
         * (<<4) & 0x3f 取后两位，左移 4 位后补了 4 个0，&0x3f 之后只有最后 2 位有效
         * (<<2) & 0x3f 取后 4 位
         */
        private int encode0(byte[] src, int off, int end, byte[] dst) {
            char[] base64 = toBase64;
            int sp = off;
            int slen = (end - off) / 3 * 3;
            int sl = off + slen;
            int dp = 0;
            while (sp < sl) {
                int sl0 = Math.min(sp + slen, sl);
                for (int sp0 = sp, dp0 = dp; sp0 < sl0; ) {
                    //取 3 个字符，生成 4 个字符
                    int bits = (src[sp0++] & 0xff) << 16 |
                            (src[sp0++] & 0xff) << 8 |
                            (src[sp0++] & 0xff);
                    dst[dp0++] = (byte) base64[(bits >>> 18) & 0x3f];
                    dst[dp0++] = (byte) base64[(bits >>> 12) & 0x3f];
                    dst[dp0++] = (byte) base64[(bits >>> 6) & 0x3f];
                    dst[dp0++] = (byte) base64[bits & 0x3f];
                }
                //dp 增加的长度
                int dlen = (sl0 - sp) / 3 * 4;
                dp += dlen;
                sp = sl0;
            }
            if (sp < end) {
                //剩 1 个或 2 个字节
                //第一个字节与 0x11111111 求与，得到 8 位
                int b0 = src[sp++] & 0xff;
                //第一个字节右移 2 位，即取前 6 位作为 index 取字符
                dst[dp++] = (byte) base64[b0 >> 2];
                if (sp == end) {
                    //到达末尾，表示剩的是一个字节，将剩余的 2 位补 0 生成一个字符，同时补 2 个 =
                    //左移 4 位，即补 4 个 0，然后与 0x111111 求与，得到 6 位
                    dst[dp++] = (byte) base64[(b0 << 4) & 0x3f];
                    dst[dp++] = '=';
                    dst[dp++] = '=';
                } else {
                    //剩余的是两个字节
                    //第二个字节取 8 位
                    int b1 = src[sp++] & 0xff;
                    //第一个字节的后两位，加上第二个字节的前四位组成一个字符
                    dst[dp++] = (byte) base64[(b0 << 4) & 0x3f | (b1 >> 4)];
                    //第二个字节的后四位，补 0
                    dst[dp++] = (byte) base64[(b1 << 2) & 0x3f];
                    dst[dp++] = '=';
                }
            }
            return dp;
        }
    }

    public static class Decoder {
        private static final int[] fromBase64 = new int[256];

        static {
            Arrays.fill(fromBase64, -1);
            for (int i = 0; i < Encoder.toBase64.length; i++) {
                fromBase64[Encoder.toBase64[i]] = i;
            }
            fromBase64['='] = -2;
        }

        public String decode(String src) {
            return new String(decode(src.getBytes()));
        }

        public byte[] decode(byte[] src) {
            byte[] dst = new byte[outLength(src, 0, src.length)];
            int ret = decode0(src, 0, src.length, dst);
            if (ret != dst.length) {
                dst = Arrays.copyOf(dst, ret);
            }
            return dst;
        }

        private int outLength(byte[] src, int sp, int sl) {
            int paddings = 0;
            int len = sl - sp;
            if (len == 0) {
                return 0;
            }
            if (len < 2) {
                return 0;
            }
            if (src[sl - 1] == '=') {
                paddings++;
                if (src[sl - 2] == '=') {
                    paddings++;
                }
            }
            if (paddings == 0 && (len & 0x3) != 0) {
                paddings = 4 - (len & 0x3);
            }
            return 3 * ((len + 3) / 4) - paddings;
        }

        private int decode0(byte[] src, int sp, int sl, byte[] dst) {
            int[] base64 = fromBase64;
            int dp = 0;
            int bits = 0;
            int shiftto = 18;       // pos of first byte of 4-byte atom
            while (sp < sl) {
                int b = src[sp++] & 0xff;
                if ((b = base64[b]) < 0) {
                    if (b == -2) {         // padding byte '='
                        // =     shiftto==18 unnecessary padding
                        // x=    shiftto==12 a dangling single x
                        // x     to be handled together with non-padding case
                        // xx=   shiftto==6&&sp==sl missing last =
                        // xx=y  shiftto==6 last is not =
                        if (shiftto == 6 && (sp == sl || src[sp++] != '=') ||
                                shiftto == 18) {
                            throw new IllegalArgumentException(
                                    "Input byte array has wrong 4-byte ending unit");
                        }
                        break;
                    }
                    throw new IllegalArgumentException(
                            "Illegal base64 character " +
                                    Integer.toString(src[sp - 1], 16));
                }
                //字符对应 index，分别左移 18，12，6，0位
                //组成 24 位的长度，然后转为3 个字节
                bits |= (b << shiftto);
                shiftto -= 6;
                if (shiftto < 0) {
                    dst[dp++] = (byte) (bits >> 16);
                    dst[dp++] = (byte) (bits >> 8);
                    dst[dp++] = (byte) (bits);
                    shiftto = 18;
                    bits = 0;
                }
            }
            // reached end of byte array or hit padding '=' characters.
            if (shiftto == 6) {
                dst[dp++] = (byte) (bits >> 16);
            } else if (shiftto == 0) {
                dst[dp++] = (byte) (bits >> 16);
                dst[dp++] = (byte) (bits >> 8);
            } else if (shiftto == 12) {
                // dangling single "x", incorrectly encoded.
                throw new IllegalArgumentException(
                        "Last unit does not have enough valid bits");
            }
            // anything left is invalid, if is not MIME.
            // if MIME, ignore all non-base64 character
            if (sp < sl) {
                throw new IllegalArgumentException(
                        "Input byte array has incorrect ending byte at " + sp);
            }
            return dp;
        }
    }
}
