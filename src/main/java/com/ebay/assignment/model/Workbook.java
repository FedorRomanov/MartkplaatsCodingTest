package com.ebay.assignment.model;

import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
public class Workbook {

    public enum DataSource {
        CSV,
        PRN
    }

    final String source;
    final List<Loaner> loaners;
    final DataSource sourceType;

    public Optional<Loaner> findLoaner(Loaner loaner) {
        return loaners.stream().filter( l-> l.isTheSameLoaner(loaner)).findFirst();
    }

}
