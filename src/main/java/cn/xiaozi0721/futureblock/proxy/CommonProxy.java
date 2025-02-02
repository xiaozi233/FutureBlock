package cn.xiaozi0721.futureblock.proxy;

import cn.xiaozi0721.futureblock.Tags;
import cn.xiaozi0721.futureblock.register.ParticleRegister;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Tags.MOD_ID)
public class CommonProxy {
    public void init() {
        ParticleRegister.init();
    }
}
