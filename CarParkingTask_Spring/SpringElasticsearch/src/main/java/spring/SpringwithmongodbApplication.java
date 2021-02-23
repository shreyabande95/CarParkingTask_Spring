package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.stereotype.Component;
import spring.controller.BaseClient;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@SpringBootApplication
@Component
@ComponentScan(basePackages = "spring.controller")
@EnableElasticsearchRepositories
public class SpringwithmongodbApplication {
//@Autowired
//static BaseClient bc;
	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException, IOException, NoSuchMethodException, ClassNotFoundException {
		BaseClient bc=new BaseClient();
		bc.getClientClass();
		SpringApplication.run(SpringwithmongodbApplication.class, args);
	}

}
