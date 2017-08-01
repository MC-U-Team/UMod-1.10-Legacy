package io.github.mc_umod.renderapi;

public class Vec3 {

	private double X, Y, Z;
	
	public Vec3(double x, double y, double z) {
		this.X = x;
		this.Y = y;
		this.Z = z;
	}
	
	public double X() {
		return this.X;
	}
	
	public double Y() {
		return this.Y;
	}
	
	public double Z() {
		return this.Z;
	}
	
	public void update(double x, double y, double z) {
		this.X = x;
		this.Y = y;
		this.Z = z;
	}
}
