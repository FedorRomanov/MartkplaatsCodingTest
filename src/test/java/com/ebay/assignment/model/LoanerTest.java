package com.ebay.assignment.model;

import com.ebay.assignment.model.Birthday.Format;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by romanovf on 27/01/2018.
 */
public class LoanerTest {

    @Test
    public void sameLoanersWithDifferentCreditLimitShouldBeTheSame() throws Exception {
        Loaner johnJohnsonWith10000Cents = new Loaner("Johnson, John",
                "Voorstraat 32",
                "3122gg",
                "020 3849381",
                new CreditLimit("10000", CreditLimit.Nomenation.CENT),
                Birthday.createBirthdayFromString("19870101", Format.NO_SEPARATOR));
        Loaner johnJohnsonWith99999Cents = new Loaner("Johnson, John",
                "Voorstraat 32",
                "3122gg",
                "020 3849381",
                new CreditLimit("99999", CreditLimit.Nomenation.CENT),
                Birthday.createBirthdayFromString("19870101", Format.NO_SEPARATOR));

        assertTrue("same loaners with different credit limits must be the same"
                , johnJohnsonWith10000Cents.isTheSameLoaner(johnJohnsonWith99999Cents));
    }

    @Test
    public void loanersWithDifferentNamesShouldNotBeTheSame() throws Exception {
        Loaner johnJohnson = new Loaner("Johnson, John",
                "Voorstraat 32",
                "3122gg",
                "020 3849381",
                new CreditLimit("1000000", CreditLimit.Nomenation.CENT),
                Birthday.createBirthdayFromString("19870101", Format.NO_SEPARATOR));
        Loaner paulAnderson = new Loaner("Anderson, Paul",
                "Voorstraat 32",
                "3122gg",
                "020 3849381",
                new CreditLimit("1000000", CreditLimit.Nomenation.CENT),
                Birthday.createBirthdayFromString("19870101", Format.NO_SEPARATOR));

        assertFalse("loaners with different names must not be the same", johnJohnson.isTheSameLoaner(paulAnderson));
    }

}