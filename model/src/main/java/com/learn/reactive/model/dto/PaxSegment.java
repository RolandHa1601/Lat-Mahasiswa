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
public class PaxSegment {
  @JsonProperty("BoardingSequence")
  private String boardingSequence;

  @JsonProperty("LiftStatus")
  private String liftStatus;

  @JsonProperty("PassengerID")
  private String passengerID;

  @JsonProperty("PassengerNumber")
  private String passengerNumber;
}
