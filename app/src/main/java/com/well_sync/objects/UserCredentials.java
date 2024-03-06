package com.well_sync.objects;

import java.util.Objects;

public class UserCredentials {
    private final String email;
    private final String password;


    public UserCredentials(final String email, final String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCredentials credentials = (UserCredentials) o;
        return Objects.equals(email, credentials.email)
                && Objects.equals(password, credentials.password);

    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }
}
