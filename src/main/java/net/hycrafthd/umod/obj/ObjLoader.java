package net.hycrafthd.umod.obj;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public class ObjLoader {

	
	private ObjInterpretter pr;
	
	public ObjLoader(String str) {
		try {
			this.pr = new ObjInterpretter(new File(ObjLoader.class.getResource(str + ".obj").toURI()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	public ObjInterpretter getInterpretter(){
		return pr;
	}
}
