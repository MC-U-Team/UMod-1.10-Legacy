package io.github.mc_umod.gui;

import io.github.mc_umod.gui.container.*;
import io.github.mc_umod.gui.items.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.math.*;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class GuiCraftFurnance extends GuiBase {
	
	public GuiCraftFurnance(EntityPlayer player, BlockPos pos) {
		super(new GuiRescources("craftfurn.png"), player, pos, new ContainerCraftFurnace(player, pos));
	}
	
	@Override
	public void addToBox(GuiCombobox box2) {
		box2.getItems().add(ComboboxItem.OUTPUT);
	}
	
	@Override
	public void onIOModeSwitched() {
		// TODO Auto-generated method stub
	}
	
}
