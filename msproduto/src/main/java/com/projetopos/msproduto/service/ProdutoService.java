package com.projetopos.msproduto.service;

import com.projetopos.msproduto.dto.ProdutoDTO;
import com.projetopos.msproduto.dto.ProdutoUpdateQuantidadeDTO;
import com.projetopos.msproduto.model.Produto;
import com.projetopos.msproduto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoDTO> getProdutosByIds(List<Long> ids) {
        List<Produto> produtos = produtoRepository.findAllByIdIn(ids);
        return produtos.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public ProdutoDTO getProdutoById(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + id));
        return convertToDto(produto);
    }

    @Transactional
    public void atualizarQuantidade(List<ProdutoUpdateQuantidadeDTO> updates) {
        for (ProdutoUpdateQuantidadeDTO update : updates) {
            Produto produto = produtoRepository.findById(update.getId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + update.getId()));

            if (produto.getQuantidade() < update.getQuantidade()) {
                throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getNome());
            }

            produto.setQuantidade(produto.getQuantidade() - update.getQuantidade());
            produtoRepository.save(produto);
        }
    }

    private ProdutoDTO convertToDto(Produto produto) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setDescricao(produto.getDescricao());
        dto.setPreco(produto.getPreco());
        dto.setQuantidade(produto.getQuantidade());
        return dto;
    }
}
