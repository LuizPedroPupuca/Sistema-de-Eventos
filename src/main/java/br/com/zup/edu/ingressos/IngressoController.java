package br.com.zup.edu.ingressos;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
public class IngressoController {

    private final IngressoRepository ingressoRepository;

    public IngressoController(IngressoRepository ingressoRepository) {
        this.ingressoRepository = ingressoRepository;
    }

    @PostMapping("/ingresso")
    public ResponseEntity<?> cadastra(@RequestBody @Valid IngressoRequest ingressoRequest,
                                      UriComponentsBuilder uriComponentsBuilder){
        if(ingressoRepository.existsByNumeroAndData(ingressoRequest.getNumero(),
                ingressoRequest.getData())){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Número e data já existentes!!!");
        }

        Ingresso ingresso = ingressoRequest.toModel();

        ingressoRepository.save(ingresso);

        URI location = uriComponentsBuilder.path("/ingresso/{id}").
                buildAndExpand(ingresso.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> errorUniqueException(ConstraintViolationException e, WebRequest request){
        Map<String, Object> body = Map.of(
                "code",422,
                "error", "UNPROCESSABLE_ENTITY",
                "timestamp", LocalDateTime.now(),
                "path", request.getDescription(false).replace("uri=",""),
                "message", "Número e data já existentes"
        );
        return ResponseEntity.unprocessableEntity().body(body);
    }
}
