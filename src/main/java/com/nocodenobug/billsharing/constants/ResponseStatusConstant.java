package com.nocodenobug.billsharing.constants;

import lombok.Getter;

@Getter
public enum ResponseStatusConstant {
    COMMON_FAIL(0, "Hệ thống đang gặp sự cố vui lòng thử lại sau."),
    SUCCESS(1, "Thành công."),

    NOT_FOUND_PRODUCT_REVIEW(100, "Sản phẩm chưa có đánh giá nào"),
    NOT_FOUND_REVIEW(100, "Review không tồn tại");

    private final int code;
    private final String message;

    ResponseStatusConstant(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
