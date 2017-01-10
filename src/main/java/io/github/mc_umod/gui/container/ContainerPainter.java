package io.github.mc_umod.gui.container;

import java.awt.Color;

import io.github.mc_umod.gui.inventory.BaseCraftSlot;
import io.github.mc_umod.gui.inventory.BaseNormalSlot;
import io.github.mc_umod.gui.inventory.ColorSlot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.math.BlockPos;

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
				super.addSlotToContainer(new BaseNormalSlot(player.inventory, (j + (i * 9)) + v, 8 + j * 18, 84 + i * 18));
			}
		}
		for (i = 0; i < 9; ++i) {
			super.addSlotToContainer(new BaseNormalSlot(player.inventory, i, 8 + i * 18, 142));
		}
	}
	
}
