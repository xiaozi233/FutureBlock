package cn.xiaozi0721.futureblock.mixin;

import cn.xiaozi0721.futureblock.interfaces.IBlockSpeedFactor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.SoundEvent;
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

import java.util.Random;

@SuppressWarnings("AddedMixinMembersNamePattern")
@Mixin(Entity.class)
public abstract class MixinEntity{
    @Shadow public double posX;
    @Shadow public double posY;
    @Shadow public double posZ;
    @Shadow public double motionX;
    @Shadow public double motionZ;
    @Shadow public float width;
    @Shadow public float fallDistance;
    @Shadow public int ticksExisted;
    @Shadow public World world;
    @Shadow protected Random rand;
    @Shadow public abstract AxisAlignedBB getEntityBoundingBox();
    @Shadow public abstract void playSound(SoundEvent soundIn, float volume, float pitch);

    @Inject(method = "move", at = @At(value = "INVOKE", target = "Lnet/minecraft/profiler/Profiler;endSection()V", ordinal = 1))
    private void applySpeedFactor(CallbackInfo ci){
        float speedFactor = this.getSpeedFactor();
        this.motionX *= speedFactor;
        this.motionZ *= speedFactor;
    }

    @Unique
    public float getJumpSpeedFactor(){
        float jumpSpeedFactor = ((IBlockSpeedFactor)this.getBlock()).getJumpSpeedFactor();
        float lowerBlockJumpSpeedFactor = ((IBlockSpeedFactor)this.getVelocityAffectingBlock()).getJumpSpeedFactor();
        return jumpSpeedFactor == 1.0F ? lowerBlockJumpSpeedFactor : jumpSpeedFactor;
    }

    @Unique
    public float getSpeedFactor(){
        float speedFactor = ((IBlockSpeedFactor)this.getBlock()).getSpeedFactor();
        float lowerBlockSpeedFactor = ((IBlockSpeedFactor)this.getVelocityAffectingBlock()).getSpeedFactor();
        return speedFactor == 1.0F ? lowerBlockSpeedFactor : speedFactor;
    }

    @Unique
    public Block getLowerBlock(double deltaY){
        int posX = MathHelper.floor(this.posX);
        int posY = MathHelper.floor(this.getEntityBoundingBox().minY - deltaY);
        int posZ = MathHelper.floor(this.posZ);

        BlockPos blockpos = new BlockPos(posX, posY, posZ);
        return world.getBlockState(blockpos).getBlock();
    }

    @Unique
    public Block getVelocityAffectingBlock(){
        return getLowerBlock(0.5000001D);
    }

    @Unique
    public Block getBlock(){
        return getLowerBlock(0.0D);
    }

    @Unique
    public IBlockState getLandingBlockState(){
        return this.world.getBlockState(this.getLandingPos());
    }

    @Unique
    public BlockPos getLandingPos() {
        int i = MathHelper.floor(this.posX);
        int j = MathHelper.floor(this.posY - 0.2F);
        int k = MathHelper.floor(this.posZ);
        BlockPos blockPos = new BlockPos(i, j, k);
        if (this.world.getBlockState(blockPos) == Material.AIR) {
            BlockPos blockPos2 = blockPos.down();
            Block block = this.world.getBlockState(blockPos2).getBlock();
            if (block instanceof BlockFence || block instanceof BlockFenceGate || block instanceof BlockWall) {
                return blockPos2;
            }
        }
        return blockPos;
    }
}
