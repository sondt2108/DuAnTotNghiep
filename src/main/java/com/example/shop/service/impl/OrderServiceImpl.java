package com.example.shop.service.impl;

import com.example.shop.exception.ResourceNotFoundException;
import com.example.shop.models.*;
import com.example.shop.payload.request.OrderRequest;
import com.example.shop.payload.request.SendMailRequest;
import com.example.shop.repository.MessageNotificationsRepository;
import com.example.shop.repository.OrderDetailRepository;
import com.example.shop.repository.OrderRepository;
import com.example.shop.service.CartService;
import com.example.shop.service.CustomerService;
import com.example.shop.service.OrderService;
import com.example.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    CustomerService customerService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CartService cartService;

    @Autowired
    ProductService productService;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    MailServiceImpl mailService;

    @Autowired
    MessageNotificationsRepository messageNotificationsRepository;

    @Override
    public void createOrder(OrderRequest orderRequest) {
        //Create Order
        long leftLimit = 1L;
        long rightLimit = 100000L;
        long id = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));

        Order order = new Order();
        order.setOrderId(id);
        order.setProvince(orderRequest.getProvince());
        order.setDistrict(orderRequest.getDistrict());
        order.setWard(orderRequest.getWard());
        order.setAddress(orderRequest.getAddress());
        order.setNote(orderRequest.getNote());
        order.setPhoneNumber(orderRequest.getPhoneNumber());
        order.setTotal(orderRequest.getTotal());
        order.setCustomer(customerService.getCustomer());
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderStatusId(1);
        order.setOrderStatus(orderStatus);
        order.setFullName(customerService.getCustomer().getFullName());

        orderRepository.save(order);

        //Create order detail
        Map<Product, Integer> listItems = cartService.getCart().getCartDetails();

        listItems.keySet().forEach(product -> {
            OrderDetail orderItem = new OrderDetail();
            int quantity = listItems.get(product);
            orderItem.setProduct(product);
            orderItem.setOrder(order);
            orderItem.setQuantity(quantity);
            orderItem.setProductName(product.getProductName());
            orderItem.setPrice(product.getPrice());
            orderItem.setTotal((product.getPrice()) * quantity);

            orderDetailRepository.save(orderItem);

            //Set new quantity
            Product productTmp = productService.getProductById(product.getProductId());
            if (ObjectUtils.isEmpty(productTmp)){
                throw new ResourceNotFoundException("Product not found");
            }
            productTmp.setQuantity(productTmp.getQuantity() - listItems.get(product));
            productService.setProduct(productTmp);
        });

        SendMailRequest emailRequest = new SendMailRequest();

        emailRequest.setOrderId(id);
        emailRequest.setToEmail(orderRequest.getEmail());
        emailRequest.setTieuDe("Thank you for trusting and ordering at Duc Phat");


        mailService.sendEmail(emailRequest);
        mailService.sendMailOrderWithAdmin(id);

        MessageNotifications ntn = new MessageNotifications();
        ntn.setOrderId(id);
        ntn.setDescription("Check out success!");
        messageNotificationsRepository.save(ntn);

        //mailService.sendMailWithOrderId(order.getOrderId());

        cartService.getCart().getCartDetails().clear();
    }
}
