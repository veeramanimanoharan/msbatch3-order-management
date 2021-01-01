package com.sl.ms.ordermanagement.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sl.ms.ordermanagement.model.Orders;
import com.sl.ms.ordermanagement.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
	OrderRepository orderrepo;

	private static RestTemplate restTemplate = new RestTemplate();
	
	public List<Orders> getAllOrders(){
		List<Orders> allOrders=  new ArrayList<Orders>();
		orderrepo.findAll().forEach(ord -> allOrders.add(ord));;
		
		return allOrders;
		
	}
	public void save(Orders order) {
		orderrepo.save(order);
	}
	public Orders getById(int id) {
		return orderrepo.findById(id).get();
	}
	public void delete(int id) {
		orderrepo.deleteById(id);
	}
	@HystrixCommand(fallbackMethod = "errorConnect")
	public String CheckProduct(String Id) {
		
//		System.out.println(Id);
		String url = "http://localhost:7777/dev//checkproductavail/";
		String result = restTemplate.getForObject(url+Id,  String.class);
		return result;
		
	}
	
	private String errorConnect(String username) {
		return "Error";
	}

}
