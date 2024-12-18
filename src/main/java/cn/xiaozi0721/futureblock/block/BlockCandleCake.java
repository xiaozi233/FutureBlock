package cn.xiaozi0721.futureblock.block;

import cn.xiaozi0721.futureblock.sound.SoundEventRegister;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCake;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.*;


@SuppressWarnings({"deprecation", "NullableProblems"})
public class BlockCandleCake extends BlockIgnitable{
    protected static final AxisAlignedBB CAKE_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.5D, 0.9375D);
    protected static final AxisAlignedBB CANDLE_AABB = new AxisAlignedBB(0.4375D, 0.5D, 0.4375D, 0.5625D, 0.875D, 0.5625D);
    protected static final AxisAlignedBB CANDLE_CAKE_AABB = CAKE_AABB.union(CANDLE_AABB);
    private static final Iterable<Vec3d> PARTICLE_OFFSETS = ImmutableList.<Vec3d>of(new Vec3d(0.5, 1.0, 0.5));
    private static final Map<BlockCandle, BlockCandleCake> CANDLES_TO_CANDLE_CAKES = Maps.<BlockCandle, BlockCandleCake>newHashMap();
    private final Item modelItem;

    public BlockCandleCake(Block modelBlock, MapColor mapColor) {
        super(Material.CAKE, mapColor);
        this.setHardness(0.5F);
        if (modelBlock instanceof BlockCandle) {
            this.modelItem = Item.getItemFromBlock(modelBlock);
            CANDLES_TO_CANDLE_CAKES.put((BlockCandle) modelBlock, this);
        } else {
            throw new IllegalArgumentException("Expected block to be of " + BlockCandle.class + " was " + modelBlock.getClass());
        }
        this.setSoundType(SoundType.CLOTH);
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState) {
        addCollisionBoxToList(pos, entityBox, collidingBoxes, CAKE_AABB);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, CANDLE_AABB);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return CANDLE_CAKE_AABB;
    }

//    @Override @SideOnly(Side.CLIENT)
//    public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
//        return CANDLE_CAKE_AABB;
//    }

    @Override @Nullable
    public RayTraceResult collisionRayTrace(IBlockState blockState, World worldIn, BlockPos pos, Vec3d start, Vec3d end) {
        List<RayTraceResult> list = Lists.<RayTraceResult>newArrayList();
        list.add(this.rayTrace(pos, start, end, CAKE_AABB));
        list.add(this.rayTrace(pos, start, end, CANDLE_AABB));

        RayTraceResult raytraceresult1 = null;
        double d1 = 0.0D;

        for (RayTraceResult raytraceresult : list) {
            if (raytraceresult != null) {
                double d0 = raytraceresult.hitVec.squareDistanceTo(end);

                if (d0 > d1) {
                    raytraceresult1 = raytraceresult;
                    d1 = d0;
                }
            }
        }
        return raytraceresult1;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        switch (meta){
            case 1: return this.getDefaultState().withProperty(LIT, Boolean.valueOf(true));
            case 0: default: return this.getDefaultState().withProperty(LIT, Boolean.valueOf(false));
        }
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(LIT) ? 1 : 0;
    }

    @Override
    protected Iterable<Vec3d> getParticleOffsets(IBlockState state) {
        return PARTICLE_OFFSETS;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack itemstack = playerIn.getHeldItem(hand);
        if (hitY > 0.5F){
            if (state.getValue(LIT) && itemstack.isEmpty()){
                worldIn.setBlockState(pos, state.cycleProperty(LIT), 2);
                worldIn.playSound(playerIn, pos, SoundEventRegister.CANDLE_EXTINGUISH, SoundCategory.BLOCKS, 1F, 1F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);
                return true;
            } else {
                return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
            }
        } else {
            return eatCandleCake(worldIn, pos, state, playerIn);
        }
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return modelItem;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public int getLightValue(IBlockState state){
        return state.getValue(LIT) ? 3 : 0;
    }

    private boolean eatCandleCake(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
        if (!player.canEat(false)) {
            return false;
        }
        else {
            player.addStat(StatList.CAKE_SLICES_EATEN);
            player.getFoodStats().addStats(2, 0.1F);

            worldIn.setBlockState(pos, Blocks.CAKE.getDefaultState().withProperty(BlockCake.BITES, Integer.valueOf(1)), 3);
            this.dropBlockAsItem(worldIn, pos, state, 0);

            return true;
        }
    }

    public static IBlockState getCandleCakeFromCandle(BlockCandle candle) {
        return ((BlockCandleCake)CANDLES_TO_CANDLE_CAKES.get(candle)).getDefaultState();
    }
}
