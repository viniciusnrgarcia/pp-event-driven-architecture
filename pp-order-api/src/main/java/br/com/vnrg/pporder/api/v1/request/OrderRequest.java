package br.com.vnrg.pporder.api.v1.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Data
public class OrderRequest {

    @NotEmpty
    private String asset;
    @NotNull
    private int amount;
}
