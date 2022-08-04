package com.nocodenobug.billsharing.payload.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DistanceResponse {
    private String length;
    private String time;
    private String summary;
}
