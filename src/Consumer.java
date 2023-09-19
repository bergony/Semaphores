import java.util.List;
import java.util.concurrent.Semaphore;

public class Consumer extends Thread {

    private List<String> elements;
    private final int total;
    private final long sleep;
    private Semaphore semaphore;

    public Consumer(List<String> elements, int total, long sleep, Semaphore semaphore) {
        this.elements = elements;
        this.total = total;
        this.sleep = sleep;
        setPriority(Thread.MIN_PRIORITY);
        this.semaphore = semaphore;

    }

    public void run() {
        try {
            semaphore.acquire();
            for (int count = 0; count < total; ) {
                synchronized (elements) {
                if (!elements.isEmpty()) {
                    String element = elements.remove(0);
                    count++;
                    System.out.println("[" + element + "] Removed"+super.getName());
                }

                Thread.sleep(sleep);
                }

            }
            semaphore.release();

        } catch (InterruptedException e) {
            semaphore.release();
            throw new RuntimeException(e);
        }
        System.out.println("#Consumer Finished");
    }
}