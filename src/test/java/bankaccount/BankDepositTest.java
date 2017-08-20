package bankaccount;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BankDepositTest {
    private Bank bank;

    @Before
    public void setUp() {
        bank = new Bank();
    }

    @Test
    public void should_not_be_possible_to_deposit_null_amount() {
        final Amount amount = new Amount.AmountBuilder()
                .withValueAsCents(0)
                .build();

        assertThat(bank.makeDeposit(amount)).isFalse();
    }
}
