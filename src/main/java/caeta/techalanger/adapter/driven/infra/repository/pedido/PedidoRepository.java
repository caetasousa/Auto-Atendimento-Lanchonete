package caeta.techalanger.adapter.driven.infra.repository.pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {

    @Query("SELECT p FROM PedidoEntity p JOIN FETCH p.itens")
    List<PedidoEntity> findAll();
}
