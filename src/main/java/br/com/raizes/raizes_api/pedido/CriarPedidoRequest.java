package br.com.raizes.raizes_api.pedido;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record CriarPedidoRequest(
        @NotNull @JsonProperty("id_cliente") Long idCliente,
        @NotNull @JsonProperty("id_loja") Long idLoja,
        @NotNull CanalPedido canal,
        @Future @JsonProperty("data_hora_agendamento") LocalDateTime dataHoraAgendamento,
        @NotNull @JsonProperty("consumir_no_local") Boolean consumirNoLocal,
        @NotEmpty @Valid List<ItemPedidoRequest> itens
) {}