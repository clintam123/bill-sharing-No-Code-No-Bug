package com.nocodenobug.billsharing.payload.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SamplePagingResponse {
    private Boolean success;
    private String message;
    private Object data;
    private Pagination pagination;
}
