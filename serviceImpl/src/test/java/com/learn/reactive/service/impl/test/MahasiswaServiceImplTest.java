package com.learn.reactive.service.impl.test;

import com.learn.reactive.model.Mahasiswa;
import com.learn.reactive.repository.MahasiswaRepository;
import com.learn.reactive.service.impl.MahasiswaServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MahasiswaServiceImplTest {
    @Mock
    private MahasiswaRepository repository;

    @InjectMocks
    private MahasiswaServiceImpl mahasiswaServiceImpl;

    private Mahasiswa mahasiswa;

    private Mahasiswa mahasiswaUpdated;

    private Mahasiswa mahasiswa2;

    private Mahasiswa mahasiswa3;

    private List<Mahasiswa> listMahasiswa = new ArrayList<>();
    private Flux<Mahasiswa> fluxMahasiswa;


    @BeforeEach
    public void setUp() {
        initMocks(this);
        mahasiswa = Mahasiswa.builder().name("rl").GPA(3.3)
                .nim("26").build();

        mahasiswaUpdated = Mahasiswa.builder().name("rlnd").GPA(3.6)
                .nim("26").build();

        mahasiswa2 = Mahasiswa.builder().name("cl").GPA(3.9)
                .nim("4").build();

        mahasiswa3 = Mahasiswa.builder().name("co").GPA(3.65)
                .nim("10").build();

        listMahasiswa.add(mahasiswa);
        listMahasiswa.add(mahasiswa2);
        listMahasiswa.add(mahasiswa3);

        fluxMahasiswa = Flux.fromIterable(listMahasiswa);

    }

    @AfterEach
    public void tearDown() {
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void executeSaveMahasiswa_thenSuccess() {
        when(repository.save(mahasiswa)).thenReturn(Mono.just(mahasiswa));

        StepVerifier.create(mahasiswaServiceImpl.saveMahasiswa(mahasiswa))
                .expectNext(mahasiswa)
                .verifyComplete();

        verify(repository).save(mahasiswa);
    }

    @Test
    public void executeGetAllMahasiswa_thenSuccess() {
        when(repository.findAll()).thenReturn(fluxMahasiswa);

        StepVerifier.create(mahasiswaServiceImpl.findAllMahasiswa())
                .expectNext(mahasiswa)
                .expectNext(mahasiswa2)
                .expectNext(mahasiswa3)
                .verifyComplete();

        verify(repository).findAll();
    }

    @Test
    public void executeGetOneMahasiswa_thenSuccess() {
        when(repository.findById(mahasiswa.getNim())).thenReturn(Mono.just(mahasiswa));

        StepVerifier.create(mahasiswaServiceImpl.getOneMahasiswa(mahasiswa.getNim()))
                .expectNext(mahasiswa)
                .verifyComplete();

        verify(repository).findById(mahasiswa.getNim());
    }

    @Test
    public void executeUpdateMahasiswa_thenSuccess() {
        when(repository.findById(mahasiswaUpdated.getNim())).thenReturn(Mono.just(mahasiswa));
        when(repository.save(mahasiswaUpdated)).thenReturn(Mono.just(mahasiswaUpdated));

        StepVerifier.create(mahasiswaServiceImpl.updateMahasiswa(mahasiswaUpdated))
                .expectNext(mahasiswaUpdated)
                .verifyComplete();

        verify(repository).findById(mahasiswaUpdated.getNim());
        verify(repository).save(mahasiswaUpdated);
    }

    @Test
    public void executeDeleteMahasiswa_thenSuccess() {
        when(repository.deleteById(mahasiswa.getNim())).thenReturn(Mono.empty());

        StepVerifier.create(mahasiswaServiceImpl.deleteMahasiswa(mahasiswa.getNim()))
                .verifyComplete();

        verify(repository).deleteById(mahasiswa.getNim());
    }




}
