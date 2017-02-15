package io.github.mc_umod.gui.items;

import java.awt.*;
import java.util.*;

import io.github.mc_umod.*;
import io.github.mc_umod.corelib.api.util.*;
import io.github.mc_umod.gui.*;
import io.github.mc_umod.render.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.*;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class GuiCombobox extends ImplGui {
	
	private ArrayList<ComboboxItem> strs = new ArrayList<ComboboxItem>();
	private ComboboxItem slected = ComboboxItem.CHOOSE;
	private boolean extend = false;
	private int x, y, width, height;
	private Runnable runn = null;
	private GLHelper help;
	
	public GuiCombobox(GuiBase base,int x, int y, int width, int height) {
		super(base);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.help = UReference.getClientProxy().getGLHelper();
	}
	@Override
	public void render(int mouseX, int mouseY) {
        Minecraft mc = base_gui.mc;
		RGBA rgb = new RGBA(Color.WHITE);
		this.help.drawGradientRect(x, y, x + width, y + height, rgb, rgb, this.zLevel);
		RGBA rgb2 = new RGBA(Color.GRAY);
		this.help.drawHLine(x, x + width, y, rgb2, this.zLevel);
		this.help.drawHLine(x, x + width, y + height, rgb2, this.zLevel);
		this.help.drawVLine(x, y, y + height, rgb2, this.zLevel);
		this.help.drawVLine(x + width, y, y + height, rgb2, this.zLevel);
		
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
			this.help.drawGradientRect(x + 1, y + height, x + width, y + strs.size() * (fontrenderer.FONT_HEIGHT + 4), rgb, rgb, this.zLevel);
			for (int i = 0; i < strs.size(); i++) {
				ComboboxItem item = strs.get(i);
				int itm = this.y + this.height + ((fontrenderer.FONT_HEIGHT)*i);
				if(mouseX >= this.x + 1 && mouseX <= this.x + this.width - 1 && mouseY >= itm && mouseY < itm + fontrenderer.FONT_HEIGHT){
					this.help.drawGradientRect(this.x + 2, itm, this.x + 2  + (this.width - 3),itm + (fontrenderer.FONT_HEIGHT - 1), rgb2);
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
