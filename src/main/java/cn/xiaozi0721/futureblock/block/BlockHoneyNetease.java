package cn.xiaozi0721.futureblock.block;

import cn.xiaozi0721.futureblock.Tags;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

@SuppressWarnings(value={"deprecation", "NullableProblems"})
public class BlockHoneyNetease extends BlockBaseHoney{
    protected static final AxisAlignedBB HONEY_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 1D, 0.9735D);

    public BlockHoneyNetease(){
        String name = "honey_netease";
        this.setRegistryName(Tags.MOD_ID, name);
        this.slipperiness = 0.8F;
        this.setTranslationKey(Tags.MOD_ID + ".block." + name);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return HONEY_AABB;
    }
}
