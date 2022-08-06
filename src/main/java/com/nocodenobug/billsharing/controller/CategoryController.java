package com.nocodenobug.billsharing.controller;

import com.nocodenobug.billsharing.model.dto.CategoryDto;
import com.nocodenobug.billsharing.payload.response.*;
import com.nocodenobug.billsharing.service.category.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    private final UploadCategoryImage uploadCategoryImage;

    public CategoryController(CreateCategoryService createCategoryService, DeleteCategoryService deleteCategoryService, GetAllCategoriesService getAllCategoriesService, GetCategoryByIdService getCategoryByIdService, UpdateCategoryService updateCategoryService, UploadCategoryImage uploadCategoryImage) {
        this.createCategoryService = createCategoryService;
        this.deleteCategoryService = deleteCategoryService;
        this.getAllCategoriesService = getAllCategoriesService;
        this.getCategoryByIdService = getCategoryByIdService;
        this.updateCategoryService = updateCategoryService;
        this.uploadCategoryImage = uploadCategoryImage;
    }

    @Operation(summary = "Lấy tất cả category", description =
    "page: trang hiện tại (bắt đầu từ 0), page_size: số record trong trang hiện tại ")
    @GetMapping("")
    public ResponseEntity<?> getCategories(@RequestParam(value = "page") int page,
                                           @RequestParam(value = "page_size") int pageSize) {
        Page<CategoryDto> categoryDtoPage = getAllCategoriesService.getAllCategories(page, pageSize);
        return ResponseEntity.ok(DefaultPagingResponse.success(categoryDtoPage));
    }

    @Operation(summary = "Lấy thể loại theo Id", description = "Lấy thể loại theo Id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(DefaultResponse.success(getCategoryByIdService.getCategoryById(id)));
    }

    @Operation(summary = "Tạo thể loại", description = "Tạo thể loại")
    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@Validated @RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(DefaultResponse.success(createCategoryService.createCategory(categoryDto)));
    }

    @Operation(summary = "Update thể loại", description = "Update thể loại")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@PathVariable Long id, @Validated @RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(DefaultResponse.success(updateCategoryService.updateCategory(id, categoryDto)));
    }

    @Operation(summary = "Xóa thể loại", description = "Xóa thể loại")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        deleteCategoryService.deleteCategory(id);
        return ResponseEntity.ok(DefaultResponse.success("Delete category success"));
    }

    @Operation(summary = "Update Image", description = "Update Image")
    @PostMapping("/update-image/{category-id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateImage(@PathVariable("category-id") Long categoryId, @RequestBody MultipartFile file) {
        return ResponseEntity.ok(DefaultResponse.success("Success", uploadCategoryImage.uploadCategoryImage(categoryId, file)));
    }
}
