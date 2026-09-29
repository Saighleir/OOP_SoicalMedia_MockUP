
package oopa2_25.pkg26;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class User {

    private int userID;
    private String firstName;
    private String surname;
    private LocalDate dateOfBirth;
    private String email;
    private String password;
    private boolean locked;
    private LocalDateTime lastLogin;
    private LocalDateTime registeredDate;
    private int friendsCount;

    public User(int userID, String firstName, String surname,
                LocalDate dateOfBirth, String email, String password) 
    {

        if (firstName == null || firstName.isEmpty()) 
        {
            throw new IllegalArgumentException("First name required");
        }

        if (surname == null || surname.isEmpty()) 
        {
            throw new IllegalArgumentException("Surname required");
        }

        if (email == null || !email.contains("@")) 
        {
            throw new IllegalArgumentException("Invalid email");
        }

        if (password == null || password.length() < 6) 
        {
            throw new IllegalArgumentException("Password must be at least 6 characters");
        }

        this.userID = userID;
        this.firstName = firstName;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.locked = false;
        this.lastLogin = null;
        this.registeredDate = LocalDateTime.now();
        this.friendsCount = 0;
    }

    public int getUserID() 
    {
        return userID;
    }

    public String getFirstName() 
    {
        return firstName;
    }

    public String getSurname() 
    {
        return surname;
    }

    public LocalDate getDateOfBirth()
    {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword()
    {
        return password;
    }

    public boolean isLocked() 
    {
        return locked;
    }

    public LocalDateTime getLastLogin() 
    {
        return lastLogin;
    }

    public LocalDateTime getRegisteredDate() 
    {
        return registeredDate;
    }

    public int getFriendsCount() 
    {
        return friendsCount;
    }

    public void setLocked(boolean locked) 
    {
        this.locked = locked;
    }

    public void setLastLogin() 
    {
        this.lastLogin = LocalDateTime.now();
    }

    public void addFriend() 
    {
        friendsCount++;
    }

    public String displayUser() 
    {
        String lastLoginText;

    if(lastLogin == null)
        {
            lastLoginText = "Never";
        }
    else
        {
            lastLoginText =
                    AppData.formatDateTime(lastLogin);
        }
    return "User ID: " + userID
            + "\nName: " + firstName + " " + surname
            + "\nDOB: " + AppData.formatDate(dateOfBirth)
            + "\nEmail: " + email
            + "\nFriends: " + friendsCount
            + "\nRegistered: "
            + AppData.formatDateTime(registeredDate)
            + "\nLast Login: " + lastLoginText;
    }
}
