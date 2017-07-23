package io.github.mc_umod.render;

import io.github.mc_umod.block.machine.BlockCable;
import io.github.mc_umod.tileentity.TileEntityCable;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CabelRender extends TileRender {
	
	public CabelRender() {
		super();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tile,double posX, double posY, double posZ) {
		//EntityPlayer pl = Minecraft.getMinecraft().thePlayer;		
		// if (pl.inventory.armorInventory[3] != null && pl.inventory.armorInventory[3].getItem() instanceof ItemEnergyGlasses && tile instanceof IPowerProvieder) {
		// TODO Create Overlay only IO Pipes
		// }
		Block blo = tile.getWorld().getBlockState(tile.getPos()).getBlock();
		if (blo != null && tile instanceof TileEntityCable && blo instanceof BlockCable) {
			BlockCable cab = (BlockCable) blo;
			String name = cab.getSpirte();
			TileEntityCable pip = (TileEntityCable) tile;
			World w = tile.getWorld();
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
		/*	if (cup) {
				drawBlock(loc, posX, posY + 0.25, posZ, 0.2, 0.5, 0.2);
				ud = true;
			}
			if (cdown) {
				drawBlock(loc, posX, posY - 0.25, posZ, 0.2, 0.5, 0.2);
				ud = true;
			}
			if (cwest) {
				drawBlock(loc, posX - 0.25, posY, posZ, 0.5, 0.2, 0.2);
				fb = true;
			}
			if (ceast) {
				drawBlock(loc, posX + 0.25, posY, posZ, 0.5, 0.2, 0.2);
				fb = true;
			}
			if (cnorth) {
				drawBlock(loc, posX, posY, posZ - 0.25, 0.2, 0.2, 0.5);
				lr = true;
			}
			if (csouth) {
				drawBlock(loc, posX, posY, posZ + 0.25, 0.2, 0.2, 0.5);
				lr = true;
			}
			
			if ((!cdown && !ceast && !cnorth && !csouth && !cup && !cwest) || (lr && fb) || (lr && ud) || (ud && fb) || (ud && fb && lr)) {
				drawBlock(loc, posX, posY, posZ, 0.205, 0.205, 0.205);
			}*/
			GlStateManager.enableCull();
		}
	}
	
}
