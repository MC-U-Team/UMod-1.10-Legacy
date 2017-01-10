package io.github.mc_umod.gui.mode;

import java.awt.*;

import io.github.mc_umod.gui.*;
import io.github.mc_umod.gui.items.*;
import io.github.mc_umod.utils.*;
import net.hycrafthd.corelib.util.*;
import net.minecraft.client.renderer.*;

public class ModeColor extends ImplGui{

	private GuiCheckbox check;
	
	public ModeColor(GuiBase base_gui) {
		super(base_gui);
		int k = (base_gui.width - base_gui.xSize) / 2;
		int l = (base_gui.height - base_gui.ySize) / 2;
		check = new GuiCheckbox(base_gui,k + 5, l + 5, 10, 10, new RGBA(Color.white), new RGBA(Color.DARK_GRAY));
		check.setTooltip(new StringMethod() {
			
			@Override
			public String getString() {
				if (check.isSelceted()) {
					return "Item Input List is visible";
				} else {
					return "Item Input List is hidden";
				}
			}
		});
	}

	@Override
	public void render(int mouseX, int mouseY) {
		int k = base_gui.guiLeft;
		int l = base_gui.guiTop;
		GlStateManager.enableDepth();
		GlStateManager.popMatrix();
		GlStateManager.pushMatrix();
		check.render(mouseX, mouseY);
		RenderHelper.enableGUIStandardItemLighting();
		GlStateManager.popMatrix();
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) k, (float) l, 0.0F);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.enableRescaleNormal();
	}
	
	@Override
	public void onClick(int mouseX, int mouseY) {
		check.onClick(mouseX, mouseY);
	}
}
