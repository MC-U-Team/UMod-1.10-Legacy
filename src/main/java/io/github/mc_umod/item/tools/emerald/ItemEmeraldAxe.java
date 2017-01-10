package io.github.mc_umod.item.tools.emerald;

import java.util.List;

import io.github.mc_umod.UReference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
