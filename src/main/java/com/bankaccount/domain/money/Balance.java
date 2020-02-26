package com.bankaccount.domain.money;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public final class Balance {
    public static final Balance INITIAL = of(0);
    private final Amount amount;
    private final boolean isPositive;

    private Balance(Amount amount, boolean isPositive) {
        this.amount = amount;
        this.isPositive = isPositive;
    }

    public static Balance of(long amount) {
        final boolean isPositive = amount > 0;
        final Amount positiveAmount = Amount.of(Math.abs(amount));
        return new Balance(positiveAmount, isPositive);
    }

    private static Balance negative(Balance other) {
        return new Balance(other.amount, false);
    }

    public Balance add(Amount amount) {
        return this.isPositive ? this.amount.add(amount) : amount.subtract(this.amount);
    }

    public Balance subtract(Amount amount) {
        return this.isPositive ? this.amount.subtract(amount) : Balance.negative(this.amount.add(amount));
    }

    public void readAmount(AmountReader amountReader) {
        final AtomicLong positiveAmount = new AtomicLong();
        amount.readAmount(positiveAmount::set);
        amountReader.read(isPositive ? positiveAmount.get() : -positiveAmount.get());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Balance balance = (Balance) o;
        return isPositive == balance.isPositive &&
                Objects.equals(amount, balance.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, isPositive);
    }

    @Override
    public String toString() {
        return "Balance{" +
                "amount=" + amount +
                ", isPositive=" + isPositive +
                '}';
    }
}
