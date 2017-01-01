package io.github.mc_umod.item.tools.energy;

import io.github.mc_umod.UReference;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class ItemEnergyGlasses extends ItemArmor {
	
	public ItemEnergyGlasses(ArmorMaterial material) {
		super(material, 0, EntityEquipmentSlot.HEAD);
		setCreativeTab(UReference.maschines);
	}
	
}
