package cn.xiaozi0721.futureblock.tab;

import cn.xiaozi0721.futureblock.block.BlocksRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

@SuppressWarnings(value={"NullableProblems"})
public class FutureBlockTab extends CreativeTabs {
    public FutureBlockTab(){
        super("futureblock.tab");
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(BlocksRegister.AMETHYST_CLUSTER);
    }
}
