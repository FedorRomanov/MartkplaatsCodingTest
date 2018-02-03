package com.ebay.assignment.services;

import com.ebay.assignment.model.LoanersCreditLimitConflict;
import com.ebay.assignment.model.Workbook;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Component
public class WorkbookService {

    ParserBuilder parserBuilder;

    public WorkbookService(ParserBuilder parserBuilder) {
        this.parserBuilder = parserBuilder;
    }

    /**
     * Concurrently loads and parses workbooks from given sources.
     * @param sources sources of workbooks, e.g. filenames on classpath.
     * @return List of load workbooks. If workbook fails to load or parse(e.g. could not be found), empty workbook is returned.
     * @throws RuntimeException in case of threading problems(e.g. JVM exceeds thread limit, e.t.c.)
     */
    public List<Workbook> loadWorkbooksFromFiles(String... sources) {
        List<CompletableFuture<Optional<Workbook>>> futureWorkbooks = Arrays.stream(sources)
                .map(s ->  parserBuilder.createWorkbookParserForSource(s))
                .map(entry -> CompletableFuture.supplyAsync(() -> entry.getValue().parse(entry.getKey())))
                .collect(Collectors.toList());
        CompletableFuture<List<Workbook>> allDone = CompletableFuture.allOf(
                futureWorkbooks.toArray(new CompletableFuture[futureWorkbooks.size()]))
                .thenApply(v -> futureWorkbooks.stream().map(future -> future.join())
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .collect(Collectors.<Workbook>toList()));
        try {
            return allDone.get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Compares passed workbooks with each other and concurrently detects loaners with credit conflict,
     * i.e.credit limit amount or nomination differs.
     * @param workbooks workbooks to search for credit limit conflicts.
     * @return list of credit conflicts.
     */
    public List<LoanersCreditLimitConflict> findLoanersWithCreditConflict(List<Workbook> workbooks) {
        //must be thread-safe, accessed concurrently by parallel streams
        List<LoanersCreditLimitConflict> result = new CopyOnWriteArrayList<>();
        for( int i = 0; i < workbooks.size() - 1; i++) {
            Workbook workbook = workbooks.get(i);
            for(int j = i + 1; j < workbooks.size(); j++){
                Workbook anotherWorkbook = workbooks.get(j);
                workbook.getLoaners().parallelStream().forEach( l -> anotherWorkbook.findLoaner(l)
                        .filter(anotherL -> !l.hasEqualCreditLimit(anotherL))
                        .ifPresent( anotherL -> result.add(new LoanersCreditLimitConflict(l, anotherL))));
            }
        }
        return result;
    }

}
