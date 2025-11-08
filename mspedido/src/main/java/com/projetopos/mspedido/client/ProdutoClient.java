package com.projetopos.mspedido.client;

import com.projetopos.mspedido.dto.ProdutoPedidoDTO;
import com.projetopos.mspedido.dto.ProdutoUpdateQuantidadeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "msproduto")
public interface ProdutoClient {

    @GetMapping("/produtos/{id}")
    ProdutoPedidoDTO getProdutoById(@PathVariable Long id);

    @PostMapping("/produtos/ids")
    List<ProdutoPedidoDTO> getProdutosByIds(@RequestBody List<Long> ids);

    @PutMapping("/produtos/atualizar-quantidade")
    void atualizarQuantidade(@RequestBody List<ProdutoUpdateQuantidadeDTO> produtos);
}
