package io.github.mc_umod.ext.ic2;

import io.github.mc_umod.corelib.core.*;
import io.github.mc_umod.ext.*;
import io.github.mc_umod.ext.abs.*;
import net.minecraft.block.*;
import net.minecraftforge.fml.common.event.*;

public class IC2ModExtension extends UmodExtension {
	
	public CommonRegistry reg;
	public static Block transformer;

	@Override
	public AbstractOreDictionaryRegistry oredirctionary() {
		return new IC2OreDictionary();
	}
	
	
	@Override
	public void init(FMLInitializationEvent evt) {
		transformer = new BlockIC2Transformer().setUnlocalizedName("ic22uetransformer");
		this.reg.registerBlock(transformer, "ic22uetransformer");
	}
	
	@Override
	public void postinit(FMLPostInitializationEvent evt) {
		this.reg.registerTileEntity(TileEntityIC2Transformer.class,"ic2trafo");
	}
	
	@Override
	public AbstractRecipeRegistry recipes() {
		return new IC2Recipes();
	}
	
}
