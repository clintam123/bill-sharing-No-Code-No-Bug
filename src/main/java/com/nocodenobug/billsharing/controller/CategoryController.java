package com.nocodenobug.billsharing.controller;

import com.nocodenobug.billsharing.model.dto.CategoryDto;
import com.nocodenobug.billsharing.response.Pagination;
import com.nocodenobug.billsharing.response.SamplePagingResponse;
import com.nocodenobug.billsharing.response.SampleResponse;
import com.nocodenobug.billsharing.service.category.*;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CreateCategoryService createCategoryService;
    private final DeleteCategoryService deleteCategoryService;
    private final GetAllCategoriesService getAllCategoriesService;
    private final GetCategoryByIdService getCategoryByIdService;
    private final UpdateCategoryService updateCategoryService;

    public CategoryController(CreateCategoryService createCategoryService, DeleteCategoryService deleteCategoryService, GetAllCategoriesService getAllCategoriesService, GetCategoryByIdService getCategoryByIdService, UpdateCategoryService updateCategoryService) {
        this.createCategoryService = createCategoryService;
        this.deleteCategoryService = deleteCategoryService;
        this.getAllCategoriesService = getAllCategoriesService;
        this.getCategoryByIdService = getCategoryByIdService;
        this.updateCategoryService = updateCategoryService;
    }

    @GetMapping("")
    public ResponseEntity<?> getCategories(@RequestParam(value = "page") int page,
                                              @RequestParam(value = "page_size") int pageSize){
        Page<CategoryDto> categoryDtoPage = getAllCategoriesService.getAllCategories(page, pageSize);
        return ResponseEntity.ok(
                SamplePagingResponse.builder()
                        .success(true)
                        .message("Success")
                        .data(categoryDtoPage.getContent())
                        .pagination(Pagination.builder().page(page).pageSize(pageSize)
                                .totalPage(categoryDtoPage.getTotalPages())
                                .totalRecord(categoryDtoPage.getTotalElements()).build())
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<SampleResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                SampleResponse.builder()
                        .success(true)
                        .message("Success")
                        .data(getCategoryByIdService.getCategoryById(id))
                        .build()
        );
    }

    @PostMapping("")
    public ResponseEntity<SampleResponse> create(@Validated @RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(
                SampleResponse.builder()
                        .success(true)
                        .message("Success")
                        .data(createCategoryService.createCategory(categoryDto))
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<SampleResponse> update(@PathVariable Long id,@Validated @RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(
                SampleResponse.builder()
                        .success(true)
                        .message("Success")
                        .data(updateCategoryService.updateCategory(id, categoryDto))
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SampleResponse> delete(@PathVariable Long id){
        deleteCategoryService.deleteCategory(id);
        return ResponseEntity.ok(
                SampleResponse.builder()
                        .success(true)
                        .message("Success")
                        .data(null)
                        .build()
        );
    }
}
