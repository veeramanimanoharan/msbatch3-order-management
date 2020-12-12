package com.sl.ms.ordermanagement.repository;

import org.springframework.data.repository.CrudRepository;

import com.sl.ms.ordermanagement.model.Items;

public interface ItemRepository extends CrudRepository<Items,Integer>{

}
