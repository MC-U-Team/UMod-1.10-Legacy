package net.hycrafthd.umod.obj;

import net.hycrafthd.corelib.util.RGBA;

public class Material {

	public final String ID;
	private RGBA color;
	
	public Material(String name) {
		this.ID = name;
	}
	
	public RGBA getColor(){
		return color;
	}
	
	public void setColor(RGBA c){
		this.color = c;
	}
	
	@Override
	public String toString() {
		return this.ID + " " + this.color.toColor().toString();
	}
}
