package io.github.mc_umod.render;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class ModelRenderHelper {
	
	private final ItemModelMesher itemModelMesher;
	private final TextureManager textureManager;
	private final ItemColors itemColors;
	private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
	
	public ModelRenderHelper(ItemModelMesher mome, TextureManager tex, ItemColors col) {
		this.itemColors = col;
		this.itemModelMesher = mome;
		this.textureManager = tex;
	}
	
	public void renderItem(ItemStack stack) {
		this.renderItem(stack, 1F);
	}
	
	public void renderItem(ItemStack stack,float alpha) {
		this.renderItem(stack, TransformType.GUI, alpha);
	}
	
	public void renderItem(ItemStack stack, TransformType type) {
		this.renderItem(stack, type, 1F);
	}
	
	public void renderItem(ItemStack stack, TransformType type,float alpha) {
		if (stack != null && stack.getItem() != null) {
			IBakedModel ibakedmodel = this.getItemModelWithOverrides(stack, Minecraft.getMinecraft().theWorld, null);
			this.renderItemModel(stack, ibakedmodel, type, false,alpha);
		}
	}
	
	public IBakedModel getItemModelWithOverrides(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entitylivingbaseIn) {
		IBakedModel ibakedmodel = this.itemModelMesher.getItemModel(stack);
		return ibakedmodel.getOverrides().handleItemState(ibakedmodel, stack, worldIn, entitylivingbaseIn);
	}
	
	protected void renderItemModel(ItemStack stack, IBakedModel bakedmodel, ItemCameraTransforms.TransformType transform, boolean leftHanded,float alpha) {
		if (stack.getItem() != null) {
			this.textureManager.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			this.textureManager.getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).setBlurMipmap(false, false);
			GlStateManager.color(1.0F, 1.0F, 1.0F, alpha);
			GlStateManager.enableRescaleNormal();
//			GlStateManager.alphaFunc(516, 0.1F);
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
			GlStateManager.pushMatrix();
			GlStateManager.enableAlpha();

			bakedmodel = net.minecraftforge.client.ForgeHooksClient.handleCameraTransforms(bakedmodel, transform, false);
			
			this.renderItem(stack, bakedmodel);
			GlStateManager.cullFace(GlStateManager.CullFace.BACK);
			GlStateManager.popMatrix();
			GlStateManager.disableRescaleNormal();
			GlStateManager.disableBlend();
			this.textureManager.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			this.textureManager.getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).restoreLastBlurMipmap();
		}
	}
	
	private void renderModel(IBakedModel model, ItemStack stack) {
		this.renderModel(model, -1, stack);
	}
	
	private void renderModel(IBakedModel model, int color) {
		this.renderModel(model, color, (ItemStack) null);
	}
	
	private void renderModel(IBakedModel model, int color, @Nullable ItemStack stack) {
		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer vertexbuffer = tessellator.getBuffer();
		vertexbuffer.begin(7, DefaultVertexFormats.ITEM);
		
		for (EnumFacing enumfacing : EnumFacing.values()) {
			this.renderQuads(vertexbuffer, model.getQuads((IBlockState) null, enumfacing, 0L), color, stack);
		}
		
		this.renderQuads(vertexbuffer, model.getQuads((IBlockState) null, (EnumFacing) null, 0L), color, stack);
		tessellator.draw();
	}
	
	public void renderItem(ItemStack stack, IBakedModel model) {
		if (stack != null) {
			GlStateManager.pushMatrix();
			GlStateManager.translate(-0.5F, -0.5F, -0.5F);
			
			if (model.isBuiltInRenderer()) {
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				GlStateManager.enableRescaleNormal();
				TileEntityItemStackRenderer.instance.renderByItem(stack);
			} else {
				this.renderModel(model, stack);
				
				if (stack.hasEffect()) {
					this.renderEffect(model);
				}
			}
			
			GlStateManager.popMatrix();
		}
	}
	
	private void renderEffect(IBakedModel model) {
		GlStateManager.depthMask(false);
		GlStateManager.depthFunc(514);
		GlStateManager.disableLighting();
		GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_COLOR, GlStateManager.DestFactor.ONE);
		this.textureManager.bindTexture(RES_ITEM_GLINT);
		GlStateManager.matrixMode(5890);
		GlStateManager.pushMatrix();
		GlStateManager.scale(8.0F, 8.0F, 8.0F);
		float f = (float) (Minecraft.getSystemTime() % 3000L) / 3000.0F / 8.0F;
		GlStateManager.translate(f, 0.0F, 0.0F);
		GlStateManager.rotate(-50.0F, 0.0F, 0.0F, 1.0F);
		this.renderModel(model, -8372020);
		GlStateManager.popMatrix();
		GlStateManager.pushMatrix();
		GlStateManager.scale(8.0F, 8.0F, 8.0F);
		float f1 = (float) (Minecraft.getSystemTime() % 4873L) / 4873.0F / 8.0F;
		GlStateManager.translate(-f1, 0.0F, 0.0F);
		GlStateManager.rotate(10.0F, 0.0F, 0.0F, 1.0F);
		this.renderModel(model, -8372020);
		GlStateManager.popMatrix();
		GlStateManager.matrixMode(5888);
		GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		GlStateManager.enableLighting();
		GlStateManager.depthFunc(515);
		GlStateManager.depthMask(true);
		this.textureManager.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
	}
	
	private void putQuadNormal(VertexBuffer renderer, BakedQuad quad) {
		Vec3i vec3i = quad.getFace().getDirectionVec();
		renderer.putNormal((float) vec3i.getX(), (float) vec3i.getY(), (float) vec3i.getZ());
	}
	
	private void renderQuad(VertexBuffer renderer, BakedQuad quad, int color) {
		renderer.addVertexData(quad.getVertexData());
		renderer.putColor4(color);
		this.putQuadNormal(renderer, quad);
	}
	
	private void renderQuads(VertexBuffer renderer, List<BakedQuad> quads, int color, @Nullable ItemStack stack) {
		boolean flag = color == -1 && stack != null;
		int i = 0;
		
		for (int j = quads.size(); i < j; ++i) {
			BakedQuad bakedquad = (BakedQuad) quads.get(i);
			int k = color;
			
			if (flag && bakedquad.hasTintIndex()) {
				k = this.itemColors.getColorFromItemstack(stack, bakedquad.getTintIndex());
				
				if (EntityRenderer.anaglyphEnable) {
					k = TextureUtil.anaglyphColor(k);
				}
				
				k = k | -16777216;
			}
			
			net.minecraftforge.client.model.pipeline.LightUtil.renderQuadColor(renderer, bakedquad, k);
		}
	}
	
	public boolean shouldRenderItemIn3D(ItemStack stack) {
		IBakedModel ibakedmodel = this.itemModelMesher.getItemModel(stack);
		return ibakedmodel == null ? false : ibakedmodel.isGui3d();
	}

	public void renderConduit(Block b,double posX,double posy,double posz){
		if(b == null)return;
		GlStateManager.pushMatrix();
		GlStateManager.translate(posX, posy, posz);
		this.renderItem(new ItemStack(b));
		GlStateManager.popMatrix();
	}
}
