package io.github.mc_umod.gui;

import io.github.mc_umod.UReference;
import io.github.mc_umod.gui.container.ContainerChargeStation;
import io.github.mc_umod.gui.items.GuiCombobox;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
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
