package io.github.mc_umod.renderapi.draw;

import io.github.mc_umod.renderapi.Vertex;
import io.github.mc_umod.util.RGBA;

public class VLine extends Pylogen{

	public VLine(double x,double y,double width,RGBA rgba,RGBA rgba2) {
		super(new Vertex(new Vec2d(x, y),rgba),
			  new Vertex(new Vec2d(x + width, y),rgba2),
			  new Vertex(new Vec2d(x + width, y + 4),rgba2),
			  new Vertex(new Vec2d(x, y + 4),rgba2));
	}
	
	public VLine(double x,double y,double width,RGBA rgba) {
		this(x,y,width,rgba,rgba);
	}
	
}
