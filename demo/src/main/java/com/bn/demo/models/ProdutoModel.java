package com.bn.demo.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity // Serve para criar uma entidade no banco como por exempo da classe aqui é a de produto
@Table(name = "TB_PRODUTO") // Serve para configurar a tabela no banco como por exemplo ai o nome da tabela
public class ProdutoModel {

    @Id // Declara qual é a variavel que é definida como o id da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Declara que ela vai se auto incrementar
    private Long id;
    private String nome;
    private BigDecimal preco;
    private Integer estoque;

    ProdutoModel () {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPreco() { return preco; }

    public void setPreco(BigDecimal preco) { this.preco = preco; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public Integer getEstoque() { return estoque; }

    public void setEstoque(Integer estoque) { this.estoque = estoque; }
}
