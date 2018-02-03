package com.ebay.assignment.services;

import com.ebay.assignment.model.Birthday;
import com.ebay.assignment.model.Birthday.Format;
import com.ebay.assignment.model.CreditLimit;
import com.ebay.assignment.model.Loaner;
import com.ebay.assignment.model.LoanersCreditLimitConflict;
import com.ebay.assignment.model.Workbook;
import com.ebay.assignment.parsers.WorkbookParser;
import org.junit.Test;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WorkbookServiceTest {

    private static Loaner johnJohnson10000Dollars = new Loaner("\"Johnson, John\"",
                                               "Voorstraat 32",
                                               "3122gg",
                                               "020 3849381",
                                               new CreditLimit("10000", CreditLimit.Nomenation.DOLLAR),
                                               Birthday.createBirthdayFromString("01/01/1987", Format.SLASH_SEPARATED));
    private static Loaner johnJohnson10000Cents = new Loaner("\"Johnson, John\"",
            "Voorstraat 32",
            "3122gg",
            "020 3849381",
            new CreditLimit("10000", CreditLimit.Nomenation.CENT),
            Birthday.createBirthdayFromString("01/01/1987", Format.SLASH_SEPARATED));

    private static Loaner paulAnderson = new Loaner("\"Anderson, Paul\"",
            "Dorpsplein 3A",
            "4532 AA",
            "030 3458986",
            new CreditLimit("109093", CreditLimit.Nomenation.DOLLAR),
            Birthday.createBirthdayFromString("03/12/1965", Format.SLASH_SEPARATED));

    public static final String WORKBOOK2_CSV = "Workbook2.csv";

    ParserBuilder builder = mock(ParserBuilder.class);

    WorkbookService workbookService;

    public WorkbookServiceTest() {
        workbookService = new WorkbookService(builder);
    }

    @Test
    public void loadingWorkbooksFromFilesTestShouldLoadWorkbook() {
        when(builder.createWorkbookParserForSource(WORKBOOK2_CSV)).thenReturn(
                new AbstractMap.SimpleEntry<>(WORKBOOK2_CSV, new WorkbookStubParser()));
        List<Workbook> expectedWorkbooks = Arrays.asList(new Workbook(WORKBOOK2_CSV,
                Arrays.asList(johnJohnson10000Dollars, paulAnderson), Workbook.DataSource.CSV));

        List<Workbook> actualWorkbooks = workbookService.loadWorkbooksFromFiles(WORKBOOK2_CSV);

        assertThat(actualWorkbooks, is(expectedWorkbooks));
    }

    @Test
    public void findLoanersWithCreditConflictShouldReturnLoanersWithCreditConflict() {
        Workbook workbook1 = new Workbook(WORKBOOK2_CSV, Arrays.asList(johnJohnson10000Dollars, paulAnderson),
                                            Workbook.DataSource.CSV);
        Workbook workbook2 = new Workbook(WORKBOOK2_CSV, Arrays.asList(johnJohnson10000Cents, paulAnderson),
                                            Workbook.DataSource.CSV);

        List<LoanersCreditLimitConflict> expectedConflicts = Arrays.asList(
                new LoanersCreditLimitConflict(johnJohnson10000Dollars, johnJohnson10000Cents));

        List<LoanersCreditLimitConflict> actualConflicts =
                workbookService.findLoanersWithCreditConflict(Arrays.asList(workbook1, workbook2));

        assertThat(actualConflicts, is(expectedConflicts));
    }

    class WorkbookStubParser implements WorkbookParser {

        @Override
        public Optional<Workbook> parse(String source) {
            List<Loaner> loaners = Arrays.asList(johnJohnson10000Dollars, paulAnderson);
            return Optional.of(new Workbook(source, loaners, Workbook.DataSource.CSV));
        }
    }
}