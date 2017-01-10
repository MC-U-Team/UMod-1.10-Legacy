package io.github.mc_umod;

import io.github.mc_umod.obj.*;

public class ObjRenderRegistry {
	
	public ObjLoader TEST;
	public ObjLoader GENERATOR;
	
	public ObjRenderRegistry() {
		this.reload();
	}
	
	public void reload() {
		TEST = new ObjLoader("test");
		GENERATOR = new ObjLoader("generator");
	}
}
