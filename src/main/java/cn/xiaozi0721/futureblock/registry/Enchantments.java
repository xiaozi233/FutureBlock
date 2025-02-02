package cn.xiaozi0721.futureblock.registry;

import cn.xiaozi0721.futureblock.enchantment.EnchantmentsSoulSpeed;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.event.RegistryEvent;

import java.util.ArrayList;
import java.util.List;

public class Enchantments {
    private static final List<Enchantment> ENCHANTMENTS = new ArrayList<>();
    public static final Enchantment SOUL_SPEED = new EnchantmentsSoulSpeed();

    public static void registerEnchantment(RegistryEvent.Register<Enchantment> event) {
        event.getRegistry().registerAll(ENCHANTMENTS.toArray(new Enchantment[0]));
    }

    public static void registerEnchantment(Enchantment enchantment) {
        ENCHANTMENTS.add(enchantment);
    }
}
