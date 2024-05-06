package caeta.techalanger.core.application.ports;

import caeta.techalanger.core.domain.Cliente;

public interface ClienteRepositoryPort {

    Cliente salvar(Cliente cliente);
    Cliente findByCPF(String cpf);
}
