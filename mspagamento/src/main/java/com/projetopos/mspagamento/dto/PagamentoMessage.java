package com.projetopos.mspagamento.dto;

import java.math.BigDecimal;

public class PagamentoMessage {
    private BigDecimal valor;
    private Long pedidoId;

    public PagamentoMessage() {
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }
}
