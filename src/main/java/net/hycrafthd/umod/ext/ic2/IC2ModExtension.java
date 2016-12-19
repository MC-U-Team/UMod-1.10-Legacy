package net.hycrafthd.umod.ext.ic2;

import net.hycrafthd.corelib.registry.*;
import net.hycrafthd.umod.ext.UmodExtension;
import net.hycrafthd.umod.ext.abs.*;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.event.*;

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
