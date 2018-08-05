package customlobby.utils;

public class TempBan {

    boolean istempBanned;
    String reason;
    String name;
    int oldmillis;
    int banneduntil;

    public TempBan(boolean istempBanned, String reason, String name, int oldmillis, int banneduntil) {
        this.istempBanned = istempBanned;
        this.reason = reason;
        this.name = name;
        this.banneduntil = banneduntil;
        this.oldmillis = oldmillis;
    }

    public boolean isIstempBanned() {
        return istempBanned;
    }
    public String getName() {
        return name;
    }
    public int getBanneduntil() {
        return banneduntil;
    }
    public int getOldmillis() {
        return oldmillis;
    }
    public String getReason() {
        return reason;
    }
}
