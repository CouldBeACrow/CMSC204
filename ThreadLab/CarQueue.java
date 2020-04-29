import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class CarQueue {
	
	public static final int DELAY = 10;
	
	Queue<Integer> q = new LinkedList<>();
	Random r = new Random();

	public CarQueue() {

	}
	
	public void addToQueue() {
		
		class CarRunnable implements Runnable {

			// Constructor
			public CarRunnable() {
				q.add(3);
				q.add(0);
				q.add(1);
				q.add(2);
				q.add(1);
			}
			
			@Override
			public void run() {
				try {
					q.add(r.nextInt((3 - 0) + 1) + 0); 
					System.out.println("Queue size: " + q.size());
					Thread.sleep(DELAY);
				} catch (InterruptedException exception) {
					
				}
			}
			
		}
		
		CarRunnable r1 = new CarRunnable();
		Thread t1 = new Thread(r1);
		t1.start();
		
	}
	
	public int deleteQueue() {
		if (q.size() < 20) 
		{
			q.add(r.nextInt((3 - 0) + 1) + 0); 
			q.add(r.nextInt((3 - 0) + 1) + 0); 
			q.add(r.nextInt((3 - 0) + 1) + 0); 
			q.add(r.nextInt((3 - 0) + 1) + 0);  
			q.add(r.nextInt((3 - 0) + 1) + 0); 
			q.add(r.nextInt((3 - 0) + 1) + 0); 
			q.add(r.nextInt((3 - 0) + 1) + 0); 
			q.add(r.nextInt((3 - 0) + 1) + 0); 
			q.add(r.nextInt((3 - 0) + 1) + 0); 
			System.out.println("Queue size: " + q.size());
		}
		else if (!q.isEmpty()) {
			return q.remove();
		}
		return q.remove();
	}
}

