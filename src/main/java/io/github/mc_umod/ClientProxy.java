package io.github.mc_umod;

import org.lwjgl.input.Keyboard;

import io.github.mc_umod.block.deco.*;
import io.github.mc_umod.block.machine.BlockSolarPanel.EnumTypeSolarPanel;
import io.github.mc_umod.entity.*;
import io.github.mc_umod.entity.rail.EntityRailFX;
import io.github.mc_umod.entity.render.*;
import io.github.mc_umod.entity.render.rail.RenderRailFX;
import io.github.mc_umod.enumtype.*;
import io.github.mc_umod.ext.ExtensionList;
import io.github.mc_umod.render.*;
import io.github.mc_umod.tileentity.*;
import io.github.mc_umod.wavefront.assets.ObjRenderRegistry;
import net.minecraft.block.BlockFurnace;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class ClientProxy extends CommonProxy {
	
	private KeyBinding info;
	private Minecraft mc = Minecraft.getMinecraft();
	private ObjRenderRegistry regs;
	private ModelRenderHelper MODEL_HELPER;
	private ClientRegistry reg;
	
	@Override
	public void init() {
		this.reg = new CoreClientRegistry();
		this.info = new KeyBinding("Information", Keyboard.KEY_I, "UMod");
		this.regs = new ObjRenderRegistry();
		((IReloadableResourceManager)Minecraft.getMinecraft().getResourceManager()).registerReloadListener(this.regs);
	}
		
	public ModelRenderHelper getModelRenderHelper() {
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
		String[] rs3 = new String[EnumTypeBaseStuff.values().length];
		String[] rs5 = new String[EnumTypeBaseStuff.values().length];
		String[] rs6 = new String[EnumTypeBaseStuff.values().length];
		String[] rs7 = new String[EnumTypeBaseStuff.values().length];
		String[] rs8 = new String[EnumTypeBaseStuff.values().length];
		for (int i = 0; i < EnumTypeBaseStuff.values().length; i++) {
			this.reg.registerModel(UItems.dusts, i, new ModelResourceLocation(UReference.resource + "dust" + EnumTypeBaseStuff.byMetadata(i).getName(), "inventory"));
			this.reg.registerModel(UItems.ingots, i, new ModelResourceLocation(UReference.resource + "ingot" + EnumTypeBaseStuff.byMetadata(i).getName(), "inventory"));
			this.reg.registerModel(UBlocks.blocks, i, new ModelResourceLocation(UReference.resource + "block" + EnumTypeBaseStuff.byMetadata(i).getName(), "inventory"));
			this.reg.registerModel(UBlocks.ores, i, new ModelResourceLocation(UReference.resource + "ore" + EnumTypeBaseStuff.byMetadata(i).getName(), "inventory"));
			this.reg.registerModel(UBlocks.netherores, i, new ModelResourceLocation(UReference.resource + "netherore" + EnumTypeBaseStuff.byMetadata(i).getName(), "inventory"));
			rs3[i] = "dust" + EnumTypeBaseStuff.byMetadata(i).getName();
			rs5[i] = "ingot" + EnumTypeBaseStuff.byMetadata(i).getName();
			rs6[i] = "block" + EnumTypeBaseStuff.byMetadata(i).getName();
			rs7[i] = "netherore" + EnumTypeBaseStuff.byMetadata(i).getName();
			rs8[i] = "ore" + EnumTypeBaseStuff.byMetadata(i).getName();
		}
		this.reg.registerModelVariants(UItems.dusts, rs3);
		this.reg.registerModelVariants(UItems.ingots, rs5);
		this.reg.registerModelVariants(Item.getItemFromBlock(UBlocks.blocks), rs6);
		this.reg.registerModelVariants(Item.getItemFromBlock(UBlocks.netherores), rs7);
		this.reg.registerModelVariants(Item.getItemFromBlock(UBlocks.ores), rs8);
		this.reg.registerModel(UItems.cdust);
		
		// Transformer
		String[] rs4 = new String[EnumTypeTransformer.values().length];
		for (int i = 0; i < EnumTypeTransformer.values().length; i++) {
			this.reg.registerModel(UItems.transformer, i, new ModelResourceLocation(UReference.resource + "transformer" + EnumTypeTransformer.byMetadata(i).getName(), "inventory"));
			rs4[i] = "transformer" + EnumTypeTransformer.byMetadata(i).getName();
		}
		this.reg.registerModelVariants(UItems.transformer, rs4);
		
		this.reg.registerModel(UItems.manganoxid);
		
		// Pulverizer
		this.reg.registerModel(UBlocks.charge);
		this.reg.registerModel(UBlocks.pulver, 0, new ModelResourceLocation(UReference.resource + "pulver", "inventory"));
		this.reg.registerModel(UBlocks.craftfurnance);
		this.reg.registerModel(UBlocks.painter);
		this.reg.registerModel(UBlocks.craftfurnance);
		
		// Energy
		this.reg.registerModel(UItems.battery);
		this.reg.registerModel(UItems.copper_wire);
		this.reg.registerModel(UItems.copper_coil);
		this.reg.registerModel(UItems.transformer);
		this.reg.registerModel(UItems.thicker_copper_wire);
		this.reg.registerModel(UItems.petrol);
		
		// magic
		this.reg.registerModel(UBlocks.magic_crafter);
		this.reg.registerModel(UItems.magic_diamond);
		this.reg.registerModel(UItems.magic_ingot);
		this.reg.registerModel(UItems.charged_quartz);
		this.reg.registerModel(UItems.magic_brew);
		this.reg.registerModel(UBlocks.magic_glass);
		this.reg.registerModel(UItems.magic_bottle);
		
		// SolarPanel
		String[] rs1 = new String[EnumTypeSolarPanel.values().length];
		for (int i = 0; i < EnumTypeSolarPanel.values().length; i++) {
			this.reg.registerModel(UBlocks.solarpanel, i, new ModelResourceLocation(UReference.resource + "solarpanel" + EnumTypeSolarPanel.byMetadata(i).getName(), "inventory"));
			rs1[i] = "solarpanel" + EnumTypeSolarPanel.byMetadata(i).getName();
		}
		this.reg.registerModelVariants(Item.getItemFromBlock(UBlocks.solarpanel), rs1);
		
		// Armor
		this.reg.registerModel(UArmor.radiationSuitHelmet);
		this.reg.registerModel(UArmor.radiationSuitChestplate);
		this.reg.registerModel(UArmor.radiationSuitLeggings);
		this.reg.registerModel(UArmor.radiationSuitBoots);
		
		this.reg.registerModel(UArmor.emeraldHelmet);
		this.reg.registerModel(UArmor.emeraldChestplate);
		this.reg.registerModel(UArmor.emeraldLeggings);
		this.reg.registerModel(UArmor.emeraldBoots);
		
		this.reg.registerModel(UArmor.magicHelmet);
		this.reg.registerModel(UArmor.magicChestplate);
		this.reg.registerModel(UArmor.magicLeggings);
		this.reg.registerModel(UArmor.magicBoots);
		
		// Infected
		this.reg.registerModel(UBlocks.infectedGrass);
		this.reg.registerModel(UBlocks.infectedDirt);
		this.reg.registerModel(UBlocks.infectedLog);
		this.reg.registerModel(UBlocks.infectedLeave);
		this.reg.registerModel(UBlocks.infectedPlank);
		this.reg.registerModel(UBlocks.infectedSapling);
		this.reg.registerModel(UBlocks.infectedFruit);
		this.reg.registerModel(UItems.infectedcrop);
		this.reg.registerModel(UItems.infectedleather);
		this.reg.registerModel(UItems.infectedbeef);
		this.reg.registerModel(UItems.infectedmilk);
		
		this.reg.registerModel(UItems.acid);
		this.reg.registerModel(UItems.plastic);
		this.reg.registerModel(UBlocks.oilsand);
		this.reg.registerModel(UBlocks.oilglass);
		
		this.reg.registerModel(UBlocks.infestedCleaner);
		
		// Pipes
		this.reg.registerModel(UBlocks.medium_voltage_cable);
		this.reg.registerModel(UBlocks.high_voltage_cable);
		this.reg.registerModel(UBlocks.low_voltage_cable);
		
		// Normal Blocks
		this.reg.registerModel(UBlocks.nuke);
		this.reg.registerModel(UBlocks.rail);
		
		// Stairs
		for (BlockStairCreator creator : UBlocks.stonestairs) {
			this.reg.registerModel(creator.getStair());
		}
		
		for (BlockStairCreator creator : UBlocks.woolstairs) {
			this.reg.registerModel(creator.getStair());
		}
		
		for (BlockStairCreator creator : UBlocks.claystairs) {
			this.reg.registerModel(creator.getStair());
		}
		
		// Slabs
		for (BlockSlabCreator creator : UBlocks.stoneslabs) {
			this.reg.registerModel(creator.getSlab());
		}
		
		// Backpack
		String[] rs2 = new String[EnumTypeBackPack.values().length];
		for (int i = 0; i < EnumTypeBackPack.values().length; i++) {
			this.reg.registerModel(UItems.backpack, i, new ModelResourceLocation(UReference.resource + "backpack" + EnumTypeBackPack.byMetadata(i).getName(), "inventory"));
			rs2[i] = "backpack" + EnumTypeBackPack.byMetadata(i).getName();
		}
		this.reg.registerModelVariants(UItems.backpack, rs2);
		
		// Tools
		this.reg.registerModel(UTools.emeraldAxe);
		this.reg.registerModel(UTools.emeraldPickaxe);
		this.reg.registerModel(UTools.emeraldSword);
		this.reg.registerModel(UTools.emeraldSpade);
		this.reg.registerModel(UTools.emeraldHoe);
		
		this.reg.registerModel(UTools.magicAxe);
		this.reg.registerModel(UTools.magicPickaxe);
		this.reg.registerModel(UTools.magicShovel);
		this.reg.registerModel(UTools.magicSword);
		this.reg.registerModel(UTools.magicHoe);
		
		// Keybinding
		this.reg.registerKeybinding(info);
		
		this.MODEL_HELPER = new ModelRenderHelper(mc.getRenderItem().getItemModelMesher(), mc.getTextureManager(), mc.getItemColors());
	}
	
	@Override
	public void registerRenderer() {
		ExtensionList.onClientProxy();

		RenderFX.register(TileEntityEnergyPannel.class, new EnergyPannelRender());
		RenderFX.register(TileEntityCable.class, new CabelRender());
		RenderFX.register(TileEntityItemPipe.class, new ItemPipeRender());
		
		net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler(EntityInfectedCreeper.class, (IRenderFactory) new RenderInfectedCreeper(null));
		net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler(EntityNukePrimed.class, (IRenderFactory) new RenderNukePrimed(null));
		net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler(EntityInfectedZombie.class, (IRenderFactory) new RenderInfectedZombie(null));
		net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler(EntityFX.class, (IRenderFactory) new RenderFX(null));
		net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler(EntityRailFX.class, (IRenderFactory) new RenderRailFX(null));
		net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler(EntityInfectedCow.class, (IRenderFactory) new RenderInfectedCow(null));
		net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler(EntityGenerator.class, (IRenderFactory) new RenderGenerator(null));

		this.reg.registerSpecialTileEntityRenderer(TileEntityPulverizer.class, new TileEntityPulverizerSpecialRender());
		this.reg.registerSpecialTileEntityRenderer(TileEntityPainter.class, new TileEntityPainterSpecialRender());
	}
}
