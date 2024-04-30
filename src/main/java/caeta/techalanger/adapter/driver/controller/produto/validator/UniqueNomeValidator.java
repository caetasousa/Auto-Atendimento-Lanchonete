package caeta.techalanger.adapter.driver.controller.produto.validator;

import caeta.techalanger.adapter.driven.infra.repository.cliente.ClienteEntity;
import caeta.techalanger.adapter.driven.infra.repository.cliente.ClienteRepository;
import caeta.techalanger.adapter.driven.infra.repository.produto.ProdutoEntity;
import caeta.techalanger.adapter.driven.infra.repository.produto.ProdutoRepository;
import caeta.techalanger.adapter.driver.controller.cliente.validator.UniqueCPF;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.Optional;

public class UniqueNomeValidator implements ConstraintValidator<UniqueNome, String> {

    @Autowired
    ProdutoRepository repository;

    @Override
    public boolean isValid(String nome, ConstraintValidatorContext constraintValidatorContext) {
        Optional<ProdutoEntity> produto = repository.findByNome(nome);

        if (produto.isEmpty()) {
            return true;
        } else {
            Assert.state(produto.isPresent(), "Foi encontrado mais de um cpf");
            return false;
        }
    }
}
