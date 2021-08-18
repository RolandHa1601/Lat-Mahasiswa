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
public class Ssr {
  @JsonProperty("Amount")
  private String amount;
  @JsonProperty("CompartmentDesignator")
  private String compartmentDesignator;
  @JsonProperty("PassengerNumber")
  private String passengerNumber;
  @JsonProperty("UnitDesignator")
  private String unitDesignator;
  @JsonProperty("LegId")
  private String legId;
}
