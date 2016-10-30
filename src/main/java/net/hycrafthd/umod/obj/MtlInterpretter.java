package net.hycrafthd.umod.obj;

import java.io.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

import org.lwjgl.util.Color;

import net.hycrafthd.corelib.util.RGBA;

public class MtlInterpretter extends FileInputStream {
	
	
	private HashMap<String, Material> mtls = new HashMap<String, Material>();
	
	public MtlInterpretter(String name) throws FileNotFoundException, URISyntaxException {
		super(new File(MtlInterpretter.class.getResource(name).toURI()));
		try {
			Scanner sc = new Scanner(this);
			Material mtl = null;
			while (sc.hasNextLine()) {
				String st = sc.nextLine();
				if (st.startsWith("newmtl ")) {
					if (mtl != null) {
						mtls.put(mtl.ID, mtl);
					}
					mtl = new Material(st.replace("newmtl ", ""));
				}
				if (st.startsWith("Kd ")) {
					String[] rgb = st.replace("Kd ", "").split(" ");
					mtl.setColor(new RGBA(new Color((int) Math.round((double) 255 * Double.valueOf(rgb[0])), (int) Math.round((double) 255 * Double.valueOf(rgb[1])), (int) Math.round((double) 255 * Double.valueOf(rgb[2])))));
				}
				if (st.startsWith("d ")) {
					double d = Double.valueOf(st.replace("d ", ""));
					mtl.setColor(mtl.getColor().setAlpha((int) Math.round((double) 255 * d)));
				}
				if(st.startsWith("map_Kd ")){
					mtl.setMap(st.replace("map_Kd ", ""));
				}
			}
			if (mtl != null) {
				mtls.put(mtl.ID, mtl);
			}
			sc.close();
			this.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Material searchfor(String nm) {
		return mtls.get(nm);
	}
	
}
