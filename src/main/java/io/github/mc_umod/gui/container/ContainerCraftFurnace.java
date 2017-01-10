package io.github.mc_umod.gui.container;

import io.github.mc_umod.gui.inventory.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.util.math.*;

public class ContainerCraftFurnace extends ContainerBase {
	
	public ContainerCraftFurnace(EntityPlayer pl, BlockPos pos) {
		super(pl, pos);
		
		int i = 0;
		int j = 0;
		
		for (i = 0; i < 3; ++i) {
			for (j = 0; j < 3; ++j) {
				super.addSlotToContainer(new BaseCraftSlot((IInventory) this.tile, j + (i * 3), 25 + j * 18, 20 + i * 18));
			}
		}
		
		super.addSlotToContainer(new BaseSlotOutput((IInventory) this.tile, 9, 135, 38));
		
		int v = 9;
		
		for (i = 0; i < 3; ++i) {
			for (j = 0; j < 9; ++j) {
				super.addSlotToContainer(new BaseNormalSlot(pl.inventory, (j + (i * 9)) + v, 8 + j * 18, 84 + i * 18));
			}
		}
		
		for (i = 0; i < 9; ++i) {
			super.addSlotToContainer(new BaseNormalSlot(pl.inventory, i, 8 + i * 18, 142));
		}
	}
	
}
