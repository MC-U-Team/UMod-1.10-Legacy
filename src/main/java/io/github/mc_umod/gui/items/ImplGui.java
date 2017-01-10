package io.github.mc_umod.gui.items;

import io.github.mc_umod.gui.*;
import net.minecraft.client.gui.*;

public abstract class ImplGui extends Gui{
	
	public static final ImplGui NULL = new ImplGui(null) {@Override public void render(int mouseX, int mouseY) {}};
	
	protected GuiBase base_gui;
	
	public ImplGui(GuiBase base_gui){
		this.base_gui = base_gui;
	}
	
	public abstract void render(int mouseX,int mouseY);
	
	public void renderOverlay(int mouseX,int mouseY){}
	
	public void onClick(int mouseX,int mouseY){}
	
	public void onDrag(int mouseX,int mouseY){}
}
