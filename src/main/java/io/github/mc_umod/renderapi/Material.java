package io.github.mc_umod.renderapi;

import net.hycrafthd.corelib.util.*;
import net.minecraft.client.*;
import net.minecraft.util.*;

/**
 * Just a Storage class
 * 
 * @author MrTroble
 *
 */

public class Material {
	
	public final String ID;
	private RGBA color;
	private double width = 0,height = 0;
	private int glTextureId = -1;
	private ResourceLocation texture;
	
	public Material(String name) {
		this.ID = name;
	}
	
	public RGBA getColor() {
		return color;
	}
	
	public void setColor(RGBA c) {
		this.color = c;
	}
	
	public void setMap(final String st) {
		this.texture = new MapResource(st);
	}
	
	public boolean hasTexture() {
		return texture != null;
	}
	
	public boolean hasColor() {
		return color != null;
	}
	
	public void bindTex() {
		Minecraft.getMinecraft().getTextureManager().bindTexture(this.texture);
	}
	
	@Override
	public String toString() {
		return this.ID + " " + this.color.toColor().toString();
	}
	
}
