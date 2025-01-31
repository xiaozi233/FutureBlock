package cn.xiaozi0721.futureblock.mixin;

import cn.xiaozi0721.futureblock.interfaces.IBlockSpeedFactor;
import cn.xiaozi0721.futureblock.interfaces.IApplySpeedFactor;
import net.minecraft.entity.Entity;
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
    @Shadow public World world;
    @Shadow public double posX;
    @Shadow public double posZ;
    @Shadow public double motionX;
    @Shadow public double motionZ;
    @Shadow public abstract AxisAlignedBB getEntityBoundingBox();

    @Inject(method = "move", at = @At(value = "INVOKE", target = "Lnet/minecraft/profiler/Profiler;endSection()V", ordinal = 1))
    private void applySpeedFactor(CallbackInfo ci){
        float speedFactor = getSpeedFactor();
        this.motionX *= speedFactor;
        this.motionZ *= speedFactor;
    }

    @Override
    public float getJumpSpeedFactor(){
        float jumpSpeedFactor = this.getBlockBelow().getJumpSpeedFactor();
        float lowerBlockJumpSpeedFactor = this.getBlockBelow(0.5D).getJumpSpeedFactor();
        return jumpSpeedFactor == 1.0F ? lowerBlockJumpSpeedFactor : jumpSpeedFactor;
    }

    @Unique
    public float getSpeedFactor(){
        float speedFactor = this.getBlockBelow().getSpeedFactor();
        float lowerBlockSpeedFactor = this.getBlockBelow(0.5D).getSpeedFactor();
        return speedFactor == 1.0F ? lowerBlockSpeedFactor : speedFactor;
    }

    @Unique
    public IBlockSpeedFactor getBlockBelow(double deltaY){
        int posX = MathHelper.floor(this.posX);
        int posY = MathHelper.floor(this.getEntityBoundingBox().minY - deltaY);
        int posZ = MathHelper.floor(this.posZ);

        BlockPos blockpos = new BlockPos(posX, posY, posZ);
        return (IBlockSpeedFactor)world.getBlockState(blockpos).getBlock();
    }

    @Unique
    public IBlockSpeedFactor getBlockBelow(){
        return getBlockBelow(0D);
    }
}
