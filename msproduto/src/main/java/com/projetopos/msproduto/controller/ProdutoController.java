package com.projetopos.msproduto.controller;

import com.projetopos.msproduto.dto.ProdutoDTO;
import com.projetopos.msproduto.dto.ProdutoUpdateQuantidadeDTO;
import com.projetopos.msproduto.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/ids")
    public ResponseEntity<List<ProdutoDTO>> getProdutosByIds(@RequestBody List<Long> ids) {
        return ResponseEntity.ok(produtoService.getProdutosByIds(ids));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> getProdutoById(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.getProdutoById(id));
    }

    @PutMapping("/atualizar-quantidade")
    public ResponseEntity<Void> atualizarQuantidade(@RequestBody List<ProdutoUpdateQuantidadeDTO> updates) {
        produtoService.atualizarQuantidade(updates);
        return ResponseEntity.ok().build();
    }
}
