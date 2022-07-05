package com.nocodenobug.billsharing.controller;

import com.nocodenobug.billsharing.model.dto.ProductReviewDto;
import com.nocodenobug.billsharing.response.SampleResponse;
import com.nocodenobug.billsharing.service.CreateReviewService;
import com.nocodenobug.billsharing.service.DeleteReviewService;
import com.nocodenobug.billsharing.service.GetReviewService;
import com.nocodenobug.billsharing.service.UpdateReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        if (getReviewService.getReviewsOfProduct(id, page, pageSize) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(SampleResponse.builder()
                .success(true)
                .message("Get review success")
                .data(getReviewService.getReviewsOfProduct(id, page, pageSize))
                .build());
    }

    @PostMapping("/create-review")
    public ResponseEntity<?> createReview(
            @RequestBody ProductReviewDto productReviewDto
    ){
        return ResponseEntity.ok(SampleResponse.builder()
                .success(true)
                .message("Create review success")
                .data(createReviewService.createReview(productReviewDto))
                .build());
    }

    @PutMapping("/update-review/{review_id}")
    public ResponseEntity<?> updateReview(
            @PathVariable("review_id") int id,
            @RequestBody ProductReviewDto productReviewDto
    ){
        if (updateReviewService.updateReview(id, productReviewDto) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(SampleResponse.builder()
                .success(true)
                .message("Update review success")
                .data(updateReviewService.updateReview(id, productReviewDto))
                .build());
    }

    @DeleteMapping("/delete-review/{review_id}")
    public ResponseEntity<?> deleteReview(
            @PathVariable("review_id") int id
    ){
        if(deleteReviewService.deleteReview(id)){
            return ResponseEntity.ok(SampleResponse.builder()
                    .success(true)
                    .message("Delete success")
                    .build());
        }
        return ResponseEntity.ok(SampleResponse.builder()
                .success(false)
                .message("Delete failed")
                .build());
    }
}
