package caeta.techalanger.core.application.ports;

import caeta.techalanger.core.domain.Categoria;
import caeta.techalanger.core.domain.Produto;

import java.util.List;

public interface ProdutoRepositoryPort {

    Produto salvar(Produto produto);
    Produto update(Long id, Produto produto);
    void delete(Long id);
    List<Produto> findByCategoria(Categoria categoria);
    Produto findById(Long id);
}


