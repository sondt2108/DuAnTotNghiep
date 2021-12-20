package com.example.datn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.example.datn.models.Customer;
import com.example.datn.models.MessageNotifications;
import com.example.datn.models.NhaCungCap;
import com.example.datn.models.Order;
import com.example.datn.models.OrderDetail;
import com.example.datn.models.PhieuNhap;
import com.example.datn.models.Product;
import com.example.datn.models.SearchForm;
import com.example.datn.models.ThuongHieu;
import com.example.datn.models.TinhTrangDonHang;
import com.example.datn.models.User;
import com.example.datn.payload.request.OrderCustomerRequest;
import com.example.datn.repository.CustomerRepository;
import com.example.datn.repository.MessageNotificationsRepository;
import com.example.datn.repository.OrderDetailRepository;
import com.example.datn.repository.OrderRepository;
import com.example.datn.repository.OrderStatusRepository;
import com.example.datn.repository.ProductRepository;
import com.example.datn.repository.SupplierRepository;
import com.example.datn.repository.TinhTrangdhRepository;
import com.example.datn.repository.TrademarkRepository;
import com.example.datn.repository.UserRepository;
import com.example.datn.repository.WarehouseReceiptRepository;
import com.example.datn.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    

    private static final int TOI_DA_SAN_PHAM = 9;
    @PreAuthorize("hasRole('ADMIN')")
	@PostMapping("api/product/search")
    public Page<Product> search(
            // thông tin form tìm kiếm
            @RequestBody SearchForm sf) {

        Pageable phanTrang = PageRequest.of(sf.getTrang(), TOI_DA_SAN_PHAM,
                // nếu đúng thì thứ tự tăng đần ngược lại giảm dần
                sf.getThuTu() ? Direction.DESC : Direction.ASC,
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


    //edit product
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/api/products/{id}")
    public Product update(@PathVariable("id") int id, @RequestBody Product product) {
        return productRepository.save(product);
    }


    //delete product
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/api/products/{id}")
	public void delete(@PathVariable("id") int id) {
		productRepository.deleteById(id);
	}

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/thuonghieu")
	public List<ThuongHieu> thuongHieu() {
		return trademarkRepository.findAll();
	}


    @GetMapping("/api/thuonghieu/{id}")
	public ThuongHieu getTrademakeById(@PathVariable("id") int id) {
		return trademarkRepository.findById(id).orElse(null);
	}
	
    @PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/api/thuonghieu")
	public ThuongHieu insertTrademake(@RequestBody @Valid ThuongHieu thuonghieu) {
		return trademarkRepository.save(thuonghieu);
	}
	
    @PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/api/thuonghieu/{id}")
	public ThuongHieu updateTradamake(@Valid @RequestBody ThuongHieu thuonghieu) {
		return trademarkRepository.save(thuonghieu);
	}
	
    @PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/api/thuonghieu/{id}")
	public void deleteTrademake(@PathVariable("id") int id) {
		trademarkRepository.deleteById(id);
	}

//supplier
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/nhacungcap")
	public List<NhaCungCap> list() {
		return supplierRepository.findAll();
	}

    @GetMapping("/api/nhacungcap/{id}")
	public NhaCungCap getSupplierById(@PathVariable("id") int id) {
		return supplierRepository.findById(id).orElse(null);
	}
	
    @PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/api/nhacungcap")
	public NhaCungCap insertSupplier(@RequestBody @Valid NhaCungCap nhacungcap) {
		return supplierRepository.save(nhacungcap);
	}
	
    @PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/api/nhacungcap/{id}")
	public NhaCungCap updateSupplier(@Valid @RequestBody NhaCungCap nhacungcap) {
		return supplierRepository.save(nhacungcap);
	}
	
    @PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/api/nhacungcap/{id}")
	public void deleteSupplier(@PathVariable("id") int id) {
		supplierRepository.deleteById(id);
	}




    //customer

    @Autowired
	CustomerRepository customerRepository;
	
    @PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/api/khachhang")
	public List<Customer> listcus() {
		return customerRepository.findAll();
	}
	

	@GetMapping("/api/khachhang/{id}")
	public Customer getByCustomerId(@PathVariable("id") int id) {
		return customerRepository.findById(id).orElse(null);
	}
	
    @PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/api/khachhang")
    public HashMap<String, Object> insert(@RequestBody @Validated Customer customer, BindingResult result) {

        HashMap<String, Object> ResponseData = new HashMap<>();
        ResponseData.put("status", true);

        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();

            HashMap<String, String> ListValid = new HashMap<>();

            for (FieldError error : fieldErrors) {
                ListValid.put(error.getField(), error.getDefaultMessage());
            }

            ResponseData.put("status", false);
            ResponseData.put("data", ListValid);

            return ResponseData;

        }
        ResponseData.put("data", customerRepository.save(customer));

        return ResponseData;
    }
	
    @PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/api/khachhang/{id}")
    public Customer update(@PathVariable("id") int id, @RequestBody Customer customer) {
        return customerRepository.save(customer);
    }
	
    @PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/api/khachhang/{id}")
	public void deleteCustomer(@PathVariable("id") int id) {
		customerRepository.deleteById(id);
	}
	
	@PostMapping("api/khachhang/search")
    public Page<Customer> searchCustomer(
            // thông tin form tìm kiếm
            @RequestBody SearchForm sf) {

        Pageable phanTrang = PageRequest.of(sf.getTrang(), TOI_DA_SAN_PHAM,
                // nếu đúng thì thứ tự tăng đần ngược lại giảm dần
                sf.getThuTu() ? Direction.ASC : Direction.DESC,
                // xếp theo trường nào ví dụ id, name, price
                sf.getXepTheo());

        // lấy sản phẩm
        Page<Customer> customerPage = customerRepository.findByHotenContaining(sf.getTen(), phanTrang);

        return customerPage;
    }



    //user
    @Autowired
  	UserRepository userRepository;
  	
    @PreAuthorize("hasRole('ADMIN')")
  	@GetMapping("/api/users")
  	public List<User> listUser() {
  		return userRepository.findAll();
  	}
  	
  	@GetMapping("/api/users/{id}")
  	public User getById(@PathVariable("id") long id) {
  		return userRepository.findById(id).orElse(null);
  	}
  	
    @PreAuthorize("hasRole('ADMIN')")
  	@PostMapping(value = "/api/users")
      public HashMap<String, Object> insert(@RequestBody @Validated User user, BindingResult result) {

          HashMap<String, Object> ResponseData = new HashMap<>();
          ResponseData.put("status", true);

          if (result.hasErrors()) {
              List<FieldError> fieldErrors = result.getFieldErrors();

              HashMap<String, String> ListValid = new HashMap<>();

              for (FieldError error : fieldErrors) {
                  ListValid.put(error.getField(), error.getDefaultMessage());
              }

              ResponseData.put("status", false);
              ResponseData.put("data", ListValid);

              return ResponseData;

          }
          ResponseData.put("data", userRepository.save(user));

          return ResponseData;
      }
  	
      @PreAuthorize("hasRole('ADMIN')")
  	@PutMapping("/api/users/{id}")
      public User update(@PathVariable("id") int id, @RequestBody User user) {
          return userRepository.save(user);
      }

  	@PreAuthorize("hasRole('ADMIN')")
  	@DeleteMapping("/api/users/{id}")
  	public void delete(@PathVariable("id") long id) {
  		userRepository.deleteById(id);
  	}
  	
    @PreAuthorize("hasRole('ADMIN')")
  	@PostMapping("api/users/search")
      public Page<User> searchUser(
              // thông tin form tìm kiếm
              @RequestBody SearchForm sf) {

          Pageable phanTrang = PageRequest.of(sf.getTrang(), TOI_DA_SAN_PHAM,
                  // nếu đúng thì thứ tự tăng đần ngược lại giảm dần
                  sf.getThuTu() ? Direction.ASC : Direction.DESC,
                  // xếp theo trường nào ví dụ id, name, price
                  sf.getXepTheo());

          // lấy sản phẩm
          Page<User> userPage = userRepository.findByUsernameContaining(sf.getTen(), phanTrang);

          return userPage;
      }



      @Autowired
      OrderRepository orderRepository;

      @GetMapping("/api/order/{id}")
	public Order getById(@PathVariable("id") Long id) {
		return orderRepository.findById(id).orElse(null);
	}

    @PreAuthorize("hasRole('ADMIN')")
      @PostMapping("api/order/search")
      public Page<Order> searchOrder(
              // thông tin form tìm kiếm
              @RequestBody SearchForm sf) {

          Pageable phanTrang = PageRequest.of(sf.getTrang(), TOI_DA_SAN_PHAM,
                  // nếu đúng thì thứ tự tăng đần ngược lại giảm dần
                  sf.getThuTu() ? Direction.ASC : Direction.DESC,
                  // xếp theo trường nào ví dụ id, name, price
                  sf.getXepTheo());

          // lấy sản phẩm
          Page<Order> orderPage = orderRepository.findByOrderId(sf.getTen(), phanTrang);

          return orderPage;
      }

    @Autowired
    CustomerService customerService;


      @PostMapping("api/orderCustomer/search")
      public Page<Order> OrderCustomer(
              // thông tin form tìm kiếm
              @RequestBody OrderCustomerRequest orderCustomerRequest) {

          Pageable phanTrang = PageRequest.of(orderCustomerRequest.getTrang(), TOI_DA_SAN_PHAM,
                  // nếu đúng thì thứ tự tăng đần ngược lại giảm dần
                  orderCustomerRequest.getThuTu() ? Direction.ASC : Direction.DESC,
                  // xếp theo trường nào ví dụ id, name, price
                  orderCustomerRequest.getXepTheo()
                 );

            String email = orderCustomerRequest.getEmail();
            long customer_id = orderCustomerRequest.getCustomer_id();

          // lấy sản phẩm
          if (customerService.isCustomerLogin()) {
            String emailUser = customerService.getCustomer().getUser().getEmail();
            long customerIdUser = customerService.getCustomer().getUser().getId();
            System.out.println(emailUser);
            System.out.println(customerIdUser);
          Page<Order> orderPage = orderRepository.findOrderCustomer(emailUser, customerIdUser, phanTrang);
            
          return orderPage;
          }

          Page<Order> orderPage = orderRepository.findOrderCustomer(email, customer_id, phanTrang);
          return orderPage;
      }


      @PreAuthorize("hasRole('ADMIN')")
      @PutMapping("/api/order/{id}")
	public Order update(@PathVariable("id") Long id, @Valid @RequestBody Order order) {

        Optional<Order> orderOptional = Optional
					.ofNullable(orderRepository.findByOrderId(id));
        Order or = orderOptional.get();
        or.setTinhtrang(order.getTinhtrang());
		return orderRepository.save(or);
	}

    public final static TinhTrangDonHang DEFAULT_TTDH = new TinhTrangDonHang();
    static {
		DEFAULT_TTDH.setIdTT(6);
      
    }

    @PutMapping("/api/cencelOrder/{id}")
	public Order cencelOrder(@PathVariable("id") Long id, @Valid @RequestBody Order order) {

        Optional<Order> orderOptional = Optional
					.ofNullable(orderRepository.findByOrderId(id));
        Order or = orderOptional.get();
        or.setTinhtrang(DEFAULT_TTDH);


        MessageNotifications ntn = new MessageNotifications();
        ntn.setOrderId(id);
        ntn.setDescription("vừa được hủy");
        messageNotificationsRepository.save(ntn);
		return orderRepository.save(or);
	}

    
    @Autowired
    MessageNotificationsRepository messageNotificationsRepository;

    @DeleteMapping("/api/notifi/{id}")
  	public void deleteNotifi(@PathVariable("id") long id) {
  		messageNotificationsRepository.deleteById(id);
  	}

      @DeleteMapping("/api/notifi")
  	public void deleteAllNotifi() {
  		messageNotificationsRepository.deleteAll();
  	}


      @Autowired
      OrderDetailRepository orderDetailRepository;




      //order detail
      @GetMapping("/api/orderdetail/{id}")
  	public List<OrderDetail> getByOrderDetail(@PathVariable("id") long id) {
  		
        
        return orderDetailRepository.findOrderItems(id);
  	}




// tình trạng đơn hàng

@Autowired
OrderStatusRepository orderStatusRepository;



@GetMapping("api/tinhtrang")
public List<TinhTrangDonHang> listOrderStatus(){

    return orderStatusRepository.findAll();
}

//PN
@Autowired
WarehouseReceiptRepository warehouseReceiptRepository;
    
@PreAuthorize("hasRole('ADMIN')")
@GetMapping("/api/phieunhap")
	public List<PhieuNhap> listPN() {
		return warehouseReceiptRepository.findAll();
	}
	
	@GetMapping("/api/phieunhap/{id}")
	public PhieuNhap getByIdPN(@PathVariable("id") int id) {
		return warehouseReceiptRepository.findById(id).orElse(null);
	}
	
    @PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/api/phieunhap")
    public HashMap<String, Object> insert(@RequestBody @Validated PhieuNhap phieuNhap, BindingResult result) {

        HashMap<String, Object> ResponseData = new HashMap<>();
        ResponseData.put("status", true);

        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();

            HashMap<String, String> ListValid = new HashMap<>();

            for (FieldError error : fieldErrors) {
                ListValid.put(error.getField(), error.getDefaultMessage());
            }

            ResponseData.put("status", false);
            ResponseData.put("data", ListValid);

            return ResponseData;

        }
        ResponseData.put("data", warehouseReceiptRepository.save(phieuNhap));

        return ResponseData;
    }
	
    @PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/api/phieunhap/{id}")
    public PhieuNhap update(@PathVariable("id") int id, @RequestBody PhieuNhap phieuNhap) {
        return warehouseReceiptRepository.save(phieuNhap);
    }
	
    @PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/api/phieunhap/{id}")
	public void deletePN(@PathVariable("id") int id) {
		warehouseReceiptRepository.deleteById(id);
	}

    @PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/api/phieunhap/search")
    public Page<PhieuNhap> searchPN(
            // thông tin form tìm kiếm
            @RequestBody SearchForm sf) {

        Pageable phanTrang = PageRequest.of(sf.getTrang(), TOI_DA_SAN_PHAM,
                // nếu đúng thì thứ tự tăng đần ngược lại giảm dần
                sf.getThuTu() ? Direction.ASC : Direction.DESC,
                // xếp theo trường nào ví dụ id, name, price
                sf.getXepTheo());

        // lấy sản phẩm
        Page<PhieuNhap> khachHangPage = warehouseReceiptRepository.findByIdPN(sf.getTen(), phanTrang);

        return khachHangPage;
    }

    @Autowired
    TinhTrangdhRepository tinhTrangdhRepository;

    @PostMapping("/api/tinhtrang")
	public TinhTrangDonHang insertTinhTrang(@RequestBody @Valid TinhTrangDonHang tinhTrangDonHang) {
		return tinhTrangdhRepository.save(tinhTrangDonHang);
	}
}









