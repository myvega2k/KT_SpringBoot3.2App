package com.kt.myspringboot.repository;

import com.kt.myspringboot.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CustomerRepositoryTest {
    @Autowired
    CustomerRepository customerRepository;

    @Test
    @Rollback(value = false)
    public void dupCustomer() throws Exception {
        Optional<Customer> optional = customerRepository.findByCustomerId("A001");
        //void ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction)
        //Consumer의 추상메서드 void accept(T t)
        //Ruunable의 추상메서드 void run()
//        optional.ifPresentOrElse(
//                //cust -> customerRepository.delete(cust),
//                customerRepository::delete,
//                () -> dupSave()
//        );

        optional.ifPresent(
                //cust -> customerRepository.delete(cust)
                customerRepository::delete
        );

        optional = customerRepository.findByCustomerId("A001");
        if(optional.isEmpty()){
            dupCheckAfterSave();
        }
    }

    private void dupCheckAfterSave() {
        Customer customer = new Customer();
        customer.setCustomerId("A001");
        customer.setCustomerName("스프링");

        Customer addCustomer = customerRepository.save(customer);
        assertThat(addCustomer).isNotNull();
        assertThat(addCustomer.getCustomerName()).isEqualTo("스프링");
    }

    @Test
    @Rollback(value = false)
    public void customer() throws Exception {
        //등록
        Customer customer = new Customer();
        customer.setCustomerId("A001");
        customer.setCustomerName("스프링");

        //등록
        Customer addCustomer = customerRepository.save(customer);
        System.out.println(addCustomer.getCustomerId() + " " + addCustomer.getCustomerName());

        assertThat(addCustomer).isNotNull();
        //assertj
        assertThat(addCustomer.getCustomerName()).isEqualTo("스프링");
        //junit
        assertEquals("스프링", addCustomer.getCustomerName());

        Optional<Customer> existCustomer = customerRepository.findByCustomerId(addCustomer.getCustomerId());
        assertThat(existCustomer).isNotEmpty();
        if(existCustomer.isPresent()){
            Customer customer1 = existCustomer.get();
            assertThat(customer1.getCustomerName().equals("스프링"));
        }
        Optional<Customer> notExistOptional = customerRepository.findByCustomerId("A001");
        //assertThat(notExistOptional).isEmpty();
        //orElseThrow(Supplier) Supplier의 추상메서드 T get()  T extends Throwable
        Customer customer2 =
                notExistOptional.orElseThrow(() -> new RuntimeException("Customer Not Found"));
        assertThat(customer2).isNotNull();
    }
}