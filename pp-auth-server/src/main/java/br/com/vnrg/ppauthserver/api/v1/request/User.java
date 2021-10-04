package br.com.vnrg.ppauthserver.api.v1.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Data
public class User {

    @NotEmpty
    private String username;
    @NotNull
    private String password;
}
