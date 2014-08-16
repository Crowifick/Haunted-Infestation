package net.darkcrow.hauntedinfestation.util;

import net.minecraft.entity.Entity;

public class Utilities {
    
    /**
     * Checks if an entity is within X range of another entity.
     * 
     * @param source: The source entity.
     * @param target: The entity being compared.
     * @param range: The acceptable block range.
     * @return true if target is within the range of the source and not the same entity.
     */
    public static boolean isEntityWithinRange (Entity source, Entity target, double range) {
    
        if (isEntityWithinRange(target, source.posX, source.posY, source.posZ, range)) {
            
            if (source != target) {
                
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Checks if an entity is within X range of given coordinates.
     * 
     * @param target: The target entity.
     * @param x: The source X coord.
     * @param y: The source Y coord.
     * @param z: The source Z coord.
     * @param range: Acceptable range of distance between entity and position.
     * @return true if entity is within distance.
     */
    public static boolean isEntityWithinRange (Entity target, double x, double y, double z, double range) {
    
        double disX = Math.abs(x - target.posX);
        double disY = Math.abs(y - target.posY);
        double disZ = Math.abs(z - target.posZ);
        
        if ((disX + disY + disZ < range)) {
            
            return true;
        }
        
        return false;
    }
}