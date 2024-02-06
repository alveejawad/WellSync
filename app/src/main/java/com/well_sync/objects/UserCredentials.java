package com.well_sync.objects;

import java.util.Objects;

public class UserCredentials {

    private final int userId;
    private final String password;

    public UserCredentials(final int userId, final String password) {
        this.userId = userId;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCredentials that = (UserCredentials) o;
        return userId == that.userId && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getPassword());
    }
}
