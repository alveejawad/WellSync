package com.well_sync.objects;

import androidx.annotation.NonNull;

import java.util.Locale;
import java.util.Objects;

public class Patient {
    enum BloodType {
        TYPE_A,
        TYPE_B,
        TYPE_AB,
        TYPE_O
    }

    enum Sex {
        MALE,
        FEMALE,
        UNSPECIFIED
    }

    private final int id;
    private String name;
    private BloodType bloodType;
    private Sex sex;
    private int age;

    public Patient(final int id) {
        this.id = id;
    }

    public Patient(final int id, final String name, final BloodType bloodType,
                   final Sex sex, final int age) {
        this.id = id;
        this.name = name;
        this.bloodType = bloodType;
        this.sex = sex;
        this.age = age;
    }

    public int getId() {
        return id;
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

    @NonNull
    @Override
    public String toString() {
        return String.format(Locale.CANADA, "%s (%d / %s / %s)", name, age, sex, bloodType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return id == patient.id
                && age == patient.age
                && Objects.equals(name, patient.name)
                && bloodType == patient.bloodType
                && sex == patient.sex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, bloodType, sex, age);
    }
}
