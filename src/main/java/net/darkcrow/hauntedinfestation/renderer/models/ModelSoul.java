package net.darkcrow.hauntedinfestation.renderer.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelSoul extends ModelBase {
    
    boolean entity = true;
    ModelRenderer head;
    ModelRenderer headgear;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    
    public ModelSoul(boolean isEntity) {
    
        textureWidth = 64;
        textureHeight = 32;
        entity = isEntity;
        
        head = new ModelRenderer(this, 0, 0);
        head.addBox(-4F, -8F, -4F, 8, 8, 8);
        head.setRotationPoint(0F, 0F, 0F);
        head.setTextureSize(64, 32);
        head.mirror = true;
        setRotation(head, 0.7063936F, 0F, 0F);
        
        headgear = new ModelRenderer(this, 32, 0);
        headgear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F);
        headgear.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotation(headgear, 0.7063936F, 0F, 0F);
        
        body = new ModelRenderer(this, 16, 16);
        body.addBox(-4F, 0F, -2F, 8, 12, 4);
        body.setRotationPoint(0F, 0F, 0F);
        body.setTextureSize(64, 32);
        body.mirror = true;
        setRotation(body, 0.5205006F, 0F, 0F);
        
        rightarm = new ModelRenderer(this, 40, 16);
        rightarm.addBox(-3F, -2F, -2F, 4, 12, 4);
        rightarm.setRotationPoint(-5F, 2F, 0F);
        rightarm.setTextureSize(64, 32);
        rightarm.mirror = true;
        setRotation(rightarm, -0.6158017F, 0F, 0F);
        
        leftarm = new ModelRenderer(this, 40, 16);
        leftarm.addBox(-1F, -2F, -2F, 4, 12, 4);
        leftarm.setRotationPoint(5F, 2F, 0F);
        leftarm.setTextureSize(64, 32);
        leftarm.mirror = true;
        setRotation(leftarm, -0.6158045F, 0F, 0F);
    }
    
    public void render (Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        head.render(f5);
        body.render(f5);
        rightarm.render(f5);
        leftarm.render(f5);
        headgear.render(f5);
    }
    
    public void renderAll () {
    
        head.render(0.0625F);
        body.render(0.0625F);
        rightarm.render(0.0625F);
        leftarm.render(0.0625F);
        headgear.render(0.0625F);
    }
    
    private void setRotation (ModelRenderer model, float x, float y, float z) {
    
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
    
    public void setRotationAngles (float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
    
        if (this.entity) {
            
            this.head.rotateAngleY = par4 / (180F / (float) Math.PI);
            this.head.rotateAngleX = par5 / (180F / (float) Math.PI);
            this.headgear.rotateAngleY = this.head.rotateAngleY;
            this.headgear.rotateAngleX = this.head.rotateAngleX;
            this.rightarm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 2.0F * par2 * 0.5F;
            this.leftarm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
            this.rightarm.rotateAngleZ = 0.0F;
            this.leftarm.rotateAngleZ = 0.0F;
        }
    }
}