package caeta.techalanger.adapter.driver.controller.cliente.request;

import caeta.techalanger.adapter.driven.infra.repository.cliente.validator.UniqueCPF;
import caeta.techalanger.core.domain.Cliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public class ClienteRequest {

    @NotBlank
    String nome;
    @NotBlank
    @CPF
    @UniqueCPF
    String cpf;
    @NotBlank
    @Email
    String email;

    public ClienteRequest() {
    }

    public ClienteRequest(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public Cliente paraCliente(){
        return new Cliente(nome, cpf, email);

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
