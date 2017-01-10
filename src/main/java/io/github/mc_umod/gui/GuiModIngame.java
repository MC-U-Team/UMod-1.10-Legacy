package io.github.mc_umod.gui;

import io.github.mc_umod.api.energy.*;
import io.github.mc_umod.item.*;
import io.github.mc_umod.render.*;
import io.github.mc_umod.tileentity.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.resources.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.nbt.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

@SuppressWarnings("deprecation")
public class GuiModIngame extends Gui{
	
	private int ticks = 0;
	private ScaledResolution res;
	private ModelRenderHelper helper;
	
	public GuiModIngame(ScaledResolution resolution,ModelRenderHelper help) {
		res = resolution;
		this.helper = help;
	}
	
	public void render() {
		try {
			EntityPlayer pl = Minecraft.getMinecraft().thePlayer;
			if (pl != null && pl.getHeldItemMainhand() != null && (pl.getHeldItemMainhand().getItem() instanceof ItemEnergyDisplay) && pl.getHeldItemMainhand().hasTagCompound() && pl.getHeldItemMainhand().getTagCompound().hasKey(ItemEnergyDisplay.NBT_TAG) && (pl.openContainer == null || pl.openContainer instanceof ContainerPlayer)) {
				drawScreen(pl);
			} else {
				tT = 0;
			}
		} catch (Exception e) {
			System.err.println(e.toString());
			System.err.println(e.getMessage());
			System.err.println("Excepted by " + e.getStackTrace()[0].getMethodName() + " in Class");
			System.err.println("" + e.getStackTrace()[0].getClassName() + "[" + e.getStackTrace()[0].getFileName() + "] Line " + e.getStackTrace()[0].getLineNumber());
			return;
		}
	}
	
	public int tT;
	
	private void drawScreen(EntityPlayer pl) {
		ticks++;
		double screenwidth = res.getScaledWidth_double();
		double screenheight = res.getScaledHeight_double();
		int width = (int) Math.round(screenwidth / 2);
		int height = (int) Math.round(screenheight / 4);
		
		String str = "No Ore Detected";
		World w = Minecraft.getMinecraft().theWorld;
		NBTTagCompound comp = (NBTTagCompound) pl.getHeldItemMainhand().getTagCompound().getTag(ItemEnergyDisplay.NBT_TAG);
		BlockPos p = new BlockPos(comp.getInteger("X"), comp.getInteger("Y"), comp.getInteger("Z"));
		TileEntityPulverizer oven = (TileEntityPulverizer) w.getTileEntity(p);
		FontRenderer rend = Minecraft.getMinecraft().getRenderManager().getFontRenderer();
		if (oven != null) {
			if (oven.getStackInSlot(3) != null) {
				str = oven.getStackInSlot(3).getDisplayName();
			}
			
			String energy = "Energy " + ((IPowerProvieder) oven).getStoredPower() + "/" + ((IPowerProvieder) oven).getMaximalPower();
			String stat = "Progress " + oven.getField(0) + "/100";
			String pos = "X=" + p.getX() + " Y=" + p.getY() + " Z=" + p.getZ();
			String name = I18n.format(oven.getWorld().getBlockState(oven.getPos()).getBlock().getUnlocalizedName() + ".name");
			
			GlStateManager.pushMatrix();
			{
				GlStateManager.translate(screenwidth / 2, height, 0);
				GlStateManager.scale(tT / 4000, 1, 1);
				rend.drawStringWithShadow(name, -rend.getStringWidth(name) / 2, -14, 0xFFFFFF);
				rend.drawStringWithShadow(str, -rend.getStringWidth(str) / 2, -1, 0xFFFFFF);
				rend.drawStringWithShadow(energy, -rend.getStringWidth(energy) / 2, 9, 0xFFFFFF);
				rend.drawStringWithShadow(stat, -rend.getStringWidth(stat) / 2, 19, 0xFFFFFF);
				if (tT < 4000) {
					rend.drawStringWithShadow(pos, -rend.getStringWidth(pos) / 2, 150, 0xFFFFFF);
					tT++;
				}
			}
			GlStateManager.popMatrix();
			
			if (tT >= 4000) {
				GlStateManager.pushMatrix();
				{
					RenderHelper.enableGUIStandardItemLighting();
					GlStateManager.color(1, 1, 1);
					GlStateManager.translate(width - 10, height -40, 0);
					this.helper.renderItem(new ItemStack(w.getBlockState(p).getBlock()));
				}
				GlStateManager.popMatrix();
			}
			
		} else {
			GlStateManager.pushMatrix();
			GlStateManager.enableDepth();
			GlStateManager.translate(width, height, 0);
			// GlStateManager.scale(tT/400, 1, 1);
			rend.drawStringWithShadow("Out of range", -rend.getStringWidth("Out of range") / 2, -14, 0xFFFFFF);
			GlStateManager.popMatrix();
		}
	}
	
	private int trans = 45;
	
	private void setupGuiTransform(int xPosition, int yPosition, boolean isGui3d) {
		GlStateManager.translate((float) xPosition, (float) yPosition, 100.0F);
		GlStateManager.translate(8.0F, 8.0F, 0.0F);
		GlStateManager.scale(2.0F, 2.0F, -2.0F);
		GlStateManager.scale(0.5F, 0.5F, 0.5F);
		
		if (isGui3d) {
			if (ticks >= 10) {
				trans++;
				ticks = 0;
			}
			if (trans >= 360) {
				trans = 0;
			}
			GlStateManager.scale(40.0F, 40.0F, 40.0F);
			GlStateManager.rotate(10F, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(trans, 0.0F, 1.0F, 0.0F);
			GlStateManager.enableLighting();
		} else {
			GlStateManager.scale(64.0F, 64.0F, 64.0F);
			GlStateManager.disableLighting();
		}
	}
	
}
