package com.nocodenobug.billsharing.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    ACTIVE(1),
    THANH_TOAN(2),
    CHUA_THANH_TOAN(3),
    INACTIVE(0);

    private final int status;
}
