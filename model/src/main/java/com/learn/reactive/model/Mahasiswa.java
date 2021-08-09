package com.learn.reactive.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Mahasiswa implements Serializable {
    @Id
    private String nim;
    private String name;
    private Double GPA;
}