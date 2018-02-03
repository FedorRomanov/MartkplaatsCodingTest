package com.ebay.assignment.services;

import com.ebay.assignment.parsers.WorkbookCsvFileParser;
import com.ebay.assignment.parsers.WorkbookParser;
import com.ebay.assignment.parsers.WorkbookPrnFileParser;
import org.junit.Test;

import java.util.AbstractMap;

import static org.junit.Assert.*;


public class ParserBuilderTest {
    @Test
    public void whenCsvSourcePassedBuilderShouldCreateCsvParser() {
        ParserBuilder builder = new ParserBuilder();
        AbstractMap.SimpleEntry<String, WorkbookParser> parser = builder.createWorkbookParserForSource("Workbook2.csv");

        assertTrue("instantiated parser is not WorkbookCsvFileParser", parser.getValue() instanceof WorkbookCsvFileParser);
    }

    @Test
    public void whenPrnSourcePassedBuilderShouldCreatePrnParser() {
        ParserBuilder builder = new ParserBuilder();
        AbstractMap.SimpleEntry<String, WorkbookParser> parser = builder.createWorkbookParserForSource("Workbook2.prn");

        assertTrue("instantiated parser is not WorkbookPrnFileParser", parser.getValue() instanceof WorkbookPrnFileParser);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenUnknownSourcePassedBuilderShouldThrowException() {
        ParserBuilder builder = new ParserBuilder();
        builder.createWorkbookParserForSource("Workbook2.xsl");

        fail("exception is not thrown for unknown source");
    }

}