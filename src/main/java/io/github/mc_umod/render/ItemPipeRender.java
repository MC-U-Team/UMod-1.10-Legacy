package io.github.mc_umod.render;

import io.github.mc_umod.block.machine.BlockItemPipe;
import io.github.mc_umod.tileentity.TileEntityItemPipe;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemPipeRender extends TileRender {
		
	@Override
	public void renderTileEntityAt(TileEntity p_180535_1_, double posX, double posY, double posZ) {
		Block blo = p_180535_1_.getWorld().getBlockState(p_180535_1_.getPos()).getBlock();
		if (blo != null && p_180535_1_ instanceof TileEntityItemPipe && blo instanceof BlockItemPipe) {
			BlockItemPipe cab = (BlockItemPipe) blo;
			String name = cab.getSpirte();
			TileEntityItemPipe pip = (TileEntityItemPipe) p_180535_1_;
			World w = p_180535_1_.getWorld();
			if (!w.isRemote)
				return;
			GlStateManager.disableCull();
			BlockPos pos = pip.getPos();
			boolean csouth = pip.canConnect(w, pos.south());
			boolean cnorth = pip.canConnect(w, pos.north());
			boolean cdown = pip.canConnect(w, pos.down());
			boolean cup = pip.canConnect(w, pos.up());
			boolean ceast = pip.canConnect(w, pos.east());
			boolean cwest = pip.canConnect(w, pos.west());
			boolean lr = false, ud = false, fb = false;
			RenderLocation loc = new RenderLocation(name + ".png");
			/*if (cup) {
				drawBlock(loc, posX, posY + 0.25, posZ, 0.4, 0.5, 0.4);
				ud = true;
			}
			if (cdown) {
				drawBlock(loc, posX, posY - 0.25, posZ, 0.4, 0.5, 0.4);
				ud = true;
			}
			if (cwest) {
				drawBlock(loc, posX - 0.25, posY, posZ, 0.5, 0.4, 0.4);
				fb = true;
			}
			if (ceast) {
				drawBlock(loc, posX + 0.25, posY, posZ, 0.5, 0.4, 0.4);
				fb = true;
			}
			if (cnorth) {
				drawBlock(loc, posX, posY, posZ - 0.25, 0.4, 0.4, 0.5);
				lr = true;
			}
			if (csouth) {
				drawBlock(loc, posX, posY, posZ + 0.25, 0.4, 0.4, 0.5);
				lr = true;
			}
			
			if ((!cdown && !ceast && !cnorth && !csouth && !cup && !cwest) || (lr && fb) || (lr && ud) || (ud && fb) || (ud && fb && lr)) {
				drawBlock(loc, posX, posY, posZ, 0.405, 0.405, 0.405);
			}*/
			GlStateManager.enableCull();
		}
		
	}
}
