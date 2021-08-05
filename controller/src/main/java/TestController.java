import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class TestController {

    @GetMapping("/getTest")
    public Flux<Integer> getAllMhs(){
        return Flux.just(1,2,3,4).log();
    }
}

