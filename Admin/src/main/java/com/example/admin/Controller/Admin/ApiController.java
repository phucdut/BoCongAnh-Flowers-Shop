package com.example.admin.Controller.Admin;

import com.example.admin.Domain.*;
import com.example.admin.Domain.ResponseBody;
import com.example.admin.Entity.ProductEntity;
import com.example.admin.Service.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class ApiController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private ImportGoodsService importGoodsService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private VoucherService voucherService;

    @GetMapping("api/items")
    public List<Item> getAllItem() {
        return itemService.getAllItem();
    }

    @GetMapping("/category-data")
    public ResponseEntity<List<CategoryData>> getCategoryData() {
        List<CategoryData> data = categoryService.getAllCategoryData(); // Lấy dữ liệu từ cơ sở dữ liệu
        return ResponseEntity.ok(data);
    }

    @GetMapping("/amount-data")
    public ResponseEntity<List<AmountData>> getAmount() {
        List<AmountData> data = orderService.getAmountByMonth();

        return ResponseEntity.ok(data);
    }
    @PostMapping("/purchase-history")
    public ResponseEntity<Response> postPurchaseHistory(@RequestBody TimeRange timeRange) {
        try {
            LocalDateTime startTime = timeRange.getStartTime();
            LocalDateTime endTime = timeRange.getEndTime();

            List<OrderHistory> orderHistories = orderService.getOrderByTime(startTime, endTime);
            Response response = new Response();
            response.setOrderHistories(orderHistories);

            // Sử dụng URLEncoder để mã hóa thời gian và xây dựng URL
            String encodedStartTime = URLEncoder.encode(startTime.toString(), StandardCharsets.UTF_8);
            String encodedEndTime = URLEncoder.encode(endTime.toString(), StandardCharsets.UTF_8);

            String url = "/admin/orders/search?startTime=" + encodedStartTime + "&endTime=" + encodedEndTime;
            System.out.println("url: " + url);
            response.setUrl(url);

            System.out.println("Thời điểm bắt đầu: " + startTime);
            System.out.println("Thời điểm kết thúc: " + endTime);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/purchase-import-goods")
    public ResponseEntity<Response> postPurchaseImportGoods(@RequestBody TimeRange timeRange) {
        try {
            LocalDateTime startTime = timeRange.getStartTime();
            LocalDateTime endTime = timeRange.getEndTime();

            List<OrderHistory> orderHistories = orderService.getOrderByTime(startTime, endTime);
            Response response = new Response();
            response.setOrderHistories(orderHistories);

            // Sử dụng URLEncoder để mã hóa thời gian và xây dựng URL
            String encodedStartTime = URLEncoder.encode(startTime.toString(), StandardCharsets.UTF_8);
            String encodedEndTime = URLEncoder.encode(endTime.toString(), StandardCharsets.UTF_8);

            String url = "/admin/importGood/search?startTime=" + encodedStartTime + "&endTime=" + encodedEndTime;
            System.out.println("url: " + url);
            response.setUrl(url);

            System.out.println("Thời điểm bắt đầu: " + startTime);
            System.out.println("Thời điểm kết thúc: " + endTime);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Transactional
    @PostMapping("/product-add")
    public ResponseEntity<ResponseBody> addProduct(@ModelAttribute ProductDTO productDto) {
        try {
            ResponseBody response = new ResponseBody();
            if (productService.checkNameProduct(productDto.getName())) {
                System.out.println("vào đc");
                ProductEntity productEntity = productService.createProduct(productDto);
                productService.setCategories(productEntity, productDto);
                response.setSuccess(true);
                response.setMessage("success");
            } else {
                System.out.println("vào đây");
                response.setSuccess(false);
                response.setMessage("Product that already exist");
            }
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @Transactional
    @PostMapping("/product-edit")
    public ResponseEntity<String> editProduct(@ModelAttribute ProductDTO productDto) {
        try {
            productService.updateProduct(productDto);
            String redirectUrl = "/admin/product";
            return ResponseEntity.ok(redirectUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Transactional
    @PostMapping("/staff-edit")
    public ResponseEntity<String> editStaff(@ModelAttribute StaffDTO staffDTO) {
        try {
            userService.updateStaff(staffDTO);
            String redirectUrl = "/admin/staff";
            return ResponseEntity.ok(redirectUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @Transactional
    @PostMapping("/staff-add")
    public ResponseEntity<ResponseBody> addStaff(@ModelAttribute StaffDTO staffDTO) {
        try {
            ResponseBody response = new ResponseBody();
            if (userService.addStaff(staffDTO)) {
                System.out.println("vào đc");
                response.setSuccess(true);
                response.setMessage("success");
            } else {
                System.out.println("vào đây");
                response.setSuccess(false);
                response.setMessage("Staff that already exist");
            }
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Transactional
    @PostMapping("/category-edit")
    public ResponseEntity<String> editCategory(@ModelAttribute CategoryDTO categoryDTO) {
        try {
            categoryService.updateCategory(categoryDTO);
            String redirectUrl = "/admin/category";
            return ResponseEntity.ok(redirectUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @Transactional
    @PostMapping("/category-add")
    public ResponseEntity<ResponseBody> addCategory(@ModelAttribute CategoryDTO categoryDTO) {
        try {
            ResponseBody response = new ResponseBody();
            if (categoryService.addCategory(categoryDTO)) {
                System.out.println("vào đc");
                response.setSuccess(true);
                response.setMessage("success");
            } else {
                System.out.println("vào đây");
                response.setSuccess(false);
                response.setMessage("category that already exist");
            }
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @Transactional
    @PostMapping("/voucher-edit")
    public ResponseEntity<String> editVoucher(@ModelAttribute VoucherDTO voucherDTO) {
        try {
            voucherService.updateVoucher(voucherDTO);
            String redirectUrl = "/admin/voucher";
            return ResponseEntity.ok(redirectUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @Transactional
    @PostMapping("/voucher-add")
    public ResponseEntity<ResponseBody> addVoucher(@ModelAttribute VoucherDTO voucherDTO) {
        try {
            ResponseBody response = new ResponseBody();
            if (voucherService.addVoucher(voucherDTO)) {
                System.out.println("vào đc");
                response.setSuccess(true);
                response.setMessage("success");
            } else {
                System.out.println("vào đây");
                response.setSuccess(false);
                response.setMessage("voucher that already exist");
            }
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/reset-password/{id}")
    public ResponseEntity<String> resetPassword(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        // Thực hiện logic reset password tại đây
        // ...
        redirectAttributes.addAttribute("id", id);
        userService.resetPassword(id);
        System.out.println("Resetting password for user with id: " + id);
        // Sau khi reset password thành công, trả về đường dẫn mới (hoặc bất kỳ thông tin nào cần thiết)
        String redirectUrl = "/admin/staff/detail/" + id; // Thay đổi thành đường dẫn mong muốn
        return ResponseEntity.ok(redirectUrl);
    }
    @GetMapping("/all-product-by-id")
    public ResponseEntity<?> findById(@RequestParam("id") Long id){
        Product result = productService.getById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
