import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Main {


    public static void main(String[] args) throws InterruptedException {

        List<String> list = Collections.synchronizedList(new ArrayList<>());

        Semaphore semaphore = new Semaphore(2, true);

        Producer p = new Producer(list, 90, 60);
        Consumer c1 = new Consumer(list, 30 , 20, semaphore);
        Consumer c2 = new Consumer(list, 30 , 20, semaphore);
        Consumer c3 = new Consumer(list, 30 , 20, semaphore);

        p.start();
        c1.start();
        c2.start();
        c3.start();

        p.join();
        c1.join();
        c2.join();
        c3.join();


        System.out.println("Final List=>"+list.toString());

    }
}