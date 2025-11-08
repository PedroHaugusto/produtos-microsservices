package com.projetopos.mspagamento.dto;

import com.projetopos.mspagamento.model.Pagamento;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PagamentoDTO {
    private Long id;
    private BigDecimal valor;
    private String codigo;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataExpiracao;
    private Long pedidoId;
    private Pagamento.StatusPagamento status;

    public PagamentoDTO() {
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

    public Pagamento.StatusPagamento getStatus() {
        return status;
    }

    public void setStatus(Pagamento.StatusPagamento status) {
        this.status = status;
    }
}
