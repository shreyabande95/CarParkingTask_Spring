package spring;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import spring.client.MongodbClient;
import spring.controller.ControllerClass;
import spring.dto.Car;
import spring.repository.CarRepository;

import javax.annotation.security.RunAs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
class SpringwithmongodbApplicationTests {

	@Autowired
	public MockMvc mvc;

	@Autowired
	public ControllerClass controllerClass;


	@Test
	public void addCarTest() throws Exception{
		mvc.perform(MockMvcRequestBuilders.get("/v1/cars/post_car/932/red")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().string("Successfully added Car with Registration Number:932"));

	}

	@Test
	public void getRegNoByColorTest() throws Exception{
		//mvc.perform(MockMvcRequestBuilders.get("/v1/cars/post_car/KA0932/red")).andReturn();
		mvc.perform(MockMvcRequestBuilders.get("/v1/cars/search/registration_number/red")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().string("Registration Number:2 Registration Number:3 Registration Number:4 Registration Number:932 "));
	}

	@Test
	public void getSlotByRegNoTest() throws Exception{
		mvc.perform(MockMvcRequestBuilders.get("/v1/cars/search/slot_by_regno/932")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().string("[Floor:1 Slot:1] "));
	}

	@Test
	public void getSlotByColorTest() throws Exception{
		mvc.perform(MockMvcRequestBuilders.get("/v1/cars/search/slot/red")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().string("[Floor:1 Slot:1] [Floor:1 Slot:1] [Floor:1 Slot:1] [Floor:1 Slot:1] "));
	}

	@Test
	public void deleteTest() throws Exception{
		mvc.perform(MockMvcRequestBuilders.get("/v1/cars/delete/9")).andExpect(MockMvcResultMatchers.status().isOk());
	}
	@Test
	void contextLoads() {
	}

}
