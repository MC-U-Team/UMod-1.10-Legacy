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
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;

public class TileEntityIC2Transformer extends TileEntityBlock implements IPowerProvieder,IEnergyConductor,IEnergySource,IEnergySink,ITeBlock{
		
	public TileEntityIC2Transformer() {
	}

	@Override
	public double getStoredPower() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void addPower(double power) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public double getPower(double powerneed) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public double getMaximalPower() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public boolean isWorking() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public String getErrorMessage() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean hasPower() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public double getPowerProducNeeds() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void setEnergy(double coun) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean needsPower() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean productsPower() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isInput() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isOutput() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean emitsEnergyTo(IEnergyAcceptor receiver, EnumFacing side) {
		return false;
	}

	@Override
	public boolean acceptsEnergyFrom(IEnergyEmitter emitter, EnumFacing side) {
		return false;
	}

	@Override
	public double getConductionLoss() {
		return 0;
	}

	@Override
	public double getInsulationEnergyAbsorption() {
		return 0;
	}

	@Override
	public double getInsulationBreakdownEnergy() {
		return 0;
	}

	@Override
	public double getConductorBreakdownEnergy() {
		return 0;
	}

	@Override
	public void removeInsulation() {
		
	}

	@Override
	public void removeConductor() {
		
	}

	@Override
	public double getDemandedEnergy() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSinkTier() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double injectEnergy(EnumFacing directionFrom, double amount, double voltage) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getOfferedEnergy() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void drawEnergy(double amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getSourceTier() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int getId() {
		return 0;
	}

	@Override
	public String getName() {
		return "";
	}

	@Override
	public void addSubBlocks(List<ItemStack> arg0, BlockTileEntity arg1, ItemBlockTileEntity arg2, CreativeTabs arg3) {		
	}

	@Override
	public boolean allowWrenchRotating() {
		return false;
	}

	@Override
	public DefaultDrop getDefaultDrop() {
		return DefaultDrop.Self;
	}

	@Override
	public TileEntityBlock getDummyTe() {
		return new TileEntityIC2Transformer();
	}

	@Override
	public float getExplosionResistance() {
		return 0;
	}

	@Override
	public float getHardness() {
		return 0;
	}

	@Override
	public HarvestTool getHarvestTool() {
		return HarvestTool.Pickaxe;
	}

	@Override
	public ResourceLocation getIdentifier() {
		return new ResourceLocation("");
	}

	public ITePlaceHandler handl;
	
	@Override
	public ITePlaceHandler getPlaceHandler() {
		return handl;
	}

	@Override
	public EnumRarity getRarity() {
		return EnumRarity.COMMON;
	}

	@Override
	public Set<EnumFacing> getSupportedFacings() {
		ArrayList<EnumFacing> arr = new ArrayList<EnumFacing>();
		for(EnumFacing fc : EnumFacing.values()){
			arr.add(fc);
		}
		return Sets.newEnumSet(arr, EnumFacing.class);
	}

	@Override
	public Class<? extends TileEntityBlock> getTeClass() {
		return TileEntityIC2Transformer.class;
	}

	@Override
	public boolean hasActive() {
		return false;
	}

	@Override
	public boolean hasItem() {
		return false;
	}

	@Override
	public void setPlaceHandler(ITePlaceHandler arg0) {
		
	}
	
}
