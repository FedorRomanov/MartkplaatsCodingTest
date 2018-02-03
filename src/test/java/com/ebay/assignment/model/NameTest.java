package com.ebay.assignment.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class NameTest {

    @Test
    public void namesWithAndWithoutQuotesShouldBeEqual() {
        Name nameWithQuotes = new Name("\"John Johnson\"");
        Name nameWithoutQuotes = new Name("John Johnson");

        assertEquals(nameWithQuotes, nameWithoutQuotes);
    }
}