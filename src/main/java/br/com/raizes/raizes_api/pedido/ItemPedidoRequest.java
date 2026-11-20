package br.com.raizes.raizes_api.pedido;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ItemPedidoRequest(
        @NotNull @JsonProperty("id_produto") Long idProduto,
        @NotNull @Positive Integer quantidade
) {}