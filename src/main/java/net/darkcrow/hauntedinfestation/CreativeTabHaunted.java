package net.darkcrow.hauntedinfestation;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTabHaunted extends CreativeTabs {
    
    CreativeTabHaunted() {
    
        super(CreativeTabs.getNextID(), "haunted");
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Item getTabIconItem () {
    
        return Item.getItemFromBlock(Blocks.pumpkin);
    }
}