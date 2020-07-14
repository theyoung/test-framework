import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.concurrent.BlockingQueue;

public abstract class TestCaseAbstract implements TestCaseInterface, Runnable {

    private boolean threadStatus = true;

    final String FINISH = "finish";

    BlockingQueue<Message> queue;

    public TestCaseAbstract() {
        this.onCreate();
    }

    public BlockingQueue<Message> getQueue() {
        return queue;
    }

    public void setQueue(BlockingQueue<Message> queue) {
        this.queue = queue;
    }

    public synchronized void addMessage(String key, String param){
        queue.offer(new Message(key, param));
    }

    public synchronized Message popMessage(){
        return queue.poll();
    }

    public abstract String getName();

    public abstract void setName(String name);

    public abstract String getYear();

    public abstract void setYear(String year);

    public abstract void onCreate();

    public void onDestroy(){
        threadStatus = false;
    };

    public void onReceiverMessage(Message msg){
        if(msg.key == FINISH){
            this.onDestroy();
        }
    }

    @Override
    public synchronized void run() {
        while (threadStatus) {
            Message message = this.popMessage();
            System.out.println(message);
            if(message != null){
                System.out.println(this.hashCode()+" : "+message.key+" / " + message.param + " Start..");
                onReceiverMessage(message);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(this.hashCode()+" : "+message.key+" / " + message.param + " End..");
            } else {
                System.out.println(this.hashCode()+" No Message");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
