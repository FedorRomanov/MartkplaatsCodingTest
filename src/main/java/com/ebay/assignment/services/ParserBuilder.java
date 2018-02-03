package com.ebay.assignment.services;

import com.ebay.assignment.model.Workbook.DataSource;
import com.ebay.assignment.parsers.WorkbookCsvFileParser;
import com.ebay.assignment.parsers.WorkbookParser;
import com.ebay.assignment.parsers.WorkbookPrnFileParser;
import org.springframework.stereotype.Component;

import java.util.AbstractMap;
import java.util.EnumMap;

@Component
public class ParserBuilder {

    private EnumMap<DataSource, WorkbookParser> supportedSources = new EnumMap<>(DataSource.class);

    /**
     * Registers supported Workbook parsers.
     * Instantiate and add new parser here.
     */
    public ParserBuilder() {
        supportedSources.put(DataSource.CSV, new WorkbookCsvFileParser());
        supportedSources.put(DataSource.PRN, new WorkbookPrnFileParser());
    }

    public AbstractMap.SimpleEntry<String, WorkbookParser> createWorkbookParserForSource(String source) {
        DataSource dataSource = DataSource.valueOf(source.substring(source.lastIndexOf(".") + 1).toUpperCase());
        return new AbstractMap.SimpleEntry<>(source, supportedSources.get(dataSource));
    }
}
