package cn.xiaozi0721.futureblock.mixin;

import cn.xiaozi0721.futureblock.interfaces.IApplySpeedFactor;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EntityLivingBase.class)
public abstract class MixinEntityLivingBase{
    @ModifyExpressionValue(method = "jump", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityLivingBase;getJumpUpwardsMotion()F"))
    private float applyJumpFactor(float jumpUpwardsMotion){
        return jumpUpwardsMotion * ((IApplySpeedFactor)this).getJumpSpeedFactor();
    }
}
