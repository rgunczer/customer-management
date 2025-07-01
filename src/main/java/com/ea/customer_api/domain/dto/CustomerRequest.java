package com.ea.customer_api.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CustomerRequest(
    @NotBlank
    String name,

    @NotBlank
    @Email
    String email,

    BigDecimal annualSpend,

    LocalDateTime lastPurchaseDate
) {}

