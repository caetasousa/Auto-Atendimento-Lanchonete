package caeta.techalanger.adapter.driven.infra.repository.produto;

import caeta.techalanger.core.domain.Categoria;
import caeta.techalanger.core.domain.Produto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "produto")
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotNull
    private BigDecimal preco;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @Deprecated
    public ProdutoEntity() {
    }

    public ProdutoEntity(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
        this.categoria = produto.getCategoria();
    }

    public Produto paraProduto() {
        return new Produto(id, nome, categoria, preco);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
