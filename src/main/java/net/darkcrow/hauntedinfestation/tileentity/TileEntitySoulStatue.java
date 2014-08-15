package net.darkcrow.hauntedinfestation.tileentity;

import net.minecraft.block.Block;
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
    
    public void setPlayerName (String username) {
    
        playerName = username;
    }
    
    public String getPlayerName () {
    
        return playerName;
    }
    
    public void setBlock (String id, int meta) {
    
        blockID = id;
        blockMeta = meta;
    }
    
    public String getBlockID () {
    
        return blockID;
    }
    
    public void setDirection (int direction) {
    
        orientation = direction;
    }
    
    public int getDirection () {
    
        return orientation;
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
    }
    
    @Override
    public void writeToNBT (NBTTagCompound nbt) {
    
        super.writeToNBT(nbt);
        nbt.setString("playername", playerName);
        nbt.setInteger("direction", orientation);
        nbt.setString("blockid", blockID);
        nbt.setInteger("blockmeta", blockMeta);
    }
    
    public IIcon getBlockIcon () {
    
        Block block = Block.getBlockFromName(blockID);
        IIcon icon = block.getIcon(0, blockMeta);
        if (block != null && icon != null)
            return icon;
        
        else
            return Blocks.stone.getIcon(0, 0);
    }
}
