package cn.xiaozi0721.futureblock.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

@SuppressWarnings({"deprecation", "NullableProblems"})
public class BlockHoneyNetease extends BlockBaseHoney {
    protected static final AxisAlignedBB HONEY_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 1D, 0.9735D);

    public BlockHoneyNetease(){
        this.slipperiness = 0.8F;
        this.speedFactor = 1F;
        this.jumpSpeedFactor = 0.6F;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return HONEY_AABB;
    }
}
