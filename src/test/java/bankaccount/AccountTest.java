package bankaccount;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {
    private Account account;

    @Before
    public void setUp() throws Exception {
        account = new Account();
    }

    @Test
    public void balance_should_be_zero_by_default() {
        assertThat(account.balance()).isEqualTo(Amount.ZERO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deposit_should_not_allow_negative_amounts() {
        account.deposit(Amount.of(-10), LocalDate.now());
    }

    @Test
    public void deposit_should_update_account_balance() {
        account.deposit(Amount.of(10), LocalDate.now());

        assertThat(account.balance()).isEqualTo(Amount.of(10));
    }

    @Test
    public void multiple_deposit_should_update_account_balance() {
        account.deposit(Amount.of(10), LocalDate.now());
        account.deposit(Amount.of(10), LocalDate.now());

        assertThat(account.balance()).isEqualTo(Amount.of(20));
    }

    @Test
    public void multiple_deposit_of_different_amount_should_update_balance() {
        account.deposit(Amount.of(10), LocalDate.now());
        account.deposit(Amount.of(15), LocalDate.now());

        assertThat(account.balance()).isEqualTo(Amount.of(25));
    }

    @Test
    public void withdraw_nothing_should_not_update_balance() {
        account.withdraw(Amount.ZERO, LocalDate.now());

        assertThat(account.balance()).isEqualTo(Amount.ZERO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void withdraw_should_not_allow_negative_amounts() {
        account.withdraw(Amount.of(-10), LocalDate.now());
    }

    @Test
    public void withdraw_should_update_account_balance() {
        account.withdraw(Amount.of(50), LocalDate.now());

        assertThat(account.balance()).isEqualTo(Amount.of(-50));
    }
}
