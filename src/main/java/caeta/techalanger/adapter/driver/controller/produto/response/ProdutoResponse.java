package caeta.techalanger.adapter.driver.controller.produto.response;

import caeta.techalanger.core.domain.Categoria;
import caeta.techalanger.core.domain.Produto;

import java.math.BigDecimal;

public record ProdutoResponse(Long id, String nome, BigDecimal preco, Categoria categoria) {

    public static ProdutoResponse paraDomainResponse(Produto produto) {
        return new ProdutoResponse(produto.getId(), produto.getNome(), produto.getPreco(), produto.getCategoria());
    }
}