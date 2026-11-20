package br.com.raizes.raizes_api.pedido;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponse(
        @JsonProperty("id_pedido") Long idPedido,
        @JsonProperty("id_cliente") Long idCliente,
        @JsonProperty("id_loja") Long idLoja,
        @JsonProperty("codigo_retirada") String codigoRetirada,
        CanalPedido canal,
        StatusPedido status,
        @JsonProperty("data_hora") LocalDateTime dataHora,
        @JsonProperty("data_hora_agendamento") LocalDateTime dataHoraAgendamento,
        @JsonProperty("consumir_no_local") Boolean consumirNoLocal,
        List<ItemPedidoResponse> itens
) {}
