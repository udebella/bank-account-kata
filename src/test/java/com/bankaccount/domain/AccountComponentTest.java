package com.bankaccount.domain;

import com.bankaccount.domain.money.Balance;
import com.bankaccount.domain.money.Amount;
import com.bankaccount.domain.operations.Deposit;
import com.bankaccount.domain.operations.Withdrawal;
import com.bankaccount.domain.visitor.Printer;
import com.bankaccount.domain.visitor.HistoryPrinter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AccountComponentTest {
    @Test
    void should_allow_deposits() {
        final Account account = new Account();

        account.deposit(Amount.of(10));

        Assertions.assertThat(account.balance()).isEqualTo(Balance.of(10));
    }

    @Test
    void should_allow_multiple_deposits() {
        final Account account = new Account();

        account.deposit(Amount.of(10));
        account.deposit(Amount.of(20));

        Assertions.assertThat(account.balance()).isEqualTo(Balance.of(30));
    }

    @Test
    void should_allow_withdrawal() {
        final Account account = new Account();

        account.withdraw(Amount.of(10));

        Assertions.assertThat(account.balance()).isEqualTo(Balance.of(-10));
    }

    @Test
    void should_print_history() {
        final Account account = new Account(
                new Deposit(Amount.of(50), LocalDate.of(2020, Month.FEBRUARY, 23)),
                new Withdrawal(Amount.of(10), LocalDate.of(2020, Month.FEBRUARY, 23)),
                new Withdrawal(Amount.of(3), LocalDate.of(2020, Month.FEBRUARY, 23)),
                new Withdrawal(Amount.of(5), LocalDate.of(2020, Month.FEBRUARY, 23))
        );
        final Printer mock = mock(Printer.class);
        HistoryPrinter historyPrinter = new HistoryPrinter(mock);

        account.accept(historyPrinter);

        verify(mock).print("Deposit | 23/02/2020 | 50 | 50");
        verify(mock).print("Withdraw | 23/02/2020 | 10 | 40");
        verify(mock).print("Withdraw | 23/02/2020 | 3 | 37");
        verify(mock).print("Withdraw | 23/02/2020 | 5 | 32");
    }
}
