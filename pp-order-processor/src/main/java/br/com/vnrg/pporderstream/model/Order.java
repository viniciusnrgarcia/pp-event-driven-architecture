package br.com.vnrg.pporderstream.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Order implements Serializable {
    private int amount;
    private String customerId;
    private String asset;
    private String pricingType;
    private BigDecimal value;

}
