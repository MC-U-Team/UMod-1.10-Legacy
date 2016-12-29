package net.hycrafthd.umod.item.block;

import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.hycrafthd.umod.api.energy.IEnergyMessage;
import net.hycrafthd.umod.block.machine.BlockSolarPanel.EnumTypeSolarPanel;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemBlockSolarPanel extends ItemBlockSubBase {
	
	public ItemBlockSolarPanel(Block block) {
		super(block);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List tip, boolean advanced) {
		if (this.block instanceof IEnergyMessage) {
			tip.add(ChatFormatting.BLUE + ((IEnergyMessage) this.block).getMessage(stack.getMetadata()));
		}
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		EnumTypeSolarPanel type = EnumTypeSolarPanel.byMetadata(stack.getMetadata());
		return "tile.solarpanel" + type.getName();
	}
	
}
