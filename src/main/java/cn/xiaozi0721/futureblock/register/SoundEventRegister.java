package cn.xiaozi0721.futureblock.register;

import cn.xiaozi0721.futureblock.Tags;
import net.minecraft.block.SoundType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;

@SuppressWarnings(value={"unused"})
@Mod.EventBusSubscriber
public class SoundEventRegister {
    public static final SoundEvent AMETHYST_CLUSTER_BREAK = newSoundEvent("block.amethyst_cluster.break");
    public static final SoundEvent AMETHYST_CLUSTER_STEP = newSoundEvent("block.amethyst_cluster.step");
    public static final SoundEvent AMETHYST_CLUSTER_PLACE = newSoundEvent("block.amethyst_cluster.place");
    public static final SoundEvent AMETHYST_CLUSTER_HIT = newSoundEvent("block.amethyst_cluster.hit");
    public static final SoundEvent AMETHYST_CLUSTER_FALL = newSoundEvent("block.amethyst_cluster.fall");
    public static final SoundEvent CHAIN_BREAK = newSoundEvent("block.chain.break");
    public static final SoundEvent CHAIN_STEP = newSoundEvent("block.chain.step");
    public static final SoundEvent CHAIN_PLACE = newSoundEvent("block.chain.place");
    public static final SoundEvent CHAIN_HIT = newSoundEvent("block.chain.hit");
    public static final SoundEvent CHAIN_FALL = newSoundEvent("block.chain.fall");
    public static final SoundEvent HONEY_BREAK = newSoundEvent("block.honey.break");
    public static final SoundEvent HONEY_FALL = newSoundEvent("block.honey.fall");
    public static final SoundEvent HONEY_HIT = newSoundEvent("block.honey.hit");
    public static final SoundEvent HONEY_PLACE = newSoundEvent("block.honey.place");
    public static final SoundEvent HONEY_SLIDE = newSoundEvent("block.honey.slide");
    public static final SoundEvent HONEY_STEP = newSoundEvent("block.honey.step");
    public static final SoundEvent CANDLE_AMBIENT = newSoundEvent("block.ambient.break");
    public static final SoundEvent CANDLE_BREAK = newSoundEvent("block.candle.break");
    public static final SoundEvent CANDLE_EXTINGUISH = newSoundEvent("block.candle.extinguish");
    public static final SoundEvent CANDLE_FALL = newSoundEvent("block.candle.fall");
    public static final SoundEvent CANDLE_HIT = newSoundEvent("block.candle.hit");
    public static final SoundEvent CANDLE_PLACE = newSoundEvent("block.candle.place");
    public static final SoundEvent CANDLE_STEP = newSoundEvent("block.candle.step");

    public static final SoundType AMETHYST_CLUSTER = new SoundType(1.0F, 1.0F, AMETHYST_CLUSTER_BREAK, AMETHYST_CLUSTER_STEP, AMETHYST_CLUSTER_PLACE, AMETHYST_CLUSTER_HIT, AMETHYST_CLUSTER_FALL);
    public static final SoundType CHAIN = new SoundType(1.0F, 1.0F, CHAIN_BREAK, CHAIN_STEP, CHAIN_PLACE, CHAIN_HIT, CHAIN_FALL);
    public static final SoundType HONEY = new SoundType(1.0F, 1.0F, HONEY_BREAK, HONEY_STEP, HONEY_PLACE, HONEY_HIT, HONEY_FALL);
    public static final SoundType CANDLE = new SoundType(1.0F, 1.0F, CANDLE_BREAK, CANDLE_STEP, CANDLE_PLACE, CANDLE_HIT, CANDLE_FALL);

    private static final SoundEvent[] soundEvents = {
            AMETHYST_CLUSTER_BREAK, AMETHYST_CLUSTER_STEP, AMETHYST_CLUSTER_PLACE, AMETHYST_CLUSTER_HIT, AMETHYST_CLUSTER_FALL,
            CHAIN_BREAK, CHAIN_STEP, CHAIN_PLACE, CHAIN_HIT, CHAIN_FALL,
            HONEY_BREAK, HONEY_STEP, HONEY_PLACE, HONEY_HIT, HONEY_FALL, HONEY_SLIDE,
            CANDLE_AMBIENT, CANDLE_BREAK, CANDLE_EXTINGUISH, CANDLE_STEP, CANDLE_PLACE, CANDLE_HIT, CANDLE_FALL,
    };

    public static void onSoundEventRegistration(RegistryEvent.Register<SoundEvent> event) {
        for (SoundEvent soundEvent : soundEvents){
            event.getRegistry().register(soundEvent);
        }
    }

    private static SoundEvent newSoundEvent(String pathIn){
        return new SoundEvent(new ResourceLocation(Tags.MOD_ID, pathIn));
    }
}
