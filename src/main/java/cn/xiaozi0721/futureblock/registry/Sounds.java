package cn.xiaozi0721.futureblock.registry;

import cn.xiaozi0721.futureblock.Tags;
import net.minecraft.block.SoundType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;

import java.util.ArrayList;
import java.util.List;

public class Sounds {
    private static final List<SoundEvent> SOUND_EVENTS = new ArrayList<>();

    public static final SoundEvent AMETHYST_CLUSTER_BREAK = registerSoundEvent("block.amethyst_cluster.break");
    public static final SoundEvent AMETHYST_CLUSTER_STEP = registerSoundEvent("block.amethyst_cluster.step");
    public static final SoundEvent AMETHYST_CLUSTER_PLACE = registerSoundEvent("block.amethyst_cluster.place");
    public static final SoundEvent AMETHYST_CLUSTER_HIT = registerSoundEvent("block.amethyst_cluster.hit");
    public static final SoundEvent AMETHYST_CLUSTER_FALL = registerSoundEvent("block.amethyst_cluster.fall");
    public static final SoundEvent CHAIN_BREAK = registerSoundEvent("block.chain.break");
    public static final SoundEvent CHAIN_STEP = registerSoundEvent("block.chain.step");
    public static final SoundEvent CHAIN_PLACE = registerSoundEvent("block.chain.place");
    public static final SoundEvent CHAIN_HIT = registerSoundEvent("block.chain.hit");
    public static final SoundEvent CHAIN_FALL = registerSoundEvent("block.chain.fall");
    public static final SoundEvent HONEY_BREAK = registerSoundEvent("block.honey.break");
    public static final SoundEvent HONEY_FALL = registerSoundEvent("block.honey.fall");
    public static final SoundEvent HONEY_HIT = registerSoundEvent("block.honey.hit");
    public static final SoundEvent HONEY_PLACE = registerSoundEvent("block.honey.place");
    public static final SoundEvent HONEY_SLIDE = registerSoundEvent("block.honey.slide");
    public static final SoundEvent HONEY_STEP = registerSoundEvent("block.honey.step");
    public static final SoundEvent CANDLE_AMBIENT = registerSoundEvent("block.candle.ambient");
    public static final SoundEvent CANDLE_BREAK = registerSoundEvent("block.candle.break");
    public static final SoundEvent CANDLE_EXTINGUISH = registerSoundEvent("block.candle.extinguish");
    public static final SoundEvent CANDLE_FALL = registerSoundEvent("block.candle.fall");
    public static final SoundEvent CANDLE_HIT = registerSoundEvent("block.candle.hit");
    public static final SoundEvent CANDLE_PLACE = registerSoundEvent("block.candle.place");
    public static final SoundEvent CANDLE_STEP = registerSoundEvent("block.candle.step");
    public static final SoundEvent CAKE_ADD_CANDLE = registerSoundEvent("block.cake.add_candle");

    public static final SoundEvent PARTICLE_SOUL_ESCAPE = registerSoundEvent("particle.soul_escape");

    public static final SoundType AMETHYST_CLUSTER = new SoundType(1.0F, 1.0F, AMETHYST_CLUSTER_BREAK, AMETHYST_CLUSTER_STEP, AMETHYST_CLUSTER_PLACE, AMETHYST_CLUSTER_HIT, AMETHYST_CLUSTER_FALL);
    public static final SoundType CHAIN = new SoundType(1.0F, 1.0F, CHAIN_BREAK, CHAIN_STEP, CHAIN_PLACE, CHAIN_HIT, CHAIN_FALL);
    public static final SoundType HONEY = new SoundType(1.0F, 1.0F, HONEY_BREAK, HONEY_STEP, HONEY_PLACE, HONEY_HIT, HONEY_FALL);
    public static final SoundType CANDLE = new SoundType(1.0F, 1.0F, CANDLE_BREAK, CANDLE_STEP, CANDLE_PLACE, CANDLE_HIT, CANDLE_FALL);

    public static void registerSoundEvent(RegistryEvent.Register<SoundEvent> event) {
        event.getRegistry().registerAll(SOUND_EVENTS.toArray(new SoundEvent[0]));
    }

    private static SoundEvent registerSoundEvent(String pathIn){
        ResourceLocation soundLocation = new ResourceLocation(Tags.MOD_ID, pathIn);
        SoundEvent soundEvent = new SoundEvent(soundLocation).setRegistryName(soundLocation);
        SOUND_EVENTS.add(soundEvent);
        return soundEvent;
    }

}
