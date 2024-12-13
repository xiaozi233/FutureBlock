package cn.xiaozi0721.futureblock.block;

import cn.xiaozi0721.futureblock.Tags;
import net.minecraft.block.BlockStructureVoid;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import static cn.xiaozi0721.futureblock.FutureBlock.FUTUREBLOCK_TAB;

@SuppressWarnings(value={"deprecation", "NullableProblems"})
public class BlockBEStructureVoid extends BlockStructureVoid {
    public BlockBEStructureVoid() {
        String name = "be_structure_void";
        this.setRegistryName(Tags.MOD_ID, name);
        this.setCreativeTab(FUTUREBLOCK_TAB);
        this.setTranslationKey(Tags.MOD_ID + ".block." + name);
        this.setBlockUnbreakable();
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.SOLID;
    }
}
