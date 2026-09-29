
package oopa2_25.pkg26;

import java.time.LocalDateTime;

public class Post 
{

    private int postID;
    private int userID;
    private String postSecurity;
    private LocalDateTime date;

    public Post(int postID, int userID, String postSecurity) {
        this.postID = postID;
        this.userID = userID;
        this.postSecurity = postSecurity;
        this.date = LocalDateTime.now();
    }

    public int getPostID() 
    {
        return postID;
    }

    public int getUserID() 
    {
        return userID;
    }

    public String getPostSecurity() 
    {
        return postSecurity;
    }

    public LocalDateTime getDate() 
    {
        return date;
    }

    public String displayPost() 
    {
        return "Post ID: " + postID
                + "\nUser ID: " + userID
                + "\nDate: " + AppData.formatDateTime(date)
                + "\nSecurity: " + postSecurity;
    }
}
