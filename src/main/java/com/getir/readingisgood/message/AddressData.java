package com.getir.readingisgood.message;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressData {
  @NotBlank(message = "street is mandatory")
  private String street;

  @NotBlank(message = "houseNumber is mandatory")
  private String houseNumber;

  @NotBlank(message = "zip is mandatory")
  @Size(min = 6, max = 6, message = "zip should be of length 5")
  private String zip;

  @NotBlank(message = "city is mandatory")
  private String city;

  @NotBlank(message = "country is mandatory")
  private String country;
}
