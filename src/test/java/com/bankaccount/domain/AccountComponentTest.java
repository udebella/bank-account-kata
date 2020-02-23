package com.bankaccount.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountComponentTest {
    @Test
    void should_allow_deposits() {
        final Account account = new Account();

        account.deposit(PositiveAmount.of(10));

        Assertions.assertThat(account.balance()).isEqualTo(Balance.of(10));
    }

    @Test
    void should_allow_multiple_deposits() {
        final Account account = new Account();

        account.deposit(PositiveAmount.of(10));
        account.deposit(PositiveAmount.of(20));

        Assertions.assertThat(account.balance()).isEqualTo(Balance.of(30));
    }

    @Test
    void should_allow_withdrawal() {
        final Account account = new Account();

        account.withdraw(PositiveAmount.of(10));

        Assertions.assertThat(account.balance()).isEqualTo(Balance.of(-10));
    }
}
