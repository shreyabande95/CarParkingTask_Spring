package springmongodocker.repository;

import org.springframework.data.mongodb.repository.DeleteQuery;
import springmongodocker.dto.Car;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends MongoRepository<Car,String> {

}
