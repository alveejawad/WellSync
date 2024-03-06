package com.well_sync.objects;

import java.util.Objects;

public class UserCredentials {
    public enum Role {
        DOCTOR,
        PATIENT;

    }
    private final String email;
    private final String password;
    //private final Role role;
    private final String role;

    public UserCredentials(final String email, final String password, final String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() { return role; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCredentials credentials = (UserCredentials) o;
        return Objects.equals(email, credentials.email)
                && Objects.equals(password, credentials.password)
                && role == credentials.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, role);
    }
}
