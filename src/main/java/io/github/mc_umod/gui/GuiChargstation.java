package io.github.mc_umod.gui;

import io.github.mc_umod.*;
import io.github.mc_umod.gui.container.*;
import io.github.mc_umod.gui.items.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class GuiChargstation extends GuiBase {
	
	public GuiChargstation(EntityPlayer player, BlockPos pos) {
		super(new ResourceLocation(UReference.modid, "textures/gui/charge.png"), player, pos, new ContainerChargeStation(player, pos));
	}
	
	@Override
	public void addToBox(GuiCombobox box2) {
	}
	
	@Override
	public void onIOModeSwitched() {
	}
}
