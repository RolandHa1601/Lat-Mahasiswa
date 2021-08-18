package com.learn.reactive.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.kafka.common.protocol.types.Field.Bool;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CheckTicketStatusRequest {
  private String lastName;
  private String recordLocator;
  private String originCode;
  private Boolean isUpdationRequired;

}
