package cn.xiaozi0721.futureblock.register;

import cn.xiaozi0721.futureblock.Tags;
import cn.xiaozi0721.futureblock.enchantment.EnchantmentsSoulSpeed;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Tags.MOD_ID)
public class EnchantmentRegister {
    private static final List<Enchantment> ENCHANTMENTS = new ArrayList<>();
    public static final Enchantment SOUL_SPEED = new EnchantmentsSoulSpeed();

    @SubscribeEvent
    public static void onEnchantmentRegister(RegistryEvent.Register<Enchantment> event) {
        event.getRegistry().registerAll(ENCHANTMENTS.toArray(new Enchantment[0]));
    }

    public static void registerEnchantment(Enchantment enchantment) {
        ENCHANTMENTS.add(enchantment);
    }
}
