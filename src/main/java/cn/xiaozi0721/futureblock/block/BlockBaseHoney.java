package cn.xiaozi0721.futureblock.block;

import cn.xiaozi0721.futureblock.FutureBlock;
import cn.xiaozi0721.futureblock.interfaces.IBlockSpeedFactor;
import cn.xiaozi0721.futureblock.sound.SoundEventRegister;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SuppressWarnings({"deprecation", "NullableProblems"})
public abstract class BlockBaseHoney extends BlockBreakable implements IBlockSpeedFactor {
    public float speedFactor = 0.4F;
    public float jumpSpeedFactor = 0.5F;
    protected BlockBaseHoney() {
        super(Material.CLAY, false, MapColor.ADOBE);
        this.setCreativeTab(FutureBlock.FUTUREBLOCK_TAB);
        this.setSoundType(SoundEventRegister.HONEY);
    }

    private static boolean hasHoneyBlockEffects(Entity entityIn) {
        return entityIn instanceof EntityLivingBase || entityIn instanceof EntityMinecart || entityIn instanceof EntityTNTPrimed || entityIn instanceof EntityBoat;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
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
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        entityIn.playSound(SoundEventRegister.HONEY_SLIDE, 1.0F, 1.0F);
        entityIn.fall(fallDistance, 0.2F);
        if (worldIn.isRemote) {
            spawnParticles(entityIn, 10);
        }
        entityIn.playSound(this.blockSoundType.getFallSound(), this.blockSoundType.getVolume() * 0.25F, this.blockSoundType.getPitch() * 0.75F);
    }

    @Override
    public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        if (this.isSliding(pos, entityIn)) {
            this.updateSlidingVelocity(entityIn);
            this.addCollisionEffects(worldIn, entityIn);
        }
    }

    private boolean isSliding(BlockPos pos, Entity entityIn) {
        if (entityIn.onGround) {
            return false;
        } else if (entityIn.posY > (double) pos.getY() + 0.9375 - 1.0E-7) {
            return false;
        } else if (entityIn.motionY >= -0.08) {
            return false;
        } else {
            double distanceX = Math.abs((double) pos.getX() + 0.5 - entityIn.posX);
            double distanceZ = Math.abs((double) pos.getZ() + 0.5 - entityIn.posZ);
            double distanceToEdge = 0.4375 + (double) (entityIn.width / 2.0F);
            return distanceX + 1.0E-7 > distanceToEdge || distanceZ + 1.0E-7 > distanceToEdge;
        }
    }

    private void updateSlidingVelocity(Entity entityIn) {
        if (entityIn.motionY < -0.13) {
            double mult = -0.05 / entityIn.motionY;
            entityIn.motionX *= mult;
            entityIn.motionZ *= mult;
        }
        entityIn.motionY = -0.05;
        entityIn.fallDistance = 0;
    }

    private void addCollisionEffects(World worldIn, Entity entityIn) {
        if (hasHoneyBlockEffects(entityIn)) {
            if (worldIn.rand.nextInt(5) == 0) {
                entityIn.playSound(SoundEventRegister.HONEY_SLIDE, 1.0F, 1.0F);
            }
            if (worldIn.isRemote && worldIn.rand.nextInt(5) == 0) {
                spawnParticles(entityIn, 5);
            }
        }
    }

    private void spawnParticles(Entity entityIn, int particleCount){
        for (int i = 0; i < particleCount; i++) {
            entityIn.world.spawnParticle(
                    EnumParticleTypes.BLOCK_CRACK, entityIn.posX, entityIn.posY, entityIn.posZ,
                    0.0, 0.0, 0.0, getStateId(getDefaultState())
            );
        }
    }

    @Override
    public float getSpeedFactor() {
        return speedFactor;
    }

    @Override
    public float getJumpSpeedFactor() {
        return jumpSpeedFactor;
    }
}
