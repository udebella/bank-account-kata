package bankaccount;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class BankWithdrawalTest {
    private Bank bank;

    @Before
    public void setUp() {
        bank = new Bank();

        bank.addAccount(AccountNumber.fromNumber(1), Amount.fromValue(1000));
        bank.addAccount(AccountNumber.fromNumber(2), Amount.fromValue(0));
    }

    @Test(expected = AccountNotFoundException.class)
    public void should_not_allow_to_withdraw_from_an_unregistered_account() throws Exception {
        final AccountNumber accountNumber = AccountNumber.fromNumber(5);
        final Amount amount = Amount.fromValue(100);

        bank.withdraw(accountNumber, amount);
    }

    @Test(expected = NotEnoughMoney.class)
    public void should_not_allow_to_withdraw_if_there_is_not_enough_money_on_the_account() throws Exception {
        final AccountNumber accountNumber = AccountNumber.fromNumber(2);
        final Amount amount = Amount.fromValue(100);

        bank.withdraw(accountNumber, amount);
    }

    @Test
    public void should_retrieve_amount_from_account_balance() throws Exception {
        final AccountNumber accountNumber = AccountNumber.fromNumber(1);
        final Amount amount = Amount.fromValue(100);

        Account account = bank.withdraw(accountNumber, amount);

        Assertions.assertThat(account.accountBalance()).isEqualTo(Amount.fromValue(900));
    }
}
