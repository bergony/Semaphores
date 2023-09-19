import java.util.List;

public class Producer extends Thread {
    private List<String> elements;
    private final int total;
    private final long sleep;

    public Producer(List<String> elements, int total, long sleep) {
        this.elements = elements;
        this.total = total;
        this.sleep = sleep;
        setPriority(Thread.NORM_PRIORITY);
    }

    public void run() {

        for (int i = 0; i < total; i++) {
            elements.add("E" + i);
            System.out.println("[E" + i + "] Added");

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("#Producer Finished");

    }
}
