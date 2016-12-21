package net.hycrafthd.umod.api;

import net.minecraft.entity.player.EntityPlayer;

public interface ISignable {
	
	/**
	 * Future API for Tile Locking 
	 * @author MrTroble
	 */
	
	public void signPlayer(EntityPlayer pl);
	
	public void signPlayers(EntityPlayer[] pl);
	
	public void setPublic();
	
	public boolean isPlayerSigned(EntityPlayer pl);
	
	public boolean isPlayerOfSights(EntityPlayer pl);
		
	public EnumSignStates getSignState();
}
