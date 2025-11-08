package com.projetopos.mspagamento.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal valor;
    private String codigo;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataExpiracao;
    private Long pedidoId;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

    public enum StatusPagamento {
        CRIADO,
        CONFIRMADO,
        CANCELADO
    }

    public Pagamento() {
    }

    public Pagamento(Long id, BigDecimal valor, String codigo, LocalDateTime dataCriacao, 
                     LocalDateTime dataExpiracao, Long pedidoId, StatusPagamento status) {
        this.id = id;
        this.valor = valor;
        this.codigo = codigo;
        this.dataCriacao = dataCriacao;
        this.dataExpiracao = dataExpiracao;
        this.pedidoId = pedidoId;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(LocalDateTime dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public StatusPagamento getStatus() {
        return status;
    }

    public void setStatus(StatusPagamento status) {
        this.status = status;
    }
}
