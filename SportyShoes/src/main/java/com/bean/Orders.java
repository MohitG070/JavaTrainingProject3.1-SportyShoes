package com.bean;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int oid;
	private int qtyOrdered;
	private LocalDate orderDate;
	private LocalTime orderTime;
	@ManyToOne
	@JoinColumn(name="pid")
	private Product productOrdered;
	@ManyToOne
	@JoinColumn(name="cid")
	private Customer orderedBy;
	

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public int getQtyOrdered() {
		return qtyOrdered;
	}

	public void setQtyOrdered(int qtyOrdered) {
		this.qtyOrdered = qtyOrdered;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public LocalTime getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(LocalTime orderTime) {
		this.orderTime = orderTime;
	}

	public Product getProductOrdered() {
		return productOrdered;
	}

	public void setProductOrdered(Product productOrdered) {
		this.productOrdered = productOrdered;
	}

	public Customer getOrderedBy() {
		return orderedBy;
	}

	public void setOrderedBy(Customer orderedBy) {
		this.orderedBy = orderedBy;
	}
}
