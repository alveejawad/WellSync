package com.well_sync.objects;

import java.util.Objects;

public class UserCredentials {
    public enum Role {
        DOCTOR,
        PATIENT,
        UNSPECIFIED;
        public static UserCredentials.Role fromString(String role) {
            if (role.toUpperCase() == "DOCTOR") {
                return DOCTOR;
            }
            else if (role.toUpperCase() == "PATIENT"){
                return PATIENT;
            }
            return UNSPECIFIED;
        }
    }

    private final String email;
    private final String password;
    private Role role;

    public UserCredentials(final String email, final String password) {
        this.email = email;
        this.password = password;
    }
    public UserCredentials(final String email, final String password, final String role) {
        this.email = email;
        this.password = password;
        this.role = Role.fromString(role);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    public Role getRole() { return role; }

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
