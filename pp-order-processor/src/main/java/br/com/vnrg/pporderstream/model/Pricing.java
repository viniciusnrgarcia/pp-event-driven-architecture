package br.com.vnrg.pporderstream.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Pricing implements Serializable {
    private String pricingType;
    private String asset;
    private BigDecimal value;
}
