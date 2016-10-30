package net.hycrafthd.umod.obj;

import java.util.ArrayList;

import net.hycrafthd.corelib.util.RGBA;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.util.math.Vec3d;

public class ObjArea {
	
	public final int[] POINTS;
	
	public ObjArea(String... strings) {
		this.POINTS = new int[strings.length];
		int i = 0;
		for (String string : strings) {
			String[] dt = string.split("//");
			POINTS[i] = Integer.valueOf(dt[0]); 
			i++;
		}
	}

	public void addVertices(VertexBuffer bf,ArrayList<Vec3d> ver,RGBA c){
		for(int i : POINTS){
			Vec3d dro = ver.get(i - 1);
			bf.pos(dro.xCoord, dro.yCoord, dro.zCoord).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
		}
	}
}
