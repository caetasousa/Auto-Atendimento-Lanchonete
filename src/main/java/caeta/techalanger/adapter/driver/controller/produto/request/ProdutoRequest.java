package caeta.techalanger.adapter.driver.controller.produto.request;

import caeta.techalanger.adapter.driver.controller.produto.validator.UniqueNome;
import caeta.techalanger.core.domain.Categoria;
import caeta.techalanger.core.domain.Produto;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ProdutoRequest {

    @NotBlank
    @UniqueNome
    private String nome;
    @NotNull
    private BigDecimal preco;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @Deprecated
    public ProdutoRequest() {
    }

    public ProdutoRequest(String nome, BigDecimal preco, Categoria categoria) {
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
    }

    public Produto paraProduto(){
        return new Produto(nome, categoria, preco);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
