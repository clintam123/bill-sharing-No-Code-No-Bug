package com.nocodenobug.billsharing.controller;

import com.nocodenobug.billsharing.model.dto.ProductReviewDto;
import com.nocodenobug.billsharing.payload.response.DefaultPagingResponse;
import com.nocodenobug.billsharing.payload.response.DefaultResponse;
import com.nocodenobug.billsharing.service.product_review.CreateReviewService;
import com.nocodenobug.billsharing.service.product_review.DeleteReviewService;
import com.nocodenobug.billsharing.service.product_review.GetReviewService;
import com.nocodenobug.billsharing.service.product_review.UpdateReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(
        description = "Review resources that provides access to available product review data",
        name = "Review Resource")
@Slf4j
@RestController
@RequestMapping("/api/v1/product-review")
public class ProductReviewController {
    @Autowired
    private CreateReviewService createReviewService;

    @Autowired
    private DeleteReviewService deleteReviewService;

    @Autowired
    private GetReviewService getReviewService;

    @Autowired
    private UpdateReviewService updateReviewService;

    @Operation(summary = "Get review", description = "Get username and user's review by product id")
    @GetMapping("/get-review/{product_id}")
    public ResponseEntity<?> getReview(
            @PathVariable("product_id") int id,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "page_size") int pageSize
    ) {
        return ResponseEntity.ok(DefaultPagingResponse.success(getReviewService.getReviewsOfProduct(id, page, pageSize)));
    }

    @Operation(summary = "Create review", description = "Create new review for product")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "${api.response-codes.ok.desc}"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "${api.response-codes.badRequest.desc}",
                            content = {@Content(examples = {@ExampleObject(value ="")})}),
                    @ApiResponse(
                            responseCode = "404",
                            description = "${api.response-codes.notFound.desc}",
                            content = {@Content(examples = {@ExampleObject(value = "")})})
            })
    @PostMapping("/create-review/{product_id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> createReview(
            @PathVariable("product_id") int productId,
            @RequestBody ProductReviewDto productReviewDto
    ){

        return ResponseEntity.ok(DefaultResponse.success(createReviewService.createReview(productId, productReviewDto)));
    }

    @Operation(summary = "Update review", description = "Update review for product")
    @PutMapping("/update-review/{review_id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> updateReview(
            @PathVariable("review_id") int id,
            @RequestBody ProductReviewDto productReviewDto
    ){
        return ResponseEntity.ok(DefaultResponse.success(updateReviewService.updateReview(id, productReviewDto)));
    }

    @Operation(summary = "Delete review", description = "Delete product review")
    @DeleteMapping("/delete-review/{review_id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> deleteReview(
            @PathVariable("review_id") int id
    ){
        return ResponseEntity.ok(DefaultResponse.success(deleteReviewService.deleteReview(id)));
    }
}
