package cn.xiaozi0721.futureblock.mixin;

import cn.xiaozi0721.futureblock.interfaces.IBlockSpeedFactor;
import cn.xiaozi0721.futureblock.interfaces.IApplySpeedFactor;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Entity.class)
public abstract class MixinEntity implements IApplySpeedFactor {
    @Shadow public double posX;
    @Shadow public double posZ;
    @Shadow public World world;
    @Shadow public abstract AxisAlignedBB getEntityBoundingBox();

    @Override
    public Block getBlockBelow(double deltaY){
        int posX = MathHelper.floor(this.posX);
        int posY = MathHelper.floor(this.getEntityBoundingBox().minY - deltaY - 1.0E-7);
        int posZ = MathHelper.floor(this.posZ);

        BlockPos blockpos = new BlockPos(posX, posY, posZ);
        return world.getBlockState(blockpos).getBlock();
    }

    @Override
    public float getJumpSpeedFactor(){
        float jumpSpeedFactor = ((IBlockSpeedFactor)(this).getBlockBelow()).getJumpSpeedFactor();
        float lowerBlockJumpSpeedFactor = ((IBlockSpeedFactor)(this).getBlockBelow(0.5D)).getJumpSpeedFactor();
        return jumpSpeedFactor == 1.0F ? lowerBlockJumpSpeedFactor : jumpSpeedFactor;
    }
}
