package com.getir.readingisgood.message;

import com.getir.readingisgood.domain.customer.command.CreateCustomer;
import com.getir.readingisgood.domain.customer.core.value.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.validation.constraints.Email;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCustomerMessage {

  @NotEmpty(message = "firstName is mandatory")
  private String firstName;

  @NotBlank(message = "lastName is mandatory")
  private String lastName;

  @NotNull(message = "address must not be null")
  @Valid
  private AddressData address;

  @Email(message = "email is mandatory and must be in proper format")
  private String email;

  @NotBlank(message = "contactNumber is mandatory")
  @Size(min = 10, max = 12, message = "contactNumber should be of length 10 to 12")
  private String contactNumber;

  @NotBlank(message = "password is mandatory")
  private String password;

  public static CreateCustomer toCreateCustomerCommand(
      CreateCustomerMessage createCustomerMessage) {
    return CreateCustomer.builder()
        .firstName(FirstName.create(createCustomerMessage.getFirstName()))
        .lastName(LastName.create(createCustomerMessage.getLastName()))
        .address(buildAddress(createCustomerMessage))
        .email(
            com.getir.readingisgood.domain.customer.core.value.Email.create(
                createCustomerMessage.getEmail()))
        .contactNumber(ContactNumber.create(createCustomerMessage.getContactNumber()))
        .password(Password.create(createCustomerMessage.getPassword()))
        .build();
  }

  protected static Address buildAddress(@NonNull CreateCustomerMessage createCustomerMessage) {
    return Address.builder()
        .city(createCustomerMessage.getAddress().getCity())
        .country(createCustomerMessage.getAddress().getCountry())
        .houseNumber(createCustomerMessage.getAddress().getHouseNumber())
        .street(createCustomerMessage.getAddress().getStreet())
        .zip(createCustomerMessage.getAddress().getZip())
        .build();
  }
}
