package com.learn.reactive.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
public class Passenger {
  private String dob;
  private String firstName;
  private String gender;
  private PassengerInfant infant;
  private String lastName;
  private String nationality;
  @JsonProperty("passengerAddress")
  private List<PassengerAddress> passengerAddresses;
  private String passengerNumber;
  private List<PassengerTravelDocument> passengerTravelDocuments;
  private String passengerType;
  @JsonProperty("paxFee")
  private List<PaxFee> paxFees;
  private String paxId;
  private String title;

  /** if value not null and equals "checkedin" it's mean already get boardingpass **/
  private String checkinStatus;
  private Boolean hasBlockedSSR;
  private Boolean hasInfant;
  private Boolean ictsStatus;
  private String id;
  private Boolean isYptaSSR;

  private List<BoardingPass> boardingPassList;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Boolean isSelected;

  private String residenceCountry;
}
