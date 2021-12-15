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
      
        //  if (userService.isRole()) {
        //     return "dashboard/products";
        //  }

        // model.addAttribute("username",customerService.getCustomer().getUser().getUsername());
        List<MessageNotifications> ntn = messageNotificationsRepository.findAll(Sort.by("createdDate").descending());

        model.addAttribute("noti", ntn);
         return "dashboard/products"; 
    }
    

    @GetMapping("admin/category")
    public String listCategory(Model model){
      
        //  if (userService.isRole()) {
        //     return "dashboard/products";
        //  }
        List<MessageNotifications> ntn = messageNotificationsRepository.findAll(Sort.by("createdDate").descending());

        model.addAttribute("noti", ntn);
         return "dashboard/categories"; 
    }


    @GetMapping("admin/customer")
    public String listCustomer(Model model){
      
        //  if (userService.isRole()) {
        //     return "dashboard/products";
        //  }
        List<MessageNotifications> ntn = messageNotificationsRepository.findAll(Sort.by("createdDate").descending());

        model.addAttribute("noti", ntn);
         return "dashboard/khachhang"; 
    }


    @GetMapping("admin/user")
    public String listUser(Model model){
      
        //  if (userService.isRole()) {
        //     return "dashboard/products";
        //  }

        List<MessageNotifications> ntn = messageNotificationsRepository.findAll(Sort.by("createdDate").descending());

        model.addAttribute("noti", ntn);
         return "dashboard/user"; 
    }


    @Autowired
    MessageNotificationsRepository messageNotificationsRepository;

    @GetMapping("admin/order")
    public String listOrder(Model model){
      
        //  if (userService.isRole()) {
        //     return "dashboard/products";
        //  }

        List<MessageNotifications> ntn = messageNotificationsRepository.findAll(Sort.by("createdDate").descending());

        model.addAttribute("noti", ntn);
         return "dashboard/order"; 
    }


    @GetMapping("admin/trademark")
    public String listTrademark(Model model){
      
        //  if (userService.isRole()) {
        //     return "dashboard/products";
        //  }

        List<MessageNotifications> ntn = messageNotificationsRepository.findAll(Sort.by("createdDate").descending());

        model.addAttribute("noti", ntn);
         return "dashboard/thuonghieu"; 
    }

    @GetMapping("admin/supplier")
    public String listSupplier(Model model){
      
        //  if (userService.isRole()) {
        //     return "dashboard/products";
        //  }

        List<MessageNotifications> ntn = messageNotificationsRepository.findAll(Sort.by("createdDate").descending());

        model.addAttribute("noti", ntn);
         return "dashboard/nhacungcap"; 
    }
}
