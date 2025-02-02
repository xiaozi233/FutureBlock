package cn.xiaozi0721.futureblock.proxy;

import cn.xiaozi0721.futureblock.Tags;
import cn.xiaozi0721.futureblock.register.ParticleRegister;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(value = Side.CLIENT, modid = Tags.MOD_ID)
public class ClientProxy extends CommonProxy{
    @Override
    public void init() {
        super.init();
        ParticleRegister.clientInit();
    }
}
