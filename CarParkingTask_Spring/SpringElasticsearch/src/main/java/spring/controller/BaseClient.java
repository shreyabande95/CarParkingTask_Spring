package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import spring.client.ESClient;
import spring.client.MongodbClient;
import spring.dto.Car;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
@Component
@Repository
@EnableElasticsearchRepositories
@ComponentScan(basePackages = "spring.client")
public class BaseClient {

    public static Object thisObject;
    public String clientClass="";

    public void getClientClass() throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IOException {
        InputStream input = BaseClient.class.getClassLoader().getResourceAsStream("application.properties");
        Properties prop = new Properties();
        if (input == null) {
            System.out.println("Sorry, unable to find client class");
        }
        prop.load(input);
        StringBuilder calledClass = new StringBuilder(prop.getProperty("client"));
        clientClass=calledClass.toString();
        Class cls = Class.forName(clientClass);
        thisObject = cls.getDeclaredConstructor().newInstance();
        System.out.println(thisObject);
    }

    public String addCar(Car car) throws IllegalAccessException, InvocationTargetException, InstantiationException, IOException, NoSuchMethodException, ClassNotFoundException {
        System.out.println("thisobj:");
        System.out.println(thisObject);
        String res= ((BaseClient)thisObject).addCar(car);
        return "";
    }

    public  String getRegNoByColor( String color) {
        String res=((BaseClient)thisObject).getRegNoByColor(color);
        return res;
    }

    public  String getSlotByRegNo(int regNo ) {
        String res=((BaseClient)thisObject).getSlotByRegNo(regNo);
        return res;
    }

    public String getSlotByColor(String color){
        String res=((BaseClient)thisObject).getSlotByColor(color);
        return res;
    }

    public String delete(int regNo){
        String res=((BaseClient)thisObject).delete(regNo);
        return res;
    }
}
