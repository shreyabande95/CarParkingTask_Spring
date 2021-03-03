package springmongodocker.controller;

import netscape.javascript.JSObject;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import springmongodocker.dto.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/cars")
public class ControllerClass {

    @Autowired
    DbClient dbClient;
    static String id;
    public static List<Boolean> slots;

    public ControllerClass(){
        slots=new ArrayList<Boolean>();
        for(int i=0;i<100;i++){
            slots.add(true);				//initially all slots are vacant
        }
    }

    public int assignSlot() {
        int vac = slots.indexOf(true);    // find first vacant slot
        slots.set(vac, false);
        return vac;
    }

    @RequestMapping(value="/post_car",method= RequestMethod.POST) // API to add new car
    public String newCar(@RequestBody Car car) throws JSONException {
        String status="";
          int slot=assignSlot();
        String regNo=car.registrationNo;
        String color=car.color;
        Car addcar=new Car(id,regNo,color,slot);
        status=dbClient.addCar(addcar);
        return status ;
    }


    @GetMapping("/search/registration_number/{color}") // API to get Registration Number of all cars of particular color
    public String retrieveRegNoByColor(@PathVariable String color) {
        String result="";
        result=dbClient.getRegNoByColor(color);
        return result;
    }

    @RequestMapping("/search/slot/registration_number/{regNo}") // API to get slot by Registration Number
    public String retrieveSlotByRegNo(@PathVariable String regNo){
        String result="";
        result=dbClient.getSlotByRegNo(regNo);
        return result;
    }

    @RequestMapping("/search/slot/color/{color}") // API to get slots of all cars of particular color
    public String retrieveSlotByColor(@PathVariable String color){
        String result="";
        result=dbClient.getSlotByColor(color);
        return result;
    }

    @DeleteMapping(value="/delete/{registrationNo}") // API to depark car
    public String delete(@RequestParam String regNo) throws JSONException{
        String result="";
        result=dbClient.delete(regNo);
        return result;
    }


}
