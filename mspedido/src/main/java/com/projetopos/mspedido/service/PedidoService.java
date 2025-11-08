package com.projetopos.mspedido.service;

import com.projetopos.mspedido.client.PagamentoClient;
import com.projetopos.mspedido.client.ProdutoClient;
import com.projetopos.mspedido.dto.*;
import com.projetopos.mspedido.messaging.PagamentoProducer;
import com.projetopos.mspedido.model.Pedido;
import com.projetopos.mspedido.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoClient produtoClient;

    @Autowired
    private PagamentoClient pagamentoClient;

    @Autowired
    private PagamentoProducer pagamentoProducer;

    @Transactional
    public PedidoDTO criarPedido(List<Long> idProdutos) {
        Pedido pedido = new Pedido();
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setStatus(Pedido.StatusPedido.CRIADO);
        pedido.setIdProdutos(idProdutos);
        pedido.setValorTotal(BigDecimal.ZERO);

        List<ProdutoPedidoDTO> produtos = produtoClient.getProdutosByIds(idProdutos);

        BigDecimal valorTotal = BigDecimal.ZERO;
        for (Long id : idProdutos) {
            ProdutoPedidoDTO produtoDTO = produtos.stream()
                    .filter(p -> p.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + id));

            if (produtoDTO.getQuantidade() < 1) {
                throw new RuntimeException("Estoque insuficiente para o produto: " + produtoDTO.getNome());
            }

            valorTotal = valorTotal.add(produtoDTO.getPreco());
        }

        pedido.setValorTotal(valorTotal);
        pedido = pedidoRepository.save(pedido);

        enviarMensagemPagamento(pedido.getId(), valorTotal);

        return convertToDto(pedido);
    }

    @Transactional
    public void atualizarStatus(Long pedidoId, Pedido.StatusPedido status) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado: " + pedidoId));
        pedido.setStatus(status);
        pedidoRepository.save(pedido);

        if (status == Pedido.StatusPedido.CONFIRMADO) {
            List<Long> ids = pedido.getIdProdutos();
            List<ProdutoUpdateQuantidadeDTO> updates = ids.stream().map(id -> {
                ProdutoUpdateQuantidadeDTO u = new ProdutoUpdateQuantidadeDTO();
                u.setId(id);
                u.setQuantidade(1);
                return u;
            }).collect(Collectors.toList());
            produtoClient.atualizarQuantidade(updates);
        }
    }

    private void enviarMensagemPagamento(Long pedidoId, BigDecimal valorTotal) {
        pagamentoProducer.enviarMensagem(pedidoId, valorTotal);
    }

    private PedidoDTO convertToDto(Pedido pedido) {
        PedidoDTO dto = new PedidoDTO();
        dto.setId(pedido.getId());
        dto.setDataPedido(pedido.getDataPedido());
        dto.setValorTotal(pedido.getValorTotal());
        dto.setStatus(pedido.getStatus());
        dto.setIdProdutos(pedido.getIdProdutos());
        return dto;
    }
}
