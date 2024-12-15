package cn.xiaozi0721.futureblock.mixin;

import cn.xiaozi0721.futureblock.interfaces.IApplySpeedFactor;
import net.minecraft.entity.monster.EntityMagmaCube;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityMagmaCube.class)
public abstract class MixinEntityMagmaCube {
    @Redirect(method = "jump", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/monster/EntityMagmaCube;motionY:D"))
    public void applyJumpFactor(EntityMagmaCube entity, double jumpUpwardsMotion){
        entity.motionY = jumpUpwardsMotion * ((IApplySpeedFactor)this).getJumpSpeedFactor();
    }
}
