package spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import spring.client.MongodbClient;
import spring.dto.Car;
import spring.repository.CarRepository;

import javax.annotation.security.RunAs;
import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunAs("Spring")
@SpringBootTest
class SpringwithmongodbApplicationTests {
	@Autowired
	MongodbClient mongodb;

	@MockBean
	CarRepository carRepo;
	@Test
	public void addCarTest(){
		Car car=new Car(1,1,"blue",0);
		when(carRepo.save(car)).thenReturn(car);
		assertEquals("Successfully added Car with Registration Number:1",mongodb.addCar(car));

	}
	@Test
	public void getSlotByColorTest(){
		Car car=new Car(2,2,"red",0);
		when(carRepo.findById(2)).thenReturn(java.util.Optional.of(car));
		assertEquals(0,car.allotedSlot);
	}
	@Test
	public void getSlotByRegNoTest(){
		Car car=new Car(2,2,"red",0);
		when(carRepo.findById(2)).thenReturn(java.util.Optional.of(car));
		assertEquals(0,car.allotedSlot);
	}

	@Test
	public void getRegNoByColorTest(){
		Car car=new Car(2,2,"red",0);
		when(carRepo.findById(2)).thenReturn(java.util.Optional.of(car));
		assertEquals(2,car.registrationNo);
	}
	@Test
	void contextLoads() {
	}

}
