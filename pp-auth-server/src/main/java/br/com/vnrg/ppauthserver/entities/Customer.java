package br.com.vnrg.ppauthserver.entities;

import lombok.Data;

import java.io.Serializable;

// @Document(indexName = "customer")
// @TypeAlias(value = "customer")
@Data
public class Customer extends DataIndex implements Serializable {

    // @Id
    private String id;

    // @Field(type = FieldType.Nested, includeInParent = true)
    private Pricing pricing;

}
