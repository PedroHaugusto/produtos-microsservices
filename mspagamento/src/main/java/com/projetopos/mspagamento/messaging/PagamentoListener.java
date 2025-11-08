package com.projetopos.mspagamento.messaging;

import com.projetopos.mspagamento.dto.PagamentoMessage;
import com.projetopos.mspagamento.service.PagamentoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PagamentoListener {

    private final PagamentoService pagamentoService;

    public PagamentoListener(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_PROCESSAR)
    public void onMessage(PagamentoMessage message) {
        pagamentoService.registrarPagamento(message.getValor(), message.getPedidoId());
    }
}
