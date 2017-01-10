package io.github.mc_umod.ext;

import io.github.mc_umod.ext.abs.*;
import net.minecraftforge.fml.common.event.*;

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
