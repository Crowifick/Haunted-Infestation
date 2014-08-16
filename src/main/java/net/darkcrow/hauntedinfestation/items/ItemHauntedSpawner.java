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
    
    public boolean onItemUse (ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
    
        if (world.isRemote) {
            return true;
        }
        else {
            Block block = world.getBlock(x, y, z);
            x += Facing.offsetsXForSide[p_77648_7_];
            y += Facing.offsetsYForSide[p_77648_7_];
            z += Facing.offsetsZForSide[p_77648_7_];
            double d0 = 0.0D;
            
            if (p_77648_7_ == 1 && block.getRenderType() == 11) {
                d0 = 0.5D;
            }
            
            Entity entity = spawnCreature(world, stack.getItemDamage(), (double) x + 0.5D, (double) y + d0, (double) z + 0.5D);
            
            if (entity != null) {
                if (entity instanceof EntityLivingBase && stack.hasDisplayName()) {
                    ((EntityLiving) entity).setCustomNameTag(stack.getDisplayName());
                }
                
                if (!player.capabilities.isCreativeMode) {
                    --stack.stackSize;
                }
            }
            
            return true;
        }
    }
    
    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args:
     * itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick (ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
    
        if (p_77659_2_.isRemote) {
            return p_77659_1_;
        }
        else {
            MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(p_77659_2_, p_77659_3_, true);
            
            if (movingobjectposition == null) {
                return p_77659_1_;
            }
            else {
                if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                    int i = movingobjectposition.blockX;
                    int j = movingobjectposition.blockY;
                    int k = movingobjectposition.blockZ;
                    
                    if (!p_77659_2_.canMineBlock(p_77659_3_, i, j, k)) {
                        return p_77659_1_;
                    }
                    
                    if (!p_77659_3_.canPlayerEdit(i, j, k, movingobjectposition.sideHit, p_77659_1_)) {
                        return p_77659_1_;
                    }
                    
                    if (p_77659_2_.getBlock(i, j, k) instanceof BlockLiquid) {
                        Entity entity = spawnCreature(p_77659_2_, p_77659_1_.getItemDamage(), (double) i, (double) j, (double) k);
                        
                        if (entity != null) {
                            if (entity instanceof EntityLivingBase && p_77659_1_.hasDisplayName()) {
                                ((EntityLiving) entity).setCustomNameTag(p_77659_1_.getDisplayName());
                            }
                            
                            if (!p_77659_3_.capabilities.isCreativeMode) {
                                --p_77659_1_.stackSize;
                            }
                        }
                    }
                }
                
                return p_77659_1_;
            }
        }
    }
    
    /**
     * Spawns the creature specified by the egg's type in the location specified by the last
     * three parameters. Parameters: world, entityID, x, y, z.
     */
    public static Entity spawnCreature (World p_77840_0_, int p_77840_1_, double p_77840_2_, double p_77840_4_, double p_77840_6_) {
    
        if (!HIEntityList.entityEggs.containsKey(Integer.valueOf(p_77840_1_))) {
            return null;
        }
        else {
            Entity entity = null;
            
            for (int j = 0; j < 1; ++j) {
                entity = HIEntityList.createEntityByID(p_77840_1_, p_77840_0_);
                
                if (entity != null && entity instanceof EntityLivingBase) {
                    EntityLiving entityliving = (EntityLiving) entity;
                    entity.setLocationAndAngles(p_77840_2_, p_77840_4_, p_77840_6_, MathHelper.wrapAngleTo180_float(p_77840_0_.rand.nextFloat() * 360.0F), 0.0F);
                    entityliving.rotationYawHead = entityliving.rotationYaw;
                    entityliving.renderYawOffset = entityliving.rotationYaw;
                    entityliving.onSpawnWithEgg((IEntityLivingData) null);
                    p_77840_0_.spawnEntityInWorld(entity);
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