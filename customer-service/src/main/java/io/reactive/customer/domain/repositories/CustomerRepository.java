package io.reactive.customer.domain.repositories;

import io.reactive.customer.domain.Customer;
import io.smallrye.mutiny.Uni;

public interface CustomerRepository {
    Uni< Customer > save( Customer customer );
}
