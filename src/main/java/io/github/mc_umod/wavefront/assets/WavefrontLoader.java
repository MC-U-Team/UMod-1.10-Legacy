package io.github.mc_umod.wavefront.assets;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import io.github.mc_umod.*;
import io.github.mc_umod.obj.WavefrontInterpretter;
import io.github.mc_umod.renderapi.*;
import net.minecraft.crash.CrashReport;
import net.minecraft.util.ReportedException;

public class WavefrontLoader {
	
	private WavefrontInterpretter pr;
	
	public WavefrontLoader(String str) {
		try {
			this.pr = new WavefrontInterpretter(new ResourceStream(new MapResource(UReference.modid,str + ".obj")),UReference.modid);
		} catch (FileNotFoundException e) {
			UMod.log.error("Model " + str + " was not found", e);
			throw new ReportedException(new CrashReport("MODEL LOAD ERROR " + str, e));
		} catch (URISyntaxException e) {
			UMod.log.error("Model " + str + " has an incorrect URI", e);
			throw new ReportedException(new CrashReport("MODEL LOAD ERROR " + str, e));
		} catch (NullPointerException e) {
			UMod.log.error("Model " + str + " was not found", e);
			throw new ReportedException(new CrashReport("MODEL LOAD ERROR " + str, e));
		} catch(Throwable e){
			throw new ReportedException(new CrashReport("MODEL LOAD ERROR " + str, e));
		}
		
	}
	
	public WavefrontInterpretter getInterpretter() {
		return pr;
	}
	
	public void draw() {
		pr.draw();
	}
}
