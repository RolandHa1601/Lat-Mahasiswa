package com.learn.reactive.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response {

  private String  code;
  private String message;
  private String errors;
  private RefundData data;
  private long serverTime;
}
