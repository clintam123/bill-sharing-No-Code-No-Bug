package com.nocodenobug.billsharing.payload.request;

import com.nocodenobug.billsharing.constants.ESort;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilterRequest {
    private String productName;
    private String categoryTitle;
    private String vendorName;
    private int sort = ESort.RANDOM.getValue();
}
