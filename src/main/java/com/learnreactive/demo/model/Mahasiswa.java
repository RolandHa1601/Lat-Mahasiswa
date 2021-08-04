package com.learnreactive.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
public class Mahasiswa {
    @Id
    private String nim;
    private String name;
    private Double GPA;
}
