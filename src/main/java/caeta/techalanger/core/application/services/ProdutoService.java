package caeta.techalanger.core.application.services;

import caeta.techalanger.core.application.ports.ProdutoRepositoryPort;
import caeta.techalanger.core.application.ports.ProdutoServicePort;
import caeta.techalanger.core.domain.Categoria;
import caeta.techalanger.core.domain.Produto;

import java.util.List;

public class ProdutoService implements ProdutoServicePort {

    public final ProdutoRepositoryPort repository;

    public ProdutoService(ProdutoRepositoryPort repository) {
        this.repository = repository;
    }


    @Override
    public Produto cadastraProduto(Produto produto) {
        return repository.salvar(produto);
    }

    @Override
    public Produto editaProduto(Long id, Produto produto) {
        return repository.update(id, produto);
    }

    @Override
    public void removerProduto(Long id) {
        repository.delete(id);
    }

    @Override
    public List<Produto> buscarProdutoPorCategoria(Categoria categoria) {
        return repository.findByCategoria(categoria);
    }

    @Override
    public Produto buscarProdutoPorId(Long id) {
        return repository.findById(id);
    }
}
