package net.darkhax.haunted.handler;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.event.RenderLivingEvent;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class RenderingHandler {
    
    public RenderingHandler() {
    
        System.out.println("0000000000000000000");
        System.out.println("0000000000000000000");
        System.out.println("0000000000000000000");
        System.out.println("0000000000000000000");
    }
    
    @SubscribeEvent
    public void onEntityRender (RenderLivingEvent.Pre event) {
    
        NBTTagCompound tag = event.entity.getEntityData();
        if (tag != null) {
            
            GL11.glPushMatrix();
            GL11.glEnable(GL11.GL_BLEND);
            // GL11.glDisable(GL11.GL_LIGHTING);
            // GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glEnable(GL11.GL_ALPHA_TEST);
            GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glColor4f(0.6F, 1.0F, 1.0F, 0.5F);
            GL11.glPopMatrix();
            
            // Render Ghost Effects
            if (tag.hasKey("isSpectral") && tag.getBoolean("isSpectral")) {
                
                System.out.println("I am a ghost");
            }
        }
    }
    
    @SubscribeEvent
    public void onEntityRender (RenderLivingEvent.Specials.Pre event) {
    
        NBTTagCompound tag = event.entity.getEntityData();
        if (tag != null) {
            
            GL11.glPushMatrix();
            GL11.glEnable(GL11.GL_BLEND);
            // GL11.glDisable(GL11.GL_LIGHTING);
            // GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glEnable(GL11.GL_ALPHA_TEST);
            GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glColor4f(0.6F, 1.0F, 1.0F, 0.5F);
            GL11.glPopMatrix();
            
            // Render Ghost Effects
            if (tag.hasKey("isSpectral") && tag.getBoolean("isSpectral")) {
                
                System.out.println("I am a ghost");
            }
        }
    }
    
    public void renderTranslucent (float red, float green, float blue, float alpha) {
    
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(red, green, blue, alpha);
        GL11.glPopMatrix();
    }
}
