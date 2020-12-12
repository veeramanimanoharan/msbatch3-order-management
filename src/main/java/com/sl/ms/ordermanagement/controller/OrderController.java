package com.sl.ms.ordermanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sl.ms.ordermanagement.service.OrderService;

@RestController
public class OrderController {
	
	
	@Autowired
	OrderService orderservice;
	@GetMapping("/")
	private String msg() {
		System.out.println("Veera");
		return "Veera";
	}
	@GetMapping("/orders")
	private List<Orders> getAllOrders() 
	{
	return orderservice.getAllOrders();
	}
	@PostMapping("/order")
	private boolean saveOrder(@RequestBody Orders order) {
		orderservice.save(order);
		return true;
		
	}
	@GetMapping("/order/{id}")
	private Orders getOrder(@PathVariable("id") int id) {
		
		return orderservice.getById(id);
	}
	
}
