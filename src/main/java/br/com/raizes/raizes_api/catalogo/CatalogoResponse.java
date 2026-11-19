package br.com.raizes.raizes_api.catalogo;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record CatalogoResponse(
        @JsonProperty("id_loja") Long idLoja,
        @JsonProperty("nome_loja") String nomeLoja,
        List<ItemCatalogoResponse> produtos
) {}
