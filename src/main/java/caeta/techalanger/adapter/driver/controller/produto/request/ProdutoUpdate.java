package caeta.techalanger.adapter.driver.controller.produto.request;

import caeta.techalanger.adapter.driver.controller.produto.validator.UniqueNome;
import caeta.techalanger.core.domain.Categoria;
import caeta.techalanger.core.domain.Produto;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;

public record ProdutoUpdate(String nome, BigDecimal preco, @Enumerated(EnumType.STRING) Categoria categoria) {
    public Produto paraProduto(){
        return new Produto(nome, categoria, preco);
    }
}
