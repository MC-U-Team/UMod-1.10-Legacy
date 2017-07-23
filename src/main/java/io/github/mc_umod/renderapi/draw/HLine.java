package io.github.mc_umod.renderapi.draw;

import io.github.mc_umod.renderapi.Vertex;
import io.github.mc_umod.util.RGBA;

public class HLine extends Pylogen{

	public HLine(double x,double y,double heigth,RGBA rgba,RGBA rgba2) {
		super(new Vertex(new Vec2d(x, y),rgba),
			  new Vertex(new Vec2d(x, y + heigth),rgba2),
			  new Vertex(new Vec2d(x + 4, y + heigth),rgba2),
			  new Vertex(new Vec2d(x + 4, y),rgba2));
	}
	
	public HLine(double x,double y,double heigth,RGBA rgba) {
		this(x,y,heigth,rgba,rgba);
	}
	
}
