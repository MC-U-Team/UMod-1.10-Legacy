package io.github.mc_umod.item.tools.magic;

import java.util.*;

import io.github.mc_umod.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraftforge.fml.relauncher.*;

public class ItemMagicHoe extends ItemHoe {
	
	public ItemMagicHoe(ToolMaterial material) {
		super(material);
		setCreativeTab(UReference.magic);
	}
	
	@SuppressWarnings("rawtypes")
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) {
		UReference.proxy.addTooltip(stack, player, tooltip, advanced);
	}
	
}
