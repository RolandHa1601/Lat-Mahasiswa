package com.learn.reactive.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckInResponse {
  private String checkedInDate;
  private String checkinStatus;
  private Boolean isCheckinSuccess;
}