package io.reactive.customer.sdk.events;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.math.BigDecimal;


@Value
@EqualsAndHashCode( callSuper = true )
public class CustomerCreatedEvent extends CustomerDomainEvent {
    String name;
    BigDecimal creditLimit;
}
