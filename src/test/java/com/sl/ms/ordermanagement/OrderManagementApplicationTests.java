package com.sl.ms.ordermanagement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sl.ms.ordermanagement.model.Items;
import com.sl.ms.ordermanagement.model.Orders;
import com.sl.ms.ordermanagement.service.ItemService;
import com.sl.ms.ordermanagement.service.OrderService;


import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc(addFilters=false)

@ExtendWith(SpringExtension.class)
//@WebMvcTest(value = OrderController.class, secure = false)
class OrderManagementApplicationTestsContoller {

	@Test
	void contextLoads() {
	}
	
	
//	private WebApplicationContext webApplicationContext;
	@MockBean
	private OrderService orser;
	@MockBean
	private ItemService itmser;
	@Autowired
	private MockMvc mockMvc;
	 
	Orders Mockord;
	Orders Mockord1;
	List<Orders> Mockords;
	List<Items> Mockitms;
	Items Mockitm;
	//Default Constructor ************************************
	@BeforeEach
	void OrderManagementApplicationTests11() throws JsonMappingException, JsonProcessingException{
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
	/*
	private String token;
//	token = getToken();// ="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxNDQwNzNfVmVlcmEiLCJleHAiOjE2MDkwMTgwOTUsImlhdCI6MTYwOTAwMzY5NX0.Isi1dN3Avx1rR-ssuMxok2q3V63cB6aG3Sg0_oPsjQidewo8O3Q5LW6W2lHf3C5chVLg75UkNt_hLNYFWq47MA";
	
		
	@Test
	public void existentUserCanGetTokenAndAuthentication() throws Exception {
	    String username = "144073_Veera";
	    String password = "password";

	    String body = "{\"username\":\"" + username + "\", \"password\":\""
	                  + password + "\"}";

	    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/authenticate")
	    		.header("Content-Type","application/json")
	            .content(body))
//	    		.andDo(print())
	            .andExpect(status().isOk()).andReturn();

	    String response = result.getResponse().getContentAsString();
	    response = response.replace("{\"access_token\": \"", "");
	    this.token = response.replace("\"}", "");
	    System.out.println(token);
	    
//	    mockMvc.perform(MockMvcRequestBuilders.get("/")
//	        .header("Authorization", "Bearer " + token))
////	    .andDo(print())
//	        .andExpect(status().isOk());
	}
	*/

	@Test
	@DisplayName("Orders by ID")	
	public void testOrdersbyIdControl () throws Exception {

		doReturn(Mockord).when(orser).getById(Mockord.getId());
				
		 mockMvc.perform(MockMvcRequestBuilders.get("/order/{id}",2))
		.andExpect(status().isOk())
//		.andDo(print())
		.andExpect(jsonPath("$.id").value(2))
		;
		 
	}
	
	@Test
	@DisplayName("All Orders ")	
	public void testOrdersControl () throws Exception {
	
		doReturn(Mockords).when(orser).getAllOrders();

		 mockMvc.perform(MockMvcRequestBuilders.get("/orders"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].id").value(2))
//		.andDo(print())
		;
	}
	
	@Test
	@DisplayName("/items ")	
	public void testItemControl () throws Exception {
		
		doReturn(Mockitms).when(itmser).getAllItems();

		 mockMvc.perform(MockMvcRequestBuilders.get("/items"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].id").value(1))
//		.andDo(print())
		;
	}

	@Test
	@DisplayName("/items/ID")	
	public void testItemIDControl () throws Exception {
		
		doReturn(Mockitm).when(itmser).getById(1);

		 mockMvc.perform(MockMvcRequestBuilders.get("/items/{id}",1))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(1))
//		.andDo(print())
		;
	}
	
	@Test
	@DisplayName("Order Delete")	
	public void testOrderDeleteControl () throws Exception {

		doReturn(Mockord).when(orser).getById(Mockord.getId());

		 mockMvc.perform(MockMvcRequestBuilders.delete("/order/{id}",Mockord.getId()))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(Mockord.getId()))
//		.andDo(print())
		;
	}
	
/*
 * 	
 
	@Test
	@DisplayName("/Order Save")	
	public void testOrderSaveControl () throws Exception {

		Orders Mockord = new ObjectMapper().readValue(jsonString, Orders.class);
		List<Items> Mockitms = new ArrayList<Items>();

		Mockitms.addAll(Mockord.getItems());
		Items Mockitm = Mockitms.get(0);
		
//		doReturn(Mockord).when(orser).save(Mockord);

		 mockMvc.perform(MockMvcRequestBuilders.post("/order")
				 .contentType(MediaType.APPLICATION_JSON_VALUE)
				 .content(new ObjectMapper().writeValueAsString(Mockord)))
		 .andDo(print())
//		 .andExpect(status().isCreated())
//		.andExpect(jsonPath("$.id").value(2))
//		.andDo(print())
		;
	}
*/	
//*******************************All Test Items*************************************************
	@Test
	@DisplayName("/test Order Save")	
	public void testOrderSaveControl () throws Exception {

		 mockMvc.perform(MockMvcRequestBuilders.post("/test")
				 .contentType(MediaType.APPLICATION_JSON_VALUE)
				 .content(new ObjectMapper().writeValueAsString(Mockord1)))
//		 .andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(Mockord1.getId()))
		;
	}
	
	@Test
	@DisplayName("/test Item Save")	
	public void testitemSaveControl () throws Exception {

		 mockMvc.perform(MockMvcRequestBuilders.post("/order/items")
				 .contentType(MediaType.APPLICATION_JSON_VALUE)
				 .content(new ObjectMapper().writeValueAsString(Mockitm)))
//		 .andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(Mockitm.getId()))
		;
	}
	@Test
	@DisplayName("/Veera")	
	public void testrootControl () throws Exception {
		
//		doReturn(Mockitm).when(itmser).getById(1);

		 mockMvc.perform(MockMvcRequestBuilders.get("/"))
		.andExpect(status().isOk())
		.andExpect(content().string("Veera"))
//		.andDo(print())
		;
	}
	
	
	@Test
	@DisplayName("Test /items/ID")	
	public void testItemListControl () throws Exception {
		
		doReturn(Mockitms).when(itmser).getAllItems();

		 mockMvc.perform(MockMvcRequestBuilders.get("/test/items"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(Mockitms.get(0).getId()))
		.andDo(print())
		;
	}
}

