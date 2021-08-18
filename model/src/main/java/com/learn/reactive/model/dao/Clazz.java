package com.learn.reactive.model.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "clazz")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Clazz extends BaseMongo {

  @Field(value = "code")
  private String code;
  @Field(value = "name")
  private String name;
}
