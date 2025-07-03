package com.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Admin;
import com.bean.Customer;
import com.bean.Orders;
import com.bean.Product;
import com.repository.AdminRepository;
import com.repository.CustomerRepository;
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
	
	@Autowired
	CustomerRepository customerRepository;
	
	public int adminLogin(Admin admin) {
		Optional<Admin> result = adminRepository.findById(admin.getEmail());
		
		if (result.isEmpty()) {
			return 0;
		}
		
		Admin user = result.get();
		if (user.getPassword().equals(admin.getPassword())) {
			return 1;
		}
		else {
			return -1;
		}
	}
	

	public int changePassword(String email, String currentPassword, String newPassword) {
		Optional<Admin> result = adminRepository.findById(email);
		
		if (result.isEmpty()) {
			return 0;
		}
		
		Admin admin = result.get();
		if(admin.getPassword().equals(currentPassword)) {
			admin.setPassword(newPassword);
			adminRepository.save(admin);
			return 1;
		}
		
		else {
			return -1;
		}
	}
	
	
	public int addProduct(Product product) {
		Optional<Product> result = productRepository.findById(product.getPid());
		
		if (result.isPresent()) {
			return 0;
		}
		else {
			productRepository.save(product);
			return 1;
		}
	}
	
	
	public int deleteProductById(int pid) {
		Optional<Product> result = productRepository.findById(pid);
		
		if (result.isEmpty()) {
			return 0;
		}
		else {
			productRepository.deleteById(pid);
			return 1;
		}
	}
	
	
	public int updateProductPrice(Product product) {
		Optional<Product> result = productRepository.findById(product.getPid());
		
		if (result.isEmpty()) {
			return 0;
		}
		else {
			Product oriProduct = result.get();
			oriProduct.setPrice(product.getPrice());
			productRepository.save(oriProduct);
			return 1;
		}
	}
	
	
	public int updateProductQty(Product product) {
		Optional<Product> result = productRepository.findById(product.getPid());
		
		if (result.isEmpty()) {
			return 0;
		}
		else {
			Product oriProduct = result.get();
			oriProduct.setQty(product.getQty());
			productRepository.save(oriProduct);
			return 1;
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
	
	
	public List<Orders> getOrdersByCategory(String category){
		List<Orders> categoryOrders = ordersRepository.filterByCategory(category);
		return categoryOrders;
	}
	
	
	public List<Orders> getOrdersByDate(LocalDate fromDate, LocalDate toDate){
		List<Orders> dateOrders = ordersRepository.filterByDate(fromDate, toDate);
		return dateOrders;
	}
	
	
	public List<Customer> getAllCustomer(){
		List<Customer> allCustomer = customerRepository.findAll();
		return allCustomer;
	}	
}
