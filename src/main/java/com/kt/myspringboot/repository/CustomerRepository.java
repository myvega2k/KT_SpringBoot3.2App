package com.kt.myspringboot.repository;

import com.kt.myspringboot.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    //customerId 로 조회하는 query method
    Optional<Customer> findByCustomerId(String id);
    List<Customer> findByCustomerName(String name);
}
