package br.com.vnrg.pporder.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Order implements Serializable {
    private int amount;
    private String asset;
    private String pricingType;
    private BigDecimal value;
    private String customerId;
    private String eventId;
}
