package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.stereotype.Component;
import spring.client.RedisClient;
import spring.controller.BaseClient;
import spring.controller.ControllerClass;
import spring.dto.Car;
import spring.repository.CarRepository;

import java.lang.reflect.InvocationTargetException;

@SpringBootApplication


public class SpringwithmongodbApplication {
	@Bean
	JedisConnectionFactory jedisConnection(){
		return new JedisConnectionFactory();
	}
//@Autowired
//	BaseClient bc;
	@Bean
	RedisTemplate<String, Car> redisTemplate(){
		RedisTemplate<String, Car> redisTemplate=new RedisTemplate<String, Car>();
		redisTemplate.setConnectionFactory(jedisConnection());
		return redisTemplate;
	}
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		//ControllerClass cc=new ControllerClass();
BaseClient bc=new BaseClient();
bc.callBaseClass();
		SpringApplication.run(SpringwithmongodbApplication.class, args);
	}

}
