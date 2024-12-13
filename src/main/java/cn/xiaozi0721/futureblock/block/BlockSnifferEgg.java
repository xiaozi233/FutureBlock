package cn.xiaozi0721.futureblock.block;

import cn.xiaozi0721.futureblock.Tags;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static cn.xiaozi0721.futureblock.FutureBlock.FUTUREBLOCK_TAB;

@SuppressWarnings(value={"deprecation", "NullableProblems"})
public class BlockSnifferEgg extends Block {
    protected static final AxisAlignedBB SNIFFER_EGG_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.125D, 0.9375D, 1D, 0.875D);

    public BlockSnifferEgg() {
        super(Material.DRAGON_EGG);
        String name = "sniffer_egg";
        this.setRegistryName(Tags.MOD_ID, name);
        this.setCreativeTab(FUTUREBLOCK_TAB);
        this.setTranslationKey(Tags.MOD_ID + ".block." + name);
        this.setHardness(0.5F);
        this.setResistance(0.5F);
//        this.setSoundType(CHAIN);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return SNIFFER_EGG_AABB;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
}
