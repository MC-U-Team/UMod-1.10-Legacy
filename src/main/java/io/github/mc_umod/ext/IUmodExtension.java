package io.github.mc_umod.ext;

import io.github.mc_umod.ext.abs.AbstractOreDictionaryRegistry;
import io.github.mc_umod.ext.abs.AbstractRecipeRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public interface IUmodExtension {
	
	public void preinit(FMLPreInitializationEvent evt);
	
	public void init(FMLInitializationEvent evt);
	
	public void postinit(FMLPostInitializationEvent evt);
	
	public AbstractOreDictionaryRegistry oredirctionary();
	
	public AbstractRecipeRegistry recipes();
	
	public void serverstarting(FMLServerStartingEvent evt);
	
	public void clientRegistry();
	
}
