package net.darkhax.haunted.renderer.tileentity;

import net.darkhax.haunted.renderer.models.ModelSoul;
import net.darkhax.haunted.tileentity.TileEntitySoulStatue;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class RenderTileEntitySoul extends TileEntitySpecialRenderer {
    
    public ResourceLocation texture;
    public ResourceLocation stone = new ResourceLocation("minecraft", "textures/blocks/stone.png");
    private ModelSoul model = new ModelSoul(false);
    
    public void renderModel (TileEntitySoulStatue te, double d, double d1, double d2, float f) {
    
        if (te.getPlayerName() != null && !te.getPlayerName().isEmpty()) {
            
            texture = AbstractClientPlayer.getLocationSkin(te.getPlayerName());
            AbstractClientPlayer.getDownloadImageSkin(texture, te.getPlayerName());
        }
        
        else {
            
            texture = new ResourceLocation("minecraft", "textures/entity/steve.png");
        }
        
        GL11.glPushMatrix();
        GL11.glTranslatef((float) d + 0.5F, (float) d1 + 1.5F, (float) d2 + 0.5F);
        
        if (te.getDirection() == 0)
            GL11.glRotatef(180, 0.0f, 1.0f, 0.0f);
        
        if (te.getDirection() == 1)
            GL11.glRotatef(90, 0.0f, 1.0f, 0.0f);
        
        if (te.getDirection() == 2)
            GL11.glRotatef(360, 0.0f, 1.0f, 0.0f);
        
        if (te.getDirection() == 3)
            GL11.glRotatef(270, 0.0f, 1.0f, 0.0f);
        
        GL11.glScalef(1.0F, -1F, -1F);
        this.bindTexture(texture);
        model.renderAll();
        GL11.glPopMatrix();
    }
    
    @Override
    public void renderTileEntityAt (TileEntity tileentity, double d0, double d1, double d2, float f) {
    
        renderModel((TileEntitySoulStatue) tileentity, d0, d1, d2, f);
    }
}