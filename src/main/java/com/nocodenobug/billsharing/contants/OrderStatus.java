package com.nocodenobug.billsharing.contants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    ACTIVE(1),
    INACTIVE(0);

    private final int status;
}
