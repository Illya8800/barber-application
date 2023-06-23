package com.barber.hopak.model;

public interface Entity<Dto> {
    Dto toDto();
    boolean equals(Object o);
    int hashCode();
}
