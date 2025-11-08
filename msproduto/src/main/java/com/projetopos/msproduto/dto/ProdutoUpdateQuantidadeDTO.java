package com.projetopos.msproduto.dto;

public class ProdutoUpdateQuantidadeDTO {
    private Long id;
    private Integer quantidade;

    public ProdutoUpdateQuantidadeDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
