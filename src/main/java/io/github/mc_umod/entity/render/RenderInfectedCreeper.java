package io.github.mc_umod.entity.render;

import io.github.mc_umod.*;
import io.github.mc_umod.entity.*;
import io.github.mc_umod.entity.model.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.util.*;
import net.minecraftforge.fml.client.registry.*;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class RenderInfectedCreeper extends RenderLiving<EntityInfectedCreeper> implements IRenderFactory<EntityInfectedCreeper> {
	
	public RenderInfectedCreeper(RenderManager r) {
		super(r, new ModelInfectedCreeper(), 0);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityInfectedCreeper entity) {
		return new ResourceLocation(UReference.resource + "textures/entity/InfectedCreeper.png");
	}
	
	@Override
	public Render<? super EntityInfectedCreeper> createRenderFor(RenderManager manager) {
		return new RenderInfectedCreeper(manager);
	}
	
}
