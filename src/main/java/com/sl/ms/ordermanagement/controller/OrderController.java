package com.sl.ms.ordermanagement.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.sl.ms.ordermanagement.model.Items;
import com.sl.ms.ordermanagement.model.Orders;
import com.sl.ms.ordermanagement.service.ItemService;
import com.sl.ms.ordermanagement.service.OrderService;

import javassist.NotFoundException;

@Component
@RestController
public class OrderController {

	private static Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	
	@Autowired
	OrderService orderservice;

	@Autowired
	ItemService itemservice;

	@GetMapping("/orders")
	private List<Orders> getAllOrders() 
	{
		logger.info("Into getAllOrders Controller");
		return orderservice.getAllOrders();
	}
	@PostMapping("/order")
	private String saveOrder(@RequestBody Orders order) {
//		System.out.println(or -> order.getItems());
//		boolean  dontplaceOrder;
		logger.info("Into saveOrder Controller");
		for(Items it : order.getItems()) {
			try {
			String Priavailstr = orderservice.CheckProduct(Integer.toString(it.getId()));
			logger.info("Priavailstr :"+Priavailstr);
			if (Priavailstr=="Error") {return "Looks like service unavailable. Please try later.";}
			boolean prodavai = Boolean.parseBoolean(Priavailstr);
			if (!prodavai) {
				logger.info("Product :"+it.getName()+"is not available to book. Order cannot be placed");
				return "Product :"+it.getName()+"is not available to book. Order cannot be placed";
				 
			}
			}catch(Exception e ) {
				return "Error";
			}
		
			
		}
//		order.getItems().forEach(it->{
//			boolean prodavai = CheckProduct(Integer.toString(it.getId()));
//			if (!prodavai) {
//				System.out.println("Product :"+it.getName()+"is not available to book. Order cannot be placed");
////				return "jjjj";
//				 
//			}
//			System.out.println("Item ID"+it.getId()+"-->"+prodavai);
//			
//		});
		orderservice.save(order);
		return "Order Posted Succesully";

	}
	@GetMapping("/order/{id}")
	private Orders getOrder(@PathVariable("id") int id) throws Exception  {
		logger.info("InTo Get Order by ID-"+id);
		Orders or = orderservice.getById(id);
	
		return  or;
	}
	
	@GetMapping("/items")
	private @ResponseBody List<Items> getAllitems() 
	{
		logger.info("into Get All Items");
		return itemservice.getAllItems();
	}
	@GetMapping("/items/{id}")
	private Items getitem(@PathVariable("id") int id) {
		logger.info("Into Items by ID-"+id);
		return itemservice.getById(id);
	}

	@DeleteMapping("/order/{id}")
	private Orders deleteOrder(@PathVariable("id") int id) throws NotFoundException {
		logger.info("Into Delete Order "+id);
		Orders tt = orderservice.getById(id);
		orderservice.delete(id);
		logger.info("Successfully Deleted -"+id);
		return tt;
	}


	//		****************** Rest Template************

	@GetMapping("/CheckProduct/{id}")
	private String testProcheck(@PathVariable("id") int id) {
		logger.info("Into Check Product by ID"+id);
		return orderservice.CheckProduct(Integer.toString(id));

	}

	//		****************** Testing************
	@GetMapping("/")
	private String msg() {
		logger.info("Into / page");
		return "Veera";
	}
	@PostMapping("/order/items")
	private Items saveItem(@RequestBody Items item) {
		logger.info("Into Save Controller - "+item.getName());
		itemservice.save(item);
		return item;

	}

	@SuppressWarnings("unused")
	@GetMapping("/test/items")
	private Items tt() throws Exception 
	{
		logger.info("Into Test Item");
		List<Items> tt= itemservice.getAllItems();
		if (false) {
			throw new Exception("Veera Exception");
		}
		return tt.get(0);
	}



	@PostMapping("/test")
	private Orders test(@RequestBody Orders order) {
		orderservice.save(order);
		logger.info("Into Post Test -"+order.getItems());
		return order;

	}


}
