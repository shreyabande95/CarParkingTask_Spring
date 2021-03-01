package springmongodocker.repository;

import springmongodocker.dto.Car;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends MongoRepository<Car,Integer> {
}
