package com.example.datn.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.datn.models.Category;
import com.example.datn.models.Product;
import com.example.datn.models.ThuongHieu;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.datn.repository.CategoryRepository;
import com.example.datn.repository.ProductRepository;
import com.example.datn.repository.TrademarkRepository;
import com.example.datn.service.CartService;
import com.example.datn.service.CustomerService;
import com.example.datn.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class HomeController {
    

    @Autowired
	CartService cartService;

	@Autowired
	TrademarkRepository trademarkRepository;

	@Autowired
	UserService userService;

    @GetMapping("/detail/{productname}")
    public String demo(@PathVariable String productname, Model model, HttpServletRequest request, HttpSession session){
         session = request.getSession();

		 Map<Product, Integer> listItems = cartService.getGioHang().getChiTietGioHang();


	for (Product product : listItems.keySet()) {

            

			
		int quantity = listItems.get(product);
		
		model.addAttribute("quantity", quantity);
	}


		 List<Category> categories = categoryRepository.findAll();


	model.addAttribute("cate", categories);

		 if (session.getAttribute("idth") == null && session.getAttribute("idCate") == null) {

			int idth = 1;
			int idcate = 1;

			List<Product> products = productRepository.findByRelatedProduct(idth, idcate);

			model.addAttribute("cart", cartService.getGioHang());

	   model.addAttribute("products", products);
		}else {
			int idth = ((Integer) (session.getAttribute("idth")) ).intValue();
		 int idcate = ((Integer) (session.getAttribute("idCate")) ).intValue();

		 
		 List<Product> products = productRepository.findByRelatedProduct(idth, idcate);
		
		
		model.addAttribute("products", products);
		}

		if (customerService.isCustomerLogin()) {

			if (userService.isRole()) {
				System.out.println("síisisisisis");
				model.addAttribute("isAdmin", 1);
			}else{
				model.addAttribute("isAdmin", 2);
			}
			model.addAttribute("name",customerService.getCustomer().getHoten());
			model.addAttribute("customer_id",customerService.getCustomer().getUser().getId());
			model.addAttribute("isLogin", 1);
			
	
	
			return "demo";
		}
	
			model.addAttribute("isLogin", 2);
		
	
        return "demo";
    }

    @Autowired
    ProductRepository productRepository;


	@Autowired
	CategoryRepository categoryRepository;

    @GetMapping("/")
    public String po(Model model) {

    List<Product> productByPanasonic = productRepository.findByTitleContaining();

	List<Product> productByNanoco = productRepository.findByTrade();

	List<Product> productByWire = productRepository.findByWire();

	List<Product> productBySockets = productRepository.findBySockets();

	List<Product> productByElectricHouseware = productRepository.findByElectricHouseware();

	List<Category> categories = categoryRepository.findAll();

	Map<Product, Integer> listItems = cartService.getGioHang().getChiTietGioHang();


	for (Product product : listItems.keySet()) {

            

			
		int quantity = listItems.get(product);
		
		model.addAttribute("quantity", quantity);

		System.out.println("sonne");
		System.out.println(quantity);
		
	}


	model.addAttribute("productByPana", productByPanasonic);
	model.addAttribute("productByNano", productByNanoco);
	model.addAttribute("productByWire", productByWire);
	model.addAttribute("productBySockets", productBySockets);
	model.addAttribute("productByElectricHouseware", productByElectricHouseware);
	model.addAttribute("cate", categories);

	if (customerService.isCustomerLogin()) {

		if (userService.isRole()) {
			model.addAttribute("isAdmin", 1);
		}
		model.addAttribute("name",customerService.getCustomer().getHoten());
		model.addAttribute("customer_id",customerService.getCustomer().getUser().getId());
		model.addAttribute("isLogin", 1);
		


		return "index";
	}

		model.addAttribute("isLogin", 2);       

        return "index";
    }



	@GetMapping("/introduce")
    public String introduce(Model model) {


	List<Category> categories = categoryRepository.findAll();

	Map<Product, Integer> listItems = cartService.getGioHang().getChiTietGioHang();


	for (Product product : listItems.keySet()) {

            

			
		int quantity = listItems.get(product);
		
		model.addAttribute("quantity", quantity);
		
	}


	
	model.addAttribute("cate", categories);

	if (customerService.isCustomerLogin()) {

		if (userService.isRole()) {
			model.addAttribute("isAdmin", 1);
		}
		model.addAttribute("name",customerService.getCustomer().getHoten());
		model.addAttribute("customer_id",customerService.getCustomer().getUser().getId());
		model.addAttribute("isLogin", 1);
		


		return "introduce";
	}

		model.addAttribute("isLogin", 2);
       

        return "introduce";
    }

	@GetMapping("/installation")
    public String ld(Model model) {


	List<Category> categories = categoryRepository.findAll();
	Map<Product, Integer> listItems = cartService.getGioHang().getChiTietGioHang();


	for (Product product : listItems.keySet()) {

            

			
		int quantity = listItems.get(product);
		
		model.addAttribute("quantity", quantity);

		System.out.println("sonne");
		System.out.println(quantity);
		
	}

	
	model.addAttribute("cate", categories);

	if (customerService.isCustomerLogin()) {

		if (userService.isRole()) {
			model.addAttribute("isAdmin", 1);
		}
		model.addAttribute("name",customerService.getCustomer().getHoten());
		model.addAttribute("customer_id",customerService.getCustomer().getUser().getId());
		model.addAttribute("isLogin", 1);
		


		return "ld";
	}

		model.addAttribute("isLogin", 2);

       

        return "ld";
    }


    @GetMapping("/checkout/success")
    public String oderSuccess(){

        return "ordersuccess";
    }

    private static final int TOI_DA_SAN_PHAM = 9;

    @GetMapping("/product")
    public String listproduct(@RequestParam(name = "sort", defaultValue = "product_id") String sortFeild,
			@RequestParam(name = "pageIndex", defaultValue = "0") int pageIndex,
			@RequestParam(name = "price_min", defaultValue = "0") Long giamin,
			@RequestParam(name = "price_max", defaultValue = "10000000") Long giamax,
			@RequestParam(name = "sortBy", defaultValue = "ASC") String sortBy, Model model, HttpServletRequest request) {

		Sort sortable = null;

		if (sortBy.equals("DESC")) {
			sortable = Sort.by(Sort.Direction.DESC, sortFeild);
		}

		if (sortBy.equals("ASC")) {
			sortable = Sort.by(Sort.Direction.ASC, sortFeild);
		}

		

		Pageable pager = PageRequest.of(pageIndex, TOI_DA_SAN_PHAM, sortable);

		Map<Product, Integer> listItems = cartService.getGioHang().getChiTietGioHang();


	for (Product product : listItems.keySet()) {

            

			
		int quantity = listItems.get(product);
		
		model.addAttribute("quantity", quantity);
		
	}
		// lấy sản phẩm
		Page<Product> productPage = productRepository.findProductAll(giamin, giamax, pager);
		List<ThuongHieu> thuonghieu = trademarkRepository.findAll();
		//category
		List<Category> categories = categoryRepository.findAll();

		String requestUrl = request.getRequestURL().toString() + "?" + request.getQueryString();

		model.addAttribute("requestUrl", requestUrl);


		model.addAttribute("cate", categories);

		model.addAttribute("thuonghieu", thuonghieu);

		model.addAttribute("countProduct", productPage.getTotalElements());
		System.out.println("dddd");
		System.out.println(productPage.getTotalElements());
		model.addAttribute("products", productPage.getContent());
		// truyền vào số lượng page tối đa
		model.addAttribute("maxPage", productPage.getTotalPages());
		model.addAttribute("demo", productPage.getTotalElements());

		// truyền vào page hiện tại
		model.addAttribute("currentPage", productPage.getNumber());

		// truyền vào sắp xếp theo id, name, price
		model.addAttribute("sortName", sortFeild);

		// truyền vào kiểu sắp xếp 
		model.addAttribute("sortDesc", sortBy);

		if (customerService.isCustomerLogin()) {
			if (userService.isRole()) {
				model.addAttribute("isAdmin", 1);
			}else{
				model.addAttribute("isAdmin", 2);
			}
			model.addAttribute("name",customerService.getCustomer().getHoten());
			model.addAttribute("customer_id",customerService.getCustomer().getUser().getId());
			model.addAttribute("isLogin", 1);
			
	
			return "product";
		}
	
			model.addAttribute("isLogin", 2);


        return "product";
    }


	@GetMapping("/category/{catename}")
    public String listcate(@PathVariable String catename, @RequestParam(name = "sort", defaultValue = "product_id") String sortFeild,
			@RequestParam(name = "pageIndex", defaultValue = "0") int pageIndex,
			@RequestParam(name = "trademark", defaultValue = "") String th,
			@RequestParam(name = "price_min", defaultValue = "0") Long giamin,
			@RequestParam(name = "price_max", defaultValue = "10000000") Long giamax,
			@RequestParam(name = "sortBy", defaultValue = "ASC") String sortBy, Model model, HttpServletRequest request) {

		Sort sortable = null;

		if (sortBy.equals("DESC")) {
			sortable = Sort.by(Sort.Direction.DESC, sortFeild);
		}

		if (sortBy.equals("ASC")) {
			sortable = Sort.by(Sort.Direction.ASC, sortFeild);
		}

		List<ThuongHieu> thuonghieu = trademarkRepository.findAll();
			//category
			List<Category> categories = categoryRepository.findAll();
			
		Map<Product, Integer> listItems = cartService.getGioHang().getChiTietGioHang();


		for (Product product : listItems.keySet()) {
	
				
	
				
			int quantity = listItems.get(product);
			
			model.addAttribute("quantity", quantity);
			
		}
	
	
			model.addAttribute("cate", categories);
	
			model.addAttribute("thuonghieu", thuonghieu);

		Pageable pager = PageRequest.of(pageIndex, TOI_DA_SAN_PHAM, sortable);

		if (th.equals("")) {
			Page<Product> productPage = productRepository.findCategory(catename,giamin, giamax, pager);

			model.addAttribute("countProduct", productPage.getTotalElements());

		model.addAttribute("products", productPage.getContent());
		// truyền vào số lượng page tối đa
		model.addAttribute("maxPage", productPage.getTotalPages());
		model.addAttribute("demo", productPage.getTotalElements());

		// truyền vào page hiện tại
		model.addAttribute("currentPage", productPage.getNumber());

		
		}else{
			Page<Product> productPage = productRepository.findCategoryAndTrademake(catename,th,giamin, giamax, pager);

			model.addAttribute("countProduct", productPage.getTotalElements());

		model.addAttribute("products", productPage.getContent());
		// truyền vào số lượng page tối đa
		model.addAttribute("maxPage", productPage.getTotalPages());
		model.addAttribute("demo", productPage.getTotalElements());

		// truyền vào page hiện tại
		model.addAttribute("currentPage", productPage.getNumber());

		
		}

		if (customerService.isCustomerLogin()) {

			if (userService.isRole()) {
				model.addAttribute("isAdmin", 1);
			}else{
				model.addAttribute("isAdmin", 2);
			}
			model.addAttribute("name",customerService.getCustomer().getHoten());
			model.addAttribute("customer_id",customerService.getCustomer().getUser().getId());
			model.addAttribute("isLogin", 1);
			
			return "category";
		}
	
			model.addAttribute("isLogin", 2);
		


		

		// truyền vào sắp xếp theo id, name, price
		model.addAttribute("sortName", sortFeild);

		// truyền vào kiểu sắp xếp 
		model.addAttribute("sortDesc", sortBy);

		String test = request.getRequestURL().toString() + "?" + request.getQueryString();

		System.out.println("sss" + test);
		model.addAttribute("requestUrl", test);

        return "category";
    }


	
	@GetMapping("/trademark/{name}")
    public String filterByTremak(@PathVariable String name, @RequestParam(name = "price_min", defaultValue = "0") Long giamin,
			@RequestParam(name = "price_max", defaultValue = "10000000") Long giamax,
			@RequestParam(name = "pageIndex", defaultValue = "0") int pageIndex,
			@RequestParam(name = "sortBy", defaultValue = "ASC") String sortBy, Model model, HttpServletRequest request) {

		
		Pageable pager = PageRequest.of(pageIndex, TOI_DA_SAN_PHAM);

		Map<Product, Integer> listItems = cartService.getGioHang().getChiTietGioHang();


	for (Product product : listItems.keySet()) {

            

			
		int quantity = listItems.get(product);
		
		model.addAttribute("quantity", quantity);
		
	}

		// lấy sản phẩm
		Page<Product> productPage = productRepository.findByTradeMake(name, giamin, giamax, pager);

		List<ThuongHieu> thuonghieu = trademarkRepository.findAll();

		List<Category> categories = categoryRepository.findAll();


		model.addAttribute("cate", categories);

		model.addAttribute("thuonghieu", thuonghieu);

		model.addAttribute("countProduct", productPage.getTotalElements());
		model.addAttribute("demo", productPage.getTotalElements());

		model.addAttribute("products", productPage.getContent());
		// truyền vào số lượng page tối đa
		model.addAttribute("maxPage", productPage.getTotalPages());

		// truyền vào page hiện tại
		model.addAttribute("currentPage", productPage.getNumber());

		model.addAttribute("trademaxName", name);
		model.addAttribute("price_min", giamin);
		model.addAttribute("price_max", giamax);

		// truyền vào sắp xếp theo id, name, price
		//model.addAttribute("sortName", sortFeild);

		// truyền vào kiểu sắp xếp 
		model.addAttribute("sortDesc", sortBy);

		if (customerService.isCustomerLogin()) {

			if (userService.isRole()) {
				model.addAttribute("isAdmin", 1);
			}else{
				model.addAttribute("isAdmin", 2);
			}
			model.addAttribute("name",customerService.getCustomer().getHoten());
			model.addAttribute("customer_id",customerService.getCustomer().getUser().getId());
			model.addAttribute("isLogin", 1);
			
	
			return "filter_product";
		}
	
			model.addAttribute("isLogin", 2);

			String requestUrl = request.getRequestURL().toString() + "?" + request.getQueryString();

		model.addAttribute("requestUrl", requestUrl);


        return "filter_product";
    }


    // Search product
	@GetMapping("search")
	public String search(@RequestParam(name = "sort", defaultValue = "productId") String sortFeild,
			@RequestParam(name = "pageIndex", defaultValue = "0") int pageIndex,
			// thêm từ khóa tìm kiếm
			@RequestParam(name = "query", defaultValue = "") String productname, 
			@RequestParam(name = "sortBy", defaultValue = "ASC") String sortBy, Model model, HttpServletRequest request
			) {

		Sort sortable = null;

		if (sortBy.equals("DESC")) {
			sortable = Sort.by(Sort.Direction.DESC, sortFeild);
		}

		if (sortBy.equals("ASC")) {
			sortable = Sort.by(Sort.Direction.ASC, sortFeild);
		}

		Pageable pager = PageRequest.of(pageIndex, TOI_DA_SAN_PHAM, sortable);
		// lấy sản phẩm
		Page<Product> productPage = productRepository.findByTensanphamContainingIgnoreCase(productname, pager);

		List<ThuongHieu> thuonghieu = trademarkRepository.findAll();

		model.addAttribute("thuonghieu", thuonghieu);

		if (productname.equals("") ) {
			model.addAttribute("demo", 0);
		}else {
			model.addAttribute("demo", productPage.getTotalElements());
		}

		//category
		List<Category> categories = categoryRepository.findAll();


		model.addAttribute("cate", categories);
		model.addAttribute("searchName", productname);
		model.addAttribute("products", productPage.getContent());

		model.addAttribute("maxPage", productPage.getTotalPages());
		// truyền vào page hiện tại
		model.addAttribute("currentPage", productPage.getNumber());
		
		// truyền vào sắp xếp theo id, name, price
		model.addAttribute("sortName", sortFeild);

		// truyền vào kiểu sắp xếp desc
		model.addAttribute("sortDesc", sortBy);

		String test = request.getRequestURL().toString() + "?" + request.getQueryString();

		System.out.println("sss" + test);
		model.addAttribute("requestUrl", test);

		Map<Product, Integer> listItems = cartService.getGioHang().getChiTietGioHang();
		for (Product product : listItems.keySet()) {

			int quantity = listItems.get(product);
			
			model.addAttribute("quantity", quantity);
			
		}

		if (customerService.isCustomerLogin()) {

			if (userService.isRole()) {
				model.addAttribute("isAdmin", 1);
			}else{
				model.addAttribute("isAdmin", 2);
			}
			model.addAttribute("name",customerService.getCustomer().getHoten());
			model.addAttribute("customer_id",customerService.getCustomer().getUser().getId());
			model.addAttribute("isLogin", 1);

			return "search";
		}
	
			model.addAttribute("isLogin", 2);

		return "search";
	}

@Autowired
CustomerService customerService;

	@GetMapping("/myOrder")
    public String OrderCustomer(Model model) {

		List<Category> categories = categoryRepository.findAll();

		Map<Product, Integer> listItems = cartService.getGioHang().getChiTietGioHang();


	for (Product product : listItems.keySet()) {
			
		int quantity = listItems.get(product);
		
		model.addAttribute("quantity", quantity);
	}

		
		model.addAttribute("cart", cartService.getGioHang());


	model.addAttribute("cate", categories);

		if (customerService.isCustomerLogin()) {

			if (userService.isRole()) {
				model.addAttribute("isAdmin", 1);
			}else{
				model.addAttribute("isAdmin", 2);
			}
			model.addAttribute("name",customerService.getCustomer().getHoten());
			model.addAttribute("customer_id",customerService.getCustomer().getUser().getId());
			model.addAttribute("isLogin", 1);
			
	
	
			return "OrderCustomer";
		}
	
			model.addAttribute("isLogin", 2);


        return "OrderCustomer";
    }



	@GetMapping("/contact")
	public String contact(Model model) {

		Map<Product, Integer> listItems = cartService.getGioHang().getChiTietGioHang();


	for (Product product : listItems.keySet()) {

            

			
		int quantity = listItems.get(product);
		
		model.addAttribute("quantity", quantity);
	}

	List<Category> categories = categoryRepository.findAll();


	model.addAttribute("cate", categories);
		
		model.addAttribute("cart", cartService.getGioHang());

		if (customerService.isCustomerLogin()) {
			model.addAttribute("name",customerService.getCustomer().getHoten());
			model.addAttribute("customer_id",customerService.getCustomer().getUser().getId());
			model.addAttribute("isLogin", 1);
			
	
	
			return "demo";
		}
	
			model.addAttribute("isLogin", 2);

		
		return "contact";
	}



    
	
    
    

   



  
}
