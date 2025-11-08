package com.projetopos.mspagamento.controller;

import com.projetopos.mspagamento.dto.PagamentoDTO;
import com.projetopos.mspagamento.dto.PagamentoRequestDTO;
import com.projetopos.mspagamento.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping("/registrar")
    public ResponseEntity<PagamentoDTO> registrarPagamento(@RequestBody PagamentoRequestDTO request) {
        PagamentoDTO pagamento = pagamentoService.registrarPagamento(request.getValor(), request.getPedidoId());
        return ResponseEntity.ok(pagamento);
    }
}
