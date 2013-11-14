package predictor;

public class KalmanFilter {
	private double Q = 0.00001;
	private double R = 0.1;
	private double P = 1, X = 0, K;

	private void measurementUpdate(){
		K = (P + Q) / (P + Q + R);
		//P = R * (P + Q) / (R + P + Q);
		P = R * K;
	}

	public double update(double measurement){
		measurementUpdate();
		double result = X + (measurement - X) * K;
		X = result;

		return result;
	}
}