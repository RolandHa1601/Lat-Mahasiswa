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
public class BookingSum {
  @JsonProperty("AlternateCurrencyBalanceDue")
  private String alternateCurrencyBalanceDue;

  @JsonProperty("AlternateCurrencyCode")
  private String alternateCurrencyCode;

  @JsonProperty("AuthorizedBalanceDue")
  private String authorizedBalanceDue;

  @JsonProperty("BalanceDue")
  private String balanceDue;

  @JsonProperty("PassiveSegmentCount")
  private String passiveSegmentCount;

  @JsonProperty("PointsBalanceDue")
  private String pointsBalanceDue;

  @JsonProperty("SegmentCount")
  private String segmentCount;

  @JsonProperty("TotalCost")
  private String totalCost;

  @JsonProperty("TotalPointCost")
  private String totalPointCost;
}
