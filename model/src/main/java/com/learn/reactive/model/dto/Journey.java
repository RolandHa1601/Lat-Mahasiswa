package com.learn.reactive.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class Journey {
  private String actualCarrierCode;
  private String actualDepartureTime;
  private String actualOriginCode;
  private String arrivalAirportCode;
  /** after get boardingpass **/
  private List<String> checkedInPax;
  private String departureAirportCode;
  private String finalDestinationCode;
  private Boolean international;
  private Boolean hasBlockedStation;
  private Boolean hasDJRestrictEmerSeat;
  private JourneyCheckinStatus journeyCheckinStatus;
  private String journeySellKey;
  private List<Segment> segments;

  private String type;

  // after submit guestselection
  private String ArrivalAirportFullname;
  private String DepartureAirportFullname;
  private String FinalDestinationFullname;
  private String FinalDestinationType;
  private String from;
  private String FromStation;
  private Integer index;
  private Integer layovers;
  private List<Passenger> passengers;
  private String pnr;
  private Boolean takeOff; /** true if depart, and false if arrival **/
  private String to;
}
