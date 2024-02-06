package com.well_sync.application;

import java.util.Objects;
public class UserDetailsHandler {
    private final String userID;
    private final String userName;
    private final String userSex;
    private final String userAddress;
    private final String userAge;


    public UserDetailsHandler(final String newID)
        {
            this.userID = newID;
            this.userName = null;
            this.userAddress = null;
            this.userSex = null;
            this.userAge = null;
        }
    public UserDetailsHandler(final String newID, final String newUserName, final String newUserAddress, final String newUserSex, final String newUserAge)
        {
            this.userID = newID;
            this.userName = newUserName;
            this.userAddress = newUserAddress;
            this.userSex = newUserSex;
            this.userAge = newUserAge;
        }

    public String getUserID()
        {
            return (userID);
        }

    public String getUserName()
        {
            return (userName);
        }
    public String getUserAge()
        {
        return (userAge);
        }
    public String getUserAddress()
        {
            return (userAddress);
        }
    public String getUserSex()
        {
        return (userSex);
        }

    public String toString()
        {
            return String.format("Student: %s %s %s", userID, userName, userAddress, userSex, userAge);
        }

    public int hashCode()
        {
            return Objects.hash(userID, userName, userAddress, userSex, userAge);
        }

    public boolean equals(final UserDetailsHandler o)
        {
            return Objects.equals(this.userID, o.userID) &&
                    Objects.equals(this.userName, o.userName) &&
                    Objects.equals(this.userAddress, o.userAddress) &&
                    Objects.equals(this.userSex, o.userSex) &&
                    Objects.equals(this.userAge, o.userAge);
        }
}


