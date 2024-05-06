package caeta.techalanger.config;

import caeta.techalanger.core.application.ports.*;
import caeta.techalanger.core.application.services.ClienteService;

import caeta.techalanger.core.application.services.PedidoService;
import caeta.techalanger.core.application.services.ProdutoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeanConfiguration {
    private final ClienteRepositoryPort clienteRepositoryPort;
    private final ProdutoRepositoryPort produtoRepositoryPort;
    private final PedidoRepositoryPort pedidoRepositoryPort;

    public BeanConfiguration(ClienteRepositoryPort clienteRepositoryPort, ProdutoRepositoryPort produtoRepositoryPort, PedidoRepositoryPort pedidoRepositoryPort) {
        this.clienteRepositoryPort = clienteRepositoryPort;
        this.produtoRepositoryPort = produtoRepositoryPort;
        this.pedidoRepositoryPort = pedidoRepositoryPort;
    }

    @Bean
    public ClienteServicePort clienteServiceImpl(ClienteRepositoryPort clienteRepositoryPort) {
        return new ClienteService(clienteRepositoryPort);
    }

    @Bean
    public ProdutoServicePort produtoServiceImpl(ProdutoRepositoryPort produtoRepositoryPort) {
        return  new ProdutoService(produtoRepositoryPort);
    }



    @Bean
    public PedidoService pedidoService() {
        return new PedidoService(clienteRepositoryPort, produtoRepositoryPort, pedidoRepositoryPort);
    }
}
