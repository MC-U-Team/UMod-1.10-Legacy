package io.github.mc_umod.renderapi;

import static org.lwjgl.opengl.GL11.*;

import net.hycrafthd.corelib.util.*;
import net.minecraft.util.math.*;

/**
 * Storage and draw class for each vertex
 * 
 * @author MrTroble
 *
 */

public class Vertex extends Vec3d{

	private RGBA rgb;
	private Vec3d normal;
	private double u, v;
	private boolean uv = false;
	
	public Vertex(double x, double y, double z) {
		super(x, y, z);
	}
	
	public Vertex(Vec3d v) {
		super(v.xCoord, v.yCoord, v.zCoord);
	}
	
	public void setCollor(RGBA rgb){
		this.rgb = rgb;
	}
	
	public RGBA getCollor(){
		return this.rgb;
	}
	
	public Vec3d getNormal(){
		return normal;
	}
	
	public void setNormal(Vec3d d){
		this.normal = d;
	}
	
	public double getU(){
		return u;
	}
	
	public double getV(){
		return v;
	}
	
	public void setUV(double u,double v){
		this.u = u;
		this.v = v;
		this.uv = true;
	}
	
	public void draw(){
		if(rgb != null)glColor4d((double)rgb.getRed() / 255, (double)rgb.getGreen() / 255, (double)rgb.getBlue() / 255, (double)rgb.getAlpha() / 255);
		if(normal != null)glNormal3d(this.normal.xCoord, this.normal.yCoord, this.normal.zCoord);
		if(this.uv)glTexCoord2d(this.u, 1 - this.v);
		glVertex3d(this.xCoord, this.yCoord, this.zCoord);
	}

}
