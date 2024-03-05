package com.well_sync.objects;

public class Medication {
    public final int maxQuantity = 5;
    private String name;
    private int quantity; // number of tablets?

    public Medication(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
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
}
