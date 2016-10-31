package net.hycrafthd.umod.ext.ic2;

import ic2.api.recipe.*;
import net.hycrafthd.corelib.util.ItemStackUtil;
import net.hycrafthd.umod.*;
import net.hycrafthd.umod.enumtype.EnumTypeBaseStuff;
import net.hycrafthd.umod.ext.*;
import net.hycrafthd.umod.ext.abs.*;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.oredict.OreDictionary;

public class IC2ModExtension extends UmodExtension {
	
	@Override
	public AbstractOreDictionaryRegistry oredirctionary() {
		return new IC2OreDictionary();
	}
	
	@Override
	public AbstractRecipeRegistry recipes() {
		return new IC2Recipes();
	}
	
}
