package com.example.datn.controller;

import com.example.datn.models.Category;
import com.example.datn.models.SearchForm;
import com.example.datn.payload.request.CategoryRequest;

import com.example.datn.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/admin/category")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@PostMapping
	public ResponseEntity<Void> create(@RequestBody CategoryRequest categoryRequest){
		categoryService.create(categoryRequest);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping
	public  ResponseEntity<Void> update(@RequestBody CategoryRequest categoryRequest){
		categoryService.update(categoryRequest);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<CategoryRequest> getCategoryById(@PathVariable int id){
		Category category = categoryService.getCategoryById(id);
		if (ObjectUtils.isEmpty(category)){
			return ResponseEntity.notFound().build();
		}
		CategoryRequest categoryRequest = CategoryRequest.toCategoryRequest(category);
		return new ResponseEntity<>(categoryRequest, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<CategoryRequest>> getAllCategory(){
		List<Category> categories = categoryService.getAllCategory();
		List<CategoryRequest> categoryRequests = CategoryRequest.toCategoryRequestList(categories);
		return new ResponseEntity<>(categoryRequests, HttpStatus.OK);
	}

	@PostMapping(value = "/search")
	public ResponseEntity<List<CategoryRequest>> searchCategory(@RequestBody SearchForm searchForm){
		List<Category> categories = categoryService.search(searchForm);
		List<CategoryRequest> categoryRequests = CategoryRequest.toCategoryRequestList(categories);

		return new ResponseEntity<>(categoryRequests, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteCategoryById(@PathVariable int id){
		categoryService.delete(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
