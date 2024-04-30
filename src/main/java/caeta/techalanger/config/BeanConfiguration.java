package caeta.techalanger.config;

import caeta.techalanger.core.application.ports.ClienteRepositoryPort;
import caeta.techalanger.core.application.ports.ClienteServicePort;
import caeta.techalanger.core.application.ports.ProdutoRepositoryPort;
import caeta.techalanger.core.application.ports.ProdutoServicePort;
import caeta.techalanger.core.application.services.ClienteService;

import caeta.techalanger.core.application.services.ProdutoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeanConfiguration {

    @Bean
    public ClienteServicePort clienteServiceImpl(ClienteRepositoryPort clienteRepositoryPort) {
        return new ClienteService(clienteRepositoryPort);
    }

    @Bean
    public ProdutoServicePort produtoServiceImpl(ProdutoRepositoryPort produtoRepositoryPort) {
        return  new ProdutoService(produtoRepositoryPort);
    }
}
