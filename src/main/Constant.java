package main;

public class Constant {

	//Animator window
	public static boolean START_ANIMATOR;// = true;
	public static final int ANIMATOR_FPS =30;
	public static final int ANIMATOR_WIDTH = 640;
	public static final int ANIMATOR_HEIGHT = 480;

	//Camera
	public static double CAMERA_POS_X;// = 0;
	public static double CAMERA_POS_Y;// = 2;
	public static double CAMERA_POS_Z;// = +2;

	public static double CAMERA_VIEW_X;// = 0;
	public static double CAMERA_VIEW_Y;// = 2;
	public static double CAMERA_VIEW_Z;// = 10;

	public static double CAMERA_UP_X;// = 0;
	public static double CAMERA_UP_Y;// = 1;
	public static double CAMERA_UP_Z;// = 0;

	public static int CAMERA_MOVE_SPEED;// = 10;
	public static double CAMERA_ROTATE_SPEED;// = 0.03;

	//Light
	public static final float[] LIGHT_POSITION = {200f, 200f, 10f};
	
	//Grid
	public static int GRID_SIZE;// = 100;
	public static final int GRID_LINE_WIDTH = 2;

	//Axis
	public static int AXIS_LENGTH;// = 10;
	public static final int AXIS_WIDTH = 3;
	
	public static int FLOOR_Y = -10;
}
