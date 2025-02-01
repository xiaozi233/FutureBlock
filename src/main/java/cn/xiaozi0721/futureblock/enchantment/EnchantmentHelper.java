package cn.xiaozi0721.futureblock.enchantment;

import cn.xiaozi0721.futureblock.register.EnchantmentRegister;
import net.minecraft.entity.EntityLivingBase;

public class EnchantmentHelper extends net.minecraft.enchantment.EnchantmentHelper {
    public static int getSoulSpeedModifier(EntityLivingBase entity){
        return net.minecraft.enchantment.EnchantmentHelper.getMaxEnchantmentLevel(EnchantmentRegister.SOUL_SPEED, entity);
    }

    public static boolean hasSoulSpeed(EntityLivingBase entity){
        return getSoulSpeedModifier(entity) > 0;
    }
}
