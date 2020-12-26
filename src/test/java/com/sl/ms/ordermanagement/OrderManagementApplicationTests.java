package com.sl.ms.ordermanagement;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sl.ms.ordermanagement.model.Orders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class OrderManagementApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Test
	public void testOrdersControl () throws Exception {
//		List<Orders> ords = new ArrayList();
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
		Orders ords = new ObjectMapper().readValue(jsonString, Orders.class);
		
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		mockMvc.perform(get("/orders")).andExpect(status().isOk())
		.andExpect(jsonPath("$.name").value("Veera2"));
	}
	
}
