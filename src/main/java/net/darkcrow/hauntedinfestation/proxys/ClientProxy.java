package net.darkcrow.hauntedinfestation.proxys;

import net.darkcrow.hauntedinfestation.entitys.EntitySoul;
import net.darkcrow.hauntedinfestation.renderer.entity.RenderSoul;
import net.darkcrow.hauntedinfestation.renderer.models.ModelSoul;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
    
    @Override
    public void registerRenders () {
    
        RenderingRegistry.registerEntityRenderingHandler(EntitySoul.class, new RenderSoul(new ModelSoul(), 1.0F));
    }
}