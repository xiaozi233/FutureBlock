package cn.xiaozi0721.futureblock.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Objects;

@SuppressWarnings(value={"unused"})
@Mod.EventBusSubscriber
public final class BlocksRegister {
    public static final Block AMETHYST_CLUSTER = new BlockAmethystCluster();
    public static final Block SMALL_AMETHYST_BUD = new BlockSmallAmethystBud();
    public static final Block MEDIUM_AMETHYST_BUD= new BlockMediumAmethystBud();
    public static final Block LARGE_AMETHYST_BUD = new BlockLargeAmethystBud();
    public static final Block BE_STRUCTURE_VOID = new BlockBEStructureVoid();
    public static final Block CHAIN = new BlockChain();
    public static final Block HONEY = new BlockHoney();

    public BlocksRegister() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    private static final Block[] blocks = {
            AMETHYST_CLUSTER,
            SMALL_AMETHYST_BUD,
            MEDIUM_AMETHYST_BUD,
            LARGE_AMETHYST_BUD,
            BE_STRUCTURE_VOID,
            CHAIN,
            HONEY
    };

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        for (Block block : blocks) {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(Objects.requireNonNull(block.getRegistryName()), "inventory"));
            event.getRegistry().register(block);
        }
    }

    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
        for (Block block : blocks) {
            Item itemBlock = new ItemBlock(block).setRegistryName(Objects.requireNonNull(block.getRegistryName()));
            ModelLoader.setCustomModelResourceLocation(itemBlock, 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
            event.getRegistry().register(itemBlock);
        }
    }

}
