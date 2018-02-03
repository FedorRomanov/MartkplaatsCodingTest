package com.ebay.assignment.parsers;


import com.ebay.assignment.model.Birthday;
import com.ebay.assignment.model.Birthday.Format;
import com.ebay.assignment.model.CreditLimit;
import com.ebay.assignment.model.Loaner;
import com.ebay.assignment.model.Workbook;

public class WorkbookCsvFileParser extends AbstractWorkbookFileParser {

    public WorkbookCsvFileParser() {
        super(Workbook.DataSource.CSV);
    }

    @Override
    Loaner createLoanerFromLine(String line) {
        String[] p = line.split(",");// a CSV has comma separated lines
        return new Loaner(p[0] + "," + p[1], p[2], p[3], p[4],
                new CreditLimit(p[5], CreditLimit.Nomenation.DOLLAR), Birthday.createBirthdayFromString(p[6], Format.SLASH_SEPARATED));
    }
}
