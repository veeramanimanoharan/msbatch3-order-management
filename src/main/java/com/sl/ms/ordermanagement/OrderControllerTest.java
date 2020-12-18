package com.sl.ms.ordermanagement;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sl.ms.ordermanagement.model.Orders;
import com.sl.ms.ordermanagement.service.OrderService;


//@RestController
public class OrderControllerTest {
	@Autowired(required = true)
	OrderService orderservice;
	@GetMapping("/")
	private String msg() {
		System.out.println("Veera");
		return "Veera";
	}
	@GetMapping("/orders")
	private List<Orders> getAllStudent() 
	{
		System.out.println("orders");
	return orderservice.getAllOrders();
	}
	@PostMapping("/order")
	private boolean saveOrder(@RequestBody Orders order) {
		orderservice.save(order);
		return true;
		
	}
	
}
