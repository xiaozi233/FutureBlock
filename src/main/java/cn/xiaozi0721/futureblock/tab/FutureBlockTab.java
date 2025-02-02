package cn.xiaozi0721.futureblock.tab;

import cn.xiaozi0721.futureblock.registry.Blocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

@SuppressWarnings(value={"NullableProblems"})
public class FutureBlockTab extends CreativeTabs {
    public FutureBlockTab(){
        super("futureblock.tab");
    }
    public static CreativeTabs FUTUREBLOCK_TAB = new FutureBlockTab();
    @Override
    public ItemStack createIcon() {
        return new ItemStack(Blocks.AMETHYST_CLUSTER);
    }
}
