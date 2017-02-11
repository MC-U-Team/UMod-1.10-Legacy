package io.github.mc_umod.wavefront.assets;

import net.minecraft.client.resources.*;

public class ObjRenderRegistry {
	
	public WavefrontLoader GENERATOR;
		
	public void reload() {
		GENERATOR = new WavefrontLoader("generator");
	}
}
