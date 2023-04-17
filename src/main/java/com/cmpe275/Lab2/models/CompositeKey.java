package com.cmpe275.Lab2.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.IdClass;

import java.io.Serializable;

//@IdClass(Employee.class)
public class CompositeKey implements Serializable {
    protected long id;

    protected String employerId;

    // default constructor

    public CompositeKey(){}

    public CompositeKey(long id, String employerId) {
        this.id = id;
        this.employerId = employerId;
    }

    // equals() and hashCode()
}
