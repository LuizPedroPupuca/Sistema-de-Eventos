package br.com.zup.edu.ingressos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface IngressoRepository extends JpaRepository<Ingresso, Long> {
    boolean existsByNumeroAndData(String numero, LocalDate data);
}
