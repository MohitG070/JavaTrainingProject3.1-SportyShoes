package com.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		Customer user = customerRepository.findByCustomerEmail(customer.getCemail());
		
		if (user == null) {
			return 0;
		}
		else if (user.getCpassword().equals(customer.getCpassword())) {
			return user.getCid();
		}
		else {
			return -1;
		}
	}
	
	
	public int customerSignUp(Customer customer) {

		Customer result = customerRepository.findByCustomerEmail(customer.getCemail());
		if (result == null) {
			customerRepository.save(customer);
			return 1;
		}
		else {
			return 0;
		}
	}
	
	
	public List<Product> getAllProducts(){
		List<Product> allProducts = productRepository.findAll();
		return allProducts;
	}
	
	
	public int placeOrder(int pid, int qtyOrdered, int cid) {
		
		if (cid == -1) {
			return -2;
		}
		
		Optional<Product> result = productRepository.findById(pid);
		if(result.isEmpty()) {
			return -1;
		}
		
		Product product = result.get();
		if (product.getQty() < qtyOrdered){
			return 0;
		}
		
		Optional<Customer> user = customerRepository.findById(cid);
	
		product.setQty(product.getQty()-qtyOrdered);
		productRepository.save(product);
		
		Orders orders = new Orders();
		orders.setQtyOrdered(qtyOrdered);
		orders.setProductOrdered(product);
		orders.setOrderedBy(user.get());
		orders.setOrderDate(LocalDate.now());
		
		ordersRepository.save(orders);
		return 1;
	}
	
	
}
