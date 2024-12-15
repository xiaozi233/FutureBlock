package cn.xiaozi0721.futureblock.register;

import cn.xiaozi0721.futureblock.Tags;
import cn.xiaozi0721.futureblock.block.*;
import cn.xiaozi0721.futureblock.item.ItemCandle;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = Tags.MOD_ID)
public final class BlocksRegister {
    public static final Block AMETHYST_CLUSTER = new BlockAmethystCluster();
    public static final Block SMALL_AMETHYST_BUD = new BlockSmallAmethystBud();
    public static final Block MEDIUM_AMETHYST_BUD= new BlockMediumAmethystBud();
    public static final Block LARGE_AMETHYST_BUD = new BlockLargeAmethystBud();
    public static final Block BE_STRUCTURE_VOID = new BlockBEStructureVoid();
    public static final Block CHAIN = new BlockChain();
    public static final Block HONEY = new BlockHoney();
    public static final Block HONEY_NETEASE = new BlockHoneyNetease();
    public static final Block HONEY_BE = new BlockHoneyBE();
    public static final Block SNIFFER_EGG = new BlockSnifferEgg();

    public static final Block CANDLE = registerCandle(-1);
    public static final Block WHITE_CANDLE = registerCandle(0);
    public static final Block ORANGE_CANDLE = registerCandle(1);
    public static final Block MAGENTA_CANDLE = registerCandle(2);
    public static final Block LIGHT_BLUE_CANDLE = registerCandle(3);
    public static final Block YELLOW_CANDLE = registerCandle(4);
    public static final Block LIME_CANDLE = registerCandle(5);
    public static final Block PINK_CANDLE = registerCandle(6);
    public static final Block GRAY_CANDLE = registerCandle(7);
    public static final Block LIGHT_GRAY_CANDLE = registerCandle(8);
    public static final Block CYAN_CANDLE = registerCandle(9);
    public static final Block PURPLE_CANDLE = registerCandle(10);
    public static final Block BLUE_CANDLE = registerCandle(11);
    public static final Block BROWN_CANDLE = registerCandle(12);
    public static final Block GREEN_CANDLE = registerCandle(13);
    public static final Block RED_CANDLE = registerCandle(14);
    public static final Block BLACK_CANDLE = registerCandle(15);

    public static final Block[] blocks = {
            SMALL_AMETHYST_BUD, MEDIUM_AMETHYST_BUD, LARGE_AMETHYST_BUD, AMETHYST_CLUSTER,
            BE_STRUCTURE_VOID,
            CHAIN,
            HONEY, HONEY_NETEASE, HONEY_BE,
            SNIFFER_EGG
    };

    public static final Block[] candles = {
            CANDLE, WHITE_CANDLE, ORANGE_CANDLE, MAGENTA_CANDLE, LIGHT_BLUE_CANDLE, YELLOW_CANDLE,
            LIME_CANDLE, PINK_CANDLE, GRAY_CANDLE, LIGHT_GRAY_CANDLE, CYAN_CANDLE, PURPLE_CANDLE,
            BLUE_CANDLE, BROWN_CANDLE, GREEN_CANDLE, RED_CANDLE, BLACK_CANDLE
    };
    
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        for (Block block : blocks) {
            event.getRegistry().register(block);
        }
        for (Block block : candles) {
            event.getRegistry().register(block);
        }
    }

    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
        Item itemBlock;
        for (Block block : blocks) {
            itemBlock = new ItemBlock(block).setRegistryName(Objects.requireNonNull(block.getRegistryName()));
                event.getRegistry().register(itemBlock);
        }
        for (Block candle : candles){
            itemBlock = new ItemCandle(candle).setRegistryName(Objects.requireNonNull(candle.getRegistryName()));
            event.getRegistry().register(itemBlock);
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event)
    {
       for (Block block : blocks){
           ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(Objects.requireNonNull(block.getRegistryName()), "inventory"));
       }
       for (Block candle : candles){
           ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(candle), 0, new ModelResourceLocation(Objects.requireNonNull(candle.getRegistryName()), "inventory"));
       }
    }

    private static Block registerCandle(int color){
        String name = getColorName("candle", color);
        MapColor mapColor = getMapColor(color);
        return new BlockCandle(Material.CIRCUITS, mapColor).setRegistryName(Tags.MOD_ID, name).setTranslationKey(Tags.MOD_ID + ".block." + name);
    }

    private static String getColorName(String name, int color){
        switch (color){
            case 0: return "white_" + name;
            case 1: return "orange_" + name;
            case 2: return "magenta_" + name;
            case 3: return "light_blue_" + name;
            case 4: return "yellow_" + name;
            case 5: return "lime_" + name;
            case 6: return "pink_" + name;
            case 7: return "gray_" + name;
            case 8: return "light_gray_" + name;
            case 9: return "cyan_" + name;
            case 10: return "purple_" + name;
            case 11: return "blue_" + name;
            case 12: return "brown_" + name;
            case 13: return "green_" + name;
            case 14: return "red_" + name;
            case 15: return "black_" + name;
            default: return name;
        }
    }

    private static MapColor getMapColor(int color){
        switch (color){
            case 0: return MapColor.SNOW;
            case 1: return MapColor.ADOBE;
            case 2: return MapColor.MAGENTA;
            case 3: return MapColor.LIGHT_BLUE;
            case 4: return MapColor.YELLOW;
            case 5: return MapColor.LIME;
            case 6: return MapColor.PINK;
            case 7: return MapColor.GRAY;
            case 8: return MapColor.SILVER;
            case 9: return MapColor.CYAN;
            case 10: return MapColor.PURPLE;
            case 11: return MapColor.BLUE;
            case 12: return MapColor.BROWN;
            case 13: return MapColor.GREEN;
            case 14: return MapColor.RED;
            case 15: return MapColor.BLACK;
            default: return MapColor.WHITE_STAINED_HARDENED_CLAY;
        }
    }

}
