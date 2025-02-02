package cn.xiaozi0721.futureblock.register;

import cn.xiaozi0721.futureblock.Tags;
import cn.xiaozi0721.futureblock.particle.ParticleSmallFlame;
import cn.xiaozi0721.futureblock.particle.ParticleSoul;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.stream.IntStream;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = Tags.MOD_ID)
public class ParticleRegister {
    public static EnumParticleTypes SMALL_FLAME;
    public static EnumParticleTypes SOUL;

    public static void init(){
        SMALL_FLAME = registerParticle("SMALL_FLAME", "smallFlame", false);
        SOUL = registerParticle("SOUl", "soul", false);
    }

    @SideOnly(Side.CLIENT)
    public static void clientInit(){
        registerParticle(SMALL_FLAME, new ParticleSmallFlame.Factory());
        registerParticle(SOUL, new ParticleSoul.Factory());
    }

    @SubscribeEvent @SideOnly(Side.CLIENT)
    public static void onTextureStitchEventPre(TextureStitchEvent.Pre event) {
        ParticleSoul.setTextures(
                IntStream.range(0, 11)
                .mapToObj(i -> new ResourceLocation(Tags.MOD_ID, "particle/soul_" + i))
                .map(event.getMap()::registerSprite)
                .toArray(TextureAtlasSprite[]::new)
        );
    }

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
