package br.com.raizes.raizes_api.pedido;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public record ItemPedidoResponse(
        @JsonProperty("id_item_pedido") Long idItemPedido,
        @JsonProperty("id_produto") Long idProduto,
        Integer quantidade,
        @JsonProperty("preco_unitario") BigDecimal precoUnitario
) {}
