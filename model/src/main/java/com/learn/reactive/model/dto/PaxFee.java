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
public class PaxFee {
  @JsonProperty("ActionStatusCode")
  private String actionStatusCode;

  @JsonProperty("CreatedDate")
  private String createdDate;

  @JsonProperty("FeeApplicationType")
  private String feeApplicationType;

  @JsonProperty("FeeCode")
  private String feeCode;

  @JsonProperty("FeeDetail")
  private String feeDetail;

  @JsonProperty("FeeNumber")
  private String feeNumber;

  @JsonProperty("FeeOverride")
  private String feeOverride;

  @JsonProperty("FeeType")
  private String feeType;

  @JsonProperty("FlightReference")
  private String flightReference;

  @JsonProperty("IsProtected")
  private String isProtected;

  @JsonProperty("Note")
  private String note;

  @JsonProperty("PaymentNumber")
  private String paymentNumber;

  @JsonProperty("SSRCode")
  private String ssrCode;

  @JsonProperty("SSRNumber")
  private String ssrNumber;

  @JsonProperty("State")
  private String state;

}
