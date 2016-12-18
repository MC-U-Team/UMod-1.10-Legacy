package net.hycrafthd.umod.ext.ic2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergyConductor;
import ic2.api.energy.tile.IEnergyEmitter;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.energy.tile.IEnergySource;
import ic2.core.block.BlockTileEntity;
import ic2.core.block.ITeBlock;
import ic2.core.block.TileEntityBlock;
import ic2.core.block.comp.Energy;
import ic2.core.item.block.ItemBlockTileEntity;
import ic2.core.ref.TeBlock.DefaultDrop;
import ic2.core.ref.TeBlock.HarvestTool;
import ic2.core.ref.TeBlock.ITePlaceHandler;
import net.hycrafthd.umod.api.energy.IPowerProvieder;
import net.hycrafthd.umod.tileentity.TileEntityBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;

public class TileEntityIC2Transformer extends TileEntity implements IPowerProvieder,IEnergySource,IEnergySink,ITickable{
	
	private double power,maxpower = 150000.0;
	
	public TileEntityIC2Transformer() {
	}

	@Override
	public double getStoredPower() {
		return this.power;
	}
	
	@Override
	public void addPower(double power) {
		this.power += power;
	}
	
	@Override
	public double getPower(double powerneed) {
		this.power -= powerneed;
		return powerneed;
	}
	
	@Override
	public double getMaximalPower() {
		return maxpower;
	}
	
	@Override
	public boolean isWorking() {
		return true;
	}
	
	@Override
	public String getErrorMessage() {
		return null;
	}
	
	@Override
	public boolean hasPower() {
		return power > 0;
	}
	
	@Override
	public double getPowerProducNeeds() {
		return 0;
	}
	
	@Override
	public void setEnergy(double coun) {
		this.power = coun;
	}
	
	@Override
	public boolean needsPower() {
		return UE_TO_EU;
	}
	
	@Override
	public boolean productsPower() {
		return !UE_TO_EU;
	}
	
	private boolean UE_TO_EU = true;
	
	@Override
	public boolean isInput() {
		return true;
	}
	
	@Override
	public boolean isOutput() {
		return true;
	}

	@Override
	public boolean emitsEnergyTo(IEnergyAcceptor receiver, EnumFacing side) {
		return UE_TO_EU;
	}

	@Override
	public boolean acceptsEnergyFrom(IEnergyEmitter emitter, EnumFacing side) {
		return !UE_TO_EU;
	}

	@Override
	public double getDemandedEnergy() {
		return maxpower - power;
	}

	@Override
	public int getSinkTier() {
		return Integer.MAX_VALUE;
	}

	@Override
	public double injectEnergy(EnumFacing directionFrom, double amount, double voltage) {
		if(amount < maxpower - power){
			this.addPower(amount*10);
			return 0;
		}else{
			double p = maxpower - power;
			this.addPower(p*10);
			return amount - p;
		}
	}

	@Override
	public double getOfferedEnergy() {
		return power;
	}

	@Override
	public void drawEnergy(double amount) {
		this.getPower(amount*10);
	}

	@Override
	public int getSourceTier() {
		return Integer.MAX_VALUE;
	}
	
	private boolean firsttick = true;
	
	@Override
	public void update() {
		if(firsttick && !worldObj.isRemote){
			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
			firsttick = false;
		}
	}
	
	@Override
	public void invalidate() {
		super.invalidate();
		if(!worldObj.isRemote)
		MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
	}
	
}
