package io.github.mc_umod.obj;

import java.awt.image.*;
import java.io.*;

import org.apache.commons.io.*;

import net.minecraft.client.renderer.texture.*;
import net.minecraft.client.resources.*;
import net.minecraft.client.resources.data.*;

/**
 * DON'T USE THIS CLASS FOR NOW USE {@link MapResource} INSTATE<br>
 * @author MrTroble
 */

@Deprecated
public class Texture extends AbstractTexture{

	public final String name;
	
	@Deprecated
	public Texture(String name) {
		this.name = name;
	}

	@Deprecated
	@Override
	public void loadTexture(IResourceManager resourceManager) throws IOException {
		this.deleteGlTexture();
        try
        {
            BufferedImage bufferedimage = TextureUtil.readBufferedImage(Texture.class.getResourceAsStream(this.name));
            TextureUtil.uploadTextureImageAllocate(this.getGlTextureId(), bufferedimage, false, false);
        }
        finally{}
	}

}
