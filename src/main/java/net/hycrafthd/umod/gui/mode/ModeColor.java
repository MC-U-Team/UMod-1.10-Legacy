package net.hycrafthd.umod.gui.mode;

import java.awt.Color;

import net.hycrafthd.corelib.util.RGBA;
import net.hycrafthd.umod.gui.*;
import net.hycrafthd.umod.utils.StringMethod;

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
		check.render(mouseX, mouseY);
	}
	
	@Override
	public void onClick(int mouseX, int mouseY) {
		check.onClick(mouseX, mouseY);
	}
}
