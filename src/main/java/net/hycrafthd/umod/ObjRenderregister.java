package net.hycrafthd.umod;

import net.hycrafthd.umod.obj.ObjLoader;

public class ObjRenderregister {
	
	
	public static ObjLoader TEST;
	
	public ObjRenderregister() {
		this.reload();
	}
	
	public void reload() {
		TEST = new ObjLoader("test");
	}
}
