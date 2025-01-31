package cn.xiaozi0721.futureblock.block;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@SuppressWarnings({"NullableProblems"})
public class BlockHoneyBE extends BlockHoneyNetease {
    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        if (!entityIn.isSneaking()) {
            double d0 = 0.4D + Math.abs((entityIn.motionY - 0.08D) * 0.98D) * 0.2D;
            entityIn.motionX *= d0;
            entityIn.motionZ *= d0;
        }
        super.onEntityWalk(worldIn, pos, entityIn);
    }
}
