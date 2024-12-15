package cn.xiaozi0721.futureblock.interfaces;

import net.minecraft.block.Block;

public interface IApplySpeedFactor {
    default Block getBlockBelow(){
        return getBlockBelow(0D);
    }

    Block getBlockBelow(double deltaY);
    float getJumpSpeedFactor();
}
