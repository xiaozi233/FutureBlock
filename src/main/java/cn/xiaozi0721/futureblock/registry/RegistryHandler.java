package cn.xiaozi0721.futureblock.registry;

import cn.xiaozi0721.futureblock.Tags;
import cn.xiaozi0721.futureblock.particle.ParticleSoul;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.stream.IntStream;

@Mod.EventBusSubscriber(modid = Tags.MOD_ID)
public class RegistryHandler {
    @SubscribeEvent @SideOnly(Side.CLIENT)
    public static void onTextureStitchEventPre(TextureStitchEvent.Pre event) {
        ParticleSoul.setTextures(
                IntStream.range(0, 11)
                        .mapToObj(i -> new ResourceLocation(Tags.MOD_ID, "particle/soul_" + i))
                        .map(event.getMap()::registerSprite)
                        .toArray(TextureAtlasSprite[]::new)
        );
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        Blocks.registerBlocks(event);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        Blocks.registerItemBlocks(event);
    }

    @SubscribeEvent @SideOnly(Side.CLIENT)
    public static void onModelRegister(ModelRegistryEvent event) {
        Blocks.onModelRegister(event);
    }

    @SubscribeEvent
    public static void registerEnchantment(RegistryEvent.Register<Enchantment> event) {
        Enchantments.registerEnchantment(event);
    }

    @SubscribeEvent
    public static void registerSoundEvent(RegistryEvent.Register<SoundEvent> event) {
        Sounds.registerSoundEvent(event);
    }
}
