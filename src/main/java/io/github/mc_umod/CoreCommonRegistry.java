package io.github.mc_umod;

import java.lang.reflect.*;
import java.util.ArrayList;

import com.google.common.collect.ObjectArrays;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.*;
import net.minecraft.stats.Achievement;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.network.*;
import net.minecraftforge.fml.common.registry.*;
import net.minecraftforge.oredict.OreDictionary;

public class CoreCommonRegistry implements CommonRegistry {
	
	@Override
	public Achievement[] getAchievementsFromClass(Class<?> clazz) {
		ArrayList<Achievement> achievements = new ArrayList<Achievement>();
		for (Field field : clazz.getFields()) {
			try {
				if (field.getType() == Achievement.class) {
					Achievement ach = (Achievement) field.get(null);
					if (ach == null) {
						continue;
					}
					achievements.add(ach);
				}
			} catch (Exception e) {
			}
		}
		return (Achievement[]) achievements.toArray(new Achievement[achievements.size()]);
	}
	
	@Override
	public void registerAchievementPage(AchievementPage page) {
		AchievementPage.registerAchievementPage(page);
	}
	
	@Override
	public void registerBlock(Block block, String name) {
		registerBlock(block, ItemBlock.class, name);
	}
	
	@Override
	public void registerBlock(Block block, Class<? extends ItemBlock> itemblock, String name, Object... args) {
		try {
			ItemBlock i = null;
			if (itemblock != null) {
				Class<?>[] ctorArgClasses = new Class<?>[args.length + 1];
				ctorArgClasses[0] = Block.class;
				for (int idx = 1; idx < ctorArgClasses.length; idx++) {
					ctorArgClasses[idx] = args[idx - 1].getClass();
				}
				Constructor<? extends ItemBlock> itemCtor = itemblock.getConstructor(ctorArgClasses);
				i = itemCtor.newInstance(ObjectArrays.concat(block, args));
			}
			GameRegistry.register(block.getRegistryName() == null ? block.setRegistryName(name) : block);
			if (i != null) {
				GameRegistry.register(i.setRegistryName(name));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Override
	public void registerEntity(Class<? extends Entity> entityClass, String entityName, int id, Object mod, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates) {
		EntityRegistry.registerModEntity(entityClass, entityName, id, mod, trackingRange, updateFrequency, sendsVelocityUpdates);
	}
	
	@Override
	public void registerEntity(Class<? extends Entity> entityClass, String entityName, int id, Object mod, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates, int eggPrimary, int eggSecondary) {
		EntityRegistry.registerModEntity(entityClass, entityName, id, mod, trackingRange, updateFrequency, sendsVelocityUpdates, eggPrimary, eggSecondary);
	}
	
	@Override
	public void registerEventHandler(Object object) {
		MinecraftForge.EVENT_BUS.register(object);
	}
	
	@Override
	public void registerFuelHandler(IFuelHandler fueldhandler) {
		GameRegistry.registerFuelHandler(fueldhandler);
	}
	
	@Override
	public void registerGuiHandler(Object mod, IGuiHandler handler) {
		NetworkRegistry.INSTANCE.registerGuiHandler(mod, handler);
	}
	
	@Override
	public void registerItem(Item item, String name) {
		GameRegistry.register(item.getRegistryName() == null ? item.setRegistryName(name) : item);
	}
	
	@Override
	public void registerOreDictionary(Item item, String name) {
		OreDictionary.registerOre(name, item);
	}
	
	@Override
	public void registerOreDictionary(Block block, String name) {
		OreDictionary.registerOre(name, block);
	}
	
	@Override
	public void registerOreDictionary(ItemStack stack, String name) {
		OreDictionary.registerOre(name, stack);
	}
	
	@Override
	public void registerRecipeShaped(ItemStack output, Object... obj) {
		GameRegistry.addShapedRecipe(output, obj);
	}
	
	@Override
	public void registerRecipeShapeless(ItemStack output, Object... obj) {
		GameRegistry.addShapelessRecipe(output, obj);
	}
	
	@Override
	public void registerSmelting(ItemStack input, ItemStack output, float xp) {
		GameRegistry.addSmelting(input, output, xp);
	}
	
	@Override
	public void registerTileEntity(Class<? extends TileEntity> clazz, String id) {
		GameRegistry.registerTileEntity(clazz, id);
	}
	
	@Override
	public void registerWorldgenerator(IWorldGenerator generator) {
		registerWorldgenerator(generator, 0);
	}
	
	@Override
	public void registerWorldgenerator(IWorldGenerator generator, int weight) {
		GameRegistry.registerWorldGenerator(generator, weight);
	}
	
}
