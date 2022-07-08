package com.nocodenobug.billsharing.constants;

public enum  RoleConstants {
    ADMIN(1),USER(0);
    private final int value;
    RoleConstants(int value) {
        this.value = value;
    }
}
