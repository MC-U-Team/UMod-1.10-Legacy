package io.github.mc_umod.renderapi;

import java.util.ArrayList;

import io.github.mc_umod.renderapi.draw.Vec2d;
import io.github.mc_umod.util.RGBA;
import net.minecraft.util.math.Vec3d;

/**
 * Vertex buffer and editor
 * 
 * @author MrTroble
 *
 */

public class RenderBuffer {
	
	public final ArrayList<Vertex> vec = new ArrayList<Vertex>();
	
	public int addVertex(Vec3d vec, RGBA rgb) {
		int i = addVertex(vec);
		addColor(rgb, i);
		return i;
	}
	
	public int addVertex(Vec3d vec, Vec2d uv) {
		int i = addVertex(vec);
		this.addUV(uv, i);
		return i;
	}
	
	public int addVertex(Vec3d vec) {
		int i = this.vec.size();
		this.vec.add(new Vertex(vec));
		return i;
	}
	
	public void addColor(RGBA rgb, int pos) {
		this.vec.get(pos).setColor(rgb);
	}
	
	public void addNormal(Vec3d vec, int pos) {
		this.vec.get(pos).setNormal(vec);
	}
	
	public void addUV(Vec2d uv, int pos) {
		this.vec.get(pos).setUV(uv);
	}
	
	public void draw() {
		for (Vertex v : this.vec) {
			v.drawNormal();
		}
	}
	
	public void clear() {
		this.vec.clear();
	}
}
