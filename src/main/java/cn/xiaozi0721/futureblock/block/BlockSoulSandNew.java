package cn.xiaozi0721.futureblock.block;

import cn.xiaozi0721.futureblock.Tags;
import cn.xiaozi0721.futureblock.interfaces.IBlockSpeedFactor;
import cn.xiaozi0721.futureblock.tab.FutureBlockTab;
import net.minecraft.block.BlockSoulSand;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@SuppressWarnings("NullableProblems")
public class BlockSoulSandNew extends BlockSoulSand implements IBlockSpeedFactor {
    public static float speedFactor = 0.4F;
    public static float jumpSpeedFactor = 1F;

    public BlockSoulSandNew() {
        String name = "soul_sand_new";
        this.setRegistryName(Tags.MOD_ID, name);
        this.setTranslationKey(Tags.MOD_ID + ".block." + name);
        this.setHardness(0.5F);
        this.setSoundType(SoundType.SAND);
        this.setCreativeTab(FutureBlockTab.FUTUREBLOCK_TAB);
    }

    @Override
    public float getSpeedFactor() {
        return speedFactor;
    }

    @Override
    public float getJumpSpeedFactor() {
        return jumpSpeedFactor;
    }

    @Override
    public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
    }
}
