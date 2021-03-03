package springmongodocker;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import springmongodocker.controller.ControllerClass;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
class ApplicationTests {

	@Autowired
	public  MockMvc mvc;

	@Autowired
	public  ControllerClass controllerClass;

//
//	@Test
//	public void getRegNoByColorTest() throws Exception{
//		mvc.perform(MockMvcRequestBuilders.get("/v1/cars/search/registration_number/yellow")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().string("Registration Number:8888 Registration Number:1010 "));
//	}
//
//	@Test
//	public void getSlotByRegNoTest() throws Exception{
//		mvc.perform(MockMvcRequestBuilders.get("/v1/cars/search/slot/registration_number/8888")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().string("[Floor:1 Slot:1] "));
//	}
//
//	@Test
//	public void getSlotByColorTest() throws Exception{
//		mvc.perform(MockMvcRequestBuilders.get("/v1/cars/search/slot/color/yellow")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().string("[Floor:1 Slot:1] [Floor:1 Slot:1] "));
//	}

	@Test
	void contextLoads() {
	}

}
