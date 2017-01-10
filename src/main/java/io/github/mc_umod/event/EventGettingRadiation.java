package io.github.mc_umod.event;

import java.util.*;

import io.github.mc_umod.*;
import io.github.mc_umod.api.*;
import io.github.mc_umod.armor.*;
import io.github.mc_umod.enumtype.*;
import io.github.mc_umod.utils.*;
import net.minecraft.block.*;
import net.minecraft.block.state.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.potion.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraftforge.event.entity.living.LivingEvent.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class EventGettingRadiation {
	
	@SubscribeEvent
	public void onUpdate(LivingUpdateEvent event) {
		EntityLivingBase base = event.getEntityLiving();
		
		if (base instanceof EntityPlayer) {
			EntityPlayer sp = (EntityPlayer) base;
			if (sp.capabilities.isCreativeMode)
				return;
		}
		
		World world = base.worldObj;
		
		double x = base.posX;
		double y = base.posY;
		double z = base.posZ;
		
		if (GenerationUtils.getBiomeGenForCoords(world, base.getPosition(), UBiome.infectedBiomBase)) {
			addPotion(base, 0);
			return;
		} else {
			
			double range = 3;
			
			double xRange = range;
			double yRange = range;
			double zRange = range;
			
			for (double xPos = x - xRange; xPos <= x + xRange; xPos++) {
				for (double yPos = y - yRange; yPos <= y + yRange; yPos++) {
					for (double zPos = z - zRange; zPos <= z + zRange; zPos++) {
						if (world.isRemote)
							continue;
						BlockPos pos = new BlockPos(xPos, yPos, zPos);
						IBlockState blockcks = world.getBlockState(pos);
						Block block = blockcks.getBlock();
						if (block == UBlocks.ores || block == UBlocks.netherores || block == UBlocks.blocks) {
							EnumTypeBaseStuff type = EnumTypeBaseStuff.byMetadata(block.getMetaFromState(blockcks));
							if (type.getName() == "uran") {
								if (new Random().nextInt(50) == 0) {
									addPotion(base, 1);
								}
							}
						} else if (block instanceof IInfectedBlock) {
							if (new Random().nextInt(200) == 0) {
								addPotion(base, 0);
							}
						}
					}
				}
			}
		}
	}
	
	private void addPotion(EntityLivingBase base, int amplifier) {
		if (base instanceof EntityPlayer) {
			EntityPlayer sp = (EntityPlayer) base;
			boolean full = false;
			for (ItemStack armor : sp.inventory.armorInventory) {
				if (armor != null && (armor.getItem() instanceof ArmorRadiation)) {
					full = true;
				} else {
					full = false;
					break;
				}
			}
			if (full) {
				base.addPotionEffect(new PotionEffect(UPotion.radiationPotion, 10, amplifier, false, false));
				return;
			}
		}
		base.addPotionEffect(new PotionEffect(UPotion.radiationPotion, 10, amplifier, false, true));
	}
}
