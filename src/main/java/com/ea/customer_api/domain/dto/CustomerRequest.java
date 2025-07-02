package com.ea.customer_api.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(

    @NotBlank(message = "Name is mandatory")
    String name,

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Valid email is required")
    String email,

    @NotNull(message = "Annual Spend is mandatory")
    BigDecimal annualSpend,

    @NotNull(message = "Last Purchase Date is mandatory")
    LocalDateTime lastPurchaseDate
) {}
