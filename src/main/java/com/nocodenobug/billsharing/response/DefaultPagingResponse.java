package com.nocodenobug.billsharing.response;

import com.nocodenobug.billsharing.constants.ResponseStatusConstant;
import com.nocodenobug.billsharing.exceptions.ProjectException;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Data
public class DefaultPagingResponse<T> {
    private Integer status;
    private String message;
    private List<T> data;
    private Pagination pagination;


    public static <T> DefaultPagingResponse<T> success(Page<T> page) {
        DefaultPagingResponse<T> response = new DefaultPagingResponse<>();
        response.setStatus(ResponseStatusConstant.SUCCESS.getCode());
        response.setMessage(ResponseStatusConstant.SUCCESS.getMessage());
        response.setData(page.getContent());
        response.setPagination(Pagination.builder()
                .page(page.getPageable().getPageNumber())
                .pageSize(page.getPageable().getPageSize())
                .totalPage(page.getTotalPages())
                .totalRecord(page.getTotalElements())
                .build());
        return response;
    }
}