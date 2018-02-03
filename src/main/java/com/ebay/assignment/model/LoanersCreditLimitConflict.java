package com.ebay.assignment.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Objects;

public class LoanersCreditLimitConflict {

    Loaner loaner;
    Loaner loanerWithCreditConflict;

    public LoanersCreditLimitConflict(Loaner loaner, Loaner loanerWithCreditConflict) {
        this.loaner = loaner;
        this.loanerWithCreditConflict = loanerWithCreditConflict;
    }

    public String getConflictingWorkbookOrigin(){
        return loanerWithCreditConflict.originWorkbook;
    }

    public String getWorkbookOrigin(){
        return loaner.originWorkbook;
    }

    public CreditLimit getCreditLimit() {
        return loaner.creditLimit;
    }

    public CreditLimit getConflictingCreditLimit() {
        return loanerWithCreditConflict.creditLimit;
    }

    public Loaner getLoaner(){
        return loaner;
    }

    public Loaner getLoanerWithCreditConflict() {
        return loanerWithCreditConflict;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LoanersCreditLimitConflict that = (LoanersCreditLimitConflict) o;
        return Objects.equals(loaner, that.loaner) &&
                Objects.equals(loanerWithCreditConflict, that.loanerWithCreditConflict);
    }

    @Override
    public int hashCode() {

        return Objects.hash(loaner, loanerWithCreditConflict);
    }

    @Override
    public String toString() {
        return "{" +
                "loaner=" + loaner +
                ", loanerWithCreditConflict=" + loanerWithCreditConflict +
                '}';
    }
}
