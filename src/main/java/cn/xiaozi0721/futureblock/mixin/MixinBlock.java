package cn.xiaozi0721.futureblock.mixin;

import cn.xiaozi0721.futureblock.interfaces.IBlockSpeedFactor;
import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@SuppressWarnings("AddedMixinMembersNamePattern")
@Mixin(Block.class)
public abstract class MixinBlock implements IBlockSpeedFactor {
    @Unique public float speedFactor = 1.0F;
    @Unique public float jumpSpeedFactor = 1.0F;

    @Override
    public float getSpeedFactor() {
        return speedFactor;
    }

    @Override
    public float getJumpSpeedFactor() {
        return jumpSpeedFactor;
    }
}
