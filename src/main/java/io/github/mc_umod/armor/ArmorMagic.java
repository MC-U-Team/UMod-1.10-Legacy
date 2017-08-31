package io.github.mc_umod.armor;

import java.util.List;

import io.github.mc_umod.UReference;
import io.github.mc_umod.util.URegistryUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;

public class ArmorMagic extends ItemArmor {
	
	public ArmorMagic(ArmorMaterial material, EntityEquipmentSlot armorType) {
		super(material, 0, armorType);
		this.setCreativeTab(UReference.magic);
		
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot s, String type) {
		switch (s) {
		case CHEST:
		case FEET:
		case LEGS:
			return UReference.modid + ":textures/models/armor/magic_layer_1.png";
		case HEAD:
			return UReference.modid + ":textures/models/armor/magic_layer_2.png";
		default:
			break;
		}
		return null;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) {
		URegistryUtils.addTooltip(stack, tooltip);
	}
	
}
