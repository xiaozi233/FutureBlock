package cn.xiaozi0721.futureblock.sound;

import cn.xiaozi0721.futureblock.Tags;
import net.minecraft.block.SoundType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;

@SuppressWarnings(value={"unused"})
@Mod.EventBusSubscriber
public class SoundEventRegister {
    public static final SoundEvent AMETHYST_CLUSTER_BREAK = new SoundEvent(new ResourceLocation(Tags.MOD_ID, "amethyst_cluster.break"));
    public static final SoundEvent AMETHYST_CLUSTER_STEP = new SoundEvent(new ResourceLocation(Tags.MOD_ID, "amethyst_cluster.step"));
    public static final SoundEvent AMETHYST_CLUSTER_PLACE = new SoundEvent(new ResourceLocation(Tags.MOD_ID, "amethyst_cluster.place"));
    public static final SoundEvent AMETHYST_CLUSTER_HIT = new SoundEvent(new ResourceLocation(Tags.MOD_ID, "amethyst_cluster.hit"));
    public static final SoundEvent AMETHYST_CLUSTER_FALL = new SoundEvent(new ResourceLocation(Tags.MOD_ID, "amethyst_cluster.fall"));
    public static final SoundEvent CHAIN_BREAK = new SoundEvent(new ResourceLocation(Tags.MOD_ID, "chain.break"));
    public static final SoundEvent CHAIN_STEP = new SoundEvent(new ResourceLocation(Tags.MOD_ID, "chain.step"));
    public static final SoundEvent CHAIN_PLACE = new SoundEvent(new ResourceLocation(Tags.MOD_ID, "chain.place"));
    public static final SoundEvent CHAIN_HIT = new SoundEvent(new ResourceLocation(Tags.MOD_ID, "chain.hit"));
    public static final SoundEvent CHAIN_FALL = new SoundEvent(new ResourceLocation(Tags.MOD_ID, "chain.fall"));
    public static final SoundEvent HONEY_BREAK = new SoundEvent(new ResourceLocation(Tags.MOD_ID, "honey.break"));
    public static final SoundEvent HONEY_FALL = new SoundEvent(new ResourceLocation(Tags.MOD_ID, "honey.fall"));
    public static final SoundEvent HONEY_HIT = new SoundEvent(new ResourceLocation(Tags.MOD_ID, "honey.hit"));
    public static final SoundEvent HONEY_PLACE = new SoundEvent(new ResourceLocation(Tags.MOD_ID, "honey.place"));
    public static final SoundEvent HONEY_SLIDE = new SoundEvent(new ResourceLocation(Tags.MOD_ID, "honey.slide"));
    public static final SoundEvent HONEY_STEP = new SoundEvent(new ResourceLocation(Tags.MOD_ID, "honey.step"));

    public static final SoundType AMETHYST_CLUSTER = new SoundType(1.0F, 1.0F, AMETHYST_CLUSTER_BREAK, AMETHYST_CLUSTER_STEP, AMETHYST_CLUSTER_PLACE, AMETHYST_CLUSTER_HIT, AMETHYST_CLUSTER_FALL);
    public static final SoundType CHAIN = new SoundType(1.0F, 1.0F, CHAIN_BREAK, CHAIN_STEP, CHAIN_PLACE, CHAIN_HIT, CHAIN_FALL);
    public static final SoundType HONEY = new SoundType(1.0F, 1.0F, HONEY_BREAK, HONEY_STEP, HONEY_PLACE, HONEY_HIT, HONEY_FALL);

    private static final SoundEvent[] soundEvents = {
            AMETHYST_CLUSTER_BREAK, AMETHYST_CLUSTER_STEP, AMETHYST_CLUSTER_PLACE, AMETHYST_CLUSTER_HIT, AMETHYST_CLUSTER_FALL,
            CHAIN_BREAK, CHAIN_STEP, CHAIN_PLACE, CHAIN_HIT, CHAIN_FALL,
            HONEY_BREAK, HONEY_STEP, HONEY_PLACE, HONEY_HIT, HONEY_FALL, HONEY_SLIDE
    };

    public static void onSoundEventRegistration(RegistryEvent.Register<SoundEvent> event) {
        for (SoundEvent soundEvent : soundEvents){
            event.getRegistry().register(soundEvent);
        }
    }
}
