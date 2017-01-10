package io.github.mc_umod.render;

import io.github.mc_umod.api.*;
import io.github.mc_umod.block.machine.*;
import net.minecraft.block.*;
import net.minecraft.client.renderer.*;
import net.minecraft.entity.player.*;
import net.minecraft.tileentity.*;

public class ConduitRender {
	
	public final GLHelper help;
	public final ModelRenderHelper model;
	
	public ConduitRender(GLHelper help,ModelRenderHelper model) {
		this.help = help;
        this.model = model;
	}
	
	public boolean render(TileEntity tile,EntityPlayer pl, double posX, double posY, double posZ) {
		if (!(tile instanceof IConduitProvider))
			return false;
		if (tile != null && (!((IConduitProvider) tile).hasConduit() || (pl.getHeldItemMainhand() != null && Block.getBlockFromItem(pl.getHeldItemMainhand().getItem()) != null && Block.getBlockFromItem(pl.getHeldItemMainhand().getItem()) instanceof IConduitBlock))) {
			return false;
		} else if (tile != null) {
			if (pl.getHeldItemMainhand() == null || !(Block.getBlockFromItem(pl.getHeldItemMainhand().getItem()) instanceof BlockCable)) {
				GlStateManager.enableLighting();
				this.model.renderConduit(Block.getBlockFromItem(((IConduitProvider) tile).getConduit().getItem()), posX, posY, posZ);
				GlStateManager.disableLighting();
				return true;
			}
		}
		return false;
	}
	
}
