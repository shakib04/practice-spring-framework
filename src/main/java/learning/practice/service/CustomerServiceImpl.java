package learning.practice.service;

import learning.practice.entity.Customer;
import learning.practice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.function.Consumer;

@Service
public class CustomerServiceImpl {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer insert(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer upsert(Customer customer) {
        Objects.requireNonNull(customer.getId());
        return customerRepository.findById(customer.getId())
                .map(customer1 -> {
                    nullSafeSetter(customer1::setFirstName, customer1.getFirstName(), customer.getFirstName());
                    nullSafeSetter(customer1::setLastName, customer1.getLastName(), customer.getLastName());

                    return customerRepository.save(customer1);
                }).orElse(insert(customer));
    }

    private static <T> void nullSafeSetter(Consumer<T> setter, T newValue, T defaultValue) {
        setter.accept(newValue == null ? defaultValue : newValue);
    }

    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }
}


