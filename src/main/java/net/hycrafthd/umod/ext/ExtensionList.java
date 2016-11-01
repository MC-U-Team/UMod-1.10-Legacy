package net.hycrafthd.umod.ext;

import java.util.ArrayList;

import net.hycrafthd.umod.ext.abs.*;
import net.hycrafthd.umod.ext.enderio.EnderIOExtension;
import net.hycrafthd.umod.ext.ic2.IC2ModExtension;
//github.com/MC-UMOD/1.10
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.event.*;

public class ExtensionList {
	
	private static ArrayList<Extension> ex = new ArrayList<Extension>();
	
	public static void onStart(FMLPreInitializationEvent evt) {
		onInit();
		for (Extension e : ex) {
			for (ModContainer c : Loader.instance().getModList()) {
				if (c.getModId().equals(e.getId())) {
					e.init();
					e.getExtension().preinit(evt);
					break;
				}
			}
		}
	}
	
	public static void onInit(FMLInitializationEvent ev) {
		for (Extension e : ex) {
			if (e.isLoaded()) {
				e.getExtension().init(ev);
			}
		}
	}
	
	public static void onPostInit(FMLPostInitializationEvent ev) {
		for (Extension e : ex) {
			if (e.isLoaded()) {
				e.getExtension().postinit(ev);
			}
		}
		for (Extension e : ex) {
			if (e.isLoaded()) {
				AbstractOreDictionaryRegistry oredic = e.getExtension().oredirctionary();
				if (oredic != null) {
					oredic.register();
				}
			}
		}
		for (Extension e : ex) {
			if (e.isLoaded()) {
				AbstractRecipeRegistry recipe = e.getExtension().recipes();
				if (recipe != null) {
					recipe.register();
				}
			}
		}
	}
	
	public static void onClientProxy() {
		for (Extension e : ex) {
			if (e.isLoaded()) {
				e.getExtension().clientRegistry();
			}
		}
	}
	
	public static void onServer(FMLServerStartingEvent ev) {
		for (Extension e : ex) {
			if (e.isLoaded()) {
				e.getExtension().serverstarting(ev);
			}
		}
	}
	
	private static void onInit() {
		
		// ex.add(new Extension("Divex", "cdm", DiveceModExtension.class));
		ex.add(new Extension("IndustrialCraft 2 Extension", "IC2", IC2ModExtension.class));
		
		// TODO Add Apis
		ex.add(new Extension("Ender IO Extension", "EnderIO", EnderIOExtension.class));
		
	}
	
}
