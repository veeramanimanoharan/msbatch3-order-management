package com.sl.ms.ordermanagement;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class TestsJWT {

	
	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	public void existentUserCanGetTokenAndAuthentication() throws Exception {
		System.out.println("JWT");
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

	    assertTrue(response.contains("token"));

	}

	
	@Test
	public void unAuthentication() throws Exception {
		
		 mockMvc.perform(MockMvcRequestBuilders.get("/"))
			.andExpect(status().isUnauthorized())
//			.andDo(print())
			;

	}
	
}
