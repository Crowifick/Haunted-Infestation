package net.darkhax.haunted.items;

import java.util.List;

import net.darkhax.haunted.Haunted;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSpawnerTest extends Item {
    
    public ItemSpawnerTest() {
    
        super();
        this.setCreativeTab(Haunted.tabsHaunted);
        this.setUnlocalizedName("spawner");
    }
    
    public ItemStack onItemRightClick (ItemStack stack, World world, EntityPlayer player) {
    
        if (!stack.hasTagCompound())
            stack.setTagCompound(new NBTTagCompound());
        
        NBTTagCompound tag = stack.stackTagCompound;
        
        if (tag.hasKey("entityData")) {
            
            NBTTagCompound storedEntity = (NBTTagCompound) tag.getTag("entityData");
            EntityLiving entity = (EntityLiving) EntityList.createEntityByName(tag.getString("entityName"), world);
            if (entity != null && !world.isRemote) {
                
                entity.readEntityFromNBT(storedEntity);
                entity.getEntityData().setBoolean("isSpectral", true);
                entity.setLocationAndAngles(player.posX, player.posY, player.posZ, 0, 0);
                world.spawnEntityInWorld(entity);
            }
        }
        
        return stack;
    }
    
    public boolean itemInteractionForEntity (ItemStack stack, EntityPlayer player, EntityLivingBase entity) {
    
        if (!stack.hasTagCompound())
            stack.setTagCompound(new NBTTagCompound());
        
        NBTTagCompound tag = stack.stackTagCompound;
        NBTTagCompound storedEntity = new NBTTagCompound();
        entity.writeEntityToNBT(storedEntity);
        tag.setTag("entityData", storedEntity);
        tag.setString("entityName", EntityList.getEntityString(entity));
        entity.setDead();
        return true;
    }
    
    @SideOnly(Side.CLIENT)
    public void addInformation (ItemStack stack, EntityPlayer player, List tooltip, boolean isAdvanced) {
    
        if (stack.hasTagCompound() && stack.stackTagCompound.hasKey("entityData")) {
            
            NBTTagCompound storedEntity = (NBTTagCompound) stack.stackTagCompound.getTag("newentitytag");
            EntityLiving entity = (EntityLiving) EntityList.createEntityByName(stack.stackTagCompound.getString("entityName"), player.worldObj);
            
            if (entity != null) {
                
                tooltip.add("Entity Name: " + EntityList.getEntityString(entity));
                tooltip.add("Entity Health: " + entity.getHealth());
            }
        }
    }
}
