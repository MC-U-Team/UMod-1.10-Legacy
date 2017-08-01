package io.github.mc_umod.renderapi;

import static org.lwjgl.opengl.GL11.*;

import io.github.mc_umod.renderapi.draw.Vec2d;
import io.github.mc_umod.util.RGBA;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.util.math.Vec3d;

/**
 * Storage and draw class for each vertex
 * 
 * @author MrTroble
 *
 */

public class Vertex extends Vec3 {
	
	private RGBA rgba;
	private Vec3d normal;
	private Vec2d tex;
		
	public Vertex(Vec3d vec,RGBA rgba) {
		super(vec.xCoord, vec.yCoord, vec.zCoord);
		this.rgba = rgba;
	}
	
	public Vertex(Vec3d vec,RGBA rgba,Vec2d tex) {
		super(vec.xCoord, vec.yCoord, vec.zCoord);
		this.rgba = rgba;
		this.tex = tex;
	}
	
	public Vertex(Vec3d vec,Vec2d tex) {
		super(vec.xCoord, vec.yCoord, vec.zCoord);
		this.tex = tex;
	}

	public Vertex(Vec3d vec,RGBA rgba,Vec2d tex,Vec3d normal) {
		super(vec.xCoord, vec.yCoord, vec.zCoord);
		this.rgba = rgba;
		this.tex = tex;
		this.normal = normal;
	}
	
	public Vertex(Vec3d vec,Vec2d tex,Vec3d normal) {
		super(vec.xCoord, vec.yCoord, vec.zCoord);
		this.tex = tex;
		this.normal = normal;
	}

	
	public Vertex(Vec3d v) {
		super(v.xCoord, v.yCoord, v.zCoord);
	}
	
	public void setColor(RGBA rgb) {
		this.rgba = rgb;
	}
	
	public RGBA getColor() {
		return this.rgba;
	}
	
	public Vec3d getNormal() {
		return normal;
	}
	
	public void setNormal(Vec3d d) {
		this.normal = d;
	}
	
	public Vec2d getUV() {
		return this.tex;
	}
	
	public void setUV(Vec2d uv) {
		this.tex = uv;
	}
	
	public void drawNormal() {
		if (this.rgba != null)
			glColor4d((double) rgba.getRed() / 255, (double) rgba.getGreen() / 255, (double) rgba.getBlue() / 255, (double) rgba.getAlpha() / 255);
		if (this.normal != null)
			glNormal3d(this.normal.xCoord, this.normal.yCoord, this.normal.zCoord);
		if (this.tex != null)
			glTexCoord2d(this.tex.xCoord, 1 - this.tex.xCoord);
		glVertex3d(this.X(), this.Y(), this.Z());
	}
	
	public void draw(VertexBuffer buffer){
		buffer.pos(X(), Y(), Z());
		if (this.rgba != null)
			buffer.color((float) rgba.getRed() / 255, (float) rgba.getGreen() / 255, (float) rgba.getBlue() / 255, (float) rgba.getAlpha() / 255);
		if (this.normal != null)
			buffer.normal((float)this.normal.xCoord, (float)this.normal.yCoord, (float)this.normal.zCoord);
		if (this.tex != null)
			buffer.tex(this.tex.xCoord, this.tex.yCoord);
		buffer.endVertex();
	}
	
}
