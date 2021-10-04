package br.com.vnrg.pporderstream.model;

import lombok.Data;

import java.io.Serializable;

// @Document(indexName = "customer")
// @TypeAlias(value = "customer")
@Data
public class Customer implements Serializable {

    // @Id
    private String id;
    // @Field(type = FieldType.Nested, includeInParent = true)
    private Pricing pricing;

}
