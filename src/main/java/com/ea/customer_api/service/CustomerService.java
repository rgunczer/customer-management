package com.ea.customer_api.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ea.customer_api.domain.model.Customer;
import com.ea.customer_api.domain.repo.CustomerRepository;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository repository;


    public Optional<Customer> findById(final UUID id) {
        return repository.findById(id);
    }

}
