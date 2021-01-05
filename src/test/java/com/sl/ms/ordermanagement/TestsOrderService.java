package com.sl.ms.ordermanagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sl.ms.ordermanagement.model.Items;
import com.sl.ms.ordermanagement.model.Orders;
import com.sl.ms.ordermanagement.repository.OrderRepository;
import com.sl.ms.ordermanagement.service.OrderService;

import javassist.NotFoundException;

@SpringBootTest
@AutoConfigureMockMvc(addFilters=false)
@ExtendWith(SpringExtension.class)
public class TestsOrderService {
	
	@Autowired
	private OrderService ordSer;
	
	@MockBean
	private OrderRepository ordRepo;
	
	Orders Mockord;
	Orders Mockord1;
	List<Orders> Mockords;
	List<Items> Mockitms;
	Items Mockitm;
	//Default Constructor ************************************
	@BeforeEach
	void OrderManagementApplicationTests11() throws IOException{
		OrderItemData data =new OrderItemData();
		String jsonString = data.jsonString2;
		String jsonString1 = data.jsonString1;
	Mockord = new ObjectMapper().readValue(jsonString, Orders.class);
	Mockord1 = new ObjectMapper().readValue(jsonString1, Orders.class);
	
	Mockords = new ArrayList<Orders>();
	Mockords.add(Mockord);
	Mockords.add(Mockord1);
	
	
	Mockitms = new ArrayList<Items>();

	Mockitms.addAll(Mockord.getItems());
	Mockitms.addAll(Mockord1.getItems());
	
	Mockitm = Mockitms.get(0);
	}
	
	@Test
	@DisplayName("Orders by ID")	
	public void testOrderbyIdService() throws NotFoundException {
		
//		doReturn( Mockord).when(ordRepo).findById(2);
		when(ordRepo.findById(Mockord.getId())).thenReturn(Optional.of(Mockord));
		
		Orders ordfound = ordSer.getById(Mockord.getId());
		
		assertNotNull(ordfound);
		assertEquals(ordfound.getId(), Mockord.getId());
		
	}


	@Test
	@DisplayName("Orders All")	
	public void testAllOrdersService() {
		
		doReturn(Mockords).when(ordRepo).findAll();
		
		List<Orders> ordsfound = ordSer.getAllOrders();
		
		assertNotNull(ordsfound);
		assertEquals(ordsfound.get(0).getId(), Mockord.getId());
		assertEquals(ordsfound.get(0).getName(), Mockord.getName());
		assertEquals(ordsfound.get(0).getTotal_amount(), Mockord.getTotal_amount());
		assertEquals(ordsfound.get(0).getItems().get(0).getName(), Mockord.getItems().get(0).getName());
		
//		
	}
	
	@Test
	@DisplayName("Orders Save")	
	public void testOrdersSaveService() {
		
		doReturn(Mockord).when(ordRepo).save(Mockord);
		
		ordSer.save(Mockord);
//		
//		Orders ordfound = ordSer.getById(Mockord.getId());
//		
//		assertNotNull(ordfound);
//		assertEquals(ordfound.getId(), Mockord.getId());
//		
//		assertNotNull(ordsfound);
//		assertEquals(ordsfound.get(0).getId(), Mockord.getId());
//		
	}
	

	@Test
	@DisplayName("Orders Delete")	
	public void testOrdersDeleteService() {
		
//		doReturn(Mockord).when(ordRepo).deleteById(Mockord.getId());
		
		ordSer.delete(Mockord.getId());
		
//		Orders ordfound = ordSer.getById(Mockord.getId());
//		assertNull(ordfound);
//		assertEquals(ordsfound.get(0).getId(), Mockord.getId());
//		
	}
}
