package com.nocodenobug.billsharing.service.order_item.impl;

import com.nocodenobug.billsharing.contants.OrderStatus;
import com.nocodenobug.billsharing.exception.ExceptionObject;
import com.nocodenobug.billsharing.exceptions.NotFoundException;
import com.nocodenobug.billsharing.model.dto.OrderItemDto;
import com.nocodenobug.billsharing.model.entity.Order;
import com.nocodenobug.billsharing.model.entity.OrderItem;
import com.nocodenobug.billsharing.model.entity.Product;
import com.nocodenobug.billsharing.reponsitory.OrderItemReponsitory;
import com.nocodenobug.billsharing.reponsitory.ProductReponsotory;
import com.nocodenobug.billsharing.service.order.UpdateOrderService;
import com.nocodenobug.billsharing.service.order_item.CreateOrderItemService;
import com.nocodenobug.billsharing.service.FindByIdService;
import com.nocodenobug.billsharing.service.order_item.UpdateOrderItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CreateOrderItemServiceImpl implements CreateOrderItemService {

    @Autowired
    private OrderItemReponsitory orderItemReponsitory;

    @Autowired
    private UpdateOrderService updateOrderService;

    @Autowired
    private FindByIdService findByIdService;

    @Autowired
    private ProductReponsotory productReponsotory;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OrderItemDto createOrderItem(OrderItemDto orderItemDto) {
        OrderItem orderItem = modelMapper.map(orderItemDto, OrderItem.class);

        Order orderOld = findByIdService.checkIdOrder(orderItemDto.getOrder().getId());
        if (orderOld.getStatus() == OrderStatus.INACTIVE.getStatus()) {
            throw ExceptionObject.builder().message("Order id không tồn tại").build();
        }

        Product productOld = findByIdService.checkIdProduct(orderItemDto.getProductId());

        if (productOld != null) {
                // tính tổng tiền
                int quantity = orderItemDto.getQuantity();
                double price = Double.parseDouble(productOld.getPrice() + "");
                BigDecimal total = BigDecimal.valueOf(price * quantity);
                orderItem.setTotal(total);

                // lưu thông tin OrderItem
                OrderItem orderItem1 = orderItemReponsitory.save(orderItem);

                // cập nhật lại số lượng trong product
                int qtyProduct = productOld.getQuantity();
                int qtyOrderItem = orderItem1.getQuantity();
                int updateQuantity = qtyProduct - qtyOrderItem;
                updateQuantityProduct(productOld.getId(), updateQuantity);

                // thiết lập order
                orderItem1.setOrder(orderOld);

                // thiêp lập tổng tiền order
                updateOrderService.updateTotal(orderItemDto.getOrder().getId());

                System.out.println(productOld.getQuantity() + "fghjklkjhgfdfghjkl");

                return modelMapper.map(orderItem1, OrderItemDto.class);
        } else {
            throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "Sản phẩm không đủ số lượng",null );
        }
    }

    public Product updateQuantityProduct(Long id, Integer quantity) {
        Product product = findByIdService.checkIdProduct(id);
        product.setQuantity(quantity);
        return productReponsotory.save(product);
    }


}
