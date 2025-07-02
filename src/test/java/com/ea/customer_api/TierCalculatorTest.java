package com.ea.customer_api;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.ea.customer_api.domain.model.Customer;
import com.ea.customer_api.etc.TierCalculator;

import static org.assertj.core.api.Assertions.assertThat;


class TierCalculatorTest {

    @Test
    void shouldReturnPlatinumWhenSpendHighAndRecent() {
        final var customer = new Customer();
        customer.setAnnualSpend(new BigDecimal("15000"));
        customer.setLastPurchaseDate(LocalDateTime.now().minusMonths(2));

        final var tier = TierCalculator.calculateTier(customer);

        assertThat(tier).isEqualTo("Platinum");
    }

    @Test
    void shouldReturnGoldWhenSpendMediumAndRecent() {
        final var customer = new Customer();
        customer.setAnnualSpend(new BigDecimal("1500"));
        customer.setLastPurchaseDate(LocalDateTime.now().minusMonths(6));

        final var tier = TierCalculator.calculateTier(customer);

        assertThat(tier).isEqualTo("Gold");
    }

    @Test
    void shouldReturnSilverWhenSpendLow() {
        final var customer = new Customer();
        customer.setAnnualSpend(new BigDecimal("200"));
        customer.setLastPurchaseDate(LocalDateTime.now().minusMonths(1));

        final var tier = TierCalculator.calculateTier(customer);

        assertThat(tier).isEqualTo("Silver");
    }

    @Test
    void shouldReturnSilverWhenSpendHighButOldPurchase() {
        final var customer = new Customer();
        customer.setAnnualSpend(new BigDecimal("15000"));
        customer.setLastPurchaseDate(LocalDateTime.now().minusYears(2));

        final var tier = TierCalculator.calculateTier(customer);

        assertThat(tier).isEqualTo("Silver");
    }

    @Test
    void shouldReturnSilverWhenSpendIsNull() {
        final var customer = new Customer();
        customer.setAnnualSpend(null);
        customer.setLastPurchaseDate(LocalDateTime.now());

        final var tier = TierCalculator.calculateTier(customer);

        assertThat(tier).isEqualTo("Silver");
    }

    @Test
    void shouldReturnSilverWhenLastPurchaseDateIsNull() {
        final var customer = new Customer();
        customer.setAnnualSpend(new BigDecimal("15000"));
        customer.setLastPurchaseDate(null);

        final var tier = TierCalculator.calculateTier(customer);

        assertThat(tier).isEqualTo("Silver");
    }

}
