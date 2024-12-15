package cn.xiaozi0721.futureblock.block;

import cn.xiaozi0721.futureblock.register.ParticleRegister;
import cn.xiaozi0721.futureblock.sound.SoundEventRegister;
import cn.xiaozi0721.futureblock.util.Util;
import com.google.common.collect.ImmutableList;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

@SuppressWarnings({"deprecation", "NullableProblems"})
public class BlockCandle extends BlockIgnitable {
    public static final PropertyInteger CANDLES = PropertyInteger.create("candles", 1, 4);
    private static final Int2ObjectMap<List<Vec3d>> CANDLES_TO_PARTICLE_OFFSETS = Util.make(
            () -> {
                Int2ObjectMap<List<Vec3d>> int2ObjectMap = new Int2ObjectOpenHashMap<>();
                int2ObjectMap.defaultReturnValue(ImmutableList.of());
                int2ObjectMap.put(1, ImmutableList.of(new Vec3d(0.5, 0.5, 0.5)));
                int2ObjectMap.put(2, ImmutableList.of(new Vec3d(0.375, 0.44, 0.5), new Vec3d(0.625, 0.5, 0.44)));
                int2ObjectMap.put(3, ImmutableList.of(new Vec3d(0.5, 0.313, 0.625), new Vec3d(0.375, 0.44, 0.5), new Vec3d(0.56, 0.5, 0.44)));
                int2ObjectMap.put(
                        4, ImmutableList.of(new Vec3d(0.44, 0.313, 0.56), new Vec3d(0.625, 0.44, 0.56), new Vec3d(0.375, 0.44, 0.375), new Vec3d(0.56, 0.5, 0.375))
                );
                return Int2ObjectMaps.unmodifiable(int2ObjectMap);
            }
    );
    protected static final AxisAlignedBB[] CANDLE_AABB = new AxisAlignedBB[] {
            new AxisAlignedBB(0.4375, 0.0, 0.4375, 0.5625, 0.375, 0.5625),
            new AxisAlignedBB(0.3125, 0.0, 0.375, 0.6875, 0.375, 0.5625),
            new AxisAlignedBB(0.3125, 0.0, 0.375, 0.625, 0.375, 0.6875),
            new AxisAlignedBB(0.3125, 0.0, 0.3125, 0.6875, 0.375, 0.625),
    };

    public BlockCandle(MapColor mapColor){
        super(Material.CIRCUITS, mapColor);
        this.setDefaultState(this.blockState.getBaseState().withProperty(LIT, Boolean.valueOf(false)).withProperty(CANDLES, Integer.valueOf(1)));
        this.setHardness(0.1F);
        this.setSoundType(SoundEventRegister.CANDLE);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack itemstack = playerIn.getHeldItem(hand);
        if (state.getValue(LIT) && itemstack.isEmpty()){
            worldIn.setBlockState(pos, state.cycleProperty(LIT), 2);
            worldIn.playSound(playerIn, pos, SoundEventRegister.CANDLE_EXTINGUISH, SoundCategory.BLOCKS, 1F, 1F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);
            return true;
        } else {
            return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
        }
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return CANDLE_AABB[((Integer)state.getValue(CANDLES)).intValue()-1];
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        IBlockState iblockstate = worldIn.getBlockState(pos.down());
        Block block = iblockstate.getBlock();

        BlockFaceShape blockfaceshape = iblockstate.getBlockFaceShape(worldIn, pos.down(), EnumFacing.UP);
        return blockfaceshape == BlockFaceShape.SOLID || iblockstate.getBlock().isLeaves(iblockstate, worldIn, pos.down()) || block == this && ((Integer)iblockstate.getValue(CANDLES)).intValue() == 4;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        if(meta > 7) return this.getDefaultState().withProperty(CANDLES, Integer.valueOf(1)).withProperty(LIT, Boolean.valueOf(false));
        boolean lit = meta > 3;
        int candles = lit ? meta - 3 : meta + 1;
        return this.getDefaultState().withProperty(CANDLES, Integer.valueOf(candles)).withProperty(LIT, Boolean.valueOf(lit));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int candles = (state.getValue(CANDLES)).intValue() - 1;
        return state.getValue(LIT) ? candles + 4: candles;
    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random random){
        return (state.getValue(CANDLES));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {LIT, CANDLES});
    }

    @Override
    public int getLightValue(IBlockState state){
        return state.getValue(LIT) ? state.getValue(CANDLES) * 3 : 0;
    }

    @Override
    protected Iterable<Vec3d> getParticleOffsets(IBlockState state) {
        return (Iterable<Vec3d>)CANDLES_TO_PARTICLE_OFFSETS.get(((Integer)state.getValue(CANDLES)).intValue());
    }
}
