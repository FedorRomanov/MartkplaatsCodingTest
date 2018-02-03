package com.ebay.assignment.parsers;

import com.ebay.assignment.model.Birthday;
import com.ebay.assignment.model.CreditLimit;
import com.ebay.assignment.model.Loaner;
import com.ebay.assignment.model.Workbook;
import com.ebay.assignment.model.Workbook.DataSource;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AbstractWorkbookFileParserTest {

    @Test
    public void parse() {
        Loaner loaner = new Loaner("\"Johnson, John\"",
                "Voorstraat 32",
                "3122gg",
                "020 3849381",
                new CreditLimit("10000", CreditLimit.Nomenation.DOLLAR),
                Birthday.createBirthdayFromString("01/01/1987", Birthday.Format.SLASH_SEPARATED));
        Workbook expectedWorkbook = new Workbook("Workbook2_with_error.csv", Arrays.asList(loaner), DataSource.CSV);
        WorkbookCsvFileParser parser = new WorkbookCsvFileParser();

        Workbook actualWorkbook = parser.parse("Workbook2_with_error.csv").get();

        assertThat("expected and actual workbooks do not match", actualWorkbook, is(expectedWorkbook));
    }
}