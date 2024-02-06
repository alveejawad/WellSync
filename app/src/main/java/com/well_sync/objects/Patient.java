package com.well_sync.objects;

import java.util.Locale;
import java.util.Objects;

public class Patient {
    public enum BloodType {
        TYPE_A,
        TYPE_B,
        TYPE_AB,
        TYPE_O
    }

    public enum Sex {
        MALE,
        FEMALE,
        UNSPECIFIED
    }

    private final String email;
    private String name;
    private BloodType bloodType;
    private Sex sex;
    private int age;

    public Patient(final String email) {
        this.email = email;
    }

    public Patient(final String email, final String name, final BloodType bloodType,
                   final Sex sex, final int age) {
        this.email = email;
        this.name = name;
        this.bloodType = bloodType;
        this.sex = sex;
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public Sex getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format(Locale.CANADA, "%s (%d / %s / %s)", name, age, sex, bloodType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return age == patient.age
                && Objects.equals(email, patient.email)
                && Objects.equals(name, patient.name)
                && bloodType == patient.bloodType
                && sex == patient.sex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name, bloodType, sex, age);
    }
}
