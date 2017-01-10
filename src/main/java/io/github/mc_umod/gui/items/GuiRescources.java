package io.github.mc_umod.gui.items;

import io.github.mc_umod.*;
import net.minecraft.util.*;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class GuiRescources extends ResourceLocation {
	
	public GuiRescources(String name) {
		super(UReference.modid, "textures/gui/" + name);
	}
	
}
