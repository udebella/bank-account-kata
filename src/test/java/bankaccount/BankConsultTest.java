package bankaccount;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class BankConsultTest {
    private Bank bank;

    @Before
    public void setUp() {
        bank = new Bank();
    }

    @Test
    public void account_should_have_empty_history_by_default() {
        AccountNumber accountNumber = AccountNumber.fromNumber(1);

        assertThat(bank.consult(accountNumber)).isEqualTo(Collections.EMPTY_LIST);
    }
}
