package com.ea.customer_api.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ea.customer_api.domain.dto.CustomerRequest;
import com.ea.customer_api.domain.dto.CustomerResponse;
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

    public List<CustomerResponse> getByEmail(final String email) {
        final var customers = repository.findByEmail(email)
            .stream()
            .map(CustomerResponse::from)
            .toList();

        return customers;
    }

    public List<CustomerResponse> getByName(final String name) {
        final var customers = repository.findByName(name)
            .stream()
            .map(CustomerResponse::from)
            .toList();

        return customers;
    }

    public CustomerResponse save(final CustomerRequest customerRequest) {
        final var customer = new Customer();

        customer.setId(null); // <-- explicit
        customer.setEmail(customerRequest.email());
        customer.setName(customerRequest.name());
        customer.setAnnualSpend(customerRequest.annualSpend());
        customer.setLastPurchaseDate(customerRequest.lastPurchaseDate());

        final var customerSavedInDb = repository.save(customer);

        return CustomerResponse.from(customerSavedInDb);
    }

    public void deleteById(final UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }


}
