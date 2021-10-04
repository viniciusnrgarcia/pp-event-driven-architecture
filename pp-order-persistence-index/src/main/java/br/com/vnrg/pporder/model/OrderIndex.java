package br.com.vnrg.pporder.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;

@Document(indexName = "order")
@TypeAlias(value = "order")
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderIndex extends DataIndex {

    @Id
    private String id;
    private int amout;
    private String asset;
    private String pricingType;
    private BigDecimal value;
    private String customerId;
    private String eventId;

}
