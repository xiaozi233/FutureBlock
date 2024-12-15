package cn.xiaozi0721.futureblock.mixin;

import cn.xiaozi0721.futureblock.block.BlockBaseHoney;
import cn.xiaozi0721.futureblock.interfaces.IGetBlock;
import cn.xiaozi0721.futureblock.interfaces.IBlockSpeedFactor;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class MixinEntity implements IGetBlock {
    @Shadow public double posX;
    @Shadow public double posZ;
    @Shadow public World world;
    @Shadow public abstract AxisAlignedBB getEntityBoundingBox();

    @Override
    public Block getBlockBelow(double deltaY){
        int posX = MathHelper.floor(this.posX);
        int posY = MathHelper.floor(this.getEntityBoundingBox().minY - deltaY - 1.0E-7);
        int posZ = MathHelper.floor(this.posZ);

        BlockPos blockpos = new BlockPos(posX, posY, posZ);
        return world.getBlockState(blockpos).getBlock();
    }

    @Override
    public boolean isUponHoneyBlock(){
        Block block = this.getBlockBelow();
        return block instanceof BlockBaseHoney;
    }
}
