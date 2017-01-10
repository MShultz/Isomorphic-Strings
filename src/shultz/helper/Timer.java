package shultz.helper;

public class Timer {
	private long startTime, endTime;
	public void start(){
		startTime = System.currentTimeMillis();
	}
	public void end(){
		endTime = System.currentTimeMillis();
		announce(calculate());
	}
	private long calculate(){
		return endTime - startTime;
	}
	private void announce(long executionTime){
		System.out.println("Total execution time: " + executionTime + " milliseconds");
	}
}
