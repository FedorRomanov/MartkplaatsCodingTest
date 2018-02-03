package com.ebay.assignment.model;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(exclude={"hasQuotes"})
public class Name {

    String name;
    boolean hasQuotes;

    public Name(String name) {
        if(name.startsWith("\"") && name.endsWith("\"")) {
            this.name = name.substring(1, name.length() - 1);
            hasQuotes = true;
        } else {
            this.name = name;
        }
    }

    @Override
    public String toString() {
        if(hasQuotes)
            return "\"" + name + "\"";
        return name;
    }

}
