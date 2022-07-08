package com.nocodenobug.billsharing.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductStatus {
    ACTIVE(1),
    INACTIVE(0);

    private final int status;
}
