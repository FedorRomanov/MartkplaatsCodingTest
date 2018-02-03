package com.ebay.assignment.parsers;

import com.ebay.assignment.model.Birthday;
import com.ebay.assignment.model.Birthday.Format;
import com.ebay.assignment.model.CreditLimit;
import com.ebay.assignment.model.Loaner;
import org.junit.Test;

import static org.junit.Assert.*;

public class WorkbookCsvFileParserTest {

    static String WELL_FORMATTEN_CSV_LINE = "\"Johnson, John\",Voorstraat 32,3122gg,020 3849381,10000,01/01/1987";

    @Test
    public void parsingCorrectLineShouldCreateLoaner() {
        //given
        WorkbookCsvFileParser parser = new WorkbookCsvFileParser();
        Loaner expected = new Loaner("\"Johnson, John\"",
                "Voorstraat 32",
                "3122gg",
                "020 3849381",
                new CreditLimit("10000", CreditLimit.Nomenation.DOLLAR),
                Birthday.createBirthdayFromString("01/01/1987", Format.SLASH_SEPARATED));

        //when
        Loaner actual = parser.createLoanerFromLine(WELL_FORMATTEN_CSV_LINE);

        //then
        assertEquals("expected and actual loaners don't match", expected, actual);
    }

}