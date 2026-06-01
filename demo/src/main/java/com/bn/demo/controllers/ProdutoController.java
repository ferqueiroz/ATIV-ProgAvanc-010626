package com.bn.demo.controllers;

import com.bn.demo.models.ProdutoModel;
import com.bn.demo.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController // Define que essa classe é relacionada a um rest controller que recebe as requisiçõe
@RequestMapping(path = {"/produtos", "/produtos/"}) // Define qual a rota padrão da classe
public class ProdutoController {

    @Autowired // Serve para fazer a injeção de dependencia,
               // o spring sabe que tem que criar uma instancia pra esse cara,
               // sem o Autowired a variavel fica como null
    private ProdutoService produtoService;

    @GetMapping // Define o endpoint de get da classe produtos
        public ResponseEntity<List<ProdutoModel> > buscarTodosOsProdutos(){
          List<ProdutoModel> requeste = produtoService.buscarTodosProdutos();
        return ResponseEntity.ok().body(requeste);
    }

    @PostMapping // Define o endpoint de post da classe de produtos
    public ResponseEntity <ProdutoModel> criarProdutos(@RequestBody ProdutoModel produtoModel){ // Recebe um JSON que no caso é o corpo da tabela de produto
        ProdutoModel requeste = produtoService.criarProduto(produtoModel);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(produtoModel.getId())
                .toUri();
        return  ResponseEntity.created(uri).body(requeste);
    }

    @DeleteMapping("/{id}") // Define o endpoint de delete da classe de produtos e define que terá uma pathvariable sendo o id do produto
    public ResponseEntity<?> deletarProdutos(@PathVariable Long id){ // a variavel que foi definida na parte de cima
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}") // Endpoint de get pelo id do produto
    public Optional<ProdutoModel> buscarProdutoPorId(@PathVariable Long id){
        return  produtoService.buscarProdutoId(id);
    }

    @PutMapping("/{id}") // Endpoint de atualizar o produto
    public ResponseEntity <ProdutoModel> atualizarProdutos(@PathVariable Long id, @RequestBody ProdutoModel ProdutoModel){
        ProdutoModel requeste = produtoService.atualizarProduto(id, ProdutoModel);
        return  ResponseEntity.ok().body(requeste);

    }
}
