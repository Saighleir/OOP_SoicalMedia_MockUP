
package oopa2_25.pkg26;

public class Image extends Post {

    private String name;
    private String description;
    private String location;

    public Image(int postID, int userID, String postSecurity,
                 String name, String description, String location) 
    {

        super(postID, userID, postSecurity);

        if (name == null || name.isEmpty()) 
        {
            throw new IllegalArgumentException("Image name required");
        }

        if (description == null || description.isEmpty()) 
        {
            throw new IllegalArgumentException("Image description required");
        }

        if (location == null || location.isEmpty()) 
        {
            throw new IllegalArgumentException("Image location required");
        }

        this.name = name;
        this.description = description;
        this.location = location;
    }

    public String getName() 
    {
        return name;
    }

    public String getDescription() 
    {
        return description;
    }

    public String getLocation() 
    {
        return location;
    }

    @Override
    public String displayPost()
    {
        return super.displayPost()
                + "\nType: Image"
                + "\nName: " + name
                + "\nDescription: " + description
                + "\nLocation: " + location;
    }
}
