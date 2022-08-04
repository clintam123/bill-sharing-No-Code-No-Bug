package com.nocodenobug.billsharing.constants;

public enum ESort {
    RANDOM(0),
    RATING(1),
    DISCOUNT(2);

    private final int value;

    ESort(int value){
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
