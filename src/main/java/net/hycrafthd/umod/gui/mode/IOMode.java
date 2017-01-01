package net.hycrafthd.umod.gui.mode;

import java.awt.Color;

import net.hycrafthd.corelib.util.RGBA;
import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.api.render.IIOMode;
import net.hycrafthd.umod.gui.*;
import net.hycrafthd.umod.gui.items.*;
import net.hycrafthd.umod.network.PacketHandler;
import net.hycrafthd.umod.network.message.*;
import net.hycrafthd.umod.render.ModelRenderHelper;
import net.hycrafthd.umod.tileentity.TileEntityPulverizer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class IOMode extends ImplGui{

	public GuiCombobox box;
	private float sclax = 0, sclay = 0;
	private int posX,posY;
	private Runnable autorun;
	private ModelRenderHelper helper;
    private int[] args = new int[] {-1,-1,-1,-1,-1,-1};
	
	public IOMode(GuiBase base_gui) {
		super(base_gui);
		this.helper = UReference.getClientProxy().getModelRenderHelper();
		int k = (base_gui.width - base_gui.xSize) / 2;
		int l = (base_gui.height - base_gui.ySize) / 2;
		box = new GuiCombobox(base_gui,k + 8, l + 7, 80, 12);
		base_gui.addToBox(box);
		box.getItems().add(new ComboboxItem("Choose", new RGBA(Color.DARK_GRAY)));
		box.setSelected(box.getItems().size() - 1);
        box.setOnListClicked(new Runnable() {
			
			@Override
			public void run() {
				if(IOMode.this.base_gui.tile instanceof IIOMode){
					PacketHandler.INSTANCE.sendToServer(new MessageIOMode(IOMode.this.base_gui.pos, IOMode.this.base_gui.hal, (box.getSelceted() < box.getItems().size() - 1 ? box.getSelceted():-1)));
					args[getPlace(IOMode.this.base_gui.hal)] = (box.getSelceted() < box.getItems().size() - 1 ? box.getSelceted():-1);
					if(autorun != null)autorun.run();
				}
			}
		});
        for(EnumFacing face : EnumFacing.values()){
        	PacketHandler.INSTANCE.sendToServer(new MessageIORequest(base_gui.pos, face));
        	
        }
    	PacketHandler.INSTANCE.sendToServer(new MessageIORequest(base_gui.pos, EnumFacing.NORTH));
	}

	private int getPlace(EnumFacing face){
		int i = 0;
		for(EnumFacing fc : EnumFacing.VALUES){
			if(fc.equals(face))return i;
			i++;
		}
		return 0;
	}
	
	@Override
	public void render(int mouseX, int mouseY) {
		int k = base_gui.guiLeft;
		int l = base_gui.guiTop;
		GlStateManager.enableDepth();
		GlStateManager.popMatrix();
		GlStateManager.pushMatrix();
		this.setupGuiTransform((base_gui.width / 2), (float) ((base_gui.height / 2) - 0.5));
		this.helper.renderItem(new ItemStack(base_gui.worldObj.getBlockState(base_gui.pos).getBlock()),TransformType.NONE);
		this.drawRects();
		this.drawSouroundings();
		GlStateManager.popMatrix();
		GlStateManager.disableDepth();
		int kl = (base_gui.width - base_gui.xSize) / 2;
		base_gui.getFontRender().drawString(base_gui.hal.toString(), kl + 10, base_gui.guiTop + base_gui.ySize - 18, 0xFFFFFF);
		box.render(mouseX,mouseY);
		RenderHelper.enableGUIStandardItemLighting();
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) k, (float) l, 0.0F);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.enableRescaleNormal();
	}
	
	private void drawSouroundings() {
		BlockPos upP = this.base_gui.pos.up();
		BlockPos downP = this.base_gui.pos.down();
		BlockPos northP = this.base_gui.pos.north();
		BlockPos southP = this.base_gui.pos.south();
		BlockPos westP = this.base_gui.pos.west();
		BlockPos eastP = this.base_gui.pos.east();

		this.drawSouround(upP, 1, 0, 0);
		this.drawSouround(downP, -1,0, 0);
		this.drawSouround(northP, 0, 1, 0);
		this.drawSouround(southP, 0, -1, 0);
		this.drawSouround(westP, 0, 0, 1);
		this.drawSouround(eastP, 0, 0, -1);
	}
	
	private void drawSouround(BlockPos pos,double x,double y,double z){
		IBlockState state = base_gui.worldObj.getBlockState(pos);
		if(state.getRenderType().equals(EnumBlockRenderType.INVISIBLE) || state.getBlock().isAir(state,this.base_gui.worldObj, pos))return;
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		
		GlStateManager.rotate(-90, 0, 0, 1);
		GlStateManager.rotate(90, 0, 1, 0);
		this.helper.renderItem(new ItemStack(state.getBlock()),TransformType.NONE,0.25F);
		
		GlStateManager.popMatrix();
	}

	private void drawRects(){
		
		//SOUTH
		int south = args[getPlace(EnumFacing.SOUTH)];
		if(south >= 0 && south < this.box.getItems().size() - 1){
		RGBA southR = new RGBA(box.getItems().get(south).color.toAWTColor()).setAlpha(125);
		southR.setAlpha(125);
		base_gui.help.drawGradientRect(-0.5, -0.5, 0.5, 0.5, southR,southR,-0.5001);
		base_gui.help.drawGradientRect(0.5, -0.5, -0.5, 0.5, southR,southR,-0.5001);
		}
		
		//NORTH
		int north = args[getPlace(EnumFacing.NORTH)];
		if(north >= 0 && north < this.box.getItems().size() - 1){
		RGBA northR = new RGBA(box.getItems().get(north).color.toAWTColor()).setAlpha(125);
		base_gui.help.drawGradientRect(-0.5, -0.5, 0.5, 0.5, northR,northR,0.5001);
		base_gui.help.drawGradientRect(0.5, -0.5, -0.5, 0.5, northR,northR,0.5001);
		}
		
		GlStateManager.rotate(90, 1, 0, 0);
		
		//UP
		int up = args[getPlace(EnumFacing.UP)];
		if(up >= 0 && up < this.box.getItems().size() - 1){
		RGBA upR = new RGBA(box.getItems().get(up).color.toAWTColor()).setAlpha(125);
		base_gui.help.drawGradientRect(-0.5, -0.5, 0.5, 0.5, upR,upR,-0.5001);
		base_gui.help.drawGradientRect(0.5, -0.5, -0.5, 0.5, upR,upR,-0.5001);
		}
		
		//DOWN
		int down = args[getPlace(EnumFacing.DOWN)];
		if(down >= 0 && down < this.box.getItems().size() - 1){
		RGBA downR = new RGBA(box.getItems().get(down).color.toAWTColor()).setAlpha(125);
		base_gui.help.drawGradientRect(-0.5, -0.5, 0.5, 0.5, downR,downR,0.5001);
		base_gui.help.drawGradientRect(0.5, -0.5, -0.5, 0.5, downR,downR,0.5001);
		}
		
		GlStateManager.rotate(90, 0, 1, 0);
		
		//EAST
		int east = args[getPlace(EnumFacing.EAST)];
		if(east >= 0 && east < this.box.getItems().size() - 1){
		RGBA eastR = new RGBA(box.getItems().get(east).color.toAWTColor()).setAlpha(125);
		base_gui.help.drawGradientRect(-0.5, -0.5, 0.5, 0.5, eastR,eastR,-0.5001);
		base_gui.help.drawGradientRect(0.5, -0.5, -0.5, 0.5, eastR,eastR,-0.5001);
		}
		//WEST
		int west = args[getPlace(EnumFacing.WEST)];
		if(west >= 0 && west < this.box.getItems().size() - 1){
		RGBA westR = new RGBA(box.getItems().get(west).color.toAWTColor()).setAlpha(125);
		base_gui.help.drawGradientRect(-0.5, -0.5, 0.5, 0.5, westR,westR,0.5001);
		base_gui.help.drawGradientRect(0.5, -0.5, -0.5, 0.5, westR,westR,0.5001);
		}
		
	}
	
	private void setupGuiTransform(float xPosition, float yPosition) {
		GlStateManager.translate((float) xPosition, (float) yPosition, 100.0F + this.zLevel);
		GlStateManager.scale(2.0F, 2.0F, -2.0F);
		GlStateManager.scale(0.5F, 0.5F, 0.5F);			
		GlStateManager.scale(40.0F, 40.0F, 40.0F);
		GlStateManager.rotate(sclay - 180, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(sclax, 0.0F, 1.0F, 0.0F);
		GlStateManager.enableLighting();
	}
	
	@Override
	public void onDrag(int mouseX, int mouseY) {
		if (mouseX > 7 + base_gui.guiLeft && mouseX < (base_gui.xSize + base_gui.guiLeft) - 7 && mouseY > base_gui.guiTop + 6 && mouseY < (base_gui.guiTop + base_gui.ySize) - 6) {
			if (sclax + (mouseX - posX) <= 90 && sclax + (mouseX - posX) >= -180) {
				sclax += mouseX - posX;
			}
			if (sclay + (mouseY - posY) <= 90 && sclay + (mouseY - posY) >= -90) {
				sclay += mouseY - posY;
			}
			posX = mouseX;
			posY = mouseY;
			if (sclay >= 45 && sclay <= 135) {
				if(base_gui.hal != EnumFacing.UP){
					base_gui.hal = EnumFacing.UP;
					this.imp_facingchange();
				}
			}
			if (sclay <= -45 && sclay >= -215) {
				if(base_gui.hal != EnumFacing.DOWN){
					base_gui.hal = EnumFacing.DOWN;
					this.imp_facingchange();
				}
			}
			if (sclay >= -45 && sclay <= 45) {
				if (sclax >= -45 && sclax <= 45) {
					if(base_gui.hal != EnumFacing.NORTH){
						base_gui.hal = EnumFacing.NORTH;
						this.imp_facingchange();
					}
				}
				if (sclax <= 135 && sclax >= 45) {
					if(base_gui.hal != EnumFacing.EAST){
						base_gui.hal = EnumFacing.EAST;
						this.imp_facingchange();
					}
				}
				if (sclax <= -45 && sclax >= -135) {
					if(base_gui.hal != EnumFacing.WEST){
						base_gui.hal = EnumFacing.WEST;
						this.imp_facingchange();
					}
				}
				if (sclax <= -135 && sclax >= -210) {
					if(base_gui.hal != EnumFacing.SOUTH){
						base_gui.hal = EnumFacing.SOUTH;
						this.imp_facingchange();
					}
				}
			}
			base_gui.onIOModeSwitched();
		}
	}
	
	private void imp_facingchange() {
		PacketHandler.INSTANCE.sendToServer(new MessageIORequest(this.base_gui.pos, this.base_gui.hal));
	}
	
	@Override
	public void onClick(int mouseX, int mouseY) {
		posX = mouseX;
		posY = mouseY;
		box.onClick(mouseX, mouseY);
	}
	
	public void checkAndAdd(EnumFacing fc, int item) {
		if (item == Byte.MAX_VALUE || item < 0 || item >= this.box.getItems().size()) {
			this.box.setSelected(this.box.getItems().size() - 1);
			args[getPlace(fc)] = this.box.getItems().size() - 1;
			return;
		}
		args[getPlace(fc)] = item;
		if (base_gui.hal.equals(fc)) {
			this.box.setSelected(item);
		} 
	}
	
	public void addListener(Runnable run){
		this.autorun = run;
	}
}
