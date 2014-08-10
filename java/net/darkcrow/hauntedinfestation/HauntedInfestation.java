package net.darkcrow.hauntedinfestation;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.darkcrow.hauntedinfestation.blocks.HIBlocks;
import net.darkcrow.hauntedinfestation.entitys.HIEntitys;
import net.darkcrow.hauntedinfestation.items.HIItems;
import net.darkcrow.hauntedinfestation.proxys.CommonProxy;
import net.darkcrow.hauntedinfestation.util.Reference;

/**
 * User: Crowifick
 * Date: 8/9/14
 * Time: 7:42 PM
 * This mod is open source but do not come to me with problems
 * on how *insert code here* won't work on your side.
 */
@Mod(name = Reference.NAME, modid = Reference.MODID, version = Reference.VERSION)
public class HauntedInfestation {

    @Mod.Instance(Reference.MODID)
    public static HauntedInfestation instance;

    @SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.COMMON)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        HIBlocks.init();
        HIItems.init();
        HIEntitys.init();

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {



    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {



    }

}
