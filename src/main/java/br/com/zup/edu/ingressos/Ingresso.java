package br.com.zup.edu.ingressos;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Table(uniqueConstraints = {
        @UniqueConstraint(name = "Unique_ingresso_numero_data"
                , columnNames = {"numero", "data"})
})

@Entity
public class Ingresso {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Cliente cliente;

    @Column(nullable = false)
    private String numero;

    @Column(nullable = false)
    private LocalDate data;

    public Ingresso(Cliente cliente, String numero, LocalDate data) {
        this.cliente = cliente;
        this.numero = numero;
        this.data = data;
    }

    @Deprecated
    public Ingresso() {
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingresso ingresso = (Ingresso) o;
        return id.equals(ingresso.id) && cliente.equals(ingresso.cliente) && numero.equals(ingresso.numero) && data.equals(ingresso.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cliente, numero, data);
    }
}
