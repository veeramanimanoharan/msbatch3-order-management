package com.sl.ms.ordermanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sl.ms.ordermanagement.model.Items;

import com.sl.ms.ordermanagement.repository.ItemRepository;

@Service
public class ItemService {
	@Autowired
	ItemRepository itemrepo;
	public List<Items> getAllItems(){
		List<Items> allitems=  new ArrayList<Items>();
		itemrepo.findAll().forEach(ord -> allitems.add(ord));;
		
		return allitems;
		
	}
	public void save(Items order) {
		itemrepo.save(order);
	}
	public Items getById(int id) {
		return itemrepo.findById(id).get();
	}
	public void delete(int id) {
		itemrepo.deleteById(id);
	}
}
