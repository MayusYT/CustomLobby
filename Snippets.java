// Init
SQLConfig conf = new SQLConfig();
conf.initialize("host", "user", "password", "dbName");


// Returns true if connected otherwise returns false
boolean conf.canConnect();


// Friend requests

// Adds a Friend request
boolean conf.addFriendReq("Player1", "Player2");

// Get all friend request off one player
try {
    List<String> reqs = conf.getFriendReqs("Hi");
        for (String s: reqs) {
            System.out.println(s);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

// Removes a friend request
boolean conf.removeFriendReq("Name1", "Name2");


// Friends

// Adds a Friend
boolean conf.addFriend("Name1", "Name2");

// Gets all Friends from a Player
try {
    List<String> list = conf.getFriends("Name");
    for (String s: list) {
        System.out.println(s);
    }
} catch (SQLException e) {
    e.printStackTrace();
}
// Removes A friend
boolean conf.removeFriend("Hi", "ashdj");




// Warns

// Add Warn
try {
    boolean conf.addWarn("Name");
} catch(SQLExeption e){
    e.printStackTrace();
}

// GetWarncount
try {
    int conf.getWarncount("Name");
} catch (SQLException e) {
    e.printStackTrace();
}

//Remove Warn
boolean conf.removeWarn("Name");



// Temp Ban

// Add temp ban
boolean conf.addTempBan("Name", "Reason", int oldmillis, int banneduntil);

// get Temp Bans
try {
    List<TempBan> bans = conf.getTempBan("Name");
    for(TempBan ban : bans) {
        System.out.println(ban.isIstempBanned() + " : " + ban.getName() + " : " + ban.getReason() + " : " + ban.getOldmillis() + " : " + ban.getBanneduntil());
    }
} catch (SQLException e) {
    e.printStackTrace();
}

// Bans

// getBans
try {
    List<PermBan> bans = conf.getBan("Name");
    for (int i = 0; i < bans.size(); i++) {
        PermBan p = bans.get(i);
        if(p.isBanned()) {
            System.out.println(p.getName()  + " : " + p.getReason() + " : " + p.isBanned());
        } else {
            System.out.println("nicht gebannt!");
        }
    }
} catch (SQLException e) {
    e.printStackTrace();
}


// Add Ban
boolean conf.addBan("Spieler", "Reason");
// Remove Ban
boolean conf.removeBan("Spieler");