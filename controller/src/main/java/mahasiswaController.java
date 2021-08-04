import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class mahasiswaController {

    @GetMapping("/getTest")
    public Flux<Integer> getAllMhs(){
        return Flux.just(1,2,3,4).log();
    }
}

