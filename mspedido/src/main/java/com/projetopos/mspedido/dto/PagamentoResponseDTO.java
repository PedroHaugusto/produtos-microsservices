package com.projetopos.mspedido.dto;

import java.time.LocalDateTime;

public class PagamentoResponseDTO {
    private Long id;
    private String codigo;
    private LocalDateTime dataExpiracao;

    public PagamentoResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDateTime getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(LocalDateTime dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }
}
