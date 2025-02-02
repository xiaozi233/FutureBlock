package cn.xiaozi0721.futureblock.enchantment;

import cn.xiaozi0721.futureblock.Tags;
import cn.xiaozi0721.futureblock.registry.Enchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;

public class EnchantmentsSoulSpeed extends Enchantment {
    public EnchantmentsSoulSpeed() {
        super(Rarity.VERY_RARE, EnumEnchantmentType.ARMOR_FEET, new EntityEquipmentSlot[] {EntityEquipmentSlot.FEET});
        String name = "soul_speed";
        this.setName(name);
        this.setRegistryName(Tags.MOD_ID, name);
        Enchantments.registerEnchantment(this);
    }
    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 10 * enchantmentLevel;
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return this.getMinEnchantability(enchantmentLevel) + 15;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isTreasureEnchantment()
    {
        return true;
    }
}
