package io.github.mc_umod;

import io.github.mc_umod.api.energy.TunnelHolder;
import net.minecraftforge.fml.relauncher.*;

public class CommonProxy {
	
	private TunnelHolder holder;
	
	public void init(){
		this.holder = new TunnelHolder();
	}
	
	// Client Stuff
	public void registerModels() {}
	
	// Client Stuff
	public void registerRenderer() {}
	
	@SideOnly(Side.SERVER)
	public TunnelHolder getTunnelHolder(){
		return this.holder;
	}
}
