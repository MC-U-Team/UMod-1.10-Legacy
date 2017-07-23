package io.github.mc_umod;

import io.github.mc_umod.api.*;
import io.github.mc_umod.event.*;
import io.github.mc_umod.event.apis.*;
import io.github.mc_umod.ext.ExtensionList;
import io.github.mc_umod.network.PacketHandler;
import io.github.mc_umod.render.RenderDiscordOverlay;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.*;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;

@Singelton
@Mod(modid = UReference.modid, version = UReference.version, name = UReference.name)
public class UMod {
	
	//, dependencies = "required-after:corelib"
	
	public static org.apache.logging.log4j.Logger log;
	private UConfig config;
	
	@Instance
	public static UMod INSTANCE;
	
	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		log = event.getModLog();
		UReference.proxy.init();
		ExtensionList.onStart(event);
		this.config = new UConfig(event.getSuggestedConfigurationFile());
		new PacketHandler();
		UReference.proxy.registerRenderer();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		ExtensionList.onInit(event);
		new UPotion();
		new UItems();
		new UBlocks();
		new UArmor();
		new UDamageSource();
		new UBiome();
		new UEntity();
		new UTools();
		this.registerGenerators();
		this.registerEvents(event);
		UMod.log.info("Init Mod.");
	}
	
	@EventHandler
	public void postinit(FMLPostInitializationEvent event) {
		ExtensionList.onPostInit(event);
		new UTiles();
		new URecipes();
		new UChestLoot();
		new UAchievement();
		NetworkRegistry.INSTANCE.registerGuiHandler(UReference.modid,new UGuiHandler());
		UReference.proxy.registerModels();
		UMod.log.info("Registered Mod.");
	}
	
	@EventHandler
	public void serverstarting(FMLServerStartingEvent event) {
		ExtensionList.onServer(event);
		new UCommands(event);
		UMod.log.info("Registered Mod Commands.");
	}
	
	public void registerEvents(FMLInitializationEvent evt) {
		UEvent event = new UEvent();
		event.addEvent(new EventGettingRadiation());
		event.addEvent(new EventExecuteRadiation());
		event.addEvent(new ProcessHandler());
		event.addEvent(new EventGettingRadiationInv());
		event.addEvent(new EventLoadWorld());
		event.addEvent(new EventDrawHUD());
		event.addEvent(new EventPlayerJoin());
		event.addEvent(new EventToolTip());
		event.addEvent(new EventChestInventory());
		if(evt.getSide().equals(Side.SERVER))event.addEvent(new EventRegRegistery());
		if(evt.getSide().equals(Side.CLIENT))event.addEvent(new EventRenderRegistery());
		event.addEvent(new RenderDiscordOverlay());
		event.register();
		UMod.log.info("Registered Mod Events.");
	}
	
	public void registerGenerators() {
		UGeneration generation = new UGeneration();
		generation.addGenerator(new UOreGeneration(), 0);
		generation.register();
		UMod.log.info("Registered Mod Generators.");
	}
	
	public UConfig getUConfig(){
		return this.config;
	}
	
}
