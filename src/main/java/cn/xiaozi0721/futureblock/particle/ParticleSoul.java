package cn.xiaozi0721.futureblock.particle;

import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleSoul extends Particle {
    private static TextureAtlasSprite[] textures;

    public ParticleSoul(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
        this.particleScale = (this.rand.nextFloat() * 0.5F + 0.5F) * 2.0F;
        this.motionX = this.motionX * 0.01D + xSpeedIn;
        this.motionY = this.motionY * 0.01D + ySpeedIn;
        this.motionZ = this.motionZ * 0.01D + zSpeedIn;
        this.posX += (this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F;
        this.posY += (this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F;
        this.posZ += (this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F;
        this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D)) + 4;
        this.multipleParticleScaleBy(1.5F);
        this.setTextureForAge(textures);
    }

    @Override
    public int getFXLayer() {
        return 1;
    }

    @Override
    public void setParticleTextureIndex(int particleTextureIndex) {
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        this.setTextureForAge(textures);
    }

    public static void setTextures(TextureAtlasSprite[] textures) {
        if (ParticleSoul.textures != null) {
            throw new IllegalStateException("The textures array has already been initialized and cannot be set again.");
        }
        ParticleSoul.textures = textures;
    }

    private void setTextureForAge(TextureAtlasSprite[] textures){
        if (!this.isExpired){
            this.setParticleTexture(textures[particleAge * (textures.length - 1) / particleMaxAge]);
        }
    }

    @SuppressWarnings("NullableProblems")
    @SideOnly(Side.CLIENT)
    public static class Factory implements IParticleFactory {
        public Particle createParticle(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
            return new ParticleSoul(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
        }
    }
}
