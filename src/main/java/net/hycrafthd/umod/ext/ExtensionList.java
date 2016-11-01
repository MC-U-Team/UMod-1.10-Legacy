package net.hycrafthd.umod.ext;

import java.util.ArrayList;

import net.hycrafthd.umod.UMod;
import net.hycrafthd.umod.ext.abs.*;
import net.hycrafthd.umod.ext.enderio.EnderIOExtension;
import net.hycrafthd.umod.ext.ic2.IC2ModExtension;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.event.*;

public class ExtensionList {
	
	private static ArrayList<Extension> ex = new ArrayList<Extension>();
	
	public static void onStart(FMLPreInitializationEvent evt) {
		onInit();
		for (Extension e : ex) {
			for (ModContainer c : Loader.instance().getModList()) {
				try {
					if (c.getModId().equals(e.getId())) {
						e.init();
						e.getExtension().preinit(evt);
						break;
					}
				} catch (Throwable ex) {
					UMod.log.error("Exception During " + evt.getModState().toString() + " Phase loading " + e.getName(), ex);
				}
			}
		}
	}
	
	public static void onInit(FMLInitializationEvent ev) {
		for (Extension e : ex) {
			try {
				if (e.isLoaded()) {
					e.getExtension().init(ev);
				}
			} catch (Throwable ex) {
				UMod.log.error("Exception During " + ev.getModState().toString() + " Phase loading " + e.getName(), ex);
			}
		}
	}
	
	public static void onPostInit(FMLPostInitializationEvent ev) {
		for (Extension e : ex) {
			try {
				if (e.isLoaded()) {
					e.getExtension().postinit(ev);
				}
			} catch (Throwable ex) {
				UMod.log.error("Exception During " + ev.getModState().toString() + " Phase loading " + e.getName(), ex);
			}
		}
		for (Extension e : ex) {
			try {
				if (e.isLoaded()) {
					AbstractOreDictionaryRegistry oredic = e.getExtension().oredirctionary();
					if (oredic != null) {
						oredic.register();
					}
					
				}
			} catch (Throwable ex) {
				UMod.log.error("Exception During " + ev.getModState().toString() + " Phase loading " + e.getName(), ex);
			}
		}
		for (Extension e : ex) {
			try {
				if (e.isLoaded()) {
					AbstractRecipeRegistry recipe = e.getExtension().recipes();
					if (recipe != null) {
						recipe.register();
					}
				}
			} catch (Throwable ex) {
				UMod.log.error("Exception During " + ev.getModState().toString() + " Phase loading " + e.getName(), ex);
			}
		}
	}
	
	public static void onClientProxy() {
		for (Extension e : ex) {
			try {
				if (e.isLoaded()) {
					e.getExtension().clientRegistry();
				}
			} catch (Throwable ex) {
				UMod.log.error("Exception During ClientRegister Phase loading " + e.getName(), ex);
			}
		}
	}
	
	public static void onServer(FMLServerStartingEvent ev) {
		for (Extension e : ex) {
			try {
				if (e.isLoaded()) {
					e.getExtension().serverstarting(ev);
				}
			} catch (Throwable ex) {
				UMod.log.error("Exception During " + ev.getModState().toString() + " Phase loading " + e.getName(), ex);
			}
		}
	}
	
	private static void onInit() {
		// TODO Add Apis
		// ex.add(new Extension("Divex", "cdm", DiveceModExtension.class));
		ex.add(new Extension("IndustrialCraft 2 Extension", "IC2", IC2ModExtension.class));
		ex.add(new Extension("Ender IO Extension", "EnderIO", EnderIOExtension.class));
	}
	
}
