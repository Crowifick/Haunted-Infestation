package net.darkcrow.hauntedinfestation.renderer.entity;

import net.darkcrow.hauntedinfestation.entitys.EntitySoul;
import net.darkcrow.hauntedinfestation.renderer.models.ModelSoul;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSoul extends RenderLiving {
    private static final ResourceLocation texture = new ResourceLocation("haunted", "textures/mobs/lost_soul.png");
    
    Minecraft mc = Minecraft.getMinecraft();
    
    public RenderSoul(ModelSoul model, float size) {
    
        super(model, size);
    }
    
    @Override
    protected ResourceLocation getEntityTexture (Entity entity) {
    
        EntitySoul soul = (EntitySoul) entity;
        
        if (soul != null) {
            
            ResourceLocation resourcelocation = AbstractClientPlayer.locationStevePng;
            
            if (soul.hasCustomNameTag()) {
                
                resourcelocation = AbstractClientPlayer.getLocationSkin(soul.getCustomNameTag());
                AbstractClientPlayer.getDownloadImageSkin(resourcelocation, soul.getCustomNameTag());
                
            }
            
            else {
                
                resourcelocation = new ResourceLocation("haunted", "textures/mobs/lost_soul.png");
            }
            
            Minecraft.getMinecraft().renderEngine.bindTexture(resourcelocation);
            return resourcelocation;
        }
        
        return new ResourceLocation("haunted", "textures/mobs/lost_Soul_Green.png");
    }
    
    public void renderSoul (EntitySoul soul, double par2, double par4, double par6, float par8, float par9) {
    
        mc.renderEngine.bindTexture(texture);
        
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
        GL11.glPopMatrix();
        
        super.doRender(soul, par2, par4, par6, par8, par9);
    }
    
    @Override
    public void doRender (Entity entity, double par2, double par4, double par6, float par8, float par9) {
    
        this.renderSoul((EntitySoul) entity, par2, par4, par6, par8, par9);
    }
}