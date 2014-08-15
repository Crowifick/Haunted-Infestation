package net.darkcrow.hauntedinfestation.proxys;

import net.darkcrow.hauntedinfestation.entitys.EntitySoul;
import net.darkcrow.hauntedinfestation.renderer.entity.RenderSoul;
import net.darkcrow.hauntedinfestation.renderer.models.ModelSoul;
import net.darkcrow.hauntedinfestation.renderer.tileentity.RenderTileEntitySoul;
import net.darkcrow.hauntedinfestation.tileentity.TileEntitySoulStatue;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
    
    @Override
    public void registerRenders () {
    
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySoulStatue.class, new RenderTileEntitySoul());
        RenderingRegistry.registerEntityRenderingHandler(EntitySoul.class, new RenderSoul(new ModelSoul(true), 1.0F));
    }
}