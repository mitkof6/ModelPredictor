package mesh;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import javax.media.opengl.GL2;

import opengl.Drawable;
import main.Constant;
import math.geom3d.Point3D;


public class PointCloudSequence extends Drawable{

	private Vector<PointCloud> sequence;
	private int FPS, SPF;

	private static int frameIndex = 0, skipFrame;

	public PointCloudSequence(){
		sequence = new Vector<>();
	}

	public Vector<PointCloud> getSequence(){
		return sequence;
	}

	public void loadSequence(String path) throws FileNotFoundException{

		Scanner scanner = new Scanner(new File(path));

		PointCloud pointCloud = null;

		while(scanner.hasNext()){
			String[] tok = scanner.nextLine().split(" ");

			if(tok.length<2) continue;

			if(tok[0].equalsIgnoreCase("SPF")){
				SPF = Integer.parseInt(tok[1]);
			}else if(tok[0].equalsIgnoreCase("FPS")){
				FPS = Integer.parseInt(tok[1]);
			}else if(tok[0].equalsIgnoreCase("p")){
				pointCloud.add(new Point3D(
						Double.parseDouble(tok[1]),
						Double.parseDouble(tok[2]),
						Double.parseDouble(tok[3])));
			}else if(tok[0].equalsIgnoreCase("t")){
				if(pointCloud!=null){
					sequence.add(pointCloud);
				}
				pointCloud = new PointCloud(Long.parseLong(tok[1]));
			}

		}

		skipFrame = FPS/SPF;
		scanner.close();
	}


	@Override
	public void draw(GL2 gl) {

		if(sequence.isEmpty()) return;

		//sync
		skipFrame--;
		if(skipFrame!=0){
			return;
		}else{
			skipFrame = FPS/SPF;
		}

		//frame index
		if(sequence.size()<=frameIndex){
			frameIndex = 0;
		}

		//frame index
		if(sequence.size()<=frameIndex){
			frameIndex = 0;
		}

		gl.glPushMatrix();
		gl.glColor3f(1.0f, 1.0f, 0.0f);
		
		for(Point3D p : sequence.get(frameIndex).getPointCloud()){
			
			gl.glBegin(GL2.GL_POINTS);
			
				gl.glVertex3d(
					p.getX()/Constant.POSITION_SCALING,
					p.getY()/Constant.POSITION_SCALING,
					p.getZ()/Constant.POSITION_SCALING);

			gl.glEnd();
		}

		gl.glPopMatrix();

		frameIndex++;

	}
}
