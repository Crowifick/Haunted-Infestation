package net.darkhax.haunted.entitys;

import net.darkhax.haunted.Haunted;
import net.minecraft.entity.Entity;
import cpw.mods.fml.common.registry.EntityRegistry;

public class HauntedEntitys {
    
    public HauntedEntitys() {
    
        registerEntity(EntitySoul.class, "soul", 1, 0x000000, 0xffffff, 30, 5, true);
    }
    
    public static void registerEntity (Class<? extends Entity> entityClass, String name, int id, int primary, int secondary, int tracking, int frequency, boolean update) {
    
        EntityRegistry.registerModEntity(entityClass, name, id, Haunted.instance, tracking, frequency, update);
        HauntedEntityList.addMapping(entityClass, name, id, primary, secondary);
    }
}