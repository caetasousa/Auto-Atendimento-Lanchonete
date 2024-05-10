package caeta.techalanger.adapter.driven.infra.repository.cliente;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    Optional<ClienteEntity> findByCpf(String cpf);
}
