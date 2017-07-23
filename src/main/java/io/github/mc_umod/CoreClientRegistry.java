package io.github.mc_umod;

import io.github.mc_umod.util.*;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.*;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class CoreClientRegistry implements ClientRegistry {
	
	@Override
	public <T extends Entity> void registerEntityRenderer(Class<T> clazz, IRenderFactory<? super T> rendererFactory) {
		RenderingRegistry.registerEntityRenderingHandler(clazz, rendererFactory);
	}
	
	@Override
	public void registerKeybinding(KeyBinding key) {
		net.minecraftforge.fml.client.registry.ClientRegistry.registerKeyBinding(key);
	}
	
	@Override
	public void registerModel(Item item) {
		registerModel(item, 0);
	}
	
	@Override
	public void registerModel(Block block) {
		registerModel(block, 0);
	}
	
	@Override
	public void registerModel(Item item, int meta) {
		registerModel(item, meta, new ModelResourceLocation(ItemUtil.getRegistryName(item), "inventory"));
	}
	
	@Override
	public void registerModel(Block block, int meta) {
		registerModel(block, meta, new ModelResourceLocation(BlockUtil.getRegistryName(block), "inventory"));
	}
	
	@Override
	public void registerModel(Item item, int meta, ModelResourceLocation location) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, location);
	}
	
	@Override
	public void registerModel(Block block, int meta, ModelResourceLocation location) {
		registerModel(Item.getItemFromBlock(block), meta, location);
	}
	
	@Override
	public void registerModelVariants(Item item, String... names) {
		ResourceLocation[] res = new ResourceLocation[names.length];
		String modid = item.getRegistryName().getResourceDomain();
		for (int i = 0; i < names.length; i++) {
			res[i] = new ResourceLocation(modid, names[i]);
		}
		ModelBakery.registerItemVariants(item, res);
	}
	
	@Override
	public <T extends TileEntity> void registerSpecialTileEntityRenderer(Class<T> clazz, TileEntitySpecialRenderer<? super T> renderer) {
		net.minecraftforge.fml.client.registry.ClientRegistry.bindTileEntitySpecialRenderer(clazz, renderer);
	}
	
}
