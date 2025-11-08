package com.projetopos.mspedido.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String EXCHANGE = "pagamento.exchange";
    public static final String ROUTING_PROCESSAR = "pagamento.processar";
    public static final String QUEUE_PROCESSAR = "pagamentos.registrar";

    @Bean
    public DirectExchange pagamentoExchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Queue queueProcessar() {
        return new Queue(QUEUE_PROCESSAR, true);
    }

    @Bean
    public Binding bindingProcessar(Queue queueProcessar, DirectExchange pagamentoExchange) {
        return BindingBuilder.bind(queueProcessar).to(pagamentoExchange).with(ROUTING_PROCESSAR);
    }
}
