package br.com.vnrg.pporder.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Document(collection = "order")
@CompoundIndexes({
        @CompoundIndex(name = "eventId_index_10", def = "{ 'eventId': 1, 'customerId': 1 }", unique = true)
})
public class Order implements Serializable {
    @Id
    @Setter(AccessLevel.PACKAGE)
    private ObjectId _id;
    private int amount;
    private String asset;
    private String pricingType;
    private BigDecimal value;
    private String customerId;
    private String eventId;
    private LocalDateTime creation;
}
