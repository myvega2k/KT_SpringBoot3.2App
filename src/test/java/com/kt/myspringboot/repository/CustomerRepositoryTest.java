package com.kt.myspringboot.repository;

import com.kt.myspringboot.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerRepositoryTest {
    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void customer() throws Exception {
        //등록
        Customer customer = new Customer();
        customer.setCustomerId("A001");
        customer.setCustomerName("스프링");
        Customer addCustomer = customerRepository.save(customer);
        System.out.println(addCustomer.getCustomerId() + " " + addCustomer.getCustomerName());
        assertThat(addCustomer).isNotNull();
        assertThat(addCustomer.getCustomerName()).isEqualTo("스프링");
        Optional<Customer> existCustomer = customerRepository.findByCustomerId(addCustomer.getCustomerId());
        assertThat(existCustomer).isNotEmpty();
        Optional<Customer> notExistOptional = customerRepository.findByCustomerId("B001");
        assertThat(notExistOptional).isEmpty();
    }
}