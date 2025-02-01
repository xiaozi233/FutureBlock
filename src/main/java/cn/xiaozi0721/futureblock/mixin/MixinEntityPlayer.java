package cn.xiaozi0721.futureblock.mixin;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityPlayer.class)
public abstract class MixinEntityPlayer extends MixinEntityLivingBase {
    @Shadow public PlayerCapabilities capabilities;

    @Override
    public float getSpeedFactor(){
        return this.capabilities.isFlying || this.isElytraFlying() ? 1.0F : super.getSpeedFactor();
    }

    @Override
    protected boolean shouldRemoveSoulSpeedBoost(IBlockState landingState) {
        return this.capabilities.isFlying  || super.shouldRemoveSoulSpeedBoost(landingState);
    }

    @Override
    protected boolean isOnSoulSpeedBlock() {
        return !this.capabilities.isFlying && super.isOnSoulSpeedBlock();
    }
}
