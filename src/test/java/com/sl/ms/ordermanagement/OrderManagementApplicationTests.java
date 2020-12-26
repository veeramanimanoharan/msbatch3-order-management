package com.sl.ms.ordermanagement;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sl.ms.ordermanagement.controller.OrderController;
import com.sl.ms.ordermanagement.model.Items;
import com.sl.ms.ordermanagement.model.Orders;
import com.sl.ms.ordermanagement.service.ItemService;
import com.sl.ms.ordermanagement.service.OrderService;


import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
class OrderManagementApplicationTests {

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
	
	String jsonString = "{\r\n"
			+ "    \"id\": 2,\r\n"
			+ "    \"name\": \"Veera2\",\r\n"
			+ "    \"total_amount\": 100.0,\r\n"
			+ "    \"items\": [\r\n"
			+ "        {\r\n"
			+ "            \"id\": 1,\r\n"
			+ "            \"name\": \"Item1\",\r\n"
			+ "            \"quantity\": \"1\",\r\n"
			+ "            \"price\": \"10\",\r\n"
			+ "            \"amount\": \"100\"\r\n"
			+ "        },\r\n"
			+ "        {\r\n"
			+ "            \"id\": 2,\r\n"
			+ "            \"name\": \"Item2\",\r\n"
			+ "            \"quantity\": \"1\",\r\n"
			+ "            \"price\": \"10\",\r\n"
			+ "            \"amount\": \"100\"\r\n"
			+ "        },\r\n"
			+ "        {\r\n"
			+ "            \"id\": 3,\r\n"
			+ "            \"name\": \"Item3\",\r\n"
			+ "            \"quantity\": \"1\",\r\n"
			+ "            \"price\": \"10\",\r\n"
			+ "            \"amount\": \"100\"\r\n"
			+ "        },\r\n"
			+ "        {\r\n"
			+ "            \"id\": 4,\r\n"
			+ "            \"name\": \"Item4\",\r\n"
			+ "            \"quantity\": \"1\",\r\n"
			+ "            \"price\": \"10\",\r\n"
			+ "            \"amount\": \"100\"\r\n"
			+ "        },\r\n"
			+ "        {\r\n"
			+ "            \"id\": 5,\r\n"
			+ "            \"name\": \"Item5\",\r\n"
			+ "            \"quantity\": \"1\",\r\n"
			+ "            \"price\": \"10\",\r\n"
			+ "            \"amount\": \"100\"\r\n"
			+ "        }\r\n"
			+ "    ]\r\n"
			+ "}";
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
//		List<Orders> ords = new ArrayList();

		Orders Mockord = new ObjectMapper().readValue(jsonString, Orders.class);
//		Orders Mockord = new Orders();
//		Mockord.setId(2);
//		Mockord.setName("mockOrder");
//		Mockord.setTotal_amount((double) 20);
		
//		System.out.println("vveera"+this.token);
//		doReturn(Mockord).when(orser).getAllOrders();
		doReturn(Mockord).when(orser).getById(Mockord.getId());
		
		
		 mockMvc.perform(MockMvcRequestBuilders.get("/order/{id}",2))
//		 .header("Authorization", "Bearer " + this.token))
		.andExpect(status().isOk())
//		.andDo(print())
		.andExpect(jsonPath("$.id").value(2))
		;
		 
		
		
//		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//		System.out.println(mockMvc.perform(get("/orders")).);
//		mockMvc.perform(get("/orders")).andExpect(status().isOk())
//		.andExpect(jsonPath("$.name").value("Veera2"));
		
		
		
//		mockMvc.perform(get("/orders"))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$.name").value("Veera2")
//						);
		
	}
	
	@Test
	@DisplayName("All Orders ")	
	public void testOrdersControl () throws Exception {

		Orders Mockord = new ObjectMapper().readValue(jsonString, Orders.class);
		List<Orders> Mockords = new ArrayList<Orders>();
		Mockords.add(Mockord);
		
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

		Orders Mockord = new ObjectMapper().readValue(jsonString, Orders.class);
		List<Items> Mockitms = new ArrayList<Items>();

		Mockitms.addAll(Mockord.getItems());
		
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

		Orders Mockord = new ObjectMapper().readValue(jsonString, Orders.class);
		List<Items> Mockitms = new ArrayList<Items>();

		Mockitms.addAll(Mockord.getItems());
		Items Mockitm = Mockitms.get(0);
		doReturn(Mockitm).when(itmser).getById(1);

		 mockMvc.perform(MockMvcRequestBuilders.get("/items/{id}",1))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(1))
		.andDo(print())
		;
	}
	
}
