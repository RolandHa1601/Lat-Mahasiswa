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
public class JourneyCheckinStatus {
  private Boolean airAsiaX;
  private String daysOrHours;
  private Boolean flightFlown;
  private Boolean isCanceled;
  private Boolean status;
  private Integer timeLeft;
  private Boolean upcoming;
  private String utcDepart;
}
