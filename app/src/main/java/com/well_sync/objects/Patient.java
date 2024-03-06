package com.well_sync.objects;

import java.util.Locale;
import java.util.Objects;

public class Patient {
    public enum BloodType {
        TYPE_A,
        TYPE_B,
        TYPE_AB,
        TYPE_O,
        UNKNOWN;

        public static BloodType fromString(String type) {
            if (type == null) return BloodType.UNKNOWN;

            switch (type){
                case "A+":
                case "A-":
                    return BloodType.TYPE_A;
                case "B+":
                case "B-":
                    return BloodType.TYPE_B;
                case "AB+":
                case "AB-":
                    return BloodType.TYPE_AB;
                case "O+":
                case "O-":
                    return BloodType.TYPE_O;

                default:
                    return BloodType.UNKNOWN;
            }
        }
    }

    public enum Sex {
        MALE,
        FEMALE,
        UNSPECIFIED;

        public static Sex fromString(String sex) {
            if (sex == null) return Sex.UNSPECIFIED;
            switch (sex.toUpperCase()) {
                case "M":
                case "MALE":
                    return Sex.MALE;
                case "F":
                case "FEMALE":
                    return Sex.FEMALE;
                default: return Sex.UNSPECIFIED;
            }
        }
    }

    // defined here in case future extensions of Patient want to change this value,
    // and the validator will still work
    public final int MAX_AGE = 122;

    private final String email;
    private String firstName;
    private String lastName;
    private BloodType bloodType;
    private Sex sex;
    private int age;

    public Patient(final String email) {
        this.email = email;
    }

    public Patient(final String email, final String firstName, final String lastName,
                   final String bloodType, final String sex, final int age) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bloodType = BloodType.fromString(bloodType);
        this.sex = Sex.fromString(sex);
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() { return lastName; }
    
    public String getName() {return firstName + lastName;}

    public BloodType getBloodType() {
        return bloodType;
    }

    public Sex getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
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
        return String.format(Locale.CANADA, "%s %s (%d / %s / %s)", firstName, lastName, age, sex, bloodType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return age == patient.age
                && Objects.equals(email, patient.email)
                && Objects.equals(firstName, patient.firstName)
                && Objects.equals(lastName, patient.lastName)
                && bloodType == patient.bloodType
                && sex == patient.sex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, firstName, lastName, bloodType, sex, age);
    }
}
