package caeta.techalanger.adapter.driven.infra.repository.pedido;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoItemRepository extends JpaRepository<PedidoEntityItem, Long> {
}
