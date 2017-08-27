package bankaccount;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    private final Map<AccountNumber, Account> accounts = new HashMap<>();

    public void addAccount(AccountNumber accountNumber, Amount amount) {
        accounts.put(accountNumber, Account.fromAmount(amount));
    }

    public Account makeDeposit(AccountNumber accountNumber, Amount amount) throws AccountNotFoundException {
        Account account = accounts.get(accountNumber);
        if (account == null) {
            throw new AccountNotFoundException("Account " + accountNumber + " not found");
        }
        account.deposit(amount);
        return account;
    }

    public Account withdraw(AccountNumber accountNumber, Amount amount) throws AccountNotFoundException, NotEnoughMoney {
        Account account = accounts.get(accountNumber);
        if (account == null) {
            throw new AccountNotFoundException("Account " + accountNumber + " not found");
        }
        throw new NotEnoughMoney("Account does not have enough money");
    }
}
