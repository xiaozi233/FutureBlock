package cn.xiaozi0721.futureblock.mixin;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings({"DataFlowIssue", "ConstantValue"})
@Mixin(EntityPlayer.class)
public abstract class MixinEntityPlayer extends MixinEntity {
    @Shadow public PlayerCapabilities capabilities;

    @Override
    public float getSpeedFactor(){
        return this.capabilities.isFlying || ((EntityPlayer)(Object)this).isElytraFlying() ? 1.0F : super.getSpeedFactor();
    }
}
