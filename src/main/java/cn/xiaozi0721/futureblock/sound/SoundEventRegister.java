package cn.xiaozi0721.futureblock.sound;

import cn.xiaozi0721.futureblock.Tags;
import net.minecraft.block.SoundType;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@SuppressWarnings(value={"unused"})
@Mod.EventBusSubscriber
public class SoundEventRegister {
    public static final SoundEvent AMETHYST_CLUSTER_BREAK = new SoundEvent(new ResourceLocation(Tags.MOD_ID, "amethyst_cluster.break"));
    public static final SoundEvent AMETHYST_CLUSTER_STEP = new SoundEvent(new ResourceLocation(Tags.MOD_ID, "amethyst_cluster.step"));
    public static final SoundEvent AMETHYST_CLUSTER_PLACE = new SoundEvent(new ResourceLocation(Tags.MOD_ID, "amethyst_cluster.place"));
    public static final SoundEvent AMETHYST_CLUSTER_HIT = new SoundEvent(new ResourceLocation(Tags.MOD_ID, "amethyst_cluster.hit"));
    public static final SoundEvent AMETHYST_CLUSTER_FALL = new SoundEvent(new ResourceLocation(Tags.MOD_ID, "amethyst_cluster.fall"));
    public static final SoundType AMETHYST_CLUSTER = new SoundType(1.0F, 1.0F, AMETHYST_CLUSTER_BREAK, AMETHYST_CLUSTER_STEP, AMETHYST_CLUSTER_PLACE, AMETHYST_CLUSTER_HIT, AMETHYST_CLUSTER_FALL);

    public static void onSoundEventRegistration(RegistryEvent.Register<SoundEvent> event) {
        event.getRegistry().register(AMETHYST_CLUSTER_BREAK.setRegistryName(new ResourceLocation(Tags.MOD_ID, "amethyst_cluster.break")));
        event.getRegistry().register(AMETHYST_CLUSTER_FALL.setRegistryName(new ResourceLocation(Tags.MOD_ID, "amethyst_cluster.fall")));
        event.getRegistry().register(AMETHYST_CLUSTER_HIT.setRegistryName(new ResourceLocation(Tags.MOD_ID, "amethyst_cluster.hit")));
        event.getRegistry().register(AMETHYST_CLUSTER_PLACE.setRegistryName(new ResourceLocation(Tags.MOD_ID, "amethyst_cluster.place")));
        event.getRegistry().register(AMETHYST_CLUSTER_STEP.setRegistryName(new ResourceLocation(Tags.MOD_ID, "amethyst_cluster.step")));
    }
}
