package net.hycrafthd.umod.ext.enderio;

import org.apache.logging.log4j.Logger;

import net.hycrafthd.umod.ext.IUmodExtension;
import net.minecraftforge.fml.common.event.*;

public class EnderIOExtension implements IUmodExtension{

	Logger lg;
	
	@Override
	public void preinit(FMLPreInitializationEvent evt) {
		lg = evt.getModLog();
	}

	@Override
	public void init(FMLInitializationEvent evt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postinit(FMLPostInitializationEvent evt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void serverstarting(FMLServerStartingEvent evt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clientRegistery() {
		// TODO Auto-generated method stub
		
	}
	
}
