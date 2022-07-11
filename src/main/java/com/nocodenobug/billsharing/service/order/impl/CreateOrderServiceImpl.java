package com.nocodenobug.billsharing.service.order.impl;

import com.nocodenobug.billsharing.model.dto.OrderDto;
import com.nocodenobug.billsharing.model.entity.Customer;
import com.nocodenobug.billsharing.model.entity.Order;
import com.nocodenobug.billsharing.constants.OrderStatus;
import com.nocodenobug.billsharing.model.entity.OrderItem;
import com.nocodenobug.billsharing.model.entity.Vendor;
import com.nocodenobug.billsharing.repository.OrderItemRepository;
import com.nocodenobug.billsharing.repository.OrderRepository;
import com.nocodenobug.billsharing.service.order.CreateOrderService;
import com.nocodenobug.billsharing.service.FindByIdService;
import com.nocodenobug.billsharing.service.order_item.GetOrderItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CreateOrderServiceImpl implements CreateOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private FindByIdService findByIdService;

    @Autowired
    private GetOrderItemService getOrderItemService;

    @Autowired
    private ModelMapper modelMapper;

//    @Override
//    public Order create(JsonNode orderData){
//        ObjectMapper mapper = new ObjectMapper();
//
//        Order order = mapper.convertValue(orderData,Order.class);
//        orderReponsitory.save(order);
//
//        TypeReference<List<OrderItem>> typeReference = new TypeReference<List<OrderItem>>() {};
//
//        List<OrderItem> orderItems = mapper.convertValue(orderData.get("orderItems"),typeReference)
//                .stream().peek(orderItem -> orderItem.setOrder(order)).collect(Collectors.toList());
//        orderItemReponsitory.saveAll(orderItems);
//       return order;
//    }

    @Override
    public OrderDto createOrder(OrderDto orderDto){
        Order order = modelMapper.map(orderDto,Order.class);


        List<OrderItem> orderItems = orderItemRepository.findAll();

        order.setId(orderDto.getId());
        order.setStatus(OrderStatus.ACTIVE.getStatus());
        order.setTotal(BigDecimal.valueOf(0));
//        double total = Double.parseDouble(order.getTotal()+"");
        order.setGrandTotal(BigDecimal.valueOf(0));

        Vendor findById = findByIdService.checkIdVendor(orderDto.getVendorId());
        Customer findByIdCustomer = findByIdService.checkIdCustomer(orderDto.getCustomerId());

        System.out.println(order.getId() + "idddđ");
        System.out.println(order.getTotal() + "llll");
        return modelMapper.map(orderRepository.save(order),OrderDto.class);
    }




}
