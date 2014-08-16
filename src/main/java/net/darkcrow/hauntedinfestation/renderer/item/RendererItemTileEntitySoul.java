package net.darkcrow.hauntedinfestation.renderer.item;

import net.darkcrow.hauntedinfestation.tileentity.TileEntitySoulStatue;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.IItemRenderer;

public class RendererItemTileEntitySoul implements IItemRenderer {
    
    public boolean handleRenderType (ItemStack item, ItemRenderType type) {
    
        return true;
    }
    
    public boolean shouldUseRenderHelper (ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
    
        return true;
    }
    
    public void renderItem (ItemRenderType type, ItemStack item, Object... data) {
    
        if (item.hasTagCompound()) {
            
            NBTTagCompound tag = item.stackTagCompound;
            String username = item.getDisplayName();
            String blockname = tag.getString("blockid");
            int blockMeta = tag.getInteger("blockmeta");
            boolean isPossessed = tag.getBoolean("isPossessed");
            TileEntityRendererDispatcher.instance.renderTileEntityAt(new TileEntitySoulStatue(username, blockname, blockMeta, 3, isPossessed), 0d, 0d, 0d, 0f);
        }
    }
}