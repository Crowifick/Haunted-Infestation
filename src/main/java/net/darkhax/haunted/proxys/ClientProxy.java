package net.darkhax.haunted.proxys;

import net.darkhax.haunted.blocks.HIBlocks;
import net.darkhax.haunted.entitys.EntitySoul;
import net.darkhax.haunted.renderer.entity.RenderSoul;
import net.darkhax.haunted.renderer.item.RendererItemTileEntitySoul;
import net.darkhax.haunted.renderer.models.ModelSoul;
import net.darkhax.haunted.renderer.tileentity.RenderTileEntitySoul;
import net.darkhax.haunted.tileentity.TileEntitySoulStatue;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
    
    @Override
    public void registerRenders () {
    
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySoulStatue.class, new RenderTileEntitySoul());
        RenderingRegistry.registerEntityRenderingHandler(EntitySoul.class, new RenderSoul(new ModelSoul(true), 1.0F));
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(HIBlocks.soulStatue), new RendererItemTileEntitySoul());
    }
    
    @Override
    public void registerSidedEvents () {
    
    }
}