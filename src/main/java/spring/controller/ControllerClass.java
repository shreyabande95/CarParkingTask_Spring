package spring.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import spring.dto.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import spring.repository.CarRepository;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/cars")
public class ControllerClass {


    static int id;
    public static List<Boolean> slots;
Object obj;
    public ControllerClass() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        System.out.println("in cons");

        slots=new ArrayList<Boolean>();
        for(int i=0;i<100;i++){
            slots.add(true);				//initially all slots are vacant
        }
        this.obj=obj;
        System.out.println(obj);
       // bc.callBaseClass();
   }


    public int assignSlot() {
        int vac = slots.indexOf(true);    // find first vacant slot
        slots.set(vac, false);
        return vac;
    }

    @GetMapping(value="/post_car/{regNo}/{color}") // post
    public String newCar(@PathVariable String regNo, @PathVariable String color) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        System.out.println("controller add");
        String stats;
        int slot=assignSlot();
        id=Integer.parseInt(regNo);
        Car car=new Car(id,Integer.parseInt(regNo),color,slot);
        BaseClient bc=new BaseClient();
         stats=bc.addCar(car);
        return stats ;
    }

    @GetMapping("/search/registration_number/{color}") // search
    public String retrieveRegNoByColor(@PathVariable String color) {
        String res="";
        BaseClient bc=new BaseClient();
        res=bc.getRegNoByColor(color);
        return res;
    }
    @RequestMapping("/test")
    public String test(){
        return "tested!";
    }
    @RequestMapping("/search/slot_by_regno/{regNo}") //search
    public String retrieveSlot(@PathVariable int regNo){
        String res="";
        BaseClient bc=new BaseClient();
        res=bc.getSlotByRegNo(regNo);
       return res;
    }

   @RequestMapping("/search/slot/{color}")
    public String retrieveSlotByColor(@PathVariable String color){
        String res="";
       BaseClient bc=new BaseClient();
       res=bc.getSlotByColor(color);
        return res;
    }

    @RequestMapping("/delete/{regNo}")
    public String retrieveSlotByColor(@PathVariable int regNo){
        String res="";
        BaseClient bc=new BaseClient();
        res=bc.delete(regNo);
        return res;
    }


}
