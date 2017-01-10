package io.github.mc_umod.ext.ic2;

import io.github.mc_umod.ext.UmodExtension;
import io.github.mc_umod.ext.abs.AbstractOreDictionaryRegistry;
import io.github.mc_umod.ext.abs.AbstractRecipeRegistry;
import net.hycrafthd.corelib.registry.BlockRegistry;
import net.hycrafthd.corelib.registry.TileEntityRegistry;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

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
