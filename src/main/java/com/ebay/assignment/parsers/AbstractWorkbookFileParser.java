package com.ebay.assignment.parsers;

import com.ebay.assignment.model.Loaner;
import com.ebay.assignment.model.Workbook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public abstract class AbstractWorkbookFileParser implements WorkbookParser {

    Workbook.DataSource sourceType;

    AbstractWorkbookFileParser(Workbook.DataSource sourceType) {
        this.sourceType = sourceType;
    }

    public Optional<Workbook> parse(String source) {
        try {
            return Optional.of(new Workbook(source, parseWorkbook(new ClassPathResource(source).getFile(), source), sourceType));
        } catch (IOException e) {
            log.error("Failed to find workbook {}. Returning empty optional", source, e);
            return Optional.empty();
        }
    }

    private List<Loaner> parseWorkbook(File file, String source) throws IOException {
        try(BufferedReader br = Files.newBufferedReader(file.toPath(), Charset.forName("ISO-8859-1"))) {
            List<Loaner> loaners = br.lines().skip(1).map( l -> {
                try {
                    return createLoanerFromLine(l);
                }catch (RuntimeException e) {
                    log.warn("Ignoring badly formatted line {}" , l, e);
                    return null;
                }
            }).filter(Objects::nonNull).collect(Collectors.toList());
            loaners.forEach(l -> l.setOriginWorkbook(source));
            return loaners;
        }
    }

    abstract Loaner createLoanerFromLine(String line);
}
