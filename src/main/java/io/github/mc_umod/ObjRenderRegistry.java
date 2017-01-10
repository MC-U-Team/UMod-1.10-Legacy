package io.github.mc_umod;

import io.github.mc_umod.obj.*;

public class ObjRenderRegistry {
	
	public WavefrontLoader TEST;
	public WavefrontLoader GENERATOR;
	
	public ObjRenderRegistry() {
		this.reload();
	}
	
	public void reload() {
		TEST = new WavefrontLoader("test");
		GENERATOR = new WavefrontLoader("generator");
	}
}
