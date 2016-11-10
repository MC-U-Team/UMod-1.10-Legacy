package net.hycrafthd.umod;

import net.hycrafthd.umod.obj.ObjLoader;

public class ObjRenderRegistry {
	
	public static ObjLoader TEST;
	public static ObjLoader GENERATOR;
	
	public ObjRenderRegistry() {
		this.reload();
	}
	
	public void reload() {
		TEST = new ObjLoader("test");
		GENERATOR = new ObjLoader("generator");
	}
}
