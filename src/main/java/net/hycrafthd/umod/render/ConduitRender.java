package net.hycrafthd.umod.render;

import net.hycrafthd.umod.api.*;
import net.hycrafthd.umod.block.BlockCable;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

public class ConduitRender {
	
	
	public static boolean render(TileEntity pip, EntityPlayer pl, double posX, double posY, double posZ) {
		if (!(pip instanceof IConduitProvider))
			return false;
		if (pip != null && (!((IConduitProvider) pip).hasConduit() || (pl.getHeldItemMainhand() != null && Block.getBlockFromItem(pl.getHeldItemMainhand().getItem()) != null && Block.getBlockFromItem(pl.getHeldItemMainhand().getItem()) instanceof IConduitBlock))) {
			return false;
		} else if (pip != null) {
			if (pl.getHeldItemMainhand() == null || !(Block.getBlockFromItem(pl.getHeldItemMainhand().getItem()) instanceof BlockCable)) {
				GlStateManager.enableLighting();
				// IMPL_LWJGLU.renderBlockConduit(((IConduitProvider) pip).getConduit(), posX, posY, posZ);
				GlStateManager.disableLighting();
				return true;
			}
		}
		return false;
	}
	
}
