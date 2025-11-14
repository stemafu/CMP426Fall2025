
//Main class
public class ProducerConsumerMonitor {
 public static void main(String[] args) {
     Buffer buffer = new Buffer(5); // capacity = 5

     Producer p1 = new Producer(buffer);
     Consumer c1 = new Consumer(buffer);

     p1.start();
     c1.start();
 }
}