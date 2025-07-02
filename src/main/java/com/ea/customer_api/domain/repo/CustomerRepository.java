package com.ea.customer_api.domain.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.repository.ListCrudRepository;

import com.ea.customer_api.domain.model.Customer;


// public interface CustomerRepository extends ListCrudRepository<Customer, UUID> {
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    List<Customer> findByName(String name);
    List<Customer> findByEmail(String email);

}
