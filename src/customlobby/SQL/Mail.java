package customlobby.SQL;

public class Mail {

    public String from;
    public String to;
    public String msg;

    public Mail(String from, String to, String msg) {
        this.from = from;
        this.to = to;
        this.msg = msg;
    }


    public String getFrom() {
        return from;
    }

    public String getMsg() {
        return msg;
    }

    public String getTo() {
        return to;
    }
}
