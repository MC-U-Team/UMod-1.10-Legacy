package io.github.mc_umod.renderapi.draw;

import static org.lwjgl.opengl.GL11.*;

import io.github.mc_umod.renderapi.Vertex;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.util.math.Vec3d;

public class Pylogen {

	private Vertex[] vertices; 
	private Tessellator tesslate;
	private VertexBuffer buffer;
	
	public Pylogen(Vertex... vec3ds) {
		this.vertices = vec3ds;
		this.tesslate = Tessellator.getInstance();
		this.buffer = this.tesslate.getBuffer();
	}
	
	public void drawNormal(){
		glBegin(GL_POLYGON);
		for(Vertex vert : vertices){
			vert.drawNormal();
		}
		glEnd();
	}
	
	public void draw(int glMode,VertexFormat format){
		this.buffer.begin(glMode, format);
		for(Vertex vert : vertices){
			vert.draw(this.buffer);
		}
		tesslate.draw();
	}
	
	public void draw(VertexFormat format){
		this.draw(6, format);
	}
	
	public void draw(){
		this.draw(6, DefaultVertexFormats.POSITION_COLOR);
	}
	
	public Vec3d[] getVertices() {
		return vertices;
	}
	
}
