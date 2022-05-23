package br.com.zup.edu.ingressos;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

public class ClienteRequest {

    @NotBlank
    private String nome;

    @NotBlank @CPF
    private String cpf;

    public ClienteRequest(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public ClienteRequest() {
    }

    public Cliente toModel(){
        return new Cliente(nome, cpf);
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }
}
