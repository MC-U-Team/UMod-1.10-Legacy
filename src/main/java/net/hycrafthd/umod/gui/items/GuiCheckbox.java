package net.hycrafthd.umod.gui.items;

import java.awt.Color;

import com.sun.javafx.scene.traversal.Hueristic2D;

import net.hycrafthd.corelib.util.RGBA;
import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.gui.*;
import net.hycrafthd.umod.render.GLHelper;
import net.hycrafthd.umod.utils.StringMethod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;

public class GuiCheckbox extends ImplGui {
	
	private double x, width, y, height;
	private RGBA rgb, hover;
	private boolean isSelected;
	private StringMethod ret;
	private Runnable run;
	private GLHelper help;
	
	public GuiCheckbox(GuiBase base,double x, double y, double width, double height, RGBA rgb, RGBA hover) {
		super(base);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.rgb = rgb;
		this.hover = hover;
		this.help = UReference.getClientProxy().getGLHelper();
	}
	
	public boolean isMouseOver(int x, int y) {
		return x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.height;
	}
	
	@Override
	public void render(int x, int y) {
        Minecraft mc = base_gui.mc;
		RGBA use = null;
		FontRenderer fontobj = mc.fontRendererObj;
		this.help.drawFrame(this.x, this.y, this.width, this.height, new RGBA(Color.black), this.zLevel);
		if (isMouseOver(x, y)) {
			use = hover;
			this.help.drawGradientRect(this.x, this.y, this.x + this.width, this.y + this.height, use, this.zLevel);
			if (isSelected) {
				fontobj.drawString("X", (int) Math.round((float) this.x + this.width / 2) - 2, (int) Math.round((float) this.y + this.height / 2) - 4, Color.RED.getRGB());
			}
			if (hasTooltip()) {
				this.help.drawGradientRect(x, y, x + getWidth(fontobj), y + getHeight(fontobj), new RGBA(Color.white), this.zLevel);
				if (this.hasMoreLines()) {
					String[] str = ret.getString().split("\n");
					for (int i = 0; i < str.length; i++)
						fontobj.drawString(str[i], x + 2, y + 2 + (i * 16), 0x000000);
				} else {
					fontobj.drawString(ret.getString(), x + 2, y + 2, 0x000000);
				}
			}
		} else {
			use = rgb;
			this.help.drawGradientRect(this.x, this.y, this.x + this.width, this.y + this.height, use, this.zLevel);
			if (isSelected) {
				fontobj.drawString("X", (int) Math.round((float) this.x + this.width / 2) - 2, (int) Math.round((float) this.y + this.height / 2) - 4, Color.RED.getRGB());
			}
		}
	}
	
	private int getWidth(FontRenderer rend) {
		int size = 0;
		String stri = ret.getString();
		if (hasMoreLines()) {
			size = 0;
			String[] str = stri.split("\n");
			for (int i = 0; i < str.length; i++) {
				if (str[i].length() * 5 > size) {
					size = str[i].length() * 5;
				}
			}
		}else{
			size = rend.getStringWidth(stri);
		}
		return size + 4;
	}
	
	private int getHeight(FontRenderer rend) {
		int size = rend.FONT_HEIGHT + 2;
		if (hasMoreLines()) {
			String[] str = ret.getString().split("\n");
			size = size * str.length;
		}
		return size;
	}
	
	public boolean isSelceted() {
		return isSelected;
	}
	
	public void setSelected(boolean b) {
		isSelected = b;
	}
	
	public boolean hasMoreLines() {
		return ret.getString().contains("\n");
	}
	
	@Override
	public void onClick(int x, int y) {
		if (this.isMouseOver(x, y)) {
			isSelected = !isSelected;
			if (run != null) {
				run.run();
			}
		}
	}
	
	public void setTooltip(StringMethod ret) {
		this.ret = ret;
	}
	
	public void setOnSlectedChanged(Runnable r) {
		run = r;
	}
	
	public boolean hasTooltip() {
		return ret != null;
	}
}
