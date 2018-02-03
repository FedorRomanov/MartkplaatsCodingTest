package com.ebay.assignment.parsers;

import com.ebay.assignment.model.Workbook;

import java.util.Optional;


/**
 * Interface to be implemented by Workbook parsers
 */
public interface WorkbookParser {

    /**
     * parses given source into single Workbook
     * @param source name of the source to parse. Must be on the classpath.
     * @return parsed Workbook wrapped in Optional or Optional.empty() if specified source could not be loaded.
     * Implementations must not throw runtime exceptions.
     */
    Optional<Workbook> parse(String source);

}
