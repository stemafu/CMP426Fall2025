/*
 * A custom semaphore can be implemented in Java
 * using basic synchronization primitives like
 * synchronized, wait(), and notifyAll().
 * 
 * Please note that Java provides its own robust class
 * for Semaphores inside the java.util.cuncurrent.Semaphore. 
 */
public class Semaphore {
	private int count;
	
	public Semaphore(int count) {
		if(count < 0) {
			throw new IllegalArgumentException("initial count must be a positive number");
		}
		this.count = count;
	}
	
	
	/* In Java the synchronized keyword is a fundamental mechanism
	 * for achieving thread synchronization and ensuring data
	 * consistency in concurrent programming.
	 * 
	 * It allows you to control access to shared resources, preventing
	 * multiple threads from modifying the same data simultaneously,
	 * which could lead to race conditions and inconsistent states.
	 * 
	 * When you declare a Java method as synchronized the entire method
	 * becomes a critical section.
	 * 
	 * 
	 * You can also use the synchronized keyword to define a specific  
	 * block of code as critical sections
	 */
	
	public synchronized void semaphoreWait() {
		
		
		if(this.count <= 0) {
			try {
				wait(); // Wait until count becomes available.
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
		
		this.count = this.count - 1;
	}
	
	synchronized public void semaphoreSignal() {
		
		
		//if(this.count <= 0) {
			try {
				// notify all waiting threads that count has become available
				notifyAll();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		//}
		this.count = this.count + 1;
	}
	
	public synchronized int getCount() {
		return this.count;
	}
	
	public static void main(String [] args) {
		// This will allow only two threads concurrently
		
		Semaphore semaphore = new Semaphore(2);
		
		Runnable task = () ->{
			
			semaphore.semaphoreWait();
			System.out.println(Thread.currentThread().getName() + " acquired a permit");
			try {
				Thread.sleep(4000); // Simulate work
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				semaphore.semaphoreSignal();
			}
			
			
			
		};
		
		
		for(int i = 0; i < 9; i++) {
			new Thread(task, "Thread " + (i + 1)).start();
		}
	}
	
	
	/*
	 * A monitor is an object that contains 
	 * shared data (the resource being protected)
	 * procedures (functions that operate in the data)
	 * synchronization code (to ensure safe access)
	 */

}
