package io.github.mc_umod.gui.mode;

import io.github.mc_umod.gui.GuiBase;
import io.github.mc_umod.gui.GuiEnergy;
import io.github.mc_umod.gui.items.ImplGui;
import net.minecraft.client.renderer.GlStateManager;

public class ModeEnergy extends ImplGui{

	public GuiEnergy energy;
	
	public ModeEnergy(GuiBase base_gui) {
		super(base_gui);
		this.energy = new GuiEnergy(base_gui.player, base_gui.pos,false);
		this.energy.setWorldAndResolution(base_gui.mc, base_gui.width, base_gui.height);
	}

	@Override
	public void render(int mouseX, int mouseY) {
		int k = (base_gui.width - base_gui.xSize) / 2;
		int l = (base_gui.height - base_gui.ySize) / 2;
		GlStateManager.popMatrix();
		this.energy.drawScreen(mouseX, mouseY, 0);
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) k, (float) l, 0.0F);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.enableRescaleNormal();
	}
	
}
