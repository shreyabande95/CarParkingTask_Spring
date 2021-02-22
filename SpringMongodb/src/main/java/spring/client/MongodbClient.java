package spring.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import spring.controller.BaseClient;
import spring.dto.Car;
import spring.repository.CarRepository;

import java.util.List;


@Service
@ComponentScan("main.java.spring.repository")
public class MongodbClient extends BaseClient {

    @Autowired
    CarRepository carRepo;

    public String addCar(Car car){
        carRepo.save(car);
        return "Successfully added Car with Registration Number:"+car.registrationNo;
    }

    public String getRegNoByColor(String color) {
        String res="";
        List<Car> cars=carRepo.findAll();
         for(Car thisCar:cars){
             if(thisCar.color.equals(color)){
                 res+="Registration Number:"+ thisCar.getRegistrationNo()+" ";
             }
         }
         return res;
    }

    public String getSlotByRegNo(int regNo){
        String res="";
        List<Car> cars=carRepo.findAll();
        for(Car thisCar:cars){
            if(thisCar.registrationNo==regNo){
                res+="[Floor:"+ ((thisCar.allotedSlot/20)+1)+" Slot:"+((thisCar.allotedSlot%20)+1)+"] ";
            }
        }
        return res;
    }

    public String getSlotByColor(String color){
        String res="";
        List<Car> cars=carRepo.findAll();
        for(Car thisCar:cars){
            if(thisCar.color.equals(color)){
                res+="[Floor:"+ ((thisCar.allotedSlot/20)+1)+" Slot:"+((thisCar.allotedSlot%20)+1)+"] ";
            }
        }
        return res;
    }

    public String delete(int regNo){
        carRepo.deleteById(regNo);
        return "De-parked!";
    }

}
