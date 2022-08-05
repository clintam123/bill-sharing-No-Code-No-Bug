package com.nocodenobug.billsharing.constants;

import lombok.Getter;

@Getter
public enum ResponseStatusConstant {
    COMMON_FAIL(0, "Hệ thống đang gặp sự cố vui lòng thử lại sau."),
    FAIL(0, "Thất bại"),
    SUCCESS(1, "Thành công."),

    NOT_FOUND_PRODUCT_REVIEW(100, "Sản phẩm chưa có đánh giá nào"),
    NOT_FOUND_REVIEW(100, "Review không tồn tại"),
    NO_MORE_COMMENT(102, "Không còn bình luận nào"),

    NOT_FOUND_SATISTICS(103,"Không có giao dịch nào"),

    DATE_INVALID(104,"Ngày bắt đầu phải nhỏ hơn ngày kết thúc"),

    NOT_FOUND_VENDORID(105,"Vendor Id Không tồn tại")

    ;




    private final int code;
    private final String message;

    ResponseStatusConstant(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
