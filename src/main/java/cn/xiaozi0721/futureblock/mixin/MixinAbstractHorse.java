package cn.xiaozi0721.futureblock.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.passive.AbstractHorse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AbstractHorse.class)
public abstract class MixinAbstractHorse extends MixinEntityLivingBase{
    @ModifyExpressionValue(method = "travel", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/AbstractHorse;getHorseJumpStrength()D"))
    private double applyJumpFactor(double original){
        return original * this.getJumpSpeedFactor();
    }
}
