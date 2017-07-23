package io.github.mc_umod.item.block;

import io.github.mc_umod.block.machine.BlockSolarPanel.EnumTypeSolarPanel;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemBlockSolarPanel extends ItemBlockSubBase {
	
	public ItemBlockSolarPanel(Block block) {
		super(block);
	}
		
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		EnumTypeSolarPanel type = EnumTypeSolarPanel.byMetadata(stack.getMetadata());
		return "tile.solarpanel" + type.getName();
	}
	
}
