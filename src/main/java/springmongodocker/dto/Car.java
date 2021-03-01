package springmongodocker.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
@Document(collection = "parkedcars")
public class Car implements Serializable {

    public String id;
    public String registrationNo;
    public String color;
    public int allotedSlot;

    public Car(String id,String registrationNo,String color,int allotedSlot) { // constructor initialization
        this.id=id;
        this.registrationNo=registrationNo;
        this.color=color;
        this.allotedSlot=allotedSlot;
    }

    @Id
    public String getId() {
        return this.id;
    }

    public String getRegistrationNo(){
        return this.registrationNo;
    }

    public String getColor(){
        return this.color;
    }

    public int getSlot(){
        return this.allotedSlot;
    }
}
