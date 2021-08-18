package com.learn.reactive.model.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseMongo {

  @Id
  @Field("_id")
  private String id;
  @Default
  @Field(value = "isDeleted")
  private int isDeleted = 0;
  @Field(value = "createdDate")
  private Date createdDate;
  @Field(value = "updateDate")
  private Date updateDate;
}
