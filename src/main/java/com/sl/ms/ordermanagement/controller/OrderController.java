package com.sl.ms.ordermanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sl.ms.ordermanagement.model.Items;
import com.sl.ms.ordermanagement.model.Orders;
import com.sl.ms.ordermanagement.service.ItemService;
import com.sl.ms.ordermanagement.service.OrderService;

@RestController
public class OrderController {
	
	
	@Autowired
	OrderService orderservice;
	
	@Autowired
	ItemService itemservice;
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
	private Orders saveOrder(@RequestBody Orders order) {
		orderservice.save(order);
		return order;
		
	}
	@GetMapping("/order/{id}")
	private Orders getOrder(@PathVariable("id") int id) {
		
		return orderservice.getById(id);
	}
		@PostMapping("/order/items")
		private Items saveItem(@RequestBody Items item) {
			itemservice.save(item);
			return item;
			
		}
		@GetMapping("/items")
		private List<Items> getAllitems() 
		{
		return itemservice.getAllItems();
		}
		@GetMapping("/items/{id}")
		private Items getitem(@PathVariable("id") int id) {
			
			return itemservice.getById(id);
		}
		
		@GetMapping("/test/items")
		private Items tt() 
		{
			List<Items> tt= itemservice.getAllItems();
		return tt.get(0);
		}
		
		
		
		@PostMapping("/test")
		private Orders test(@RequestBody Orders order) {
			orderservice.save(order);
			System.out.println(order.getName());
			System.out.println(order.getItems());
			return order;
			
		}
		
	
}
