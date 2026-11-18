package br.com.raizes.raizes_api.loja;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "loja")
public class Loja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_loja")
    private Long idLoja;

    @Column(nullable = false)
    private String nome;

    private String regiao;

    private String dono;

    @Column(name = "horario_abertura")
    private LocalTime horarioAbertura;

    @Column(name = "horario_fechamento")
    private LocalTime horarioFechamento;

    @Column(name = "horario_pedidos")
    private LocalTime horarioPedidos;

    public Loja() {}

    // Getters e Setters
    public Long getIdLoja() { return idLoja; }
    public void setIdLoja(Long idLoja) { this.idLoja = idLoja; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getRegiao() { return regiao; }
    public void setRegiao(String regiao) { this.regiao = regiao; }

    public String getDono() { return dono; }
    public void setDono(String dono) { this.dono = dono; }

    public LocalTime getHorarioAbertura() { return horarioAbertura; }
    public void setHorarioAbertura(LocalTime horarioAbertura) { this.horarioAbertura = horarioAbertura; }

    public LocalTime getHorarioFechamento() { return horarioFechamento; }
    public void setHorarioFechamento(LocalTime horarioFechamento) { this.horarioFechamento = horarioFechamento; }

    public LocalTime getHorarioPedidos() { return horarioPedidos; }
    public void setHorarioPedidos(LocalTime horarioPedidos) { this.horarioPedidos = horarioPedidos; }
}