package com.projetopos.mspedido.dto;

import com.projetopos.mspedido.model.Pedido;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PedidoDTO {
    private Long id;
    private LocalDateTime dataPedido;
    private BigDecimal valorTotal;
    private Pedido.StatusPedido status;
    private List<Long> idProdutos;

    public PedidoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Pedido.StatusPedido getStatus() {
        return status;
    }

    public void setStatus(Pedido.StatusPedido status) {
        this.status = status;
    }

    public List<Long> getIdProdutos() {
        return idProdutos;
    }

    public void setIdProdutos(List<Long> idProdutos) {
        this.idProdutos = idProdutos;
    }
}
