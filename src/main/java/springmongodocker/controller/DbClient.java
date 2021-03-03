package springmongodocker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import springmongodocker.client.MongodbClient;
import springmongodocker.dto.Car;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Properties;

public class DbClient {

    @Autowired
    MongodbClient mongodbClient;
    Object thisObject;

    //Reads client name from application.properties file
    public void getClientClass() throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        String clientClass="";
        try {
            InputStream input = DbClient.class.getClassLoader().getResourceAsStream("application.properties");
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
        String res= ((DbClient)thisObject).addCar(car);
        return res;
    }

    public  String getRegNoByColor( String color) {
        String res=((DbClient)thisObject).getRegNoByColor(color);
        return res;
    }

    public  String getSlotByRegNo(String regNo ) {
        String res=((DbClient)thisObject).getSlotByRegNo(regNo);
        return res;
    }

    public String getSlotByColor(String color){
        String res=((DbClient)thisObject).getSlotByColor(color);
        return res;
    }

    public String delete(String regNo){
        String res=((DbClient)thisObject).delete(regNo);
        return res;
    }

}
