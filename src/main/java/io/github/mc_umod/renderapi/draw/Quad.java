package io.github.mc_umod.renderapi.draw;

import io.github.mc_umod.renderapi.Vertex;
import io.github.mc_umod.util.RGBA;

public class Quad extends Pylogen {

	public Quad(double x,double y,double width,double height,RGBA rgba) {
		this(x, y, width, height, rgba, rgba, rgba, rgba);
	}
	
	public Quad(double x,double y,double width,double height,RGBA rgba1,RGBA rgba2,RGBA rgba3,RGBA rgba4) {
		super(new Vertex(new Vec2d(x, y),rgba1),
			  new Vertex(new Vec2d(x + width, y),rgba2),
			  new Vertex(new Vec2d(x + width, y - height),rgba3),
			  new Vertex(new Vec2d(x, y - height),rgba4));
	}
	
}
