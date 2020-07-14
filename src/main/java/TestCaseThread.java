import java.util.concurrent.BlockingQueue;

public class TestCaseThread extends TestCaseAbstract{
    public TestCaseThread() {
        super();
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public String getYear() {
        return null;
    }

    @Override
    public void setYear(String year) {

    }

    @Override
    public void onCreate() {
        System.out.println("onCreate");
    }

    @Override
    public void onDestroy() {
        System.out.println("onDestroy");
        super.onDestroy();
    }

    @Override
    public void onReceiverMessage(Message msg) {
        super.onReceiverMessage(msg);
        System.out.println(msg.key + " / " + msg.param);
    }
}
