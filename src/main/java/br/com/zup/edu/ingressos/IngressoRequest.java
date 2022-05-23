package br.com.zup.edu.ingressos;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class IngressoRequest {

    @NotNull
    private ClienteRequest cliente;

    @NotBlank
    private String numero;

    @NotNull @Future
    private LocalDate data;

    public IngressoRequest(ClienteRequest cliente, String numero, LocalDate data) {
        this.cliente = cliente;
        this.numero = numero;
        this.data = data;
    }

    public IngressoRequest() {
    }

    public String getNumero() {
        return numero;
    }

    public LocalDate getData() {
        return data;
    }

    public ClienteRequest getCliente() {
        return cliente;
    }

    public Ingresso toModel(){
        return new Ingresso(cliente.toModel(), numero, data);
    }
}
