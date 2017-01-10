package io.github.mc_umod.item.block;

import java.util.*;

import com.mojang.realmsclient.gui.*;

import io.github.mc_umod.api.energy.*;
import net.minecraft.block.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;

public class ItemBlockEnergy extends ItemBlockBase {
	
	public ItemBlockEnergy(Block block) {
		super(block);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List tip, boolean advanced) {
		if (this.block instanceof IEnergyMessage) {
			tip.add(ChatFormatting.BLUE + ((IEnergyMessage) this.block).getMessage(0));
		}
	}
}
