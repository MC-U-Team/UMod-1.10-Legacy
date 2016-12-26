package net.hycrafthd.umod.gui.container;

import java.awt.Color;

import net.hycrafthd.umod.gui.inventory.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ContainerPainter extends ContainerBase {
	
	public ContainerPainter(EntityPlayer player, BlockPos pos) {
		super(player, pos);
		super.addSlotToContainer(new ColorSlot(Color.red, (IInventory) this.tile, 0, 13, 11));
		super.addSlotToContainer(new ColorSlot(Color.green, (IInventory) this.tile, 1, 13, 32));
		super.addSlotToContainer(new ColorSlot(Color.blue, (IInventory) this.tile, 2, 13, 53));
		super.addSlotToContainer(new BaseCraftSlot((IInventory) this.tile, 3, 146, 11));
		super.addSlotToContainer(new BaseCraftSlot((IInventory) this.tile, 4, 146, 32));
		
		int i = 0;
		int v = 9;
		int j = 0;
		
		for (i = 0; i < 3; ++i) {
			for (j = 0; j < 9; ++j) {
				super.addSlotToContainer(new Slot(player.inventory, (j + (i * 9)) + v, 8 + j * 18, 84 + i * 18));
			}
		}
		for (i = 0; i < 9; ++i) {
			super.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 142));
		}
	}
	
}
