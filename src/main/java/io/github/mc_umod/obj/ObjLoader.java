package io.github.mc_umod.obj;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import io.github.mc_umod.UMod;
import net.minecraft.crash.CrashReport;
import net.minecraft.util.ReportedException;

public class ObjLoader {
	
	private ObjInterpretter pr;
	
	public ObjLoader(String str) {
		try {
			this.pr = new ObjInterpretter(new File(ObjLoader.class.getResource(str + ".obj").toURI()));
		} catch (FileNotFoundException e) {
			UMod.log.error("Model " + str + " was not found", e);
			throw new ReportedException(new CrashReport("MODEL LODE ERROR " + str, e));
		} catch (URISyntaxException e) {
			UMod.log.error("Model " + str + " has an incorrect URI", e);
			throw new ReportedException(new CrashReport("MODEL LODE ERROR " + str, e));
		} catch (NullPointerException e) {
			UMod.log.error("Model " + str + " was not found", e);
			throw new ReportedException(new CrashReport("MODEL LODE ERROR " + str, e));
		} catch(Throwable e){
			throw new ReportedException(new CrashReport("MODEL LODE ERROR " + str, e));
		}
		
	}
	
	public ObjInterpretter getInterpretter() {
		return pr;
	}
	
	public void draw() {
		pr.draw();
	}
}
