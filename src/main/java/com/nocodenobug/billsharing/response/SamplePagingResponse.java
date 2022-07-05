package com.nocodenobug.billsharing.response;

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
