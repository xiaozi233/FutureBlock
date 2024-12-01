package cn.xiaozi0721.futureblock.block;

import cn.xiaozi0721.futureblock.Tags;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

@SuppressWarnings(value={"deprecation", "NullableProblems"})
public class SmallAmethystBud extends AbstractAmethystCluster {
    protected static final AxisAlignedBB AABB_NORTH = new AxisAlignedBB(0.25, 0.25, 0.8125D, 0.75D, 0.75D, 1D);
    protected static final AxisAlignedBB AABB_SOUTH = new AxisAlignedBB(0.25, 0.25, 0D, 0.75D, 0.75D, 0.1875D);
    protected static final AxisAlignedBB AABB_WEST = new AxisAlignedBB(0.8125D, 0.25D, 0.25D, 1D, 0.75D, 0.75D);
    protected static final AxisAlignedBB AABB_EAST = new AxisAlignedBB(0D, 0.25D, 0.25D, 0.1875D, 0.75D, 0.75D);
    protected static final AxisAlignedBB AABB_DOWN = new AxisAlignedBB(0.25D, 0.8125D, 0.25D, 0.75D, 1D, 0.75D);
    protected static final AxisAlignedBB AABB_UP = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.1875D, 0.75D);

    public SmallAmethystBud() {
        String name = "small_amethyst_bud";
        this.setRegistryName(Tags.MOD_ID, name);
        this.setCreativeTab(CreativeTabs.DECORATIONS);
        this.setTranslationKey(Tags.MOD_ID + ".block." + name);
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, facing);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return getBox(blockState, worldIn, pos);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return getBox(blockState, worldIn, pos);
    }

    public AxisAlignedBB getBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos){
        AxisAlignedBB axisalignedbb;
        EnumFacing enumfacing = (EnumFacing)blockState.getValue(FACING);
        switch (enumfacing)
        {
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
