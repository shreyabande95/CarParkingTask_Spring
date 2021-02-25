package spring.controller;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.factory.annotation.Autowired;
import spring.client.MongodbClient;
import spring.dto.Car;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class DbClient {
    @Autowired
    MongodbClient mongodbClient;
    Object thisObject;

//    public DbClient() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException {
//        StringBuilder clientClass=getClient();
//        setClient(clientClass);
//    }
//
//    private void setClient(StringBuilder clientClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {
//        String client=clientClass.toString();
//        Class cls = Class.forName(client);
//        this.thisObject = cls.getDeclaredConstructor().newInstance();
//    }
//
//
//    public StringBuilder getClient() {
//        StringBuilder clientClass = null;
//        try {
//            InputStream input = DbClient.class.getClassLoader().getResourceAsStream("application.properties");
//            Properties properties = new Properties();
//            if (input == null) {
//                System.out.println("Sorry, unable to find client class");
//            } else
//                properties.load(input);
//            clientClass = new StringBuilder(properties.getProperty("client"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return clientClass;
//    }

    public void callBaseClass() throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
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

    public String getRegNoByColor( String color) {
        String res=((DbClient)thisObject).getRegNoByColor(color);
        return res;
    }

    public String getSlotByRegNo(int regNo ) {
        String res=((DbClient)thisObject).getSlotByRegNo(regNo);
        return res;
    }

    public String getSlotByColor(String color){
        String res=((DbClient)thisObject).getSlotByColor(color);
        return res;
    }

    public String delete(int regNo){
        String res=((DbClient)thisObject).delete(regNo);
        return res;
    }
}
