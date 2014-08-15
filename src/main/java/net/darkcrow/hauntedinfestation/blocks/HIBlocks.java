package net.darkcrow.hauntedinfestation.blocks;

import net.darkcrow.hauntedinfestation.tileentity.TileEntitySoulStatue;
import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;

public class HIBlocks {
    
    public static final Block soulStatue = new BlockSoulStatue();
    
    public HIBlocks() {
    
        GameRegistry.registerBlock(soulStatue, "soulStatue");
        GameRegistry.registerTileEntity(TileEntitySoulStatue.class, "soulstatue");
    }
}