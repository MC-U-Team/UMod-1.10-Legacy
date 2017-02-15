package io.github.mc_umod.render;

import java.awt.*;
import java.util.*;

import io.github.mc_umod.*;
import io.github.mc_umod.api.energy.*;
import io.github.mc_umod.api.render.*;
import io.github.mc_umod.corelib.api.util.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.resources.*;
import net.minecraft.tileentity.*;

public class InfoFieldRender {
	
	public static final InfoFieldRender INSTANCE = new InfoFieldRender();
	private GLHelper help;
	
	private InfoFieldRender() {
		this.help = UReference.getClientProxy().getGLHelper();
	}
	
	public void render(TileEntity tileEntity, double posX, double posY, double posZ) {
		if (!(tileEntity instanceof IWorldView))
			return;
		final FontRenderer rend = Minecraft.getMinecraft().fontRendererObj;
		final IWorldView oven = (IWorldView) tileEntity;
		if (oven instanceof IWorldSpecialView) {
			IWorldSpecialView spview = (IWorldSpecialView) oven;
			spview.specialRender();
			if (!spview.renderMain())
				return;
		}
		final ArrayList<String> st = new ArrayList<String>();
		st.add(I18n.format(oven.getWorld().getBlockState(oven.getPos()).getBlock().getUnlocalizedName() + ".name"));
		if (oven instanceof IPowerProvieder && oven.showPower()) {
			st.add("Energy " + ((IPowerProvieder) oven).getStoredPower() + "/" + ((IPowerProvieder) oven).getMaximalPower());
		}
		String[] strs = oven.textToAdd();
		if (strs != null) {
			for (int i = 0; i < strs.length; i++) {
				st.add(strs[i]);
			}
		}
		GlStateManager.pushMatrix();
		GlStateManager.shadeModel(7425);
		GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
		GlStateManager.enableDepth();
		GlStateManager.enableNormalize();
		GlStateManager.color(1, 1, 1);
		final int stringSi = checkBiggestString(rend, st.toArray(new String[st.size()]));
		final int j = stringSi / 2;
		final int j2 = stringSi + 4;
		final int stringmu = st.size() - 1;
		this.help.drawSmThInWorld(oven.getPos(), posX, posY + ((double) stringmu / 10), posZ, new Runnable() {
			
			@Override
			public void run() {				
				RGBA rgb = new RGBA(Color.DARK_GRAY);
				rgb.setAlpha(225);
				help.drawFrame((double) (-j - 1), (double) (-1 - 0), (double) (j2 + 1), (double) (10) * stringmu, rgb);
				
				RGBA rgb3 = new RGBA(Color.WHITE);
				rgb3.setAlpha(255);
				help.drawFrame((double) (-j - 2), (double) (-1 - 1), (double) (j2 + 1), (double) (10) * stringmu, rgb3);
				
				Iterator<String> itar = st.iterator();
				int y = -11;
				while (itar.hasNext()) {
					String s = itar.next();
					rend.drawStringWithShadow(s, -rend.getStringWidth(s) / 2, y, 0xFFFFFF);
					y += 10;
				}
				
				GlStateManager.enableDepth();
				GlStateManager.shadeModel(7424);
			}
		});
		GlStateManager.popMatrix();
	}
	
	private int checkBiggestString(FontRenderer re, String... args) {
		int i = 0;
		for (String str : args) {
			if (re.getStringWidth(str) > i) {
				i = re.getStringWidth(str);
			}
		}
		return i;
	}
}
