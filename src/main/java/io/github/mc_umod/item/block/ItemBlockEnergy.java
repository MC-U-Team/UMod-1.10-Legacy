package io.github.mc_umod.item.block;

import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import io.github.mc_umod.api.energy.IEnergyMessage;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

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
