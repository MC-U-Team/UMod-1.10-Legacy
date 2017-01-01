package io.github.mc_umod.api;

import net.minecraft.entity.player.EntityPlayer;

public class BasicSignable implements ISignable{

	private EnumSignStates states = EnumSignStates.PRIVATE;
	private EntityPlayer player;	
	private EntityPlayer[] players;
	
	@Override
	public void signPlayer(EntityPlayer pl) {
		this.player = pl;
		this.players = null;
		this.states = EnumSignStates.PRIVATE;
	}

	@Override
	public void signPlayers(EntityPlayer pl,EntityPlayer[] pls) {
		this.player = pl;
		this.players = pls;
		this.states = EnumSignStates.PROTECTED;
	}

	@Override
	public void setPublic(EntityPlayer pl) {
		this.player = pl;
		this.players = null;
		this.states = EnumSignStates.PUBLIC;
	}

	@Override
	public boolean isPlayerSigned(EntityPlayer pl) {
		for(EntityPlayer plf : this.players){
			if(plf.getUniqueID().equals(pl.getUniqueID())){
				return true;
			}
		}
		return this.isOwner(pl);
	}

	@Override
	public EnumSignStates getSignState() {
		return this.states;
	}

	@Override
	public boolean isOwner(EntityPlayer pl) {
		return this.player.getUniqueID().equals(pl.getUniqueID());
	}
	
}
