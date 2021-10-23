package com.example.datn.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.datn.models.ImgProduct;
import com.example.datn.models.Product;
import com.example.datn.repository.ProductImgRepository;
import com.example.datn.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class ProductRescontroller {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductImgRepository productImgRepository;

    @GetMapping("api/productbytrademark")
    public ResponseEntity<Map<String, Object>> getAllTutorials(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "6") int size
      ) {

    try {
      List<Product> products = new ArrayList<Product>();
      Pageable paging = PageRequest.of(page, size);
      
      Page<Product> pageTuts;
     
        pageTuts = productRepository.findByTitleContaining(paging);
      
        products = pageTuts.getContent();

      Map<String, Object> response = new HashMap<>();
      response.put("pr", products);
      response.put("currentPage", pageTuts.getNumber());
      response.put("totalItems", pageTuts.getTotalElements());
      response.put("totalPages", pageTuts.getTotalPages());

      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("api/productdetails/{id}")
  public ResponseEntity<Product> getTutorialById(@PathVariable("id") int id) {
		Optional<Product> tutorialData = productRepository.findById(id);

		if (tutorialData.isPresent()) {
			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

  


  @GetMapping("api/productimg/{id}")
  public List<ImgProduct> all(@PathVariable("id") Long id){

    return productImgRepository.findByProductId(id);
  }
  
  

}
