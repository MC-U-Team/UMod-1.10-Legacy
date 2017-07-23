package io.github.mc_umod;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.*;
import net.minecraft.stats.Achievement;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.network.IGuiHandler;

public abstract interface CommonRegistry {
	
	public abstract Achievement[] getAchievementsFromClass(Class<?> clazz);
	
	public abstract void registerAchievementPage(AchievementPage page);
	
	public abstract void registerBlock(Block block, String name);
	
	public abstract void registerBlock(Block block, Class<? extends ItemBlock> itemblock, String name, Object... args);
	
	public abstract void registerEntity(Class<? extends Entity> entityClass, String entityName, int id, Object mod, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates);
	
	public abstract void registerEntity(Class<? extends Entity> entityClass, String entityName, int id, Object mod, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates, int eggPrimary, int eggSecondary);
	
	public abstract void registerEventHandler(Object object);
	
	public abstract void registerFuelHandler(IFuelHandler fueldhandler);
	
	public abstract void registerGuiHandler(Object mod, IGuiHandler handler);
	
	public abstract void registerItem(Item item, String name);
	
	public abstract void registerOreDictionary(Item item, String name);
	
	public abstract void registerOreDictionary(Block block, String name);
	
	public abstract void registerOreDictionary(ItemStack stack, String name);
	
	public abstract void registerRecipeShaped(ItemStack output, Object... obj);
	
	public abstract void registerRecipeShapeless(ItemStack output, Object... obj);
	
	public abstract void registerSmelting(ItemStack input, ItemStack output, float xp);
	
	public abstract void registerTileEntity(Class<? extends TileEntity> clazz, String id);
	
	public abstract void registerWorldgenerator(IWorldGenerator generator);
	
	public abstract void registerWorldgenerator(IWorldGenerator generator, int weight);
	
}
