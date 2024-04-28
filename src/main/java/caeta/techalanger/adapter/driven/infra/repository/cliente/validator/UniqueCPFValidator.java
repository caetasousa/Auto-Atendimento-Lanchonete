package caeta.techalanger.adapter.driven.infra.repository.cliente.validator;

import caeta.techalanger.adapter.driven.infra.repository.cliente.ClienteEntity;
import caeta.techalanger.adapter.driven.infra.repository.cliente.ClienteRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.Optional;

public class UniqueCPFValidator implements ConstraintValidator<UniqueCPF, String> {

    @Autowired
    ClienteRepository repository;

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext constraintValidatorContext) {
        Optional<ClienteEntity> cliente = repository.findByCpf(cpf);

        if (cliente.isEmpty()) {
            return true;
        } else {
            Assert.state(cliente.isPresent(), "Foi encontrado mais de um cpf");
            return false;
        }
    }
}
