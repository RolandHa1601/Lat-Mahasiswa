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
public class Leg {
  @JsonProperty("Lid")
  private String lid;
  private String carrierCode;
  private String equipType;
  private String etsf;
  private String flightnumber;
  private String legArrival;
  private String legDeparture;
  private String legId;
  private String prbcCode;
  private String sta;
  private String status;
  private String std;
}
