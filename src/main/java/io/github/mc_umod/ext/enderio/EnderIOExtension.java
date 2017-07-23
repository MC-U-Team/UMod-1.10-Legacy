package io.github.mc_umod.ext.enderio;

import org.apache.logging.log4j.Logger;

import io.github.mc_umod.ext.IUmodExtension;
import io.github.mc_umod.ext.abs.*;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.event.*;

public class EnderIOExtension implements IUmodExtension {
	
	public static Logger lg;
	public static Block transformer;
	//public CommonRegistry reg;
	
	@Override
	public void preinit(FMLPreInitializationEvent evt) {
		lg = evt.getModLog();
	}
	
	@Override
	public void init(FMLInitializationEvent evt) {
		transformer = new BlockEnderIOTransformer().setUnlocalizedName("enderiotransformer");
		//this.reg.registerBlock(transformer, "enderiotransformer");
	}
	
	@Override
	public void postinit(FMLPostInitializationEvent evt) {
		//this.reg.registerTileEntity(TileEntityEnderIOTransformer.class,"enderiotrafo");
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
