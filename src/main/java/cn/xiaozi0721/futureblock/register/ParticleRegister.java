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
        SMALL_FLAME = registerParticle("SMALL_FLAME", "smallFlame", false, new ParticleSmallFlame.SmallFactory());
        SOUL = registerParticle("SOUl", "soul", false, new ParticleSoul.Factory());
    }

    private static EnumParticleTypes registerParticle(String enumName, String particleName, boolean alwaysShow, IParticleFactory factory) {
        int id = EnumParticleTypes.values().length;
        EnumParticleTypes particle = EnumHelper.addEnum(EnumParticleTypes.class, enumName, new Class[]{String.class, int.class, boolean.class}, particleName, id, alwaysShow);

        EnumParticleTypes.PARTICLES.put(id, particle);
        EnumParticleTypes.BY_NAME.put(particleName, particle);

        Minecraft.getMinecraft().effectRenderer.registerParticle(id, factory);
        return particle;
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onTextureStitchEventPre(TextureStitchEvent.Pre event) {
        ParticleSoul.textures = IntStream.range(0, 11)
                .mapToObj(i -> new ResourceLocation(Tags.MOD_ID, "particle/soul_" + i))
                .map(event.getMap()::registerSprite)
                .toArray(TextureAtlasSprite[]::new);
    }
}
