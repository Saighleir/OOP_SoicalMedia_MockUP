
package oopa2_25.pkg26;

import java.time.LocalDateTime;

public class Comment 
{

    private int commentID;
    private int postID;
    private int userID;
    private String text;
    private LocalDateTime date;

    public Comment(int commentID, int postID, int userID, String text)
    {

        if (text == null || text.isEmpty()) 
        {
            throw new IllegalArgumentException("Comment cannot be empty");
        }
        this.commentID = commentID;
        this.postID = postID;
        this.userID = userID;
        this.text = text;
        this.date = LocalDateTime.now();
    }

    public int getCommentID()
    {
        return commentID;
    }

    public int getPostID() 
    {
        return postID;
    }

    public int getUserID() {
        return userID;
    }

    public String getText()
    {
        return text;
    }

    public String displayComment() 
    {
        return "User "+ userID + ": " + text 
                + " ("+ AppData.formatDateTime(date)+ ")";
    }
}
