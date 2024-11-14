package cn.xiaozi0721.futureblock.block;

import cn.xiaozi0721.futureblock.Tags;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@SuppressWarnings(value={"deprecation", "NullableProblems"})
public class SmallAmethystBud extends AbstractAmethystCluster {
    public SmallAmethystBud() {
        String name = "small_amethyst_bud";
        this.setRegistryName(Tags.MOD_ID, name);
        this.setCreativeTab(CreativeTabs.DECORATIONS);
        this.setTranslationKey(Tags.MOD_ID + ".block." + name);
    }
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, facing);
    }
}
