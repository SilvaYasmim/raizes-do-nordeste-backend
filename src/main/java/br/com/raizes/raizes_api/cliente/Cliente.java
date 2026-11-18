package br.com.raizes.raizes_api.cliente;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;

    private String login;

    private String hash;

    @Column(nullable = false)
    private String nome;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    private String email;

    private String contato;

    private String cpf;

    private Boolean convidado;

    @Column(name = "pontos_do_cliente")
    private Integer pontosDoCliente;

    public Cliente() {}

    public Long getIdCliente() { return idCliente; }
    public void setIdCliente(Long idCliente) { this.idCliente = idCliente; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getHash() { return hash; }
    public void setHash(String hash) { this.hash = hash; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getContato() { return contato; }
    public void setContato(String contato) { this.contato = contato; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public Boolean getConvidado() { return convidado; }
    public void setConvidado(Boolean convidado) { this.convidado = convidado; }

    public Integer getPontosDoCliente() { return pontosDoCliente; }
    public void setPontosDoCliente(Integer pontosDoCliente) { this.pontosDoCliente = pontosDoCliente; }
}