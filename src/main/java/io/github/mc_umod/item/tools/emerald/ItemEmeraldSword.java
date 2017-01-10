package io.github.mc_umod.item.tools.emerald;

import java.util.*;

import io.github.mc_umod.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraftforge.fml.relauncher.*;

public class ItemEmeraldSword extends ItemSword {
	
	public ItemEmeraldSword(ToolMaterial material) {
		super(material);
		setCreativeTab(UReference.things);
	}
	
	@SuppressWarnings("rawtypes")
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) {
		UReference.proxy.addTooltip(stack, player, tooltip, advanced);
	}
	
}
