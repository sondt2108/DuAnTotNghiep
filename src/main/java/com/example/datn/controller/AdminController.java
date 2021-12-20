package com.example.datn.controller;

import java.util.List;

import com.example.datn.models.MessageNotifications;
import com.example.datn.repository.MessageNotificationsRepository;
import com.example.datn.service.CustomerService;
import com.example.datn.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {



    @Autowired
    UserService userService;


    @Autowired
    CustomerService customerService;


    @GetMapping("admin/product")
    public String listProduct(Model model){
      
     if (customerService.isCustomerLogin()) {

          if (userService.isRole()) {
               model.addAttribute("username",customerService.getCustomer().getUser().getUsername());
               model.addAttribute("customer_id", customerService.getCustomer().getUser().getId());
               List<MessageNotifications> ntn = messageNotificationsRepository.findAll(Sort.by("createdDate").descending());
                model.addAttribute("noti", ntn);
               
              return "dashboard/products";
           }   
           
           return "404page";

          }

        
     
         return "404page"; 
    }
    

    @GetMapping("admin/category")
    public String listCategory(Model model){
      
     if (customerService.isCustomerLogin()) {

          if (userService.isRole()) {
               model.addAttribute("username",customerService.getCustomer().getUser().getUsername());
               model.addAttribute("customer_id", customerService.getCustomer().getUser().getId());
               List<MessageNotifications> ntn = messageNotificationsRepository.findAll(Sort.by("createdDate").descending());
                model.addAttribute("noti", ntn);
              return "dashboard/categories";
           }   
           
           return "404page";

          }
        
        
         return "404page"; 
    }


    @GetMapping("admin/customer")
    public String listCustomer(Model model){
     if (customerService.isCustomerLogin()) {

          if (userService.isRole()) {
               model.addAttribute("username",customerService.getCustomer().getUser().getUsername());
               model.addAttribute("customer_id", customerService.getCustomer().getUser().getId());
               List<MessageNotifications> ntn = messageNotificationsRepository.findAll(Sort.by("createdDate").descending());
                model.addAttribute("noti", ntn);
              return "dashboard/khachhang";
           }   
           
           return "404page";

          }
        
        
         return "404page"; 
    }


    @GetMapping("admin/user")
    public String listUser(Model model){
      
     if (customerService.isCustomerLogin()) {

          if (userService.isRole()) {
               model.addAttribute("username",customerService.getCustomer().getUser().getUsername());
               model.addAttribute("customer_id", customerService.getCustomer().getUser().getId());
               List<MessageNotifications> ntn = messageNotificationsRepository.findAll(Sort.by("createdDate").descending());
                model.addAttribute("noti", ntn);
                System.out.println("sonneeeeee");
              return "dashboard/user";
           }   
           System.out.println("sonneeeeee12333");
           return "404page";

          }

        
        
         return "404page"; 
    }


    @Autowired
    MessageNotificationsRepository messageNotificationsRepository;

    @GetMapping("admin/order")
    public String listOrder(Model model){
     if (customerService.isCustomerLogin()) {
          if (userService.isRole()) {
               System.out.println("sonne");
               model.addAttribute("username",customerService.getCustomer().getUser().getUsername());
               model.addAttribute("customer_id", customerService.getCustomer().getUser().getId());
               List<MessageNotifications> ntn = messageNotificationsRepository.findAll(Sort.by("createdDate").descending());
                model.addAttribute("noti", ntn);
              return "dashboard/order";
           }   
           
           return "404page";

          }

        
        
         return "404page"; 
    }


    @GetMapping("admin/trademark")
    public String listTrademark(Model model){
      
     if (customerService.isCustomerLogin()) {

          if (userService.isRole()) {
               model.addAttribute("username",customerService.getCustomer().getUser().getUsername());
               model.addAttribute("customer_id", customerService.getCustomer().getUser().getId());
               List<MessageNotifications> ntn = messageNotificationsRepository.findAll(Sort.by("createdDate").descending());
                model.addAttribute("noti", ntn);
              return "dashboard/thuonghieu";
           }   
           
           return "404page";

          }

        
        
         return "404page"; 
    }

    @GetMapping("admin/supplier")
    public String listSupplier(Model model){
      
     if (customerService.isCustomerLogin()) {

          if (userService.isRole()) {
               model.addAttribute("username",customerService.getCustomer().getUser().getUsername());
               model.addAttribute("customer_id", customerService.getCustomer().getUser().getId());
               List<MessageNotifications> ntn = messageNotificationsRepository.findAll(Sort.by("createdDate").descending());
                model.addAttribute("noti", ntn);
              return "dashboard/nhacungcap";
           }   
           
           return "404page";

          }
        
        
         return "404page"; 
    }

    @GetMapping("admin/warehouse-receipt")
    public String listWAREHOUSEEXPORT(Model model){
      
     if (customerService.isCustomerLogin()) {

          if (userService.isRole()) {
               model.addAttribute("username",customerService.getCustomer().getUser().getUsername());
               model.addAttribute("customer_id", customerService.getCustomer().getUser().getId());
               List<MessageNotifications> ntn = messageNotificationsRepository.findAll(Sort.by("createdDate").descending());
                model.addAttribute("noti", ntn);
              return "dashboard/warehousereceipt";
           }   
           
           return "404page";

          }

        
         return "404page"; 
    }
}
