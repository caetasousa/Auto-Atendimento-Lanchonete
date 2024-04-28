package caeta.techalanger.adapter.driver.controller.cliente;


import caeta.techalanger.adapter.driver.controller.cliente.request.ClienteRequest;
import caeta.techalanger.adapter.driver.controller.cliente.response.ClienteResponse;
import caeta.techalanger.core.application.ports.ClienteServicePort;
import caeta.techalanger.core.domain.Cliente;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {

    private final ClienteServicePort servicePort;

    public ClienteController(ClienteServicePort servicePort) {
        this.servicePort = servicePort;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ClienteResponse> criarCliente(@RequestBody @Valid ClienteRequest request) {
        Cliente novoCliente = servicePort.cadastraCliente(request.paraCliente());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{cpf}")
                .buildAndExpand(novoCliente.getCpf())
                .toUri();

        return ResponseEntity.created(location).body(ClienteResponse.paraDomainResponse(novoCliente));
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ClienteResponse> buscaClientePorCpf(@PathVariable String cpf) {
        Cliente clienteEncontrado = servicePort.consultarCliente(cpf);
        return ResponseEntity.ok(ClienteResponse.paraDomainResponse(clienteEncontrado));
    }
}
