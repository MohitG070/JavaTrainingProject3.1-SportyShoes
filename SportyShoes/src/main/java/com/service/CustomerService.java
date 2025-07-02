package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Admin;
import com.bean.Customer;
import com.bean.Orders;
import com.bean.Product;
import com.repository.CustomerRepository;
import com.repository.OrdersRepository;
import com.repository.ProductRepository;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	OrdersRepository ordersRepository;
	
	public int customerLogin(Customer customer) {
		Optional<Customer> result = customerRepository.findById(customer.getCid());
		
		if (result.isEmpty()) {
			return 0;
		}
		
		Customer user = result.get();
		if (user.getCpassword().equals(customer.getCpassword())) {
			return 1;
		}
		else {
			return -1;
		}
	}
	
	
	public String customerSignUp(Customer customer) {
		Customer result = customerRepository.findByCustomerEmail(customer.getCemail());
		
		if (result == null) {
			customerRepository.save(customer);
			return "Account created successfully";
		}
		else {
			return "Account already exists";
		}
	}
	
	
	public List<Product> getAllProducts(){
		List<Product> allProducts = productRepository.findAll();
		return allProducts;
	}
	
	
//	public String placeOrder(Product product, int qtyOrdered) {
//		if (product.getQty() < qtyOrdered) {
//			return "Insufficient stock";
//		}
//		
//		product.setQty(product.getQty()-qtyOrdered);
//		productRepository.save(product);
//		
//		Orders orders = new Orders();
//		orders.setQtyOrdered(qtyOrdered);
//		orders.setProductOrdered(product);
//		orders.setOrderDate();
//		
//		
//		
//	}
	
	
}
