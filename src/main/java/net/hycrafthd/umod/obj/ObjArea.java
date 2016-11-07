package net.hycrafthd.umod.obj;

import java.awt.Color;
import java.util.ArrayList;

import net.hycrafthd.corelib.util.RGBA;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.Vec3d;

public class ObjArea {
	
	public final int[] POINTS;
	public final Material mtl;
	
	public ObjArea(Material mtl, String... strings) {
		this.mtl = mtl;
		this.POINTS = new int[strings.length];
		int i = 0;
		for (String string : strings) {
			String[] dt = string.split("//");
			POINTS[i] = Integer.valueOf(dt[0]);
			i++;
		}
	}
	
	public void addVertices(VertexBuffer bf, ArrayList<Vec3d> ver) {
		if (!mtl.hasTexture()) {
			RGBA c = mtl.getColor();
			for (int i : POINTS) {
				Vec3d dro = ver.get(i - 1);
				bf.pos(dro.xCoord, dro.yCoord, dro.zCoord).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
			}
		} else {
			GlStateManager.enableTexture2D();
			Tessellator.getInstance().draw();
			bf.begin(7, DefaultVertexFormats.POSITION_TEX);
			mtl.bindTex();
			int x = 0;
			for (int i : POINTS) {
				Vec3d dro = ver.get(i - 1);
				double u = 0, v = 0;
				switch (x) {
				case 1:
					u = 1;
					break;
				case 2:
					v = 1;
					break;
				case 3:
					v = 1;
					u = 1;
					break;
				}
				bf.pos(dro.xCoord, dro.yCoord, dro.zCoord).tex(u, v).endVertex();
				x++;
			}
			GlStateManager.disableTexture2D();
			Tessellator.getInstance().draw();
			bf.begin(7, DefaultVertexFormats.POSITION_COLOR);
		}
	}
	
	public void addVerticesToBounding(VertexBuffer bf, ArrayList<Vec3d> ver) {
		RGBA c = new RGBA(Color.BLACK);
		for (int i : POINTS) {
			Vec3d dro = ver.get(i - 1);
			final double d = 0.00001;
			double x = dro.xCoord, y = dro.yCoord + 0.01, z = dro.zCoord + 0.01;
			if (x < 0) {
				x -= d;
			} else {
				x += d;
			}
			if (y < 0) {
				y -= d;
			} else {
				y += d;
			}
			if (z < 0) {
				z -= d;
			} else {
				z += d;
			}
			bf.pos(x, y, z).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
		}
		
	}
}
