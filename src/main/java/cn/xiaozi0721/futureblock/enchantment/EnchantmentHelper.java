package cn.xiaozi0721.futureblock.enchantment;

import cn.xiaozi0721.futureblock.registry.Enchantments;
import net.minecraft.entity.EntityLivingBase;

public class EnchantmentHelper extends net.minecraft.enchantment.EnchantmentHelper {
    public static int getSoulSpeedModifier(EntityLivingBase entity){
        return net.minecraft.enchantment.EnchantmentHelper.getMaxEnchantmentLevel(Enchantments.SOUL_SPEED, entity);
    }

    public static boolean hasSoulSpeed(EntityLivingBase entity){
        return getSoulSpeedModifier(entity) > 0;
    }
}
