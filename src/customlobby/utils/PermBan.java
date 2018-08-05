package customlobby.utils;

public class PermBan {

    boolean isBanned;
    String reason;
    String name;

    public PermBan(boolean isBanned, String reason, String name) {
        this.isBanned = isBanned;
        this.name = name;
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public String getName() {
        return name;
    }

    public boolean isBanned() {
        return isBanned;
    }
}
