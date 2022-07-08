package com.nocodenobug.billsharing.constants;

import lombok.Getter;

@Getter
public enum StatusConstants {
    ACTIVE(1),
    INACTIVE(0),
    DELETED(-1);

    private final int value;

    StatusConstants(int value) {
        this.value = value;
    }
}
