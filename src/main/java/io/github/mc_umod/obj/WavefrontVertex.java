package io.github.mc_umod.obj;

import java.nio.*;
import java.util.*;

import net.hycrafthd.corelib.util.*;
import net.minecraft.client.renderer.*;
import net.minecraft.util.math.*;

public class WavefrontVertex {

	private int VERTEX = 0, VERTEX_NORMAL = 0,VERTEX_TEXTURE = 0;
	
	public WavefrontVertex(String info) {
		String[] dt;
		if(info.contains("//")){
			dt = info.split("//");
			this.VERTEX = Integer.valueOf(dt[0]);
			if(dt.length > 1){
				this.VERTEX_NORMAL = Integer.valueOf(dt[1]);
			}
		}else if(info.contains("/")){
			dt = info.split("/");
			this.VERTEX = Integer.valueOf(dt[0]);
			if(dt.length > 1){
				this.VERTEX_TEXTURE = Integer.valueOf(dt[1]);
			}
			if(dt.length > 2){
				this.VERTEX_NORMAL = Integer.valueOf(dt[2]);
			}
		}else{
			this.VERTEX = Integer.valueOf(info);
		}
	}
	
	public void addVertex(Material mat,WaveFrontBuffer buffer,ArrayList<Vec3d> vertieces,ArrayList<Vec3d> vertex_texture_cords,ArrayList<Vec3d> vertex_normals){
		if(this.VERTEX <= 0)return;
		int ver = buffer.addVertex(vertieces.get(this.VERTEX - 1));
		
		if(this.VERTEX_TEXTURE > 0){
			Vec3d texture = vertex_texture_cords.get(this.VERTEX_TEXTURE - 1);
			buffer.addUV(texture.xCoord,texture.yCoord, ver);
		}
		
		if(this.VERTEX_NORMAL > 0){
			Vec3d normal = vertex_normals.get(this.VERTEX_NORMAL - 1);
			buffer.addNormal(normal, ver);
		}
		
		if(mat.hasColor()){
			RGBA rgb = mat.getColor();
			buffer.addColor(rgb, ver);
		}
	}
	
	@Override
	public String toString() {
		return "VERTEX NUMBER: " + this.VERTEX;
	}
}
