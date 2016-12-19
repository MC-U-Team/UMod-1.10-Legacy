package net.hycrafthd.umod.block;

import net.hycrafthd.umod.*;
import net.hycrafthd.umod.api.*;
import net.hycrafthd.umod.api.energy.IEnergyMessage;
import net.hycrafthd.umod.tileentity.TileEntityCable;
import net.hycrafthd.umod.utils.NBTUtils;
import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraftforge.fml.relauncher.*;

public class BlockCable extends BlockBaseMachine implements ITileEntityProvider, IEnergyMessage, ISpiritProvider, IConduitBlock {
	
	public int powertrans;
	public int lo;
	public boolean iso;
	public String asp;
	
	public BlockCable(String name, int transf, int loos, boolean iso, String sp) {
		super();
		this.powertrans = transf;
		this.iso = iso;
		this.setHardness(6F);
		this.setResistance(5F);
		this.setUnlocalizedName(name);
		this.setCreativeTab(UReference.maschines);
		this.lo = loos;
		this.asp = sp;
	}
	
	@Override
	public boolean canDropFromExplosion(Explosion explosionIn) {
		return false;
	}
	
	@Override
	public String getSpirte() {
		return asp;
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityCable(powertrans, lo);
	}
	
	@Override
	public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
		return false;
	}
	
	public boolean isFullCube() {
		return false;
	}
	
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
	
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess w, BlockPos pos, EnumFacing side) {
		return false;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileEntityCable cab = (TileEntityCable) worldIn.getTileEntity(pos);
		if (playerIn.getHeldItemMainhand() != null) {
			Block rand = Block.getBlockFromItem(playerIn.getHeldItemMainhand().getItem());
			if (!cab.hasConduit() && rand != null && rand instanceof BlockConduit) {
				cab.setConduit(NBTUtils.getStackFromConduit(playerIn.getHeldItemMainhand()));
				playerIn.inventory.setInventorySlotContents(playerIn.inventory.currentItem, null);
				// playerIn.worldObj.playSoundAtEntity(playerIn, "step.stone", 0.2F, ((playerIn.getRNG().nextFloat() - playerIn.getRNG().nextFloat()) * 0.7F + 1.0F) * 2.0F);
				return true;
			} else if (cab.hasConduit() && Block.getBlockFromItem(playerIn.getHeldItemMainhand().getItem()) != null && !(Block.getBlockFromItem(playerIn.getHeldItemMainhand().getItem()) instanceof BlockCable)) {
				dropForPlayer(playerIn, cab);
				cab.setConduit(null);
				return true;
			}
		} else if (cab.hasConduit()) {
			dropForPlayer(playerIn, cab);
			cab.setConduit(null);
			return true;
		}
		return false;
	}
	
	private void dropForPlayer(EntityPlayer playerIn, TileEntityCable cab) {
		ItemStack stack = new ItemStack(UBlocks.conduit);
		NBTUtils.addStackToConduit(stack, cab.getConduit());
		boolean flag = playerIn.inventory.addItemStackToInventory(stack);
		
		if (flag) {
			playerIn.inventoryContainer.detectAndSendChanges();
		}
		
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		if (source instanceof World) {
			return getSelectedBoundingBox(state, (World) source, pos);
		}
		return FULL_BLOCK_AABB;
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
		return getSelectedBoundingBox(blockState, worldIn, pos);
	}
	
	@Override
	public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World source, BlockPos pos) {
		TileEntityCable cab = (TileEntityCable) source.getTileEntity(pos);
		if (cab == null)
			return FULL_BLOCK_AABB;
		IBlockAccess w = source;
		if (cab.hasConduit()) {
			return FULL_BLOCK_AABB;
		}
		TileEntityCable pip = (TileEntityCable) w.getTileEntity(pos);
		if (pip != null) {
			boolean csouth = pip.canConnect(w, pos.south());
			boolean cnorth = pip.canConnect(w, pos.north());
			boolean cdown = pip.canConnect(w, pos.down());
			boolean cup = pip.canConnect(w, pos.up());
			boolean ceast = pip.canConnect(w, pos.east());
			boolean cwest = pip.canConnect(w, pos.west());
			double anfangunten = 0.4F;
			double anfnagoben = 0.6F;
			double anfangX = 0.4F;
			double endeX = 0.6F;
			double anfangZ = 0.4F;
			double endeZ = 0.6F;
			
			if (cup) {
				anfnagoben = 1;
			}
			if (cdown) {
				anfangunten = 0;
			}
			if (cwest) {
				anfangX = 0;
			}
			if (ceast) {
				endeX = 1;
			}
			if (cnorth) {
				anfangZ = 0;
			}
			if (csouth) {
				endeZ = 1;
			}
			
			return new AxisAlignedBB(anfangX, anfangunten, anfangZ, endeX, anfnagoben, endeZ);
			
		}
		return FULL_BLOCK_AABB;
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.INVISIBLE;
	}
	
	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		super.onEntityCollidedWithBlock(worldIn, pos, state, entityIn);
		if (!isIsolated()) {
			entityIn.attackEntityFrom(UDamageSource.electroshock, 5);
		}
	}
	
	public boolean isIsolated() {
		return iso;
	}
	
	@Override
	public String getMessage(int i) {
		return "Transports " + powertrans + "UE/t";
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}
}
