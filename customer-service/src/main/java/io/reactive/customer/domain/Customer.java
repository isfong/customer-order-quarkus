package io.reactive.customer.domain;

import io.eventuate.tram.events.publisher.ResultWithEvents;
import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import io.reactive.customer.sdk.events.CustomerCreatedEvent;
import io.reactive.customer.sdk.representations.CustomerRepresentation;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Version;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter( AccessLevel.PRIVATE )
@NoArgsConstructor( access = AccessLevel.PROTECTED )
public class Customer extends PanacheEntity {
    @Version
    private Long version;
    private String name;
    private BigDecimal creditLimit;
    @ElementCollection
    private Map< Long, BigDecimal > creditReservations;

    private Customer( String name, BigDecimal creditLimit ) {
        this.name = name;
        this.creditLimit = creditLimit;
    }

    public static ResultWithEvents< Customer > create( String name, BigDecimal creditLimit ) {
        return new ResultWithEvents<>( new Customer( name, creditLimit ),
                List.of( new CustomerCreatedEvent( name, creditLimit ) ) );
    }

    public CustomerRepresentation toRepresentation( ) {
        return new CustomerRepresentation( id, version, name, creditLimit, creditReservations );
    }
}
