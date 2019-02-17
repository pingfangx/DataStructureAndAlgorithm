package com.pingfangx.algorithm.tool;

/**
 * 添加、清除、检查 flag
 *
 * @author pingfangx
 * @date 2019/2/17
 */
public class FlagsUtils {
    private int mFlags;

    public void addFlags(int flags) {
        setFlags(flags, flags);
    }

    public void clearFlags(int flags) {
        setFlags(0, flags);
    }

    public void setFlags(int flags, int mask) {
        /*
        & ~mask ，会让 mask 对应位上的值清空
        add 时，mFlags & !mask 清空，flags & mask 得到 flags，再| 即为赋值
        clear 时，mask 即为要清除的 flags，mFlags &~ mask已经被清空，后面的 0 & mask=0

        那么 mask 还有什么用呢
        比如某组 flags 为 0x10,0x20,0x40,0x80 全部 add 后，只需要 setFlags(0,0xF0) 即可清除
         */
        mFlags = (mFlags & ~mask) | (flags & mask);
    }

    public boolean hasFlag(int flag) {
        return (mFlags & flag) != 0;
    }
}
