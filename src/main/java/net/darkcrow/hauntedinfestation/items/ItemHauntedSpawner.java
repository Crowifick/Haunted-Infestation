package net.darkcrow.hauntedinfestation.items;

import java.util.Iterator;
import java.util.List;

import net.darkcrow.hauntedinfestation.entitys.HIEntityList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemHauntedSpawner extends Item {
    
    @SideOnly(Side.CLIENT)
    private IIcon theIcon;
    
    public ItemHauntedSpawner() {
    
        // TODO: Switch creative tab to our own tab later.
        this.setHasSubtypes(true);
        this.setCreativeTab(CreativeTabs.tabMisc);
    }
    
    /**
     * Used to generate a unique display name for each entity in our list.
     */
    @Override
    public String getItemStackDisplayName (ItemStack stack) {
    
        String display = ("" + StatCollector.translateToLocal(this.getUnlocalizedName() + ".name")).trim();
        String entityName = HIEntityList.getStringFromID(stack.getItemDamage());
        
        if (entityName != null)
            display = display + " " + StatCollector.translateToLocal("entity." + entityName + ".name");
        
        return display;
    }
    
    /**
     * Retrieves the color for the render pass. Ours has normal, primary and secondar colors.
     */
    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack (ItemStack stack, int pass) {
    
        HIEntityList.EntityEggInfo entityegginfo = (HIEntityList.EntityEggInfo) HIEntityList.entityEggs.get(Integer.valueOf(stack.getItemDamage()));
        return entityegginfo != null ? (pass == 0 ? entityegginfo.primaryColor : entityegginfo.secondaryColor) : 16777215;
    }
    
    @Override
    public boolean onItemUse (ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float f0, float f1, float f2) {
    
        if (world.isRemote) {
            
            return true;
        }
        
        else {
            
            Block block = world.getBlock(x, y, z);
            x += Facing.offsetsXForSide[side];
            y += Facing.offsetsYForSide[side];
            z += Facing.offsetsZForSide[side];
            double d0 = 0.0D;
            
            if (side == 1 && block.getRenderType() == 11)
                d0 = 0.5D;
            
            Entity entity = spawnCreature(world, stack.getItemDamage(), (double) x + 0.5D, (double) y + d0, (double) z + 0.5D);
            
            if (entity != null) {
                
                if (entity instanceof EntityLivingBase && stack.hasDisplayName())
                    ((EntityLiving) entity).setCustomNameTag(stack.getDisplayName());
                
                --stack.stackSize;
            }
            
            return true;
        }
    }
    
    @Override
    public ItemStack onItemRightClick (ItemStack stack, World world, EntityPlayer player) {
    
        if (world.isRemote) {
            
            return stack;
        }
        
        else {
            MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, player, true);
            
            if (movingobjectposition == null)
                return stack;
            
            else {
                
                if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                    
                    int i = movingobjectposition.blockX;
                    int j = movingobjectposition.blockY;
                    int k = movingobjectposition.blockZ;
                    
                    if (!world.canMineBlock(player, i, j, k))
                        return stack;
                    
                    if (!player.canPlayerEdit(i, j, k, movingobjectposition.sideHit, stack))
                        return stack;
                    
                    if (world.getBlock(i, j, k) instanceof BlockLiquid) {
                        
                        Entity entity = spawnCreature(world, stack.getItemDamage(), (double) i, (double) j, (double) k);
                        
                        if (entity != null) {
                            
                            if (entity instanceof EntityLivingBase && stack.hasDisplayName())
                                ((EntityLiving) entity).setCustomNameTag(stack.getDisplayName());
                            
                            if (!player.capabilities.isCreativeMode)
                                --stack.stackSize;
                        }
                    }
                }
                
                return stack;
            }
        }
    }
    
    /**
     * Used to summon an entity based on the id provided.
     * 
     * @param world: Instance of the world that this entity should be spawned in.
     * @param entityID: The integer based ID for the mob we want to spawn.
     * @param x: The x position for the mob.
     * @param y: The y position for the mob.
     * @param z: The z position for the mob.
     * @return Entity: The entity spawned.
     */
    public static Entity spawnCreature (World world, int entityID, double x, double y, double z) {
    
        if (!HIEntityList.entityEggs.containsKey(Integer.valueOf(entityID))) {
            
            return null;
        }
        
        else {
            
            Entity entity = null;
            
            for (int j = 0; j < 1; ++j) {
                
                entity = HIEntityList.createEntityByID(entityID, world);
                
                if (entity != null && entity instanceof EntityLivingBase) {
                    
                    EntityLiving entityliving = (EntityLiving) entity;
                    entity.setLocationAndAngles(x, y, z, MathHelper.wrapAngleTo180_float(world.rand.nextFloat() * 360.0F), 0.0F);
                    entityliving.rotationYawHead = entityliving.rotationYaw;
                    entityliving.renderYawOffset = entityliving.rotationYaw;
                    entityliving.onSpawnWithEgg((IEntityLivingData) null);
                    world.spawnEntityInWorld(entity);
                    entityliving.playLivingSound();
                }
            }
            
            return entity;
        }
    }
    
    /**
     * This item takes advantage of multiple render passes to create the different colors for
     * the overlay.
     */
    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses () {
    
        return true;
    }
    
    /**
     * Used to grab a different icon for ech render stage. 0 = base, 1 = primary, 2 =
     * secondary.
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamageForRenderPass (int damage, int pass) {
    
        return pass > 0 ? this.theIcon : super.getIconFromDamageForRenderPass(damage, pass);
    }
    
    /**
     * Uses the entity list to add all versions of this item to the creative tab.
     */
    @SideOnly(Side.CLIENT)
    public void getSubItems (Item item, CreativeTabs tab, List itemList) {
    
        Iterator iterator = HIEntityList.entityEggs.values().iterator();
        
        while (iterator.hasNext()) {
            
            HIEntityList.EntityEggInfo entityegginfo = (HIEntityList.EntityEggInfo) iterator.next();
            itemList.add(new ItemStack(item, 1, entityegginfo.entityID));
        }
    }
    
    /**
     * Registers different icons for this item to use.
     */
    @SideOnly(Side.CLIENT)
    public void registerIcons (IIconRegister register) {
    
        super.registerIcons(register);
        this.theIcon = register.registerIcon(this.getIconString() + "_overlay");
    }
}