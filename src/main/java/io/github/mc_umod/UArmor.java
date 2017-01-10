package io.github.mc_umod;

import io.github.mc_umod.armor.*;
import io.github.mc_umod.item.tools.energy.*;
import io.github.mc_umod.utils.*;
import net.minecraft.init.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.item.ItemArmor.*;
import net.minecraftforge.common.util.*;

public class UArmor {
	
	// Radiation Suit
	public static ArmorMaterial radiationSuitMaterial;
	// Energy
	public static ArmorMaterial enrgy;
	// Emerald
	public static ArmorMaterial emeraldMaterial;
	// Magic
	public static ArmorMaterial magicMaterial;
	
	// Radiation Suit
	public static Item radiationSuitHelmet;
	public static Item radiationSuitChestplate;
	public static Item radiationSuitLeggings;
	public static Item radiationSuitBoots;
	// Energy
	public static Item energyglasses;
	// Emerald
	public static Item emeraldHelmet;
	public static Item emeraldChestplate;
	public static Item emeraldLeggings;
	public static Item emeraldBoots;
	// Magic
	public static Item magicHelmet;
	public static Item magicChestplate;
	public static Item magicLeggings;
	public static Item magicBoots;
	
	public UArmor() {
		init();
		register();
	}
	
	private void init() {
		// Radiation Suit
		radiationSuitMaterial = EnumHelper.addArmorMaterial("RADIATIONARMOR", "", 10, new int[] { 1, 3, 1, 1 }, 1, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0);
		// Energy
		enrgy = EnumHelper.addArmorMaterial("ENERGY", "", 5, new int[] { 1, 3, 1, 1 }, 1, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0);
		// Emerald
		emeraldMaterial = EnumHelper.addArmorMaterial("EMERALDARMOR", "", 28, new int[] { 3, 7, 5, 3 }, 20, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0);
		// Magic
		magicMaterial = EnumHelper.addArmorMaterial("MAGICARMOR", "", 15, new int[] { 3, 8, 6, 3 }, 30, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0);
		
		// Radiation Suit
		radiationSuitHelmet = new ArmorRadiation(radiationSuitMaterial, EntityEquipmentSlot.HEAD).setUnlocalizedName("radiationsuithelmet");
		radiationSuitChestplate = new ArmorRadiation(radiationSuitMaterial, EntityEquipmentSlot.CHEST).setUnlocalizedName("radiationsuitchestplate");
		radiationSuitLeggings = new ArmorRadiation(radiationSuitMaterial, EntityEquipmentSlot.LEGS).setUnlocalizedName("radiationsuitleggings");
		radiationSuitBoots = new ArmorRadiation(radiationSuitMaterial, EntityEquipmentSlot.FEET).setUnlocalizedName("radiationsuitboots");
		
		// Energy
		energyglasses = new ItemEnergyGlasses(enrgy).setUnlocalizedName("energyglasses");
		
		// Emerald
		emeraldHelmet = new ArmorEmerald(emeraldMaterial, EntityEquipmentSlot.HEAD).setUnlocalizedName("emeraldhelmet");
		emeraldChestplate = new ArmorEmerald(emeraldMaterial, EntityEquipmentSlot.CHEST).setUnlocalizedName("emeraldchestplate");
		emeraldLeggings = new ArmorEmerald(emeraldMaterial, EntityEquipmentSlot.LEGS).setUnlocalizedName("emeraldleggings");
		emeraldBoots = new ArmorEmerald(emeraldMaterial, EntityEquipmentSlot.FEET).setUnlocalizedName("emeraldboots");
		
		// Magic
		magicHelmet = new ArmorMagic(magicMaterial, EntityEquipmentSlot.HEAD).setUnlocalizedName("magic_helmet");
		magicChestplate = new ArmorMagic(magicMaterial, EntityEquipmentSlot.CHEST).setUnlocalizedName("magic_chestplate");
		magicLeggings = new ArmorMagic(magicMaterial, EntityEquipmentSlot.LEGS).setUnlocalizedName("magic_leggings");
		magicBoots = new ArmorMagic(magicMaterial, EntityEquipmentSlot.FEET).setUnlocalizedName("magic_boots");
		
		UMod.log.debug("Init Armor");
	}
	
	private void register() {
		// Radiation Suit
		URegistryUtils.registerItem(radiationSuitHelmet);
		URegistryUtils.registerItem(radiationSuitChestplate);
		URegistryUtils.registerItem(radiationSuitLeggings);
		URegistryUtils.registerItem(radiationSuitBoots);
		
		// Energy
		URegistryUtils.registerItem(energyglasses);
		
		// Emerald
		URegistryUtils.registerItem(emeraldHelmet);
		URegistryUtils.registerItem(emeraldChestplate);
		URegistryUtils.registerItem(emeraldLeggings);
		URegistryUtils.registerItem(emeraldBoots);
		
		// Magic
		URegistryUtils.registerItem(magicHelmet);
		URegistryUtils.registerItem(magicChestplate);
		URegistryUtils.registerItem(magicLeggings);
		URegistryUtils.registerItem(magicBoots);
		
		UMod.log.debug("Register Armor");
	}
	
}
