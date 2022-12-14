package com.example.datn.payload.request;

import com.example.datn.models.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryRequest {
    private int id;
    private String name;
    private String seoUrl;
    private String icon;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeoUrl() {
        return seoUrl;
    }

    public void setSeoUrl(String seoUrl) {
        this.seoUrl = seoUrl;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public static CategoryRequest toCategoryRequest(Category category){
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName(category.getName());
        categoryRequest.setSeoUrl(category.getSeoUrl());
        categoryRequest.setIcon(category.getIcon());

        return categoryRequest;
    }

    public static List<CategoryRequest> toCategoryRequestList(List<Category> categories){
        List<CategoryRequest> categoryRequests = new ArrayList<>();
        for (Category category : categories){
            CategoryRequest categoryRequest = new CategoryRequest();
            categoryRequest.setName(category.getName());
            categoryRequest.setSeoUrl(category.getSeoUrl());
            categoryRequest.setIcon(category.getIcon());

            categoryRequests.add(categoryRequest);
        }
        return categoryRequests;
    }
}
