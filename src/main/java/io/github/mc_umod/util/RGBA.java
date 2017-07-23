package io.github.mc_umod.util;

import org.lwjgl.util.Color;

public class RGBA {
	
	private int red;
	private int green;
	private int blue;
	private int alpha;
	
	public static final RGBA NULL = new RGBA(0, 0, 0, 0);
	
	public RGBA(int r, int g, int b, int a) {
		red = r;
		green = g;
		blue = b;
		alpha = a;
	}
	
	public RGBA(Color cl) {
		this(cl.getRed(), cl.getGreen(), cl.getBlue(), cl.getAlpha());
	}
	
	public RGBA(java.awt.Color cl) {
		this(cl.getRed(), cl.getGreen(), cl.getBlue(), cl.getAlpha());
	}
	
	public int getRed() {
		return red;
	}
	
	public int getGreen() {
		return green;
	}
	
	public int getBlue() {
		return blue;
	}
	
	public int getAlpha() {
		return alpha;
	}
	
	public RGBA setAlpha(int i) {
		alpha = i;
		return this;
	}
	
	public java.awt.Color toAWTColor() {
		return new java.awt.Color(red, green, blue, alpha);
	}
	
	public Color toColor() {
		return new Color(red, green, blue, alpha);
	}
}
