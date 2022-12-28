package com.example.shop.payload.request;

import com.example.shop.models.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerRequest {
    private String fullName;
    private String address;
    private String phoneNumber;

    public CustomerRequest() {
    }

    public CustomerRequest(String fullName, String address, String phoneNumber) {
        this.fullName = fullName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static List<CustomerRequest> toCustomerRequestList(List<Customer> customerList){
        List<CustomerRequest> customerRequestList = new ArrayList<>();
        for (Customer customer : customerList) {
            customerRequestList.add(customerToCustomerRequest(customer));
        }
        return customerRequestList;
    }

    public static CustomerRequest customerToCustomerRequest(Customer customer){
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setFullName(customer.getFullName());
        customerRequest.setAddress(customer.getAddress());
        customerRequest.setPhoneNumber(customer.getPhoneNumber());

        return customerRequest;
    }
}
