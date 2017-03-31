package io.github.mc_umod.item.tools.magic;

import java.util.*;

import io.github.mc_umod.*;
import io.github.mc_umod.utils.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraftforge.fml.relauncher.*;

public class ItemMagicAxe extends ItemAxe {
	
	public ItemMagicAxe(ToolMaterial material) {
		super(material, material.getDamageVsEntity(), 0);
		setCreativeTab(UReference.magic);
	}
	
	@SuppressWarnings("rawtypes")
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) {
		URegistryUtils.addTooltip(stack, tooltip);
	}
	
}
