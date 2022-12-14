package com.example.datn.controller;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import com.example.datn.models.MessageNotifications;
import com.example.datn.models.Order;
import com.example.datn.models.OrderDetail;
import com.example.datn.models.Product;
import com.example.datn.models.OrderStatus;
import com.example.datn.payload.request.OrderRequest;
import com.example.datn.payload.request.SendMailRequest;
import com.example.datn.payload.response.MessageResponse;
import com.example.datn.repository.MessageNotificationsRepository;
import com.example.datn.repository.OrderDetailRepository;
import com.example.datn.repository.OrderRepository;
import com.example.datn.repository.ProductRepository;
import com.example.datn.service.CartService;
import com.example.datn.service.CustomerService;
import com.example.datn.service.impl.MailServiceImpl;
import com.sendgrid.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class OderRescontroller {

    @Autowired
    CustomerService customerService;

    @Autowired
    CartService cartService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    MailServiceImpl mailService;

   @Autowired
   MessageNotificationsRepository messageNotificationsRepository;
    
   public final static OrderStatus DEFAULT_TTDH = new OrderStatus();
    static {
		DEFAULT_TTDH.setOrderStatusId(1);
      
    }


    @Autowired
    ProductRepository productRepository;


    @PostMapping("/checkout")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> checkoutByUser(@Valid @RequestBody OrderRequest orderRequest){

        long leftLimit = 1L;
        long rightLimit = 100000L;
        long id = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
        //order
        Order order = new Order();
        order.setOrderId(id);
        order.setProvince(orderRequest.getProvince());
        order.setDistrict(orderRequest.getDistrict());
        order.setWard(orderRequest.getWard()); 
        order.setAddress(orderRequest.getAddress());
        order.setNote(orderRequest.getNote());
        order.setPhoneNumber(orderRequest.getPhoneNumber());
        //order.setTotal(cartService.getTotal());
        order.setCustomer(customerService.getCustomer());
        //order.setOrderStatus(DEFAULT_TTDH);
        order.setFullName(customerService.getCustomer().getFullName());
        orderRepository.save(order);
        //oder detail

        // luu orderItems
		Map<Product, Integer> listItems = cartService.getCart().getCartDetails();

		for (Product product : listItems.keySet()) {

            

			OrderDetail orderItem = new OrderDetail();
			int quantity = listItems.get(product);
            
			orderItem.setProduct(product);
			orderItem.setOrder(order);
			orderItem.setQuantity(quantity);
			orderItem.setProductName(product.getProductName());
			orderItem.setPrice(product.getPrice());
			//orderItem.setTotal((product.getGia()) * quantity);

            
        Optional<Product> productOptional = Optional
        .ofNullable(productRepository.findByProductId(product.getProductId()));
Product pr = productOptional.get();

 pr.setQuantity((int)(pr.getQuantity()) - listItems.get(product));
 productRepository.save(pr);

 
            orderDetailRepository.save(orderItem);
			
		}

        SendMailRequest emailrequest = new SendMailRequest();
        
        emailrequest.setOrderId(id);
        emailrequest.setToEmail(orderRequest.getEmail());
        emailrequest.setTieuDe("Cảm ơn bạn đã tin tưởng và đặt hàng tại Đức Phát");
        
        
        mailService.sendEmail(emailrequest);
        mailService.sendMailOrderWithAdmin(id);

        MessageNotifications ntn = new MessageNotifications();
        ntn.setOrderId(id);
        ntn.setDescription("vừa đặt hàng thành công");
        messageNotificationsRepository.save(ntn);

        //mailService.sendMailWithOrderId(order.getOrderId());

        cartService.getCart().getCartDetails().clear();

        return ResponseEntity.ok(new MessageResponse("check out success!"));
    }


    

    @PostMapping("/checkoutByUser")
    public ResponseEntity<?> checkOut(@Valid @RequestBody OrderRequest orderRequest){

        long leftLimit = 1L;
        long rightLimit = 100000L;
        long id = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));

        
        //order
        
        Order order = new Order();
        order.setOrderId(id);
        order.setProvince(orderRequest.getProvince());
        order.setDistrict(orderRequest.getDistrict());
        order.setWard(orderRequest.getWard()); 
        order.setAddress(orderRequest.getAddress());
        order.setNote(orderRequest.getNote());
        order.setPhoneNumber(orderRequest.getPhoneNumber());
        order.setEmail(orderRequest.getEmail());
        order.setFullName(orderRequest.getName());
        //order.setTotal(cartService.getTotal());
        //order.setOrderStatus(DEFAULT_TTDH);
        
        orderRepository.save(order);
        //oder detail

        // luu orderItems
		Map<Product, Integer> listItems = cartService.getCart().getCartDetails();

		for (Product product : listItems.keySet()) {

            

			OrderDetail orderItem = new OrderDetail();
			int quantity = listItems.get(product);
            
			orderItem.setProduct(product);
			orderItem.setOrder(order);
			orderItem.setQuantity(quantity);
			orderItem.setProductName(product.getProductName());
			orderItem.setPrice(product.getPrice());
			//orderItem.setTotal((product.getGia()) * quantity);

            orderDetailRepository.save(orderItem);
			
		}


        MessageNotifications ntn = new MessageNotifications();
        ntn.setOrderId(order.getOrderId());
        ntn.setDescription("vừa đặt hàng thành công");
        messageNotificationsRepository.save(ntn);

        //mailService.sendMailWithOrderId(order.getOrderId());

        SendMailRequest emailrequest = new SendMailRequest();
        
        emailrequest.setOrderId(id);
        emailrequest.setToEmail(orderRequest.getEmail());
        emailrequest.setTieuDe("Cảm ơn bạn đã tin tưởng và đặt hàng tại Đức Phát");
        
        
        mailService.sendEmail(emailrequest);
        
        mailService.sendMailOrderWithAdmin(id);

        cartService.getCart().getCartDetails().clear();

        return ResponseEntity.ok(new MessageResponse("check out success!"));
    }
}
