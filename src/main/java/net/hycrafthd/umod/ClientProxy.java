package net.hycrafthd.umod;

import java.util.List;

import org.lwjgl.input.Keyboard;

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
import net.hycrafthd.umod.gui.ModlerenderHelper;
import net.hycrafthd.umod.render.CabelRender;
import net.hycrafthd.umod.render.GLHelper;
import net.hycrafthd.umod.render.ItemPipeRender;
import net.hycrafthd.umod.render.TileEntityEnergyPannelRender;
import net.hycrafthd.umod.render.TileEntityPainterSpecialRender;
import net.hycrafthd.umod.render.TileEntityPulverizerSpecialRender;
import net.hycrafthd.umod.tileentity.TileEntityCable;
import net.hycrafthd.umod.tileentity.TileEntityEnergyPannel;
import net.hycrafthd.umod.tileentity.TileEntityItemPipe;
import net.hycrafthd.umod.tileentity.TileEntityPainter;
import net.hycrafthd.umod.tileentity.TileEntityPulverizer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	
	private KeyBinding info;
	private Minecraft mc;
	private ObjRenderRegistry regs;
	private ModlerenderHelper MODEL_HELPER;
	private GLHelper help;
	
	@Override
	public void init() {
		this.mc = Minecraft.getMinecraft();
		this.info = new KeyBinding("Information", Keyboard.KEY_I, "UMod");
		this.MODEL_HELPER = new ModlerenderHelper(mc.getRenderItem().getItemModelMesher(), mc.getTextureManager(), mc.getItemColors());
		this.regs = new ObjRenderRegistry();
		this.help = new GLHelper(Minecraft.getMinecraft().getTextureManager(), Tessellator.getInstance().getBuffer());
	}
	
	public GLHelper getGLHelper() {
		return this.help;
	}
	
	public ModlerenderHelper getModelRenderHelper() {
		return this.MODEL_HELPER;
	}
	
	public ObjRenderRegistry getObjRenderList(){
		return this.regs;
	}
	
	public KeyBinding getInfoBinding() {
		return this.info;
	}
	
	@Override
	public void registerModels() {
		// Register Base Type Variants
		ResourceLocation[] rs3 = new ResourceLocation[EnumTypeBaseStuff.values().length];
		ResourceLocation[] rs5 = new ResourceLocation[EnumTypeBaseStuff.values().length];
		ResourceLocation[] rs6 = new ResourceLocation[EnumTypeBaseStuff.values().length];
		ResourceLocation[] rs7 = new ResourceLocation[EnumTypeBaseStuff.values().length];
		ResourceLocation[] rs8 = new ResourceLocation[EnumTypeBaseStuff.values().length];
		for (int i = 0; i < EnumTypeBaseStuff.values().length; i++) {
			ModelRegistry.register(UItems.dusts, i, new ModelResourceLocation(UReference.resource + "dust" + EnumTypeBaseStuff.byMetadata(i).getName(), "inventory"));
			ModelRegistry.register(UItems.ingots, i, new ModelResourceLocation(UReference.resource + "ingot" + EnumTypeBaseStuff.byMetadata(i).getName(), "inventory"));
			ModelRegistry.register(ItemUtil.from(UBlocks.blocks), i, new ModelResourceLocation(UReference.resource + "block" + EnumTypeBaseStuff.byMetadata(i).getName(), "inventory"));
			ModelRegistry.register(ItemUtil.from(UBlocks.ores), i, new ModelResourceLocation(UReference.resource + "ore" + EnumTypeBaseStuff.byMetadata(i).getName(), "inventory"));
			ModelRegistry.register(ItemUtil.from(UBlocks.netherores), i, new ModelResourceLocation(UReference.resource + "netherore" + EnumTypeBaseStuff.byMetadata(i).getName(), "inventory"));
			rs3[i] = new ResourceLocation(UReference.modid, "dust" + EnumTypeBaseStuff.byMetadata(i).getName());
			rs5[i] = new ResourceLocation(UReference.modid, "ingot" + EnumTypeBaseStuff.byMetadata(i).getName());
			rs6[i] = new ResourceLocation(UReference.modid, "block" + EnumTypeBaseStuff.byMetadata(i).getName());
			rs7[i] = new ResourceLocation(UReference.modid, "netherore" + EnumTypeBaseStuff.byMetadata(i).getName());
			rs8[i] = new ResourceLocation(UReference.modid, "ore" + EnumTypeBaseStuff.byMetadata(i).getName());
		}
		ModelRegistry.registerVariants(UItems.dusts, rs3);
		ModelRegistry.registerVariants(UItems.ingots, rs5);
		ModelRegistry.registerVariants(Item.getItemFromBlock(UBlocks.blocks), rs6);
		ModelRegistry.registerVariants(Item.getItemFromBlock(UBlocks.netherores), rs7);
		ModelRegistry.registerVariants(Item.getItemFromBlock(UBlocks.ores), rs8);
		ModelRegistry.register(UItems.cdust);
		
		// Transformer
		ResourceLocation[] rs4 = new ResourceLocation[EnumTypeTransformer.values().length];
		for (int i = 0; i < EnumTypeTransformer.values().length; i++) {
			ModelRegistry.register(UItems.transformer, i, new ModelResourceLocation(UReference.resource + "transformer" + EnumTypeTransformer.byMetadata(i).getName(), "inventory"));
			rs4[i] = new ResourceLocation(UReference.modid, "transformer" + EnumTypeTransformer.byMetadata(i).getName());
		}
		ModelRegistry.registerVariants(UItems.transformer, rs4);
		
		ModelRegistry.register(UItems.manganoxid);
		
		// Pulverizer
		ModelRegistry.register(UBlocks.charge);
		ModelRegistry.register(ItemUtil.from(UBlocks.pulver), 0, new ModelResourceLocation(UReference.resource + "pulver", "inventory"));
		ModelRegistry.register(UBlocks.craftfurnance);
		ModelRegistry.register(UBlocks.painter);
		ModelRegistry.register(UBlocks.craftfurnance);
		
		// Energy
		ModelRegistry.register(UItems.battery);
		ModelRegistry.register(UItems.copper_wire);
		ModelRegistry.register(UItems.copper_coil);
		ModelRegistry.register(UItems.transformer);
		ModelRegistry.register(UItems.thicker_copper_wire);
		ModelRegistry.register(UItems.petrol);
		
		// magic
		ModelRegistry.register(UBlocks.magic_crafter);
		ModelRegistry.register(UItems.magic_diamond);
		ModelRegistry.register(UItems.magic_ingot);
		ModelRegistry.register(UItems.charged_quartz);
		ModelRegistry.register(UItems.magic_brew);
		ModelRegistry.register(UBlocks.magic_glass);
		ModelRegistry.register(UItems.magic_bottle);
		
		// SolarPanel
		ResourceLocation[] rs1 = new ResourceLocation[EnumTypeSolarPanel.values().length];
		for (int i = 0; i < EnumTypeSolarPanel.values().length; i++) {
			ModelRegistry.register(ItemUtil.from(UBlocks.solarpanel), i, new ModelResourceLocation(UReference.resource + "solarpanel" + EnumTypeSolarPanel.byMetadata(i).getName(), "inventory"));
			rs1[i] = new ResourceLocation(UReference.modid, "solarpanel" + EnumTypeSolarPanel.byMetadata(i).getName());
		}
		ModelRegistry.registerVariants(ItemUtil.from(UBlocks.solarpanel), rs1);
		
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
		ResourceLocation[] rs2 = new ResourceLocation[EnumTypeBackPack.values().length];
		for (int i = 0; i < EnumTypeBackPack.values().length; i++) {
			ModelRegistry.register(UItems.backpack, i, new ModelResourceLocation(UReference.resource + "backpack" + EnumTypeBackPack.byMetadata(i).getName(), "inventory"));
			rs2[i] = new ResourceLocation(UReference.modid, "backpack" + EnumTypeBackPack.byMetadata(i).getName());
		}
		ModelRegistry.registerVariants(ItemUtil.from(UItems.backpack), rs2);
		
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
		
		RenderFX.register(TileEntityEnergyPannel.class, new TileEntityEnergyPannelRender(this.help));
		RenderFX.register(TileEntityCable.class, new CabelRender(this.help));
		RenderFX.register(TileEntityItemPipe.class, new ItemPipeRender(this.help));
		
		RenderingRegistry.registerEntityRenderingHandler(EntityInfectedCreeper.class, (IRenderFactory) new RenderInfectedCreeper(null));
		RenderingRegistry.registerEntityRenderingHandler(EntityNukePrimed.class, (IRenderFactory) new RenderNukePrimed(null));
		RenderingRegistry.registerEntityRenderingHandler(EntityInfectedZombie.class, (IRenderFactory) new RenderInfectedZombie(null));
		RenderingRegistry.registerEntityRenderingHandler(EntityFX.class, (IRenderFactory) new RenderFX(null));
		RenderingRegistry.registerEntityRenderingHandler(EntityRailFX.class, (IRenderFactory) new RenderRailFX(null));
		RenderingRegistry.registerEntityRenderingHandler(EntityInfectedCow.class, (IRenderFactory) new RenderInfectedCow(null));
		
		RenderRegistry.bindTileEntitySpecialRenderer(TileEntityPulverizer.class, new TileEntityPulverizerSpecialRender());
		RenderRegistry.bindTileEntitySpecialRenderer(TileEntityPainter.class, new TileEntityPainterSpecialRender());
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void addTooltip(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) {
		String tip = I18n.format("tooltip." + stack.getUnlocalizedName());
		if (!tip.startsWith("tooltip.")) {
			tooltip.add(ChatFormatting.BLUE + tip + ChatFormatting.RESET);
		}
	}
}
