package com.nocodenobug.billsharing.service;

import com.nocodenobug.billsharing.exception.ExceptionObject;
import com.nocodenobug.billsharing.exceptions.NotFoundException;
import com.nocodenobug.billsharing.model.entity.*;
import com.nocodenobug.billsharing.reponsitory.*;
import com.nocodenobug.billsharing.service.FindByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
        throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "Id Order NotFound",null );
    }

    @Override
    public Product checkIdProduct(Long id){
        Optional<Product> optional = productReponsitory.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "Id Product NotFound",null );
    }

    @Override
    public OrderItem checkIdOrderItem(Long id){
        Optional<OrderItem> optional = orderItemReponsitory.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "Id Order Item NotFound",null );
    }

    @Override
    public Vendor checkIdVendor(Long id){
        Optional<Vendor> optional = venderReponsitory.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "Id vendor NotFound",null );
    }

    @Override
    public Customer checkIdCustomer(Long id){
        Optional<Customer> optional = customerReponsitory.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "Id customer NotFound",null );
    }

}
