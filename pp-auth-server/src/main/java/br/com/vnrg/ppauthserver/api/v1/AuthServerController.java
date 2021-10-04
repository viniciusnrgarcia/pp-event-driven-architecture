package br.com.vnrg.ppauthserver.api.v1;

import br.com.vnrg.ppauthserver.api.v1.request.User;
import br.com.vnrg.ppauthserver.service.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/auth")
@Tag(name = "API Auth", description = "API Auth demo")
public class AuthServerController {

    private final AuthenticationService authentication;

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody final User user) {
        // mock authetication
        return ResponseEntity.ok(this.authentication.getToken(user.getUsername()));
    }

    // @GetMapping
    public ResponseEntity<String> validate(@RequestParam String token) {
        return ResponseEntity.ok(this.authentication.validate(token));
    }

}
