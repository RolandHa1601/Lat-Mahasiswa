package com.learn.reactive.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PassengerAddress {
  @JsonProperty("AddressLine1")
  private String addressLine1;

  @JsonProperty("AddressLine2")
  private String addressLine2;

  @JsonProperty("Phone")
  private String phone;

  @JsonProperty("PostalCode")
  private String postalCode;

  @JsonProperty("StationCode")
  private String stationCode;

  @JsonProperty("TypeCode")
  private String typeCode;
}
