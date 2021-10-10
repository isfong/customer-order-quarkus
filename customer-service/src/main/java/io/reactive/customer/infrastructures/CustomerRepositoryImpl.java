package io.reactive.customer.infrastructures;

import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import io.reactive.customer.domain.Customer;
import io.reactive.customer.domain.repositories.CustomerRepository;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerRepositoryImpl implements CustomerRepository, PanacheRepositoryBase< Customer, Long > {
    @Override
    public Uni< Customer > save( Customer customer ) {
        return persist( customer );
    }
}
