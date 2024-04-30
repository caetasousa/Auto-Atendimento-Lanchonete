package caeta.techalanger.adapter.driven.infra.repository.produto;

import caeta.techalanger.core.application.ports.ProdutoRepositoryPort;
import caeta.techalanger.core.domain.Categoria;
import caeta.techalanger.core.domain.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProdutoRepositoryAdapter implements ProdutoRepositoryPort {

    @Autowired
    ProdutoRepository repository;

    @Override
    public Produto cadastraProduto(Produto produto) {
        ProdutoEntity novoProduto = new ProdutoEntity(produto.getNome(), produto.getPreco(), produto.getCategoria());

        repository.save(novoProduto);
        return new Produto(novoProduto.getId(),
                novoProduto.getNome(),
                novoProduto.getCategoria(),
                novoProduto.getPreco());
    }

    @Override
    public Produto editaProduto(Long id, Produto produto) {
        ProdutoEntity editaProduto = repository.findById(id).orElseThrow(() -> new RuntimeException("Pdroduto não encontrado com esse id: " + id));

        editaProduto.setNome(produto.getNome());
        editaProduto.setPreco(produto.getPreco());
        editaProduto.setCategoria(produto.getCategoria());

        repository.save(editaProduto);

        return  new Produto(
                editaProduto.getId(),
                editaProduto.getNome(),
                editaProduto.getCategoria(),
                editaProduto.getPreco()
        );
    }

    @Override
    public void removerProduto(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Produto> buscarProdutoPorCategoria(Categoria categoria) {
        List<ProdutoEntity> produtoResposta = repository.findAllByCategoria(categoria);
        List<Produto> produtos = new ArrayList<>();
        for (ProdutoEntity produtoEntity : produtoResposta) {
            Produto produto = new Produto(
                    produtoEntity.getId(),
                    produtoEntity.getNome(),
                    produtoEntity.getCategoria(),
                    produtoEntity.getPreco()
            );
            produtos.add(produto);
        }

        return produtos;
    }

    @Override
    public Produto buscarProdutoPorId(Long id) {
        ProdutoEntity recuperaProduto = repository.findById(id).orElseThrow(() -> new RuntimeException("Pdroduto não encontrado com esse id: " + id));
        return new Produto(recuperaProduto.getId(), recuperaProduto.getNome(), recuperaProduto.getCategoria(), recuperaProduto.getPreco());
    }

//    @Override
//    public Produto editaProduto(Produto produto) {
//        return null;
//    }
//
//    @Override
//    public void removerProduto(Long id) {
//        repository.deleteById(id);
//    }
//
//    @Override
//    public Produto buscarProdutoPorCategoria(String categoria) {
//        ProdutoEntity produto = repository.getByCategoria(categoria);
//        return new Produto(produto.getId(),
//                produto.getNome(),
//                produto.getCategoria(),
//                produto.getPreco());
//    }
}
