package io.github.mc_umod.item.infected;

import io.github.mc_umod.*;
import net.minecraft.item.ItemFood;
import net.minecraft.potion.PotionEffect;

public class ItemInfectedCrop extends ItemFood {
	
	public ItemInfectedCrop() {
		super(1, 0.2F, false);
		this.setCreativeTab(UReference.infected);
		this.setPotionEffect(new PotionEffect(UPotion.radiationPotion, 5, 1), 0.8F);
	}
		
}
