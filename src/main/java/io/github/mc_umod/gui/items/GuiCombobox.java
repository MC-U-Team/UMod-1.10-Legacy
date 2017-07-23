package io.github.mc_umod.gui.items;

import java.awt.Color;
import java.util.ArrayList;

import io.github.mc_umod.gui.GuiBase;
import io.github.mc_umod.renderapi.draw.*;
import io.github.mc_umod.util.RGBA;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class GuiCombobox extends ImplGui {
	
	private ArrayList<ComboboxItem> strs = new ArrayList<ComboboxItem>();
	private ComboboxItem slected = ComboboxItem.CHOOSE;
	private boolean extend = false;
	private int x, y, width, height;
	private Runnable runn = null;
	
	public GuiCombobox(GuiBase base,int x, int y, int width, int height) {
		super(base);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	@Override
	public void render(int mouseX, int mouseY) {
        Minecraft mc = base_gui.mc;
		RGBA rgb = new RGBA(Color.WHITE);
		new Quad(x, y, x + width, y + height, rgb);
		RGBA rgb2 = new RGBA(Color.GRAY);
		new HLine(x, width, y, rgb2);
		new HLine(x, width, y + height, rgb2);
		new VLine(x, y, height, rgb2);
		new VLine(x + width, y, height, rgb2);
		
		FontRenderer fontrenderer = mc.fontRendererObj;
		if (!extend) {
			mc.getTextureManager().bindTexture(new GuiRescources("menudown.png"));
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
			GlStateManager.blendFunc(770, 771);
			drawModalRectWithCustomSizedTexture(x + width - 15, y + height / 2 - 4, 0, 0, 15, 9, 15, 9);
			fontrenderer.drawString(slected.item, x + 3, y + 2, slected.color.toAWTColor().getRGB());
		} else {
			mc.getTextureManager().bindTexture(new GuiRescources("menuup.png"));
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
			GlStateManager.blendFunc(770, 771);
			drawModalRectWithCustomSizedTexture(x + width - 15, y + height / 2 - 4, 0, 0, 15, 9, 15, 9);
			new Quad(x + 1, y + height, x + width, y + strs.size() * (fontrenderer.FONT_HEIGHT + 4), rgb);
			for (int i = 0; i < strs.size(); i++) {
				ComboboxItem item = strs.get(i);
				int itm = this.y + this.height + ((fontrenderer.FONT_HEIGHT)*i);
				if(mouseX >= this.x + 1 && mouseX <= this.x + this.width - 1 && mouseY >= itm && mouseY < itm + fontrenderer.FONT_HEIGHT){
					new Quad(this.x + 2, itm, this.x + 2  + (this.width - 3),itm + (fontrenderer.FONT_HEIGHT - 1), rgb2);
				}
				fontrenderer.drawString(item.item, x + 3, y + height + 9 * i, item.color.toAWTColor().getRGB());
			}
		}
	}
	
	public ArrayList<ComboboxItem> getItems() {
		return strs;
	}
	
	public boolean isMouseover(int x, int y) {
		return x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.height;
	}
	
	@Override
	public void onClick(int x, int y) {
		if (extend) {
			if (x >= this.x && x <= this.x + this.width && y >= this.y + this.height) {
				for (int i = 0; i < strs.size(); i++) {
					if (y <= this.y + this.height + (9 * (i + 1))) {
						setSelected(i);
						extend = false;
						if (runn != null) {
							runn.run();
						}
						return;
					}
				}
			}
		}
		if (isMouseover(x, y)) {
			extend = !extend;
		}
		
	}
	
	public void setOnListClicked(Runnable run) {
		runn = run;
	}
	
	public void setSelected(int i) {
		slected = strs.get(i);
	}
	
	public int getSelceted() {
		return strs.indexOf(slected);
	}
}
