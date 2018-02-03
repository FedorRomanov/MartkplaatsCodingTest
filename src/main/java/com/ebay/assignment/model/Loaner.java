package com.ebay.assignment.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class Loaner {
    final Name name;

    public final String address;

    public final String postcode;

    public final String phone;

    final CreditLimit creditLimit;

    final Birthday birthday;

    String originWorkbook;

    public Loaner(String name, String address, String postcode, String phone, CreditLimit creditLimit, Birthday birthday) {
        this.name = new Name(name);
        this.address = address;
        this.postcode = postcode;
        this.phone = phone;
        this.creditLimit = creditLimit;
        this.birthday = birthday;
    }

    public String getBirthday(){
        return birthday.date();
    }

    public String getCreditLimit(){
        return creditLimit.formatCreditLimit();
    }

    public boolean isTheSameLoaner(Loaner anotherLoaner) {
        if (this == anotherLoaner) {
            return true;
        }
        if (!name.equals(anotherLoaner.name)) {
            return false;
        }
        if (!address.equals(anotherLoaner.address)) {
            return false;
        }
        if (!postcode.equals(anotherLoaner.postcode)) {
            return false;
        }
        if (!phone.equals(anotherLoaner.phone)) {
            return false;
        }
        return birthday.equals(anotherLoaner.birthday);
    }

    public void setOriginWorkbook(String originWorkbook) {
        this.originWorkbook = originWorkbook;
    }

    public boolean hasEqualCreditLimit(Loaner loaner) {
        return creditLimit.equals(loaner.creditLimit);
    }

    public String getName(){
        return name.toString();
    }

    public String getOriginWorkbook() {
        return originWorkbook;
    }

}
