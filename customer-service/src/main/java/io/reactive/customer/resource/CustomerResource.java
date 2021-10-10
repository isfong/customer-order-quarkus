package io.reactive.customer.resource;

import io.reactive.customer.application.CustomerApplicationService;
import io.reactive.customer.sdk.commands.CreateCustomerCommand;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.net.URI;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@ApplicationScoped
@Path( "/customers" )
@Consumes( APPLICATION_JSON )
@Produces( APPLICATION_JSON )
public class CustomerResource {
    @Inject
    CustomerApplicationService customerApplicationService;

    @POST
    public Uni< Response > createCustomer( @Valid CreateCustomerCommand command ) {
        return this.customerApplicationService
                .createCustomer( command.getName( ), command.getCreditLimit( ) )
                .onItem( )
                .transform( created -> Response
                        .created( URI.create( "/customers/" + created.getId( ) ) )
                        .entity( created )
                        .build( ) );
    }
}
