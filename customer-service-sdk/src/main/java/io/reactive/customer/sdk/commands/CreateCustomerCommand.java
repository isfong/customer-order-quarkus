package io.reactive.customer.sdk.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class CreateCustomerCommand {
    @NotBlank
    String name;
    @NotNull
    BigDecimal creditLimit;
}
