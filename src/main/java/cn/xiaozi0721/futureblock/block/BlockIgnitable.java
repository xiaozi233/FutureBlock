package cn.xiaozi0721.futureblock.block;

import cn.xiaozi0721.futureblock.FutureBlock;
import cn.xiaozi0721.futureblock.register.ParticleRegister;
import cn.xiaozi0721.futureblock.register.SoundEventRegister;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;


@SuppressWarnings({"NullableProblems", "UnnecessaryBoxing"})
public abstract class BlockIgnitable extends Block {
    public static final PropertyBool LIT = PropertyBool.create("lit");
    protected abstract Iterable<Vec3d> getParticleOffsets(IBlockState state);

    protected BlockIgnitable(Material material, MapColor mapColor) {
        super(material, mapColor);
        this.setDefaultState(this.blockState.getBaseState().withProperty(LIT, Boolean.valueOf(false)));
        this.setCreativeTab(FutureBlock.FUTUREBLOCK_TAB);
        useNeighborBrightness = true;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack itemstack = playerIn.getHeldItem(hand);
        if (state.getValue(LIT) && itemstack.isEmpty()){
            worldIn.setBlockState(pos, state.cycleProperty(LIT), 2);
            worldIn.playSound(playerIn, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.25F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);
        } else if (!state.getValue(LIT) && (itemstack.getItem() == Items.FLINT_AND_STEEL || itemstack.getItem() == Items.FIRE_CHARGE)){
            worldIn.setBlockState(pos, state.cycleProperty(LIT), 2);
            if (itemstack.getItem() == Items.FLINT_AND_STEEL) {
                worldIn.playSound(playerIn, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, worldIn.rand.nextFloat() * 0.4F + 0.8F);
                itemstack.damageItem(1, playerIn);
            }
            else {
                worldIn.playSound(playerIn, pos, SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.BLOCKS, 1.0F, worldIn.rand.nextFloat() * 0.2F + 1.0F);
                if (!playerIn.capabilities.isCreativeMode){
                    itemstack.shrink(1);
                }
            }
        } else {
            return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
        }
        return true;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {LIT});
    }

    @Override @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (stateIn.getValue(LIT)){
            this.getParticleOffsets(stateIn).forEach(offset -> spawnCandleParticles(worldIn, offset.add((double)pos.getX(), (double)pos.getY(), (double)pos.getZ()), rand));
        }
    }

    private static void spawnCandleParticles(World worldIn, Vec3d vec3d, Random rand) {
        float f = rand.nextFloat();
        if (f < 0.3F) {
            worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, vec3d.x, vec3d.y, vec3d.z, 0.0, 0.0, 0.0);
            if (f < 0.17F) {
                worldIn.playSound(
                        vec3d.x + 0.5,
                        vec3d.y + 0.5,
                        vec3d.z + 0.5,
                        SoundEventRegister.CANDLE_AMBIENT,
                        SoundCategory.BLOCKS,
                        1.0F + rand.nextFloat(),
                        rand.nextFloat() * 0.7F + 0.3F,
                        false
                );
            }
        }

        worldIn.spawnParticle(ParticleRegister.SMALL_FLAME, vec3d.x, vec3d.y, vec3d.z, 0.0, 0.0, 0.0);
    }
}
