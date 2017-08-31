package io.github.mc_umod.gui.mode;

import io.github.mc_umod.*;
import io.github.mc_umod.api.render.IIOMode;
import io.github.mc_umod.gui.GuiBase;
import io.github.mc_umod.gui.items.*;
import io.github.mc_umod.network.PacketHandler;
import io.github.mc_umod.network.message.*;
import io.github.mc_umod.render.ModelRenderHelper;
import io.github.mc_umod.renderapi.draw.Quad;
import io.github.mc_umod.util.RGBA;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;

public class IOMode extends ImplGui{

	public GuiCombobox box;
	private float sclax = 0, sclay = 0;
	private int posX,posY;
	private ModelRenderHelper helper;
	private EnumFacing facing = EnumFacing.NORTH;
    private int[] args = new int[] {-1,-1,-1,-1,-1,-1};
	
	public IOMode(GuiBase base_gui) {
		super(base_gui);
		this.helper = UReference.getClientProxy().getModelRenderHelper();
		int k = (base_gui.width - base_gui.xSize) / 2;
		int l = (base_gui.height - base_gui.ySize) / 2;
		box = new GuiCombobox(base_gui,k + 8, l + 7, 80, 12);
		base_gui.addToBox(box);
		box.getItems().add(ComboboxItem.CHOOSE);
		box.setSelected(box.getItems().size() - 1);
        box.setOnListClicked(new Runnable() {
			
			@Override
			public void run() {
				if(IOMode.this.base_gui.tile instanceof IIOMode){
					UMod.getHandler().INSTANCE.sendToServer(new MessageIOMode(IOMode.this.base_gui.pos, IOMode.this.facing, (box.getSelceted() < box.getItems().size() - 1 ? box.getSelceted():-1)));
					args[facing.ordinal()] = (box.getSelceted() < box.getItems().size() - 1 ? box.getSelceted():-1);
				}
			}
		});
        for(EnumFacing face : EnumFacing.values()){
        	UMod.getHandler().INSTANCE.sendToServer(new MessageIORequest(base_gui.pos, face));
        }
        UMod.getHandler().INSTANCE.sendToServer(new MessageIORequest(base_gui.pos, EnumFacing.NORTH));
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
		base_gui.getFontRender().drawString(facing.toString(), kl + 10, base_gui.guiTop + base_gui.ySize - 18, 0xFFFFFF);
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
		Block b = state.getBlock();
		if(state.getRenderType().equals(EnumBlockRenderType.INVISIBLE) || b.isAir(state,this.base_gui.worldObj, pos))return;
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		
		GlStateManager.rotate(-90, 0, 0, 1);
		GlStateManager.rotate(90, 0, 1, 0);
		this.helper.renderItem(new ItemStack(b, 1, b.getMetaFromState(state)),TransformType.NONE,0.25F);
		
		GlStateManager.popMatrix();
	}

	private void drawRects(){
		
		//SOUTH
		int south = args[EnumFacing.SOUTH.ordinal()];
		if(south >= 0 && south < this.box.getItems().size() - 1){
		RGBA southR = new RGBA(box.getItems().get(south).color.toAWTColor()).setAlpha(125);
		southR.setAlpha(125);
		new Quad(-0.5, -0.5, 0.5, 0.5, southR).draw();
		new Quad(0.5, -0.5, -0.5, 0.5, southR).draw();
		}
		
		//NORTH
		int north = args[EnumFacing.NORTH.ordinal()];
		if(north >= 0 && north < this.box.getItems().size() - 1){
		RGBA northR = new RGBA(box.getItems().get(north).color.toAWTColor()).setAlpha(125);
		new Quad(-0.5, -0.5, 0.5, 0.5, northR).draw();
		new Quad(0.5, -0.5, -0.5, 0.5, northR).draw();
		}
		
		GlStateManager.rotate(90, 1, 0, 0);
		
		//UP
		int up = args[EnumFacing.UP.ordinal()];
		if(up >= 0 && up < this.box.getItems().size() - 1){
		RGBA upR = new RGBA(box.getItems().get(up).color.toAWTColor()).setAlpha(125);
		new Quad(-0.5, -0.5, 0.5, 0.5, upR).draw();
		new Quad(0.5, -0.5, -0.5, 0.5, upR).draw();
		}
		
		//DOWN
		int down = args[EnumFacing.DOWN.ordinal()];
		if(down >= 0 && down < this.box.getItems().size() - 1){
		RGBA downR = new RGBA(box.getItems().get(down).color.toAWTColor()).setAlpha(125);
		new Quad(-0.5, -0.5, 0.5, 0.5, downR).draw();
		new Quad(0.5, -0.5, -0.5, 0.5, downR).draw();
		}
		
		GlStateManager.rotate(90, 0, 1, 0);
		
		//EAST
		int east = args[EnumFacing.EAST.ordinal()];
		if(east >= 0 && east < this.box.getItems().size() - 1){
		RGBA eastR = new RGBA(box.getItems().get(east).color.toAWTColor()).setAlpha(125);
		new Quad(-0.5, -0.5, 0.5, 0.5, eastR).draw();
		new Quad(0.5, -0.5, -0.5, 0.5, eastR).draw();
		}
		//WEST
		int west = args[EnumFacing.WEST.ordinal()];
		
		
	}
	
	private void checkAndDraw(EnumFacing face){
		int count = face.ordinal();
		if(count >= 0 && count < this.box.getItems().size() - 1){
			RGBA westR = new RGBA(box.getItems().get(count).color.toAWTColor()).setAlpha(125);
			new Quad(-0.5, -0.5, 0.5, 0.5, westR);
			new Quad(0.5, -0.5, -0.5, 0.5, westR);
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
				if(facing != EnumFacing.UP){
					facing = EnumFacing.UP;
					this.imp_facingchange();
				}
			}
			if (sclay <= -45 && sclay >= -215) {
				if(facing != EnumFacing.DOWN){
					facing = EnumFacing.DOWN;
					this.imp_facingchange();
				}
			}
			if (sclay >= -45 && sclay <= 45) {
				if (sclax >= -45 && sclax <= 45) {
					if(facing != EnumFacing.NORTH){
						facing = EnumFacing.NORTH;
						this.imp_facingchange();
					}
				}
				if (sclax <= 135 && sclax >= 45) {
					if(facing != EnumFacing.EAST){
						facing = EnumFacing.EAST;
						this.imp_facingchange();
					}
				}
				if (sclax <= -45 && sclax >= -135) {
					if(facing != EnumFacing.WEST){
						facing = EnumFacing.WEST;
						this.imp_facingchange();
					}
				}
				if (sclax <= -135 && sclax >= -210) {
					if(facing != EnumFacing.SOUTH){
						facing = EnumFacing.SOUTH;
						this.imp_facingchange();
					}
				}
			}
			base_gui.onIOModeSwitched();
		}
	}
	
	private void imp_facingchange() {
		UMod.getHandler().INSTANCE.sendToServer(new MessageIORequest(this.base_gui.pos, facing));
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
			args[fc.ordinal()] = this.box.getItems().size() - 1;
			return;
		}
		args[fc.ordinal()] = item;
		if (facing.equals(fc)) {
			this.box.setSelected(item);
		} 
	}
}
