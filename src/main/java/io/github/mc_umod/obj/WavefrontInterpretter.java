package io.github.mc_umod.obj;

import java.io.*;
import java.net.*;
import java.util.*;

import io.github.mc_umod.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.util.math.*;

public class WavefrontInterpretter extends FileInputStream {
	
	private ArrayList<Vec3d> vert = new ArrayList<Vec3d>();
	private ArrayList<WavefrontField> area = new ArrayList<WavefrontField>();
	private ArrayList<MaterialInterpretter> mtls = new ArrayList<MaterialInterpretter>();
	
	public WavefrontInterpretter(File fl,String ModID) throws FileNotFoundException {
		super(fl);
		try {
			Scanner sc = new Scanner(this);
			Material mtl = new BaseMaterial();
			while (sc.hasNextLine()) {
				String stc = sc.nextLine();
				if (stc.startsWith("v ")) {
					String[] st = stc.replace("v ", "").split(" ");
					vert.add(new Vec3d(Double.valueOf(st[0]), Double.valueOf(st[1]), Double.valueOf(st[2])));
				}
				if (stc.startsWith("f ")) {
					area.add(new WavefrontField(mtl, stc.replace("f ", "").split(" ")));
				}
				if (stc.startsWith("mtllib ")) {
					mtls.add(new MaterialInterpretter(stc.replace("mtllib ", ""),ModID));
				}
				if (stc.startsWith("usemtl ")) {
					String s = stc.replace("usemtl ", "");
					for (MaterialInterpretter mt : mtls) {
						Material mv = mt.searchfor(s);
						if (mv != null) {
							mtl = mv;
							break;
						}
					}
				}
			}
			sc.close();
			this.close();
		} catch (IOException e1) {
			UMod.log.error("Error Loading Model " + fl.getName() + " (IO Failer)", e1);
		} catch (NumberFormatException ex) {
			UMod.log.error("Error Loading Model " + fl.getName() + " (Number Failer)", ex);
		} catch (ArrayIndexOutOfBoundsException e) {
			UMod.log.error("Error Loading Model " + fl.getName() + " (Array Failer)", e);
		} catch (URISyntaxException e) {
			UMod.log.error("Model " + fl.getName() + " has an incorrect URI", e);
		} catch (NullPointerException e) {
			UMod.log.error("Model " + fl.getName() + " has an Nullpointer", e);
		}
	}
	
	public void draw() {
		Tessellator ts = Tessellator.getInstance();
		VertexBuffer bf = ts.getBuffer();
		drawOnlyArea(bf);
		ts.draw();
		drawOnlyBoundingBox(bf);
		ts.draw();
	}
	
	public void drawOnlyArea(VertexBuffer bf) {
		bf.begin(7, DefaultVertexFormats.POSITION_COLOR);
		for (WavefrontField are : this.area) {
			are.addVertices(bf, this.vert);
		}
	}
	
	public void drawOnlyBoundingBox(VertexBuffer bf) {
		bf.begin(3, DefaultVertexFormats.POSITION_COLOR);
		for (WavefrontField are : this.area) {
			are.addVerticesToBounding(bf, this.vert);
		}
	}
}
