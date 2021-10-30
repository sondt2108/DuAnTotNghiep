package com.example.datn.controller;

import java.util.List;

import com.example.datn.models.NhaCungCap;
import com.example.datn.models.Product;
import com.example.datn.models.SearchForm;
import com.example.datn.models.ThuongHieu;
import com.example.datn.repository.ProductRepository;
import com.example.datn.repository.SupplierRepository;
import com.example.datn.repository.TrademarkRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminRescontroller {


    @Autowired
    ProductRepository productRepository;


    @Autowired
    TrademarkRepository trademarkRepository;

    @Autowired
    SupplierRepository supplierRepository;
    

    private static final int TOI_DA_SAN_PHAM = 2;
	@PostMapping("api/product/search")
    public Page<Product> search(
            // thông tin form tìm kiếm
            @RequestBody SearchForm sf) {

        Pageable phanTrang = PageRequest.of(sf.getTrang(), TOI_DA_SAN_PHAM,
                // nếu đúng thì thứ tự tăng đần ngược lại giảm dần
                sf.getThuTu() ? Direction.ASC : Direction.DESC,
                // xếp theo trường nào ví dụ id, name, price
                sf.getXepTheo());

        // lấy sản phẩm
        Page<Product> productPage = productRepository.findByTensanphamContainingIgnoreCase(sf.getTen(), phanTrang);

        return productPage;
    }

    @GetMapping("/api/products/{id}")
	public Product getById(@PathVariable("id") int id) {
		return productRepository.findById(id).orElse(null);
	}


    @GetMapping("/api/thuonghieu")
	public List<ThuongHieu> thuongHieu() {
		return trademarkRepository.findAll();
	}


    @GetMapping("/api/nhacungcap")
	public List<NhaCungCap> list() {
		return supplierRepository.findAll();
	}
}
