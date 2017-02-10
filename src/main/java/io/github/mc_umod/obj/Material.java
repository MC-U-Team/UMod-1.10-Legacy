package io.github.mc_umod.obj;

import java.awt.image.*;
import java.io.*;
import java.nio.*;

import javax.imageio.*;

import org.apache.commons.io.*;
import org.lwjgl.*;
import org.lwjgl.opengl.*;

import static org.lwjgl.opengl.GL11.*;

import net.hycrafthd.corelib.util.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.client.resources.*;
import net.minecraft.client.resources.data.*;
import net.minecraft.util.*;

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
