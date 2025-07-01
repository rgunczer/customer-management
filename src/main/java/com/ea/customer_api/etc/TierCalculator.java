package com.ea.customer_api.etc;

import java.time.LocalDateTime;

import com.ea.customer_api.domain.model.Customer;

import lombok.experimental.UtilityClass;


@UtilityClass
public class TierCalculator {

    public static String calculateTier(Customer customer) {

        if (customer.getAnnualSpend() == null) {
            return "Silver";
        }

        var spend = customer.getAnnualSpend();
        var lastPurchase = customer.getLastPurchaseDate();

        if (
            spend.compareTo(new java.math.BigDecimal("10000")) >= 0
                && lastPurchase != null
                && lastPurchase.isAfter(LocalDateTime.now().minusMonths(6))
        ) {
            return "Platinum";
        } else if (
            spend.compareTo(new java.math.BigDecimal("1000")) >= 0
                && lastPurchase != null
                && lastPurchase.isAfter(LocalDateTime.now().minusMonths(12))
        ) {
            return "Gold";
        }

        return "Silver";
    }

}
