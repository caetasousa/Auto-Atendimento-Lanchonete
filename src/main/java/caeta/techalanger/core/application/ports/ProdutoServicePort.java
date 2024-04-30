package caeta.techalanger.core.application.ports;

import caeta.techalanger.core.domain.Categoria;
import caeta.techalanger.core.domain.Produto;

import java.util.List;

public interface ProdutoServicePort {

    Produto cadastraProduto(Produto produto);
    Produto editaProduto(Long id, Produto produto);
    void removerProduto(Long id);
    List<Produto> buscarProdutoPorCategoria(Categoria categoria);
    Produto buscarProdutoPorId(Long id);
}
