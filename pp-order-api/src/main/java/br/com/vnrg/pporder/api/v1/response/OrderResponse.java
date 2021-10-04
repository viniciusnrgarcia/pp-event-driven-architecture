package br.com.vnrg.pporder.api.v1.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderResponse {
    String protocol;
}
