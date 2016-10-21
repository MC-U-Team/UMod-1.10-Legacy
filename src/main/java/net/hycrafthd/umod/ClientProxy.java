package net.hycrafthd.umod;

import java.util.List;
import java.util.Map;

import org.lwjgl.input.Keyboard;

import com.google.common.collect.Maps;
import com.mojang.realmsclient.gui.ChatFormatting;

import net.hycrafthd.corelib.registry.KeybindingRegistry;
import net.hycrafthd.corelib.registry.ModelRegistry;
import net.hycrafthd.corelib.registry.RenderRegistry;
import net.hycrafthd.corelib.util.ItemUtil;
import net.hycrafthd.umod.block.BlockSlabCreator;
import net.hycrafthd.umod.block.BlockSolarPanel.EnumTypeSolarPanel;
import net.hycrafthd.umod.block.BlockStairCreator;
import net.hycrafthd.umod.entity.EntityFX;
import net.hycrafthd.umod.entity.EntityInfectedCow;
import net.hycrafthd.umod.entity.EntityInfectedCreeper;
import net.hycrafthd.umod.entity.EntityInfectedZombie;
import net.hycrafthd.umod.entity.EntityNukePrimed;
import net.hycrafthd.umod.entity.rail.EntityRailFX;
import net.hycrafthd.umod.entity.render.RenderFX;
import net.hycrafthd.umod.entity.render.RenderInfectedCow;
import net.hycrafthd.umod.entity.render.RenderInfectedCreeper;
import net.hycrafthd.umod.entity.render.RenderInfectedZombie;
import net.hycrafthd.umod.entity.render.RenderNukePrimed;
import net.hycrafthd.umod.entity.render.rail.RenderRailFX;
import net.hycrafthd.umod.enumtype.EnumTypeBackPack;
import net.hycrafthd.umod.enumtype.EnumTypeBaseStuff;
import net.hycrafthd.umod.enumtype.EnumTypeTransformer;
import net.hycrafthd.umod.ext.ExtensionList;
import net.hycrafthd.umod.render.TileEntityCabelRender;
import net.hycrafthd.umod.render.TileEntityEnergyPannelRender;
import net.hycrafthd.umod.render.TileEntityItemPipeRender;
import net.hycrafthd.umod.render.TileEntityPainterSpecialRender;
import net.hycrafthd.umod.render.TileEntityPulverizerSpecialRender;
import net.hycrafthd.umod.tileentity.TileEntityCable;
import net.hycrafthd.umod.tileentity.TileEntityEnergyPannel;
import net.hycrafthd.umod.tileentity.TileEntityItemPipe;
import net.hycrafthd.umod.tileentity.TileEntityPainter;
import net.hycrafthd.umod.tileentity.TileEntityPulverizer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayer.EnumChatVisibility;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	
	public static KeyBinding info = new KeyBinding("Infromation", Keyboard.KEY_I, "UMod");
	
	@Override
	public void registerModels() {
		
		// Ore and NetherOre
		for (int i = 0; i < EnumTypeBaseStuff.values().length; i++) {
			ModelRegistry.registerVariants(Item.getItemFromBlock(UBlocks.ores),new ResourceLocation("umod", UReference.resource + "ore" + EnumTypeBaseStuff.byMetadata(i).getName()));
			ModelRegistry.register(ItemUtil.from(UBlocks.ores), i, new ModelResourceLocation(UReference.resource + "ore" + EnumTypeBaseStuff.byMetadata(i).getName(), "inventory"));
			reg(Item.getItemFromBlock(UBlocks.netherores), UReference.resource + "netherore" + EnumTypeBaseStuff.byMetadata(i).getName());
			ModelRegistry.register(ItemUtil.from(UBlocks.netherores), i, new ModelResourceLocation(UReference.resource + "netherore" + EnumTypeBaseStuff.byMetadata(i).getName(), "inventory"));
		}
		
		// Blocks
		for (int i = 0; i < EnumTypeBaseStuff.values().length; i++) {
			reg(Item.getItemFromBlock(UBlocks.blocks), UReference.resource + "block" + EnumTypeBaseStuff.byMetadata(i).getName());
			ModelRegistry.register(ItemUtil.from(UBlocks.blocks), i, new ModelResourceLocation(UReference.resource + "block" + EnumTypeBaseStuff.byMetadata(i).getName(), "inventory"));
		}
		
		// Ingot (and Sulphur Chunk)
		for (int i = 0; i < EnumTypeBaseStuff.values().length; i++) {
			reg(UItems.ingots, UReference.resource + "ingot" + EnumTypeBaseStuff.byMetadata(i).getName());
			ModelRegistry.register(UItems.ingots, i, new ModelResourceLocation(UReference.resource + "ingot" + EnumTypeBaseStuff.byMetadata(i).getName(), "inventory"));
		}
		
		// Transformer
		for (int i = 0; i < EnumTypeTransformer.values().length; i++) {
			reg(UItems.transformer, UReference.resource + "transformer" + EnumTypeTransformer.byMetadata(i).getName());
			ModelRegistry.register(UItems.transformer, i, new ModelResourceLocation(UReference.resource + "transformer" + EnumTypeTransformer.byMetadata(i).getName(), "inventory"));
		}
		
		// Dust
		for (int i = 0; i < EnumTypeBaseStuff.values().length; i++) {
			reg(UItems.dusts, UReference.resource + "dust" + EnumTypeBaseStuff.byMetadata(i).getName());
			ModelRegistry.register(UItems.dusts, i, new ModelResourceLocation(UReference.resource + "dust" + EnumTypeBaseStuff.byMetadata(i).getName(), "inventory"));
		}
		
		ModelRegistry.register(UItems.manganoxid);
		
		// Pulverizer
		ModelRegistry.register(UBlocks.charge);
		ModelRegistry.register(UBlocks.pulver);
		ModelRegistry.register(UBlocks.craftfurnance);
		ModelRegistry.register(UBlocks.painter);
		
		// cbbl Dust
		ModelRegistry.register(UItems.cdust);
		
		// battery
		ModelRegistry.register(UItems.battery);
		ModelRegistry.register(UItems.copper_wire);
		ModelRegistry.register(UItems.copper_coil);
		ModelRegistry.register(UItems.transformer);
		ModelRegistry.register(UItems.thicker_copper_wire);
		ModelRegistry.register(UItems.petrol);
		
		//magic
		ModelRegistry.register(UBlocks.magic_crafter);
		ModelRegistry.register(UItems.magic_diamond);
		ModelRegistry.register(UItems.magic_ingot);
		ModelRegistry.register(UItems.charged_quartz);
		ModelRegistry.register(UItems.magic_brew);
		ModelRegistry.register(UBlocks.magic_glass);
		ModelRegistry.register(UItems.magic_bottle);
		
		// SolarPanel
		for (int i = 0; i < EnumTypeSolarPanel.values().length; i++) {
			reg(Item.getItemFromBlock(UBlocks.solarpanel), UReference.resource + "solarpanel" + EnumTypeSolarPanel.byMetadata(i).getName());
			ModelRegistry.register(ItemUtil.from(UBlocks.solarpanel), i, new ModelResourceLocation(UReference.resource + "solarpanel" + EnumTypeSolarPanel.byMetadata(i).getName(), "inventory"));
		}
		
		// Armor
		ModelRegistry.register(UArmor.radiationSuitHelmet);
		ModelRegistry.register(UArmor.radiationSuitChestplate);
		ModelRegistry.register(UArmor.radiationSuitLeggings);
		ModelRegistry.register(UArmor.radiationSuitBoots);
		
		ModelRegistry.register(UArmor.emeraldHelmet);
		ModelRegistry.register(UArmor.emeraldChestplate);
		ModelRegistry.register(UArmor.emeraldLeggings);
		ModelRegistry.register(UArmor.emeraldBoots);
		
		ModelRegistry.register(UArmor.magicHelmet);
		ModelRegistry.register(UArmor.magicChestplate);
		ModelRegistry.register(UArmor.magicLeggings);
		ModelRegistry.register(UArmor.magicBoots);

		// Infected
		ModelRegistry.register(UBlocks.infectedGrass);
		ModelRegistry.register(UBlocks.infectedDirt);
		ModelRegistry.register(UBlocks.infectedLog);
		ModelRegistry.register(UBlocks.infectedLeave);
		ModelRegistry.register(UBlocks.infectedPlank);
		ModelRegistry.register(UBlocks.infectedSapling);
		ModelRegistry.register(UBlocks.infectedFruit);
		ModelRegistry.register(UItems.infectedcrop);
		ModelRegistry.register(UItems.infectedleather);
		ModelRegistry.register(UItems.infectedbeef);
		ModelRegistry.register(UItems.infectedmilk);
		
		ModelRegistry.register(UItems.acid);
		ModelRegistry.register(UItems.plastic);
		ModelRegistry.register(UBlocks.oilsand);
		ModelRegistry.register(UBlocks.oilglass);
		
		ModelRegistry.register(UBlocks.infestedCleaner);
		
		// Pipes
		ModelRegistry.register(UBlocks.medium_voltage_cable);
		ModelRegistry.register(UBlocks.high_voltage_cable);
		ModelRegistry.register(UBlocks.low_voltage_cable);
		
		// Normal Blocks
		ModelRegistry.register(UBlocks.nuke);
		ModelRegistry.register(UBlocks.craftfurnance);
		ModelRegistry.register(UBlocks.rail);
		
		// Stairs
		for (BlockStairCreator creator : UBlocks.stonestairs) {
			ModelRegistry.register(creator.getStair());
		}
		
		for (BlockStairCreator creator : UBlocks.woolstairs) {
			ModelRegistry.register(creator.getStair());
		}
		
		for (BlockStairCreator creator : UBlocks.claystairs) {
			ModelRegistry.register(creator.getStair());
		}
		
		// Slabs
		for (BlockSlabCreator creator : UBlocks.stoneslabs) {
			ModelRegistry.register(creator.getSlab());
		}
		
		// Backpack
		for (int i = 0; i < EnumTypeBackPack.values().length; i++) {
			reg(UItems.backpack, UReference.resource + "backpack" + EnumTypeBackPack.byMetadata(i).getName());
			ModelRegistry.register(UItems.backpack, i, new ModelResourceLocation(UReference.resource + "backpack" + EnumTypeBackPack.byMetadata(i).getName(), "inventory"));
		}
		
		// Tools
		ModelRegistry.register(UTools.emeraldAxe);
		ModelRegistry.register(UTools.emeraldPickaxe);
		ModelRegistry.register(UTools.emeraldSword);
		ModelRegistry.register(UTools.emeraldSpade);
		ModelRegistry.register(UTools.emeraldHoe);
		
		ModelRegistry.register(UTools.magicAxe);
		ModelRegistry.register(UTools.magicPickaxe);
		ModelRegistry.register(UTools.magicShovel);
		ModelRegistry.register(UTools.magicSword);
		ModelRegistry.register(UTools.magicHoe);

		// Keybinding
		KeybindingRegistry.register(info);
	}
	
	@Override
	public void registerRenderer() {
		ExtensionList.onClientProxy();
		
		RenderFX.register(TileEntityEnergyPannel.class, new TileEntityEnergyPannelRender());
		RenderFX.register(TileEntityCable.class, new TileEntityCabelRender());
		RenderFX.register(TileEntityItemPipe.class, new TileEntityItemPipeRender());
		
		Map<Class<? extends Entity>, Render<? extends Entity>> mp = Maps.newHashMap();
		mp.put(EntityInfectedCreeper.class, new RenderInfectedCreeper());
		mp.put(EntityNukePrimed.class, new RenderNukePrimed());
		mp.put(EntityInfectedZombie.class, new RenderInfectedZombie());
		mp.put(EntityFX.class, new RenderFX());
		mp.put(EntityRailFX.class, new RenderRailFX());
		mp.put(EntityInfectedCow.class, new RenderInfectedCow());
		RenderingRegistry.loadEntityRenderers(Minecraft.getMinecraft().getRenderManager(), mp);
		
		RenderRegistry.bindTileEntitySpecialRenderer(TileEntityPulverizer.class, new TileEntityPulverizerSpecialRender());
		RenderRegistry.bindTileEntitySpecialRenderer(TileEntityPainter.class, new TileEntityPainterSpecialRender());
		VIARegister.registerVIA();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void addTooltip(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) {
		String tip = I18n.format("tooltip." + stack.getUnlocalizedName());
		if (!tip.startsWith("tooltip.")) {
			tooltip.add(ChatFormatting.BLUE + tip + ChatFormatting.RESET);
		}
	}
	
	public void reg(Object ocj,String str){
		ModelRegistry.registerVariants(ocj,new ResourceLocation("umod", str));
	}
	
}
