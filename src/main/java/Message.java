
public class Message {
    String key;
    String param;

    public Message(String key, String param) {
        this.key = key;
        this.param = param;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}