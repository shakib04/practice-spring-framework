package learning.practice.service;

import learning.practice.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerServiceImplTest implements SpringBootTestDefault {

    @Autowired
    private CustomerServiceImpl customerService;

    @Test
    void insert() {
        Customer customer1 = new Customer();
        customer1.setId("1");
        customer1.setFirstName("test");
        customer1.setLastName("test");
        Customer savedCustomer1 = customerService.insert(customer1);

        Customer customer2 = new Customer();
        customer2.setId("1");
        customer2.setFirstName("test");
//        customer2.setLastName("test");
        Customer savedCustomer2 = customerService.upsert(customer2);

        assertEquals(savedCustomer1.getLastName(), savedCustomer2.getLastName());
    }

    @Test
    void upsert() {
        Customer customer1 = new Customer();
        customer1.setId("2");
        customer1.setFirstName("test");
        customer1.setLastName("test");
        Customer savedCustomer1 = customerService.upsert(customer1);
        customerService.delete(savedCustomer1);
    }
}