package com.projetopos.mspagamento.client;

import com.projetopos.mspagamento.dto.PedidoUpdateStatusDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "mspedido")
public interface PedidoClient {

    @PutMapping("/pedidos/atualizar-status")
    void atualizarStatus(@RequestBody PedidoUpdateStatusDTO request);
}
