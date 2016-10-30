package net.hycrafthd.umod.obj;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import net.hycrafthd.corelib.util.RGBA;
import net.hycrafthd.umod.UMod;
import net.minecraft.client.renderer.Tessellator;

public class ObjLoader {

	
	private ObjInterpretter pr;
	
	public ObjLoader(String str) {
		try {
			this.pr = new ObjInterpretter(new File(ObjLoader.class.getResource(str + ".obj").toURI()));
		} catch (FileNotFoundException e) {
			UMod.log.error("Model " + str + " was not found", e);
		} catch (URISyntaxException e) {
			UMod.log.error("Model " + str + " has an incorrect URI", e);
		} catch (NullPointerException e){
			UMod.log.error("Model " + str + " was not found", e);
		}
	}
	
	public ObjInterpretter getInterpretter(){
		return pr;
	}
	
	public void draw(){
		pr.draw();
	}
}
