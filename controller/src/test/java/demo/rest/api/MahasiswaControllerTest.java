package demo.rest.api;

import com.learn.reactive.LearnReactiveApplication;
import com.learn.reactive.model.Kelas;
import com.learn.reactive.model.Mahasiswa;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

@ContextConfiguration(classes= LearnReactiveApplication.class)
@ExtendWith(SpringExtension.class)
@WebFluxTest
public class MahasiswaControllerTest {

    private Mahasiswa mahasiswa;

    private WebTestClient webTestClient;

    private List<Mahasiswa> listMahasiswa;

//      @Test
//  public void getListTransactionSettingTest() {
////    PagingCommandRequest request = PagingCommandRequest.builder()
////      .page(PAGE)
////      .size(SIZE)
////      .sortBy(SORT_BY)
////      .sortDirection(SORT_DIRECTION)
////      .build();
//
//     mahasiswa = Mahasiswa.builder().nim("1").name("rlnd").GPA(3.9).build();
//    listMahasiswa = new ArrayList<Mahasiswa>();
//    listMahasiswa.add(mahasiswa);
//
//    Flux<Kelas> monoKelas = webTestClient.get().uri("/mahasiswa/get-kelas")
//            .accept(MediaType.APPLICATION_JSON_UTF8)
//            .exchange()
//            .expectStatus().isOk()
//            .returnResult(Kelas.class)
//            .getResponseBody();
//      Kelas kelas = Kelas.builder().kodeKelas("a01").namaDosen("rnd").namaKelas("a01").build();
//          StepVerifier.create(monoKelas)
//                  .expectSubscription()
//                  .expectNext(kelas)
//                  .verifyComplete();
//
////    Response<List<TransactionSettingResponse>> response = ResponseHelper.ok();
////    response.setData(transactionSettingResponses);
////
////    given(executor.execute(GetListTransactionSettingCommand.class, request))
////      .willReturn(Mono.just(response));
////
////    client.get()
////      .uri(uriBuilder -> uriBuilder
////        .path(GET_LIST_SETTING_URL)
////        .queryParam("page", PAGE)
////        .queryParam("size", SIZE)
////        .queryParam("sortBy", SORT_BY)
////        .queryParam("sortDirection", SORT_DIRECTION)
////        .build())
////      .exchange()
////      .expectStatus()
////      .isOk()
////      .expectHeader()
////      .contentType(MediaType.APPLICATION_JSON)
////      .expectBody(new ParameterizedTypeReference<Response<List<TransactionSettingResponse>>>() {})
////      .equals(response);
////
////    then(executor).should()
////      .execute(GetListTransactionSettingCommand.class, request);
//  }
}
