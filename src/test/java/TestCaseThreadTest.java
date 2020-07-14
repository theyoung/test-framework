import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class TestCaseThreadTest {

    @Test
    void getName() {
    }

    @Test
    void setName() {
    }

    @Test
    void getYear() {
    }

    @Test
    void setYear() {
    }

    @Test
    void onCreate() {
    }

    @Test
    void onDestroy() {
    }

    @Test
    void run() {

    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Message> blockingQueue = new ArrayBlockingQueue<>(10);

        TestCaseThread thread = new TestCaseThread();
        thread.setQueue(blockingQueue);

        Thread aaa = new Thread(thread);
        thread.addMessage("test1", "parameters1");

        aaa.start();

        Thread.sleep(1000*5);

        System.out.println("add Parameters");
        thread.addMessage("finish", "end");
//        blockingQueue.offer(new Message("finish", "end"));
        System.out.println("end Parameters");
    }
}