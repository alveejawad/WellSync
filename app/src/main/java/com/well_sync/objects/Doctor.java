package com.well_sync.objects;

import java.util.Objects;

public class Doctor {
    private final String email;

    public Doctor(final String email) {
        this.email = email;
    }

    public Doctor(final String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(email, doctor.email) &&
                Objects.equals(firstName, doctor.firstName) &&
                Objects.equals(lastName, doctor.lastName);
    }
}
