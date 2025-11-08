package com.projetopos.mspedido.messaging;

import java.math.BigDecimal;

public class PagamentoProcessarMessage {
    private BigDecimal valor;
    private Long pedidoId;

    public PagamentoProcessarMessage() {
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
