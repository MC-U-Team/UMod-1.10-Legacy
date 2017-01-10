package io.github.mc_umod.obj;

import io.github.mc_umod.*;
import net.hycrafthd.corelib.util.*;
import net.minecraft.client.*;
import net.minecraft.util.*;

public class Material {
	
	public final String ID;
	private RGBA color;
	private ResourceLocation loc;
	
	public Material(String name) {
		this.ID = name;
	}
	
	public RGBA getColor() {
		return color;
	}
	
	public void setColor(RGBA c) {
		this.color = c;
	}
	
	public void setMap(String st) {
		this.loc = new ResourceLocation(UReference.modid, "textures/maps/" + st);
	}
	
	public boolean hasTexture() {
		return loc != null;
	}
	
	public boolean hasColor() {
		return color != null;
	}
	
	public void bindTex() {
		Minecraft.getMinecraft().getTextureManager().bindTexture(loc);
	}
	
	@Override
	public String toString() {
		return this.ID + " " + this.color.toColor().toString();
	}
}
