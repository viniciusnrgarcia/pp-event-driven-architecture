package br.com.vnrg.pporder.api.v1;

import br.com.vnrg.pporder.api.v1.request.OrderRequest;
import br.com.vnrg.pporder.producer.OrderProducer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/order")
@Tag(name = "API Order", description = "API Orders demo")
public class OrderController {

    private final OrderProducer producer;

    @Operation(description = "Create Order", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody)
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody final OrderRequest request, @RequestHeader(required = true, name = "token") String token) {
        this.producer.create(request, token);
        return ResponseEntity.ok().build();
    }

}
