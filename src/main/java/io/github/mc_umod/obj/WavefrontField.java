package io.github.mc_umod.obj;

import java.util.*;

import io.github.mc_umod.renderapi.*;
import net.minecraft.util.math.*;

/**
 * Storage for all points of an Polygon
 * 
 * @author MrTroble
 *
 */

public class WavefrontField {
	
	public final WavefrontVertex[] POINTS;
	public final Material mtl;
	
	public WavefrontField(Material mtl, String... strings) throws Exception {
		this.mtl = mtl;
		this.POINTS = new WavefrontVertex[strings.length];
		int i = 0;
		for (String string : strings) {
			this.POINTS[i] = new WavefrontVertex(string);
			i++;
		}
	}
	
	public void addVertices(RenderBuffer buffer,ArrayList<Vec3d> vertieces,ArrayList<Vec3d> vertex_texture_cords,ArrayList<Vec3d> vertex_normals) {
		    if(this.mtl.hasTexture())this.mtl.bindTex();
		    for (WavefrontVertex v : POINTS) {
				v.addVertex(this.mtl, buffer, vertieces, vertex_texture_cords, vertex_normals);
			}
	}
	
}
