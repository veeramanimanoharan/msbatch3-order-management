package com.sl.ms.ordermanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sl.ms.ordermanagement.model.Items;

import com.sl.ms.ordermanagement.repository.ItemRepository;

@Service
public class ItemService {
	@Autowired
	ItemRepository itemrepo;
	
	private static Logger logger = LoggerFactory.getLogger(ItemService.class);
	
	public List<Items> getAllItems(){
		logger.info("Into Get All Item Service");
		List<Items> allitems=  new ArrayList<Items>();
		itemrepo.findAll().forEach(ord -> allitems.add(ord));;
		
		return allitems;
		
	}
	public void save(Items order) {
		logger.info("Into save Item Service");
		itemrepo.save(order);
	}
	public Items getById(int id) {
		logger.info("Into Get Item Service-"+id);
		return itemrepo.findById(id).get();
	}
	public void delete(int id) {
		logger.info("Into delete  Item Service -"+id);
		itemrepo.deleteById(id);
	}
}
