package com.sl.ms.ordermanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sl.ms.ordermanagement.model.Orders;
import com.sl.ms.ordermanagement.repository.OrderRepository;

import javassist.NotFoundException;

@Service
public class OrderService {
	@Autowired
	OrderRepository orderrepo;

	private static Logger logger = LoggerFactory.getLogger(OrderService.class);
	
	@Autowired
	private RestTemplate restTemplate ; //= new RestTemplate();
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	public List<Orders> getAllOrders(){
		List<Orders> allOrders=  new ArrayList<Orders>();
		orderrepo.findAll().forEach(ord -> allOrders.add(ord));;
		logger.info("Into Order Service get All Orders");
		return allOrders;
		
	}
	public void save(Orders order) {
		logger.info("Into save Service");
		orderrepo.save(order);
	}
	public Orders getById(int id) throws NotFoundException{
		logger.info("In to Get Order by Id-"+id);
		return orderrepo.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Order %d not found", id)));
//				.orElseThrow(() -> new IllegalArgumentException("No Such Order found Veera"));
	}
	public void delete(int id) {
		logger.info("Into Delete Service");
		orderrepo.deleteById(id);
	}
	@HystrixCommand(fallbackMethod = "errorConnect")
	public String CheckProduct(String Id) {
		logger.info("Into CheckProduct Service ID ="+Id);
//		System.out.println(Id);
		String url = "http://localhost:7777/dev//checkproductavail/";
//		String result = restTemplate.getForObject(url+Id,  String.class);
		String result = (String) restTemplate.exchange(url+Id, HttpMethod.GET, null, String.class).getBody();
		
		logger.info("result="+result);
		return result;
		
	}
	
	@SuppressWarnings("unused")
	private String errorConnect(String username) {
		logger.error("Into error function Connect. Look like the check Product Availability Service is not available");
		return "Error";
	}

}
