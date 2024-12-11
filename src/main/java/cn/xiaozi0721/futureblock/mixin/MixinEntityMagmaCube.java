package cn.xiaozi0721.futureblock.mixin;

import cn.xiaozi0721.futureblock.interfaces.IGetBlock;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.monster.EntityMagmaCube;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EntityMagmaCube.class)
public abstract class MixinEntityMagmaCube {
    @ModifyExpressionValue(method = "jump", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/monster/EntityMagmaCube;motionY:D"))
    public float applyJumpFactor(float jumpUpwardsMotion){
        return ((IGetBlock)this).isUponHoneyBlock() ? jumpUpwardsMotion * 0.5F : jumpUpwardsMotion;
    }
}
