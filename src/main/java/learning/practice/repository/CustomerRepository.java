package learning.practice.repository;

import learning.practice.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface CustomerRepository extends MongoRepository<Customer, String> {

}
