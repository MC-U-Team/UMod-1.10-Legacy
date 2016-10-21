package net.hycrafthd.umod.item.tools.energy;

import net.hycrafthd.umod.UReference;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class ItemEnergyGlasses extends ItemArmor {
	
	public ItemEnergyGlasses(ArmorMaterial material) {
		super(material, 0, EntityEquipmentSlot.HEAD);
		setCreativeTab(UReference.maschines);
	}
	
}
