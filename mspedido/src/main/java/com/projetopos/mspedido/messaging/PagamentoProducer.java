package com.projetopos.mspedido.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PagamentoProducer {

    public static final String PAGAMENTO_EXCHANGE = "pagamento.exchange";
    public static final String PAGAMENTO_ROUTING_KEY_PROCESSAR = "pagamento.processar";

    private final RabbitTemplate rabbitTemplate;

    public PagamentoProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarMensagem(Long pedidoId, BigDecimal valorTotal) {
        PagamentoProcessarMessage message = new PagamentoProcessarMessage();
        message.setPedidoId(pedidoId);
        message.setValor(valorTotal);
        rabbitTemplate.convertAndSend(PAGAMENTO_EXCHANGE, PAGAMENTO_ROUTING_KEY_PROCESSAR, message);
    }
}
