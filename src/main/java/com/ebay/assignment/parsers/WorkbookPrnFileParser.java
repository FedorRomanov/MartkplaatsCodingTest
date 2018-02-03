package com.ebay.assignment.parsers;

import com.ebay.assignment.model.Birthday;
import com.ebay.assignment.model.Birthday.Format;
import com.ebay.assignment.model.CreditLimit;
import com.ebay.assignment.model.Loaner;
import com.ebay.assignment.model.Workbook;


public class WorkbookPrnFileParser extends AbstractWorkbookFileParser {

    public WorkbookPrnFileParser() {
        super(Workbook.DataSource.PRN);
    }

    @Override
    Loaner createLoanerFromLine(String line) {
        String name = line.substring(0, 16).trim();
        String address = line.substring(16, 38).trim();
        String postcode = line.substring(38, 47).trim();
        String phone = line.substring(47, 62).trim();
        CreditLimit creditLimit = new CreditLimit(line.substring(62, 74).trim(), CreditLimit.Nomenation.CENT);
        Birthday birthday = Birthday.createBirthdayFromString(line.substring(74).trim(), Format.NO_SEPARATOR);
        return new Loaner(name, address, postcode, phone, creditLimit, birthday);
    }
}
