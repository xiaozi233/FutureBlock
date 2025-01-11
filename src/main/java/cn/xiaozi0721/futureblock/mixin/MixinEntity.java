package cn.xiaozi0721.futureblock.mixin;

import cn.xiaozi0721.futureblock.interfaces.IBlockSpeedFactor;
import cn.xiaozi0721.futureblock.interfaces.IApplySpeedFactor;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class MixinEntity implements IApplySpeedFactor {
    @Shadow public double posX;
    @Shadow public double posZ;
    @Shadow public World world;
    @Shadow public double motionX;
    @Shadow public double motionZ;
    @Shadow public abstract AxisAlignedBB getEntityBoundingBox();

    @Inject(method = "move", at = @At(value = "INVOKE", target = "Lnet/minecraft/profiler/Profiler;endSection()V", ordinal = 1))
    private void applySpeedFactor(MoverType type, double x, double y, double z, CallbackInfo ci){
        float speedFactor = futureblock$getSpeedFactor();
        this.motionX *= speedFactor;
        this.motionZ *= speedFactor;
    }

    @Unique
    public float futureblock$getSpeedFactor(){
        float speedFactor = ((IBlockSpeedFactor)this.getBlockBelow()).getSpeedFactor();
        float lowerBlockSpeedFactor = ((IBlockSpeedFactor)this.futureblock$getBlockBelow(0.5D)).getSpeedFactor();
        boolean isFlying = (Entity)(Object)this instanceof EntityPlayer && ((EntityPlayer)(Object)this).capabilities.isFlying;
        boolean isElytraFlying = (Entity)(Object)this instanceof EntityLivingBase && ((EntityLivingBase)(Object)this).isElytraFlying();
        return isFlying || isElytraFlying ? 1.0F : speedFactor == 1.0F ? lowerBlockSpeedFactor : speedFactor;
    }

    @Override
    public Block futureblock$getBlockBelow(double deltaY){
        int posX = MathHelper.floor(this.posX);
        int posY = MathHelper.floor(this.getEntityBoundingBox().minY - deltaY);
        int posZ = MathHelper.floor(this.posZ);

        BlockPos blockpos = new BlockPos(posX, posY, posZ);
        return world.getBlockState(blockpos).getBlock();
    }

    @Override
    public float futureblock$getJumpSpeedFactor(){
        float jumpSpeedFactor = ((IBlockSpeedFactor)(this).getBlockBelow()).getJumpSpeedFactor();
        float lowerBlockJumpSpeedFactor = ((IBlockSpeedFactor)(this).futureblock$getBlockBelow(0.5D)).getJumpSpeedFactor();
        return jumpSpeedFactor == 1.0F ? lowerBlockJumpSpeedFactor : jumpSpeedFactor;
    }
}
