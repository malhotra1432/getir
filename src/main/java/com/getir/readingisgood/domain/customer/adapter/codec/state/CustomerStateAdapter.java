package com.getir.readingisgood.domain.customer.adapter.codec.state;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.getir.readingisgood.domain.customer.adapter.entity.CustomerStateEntity;
import com.getir.readingisgood.domain.customer.core.CustomerState;
import com.getir.readingisgood.domain.customer.core.value.*;
import java.time.Instant;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
public class CustomerStateAdapter {

  private final ObjectMapper objectMapper;

  public CustomerStateAdapter(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  public CustomerState decode(CustomerStateEntity entity) {
    return CustomerState.builder()
        .customerId(CustomerId.create(entity.getId()))
        .firstName(FirstName.create(entity.getFirstName()))
        .lastName(LastName.create(entity.getLastName()))
        .email(Email.create(entity.getEmail()))
        .contactNumber(ContactNumber.create(entity.getContactNumber()))
        .address(decodeAddress(entity.getAddress()))
        .creationDate(entity.getCreatedAt())
        .build();
  }

  private Address decodeAddress(String address) {
    var payload = asObject(address, new TypeReference<AddressPayload>() {});
    return Address.builder()
        .street(payload.street)
        .houseNumber(payload.houseNumber)
        .zip(payload.zip)
        .city(payload.city)
        .country(payload.country)
        .build();
  }

  private <T> T asObject(String value, TypeReference<T> type) {
    try {
      return objectMapper.readValue(value, type);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Error while decoding");
    }
  }

  public CustomerStateEntity encode(CustomerState customerState) {
    return CustomerStateEntity.builder()
        .firstName(customerState.getFirstName().getValue())
        .lastName(customerState.getLastName().getValue())
        .email(customerState.getEmail().getValue())
        .contactNumber(customerState.getContactNumber().getValue())
        .address(asString(customerState.getAddress()))
        .createdAt(Instant.now())
        .password(customerState.getPassword().getValue())
        .build();
  }

  private <T> String asString(T obj) {
    try {
      return objectMapper.writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Error while encoding");
    }
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  static class AddressPayload {
    @NonNull String street;
    @NonNull String houseNumber;
    @NonNull String zip;
    @NonNull String city;
    @NonNull String country;
  }
}
