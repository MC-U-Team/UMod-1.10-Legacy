package net.hycrafthd.umod.ext.ic2;

import ic2.api.recipe.*;
import ic2.core.block.ITeBlock;
import ic2.core.block.TeBlockRegistry;
import net.hycrafthd.corelib.registry.BlockRegistry;
import net.hycrafthd.corelib.registry.TileEntityRegistry;
import net.hycrafthd.corelib.util.ItemStackUtil;
import net.hycrafthd.umod.*;
import net.hycrafthd.umod.enumtype.EnumTypeBaseStuff;
import net.hycrafthd.umod.ext.*;
import net.hycrafthd.umod.ext.abs.*;
import net.hycrafthd.umod.utils.ModRegistryUtils;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.oredict.OreDictionary;

public class IC2ModExtension extends UmodExtension {
	
	@Override
	public AbstractOreDictionaryRegistry oredirctionary() {
		return new IC2OreDictionary();
	}
	
	public static Block transformer;
	
	@Override
	public void init(FMLInitializationEvent evt) {
		transformer = new BlockIC2Transformer().setUnlocalizedName("ic22uetransformer");
		BlockRegistry.register(transformer, "ic22uetransformer");
	}
	
	@Override
	public void postinit(FMLPostInitializationEvent evt) {
		TileEntityRegistry.register(TileEntityIC2Transformer.class);
	}
	
	@Override
	public AbstractRecipeRegistry recipes() {
		return new IC2Recipes();
	}
	
}
