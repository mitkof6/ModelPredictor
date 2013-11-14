package mesh;

import java.util.Vector;

import math.geom3d.Point3D;

public class PointCloud {
	
	private Vector<Point3D> pointCloud;
	private long timeStamp;

	public PointCloud(long timeStamp){
		this.timeStamp = timeStamp;
		
		pointCloud = new Vector<>();
	}
	
	public void add(Point3D p){
		pointCloud.add(p);
	}
	
	public Vector<Point3D> getPointCloud(){
		
		return pointCloud;
	}
	
	public long getTimeStamp(){
		return timeStamp;
	}
}
