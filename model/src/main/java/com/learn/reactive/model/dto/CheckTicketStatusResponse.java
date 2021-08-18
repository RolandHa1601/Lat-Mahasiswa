package com.learn.reactive.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.learn.reactive.model.CheckInResponse;
import java.util.ArrayList;
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
public class CheckTicketStatusResponse {
  private BookingContact bookingContact;
  private String bookingId;
  private BookingSum bookingSum;
  private String currencyCode;
  private Boolean isFraud;
  private String journeyExpiry;
  private String pnr;
  private Boolean success;
  private String token;
  private Boolean v2;
  private Boolean validated;
  private String error;
  private List<Journey> journeys;
  private List<Passenger> passengers;
  private List<SelectedPax> selectedPax;
  private List<Journey> trips;

  private String deviceType;
  private Boolean executeCheckinCheckout;




//  @Builder
//  @AllArgsConstructor
//  @NoArgsConstructor
//  @Data
//  public static class Segment{
//
//    private List<Passenger> passengers;
//  }
//
//  @Builder
//  @AllArgsConstructor
//  @NoArgsConstructor
//  @Data
//  public static class Passenger{
//
//    private String checkedInDate;
//    private String checkinStatus;
//    private Boolean isCheckinSuccess;
//  }
}
