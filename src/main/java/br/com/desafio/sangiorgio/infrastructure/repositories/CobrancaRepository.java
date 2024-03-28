package br.com.desafio.sangiorgio.infrastructure.repositories;

import br.com.desafio.sangiorgio.domain.model.Cobranca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CobrancaRepository extends JpaRepository<Cobranca, Long> {
}
