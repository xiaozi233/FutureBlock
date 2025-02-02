package cn.xiaozi0721.futureblock.mixin.client;

import cn.xiaozi0721.futureblock.mixin.MixinEntityPlayer;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.entity.EntityPlayerSP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EntityPlayerSP.class)
public abstract class MixinEntityPlayerSP extends MixinEntityPlayer {
    @Override
    public boolean shouldDisplaySoulSpeedEffects() {
        return !this.capabilities.isFlying && super.shouldDisplaySoulSpeedEffects();
    }

    @ModifyExpressionValue(method = "updateAutoJump", at = @At(value = "FIELD", target = "Lnet/minecraft/client/entity/EntityPlayerSP;onGround:Z"))
    private boolean considerJumpFactor(boolean original){
        return original && this.getJumpSpeedFactor() >= 1.0;
    }
}
