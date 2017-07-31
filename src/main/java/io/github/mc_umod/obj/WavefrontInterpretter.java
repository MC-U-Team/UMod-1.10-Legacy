package io.github.mc_umod.obj;

import static org.lwjgl.opengl.GL11.*;

import java.io.*;
import java.net.URISyntaxException;
import java.util.*;

import org.apache.logging.log4j.*;

import io.github.mc_umod.renderapi.*;
import io.github.mc_umod.renderapi.draw.Vec2d;
import net.minecraft.util.math.Vec3d;

/**
 * Reads and parses the given .obj File
 * 
 * @author MrTroble
 *
 */

public class WavefrontInterpretter extends DataInputStream {
	
	private Logger LOG = LogManager.getLogger();
	private RenderBuffer buffer;
	private ArrayList<Vec3d> vertex = new ArrayList<Vec3d>(), vertexNormal = new ArrayList<Vec3d>();
	private ArrayList<Vec2d> vertexTexture = new ArrayList<Vec2d>();
	private ArrayList<WavefrontField> area = new ArrayList<WavefrontField>();
	private ArrayList<MaterialInterpretter> mtls = new ArrayList<MaterialInterpretter>();
	
	public WavefrontInterpretter(ResourceStream str, String ModID) throws Exception {
		super(str);
		this.buffer = new RenderBuffer();
		try {
			Scanner sc = new Scanner(this);
			Material mtl = new BaseMaterial();
			while (sc.hasNextLine()) {
				String stc = sc.nextLine();
				if (stc.startsWith("vt ")) {
					String[] st = stc.replace("vt ", "").split(" ");
					vertexTexture.add(new Vec2d(Double.valueOf(st[0]), Double.valueOf(st[1])));
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
					mtls.add(new MaterialInterpretter(new MapResource(ModID, stc.replace("mtllib ", "")), ModID));
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
				if (stc.startsWith("# ")) {
					LOG.debug(stc);
				}
			}
			sc.close();
			this.close();
		} catch (IOException e1) {
			LOG.error("Error loading model (IO failer)", e1);
		} catch (NumberFormatException ex) {
			LOG.error("Error loading model (Number failer)", ex);
		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.error("Error loading model (Array failer)", e);
		} catch (URISyntaxException e) {
			LOG.error("Model has an incorrect URI", e);
		} catch (Throwable th) {
			LOG.error("Model load error", th);
		}
	}
	
	public void draw() {
		try {
			for (WavefrontField are : this.area) {
				are.addVertices(this.buffer, this.vertex, this.vertexTexture, this.vertexNormal);
				glBegin(GL_POLYGON);
				this.buffer.draw();
				glEnd();
				this.buffer.clear();

			}
		} catch (Throwable th) {
			LOG.error("Model render error", th);
		}
	}
}
