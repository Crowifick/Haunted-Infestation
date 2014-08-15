package net.darkcrow.hauntedinfestation.blocks;

import net.darkcrow.hauntedinfestation.tileentity.TileEntitySoulStatue;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemNameTag;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSoulStatue extends BlockContainer {
    
    protected BlockSoulStatue() {
    
        super(Material.rock);
        this.setBlockName("soulStatue");
        this.setBlockTextureName("stone");
        this.setCreativeTab(CreativeTabs.tabMisc);
        this.setLightOpacity(0);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
        this.setHardness(3.0f);
        this.setResistance(6.0f);
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIcon (IBlockAccess access, int x, int y, int z, int meta) {
    
        TileEntitySoulStatue te = (TileEntitySoulStatue) access.getTileEntity(x, y, z);
        
        if (te != null)
            return te.getBlockIcon();
        
        else
            return this.getIcon(0, 0);
    }
    
    @Override
    public void onBlockPlacedBy (World world, int x, int y, int z, EntityLivingBase living, ItemStack stack) {
    
        TileEntitySoulStatue tile = (TileEntitySoulStatue) world.getTileEntity(x, y, z);
        
        if (stack.hasDisplayName())
            tile.setPlayerName(stack.getDisplayName());
        
        else
            tile.setPlayerName("statue");
        
        tile.setDirection(MathHelper.floor_double((double) ((living.rotationYaw * 4F) / 360F) + 0.5D) & 3);
    }
    
    @Override
    public boolean onBlockActivated (World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
    
        ItemStack stack = player.getHeldItem();
        TileEntitySoulStatue te = (TileEntitySoulStatue) world.getTileEntity(x, y, z);
        if (te != null && stack != null) {
            
            if (stack.getItem() instanceof ItemNameTag) {
                
                te.setPlayerName(stack.getDisplayName());
                --stack.stackSize;
                return true;
            }
            
            Block block = Block.getBlockFromItem(stack.getItem());
            
            if (block != null)
                te.setBlock(Block.blockRegistry.getNameForObject(block), stack.getItemDamage());
        }
        
        return false;
    }
    
    @Override
    public TileEntity createNewTileEntity (World world, int meta) {
    
        return new TileEntitySoulStatue();
    }
    
    @Override
    public boolean renderAsNormalBlock () {
    
        return false;
    }
    
    @Override
    public boolean canConnectRedstone (IBlockAccess world, int x, int y, int z, int side) {
    
        return true;
    }
    
    @Override
    public boolean isOpaqueCube () {
    
        return false;
    }
}