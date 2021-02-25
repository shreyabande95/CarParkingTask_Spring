package spring.controller;

import spring.dto.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/cars")

public class ControllerClass {

    @Autowired
    DbClient dbClient;

    static int id;
    public static List<Boolean> slots;

    public ControllerClass() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        dbClient=new DbClient();
        slots=new ArrayList<Boolean>();
        for(int i=0;i<100;i++){
            slots.add(true);				//initially all slots are vacant
        }
   }

    public int assignSlot() {
        int vacant = slots.indexOf(true);    // find first vacant slot
        slots.set(vacant, false);
        return vacant;
    }

    @RequestMapping(value="/post_car/{regNo}/{color}",method= RequestMethod.GET)
   // @PostMapping( value = "/post_car/{regNo}/{color}")
    //public String newCar(@RequestParam(value = "registrationNo") String regNo, @RequestParam(value="color") String color){
    public String newCar(@PathVariable String regNo,@PathVariable String color){
        String status;
        int slot=assignSlot();
        id=Integer.parseInt(regNo);
        Car car=new Car(id,Integer.parseInt(regNo),color,slot);
         status=dbClient.addCar(car);
        return status ;
    }

    @GetMapping("/search/registration_number/{color}") // search
    public String retrieveRegNoByColor(@PathVariable String color) {
        String result="";
        result=dbClient.getRegNoByColor(color);
        return result;
    }

    @RequestMapping("/search/slot_by_regno/{regNo}") //search
    public String retrieveSlot(@PathVariable int regNo){
        String result="";
        result=dbClient.getSlotByRegNo(regNo);
       return result;
    }

   @RequestMapping("/search/slot/{color}")
    public String retrieveSlotByColor(@PathVariable String color){
        String result="";
       result=dbClient.getSlotByColor(color);
        return result;
    }

    @RequestMapping("/delete/{regNo}")
    public String retrieveSlotByColor(@PathVariable int regNo){
        String result="";
        result=dbClient.delete(regNo);
        return result;
    }


}
