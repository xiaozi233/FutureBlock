package cn.xiaozi0721.futureblock.mixin;

import net.minecraft.entity.monster.EntityMagmaCube;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityMagmaCube.class)
public abstract class MixinEntityMagmaCube extends MixinEntitySlime{
    @Redirect(method = "jump", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/monster/EntityMagmaCube;motionY:D", opcode = Opcodes.PUTFIELD))
    private void applyJumpFactor(EntityMagmaCube entity, double jumpUpwardsMotion){
        entity.motionY = jumpUpwardsMotion + (0.42F - 1) * this.getJumpSpeedFactor();
    }
}
