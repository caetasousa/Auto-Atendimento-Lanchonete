package caeta.techalanger.adapter.driven.infra.repository.produto;

import caeta.techalanger.core.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

    Optional<ProdutoEntity> findByNome(String nome);
    List<ProdutoEntity> findAllByCategoria(Categoria categoria);
}
