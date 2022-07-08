package com.nocodenobug.billsharing.controller;

import com.nocodenobug.billsharing.model.dto.CategoryDto;
import com.nocodenobug.billsharing.response.Pagination;
import com.nocodenobug.billsharing.response.SamplePagingResponse;
import com.nocodenobug.billsharing.response.SampleResponse;
import com.nocodenobug.billsharing.service.category.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
   description = "Category controller",
        name = "Các api về thể loại (dành cho admin)"
)
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

    @Operation(summary = "Lấy tất cả category", description =
    "page: trang hiện tại (bắt đầu từ 0), page_size: số record trong trang hiện tại ")
    @GetMapping("")
    public ResponseEntity<?> getCategories(@RequestParam(value = "page") int page,
                                           @RequestParam(value = "page_size") int pageSize) {
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

    @Operation(summary = "Lấy thể loại theo Id", description = "Lấy thể loại theo Id")
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

    @Operation(summary = "Tạo thể loại", description = "Tạo thể loại")
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

    @Operation(summary = "Update thể loại", description = "Update thể loại")
    @PutMapping("/{id}")
    public ResponseEntity<SampleResponse> update(@PathVariable Long id, @Validated @RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(
                SampleResponse.builder()
                        .success(true)
                        .message("Success")
                        .data(updateCategoryService.updateCategory(id, categoryDto))
                        .build()
        );
    }

    @Operation(summary = "Xóa thể loại", description = "Xóa thể loại")
    @DeleteMapping("/{id}")
    public ResponseEntity<SampleResponse> delete(@PathVariable Long id) {
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
