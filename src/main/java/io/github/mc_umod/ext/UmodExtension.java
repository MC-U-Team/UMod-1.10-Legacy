package io.github.mc_umod.ext;

import io.github.mc_umod.ext.abs.AbstractOreDictionaryRegistry;
import io.github.mc_umod.ext.abs.AbstractRecipeRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public abstract class UmodExtension implements IUmodExtension {
	
	@Override
	public void preinit(FMLPreInitializationEvent evt) {
		
	}
	
	@Override
	public void init(FMLInitializationEvent evt) {
		
	}
	
	@Override
	public void postinit(FMLPostInitializationEvent evt) {
		
	}
	
	@Override
	public AbstractOreDictionaryRegistry oredirctionary() {
		return null;
	}
	
	@Override
	public AbstractRecipeRegistry recipes() {
		return null;
	}
	
	@Override
	public void serverstarting(FMLServerStartingEvent evt) {
		
	}
	
	@Override
	public void clientRegistry() {
		
	}
	
}
