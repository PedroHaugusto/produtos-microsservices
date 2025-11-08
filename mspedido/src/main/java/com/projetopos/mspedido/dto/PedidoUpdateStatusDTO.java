package com.projetopos.mspedido.dto;

public class PedidoUpdateStatusDTO {
    private Long pedidoId;
    private String status;

    public PedidoUpdateStatusDTO() {
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
