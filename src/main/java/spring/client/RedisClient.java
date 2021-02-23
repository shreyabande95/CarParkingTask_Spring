package spring.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import spring.controller.BaseClient;
import spring.dto.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
@Component
@Repository
//@Controller
//@RestController

@Primary
public class RedisClient extends BaseClient {
    @Autowired
    public static RedisTemplate<String, Car> redis;

    public static HashOperations hashOperations;
    public static List<Boolean> slots;
    static int count;
public RedisClient(){
return;
}
    public RedisClient(RedisTemplate<String,Car> redisTemplate){
    this();
        redis=redisTemplate;
        hashOperations=redis.opsForHash();
        System.out.println("redis constructor initialised");
        slots=new ArrayList<Boolean>();
        for(int i=0;i<100;i++){
            slots.add(true);				//initially all slots are vacant
        }
        count=1;
       // System.out.println("Slots initialised");
    }

    public int assignSlot() {
        int vac=slots.indexOf(true);	// find first vacant slot
        slots.set(vac,false);
        return vac;
    }

   // @RequestMapping(value="/post_car/{regNo}/{color}",method= RequestMethod.GET)
    public String addCar(Car car){
        System.out.println("inside redis add");
        hashOperations=redis.opsForHash();

        //int regNo= checkDuplicate(car.getRegistrationNo());
//        int slot=assignSlot();
//        int id=Integer.parseInt(regNo);
//        Car car=new Car(id,Integer.parseInt(regNo),color,slot);
        int floor= (car.getSlot()/20)+1;
        int slot2=(car.getSlot()%20)+1;
        hashOperations.put("parkedCars"+count,"id",car.getRegistrationNo());
        hashOperations.put("parkedCars"+count,"Registration Number",car.getRegistrationNo());
        hashOperations.put("parkedCars"+count,"Color",car.getColor());
        hashOperations.put("parkedCars"+count,"Floor",floor);
        hashOperations.put("parkedCars"+count,"Slot",slot2);
        count++;
        return "Successfully added Car with Registration Number:"+car.registrationNo;
    }
    @GetMapping("/search/registration_number/{color}")
    public String getRegNoByColor(@PathVariable String color) {
        String res = " ";
        for (int i = 1; i < count; i++) {
            Map<String, Car> parkedCars = hashOperations.entries("parkedCars" + i);
            for (Map.Entry<String, Car> entry : parkedCars.entrySet()) {
                String key = entry.getKey();
                String val = String.valueOf(entry.getValue());
                if (key.equals("Color") && (val.equals(color))) {
                    for (Map.Entry<String, Car> subentry : parkedCars.entrySet()) {
                        String subkey = subentry.getKey();
                        if (subkey.equals("Registration Number")) {
                            res = res + "[" + String.valueOf(subentry.getValue()) + "] ";
                        }
                    }
                }
            }

        }
        return res;
    }
    @RequestMapping("/search/slot_by_regno/{regNo}")
    public String getSlotByRegNo(@PathVariable int regNo) {
        String res = " ";
        for (int i = 1; i < count; i++) {
            Map<String, Car> parkedCars = hashOperations.entries("parkedCars" + i);
            for (Map.Entry<String, Car> entry : parkedCars.entrySet()) {
                String key = entry.getKey();
                String val = String.valueOf(entry.getValue());
                if(key.equals("Registration Number")&&(val.equals(String.valueOf(regNo)))){
                    for(Map.Entry<String, Car> subentry : parkedCars.entrySet()){
                        String subkey= subentry.getKey();
                        if(subkey.equals("Floor")){
                            res=res+"[Floor:"+String.valueOf(subentry.getValue())+" Slot:";
                            break;
                        }
                    }
                    for(Map.Entry<String, Car> subentry : parkedCars.entrySet()){
                        String subkey= subentry.getKey();
                        if(subkey.equals("Slot")){
                            res=res+""+subentry.getValue()+"] ";
                            break;
                        }
                    }
                    break;
                }

            }
        }
        return res;
    }

    @RequestMapping("/search/slot/{color}")
    public String getSlotByColor(@PathVariable String color) {
        String res=" ";
        for (int i = 1; i < count; i++) {
            Map<String, Car> parkedCars = hashOperations.entries("parkedCars"+i);
            for (Map.Entry<String, Car> entry : parkedCars.entrySet()){
                String key=entry.getKey();
                String val= String.valueOf(entry.getValue());
                if((key.equals("Color"))&&val.equals(color)){
                    String result=String.valueOf(entry);
                    for(Map.Entry<String, Car> subentry : parkedCars.entrySet()){
                        String subkey= subentry.getKey();
                        if(subkey.equals("Floor")){
                            res=res+"[Floor:"+String.valueOf(subentry.getValue())+" Slot:";
                            break;
                        }
                    }
                    for(Map.Entry<String, Car> subentry : parkedCars.entrySet()){
                        String subkey= subentry.getKey();
                        if(subkey.equals("Slot")){
                            res=res+""+subentry.getValue()+"] ";
                            break;
                        }
                    }

                }
            }

        }
        return res;
    }
}
