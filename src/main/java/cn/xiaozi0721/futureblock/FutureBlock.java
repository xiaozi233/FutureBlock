package cn.xiaozi0721.futureblock;

import cn.xiaozi0721.futureblock.proxy.CommonProxy;
import cn.xiaozi0721.futureblock.tab.FutureBlockTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings(value={"unused"})
@Mod.EventBusSubscriber
@Mod(modid = Tags.MOD_ID, name = Tags.MOD_NAME, version = Tags.VERSION)
public class FutureBlock {
    public static final Logger LOGGER = LogManager.getLogger(Tags.MOD_NAME);
    private static final String CLIENT_PROXY = "cn.xiaozi0721." + Tags.MOD_ID + ".proxy.ClientProxy";
    private static final String COMMON_PROXY = "cn.xiaozi0721." + Tags.MOD_ID + ".proxy.CommonProxy";
    public static CreativeTabs FUTUREBLOCK_TAB = new FutureBlockTab();
    @SidedProxy(clientSide = CLIENT_PROXY, serverSide = COMMON_PROXY)
    private static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER.info("Hello From {}!", Tags.MOD_NAME);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }
}
