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
public class PassengerInfant {
  private String dob;
  private String firstName;
  private String gender;
  private String infantTravelDocuments;
  private String lastName;
  private String nationality;
  private String residenceCountry;
  private String title;
}
