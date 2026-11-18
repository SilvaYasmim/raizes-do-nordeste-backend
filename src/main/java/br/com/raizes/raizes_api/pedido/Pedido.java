package br.com.raizes.raizes_api.pedido;

import br.com.raizes.raizes_api.cliente.Cliente;
import br.com.raizes.raizes_api.loja.Loja;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Long idPedido;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_loja", nullable = false)
    private Loja loja;

    @Column(name = "codigo_retirada", nullable = false, unique = true)
    private String codigoRetirada;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CanalPedido canal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPedido status;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @Column(name = "data_hora_agendamento")
    private LocalDateTime dataHoraAgendamento;

    @Column(name = "consumir_no_local", nullable = false)
    private Boolean consumirNoLocal;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens = new ArrayList<>();

    public Pedido() {}

    public Long getIdPedido() { return idPedido; }
    public void setIdPedido(Long idPedido) { this.idPedido = idPedido; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Loja getLoja() { return loja; }
    public void setLoja(Loja loja) { this.loja = loja; }

    public String getCodigoRetirada() { return codigoRetirada; }
    public void setCodigoRetirada(String codigoRetirada) { this.codigoRetirada = codigoRetirada; }

    public CanalPedido getCanal() { return canal; }
    public void setCanal(CanalPedido canal) { this.canal = canal; }

    public StatusPedido getStatus() { return status; }
    public void setStatus(StatusPedido status) { this.status = status; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

    public LocalDateTime getDataHoraAgendamento() { return dataHoraAgendamento; }
    public void setDataHoraAgendamento(LocalDateTime dataHoraAgendamento) { this.dataHoraAgendamento = dataHoraAgendamento; }

    public Boolean getConsumirNoLocal() { return consumirNoLocal; }
    public void setConsumirNoLocal(Boolean consumirNoLocal) { this.consumirNoLocal = consumirNoLocal; }

    public List<ItemPedido> getItens() { return itens; }
    public void setItens(List<ItemPedido> itens) { this.itens = itens; }
}
