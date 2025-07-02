package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bean.Admin;
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
		int result = adminService.updateProduct(product);
		
		if (result == 0) {
			mm.addAttribute("error", "Product not Present");
		}
		else {
			mm.addAttribute("msg", "Product price updated successfully");
		}
		
		return "adminUpdatePrice";
	}
	
	
	@PostMapping("/updateQty")
	public String adminUpdateQty(Model mm, @ModelAttribute Product product) {
		int result = adminService.updateProduct(product);
		
		if (result == 0) {
			mm.addAttribute("error", "Product not Present");
		}
		else {
			mm.addAttribute("msg", "Product quantity updated successfully");
		}
		
		return "adminUpdateQty";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
