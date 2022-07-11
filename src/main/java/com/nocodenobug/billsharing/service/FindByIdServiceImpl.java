package com.nocodenobug.billsharing.service;

import com.nocodenobug.billsharing.exceptions.NotFoundException;
import com.nocodenobug.billsharing.model.entity.*;
import com.nocodenobug.billsharing.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindByIdServiceImpl implements FindByIdService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private VendorRepository vendorRepository;

    @Override
    public Order checkIdOrder(Long id){
        Optional<Order> optional = orderRepository.findById(id);
        System.out.println(optional.get()+"+++++++++++");
        if(optional.isPresent()){
            return optional.get();
        }
        throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "Id Order NotFound");
    }

    @Override
    public Product checkIdProduct(Long id){
        Optional<Product> optional = productRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "Id Product NotFound");
    }

    @Override
    public OrderItem checkIdOrderItem(Long id){
        Optional<OrderItem> optional = orderItemRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "Id Order Item NotFound");
    }

    @Override
    public Vendor checkIdVendor(Long id){
        Optional<Vendor> optional = vendorRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "Id vendor NotFound");
    }

    @Override
    public Customer checkIdCustomer(Long id){
        Optional<Customer> optional = customerRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "Id customer NotFound");
    }

}
