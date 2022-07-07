package com.nocodenobug.billsharing.controller;

import com.nocodenobug.billsharing.model.dto.ProductReviewDto;
import com.nocodenobug.billsharing.response.DefaultPagingResponse;
import com.nocodenobug.billsharing.response.DefaultResponse;
import com.nocodenobug.billsharing.service.CreateReviewService;
import com.nocodenobug.billsharing.service.DeleteReviewService;
import com.nocodenobug.billsharing.service.GetReviewService;
import com.nocodenobug.billsharing.service.UpdateReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1.0/product-review")
public class ProductReviewController {
    @Autowired
    private CreateReviewService createReviewService;

    @Autowired
    private DeleteReviewService deleteReviewService;

    @Autowired
    private GetReviewService getReviewService;

    @Autowired
    private UpdateReviewService updateReviewService;

    @GetMapping("/get-review/{product_id}")
    public ResponseEntity<?> getReview(
            @PathVariable("product_id") int id,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "page_size") int pageSize
    ) {
        return ResponseEntity.ok(DefaultPagingResponse.success(getReviewService.getReviewsOfProduct(id, page, pageSize)));
    }

    @PostMapping("/create-review/{product_id}")
    public ResponseEntity<?> createReview(
            @PathVariable("product_id") int productId,
            @RequestBody ProductReviewDto productReviewDto
    ){

        return ResponseEntity.ok(DefaultResponse.success(createReviewService.createReview(productId, productReviewDto)));
    }

    @PutMapping("/update-review/{review_id}")
    public ResponseEntity<?> updateReview(
            @PathVariable("review_id") int id,
            @RequestBody ProductReviewDto productReviewDto
    ){
        return ResponseEntity.ok(DefaultResponse.success(updateReviewService.updateReview(id, productReviewDto)));
    }

    @DeleteMapping("/delete-review/{review_id}")
    public ResponseEntity<?> deleteReview(
            @PathVariable("review_id") int id
    ){
        return ResponseEntity.ok(DefaultResponse.success(deleteReviewService.deleteReview(id)));
    }
}
