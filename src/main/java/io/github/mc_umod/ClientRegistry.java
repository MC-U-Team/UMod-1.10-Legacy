package io.github.mc_umod;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public abstract interface ClientRegistry {
	
	public abstract <T extends Entity> void registerEntityRenderer(Class<T> clazz, IRenderFactory<? super T> rendererFactory);
	
	public abstract void registerKeybinding(KeyBinding key);
	
	public abstract void registerModel(Item item);
	
	public abstract void registerModel(Block block);
	
	public abstract void registerModel(Item item, int meta);
	
	public abstract void registerModel(Block block, int meta);
	
	public abstract void registerModel(Item item, int meta, ModelResourceLocation location);
	
	public abstract void registerModel(Block block, int meta, ModelResourceLocation location);
	
	public abstract void registerModelVariants(Item item, String... names);
	
	public abstract <T extends TileEntity> void registerSpecialTileEntityRenderer(Class<T> clazz, TileEntitySpecialRenderer<? super T> renderer);
}
