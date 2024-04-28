package caeta.techalanger.core.application.ports;

import caeta.techalanger.core.domain.Cliente;

public interface ClienteServicePort {

    Cliente cadastraCliente(Cliente cliente);
    Cliente consultarCliente(String cpf);
}
