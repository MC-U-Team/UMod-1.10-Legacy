package net.hycrafthd.umod.network.message;

import io.netty.buffer.ByteBuf;
import net.hycrafthd.umod.api.render.IIOMode;
import net.hycrafthd.umod.utils.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.*;

public class MessageIOMode implements IMessage, IMessageHandler<MessageIOMode, IMessage> {
	
	public BlockPos pos;
	public EnumFacing face;
	public int mode;
	
	public MessageIOMode() {
		
	}
	
	public MessageIOMode(BlockPos pos, EnumFacing face, int mode) {
		this.face = face;
		this.mode = mode;
		this.pos = pos;
	}
	
	@Override
	public IMessage onMessage(MessageIOMode message, MessageContext ctx) {
		World w = ctx.getServerHandler().playerEntity.worldObj;
		TileEntity ent = w.getTileEntity(message.pos);
		if (ent instanceof IIOMode) {
			IIOMode est = (IIOMode) ent;
			est.setModeToFace(message.face, message.mode);
		}
		return null;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.pos = NetworkUtil.getPosFromBuffer(buf);
		this.face = DirectionUtils.getFacingFromShort(buf.readShort());
		this.mode = buf.readInt();
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		NetworkUtil.addPosToBuffer(buf, pos);
		buf.writeShort(DirectionUtils.getShortFromFacing(this.face));
		buf.writeInt(mode);
	}
	
}
