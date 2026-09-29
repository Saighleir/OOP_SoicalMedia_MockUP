package oopa2_25.pkg26;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AppData 
{
    // Arrays
    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<Post> posts = new ArrayList<>();
    public static ArrayList<Friend> friends = new ArrayList<>();
    public static ArrayList<Comment> comments = new ArrayList<>();

    // Current user stored
    public static User currentUser;

    // Screens
    public static ScrSplash scrSplash;
    public static ScrMenu scrMenu;
    public static ScrRegister scrRegister;
    public static ScrLogin scrLogin;
    public static ScrMainMenu scrMainMenu;
    public static ScrAddPost scrAddPost;
    public static ScrViewPosts scrViewPosts;
    public static ScrFriends scrFriends;

    //todays date as string
    public static String todaysDate() 
    {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = today.format(formatter);
        return formattedDate;
    }
    //Hours and mins
    public static String formatDateTime(LocalDateTime dateTime)
    {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return dateTime.format(formatter);
    }
    //Just date
    public static String formatDate(LocalDate date)
    {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
    }
    //Convert user String to local date
    public static LocalDate parseDate(String dateText)
    {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dateText, formatter);
    }

    // Random gen ID
    public static int generateUserID()
    {
        int id = (int)(Math.random() * (99999 - 20000 + 1)) + 20000;

        // gen until ID unique
        while(isUniqueUser(id) == false)
        {
            id = (int)(Math.random() * (99999 - 20000 + 1)) + 20000;
        }

        return id;
    }

    // Random gen ID
    public static int generatePostID()
    {
        int id = (int)(Math.random() * (99999 - 20000 + 1)) + 20000;

        // Keep generating until the ID is unique
        while(isUniquePost(id) == false)
        {
            id = (int)(Math.random() * (99999 - 20000 + 1)) + 20000;
        }

        return id;
    }

    // Random gen ID
    public static int generateCommentID()
    {
        int id = (int)(Math.random() * (99999 - 20000 + 1)) + 20000;

        // Keep generating until the ID is unique
        while(isUniqueComment(id) == false)
        {
            id = (int)(Math.random() * (99999 - 20000 + 1)) + 20000;
        }

        return id;
    }

    // Check unique
    public static boolean isUniqueUser(int id)
    {
        for(int index = 0; index < users.size(); index++)
        {
            User user = users.get(index);

            if(user.getUserID() == id)
            {
                return false;
            }
        }

        return true;
    }

    // Check unique
    public static boolean isUniquePost(int id)
    {
        for(int index = 0; index < posts.size(); index++)
        {
            Post post = posts.get(index);

            if(post.getPostID() == id)
            {
                return false;
            }
        }

        return true;
    }

    // Check unique
    public static boolean isUniqueComment(int id)
    {
        for(int index = 0; index < comments.size(); index++)
        {
            Comment comment = comments.get(index);

            if(comment.getCommentID() == id)
            {
                return false;
            }
        }

        return true;
    }

    // Check login details
    public static User validateLogin(String email, String password)
    {
        // Check
        if(email == null || email.isEmpty())
        {
            throw new IllegalArgumentException("Email is required.");
        }

        if(password == null || password.isEmpty())
        {
            throw new IllegalArgumentException("Password is required.");
        }

        for(int index = 0; index < users.size(); index++)
        {
            User user = users.get(index);

            String storedEmail = user.getEmail();
            String storedPassword = user.getPassword();

            //Check email password
            if(storedEmail.equalsIgnoreCase(email))
            {
                if(storedPassword.equals(password))
                {
                    // Do not allow locked accounts to log in
                    if(user.isLocked())
                    {
                        throw new IllegalArgumentException("Account is locked.");
                    }
                    // Update last login time
                    user.setLastLogin();
                    // Store logged-in user
                    currentUser = user;
                    return user;
                }
            }
        }
        return null;
    }

    // Adds a new user to array
    public static void registerUser(User user)
    {
        if(user == null)
        {
            throw new IllegalArgumentException("User cannot be empty.");
        }
        users.add(user);
    }

    // Adds a post to array
    public static void addPost(Post post)
    {
        if(post == null)
        {
            throw new IllegalArgumentException("Post cannot be empty.");
        }
        posts.add(post);
    }

    // Returns user posts
    public static ArrayList<Post> getMyPosts()
    {
        ArrayList<Post> result = new ArrayList<>();

        for(int index = 0; index < posts.size(); index++)
        {
            Post post = posts.get(index);

            if(currentUser != null)
            {
                int postUserID = post.getUserID();
                int currentUserID = currentUser.getUserID();
                // Add post if belongs current user
                if(postUserID == currentUserID)
                {
                    result.add(post);
                }
            }
        }
        sortPostsNewestFirst(result);
        return result;
    }

    // Returns posts Public
    public static ArrayList<Post> getPublicPosts()
    {
        ArrayList<Post> result = new ArrayList<>();

        for(int index = 0; index < posts.size(); index++)
        {
            Post post = posts.get(index);
            String security = post.getPostSecurity();
            // Add post public
            if(security.equalsIgnoreCase("Public"))
            {
                result.add(post);
            }
        }

        sortPostsNewestFirst(result);

        return result;
    }

    // Returns posts
    public static ArrayList<Post> getFriendPosts(int friendID)
    {
        ArrayList<Post> result = new ArrayList<>();

        for(int index = 0; index < posts.size(); index++)
        {
            Post post = posts.get(index);
            int postUserID = post.getUserID();
            if(postUserID == friendID)
            {
                result.add(post);
            }
        }
        sortPostsNewestFirst(result);
        return result;
    }

    // newest to oldest
    private static void sortPostsNewestFirst(ArrayList<Post> list)
{
    for(int index = 0; index < list.size(); index++)
    {
        for(int sIndex = index + 1; sIndex < list.size(); sIndex++)
        {
            Post firstPost = list.get(index);
            Post secondPost = list.get(sIndex);
            if(firstPost.getDate().isBefore(secondPost.getDate()))
            {
                Post temp = firstPost;
                list.set(index, secondPost);
                list.set(sIndex, temp);
            }
        }
    }
}

    // Adds a friend
    public static void addFriend(int friendID)
    {
        if(currentUser == null)
        {
            throw new IllegalArgumentException("No user logged in.");
        }

        int currentUserID = currentUser.getUserID();

        if(friendID == currentUserID)
        {
            throw new IllegalArgumentException("You cannot add yourself.");
        }

        // Check friend exists
        User friendUser = findUserByID(friendID);

        if(friendUser == null)
        {
            throw new IllegalArgumentException("User ID not found.");
        }

        // Check if already friends
        for(int index = 0; index < friends.size(); index++)
        {
            Friend friend = friends.get(index);

            int storedUserID = friend.getUserID();
            int storedFriendID = friend.getFriendID();

            if(storedUserID == currentUserID)
            {
                if(storedFriendID == friendID)
                {
                    throw new IllegalArgumentException("This user is already your friend.");
                }
            }
        }

        // Create and add friend
        Friend friend = new Friend(currentUserID,friendID);

        friends.add(friend);

        currentUser.addFriend();
    }

    // Adds comment to post
    public static void addComment(int postID, String text)
    {
        if(currentUser == null)
        {
            throw new IllegalArgumentException("No user logged in.");
        }
        int commentID = generateCommentID();
        int userID = currentUser.getUserID();
        Comment comment = new Comment(commentID,postID,userID,text);

        // Store comment
        comments.add(comment);
    }

    // Returns comments for a post
    public static ArrayList<Comment> getCommentsForPost(int postID)
    {
        ArrayList<Comment> result = new ArrayList<>();

        // Loop comments
        for(int index = 0; index < comments.size(); index++)
        {
            Comment comment = comments.get(index);
            int commentPostID = comment.getPostID();
            // Add comment
            if(commentPostID == postID)
            {
                result.add(comment);
            }
        }
        return result;
    }

    // Finds and returns by user ID
    public static User findUserByID(int userID)
    {
        for(int index = 0; index < users.size(); index++)
        {
            User user = users.get(index);

            int storedUserID = user.getUserID();

            if(storedUserID == userID)
            {
                return user;
            }
        }

        return null;
    }

    // Loads sample data
    public static void loadData()
    {
        // Should always be 0 on load as no file saving
        if(users.size() > 0)
        {
            return;
        }

        User user1 = new User(3355,"Frank","Stein",
                java.time.LocalDate.of(1944, 3, 2),
                "f@s.com","password");
        users.add(user1);

        User user2 = new User(4242,"Betty","Boop",
                java.time.LocalDate.of(1977, 3, 8),
                "b@b.com","password");
        users.add(user2);

        User user3 = new User(4944,"Jenny","Wot",
                java.time.LocalDate.of(1999, 5, 20),
                "j@wot.com","password");
        users.add(user3);

        User user4 = new User(1007,"James","Bond",
                java.time.LocalDate.of(2002, 6, 7),
                "j@bond.com","password");
        users.add(user4);

        User user5 = new User(8888,"Ted","Bear",
                java.time.LocalDate.of(2001, 10, 12),
                "t@b.com","password");
        users.add(user5);
        // Locked option never actully used set this as an example that it works.
        user5.setLocked(true);

        Image imagePost1 = new Image(23456,3355,"Public","myfile.txt",
                "Big file","c:/temp");
        posts.add(imagePost1);

        Text textPost1 = new Text(23467,3355,"Private","Mary had dinner");
        posts.add(textPost1);

        Image imagePost2 = new Image(23400,4242,"Public","myfile.txt","Big file",
                "c:/temp");
        posts.add(imagePost2);

        Text textPost2 = new Text(23404,4242,"Private","Mary had supper");
        posts.add(textPost2);

        Image imagePost3 = new Image(23401,4242,"Public","yourfile.txt",
                "Small file","c:/temp");
        posts.add(imagePost3);

        Text textPost3 = new Text(23444,3355,"Public","Mary had breakfast");
        posts.add(textPost3);

        Friend friend1 = new Friend(3355, 4242);
        friends.add(friend1);
        user1.addFriend();

        Friend friend2 = new Friend(3355, 4944);
        friends.add(friend2);
        user1.addFriend();

        Friend friend3 = new Friend(4242, 3355);
        friends.add(friend3);
        user2.addFriend();

        Comment comment1 = new Comment(90001,23456,4242,"Nice image!");
        comments.add(comment1);

        Comment comment2 = new Comment(90002,23444,4944,"Great post!");
        comments.add(comment2);
    }
}