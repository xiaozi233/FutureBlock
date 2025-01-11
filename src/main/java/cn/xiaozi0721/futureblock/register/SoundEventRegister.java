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
    public static final SoundEvent AMETHYST_CLUSTER_BREAK = new SoundEvent(new ResourceLocation(Tags.MOD_ID, "block.amethyst_cluster.break"));
    public static final SoundEvent AMETHYST_CLUSTER_STEP = new SoundEvent(new ResourceLocation(Tags.MOD_ID, "block.amethyst_cluster.step"));
    public static final SoundEvent AMETHYST_CLUSTER_PLACE = new SoundEvent(new ResourceLocation(Tags.MOD_ID, "block.amethyst_cluster.place"));
    public static final SoundEvent AMETHYST_CLUSTER_HIT = new SoundEvent(new ResourceLocation(Tags.MOD_ID, "block.amethyst_cluster.hit"));
    public static final SoundEvent AMETHYST_CLUSTER_FALL = new SoundEvent(new ResourceLocation(Tags.MOD_ID, "block.amethyst_cluster.fall"));
    public static final SoundEvent CHAIN_BREAK = new SoundEvent(new ResourceLocation(Tags.MOD_ID, "block.chain.break"));
    public static final SoundEvent CHAIN_STEP = new SoundEvent(new ResourceLocation(Tags.MOD_ID, "block.chain.step"));
    public static final SoundEvent CHAIN_PLACE = new SoundEvent(new ResourceLocation(Tags.MOD_ID, "block.chain.place"));
    public static final SoundEvent CHAIN_HIT = new SoundEvent(new ResourceLocation(Tags.MOD_ID, "block.chain.hit"));
    public static final SoundEvent CHAIN_FALL = new SoundEvent(new ResourceLocation(Tags.MOD_ID, "block.chain.fall"));
    public static final SoundEvent HONEY_BREAK = new SoundEvent(new ResourceLocation(Tags.MOD_ID, "block.honey.break"));
    public static final SoundEvent HONEY_FALL = new SoundEvent(new ResourceLocation(Tags.MOD_ID, "block.honey.fall"));
    public static final SoundEvent HONEY_HIT = new SoundEvent(new ResourceLocation(Tags.MOD_ID, "block.honey.hit"));
    public static final SoundEvent HONEY_PLACE = new SoundEvent(new ResourceLocation(Tags.MOD_ID, "block.honey.place"));
    public static final SoundEvent HONEY_SLIDE = new SoundEvent(new ResourceLocation(Tags.MOD_ID, "block.honey.slide"));
    public static final SoundEvent HONEY_STEP = new SoundEvent(new ResourceLocation(Tags.MOD_ID, "block.honey.step"));
    public static final SoundEvent CANDLE_AMBIENT = new SoundEvent(new ResourceLocation(Tags.MOD_ID, "block.ambient.break"));
    public static final SoundEvent CANDLE_BREAK = new SoundEvent(new ResourceLocation(Tags.MOD_ID, "block.candle.break"));
    public static final SoundEvent CANDLE_EXTINGUISH = new SoundEvent(new ResourceLocation(Tags.MOD_ID, "block.candle.extinguish"));
    public static final SoundEvent CANDLE_FALL = new SoundEvent(new ResourceLocation(Tags.MOD_ID, "block.candle.fall"));
    public static final SoundEvent CANDLE_HIT = new SoundEvent(new ResourceLocation(Tags.MOD_ID, "block.candle.hit"));
    public static final SoundEvent CANDLE_PLACE = new SoundEvent(new ResourceLocation(Tags.MOD_ID, "block.candle.place"));
    public static final SoundEvent CANDLE_STEP = new SoundEvent(new ResourceLocation(Tags.MOD_ID, "block.candle.step"));

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
}
