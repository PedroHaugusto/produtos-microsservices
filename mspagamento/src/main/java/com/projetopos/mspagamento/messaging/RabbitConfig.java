package com.projetopos.mspagamento.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String EXCHANGE = "pagamento.exchange";
    public static final String QUEUE_PROCESSAR = "pagamentos.registrar";
    public static final String ROUTING_PROCESSAR = "pagamento.processar";
    public static final String QUEUE_CONFIRMADO = "pagamentos.confirmado";
    public static final String ROUTING_CONFIRMADO = "pagamento.confirmado";

    @Bean
    public DirectExchange pagamentoExchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Queue queueProcessarPagamento() {
        return new Queue(QUEUE_PROCESSAR, true);
    }

    @Bean
    public Queue queuePagamentoConfirmado() {
        return new Queue(QUEUE_CONFIRMADO, true);
    }

    @Bean
    public Binding bindingProcessar(Queue queueProcessarPagamento, DirectExchange pagamentoExchange) {
        return BindingBuilder.bind(queueProcessarPagamento).to(pagamentoExchange).with(ROUTING_PROCESSAR);
    }

    @Bean
    public Binding bindingConfirmado(Queue queuePagamentoConfirmado, DirectExchange pagamentoExchange) {
        return BindingBuilder.bind(queuePagamentoConfirmado).to(pagamentoExchange).with(ROUTING_CONFIRMADO);
    }
}
