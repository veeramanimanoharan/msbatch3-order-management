package com.sl.ms.ordermanagement.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table
public class Orders {
	
	@Id
//	 @GeneratedValue(strategy=GenerationType.AUTO)
	private int  id;
	private String name;
	private Double total_amount;
	@JsonManagedReference
	 @OneToMany(
	            mappedBy = "order",
	            cascade = CascadeType.ALL,
	            orphanRemoval = true
	            )
//	 @OneToMany(
//	            mappedBy = "order")
	 @Fetch(FetchMode.JOIN)
	    private List<Items> items = new ArrayList<>();


	public List<Items> getItems() {
		return items;
	}
	public void setItems(List<Items> items) {
		this.items = items;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(Double total_amount) {
		this.total_amount = total_amount;
	}
	
}
