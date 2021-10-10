package io.reactive.customer.sdk.representations;

import lombok.Value;

import java.math.BigDecimal;
import java.util.Map;

@Value
public class CustomerRepresentation {
    long id;
    long version;
    String name;
    BigDecimal creditLimit;
    Map< Long, BigDecimal > creditReservations;
}
