package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import spring.client.MongodbClient;
import spring.dto.Car;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class BaseClient {
    @Autowired
    MongodbClient mc;
    Object thisObject;

    public void callBaseClass() throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        String clientClass="";
        try {
            InputStream input = BaseClient.class.getClassLoader().getResourceAsStream("application.properties");
            Properties prop = new Properties();
            if (input == null) {
                System.out.println("Sorry, unable to find client class");
                return;
            }
            prop.load(input);
            StringBuilder calledClass = new StringBuilder(prop.getProperty("client"));
            clientClass=calledClass.toString();
            Class cls = Class.forName(clientClass);
            this.thisObject = cls.getDeclaredConstructor().newInstance();
        }catch(Exception e) {
            e.printStackTrace();
        }

    }

    public String addCar(Car car) {
        String res= ((BaseClient)thisObject).addCar(car);
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
