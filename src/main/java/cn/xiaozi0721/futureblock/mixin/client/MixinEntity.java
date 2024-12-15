package cn.xiaozi0721.futureblock.mixin.client;

import cn.xiaozi0721.futureblock.interfaces.IBlockSpeedFactor;
import cn.xiaozi0721.futureblock.interfaces.IGetBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class MixinEntity {
    @Shadow public double motionX;
    @Shadow public double motionY;
    @Shadow public double motionZ;
    @Shadow public abstract void setVelocity(double x, double y, double z);

    @Inject(method = "move", at = @At(value = "INVOKE", target = "Lnet/minecraft/profiler/Profiler;endSection()V", ordinal = 1))
    private void applySpeedFactor(MoverType type, double x, double y, double z, CallbackInfo ci){
        float speedFactor = getSpeedFactor();
        this.setVelocity(this.motionX * speedFactor, this.motionY, this.motionZ * speedFactor);
    }

    @Unique
    public float getSpeedFactor(){
        float speedFactor = ((IBlockSpeedFactor)((IGetBlock)this).getBlockBelow()).getSpeedFactor();
        float lowerBlockSpeedFactor = ((IBlockSpeedFactor)((IGetBlock)(this)).getBlockBelow(0.5D)).getSpeedFactor();
        boolean isFlying = (Entity)(Object)this instanceof EntityPlayer && ((EntityPlayer)(Object)this).capabilities.isFlying;
        boolean isElytraFlying = (Entity)(Object)this instanceof EntityLivingBase && ((EntityLivingBase)(Object)this).isElytraFlying();
        return !isFlying && !isElytraFlying && speedFactor == 1.0F ? lowerBlockSpeedFactor : speedFactor;
    }
}
