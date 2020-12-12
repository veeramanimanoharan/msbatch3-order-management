package com.sl.ms.ordermanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import com.sl.ms.ordermanagement.model.Orders;
import com.sl.ms.ordermanagement.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
	OrderRepository orderrepo;

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
}
