package com.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bean.Admin;
import com.bean.Customer;
import com.bean.Orders;
import com.bean.Product;
import com.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService adminService;
	
	@GetMapping("/login")
	public String showAdminLoginPage(Model mm) {
		return "adminLogin";
	}
	
	@GetMapping("/changePassword")
	public String showAdminChangePassword(Model mm) {
		return "adminChangePassword";
	}

	@GetMapping("/dashboard")
	public String showAdminDashboard(Model mm) {
		return "adminDashboard";
	}
	
	@GetMapping("/addProduct")
	public String showAdminAddProduct(Model mm) {
		return "adminAddProduct";
	}
	
	@GetMapping("/deleteProduct")
	public String showAdminDeleteProduct(Model mm) {
		return "adminDeleteProduct";
	}
	
	@GetMapping("/updatePrice")
	public String showAdminUpdatePrice(Model mm) {
		return "adminUpdatePrice";
	}
	
	@GetMapping("/updateQty")
	public String showAdminUpdateQty(Model mm) {
		return "adminUpdateQty";
	}
	
	@GetMapping("/ordersByCategory")
	public String showAdminOrdersByCategory(Model mm) {
		return "adminGetOrdersByCategory";
	}
	
	@GetMapping("/ordersByDate")
	public String showAdminOrdersByDate(Model mm) {
		return "adminGetOrdersByDate";
	}
	
	
	@PostMapping("/login")
	public String adminLogin(Model mm, @ModelAttribute Admin admin) {
		
		int result = adminService.adminLogin(admin);
		if (result==1) {
			return "redirect:/admin/dashboard";
		}
		else if(result==0) {
			mm.addAttribute("error", "Account doesn't existes");
		}
		else {
			mm.addAttribute("error", "Incorrect Password");
		}
		
		return "adminLogin";
	}
	
	
	@PostMapping("/changePassword")
	public String adminChangePassword(Model mm, @RequestParam String email, @RequestParam String currentPassword, @RequestParam String newPassword) {
		
		int result = adminService.changePassword(email, currentPassword, newPassword);
		if (result == 0) {
			mm.addAttribute("error", "Admin doesn't exists");
		}
		else if (result == 1) {
			mm.addAttribute("msg", "Password changed successfully");
		}
		else {
			mm.addAttribute("error", "Please Enter the correct Current Password");
		}
		
		return "adminChangePassword";
	}
	
	
	@PostMapping("/addProduct")
	public String adminAddProduct(Model mm, @ModelAttribute Product product) {
		int result = adminService.addProduct(product);
		
		if (result == 0) {
			mm.addAttribute("error", "Product ID must be unique");
		}
		else {
			mm.addAttribute("msg", "Product Stored successfully");
		}
		
		return "adminAddproduct";
	}
	
	
	@PostMapping("/deleteProduct")
	public String adminDeleteProduct(Model mm, @RequestParam int pid) {
		int result = adminService.deleteProductById(pid);
		
		if (result == 0) {
			mm.addAttribute("error", "Invalid product ID (Product isn't present)");
		}
		else {
			mm.addAttribute("msg", "Product deleted successfully");
		}
		
		return "adminDeleteProduct";
	}
	
	
	@PostMapping("/updatePrice")
	public String adminUpdatePrice(Model mm, @ModelAttribute Product product) {
		int result = adminService.updateProductPrice(product);
		
		if (result == 0) {
			mm.addAttribute("error", "Product not present");
		}
		else {
			mm.addAttribute("msg", "Product price updated successfully");
		}
		
		return "adminUpdatePrice";
	}
	
	
	@PostMapping("/updateQty")
	public String adminUpdateQty(Model mm, @ModelAttribute Product product) {
		int result = adminService.updateProductQty(product);
		
		if (result == 0) {
			mm.addAttribute("error", "Product not present");
		}
		else {
			mm.addAttribute("msg", "Product quantity updated successfully");
		}
		
		return "adminUpdateQty";
	}
	
	
	@GetMapping("/allProduct")
	public String adminAllProduct(Model mm) {
		List<Product> allProduct = adminService.getAllProducts();
		
		mm.addAttribute("product", allProduct);			
		if (allProduct.isEmpty()) {
			mm.addAttribute("error", "No products found");			
		}

		return "adminGetAllProduct";
	}
	
	
	@GetMapping("/allOrders")
	public String adminAllOrders(Model mm) {
		List<Orders> allOrders = adminService.getAllOrders();
		
		mm.addAttribute("orders", allOrders);			
		if (allOrders.isEmpty()) {
			mm.addAttribute("error", "No orders found");			
		}

		return "adminGetAllOrders";
	}
	
	
	@PostMapping("/ordersByCategory")
	public String adminOrdersByCategory(Model mm, @RequestParam String category) {
		List<Orders> ordersByCategory = adminService.getOrdersByCategory(category);
		
		mm.addAttribute("orders", ordersByCategory);			
		if (ordersByCategory.isEmpty()) {
			mm.addAttribute("error", "No orders found");			
		}

		return "adminGetOrdersByCategory";
	}
	
	
	@PostMapping("/ordersByDate")
	public String adminOrdersByDate(Model mm, @RequestParam LocalDate fromDate, @RequestParam LocalDate toDate) {
		List<Orders> ordersByDate = adminService.getOrdersByDate(fromDate, toDate);
		
		mm.addAttribute("orders", ordersByDate);			
		if (ordersByDate.isEmpty()) {
			mm.addAttribute("error", "No orders found");			
		}
		
		return "adminGetOrdersByDate";
	}
	

	@GetMapping("/allCustomer")
	public String adminAllCustomer(Model mm) {
		List<Customer> allCustomer = adminService.getAllCustomer();
		
		mm.addAttribute("customer", allCustomer);			
		if (allCustomer.isEmpty()) {
			mm.addAttribute("error", "No customers found");			
		}
		
		return "adminGetAllCustomer";
	}

	
	
	
	
	
	
	
	
	
	
	
}
