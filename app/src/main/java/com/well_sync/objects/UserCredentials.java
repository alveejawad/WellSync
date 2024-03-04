package com.well_sync.objects;

import java.util.Objects;

public class UserCredentials {

    public enum Role {

        DOCTOR,

        PATIENT;

        public static Role fromString(String type){
            if (type.equals("Doctor")) {
                return Role.DOCTOR;
            }
            return Role.PATIENT;
        }
    }
    private final String email;
    private final String password;

    private final Role role;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCredentials that = (UserCredentials) o;
        return Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, role);
    }
}
