package com.nocodenobug.billsharing.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public enum ResponseStatusConstant {
    COMMON_FAIL(0, "Hệ thống đang gặp sự cố vui lòng thử lại sau."),
    SUCCESS(1, "Thành công."),

    NOT_FOUND_PRODUCT_REVIEW(100, "Sản phẩm chưa có đánh giá nào"),
    NOT_FOUND_REVIEW(100, "Review không tồn tại"),
    NO_MORE_COMMENT(102, "Không còn bình luận nào");

    private final int code;
    private final String message;

    ResponseStatusConstant(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
