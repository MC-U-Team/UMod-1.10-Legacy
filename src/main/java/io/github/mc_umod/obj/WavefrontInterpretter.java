package io.github.mc_umod.obj;

import java.io.*;
import java.net.*;
import java.util.*;

import static org.lwjgl.opengl.GL11.*;

import io.github.mc_umod.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;

public class WavefrontInterpretter extends FileInputStream {
	
	private WaveFrontBuffer buffer;
	private ArrayList<Vec3d> 
	        vertex = new ArrayList<Vec3d>(),
			vertexTexture = new ArrayList<Vec3d>(),
	        vertexNormal = new ArrayList<Vec3d>();
	private ArrayList<WavefrontField> area = new ArrayList<WavefrontField>();
	private ArrayList<MaterialInterpretter> mtls = new ArrayList<MaterialInterpretter>();
	
	public WavefrontInterpretter(File fl,String ModID) throws Exception {
		super(fl);
		this.buffer = new WaveFrontBuffer();
		try {
			Scanner sc = new Scanner(this);
			Material mtl = new BaseMaterial();
			while (sc.hasNextLine()) {
				String stc = sc.nextLine();
				if (stc.startsWith("vt ")) {
					String[] st = stc.replace("vt ", "").split(" ");
					vertexTexture.add(new Vec3d(Double.valueOf(st[0]), Double.valueOf(st[1]), Double.valueOf(st[2])));
				}
				if (stc.startsWith("vn ")) {
					String[] st = stc.replace("vn ", "").split(" ");
					vertexNormal.add(new Vec3d(Double.valueOf(st[0]), Double.valueOf(st[1]), Double.valueOf(st[2])));
				}
				if (stc.startsWith("v ")) {
					String[] st = stc.replace("v ", "").split(" ");
					vertex.add(new Vec3d(Double.valueOf(st[0]), Double.valueOf(st[1]), Double.valueOf(st[2])));
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
				if(stc.startsWith("# ")){
					UMod.log.debug(stc);
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
	
	public void draw(VertexFormat form) {
		for (WavefrontField are : this.area) {
			are.addVertices(this.buffer, this.vertex, this.vertexTexture, this.vertexNormal);
			glBegin(GL_POLYGON);
			this.buffer.draw();
			glEnd();
			this.buffer.clear();
		}
	}
}
