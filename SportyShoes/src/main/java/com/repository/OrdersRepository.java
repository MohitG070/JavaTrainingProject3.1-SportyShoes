package com.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bean.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer>{
	
	@Query("select ord from Orders ord where ord.productOrdered.category=:category")
	public List<Orders> filterByCategory(@Param("category") String category);
	
	@Query("select ord from Orders ord where ord.orderDate between :fromDate and :toDate")
	public List<Orders> filterByDate (@Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate);
}
