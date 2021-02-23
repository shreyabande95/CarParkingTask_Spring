package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import spring.client.MongodbClient;
import spring.client.RedisClient;
import spring.dto.Car;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
//@ComponentScan("spring.client")
//@Service
//@Component
@Component
public class BaseClient {
//    @Autowired
//    MongodbClient mc;

//   @Autowired
//    RedisClient rc;
String clientClass;
    Object thisObject;


    public void callBaseClass() throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        System.out.println("called");
        //String clientClass="";
        try {
            InputStream input = BaseClient.class.getClassLoader().getResourceAsStream("application.properties");
            Properties prop = new Properties();
            if (input == null) {
                System.out.println("Sorry, unable to find client class");
                return;
            }
            prop.load(input);
            StringBuilder calledClass = new StringBuilder(prop.getProperty("client"));
            System.out.println(calledClass);
            this.clientClass=calledClass.toString();
            System.out.println(clientClass);
            Class cls = Class.forName(clientClass);
            thisObject = cls.getDeclaredConstructor().newInstance();
            System.out.println(thisObject);
        }catch(Exception e) {
            e.printStackTrace();
        }

    }

    public String addCar(Car car) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        System.out.println("in base add");
        String res="";
        try {
            InputStream input = BaseClient.class.getClassLoader().getResourceAsStream("application.properties");
            Properties prop = new Properties();
            if (input == null) {
                System.out.println("Sorry, unable to find client class");
                return "";
            }
            prop.load(input);
            StringBuilder calledClass = new StringBuilder(prop.getProperty("client"));
            System.out.println(calledClass);
            this.clientClass=calledClass.toString();
        System.out.println(clientClass);
        Class cls = Class.forName(clientClass);
        thisObject = cls.getDeclaredConstructor().newInstance();
        res= ((BaseClient)thisObject).addCar(car);

    } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
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
