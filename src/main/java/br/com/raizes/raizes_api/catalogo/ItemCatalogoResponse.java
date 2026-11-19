package br.com.raizes.raizes_api.catalogo;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public record ItemCatalogoResponse(
        @JsonProperty("id_produto") Long idProduto,
        String nome,
        BigDecimal preco,
        String categoria,
        Integer quantidade,
        Boolean disponivel
) {}
