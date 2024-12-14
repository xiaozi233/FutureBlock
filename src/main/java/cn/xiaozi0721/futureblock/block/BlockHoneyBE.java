package cn.xiaozi0721.futureblock.block;

import cn.xiaozi0721.futureblock.Tags;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

@SuppressWarnings({"deprecation", "NullableProblems"})
public class BlockHoneyBE extends BlockBaseHoney{
    protected static final AxisAlignedBB HONEY_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 1D, 0.9735D);

    public BlockHoneyBE(){
        String name = "honey_be";
        this.setRegistryName(Tags.MOD_ID, name);
        this.slipperiness = 0.8F;
        this.setTranslationKey(Tags.MOD_ID + ".block." + name);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return HONEY_AABB;
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn)
    {
        if (!entityIn.isSneaking())
        {
            double d0 = 0.4D + Math.abs((entityIn.motionY - 0.08) * 0.98) * 0.2D;
            System.out.println(d0);
            entityIn.motionX *= d0;
            entityIn.motionZ *= d0;
        }

        super.onEntityWalk(worldIn, pos, entityIn);
    }
}
