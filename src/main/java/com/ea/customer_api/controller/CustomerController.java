package com.ea.customer_api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ea.customer_api.domain.dto.CustomerRequest;
import com.ea.customer_api.domain.dto.CustomerResponse;
import com.ea.customer_api.domain.model.Customer;
import com.ea.customer_api.service.CustomerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService service;

    // ----------------------------------------------------
    // GETTERS

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getById(@PathVariable UUID id) {
        log.info("getById [" + id + "]");

        return service.findById(id)
            .map(CustomerResponse::from)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping(params = "email")
    public ResponseEntity<List<CustomerResponse>> getByEmail(@RequestParam String email) {
        log.info("getByEmail [" + email + "]");
        final var customers = service.getByEmail(email);

        if (customers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(customers);
    }

    @GetMapping(params = "name")
    public ResponseEntity<List<CustomerResponse>> getByName(@RequestParam String name) {
        log.info("getByName [" + name + "]");
        final var customers = service.getByName(name);

        if (customers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(customers);
    }


    // ----------------------------------------------------
    // POST

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(
        @Valid @RequestBody CustomerRequest customerRequest
    ) {
        final var savedCustomer = service.save(customerRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
    }


    // ----------------------------------------------------
    // PUT


    // ----------------------------------------------------
    // DELETE

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        log.info("delete [" + id + "]");

        service.deleteById(id);

        return ResponseEntity.notFound().build();
    }

}
