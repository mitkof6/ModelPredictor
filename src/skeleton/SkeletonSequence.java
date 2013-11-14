package skeleton;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import javax.media.opengl.GL2;

import main.Constant;
import math.geom3d.Point3D;
import opengl.Drawable;
import predictor.KalmanFilter;

public class SkeletonSequence extends Drawable{

	private Vector<Skeleton> sequence;
	private Vector<String> jointType;
	private int FPS, SPF;
	
	private JointHierarchy jointH;
	
	private static int frameIndex = 0, skipFrame;
	
	public SkeletonSequence(JointHierarchy jointH){
		
		this.jointH = jointH;
		
		sequence = new Vector<>();
		jointType = new Vector<>();
	}
	
	public Vector<Skeleton> getSequence(){
		return sequence;
	}
	
	public int getFPS(){
		return FPS;
	}
	
	public int getSPF(){
		return SPF;
	}
	
	public Vector<String> getJointType(){
		return jointType;
	}
	
	public void loadSequence(String path) throws FileNotFoundException{
		
		Scanner scanner = new Scanner(new File(path));
		
		Skeleton skel = null;
		while(scanner.hasNext()){
			String[] tok = scanner.nextLine().split(" ");
			
			if(tok.length<2) continue;
			
			if(tok[0].equalsIgnoreCase("SPF")){
				SPF = Integer.parseInt(tok[1]);
			}else if(tok[0].equalsIgnoreCase("FPS")){
				FPS = Integer.parseInt(tok[1]);
			}else if(tok[0].equalsIgnoreCase("jt")){
				jointType.add(tok[1]);
			}else if(tok[0].equalsIgnoreCase("t")){
				if(skel!=null){
					sequence.add(skel);
				}
				skel = new Skeleton(Long.parseLong(tok[1]));
			}else if(tok[0].equalsIgnoreCase("j")){
				skel.add(constructJoint(tok));
			}
		}
		skipFrame = FPS/SPF;
		
		scanner.close();
	}
	
	public void applyFilter(){
		
		for(String type : jointType){
			KalmanFilter kX = new KalmanFilter();
			KalmanFilter kY = new KalmanFilter();
			KalmanFilter kZ = new KalmanFilter();
			for(Skeleton skel : sequence){
				Joint j = skel.getJoint(type);
				
				j.setPosition(new Point3D(
						kX.update(j.getPosition().getX()), 
						kY.update(j.getPosition().getY()), 
						kZ.update(j.getPosition().getZ())));
				
			}
		}
	}
	
	private Joint constructJoint(String[] tok){
		
		return new Joint(
				tok[1], 
				jointH.getParent(tok[1]),
				Float.parseFloat(tok[2]),
				Float.parseFloat(tok[3]),
				Float.parseFloat(tok[4]),
				Float.parseFloat(tok[5]),
				Float.parseFloat(tok[6]),
				Float.parseFloat(tok[7]),
				Float.parseFloat(tok[8]),
				Float.parseFloat(tok[9]),
				Float.parseFloat(tok[10]));
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
		
		gl.glPushMatrix();
		gl.glColor3f(1.0f, 0.0f, 0.0f);
		
		for(String type : jointType){
			Joint child = sequence.get(frameIndex).getJoint(type);
			Joint parent = sequence.get(frameIndex).getJoint(child.getParent());

			gl.glBegin(GL2.GL_LINE_LOOP);
				gl.glVertex3d(
					parent.getPosition().getX()/Constant.POSITION_SCALING,
					parent.getPosition().getY()/Constant.POSITION_SCALING,
					parent.getPosition().getZ()/Constant.POSITION_SCALING);
				gl.glVertex3d(
						child.getPosition().getX()/Constant.POSITION_SCALING,
						child.getPosition().getY()/Constant.POSITION_SCALING,
						child.getPosition().getZ()/Constant.POSITION_SCALING);
				
			gl.glEnd();
		}
		
		gl.glPopMatrix();
		
		frameIndex++;
	}
}
