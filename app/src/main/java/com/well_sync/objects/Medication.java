package com.well_sync.objects;

public class Medication {
    public final int maxQuantity = 5;
    public final int maxDosage = 5;
    private String name;
    private int quantity; // number of tablets?
    private int dosage;

    public Medication(String name, int quantity, int dosage) {
        this.name = name;
        this.quantity = quantity;
        this.dosage = dosage;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    public int getDosage(){
        return this.dosage;
    }
}
