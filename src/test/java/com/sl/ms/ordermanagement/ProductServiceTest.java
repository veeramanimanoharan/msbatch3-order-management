package com.sl.ms.ordermanagement;

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
import com.github.tomakehurst.wiremock.WireMockServer;
import com.sl.ms.ordermanagement.model.Items;
import com.sl.ms.ordermanagement.model.Orders;
import com.sl.ms.ordermanagement.service.ItemService;
import com.sl.ms.ordermanagement.service.OrderService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

@SpringBootTest
@AutoConfigureMockMvc(addFilters=false)
@ExtendWith(SpringExtension.class)
public class ProductServiceTest {
//	http://localhost:7777/dev//checkproductavail/1
	 private static final String PATH = "/dev/checkproductavail/";
    

    private WireMockServer wireMockServer = new WireMockServer(7777);
    private CloseableHttpClient httpClient = HttpClients.createDefault();
    
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
    
	 
    @BeforeEach
    public void init() throws JsonMappingException, JsonProcessingException {
    
    	
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
    
    	 
     	wireMockServer.start();
    }
    
    @AfterEach
    public void stop() {
    	wireMockServer.stop();
    }
    
    
    @Test
	@DisplayName("/CheckProduct/{id} TRUE")	
	public void testCheckProductTrue () throws Exception {
    	
    	configureFor("localhost", 7777);
   	 stubFor(get(urlEqualTo(PATH+"1")).willReturn(aResponse().withBody("true")));
   	stubFor(get(urlEqualTo(PATH+"2")).willReturn(aResponse().withBody("false")));
    	mockMvc.perform(MockMvcRequestBuilders.get("/CheckProduct/1")
				 .contentType(MediaType.APPLICATION_JSON_VALUE)
				 .content(new ObjectMapper().writeValueAsString(Mockord1)))
//		 .andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string("true"))
		;
    	
    	
    }
    
    @Test
	@DisplayName("/CheckProduct/{id} False")	
	public void testCheckProductFalse () throws Exception {
    	
    	configureFor("localhost", 7777);
   	 stubFor(get(urlEqualTo(PATH+"1")).willReturn(aResponse().withBody("true")));
   	stubFor(get(urlEqualTo(PATH+"2")).willReturn(aResponse().withBody("false")));
    	mockMvc.perform(MockMvcRequestBuilders.get("/CheckProduct/2")
				 .contentType(MediaType.APPLICATION_JSON_VALUE)
				 .content(new ObjectMapper().writeValueAsString(Mockord1)))
//		 .andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string("false"))
		;
    	
    	
    }
    
    @Test
	@DisplayName("/Order Save")	
	public void testOrderSaveControl () throws Exception {
    	
       	configureFor("localhost", 7777);
    	stubFor(get(urlEqualTo(PATH+"1")).willReturn(aResponse().withBody("true")));
    	stubFor(get(urlEqualTo(PATH+"2")).willReturn(aResponse().withBody("true")));
    	stubFor(get(urlEqualTo(PATH+"3")).willReturn(aResponse().withBody("true")));
    	stubFor(get(urlEqualTo(PATH+"4")).willReturn(aResponse().withBody("true")));
    	stubFor(get(urlEqualTo(PATH+"5")).willReturn(aResponse().withBody("true")));
    	stubFor(get(urlEqualTo(PATH+"6")).willReturn(aResponse().withBody("true")));

		 mockMvc.perform(MockMvcRequestBuilders.post("/order")
				 .contentType(MediaType.APPLICATION_JSON_VALUE)
				 .content(new ObjectMapper().writeValueAsString(Mockord)))
//		 .andDo(print())
		 .andExpect(content().string("Order Posted Succesully"))
		 ;
    }


    @Test
	@DisplayName("/Order Save Fail")	
	public void testOrderSaveControlFail () throws Exception {
    	
       	configureFor("localhost", 7777);
    	stubFor(get(urlEqualTo(PATH+"1")).willReturn(aResponse().withBody("true")));
    	stubFor(get(urlEqualTo(PATH+"2")).willReturn(aResponse().withBody("false")));
    	stubFor(get(urlEqualTo(PATH+"3")).willReturn(aResponse().withBody("true")));
    	stubFor(get(urlEqualTo(PATH+"4")).willReturn(aResponse().withBody("true")));
    	stubFor(get(urlEqualTo(PATH+"5")).willReturn(aResponse().withBody("true")));
    	stubFor(get(urlEqualTo(PATH+"6")).willReturn(aResponse().withBody("true")));

		 mockMvc.perform(MockMvcRequestBuilders.post("/order")
				 .contentType(MediaType.APPLICATION_JSON_VALUE)
				 .content(new ObjectMapper().writeValueAsString(Mockord)))
//		 .andDo(print())
		 .andReturn()
		    .getResponse()
		    .getContentAsString()
		    .contains("not available to book. Order cannot be placed");
		 ; //content().string(" not available to book. Order cannot be placed")
    }
    
    
    
    private static String convertResponseToString(HttpResponse response) throws IOException {
 

        InputStream responseStream = response.getEntity().getContent();
        Scanner scanner = new Scanner(responseStream, "UTF-8");
        String stringResponse = scanner.useDelimiter("\\Z").next();
        scanner.close();
        return stringResponse;
    }
    
}
