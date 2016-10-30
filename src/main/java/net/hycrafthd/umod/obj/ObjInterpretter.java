package net.hycrafthd.umod.obj;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import javax.print.attribute.TextSyntax;

import net.hycrafthd.corelib.util.RGBA;
import net.hycrafthd.corelib.via.Vertex;
import net.hycrafthd.umod.UMod;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.Vec3d;
import scala.tools.nsc.interactive.tests.core.TestSettings;

public class ObjInterpretter extends FileInputStream{

	private ArrayList<Vec3d> vert = new ArrayList<Vec3d>();
	private ArrayList<ObjArea> area = new ArrayList<ObjArea>();
	private ArrayList<MtlInterpretter> mtls = new ArrayList<MtlInterpretter>();

	public ObjInterpretter(File fl) throws FileNotFoundException {
		super(fl);
		try {
		Scanner sc = new Scanner(this);
		Material mtl = new BaseMaterial();
		while(sc.hasNextLine()){
			String stc = sc.nextLine();
			if(stc.startsWith("v ")){
				String[] st = stc.replace("v ", "").split(" ");
				vert.add(new Vec3d(Double.valueOf(st[0]),Double.valueOf(st[1]),Double.valueOf(st[2])));
			}
			if(stc.startsWith("f ")){
				area.add(new ObjArea(mtl,stc.replace("f ", "").split(" ")));
			}
			if(stc.startsWith("mtllib ")){
				mtls.add(new MtlInterpretter(stc.replace("mtllib ", "")));
			}
			if(stc.startsWith("usemtl ")){
				String s = stc.replace("usemtl ","");
				for (MtlInterpretter mt : mtls) {
					Material mv = mt.searchfor(s);
					if(mv != null){
						mtl = mv;
						break;
					}
				}
			}
		}
		sc.close();
			this.close();
		} catch (IOException e1) {
			UMod.log.error("Error Loading Model " + fl.getName() + " (IO Failer)", e1);
		} catch(NumberFormatException ex){
			UMod.log.error("Error Loading Model " + fl.getName() + " (Number Failer)", ex);
		} catch(ArrayIndexOutOfBoundsException e){
			UMod.log.error("Error Loading Model " + fl.getName() + " (Array Failer)", e);
		} catch (URISyntaxException e) {
			UMod.log.error("Model " + fl.getName() + " has an incorrect URI", e);
		} catch (NullPointerException e){
			UMod.log.error("Model " + fl.getName() + " has an Nullpointer", e);
		}
	}
	
	public void draw(){
		Tessellator ts = Tessellator.getInstance();
		VertexBuffer bf = ts.getBuffer();
        drawOnlyArea(bf);
        ts.draw();
		drawOnlyBoundingBox(bf);
		ts.draw();
	}
	
	public void drawOnlyArea(VertexBuffer bf){
		bf.begin(7, DefaultVertexFormats.POSITION_COLOR);
		for(ObjArea are : this.area){
			are.addVertices(bf, this.vert);
		}
	}
	
	public void drawOnlyBoundingBox(VertexBuffer bf){
		bf.begin(3, DefaultVertexFormats.POSITION_COLOR);
		for(ObjArea are : this.area){
			are.addVerticesToBounding(bf, this.vert);
		}
	}
}
