package io.github.mc_umod.renderapi;

import java.io.*;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;

/**
 * Loading from different places
 * 
 * @author MrTroble
 *
 */

public class ResourceStream extends DataInputStream {
	
	public ResourceStream(File fl) throws FileNotFoundException {
		super(new FileInputStream(fl));
	}
	
	public ResourceStream(InputStream str) {
		super(str);
	}
	
	/**
	 * After {@link IResourceManager} is loaded
	 * 
	 * @param loc
	 * @throws IOException
	 */
	public ResourceStream(ResourceLocation loc) throws IOException {
		super(Minecraft.getMinecraft().getResourceManager().getResource(loc).getInputStream());
	}
	
}
