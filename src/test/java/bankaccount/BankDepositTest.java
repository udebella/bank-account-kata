package bankaccount;

import org.junit.Before;
import org.junit.Test;

import static bankaccount.AccountNumber.AccountNumberBuilder;
import static org.assertj.core.api.Assertions.assertThat;

public class BankDepositTest {
    private AccountNumberBuilder accountNumberBuilder;
    private Bank bank;

    @Before
    public void setUp() {
        accountNumberBuilder = new AccountNumberBuilder();

        bank = new Bank();
        bank.addAccount(accountNumberBuilder.withNumber(1).build(), Amount.fromValue(0));
        bank.addAccount(accountNumberBuilder.withNumber(2).build(), Amount.fromValue(0));
    }

    @Test
    public void deposit_should_update_account_balance() throws AccountNotFoundException {
        final Amount amount = Amount.fromValue(10);
        final AccountNumber accountNumber = accountNumberBuilder.withNumber(1).build();

        Account account = bank.makeDeposit(accountNumber, amount);

        assertThat(account.accountBalance())
                .isEqualTo(Amount.fromValue(10));
    }

    @Test
    public void depositing_two_times_in_a_row_on_the_same_account_should_update_the_account() throws AccountNotFoundException {
        final Amount amount = Amount.fromValue(10);
        final AccountNumber accountNumber = accountNumberBuilder.withNumber(1).build();

        bank.makeDeposit(accountNumber, amount);
        Account account = bank.makeDeposit(accountNumber, amount);

        assertThat(account.accountBalance())
                .isEqualTo(Amount.fromValue(20));
    }

    @Test
    public void should_manage_multiple_different_accounts() throws AccountNotFoundException {
        final Amount amount = Amount.fromValue(10);
        final AccountNumber accountNumber = accountNumberBuilder.withNumber(1).build();
        final AccountNumber accountNumber2 = accountNumberBuilder.withNumber(2).build();

        Account account = bank.makeDeposit(accountNumber, amount);
        Account account2 = bank.makeDeposit(accountNumber2, amount);

        assertThat(account).isNotEqualTo(account2);
    }

    @Test(expected = AccountNotFoundException.class)
    public void should_not_allow_to_deposit_on_an_unregistered_account() throws AccountNotFoundException {
        final Amount amount = Amount.fromValue(10);
        final AccountNumber accountNumber = accountNumberBuilder.withNumber(3).build();

        bank.makeDeposit(accountNumber, amount);
    }
}
