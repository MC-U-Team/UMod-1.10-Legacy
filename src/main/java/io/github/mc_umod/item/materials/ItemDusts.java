package io.github.mc_umod.item.materials;

import java.util.List;

import io.github.mc_umod.enumtype.EnumTypeBaseStuff;
import io.github.mc_umod.item.ItemBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.*;

public class ItemDusts extends ItemBase {
	
	public ItemDusts() {
		super();
		this.hasSubtypes = true;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		EnumTypeBaseStuff type = EnumTypeBaseStuff.byMetadata(stack.getMetadata());
		return "item.dust" + type.getName();
	}
	
	@Override
	public int getMetadata(int damage) {
		return damage;
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs creativetab, List list) {
		for (int i = 0; i < EnumTypeBaseStuff.values().length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}
	
}
