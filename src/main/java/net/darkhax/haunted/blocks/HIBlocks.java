package net.darkhax.haunted.blocks;

import net.darkhax.haunted.items.ItemTileEntitySoulStatue;
import net.darkhax.haunted.tileentity.TileEntitySoulStatue;
import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;

public class HIBlocks {
    
    public static final Block soulStatue = new BlockSoulStatue();
    
    public HIBlocks() {
    
        GameRegistry.registerBlock(soulStatue, ItemTileEntitySoulStatue.class, "soulStatue");
        GameRegistry.registerTileEntity(TileEntitySoulStatue.class, "soulstatue");
    }
}