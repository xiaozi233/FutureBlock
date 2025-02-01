package cn.xiaozi0721.futureblock.mixin.client;

import cn.xiaozi0721.futureblock.mixin.MixinEntityPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntityPlayerSP.class)
public abstract class MixinEntityPlayerSP extends MixinEntityPlayer {
    @Override
    public boolean shouldDisplaySoulSpeedEffects() {
        return !this.capabilities.isFlying && super.shouldDisplaySoulSpeedEffects();
    }
}
