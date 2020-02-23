package com.bankaccount.domain;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AmountTest {
    @Test
    void should_verify_equality() {
        EqualsVerifier.forClass(PositiveAmount.class).verify();
    }

    @Test
    void should_not_allow_to_create_negative_amounts() {
        Assertions.assertThatThrownBy(() -> PositiveAmount.of(-10))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Amounts cannot be negative");
    }

    @Test
    void should_allow_to_create_amounts() {
        assertThat(PositiveAmount.of(1)).isEqualTo(PositiveAmount.of(1));
    }

    @Test
    void should_allow_to_add_to_zero_amount() {
        final Amount amount = PositiveAmount.of(10);
        final PositiveAmount amount2 = PositiveAmount.of(0);

        final Balance balance = amount.add(amount2);

        assertThat(balance).isEqualTo(Balance.of(10));
    }

    @Test
    void should_allow_to_add_two_non_zero_amounts() {
        final Amount amount = PositiveAmount.of(10);
        final PositiveAmount amount2 = PositiveAmount.of(5);

        final Balance balance = amount.add(amount2);

        assertThat(balance).isEqualTo(Balance.of(15));
    }
}