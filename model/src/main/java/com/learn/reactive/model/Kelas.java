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
public class Kelas {
    private String namaKelas;
    @Id
    private String kodeKelas;
    private String namaDosen;


}
