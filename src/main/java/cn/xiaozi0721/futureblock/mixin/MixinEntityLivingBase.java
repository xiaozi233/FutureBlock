package cn.xiaozi0721.futureblock.mixin;

import cn.xiaozi0721.futureblock.enchantment.EnchantmentHelper;
import cn.xiaozi0721.futureblock.register.BlocksRegister;
import cn.xiaozi0721.futureblock.register.EnchantmentRegister;
import cn.xiaozi0721.futureblock.register.ParticleRegister;
import cn.xiaozi0721.futureblock.register.SoundEventRegister;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@SuppressWarnings("AddedMixinMembersNamePattern")
@Mixin(EntityLivingBase.class)
public abstract class MixinEntityLivingBase extends MixinEntity {
    @Shadow public abstract boolean isElytraFlying();
    @Shadow public abstract IAttributeInstance getEntityAttribute(IAttribute attribute);

    @Unique private static final UUID SOUL_SPEED_BOOST_ID = UUID.fromString("87f46a96-686f-4796-b035-22e16ee9e038");

    @ModifyExpressionValue(method = "jump", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityLivingBase;getJumpUpwardsMotion()F"))
    private float applyJumpFactor(float jumpUpwardsMotion){
        return jumpUpwardsMotion * this.getJumpSpeedFactor();
    }

    @Inject(method = "onEntityUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;onEntityUpdate()V"))
    private void displaySoulSpeedEffects(CallbackInfo ci){
        if (this.shouldDisplaySoulSpeedEffects()) {
            this.displaySoulSpeedEffects();
        }
    }

    @Inject(method = "onEntityUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityLivingBase;frostWalk(Lnet/minecraft/util/math/BlockPos;)V", shift = At.Shift.AFTER))
    private void soulSpeedBoost(CallbackInfo ci){
        if (this.shouldRemoveSoulSpeedBoost(this.getLandingBlockState())) {
            this.removeSoulSpeedBoost();
        }
        this.tryAddSoulSpeedBoost();
    }

    @Inject(method ="updateFallState", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/EntityLivingBase;world:Lnet/minecraft/world/World;", ordinal = 0))
    private void soulSpeedBoostWhenLanding(double y, boolean onGroundIn, IBlockState state, BlockPos pos, CallbackInfo ci){
        if (!this.world.isRemote && onGroundIn && this.fallDistance > 0.0F) {
            this.removeSoulSpeedBoost();
            this.tryAddSoulSpeedBoost();
        }
    }

    @Override
    public float getSpeedFactor(){
        return isOnSoulSpeedBlock() && EnchantmentHelper.hasSoulSpeed(getEntity()) ? 1.0F : super.getSpeedFactor();
    }

    @Unique
    public boolean shouldDisplaySoulSpeedEffects() {
        boolean isPlayer = getEntity() instanceof EntityPlayer;
        return this.ticksExisted % 5 == 0
                && this.motionX != 0.0
                && this.motionZ != 0.0
                && (!isPlayer || !((EntityPlayer)getEntity()).isSpectator())
                && EnchantmentHelper.hasSoulSpeed(getEntity())
                && this.isOnSoulSpeedBlock();
    }

    @Unique
    protected void displaySoulSpeedEffects() {
        this.world.spawnParticle(
                ParticleRegister.SOUL,
                this.posX + (this.rand.nextDouble() - 0.5) * (double)this.width,
                this.posY + 0.1,
                this.posZ + (this.rand.nextDouble() - 0.5) * (double)this.width,
                this.motionX * -0.2,
                0.1,
                this.motionZ * -0.2
        );
        float f = this.rand.nextFloat() * 0.4F + this.rand.nextFloat() > 0.9F ? 0.6F : 0.0F;
        this.playSound(SoundEventRegister.PARTICLE_SOUL_ESCAPE, f, 0.6F + this.rand.nextFloat() * 0.4F);
    }

    @Unique
    protected boolean isOnSoulSpeedBlock() {
        return this.getVelocityAffectingBlock() == BlocksRegister.SOUL_SAND_NEW;
    }

    @Unique
    protected boolean shouldRemoveSoulSpeedBoost(IBlockState landingState) {
        return landingState != Blocks.AIR.getDefaultState() || this.isElytraFlying();
    }

    @Unique
    protected void removeSoulSpeedBoost() {
        IAttributeInstance iattributeinstance = this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
        if (iattributeinstance != null) {
            if (iattributeinstance.getModifier(SOUL_SPEED_BOOST_ID) != null) {
                iattributeinstance.removeModifier(SOUL_SPEED_BOOST_ID);
            }
        }
    }

    @Unique
    protected void tryAddSoulSpeedBoost() {
        if (this.getLandingBlockState() != Blocks.AIR.getDefaultState() ) {
            int i = EnchantmentHelper.getSoulSpeedModifier(getEntity());
            if (i > 0 && getLowerBlock(0.5D) == BlocksRegister.SOUL_SAND_NEW) {
                IAttributeInstance iattributeinstance = this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);

                if (iattributeinstance == null) {
                    return;
                }

                iattributeinstance.applyModifier((
                        new AttributeModifier(
                                SOUL_SPEED_BOOST_ID,
                                "Soul speed boost",
                                (0.03F * (1.0F + i * 0.35F)),
                                0
                        )).setSaved(false)
                );
                if (this.rand.nextFloat() < 0.04F) {
                    ItemStack itemStack = EnchantmentHelper.getEnchantedItem(EnchantmentRegister.SOUL_SPEED, getEntity());
                    itemStack.damageItem(1, getEntity());
                }
            }
        }
    }

    @Unique
    private EntityLivingBase getEntity(){
        return (EntityLivingBase)(Object)this;
    }
}

