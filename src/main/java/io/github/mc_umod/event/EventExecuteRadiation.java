package io.github.mc_umod.event;

import java.util.*;

import io.github.mc_umod.*;
import io.github.mc_umod.armor.*;
import io.github.mc_umod.utils.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.potion.*;
import net.minecraftforge.event.entity.living.LivingEvent.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class EventExecuteRadiation {
	
	private HashMap<EntityPlayer, Integer> timer = new HashMap<EntityPlayer, Integer>();
	
	@SubscribeEvent
	public void onUpdateEntity(LivingUpdateEvent event) {
		EntityLivingBase base = event.getEntityLiving();
		if (base.isPotionActive(UPotion.radiationPotion)) {
			
			if (EntityUtils.isInfectedEntity(base))
				return;
			
			if (base instanceof EntityPlayer) {
				EntityPlayer sp = (EntityPlayer) base;
				if (sp.capabilities.isCreativeMode)
					return;
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
					if (!timer.containsKey(sp)) {
						timer.put(sp, 0);
					} else {
						timer.put(sp, timer.get(sp) + 1);
						if (timer.get(sp) >= 10 * 20) {
							sp.inventory.damageArmor(1);
							timer.remove(sp);
						}
					}
					return;
				}
			}
			
			PotionEffect effect = base.getActivePotionEffect(UPotion.radiationPotion);
			base.attackEntityFrom(UDamageSource.radiationDamageSource, effect.getAmplifier() + 0.5F);
		}
	}
	
}
