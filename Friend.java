
package oopa2_25.pkg26;

import java.time.LocalDateTime;

public class Friend 
{

    private int userID;
    private int friendID;
    private LocalDateTime dateTime;

    public Friend(int userID, int friendID) 
    {
        this.userID = userID;
        this.friendID = friendID;
        this.dateTime = LocalDateTime.now();
    }

    public int getUserID()
    {
        return userID;
    }

    public int getFriendID()
    {
        return friendID;
    }

    public LocalDateTime getDateTime()
    {
        return dateTime;
    }
    
    public String displayFriend()
    {
        return "User ID: " + userID
                + "\nFriend ID: " + friendID
                + "\nDate Added: "
                + AppData.formatDateTime(dateTime);
    }
}
