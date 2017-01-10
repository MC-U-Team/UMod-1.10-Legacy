package io.github.mc_umod.obj;

import java.io.*;
import java.net.*;

import io.github.mc_umod.*;
import net.minecraft.crash.*;
import net.minecraft.util.*;

public class WavefrontLoader {
	
	private WavefrontInterpretter pr;
	
	public WavefrontLoader(String str) {
		try {
			this.pr = new WavefrontInterpretter(new File(WavefrontLoader.class.getResource(str + ".obj").toURI()),UReference.modid);
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
	
	public WavefrontInterpretter getInterpretter() {
		return pr;
	}
	
	public void draw() {
		pr.draw();
	}
}
