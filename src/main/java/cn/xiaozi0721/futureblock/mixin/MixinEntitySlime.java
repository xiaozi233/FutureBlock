package cn.xiaozi0721.futureblock.mixin;

import cn.xiaozi0721.futureblock.interfaces.IApplySpeedFactor;
import net.minecraft.entity.monster.EntitySlime;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntitySlime.class)
public abstract class MixinEntitySlime {
    @Redirect(method = "jump", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/monster/EntitySlime;motionY:D"))
    public void applyJumpFactor(EntitySlime entity, double jumpUpwardsMotion){
        entity.motionY = jumpUpwardsMotion * ((IApplySpeedFactor)this).getJumpSpeedFactor();
    }
}
