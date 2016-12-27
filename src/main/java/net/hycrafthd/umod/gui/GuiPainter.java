package net.hycrafthd.umod.gui;

import java.awt.Color;
import java.io.IOException;

import org.lwjgl.input.Keyboard;

import net.hycrafthd.corelib.util.RGBA;
import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.api.render.ISliderTile;
import net.hycrafthd.umod.gui.container.ContainerPainter;
import net.hycrafthd.umod.gui.mode.ModeNormal;
import net.hycrafthd.umod.utils.StringMethod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.*;
import net.minecraft.util.math.BlockPos;

public class GuiPainter extends GuiBase implements ISliderTile {
	
	public GuiPainter(EntityPlayer player, BlockPos pos) {
		super(new GuiRescources("painter.png"), player, pos, new ContainerPainter(player, pos));
	}
	
	public GuiSlider red, green, blue, sat;
	
	public void initGui() {
		super.initGui();
		int x = this.guiLeft + 36;
		int y = this.guiTop;
		red = new GuiSlider(this,x, y + 11, new RGBA(Color.red).setAlpha(155), new RGBA(Color.black), new RGBA(Color.WHITE), 0);
		red.setStringRet(new StringMethod() {
			
			@Override
			public String getString() {
				return "Red Color: " + red.getValue();
			}
		});
		green = new GuiSlider(this,x, y + 32, new RGBA(Color.green).setAlpha(155), new RGBA(Color.black), new RGBA(Color.WHITE), 1);
		green.setStringRet(new StringMethod() {
			
			@Override
			public String getString() {
				return "Green Color: " + green.getValue();
			}
		});
		blue = new GuiSlider(this,x, y + 53, new RGBA(Color.blue).setAlpha(155), new RGBA(Color.black), new RGBA(Color.WHITE), 2);
		blue.setStringRet(new StringMethod() {
			
			@Override
			public String getString() {
				return "Blue Color: " + blue.getValue();
			}
		});
		sat = new GuiSlider(this,x, y + 67, new RGBA(Color.WHITE).setAlpha(155), new RGBA(Color.black).setAlpha(155), new RGBA(Color.WHITE).setAlpha(155), 3);
		sat.setStringRet(new StringMethod() {
			
			@Override
			public String getString() {
				return "Alpha of Color: " + sat.getValue();
			}
		});
	}
	
	@Override
	public void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		
		GlStateManager.popMatrix();
		if (this.activeTab.getGui() instanceof ModeNormal) {
			red.render(mouseX, mouseY);
			green.render(mouseX, mouseY);
			blue.render(mouseX, mouseY);
			sat.render(mouseX, mouseY);
			this.help.drawGradientRect(297, 53 + this.guiTop, 315, 53 + this.guiTop + 19, new RGBA(red.getValue() * 255 / 100, green.getValue() * 255 / 100, blue.getValue() * 255 / 100, sat.getValue() * 255 / 100));
			if (Keyboard.isKeyDown(UReference.getClientProxy().getInfoBinding().getKeyCode())) {
				red.renderOverlay(mouseX, mouseY);
				green.renderOverlay(mouseX, mouseY);
				blue.renderOverlay(mouseX, mouseY);
				sat.renderOverlay(mouseX, mouseY);
				if (mouseX > 297 && mouseX < 315 && mouseY > 53 + this.guiTop && mouseY < 53 + this.guiTop + 19) {
					FontRenderer rend = Minecraft.getMinecraft().getRenderManager().getFontRenderer();
					String blued = "Blue: " + blue.getValue();
					String redd = "Red: " + red.getValue();
					String greend = "Green: " + green.getValue();
					int with = Math.max(Math.max(rend.getStringWidth(redd), rend.getStringWidth(greend)), rend.getStringWidth(blued));
					this.help.drawGradientRect(mouseX, mouseY, mouseX + with + 12, mouseY + 16 * 3 + 4, new RGBA(new Color(red.getValue() * 255 / 100, green.getValue() * 255 / 100, blue.getValue() * 255 / 100, sat.getValue() * 255 / 100).darker()));
					rend.drawString(redd, mouseX + 6, mouseY + 6, 0xFF0000);
					rend.drawString(greend, mouseX + 6, mouseY + 22, 0x00FF00);
					rend.drawString(blued, mouseX + 6, mouseY + 38, 0x0000FF);
				}
			}
		}
		RenderHelper.enableGUIStandardItemLighting();
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) this.guiLeft, (float) this.guiTop, 0.0F);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.enableRescaleNormal();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	}
	
	@Override
	public void addToBox(GuiCombobox box2) {
		box2.getItems().add("Input");
		
	}
	
	@Override
	public void onIOModeSwitched() {
		
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		super.keyTyped(typedChar, keyCode);
		
	}
	
	@Override
	public void handelMouseInput(int mouseX, int mouseY) {
		if (this.activeTab.getGui() instanceof ModeNormal) {
			red.onClick(mouseX, mouseY);
			green.onClick(mouseX, mouseY);
			blue.onClick(mouseX, mouseY);
			sat.onClick(mouseX, mouseY);
		}
	}
	
	@Override
	public void onMouseClickMoved(int mouseX, int mouseY) {
		handelMouseInput(mouseX, mouseY);
	}
	
	@Override
	public void set(int id, int val) {
		switch (id) {
		case 0:
			red.setValue(val);
			return;
		case 1:
			green.setValue(val);
			return;
		case 2:
			blue.setValue(val);
			return;
		case 3:
			sat.setValue(val);
			return;
		}
	}
}
