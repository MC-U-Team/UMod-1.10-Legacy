package io.github.mc_umod.item.infected;

import java.util.*;

import io.github.mc_umod.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.potion.*;

public class ItemInfectedBeef extends ItemFood {
	
	public ItemInfectedBeef() {
		super(3, 0.3F, true);
		this.setCreativeTab(UReference.infected);
		this.setPotionEffect(new PotionEffect(UPotion.radiationPotion, 5, 1), 0.8F);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) {
		UReference.proxy.addTooltip(stack, player, tooltip, advanced);
	}
	
}
