package net.darkhax.haunted.items;

import net.darkhax.haunted.util.Constants;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class HIItems {
    
    public static final String MODID = Constants.MODID;
    public static final Item spawnEgg = new ItemHauntedSpawner().setUnlocalizedName("hauntedSpawner").setTextureName("spawn_egg");
    public static final Item spawner = new ItemSpawnerTest().setTextureName("test");
    
    public HIItems() {
    
        GameRegistry.registerItem(spawnEgg, "hauntedSpawner", MODID);
        GameRegistry.registerItem(spawner, "testSpawner", MODID);
    }
}