package cn.xiaozi0721.futureblock.block;

import cn.xiaozi0721.futureblock.Tags;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

@SuppressWarnings({"deprecation", "NullableProblems", "DefaultNotLastCaseInSwitch"})
public class BlockAmethystCluster extends BlockBaseAmethystCluster {
    protected static final AxisAlignedBB AABB_NORTH = new AxisAlignedBB(0.1875D, 0.1875D, 0.5625D, 0.8125D, 0.8125D, 1D);
    protected static final AxisAlignedBB AABB_SOUTH = new AxisAlignedBB(0.1875D, 0.1875D, 0D, 0.8125D, 0.8125D, 0.4375D);
    protected static final AxisAlignedBB AABB_WEST = new AxisAlignedBB(0.5625D, 0.1875D, 0.1875D, 1D, 0.8125D, 0.8125D);
    protected static final AxisAlignedBB AABB_EAST = new AxisAlignedBB(0D, 0.1875D, 0.1875D, 0.4375D, 0.8125D, 0.8125D);
    protected static final AxisAlignedBB AABB_DOWN = new AxisAlignedBB(0.1875D, 0.5625D, 0.1875D, 0.8125D, 1D, 0.8125D);
    protected static final AxisAlignedBB AABB_UP = new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 0.8125D, 0.4375D, 0.8125D);

    public BlockAmethystCluster() {
        String name = "amethyst_cluster";
        this.setRegistryName(Tags.MOD_ID, name);
        this.setTranslationKey(Tags.MOD_ID + ".block." + name);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        AxisAlignedBB axisalignedbb;
        EnumFacing enumfacing = blockState.getValue(FACING);
        switch (enumfacing) {
            case NORTH:
            default:
                axisalignedbb = AABB_NORTH;
                break;
            case SOUTH:
                axisalignedbb = AABB_SOUTH;
                break;
            case WEST:
                axisalignedbb = AABB_WEST;
                break;
            case EAST:
                axisalignedbb = AABB_EAST;
                break;
            case UP:
                axisalignedbb = AABB_UP;
                break;
            case DOWN:
                axisalignedbb = AABB_DOWN;
                break;
        }
        return axisalignedbb;
    }
}
