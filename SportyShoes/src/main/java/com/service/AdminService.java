package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Admin;
import com.bean.Orders;
import com.bean.Product;
import com.repository.AdminRepository;
import com.repository.OrdersRepository;
import com.repository.ProductRepository;

@Service
public class AdminService {

	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	OrdersRepository ordersRepository;
	
	public String changePassword(String email, String currentPassword, String newPassword) {
		Optional<Admin> result = adminRepository.findById(email);
		
		if (result.isEmpty()) {
			return "Admin not found";
		}
		
		Admin admin = result.get();
		if(admin.getPassword().equals(currentPassword)) {
			admin.setPassword(newPassword);
			adminRepository.save(admin);
			return "Password Changed Successfully";
		}
		
		else {
			return "Please Enter the correct Current Password";
		}
	}
	
	
	public String addProduct(Product product) {
		Optional<Product> result = productRepository.findById(product.getPid());
		
		if (result.isPresent()) {
			return "Product ID must be unique";
		}
		else {
			productRepository.save(product);
			return "Product Stored Successfully";
		}
	}
	
	
	public String deleteProductById(int pid) {
		Optional<Product> result = productRepository.findById(pid);
		
		if (result.isEmpty()) {
			return "Invalid product ID (Product isn't present)";
		}
		else {
			productRepository.deleteById(pid);
			return "Product deleted successfully";
		}
	}
	
	
	public List<Product> getAllProducts() {
		List<Product> allProducts = productRepository.findAll();
		return allProducts;
	}
	
	
	public List<Orders> getAllOrders(){
		List<Orders> allOrders = ordersRepository.findAll();
		return allOrders;
	}
	
	
//	public List<Orders> getOrdersByCategory(List<String> category){
//	}
	
	
}
