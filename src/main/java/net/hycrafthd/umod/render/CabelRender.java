package net.hycrafthd.umod.render;

import net.hycrafthd.umod.block.BlockCable;
import net.hycrafthd.umod.tileentity.TileEntityCable;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CabelRender extends TileRender {
	
	public CabelRender(GLHelper help) {
		super(help);
	}
	
	@Override
	public void renderTileEntityAt(TileEntity p_180535_1_, double posX, double posY, double posZ) {
		// EntityPlayer pl = Minecraft.getMinecraft().thePlayer;
		// if (pl.inventory.armorInventory[3] != null && pl.inventory.armorInventory[3].getItem() instanceof ItemEnergyGlasses && p_180535_1_ instanceof IPowerProvieder) {
		// TODO Create Overlay only IO Pipes
		// }
		Block blo = p_180535_1_.getWorld().getBlockState(p_180535_1_.getPos()).getBlock();
		if (blo != null && p_180535_1_ instanceof TileEntityCable && blo instanceof BlockCable) {
			BlockCable cab = (BlockCable) blo;
			String name = cab.getSpirte();
			TileEntityCable pip = (TileEntityCable) p_180535_1_;
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
			if (cup) {
				this.help.drawBlock(loc, posX, posY + 0.25, posZ, 0.2, 0.5, 0.2);
				ud = true;
			}
			if (cdown) {
				this.help.drawBlock(loc, posX, posY - 0.25, posZ, 0.2, 0.5, 0.2);
				ud = true;
			}
			if (cwest) {
				this.help.drawBlock(loc, posX - 0.25, posY, posZ, 0.5, 0.2, 0.2);
				fb = true;
			}
			if (ceast) {
				this.help.drawBlock(loc, posX + 0.25, posY, posZ, 0.5, 0.2, 0.2);
				fb = true;
			}
			if (cnorth) {
				this.help.drawBlock(loc, posX, posY, posZ - 0.25, 0.2, 0.2, 0.5);
				lr = true;
			}
			if (csouth) {
				this.help.drawBlock(loc, posX, posY, posZ + 0.25, 0.2, 0.2, 0.5);
				lr = true;
			}
			
			if ((!cdown && !ceast && !cnorth && !csouth && !cup && !cwest) || (lr && fb) || (lr && ud) || (ud && fb) || (ud && fb && lr)) {
				this.help.drawBlock(loc, posX, posY, posZ, 0.205, 0.205, 0.205);
			}
			GlStateManager.enableCull();
		}
	}
	
}
