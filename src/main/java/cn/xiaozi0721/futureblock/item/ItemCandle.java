package cn.xiaozi0721.futureblock.item;

import cn.xiaozi0721.futureblock.block.BlockCandle;
import cn.xiaozi0721.futureblock.register.BlocksRegister;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@SuppressWarnings(value={"NullableProblems"})
public class ItemCandle extends ItemBlock {
    public ItemCandle(Block block) {
        super(block);
        this.setMaxDamage(0);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack itemstack = player.getHeldItem(hand);

        if (!itemstack.isEmpty() && player.canPlayerEdit(pos, facing, itemstack)) {
            IBlockState iblockstate = worldIn.getBlockState(pos);
            Block block = iblockstate.getBlock();
            BlockPos blockpos = pos;

            if (block != this.block && !block.isReplaceable(worldIn, pos)) {
                blockpos = pos.offset(facing);
                iblockstate = worldIn.getBlockState(blockpos);
                block = iblockstate.getBlock();
            }

            if (block == this.block) {
                int i = ((Integer)iblockstate.getValue(BlockCandle.CANDLES)).intValue();

                if (i < 4) {
                    IBlockState iblockstate1 = iblockstate.withProperty(BlockCandle.CANDLES, Integer.valueOf(i + 1));
                    AxisAlignedBB axisalignedbb = iblockstate1.getCollisionBoundingBox(worldIn, blockpos);

                    if (axisalignedbb != Block.NULL_AABB && worldIn.checkNoEntityCollision(axisalignedbb.offset(blockpos)) && worldIn.setBlockState(blockpos, iblockstate1, 10)) {
                        SoundType soundtype = this.block.getSoundType(iblockstate1, worldIn, pos, player);
                        worldIn.playSound(player, blockpos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);

                        if (player instanceof EntityPlayerMP) {
                            CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos, itemstack);
                        }

                        itemstack.shrink(1);
                        return EnumActionResult.SUCCESS;
                    }
                } else if (facing == EnumFacing.UP){
                    return EnumActionResult.FAIL;
                }
            }

            return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
        }
        else
        {
            return EnumActionResult.FAIL;
        }
    }

    public int getMetadata(int damage)
    {
        return damage;
    }

    public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing side, EntityPlayer player, ItemStack stack)
    {
        IBlockState state = world.getBlockState(pos);
        System.out.println((state.getBlock() != BlocksRegister.CANDLE || ((Integer)state.getValue(BlockCandle.CANDLES)) > 3));
        return state.getBlock() instanceof BlockCandle && ((Integer) state.getValue(BlockCandle.CANDLES)) <= 3 || super.canPlaceBlockOnSide(world, pos, side, player, stack);
    }
}
