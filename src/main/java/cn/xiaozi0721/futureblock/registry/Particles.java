package cn.xiaozi0721.futureblock.registry;

import cn.xiaozi0721.futureblock.particle.ParticleSmallFlame;
import cn.xiaozi0721.futureblock.particle.ParticleSoul;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class Particles {
    public static final EnumParticleTypes SMALL_FLAME = registerParticle("SMALL_FLAME", "smallFlame", false);
    public static final EnumParticleTypes SOUL = registerParticle("SOUl", "soul", false);

    @SideOnly(Side.CLIENT)
    public static void registerParticleOnClient(){
        registerParticle(SMALL_FLAME, new ParticleSmallFlame.Factory());
        registerParticle(SOUL, new ParticleSoul.Factory());
    }

    @SuppressWarnings("SameParameterValue")
    private static EnumParticleTypes registerParticle(String enumName, String particleName, boolean alwaysShow) {
        int id = EnumParticleTypes.values().length;
        EnumParticleTypes particle = EnumHelper.addEnum(EnumParticleTypes.class, enumName, new Class[]{String.class, int.class, boolean.class}, particleName, id, alwaysShow);

        EnumParticleTypes.PARTICLES.put(id, particle);
        EnumParticleTypes.BY_NAME.put(particleName, particle);
        return particle;
    }

    @SideOnly(Side.CLIENT)
    private static void registerParticle(EnumParticleTypes particleTypes, IParticleFactory particleFactory){
        Minecraft.getMinecraft().effectRenderer.registerParticle(particleTypes.getParticleID(), particleFactory);
    }
}
