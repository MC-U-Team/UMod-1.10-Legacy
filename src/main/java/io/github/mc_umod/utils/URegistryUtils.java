package io.github.mc_umod.utils;

import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import io.github.mc_umod.*;
import io.github.mc_umod.block.deco.*;
import io.github.mc_umod.item.block.*;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.*;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class URegistryUtils {
	
	private static CommonRegistry reg;
	
	public static void registerItem(Item item) {
		if(reg == null){
			reg = new CoreCommonRegistry();
		}
		reg.registerItem(item, item.getUnlocalizedName().substring(5));
		reg.registerOreDictionary(item,item.getUnlocalizedName().substring(5));
	}
	
	public static void registerBlock(Block block) {
		registerBlock(block, ItemBlockBase.class);
	}
	
	public static void registerBlock(Block block, Class<? extends ItemBlock> bl) {
		if(reg == null){
			reg = new CoreCommonRegistry();
		}
		reg.registerBlock(block, block.getUnlocalizedName().substring(5));
		reg.registerOreDictionary(block,block.getUnlocalizedName().substring(5));
	}
	
	public static void registerHalfSlabs(BlockSlabCreator creator) {
		registerHalfSlabs(creator, ItemBlockCustomSlab.class);
	}
	
	public static void registerHalfSlabs(BlockSlabCreator creator, Class<? extends ItemBlockCustomSlab> bl) {
		if(reg == null){
			reg = new CoreCommonRegistry();
		}
		BlockHalfCustomSlab slab = creator.getSlab();
		BlockDoubleCustomSlab doubleSlab = creator.getDoubleslab();
		GameRegistry.registerBlock(slab, bl, slab.getUnlocalizedName().substring(5), slab, doubleSlab);
		GameRegistry.registerBlock(doubleSlab, null, doubleSlab.getUnlocalizedName().substring(5), slab, doubleSlab);
		reg.registerOreDictionary(slab,slab.getUnlocalizedName().substring(5));
	}
	
	public static void addTooltip(ItemStack stack, List tooltip){
		String tip = I18n.format("tooltip." + stack.getUnlocalizedName());
		if (!tip.startsWith("tooltip.")) {
			tooltip.add(ChatFormatting.BLUE + tip + ChatFormatting.RESET);
		}
	}
}
