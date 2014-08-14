package net.darkcrow.hauntedinfestation.entitys;

import net.darkcrow.hauntedinfestation.HauntedInfestation;
import net.darkcrow.hauntedinfestation.util.Reference;
import net.minecraft.entity.Entity;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class HIEntitys {
    
    public static void init () {
    
        registerEntity(EntitySoul.class, "soul", 1, 0x000000, 0xffffff, 30, 5, true);
    }
    
    public static void registerEntity (Class<? extends Entity> entityClass, String name, int id, int primary, int secondary, int tracking, int frequency, boolean update) {
    
        EntityRegistry.registerModEntity(entityClass, name, id, HauntedInfestation.instance, tracking, frequency, update);
        HIEntityList.addMapping(entityClass, name, id, primary, secondary);
    }
}