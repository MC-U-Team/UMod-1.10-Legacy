package net.hycrafthd.umod.entity;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityGenerator extends EntityFX{
	
	public EntityGenerator(World w) {
		super(w);
	}

	public EntityGenerator(World worldIn, BlockPos p) {
		super(worldIn, p);
		this.setPosition((double) p.getX() + 0.5D, (double) p.getY() + 0.5D, (double) p.getZ() + 0.5D);
		this.setEntityBoundingBox(new AxisAlignedBB(p, p.add(1, 1, 1)));
		this.setRotation(0, 180);
		this.facingDirection = EnumFacing.NORTH;
	}
	
}
