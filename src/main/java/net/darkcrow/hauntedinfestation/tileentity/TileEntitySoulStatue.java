package net.darkcrow.hauntedinfestation.tileentity;

import net.darkcrow.hauntedinfestation.entitys.EntitySoul;
import net.darkcrow.hauntedinfestation.util.Utilities;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;

public class TileEntitySoulStatue extends TileEntity {
    
    // TODO system should be changed to use UUID rather than player names.
    private String playerName = "";
    private String blockID = "stone";
    private int blockMeta = 0;
    private int orientation = 0;
    private boolean isPossessed = true;
    
    /**
     * Sets the username of the player that this statue will represent.
     * 
     * @param username: The username of the player to represent.
     */
    public void setPlayerName (String username) {
    
        playerName = username;
    }
    
    /**
     * Retrieves the username of the player that the statue represents.
     * 
     * @return String: The represented username.
     */
    public String getPlayerName () {
    
        return playerName;
    }
    
    /**
     * Sets the block info used for the stand.
     * 
     * @param id: The string ID for the block.
     * @param meta: The metadata value for the block.
     */
    public void setBlock (String id, int meta) {
    
        blockID = id;
        blockMeta = meta;
    }
    
    /**
     * Retrieves the String ID for the block used in the base stand.
     * 
     * @return String: The String ID for the block.
     */
    public String getBlockID () {
    
        return blockID;
    }
    
    /**
     * Sets the direction that this block should be facing.
     * 
     * @param direction: The direction this block should be facing.
     */
    public void setDirection (int direction) {
    
        orientation = direction;
    }
    
    /**
     * Retrieves the direction that this block is curently facing.
     * 
     * @return int: The integer value of the position.
     */
    public int getDirection () {
    
        return orientation;
    }
    
    /**
     * Sets this statue as being possessed.
     * 
     * @param possessed: Is this statue possessed?
     */
    public void setPossessed (boolean possessed) {
    
        isPossessed = possessed;
    }
    
    /**
     * Checks if the statue is possessed or not.
     * 
     * @return boolean: Is this statue possesed?
     */
    public boolean getPossessed () {
    
        return isPossessed;
    }
    
    /**
     * Retrieves the IIcon to use for the bottom stand of the block.
     * 
     * @return IIcon: The icon to be used for the bottom of the statue.
     */
    public IIcon getBlockIcon () {
    
        Block block = Block.getBlockFromName(blockID);
        IIcon icon = block.getIcon(0, blockMeta);
        if (block != null && icon != null)
            return icon;
        
        else
            return Blocks.stone.getIcon(0, 0);
    }
    
    /**
     * Awakens the statue, spawning an EnttiySoul where the statue is standing, and deletes the
     * statue.
     */
    public void awakenStatue () {
    
        if (!worldObj.isRemote) {
            
            EntitySoul soul = new EntitySoul(worldObj);
            soul.setLocationAndAngles(xCoord, yCoord, zCoord, 0, 0);
            soul.setCustomNameTag(playerName);
            worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
            worldObj.spawnEntityInWorld(soul);
        }
    }
    
    @Override
    public void onDataPacket (NetworkManager net, S35PacketUpdateTileEntity pkt) {
    
        this.readFromNBT(pkt.func_148857_g());
        
    }
    
    @Override
    public Packet getDescriptionPacket () {
    
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbt);
    }
    
    @Override
    public void readFromNBT (NBTTagCompound nbt) {
    
        super.readFromNBT(nbt);
        playerName = nbt.getString("playername");
        orientation = nbt.getInteger("direction");
        blockID = nbt.getString("blockid");
        blockMeta = nbt.getInteger("blockmeta");
        isPossessed = nbt.getBoolean("isPossessed");
    }
    
    @Override
    public void writeToNBT (NBTTagCompound nbt) {
    
        super.writeToNBT(nbt);
        nbt.setString("playername", playerName);
        nbt.setInteger("direction", orientation);
        nbt.setString("blockid", blockID);
        nbt.setInteger("blockmeta", blockMeta);
        nbt.setBoolean("isPossessed", isPossessed);
    }
    
    @Override
    public void updateEntity () {
    
        if (this.worldObj != null && !this.worldObj.playerEntities.isEmpty() && this.isPossessed) {
            
            for (Object playerObj : this.worldObj.playerEntities) {
                
                if (playerObj instanceof EntityPlayer) {
                    
                    EntityPlayer player = (EntityPlayer) playerObj;
                    
                    if (!player.capabilities.isCreativeMode && Utilities.isEntityWithinRange(player, xCoord, yCoord, zCoord, 5.0))
                        awakenStatue();
                }
            }
        }
    }
}
