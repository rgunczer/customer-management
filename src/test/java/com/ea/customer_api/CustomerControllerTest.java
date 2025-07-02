package com.ea.customer_api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.ea.customer_api.domain.model.Customer;
import com.ea.customer_api.domain.repo.CustomerRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository repository;

    private Customer existingCustomer;

    @BeforeEach
    void setup() {
        repository.deleteAll();
        existingCustomer = new Customer();
        existingCustomer.setName("Alice");
        existingCustomer.setEmail("alice@example.com");
        existingCustomer.setAnnualSpend(new BigDecimal("1500.00"));
        existingCustomer.setLastPurchaseDate(LocalDateTime.of(2024, 8, 1, 10, 0));
        existingCustomer = repository.save(existingCustomer);
        repository.flush();
    }

    @Test
    void testGetCustomerById() throws Exception {
        mockMvc.perform(get("/customers/" + existingCustomer.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Alice"));
    }

    @Test
    void testCreateCustomer() throws Exception {
        String payload = """
                {
                    "name": "Bob",
                    "email": "bob@example.com",
                    "annualSpend": 1200.50,
                    "lastPurchaseDate": "2024-08-01T10:00:00"
                }
                """;

        mockMvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value("bob@example.com"))
                .andExpect(jsonPath("$.tier").value("Gold"));
    }

    @Test
    void testDeleteCustomer() throws Exception {
        mockMvc.perform(delete("/customers/" + existingCustomer.getId()))
                .andExpect(status().isNoContent());
    }

}
