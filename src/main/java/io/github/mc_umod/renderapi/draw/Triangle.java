package io.github.mc_umod.renderapi.draw;

import io.github.mc_umod.renderapi.Vertex;
import io.github.mc_umod.util.RGBA;
import net.minecraft.util.math.Vec3d;

public class Triangle extends Pylogen{
	
	public Triangle(Vec3d vec1,Vec3d vec2,Vec3d vec3,RGBA rgba) {
		super(new Vertex(vec1, rgba),
			  new Vertex(vec2, rgba),
			  new Vertex(vec3, rgba));
	}
	
	public Triangle(Vertex vec1,Vertex vec2,Vertex vec3) {
		super(vec1,vec2,vec3);
	}
}
