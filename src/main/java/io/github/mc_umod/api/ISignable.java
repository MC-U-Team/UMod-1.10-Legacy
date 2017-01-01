package io.github.mc_umod.api;

import net.minecraft.entity.player.EntityPlayer;

public interface ISignable {
	
	/**
	 * Future API for Tile Locking 
	 * @author MrTroble
	 */
	
	public void signPlayer(EntityPlayer pl);
	
	public void signPlayers(EntityPlayer pl,EntityPlayer[] pls);
	
	public void setPublic(EntityPlayer pl);
	
	public boolean isPlayerSigned(EntityPlayer pl);
			
	public EnumSignStates getSignState();
	
	public boolean isOwner(EntityPlayer pl);
}
