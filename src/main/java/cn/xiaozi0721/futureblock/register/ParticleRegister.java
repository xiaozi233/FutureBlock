package cn.xiaozi0721.futureblock.register;

import cn.xiaozi0721.futureblock.particle.ParticleSmallFlame;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraftforge.common.util.EnumHelper;

public class ParticleRegister {
    public static EnumParticleTypes SMALL_FLAME;

    public static void registerParticles(){
        SMALL_FLAME = registerParticle(
                "SMALL_FLAME",
                "small_Flame",
                true,
                new ParticleSmallFlame.SmallFactory()
        );
    }

    private static EnumParticleTypes registerParticle(String enumName, String particleName, boolean alwaysShow, IParticleFactory factory) {
        int id = EnumParticleTypes.values().length;
        EnumParticleTypes particle = EnumHelper.addEnum(EnumParticleTypes.class, enumName, new Class[]{String.class, int.class, boolean.class}, particleName, id, alwaysShow);

        EnumParticleTypes.PARTICLES.put(id, particle);
        EnumParticleTypes.BY_NAME.put(particleName, particle);

        Minecraft.getMinecraft().effectRenderer.registerParticle(id, factory);
        return particle;
    }
}
