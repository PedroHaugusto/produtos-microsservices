package com.projetopos.mspedido.dto;

import java.math.BigDecimal;

public class PagamentoRequestDTO {
    private BigDecimal valor;
    private Long pedidoId;

    public PagamentoRequestDTO() {
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
