package net.darkcrow.hauntedinfestation.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemTileEntitySoulStatue extends ItemBlock {
    
    public ItemTileEntitySoulStatue(Block block) {
    
        super(block);
        this.setCreativeTab(CreativeTabs.tabMisc);
        this.setUnlocalizedName("haunted:soulstatue");
    }
    
    @SideOnly(Side.CLIENT)
    public void getSubItems (Item item, CreativeTabs tab, List itemList) {
    
        for (int i = 0; i < 2; i++) {
            
            ItemStack statue = new ItemStack(item);
            statue.setTagCompound(new NBTTagCompound());
            statue.stackTagCompound.setString("blockid", "stone");
            statue.stackTagCompound.setInteger("blockmeta", 0);
            statue.stackTagCompound.setBoolean("isPossessed", (i == 0) ? false : true);
            itemList.add(statue);
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void addInformation (ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) {
    
        if (stack.hasTagCompound()) {
            
            if (stack.stackTagCompound.getBoolean("isPossessed"))
                tooltip.add(EnumChatFormatting.DARK_RED + "Possessed!");
        }
    }
}
