package com.projetopos.mspagamento.service;

import com.projetopos.mspagamento.client.PedidoClient;
import com.projetopos.mspagamento.dto.PagamentoDTO;
import com.projetopos.mspagamento.dto.PedidoUpdateStatusDTO;
import com.projetopos.mspagamento.model.Pagamento;
import com.projetopos.mspagamento.model.Pagamento.StatusPagamento;
import com.projetopos.mspagamento.repository.PagamentoRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private PedidoClient pedidoClient;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public static final String PAGAMENTO_EXCHANGE = "pagamento.exchange";
    public static final String PAGAMENTO_ROUTING_KEY = "pagamento.confirmado";

    @Transactional
    public PagamentoDTO registrarPagamento(BigDecimal valor, Long pedidoId) {
        Pagamento pagamento = new Pagamento();
        pagamento.setValor(valor);
        pagamento.setPedidoId(pedidoId);
        pagamento.setDataCriacao(LocalDateTime.now());
        pagamento.setDataExpiracao(LocalDateTime.now().plusHours(24));
        pagamento.setStatus(StatusPagamento.CRIADO);
        pagamento.setCodigo(UUID.randomUUID().toString());

        pagamento = pagamentoRepository.save(pagamento);

        pagamento.setStatus(StatusPagamento.CONFIRMADO);
        pagamento = pagamentoRepository.save(pagamento);

        PedidoUpdateStatusDTO updateStatus = new PedidoUpdateStatusDTO();
        updateStatus.setPedidoId(pedidoId);
        updateStatus.setStatus("CONFIRMADO");
        pedidoClient.atualizarStatus(updateStatus);

        PagamentoDTO mensagem = convertToDto(pagamento);
        rabbitTemplate.convertAndSend(PAGAMENTO_EXCHANGE, PAGAMENTO_ROUTING_KEY, mensagem);

        return convertToDto(pagamento);
    }

    private PagamentoDTO convertToDto(Pagamento pagamento) {
        PagamentoDTO dto = new PagamentoDTO();
        dto.setId(pagamento.getId());
        dto.setValor(pagamento.getValor());
        dto.setCodigo(pagamento.getCodigo());
        dto.setDataCriacao(pagamento.getDataCriacao());
        dto.setDataExpiracao(pagamento.getDataExpiracao());
        dto.setPedidoId(pagamento.getPedidoId());
        dto.setStatus(pagamento.getStatus());
        return dto;
    }
}
