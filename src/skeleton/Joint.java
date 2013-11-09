package skeleton;

import math.geom3d.Point3D;

public class Joint {

	private String name;
	private String parent;
	private Point3D position;
	private double posConfidence;
	
	public Joint(String name, double px, double py, double pz, double posConfidence){
		this.name = name;
		position = new Point3D(px, px, pz);
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
	
	
	
}
