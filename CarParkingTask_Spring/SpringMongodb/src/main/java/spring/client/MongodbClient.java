package spring.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import spring.controller.DbClient;
import spring.dto.Car;
import spring.repository.CarRepository;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@Service
@ComponentScan("main.java.spring.repository")
public class MongodbClient extends DbClient {

    @Autowired
    CarRepository carRepo;

    public MongodbClient() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException {

    }

    public int checkDuplicate(int regNo){
        List<Car> cars=carRepo.findAll();
        if (cars==null)
                return regNo;
        Scanner sc=new Scanner(System.in);
        ArrayList<Integer> allRegNos=new ArrayList<Integer>();
        for(Car thisCar:cars){
            allRegNos.add(thisCar.registrationNo);
        }
        boolean duplicate=allRegNos.contains(regNo);
        while(duplicate){
            System.out.println("Duplicate Registration Number found!Enter new one");
            regNo=sc.nextInt();
        }
            return regNo;
    }
    public String addCar(Car car){
        int registrationNo=checkDuplicate(car.registrationNo);
        car.registrationNo=registrationNo;
        carRepo.save(car);
        return "Successfully added Car with Registration Number:"+car.registrationNo;
    }

    public String getRegNoByColor(String color) {
        String result="";
        List<Car> cars=carRepo.findAll();
        for(Car thisCar:cars){
             if(thisCar.color.equals(color)){
                 result+="Registration Number:"+ thisCar.getRegistrationNo()+" ";
             }
        }
         return result;
    }

    public String getSlotByRegNo(int regNo){
        String result="";
        List<Car> cars=carRepo.findAll();
        for(Car thisCar:cars){
            if(thisCar.registrationNo==regNo){
                result+="[Floor:"+ ((thisCar.allotedSlot/20)+1)+" Slot:"+((thisCar.allotedSlot%20)+1)+"] ";
            }
        }
        return result;
    }

    public String getSlotByColor(String color){
        String result="";
        List<Car> cars=carRepo.findAll();
        for(Car thisCar:cars){
            if(thisCar.color.equals(color)){
                result+="[Floor:"+ ((thisCar.allotedSlot/20)+1)+" Slot:"+((thisCar.allotedSlot%20)+1)+"] ";
            }
        }
        return result;
    }

    public String delete(int regNo){
        carRepo.deleteById(regNo);
        return "De-parked!";
    }

}
