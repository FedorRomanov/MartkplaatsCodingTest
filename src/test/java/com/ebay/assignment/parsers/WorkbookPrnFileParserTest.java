package com.ebay.assignment.parsers;

import com.ebay.assignment.model.Birthday;
import com.ebay.assignment.model.CreditLimit;
import com.ebay.assignment.model.Loaner;
import org.junit.Test;

import static org.junit.Assert.*;

public class WorkbookPrnFileParserTest {

    static String WELL_FORMATTEN_PRN_LINE = "Johnson, John   Voorstraat 32         3122gg   020 3849381        1000000 19870101";

    @Test
    public void parsingCorrectLineShouldCreateLoaner() {
        //given
        WorkbookPrnFileParser parser = new WorkbookPrnFileParser();
        Loaner expected = new Loaner("Johnson, John",
                "Voorstraat 32",
                "3122gg",
                "020 3849381",
                new CreditLimit("1000000", CreditLimit.Nomenation.CENT),
                Birthday.createBirthdayFromString("19870101", Birthday.Format.NO_SEPARATOR));

        //when
        Loaner actual = parser.createLoanerFromLine(WELL_FORMATTEN_PRN_LINE);

        //then
        assertEquals("expected and actual loaners don't match", expected, actual);
    }

}