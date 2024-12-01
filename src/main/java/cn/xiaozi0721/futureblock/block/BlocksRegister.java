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
    public static final Block Amethyst_Cluster = new BlockAmethystCluster();
    public static final Block Small_Amethyst_Bud = new BlockSmallAmethystBud();
    public static final Block Medium_Amethyst_Bud = new BlockMediumAmethystBud();
    public static final Block Large_Amethyst_Bud = new BlockLargeAmethystBud();
    public static final Block BE_STRUCTURE_VOID = new BlockBEStructureVoid();
    public static final Block Chain = new BlockChain();

    public BlocksRegister() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    private static final Block[] blocks = {
            Amethyst_Cluster,
            Small_Amethyst_Bud,
            Medium_Amethyst_Bud,
            Large_Amethyst_Bud,
            BE_STRUCTURE_VOID,
           Chain
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
