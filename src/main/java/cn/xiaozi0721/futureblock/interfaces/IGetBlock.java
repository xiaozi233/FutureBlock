package cn.xiaozi0721.futureblock.interfaces;

import net.minecraft.block.Block;

public interface IGetBlock {
    default Block getBlockBelow(){
        return getBlockBelow(0D);
    }
    boolean isUponHoneyBlock();
    Block getBlockBelow(double deltaY);
}
