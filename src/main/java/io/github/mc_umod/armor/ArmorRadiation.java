package io.github.mc_umod.armor;

import java.util.*;

import io.github.mc_umod.*;
import io.github.mc_umod.utils.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;

public class ArmorRadiation extends ItemArmor {
	
	public ArmorRadiation(ArmorMaterial material, EntityEquipmentSlot armorType) {
		super(material, 0, armorType);
		this.setCreativeTab(UReference.infected);
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot s, String type) {
		switch (s) {
		case CHEST:
		case FEET:
		case LEGS:
			return UReference.modid + ":textures/models/armor/radiation_suit_layer_1.png";
		case HEAD:
			return UReference.modid + ":textures/models/armor/radiation_suit_layer_2.png";
		default:
			break;
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) {
		URegistryUtils.addTooltip(stack, tooltip);
	}
}
