package net.hycrafthd.umod.tileentity.rail;

import net.hycrafthd.umod.entity.rail.EntityRailFX;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityRail extends TileEntity implements ITickable {
	
	public boolean isn = false;
	public byte b = 0;
	
	public void add() {
		if (b < 2)
			b++;
	}
	
	@Override
	public void update() {
		if (!isn)
			init();
	}
	
	private void init() {
		this.worldObj.spawnEntityInWorld(new EntityRailFX(worldObj, pos));
		isn = true;
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		NBTTagCompound tagCom = pkt.getNbtCompound();
		this.readFromNBT(tagCom);
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound tagCom = new NBTTagCompound();
		this.writeToNBT(tagCom);
		return new SPacketUpdateTileEntity(pos, getBlockMetadata(), tagCom);
	}
	
	public static final String COUNT = "r_count";
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound com) {
		super.writeToNBT(com);
		com.setByte(COUNT, b);
		return com;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound com) {
		super.readFromNBT(com);
		b = com.getByte(COUNT);
	}
	
}
