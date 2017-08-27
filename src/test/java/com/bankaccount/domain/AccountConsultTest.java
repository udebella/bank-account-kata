package com.bankaccount.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountConsultTest {
    @Test
    public void account_should_have_empty_history_by_default() {
        final Account account = Account.fromAmount(Amount.ZERO);

        assertThat(account.consult()).containsExactly(CreationOperation.fromAmount(Amount.ZERO));
    }

    @Test
    public void should_not_allow_to_modify_account_history() {
        final Account account = Account.fromAmount(Amount.ZERO);

        account.consult().add(CreationOperation.fromAmount(Amount.ZERO));

        assertThat(account.consult()).containsExactly(CreationOperation.fromAmount(Amount.ZERO));
    }

    @Test
    public void should_display_deposit_in_history() {
        final Account account = Account.fromAmount(Amount.ZERO);

        account.deposit(Amount.fromValue(10));

        assertThat(account.consult()).contains(DepositOperation.fromAmount(Amount.fromValue(10)));
    }
}
