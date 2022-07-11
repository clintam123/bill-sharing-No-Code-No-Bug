package com.nocodenobug.billsharing.service;

import com.nocodenobug.billsharing.exceptions.NotFoundException;
import com.nocodenobug.billsharing.model.entity.*;
import com.nocodenobug.billsharing.reponsitory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindByIdServiceImpl implements FindByIdService {
    @Autowired
    private ProductReponsitory productReponsitory;

    @Autowired
    private OrderReponsitory orderReponsitory;

    @Autowired
    private OrderItemReponsitory orderItemReponsitory;

    @Autowired
    private CustomerReponsitory customerReponsitory;

    @Autowired
    private VenderReponsitory venderReponsitory;

    @Override
    public Order checkIdOrder(Long id){
        Optional<Order> optional = orderReponsitory.findById(id);
        System.out.println(optional.get()+"+++++++++++");
        if(optional.isPresent()){
            return optional.get();
        }
        throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "Id Order NotFound");
    }

    @Override
    public Product checkIdProduct(Long id){
        Optional<Product> optional = productReponsitory.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "Id Product NotFound");
    }

    @Override
    public OrderItem checkIdOrderItem(Long id){
        Optional<OrderItem> optional = orderItemReponsitory.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "Id Order Item NotFound");
    }

    @Override
    public Vendor checkIdVendor(Long id){
        Optional<Vendor> optional = venderReponsitory.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "Id vendor NotFound");
    }

    @Override
    public Customer checkIdCustomer(Long id){
        Optional<Customer> optional = customerReponsitory.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "Id customer NotFound");
    }

}
