package cn.xiaozi0721.futureblock.block;

import cn.xiaozi0721.futureblock.Tags;
import cn.xiaozi0721.futureblock.interfaces.IBlockSpeedFactor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

@SuppressWarnings(value={"deprecation", "NullableProblems"})
public class BlockHoney extends BlockBaseHoney implements IBlockSpeedFactor {
    protected static final AxisAlignedBB HONEY_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.9375D, 0.9735D);

    public BlockHoney(){
        String name = "honey";
        this.setRegistryName(Tags.MOD_ID, name);
        this.setTranslationKey(Tags.MOD_ID + ".block." + name);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return HONEY_AABB;
    }

    @Override
    public float getSpeedFactor() {
        return 0.4F;
    }
}
