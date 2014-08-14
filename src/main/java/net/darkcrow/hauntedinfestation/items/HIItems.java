package net.darkcrow.hauntedinfestation.items;

import net.darkcrow.hauntedinfestation.util.Reference;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class HIItems {
    
    public static final String MODID = Reference.MODID;
    public static final Item spawnEgg = new ItemHauntedSpawner().setUnlocalizedName("hauntedSpawner").setTextureName("spawn_egg");
    
    public HIItems() {
    
        GameRegistry.registerItem(spawnEgg, "hauntedSpawner", MODID);
    }
}