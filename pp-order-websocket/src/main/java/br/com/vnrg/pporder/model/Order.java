package br.com.vnrg.pporder.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class Order implements Serializable {
    @JsonIgnoreProperties
    private String token;
    private String asset;
    private int amount;
    private String customer;
}
