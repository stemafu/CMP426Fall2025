
public class Buffer {
	private int [] buffer;
	private int count, in, out;
	private final int size;
	
	public Buffer(int size) {
		this.size = size;
		this.buffer = new int[size];
		count = in = out = 0;
	}
	
	public synchronized void produce(int item) {
		while(count == this.size) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		buffer[in] = item;
		in = (in + 1) % size;
		count++;
		
		System.out.println("produced");
		notifyAll();
	}
	
	public synchronized int consume() {
		while(count == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		int item = buffer[out];
		out = (out + 1) % size;
		count--;
		
		System.out.println("consumed");
		notifyAll();
		return item;
	}
	
	
	
}


class Producer extends Thread {
    private final Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                buffer.produce(i);
                Thread.sleep(1000); // simulate time to produce
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}




//Consumer thread
class Consumer extends Thread {
 private final Buffer buffer;

 public Consumer(Buffer buffer) {
     this.buffer = buffer;
 }

 public void run() {
     try {
         for (int i = 1; i <= 10; i++) {
             buffer.consume();
             Thread.sleep(150); // simulate time to consume
         }
     } catch (InterruptedException e) {
         Thread.currentThread().interrupt();
     }
 }
}



