package io.reactive.customer.application;

import io.eventuate.tram.events.publisher.DomainEventPublisher;
import io.eventuate.tram.events.publisher.ResultWithEvents;
import io.reactive.customer.domain.Customer;
import io.reactive.customer.domain.repositories.CustomerRepository;
import io.reactive.customer.sdk.representations.CustomerRepresentation;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.math.BigDecimal;

@Singleton
public class CustomerApplicationService {

    @Inject
    CustomerRepository customerRepository;
    @Inject
    DomainEventPublisher domainEventPublisher;

    @Transactional
    public Uni< CustomerRepresentation > createCustomer( String name, BigDecimal creditLimit ) {
        ResultWithEvents< Customer > resultWithEvents = Customer.create( name, creditLimit );
        Customer result = resultWithEvents.result;
        this.domainEventPublisher.publish( result.getClass( ), result.id, resultWithEvents.events );
        return customerRepository.save( result )
                .map( Customer::toRepresentation );
    }
}
