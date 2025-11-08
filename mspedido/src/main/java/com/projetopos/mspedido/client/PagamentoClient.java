package com.projetopos.mspedido.client;

import com.projetopos.mspedido.dto.PagamentoRequestDTO;
import com.projetopos.mspedido.dto.PagamentoResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "mspagamento")
public interface PagamentoClient {

    @PostMapping("/pagamentos/registrar")
    PagamentoResponseDTO registrarPagamento(@RequestBody PagamentoRequestDTO request);
}
