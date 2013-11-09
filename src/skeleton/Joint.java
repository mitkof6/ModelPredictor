package skeleton;

import math.geom3d.Point3D;

public class Joint {

	private String name;
	private String parent;
	private Point3D position;
	private double posConfidence;
	
	public Joint(String name, String parent, double px, double py, double pz, double posConfidence){
		this.name = name;
		this.parent = parent;
		position = new Point3D(px, py, pz);
		this.posConfidence = posConfidence;
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public Point3D getPosition() {
		return position;
	}
	public void setPosition(Point3D position) {
		this.position = position;
	}
	public double getPosConfident() {
		return posConfidence;
	}
	public void setPosConfident(double posConfidence) {
		this.posConfidence = posConfidence;
	}
	
	public String toString(){
		return name+" "+parent+" ["+position.getX()+", "+position.getY()+", "+position.getZ()+"]";
	}
	
}
