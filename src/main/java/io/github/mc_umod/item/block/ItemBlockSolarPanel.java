package io.github.mc_umod.item.block;

import java.util.*;

import com.mojang.realmsclient.gui.*;

import io.github.mc_umod.api.energy.*;
import io.github.mc_umod.block.machine.BlockSolarPanel.*;
import net.minecraft.block.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;

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
