package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bean.Customer;
import com.bean.Product;
import com.service.CustomerService;


@Controller
@RequestMapping("/customer")
public class CustomerController {

	private static int currentCustomer = -1;
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/login")
	public String showCustomerLoginPage(Model mm) {
		return "customerLogin";
	}
	
	
	@GetMapping("/dashboard")
	public String showCustomerDashboard(Model mm) {
		return "customerDashboard";
	}
	
	
	@GetMapping("/signup")
	public String showCustomerSignup(Model mm) {
		return "customerSignup";
	}
	
	@GetMapping("/placeOrders")
	public String showCustomerPlaceOrders(Model mm) {
		return "customerPlaceOrders";
	}
	
	
	@PostMapping("/login")
	public String customerLogin(Model mm, @ModelAttribute Customer customer) {
		
		int result = customerService.customerLogin(customer);
		if(result==0) {
			mm.addAttribute("error", "Account doesn't existes (Please SignUp)");
		}
		else if (result==-1){
			mm.addAttribute("error", "Incorrect Password");
		}
		else {
			currentCustomer = result;
			return "redirect:/customer/dashboard";
		}
		
		return "customerLogin";
	}
	
	
	@PostMapping("/signup")
	public String customerSignup(Model mm, @ModelAttribute Customer customer) {

		int result = customerService.customerSignUp(customer);
		if (result==1) {
			mm.addAttribute("msg", "Account created successfully. Please Login <a href=\"/customer/login\">HERE!!</a>");
		}
		else {
			mm.addAttribute("error", "Account already existes with same email");
		}
		
		return "customerSignup";
	}
	
	
	@GetMapping("/allProduct")
	public String customerAllProduct(Model mm) {
		List<Product> allProduct = customerService.getAllProducts();
		
		mm.addAttribute("product", allProduct);			
		if (allProduct.isEmpty()) {
			mm.addAttribute("error", "No products found");			
		}

		return "customerGetAllProduct";
	}
	
	
	@PostMapping("/placeOrders")
	public String customerPlaceOrder(Model mm, @RequestParam int pid, @RequestParam int qtyOrdered) {
		
		int result = customerService.placeOrder(pid, qtyOrdered, currentCustomer);
		if (result == -2) {
			mm.addAttribute("error", "Customer not logged in");
		}
		else if (result == -1) {
			mm.addAttribute("error", "Invalid Product ID");
		}
		else if (result == 0) {
			mm.addAttribute("error", "Insufficient Quantity");
		}
		else {
			mm.addAttribute("msg", "Order placed successfully");
		}
		
		return "customerPlaceOrders";
	}
	
	
	@GetMapping("/logout")
	public String customerLogOut() {
		currentCustomer = -1;
	    return "redirect:/customer/login";
	}
}
