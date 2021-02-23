package spring.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import spring.dto.Car;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends MongoRepository<Car,Integer>  {
}
