package com.ea.customer_api.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.ea.customer_api.domain.model.Customer;
import com.ea.customer_api.etc.TierCalculator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {

    private UUID id;
    private String name;
    private String email;
    private BigDecimal annualSpend;
    private LocalDateTime lastPurchaseDate;
    private String tier;


    public static CustomerResponse from(
        final Customer customer
    ) {
        final var response = new CustomerResponse();

        response.id = customer.getId();
        response.name = customer.getName();
        response.email = customer.getEmail();
        response.annualSpend = customer.getAnnualSpend();
        response.lastPurchaseDate = customer.getLastPurchaseDate();
        response.tier = TierCalculator.calculateTier(customer);

        return response;
    }

}
