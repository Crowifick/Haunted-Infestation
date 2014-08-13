package net.darkcrow.hauntedinfestation;

import java.util.Arrays;

import net.darkcrow.hauntedinfestation.blocks.HIBlocks;
import net.darkcrow.hauntedinfestation.entitys.HIEntitys;
import net.darkcrow.hauntedinfestation.items.HIItems;
import net.darkcrow.hauntedinfestation.proxys.CommonProxy;
import net.darkcrow.hauntedinfestation.util.Reference;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(name = Reference.NAME, modid = Reference.MODID, version = Reference.VERSION)
public class HauntedInfestation {
    
    @Mod.Instance(Reference.MODID)
    public static HauntedInfestation instance;
    
    @SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.COMMON)
    public static CommonProxy proxy;
    
    @Mod.EventHandler
    public void preInit (FMLPreInitializationEvent event) {
    
        setModInfo(event.getModMetadata());
        HIBlocks.init();
        HIItems.init();
        HIEntitys.init();
    }
    
    @Mod.EventHandler
    public void init (FMLInitializationEvent event) {
    
    }
    
    @Mod.EventHandler
    public void postInit (FMLPostInitializationEvent event) {
    
    }
    
    /**
     * Method used to provide information on the mod. This is the code equivalent of a mcmod.info file.
     * 
     * @param meta: An instance of the ModMetadata.
     */
    void setModInfo (ModMetadata meta) {
    
        meta.authorList = Arrays.asList(new String[] { "Crowfick", "Darkhax", "HoopAWolf" });
        meta.logoFile = "";
        meta.credits = "";
        meta.description = "";
        meta.url = "";
        meta.autogenerated = false;
    }
}