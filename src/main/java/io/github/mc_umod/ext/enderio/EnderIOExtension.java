package io.github.mc_umod.ext.enderio;

import org.apache.logging.log4j.*;

import io.github.mc_umod.ext.*;
import io.github.mc_umod.ext.abs.*;
import net.hycrafthd.corelib.registry.*;
import net.minecraft.block.*;
import net.minecraftforge.fml.common.event.*;

public class EnderIOExtension implements IUmodExtension {
	
	public static Logger lg;
	public static Block transformer;
	
	@Override
	public void preinit(FMLPreInitializationEvent evt) {
		lg = evt.getModLog();
	}
	
	@Override
	public void init(FMLInitializationEvent evt) {
		transformer = new BlockEnderIOTransformer().setUnlocalizedName("enderiotransformer");
		BlockRegistry.register(transformer, "enderiotransformer");
	}
	
	@Override
	public void postinit(FMLPostInitializationEvent evt) {
		TileEntityRegistry.register(TileEntityEnderIOTransformer.class);
	}
	
	@Override
	public void serverstarting(FMLServerStartingEvent evt) {
		// TODO Auto-generated method stub
		
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
	public void clientRegistry() {
		// TODO Auto-generated method stub
		
	}
}
