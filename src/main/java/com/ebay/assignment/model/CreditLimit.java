package com.ebay.assignment.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Objects;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CreditLimit {

    private static BigDecimal ONE_HUNDRED = new BigDecimal("100");
    public enum Nomenation {
        DOLLAR,
        CENT
    }

    @Getter
    BigDecimal amount;

    @Getter
    Nomenation nomenation;

    public CreditLimit(String amount, Nomenation nomenation) {
        this(new BigDecimal(amount), nomenation);
    }

    public String formatCreditLimit(){
        return amount.toPlainString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CreditLimit that = (CreditLimit) o;

        if(nomenation != that.nomenation) {
            that = that.convertTo(nomenation);
        }
        return amount.equals(that.amount);
    }

    CreditLimit convertTo(Nomenation nomenation) {
        switch (nomenation) {
            case CENT:
                BigDecimal amountInCents = amount.multiply(ONE_HUNDRED).setScale(0);//truncates digits after dot
                return new CreditLimit(amountInCents, Nomenation.CENT);
            case DOLLAR:
                return new CreditLimit(amount.divide(ONE_HUNDRED), Nomenation.DOLLAR);
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, nomenation);
    }

    @Override
    public String toString() {
        return  amount +" " + nomenation + "(s)" ;
    }
}
