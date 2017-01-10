package io.github.mc_umod.item.tools.emerald;

import java.util.*;

import io.github.mc_umod.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraftforge.fml.relauncher.*;

public class ItemEmeraldAxe extends ItemAxe {
	
	public ItemEmeraldAxe(ToolMaterial material) {
		super(material, material.getDamageVsEntity(), 0);
		setCreativeTab(UReference.things);
	}
	
	@SuppressWarnings("rawtypes")
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) {
		UReference.proxy.addTooltip(stack, player, tooltip, advanced);
	}
	
}
