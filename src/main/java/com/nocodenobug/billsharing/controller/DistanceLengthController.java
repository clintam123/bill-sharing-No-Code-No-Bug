package com.nocodenobug.billsharing.controller;

import com.nocodenobug.billsharing.model.dto.VendorDto;
import com.nocodenobug.billsharing.payload.response.DistanceResponse;
import com.nocodenobug.billsharing.service.vendor.GetVendorByIdService;
import com.nocodenobug.billsharing.utils.DistanceLengthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class DistanceLengthController {
    @Autowired
    private GetVendorByIdService getVendorByIdService;
    @RequestMapping("/getDistanceLenght")
    public DistanceResponse getLength(@RequestParam(name = "origin") String origin,
                                      @RequestParam(name = "vendorId") Long id
                            ) throws IOException {
        VendorDto vendor=getVendorByIdService.getVendorById(id);
        return DistanceLengthUtils.getDistanceLength(origin, vendor.getAddress());
    }
}
