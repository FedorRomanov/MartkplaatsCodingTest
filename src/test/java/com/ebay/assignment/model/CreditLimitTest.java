package com.ebay.assignment.model;

import com.ebay.assignment.model.CreditLimit.Nomenation;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreditLimitTest {

    @Test
    public void convertingFromCent2DollarsShouldPass() {

        CreditLimit expectedInDollars = new CreditLimit("1.5", Nomenation.DOLLAR);
        CreditLimit creditLimitInCents = new CreditLimit("150", Nomenation.CENT);

        CreditLimit actualInDollars = creditLimitInCents.convertTo(Nomenation.DOLLAR);

        assertEquals("credit limits in dollars do not match", expectedInDollars, actualInDollars);

    }

    @Test
    public void convertingFromDollars2CentsShouldPass() {

        CreditLimit expectedInCents = new CreditLimit("150", Nomenation.CENT);
        CreditLimit creditLimitInDollars = new CreditLimit("1.5", Nomenation.DOLLAR);

        CreditLimit actualInCents = creditLimitInDollars.convertTo(Nomenation.CENT);

        assertEquals("credit limits in cents do not match", expectedInCents, actualInCents);

    }

}