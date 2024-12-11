package cn.xiaozi0721.futureblock.mixin;

import cn.xiaozi0721.futureblock.interfaces.IBlockSpeedFactor;
import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Block.class)
public class MixinBlock implements IBlockSpeedFactor {
    @Unique float speedFactor = 1.0F;

    @Override
    public float getSpeedFactor() {
        return speedFactor;
    }
}
