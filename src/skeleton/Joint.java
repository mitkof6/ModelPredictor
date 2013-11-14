package skeleton;

import com.jogamp.graph.math.Quaternion;

import math.geom3d.Point3D;

public class Joint {

	private String name;
	private String parent;
	private Point3D position;
	private float posConfidence;
	private Quaternion orientation;
	private float oriConfidence;
	
	public Joint(String name, String parent, float px, float py, float pz, float posConfidence,
			float qx, float qy, float qz, float qw, float oriConfidence){
		this.name = name;
		this.parent = parent;
		position = new Point3D(px, py, pz);
		this.posConfidence = posConfidence;
		
		orientation = new Quaternion((float)qx, (float)qy, (float)qz, (float)qw);
		this.oriConfidence = oriConfidence;
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



	public float getPosConfidence() {
		return posConfidence;
	}



	public void setPosConfidence(float posConfidence) {
		this.posConfidence = posConfidence;
	}



	public Quaternion getOrientation() {
		return orientation;
	}



	public void setOrientation(Quaternion orientation) {
		this.orientation = orientation;
	}



	public float getOriConfidence() {
		return oriConfidence;
	}



	public void setOriConfidence(float oriConfidence) {
		this.oriConfidence = oriConfidence;
	}



	public String toString(){
		return name+" "+parent+" ["+position.getX()+", "+position.getY()+", "+position.getZ()+"]";
	}
	
	@SuppressWarnings("unused")
	private double[] quaternionToEuler(double qx, double qy, double qz, double qw) {
        double[] angles = new double[3];

        double sqw = qw * qw;
        double sqx = qx * qx;
        double sqy = qy * qy;
        double sqz = qz * qz;
        double unit = sqx + sqy + sqz + sqw; // if normalized is one, otherwise
        // is correction factor
        double test = qx * qy + qz * qw;
        if (test > 0.499 * unit) { // singularity at north pole
            angles[1] = 2 * Math.atan2(qx, qw);
            angles[2] = Math.PI/2;
            angles[0] = 0;
        } else if (test < -0.499 * unit) { // singularity at south pole
            angles[1] = -2 * Math.atan2(qx, qw);
            angles[2] = -Math.PI/2;
            angles[0] = 0;
        } else {
            angles[1] = Math.atan2(2 * qy * qw - 2 * qx * qz, sqx - sqy - sqz + sqw); // roll or heading 
            angles[2] = Math.asin(2 * test / unit); // pitch or attitude
            angles[0] = Math.atan2(2 * qx * qw - 2 * qy * qz, -sqx + sqy - sqz + sqw); // yaw or bank
        }
        return angles;
    }
	
}
