package com.learn.reactive.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Segment {
  @JsonProperty("Fares")
  private List<Fare> fares;
  private String arrivalTime;
  private String carrierCode;
  private String departureTime;
  private String flightNumber;
  private String international;
  private String inventoryLegIds;
  private List<Leg> legs;
  private String opSuffix;
  private List<PassengerSegment> passengers;
  private List<PaxSegment> paxSegments;
  private List<Seat> seatList;
  private String segmentInfo;
  private List<Ssr> ssrList;

}
