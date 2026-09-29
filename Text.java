
package oopa2_25.pkg26;

public class Text extends Post {

    private String text;

    public Text(int postID, int userID, String postSecurity, String text) {
        super(postID, userID, postSecurity);

        if (text == null || text.isEmpty())
        {
            throw new IllegalArgumentException("Text cannot be empty");
        }

        if (text.length() > 300) 
        {
            throw new IllegalArgumentException("Text cannot exceed 300 characters");
        }

        this.text = text;
    }

    public String getText() 
    {
        return text;
    }

    @Override
    public String displayPost() 
    {
        return super.displayPost()
                + "\nType: Text"
                + "\nText: " + text;
    }
}
