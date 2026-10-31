package com.shashi.bookmovie.Entity;

import jakarta.persistence.EnumType;
import jakarta.persistence.EnumeratedValue;


public enum Status {
    PENDING,
    SUCCESS,
    FAILED,
    REFUNDED,
}
