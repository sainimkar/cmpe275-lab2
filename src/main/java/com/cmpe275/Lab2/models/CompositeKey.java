package com.cmpe275.Lab2.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable

public class CompositeKey implements Serializable {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
    @SequenceGenerator(name = "my_seq", sequenceName = "MY_SEQUENCE", allocationSize = 1)
    protected long id;

    @Column(name = "employerId")
    protected String employerId;

    // default constructor

    public CompositeKey(){}

    public CompositeKey(long id, String employerId) {
        this.id = id;
        this.employerId = employerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositeKey compositeKey = (CompositeKey) o;
        return id == (compositeKey.id) &&
                employerId.equals(compositeKey.employerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employerId);
    }
    // equals() and hashCode()
}
