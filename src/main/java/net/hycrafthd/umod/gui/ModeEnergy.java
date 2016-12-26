package net.hycrafthd.umod.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;

public class ModeEnergy extends ImplGui{

	public GuiEnergy energy;
	
	public ModeEnergy(GuiBase base_gui) {
		super(base_gui);
		this.energy = new GuiEnergy(base_gui.player, base_gui.pos);
		this.energy.setWorldAndResolution(base_gui.mc, base_gui.width, base_gui.height);
	}

	@Override
	public void render(int mouseX, int mouseY) {
		this.energy.drawScreen(mouseX, mouseY, 0);
	}
	
}
