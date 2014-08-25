package net.darkhax.haunted.entitys;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HauntedEntityList {
    
    private static final Logger logger = LogManager.getLogger();
    
    public static Map stringToClassMapping = new HashMap();
    public static Map classToStringMapping = new HashMap();
    public static Map IDtoClassMapping = new HashMap();
    private static Map classToIDMapping = new HashMap();
    private static Map stringToIDMapping = new HashMap();
    public static HashMap entityEggs = new LinkedHashMap();
    
    /**
     * 
     * @param clazz: The entity class.
     * @param name: Unique name of the entity.
     * @param id: Unique id for the entity.
     */
    public static void addMapping (Class clazz, String name, int id) {
    
        if (stringToClassMapping.containsKey(name)) {
            
            throw new IllegalArgumentException("ID is already registered: " + name);
        }
        
        else if (IDtoClassMapping.containsKey(Integer.valueOf(id))) {
            
            throw new IllegalArgumentException("ID is already registered: " + id);
        }
        
        else {
            
            stringToClassMapping.put(name, clazz);
            classToStringMapping.put(clazz, name);
            IDtoClassMapping.put(Integer.valueOf(id), clazz);
            classToIDMapping.put(clazz, Integer.valueOf(id));
            stringToIDMapping.put(name, Integer.valueOf(id));
        }
    }
    
    /**
     * Registers an entity with the system, also does mob eggs.
     * 
     * @param clazz: The entity class.
     * @param name: Unique name of the entity.
     * @param id: Unique id for the entity.
     * @param primary: The primary color for the egg, 0x000000
     * @param secondary: The secondary color for the egg, 0xffffff
     */
    public static void addMapping (Class clazz, String name, int id, int primary, int secondary) {
    
        addMapping(clazz, name, id);
        entityEggs.put(Integer.valueOf(id), new HauntedEntityList.EntityEggInfo(id, primary, secondary));
    }
    
    /**
     * Retrieves the unique the name of the entity from its unique id.
     * 
     * @param id: The unique id for the entity.
     * @return String: The unique name of the entity provided when first registered.
     */
    public static String getStringFromID (int id) {
    
        Class oclass = getClassFromID(id);
        return oclass != null ? (String) classToStringMapping.get(oclass) : null;
    }
    
    /**
     * Retrieves a class file from the entity list based on the entity id provide.
     * 
     * @param id: The unique entity ID.
     * @return Class: The class associated with the provided id.
     */
    public static Class getClassFromID (int id) {
    
        return (Class) IDtoClassMapping.get(Integer.valueOf(id));
    }
    
    /**
     * Creates an instance of an Entity from an id.
     * 
     * @param id: The unique entity ID.
     * @param world: An instance of the world.
     * @return Entity: Entity based on provided ID.
     */
    public static Entity createEntityByID (int id, World world) {
    
        Entity entity = null;
        
        try {
            
            Class oclass = getClassFromID(id);
            
            if (oclass != null) {
                
                entity = (Entity) oclass.getConstructor(new Class[] { World.class }).newInstance(new Object[] { world });
            }
        }
        
        catch (Exception exception) {
            
            exception.printStackTrace();
        }
        
        if (entity == null) {
            
            logger.warn("Entity with ID " + id + " is null, it will be skipped.");
        }
        
        return entity;
    }
    
    public static class EntityEggInfo {
        
        public final int entityID;
        public final int primaryColor;
        public final int secondaryColor;
        
        /**
         * Creates an EntityEggInfo, this is a wrapper for entity eggs.
         * 
         * @param id: The unique id for the entity.
         * @param primary: The primary color for the entity egg, 0x000000
         * @param secondary: The secondary color for the entity egg, 0xffffff
         */
        public EntityEggInfo(int id, int primary, int secondary) {
        
            this.entityID = id;
            this.primaryColor = primary;
            this.secondaryColor = secondary;
        }
    }
}