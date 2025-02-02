package cn.xiaozi0721.futureblock.enchantment;

import cn.xiaozi0721.futureblock.Tags;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;

import static cn.xiaozi0721.futureblock.register.EnchantmentRegister.registerEnchantment;

public class EnchantmentsSoulSpeed extends Enchantment {
    public EnchantmentsSoulSpeed() {
        super(Rarity.VERY_RARE, EnumEnchantmentType.ARMOR_FEET, new EntityEquipmentSlot[] {EntityEquipmentSlot.FEET});
        String name = "soul_speed";
        this.setName(name);
        this.setRegistryName(new ResourceLocation(Tags.MOD_ID + name));
        registerEnchantment(this);
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
