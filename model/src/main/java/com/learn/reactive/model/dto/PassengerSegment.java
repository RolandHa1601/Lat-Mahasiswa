package com.learn.reactive.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PassengerSegment {
  private String checkinStatus;
  private Boolean hasBlockedSSR;
  private Boolean hasInfant;
  private Boolean ictsStatus;
  private String id;
  private Boolean isYptaSSR;
  private String passengerNumber;
}
