package com.ea.customer_api;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.ea.customer_api.domain.dto.CustomerRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerRequestValidationTest {

    private static Validator validator;

    @BeforeAll
    static void setupValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldFailWhenEmailIsInvalid() {
        var request = new CustomerRequest(
                "Alice",
                "not-an-email",
                BigDecimal.valueOf(1000),
                LocalDateTime.now()
        );

        Set<ConstraintViolation<CustomerRequest>> violations = validator.validate(request);

        assertThat(violations)
            .anyMatch(v -> v.getPropertyPath().toString().equals("email") && v.getMessage().contains("Valid email"));
    }

    @Test
    void shouldFailWhenEmailIsBlank() {
        var request = new CustomerRequest(
                "Alice",
                "",
                BigDecimal.valueOf(1000),
                LocalDateTime.now()
        );

        Set<ConstraintViolation<CustomerRequest>> violations = validator.validate(request);

        assertThat(violations)
            .anyMatch(v -> v.getPropertyPath().toString().equals("email") && v.getMessage().contains("mandatory"));
    }

    @Test
    void shouldPassWhenEmailIsValid() {
        var request = new CustomerRequest(
                "Alice",
                "alice@example.com",
                BigDecimal.valueOf(1000),
                LocalDateTime.now()
        );

        Set<ConstraintViolation<CustomerRequest>> violations = validator.validate(request);

        assertThat(violations).isEmpty();
    }

}
