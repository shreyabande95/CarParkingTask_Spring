package spring.controller;

import spring.dto.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import spring.repository.CarRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/cars")

public class ControllerClass {

    @Autowired
        BaseClient bc;
    static int id;
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

    @RequestMapping(value="/post_car/{regNo}/{color}",method= RequestMethod.GET) // post
    public String newCar(@PathVariable String regNo, @PathVariable String color){
        String stats;
        int slot=assignSlot();
        id=Integer.parseInt(regNo);
        Car car=new Car(id,Integer.parseInt(regNo),color,slot);
         stats=bc.addCar(car);
        return stats ;
    }

    @GetMapping("/search/registration_number/{color}") // search
    public String retrieveRegNoByColor(@PathVariable String color) {
        String res="";
        res=bc.getRegNoByColor(color);
        return res;
    }

    @RequestMapping("/search/slot_by_regno/{regNo}") //search
    public String retrieveSlot(@PathVariable int regNo){
        String res="";
        res=bc.getSlotByRegNo(regNo);
       return res;
    }

   @RequestMapping("/search/slot/{color}")
    public String retrieveSlotByColor(@PathVariable String color){
        String res="";
       res=bc.getSlotByColor(color);
        return res;
    }

    @RequestMapping("/delete/{regNo}")
    public String retrieveSlotByColor(@PathVariable int regNo){
        String res="";
        res=bc.delete(regNo);
        return res;
    }


}
