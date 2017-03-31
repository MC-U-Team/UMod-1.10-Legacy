package io.github.mc_umod;

import java.util.*;

import io.github.mc_umod.api.energy.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
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
