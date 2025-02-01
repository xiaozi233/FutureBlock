package cn.xiaozi0721.futureblock.mixin;

import net.minecraft.entity.monster.EntitySlime;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(EntitySlime.class)
public abstract class MixinEntitySlime extends MixinEntityLivingBase{
    @ModifyConstant(method = "jump", constant = @Constant(doubleValue = 0.41999998688697815D))
    private double applyJumpFactor(double jumpUpwardsMotion){
        return jumpUpwardsMotion * this.getJumpSpeedFactor();
    }
}
