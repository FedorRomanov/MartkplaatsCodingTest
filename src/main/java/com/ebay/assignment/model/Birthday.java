package com.ebay.assignment.model;

import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@EqualsAndHashCode(exclude={"formatter"})
public class Birthday {

    public enum Format {
        SLASH_SEPARATED,
        NO_SEPARATOR
    }

    static DateTimeFormatter SLASH_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    static DateTimeFormatter NO_SEPAR_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    private LocalDate date;

    private DateTimeFormatter formatter;

    private Birthday(String date, DateTimeFormatter formatter) {
        this.formatter = formatter;
        this.date = LocalDate.parse(date, formatter);
    }

    public String date() {
        return date.format(formatter);
    }

    public static Birthday createBirthdayFromString(String date, Format format) {
        switch (format) {
            case SLASH_SEPARATED:
                return new Birthday(date, SLASH_FORMATTER);
            case NO_SEPARATOR:
                return new Birthday(date, NO_SEPAR_FORMATTER);
            default:
                throw new IllegalStateException("Format " + format + " not supported");
        }
    }

    @Override
    public String toString() {
        return "Birthday{" + date +  '}';
    }
}
