package io.github.mc_umod.item.tools.energy;

import io.github.mc_umod.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;

public class ItemEnergyGlasses extends ItemArmor {
	
	public ItemEnergyGlasses(ArmorMaterial material) {
		super(material, 0, EntityEquipmentSlot.HEAD);
		setCreativeTab(UReference.maschines);
	}
	
}
