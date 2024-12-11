package cn.xiaozi0721.futureblock.interfaces;

import net.minecraft.block.Block;

public interface IGetBlock {
    boolean isUponHoneyBlock();
    Block getBlockBelow(double deltaY);
}
