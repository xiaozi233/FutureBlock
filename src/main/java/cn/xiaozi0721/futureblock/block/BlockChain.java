package cn.xiaozi0721.futureblock.block;

import cn.xiaozi0721.futureblock.Tags;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static cn.xiaozi0721.futureblock.sound.SoundEventRegister.CHAIN;

@SuppressWarnings(value={"deprecation", "NullableProblems"})
public class BlockChain extends BlockRotatedPillar {
    protected static final AxisAlignedBB Y_AXIS_AABB = new AxisAlignedBB(0.40625D, 0.0D, 0.40625D, 0.59375D, 1.0D, 0.59375D);
    protected static final AxisAlignedBB Z_AXIS_AABB = new AxisAlignedBB(0.40625D, 0.40625D, 0.0D, 0.59375D, 0.59375D, 1.0D);
    protected static final AxisAlignedBB X_AXIS_AABB = new AxisAlignedBB(0.0D, 0.40625D, 0.40625D, 1.0D, 0.59375D, 0.59375D);

    public BlockChain() {
        super(Material.IRON);
        String name = "chain";
        this.setRegistryName(Tags.MOD_ID, name);
        this.setCreativeTab(CreativeTabs.DECORATIONS);
        this.setTranslationKey(Tags.MOD_ID + ".block." + name);
        this.setDefaultState(this.blockState.getBaseState().withProperty(AXIS, EnumFacing.Axis.X));
        this.setLightOpacity(0);
        this.setSoundType(CHAIN);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        EnumFacing.Axis enumAxis = state.getValue(AXIS);
        switch (enumAxis){
            case X:
            default:
                return X_AXIS_AABB;
            case Y:
                return Y_AXIS_AABB;
            case Z:
                return Z_AXIS_AABB;
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing.Axis enumAxis;
        switch (meta)
        {
            case 0:
            default:
                enumAxis = EnumFacing.Axis.X;
                break;
            case 1:
                enumAxis = EnumFacing.Axis.Y;
                break;
            case 2:
                enumAxis = EnumFacing.Axis.Z;
                break;
        }

        return this.getDefaultState().withProperty(AXIS, enumAxis);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        switch (state.getValue(AXIS))
        {
            case X:
            default:
                return 0;
            case Y:
                return 1;
            case Z:
                return 2;
        }
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }
    @Override
    public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side)
    {
        return true;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }
}
