package com.sl.ms.ordermanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.sl.ms.ordermanagement.model.Items;
import com.sl.ms.ordermanagement.model.Orders;
import com.sl.ms.ordermanagement.repository.OrderRepository;
import com.sl.ms.ordermanagement.service.ItemService;
import com.sl.ms.ordermanagement.service.OrderService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
@ActiveProfiles("test") 
public class ProductServiceTest {
//	http://localhost:7777/dev//checkproductavail/1
	 private static final String PATH = "/dev/checkproductavail/";
    

    private WireMockServer wireMockServer = new WireMockServer(7777);
//    private CloseableHttpClient httpClient = HttpClients.createDefault();

    @Autowired
	private OrderService ordSer;
	
	@MockBean
	private OrderRepository ordRepo;
	
    
	@MockBean
	private ItemService itmser;
//	@Autowired
//	private MockMvc mockMvc;
	 
	Orders Mockord;
	Orders Mockord1;
	List<Orders> Mockords;
	List<Items> Mockitms;
	Items Mockitm;
    
	 
    @BeforeEach
    public void init() throws IOException {
    
    	
    		OrderItemData data =new OrderItemData();
    		String jsonString = data.jsonString2;
    		String jsonString1 = data.jsonString1;
    	Mockord = new ObjectMapper().readValue(jsonString, Orders.class);
    	Mockord1 = new ObjectMapper().readValue(jsonString1, Orders.class);
    	
    	Mockords = new ArrayList<Orders>();
    	Mockords.add(Mockord);
    	Mockords.add(Mockord1);
//    	System.out.println(jsonString);
    	
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
	@DisplayName("CheckProduct")	
	public void testCheckProduct()  {
    	
    	configureFor("localhost", 7777);
    	stubFor(get(urlEqualTo(PATH+"1")).willReturn(aResponse().withBody("true")));
    	stubFor(get(urlEqualTo(PATH+"2")).willReturn(aResponse().withBody("false")));     	
    	
    	String result = ordSer.CheckProduct("1");
    	assertEquals("true",result);
    	
      	String result1 = ordSer.CheckProduct("2");
    	assertEquals("false",result1);
    }
   

    
}
