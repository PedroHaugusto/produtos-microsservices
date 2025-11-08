package com.projetopos.mspedido.controller;

import com.projetopos.mspedido.dto.PedidoDTO;
import com.projetopos.mspedido.dto.PedidoUpdateStatusDTO;
import com.projetopos.mspedido.model.Pedido;
import com.projetopos.mspedido.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoDTO> criarPedido(@RequestBody List<Long> idProdutos) {
        return ResponseEntity.ok(pedidoService.criarPedido(idProdutos));
    }

    @PutMapping("/atualizar-status")
    public ResponseEntity<Void> atualizarStatus(@RequestBody PedidoUpdateStatusDTO request) {
        pedidoService.atualizarStatus(request.getPedidoId(), Pedido.StatusPedido.valueOf(request.getStatus()));
        return ResponseEntity.ok().build();
    }
}
