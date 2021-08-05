package demo;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

public class testFlux {

    @Test
    public void fluxText(){
        Flux<String> test = Flux.just("a","b","c").concatWith(Flux.error(new RuntimeException("test Error")))
                .concatWith(Flux.just("dd"));
        test.subscribe(System.out::println, (e) -> System.out.println(e));
    }

    @Test
    public void fluxWithFlatmap(){
        Flux<String> test = Flux.just("a","b","c","d")
                .flatMap(
                        s-> {
                            return Flux.fromIterable(convertToList(s));
                        }
                ).map(s -> {
                    System.out.println(s);
                    return s;
                })
//                .subscribe(System.out::println)
                .log();

        StepVerifier.create(test).expectNextCount(8).verifyComplete();
//        test.subscribe(System.out::println, (e) -> System.out.println(e));
    }

    @Test
    public void fluxString(){
        Flux<String> test = Flux.fromIterable(Arrays.asList("a","b","c","d","e"))
                .window(2)
                .flatMap(s -> s.map(adsa -> adsa))
                .log();
//                .flatMap(
//                        s-> s.map(this::convertToList.subscribeOn(parallel())
//                ).map(s -> {
//                    System.out.println(s);
//                    return s;
//                })
//                .subscribe(System.out::println)

        StepVerifier.create(test).expectNextCount(5).verifyComplete();
//        test.subscribe(System.out::println, (e) -> System.out.println(e));
    }


    public List<String> convertToList(String test) {
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        return Arrays.asList(test,"new val");
    }
}
