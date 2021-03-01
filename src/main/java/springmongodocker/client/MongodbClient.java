package springmongodocker.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import springmongodocker.controller.DbClient;
import springmongodocker.dto.Car;
import springmongodocker.repository.CarRepository;

import java.util.List;


@Service
@ComponentScan("main.java.spring.repository")
public class MongodbClient extends DbClient {

    @Autowired
    CarRepository carRepo;

    public String addCar(Car car){
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

    public String getSlotByRegNo(String regNo){
        String result="";
        List<Car> cars=carRepo.findAll();
        for(Car thisCar:cars){
            if(thisCar.registrationNo.equals(regNo)){
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

    public String delete(String regNo){
        carRepo.deleteById(Integer.parseInt(regNo));
        return "De-parked!";
    }

}
