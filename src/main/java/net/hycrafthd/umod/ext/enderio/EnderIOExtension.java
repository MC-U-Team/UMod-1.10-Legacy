package net.hycrafthd.umod.ext.enderio;

import org.apache.logging.log4j.Logger;

import net.hycrafthd.corelib.registry.BlockRegistry;
import net.hycrafthd.corelib.registry.TileEntityRegistry;
import net.hycrafthd.umod.ext.IUmodExtension;
import net.hycrafthd.umod.ext.abs.AbstractOreDictionaryRegistry;
import net.hycrafthd.umod.ext.abs.AbstractRecipeRegistry;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.event.*;

public class EnderIOExtension implements IUmodExtension{

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
