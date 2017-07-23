package io.github.mc_umod.util;

import java.lang.reflect.Field;

import net.minecraft.client.renderer.EntityRenderer;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class RenderUtil {
	
	public int getRenderUpdateCount(EntityRenderer renderer) {
		try {
			Field field = renderer.getClass().getDeclaredField("rendererUpdateCount");
			field.setAccessible(true);
			return (int) field.get(renderer);
		} catch (Exception e) {
		}
		return 0;
	}
	
}
