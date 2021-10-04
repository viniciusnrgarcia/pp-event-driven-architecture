package br.com.vnrg.pporder.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Builder
@Data
@EqualsAndHashCode
public class Order implements Serializable {
    private String customerId;
    private String asset;
    private int amount;
}
